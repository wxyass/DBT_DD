package dd.tsingtaopad.business.operation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.business.operation.bean.OperationDealplanStc;
import dd.tsingtaopad.business.visit.SyncBasicFragment;
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
import dd.tsingtaopad.dd.ddaddterm.DdAddTermFragment;
import dd.tsingtaopad.dd.dddaysummary.DdDaySummaryFragment;
import dd.tsingtaopad.dd.dddealplan.DdDealPlanFragment;
import dd.tsingtaopad.dd.ddmeeting.MeetingFragment;
import dd.tsingtaopad.dd.ddweekplan.DdWeekPlanFragment;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class OperationFragment extends BaseFragmentSupport implements View.OnClickListener{

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    RelativeLayout weekPlan;
    RelativeLayout summaryBtn;//
    RelativeLayout meetingBtn;//
    LinearLayout dealBtn;
    private TextView username;
    private TextView num;
    private TextView time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_operation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        backBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        weekPlan = (RelativeLayout)view.findViewById(R.id.dd_operation_btn_plan);
        summaryBtn = (RelativeLayout)view.findViewById(R.id.dd_operation_btn_summer);
        meetingBtn = (RelativeLayout)view.findViewById(R.id.dd_operation_btn_meeting);
        dealBtn = (LinearLayout)view.findViewById(R.id.dd_operation_btn_zhenggai);
        username = (TextView)view.findViewById(R.id.dd_operation_btn_zhenggai_username);
        num = (TextView)view.findViewById(R.id.dd_operation_btn_zhenggai_num);
        time = (TextView)view.findViewById(R.id.dd_operation_btn_zhenggai_time);

        weekPlan.setOnClickListener(this);
        summaryBtn.setOnClickListener(this);
        dealBtn.setOnClickListener(this);
        meetingBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("运营管理");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dd_operation_btn_plan:// 周计划
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdWeekPlanFragment(), "ddweekplanfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.dd_operation_btn_summer:// 日工作记录及总结
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdDaySummaryFragment(), "dddaysummaryfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                break;
            case R.id.dd_operation_btn_meeting:// 晨会录入
                changeHomeFragment(new MeetingFragment(), "dddaysummaryfragment");
                break;
            case R.id.dd_operation_btn_zhenggai:// 整改计划
                if (getCmmAreaMCount() > 0) {
                    changeHomeFragment(new DdDealPlanFragment(), "dddealplanfragment");
                } else {
                    Toast.makeText(getActivity(), R.string.sync_data, Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new SyncBasicFragment(), "syncbasicfragment");
                }

                //changeHomeFragment(new DdDealMakeFragment(), "dddealmakefragment");
                //changeHomeFragment(new DdDealSelectFragment(), "dddealselectfragment");
                break;
        }

    }

    // 获取整改计划数据
    private void getUrlData() {
        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "time:'" + DateUtil.getDateTimeStr(8) + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";

        String optcode = "opt_get_operation_data";

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
        OperationDealplanStc emp = JsonUtil.parseJson(formjson, OperationDealplanStc.class);
        username.setText(emp.getUsername());
        num.setText(emp.getTotalnum());
        time.setText(emp.getCredate());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getUrlData(); // 在此请求数据
        }
    }



}
