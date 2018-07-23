package et.tsingtaopad.dd.dddealplan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.business.visit.bean.AreaGridRoute;
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
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.db.table.MitRepairM;
import et.tsingtaopad.db.table.MitRepaircheckM;
import et.tsingtaopad.db.table.MitRepairterM;
import et.tsingtaopad.dd.dddealplan.domain.DealStc;
import et.tsingtaopad.dd.dddealplan.make.DdDealMakeFragment;
import et.tsingtaopad.dd.dddealplan.remake.DdReDealMakeFragment;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.listviewintf.IClick;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdDealPlanFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdDealPlanFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private DdDealPlanService ddDealPlanService;

    //
    public static final int DEALPLAN_UP_SUC = 3301;

    //
    public static final int DEALPLAN_UP_FAIL = 3302;

    public static final int DEALPLAN_NEED_UP = 3303;

    private TextView tv_month;
    private Button bt_addplan;
    private et.tsingtaopad.view.NoScrollListView monthplan_lv;
    private DdDealPlanAdapter dealPlanAdapter;
    private List<DealStc> dataLst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_dealplan, container, false);
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
        backBtn.setOnClickListener(this);

        tv_month = (TextView) view.findViewById(R.id.zgjh_tv_month);
        bt_addplan = (Button) view.findViewById(R.id.zgjh_bt_addplan);
        monthplan_lv = (et.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.zgjh_monthplan_lv);

        bt_addplan.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("整改计划");
        handler = new MyHandler(this);
        ConstValues.handler = handler;
        confirmTv.setText("");

        ddDealPlanService = new DdDealPlanService(getActivity());
        initData();
        initUrlData();
    }

    // 初始化数据
    private void initData() {

        dataLst = ddDealPlanService.getDealPlanTerminal();

        dealPlanAdapter = new DdDealPlanAdapter(getActivity(), dataLst, new IClick() {
            @Override
            public void listViewItemClick(int position, View v) {
                DealStc stc = dataLst.get(position);

                int id = v.getId();
                switch (id) {
                    case R.id.item_dealplan_rl_termname:// 终端名称
                        if("0".equals(stc.getIsshow())){
                            stc.setIsshow("1");
                        }else if("1".equals(stc.getIsshow())){
                            stc.setIsshow("0");
                        }
                        dealPlanAdapter.notifyDataSetChanged();
                        break;
                    case R.id.item_dealplan_rl_checkstatus:// 复查结果
                        String status = stc.getRepairstatus();
                        if ("1".equals(status)) {// 未通过
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("DealStc", stc);
                            /*bundle.putSerializable("weekplan", mitPlanweekM);
                            bundle.putSerializable("weekDateStart", weekDateStart);
                            bundle.putSerializable("weekDateEnd", weekDateEnd);*/
                            DdReDealMakeFragment ddReDealMakeFragment = new DdReDealMakeFragment(handler);
                            ddReDealMakeFragment.setArguments(bundle);
                            // 跳转 新增整改计划
                            addHomeFragment(ddReDealMakeFragment, "ddredealmakefragment");
                        } else if ("2".equals(status)) {// 已通过

                        } else {
                            alertShow6(position);// 弹窗: 未通过,已通过
                        }
                        break;

                }



            }
        });
        monthplan_lv.setAdapter(dealPlanAdapter);
        ViewUtil.setListViewHeight(monthplan_lv);

    }

    private void initUrlData() {
        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "tablename:'" + "MIT_REPAIR_REPAIRTER_REPAIRCHECK_M" + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_repair_ter_check", "MIT_REPAIR_REPAIRTER_REPAIRCHECK_M", content);
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
                                String formjson = resObj.getResBody().getContent();
                                parseTableJson(formjson);
                                initData();

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
        String MIT_REPAIR_M = emp.getMIT_REPAIR_M();
        String MIT_REPAIRTER_M = emp.getMIT_REPAIRTER_M();
        String MIT_REPAIRCHECK_M = emp.getMIT_REPAIRCHECK_M();

        MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(MIT_REPAIR_M, "MIT_REPAIR_M", MitRepairM.class);
        service.createOrUpdateTable(MIT_REPAIRTER_M, "MIT_REPAIRTER_M", MitRepairterM.class);
        service.createOrUpdateTable(MIT_REPAIRCHECK_M, "MIT_REPAIRCHECK_M", MitRepaircheckM.class);
    }

    /**
     * 弹窗6
     * 参数1: 标题 √
     * 参数2: 主体内容    ×
     * 参数3: 取消按钮    √
     * 参数4: 高亮按钮 数组 ×
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (只有取消按钮)   √
     * 参数8: 条目点击监听  √
     */
    DealStc stc ;
    private AlertView mAlertViewExt;//窗口拓展例子
    public void alertShow6(final int posi) {
        mAlertViewExt = new AlertView("请选择复查结果", null, "取消", null,
                new String[]{"未通过","已通过"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        stc = dataLst.get(posi);
                        String repaircheckid = stc.getRepaircheckid();
                        String repairid = stc.getRepairid();
                        if (0 == position) {// 未通过
                            ddDealPlanService.setStatus(repairid,repaircheckid, "1");// 并修改未未上传
                            stc.setRepairstatus("1");
                            handler.sendEmptyMessage(DEALPLAN_NEED_UP);
                        } else if(1 == position) {// 已通过
                            ddDealPlanService.setStatus(repairid,repaircheckid, "2");// 并修改未未上传
                            stc.setRepairstatus("2");
                            handler.sendEmptyMessage(DEALPLAN_NEED_UP);
                        }

                    }
                });
        mAlertViewExt.setCancelable(true).show();
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
                Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zgjh_bt_addplan:// 新增整顿计划
                // 跳转到  新增整改计划
                toDdDealMakeFragment();
                break;

            default:
                break;
        }
    }

    // 跳转到  新增整改计划
    private void toDdDealMakeFragment() {
        Bundle bundle = new Bundle();
        /*bundle.putSerializable("dayplanstc", workPlanStcs.get(position));
        bundle.putSerializable("weekplan", mitPlanweekM);
        bundle.putSerializable("weekDateStart", weekDateStart);
        bundle.putSerializable("weekDateEnd", weekDateEnd);*/
        DdDealMakeFragment ddDealMakeFragment = new DdDealMakeFragment(handler);
        ddDealMakeFragment.setArguments(bundle);

        // 跳转 新增整改计划
        addHomeFragment(ddDealMakeFragment, "dddealmakefragment");

    }


    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdDealPlanFragment> fragmentRef;

        public MyHandler(DdDealPlanFragment fragment) {
            fragmentRef = new SoftReference<DdDealPlanFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdDealPlanFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }


            // 处理UI 变化
            switch (msg.what) {
                case DEALPLAN_UP_SUC://
                    fragment.shuaxinFragment();
                    break;
                case DEALPLAN_NEED_UP://
                    fragment.upRepair();
                    break;

            }
        }
    }

    // 上传未通过 或已通过
    private void upRepair() {

        //initData();

        dealPlanAdapter.notifyDataSetChanged();


        // 上传整顿计划
        MitRepairM repairM = new MitRepairM();
        repairM.setId(stc.getRepairid());
        repairM.setGridkey(stc.getGridkey());//定格
        repairM.setUserid(stc.getUserid());// 业代ID
        repairM.setContent(stc.getContent());//问题描述
        repairM.setRepairremark(stc.getRepairremark());//改进计划
        repairM.setCheckcontent(stc.getCheckcontent());//考核措施
        repairM.setCreuser(PrefUtils.getString(getActivity(), "userid", ""));//追溯人
        repairM.setCreuserareaid(PrefUtils.getString(getActivity(), "departmentid", ""));//追溯人所属区域
        ////repairM.setCredate(new Date());//创建日期
        repairM.setCredate(stc.getCredate());//创建日期
        repairM.setUpdateuser(PrefUtils.getString(getActivity(), "userid", ""));//更新人
        repairM.setUpdatedate(DateUtil.getDateTimeStr(8));//更新时间
        repairM.setUploadflag("1");
        repairM.setPadisconsistent("0");
        repairM.setStatus(stc.getRepairstatus());

        XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
        xtUploadService.upload_repair(false, repairM, null, 1);
    }

    // 修改成功   刷新页面
    private void shuaxinFragment() {
        List<DealStc> newdataLst = ddDealPlanService.getDealPlanTerminal();
        for (DealStc newdealStc : newdataLst){
            for (DealStc dealStc :dataLst){
                if(newdealStc.getRepairid().equals(dealStc.getRepairid())){
                    newdealStc.setIsshow(dealStc.getIsshow());
                }
            }
        }
        dataLst.clear();
        dataLst.addAll(newdataLst);
        // dealPlanAdapter.notifyDataSetChanged();
        dealPlanAdapter = new DdDealPlanAdapter(getActivity(), dataLst, new IClick() {
            @Override
            public void listViewItemClick(int position, View v) {
                DealStc stc = dataLst.get(position);

                int id = v.getId();
                switch (id) {
                    case R.id.item_dealplan_rl_termname:// 终端名称
                        if("0".equals(stc.getIsshow())){
                            stc.setIsshow("1");
                        }else if("1".equals(stc.getIsshow())){
                            stc.setIsshow("0");
                        }
                        dealPlanAdapter.notifyDataSetChanged();
                        break;
                    case R.id.item_dealplan_rl_checkstatus:// 复查结果
                        String status = stc.getRepairstatus();
                        if ("1".equals(status)) {// 未通过
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("DealStc", stc);
                            /*bundle.putSerializable("weekplan", mitPlanweekM);
                            bundle.putSerializable("weekDateStart", weekDateStart);
                            bundle.putSerializable("weekDateEnd", weekDateEnd);*/
                            DdReDealMakeFragment ddReDealMakeFragment = new DdReDealMakeFragment(handler);
                            ddReDealMakeFragment.setArguments(bundle);
                            // 跳转 新增整改计划
                            addHomeFragment(ddReDealMakeFragment, "ddredealmakefragment");
                        } else if ("2".equals(status)) {// 已通过

                        } else {
                            alertShow6(position);// 弹窗: 未通过,已通过
                        }
                        break;

                }



            }
        });
        monthplan_lv.setAdapter(dealPlanAdapter);
        ViewUtil.setListViewHeight(monthplan_lv);

    }

    // 监听返回键
    @Override
    public boolean onBackPressed() {
        if (mAlertViewExt != null && mAlertViewExt.isShowing()) {
            mAlertViewExt.dismiss();
            return true;
        } else {
            return super.onBackPressed();
        }
    }

}
