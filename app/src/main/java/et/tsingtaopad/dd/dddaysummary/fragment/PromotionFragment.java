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
import java.util.ArrayList;
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
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.dd.dddaysummary.DdDaySummaryFragment;
import et.tsingtaopad.dd.dddaysummary.adapter.DdPromotionAdapter;
import et.tsingtaopad.dd.dddaysummary.domain.DaySummaryStc;
import et.tsingtaopad.dd.dddaysummary.domain.DdPromotionStc;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 促销活动
 * Created by yangwenmin on 2018/3/12.
 */

public class PromotionFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "PromotionFragment";
    //
    public static final int DEALPLAN_UP_SUC = 3301;

    //
    public static final int DEALPLAN_UP_FAIL = 3302;

    public static final int DEALPLAN_NEED_UP = 3303;

    private TextView tv_time;
    private et.tsingtaopad.view.NoScrollListView monthplan_lv;
    private DdPromotionAdapter workPlanAdapter;
    List<DdPromotionStc> dataLst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_operation_promotion, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        tv_time = (TextView) view.findViewById(R.id.operation_promotion_bt_addplan);
        monthplan_lv = (et.tsingtaopad.view.NoScrollListView) view.findViewById(R.id.operation_promotion_monthplan_lv);

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
        workPlanAdapter = new DdPromotionAdapter(getActivity(), dataLst,null);
        monthplan_lv.setAdapter(workPlanAdapter);

        tv_time.setText(PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7)));


    }

    private void initUrlData() {
        String currenttime = PrefUtils.getString(getActivity(), DdDaySummaryFragment.DDDAYSUMMARYFRAGMENT_CURRENTTIME, DateUtil.getDateTimeStr(7));

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "tablename:'" + "promotionactivity" + "'," +
                "credate:'" + currenttime + "'," + // currenttime
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_get_dailyrecord", "promotionactivity", content);
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
    private void parseTableJson(String formjson) {
        DaySummaryStc daySummaryStc = JsonUtil.parseJson(formjson, DaySummaryStc.class);
        List<DdPromotionStc> ddPromotionStcs = JsonUtil.parseList(daySummaryStc.getPromotionactivity(), DdPromotionStc.class);
        dataLst.clear();
        dataLst.addAll(ddPromotionStcs);
        initJsonData();
    }

    private void initJsonData() {
        workPlanAdapter.notifyDataSetChanged();
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

    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<PromotionFragment> fragmentRef;

        public MyHandler(PromotionFragment fragment) {
            fragmentRef = new SoftReference<PromotionFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            PromotionFragment fragment = fragmentRef.get();
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
