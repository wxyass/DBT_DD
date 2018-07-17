package et.tsingtaopad.dd.ddzs.zsinvoicing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.db.table.MitValaddaccountproMTemp;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import et.tsingtaopad.dd.ddxt.invoicing.XtInvoicingService;
import et.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsaddinvoicing.ZsAddInvoicingFragment;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsaddinvoicing.ZsInvocingAddDataFragment;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.ZsInvoicingTzAmendFragment;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.ZsInvoicingTzAdapter;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzItemIndex;
import et.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.listviewintf.IClick;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsInvoicingFragment extends XtBaseVisitFragment implements View.OnClickListener {

    private final String TAG = "ZsInvoicingFragment";

    //List<XtInvoicingStc> dataLst;
    List<MitValsupplyMTemp> dataLst;
    List<ZsTzItemIndex> termLedgerInfos;
    ZsInvoicingService invoicingService;
    MyHandler handler;

    public static final int ZS_ADD_SUC = 2;// 追溯-新增我品供货关系成功
    public static final int INIT_AMEND = 22;// 督导输入数据成功

    public static final int MAKE_RIGHT_TZ = 23;// 台账稽查正确

    ZsInvoicingAdapter zsInvoicingAdapter;
    ZsInvoicingTzAdapter tzAdapter;


    private ScrollView zdzs_invoicing_scrollView;
    private ImageView zdzs_invoicing_point1;
    private TextView zdzs_invocing_textview01;
    private RelativeLayout zdzs_invoicing_rl_addrelation;
    private Button zdzs_invoicing_bt_addrelation;
    private ListView zsInvoicingLv;
    private ListView zsTermtzLv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_invoicing, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        zdzs_invoicing_scrollView = (ScrollView) view.findViewById(R.id.zdzs_invoicing_scrollView);
        zdzs_invoicing_point1 = (ImageView) view.findViewById(R.id.zdzs_invoicing_point1);
        zdzs_invocing_textview01 = (TextView) view.findViewById(R.id.zdzs_invocing_textview01);
        zdzs_invoicing_rl_addrelation = (RelativeLayout) view.findViewById(R.id.zdzs_invoicing_rl_addrelation);
        zdzs_invoicing_bt_addrelation = (Button) view.findViewById(R.id.zdzs_invoicing_bt_addrelation);
        zsInvoicingLv = (ListView) view.findViewById(R.id.zdzs_invoicing_lv_invoicing);
        zsTermtzLv = (ListView) view.findViewById(R.id.zdzs_invoicing_lv_tz);


        zdzs_invoicing_bt_addrelation.setOnClickListener(this);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);
        invoicingService = new ZsInvoicingService(getActivity(), null);

        // 根据追溯模板 设置各个控件是否显示
        initViewVisible();

        // 初始化产品数据
        initProData();

    }

    // 根据追溯模板 设置各个控件是否显示
    private void initViewVisible() {
        // 新增供货关系
        if ("Y".equals(mitValcheckterM.getAddsupply())) {
            zdzs_invoicing_rl_addrelation.setVisibility(View.VISIBLE);
        }
    }


    // 初始化产品数据
    private void initProData() {
        //删除重复拜访产品
        invoicingService.delRepeatVistProduct(visitId);
        //获取某次拜访的我品的进销存数据情况
        //dataLst = invoicingService.queryMineProToValSupplyTemp(visitId, termId,mitValterMTempKey);
        //mitValsupplyMTemps = invoicingService.queryMineProToValSupplyTemp(visitId, termId,mitValterMTempKey);
        dataLst = invoicingService.queryValSupplyTemp(mitValterMTempKey);

        //
        zsInvoicingAdapter = new ZsInvoicingAdapter(getActivity(), dataLst, new IClick() {
            @Override
            public void listViewItemClick(int position, View v) {

                if ("Y".equals(dataLst.get(position).getValaddagencysupply())) {// 督导新增的供货关系
                    alertShow4(position);
                    //Toast.makeText(getActivity(),dataLst.get(position).getValproname(),Toast.LENGTH_SHORT).show();
                } else {// 处理业代供货关系
                    alertShow3(position);
                }

            }
        });
        zsInvoicingLv.setAdapter(zsInvoicingAdapter);
        ViewUtil.setListViewHeight(zsInvoicingLv);


        // 查出该终端 所有业代终端台账表
        termLedgerInfos = invoicingService.queryValTermLedger(termId);

        tzAdapter = new ZsInvoicingTzAdapter(getActivity(), termLedgerInfos, new IClick() {
            @Override
            public void listViewItemClick(int position, View v) {
                alertShow5(position);
                // Toast.makeText(getActivity(),termLedgerInfos.get(position).getValproname(),Toast.LENGTH_SHORT).show();
            }
        });
        zsTermtzLv.setAdapter(tzAdapter);
        ViewUtil.setListViewHeight(zsTermtzLv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zdzs_invoicing_bt_addrelation:
                Bundle bundle = new Bundle();
                bundle.putSerializable("termId", termStc.getTerminalkey());
                bundle.putSerializable("termname", termStc.getTerminalname());
                bundle.putSerializable("channelId", termStc.getMinorchannel());// 次渠道
                bundle.putSerializable("termStc", termStc);
                bundle.putSerializable("visitKey", visitId);//visitId
                bundle.putSerializable("seeFlag", seeFlag);// 默认0   0:拜访 1:查看

                ZsAddInvoicingFragment xtaddinvoicingfragment = new ZsAddInvoicingFragment(handler);
                xtaddinvoicingfragment.setArguments(bundle);

                ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity) getActivity();
                xtVisitShopActivity.changeXtvisitFragment(xtaddinvoicingfragment, "xtaddchatviefragment");
                break;

            default:
                break;
        }
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsInvoicingFragment> fragmentRef;

        public MyHandler(ZsInvoicingFragment fragment) {
            fragmentRef = new SoftReference<ZsInvoicingFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsInvoicingFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            ArrayList<KvStc> products = (ArrayList<KvStc>) msg.obj;
            Bundle bundle = msg.getData();
            KvStc agency = (KvStc) bundle.getSerializable("agency");

            // 处理UI 变化
            switch (msg.what) {
                case ZS_ADD_SUC:
                    fragment.showAddProSuc(products, agency);
                    break;
                case INIT_AMEND: // 督导输入数据后
                    fragment.showAdapter();
                    break;
                case MAKE_RIGHT_TZ: // 台账输入数据后
                    fragment.showTzAdapter();
                    break;
            }
        }
    }

    private void showTzAdapter() {
        tzAdapter.notifyDataSetChanged();
    }

    private void showAdapter() {
        zsInvoicingAdapter.notifyDataSetChanged();
        ViewUtil.setListViewHeight(zsInvoicingLv);
    }

    /**
     * 新增供货关系 添加产品成功 UI
     */
    public void showAddProSuc(ArrayList<KvStc> products, KvStc agency) {
        for (KvStc product : products) {
            //DbtLog.logUtils(TAG,"经销商key:"+agency.getKey()+"、经销商名称:"+agency.getValue()+"-->产品key："+product.getKey()+"、产品名称："+product.getValue());
            //List<String> proIdLst = FunUtil.getPropertyByName(dataLst, "proId", String.class);
            //List<String> proIdLst = FunUtil.getPropertyByName(dataLst, "valpro", String.class);
            List<String> proIdLst = new ArrayList<String>();

            // 此处需要一个for
            for (MitValsupplyMTemp valsupplyMTemp : dataLst) {
                // 供货关系是否有效 为null,为"", 为"Y" 都是有效
                if (valsupplyMTemp.getValagencysupplyflag() == null || "Y".equals(valsupplyMTemp.getValagencysupplyflag())
                        || "".equals(valsupplyMTemp.getValagencysupplyflag())) {
                    proIdLst.add(valsupplyMTemp.getValpro());
                }
            }
            if (proIdLst.contains(product.getKey())) {
                DbtLog.logUtils(TAG, "产品重复提示");
                Toast.makeText(getActivity(), getString(R.string.addrelation_msg_repetitionadd), Toast.LENGTH_LONG).show();
            } else {
                /*XtInvoicingStc supplyStc = new XtInvoicingStc();
                supplyStc.setProId(product.getKey());
                supplyStc.setProName(product.getValue());
                supplyStc.setAgencyId(agency.getKey());
                supplyStc.setAgencyName(agency.getValue());
                supplyStc.setPrevStore("0");
                dataLst.add(supplyStc);
                //新增供货关系指标记录表进行更新
                invoicingService.updateMstcheckexerecordInfoTempDeleteflag(visitId,product.getKey());*/


                MitValsupplyMTemp valsupplyMTemp = new MitValsupplyMTemp();
                valsupplyMTemp.setId(FunUtil.getUUID());
                valsupplyMTemp.setValterid(mitValterMTempKey);// 终端追溯主表ID
                valsupplyMTemp.setValaddagencysupply("Y");// 是否新增供货关系
                valsupplyMTemp.setValagency(agency.getKey());// 经销商;
                valsupplyMTemp.setValagencyname(agency.getValue());// 经销商名称
                valsupplyMTemp.setValpro(product.getKey());// 产品
                valsupplyMTemp.setValproname(product.getValue());// 产品名称
                dataLst.add(valsupplyMTemp);

                zsInvoicingAdapter.setDelPosition(-1);
                zsInvoicingAdapter.notifyDataSetChanged();
                ViewUtil.setListViewHeight(zsInvoicingLv);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");

        // 保存追溯 进销存数据  MitValsupplyMTemp
        invoicingService.saveZsInvoicing(dataLst);
        invoicingService.saveZsInvoicingTz(termLedgerInfos);


    }

    private AlertView mAlertViewExt;//窗口拓展例子

    // 长按删除我品供货关系弹窗
    private void deletesupply(int position, final XtInvoicingStc xtInvoicingStc) {
        String proName = xtInvoicingStc.getProName();
        // 普通窗口
        mAlertViewExt = new AlertView("解除我品: " + proName, null, "取消", new String[]{"确定"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "删除供货关系：是");
                            // 删除对应的数据
                            if (!CheckUtil.isBlankOrNull(xtInvoicingStc.getRecordId())) {
                                DbtLog.logUtils(TAG, "解除供货关系");
                                XtInvoicingService service = new XtInvoicingService(getActivity(), null);
                                boolean isFlag = service.deleteSupply(xtInvoicingStc.getRecordId(), termId, visitId, xtInvoicingStc.getProId());
                                if (isFlag) {
                                    // 删除界面listView相应行
                                    dataLst.remove(position);
                                    zsInvoicingAdapter.notifyDataSetChanged();

                                    ViewUtil.setListViewHeight(zsInvoicingLv);
                                } else {
                                    Toast.makeText(getActivity(), "删除产品失败!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // 删除界面listView相应行
                                dataLst.remove(position);
                                zsInvoicingAdapter.notifyDataSetChanged();

                                ViewUtil.setListViewHeight(zsInvoicingLv);
                            }
                        }

                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "删除供货关系：否");
                    }
                });
        mAlertViewExt.show();
    }

    /**
     * 弹窗3
     * 参数1: 标题 ×
     * 参数2: 主体内容    ×
     * 参数3: 取消按钮    ×
     * 参数4: 高亮按钮 数组 √
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (正常取消,确定按钮)   √
     * 参数8: 条目点击监听  √
     */
    MitValsupplyMTemp valsupplyMTemp;

    public void alertShow3(final int position) {

        // mAlertViewExt = new AlertView(null, null, null, null,null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_result_form, null);

        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");

        final RadioButton right_rb = (RadioButton) extView.findViewById(R.id.result_right_rb);
        final RadioGroup right_rg = (RadioGroup) extView.findViewById(R.id.result_right_rg);
        final CheckBox cb1 = (CheckBox) extView.findViewById(R.id.result_cb1);
        final CheckBox cb2 = (CheckBox) extView.findViewById(R.id.result_cb2);
        final CheckBox cb3 = (CheckBox) extView.findViewById(R.id.result_cb3);
        final CheckBox cb4 = (CheckBox) extView.findViewById(R.id.result_cb4);

        // 根据督导模板配置选项
        // 品项有误
        if ("Y".equals(mitValcheckterM.getProerror())) {
            cb1.setVisibility(View.VISIBLE);
        }
        // 经销商有误
        if ("Y".equals(mitValcheckterM.getAgencyerror())) {
            cb2.setVisibility(View.VISIBLE);
        }
        // 数据有误
        if ("Y".equals(mitValcheckterM.getDataerror())) {
            cb3.setVisibility(View.VISIBLE);
        }
        // 是否窜货
        if ("Y".equals(mitValcheckterM.getIffleeing())) {
            cb4.setVisibility(View.VISIBLE);
        }

        valsupplyMTemp = dataLst.get(position);
        String flag = valsupplyMTemp.getValagencysupplyflag();
        if ("Y".equals(flag)) {
            right_rb.setChecked(true);
        }
        String flag1 = valsupplyMTemp.getValproerror();
        String flag2 = valsupplyMTemp.getValagencyerror();
        String flag3 = valsupplyMTemp.getValdataerror();
        String flag4 = valsupplyMTemp.getValiffleeing();
        if ("Y".equals(flag1)) {
            cb1.setChecked(true);
            right_rg.clearCheck();
        }
        if ("Y".equals(flag2)) {
            cb2.setChecked(true);
            right_rg.clearCheck();
        }
        if ("Y".equals(flag3)) {
            cb3.setChecked(true);
            right_rg.clearCheck();
        }
        if ("Y".equals(flag4)) {
            cb4.setChecked(true);
            right_rg.clearCheck();
        }

        // 显示对话框
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(extView, 0, 0, 0, 0);
        dialog.setCancelable(false);
        dialog.show();


        // 取消按钮
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // 确定按钮
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 请至少勾选一项
                if ((!right_rb.isChecked()) && (!cb1.isChecked()) && (!cb2.isChecked()) && (!cb3.isChecked()) && (!cb4.isChecked())) {
                    Toast.makeText(getActivity(), "请至少勾选一项", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (right_rb.isChecked()) {

                    // 当稽查错误,又改正确时
                    if("N".equals(valsupplyMTemp.getValagencysupplyflag())){
                        int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
                        count--;
                        PrefUtils.putInt(getActivity(),"valterErrorCount",count);
                    }

                    valsupplyMTemp.setValagencysupplyflag("Y");// 供货关系正确与否
                    valsupplyMTemp.setValproerror("N");// 品项有误
                    valsupplyMTemp.setValagencyerror("N");// 经销商有误
                    valsupplyMTemp.setValdataerror("N");// 数据有误
                    valsupplyMTemp.setValiffleeing("N");// 窜货
                    valsupplyMTemp.setValagencyqdflag("Y");// 供货关系正确渠道价状态
                    valsupplyMTemp.setValagencylsflag("Y");// 供货关系正确零售价状态
                    dialog.dismiss();
                    handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);
                } else {
                    if (cb1.isChecked()) {// 品项有误
                        valsupplyMTemp.setValagencysupplyflag("N");// 供货关系正确与否
                        valsupplyMTemp.setValproerror("Y");// 品项有误
                        valsupplyMTemp.setValagencyerror("N");// 经销商有误
                        valsupplyMTemp.setValdataerror("N");// 数据有误
                        valsupplyMTemp.setValiffleeing("N");// 窜货
                        dialog.dismiss();
                        handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);
                    } else {
                        valsupplyMTemp.setValagencysupplyflag("N");// 供货关系正确与否
                        valsupplyMTemp.setValproerror("N");// 品项有误

                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);//
                        bundle.putString("termId", termId);//
                        bundle.putString("mitValterMTempKey", mitValterMTempKey);
                        bundle.putBoolean("valagencyerror", cb2.isChecked());
                        bundle.putBoolean("valdataerror", cb3.isChecked());
                        bundle.putBoolean("valiffleeing", cb4.isChecked());
                        bundle.putSerializable("dataLst", (Serializable) dataLst);
                        ZsInvocingAmendFragment zsInvocingAmendFragment = new ZsInvocingAmendFragment(handler);
                        zsInvocingAmendFragment.setArguments(bundle);
                        ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                        zsVisitShopActivity.changeXtvisitFragment(zsInvocingAmendFragment, "zsamendfragment");
                        dialog.dismiss();
                    }
                }
            }
        });

        // 正确
        right_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });

        // 品项有误
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    right_rg.clearCheck();
                }
            }
        });

        // 经销商有误
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    right_rg.clearCheck();
                }
            }
        });

        // 数据有误
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    right_rg.clearCheck();
                }
            }
        });

        // 窜货
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    right_rg.clearCheck();
                }
            }
        });

        /*mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();*/
    }

    public void alertShow5(final int posi) {

        /*List<KvStc> sureOrFail = new ArrayList<>();
        sureOrFail.add(new KvStc("zhengque","正确","-1"));
        sureOrFail.add(new KvStc("cuowu","错误(去修正)","-1"));
        mAlertViewExt = new AlertView("请选择结果", null, null, null,
                null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), sureOrFail,
                new String[]{"key", "value"}, "zhengque");
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(0==position){
                    termLedgerInfos.get(posi).setValprostatus("Y");
                    handler.sendEmptyMessage(ZsInvoicingFragment.MAKE_RIGHT_TZ);
                }
                else if(1==position){
                    Bundle bundle = new Bundle();
                    // bundle.putString("mitValterMTempKey", mitValterMTempKey);
                    bundle.putSerializable("zstzitemindex", termLedgerInfos.get(posi));
                    ZsInvoicingTzAmendFragment tzFragment = new ZsInvoicingTzAmendFragment(handler);
                    tzFragment.setArguments(bundle);
                    ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity)getActivity();
                    zsVisitShopActivity.changeXtvisitFragment(tzFragment,"zssayhiamendfragment");
                }

                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();*/

        new AlertView("请选择结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确

                            // 当稽查错误,又改正确时
                            if("N".equals(termLedgerInfos.get(posi).getValprostatus())){
                                int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
                                count--;
                                PrefUtils.putInt(getActivity(),"valterErrorCount",count);
                            }

                            termLedgerInfos.get(posi).setValprostatus("Y");
                            List<MitValaddaccountproMTemp>  mTemps = termLedgerInfos.get(posi).getIndexValueLst();
                            for(MitValaddaccountproMTemp temp : mTemps){
                                temp.setValpronumfalg("Y");
                            }
                            handler.sendEmptyMessage(ZsInvoicingFragment.MAKE_RIGHT_TZ);
                        } else if (1 == position) {// 错误
                            Bundle bundle = new Bundle();
                            // bundle.putString("mitValterMTempKey", mitValterMTempKey);
                            bundle.putSerializable("zstzitemindex", termLedgerInfos.get(posi));
                            ZsInvoicingTzAmendFragment tzFragment = new ZsInvoicingTzAmendFragment(handler);
                            tzFragment.setArguments(bundle);
                            ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            zsVisitShopActivity.changeXtvisitFragment(tzFragment, "zssayhiamendfragment");
                        }

                    }
                }).setCancelable(true).show();
    }

    /**
     * 参数1: 标题 ×
     * 参数2: 主体内容 ×
     * 参数3: 取消按钮 ×
     * 参数4: 高亮按钮 数组 √
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (正常取消,确定按钮) √
     * 参数8: 条目点击监听 √
     */
    public void alertShow4(final int posi) {
        /*List<KvStc> sureOrFail = new ArrayList<>();
        sureOrFail.add(new KvStc("zhengque", "失效这条供货关系", "-1"));
        sureOrFail.add(new KvStc("cuowu", "录入该条记录数据", "-1"));
        mAlertViewExt = new AlertView(null, null, null, null,
                null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), sureOrFail,
                new String[]{"key", "value"}, "zhengque");
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 == position) {// 失效
                    dataLst.remove(posi);
                    handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);
                } else {// 跳转数据录入
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", posi);//
                    bundle.putString("termId", termId);//
                    bundle.putString("mitValterMTempKey", mitValterMTempKey);
                    bundle.putSerializable("dataLst", (Serializable) dataLst);
                    ZsInvocingAddDataFragment zsInvocingAddDataFragment = new ZsInvocingAddDataFragment(handler);
                    zsInvocingAddDataFragment.setArguments(bundle);
                    ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                    zsVisitShopActivity.changeXtvisitFragment(zsInvocingAddDataFragment, "zsamendfragment");
                }

                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();*/


        new AlertView("处理新增供货关系", null, "取消", null,
                new String[]{"失效这条供货关系", "录入该条记录数据"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 失效这条供货关系
                            dataLst.remove(posi);
                            handler.sendEmptyMessage(ZsInvoicingFragment.INIT_AMEND);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", posi);//
                            bundle.putString("termId", termId);//
                            bundle.putString("mitValterMTempKey", mitValterMTempKey);
                            bundle.putSerializable("dataLst", (Serializable) dataLst);
                            ZsInvocingAddDataFragment zsInvocingAddDataFragment = new ZsInvocingAddDataFragment(handler);
                            zsInvocingAddDataFragment.setArguments(bundle);
                            ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            zsVisitShopActivity.changeXtvisitFragment(zsInvocingAddDataFragment, "zsamendfragment");
                        }

                    }
                }).setCancelable(true).show();
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
