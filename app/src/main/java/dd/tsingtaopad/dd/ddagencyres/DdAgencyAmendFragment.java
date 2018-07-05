package dd.tsingtaopad.dd.ddagencyres;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.db.table.MitValagencykfM;
import dd.tsingtaopad.db.table.MstAgencyKFM;

/**
 * 经销商资料库 经销商数据修改
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAgencyAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAgencyAmendFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    DdAgencyContentFragment.MyHandler handler;

    public static final int DD_AGENCY_AMEND_SUC = 2321;//
    public static final int DD_AGENCY_AMEND_FAIL = 2322;//
    public static final String ERROR = "N";// 业代错误
    public static final String RIGHT = "Y";// 业代错误

    private MstAgencyKFM mstAgencyKFM;
    private MitValagencykfM mitValagencykfM;
    private String type;

    private RelativeLayout yd_all_rl;
    private TextView yd_title_tv;
    private TextView yd_con1_tv;
    private TextView yd_con2_tv;
    private TextView yd_statue_tv;
    private LinearLayout dd_head_ll;
    private RelativeLayout dd_all_rl;
    private RelativeLayout dd_et_rl;
    private TextView dd_title_tv;
    private EditText dd_con1_et;
    private TextView dd_con2_tv;
    private RelativeLayout dd_sp_rl;
    private TextView dd_title_sp;
    private TextView dd_con1_sp;
    private EditText dd_con1_et_sp;
    private TextView dd_con2_tv_sp;
    private EditText dd_report_et;
    private Button saveBtn;

    // 时间控件
    private String selectDate;
    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;

    public DdAgencyAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public DdAgencyAmendFragment(DdAgencyContentFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_agencyamend, container, false);
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

        yd_all_rl = (RelativeLayout) view.findViewById(R.id.agency_res_amend_rl);
        yd_title_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_yd_title);
        yd_con1_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_con1);
        yd_con2_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_con2);
        yd_statue_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_statue);

        dd_head_ll = (LinearLayout) view.findViewById(R.id.agency_res_amend_rl_ll_head);

        dd_all_rl = (RelativeLayout) view.findViewById(R.id.agency_res_amend_rl_dd_head);

        dd_et_rl = (RelativeLayout) view.findViewById(R.id.agency_res_amend_rl_dd_et);
        dd_title_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_dd_title_et);
        dd_con1_et = (EditText) view.findViewById(R.id.agency_res_amend_rl_dd_con1_et);
        dd_con2_tv = (TextView) view.findViewById(R.id.agency_res_amend_rl_con2_et);

        dd_sp_rl = (RelativeLayout) view.findViewById(R.id.agency_res_amend_rl_dd_sp);
        dd_title_sp = (TextView) view.findViewById(R.id.agency_res_amend_rl_dd_title_sp);
        dd_con1_sp = (TextView) view.findViewById(R.id.agency_res_amend_rl_dd_con1_sp);
        dd_con1_et_sp = (EditText) view.findViewById(R.id.agency_res_amend_rl_dd_con1_et_sp);
        dd_con2_tv_sp = (TextView) view.findViewById(R.id.agency_res_amend_rl_dd_con2_sp);

        dd_report_et = (EditText) view.findViewById(R.id.agency_res_amend_dd_et_report);

        saveBtn = (Button) view.findViewById(R.id.agency_res_amend_dd_bt_save);

        dd_sp_rl.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 初始化数据
        initData();

    }

    private void initData() {
        titleTv.setText("更正信息");

        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        mstAgencyKFM = (MstAgencyKFM) bundle.getSerializable("mstAgencyKFM");
        mitValagencykfM = (MitValagencykfM) bundle.getSerializable("valagencykfM");

        if (DdAgencyContentFragment.AGENCYNAME.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_agencyname);
            dd_title_tv.setText(R.string.agencykf_agencyname);
            dd_title_sp.setText(R.string.agencykf_agencyname);

            yd_con1_tv.setText(mstAgencyKFM.getAgencyname());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 140 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getAgencyrealname());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.CONTACT.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_contact);
            dd_title_tv.setText(R.string.agencykf_contact);
            dd_title_sp.setText(R.string.agencykf_contact);

            yd_con1_tv.setText(mstAgencyKFM.getContact());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 140 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getContactreal());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.MOBILE.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_mobile);
            dd_title_tv.setText(R.string.agencykf_mobile);
            dd_title_sp.setText(R.string.agencykf_mobile);

            yd_con1_tv.setText(mstAgencyKFM.getMobile());
            dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 40 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getMobilereal());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.ADDRESS.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_address);
            dd_title_tv.setText(R.string.agencykf_address);
            dd_title_sp.setText(R.string.agencykf_address);

            yd_con1_tv.setText(mstAgencyKFM.getAddress());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 280 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getAddressreal());
            dd_et_rl.setVisibility(View.VISIBLE);
        } else if (DdAgencyContentFragment.AREA.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_area);
            dd_title_tv.setText(R.string.agencykf_area);
            dd_title_sp.setText(R.string.agencykf_area);

            yd_con1_tv.setText(mstAgencyKFM.getArea());
            dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getAreareal());
            dd_et_rl.setVisibility(View.VISIBLE);
        } else if (DdAgencyContentFragment.MONEY.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_money);
            dd_title_tv.setText(R.string.agencykf_money);
            dd_title_sp.setText(R.string.agencykf_money);

            yd_con1_tv.setText(mstAgencyKFM.getMoney());
            dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getMoneyreal());
            dd_et_rl.setVisibility(View.VISIBLE);
        } else if (DdAgencyContentFragment.PERSION.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_persion);
            dd_title_tv.setText(R.string.agencykf_persion);
            dd_title_sp.setText(R.string.agencykf_persion);

            yd_con1_tv.setText(mstAgencyKFM.getPersion());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 240 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getPersionreal());// 错误的
            dd_et_rl.setVisibility(View.VISIBLE);
        } else if (DdAgencyContentFragment.CARNUM.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_carnum);
            dd_title_tv.setText(R.string.agencykf_carnum);
            dd_title_sp.setText(R.string.agencykf_carnum);

            yd_con1_tv.setText(mstAgencyKFM.getCarnum());
            dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getCarnumreal());
            dd_et_rl.setVisibility(View.VISIBLE);
        } else if (DdAgencyContentFragment.ISONE.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_isone);
            dd_title_tv.setText(R.string.agencykf_isone);
            dd_title_sp.setText(R.string.agencykf_isone);

            yd_con1_tv.setText(parseIsone(mstAgencyKFM.getIsone()));
            dd_con1_et.setText(mitValagencykfM.getAgencyrealname());

            // 是否数一数二
            dd_sp_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.KFDATA.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_kfdata);
            dd_title_tv.setText(R.string.agencykf_kfdata);
            dd_title_sp.setText(R.string.agencykf_kfdata);

            yd_con1_tv.setText(mstAgencyKFM.getKfdate());
            dd_con1_sp.setText(mitValagencykfM.getKfdatereal());

            // 开发时间
            dd_sp_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.PASSDATA.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_passdate);
            dd_title_tv.setText(R.string.agencykf_passdate);
            dd_title_sp.setText(R.string.agencykf_passdate);
            yd_con1_tv.setText(mstAgencyKFM.getPassdate());
            dd_con1_sp.setText(mitValagencykfM.getPassdatereal());

            // 达成时间
            dd_sp_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.PRODUCTNAME.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_productname);
            dd_title_tv.setText(R.string.agencykf_productname);
            dd_title_sp.setText(R.string.agencykf_productname);

            yd_con1_tv.setText(mstAgencyKFM.getProductname());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 480 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getProductnamereal());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.BUSINESS.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_business);
            dd_title_tv.setText(R.string.agencykf_business);
            dd_title_sp.setText(R.string.agencykf_business);

            yd_con1_tv.setText(mstAgencyKFM.getBusiness());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 80 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getBusinessreal());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.COVERTERMS.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_coverterms);
            dd_title_tv.setText(R.string.agencykf_coverterms);
            dd_title_sp.setText(R.string.agencykf_coverterms);

            yd_con1_tv.setText(mstAgencyKFM.getCoverterms());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 500 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getCovertermreal());
            dd_et_rl.setVisibility(View.VISIBLE);

        } else if (DdAgencyContentFragment.SUPPLYTERMS.equals(type)) {
            yd_title_tv.setText(R.string.agencykf_supplyterms);
            dd_title_tv.setText(R.string.agencykf_supplyterms);
            dd_title_sp.setText(R.string.agencykf_supplyterms);

            yd_con1_tv.setText(mstAgencyKFM.getSupplyterms());
            dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 500 )});// 设置输入长度
            dd_con1_et.setText(mitValagencykfM.getSupplytermsreal());
            dd_et_rl.setVisibility(View.VISIBLE);
        }

    }

    // 判定是否数一数二经销商
    private String parseIsone(int type){
        String isone ="";
        if(0 == type){
            isone = "否";
        }else{
            isone = "是";
        }
        return isone;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            // 返回
            case R.id.agency_res_amend_rl_dd_sp:// 选择时间
                // supportFragmentManager.popBackStack();
                selectTime();
                break;
            case R.id.top_navigation_rl_confirm:// 确定
                break;
            case R.id.agency_res_amend_dd_bt_save:// 保存
                saveValue();// 保存
                break;
            default:
                break;
        }
    }

    // 督导选择时间
    private void selectTime() {
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        yearr = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        if (dayOfMonth < 10) {
                            aday = "0" + dayOfMonth;
                        } else {
                            aday = Integer.toString(dayOfMonth);
                        }
                        selectDate = (Integer.toString(year) + "-" + String.format("%02d", monthOfYear + 1) + "-" + aday);
                        dd_con1_sp.setText(selectDate);
                    }
                }, yearr, month, day);
        if (!dateDialog.isShowing()) {
            dateDialog.show();
        }

    }


    // 保存并上传数据
    private void saveValue() {

        String dd_et = dd_con1_et.getText().toString();// 督导填写的内容
        String dd_tv = dd_con1_sp.getText().toString();// 督导选择的内容
        String report_tv = dd_report_et.getText().toString();// 督导备注内容

        if (DdAgencyContentFragment.AGENCYNAME.equals(type)) {// 经销商名称

            mitValagencykfM.setAgencynameflag(ERROR);
            mitValagencykfM.setAgencyrealname(dd_et);
            mitValagencykfM.setAgencynameremark(report_tv);

        } else if (DdAgencyContentFragment.CONTACT.equals(type)) {// 法定人
            mitValagencykfM.setContactflag(ERROR);
            mitValagencykfM.setContactreal(dd_et);
            mitValagencykfM.setContactremark(report_tv);

        } else if (DdAgencyContentFragment.MOBILE.equals(type)) {// 电话
            mitValagencykfM.setMobileflag(ERROR);
            mitValagencykfM.setMobilereal(dd_et);
            mitValagencykfM.setMobileremark(report_tv);

        } else if (DdAgencyContentFragment.ADDRESS.equals(type)) {// 仓库地址
            mitValagencykfM.setAddressflag(ERROR);
            mitValagencykfM.setAddressreal(dd_et);
            mitValagencykfM.setAddressremark(report_tv);

        } else if (DdAgencyContentFragment.AREA.equals(type)) {// 仓库面积
            mitValagencykfM.setAreaflag(ERROR);
            mitValagencykfM.setAreareal(dd_et);
            mitValagencykfM.setArearemark(report_tv);

        } else if (DdAgencyContentFragment.MONEY.equals(type)) {// 资金
            mitValagencykfM.setMoneyflag(ERROR);
            mitValagencykfM.setMoneyreal(dd_et);
            mitValagencykfM.setMoneyremark(report_tv);

        } else if (DdAgencyContentFragment.PERSION.equals(type)) {// 人员
            mitValagencykfM.setPersionflag(ERROR);
            mitValagencykfM.setPersionreal(dd_et);
            mitValagencykfM.setPersionremark(report_tv);

        } else if (DdAgencyContentFragment.CARNUM.equals(type)) {// 车辆
            mitValagencykfM.setCarnumflag(ERROR);
            mitValagencykfM.setCarnumreal(dd_et);
            mitValagencykfM.setCarnumremark(report_tv);

        } else if (DdAgencyContentFragment.ISONE.equals(type)) {// 是否数一数二经销商
            // valagencykfM.setIsone(dd_et);
            mitValagencykfM.setStatusflag(ERROR);
            mitValagencykfM.setStatusremark(report_tv);

        } else if (DdAgencyContentFragment.KFDATA.equals(type)) {// 开发时间
            mitValagencykfM.setKfdateflag(ERROR);
            mitValagencykfM.setKfdatereal(dd_tv);
            mitValagencykfM.setKfdateremark(report_tv);

        } else if (DdAgencyContentFragment.PASSDATA.equals(type)) {// 达成时间
            mitValagencykfM.setPassdateflag(ERROR);
            mitValagencykfM.setPassdatereal(dd_tv);
            mitValagencykfM.setPassdateremark(report_tv);

        } else if (DdAgencyContentFragment.PRODUCTNAME.equals(type)) {// 销售产品
            mitValagencykfM.setProductnameflag(ERROR);
            mitValagencykfM.setProductnamereal(dd_et);
            mitValagencykfM.setProductnameremark(report_tv);

        } else if (DdAgencyContentFragment.BUSINESS.equals(type)) {// 经营状况
            mitValagencykfM.setBusinessflag(ERROR);
            mitValagencykfM.setBusinessreal(dd_et);
            mitValagencykfM.setBusinessremark(report_tv);

        } else if (DdAgencyContentFragment.COVERTERMS.equals(type)) {// 覆盖终端
            mitValagencykfM.setCovertermflag(ERROR);
            mitValagencykfM.setCovertermreal(dd_et);
            mitValagencykfM.setCovertermremark(report_tv);

        } else if (DdAgencyContentFragment.SUPPLYTERMS.equals(type)) {// 直供终端
            mitValagencykfM.setSupplytermsflag(ERROR);
            mitValagencykfM.setSupplytermsreal(dd_et);
            mitValagencykfM.setSupplytermsremark(report_tv);
        }

        handler.sendEmptyMessage(DdAgencyContentFragment.DD_AGENCY_CONTENT_SUC);
        supportFragmentManager.popBackStack();
    }

}
