package dd.tsingtaopad.dd.ddweekplan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.business.visit.bean.AreaGridRoute;
import dd.tsingtaopad.core.net.HttpUrl;
import dd.tsingtaopad.core.net.RestClient;
import dd.tsingtaopad.core.net.callback.IError;
import dd.tsingtaopad.core.net.callback.IFailure;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.domain.RequestHeadStc;
import dd.tsingtaopad.core.net.domain.RequestStructBean;
import dd.tsingtaopad.core.net.domain.ResponseStructBean;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitPlandayvalMDao;
import dd.tsingtaopad.db.table.MitPlandayM;
import dd.tsingtaopad.db.table.MitPlandaydetailM;
import dd.tsingtaopad.db.table.MitPlandayvalM;
import dd.tsingtaopad.db.table.MitPlanweekM;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailValStc;
import dd.tsingtaopad.dd.ddweekplan.domain.DayPlanStc;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;
import dd.tsingtaopad.home.app.MainService;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.listviewintf.IClick;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdWeekPlanFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdWeekPlanFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    //
    public static final int WEEKPLAN_UP_SUC = 3101;
    //
    public static final int WEEKPLAN_UP_FAIL = 3102;

    private String time;
    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    private TextView weektimeTv;
    private Button submitTv;
    private ListView planLv;
    private WeekPlanAdapter adapter;

    private String weekDateStart = "";
    private String weekDateEnd = "";
    /*private String weekStart = "";
    private String weekEnd = "";*/

    List<DayPlanStc> dayPlanStcs = new ArrayList<DayPlanStc>();

    private DatabaseHelper helper;
    private List<MitPlanweekM> weekplanLst = new ArrayList<MitPlanweekM>();
    private MitPlanweekM mitPlanweekM;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_weekplan, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);

        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);


        weektimeTv = (TextView) view.findViewById(R.id.item_weekplan_tv_weektime);
        submitTv = (Button) view.findViewById(R.id.dd_weekplan_bt_submit);
        planLv = (ListView) view.findViewById(R.id.dd_weekplan_lv_);
        submitTv.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("我的计划");
        handler = new MyHandler(this);
        ConstValues.handler = handler;
        confirmTv.setText("日历");

        // 获取系统时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        initData();
        initUrlData();// 获取当前周计划

    }

    private void initData() {

        dayPlanStcs.clear();
        weekplanLst.clear();
        mitPlanweekM = new MitPlanweekM();
        mitPlanweekM.setStatus("0");
        helper = DatabaseHelper.getHelper(getActivity());

        try {
            Dao<MitPlanweekM, String> mitPlanweekMDao = helper.getMitPlanweekMDao();
            Dao<MitPlandayM, String> mitPlandayMDao = helper.getMitPlandayMDao();
            Dao<MitPlandaydetailM, String> mitPlandaydetailMDao = helper.getMitPlandaydetailMDao();
            MitPlandayvalMDao mitPlandayvalMDao = helper.getDao(MitPlandayvalM.class);

            calendar.setTime(this.calendar.getTime());//把随意设置的时间或者是系统的时间赋值给    caleendar

            // 初始化本周7天计划(即在MstPlanforuserM表中生成7条记录),若这周7条记录存在过则复用这7条记录
            for (int i = 1; i <= 7; i++) {
                calendar.add(Calendar.DAY_OF_WEEK, i - calendar.get(Calendar.DAY_OF_WEEK));
                Date date = calendar.getTime();
                //String plandate = DateUtil.formatDate(date, "yyyyMMdd");
                String plandate = DateUtil.formatDate(date, "yyyy-MM-dd");// 用于页面展示
                if (i == 1) {
                    // 获取本周的开始时间(定义为全局)
                    weekDateStart = plandate;
                    //weekStart = plandate;// 用于页面展示
                } else if (i == 7) {
                    // 获取本周的结束时间(定义为全局)
                    weekDateEnd = plandate;
                    //weekEnd = plandate;// 用于页面展示
                }

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("plandate", plandate);
                List<MitPlandayM> planList = mitPlandayMDao.queryForFieldValues(map);

                // 日计划记录(7条)
                DayPlanStc dayPlanStc = new DayPlanStc();
                dayPlanStc.setPlanKey(FunUtil.getUUID());
                dayPlanStc.setState("0");// 0:未制定 1等待提交 2待审核 3 审核通过  4 未通过
                dayPlanStc.setPlandate(plandate);
                dayPlanStc.setVisitTime(plandate);// // 用于页面展示
                dayPlanStc.setWeekday("周" + datePreview(i));// 周二
                if (planList.size() > 0) {
                    dayPlanStc.setPlanKey(planList.get(0).getId());
                    dayPlanStc.setState(planList.get(0).getStatus());
                }

                // 日计划详情
                List<DayDetailStc> detailStcs = mitPlandayvalMDao.queryPlanMitPlandaydetailM(helper, dayPlanStc.getPlanKey());
                // List<DayDetailStc> detailStcs = new ArrayList<>();
                String check = "";
                String route = "";
                String area = "";
                for (DayDetailStc dayDetailStc : detailStcs) {

                    // 日计划详情附表  追溯项
                    /*List<DayDetailValStc> dayDetailValStcs = mitPlandayvalMDao.queryPlanMitPlandayvalM(helper, dayDetailStc.getDetailkey(), "0");
                    List<String> checks = new ArrayList<>();
                    for (DayDetailValStc valStc : dayDetailValStcs) {
                        checks.add(valStc.getValcheckname());
                        // 追溯项汇总
                        if (!check.contains(valStc.getValcheckname())) {
                            check = valStc.getValcheckname() + "," + check;
                        }
                    }
                    dayDetailStc.setValchecknameLv(checks);*/
                    List<DayDetailValStc> dayDetailValStcs = mitPlandayvalMDao.queryPlanMitPlandayvalM(helper, dayDetailStc.getDetailkey(), "0");
                    StringBuffer checkkeys = new StringBuffer();
                    StringBuffer checknames = new StringBuffer();
                    for (DayDetailValStc valStc : dayDetailValStcs) {
                        checkkeys.append(valStc.getValcheckkey()).append(",");
                        checknames.append(valStc.getValcheckname()).append(",");
                        // 追溯项汇总
                        if (!check.contains(valStc.getValcheckname())) {
                            check = valStc.getValcheckname() + "," + check;
                        }
                    }
                    dayDetailStc.setValcheckkey(checkkeys.toString());
                    dayDetailStc.setValcheckname(checknames.toString());

                    // 日计划详情附表  路线
                    List<DayDetailValStc> valroutekeys = mitPlandayvalMDao.queryPlanMitPlandayvalMRoutes(helper, dayDetailStc.getDetailkey(), "1");
                    StringBuffer routekeys = new StringBuffer();
                    StringBuffer routenames = new StringBuffer();
                    for (DayDetailValStc detailValStc : valroutekeys) {
                        routekeys.append(detailValStc.getValroutekey()).append(",");
                        routenames.append(detailValStc.getValroutename()).append(",");
                        // 路线汇总
                        if (!route.contains(detailValStc.getValgridname() + "-" + detailValStc.getValroutename())) {
                            route = detailValStc.getValgridname() + "-" + detailValStc.getValroutename() + "," + route;
                        }
                        // 区域汇总
                        if (!area.contains(detailValStc.getValareaname())) {
                            area = detailValStc.getValareaname() + "," + area;
                        }
                    }
                    dayDetailStc.setValroutekeys(routekeys.toString());
                    dayDetailStc.setValroutenames(routenames.toString());
                    // detailStcs.add(dayDetailStc);
                }

                dayPlanStc.setPlancheck(check);//追溯项汇总
                dayPlanStc.setPlanroute(route);//追溯路线汇总
                dayPlanStc.setPlanareaid(area);//追溯区域汇总
                /*
                dayPlanStc.setPlangridid();//追溯定格
                */

                dayPlanStc.setDetailStcs(detailStcs);
                dayPlanStcs.add(dayPlanStc);
            }

            // 查找周计划
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("starttime", weekDateStart);
            map2.put("endtime", weekDateEnd);
            weekplanLst = mitPlanweekMDao.queryForFieldValues(map2);
            if (weekplanLst.size() > 0) {
                mitPlanweekM = weekplanLst.get(0);
            }

            // 修改状态
            for (DayPlanStc dayPlanStc : dayPlanStcs) {
                if ("2".equals(mitPlanweekM.getStatus())
                        || "3".equals(mitPlanweekM.getStatus())
                        || "4".equals(mitPlanweekM.getStatus())) {// 0未制定1未提交2待审核3审核通过4未通过
                    dayPlanStc.setState(mitPlanweekM.getStatus());
                }

            }

            adapter = new WeekPlanAdapter(getActivity(), dayPlanStcs, new IClick() {
                @Override
                public void listViewItemClick(int position, View v) {
                    toDayPlanFragment(position, dayPlanStcs, weekplanLst);
                }
            });
            planLv.setAdapter(adapter);

            weektimeTv.setText(weekDateStart + "  -  " + weekDateEnd);
        } catch (Exception e) {

        }

        // 提交按钮消失
        if ("2".equals(mitPlanweekM.getStatus())) {//  0未制定1未提交2待审核3审核通过4未通过
            submitTv.setText("等待审核");
            submitTv.setClickable(false);
            submitTv.setBackgroundColor(getResources().getColor(R.color.bg_content_color_gray));
        } else if ("3".equals(mitPlanweekM.getStatus())) {//  0未制定1未提交2待审核3审核通过4未通过
            submitTv.setText("审核通过");
            submitTv.setClickable(false);
            submitTv.setBackgroundColor(getResources().getColor(R.color.bg_content_color_gray));
        } else if ("4".equals(mitPlanweekM.getStatus())) {//  0未制定1未提交2待审核3审核通过4未通过
            submitTv.setText("重新提交");
            submitTv.setClickable(true);
            submitTv.setBackgroundColor(getResources().getColor(R.color.bg_content_color_gray));
        } else {
            submitTv.setText("提交");
            submitTv.setClickable(true);
            submitTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_select_green));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initUrlData() {
        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "starttime:'" + weekDateStart + "'," +
                "endtime:'" + weekDateEnd + "'," +
                "tablename:'" + "PLANWEEK" + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_ddplanweek", "PLANWEEK", content);
    }

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;

            case R.id.top_navigation_rl_confirm:// 确定
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
                        time = (Integer.toString(year) + "-" + String.format("%02d", monthOfYear + 1) + "-" + aday);

                        String workplan_tv_rounds = (getString(R.string.workplan_msg1) + calendar.get(Calendar.WEEK_OF_MONTH) + getString(R.string.workplan_msg2));
                        String tv_plantitle = (calendar.get(Calendar.YEAR) + getString(R.string.workplan_msg3) + (calendar.get(Calendar.MONTH) + 1) + getString(R.string.workplan_msg4) + calendar.get(Calendar.WEEK_OF_MONTH) + getString(R.string.workplan_msg5));

                        initData();
                        initUrlData();

                    }
                }, yearr, month, day);
                if (!dateDialog.isShowing()) {
                    dateDialog.show();
                }

                break;
            case R.id.dd_weekplan_bt_submit:// 提交周计划
                //
                saveWeekPlan();
                break;
            default:
                break;
        }
    }

    private void saveWeekPlan() {
        if (mitPlanweekM != null && !"".equals(mitPlanweekM.getId()) && !TextUtils.isEmpty(mitPlanweekM.getId())) {
            mitPlanweekM.setStatus("2");// 0未制定1未提交2待审核3审核通过4未通过
            // 上传周计划
            XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
            xtUploadService.uploadWeekPlan(false, mitPlanweekM.getId(), 1);

            submitTv.setText("等待审核");
            submitTv.setClickable(false);
            submitTv.setBackgroundColor(getResources().getColor(R.color.bg_content_color_gray));

        } else {
            Toast.makeText(getActivity(), "请先至少制定一条日计划", Toast.LENGTH_SHORT).show();
        }
    }


    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdWeekPlanFragment> fragmentRef;

        public MyHandler(DdWeekPlanFragment fragment) {
            fragmentRef = new SoftReference<DdWeekPlanFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdWeekPlanFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case WEEKPLAN_UP_SUC://
                    //fragment.shuaxinXtTermSelect(1);
                    break;
                case WEEKPLAN_UP_FAIL://
                    //fragment.shuaxinXtTermSelect(2);
                    break;
                case ConstValues.WAIT0://
                    fragment.refreshWeekFragment(1);// 日计划保存成功
                    break;
                case GlobalValues.SINGLE_UP_SUC://  周计划保存成功
                    fragment.refreshWeekFragment(2);
                    break;
                case GlobalValues.SINGLE_UP_FAIL://  周计划保存失败
                    fragment.refreshWeekFragment(3);
                    break;

            }
        }
    }

    // 结束上传  刷新页面  0:确定上传  1上传成功  2上传失败
    private void refreshWeekFragment(int upType) {
        if (1 == upType) {
            //Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
        } else if (2 == upType) {
            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
        } else if (3 == upType) {
            Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
        }
        initData();
    }

    // 跳转到日计划
    private void toDayPlanFragment(int position, List<DayPlanStc> workPlanStcs, List<MitPlanweekM> weekplanList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("dayplanstc", workPlanStcs.get(position));
        bundle.putSerializable("weekplan", mitPlanweekM);
        bundle.putSerializable("weekDateStart", weekDateStart);
        bundle.putSerializable("weekDateEnd", weekDateEnd);
        DdDayPlanFragment ddDayPlanFragment = new DdDayPlanFragment(handler);
        ddDayPlanFragment.setArguments(bundle);
        // 跳转日计划制定
        addHomeFragment(ddDayPlanFragment, "dddayplanfragment");
    }

    private String datePreview(int num) {
        String temp = null;
        switch (num) {
            case 1:
                temp = "日";

                break;
            case 2:
                temp = "一";

                break;
            case 3:
                temp = "二";

                break;
            case 4:
                temp = "三";

                break;
            case 5:
                temp = "四";

                break;
            case 6:
                temp = "五";

                break;
            case 7:
                temp = "六";
                break;

        }
        return temp;
    }

    /**
     * 同步表数据
     *
     * @param optcode 请求码
     * @param table   请求表名(请求不同的)
     * @param content 请求json
     */
    void ceshiHttp(final String optcode, final String table, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                .loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);

                        if ("".equals(json) || json == null) {
                            Toast.makeText(getActivity(), "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存登录信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                // 保存信息
                                if ("opt_get_ddplanweek".equals(optcode) && "PLANWEEK".equals(table)) {

                                    String formjson = resObj.getResBody().getContent();
                                    parseTableJson(formjson);
                                    initData();

                                /*Bundle bundle = new Bundle();
                                bundle.putString("msg","正在处理区域数据表");
                                Message msg = new Message();
                                msg.what = FirstFragment.SYNC_SUCCSE;//
                                msg.setData(bundle);
                                handler.sendMessage(msg);*/

                                    //Toast.makeText(getActivity(), "区域定格路线同步成功", Toast.LENGTH_SHORT).show();
                                    //initData();
                                }

                            } else {
                                Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            /*Message msg = new Message();
                            msg.what = FirstFragment.SYNC_CLOSE;//
                            handler.sendMessage(msg);*/
                                //initData();
                            }
                        }


                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        /*Message msg1 = new Message();
                        msg1.what = FirstFragment.SYNC_CLOSE;//
                        handler.sendMessage(msg1);*/
                        //initData();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        /*Message msg2 = new Message();
                        msg2.what = FirstFragment.SYNC_CLOSE;//
                        handler.sendMessage(msg2);*/
                        //initData();
                    }
                })
                .builde()
                .post();
    }

    // 解析区域定格路线成功
    private void parseTableJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MIT_PLANWEEK_M = emp.getMIT_PLANWEEK_M();
        String MIT_PLANDAY_M = emp.getMIT_PLANDAY_M();
        String MIT_PLANDAYDETAIL_M = emp.getMIT_PLANDAYDETAIL_M();
        String MIT_PLANDAYVAL_M = emp.getMIT_PLANDAYVAL_M();

        MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(MIT_PLANWEEK_M, "MIT_PLANWEEK_M", MitPlanweekM.class);
        service.createOrUpdateTable(MIT_PLANDAY_M, "MIT_PLANDAY_M", MitPlandayM.class);
        service.createOrUpdateTable(MIT_PLANDAYDETAIL_M, "MIT_PLANDAYDETAIL_M", MitPlandaydetailM.class);
        service.createOrUpdateTable(MIT_PLANDAYVAL_M, "MIT_PLANDAYVAL_M", MitPlandayvalM.class);
    }

}
