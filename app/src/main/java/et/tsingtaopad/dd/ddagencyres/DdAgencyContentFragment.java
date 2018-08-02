package et.tsingtaopad.dd.ddagencyres;

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

import java.lang.ref.SoftReference;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.db.table.MitValagencykfM;
import et.tsingtaopad.db.table.MstAgencyKFM;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.dd.ddzs.zssayhi.ZsSayhiFragment;

/**
 * 经销商资料库 经销商未稽查判断
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAgencyContentFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAgencyContentFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_AGENCY_CONTENT_SUC = 2311;// 修改成功
    public static final int DD_AGENCY_CONTENT_FAIL = 2312;//

    public static final String AGENCYNAME = "agencyname";
    public static final String CONTACT = "contact";
    public static final String MOBILE = "mobile";
    public static final String ADDRESS = "address";
    public static final String AREA = "area";
    public static final String MONEY = "money";
    public static final String PERSION = "persion";
    public static final String CARNUM = "carnum";
    public static final String ISONE = "isone";
    public static final String KFDATA = "kfdata";
    public static final String PASSDATA = "passdata";
    public static final String PRODUCTNAME = "productname";
    public static final String BUSINESS = "business";
    public static final String COVERTERMS = "coverterms";
    public static final String SUPPLYTERMS = "supplyterms";

    public static final String ERROR = "N";// 业代错误
    public static final String RIGHT = "Y";// 业代错误

    private RelativeLayout agencyname;
    private TextView agencyname_con1;
    private TextView agencyname_statue;
    private RelativeLayout contact;
    private TextView contact_con1;
    private TextView contact_statue;
    private RelativeLayout mobile;
    private TextView mobile_con1;
    private TextView mobile_statue;
    private RelativeLayout address;
    private TextView address_con1;
    private TextView address_statue;
    private RelativeLayout area;
    private TextView area_con1;
    private TextView area_statue;
    private RelativeLayout money;
    private TextView money_con1;
    private TextView money_statue;
    private RelativeLayout persion;
    private TextView persion_con1;
    private TextView persion_statue;
    private RelativeLayout carnum;
    private TextView carnum_con1;
    private TextView carnum_statue;
    private RelativeLayout isone;
    private TextView isone_con1;
    private TextView isone_statue;
    private RelativeLayout kfdata;
    private TextView kfdata_con1;
    private TextView kfdata_statue;
    private RelativeLayout passdata;
    private TextView passdata_con1;
    private TextView passdata_statue;
    private RelativeLayout productname;
    private TextView productname_con1;
    private TextView productname_statue;
    private RelativeLayout business;
    private TextView business_con1;
    private TextView business_statue;
    private RelativeLayout coverterms;
    private TextView coverterms_con1;
    private TextView coverterms_statue;
    private RelativeLayout supplyterms;
    private TextView supplyterms_con1;
    private TextView supplyterms_statue;
    private EditText agencyReport;
    private Button dd_bt_save;

    private MstAgencyKFM mstAgencyKFM;
    private DdAgencySelectService service;
    private MitValagencykfM valagencykfM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_agencycontent, container, false);
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
        //confirmBtn.setVisibility(View.VISIBLE);
        //confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        agencyname = (RelativeLayout) view.findViewById(R.id.agency_content_rl_agencyname);
        agencyname_con1 = (TextView) view.findViewById(R.id.agency_content_rl_agencyname_con1);
        agencyname_statue = (TextView) view.findViewById(R.id.agency_content_rl_agencyname_statue);

        contact = (RelativeLayout) view.findViewById(R.id.agency_content_rl_contact);
        contact_con1 = (TextView) view.findViewById(R.id.agency_content_rl_contact_con1);
        contact_statue = (TextView) view.findViewById(R.id.agency_content_rl_contact_statue);

        mobile = (RelativeLayout) view.findViewById(R.id.agency_content_rl_mobile);
        mobile_con1 = (TextView) view.findViewById(R.id.agency_content_rl_mobile_con1);
        mobile_statue = (TextView) view.findViewById(R.id.agency_content_rl_mobile_statue);

        address = (RelativeLayout) view.findViewById(R.id.agency_content_rl_address);
        address_con1 = (TextView) view.findViewById(R.id.agency_content_rl_address_con1);
        address_statue = (TextView) view.findViewById(R.id.agency_content_rl_address_statue);

        area = (RelativeLayout) view.findViewById(R.id.agency_content_rl_area);
        area_con1 = (TextView) view.findViewById(R.id.agency_content_rl_area_con1);
        area_statue = (TextView) view.findViewById(R.id.agency_content_rl_area_statue);

        money = (RelativeLayout) view.findViewById(R.id.agency_content_rl_money);
        money_con1 = (TextView) view.findViewById(R.id.agency_content_rl_money_con1);
        money_statue = (TextView) view.findViewById(R.id.agency_content_rl_money_statue);

        persion = (RelativeLayout) view.findViewById(R.id.agency_content_rl_persion);
        persion_con1 = (TextView) view.findViewById(R.id.agency_content_rl_persion_con1);
        persion_statue = (TextView) view.findViewById(R.id.agency_content_rl_persion_statue);

        carnum = (RelativeLayout) view.findViewById(R.id.agency_content_rl_carnum);
        carnum_con1 = (TextView) view.findViewById(R.id.agency_content_rl_carnum_con1);
        carnum_statue = (TextView) view.findViewById(R.id.agency_content_rl_carnum_statue);

        isone = (RelativeLayout) view.findViewById(R.id.agency_content_rl_isone);
        isone_con1 = (TextView) view.findViewById(R.id.agency_content_rl_isone_con1);
        isone_statue = (TextView) view.findViewById(R.id.agency_content_rl_isone_statue);

        kfdata = (RelativeLayout) view.findViewById(R.id.agency_content_rl_kfdata);
        kfdata_con1 = (TextView) view.findViewById(R.id.agency_content_rl_kfdata_con1);
        kfdata_statue = (TextView) view.findViewById(R.id.agency_content_rl_kfdata_statue);

        passdata = (RelativeLayout) view.findViewById(R.id.agency_content_rl_passdata);
        passdata_con1 = (TextView) view.findViewById(R.id.agency_content_rl_passdata_con1);
        passdata_statue = (TextView) view.findViewById(R.id.agency_content_rl_passdata_statue);

        productname = (RelativeLayout) view.findViewById(R.id.agency_content_rl_productname);
        productname_con1 = (TextView) view.findViewById(R.id.agency_content_rl_productname_con1);
        productname_statue = (TextView) view.findViewById(R.id.agency_content_rl_productname_statue);

        business = (RelativeLayout) view.findViewById(R.id.agency_content_rl_business);
        business_con1 = (TextView) view.findViewById(R.id.agency_content_rl_business_con1);
        business_statue = (TextView) view.findViewById(R.id.agency_content_rl_business_statue);

        coverterms = (RelativeLayout) view.findViewById(R.id.agency_content_rl_coverterms);
        coverterms_con1 = (TextView) view.findViewById(R.id.agency_content_rl_coverterms_con1);
        coverterms_statue = (TextView) view.findViewById(R.id.agency_content_rl_coverterms_statue);

        supplyterms = (RelativeLayout) view.findViewById(R.id.agency_content_rl_supplyterms);
        supplyterms_con1 = (TextView) view.findViewById(R.id.agency_content_rl_supplyterms_con1);
        supplyterms_statue = (TextView) view.findViewById(R.id.agency_content_rl_supplyterms_statue);

        agencyReport = (EditText) view.findViewById(R.id.agency_content_dd_et_report);

        dd_bt_save = (Button) view.findViewById(R.id.agency_content_dd_bt_save);

        agencyname.setOnClickListener(this);
        contact.setOnClickListener(this);
        mobile.setOnClickListener(this);
        address.setOnClickListener(this);
        area.setOnClickListener(this);
        money.setOnClickListener(this);
        persion.setOnClickListener(this);
        carnum.setOnClickListener(this);
        isone.setOnClickListener(this);
        kfdata.setOnClickListener(this);
        passdata.setOnClickListener(this);
        productname.setOnClickListener(this);
        business.setOnClickListener(this);
        coverterms.setOnClickListener(this);
        supplyterms.setOnClickListener(this);
        dd_bt_save.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

    }

    private void initData() {

        confirmTv.setText("更正");

        service = new DdAgencySelectService(getActivity());

        Bundle bundle = getArguments();
        // 获取传递过来的 经销商主键,名称,地址,联系电话
        mstAgencyKFM = (MstAgencyKFM) bundle.getSerializable("mstagencykfm");

        titleTv.setText(mstAgencyKFM.getAgencyname());

        agencyname_con1.setText(mstAgencyKFM.getAgencyname());
        contact_con1.setText(mstAgencyKFM.getContact());
        mobile_con1.setText(mstAgencyKFM.getMobile());
        address_con1.setText(mstAgencyKFM.getAddress());
        area_con1.setText(mstAgencyKFM.getArea());
        money_con1.setText(mstAgencyKFM.getMoney());
        persion_con1.setText(mstAgencyKFM.getPersion());
        carnum_con1.setText(mstAgencyKFM.getCarnum());
        isone_con1.setText(parseIsone(mstAgencyKFM.getIsone()));
        kfdata_con1.setText(mstAgencyKFM.getKfdate());
        passdata_con1.setText(mstAgencyKFM.getPassdate());
        productname_con1.setText(mstAgencyKFM.getProductname());
        business_con1.setText(mstAgencyKFM.getBusiness());
        coverterms_con1.setText(mstAgencyKFM.getCoverterms());
        supplyterms_con1.setText(mstAgencyKFM.getSupplyterms());

        if (valagencykfM == null) {
            valagencykfM = new MitValagencykfM();
        }

        // 设置稽查状态和颜色
        setCheckColor();
    }

    private void setCheckColor() {
        agencyname_statue.setText(getYdKfInfo(valagencykfM.getAgencynameflag()));
        agencyname_statue.setTextColor(getYdKfInfoColor(valagencykfM.getAgencynameflag()));

        contact_statue.setText(getYdKfInfo(valagencykfM.getContactflag()));
        contact_statue.setTextColor(getYdKfInfoColor(valagencykfM.getContactflag()));

        mobile_statue.setText(getYdKfInfo(valagencykfM.getMobileflag()));
        mobile_statue.setTextColor(getYdKfInfoColor(valagencykfM.getMobileflag()));

        address_statue.setText(getYdKfInfo(valagencykfM.getAddressflag()));
        address_statue.setTextColor(getYdKfInfoColor(valagencykfM.getAddressflag()));

        area_statue.setText(getYdKfInfo(valagencykfM.getAreaflag()));
        area_statue.setTextColor(getYdKfInfoColor(valagencykfM.getAreaflag()));

        money_statue.setText(getYdKfInfo(valagencykfM.getMoneyflag()));
        money_statue.setTextColor(getYdKfInfoColor(valagencykfM.getMoneyflag()));

        persion_statue.setText(getYdKfInfo(valagencykfM.getPersionflag()));
        persion_statue.setTextColor(getYdKfInfoColor(valagencykfM.getPersionflag()));

        carnum_statue.setText(getYdKfInfo(valagencykfM.getCarnumflag()));
        carnum_statue.setTextColor(getYdKfInfoColor(valagencykfM.getCarnumflag()));

        isone_statue.setText(getYdKfInfo(valagencykfM.getStatusflag()));
        isone_statue.setTextColor(getYdKfInfoColor(valagencykfM.getStatusflag()));

        kfdata_statue.setText(getYdKfInfo(valagencykfM.getKfdateflag()));
        kfdata_statue.setTextColor(getYdKfInfoColor(valagencykfM.getKfdateflag()));

        passdata_statue.setText(getYdKfInfo(valagencykfM.getPassdateflag()));
        passdata_statue.setTextColor(getYdKfInfoColor(valagencykfM.getPassdateflag()));

        productname_statue.setText(getYdKfInfo(valagencykfM.getProductnameflag()));
        productname_statue.setTextColor(getYdKfInfoColor(valagencykfM.getProductnameflag()));

        business_statue.setText(getYdKfInfo(valagencykfM.getBusinessflag()));
        business_statue.setTextColor(getYdKfInfoColor(valagencykfM.getBusinessflag()));

        coverterms_statue.setText(getYdKfInfo(valagencykfM.getCovertermflag()));
        coverterms_statue.setTextColor(getYdKfInfoColor(valagencykfM.getCovertermflag()));

        supplyterms_statue.setText(getYdKfInfo(valagencykfM.getSupplytermsflag()));
        supplyterms_statue.setTextColor(getYdKfInfoColor(valagencykfM.getSupplytermsflag()));

    }

    // 判定是否数一数二经销商
    private String parseIsone(int type) {
        String isone = "";
        if (0 == type) {
            isone = "否";
        } else {
            isone = "是";
        }
        return isone;
    }

    // 判定业代录入的状态
    private String getYdKfInfo(String flag) {
        String con = "";
        if ("N".equals(flag)) {
            con = "错误";
        } else if ("Y".equals(flag)) {
            con = "正确";
        } else {
            con = "未稽查";
        }
        return con;
    }

    private int getYdKfInfoColor(String flag) {
        int color;
        if ("N".equals(flag)) {
            color = getResources().getColor(R.color.zdzs_dd_error);
        } else if ("Y".equals(flag)) {
            color = getResources().getColor(R.color.zdzs_dd_yes);
        } else {
            color = getResources().getColor(R.color.gray_color_666666);
        }
        return color;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定

                break;
            case R.id.agency_content_rl_agencyname:// 经销商名称
                alertShow3(AGENCYNAME);
                break;
            case R.id.agency_content_rl_contact:// 法定代表人
                alertShow3(CONTACT);
                break;
            case R.id.agency_content_rl_mobile:// 电话
                alertShow3(MOBILE);
                break;
            case R.id.agency_content_rl_address:// 地址
                alertShow3(ADDRESS);
                break;
            case R.id.agency_content_rl_area:// 面积
                alertShow3(AREA);
                break;
            case R.id.agency_content_rl_money:// 资金
                alertShow3(MONEY);
                break;
            case R.id.agency_content_rl_persion://人员
                alertShow3(PERSION);
                break;
            case R.id.agency_content_rl_carnum:// 车辆
                alertShow3(CARNUM);
                break;
            case R.id.agency_content_rl_isone:// 数一数二经销商
                alertShow3(ISONE);
                break;
            case R.id.agency_content_rl_kfdata:// 开发时间
                alertShow3(KFDATA);
                break;
            case R.id.agency_content_rl_passdata:// 达成时间
                alertShow3(PASSDATA);
                break;
            case R.id.agency_content_rl_productname:// 销售产品
                alertShow3(PRODUCTNAME);
                break;
            case R.id.agency_content_rl_business:// 经营状况
                alertShow3(BUSINESS);
                break;
            case R.id.agency_content_rl_coverterms:// 覆盖终端
                alertShow3(COVERTERMS);
                break;
            case R.id.agency_content_rl_supplyterms:// 直供终端
                alertShow3(SUPPLYTERMS);
                break;
            case R.id.agency_content_dd_bt_save:// 保存上传
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    // 上传数据
    private void saveValue() {
        // 被追
        String des = agencyReport.getText().toString();
        // 保存数据
        MitValagencykfM valagencykf = service.saveMitValagencykfM(des, mstAgencyKFM, valagencykfM);
        // 上传数据
        XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
        xtUploadService.uploadMitValagencykfM(false, valagencykf, 1);

    }

    // 设置正确选项
    public void setMstAgencyKFMFlag(MitValagencykfM valagencykfM, String type) {
        if (DdAgencyContentFragment.AGENCYNAME.equals(type)) {// 经销商名称

            valagencykfM.setAgencynameflag(RIGHT);

        } else if (DdAgencyContentFragment.CONTACT.equals(type)) {// 法定人
            valagencykfM.setContactflag(RIGHT);

        } else if (DdAgencyContentFragment.MOBILE.equals(type)) {// 电话
            valagencykfM.setMobileflag(RIGHT);

        } else if (DdAgencyContentFragment.ADDRESS.equals(type)) {// 仓库地址
            valagencykfM.setAddressflag(RIGHT);

        } else if (DdAgencyContentFragment.AREA.equals(type)) {// 仓库面积
            valagencykfM.setAreaflag(RIGHT);

        } else if (DdAgencyContentFragment.MONEY.equals(type)) {// 资金
            valagencykfM.setMoneyflag(RIGHT);

        } else if (DdAgencyContentFragment.PERSION.equals(type)) {// 人员
            valagencykfM.setPersionflag(RIGHT);

        } else if (DdAgencyContentFragment.CARNUM.equals(type)) {// 车辆
            valagencykfM.setCarnumflag(RIGHT);

        } else if (DdAgencyContentFragment.ISONE.equals(type)) {// 是否数一数二经销商
            // valagencykfM.setIsone(dd_et);
            valagencykfM.setStatusflag(RIGHT);

        } else if (DdAgencyContentFragment.KFDATA.equals(type)) {// 开发时间
            valagencykfM.setKfdateflag(RIGHT);

        } else if (DdAgencyContentFragment.PASSDATA.equals(type)) {// 达成时间
            valagencykfM.setPassdateflag(RIGHT);

        } else if (DdAgencyContentFragment.PRODUCTNAME.equals(type)) {// 销售产品
            valagencykfM.setProductnameflag(RIGHT);

        } else if (DdAgencyContentFragment.BUSINESS.equals(type)) {// 经营状况
            valagencykfM.setBusinessflag(RIGHT);

        } else if (DdAgencyContentFragment.COVERTERMS.equals(type)) {// 覆盖终端
            valagencykfM.setCovertermflag(RIGHT);

        } else if (DdAgencyContentFragment.SUPPLYTERMS.equals(type)) {// 直供终端
            valagencykfM.setSupplytermsflag(RIGHT);
        }
    }

    // 设置未稽查
    public void setMstAgencyKFMFlagNull(MitValagencykfM valagencykfM, String type) {
        if (DdAgencyContentFragment.AGENCYNAME.equals(type)) {// 经销商名称

            valagencykfM.setAgencynameflag("");

        } else if (DdAgencyContentFragment.CONTACT.equals(type)) {// 法定人
            valagencykfM.setContactflag("");

        } else if (DdAgencyContentFragment.MOBILE.equals(type)) {// 电话
            valagencykfM.setMobileflag("");

        } else if (DdAgencyContentFragment.ADDRESS.equals(type)) {// 仓库地址
            valagencykfM.setAddressflag("");

        } else if (DdAgencyContentFragment.AREA.equals(type)) {// 仓库面积
            valagencykfM.setAreaflag("");

        } else if (DdAgencyContentFragment.MONEY.equals(type)) {// 资金
            valagencykfM.setMoneyflag("");

        } else if (DdAgencyContentFragment.PERSION.equals(type)) {// 人员
            valagencykfM.setPersionflag("");

        } else if (DdAgencyContentFragment.CARNUM.equals(type)) {// 车辆
            valagencykfM.setCarnumflag("");

        } else if (DdAgencyContentFragment.ISONE.equals(type)) {// 是否数一数二经销商
            // valagencykfM.setIsone(dd_et);
            valagencykfM.setStatusflag("");

        } else if (DdAgencyContentFragment.KFDATA.equals(type)) {// 开发时间
            valagencykfM.setKfdateflag("");

        } else if (DdAgencyContentFragment.PASSDATA.equals(type)) {// 达成时间
            valagencykfM.setPassdateflag("");

        } else if (DdAgencyContentFragment.PRODUCTNAME.equals(type)) {// 销售产品
            valagencykfM.setProductnameflag("");

        } else if (DdAgencyContentFragment.BUSINESS.equals(type)) {// 经营状况
            valagencykfM.setBusinessflag("");

        } else if (DdAgencyContentFragment.COVERTERMS.equals(type)) {// 覆盖终端
            valagencykfM.setCovertermflag("");

        } else if (DdAgencyContentFragment.SUPPLYTERMS.equals(type)) {// 直供终端
            valagencykfM.setSupplytermsflag("");
        }
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdAgencyContentFragment> fragmentRef;

        public MyHandler(DdAgencyContentFragment fragment) {
            fragmentRef = new SoftReference<DdAgencyContentFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdAgencyContentFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_AGENCY_CONTENT_SUC:
                    fragment.setCheckColor();
                    break;
                case DD_AGENCY_CONTENT_FAIL: // 督导输入数据后
                    //fragment.showAdapter();
                    break;
            }
        }
    }

    /**
     * 正确 错误(去修正)
     * 参数1: 标题 ×
     * 参数2: 主体内容    ×
     * 参数3: 取消按钮    ×
     * 参数4: 高亮按钮 数组 √
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (正常取消,确定按钮)   √
     * 参数8: 条目点击监听  √
     */
    private AlertView mAlertViewExt;//窗口拓展例子

    public void alertShow3(final String type) {
        new AlertView("请选择核查结果", null, "未稽查", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确
                            setMstAgencyKFMFlag(valagencykfM, type);
                            handler.sendEmptyMessage(DdAgencyContentFragment.DD_AGENCY_CONTENT_SUC);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putString("type", type);
                            bundle.putSerializable("mstAgencyKFM", mstAgencyKFM);
                            bundle.putSerializable("valagencykfM", valagencykfM);
                            DdAgencyAmendFragment agencyAmendFragment = new DdAgencyAmendFragment(handler);
                            agencyAmendFragment.setArguments(bundle);
                            // 跳转 经销商库存盘点 填充数据
                            changeHomeFragment(agencyAmendFragment, "DdAgencyAmendFragment");
                        } else if (-1 == position) {// 跳转数据录入
                            setMstAgencyKFMFlagNull(valagencykfM, type);
                            handler.sendEmptyMessage(DdAgencyContentFragment.DD_AGENCY_CONTENT_SUC);
                        }

                    }
                }).setCancelable(true).show();
    }

}
