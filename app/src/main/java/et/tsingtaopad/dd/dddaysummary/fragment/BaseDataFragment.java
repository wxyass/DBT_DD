package et.tsingtaopad.dd.dddaysummary.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
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
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.dd.dddaysummary.DdDaySummaryFragment;
import et.tsingtaopad.dd.dddaysummary.domain.BaseDataStc;
import et.tsingtaopad.dd.dddaysummary.domain.DaySummaryStc;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 基础数据群
 * Created by yangwenmin on 2018/3/12.
 */

public class BaseDataFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "BaseDataFragment";

    //
    public static final int DEALPLAN_UP_SUC = 3301;

    //
    public static final int DEALPLAN_UP_FAIL = 3302;

    public static final int DEALPLAN_NEED_UP = 3303;

    private TextView tv_time;
    private TextView operation_basedata_num;
    private TextView operation_basedata_percent;
    private TextView operation_basedata_netnum;
    private TextView operation_basedata_netpercent;
    private TextView operation_basedata_pricenum;
    private TextView operation_basedata_pricepercent;
    private TextView operation_basedata_cmpnum;
    private TextView operation_basedata_cmppercent;
    private TextView operation_basedata_promotionnum;
    private TextView operation_basedata_promotionpercent;
    private TextView operation_basedata_basenum;
    private TextView operation_basedata_basepercent;
    private TextView operation_basedata_sumnum;
    private TextView operation_basedata_sumpercent;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_operation_basedata, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        tv_time = (TextView) view.findViewById(R.id.operation_basedata_tv_time);
        operation_basedata_num = (TextView) view.findViewById(R.id.operation_basedata_basenum);
        operation_basedata_percent = (TextView) view.findViewById(R.id.operation_basedata_basepercent);
        operation_basedata_netnum = (TextView) view.findViewById(R.id.operation_basedata_netnum);
        operation_basedata_netpercent = (TextView) view.findViewById(R.id.operation_basedata_netpercent);
        operation_basedata_pricenum = (TextView) view.findViewById(R.id.operation_basedata_pricenum);
        operation_basedata_pricepercent = (TextView) view.findViewById(R.id.operation_basedata_pricepercent);
        operation_basedata_cmpnum = (TextView) view.findViewById(R.id.operation_basedata_cmpnum);
        operation_basedata_cmppercent = (TextView) view.findViewById(R.id.operation_basedata_cmppercent);
        operation_basedata_promotionnum = (TextView) view.findViewById(R.id.operation_basedata_promotionnum);
        operation_basedata_promotionpercent = (TextView)view. findViewById(R.id.operation_basedata_promotionpercent);
        operation_basedata_basenum = (TextView) view.findViewById(R.id.operation_basedata_xieyinum);
        operation_basedata_basepercent = (TextView) view.findViewById(R.id.operation_basedata_xieyipercent);
        operation_basedata_sumnum = (TextView)view. findViewById(R.id.operation_basedata_sumnum);
        operation_basedata_sumpercent = (TextView)view.findViewById(R.id.operation_basedata_sumpercent);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        initData();
        // initUrlData();
    }

    // 初始化数据
    private void initData() {

        tv_time.setText(PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7)));
    }

    private void initUrlData() {
        String currenttime = PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7));

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "tablename:'" + "basicdata" + "'," +
                "credate:'" + currenttime + "'," + // currenttime
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_dailyrecord", "basicdata", content);
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
                //.loader(getContext())// 滚动条
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

                            } else {
                                Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 解析数据
    private void parseTableJson(String json) {
        DaySummaryStc daySummaryStc = JsonUtil.parseJson(json, DaySummaryStc.class);
        List<BaseDataStc>  baseDataStcs= JsonUtil.parseList(daySummaryStc.getBasicdata(), BaseDataStc.class);
        jiexiData(baseDataStcs);
    }

    private void jiexiData(List<BaseDataStc>  baseDataStcs) {
        if(baseDataStcs.size()>0){
            BaseDataStc baseDataStc = baseDataStcs.get(0);
            operation_basedata_num.setText(FunUtil.isBlankOrNullTo(baseDataStc.getBasicstrueterm(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getBasicstotalterm(),"0"));
            operation_basedata_percent.setText(FunUtil.isBlankOrNullTo(baseDataStc.getBasicstermratio(),"0")) ;//

            operation_basedata_netnum.setText(FunUtil.isBlankOrNullTo(baseDataStc.getTruenet(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotalnet(),"0")) ;//
            operation_basedata_netpercent.setText(FunUtil.isBlankOrNullTo(baseDataStc.getNetratio(),"0")) ;//

            operation_basedata_pricenum .setText(FunUtil.isBlankOrNullTo(baseDataStc.getTruesupply(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotalsupply(),"0")) ;//
            operation_basedata_pricepercent.setText(FunUtil.isBlankOrNullTo(baseDataStc.getSupplyratio(),"0")) ;//

            operation_basedata_cmpnum .setText(FunUtil.isBlankOrNullTo(baseDataStc.getTruecmpsupply(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotalcmpsupply(),"0")) ;//
            operation_basedata_cmppercent .setText(FunUtil.isBlankOrNullTo(baseDataStc.getCmpsupplyratio(),"0")) ;//

            operation_basedata_promotionnum.setText(FunUtil.isBlankOrNullTo(baseDataStc.getTrueactivity(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotalactivity(),"0")) ;//
            operation_basedata_promotionpercent .setText(FunUtil.isBlankOrNullTo(baseDataStc.getActivityratio(),"0")) ;//

            operation_basedata_basenum .setText(FunUtil.isBlankOrNullTo(baseDataStc.getTrueaccnt(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotalaccnt(),"0")) ;//
            operation_basedata_basepercent .setText(FunUtil.isBlankOrNullTo(baseDataStc.getAccntratio(),"0")) ;//getAccnratio

            operation_basedata_sumnum .setText(FunUtil.isBlankOrNullTo(baseDataStc.getTruedatagroup(),"0")
                    +"/"+FunUtil.isBlankOrNullTo(baseDataStc.getTotaldatagroup(),"0")) ;//
            operation_basedata_sumpercent.setText(FunUtil.isBlankOrNullTo(baseDataStc.getDatagroupratio(),"0")) ;//

        }


    }




    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*// 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定
                Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zgjh_bt_addplan:// 新增整顿计划
                // 跳转到  新增整改计划
                toDdDealMakeFragment();
                break;*/

            default:
                break;
        }
    }

    // 跳转到  新增整改计划
    private void toDdDealMakeFragment() {

    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<BaseDataFragment> fragmentRef;

        public MyHandler(BaseDataFragment fragment) {
            fragmentRef = new SoftReference<BaseDataFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseDataFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DEALPLAN_UP_SUC://
                    fragment.shuaxinFragment(1);
                    break;
                case DEALPLAN_NEED_UP://
                    fragment.upRepair(1);
                    break;
                /*case DEALPLAN_UP_FAIL://
                    fragment.shuaxinFragment(2);
                    break;*/

            }
        }
    }

    // 上传未通过 或已通过
    private void upRepair(int i) {

    }

    // 结束上传  刷新页面
    private void shuaxinFragment(int upType) {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initUrlData(); // 在此请求数据 首页数据
        }
    }

}
