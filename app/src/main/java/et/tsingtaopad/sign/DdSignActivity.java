package et.tsingtaopad.sign;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseActivity;
import et.tsingtaopad.core.net.HttpUrl;
import et.tsingtaopad.core.net.RestClient;
import et.tsingtaopad.core.net.callback.IError;
import et.tsingtaopad.core.net.callback.IFailure;
import et.tsingtaopad.core.net.callback.ISuccess;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.sign.bean.SignStc;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdSignActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "DdSignActivity";

    private LinearLayout ll_title;
    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    public static final int DD_AGENCY_CHECKSELECT_SUC = 1101;//
    public static final int DD_AGENCY_CHECKSELECT_FAIL = 1102;//

    private ImageView first_img_on_sign;
    private ImageView first_img_down_sign;
    private LinearLayout first_img_click_sign;
    private TextView tv_type;
    private TextView tv_time;
    private et.tsingtaopad.view.NoScrollListView first_lv_sign;

    MyHandler handler;

    private DdSignAdapter signAdapter;

    private String attencetype = "0";// 0:上班打卡  , 1:下班打卡

    private String currenttime;// 2011-04-11
    private String todaytime;// 2011-04-11

    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;

    List<SignStc> signStcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dd_sign);
        initView();

        if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
            // 拥有了此权限,那么直接执行业务逻辑
            registerGPS();
        } else {
            // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
            requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
        }

        initData();
        // 刚进入 获取打卡信息
        getSignData();
    }

    // 初始化控件
    private void initView() {
        ll_title = (LinearLayout) findViewById(R.id.top_ll_title);
        backBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        //titleTv.setOnClickListener(this);

        first_img_on_sign = (ImageView) findViewById(R.id.first_img_on_sign);
        first_img_down_sign = (ImageView) findViewById(R.id.first_img_down_sign);
        first_img_click_sign = (LinearLayout) findViewById(R.id.first_img_click_sign);
        tv_type = (TextView) findViewById(R.id.first_tv_type);
        tv_time = (TextView) findViewById(R.id.first_tv_time);
        first_lv_sign = (et.tsingtaopad.view.NoScrollListView) findViewById(R.id.first_lv_sign);
        first_img_click_sign.setOnClickListener(this);

    }

    // 初始化数据
    private void initData() {

        titleTv.setText("考勤管理");
        confirmTv.setText("");
        // ll_title.setBackgroundResource(R.color.tab_select);
        confirmTv.setBackgroundResource(R.drawable.icon_work_time);
        confirmTv.setWidth(10);
        confirmTv.setHeight(10);
        handler = new MyHandler(this);

        // 获取系统时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        currenttime = DateUtil.getDateTimeStr(7);
        todaytime = DateUtil.getDateTimeStr(7);

        //实时更新时间（1秒更新一次）
        TimeThread timeThread = new TimeThread(tv_time);//tvDate 是显示时间的控件TextView
        timeThread.start();//启动线程


        signStcs = new ArrayList<>();
        /*signStcs.add(new SignStc("2018-06-06 09:52:08", "0", "1号地址", "哈哈哈"));
        signStcs.add(new SignStc("2018-06-06 10:12:11", "1", "2号地址", ""));
        signStcs.add(new SignStc("2018-06-06 14:52:51", "1", "3号地址", ""));
        signStcs.add(new SignStc("2018-06-06 18:32:42", "1", "4号地址", ""));
        signStcs.add(new SignStc("2018-06-06 19:32:42", "1", "5号地址", ""));*/

        signAdapter = new DdSignAdapter(this, signStcs, null);
        first_lv_sign.setAdapter(signAdapter);
        ViewUtil.setListViewHeight(first_lv_sign);


    }


    // 按钮点击 监听
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.top_navigation_rl_back:// 返回
                DdSignActivity.this.finish();
                break;
            case R.id.top_navigation_rl_confirm://
                if (ViewUtil.isDoubleClick(v.getId(), 2000))
                    return;
                // 日历
                //Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        yearr = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        if (dayOfMonth < 10) {
                            aday = "0" + dayOfMonth;
                        } else {
                            aday = Integer.toString(dayOfMonth);
                        }
                        currenttime = (Integer.toString(year) + "-" + String.format("%02d", monthOfYear + 1) + "-" + aday);

                        // 刚进入 获取打卡信息
                        getSignData();

                    }
                }, yearr, month, day);
                if (!dateDialog.isShowing()) {
                    dateDialog.show();
                }

                break;
            case R.id.first_img_click_sign://
                if (ViewUtil.isDoubleClick(v.getId(), 2000))
                    return;
                //发送签到请求
                saveSignData();

                break;

            default:
                break;
        }
    }

    // 刚进入 获取打卡信息
    private void getSignData() {

        String content = "{" +
                "areaid:'" + PrefUtils.getString(this, "departmentid", "") + "'," +
                "attencetime:'" + currenttime + "'," +
                "creuser:'" + PrefUtils.getString(this, "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_sign_data", content);
    }

    // 发送打卡信息
    private void saveSignData() {

        // String s = "[{'MIT_ATTENCEDETAIL_M':["+"]}]";
        String content = "{" +
                "areaid:'" + PrefUtils.getString(this, "departmentid", "") + "'," +
                "sourcelon:'" + longitude + "'," +  //"117.090734350000005000"  longitude
                "sourcelat:'" + latitude + "'," +  // "24.050067309999999300"   latitude
                "attencetype:'" + attencetype + "'," +
                "attencetime:'" + DateUtil.getDateTimeStr(8) + "'," +
                "creuser:'" + PrefUtils.getString(this, "userid", "") + "'" +
                "}";
        ceshiHttp("opt_save_sign_data", content);
    }

    /**
     * 打卡
     *
     * @param optcode 请求码
     * @param content 请求json
     */
    void ceshiHttp(final String optcode, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(this);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);
        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                .loader(DdSignActivity.this)// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);

                        if ("".equals(json) || json == null) {
                            Toast.makeText(DdSignActivity.this, "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存登录信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {

                                if (optcode.equals("opt_get_sign_data")) {
                                    // 保存信息
                                    String formjson = resObj.getResBody().getContent();
                                    parseAttenceJson(formjson);
                                } else if (optcode.equals("opt_save_sign_data")) {
                                    // 保存信息
                                    String formjson = resObj.getResBody().getContent();
                                    parseAttenceJson(formjson);
                                    Toast.makeText(DdSignActivity.this,"打卡成功",Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(DdSignActivity.this, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(DdSignActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(DdSignActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    private void parseAttenceJson(String formjson) {
        List<SignStc> signs = JsonUtil.parseList(formjson, SignStc.class);
        if (signs.size() > 0) {
            attencetype = "1";
            tv_type.setText("下班打卡");
        } else {
            attencetype = "0";
            tv_type.setText("上班打卡");
        }

        // 当前时间 == 今天
        if (currenttime.equals(todaytime)) {// 可点击
            first_img_click_sign.setEnabled(true);
            // first_img_click_sign.setClickable(true);
        } else {// 不可点击
            first_img_click_sign.setEnabled(false);
            // first_img_click_sign.setClickable(false);
        }

        signStcs.clear();
        signStcs.addAll(signs);

        initJsonData();
    }

    private void initJsonData() {
        signAdapter.notifyDataSetChanged();
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdSignActivity> fragmentRef;

        public MyHandler(DdSignActivity fragment) {
            fragmentRef = new SoftReference<DdSignActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdSignActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_AGENCY_CHECKSELECT_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_AGENCY_CHECKSELECT_FAIL: // 督导输入数据后
                    //fragment.showAdapter();
                    break;
            }
        }
    }


    // 原生经纬度 处理 --------------------------------------------------------

    private double longitude;// 经度
    private double latitude;// 维度
    public LocationManager lm;

    // 拥有定位权限 开启注册定位
    @Override
    public void doLocation() {
        registerGPS();
    }

    private void registerGPS() {

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //判断GPS是否正常启动
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0);
            return;
        }

        //为获取地理位置信息时设置查询条件
        String bestProvider = lm.getBestProvider(getCriteria(), true);
        //获取位置信息
        //如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
        Location location = lm.getLastKnownLocation(bestProvider);
        //        Location location= lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        updateView(location);
        //监听状态
        lm.addGpsStatusListener(listener);
        //绑定监听，有4个参数
        //参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
        //参数2，位置信息更新周期，单位毫秒
        //参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
        //参数4，监听
        //备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新

        // 1秒更新一次，或最小位移变化超过1米更新一次；
        //注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
    }

    //位置监听
    private LocationListener locationListener = new LocationListener() {

        /**
         * 位置信息变化时触发
         */
        public void onLocationChanged(Location location) {
            updateView(location);
            Log.i(TAG, "时间：" + location.getTime());
            Log.i(TAG, "经度：" + location.getLongitude());
            Log.i(TAG, "纬度：" + location.getLatitude());
            Log.i(TAG, "海拔：" + location.getAltitude());
        }

        /**
         * GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    Log.i(TAG, "当前GPS状态为可见状态");
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    Log.i(TAG, "当前GPS状态为服务区外状态");
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.i(TAG, "当前GPS状态为暂停服务状态");
                    break;
            }
        }

        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            Location location = lm.getLastKnownLocation(provider);
            updateView(location);
        }

        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
            updateView(null);
        }
    };

    //状态监听
    GpsStatus.Listener listener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            switch (event) {
                //第一次定位
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    Log.i(TAG, "第一次定位");
                    break;
                //卫星状态改变
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Log.i(TAG, "卫星状态改变");
                    //获取当前状态
                    /*GpsStatus gpsStatus=lm.getGpsStatus(null);
                    //获取卫星颗数的默认最大值
                    int maxSatellites = gpsStatus.getMaxSatellites();
                    //创建一个迭代器保存所有卫星
                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                    int count = 0;
                    while (iters.hasNext() && count <= maxSatellites) {
                        GpsSatellite s = iters.next();
                        count++;
                    }
                    System.out.println("搜索到："+count+"颗卫星");
                    tv_gps.setText("搜索到："+count+"颗卫星");*/
                    break;
                //定位启动
                case GpsStatus.GPS_EVENT_STARTED:
                    Log.i(TAG, "定位启动");
                    break;
                //定位结束
                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.i(TAG, "定位结束");
                    break;
            }
        }

        ;
    };

    /**
     * 实时更新文本内容
     *
     * @param location
     */
    private void updateView(Location location) {
        if (location != null) {
            // 经度
            longitude = location.getLongitude();
            // 纬度
            latitude = location.getLatitude();
        } else {
            //清空EditText对象
            //editText.getEditableText().clear();
        }
    }

    /**
     * 返回查询条件
     *
     * @return
     */
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(locationListener);
    }

}
