package dd.tsingtaopad.dd.ddzs.zschatvie.zsaddchatvie;

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

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.db.table.MitValcmpMTemp;
import dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import dd.tsingtaopad.dd.ddzs.zschatvie.ZsChatvieFragment;
import dd.tsingtaopad.dd.ddzs.zsinvoicing.ZsInvoicingService;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsChatVieAddDataFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsChatvieFragment.MyHandler handler;




    protected int position;//
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected List<MitValcmpMTemp> dataLst;//


    private EditText zdzs_chatvie_dd_et_vieagency;
    private EditText zdzs_chatvie_adddata_rl_con2_qd;
    private EditText zdzs_chatvie_adddata_rl_con2_ls;
    private EditText zdzs_chatvie_adddata_rl_con2_ddl;
    private EditText zdzs_chatvie_adddata_rl_con2_ljk;
    private EditText zdzs_chatvie_adddata_dd_et_report;
    private Button sureBtn;



    private ZsInvoicingService zsInvoicingService;
    private MitValcmpMTemp valsupplyMTemp;

    private XtAddInvocingService addInvocingService;
    private List<KvStc> agencyLst;


    public ZsChatVieAddDataFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsChatVieAddDataFragment(ZsChatvieFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_chatvie_adddata, container, false);
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


        zdzs_chatvie_dd_et_vieagency = (EditText) view.findViewById(R.id.zdzs_chatvie_dd_et_vieagency);
        zdzs_chatvie_adddata_rl_con2_qd = (EditText) view.findViewById(R.id.zdzs_chatvie_adddata_rl_con2_qd);
        zdzs_chatvie_adddata_rl_con2_ls = (EditText) view.findViewById(R.id.zdzs_chatvie_adddata_rl_con2_ls);
        zdzs_chatvie_adddata_rl_con2_ddl = (EditText) view.findViewById(R.id.zdzs_chatvie_adddata_rl_con2_ddl);
        zdzs_chatvie_adddata_rl_con2_ljk = (EditText) view.findViewById(R.id.zdzs_chatvie_adddata_rl_con2_ljk);
        zdzs_chatvie_adddata_dd_et_report = (EditText) view.findViewById(R.id.zdzs_chatvie_adddata_dd_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_chatvie_adddata_dd_bt_save);

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
        dataLst = (List<MitValcmpMTemp>) bundle.getSerializable("dataLst");

        initData();
    }

    private void initData() {

        valsupplyMTemp = dataLst.get(position);

        zdzs_chatvie_dd_et_vieagency.setText(valsupplyMTemp.getValcmpagencyval());// 经销商

        zdzs_chatvie_adddata_rl_con2_qd.setText(valsupplyMTemp.getValcmpjdjval());// 渠道价

        zdzs_chatvie_adddata_rl_con2_ls.setText(valsupplyMTemp.getValcmplsjval());// 零售价

        zdzs_chatvie_adddata_rl_con2_ddl.setText(valsupplyMTemp.getValcmpsalesval());// 销量

        zdzs_chatvie_adddata_rl_con2_ljk.setText(valsupplyMTemp.getValcmpkcval());// 库存

        zdzs_chatvie_adddata_dd_et_report.setText(valsupplyMTemp.getValcmpsupremark());// 备注
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_chatvie_adddata_dd_bt_save:// 保存
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    private void saveValue() {

        valsupplyMTemp.setValagencysupplyflag("Y");// 供货关系是否有效


        String qdinfo = zdzs_chatvie_adddata_rl_con2_qd.getText().toString();
        String lsinfo = zdzs_chatvie_adddata_rl_con2_ls.getText().toString();
        String ddlqdinfo = zdzs_chatvie_adddata_rl_con2_ddl.getText().toString();
        String ljkqdinfo = zdzs_chatvie_adddata_rl_con2_ljk.getText().toString();

        valsupplyMTemp.setValcmpjdjval(qdinfo);
        valsupplyMTemp.setValcmplsjval(lsinfo);
        valsupplyMTemp.setValcmpsalesval(ddlqdinfo);
        valsupplyMTemp.setValcmpkcval(ljkqdinfo);

        valsupplyMTemp.setValcmpjdj(qdinfo);//    竞品进店价
        valsupplyMTemp.setValcmplsj(lsinfo);//    竞品零售价
        valsupplyMTemp.setValcmpsales(ddlqdinfo);//  竞品销量
        valsupplyMTemp.setValcmpkc(ljkqdinfo);//竞品库存

        // 经销商
        String vieagency = zdzs_chatvie_dd_et_vieagency.getText().toString();
        valsupplyMTemp.setValcmpagencyval(vieagency);

        valsupplyMTemp.setValcmpagency(vieagency);

        // 备注
        String report = zdzs_chatvie_adddata_dd_et_report.getText().toString();
        valsupplyMTemp.setValcmpsupremark(report);

        handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);

    }
}
