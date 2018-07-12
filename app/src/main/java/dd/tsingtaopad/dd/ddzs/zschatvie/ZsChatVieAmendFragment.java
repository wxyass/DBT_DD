package dd.tsingtaopad.dd.ddzs.zschatvie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.db.table.MitValcmpMTemp;
import dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import dd.tsingtaopad.dd.ddzs.zsinvoicing.ZsInvoicingService;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsChatVieAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsChatvieFragment.MyHandler handler;

    private Button sureBtn;


    protected int position;// 页面类型,比如: "1"
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected List<MitValcmpMTemp> dataLst;//
    protected boolean valagencyerror;//
    protected boolean valdataerror;//
    protected boolean valiffleeing;//



    /*private LinearLayout zdzs_invoicing_amend_rl_ll_agency;
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
    private Button zdzs_invoicing_amend_dd_bt_save;*/


    private LinearLayout zdzs_chatvie_amend_rl_ll_agency;
    private EditText zdzs_chatvie_amend_dd_et_vieagency;

    private LinearLayout zdzs_chatvie_amend_rl_ll_head;

    private RelativeLayout zdzs_chatvie_amend_rl_dd_qd;
    private TextView zdzs_chatvie_amend_rl_dd_con1_qd;
    private EditText zdzs_chatvie_amend_rl_con2_qd;

    private RelativeLayout zdzs_chatvie_amend_rl_dd_ls;
    private TextView zdzs_chatvie_amend_rl_dd_con1_ls;
    private EditText zdzs_chatvie_amend_rl_con2_ls;

    private RelativeLayout zdzs_chatvie_amend_rl_dd_ddl;
    private TextView zdzs_chatvie_amend_rl_dd_con1_ddl;
    private EditText zdzs_chatvie_amend_rl_con2_ddl;

    private RelativeLayout zdzs_chatvie_amend_rl_dd_ljk;
    private TextView zdzs_chatvie_amend_rl_dd_con1_ljk;
    private EditText zdzs_chatvie_amend_rl_con2_ljk;

    private EditText zdzs_chatvie_amend_dd_et_report;

    private Button zdzs_chatvie_amend_dd_bt_save;


    private ZsInvoicingService zsInvoicingService;
    private MitValcmpMTemp valsupplyMTemp;

    private XtAddInvocingService addInvocingService;
    private List<KvStc> agencyLst;


    public ZsChatVieAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsChatVieAmendFragment(ZsChatvieFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_chatvie_amend, container, false);
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


        zdzs_chatvie_amend_rl_ll_agency = (LinearLayout) view.findViewById(R.id.zdzs_chatvie_amend_rl_ll_agency);

        zdzs_chatvie_amend_dd_et_vieagency = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_dd_et_vieagency);

        zdzs_chatvie_amend_rl_ll_head = (LinearLayout) view.findViewById(R.id.zdzs_chatvie_amend_rl_ll_head);

        zdzs_chatvie_amend_rl_dd_qd = (RelativeLayout) view.findViewById(R.id.zdzs_chatvie_amend_rl_dd_qd);
        zdzs_chatvie_amend_rl_dd_con1_qd = (TextView) view.findViewById(R.id.zdzs_chatvie_amend_rl_dd_con1_qd);
        zdzs_chatvie_amend_rl_con2_qd = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_rl_con2_qd);

        zdzs_chatvie_amend_rl_dd_ls = (RelativeLayout)view. findViewById(R.id.zdzs_chatvie_amend_rl_dd_ls);
        zdzs_chatvie_amend_rl_dd_con1_ls = (TextView) view.findViewById(R.id.zdzs_chatvie_amend_rl_dd_con1_ls);
        zdzs_chatvie_amend_rl_con2_ls = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_rl_con2_ls);

        zdzs_chatvie_amend_rl_dd_ddl = (RelativeLayout)view. findViewById(R.id.zdzs_chatvie_amend_rl_dd_ddl);
        zdzs_chatvie_amend_rl_dd_con1_ddl = (TextView) view.findViewById(R.id.zdzs_chatvie_amend_rl_dd_con1_ddl);
        zdzs_chatvie_amend_rl_con2_ddl = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_rl_con2_ddl);

        zdzs_chatvie_amend_rl_dd_ljk = (RelativeLayout)view. findViewById(R.id.zdzs_chatvie_amend_rl_dd_ljk);
        zdzs_chatvie_amend_rl_dd_con1_ljk = (TextView) view.findViewById(R.id.zdzs_chatvie_amend_rl_dd_con1_ljk);
        zdzs_chatvie_amend_rl_con2_ljk = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_rl_con2_ljk);

        zdzs_chatvie_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_chatvie_amend_dd_et_report);

        sureBtn = (Button) view.findViewById(R.id.zdzs_chatvie_amend_dd_bt_save);


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
        dataLst = (List<MitValcmpMTemp>) bundle.getSerializable("dataLst");


        valagencyerror = bundle.getBoolean("valagencyerror");
        valdataerror = bundle.getBoolean("valdataerror");
        valiffleeing = bundle.getBoolean("valiffleeing");

        initData();
    }

    private void initData() {

        valsupplyMTemp = dataLst.get(position);

        // 经销商显示
        if(valagencyerror){
            zdzs_chatvie_amend_rl_ll_agency.setVisibility(View.VISIBLE);
        }else{
            zdzs_chatvie_amend_rl_ll_agency.setVisibility(View.GONE);
        }
        // 数据显示
        if(valdataerror){
            zdzs_chatvie_amend_rl_ll_head.setVisibility(View.VISIBLE);
        }else{
            zdzs_chatvie_amend_rl_ll_head.setVisibility(View.GONE);
        }


        zdzs_chatvie_amend_dd_et_vieagency.setText(valsupplyMTemp.getValcmpagencyval());// 经销商名称// Valcmpagencyval

        zdzs_chatvie_amend_rl_dd_con1_qd.setText(valsupplyMTemp.getValcmpjdj());// 渠道价
        zdzs_chatvie_amend_rl_con2_qd.setText(valsupplyMTemp.getValcmpjdjval());// 渠道价

        zdzs_chatvie_amend_rl_dd_con1_ls.setText(valsupplyMTemp.getValcmplsj());// 零售价
        zdzs_chatvie_amend_rl_con2_ls.setText(valsupplyMTemp.getValcmplsjval());// 零售价

        zdzs_chatvie_amend_rl_dd_con1_ddl.setText(valsupplyMTemp.getValcmpsales());// 销量
        zdzs_chatvie_amend_rl_con2_ddl.setText(valsupplyMTemp.getValcmpsalesval());// 销量

        zdzs_chatvie_amend_rl_dd_con1_ljk.setText(valsupplyMTemp.getValcmpkc());// 库存
        zdzs_chatvie_amend_rl_con2_ljk.setText(valsupplyMTemp.getValcmpkcval());// 库存

        zdzs_chatvie_amend_dd_et_report.setText(valsupplyMTemp.getValcmpsupremark());// 备注
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;


            case R.id.zdzs_chatvie_amend_dd_bt_save:// 保存
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    private void saveValue() {

        valsupplyMTemp.setValagencysupplyflag("N");

        // 经销商显示
        if(valagencyerror){
            valsupplyMTemp.setValagencyerror("Y");
        }else{
            valsupplyMTemp.setValagencyerror("N");
        }
        // 数据显示
        if(valdataerror){
            valsupplyMTemp.setValdataerror("Y");
        }else{
            valsupplyMTemp.setValdataerror("N");
        }

        String qdinfo = zdzs_chatvie_amend_rl_con2_qd.getText().toString();
        String lsinfo = zdzs_chatvie_amend_rl_con2_ls.getText().toString();
        String ddlqdinfo = zdzs_chatvie_amend_rl_con2_ddl.getText().toString();
        String ljkqdinfo = zdzs_chatvie_amend_rl_con2_ljk.getText().toString();

        valsupplyMTemp.setValcmpjdjval(qdinfo);
        valsupplyMTemp.setValcmplsjval(lsinfo);
        valsupplyMTemp.setValcmpsalesval(ddlqdinfo);
        valsupplyMTemp.setValcmpkcval(ljkqdinfo);

        // 经销商
        String vieagency = zdzs_chatvie_amend_dd_et_vieagency.getText().toString();
        valsupplyMTemp.setValcmpagencyval(vieagency);

        // 备注
        String report = zdzs_chatvie_amend_dd_et_report.getText().toString();
        valsupplyMTemp.setValcmpsupremark(report);

        handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);

    }

}
