package et.tsingtaopad.dd.ddzs.zsterm.filtrate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.DayDetailSelectKeyValueAdapter;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.dropdownmenu.DropBean;
import et.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import et.tsingtaopad.core.view.refresh.UltraRefreshListView;
import et.tsingtaopad.db.table.MitValcheckterM;
import et.tsingtaopad.dd.ddxt.chatvie.addchatvie.XtAddChatVieService;
import et.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import et.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.ZsTermFragment;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.AgencyCmpLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.ProCheckLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.TermCheckStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 筛选条件 界面
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsFiltrateTermFragment extends BaseFragmentSupport implements View.OnClickListener
        , CompoundButton.OnCheckedChangeListener {

    private final String TAG = "ZsFiltrateTermFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    // 主渠道选项
    private CheckBox cb_ka;
    private CheckBox cb_s;
    private CheckBox cb_nl;
    private CheckBox cb_op;
    private CheckBox cb_sc;

    // 所有的销售次渠道
    private com.nex3z.flowlayout.FlowLayout flower_tags1;

    // 指标信息  产品组合是否达标 合作执行是否到位 是否高质量配送
    private CheckBox rb_checkzh;//
    private CheckBox rb_notcheckzh;//
    private CheckBox rb_checkhz;//
    private CheckBox rb_notcheckhz;//
    private CheckBox rb_checkgao;//
    private CheckBox rb_notcheckgao;//

    private ArrayList<String> sellTag = new ArrayList<>();
    private ArrayList<String> puhuoLv = new ArrayList<>();// 铺货状态
    private ArrayList<String> termlv = new ArrayList<>();// 1级终端
    private ArrayList<String> areaLv = new ArrayList<>();// 城区
    private ArrayList<String> zhuSell = new ArrayList<>();// 主渠道集合
    private ArrayList<ProSellStc> checkSelectLst = new ArrayList<>();// 铺货状态选中的产品 一个小集合
    private ArrayList<ProSellStc> priceProLst = new ArrayList<>();// 价格体系要展示的产品集合 (但当小集合有值时,展示小集合)

    private XtTermSelectService xtSelectService;
    private XtAddInvocingService addInvocingService;
    private XtAddChatVieService xtAddChatVieService;

    private List<KvStc> proLst;
    private List<KvStc> agencyLst;
    ;
    private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据

    private RelativeLayout rl_sure;
    private RelativeLayout rl_reset;
    private RadioButton rb_check;
    private RadioButton rb_price;
    private RadioButton rb_cmppro;

    // 基本信息
    private CheckBox rb_mineshop;
    private CheckBox rb_notmineshop;
    private ListView termcheck_pro_lv;
    private TextView proname_lv;// 铺货状态 的产品key
    private TextView cmpproname_lv;// 竞品信息 的产品key

    private LinearLayout prolv_ll;
    private TextView prolv_tv;

    private CheckBox mZdzs_termcheck_minearea;//
    private CheckBox mZdzs_termcheck_cmparea;//
    private CheckBox mZdzs_termcheck_minetreaty;//
    private CheckBox mZdzs_termcheck_cmptreaty;//
    private CheckBox mZdzs_termcheck_termone;//
    private CheckBox mZdzs_termcheck_termtwo;//
    private CheckBox mZdzs_termcheck_termthree;//
    private CheckBox mZdzs_termcheck_termfour;//
    private CheckBox mZdzs_termcheck_termcq;//
    private CheckBox mZdzs_termcheck_termxz;//
    private CheckBox mZdzs_termcheck_termcj;//
    private CheckBox mZdzs_termcheck_termddb;//
    private EditText mZdzs_termcheck_termaddress;//
    private EditText mZdzs_termcheck_termcycle;//


    private LinearLayout mZdzs_termcheck_checkid;
    private CheckBox zdzs_termcheck_black;
    private CheckBox zdzs_termcheck_puhuo;
    private CheckBox zdzs_termcheck_youxiao;
    private CheckBox zdzs_termcheck_xiaoshou;

    TermCheckStc checkStc;

    /*private int startrow = -1;// 开始行
    private int endrow = -1;// 结束行
    private int pagercount = 50;// 每次请求的个数*/

    private String areaKey = "";
    private String gridKey = "";
    private String routeKey = "";

    private PtrClassicFrameLayout mPtrFrame;
    private LinearLayout ll_procheck;//
    private RelativeLayout rl_proselect;//


    // 价格体系
    private LinearLayout zdzs_termcheck_ll_price;
    private RelativeLayout zdzs_termcheck_rl_proprice;
    private TextView zdzs_termcheck_proprice;
    private TextView zdzs_termcheck_pronameprice;
    private TextView zdzs_termcheck_priceget;
    private LinearLayout zdzs_termcheck_ll_proprice;
    private ListView zdzs_termcheck_lv_proprice;

    // 竞品信息
    private LinearLayout zdzs_termcheck_ll_top_cmppro;
    private RelativeLayout zdzs_termcheck_rl_cmppro;
    private TextView zdzs_termcheck_cmpproget06;
    private LinearLayout zdzs_termcheck_ll_cmppro;
    private ListView zdzs_termcheck_lv_cmppro;


    MyHandler handler;

    public static final int DD_TEMPLATE_SUC = 2001;//
    public static final int DD_TEMPLATE_FAIL = 2002;//

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_filtrate, container, false);
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
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);


        // 铺货状态 价格体系 竞品
        rb_check = (RadioButton) view.findViewById(R.id.rb_filtrate_check);//
        rb_price = (RadioButton) view.findViewById(R.id.rb_filtrate_price);//
        rb_cmppro = (RadioButton) view.findViewById(R.id.rb_filtrate_cmppro);//
        rb_check.setOnCheckedChangeListener(this);
        rb_price.setOnCheckedChangeListener(this);
        rb_cmppro.setOnCheckedChangeListener(this);


        // 铺货状态
        ll_procheck = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_procheck);
        // 空白,铺货,有效铺货,有效销售
        mZdzs_termcheck_checkid = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_checkid);
        zdzs_termcheck_black = (CheckBox) view.findViewById(R.id.zdzs_filtrate_black);
        zdzs_termcheck_puhuo = (CheckBox) view.findViewById(R.id.zdzs_filtrate_puhuo);
        zdzs_termcheck_youxiao = (CheckBox) view.findViewById(R.id.zdzs_filtrate_youxiao);
        zdzs_termcheck_xiaoshou = (CheckBox) view.findViewById(R.id.zdzs_filtrate_xiaoshou);
        zdzs_termcheck_black.setOnCheckedChangeListener(this);
        zdzs_termcheck_puhuo.setOnCheckedChangeListener(this);
        zdzs_termcheck_youxiao.setOnCheckedChangeListener(this);
        zdzs_termcheck_xiaoshou.setOnCheckedChangeListener(this);
        // 铺货状态 选择产品一行
        rl_proselect = (RelativeLayout) view.findViewById(R.id.zdzs_filtrate_rl_proselect);
        rl_proselect.setOnClickListener(this);
        proname_lv = (TextView) view.findViewById(R.id.zdzs_filtrate_proname);// 产品名称
        proname_lv.setTag("");
        prolv_tv = (TextView) view.findViewById(R.id.zdzs_filtrate_get06);// 收起
        prolv_tv.setOnClickListener(this);
        // 底端产品列表 铺货状态 产品列表
        prolv_ll = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_prolv);
        termcheck_pro_lv = (ListView) view.findViewById(R.id.zdzs_filtrate_pro);

        // 价格体系
        zdzs_termcheck_ll_price = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_price);
        zdzs_termcheck_rl_proprice = (RelativeLayout) view.findViewById(R.id.zdzs_filtrate_rl_proprice);
        zdzs_termcheck_proprice = (TextView) view.findViewById(R.id.zdzs_filtrate_proprice);
        zdzs_termcheck_pronameprice = (TextView) view.findViewById(R.id.zdzs_filtrate_pronameprice);
        zdzs_termcheck_priceget = (TextView) view.findViewById(R.id.zdzs_filtrate_priceget);
        zdzs_termcheck_ll_proprice = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_proprice);
        zdzs_termcheck_lv_proprice = (ListView) view.findViewById(R.id.zdzs_filtrate_lv_proprice);
        zdzs_termcheck_rl_proprice.setOnClickListener(this);
        zdzs_termcheck_priceget.setOnClickListener(this);


        // 竞品信息
        zdzs_termcheck_ll_top_cmppro = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_top_cmppro);
        zdzs_termcheck_rl_cmppro = (RelativeLayout) view.findViewById(R.id.zdzs_filtrate_rl_cmppro);
        cmpproname_lv = (TextView) view.findViewById(R.id.zdzs_filtrate_tv_cmppro);// c产品名称
        cmpproname_lv.setTag("");
        zdzs_termcheck_cmpproget06 = (TextView) view.findViewById(R.id.zdzs_filtrate_cmpproget06);
        zdzs_termcheck_ll_cmppro = (LinearLayout) view.findViewById(R.id.zdzs_filtrate_ll_cmppro);
        zdzs_termcheck_lv_cmppro = (ListView) view.findViewById(R.id.zdzs_filtrate_lv_cmppro);
        zdzs_termcheck_rl_cmppro.setOnClickListener(this);
        zdzs_termcheck_cmpproget06.setOnClickListener(this);


        // 基本信息
        rb_mineshop = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_mineshop);
        rb_notmineshop = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_notmineshop);
        mZdzs_termcheck_minearea = (CheckBox) view.findViewById(R.id.zdzs_filtrate_minearea);
        mZdzs_termcheck_cmparea = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cmparea);
        mZdzs_termcheck_minetreaty = (CheckBox) view.findViewById(R.id.zdzs_filtrate_minetreaty);
        mZdzs_termcheck_cmptreaty = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cmptreaty);
        mZdzs_termcheck_termone = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termone);
        mZdzs_termcheck_termtwo = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termtwo);
        mZdzs_termcheck_termthree = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termthree);
        mZdzs_termcheck_termfour = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termfour);
        mZdzs_termcheck_termcq = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termcq);
        mZdzs_termcheck_termxz = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termxz);
        mZdzs_termcheck_termcj = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termcj);
        mZdzs_termcheck_termddb = (CheckBox) view.findViewById(R.id.zdzs_filtrate_termddb);
        mZdzs_termcheck_termaddress = (EditText) view.findViewById(R.id.zdzs_filtrate_termaddress);
        mZdzs_termcheck_termcycle = (EditText) view.findViewById(R.id.zdzs_filtrate_termcycle);
        rb_mineshop.setOnCheckedChangeListener(this);
        rb_notmineshop.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termone.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termtwo.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termthree.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termfour.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termcq.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termxz.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termcj.setOnCheckedChangeListener(this);
        mZdzs_termcheck_termddb.setOnCheckedChangeListener(this);

        // 主渠道选项
        cb_ka = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cb_ka1);
        cb_s = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cb_s1);
        cb_nl = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cb_nl1);
        cb_op = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cb_op1);
        cb_sc = (CheckBox) view.findViewById(R.id.zdzs_filtrate_cb_sc1);
        // 所有的销售次渠道
        flower_tags1 = (com.nex3z.flowlayout.FlowLayout) view.findViewById(R.id.flower_tags1);
        cb_ka.setOnCheckedChangeListener(this);
        cb_s.setOnCheckedChangeListener(this);
        cb_nl.setOnCheckedChangeListener(this);
        cb_op.setOnCheckedChangeListener(this);
        cb_sc.setOnCheckedChangeListener(this);

        // 指标信息  产品组合是否达标 合作执行是否到位 是否高质量配送
        rb_checkzh = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_checkzh);
        rb_notcheckzh = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_notcheckzh);
        rb_checkhz = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_checkhz);
        rb_notcheckhz = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_notcheckhz);
        rb_checkgao = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_checkgao);
        rb_notcheckgao = (CheckBox) view.findViewById(R.id.zdzs_filtrate_rb_notcheckgao);
        rb_checkzh.setOnCheckedChangeListener(this);
        rb_notcheckzh.setOnCheckedChangeListener(this);
        rb_checkhz.setOnCheckedChangeListener(this);
        rb_notcheckhz.setOnCheckedChangeListener(this);
        rb_checkgao.setOnCheckedChangeListener(this);
        rb_notcheckgao.setOnCheckedChangeListener(this);

        // 确定按钮
        rl_sure = (RelativeLayout) view.findViewById(R.id.zdzs_filtrate_rl_sure);
        rl_reset = (RelativeLayout) view.findViewById(R.id.zdzs_filtrate_rl_reset);
        rl_sure.setOnClickListener(this);
        rl_reset.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();
    }

    // 初始化数据
    private void initData() {
        titleTv.setText("选择核查指标");
        confirmTv.setText("确定");

        xtSelectService = new XtTermSelectService(getActivity());
        addInvocingService = new XtAddInvocingService(getActivity(), null);
        xtAddChatVieService = new XtAddChatVieService(getActivity(), null);

        checkStc = new TermCheckStc();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:

                xtSelectService.deleteMst_terminal_m();
                getTermList();
                break;

            case R.id.zdzs_filtrate_get06:// 铺货状态 产品信息 收起
            case R.id.zdzs_filtrate_rl_proselect:// 铺货状态  产品信息 收起
                String pro = prolv_tv.getText().toString();
                if ("收起".equals(pro)) {
                    prolv_tv.setText("打开");
                    prolv_ll.setVisibility(View.GONE);
                } else {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zdzs_filtrate_priceget:// 价格体系 产品信息 收起
            case R.id.zdzs_filtrate_rl_proprice:// 价格体系  产品信息 收起
                String priceget = zdzs_termcheck_priceget.getText().toString();
                if ("收起".equals(priceget)) {
                    zdzs_termcheck_priceget.setText("打开");
                    zdzs_termcheck_ll_proprice.setVisibility(View.GONE);
                } else {
                    zdzs_termcheck_priceget.setText("收起");
                    zdzs_termcheck_ll_proprice.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zdzs_filtrate_cmpproget06:// 竞品信息 产品信息 收起
            case R.id.zdzs_filtrate_rl_cmppro:// 竞品信息  产品信息 收起
                String cmpproget06 = zdzs_termcheck_cmpproget06.getText().toString();
                if ("收起".equals(cmpproget06)) {
                    zdzs_termcheck_cmpproget06.setText("打开");
                    zdzs_termcheck_ll_cmppro.setVisibility(View.GONE);
                } else {
                    zdzs_termcheck_cmpproget06.setText("收起");
                    zdzs_termcheck_ll_cmppro.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    AgencyCmpLvAdapter cmpAdapter;
    ProCheckLvAdapter lvAdapter;

    // 选中事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            // 返回
            case R.id.zdzs_filtrate_cb_ka1:// 渠道ka
                if (isChecked) {
                    zhuSell.add("39DD41A399318C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399318C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399228C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399238C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399248C68E05010ACE0016FCD");
                }
                setCiSell();

                break;
            case R.id.zdzs_filtrate_cb_s1:// 渠道s
                if (isChecked) {
                    zhuSell.add("39DD41A399338C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399338C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3991D8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3991E8C68E05010ACE0016FCD");
                }

                setCiSell();
                break;
            case R.id.zdzs_filtrate_cb_op1:// 渠道op
                if (isChecked) {
                    zhuSell.add("39DD41A399348C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399348C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992D8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992C8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992B8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992A8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399298C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399288C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399278C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399268C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399258C68E05010ACE0016FCD");

                }

                setCiSell();
                break;
            case R.id.zdzs_filtrate_cb_nl1:// 渠道nl
                if (isChecked) {
                    zhuSell.add("39DD41A399328C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399328C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992E8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3992F8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399308C68E05010ACE0016FCD");
                }

                setCiSell();
                break;
            case R.id.zdzs_filtrate_cb_sc1:// 渠道sc
                if (isChecked) {
                    zhuSell.add("39DD41A399358C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399358C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A399208C68E05010ACE0016FCD");
                    sellTag.remove("4CCEA64B239E6F05E05010ACE0010B86");
                    sellTag.remove("4CCEA64B239F6F05E05010ACE0010B86");
                }
                setCiSell();

                break;
            case R.id.zdzs_filtrate_black:// 空白
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("301");
                } else {
                    // puhuoLv.remove("301");
                    Iterator<String> it = puhuoLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("301")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_puhuo:// 铺货
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("302");
                } else {
                    // puhuoLv.remove("302");
                    Iterator<String> it = puhuoLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("302")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_youxiao:// 有效铺货
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("303");
                } else {
                    Iterator<String> it = puhuoLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("303")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_xiaoshou:// 有效销售
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("304");
                } else {
                    // puhuoLv.remove("304");
                    Iterator<String> it = puhuoLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("304")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termone:// 一级终端//
                if (isChecked) {
                    termlv.add("81B1A7272795498FBBE8EBDFB065F9FE");
                } else {
                    Iterator<String> it = termlv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("81B1A7272795498FBBE8EBDFB065F9FE")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termtwo:// 二级终端//
                if (isChecked) {
                    termlv.add("20E51C0398E34AC8A09375470B5D9DFE");
                } else {
                    Iterator<String> it = termlv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("20E51C0398E34AC8A09375470B5D9DFE")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termthree:// 三级终端//
                if (isChecked) {
                    termlv.add("3B86CC33732C454291509E1745FF315E");
                } else {
                    Iterator<String> it = termlv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("3B86CC33732C454291509E1745FF315E")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termfour:// 四级终端//
                if (isChecked) {
                    termlv.add("4D49F29894CA4C71B4DA56629CEED17F");
                } else {
                    //termlv.remove("4");
                    Iterator<String> it = termlv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("4D49F29894CA4C71B4DA56629CEED17F")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termcq:// 城区//
                if (isChecked) {
                    areaLv.add("85C3678B44FA42B794F8BABD2846E6D1");
                } else {
                    //areaLv.remove("85C3678B44FA42B794F8BABD2846E6D1");
                    Iterator<String> it = areaLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("85C3678B44FA42B794F8BABD2846E6D1")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termxz:// 乡镇//
                if (isChecked) {
                    areaLv.add("4C37979BC4424890BEA016EE7DED02CE");
                } else {
                    // areaLv.remove("4C37979BC4424890BEA016EE7DED02CE");
                    Iterator<String> it = areaLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("4C37979BC4424890BEA016EE7DED02CE")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termcj:// 村级//
                if (isChecked) {
                    areaLv.add("5FA07909011F447AB3C142C52CD54DD4");
                } else {
                    // areaLv.remove("5FA07909011F447AB3C142C52CD54DD4");
                    Iterator<String> it = areaLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("5FA07909011F447AB3C142C52CD54DD4")) {
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_filtrate_termddb:// 大店部//
                if (isChecked) {
                    areaLv.add("29BC5F0C31A4D59DE050A8C0D6006510");
                } else {
                    // areaLv.remove("29BC5F0C31A4D59DE050A8C0D6006510");
                    Iterator<String> it = areaLv.iterator();
                    while (it.hasNext()) {
                        String x = it.next();
                        if (x.equals("29BC5F0C31A4D59DE050A8C0D6006510")) {
                            it.remove();
                        }
                    }
                }
                break;

            case R.id.zdzs_filtrate_rb_mineshop:// 我品店招//
                if (isChecked) {
                    rb_mineshop.setChecked(true);
                    rb_notmineshop.setChecked(false);
                } else {
                    rb_mineshop.setChecked(false);

                }
                break;
            case R.id.zdzs_filtrate_rb_notmineshop:// 非我品店招//
                if (isChecked) {
                    rb_notmineshop.setChecked(true);
                    rb_mineshop.setChecked(false);
                } else {
                    rb_notmineshop.setChecked(false);
                }
                break;
            case R.id.zdzs_filtrate_rb_checkzh:// 产品组合达标//
                if (isChecked) {
                    rb_checkzh.setChecked(true);
                    rb_notcheckzh.setChecked(false);
                } else {
                    rb_checkzh.setChecked(false);

                }
                break;
            case R.id.zdzs_filtrate_rb_notcheckzh:// 产品组合未达标//
                if (isChecked) {
                    rb_notcheckzh.setChecked(true);
                    rb_checkzh.setChecked(false);
                } else {
                    rb_notcheckzh.setChecked(false);
                }
                break;
            case R.id.zdzs_filtrate_rb_checkhz:// 合作执行到位//
                if (isChecked) {
                    rb_checkhz.setChecked(true);
                    rb_notcheckhz.setChecked(false);
                } else {
                    rb_checkhz.setChecked(false);
                }
                break;
            case R.id.zdzs_filtrate_rb_notcheckhz:// 合作执行未到位//
                if (isChecked) {
                    rb_notcheckhz.setChecked(true);
                    rb_checkhz.setChecked(false);
                } else {
                    rb_notcheckhz.setChecked(false);
                }
                break;
            case R.id.zdzs_filtrate_rb_checkgao:// 高质量配送//
                if (isChecked) {
                    rb_checkgao.setChecked(true);
                    rb_notcheckgao.setChecked(false);
                } else {
                    rb_checkgao.setChecked(false);
                }
                break;
            case R.id.zdzs_filtrate_rb_notcheckgao:// 非高质量配送//
                if (isChecked) {
                    rb_notcheckgao.setChecked(true);
                    rb_checkgao.setChecked(false);
                } else {
                    rb_notcheckgao.setChecked(false);
                }
                break;


            case R.id.rb_filtrate_check:// 铺货状态
                if (isChecked) {
                    ll_procheck.setVisibility(View.VISIBLE);
                    zdzs_termcheck_ll_price.setVisibility(View.GONE);
                    zdzs_termcheck_ll_top_cmppro.setVisibility(View.GONE);

                    mZdzs_termcheck_checkid.setVisibility(View.VISIBLE);// 空白,铺货,有效铺货,有效销售 4个CheckBox

                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);// 铺货状态 用于包含ListView的LL
                    rl_proselect.setVisibility(View.VISIBLE);// 铺货状态  选择产品一行

                    proLst = addInvocingService.getProList();

                    // 获取已选中的集合
                    List<String> selectedId = new ArrayList<String>();
                    String selectpro = (String) proname_lv.getTag();// 已经选中的产品key
                    if (!TextUtils.isEmpty(selectpro)) {
                        selectedId = Arrays.asList(selectpro.split(","));
                    }

                    // 标记选中状态
                    for (KvStc kvstc : proLst) {
                        for (String itemselect : selectedId) {
                            if (kvstc.getKey().equals(itemselect)) {
                                kvstc.setIsDefault("1");// 0:没选中 1已选中
                            }
                        }
                    }

                    final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(getActivity(), proLst,
                            new String[]{"key", "value"}, null);
                    termcheck_pro_lv.setAdapter(sadapter);
                    ViewUtil.setListViewHeight(termcheck_pro_lv);


                    termcheck_pro_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int posi, long arg3) {
                            CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                            TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                            itemCB.toggle();//点击整行可以显示效果

                            String checkkey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                            String checkname = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                            if (itemCB.isChecked()) {
                                proname_lv.setTag(((String) proname_lv.getTag()) + checkkey);
                                ((KvStc) proLst.get(posi)).setIsDefault("1");
                                KvStc stc = (KvStc) proLst.get(posi);
                                ProSellStc proSellStc = new ProSellStc();
                                proSellStc.setKey(stc.getKey());
                                proSellStc.setValue(stc.getValue());
                                checkSelectLst.add(proSellStc);
                            } else {
                                // checkSelectLst.remove((KvStc)proLst.get(posi));;
                                proname_lv.setTag(((String) proname_lv.getTag()).replace(checkkey, ""));
                                ((KvStc) proLst.get(posi)).setIsDefault("0");
                                /*for (ProSellStc kvStc : checkSelectLst) {
                                    if (itemTv.getHint().equals(kvStc.getKey())) {
                                        checkSelectLst.remove(kvStc);
                                        break;
                                    }
                                }*/
                                Iterator<ProSellStc> it = checkSelectLst.iterator();
                                while (it.hasNext()) {
                                    ProSellStc x = it.next();
                                    if (itemTv.getHint().equals(x.getKey())) {
                                        it.remove();
                                    }
                                }
                            }
                            sadapter.notifyDataSetChanged();
                        }
                    });

                }

                break;
            case R.id.rb_filtrate_price:// 价格体系
                if (isChecked) {
                    ll_procheck.setVisibility(View.GONE);
                    zdzs_termcheck_ll_price.setVisibility(View.VISIBLE);
                    zdzs_termcheck_ll_top_cmppro.setVisibility(View.GONE);

                    zdzs_termcheck_priceget.setText("收起");
                    zdzs_termcheck_rl_proprice.setVisibility(View.VISIBLE);// 竞品信息  选择产品一行
                    zdzs_termcheck_ll_proprice.setVisibility(View.VISIBLE);// 价格体系 包含ListView的LL

                    if (checkSelectLst.size() > 0) {// 铺货状态 勾选了产品  用小集合
                        lvAdapter = new ProCheckLvAdapter(getActivity(), checkSelectLst);// 已选中的产品
                    } else {// 铺货状态 未勾选了产品  用价格体系的集合
                        if (priceProLst.size() > 0) {
                            lvAdapter = new ProCheckLvAdapter(getActivity(), priceProLst);// 全部产品
                        } else {
                            List<ProSellStc> allproLst = addInvocingService.getAllproLst();
                            priceProLst.addAll(allproLst);
                            lvAdapter = new ProCheckLvAdapter(getActivity(), priceProLst);// 全部产品
                        }
                    }


                    zdzs_termcheck_lv_proprice.setAdapter(lvAdapter);
                    ViewUtil.setListViewHeight(zdzs_termcheck_lv_proprice);
                }

                break;
            case R.id.rb_filtrate_cmppro:// 竞品信息
                if (isChecked) {
                    ll_procheck.setVisibility(View.GONE);
                    zdzs_termcheck_ll_price.setVisibility(View.GONE);
                    zdzs_termcheck_ll_top_cmppro.setVisibility(View.VISIBLE);

                    zdzs_termcheck_cmpproget06.setText("收起");
                    zdzs_termcheck_ll_cmppro.setVisibility(View.VISIBLE);// 竞品信息 用于包含ListView的LL
                    zdzs_termcheck_rl_cmppro.setVisibility(View.VISIBLE);// 竞品信息  选择产品一行

                    agencyLst = xtAddChatVieService.getAgencyVie();
                    cmpAdapter = new AgencyCmpLvAdapter(getActivity(),
                            agencyLst,
                            zdzs_termcheck_lv_cmppro,
                            zdzs_termcheck_cmpproget06,
                            zdzs_termcheck_ll_cmppro,
                            cmpproname_lv);
                    zdzs_termcheck_lv_cmppro.setAdapter(cmpAdapter);
                    ViewUtil.setListViewHeight(zdzs_termcheck_lv_cmppro);

                }
                break;

            default:
                break;
        }
    }

    // 销售次渠道
    private void setCiSell() {

        /*flower_tags.removeAllViews();
        flower_tags.removeAllViewsInLayout();
        flower_tags.destroyDrawingCache();*/
        flower_tags1.removeAllViews();

        StringBuffer buffer = new StringBuffer();
        for (String key : zhuSell) {
            buffer.append("'");
            buffer.append(key);
            buffer.append("'");
            buffer.append(",");
        }

        String lson = buffer.toString().trim();
        if (lson.length() > 4) {
            if (lson.endsWith(",")) {
                lson = lson.substring(0, lson.length() - 1);
            }
        }
        lson = "(" + lson + ")";


        // 销售渠道
        List<KvStc> dataDicLst = xtSelectService.initSecondSellLos(lson);
        for (KvStc kvStc : dataDicLst) {
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(kvStc.getValue());
            checkBox.setTag(kvStc.getKey());
            checkBox.setButtonDrawable(new ColorDrawable());
            checkBox.setBackgroundResource(R.drawable.bg_shape_green);
            checkBox.setTextColor(getResources().getColorStateList(R.color.selector_text_color));
            checkBox.setPadding(18, 10, 18, 10);
            checkBox.setChecked(false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            checkBox.setLayoutParams(params);

            for (String sellkey : sellTag) {
                if (kvStc.getKey().equals(sellkey)) {
                    checkBox.setChecked(true);
                }
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        sellTag.add((String) buttonView.getTag());
                        //Toast.makeText(getActivity(),"个数"+sellTag.size()+"添加"+(String)buttonView.getTag(),Toast.LENGTH_SHORT).show();
                    } else {
                        sellTag.remove((String) buttonView.getTag());
                        //Toast.makeText(getActivity(),"个数"+sellTag.size()+"删除"+(String)buttonView.getTag(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // flower_tags.addView(checkBox);
            flower_tags1.addView(checkBox);
        }
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsFiltrateTermFragment> fragmentRef;

        public MyHandler(ZsFiltrateTermFragment fragment) {
            fragmentRef = new SoftReference<ZsFiltrateTermFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsFiltrateTermFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_TEMPLATE_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_TEMPLATE_FAIL: //
                    //fragment.showAdapter();
                    break;
            }
        }
    }


    // 发起请求
    private void getTermList() {

        if (rb_mineshop.isChecked()) {// 我品店招
            checkStc.setMineshop("1");
        } else if (rb_notmineshop.isChecked()) {
            checkStc.setMineshop("0");
        }
        if (mZdzs_termcheck_minearea.isChecked()) {// 我品销售范围
            checkStc.setMinearea("1");
        } else {
            checkStc.setMinearea("");
        }
        if (mZdzs_termcheck_cmparea.isChecked()) {// 竞品销售范围
            checkStc.setCmparea("1");
        } else {
            checkStc.setCmparea("");
        }
        if (mZdzs_termcheck_minetreaty.isChecked()) {// 我品合作
            checkStc.setMinetreaty("1");
        } else {
            checkStc.setMinetreaty("");
        }
        if (mZdzs_termcheck_cmptreaty.isChecked()) {// 竞品合作
            checkStc.setCmptreaty("1");
        } else {
            checkStc.setCmptreaty("");
        }


        if (rb_checkzh.isChecked()) {// 产品组合
            checkStc.setCheckzh("Y");// 达成
        } else if (rb_notcheckzh.isChecked()) {
            checkStc.setCheckzh("N");// // 合作未达成
        }
        if (rb_checkhz.isChecked()) {//
            checkStc.setCheckhz("9019cf03-4572-4559-9971-48a27a611c3d");// 合作执行到位
        } else if (rb_notcheckhz.isChecked()) {
            checkStc.setCheckhz("8d36d1e5-c776-452e-8893-589ad786d71d");// 合作执行不到位
        }
        if (rb_checkgao.isChecked()) {// 高质量
            checkStc.setCheckgao("460647a9-283a-44ea-b11f-42efe1fd62e4");// 是高质量配送
        } else if (rb_notcheckgao.isChecked()) {
            checkStc.setCheckgao("bf600cfe-f70d-4170-857d-65dd59740d57");// 非高质量配送
        }

        // 地址
        checkStc.setTermaddress(mZdzs_termcheck_termaddress.getText().toString());
        // 拜访周期
        checkStc.setTermcycle(mZdzs_termcheck_termcycle.getText().toString());


        checkStc.setSell(sellTag);// 销售次渠道
        checkStc.setPuhuolst(puhuoLv);// 铺货,空白,有效铺货,有效销售
        checkStc.setTermlvlst(termlv);//  终端等级
        checkStc.setArealst(areaLv);// 区域类型学

        // 铺货状态 产品key
        String prokey = (String) proname_lv.getTag();
        if (prokey.endsWith(",")) {
            checkStc.setProkey(prokey.substring(0, prokey.length() - 1));
        }

        // 竞品信息 产品key
        String cmpprokey = (String) cmpproname_lv.getTag();
        if (cmpprokey.endsWith(",")) {
            checkStc.setCmpprokey(cmpprokey.substring(0, cmpprokey.length() - 1));
        }

        // 价格体系
        ArrayList<ProSellStc> proSellPrice = new ArrayList<>();
        if (checkSelectLst.size() > 0) {// 铺货状态 勾选了产品  用小集合
            for (ProSellStc proSellStc : checkSelectLst) {
                if ((FunUtil.isBlankOrNullTo(proSellStc.getQudaolow(), "").length() > 0
                        && FunUtil.isBlankOrNullTo(proSellStc.getQudaonum(), "").length() > 0)
                        || (FunUtil.isBlankOrNullTo(proSellStc.getLingshoulow(), "").length() > 0
                        && FunUtil.isBlankOrNullTo(proSellStc.getLingshounum(), "").length() > 0)) {
                    proSellPrice.add(proSellStc);
                }
            }
        } else {// 铺货状态 未勾选了产品  用价格体系的集合
            for (ProSellStc proSellStc : priceProLst) {
                if ((FunUtil.isBlankOrNullTo(proSellStc.getQudaolow(), "").length() > 0
                        && FunUtil.isBlankOrNullTo(proSellStc.getQudaonum(), "").length() > 0)
                        || (FunUtil.isBlankOrNullTo(proSellStc.getLingshoulow(), "").length() > 0
                        && FunUtil.isBlankOrNullTo(proSellStc.getLingshounum(), "").length() > 0)) {
                    proSellPrice.add(proSellStc);
                }
            }
        }
        checkStc.setPricelst(proSellPrice);

        checkStc.setBigid(PrefUtils.getString(getActivity(), "departmentid", ""));// 大区id
        checkStc.setSecid(areaKey);// 二级区域id
        checkStc.setGridid(gridKey);// gridid
        checkStc.setRouteid(routeKey);// 路线id

        supportFragmentManager.popBackStack();

        Bundle bundle = new Bundle();
        bundle.putSerializable("checkStc", checkStc);
        ZsTermFragment termGetFragment = new ZsTermFragment();
        termGetFragment.setArguments(bundle);
        changeHomeFragment(termGetFragment, "zstermfragment");// 专项追溯
    }
}
