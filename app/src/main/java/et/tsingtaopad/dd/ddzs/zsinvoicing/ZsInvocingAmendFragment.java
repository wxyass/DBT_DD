package et.tsingtaopad.dd.ddzs.zsinvoicing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.AlertKeyValueAdapter;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsInvocingAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsInvoicingFragment.MyHandler handler;

    private Button sureBtn;


    protected int position;// 页面类型,比如: "1"
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected List<MitValsupplyMTemp> dataLst;//
    protected boolean valagencyerror;//
    protected boolean valdataerror;//
    protected boolean valiffleeing;//



    private LinearLayout zdzs_invoicing_amend_rl_ll_agency;
    private RelativeLayout zdzs_invoicing_amend_rl_dd_sp_agency;
    private TextView zdzs_invoicing_amend_rl_dd_con1_sp_agency;
    private TextView zdzs_invoicing_amend_rl_dd_con2_sp_agency;

    private LinearLayout zdzs_invoicing_amend_rl_ll_head;
    private RelativeLayout zdzs_invoicing_amend_rl_dd_qd;
    private TextView zdzs_invoicing_amend_rl_dd_con1_qd;
    private EditText zdzs_invoicing_amend_rl_con2_qd;

    private RelativeLayout zdzs_invoicing_amend_rl_dd_ls;
    private TextView zdzs_invoicing_amend_rl_dd_con1_ls;
    private EditText zdzs_invoicing_amend_rl_con2_ls;

    private RelativeLayout zdzs_invoicing_amend_rl_dd_ddl;
    private TextView zdzs_invoicing_amend_rl_dd_con1_ddl;
    private EditText zdzs_invoicing_amend_rl_con2_ddl;

    private RelativeLayout zdzs_invoicing_amend_rl_dd_ljk;
    private TextView zdzs_invoicing_amend_rl_dd_con1_ljk;
    private EditText zdzs_invoicing_amend_rl_con2_ljk;

    private LinearLayout zdzs_invoicing_amend_rl_ll_cuanhuo;
    private EditText zdzs_invoicing_amend_dd_et_cuanhuo;

    private EditText zdzs_invoicing_amend_dd_et_report;
    private Button zdzs_invoicing_amend_dd_bt_save;

    private ZsInvoicingService zsInvoicingService;
    private MitValsupplyMTemp valsupplyMTemp;

    private XtAddInvocingService addInvocingService;
    private List<KvStc> agencyLst;


    public ZsInvocingAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsInvocingAmendFragment(ZsInvoicingFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_invoicing_amend, container, false);
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
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);


        zdzs_invoicing_amend_rl_ll_agency = (LinearLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_ll_agency);

        zdzs_invoicing_amend_rl_dd_sp_agency = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_sp_agency);
        zdzs_invoicing_amend_rl_dd_con1_sp_agency = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con1_sp_agency);
        zdzs_invoicing_amend_rl_dd_con2_sp_agency = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con2_sp_agency);

        zdzs_invoicing_amend_rl_ll_head = (LinearLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_ll_head);

        zdzs_invoicing_amend_rl_dd_qd = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_qd);
        zdzs_invoicing_amend_rl_dd_con1_qd = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con1_qd);
        zdzs_invoicing_amend_rl_con2_qd = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_rl_con2_qd);

        zdzs_invoicing_amend_rl_dd_ls = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_ls);
        zdzs_invoicing_amend_rl_dd_con1_ls = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con1_ls);
        zdzs_invoicing_amend_rl_con2_ls = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_rl_con2_ls);

        zdzs_invoicing_amend_rl_dd_ddl = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_ddl);
        zdzs_invoicing_amend_rl_dd_con1_ddl = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con1_ddl);
        zdzs_invoicing_amend_rl_con2_ddl = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_rl_con2_ddl);

        zdzs_invoicing_amend_rl_dd_ljk = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_ljk);
        zdzs_invoicing_amend_rl_dd_con1_ljk = (TextView) view.findViewById(R.id.zdzs_invoicing_amend_rl_dd_con1_ljk);
        zdzs_invoicing_amend_rl_con2_ljk = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_rl_con2_ljk);

        zdzs_invoicing_amend_rl_ll_cuanhuo = (LinearLayout) view.findViewById(R.id.zdzs_invoicing_amend_rl_ll_cuanhuo);
        zdzs_invoicing_amend_dd_et_cuanhuo = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_dd_et_cuanhuo);

        zdzs_invoicing_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_invoicing_amend_dd_et_report);

        sureBtn = (Button) view.findViewById(R.id.zdzs_invoicing_amend_dd_bt_save);

        zdzs_invoicing_amend_rl_dd_sp_agency.setOnClickListener(this);
        sureBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入正确数据");

        zsInvoicingService = new ZsInvoicingService(getActivity(), handler);
        addInvocingService = new XtAddInvocingService(getActivity(), null);

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        termId = bundle.getString("termId");
        mitValterMTempKey = bundle.getString("mitValterMTempKey");//追溯主键
        dataLst = (List<MitValsupplyMTemp>) bundle.getSerializable("dataLst");


        valagencyerror = bundle.getBoolean("valagencyerror");// 经销商显示
        valdataerror = bundle.getBoolean("valdataerror");// 数据显示
        valiffleeing = bundle.getBoolean("valiffleeing");// 窜货显示



        initData();
    }

    private void initData() {

        valsupplyMTemp = dataLst.get(position);

        // 经销商显示
        if(valagencyerror){
            zdzs_invoicing_amend_rl_ll_agency.setVisibility(View.VISIBLE);
        }else{
            zdzs_invoicing_amend_rl_ll_agency.setVisibility(View.GONE);
        }
        // 数据显示
        if(valdataerror){
            zdzs_invoicing_amend_rl_ll_head.setVisibility(View.VISIBLE);
        }else{
            zdzs_invoicing_amend_rl_ll_head.setVisibility(View.GONE);
        }
        // 窜货显示
        if(valiffleeing){
            zdzs_invoicing_amend_rl_ll_cuanhuo.setVisibility(View.VISIBLE);
        }else{
            zdzs_invoicing_amend_rl_ll_cuanhuo.setVisibility(View.GONE);
        }

        zdzs_invoicing_amend_rl_dd_con1_sp_agency.setText(valsupplyMTemp.getValtrueagencyname());// 经销商名称

        zdzs_invoicing_amend_rl_dd_con1_qd.setText(valsupplyMTemp.getValsqd());// 渠道价
        zdzs_invoicing_amend_rl_con2_qd.setText(valsupplyMTemp.getValagencysupplyqd());// 渠道价

        zdzs_invoicing_amend_rl_dd_con1_ls.setText(valsupplyMTemp.getValsls());// 零售价
        zdzs_invoicing_amend_rl_con2_ls.setText(valsupplyMTemp.getValagencysupplyls());// 零售价

        zdzs_invoicing_amend_rl_dd_con1_ddl.setText(valsupplyMTemp.getValsdd());// 订单量价
        zdzs_invoicing_amend_rl_con2_ddl.setText(valsupplyMTemp.getValagencysupplydd());// 订单量价

        zdzs_invoicing_amend_rl_dd_con1_ljk.setText(valsupplyMTemp.getValsljk());// 累计卡
        zdzs_invoicing_amend_rl_con2_ljk.setText(valsupplyMTemp.getValagencysupplyljk());// 累计卡

        zdzs_invoicing_amend_dd_et_cuanhuo.setText(valsupplyMTemp.getValiffleeingremark());// 窜货
        zdzs_invoicing_amend_dd_et_report.setText(valsupplyMTemp.getValagencysupplyremark());// 备注
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;
            case R.id.zdzs_invoicing_amend_rl_dd_sp_agency:// 经销商
                agencyLst = addInvocingService.getAgencyList(termId);
                setSelectValueRight(agencyLst,valsupplyMTemp.getValtrueagency());
                break;

            case R.id.zdzs_invoicing_amend_dd_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    private void saveValue() {

        // 督导渠道价
        String qdinfo = zdzs_invoicing_amend_rl_con2_qd.getText().toString();
        // 督导零售价
        String lsinfo = zdzs_invoicing_amend_rl_con2_ls.getText().toString();
        // 督导订单量
        String ddlqdinfo = zdzs_invoicing_amend_rl_con2_ddl.getText().toString();
        // 督导累计卡
        String ljkqdinfo = zdzs_invoicing_amend_rl_con2_ljk.getText().toString();



        valsupplyMTemp.setValagencysupplyflag("N");

        // 经销商显示
        if(valagencyerror){
            valsupplyMTemp.setValagencyerror("Y");
        }else{
            valsupplyMTemp.setValagencyerror("N");
        }
        // 数据显示
        if(valdataerror){
            valsupplyMTemp.setValdataerror("Y");// 数据异常
            // 供货关系正确渠道价状态
            if(FunUtil.isBlankOrNullTo(valsupplyMTemp.getValsqd(),"").equals(qdinfo)){
                valsupplyMTemp.setValagencyqdflag("Y");// 与业代数据一致
            }else{
                valsupplyMTemp.setValagencyqdflag("N");// 与业代数据不一致
            }
            // 供货关系正确零售价状态
            if(FunUtil.isBlankOrNullTo(valsupplyMTemp.getValsls(),"").equals(lsinfo)){
                valsupplyMTemp.setValagencylsflag("Y");// 与业代数据一致
            }else{
                valsupplyMTemp.setValagencylsflag("N");// 与业代数据不一致
            }
        }else{
            valsupplyMTemp.setValdataerror("N");// 数据正常
            valsupplyMTemp.setValagencyqdflag("Y");// 与业代数据一致
            valsupplyMTemp.setValagencylsflag("Y");// 与业代数据一致
        }



        // 窜货显示
        if(valiffleeing){
            valsupplyMTemp.setValiffleeing("Y");
        }else{
            valsupplyMTemp.setValiffleeing("N");
        }



        valsupplyMTemp.setValagencysupplyqd(qdinfo);
        valsupplyMTemp.setValagencysupplyls(lsinfo);
        valsupplyMTemp.setValagencysupplydd(ddlqdinfo);
        valsupplyMTemp.setValagencysupplyljk(ljkqdinfo);

        // 窜货
        String cuanhuo = zdzs_invoicing_amend_dd_et_cuanhuo.getText().toString();
        valsupplyMTemp.setValiffleeingremark(cuanhuo);

        // 备注
        String report = zdzs_invoicing_amend_dd_et_report.getText().toString();
        valsupplyMTemp.setValagencysupplyremark(report);

        // 当稽查选择错误时
        int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
        count++;
        PrefUtils.putInt(getActivity(),"valterErrorCount",count);

        handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);

    }

    AlertView mAlertViewExt;
    // 经销商选择
    private void setSelectValueRight(final List<KvStc> datas, String key){
        mAlertViewExt = new AlertView("请正确值", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), datas,
                new String[]{"key", "value"}, key);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                zdzs_invoicing_amend_rl_dd_con1_sp_agency.setText(datas.get(position).getValue());
                valsupplyMTemp.setValtrueagency(datas.get(position).getKey());
                valsupplyMTemp.setValtrueagencyname(datas.get(position).getValue());
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 监听返回键
    @Override
    public boolean onBackPressed() {
        if (mAlertViewExt != null && mAlertViewExt.isShowing()) {
            mAlertViewExt.dismiss();
            return true;
        } else {
            return super.onBackPressed();
        }
    }

}
