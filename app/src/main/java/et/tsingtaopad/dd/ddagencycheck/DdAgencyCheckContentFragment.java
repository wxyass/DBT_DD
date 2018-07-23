package et.tsingtaopad.dd.ddagencycheck;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.db.table.MitAgencynumM;
import et.tsingtaopad.db.table.MitAgencyproM;
import et.tsingtaopad.db.table.MstAgencyvisitM;
import et.tsingtaopad.dd.ddagencycheck.domain.ZsInOutSaveStc;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.main.visit.agencyvisit.domain.AgencySelectStc;

/**
 * 经销商库存盘点 填充数据
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAgencyCheckContentFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAgencyCheckContentFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_AGENCY_CONTENT_SUC = 2411;//
    public static final int DD_AGENCY_CONTENT_FAIL = 2412;//


    private TextView agencycodeTv;
    private TextView contactTv;
    private TextView phoneTv;
    private ListView proList;
    private Button saveBtn;

    private MstAgencyvisitM visitM;
    private String visitDate;
    private CheckService service;
    private LinearLayout ll_check;
    //记录上传拜访的主键
    private String prevVisitKey;
    // private List<InOutSaveStc> iosStcLst;
    //private List<MstInvoicingInfo> mstInvoicingInfos;
    private List<ZsInOutSaveStc> zsInOutSaveStcs;

    private AgencySelectStc asStc;
    private String secondAreaid;// 二级区域ID
    private List<MitAgencyproM>  mitAgencyproMS;// 核查产品数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_agencycheckcontent, container, false);
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
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        agencycodeTv = (TextView) view.findViewById(R.id.agency_check_tv_agencycode);
        contactTv = (TextView) view.findViewById(R.id.agency_check_tv_contact);
        phoneTv = (TextView) view.findViewById(R.id.agency_check_tv_phone);
        ll_check = (LinearLayout) view.findViewById(R.id.agency_check_ll_check);
        proList = (ListView) view.findViewById(R.id.agency_check_lv_list);
        saveBtn = (Button) view.findViewById(R.id.dd_agencychenck_bt_next);
        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

    }

    private void initData() {

        confirmTv.setText("保存");


        service = new CheckService(getActivity());

        //记录拜访开始日期
        visitDate = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");

        //获取参数

        Bundle bundle = getArguments();
        // 获取传递过来的 经销商主键,名称,地址,联系电话
        asStc = (AgencySelectStc) bundle.getSerializable("agencyselectstc");
        secondAreaid = (String) bundle.getSerializable("secondAreaid");

        // titleTv.setText("经销商库存盘点");
        titleTv.setText(asStc.getAgencyName());
        agencycodeTv.setText(asStc.getAgencycode());
        contactTv.setText(asStc.getContact());
        phoneTv.setText(asStc.getPhone());


        /*// 获取当天的这次和上次拜访记录 mapLedger
        Map<String, Object> mapLedger = service.getMstAgencyvisitM(asStc.getAgencyKey(), visitDate);//一天多次拜访显示数据
        // 获取这次经销商拜访的记录,包含主键,拜访时间等等
        visitM = (MstAgencyvisitM) mapLedger.get("LsMstAgencyvisitM");
        // 获取上次经销商拜访的主键
        prevVisitKey = (String) mapLedger.get("LsprevVisitKey");*/

        //获取进销存台账数据
        zsInOutSaveStcs = service.getZsInOutSave(asStc.getAgencyKey());
        if(zsInOutSaveStcs!=null&&zsInOutSaveStcs.size()>0){
            ll_check.setVisibility(View.VISIBLE);
            proList.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
            confirmBtn.setVisibility(View.VISIBLE);
            DdAgencyCheckContentAdapter adapter = new DdAgencyCheckContentAdapter(getActivity(), zsInOutSaveStcs);
            proList.setAdapter(adapter);
        }else{
            ll_check.setVisibility(View.GONE);
            proList.setVisibility(View.GONE);
            saveBtn.setVisibility(View.GONE);
            confirmBtn.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.dd_agencychenck_bt_next:// 保存
            case R.id.top_navigation_rl_confirm:// 保存
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    // 保存提交数据
    private void saveValue() {
        //
        View itemV;
        // 遍历活动状态的达成情况
        EditText realStoreEt;
        EditText desEt;
        for (int i = 0; i < zsInOutSaveStcs.size(); i++) {
            itemV = proList.getChildAt(i);
            if (itemV == null || itemV.findViewById(R.id.item_agency_check_content_et_daysellnum) == null)continue;
            realStoreEt = (EditText) itemV.findViewById(R.id.item_agency_check_content_et_daysellnum);
            desEt = (EditText) itemV.findViewById(R.id.item_agency_check_content_et_des);
            ZsInOutSaveStc zsInOutSaveStc = zsInOutSaveStcs.get(i);
            zsInOutSaveStc.setRealstore(realStoreEt.getText().toString());
            zsInOutSaveStc.setDes(desEt.getText().toString());
        }
        // zsInOutSaveStcs.get(0);

        // 存储 经销商库存盘点主表
        MitAgencynumM mitAgencynumM = service.saveMitAgencynumM(asStc,secondAreaid);

        // 存储 经销商判断产品表
        mitAgencyproMS = service.saveMitAgencyproM(mitAgencynumM.getId(),zsInOutSaveStcs,secondAreaid);

        // 上传
        XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
        xtUploadService.uploadMitAgencynumM(false, mitAgencynumM,mitAgencyproMS, 1);
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdAgencyCheckContentFragment> fragmentRef;

        public MyHandler(DdAgencyCheckContentFragment fragment) {
            fragmentRef = new SoftReference<DdAgencyCheckContentFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdAgencyCheckContentFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_AGENCY_CONTENT_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_AGENCY_CONTENT_FAIL: // 督导输入数据后
                    //fragment.showAdapter();
                    break;
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");

        // 保存追溯 进销存数据  MitValsupplyMTemp
        //invoicingService.saveZsInvoicing(dataLst);
    }

}
