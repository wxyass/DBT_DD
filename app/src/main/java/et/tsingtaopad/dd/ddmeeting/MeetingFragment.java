package et.tsingtaopad.dd.ddmeeting;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.Calendar;

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
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.home.app.MyApplication;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 晨会录入
 * Created by yangwenmin on 2018/3/12.
 */

public class MeetingFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "MeetingFragment";

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

    private EditText et_record;// 会议纪要
    private EditText et_partin;// 参会人
    private EditText et_take;// 主持人
    private Button btn_submit_meeting;

    private RelativeLayout startdateRl;//
    private TextView startdateTv;//
    private RelativeLayout enddateRl;//
    private TextView enddateTv;//

    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    private String selectDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_meeting, container, false);
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

        startdateRl = (RelativeLayout) view.findViewById(R.id.meeting_dd_rl_startdate);
        startdateTv = (TextView) view.findViewById(R.id.meeting_dd_rl_startdate_con1);
        enddateRl = (RelativeLayout) view.findViewById(R.id.meeting_dd_rl_enddate);
        enddateTv = (TextView) view.findViewById(R.id.meeting_dd_rl_enddate_con1);
        et_record = (EditText) view.findViewById(R.id.meeting_dd_et_record);
        et_partin = (EditText) view.findViewById(R.id.meeting_dd_et_partin);
        et_take = (EditText) view.findViewById(R.id.meeting_dd_et_take);
        btn_submit_meeting = (Button) view.findViewById(R.id.meeting_dd_btn_submit);

        startdateRl.setOnClickListener(this);
        enddateRl.setOnClickListener(this);
        btn_submit_meeting.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 获取系统时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        initData();
        // initUrlData();
    }

    // 初始化数据
    private void initData() {
        titleTv.setText("晨会录入");
    }

    private void initUrlData(String id,
                             String meetingrecord,
                             String participant,
                             String compere,
                             String startdate,
                             String enddate,
                             String currentdate) {
        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "id:'" + id + "'," +
                "meetingrecord:'" + meetingrecord + "'," +
                "participant:'" + participant + "'," +
                "compere:'" + compere + "'," +
                "startdate:'" + startdate + "'," +
                "enddate:'" + enddate + "'," +
                "currentdate:'" + currentdate + "'," +
                "credate:'" + DateUtil.getDateTimeStr(8) + "'," +
                "updateuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'," +
                "updatedate:'" + DateUtil.getDateTimeStr(8) + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";
        ceshiHttp("opt_save_morning", content);
    }

    /**
     * 发起请求数据
     *
     * @param optcode 请求码
     * @param content 请求json
     */
    void ceshiHttp(final String optcode, String content) {

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
                            Toast.makeText(MyApplication.getAppContext(), "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存登录信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                // 保存信息
                                String formjson = resObj.getResBody().getContent();
                                parseTableJson(formjson);

                            } else {
                                Toast.makeText(MyApplication.getAppContext(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(MyApplication.getAppContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 解析数据
    private void parseTableJson(String formjson) {
        Toast.makeText(MyApplication.getAppContext(), "上传成功", Toast.LENGTH_SHORT).show();
    }


    int houre;
    int min;

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
            case R.id.meeting_dd_btn_submit:// 上传问题反馈
                // 上传问题反馈
                toUpQueryback();
                break;
            case R.id.meeting_dd_rl_startdate:// 开始时间
                new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10) {
                            startdateTv.setText(DateUtil.getDateTimeStr(7) + " " + hourOfDay + ":" + "0" + minute);
                        } else {
                            startdateTv.setText(DateUtil.getDateTimeStr(7) + " " + hourOfDay + ":" + minute);
                        }
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                break;
            case R.id.meeting_dd_rl_enddate:// 结束时间
                new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10) {
                            enddateTv.setText(DateUtil.getDateTimeStr(7) + " " + hourOfDay + ":" + "0" + minute);
                        } else {
                            enddateTv.setText(DateUtil.getDateTimeStr(7) + " " + hourOfDay + ":" + minute);
                        }
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                break;

            default:
                break;
        }
    }

    // 上传问题反馈
    private void toUpQueryback() {

        String id = FunUtil.getUUID();
        String meetingrecord = et_record.getText().toString();// 会议纪要
        String participant = et_partin.getText().toString();// 参会人
        String compere = et_take.getText().toString();// 主持人
        String startdate = startdateTv.getText().toString()+":00";// 开始时间
        String enddate = enddateTv.getText().toString()+":00";// 结束时间
        String currentdate = DateUtil.getDateTimeStr(7);// 当前时间

        //
        checkMeetingback(id, meetingrecord, participant, compere, startdate, enddate, currentdate);
    }

    /**
     * 会议
     *
     * @return
     */
    public void checkMeetingback(String id, String meetingrecord, final String participant, String compere, String startdate, String enddate, String currentdate) {
        if (startdate.length()<5) {// 开始时间
            Toast.makeText(getActivity(), getString(R.string.operation_startdate_fail), Toast.LENGTH_SHORT).show();
        } else if (enddate.length()<5) {// 结束时间
            Toast.makeText(getActivity(), getString(R.string.operation_enddate_fail), Toast.LENGTH_SHORT).show();
        } else if (CheckUtil.isBlankOrNull(participant)) {// 参会人
            Toast.makeText(getActivity(), getString(R.string.operation_participant_fail), Toast.LENGTH_SHORT).show();
        } else if (CheckUtil.isBlankOrNull(compere)) {// 主持人
            Toast.makeText(getActivity(), getString(R.string.operation_compere_fail), Toast.LENGTH_SHORT).show();
        } else if (CheckUtil.isBlankOrNull(meetingrecord)) {// 会议纪要
            Toast.makeText(getActivity(), getString(R.string.operation_meetingrecord_fail), Toast.LENGTH_SHORT).show();
        }
        else {
            // 发送请求
            initUrlData(id, meetingrecord, participant, compere, startdate, enddate, currentdate);
            supportFragmentManager.popBackStack();

        }
    }


    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<MeetingFragment> fragmentRef;

        public MyHandler(MeetingFragment fragment) {
            fragmentRef = new SoftReference<MeetingFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            MeetingFragment fragment = fragmentRef.get();
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
