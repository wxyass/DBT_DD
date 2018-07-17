package et.tsingtaopad.dd.ddzs.zschatvie;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.db.table.MitValcmpMTemp;
import et.tsingtaopad.db.table.MitValcmpotherMTemp;
import et.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import et.tsingtaopad.dd.ddxt.chatvie.XtChatVieService;
import et.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;
import et.tsingtaopad.dd.ddzs.zschatvie.zsaddchatvie.ZsChatVieAddDataFragment;
import et.tsingtaopad.listviewintf.IClick;
import et.tsingtaopad.dd.ddzs.zschatvie.zsaddchatvie.ZsAddChatVieFragment;
import et.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsChatvieFragment extends XtBaseVisitFragment implements View.OnClickListener {

    private final String TAG = "ZsChatvieFragment";

    private ImageView zdzs_chatvie_point1;
    private TextView zdzs_chatvie_textview01;
    private Button zdzs_chatvie_bt_addrelation;
    private ListView zsChatvieLv;
    private LinearLayout zdzs_chatvie_ll_clearvie;
    private RelativeLayout zdzs_chatvie_rl_clearvie;
    private TextView zdzs_chatvie_rl_clearvie_con1;
    private TextView zdzs_chatvie_rl_clearvie_statue;
    /*private LinearLayout zdzs_chatvie_ll_visitreport_title;
    private LinearLayout zdzs_chatvie_ll_visitreport;
    private EditText zdzs_chatvie_et_visitreport;*/

    ZsChatvieAdapter zsChatvieAdapter;

    MitValcmpotherMTemp mitValcmpotherMTemp = null;

    public static final int ZS_ADD_VIE_SUC = 3;// 新增竞品关系成功
    public static final int INIT_AMEND = 32;// 督导处理错误数据成功
    public static final int INIT_WJ_AMEND = 422;// 瓦解竞品处理成功
    MyHandler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_chatvie, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        zdzs_chatvie_point1 = (ImageView) view.findViewById(R.id.zdzs_chatvie_point1);
        zdzs_chatvie_textview01 = (TextView) view.findViewById(R.id.zdzs_chatvie_textview01);
        zdzs_chatvie_bt_addrelation = (Button) view.findViewById(R.id.zdzs_chatvie_bt_addrelation);
        zsChatvieLv = (ListView) view.findViewById(R.id.zdzs_chatvie_lv_viesource);
        zdzs_chatvie_ll_clearvie = (LinearLayout) view.findViewById(R.id.zdzs_chatvie_ll_clearvie);
        zdzs_chatvie_rl_clearvie = (RelativeLayout) view.findViewById(R.id.zdzs_chatvie_rl_clearvie);
        zdzs_chatvie_rl_clearvie_con1 = (TextView) view.findViewById(R.id.zdzs_chatvie_rl_clearvie_con1);
        zdzs_chatvie_rl_clearvie_statue = (TextView) view.findViewById(R.id.zdzs_chatvie_rl_clearvie_statue);
        /*zdzs_chatvie_ll_visitreport_title = (LinearLayout) view.findViewById(R.id.zdzs_chatvie_ll_visitreport_title);
        zdzs_chatvie_ll_visitreport = (LinearLayout) view.findViewById(R.id.zdzs_chatvie_ll_visitreport);
        zdzs_chatvie_et_visitreport = (EditText) view.findViewById(R.id.zdzs_chatvie_et_visitreport);*/


        zdzs_chatvie_bt_addrelation.setOnClickListener(this);
        zdzs_chatvie_rl_clearvie.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        zsChatVieService = new ZsChatVieService(getActivity(), null);
        handler = new MyHandler(this);

        // 根据追溯模板 设置各个控件是否显示
        initViewVisible();

        initProData();
    }

    // 根据追溯模板 设置各个控件是否显示
    private void initViewVisible() {
        // 新增供货关系
        if("Y".equals(mitValcheckterM.getAddsupply())){
            zdzs_chatvie_bt_addrelation.setVisibility(View.VISIBLE);
        }
        // 是否成功瓦解竞品
        if("Y".equals(mitValcheckterM.getIfcmp())){
            zdzs_chatvie_rl_clearvie.setVisibility(View.VISIBLE);
            zdzs_chatvie_ll_clearvie.setVisibility(View.VISIBLE);
        }

    }

    //List<XtChatVieStc> dataLst;
    List<MitValcmpMTemp> dataLst;
    ZsChatVieService zsChatVieService;
    // private MstVisitMTemp visitMTemp;
    MitValcmpMTemp valsupplyMTemp;

    // 初始化数据
    private void initProData() {

        zsChatVieService.delRepeatVistProduct(visitId);
        //dataLst = xtChatVieService.queryVieProTemp(visitId);
        dataLst = zsChatVieService.queryValVieSupplyTemp(mitValterMTempKey);

        /*visitMTemp = zsChatVieService.findVisitTempById(visitId);// 拜访临时表记录

        if (visitMTemp != null) {
            // 是否瓦解竞品  0:未瓦解  1:瓦解
            if (ConstValues.FLAG_1.equals(visitMTemp.getIscmpcollapse()) ) {
                zdzs_chatvie_rl_clearvie_con1.setText("是");
            } else {
                zdzs_chatvie_rl_clearvie_con1.setText("否");
            }
        }*/

        // 竞品信息
        zsChatvieAdapter = new ZsChatvieAdapter(getActivity(), dataLst,new IClick(){
            @Override
            public void listViewItemClick(int position, View v) {

                if("Y".equals(dataLst.get(position).getValaddagencysupply())){// 督导新增的供货关系
                    alertShow4(position);
                    //Toast.makeText(getActivity(),dataLst.get(position).getValproname(),Toast.LENGTH_SHORT).show();
                }else{// 处理业代的供货关系
                    alertShow3(position);
                }
            }
        });
        zsChatvieLv.setAdapter(zsChatvieAdapter);
        ViewUtil.setListViewHeight(zsChatvieLv);

        // 瓦解竞品
        mitValcmpotherMTemp = zsChatVieService.findMitValcmpotherMTempById(mitValterMTempKey);
        if (mitValcmpotherMTemp != null) {
            // 是否瓦解竞品  0:未瓦解  1:瓦解
            if (ConstValues.FLAG_1.equals(mitValcmpotherMTemp.getValistruecmpval()) ) {
                zdzs_chatvie_rl_clearvie_con1.setText("是");
            } else {
                zdzs_chatvie_rl_clearvie_con1.setText("否");
            }
        }
        // 设置瓦解竞品稽查
        setWjFalg();

        // 拜访记录
        // zdzs_chatvie_et_visitreport.setText(mitValcmpotherMTemp.getValvisitremark());
    }

    // 设置瓦解竞品稽查
    private void setWjFalg(){
        // 未稽查
        if("N".equals(mitValcmpotherMTemp.getValistrueflag())){// 达成组数正确与否
            zdzs_chatvie_rl_clearvie_statue.setText("错误");
            zdzs_chatvie_rl_clearvie_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_error));
        }else if("Y".equals(mitValcmpotherMTemp.getValistrueflag())){
            zdzs_chatvie_rl_clearvie_statue.setText("正确");
            zdzs_chatvie_rl_clearvie_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_yes));
        }else{
            zdzs_chatvie_rl_clearvie_statue.setText("未稽查");
            zdzs_chatvie_rl_clearvie_statue.setTextColor(getResources().getColor(R.color.gray_color_666666));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zdzs_chatvie_bt_addrelation:// 新增竞品
                Bundle bundle = new Bundle();
                bundle.putSerializable("termId", termStc.getTerminalkey());
                bundle.putSerializable("termname", termStc.getTerminalname());
                bundle.putSerializable("channelId", termStc.getMinorchannel());// 次渠道
                bundle.putSerializable("termStc", termStc);
                bundle.putSerializable("visitKey", visitId);//visitId
                bundle.putSerializable("seeFlag", seeFlag);// 默认0   0:拜访 1:查看

                ZsAddChatVieFragment xtaddchatviefragment = new ZsAddChatVieFragment(handler);
                xtaddchatviefragment.setArguments(bundle);

                ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity) getActivity();
                xtVisitShopActivity.changeXtvisitFragment(xtaddchatviefragment, "zsvisitshopactivity");
                break;
            case R.id.zdzs_chatvie_rl_clearvie:// 督查是否瓦解竞品
                alertShow5(mitValcmpotherMTemp);
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
        SoftReference<ZsChatvieFragment> fragmentRef;

        public MyHandler(ZsChatvieFragment fragment) {
            fragmentRef = new SoftReference<ZsChatvieFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsChatvieFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            ArrayList<KvStc> products = (ArrayList<KvStc>) msg.obj;
            Bundle bundle = msg.getData();
            KvStc agency = (KvStc) bundle.getSerializable("agency");

            // 处理UI 变化
            switch (msg.what) {
                case ZS_ADD_VIE_SUC:// 新增竞品
                    fragment.showAddVieProSuc(products, agency);
                    break;
                case INIT_AMEND:// 竞品处理
                    fragment.showAdapter();
                    break;
                case INIT_WJ_AMEND:// 瓦解竞品处理成功
                    fragment.showWjProduct();
                    break;
            }
        }
    }

    private void showWjProduct() {
        // 设置瓦解竞品稽查
        setWjFalg();
    }

    private void showAdapter() {
        zsChatvieAdapter.notifyDataSetChanged();
        ViewUtil.setListViewHeight(zsChatvieLv);
    }

    /**
     * 新增竞品成功 后的处理 UI
     */
    public void showAddVieProSuc(ArrayList<KvStc> products, KvStc agency) {

        for (KvStc product : products) {

            List<String> proIdLst = new ArrayList<String>();

            // 此处需要一个for
            for (MitValcmpMTemp valsupplyMTemp : dataLst) {
                // 供货关系是否有效 为null,为"", 为"Y" 都是有效
                if (valsupplyMTemp.getValagencysupplyflag() == null || "Y".equals(valsupplyMTemp.getValagencysupplyflag())
                        || "".equals(valsupplyMTemp.getValagencysupplyflag())) {
                    proIdLst.add(valsupplyMTemp.getValcmpid());
                }
            }
            if (proIdLst.contains(product.getKey())) {
                DbtLog.logUtils(TAG, "产品重复提示");
                Toast.makeText(getActivity(), getString(R.string.addrelation_msg_repetitionadd), Toast.LENGTH_LONG).show();
            } else {

                MitValcmpMTemp valsupplyMTemp = new MitValcmpMTemp();
                valsupplyMTemp.setId(FunUtil.getUUID());
                valsupplyMTemp.setValterid(mitValterMTempKey);// 终端追溯主表ID
                valsupplyMTemp.setValaddagencysupply("Y");// 是否新增供货关系
                //valsupplyMTemp.setValagency(agency.getKey());// 经销商;
                //valsupplyMTemp.setValagencyname(agency.getValue());// 经销商名称
                valsupplyMTemp.setValcmpid(product.getKey());// 产品
                valsupplyMTemp.setValcmpname(product.getValue());// 产品名称
                dataLst.add(valsupplyMTemp);

                zsChatvieAdapter.setDelPosition(-1);
                zsChatvieAdapter.notifyDataSetChanged();
                ViewUtil.setListViewHeight(zsChatvieLv);

            }
        }
    }

    /**
     * 处理供货关系对错
     * @param position
     */
    public void alertShow3(final int position) {

        // mAlertViewExt = new AlertView(null, null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_result_chatvie_form, null);

        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");

        final RadioButton right_rb = (RadioButton) extView.findViewById(R.id.right_rb);
        final RadioGroup right_rg = (RadioGroup) extView.findViewById(R.id.right_rg);
        final CheckBox cb1 = (CheckBox) extView.findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox) extView.findViewById(R.id.cb2);
        final CheckBox cb3 = (CheckBox) extView.findViewById(R.id.cb3);

        // 竞品品项有误
        if("Y".equals(mitValcheckterM.getCmperror())){
            cb1.setVisibility(View.VISIBLE);
        }
        // 竞品经销商有误
        if("Y".equals(mitValcheckterM.getCmpagencyerror())){
            cb2.setVisibility(View.VISIBLE);
        }
        // 竞品数据错误
        if("Y".equals(mitValcheckterM.getCmpdataerror())){
            cb3.setVisibility(View.VISIBLE);
        }

        valsupplyMTemp = dataLst.get(position);
        String flag = valsupplyMTemp.getValagencysupplyflag();
        if ("Y".equals(flag)) {
            right_rb.setChecked(true);
        }
        String flag1 = valsupplyMTemp.getValproerror();
        String flag2 = valsupplyMTemp.getValagencyerror();
        String flag3 = valsupplyMTemp.getValdataerror();
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
                if ((!right_rb.isChecked()) && (!cb1.isChecked()) && (!cb2.isChecked()) && (!cb3.isChecked()) ) {
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
                    dialog.dismiss();
                    handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);
                } else {
                    if (cb1.isChecked()) {// 品项有误
                        valsupplyMTemp.setValagencysupplyflag("N");// 供货关系正确与否
                        valsupplyMTemp.setValproerror("Y");// 品项有误
                        valsupplyMTemp.setValagencyerror("N");// 经销商有误
                        valsupplyMTemp.setValdataerror("N");// 数据有误
                        dialog.dismiss();
                        handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);
                    } else {
                        valsupplyMTemp.setValagencysupplyflag("N");// 供货关系正确与否
                        valsupplyMTemp.setValproerror("N");// 品项有误

                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);//
                        bundle.putString("termId", termId);//
                        bundle.putString("mitValterMTempKey", mitValterMTempKey);
                        bundle.putBoolean("valagencyerror", cb2.isChecked());
                        bundle.putBoolean("valdataerror", cb3.isChecked());
                        bundle.putSerializable("dataLst", (Serializable) dataLst);
                        ZsChatVieAmendFragment zsInvocingAmendFragment = new ZsChatVieAmendFragment(handler);
                        zsInvocingAmendFragment.setArguments(bundle);
                        ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                        zsVisitShopActivity.changeXtvisitFragment(zsInvocingAmendFragment, "zschatvieamendfragment");
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


        /*mAlertViewExt.addExtView(extView);
        mAlertViewExt.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();*/
    }


    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");
        // 如果是查看操作，则不做数据校验及数据处理
        if (ConstValues.FLAG_1.equals(seeFlag)) return;

        // 拜访记录
        // mitValcmpotherMTemp.setValvisitremark(zdzs_chatvie_et_visitreport.getText().toString());

        // 保存追溯聊竞品页面数据到临时表
        zsChatVieService.saveZsVie(dataLst, mitValcmpotherMTemp);
    }

    private AlertView mAlertViewExt;//窗口拓展例子

    // 长按删除我品供货关系弹窗
    private void deleteviesupply(int position, final XtChatVieStc xtChatVieStc) {
        String proName = xtChatVieStc.getProName();
        // 普通窗口
        mAlertViewExt = new AlertView("删除竞品: " + proName, null, "取消", new String[]{"确定"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            if (!CheckUtil.isBlankOrNull(xtChatVieStc.getRecordId())) {
                                DbtLog.logUtils(TAG, "删除竞品供货关系：是");
                                XtChatVieService service = new XtChatVieService(getActivity(), null);
                                boolean isFlag = service.deleteSupply(xtChatVieStc.getRecordId(), termId, xtChatVieStc.getProId());
                                if (isFlag) {
                                    // 删除界面listView相应行
                                    dataLst.remove(position);
                                    zsChatvieAdapter.notifyDataSetChanged();
                                    zsChatvieAdapter.setDelPosition(position);
                                    ViewUtil.setListViewHeight(zsChatvieLv);

                                } else {
                                    Toast.makeText(getActivity(), "删除产品失败!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // 删除界面listView相应行
                                dataLst.remove(position);
                                zsChatvieAdapter.notifyDataSetChanged();
                                zsChatvieAdapter.setDelPosition(position);
                                ViewUtil.setListViewHeight(zsChatvieLv);
                            }
                        }
                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "删除竞品供货关系：否");
                    }
                });
        mAlertViewExt.show();
    }

    /**
     * 处理督导新增的供货关系
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
        sureOrFail.add(new KvStc("zhengque","失效这条供货关系","-1"));
        sureOrFail.add(new KvStc("cuowu","录入该条记录数据","-1"));
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
                if(0==position){// 失效
                    dataLst.remove(posi);
                    handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);
                }else{// 跳转数据录入
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", posi);//
                    bundle.putString("termId", termId);//
                    bundle.putString("mitValterMTempKey", mitValterMTempKey);
                    bundle.putSerializable("dataLst", (Serializable) dataLst);
                    ZsChatVieAddDataFragment zsInvocingAddDataFragment = new ZsChatVieAddDataFragment(handler);
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
                            handler.sendEmptyMessage(ZsChatvieFragment.INIT_AMEND);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", posi);//
                            bundle.putString("termId", termId);//
                            bundle.putString("mitValterMTempKey", mitValterMTempKey);
                            bundle.putSerializable("dataLst", (Serializable) dataLst);
                            ZsChatVieAddDataFragment zsInvocingAddDataFragment = new ZsChatVieAddDataFragment(handler);
                            zsInvocingAddDataFragment.setArguments(bundle);
                            ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            zsVisitShopActivity.changeXtvisitFragment(zsInvocingAddDataFragment, "zsamendfragment");
                        }

                    }
                }).setCancelable(true).show();
    }

    /**
     * 处理瓦解竞品
     * 参数1: 标题 ×
     * 参数2: 主体内容 ×
     * 参数3: 取消按钮 ×
     * 参数4: 高亮按钮 数组 √
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (正常取消,确定按钮) √
     * 参数8: 条目点击监听 √
     */
    public void alertShow5(final MitValcmpotherMTemp mitValcmpotherMTemp) {
        /*List<KvStc> sureOrFail = new ArrayList<>();
        sureOrFail.add(new KvStc("zhengque","正确","-1"));
        sureOrFail.add(new KvStc("cuowu","错误(去修正)","-1"));
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
                if(0==position){// 正确
                    mitValcmpotherMTemp.setValistrueflag("Y");
                    handler.sendEmptyMessage(ZsChatvieFragment.INIT_WJ_AMEND);
                }else{// 跳转   错误(去修正)

                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("mitValcmpotherMTemp", mitValcmpotherMTemp);

                    ZsWjVieAmendFragment zsWjVieAmendFragment = new ZsWjVieAmendFragment(handler);
                    zsWjVieAmendFragment.setArguments(bundle1);

                    ZsVisitShopActivity visitShopActivity = (ZsVisitShopActivity) getActivity();
                    visitShopActivity.changeXtvisitFragment(zsWjVieAmendFragment, "zswjvieamendfragment");
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


        new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确

                            // 当稽查错误,又改正确时
                            if("N".equals(mitValcmpotherMTemp.getValistrueflag())){
                                int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
                                count--;
                                PrefUtils.putInt(getActivity(),"valterErrorCount",count);
                            }

                            mitValcmpotherMTemp.setValistrueflag("Y");
                            handler.sendEmptyMessage(ZsChatvieFragment.INIT_WJ_AMEND);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("mitValcmpotherMTemp", mitValcmpotherMTemp);
                            /*bundle1.putSerializable("termname", termStc.getTerminalname());
                            bundle1.putSerializable("channelId", termStc.getMinorchannel());// 次渠道
                            bundle1.putSerializable("termStc", termStc);
                            bundle1.putSerializable("visitKey", visitId);//visitId
                            bundle1.putSerializable("seeFlag", seeFlag);// 默认0   0:拜访 1:查看*/

                            ZsWjVieAmendFragment zsWjVieAmendFragment = new ZsWjVieAmendFragment(handler);
                            zsWjVieAmendFragment.setArguments(bundle1);

                            ZsVisitShopActivity visitShopActivity = (ZsVisitShopActivity) getActivity();
                            visitShopActivity.changeXtvisitFragment(zsWjVieAmendFragment, "zswjvieamendfragment");
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
