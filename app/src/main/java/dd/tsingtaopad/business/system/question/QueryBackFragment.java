package dd.tsingtaopad.business.system.question;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.ref.SoftReference;

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
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * 问题反馈
 * Created by yangwenmin on 2018/3/12.
 */

public class QueryBackFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "QueryBackFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    //
    public static final int DEALPLAN_UP_SUC = 3301;
    //
    public static final int DEALPLAN_UP_FAIL = 3302;

    public static final int DEALPLAN_NEED_UP = 3303;

    private EditText et_queryback;
    private EditText et_phone;
    private Button btn_queryback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_queryback, container, false);
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
        confirmBtn.setVisibility(View.INVISIBLE);
        //confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        et_queryback = (EditText) view.findViewById(R.id.queryback_dd_et_queryback);
        et_phone = (EditText) view.findViewById(R.id.queryback_dd_et_phone);
        btn_queryback = (Button) view.findViewById(R.id.syssetting_dd_queryback_btn_submit);

        btn_queryback.setOnClickListener(this);
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
        titleTv.setText("问题反馈");
    }

    private void initUrlData(final String queryback,final String mobile) {

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "id:'" + FunUtil.getUUID() + "'," +
                "mobile:'" + mobile + "'," +
                "remark:'" + queryback + "'," +
                "credate:'" + DateUtil.getDateTimeStr(8) + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_save_queryback", "workplan", content);
    }

    /**
     * 发起请求数据
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
        Toast.makeText(getActivity(),"问题已反馈",Toast.LENGTH_SHORT).show();
    }


    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm://
                // Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();
                break;
            case R.id.syssetting_dd_queryback_btn_submit:// 上传问题反馈
                // 上传问题反馈
                toUpQueryback();
                break;

            default:
                break;
        }
    }

    // 上传问题反馈
    private void toUpQueryback() {
        String queryback = et_queryback.getText().toString();//
        String ephone = et_phone.getText().toString();//

        // 判断密码是否合法
        changeQueryback(queryback,ephone);
    }

    /**
     * 问题反馈
     *
     * @param queryback
     * @return
     */
    public void changeQueryback(final String queryback,final String ephone) {
        int msg = -1;
        if (CheckUtil.isBlankOrNull(queryback)) {
            msg = R.string.sys_queryback_fail;// 用户密码不能为空
        }
        // 弹出提示信息
        if (msg != -1) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        } else {
            // 发送请求
            initUrlData(queryback,ephone);
            supportFragmentManager.popBackStack();
        }
    }



    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<QueryBackFragment> fragmentRef;

        public MyHandler(QueryBackFragment fragment) {
            fragmentRef = new SoftReference<QueryBackFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            QueryBackFragment fragment = fragmentRef.get();
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

}
