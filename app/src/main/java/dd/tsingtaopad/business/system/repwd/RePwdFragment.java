package dd.tsingtaopad.business.system.repwd;

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
import dd.tsingtaopad.business.system.repwd.domain.UserStc;
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
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * 修改密码
 * Created by yangwenmin on 2018/3/12.
 */

public class RePwdFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "RePwdFragment";

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

    private EditText et_currentpwd;
    private EditText et_newpwd;
    private EditText et_repeatpwd;
    private Button btn_submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_repwd, container, false);
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

        et_currentpwd = (EditText) view.findViewById(R.id.dd_system_et_currentpwd);
        et_newpwd = (EditText) view.findViewById(R.id.dd_system_et_newpwd);
        et_repeatpwd = (EditText) view.findViewById(R.id.dd_system_et_repeatpwd);
        btn_submit = (Button) view.findViewById(R.id.syssetting_dd_btn_submit);

        btn_submit.setOnClickListener(this);

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
        titleTv.setText("修改密码");
    }

    // 修改密码json
    private void initUrlData(final String pwd, final String newpwd, final String repeatpwd) {

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "currentpwd:'" + pwd + "'," +
                "newpwd:'" + newpwd + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_save_repwd", "workplan", content);
    }

    /**
     * 发起请求
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
                            // 保存信息
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
        UserStc userStc = JsonUtil.parseJson(formjson, UserStc.class);
        PrefUtils.putString(getActivity(), "userid", userStc.getUserid());// 19b1ded5-f853-48ab-aa2b-b12e963c8f9b
        PrefUtils.putString(getActivity(), "userPwd", userStc.getPassword());//a1234567

        Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
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
            case R.id.syssetting_dd_btn_submit:// 上传新密码
                // 上传新密码
                toUpNewPwd();
                break;

            default:
                break;
        }
    }

    // 上传新密码
    private void toUpNewPwd() {
        String currentpwd = et_currentpwd.getText().toString();//
        String newpwd = et_newpwd.getText().toString();//
        String repeatpwd = et_repeatpwd.getText().toString();//

        // 判断密码是否合法
        changePwd(currentpwd, newpwd, repeatpwd);
    }

    /**
     * 修改密码
     *
     * @param pwd       密码
     * @param newpwd    新密码
     * @param repeatpwd 重复密码
     * @return
     */
    public void changePwd(final String pwd, final String newpwd, final String repeatpwd) {
        int msg = -1;
        if (CheckUtil.isBlankOrNull(pwd)) {
            msg = R.string.sys_msg_psd1;// 用户密码不能为空

        } else if (!pwd.equals(PrefUtils.getString(getActivity(), "userPwd", ""))) {
            msg = R.string.sys_msg_psd4;// 当前密码输入不正确，请重新输入

        } else if ("a1234567".equals(newpwd)) {
            msg = R.string.sys_msg_psd9;// 新密码不能修改成原始密码

        } else if (CheckUtil.isBlankOrNull(newpwd)) {
            msg = R.string.sys_msg_psd2;// 新密码不能为空

        } else if (CheckUtil.isBlankOrNull(repeatpwd)) {
            msg = R.string.sys_msg_psd3;// 重复密码不能为空

        } else if (!newpwd.equals(repeatpwd)) {
            msg = R.string.sys_msg_psd5;//新密码两次设置不一致

        } else if (pwd.equals(repeatpwd)) {
            msg = R.string.sys_msg_psd6;// 新密码与原密码不能相同

        } else if (newpwd.length() < 8) {
            msg = R.string.sys_msg_psd7;// 新密码长度需大于等于8位

        } else if (!newpwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            msg = R.string.sys_msg_psd8;// 新密码必须包含字母和阿拉伯数字

        } else if (!NetStatusUtil.isNetValid(getActivity())) {
            msg = R.string.sys_msg_net_fail;// 网络状态不可用，请确保网络状态及网络设置是否正确
        }
        // 弹出提示信息
        if (msg != -1) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        } else {
            // 发送请求
            initUrlData(pwd,newpwd,repeatpwd);
            supportFragmentManager.popBackStack();
        }
    }


    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<RePwdFragment> fragmentRef;

        public MyHandler(RePwdFragment fragment) {
            fragmentRef = new SoftReference<RePwdFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            RePwdFragment fragment = fragmentRef.get();
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
            // initUrlData(); // 在此请求数据 首页数据
        }
    }
}
