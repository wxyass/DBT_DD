package dd.tsingtaopad.dd.dddaysummary.fragment;

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
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
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
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.dd.dddaysummary.DdDaySummaryFragment;
import dd.tsingtaopad.dd.dddaysummary.adapter.WorkSumAdapter;
import dd.tsingtaopad.dd.dddaysummary.adapter.WorkSumAgreeTermAdapter;
import dd.tsingtaopad.dd.dddaysummary.domain.DaySummaryStc;
import dd.tsingtaopad.dd.dddaysummary.domain.WorkSumStc;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * 工作总结
 * Created by yangwenmin on 2018/3/12.
 */

public class WorkSumFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "WorkSumFragment";

    //
    public static final int DEALPLAN_UP_SUC = 3301;
    //
    public static final int DEALPLAN_UP_FAIL = 3302;

    public static final int DEALPLAN_NEED_UP = 3303;

    private TextView tv_time;
    private dd.tsingtaopad.view.NoScrollListView termnum_lv;
    private dd.tsingtaopad.view.NoScrollListView agree_lv;
    private dd.tsingtaopad.view.NoScrollListView sdlv_lv;

    ArrayList<WorkSumStc> dataLst;
    WorkSumAdapter workTermAdapter;
    WorkSumAgreeTermAdapter workXieyiAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_operation_worksum, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        tv_time = (TextView) view.findViewById(R.id.operation_worksum_tv_time);
        termnum_lv = (dd.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.operation_worksum_termnum_lv);
        agree_lv = (dd.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.operation_worksum_agree_lv);
        sdlv_lv = (dd.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.operation_worksum_sdlv_lv);
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


        dataLst = new ArrayList<>();

        ArrayList<KvStc> kvStcs =new ArrayList<>();
        /*kvStcs.add(new KvStc("A","123"));
        kvStcs.add(new KvStc("B","456"));
        kvStcs.add(new KvStc("C","789"));
        kvStcs.add(new KvStc("D","300"));
        kvStcs.add(new KvStc("B","456"));
        kvStcs.add(new KvStc("C","789"));
        kvStcs.add(new KvStc("D","300"));*/


        workTermAdapter = new WorkSumAdapter(getActivity(),dataLst);
        termnum_lv.setAdapter(workTermAdapter);

        workXieyiAdapter = new WorkSumAgreeTermAdapter(getActivity(),dataLst);
        agree_lv.setAdapter(workXieyiAdapter);
        // sdlv_lv.setAdapter(workSumAdapter);

        tv_time.setText(PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7)));


    }

    private void initUrlData() {
        String currenttime = PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7));

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "tablename:'" + "jobsummary" + "'," +
                "credate:'" + currenttime + "'," + // currenttime
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_dailyrecord", "jobsummary", content);
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
        List<WorkSumStc> workSumStcs = JsonUtil.parseList(daySummaryStc.getJobsummary(), WorkSumStc.class);
        dataLst.clear();
        dataLst.addAll(workSumStcs);
        initJsonData();
    }

    private void initJsonData() {
        workTermAdapter.notifyDataSetChanged();
        workXieyiAdapter.notifyDataSetChanged();
    }


    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<WorkSumFragment> fragmentRef;

        public MyHandler(WorkSumFragment fragment) {
            fragmentRef = new SoftReference<WorkSumFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            WorkSumFragment fragment = fragmentRef.get();
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
            }
        }
    }

    // 上传未通过 或已通过
    private void upRepair(int i) {

    }

    // 结束上传  刷新页面
    private void shuaxinFragment(int upType) {
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initUrlData(); // 在此请求数据 首页数据
        }
    }

}
