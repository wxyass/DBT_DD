package et.tsingtaopad.dd.ddzs.zsinvoicing.zsaddinvoicing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import et.tsingtaopad.dd.ddzs.zsinvoicing.ZsInvoicingFragment;
import et.tsingtaopad.dd.ddzs.zsinvoicing.ZsInvoicingService;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsInvocingAddDataFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsInvoicingFragment.MyHandler handler;




    protected int position;//
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected List<MitValsupplyMTemp> dataLst;//


    private EditText zdzs_invoicing_adddata_rl_con2_qd;
    private EditText zdzs_invoicing_adddata_rl_con2_ls;
    private EditText zdzs_invoicing_adddata_rl_con2_ddl;
    private EditText zdzs_invoicing_adddata_rl_con2_ljk;
    private EditText zdzs_invoicing_adddata_dd_et_report;
    private Button sureBtn;



    private ZsInvoicingService zsInvoicingService;
    private MitValsupplyMTemp valsupplyMTemp;

    private XtAddInvocingService addInvocingService;
    private List<KvStc> agencyLst;


    public ZsInvocingAddDataFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsInvocingAddDataFragment(ZsInvoicingFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_invoicing_adddata, container, false);
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


        zdzs_invoicing_adddata_rl_con2_qd = (EditText) view.findViewById(R.id.zdzs_invoicing_adddata_rl_con2_qd);
        zdzs_invoicing_adddata_rl_con2_ls = (EditText) view.findViewById(R.id.zdzs_invoicing_adddata_rl_con2_ls);
        zdzs_invoicing_adddata_rl_con2_ddl = (EditText) view.findViewById(R.id.zdzs_invoicing_adddata_rl_con2_ddl);
        zdzs_invoicing_adddata_rl_con2_ljk = (EditText) view.findViewById(R.id.zdzs_invoicing_adddata_rl_con2_ljk);
        zdzs_invoicing_adddata_dd_et_report = (EditText) view.findViewById(R.id.zdzs_invoicing_adddata_dd_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_invoicing_adddata_dd_bt_save);

        sureBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入数据");

        zsInvoicingService = new ZsInvoicingService(getActivity(), handler);
        addInvocingService = new XtAddInvocingService(getActivity(), null);

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        termId = bundle.getString("termId");
        mitValterMTempKey = bundle.getString("mitValterMTempKey");//追溯主键
        dataLst = (List<MitValsupplyMTemp>) bundle.getSerializable("dataLst");

        initData();
    }

    private void initData() {

        valsupplyMTemp = dataLst.get(position);

        zdzs_invoicing_adddata_rl_con2_qd.setText(valsupplyMTemp.getValagencysupplyqd());// 督导 渠道价

        zdzs_invoicing_adddata_rl_con2_ls.setText(valsupplyMTemp.getValagencysupplyls());// 督导 零售价

        zdzs_invoicing_adddata_rl_con2_ddl.setText(valsupplyMTemp.getValagencysupplydd());// 督导 订单量价

        zdzs_invoicing_adddata_rl_con2_ljk.setText(valsupplyMTemp.getValagencysupplyljk());// 督导 累计卡

        zdzs_invoicing_adddata_dd_et_report.setText(valsupplyMTemp.getValagencysupplyremark());// 督导 备注
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_invoicing_adddata_dd_bt_save:// 保存
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    private void saveValue() {

        valsupplyMTemp.setValagencysupplyflag("Y");// 供货关系是否有效


        String qdinfo = zdzs_invoicing_adddata_rl_con2_qd.getText().toString();
        String lsinfo = zdzs_invoicing_adddata_rl_con2_ls.getText().toString();
        String ddlqdinfo = zdzs_invoicing_adddata_rl_con2_ddl.getText().toString();
        String ljkqdinfo = zdzs_invoicing_adddata_rl_con2_ljk.getText().toString();

        valsupplyMTemp.setValsqd(qdinfo);
        valsupplyMTemp.setValagencysupplyqd(qdinfo);
        valsupplyMTemp.setValsls(lsinfo);
        valsupplyMTemp.setValagencysupplyls(lsinfo);
        valsupplyMTemp.setValsdd(ddlqdinfo);
        valsupplyMTemp.setValagencysupplydd(ddlqdinfo);
        valsupplyMTemp.setValsljk(ljkqdinfo);
        valsupplyMTemp.setValagencysupplyljk(ljkqdinfo);

        // 备注
        String report = zdzs_invoicing_adddata_dd_et_report.getText().toString();
        valsupplyMTemp.setValagencysupplyremark(report);

        handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);
    }
}
