package et.tsingtaopad.business.first;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.business.first.bean.FirstDataStc;
import et.tsingtaopad.business.first.bean.GvTop;
import et.tsingtaopad.business.first.bean.LvTop;
import et.tsingtaopad.business.first.bean.XtZsNumStc;
import et.tsingtaopad.business.visit.SyncBasicFragment;
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
import et.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.db.table.MstTerminalinfoMCart;
import et.tsingtaopad.db.table.MstTerminalinfoMZsCart;
import et.tsingtaopad.dd.ddagencycheck.DdAgencyCheckSelectFragment;
import et.tsingtaopad.dd.dddaysummary.DdDaySummaryFragment;
import et.tsingtaopad.dd.dddealplan.DdDealPlanFragment;
import et.tsingtaopad.dd.ddmeeting.MeetingFragment;
import et.tsingtaopad.dd.ddxt.term.cart.XtTermCartFragment;
import et.tsingtaopad.dd.ddxt.term.select.XtTermListFragment;
import et.tsingtaopad.dd.ddzs.zsterm.zscart.ZsTermCartSdlvFragment;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.ZsTemplateFragment;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.sign.DdSignActivity;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class FirstFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "FirstFragment";


    private TextView tv_username;
    private TextView tv_zhuisu;
    private TextView tv_xietong;
    private ImageView signImg;
    private et.tsingtaopad.view.MyGridView gv_top;
    private et.tsingtaopad.view.NoScrollListView sclv_top;

    private FirstGvTopAdapter gvTopAdapter;
    private FirstLvTopAdapter lvTopAdapter;

    List<GvTop> gvTopList;
    List<LvTop> lvTopList;

    List<XtZsNumStc> xtZsNumStcs = new ArrayList<>();

    private RelativeLayout rl_xtbf;
    private RelativeLayout rl_zdzs;
    private RelativeLayout rl_kcpd;
    private RelativeLayout rl_zgjh;
    private RelativeLayout rl_jhzd;
    private RelativeLayout rl_gzzj;

    MainService service;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        tv_username = (TextView) view.findViewById(R.id.first_tv_username);
        tv_zhuisu = (TextView) view.findViewById(R.id.first_tv_zhuisu);
        tv_xietong = (TextView) view.findViewById(R.id.first_tv_xietong);
        signImg = (ImageView) view.findViewById(R.id.first_img_sign);

        rl_xtbf = (RelativeLayout) view.findViewById(R.id.first_rl_xtbf);
        rl_zdzs = (RelativeLayout) view.findViewById(R.id.first_rl_zdzs);
        rl_kcpd = (RelativeLayout) view.findViewById(R.id.first_rl_kcpd);
        rl_zgjh = (RelativeLayout) view.findViewById(R.id.first_rl_zgjh);
        rl_jhzd = (RelativeLayout) view.findViewById(R.id.first_rl_jhzd);
        rl_gzzj = (RelativeLayout) view.findViewById(R.id.first_rl_gzzj);

        gv_top = (et.tsingtaopad.view.MyGridView) view.findViewById(R.id.first_gv_top);
        sclv_top = (et.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.first_sclv_top);

        signImg.setOnClickListener(this);
        rl_xtbf.setOnClickListener(this);
        rl_zdzs.setOnClickListener(this);
        rl_kcpd.setOnClickListener(this);
        rl_zgjh.setOnClickListener(this);
        rl_jhzd.setOnClickListener(this);
        rl_gzzj.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        tv_username.setText(PrefUtils.getString(getActivity(), "username", ""));

        gvTopList = new ArrayList<>();

        gvTopAdapter = new FirstGvTopAdapter(getActivity(), gvTopList);
        gv_top.setAdapter(gvTopAdapter);

        lvTopList = new ArrayList<>();
        lvTopAdapter = new FirstLvTopAdapter(getActivity(), lvTopList, null);
        sclv_top.setAdapter(lvTopAdapter);


        // 基础信息不完整,请先同步数据
        /*if (getCmmAreaMCount() > 0) {
            // changeHomeFragment(new DdWeekPlanFragment(), "ddweekplanfragment");

        } else {
            // Toast.makeText(getActivity(), "基础信息不完整,请先同步数据", Toast.LENGTH_SHORT).show();
            changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
        }*/

        String json = PrefUtils.getString(getActivity(), "firstjson", "");
        if (!TextUtils.isEmpty(json)) {// 读取预存数据展示
            parseFirstJson(json);
        }

        service = new MainService(getActivity(), null);

    }

    // 创建json 发起请求
    private void initUrlData() {
        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "time:'" + DateUtil.getDateTimeStr(8) + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        getFirstData("opt_get_first_data", content);
    }

    /**
     * 请求首页数据
     *
     * @param optcode 请求码
     * @param content 请求json
     */
    void getFirstData(final String optcode, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getActivity());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);
        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                // .loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);

                        if ("".equals(json) || json == null) {
                            Toast.makeText(getActivity(), "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 处理信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                // 处理信息
                                String formjson = resObj.getResBody().getContent();
                                PrefUtils.putString(getActivity(), "firstjson", formjson);
                                parseFirstJson(formjson);

                            } else {
                                Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        // Toast.makeText(getActivity(), "首页数据请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 解析返回的数据
    private void parseFirstJson(String formjson) {
        FirstDataStc emp = JsonUtil.parseJson(formjson, FirstDataStc.class);
        xtZsNumStcs = JsonUtil.parseList(emp.getHomesynergyandcheck(), XtZsNumStc.class);
        List<GvTop> gvTops = JsonUtil.parseList(emp.getHomesevendayrank(), GvTop.class);
        List<LvTop> lvTops = JsonUtil.parseList(emp.getSevendayindexrank(), LvTop.class);

        if (gvTops != null && gvTops.size() > 0) {
            gvTopList.clear();
            gvTopList.addAll(gvTops);
        }

        if (lvTops != null && gvTops.size() > 0) {
            lvTopList.clear();
            lvTopList.addAll(lvTops);
        }

        initJsonData();
    }

    // 刷新界面数据
    private void initJsonData() {
        tv_zhuisu.setText("追溯数量: " + xtZsNumStcs.get(0).getCountnum());
        tv_xietong.setText("协同数量: " + xtZsNumStcs.get(1).getCountnum());

        gvTopAdapter.notifyDataSetChanged();
        lvTopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_img_sign:// 跳转打卡
                if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    startDdSignActivity();
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
                }
                break;

            case R.id.first_rl_xtbf:// 协同拜访
                if (getCmmAreaMCount() > 0) {
                    // changeHomeFragment(new XtTermSelectFragment(), "xttermlistfragment");
                    changeHomeFragment(new XtTermListFragment(), "xttermlistfragment");
                } else {

                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                /*if (getCmmAreaMCount() > 0) {
                    // 终端夹隔天清零
                    String addTime = PrefUtils.getString(getActivity(), GlobalValues.XT_CART_TIME, DateUtil.getDateTimeStr(7));
                    if(!DateUtil.getDateTimeStr(7).equals(addTime)){
                        // 隔天清空购物车表数据
                        service.deleteCart("MST_TERMINALINFO_M_CART","1");
                    }

                    toXtTermCartFragment();
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }*/

                break;
            case R.id.first_rl_zdzs:// 终端追溯
                /*if (getCmmAreaMCount() > 0) {
                    // changeHomeFragment(new ZsTermSelectFragment(), "zstermselectfragment");
                    //changeHomeFragment(new ZsTermCheckFragment(), "zstermcheckfragment");
                    changeHomeFragment(new ZsTermGetFragment(), "zstermgetfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }*/

                if (getCmmAreaMCount() > 0) {
                    // 终端夹隔天清零
                    String addTime = PrefUtils.getString(getActivity(), GlobalValues.ZS_CART_TIME, DateUtil.getDateTimeStr(7));
                    if (!DateUtil.getDateTimeStr(7).equals(addTime)) {
                        // 终端夹隔天清零
                        service.deleteCart("MST_TERMINALINFO_M_ZSCART", "2");
                    }
                    toZsTermCartFragment();
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.first_rl_kcpd:// 库存盘点
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdAgencyCheckSelectFragment(), "ddagencycheckselectfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.first_rl_zgjh:// 整改计划
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdDealPlanFragment(), "dddealplanfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.first_rl_jhzd:// 会议记录
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new MeetingFragment(), "ddweekplanfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.first_rl_gzzj:// 工作总结
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdDaySummaryFragment(), "dddaysummaryfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            default:
                break;
        }
    }

    // 跳转协同终端文件夹
    private void toXtTermCartFragment() {
        List<MstTerminalinfoMCart> valueLst = service.getMstTerminalinfoMCartList();
        if(valueLst.size()>0){
            Bundle bundle = new Bundle();
            bundle.putSerializable("fromFragment", "VisitFragment");
            XtTermCartFragment xtTermCartFragment = new XtTermCartFragment();
            xtTermCartFragment.setArguments(bundle);
            changeHomeFragment(xtTermCartFragment, "xttermcartfragment");
        }else{
            // Toast.makeText(getActivity(),"追溯终端夹暂无数据",Toast.LENGTH_SHORT).show();
            new AlertView("终端夹暂无数据是否去添加", null, null, new String[]{"取消","确定"}, null, getActivity(), AlertView.Style.Alert,
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                            //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                            if (0 == position) {// 取消

                            }else if(1 == position){// 确定
                                // changeHomeFragment(new XtTermSelectFragment(), "xttermlistfragment");
                            }
                        }
                    })
                    .setCancelable(true)
                    .setOnDismissListener(null)
                    .show();
        }
    }

    // 跳转追溯终端文件夹
    private void toZsTermCartFragment() {
        List<MstTerminalinfoMZsCart> valueLst = service.getMstTerminalinfoMZsCartList();
        if(valueLst.size()>0){
            Bundle zsBundle = new Bundle();
            zsBundle.putSerializable("fromFragment", "VisitFragment");
            // ZsTermCartFragment zsTermCartFragment = new ZsTermCartFragment();
            ZsTermCartSdlvFragment zsTermCartFragment = new ZsTermCartSdlvFragment();
            zsTermCartFragment.setArguments(zsBundle);
            changeHomeFragment(zsTermCartFragment, "zstermcartfragment");
        }else{
            // Toast.makeText(getActivity(),"协同终端夹暂无数据",Toast.LENGTH_SHORT).show();

            new AlertView("终端夹暂无数据是否去添加", null, null, new String[]{"取消","确定"}, null, getActivity(), AlertView.Style.Alert,
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                            //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                            if (0 == position) {// 取消

                            }else if(1 == position){// 确定
                                // changeHomeFragment(new ZsTermSelectFragment(), "zstermselectfragment");
                                // changeHomeFragment(new ZsTermCheckFragment(), "zstermselectfragment");
                                // changeHomeFragment(new ZsTermGetFragment(), "zstermgetfragment"); 专项追溯
                                changeHomeFragment(new ZsTemplateFragment(), "zstemplatefragment");// 督导模板
                            }
                        }
                    })
                    .setCancelable(true)
                    .setOnDismissListener(null)
                    .show();
        }
    }

    @Override
    public void doLocation() {
        startDdSignActivity();
    }

    // // 签到
    private void startDdSignActivity() {

        // 检查网络
        if (NetStatusUtil.isNetValid(getContext())) {
            Intent intent = new Intent(getActivity(), DdSignActivity.class);
            startActivity(intent);
        } else {
            // 提示修改网络
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("网络错误");
            builder.setMessage("请连接好网络,在进行打卡");
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getContext().startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                }
            }).create().show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initUrlData(); // 在此请求数据 首页数据
        }
    }
}
