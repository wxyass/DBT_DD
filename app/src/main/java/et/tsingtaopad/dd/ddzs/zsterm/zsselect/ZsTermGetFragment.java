package et.tsingtaopad.dd.ddzs.zsterm.zsselect;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
import et.tsingtaopad.business.visit.bean.AreaGridRoute;
import et.tsingtaopad.core.net.HttpUrl;
import et.tsingtaopad.core.net.RestClient;
import et.tsingtaopad.core.net.callback.IError;
import et.tsingtaopad.core.net.callback.IFailure;
import et.tsingtaopad.core.net.callback.ISuccess;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.core.view.dropdownmenu.DropBean;
import et.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import et.tsingtaopad.core.view.refresh.UltraRefreshListView;
import et.tsingtaopad.core.view.refresh.UltraRefreshListener;
import et.tsingtaopad.db.table.MitValcheckterM;
import et.tsingtaopad.db.table.MitValterM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.db.table.MstTerminalinfoMDown;
import et.tsingtaopad.db.table.MstVisitM;
import et.tsingtaopad.dd.ddxt.chatvie.addchatvie.XtAddChatVieService;
import et.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import et.tsingtaopad.dd.ddxt.term.select.IXtTermSelectClick;
import et.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import et.tsingtaopad.dd.ddxt.term.select.adapter.XtTermSelectAdapter;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import et.tsingtaopad.dd.ddzs.zsterm.zscart.ZsTermCartFragment;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.AgencyCmpLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.AgengcyLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.ProCheckLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.ProLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.TermCheckStc;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.home.app.MyApplication;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.util.requestHeadUtil;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 督导筛选终端
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsTermGetFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener
        , CompoundButton.OnCheckedChangeListener {

    private final String TAG = "ZsTermSelectFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private TextView checkTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private TextView termcheck_num;
    private LinearLayout ll_termcheck_num;
    private LinearLayout prolv_ll;
    private TextView prolv_tv;
    private CheckBox cb_ka;
    private CheckBox cb_s;
    private CheckBox cb_nl;
    private CheckBox cb_op;
    private CheckBox cb_sc;

    private DropdownButton areaBtn;
    private DropdownButton gridBtn;
    private DropdownButton routeBtn;
    private RelativeLayout termcheck_lou;
    private List<DropBean> areaList;
    private List<DropBean> gridList;
    private List<DropBean> routeList;
    private List<XtTermSelectMStc> termList;


    private LinearLayout termRouteLl;
    private UltraRefreshListView termRouteLv;
    private EditText searchEt;
    private Button searchBtn;
    private Button addAllTermBtn;
    private Button removeAllTermBtn;
    private DrawerLayout mDrawerLayout;

    private XtTermSelectMStc xtTermSelectMStc;
    private List<MitValcheckterM> mitValcheckterMs;
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
    // MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(xtselect.getTerminalkey());
    //private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据
    private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据

    private int TOFRAGMENT = 1;
    private int TOACTIVITY = 2;


    private AlertView mAlertViewExt;//窗口拓展

    // private TagLayout flower_tags;
    private com.nex3z.flowlayout.FlowLayout flower_tags1;
    private RelativeLayout rl_sure;
    private RelativeLayout rl_reset;
    private RadioButton rb_check;
    private RadioButton rb_price;
    private RadioButton rb_cmppro;
    private CheckBox rb_mineshop;
    private CheckBox rb_notmineshop;
    private ListView termcheck_pro_lv;
    private TextView proname_lv;// 铺货状态 的产品key
    private TextView cmpproname_lv;// 竞品信息 的产品key


    // private CheckBox mZdzs_termcheck_mineshop;//
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

    private EditText mZdzs_termcheck_hlow;//
    private EditText mZdzs_termcheck_hnum;//
    private EditText mZdzs_termcheck_zlow;//
    private EditText mZdzs_termcheck_znum;//
    private EditText mZdzs_termcheck_plow;//
    private EditText mZdzs_termcheck_pnum;//
    private EditText mZdzs_termcheck_llow;//
    private EditText mZdzs_termcheck_lnum;//

    /*private CheckBox mZdzs_termcheck_checkzh;//
    private CheckBox mZdzs_termcheck_checkhz;//
    private CheckBox mZdzs_termcheck_checkgao;//*/
    private EditText mZdzs_termcheck_zyllow;//
    private EditText mZdzs_termcheck_zylnum;//

    private EditText mZdzs_termcheck_qdjlow;//
    private EditText mZdzs_termcheck_qdjnum;//
    private EditText mZdzs_termcheck_lsjlow;//
    private EditText mZdzs_termcheck_lsjnum;//
    private LinearLayout ll_proprice1;//
    private LinearLayout ll_proprice2;//

    private LinearLayout mZdzs_termcheck_checkid;
    private CheckBox zdzs_termcheck_black;
    private CheckBox zdzs_termcheck_puhuo;
    private CheckBox zdzs_termcheck_youxiao;
    private CheckBox zdzs_termcheck_xiaoshou;

    TermCheckStc checkStc;

    private int startrow = -1;// 开始行
    private int endrow = -1;// 结束行
    private int pagercount = 50;// 每次请求的个数

    private String areaKey = "";
    private String gridKey = "";
    private String routeKey = "";

    private PtrClassicFrameLayout mPtrFrame;
    private LinearLayout ll_procheck;//
    private RelativeLayout rl_proselect;//

    private CheckBox rb_checkzh;//
    private CheckBox rb_notcheckzh;//
    private CheckBox rb_checkhz;//
    private CheckBox rb_notcheckhz;//
    private CheckBox rb_checkgao;//
    private CheckBox rb_notcheckgao;//

    private LinearLayout zdzs_termcheck_ll_price;
    private RelativeLayout zdzs_termcheck_rl_proprice;
    private TextView zdzs_termcheck_proprice;
    private TextView zdzs_termcheck_pronameprice;
    private TextView zdzs_termcheck_priceget;
    private LinearLayout zdzs_termcheck_ll_proprice;
    private ListView zdzs_termcheck_lv_proprice;

    private LinearLayout zdzs_termcheck_ll_top_cmppro;
    private RelativeLayout zdzs_termcheck_rl_cmppro;
    // private TextView zdzs_termcheck_tv_cmppro;
    private TextView zdzs_termcheck_cmpproget06;
    private LinearLayout zdzs_termcheck_ll_cmppro;
    private ListView zdzs_termcheck_lv_cmppro;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_termget, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        checkTv = (TextView) view.findViewById(R.id.top_navigation_tv_check);// 模板
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        checkTv.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        checkTv.setOnClickListener(this);

        // 区域,定格,路线
        areaBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_area);
        gridBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_grid);
        routeBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_route);
        // 筛选按钮
        termcheck_lou = (RelativeLayout) view.findViewById(R.id.xtbf_termcheck_lou);
        // 搜索 放大镜按钮
         searchEt = (EditText) view.findViewById(R.id.xtbf_termcheck_et_search);
        searchBtn = (Button) view.findViewById(R.id.xtbf_termcheck_bt_search);
        // 全部添加
        addAllTermBtn = (Button) view.findViewById(R.id.xtbf_termcheck_bt_add);
        // 全部删除
        removeAllTermBtn = (Button) view.findViewById(R.id.xtbf_termcheck_bt_remove);
        // 终端列表
        termRouteLl = (LinearLayout) view.findViewById(R.id.xtbf_termcheck_ll_lv);
        termRouteLv = (UltraRefreshListView) view.findViewById(R.id.xtbf_termcheck_lv);
        addAllTermBtn.setOnClickListener(this);
        removeAllTermBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        termcheck_lou.setOnClickListener(this);

        // 上拉加载
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawerlayout);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr);
        mPtrFrame.setPtrHandler(termRouteLv);


        // 铺货状态 价格体系 竞品
        rb_check = (RadioButton) view.findViewById(R.id.rb_check);//
        rb_price = (RadioButton) view.findViewById(R.id.rb_price);//
        rb_cmppro = (RadioButton) view.findViewById(R.id.rb_cmppro);//
        rb_check.setOnCheckedChangeListener(this);
        rb_price.setOnCheckedChangeListener(this);
        rb_cmppro.setOnCheckedChangeListener(this);


        // 铺货状态
        ll_procheck = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_procheck);
        // 空白,铺货,有效铺货,有效销售
        mZdzs_termcheck_checkid = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_checkid);
        zdzs_termcheck_black = (CheckBox) view.findViewById(R.id.zdzs_termcheck_black);
        zdzs_termcheck_puhuo = (CheckBox) view.findViewById(R.id.zdzs_termcheck_puhuo);
        zdzs_termcheck_youxiao = (CheckBox) view.findViewById(R.id.zdzs_termcheck_youxiao);
        zdzs_termcheck_xiaoshou = (CheckBox) view.findViewById(R.id.zdzs_termcheck_xiaoshou);
        zdzs_termcheck_black.setOnCheckedChangeListener(this);
        zdzs_termcheck_puhuo.setOnCheckedChangeListener(this);
        zdzs_termcheck_youxiao.setOnCheckedChangeListener(this);
        zdzs_termcheck_xiaoshou.setOnCheckedChangeListener(this);
        // 铺货状态 选择产品一行
        rl_proselect = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_proselect);
        rl_proselect.setOnClickListener(this);
        proname_lv = (TextView) view.findViewById(R.id.zdzs_termcheck_proname);// 产品名称
        proname_lv.setTag("");
        prolv_tv = (TextView) view.findViewById(R.id.zdzs_termcheck_get06);// 收起
        prolv_tv.setOnClickListener(this);
        // 底端产品列表 铺货状态 产品列表
        prolv_ll = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_prolv);
        termcheck_pro_lv = (ListView) view.findViewById(R.id.zdzs_termcheck_pro);


        // 价格体系
        zdzs_termcheck_ll_price = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_price);
        zdzs_termcheck_rl_proprice = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_proprice);
        zdzs_termcheck_proprice = (TextView) view.findViewById(R.id.zdzs_termcheck_proprice);
        zdzs_termcheck_pronameprice = (TextView) view.findViewById(R.id.zdzs_termcheck_pronameprice);
        zdzs_termcheck_priceget = (TextView) view.findViewById(R.id.zdzs_termcheck_priceget);
        zdzs_termcheck_ll_proprice = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_proprice);
        zdzs_termcheck_lv_proprice = (ListView) view.findViewById(R.id.zdzs_termcheck_lv_proprice);
        zdzs_termcheck_rl_proprice.setOnClickListener(this);
        zdzs_termcheck_priceget.setOnClickListener(this);


        // 竞品信息
        zdzs_termcheck_ll_top_cmppro = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_top_cmppro);
        zdzs_termcheck_rl_cmppro = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_cmppro);
        cmpproname_lv = (TextView) view.findViewById(R.id.zdzs_termcheck_tv_cmppro);// c产品名称
        cmpproname_lv.setTag("");
        zdzs_termcheck_cmpproget06 = (TextView) view.findViewById(R.id.zdzs_termcheck_cmpproget06);
        zdzs_termcheck_ll_cmppro = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_cmppro);
        zdzs_termcheck_lv_cmppro = (ListView) view.findViewById(R.id.zdzs_termcheck_lv_cmppro);
        zdzs_termcheck_rl_cmppro.setOnClickListener(this);
        zdzs_termcheck_cmpproget06.setOnClickListener(this);


        // 基本信息
        rb_mineshop = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_mineshop);
        rb_notmineshop = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_notmineshop);
        mZdzs_termcheck_minearea = (CheckBox) view.findViewById(R.id.zdzs_termcheck_minearea);
        mZdzs_termcheck_cmparea = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cmparea);
        mZdzs_termcheck_minetreaty = (CheckBox) view.findViewById(R.id.zdzs_termcheck_minetreaty);
        mZdzs_termcheck_cmptreaty = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cmptreaty);
        mZdzs_termcheck_termone = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termone);
        mZdzs_termcheck_termtwo = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termtwo);
        mZdzs_termcheck_termthree = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termthree);
        mZdzs_termcheck_termfour = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termfour);
        mZdzs_termcheck_termcq = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termcq);
        mZdzs_termcheck_termxz = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termxz);
        mZdzs_termcheck_termcj = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termcj);
        mZdzs_termcheck_termddb = (CheckBox) view.findViewById(R.id.zdzs_termcheck_termddb);
        mZdzs_termcheck_termaddress = (EditText) view.findViewById(R.id.zdzs_termcheck_termaddress);
        mZdzs_termcheck_termcycle = (EditText) view.findViewById(R.id.zdzs_termcheck_termcycle);
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
        cb_ka = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_ka1);
        cb_s = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_s1);
        cb_nl = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_nl1);
        cb_op = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_op1);
        cb_sc = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_sc1);
        // 所有的销售次渠道
        flower_tags1 = (com.nex3z.flowlayout.FlowLayout) view.findViewById(R.id.flower_tags1);
        cb_ka.setOnCheckedChangeListener(this);
        cb_s.setOnCheckedChangeListener(this);
        cb_nl.setOnCheckedChangeListener(this);
        cb_op.setOnCheckedChangeListener(this);
        cb_sc.setOnCheckedChangeListener(this);

        // 指标信息  产品组合是否达标 合作执行是否到位 是否高质量配送
        rb_checkzh = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_checkzh);
        rb_notcheckzh = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_notcheckzh);
        rb_checkhz = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_checkhz);
        rb_notcheckhz = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_notcheckhz);
        rb_checkgao = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_checkgao);
        rb_notcheckgao = (CheckBox) view.findViewById(R.id.zdzs_termcheck_rb_notcheckgao);
        rb_checkzh.setOnCheckedChangeListener(this);
        rb_notcheckzh.setOnCheckedChangeListener(this);
        rb_checkhz.setOnCheckedChangeListener(this);
        rb_notcheckhz.setOnCheckedChangeListener(this);
        rb_checkgao.setOnCheckedChangeListener(this);
        rb_notcheckgao.setOnCheckedChangeListener(this);


        // 终端容量 标题及内容 收起 (砍掉)
        termcheck_num = (TextView) view.findViewById(R.id.zdzs_termcheck_get02);
        ll_termcheck_num = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_termnum);
        termcheck_num.setOnClickListener(this);

        // 高中普低 砍掉
        mZdzs_termcheck_hlow = (EditText) view.findViewById(R.id.zdzs_termcheck_hlow);// 高
        mZdzs_termcheck_hnum = (EditText) view.findViewById(R.id.zdzs_termcheck_hnum);
        mZdzs_termcheck_zlow = (EditText) view.findViewById(R.id.zdzs_termcheck_zlow);// 中
        mZdzs_termcheck_znum = (EditText) view.findViewById(R.id.zdzs_termcheck_znum);
        mZdzs_termcheck_plow = (EditText) view.findViewById(R.id.zdzs_termcheck_plow);// 普
        mZdzs_termcheck_pnum = (EditText) view.findViewById(R.id.zdzs_termcheck_pnum);
        mZdzs_termcheck_llow = (EditText) view.findViewById(R.id.zdzs_termcheck_llow);// 低
        mZdzs_termcheck_lnum = (EditText) view.findViewById(R.id.zdzs_termcheck_lnum);
        mZdzs_termcheck_zyllow = (EditText) view.findViewById(R.id.zdzs_termcheck_zyllow);// 占有率
        mZdzs_termcheck_zylnum = (EditText) view.findViewById(R.id.zdzs_termcheck_zylnum);

        // 渠道价零售价  砍掉
        ll_proprice1 = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_proprice1);
        ll_proprice2 = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_proprice2);
        mZdzs_termcheck_qdjlow = (EditText) view.findViewById(R.id.zdzs_termcheck_qdjlow);
        mZdzs_termcheck_qdjnum = (EditText) view.findViewById(R.id.zdzs_termcheck_qdjnum);
        mZdzs_termcheck_lsjlow = (EditText) view.findViewById(R.id.zdzs_termcheck_lsjlow);
        mZdzs_termcheck_lsjnum = (EditText) view.findViewById(R.id.zdzs_termcheck_lsjnum);


        // 确定按钮
        rl_sure = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_sure);
        rl_reset = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_reset);
        rl_sure.setOnClickListener(this);
        rl_reset.setOnClickListener(this);


        /*rb_checkzh.setOnCheckedChangeListener(this);
        rb_notcheckzh.setOnCheckedChangeListener(this);
        rb_checkhz.setOnCheckedChangeListener(this);
        rb_notcheckhz.setOnCheckedChangeListener(this);
        rb_checkgao.setOnCheckedChangeListener(this);
        rb_notcheckgao.setOnCheckedChangeListener(this);*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText(R.string.zdzs_selectterm);
        confirmTv.setText("确定");
        handler = new MyHandler(this);
        ConstValues.handler = handler;

        xtSelectService = new XtTermSelectService(getActivity());

        addInvocingService = new XtAddInvocingService(getActivity(), null);
        xtAddChatVieService = new XtAddChatVieService(getActivity(), null);

        checkStc = new TermCheckStc();
        // 设置三个下拉按钮的假数据
        initSomeData();

        // 三个下拉按钮的点击监听
        setDropdownItemSelectListener();

        termList = new ArrayList<XtTermSelectMStc>();

        // 设置终端列表数据 假数据
        // initTermListData("");

        // 查询已选中的终端
        selectedList = xtSelectService.queryZsTerminalCart("2");

        // 终端夹隔天清零
        String addTime = PrefUtils.getString(getActivity(), GlobalValues.ZS_CART_TIME, DateUtil.getDateTimeStr(7));
        if (!DateUtil.getDateTimeStr(7).equals(addTime)) {
            selectedList.clear();
            // 终端夹隔天清零
            xtSelectService.deleteCartData("MST_TERMINALINFO_M_ZSCART", "2");
        }

        setSelectTerm();// 设置已添加购物车的符号

        // 设置终端条目适配器,及条目点击事件
        setItemAdapterListener();

        // // 获取追溯模板 大区id
        String areapid = PrefUtils.getString(getActivity(), "departmentid", "");
        mitValcheckterMs = xtSelectService.getValCheckterMList(areapid);
        //mitValcheckterM = mitValcheckterMs.get(0);


        if (selectedList.size() > 0) {
            confirmTv.setText("确定" + "(" + selectedList.size() + ")");
        }


        // 销售渠道
        /*List<KvStc> dataDicLst = xtSelectService.initSecondSell();
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

            flower_tags.addView(checkBox);
        }*/


        //设置上拉下拉的数据刷新回调接口
        termRouteLv.setUltraRefreshListener(new UltraRefreshListener() {
            // 上拉加载
            @Override
            public void addMore() {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 获取新数据
                        getTermList();
                        // 关闭上拉加载的动画
                        //stopResh();
                        //termRouteLv.refreshComplete();


                    }
                }, 1000);
            }

            // 下拉刷新
            @Override
            public void onRefresh() {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 获取新数据
                        /*count  = end;
                        end = count+num;
                        getData(0);
                        adapter.notifyDataSetChanged();
                        // 关闭下拉刷新的动画
                        stopResh();*/
                        termRouteLv.refreshComplete();
                    }
                }, 1000);
            }
        });


    }

    // 三个下拉按钮的点击监听
    private void setDropdownItemSelectListener() {

        areaBtn.setData(areaList);
        gridBtn.setData(gridList);
        routeBtn.setData(routeList);

        areaBtn.setText("选择区域");
        gridBtn.setText("请先选择左侧");
        routeBtn.setText("请先选择左侧");

        // 区域选择
        areaBtn.setOnDropItemSelectListener(new DropdownButton.OnDropItemSelectListener() {
            @Override
            public void onDropItemSelect(int Postion) {
                //Toast.makeText(getContext(), "您选择了 " + areaList.get(Postion).getName(), Toast.LENGTH_SHORT).show();
                if (Postion == 0) {
                    gridList.clear();
                    gridBtn.setText("请先选择左侧");
                    gridList.add(new DropBean("请选择定格"));
                    gridBtn.setData(gridList);
                    routeList.clear();
                    routeBtn.setText("请先选择左侧");
                    routeList.add(new DropBean("请选择路线"));
                    routeBtn.setData(routeList);
                } else {
                    gridList.clear();
                    gridList.add(new DropBean("请选择定格"));
                    areaKey = areaList.get(Postion).getKey();
                    List<MstGridM> valueLst = xtSelectService.getMstGridMList(areaKey);
                    for (MstGridM mstGridM : valueLst) {
                        gridList.add(new DropBean(mstGridM.getGridname(), mstGridM.getGridkey()));
                    }

                    gridBtn.setData(gridList);
                }
            }
        });

        // 定格选择
        gridBtn.setOnDropItemSelectListener(new DropdownButton.OnDropItemSelectListener() {
            @Override
            public void onDropItemSelect(int Postion) {
                //Toast.makeText(getContext(), "您选择了 " + gridList.get(Postion).getName(), Toast.LENGTH_SHORT).show();
                if (Postion == 0) {
                    routeList.clear();
                    routeBtn.setText("请先选择左侧");
                    routeList.add(new DropBean("请选择路线"));
                    routeBtn.setData(routeList);
                } else {
                    routeList.clear();
                    routeList.add(new DropBean("请选择路线"));
                    gridKey = gridList.get(Postion).getKey();
                    List<MstRouteM> valueLst = xtSelectService.getMstRouteMList(gridKey);
                    for (MstRouteM mstRouteM : valueLst) {
                        routeList.add(new DropBean(mstRouteM.getRoutename(), mstRouteM.getRoutekey()));
                    }
                    routeBtn.setData(routeList);
                }
            }
        });

        // 路线选择
        routeBtn.setOnDropItemSelectListener(new DropdownButton.OnDropItemSelectListener() {
            @Override
            public void onDropItemSelect(int Postion) {
                //Toast.makeText(getContext(), "您选择了 " + routeList.get(Postion).getName(), Toast.LENGTH_SHORT).show();
                //
                routeKey = routeList.get(Postion).getKey();
                // 请求路线下的所有终端
                String content = "{routekey:'" + routeKey + "'," + "tablename:'MST_TERMINALINFO_M'" + "}";
                getDataByHttp("opt_get_dates2", "MST_TERMINALINFO_M", content);
                PrefUtils.putString(getActivity(), GlobalValues.ROUNTE_TIME, DateUtil.getDateTimeStr(7));
            }
        });
    }

    // 设置终端列表数据 假数据
    private void initTermListData(String routekey) {

        // 绑定TermList数据
        List<String> routes = new ArrayList<String>();
        routes.add(routekey);// 不同路线
        termList.clear();
        // termList = xtSelectService.queryZsTerminal(routes);
        termList.addAll(xtSelectService.queryZsTerminal(routes));
    }

    XtTermSelectAdapter selectAdapter;

    //  设置终端条目适配器,及条目点击事件
    private void setItemAdapterListener() {
        // 设置适配器 加号按钮点击事件
        selectAdapter = new XtTermSelectAdapter(getActivity(), termList, termList, confirmTv, null, new IXtTermSelectClick() {
            @Override
            public void imageViewClick(int position, View v) {
                ImageView imageView = (ImageView) v;
                XtTermSelectMStc item = termList.get(position);
                //MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(item.getTerminalkey());
                if (selectedList.contains(item)) {
                    selectedList.remove(item);
                    item.setIsSelectToCart("0");
                    imageView.setImageResource(R.drawable.icon_visit_add);
                    confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                } else {
                    selectedList.add(item);
                    item.setIsSelectToCart("1");
                    imageView.setImageResource(R.drawable.icon_select_minus);
                    confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                }
            }
        });
        // 设置适配器
        termRouteLv.setAdapter(selectAdapter);
        //termRouteLv.setSelector(R.color.bg_content_color_gray);
        // 条目点击事件
        termRouteLv.setOnItemClickListener(this);
        // 终端列表显示,之后放到下拉选择后显示
        termRouteLl.setVisibility(View.VISIBLE);
    }

    // 下来菜单设置数据  设置区域数据
    private void initSomeData() {
        areaList = new ArrayList<>();
        gridList = new ArrayList<>();
        routeList = new ArrayList<>();
        areaList.add(new DropBean("请选择区域"));
        List<MstMarketareaM> valueLst = xtSelectService.getMstMarketareaMList(PrefUtils.getString(getActivity(), "departmentid", ""));
        for (MstMarketareaM mstMarketareaM : valueLst) {
            areaList.add(new DropBean(mstMarketareaM.getAreaname(), mstMarketareaM.getAreaid()));
        }

        gridList.add(new DropBean("请选择定格"));
        routeList.add(new DropBean("请选择路线"));
    }


    // 选中事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            // 返回
            case R.id.zdzs_termcheck_cb_ka1:// 渠道ka
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
            case R.id.zdzs_termcheck_cb_s1:// 渠道s
                if (isChecked) {
                    zhuSell.add("39DD41A399338C68E05010ACE0016FCD");
                } else {
                    zhuSell.remove("39DD41A399338C68E05010ACE0016FCD");

                    sellTag.remove("39DD41A3991D8C68E05010ACE0016FCD");
                    sellTag.remove("39DD41A3991E8C68E05010ACE0016FCD");
                }

                setCiSell();
                break;
            case R.id.zdzs_termcheck_cb_op1:// 渠道op
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
            case R.id.zdzs_termcheck_cb_nl1:// 渠道nl
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
            case R.id.zdzs_termcheck_cb_sc1:// 渠道sc
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
            case R.id.zdzs_termcheck_black:// 空白
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("301");
                }else{
                    // puhuoLv.remove("301");
                    Iterator<String> it = puhuoLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("301")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_puhuo:// 铺货
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("302");
                }else{
                    // puhuoLv.remove("302");
                    Iterator<String> it = puhuoLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("302")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_youxiao:// 有效铺货
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("303");
                }else{
                    // puhuoLv.remove("303");
                    Iterator<String> it = puhuoLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("303")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_xiaoshou:// 有效销售
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    rl_proselect.setVisibility(View.VISIBLE);// 选择产品

                    puhuoLv.add("304");
                }else{
                    // puhuoLv.remove("304");
                    Iterator<String> it = puhuoLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("304")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termone:// 一级终端//
                if (isChecked) {
                    termlv.add("81B1A7272795498FBBE8EBDFB065F9FE");
                }else{
                    Iterator<String> it = termlv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("81B1A7272795498FBBE8EBDFB065F9FE")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termtwo:// 二级终端//
                if (isChecked) {
                    termlv.add("20E51C0398E34AC8A09375470B5D9DFE");
                }else{
                    Iterator<String> it = termlv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("20E51C0398E34AC8A09375470B5D9DFE")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termthree:// 三级终端//
                if (isChecked) {
                    termlv.add("3B86CC33732C454291509E1745FF315E");
                }else{
                    Iterator<String> it = termlv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("3B86CC33732C454291509E1745FF315E")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termfour:// 四级终端//
                if (isChecked) {
                    termlv.add("4D49F29894CA4C71B4DA56629CEED17F");
                }else{
                    //termlv.remove("4");
                    Iterator<String> it = termlv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("4D49F29894CA4C71B4DA56629CEED17F")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termcq:// 城区//
                if (isChecked) {
                    areaLv.add("85C3678B44FA42B794F8BABD2846E6D1");
                }else{
                    //areaLv.remove("85C3678B44FA42B794F8BABD2846E6D1");
                    Iterator<String> it = areaLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("85C3678B44FA42B794F8BABD2846E6D1")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termxz:// 乡镇//
                if (isChecked) {
                    areaLv.add("4C37979BC4424890BEA016EE7DED02CE");
                }else{
                    // areaLv.remove("4C37979BC4424890BEA016EE7DED02CE");
                    Iterator<String> it = areaLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("4C37979BC4424890BEA016EE7DED02CE")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termcj:// 村级//
                if (isChecked) {
                    areaLv.add("5FA07909011F447AB3C142C52CD54DD4");
                }else{
                    // areaLv.remove("5FA07909011F447AB3C142C52CD54DD4");
                    Iterator<String> it = areaLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("5FA07909011F447AB3C142C52CD54DD4")){
                            it.remove();
                        }
                    }
                }
                break;
            case R.id.zdzs_termcheck_termddb:// 大店部//
                if (isChecked) {
                    areaLv.add("29BC5F0C31A4D59DE050A8C0D6006510");
                }else{
                    // areaLv.remove("29BC5F0C31A4D59DE050A8C0D6006510");
                    Iterator<String> it = areaLv.iterator();
                    while(it.hasNext()){
                        String x = it.next();
                        if(x.equals("29BC5F0C31A4D59DE050A8C0D6006510")){
                            it.remove();
                        }
                    }
                }
                break;

            case R.id.zdzs_termcheck_rb_mineshop:// 我品店招//
                if (isChecked) {
                    rb_mineshop.setChecked(true);
                    rb_notmineshop.setChecked(false);
                }else{
                    rb_mineshop.setChecked(false);

                }
                break;
            case R.id.zdzs_termcheck_rb_notmineshop:// 非我品店招//
                if (isChecked) {
                    rb_notmineshop.setChecked(true);
                    rb_mineshop.setChecked(false);
                }else{
                    rb_notmineshop.setChecked(false);
                }
                break;
            case R.id.zdzs_termcheck_rb_checkzh:// 产品组合达标//
                if (isChecked) {
                    rb_checkzh.setChecked(true);
                    rb_notcheckzh.setChecked(false);
                }else{
                    rb_checkzh.setChecked(false);

                }
                break;
            case R.id.zdzs_termcheck_rb_notcheckzh:// 产品组合未达标//
                if (isChecked) {
                    rb_notcheckzh.setChecked(true);
                    rb_checkzh.setChecked(false);
                }else{
                    rb_notcheckzh.setChecked(false);
                }
                break;
            case R.id.zdzs_termcheck_rb_checkhz:// 合作执行到位//
                if (isChecked) {
                    rb_checkhz.setChecked(true);
                    rb_notcheckhz.setChecked(false);
                }else{
                    rb_checkhz.setChecked(false);
                }
                break;
            case R.id.zdzs_termcheck_rb_notcheckhz:// 合作执行未到位//
                if (isChecked) {
                    rb_notcheckhz.setChecked(true);
                    rb_checkhz.setChecked(false);
                }else{
                    rb_notcheckhz.setChecked(false);
                }
                break;
            case R.id.zdzs_termcheck_rb_checkgao:// 高质量配送//
                if (isChecked) {
                    rb_checkgao.setChecked(true);
                    rb_notcheckgao.setChecked(false);
                }else{
                    rb_checkgao.setChecked(false);
                }
                break;
            case R.id.zdzs_termcheck_rb_notcheckgao:// 非高质量配送//
                if (isChecked) {
                    rb_notcheckgao.setChecked(true);
                    rb_checkgao.setChecked(false);
                }else{
                    rb_notcheckgao.setChecked(false);
                }
                break;



            case R.id.rb_check:// 铺货状态
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
                                while(it.hasNext()){
                                    ProSellStc x = it.next();
                                    if(itemTv.getHint().equals(x.getKey())){
                                        it.remove();
                                    }
                                }
                            }
                            sadapter.notifyDataSetChanged();
                        }
                    });

                }

                break;
            case R.id.rb_price:// 价格体系
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
            case R.id.rb_cmppro:// 竞品信息
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

    AgencyCmpLvAdapter cmpAdapter;
    ProCheckLvAdapter lvAdapter;

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.xtbf_termcheck_bt_search:// 放大镜搜索
                searchTerm();
                break;
            case R.id.zdzs_termcheck_rl_reset:// 筛选重置按钮
                reSetTermSelect();
                break;
            case R.id.zdzs_termcheck_rl_sure:// 筛选确定按钮

                // 第一次筛选 先清空Mst_terminal_m表 因为若是已经选了路线了,这张表就会有数据了,展示的筛选结果就不对了
                xtSelectService.deleteMst_terminal_m();
                termList.clear();// 将展示在页面上的集合 数据删除

                startrow = 0;// 开始行
                endrow = pagercount;// 结束行

                getTermList();
                //关闭右侧菜单
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.top_navigation_tv_check:// 配置模板
                changeHomeFragment(new ZsTemplateFragment(), "xttermcartfragment");
                break;
            case R.id.xtbf_termcheck_lou:// 筛选终端
                // Toast.makeText(getActivity(), "请筛选终端", Toast.LENGTH_SHORT).show();
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.zdzs_termcheck_get02:// 终端容量收起
                String s = termcheck_num.getText().toString();
                if ("收起".equals(s)) {
                    termcheck_num.setText("打开");
                    ll_termcheck_num.setVisibility(View.GONE);
                } else {
                    termcheck_num.setText("收起");
                    ll_termcheck_num.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zdzs_termcheck_get06:// 铺货状态 产品信息 收起
            case R.id.zdzs_termcheck_rl_proselect:// 铺货状态  产品信息 收起
                String pro = prolv_tv.getText().toString();
                if ("收起".equals(pro)) {
                    prolv_tv.setText("打开");
                    prolv_ll.setVisibility(View.GONE);
                } else {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zdzs_termcheck_priceget:// 价格体系 产品信息 收起
            case R.id.zdzs_termcheck_rl_proprice:// 价格体系  产品信息 收起
                String priceget = zdzs_termcheck_priceget.getText().toString();
                if ("收起".equals(priceget)) {
                    zdzs_termcheck_priceget.setText("打开");
                    zdzs_termcheck_ll_proprice.setVisibility(View.GONE);
                } else {
                    zdzs_termcheck_priceget.setText("收起");
                    zdzs_termcheck_ll_proprice.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zdzs_termcheck_cmpproget06:// 竞品信息 产品信息 收起
            case R.id.zdzs_termcheck_rl_cmppro:// 竞品信息  产品信息 收起
                String cmpproget06 = zdzs_termcheck_cmpproget06.getText().toString();
                if ("收起".equals(cmpproget06)) {
                    zdzs_termcheck_cmpproget06.setText("打开");
                    zdzs_termcheck_ll_cmppro.setVisibility(View.GONE);
                } else {
                    zdzs_termcheck_cmpproget06.setText("收起");
                    zdzs_termcheck_ll_cmppro.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.top_navigation_rl_confirm:// 确定
                if (mitValcheckterMs.size() > 0) {// 配置了督导模板
                    // 生成临时表,跳转终端购物车
                    breakNextLayout(TOFRAGMENT, selectedList);
                    PrefUtils.putString(getActivity(), GlobalValues.DDXTZS, "2");
                } else {
                    Toast.makeText(getActivity(), "请先配置督导模板", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xtbf_termcheck_bt_add:// 全部添加
                for (XtTermSelectMStc selectMStc : termList) {
                    //MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(selectMStc.getTerminalkey());
                    if (selectedList.contains(selectMStc)) {
                        selectedList.remove(selectMStc);
                    }
                    selectMStc.setIsSelectToCart("1");
                }
                selectedList.addAll(termList);
                selectAdapter.notifyDataSetChanged();
                confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                break;
            case R.id.xtbf_termcheck_bt_remove:// 全部删除
                for (XtTermSelectMStc selectMStc : termList) {
                    selectMStc.setIsSelectToCart("0");
                }
                selectedList.clear();
                selectAdapter.notifyDataSetChanged();
                confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                break;
            /*case R.id.xtbf_termselect_bt_search:// 查询
                Toast.makeText(getActivity(), "未查到终端,请到相关路线下寻找", Toast.LENGTH_SHORT).show();
                break;*/
            default:
                break;
        }
    }

    // 模糊搜多终端
    private void searchTerm() {

        String termname =searchEt.getText().toString();
        if("".equals(termname)||TextUtils.isEmpty(termname)){
            Toast.makeText(getActivity(),"请输入终端名称",Toast.LENGTH_SHORT).show();
        }else{
            String json = "{bigid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "', " +
                    "secid:'" + areaKey + "'," +
                    "gridid:'" + gridKey + "'," +
                    "routeid:'" + routeKey + "'," +
                    "terminalname:'" + termname + "'}";
            getTermDataByTermName("opt_terminalbyterminalname_3","MST_TERMINALINFO_M",json);
        }
    }

    /**
     * 根据终端名称 条件请求终端
     *
     * @param optcode
     * @param tableName
     * @param content
     */
    void getTermDataByTermName(final String optcode, final String tableName, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                        // 保存登录信息
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            // 请求路线下所有终端列表
                            if ("opt_terminalbyterminalname_3".equals(optcode) && "MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                // parseTermListJson(formjson, 0);// 0:不需要  1需要删除
                                parseSearchTermListJson(formjson);// 0:不需要  1需要删除
                                /*initTermListData("");
                                // refreshTermListData();// 读取刚刚下载的终端数据,并添加到list中
                                setSelectTerm();// 设置已添加购物车的符号
                                selectAdapter.notifyDataSetChanged();*/
                            }
                        } else {
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 筛选重置
    private void reSetTermSelect() {

        // 新建一个筛选对象
        checkStc = new TermCheckStc();

        // 铺货状态 价格体系 竞品信息
        rb_check.setChecked(false);//
        rb_price.setChecked(false);
        rb_cmppro.setChecked(false);


        // 铺货状态 价格体系 竞品信息产品 全部隐藏
        ll_procheck.setVisibility(View.GONE);
        zdzs_termcheck_ll_price.setVisibility(View.GONE);
        zdzs_termcheck_ll_top_cmppro.setVisibility(View.GONE);

        puhuoLv.clear();// 铺货状态
        checkSelectLst.clear();// 铺货状态选中的产品 一个小集合
        priceProLst.clear();// 价格体系要展示的产品集合 (但当小集合有值时,展示小集合)

        zdzs_termcheck_black.setChecked(false);
        zdzs_termcheck_puhuo.setChecked(false);
        zdzs_termcheck_youxiao.setChecked(false);
        zdzs_termcheck_xiaoshou.setChecked(false);

        proname_lv.setTag("");
        cmpproname_lv.setTag("");

        // 产品信息全部设为false,
        rb_mineshop.setChecked(false);//
        rb_notmineshop.setChecked(false);//
        mZdzs_termcheck_minearea.setChecked(false);//
        mZdzs_termcheck_cmparea.setChecked(false);//
        mZdzs_termcheck_minetreaty.setChecked(false);//
        mZdzs_termcheck_cmptreaty.setChecked(false);//
        mZdzs_termcheck_termone.setChecked(false);//
        mZdzs_termcheck_termtwo.setChecked(false);//
        mZdzs_termcheck_termthree.setChecked(false);//
        mZdzs_termcheck_termfour.setChecked(false);//
        mZdzs_termcheck_termcq.setChecked(false);//
        mZdzs_termcheck_termxz.setChecked(false);//
        mZdzs_termcheck_termcj.setChecked(false);//
        mZdzs_termcheck_termddb.setChecked(false);//
        termlv.clear();//终端等级
        areaLv.clear();// 区域类型
        mZdzs_termcheck_termaddress.setText("");
        mZdzs_termcheck_termcycle.setText("");

        // 渠道全部设为false
        zhuSell.clear();// 主渠道
        sellTag.clear();// 次渠道
        cb_ka.setChecked(false);//
        cb_s.setChecked(false);//
        cb_nl.setChecked(false);//
        cb_op.setChecked(false);//
        cb_sc.setChecked(false);//

        // 合作到位全部设为false
        rb_checkzh.setChecked(false);//
        rb_notcheckzh.setChecked(false);//
        rb_checkhz.setChecked(false);//
        rb_notcheckhz.setChecked(false);//
        rb_checkgao.setChecked(false);//
        rb_notcheckgao.setChecked(false);//

    }

    // 发起请求
    private void getTermList() {

        // 判断是否需要发起请求
        if (startrow == -1) {

        } else {


            if (rb_mineshop.isChecked()) {// 我品店招
                checkStc.setMineshop("1");
            } else if (rb_notmineshop.isChecked()) {
                checkStc.setMineshop("0");
            }
            if (mZdzs_termcheck_minearea.isChecked()) {// 我品销售范围
                checkStc.setMinearea("1");
            }else{
                checkStc.setMinearea("");
            }
            if (mZdzs_termcheck_cmparea.isChecked()) {// 竞品销售范围
                checkStc.setCmparea("1");
            }else{
                checkStc.setCmparea("");
            }
            if (mZdzs_termcheck_minetreaty.isChecked()) {// 我品合作
                checkStc.setMinetreaty("1");
            }else{
                checkStc.setMinetreaty("");
            }
            if (mZdzs_termcheck_cmptreaty.isChecked()) {// 竞品合作
                checkStc.setCmptreaty("1");
            }else{
                checkStc.setCmptreaty("");
            }
            /*if (mZdzs_termcheck_termone.isChecked()) {// 1级
                // checkStc.setTermone("Y");
                termlv.add("1");
            }
            if (mZdzs_termcheck_termtwo.isChecked()) {//2级
                // checkStc.setTermtwo("Y");
                termlv.add("2");
            }
            if (mZdzs_termcheck_termthree.isChecked()) {// 3级
                // checkStc.setTermthree("Y");
                termlv.add("3");
            }
            if (mZdzs_termcheck_termfour.isChecked()) {// 4级
                // checkStc.setTermfour("Y");
                termlv.add("4");
            }
            if (mZdzs_termcheck_termcq.isChecked()) {// 城区
                // checkStc.setTermcq("Y");
                areaLv.add("85C3678B44FA42B794F8BABD2846E6D1");
            }
            if (mZdzs_termcheck_termxz.isChecked()) {// 乡镇
                // checkStc.setTermxz("Y");
                areaLv.add("4C37979BC4424890BEA016EE7DED02CE");
            }
            if (mZdzs_termcheck_termcj.isChecked()) {// 村级
                // checkStc.setTermcj("Y");
                areaLv.add("5FA07909011F447AB3C142C52CD54DD4");
            }
            if (mZdzs_termcheck_termddb.isChecked()) {// 大店部
                // checkStc.setTermddb("Y");
                areaLv.add("29BC5F0C31A4D59DE050A8C0D6006510");
            }*/


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


            /*if (zdzs_termcheck_black.isChecked()) {// 空白
                if(!puhuoLv.contains("301")){
                    puhuoLv.add("301");
                }
            }
            if (zdzs_termcheck_puhuo.isChecked()) {// 铺货
                if(!puhuoLv.contains("302")){
                    puhuoLv.add("302");
                }
            }
            if (zdzs_termcheck_youxiao.isChecked()) {// 有效铺货
                if(!puhuoLv.contains("303")){
                    puhuoLv.add("303");
                }
            }
            if (zdzs_termcheck_xiaoshou.isChecked()) {// 有效终端
                if(!puhuoLv.contains("304")){
                    puhuoLv.add("304");
                }
            }*/

            checkStc.setTermaddress(mZdzs_termcheck_termaddress.getText().toString());
            checkStc.setTermcycle(mZdzs_termcheck_termcycle.getText().toString());
            /*checkStc.setHlow(mZdzs_termcheck_hlow.getText().toString());
            checkStc.setHnum(mZdzs_termcheck_hnum.getText().toString());
            checkStc.setZlow(mZdzs_termcheck_zlow.getText().toString());
            checkStc.setZnum(mZdzs_termcheck_znum.getText().toString());
            checkStc.setPlow(mZdzs_termcheck_plow.getText().toString());
            checkStc.setPnum(mZdzs_termcheck_pnum.getText().toString());
            checkStc.setLlow(mZdzs_termcheck_llow.getText().toString());
            checkStc.setLnum(mZdzs_termcheck_lnum.getText().toString());
            checkStc.setZyllow(mZdzs_termcheck_zyllow.getText().toString());
            checkStc.setZylnum(mZdzs_termcheck_zylnum.getText().toString());
            checkStc.setQdjlow(mZdzs_termcheck_qdjlow.getText().toString());
            checkStc.setQdjnum(mZdzs_termcheck_qdjnum.getText().toString());
            checkStc.setLsjlow(mZdzs_termcheck_lsjlow.getText().toString());
            checkStc.setLsjnum(mZdzs_termcheck_lsjnum.getText().toString());*/

            checkStc.setSell(sellTag);// 销售次渠道
            checkStc.setPuhuolst(puhuoLv);// 铺货,空白,有效铺货,有效销售
            checkStc.setTermlvlst(termlv);//  终端等级
            checkStc.setArealst(areaLv);// 区域类型学

            String prokey = (String) proname_lv.getTag();// 铺货状态 产品key
            if (prokey.endsWith(",")) {
                checkStc.setProkey(prokey.substring(0, prokey.length() - 1));
            }

            String cmpprokey = (String) cmpproname_lv.getTag();// 竞品信息 产品key
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


            checkStc.setStartrow(startrow);// 0  101
            checkStc.setEndrow(endrow);// 100  200
            startrow = endrow + 1;// 101  201
            endrow = endrow + pagercount;// 200  300


            String json = JsonUtil.toJson(checkStc);
            getTermDataByUrl("opt_query_terminal", "MST_TERMINALINFO_M", json);
            String sd = "";

        }


    }

    /**
     * 根据条件请求终端
     *
     * @param optcode
     * @param tableName
     * @param content
     */
    void getTermDataByUrl(final String optcode, final String tableName, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                        // 保存登录信息
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            // 请求路线下所有终端列表
                            if ("opt_query_terminal".equals(optcode) && "MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                parseTermListJson(formjson, 0);// 0:不需要  1需要删除
                                // initTermListData("");
                                refreshTermListData();// 读取刚刚下载的终端数据,并添加到list中
                                setSelectTerm();// 设置已添加购物车的符号
                                selectAdapter.notifyDataSetChanged();
                                termRouteLv.refreshComplete();
                            }
                        } else {
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    private void refreshTermListData() {
        // 绑定TermList数据
        /*List<String> routes = new ArrayList<String>();
        routes.add(routekey);// 不同路线
        termList.clear();
        // termList = xtSelectService.queryZsTerminal(routes);*/
        termList.addAll(xtSelectService.queryShaixuanTerminal());
    }

    // listview的条目点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //view.setBackgroundColor(getResources().getColor(R.color.bg_content_color_gray));
        /*selectedList.clear();
        //MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(termList.get(position).getTerminalkey());
        selectedList.add(termList.get(position));
        // 生成临时表,跳转终端拜访
        breakNextLayout(TOACTIVITY, selectedList);*/

        if (mitValcheckterMs.size() > 0) {// 配置了督导模板
            xtTermSelectMStc = termList.get(position);
            // 检测条数是否已上传  // 该终端追溯数据是否全部上传
            List<MitValterM> terminalList = xtSelectService.getZsMitValterM(xtTermSelectMStc.getTerminalkey());
            if (terminalList.size() > 0) {// 未上传,弹窗上传
                deleteOrXtUplad(terminalList.get(0));
            } else {// 已上传,去追溯

                if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    // 弹出提示 是否拜访这家终端
                    confirmXtUplad(xtTermSelectMStc);// 去拜访
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
                }
            }
        } else {
            Toast.makeText(getActivity(), "请先配置督导模板", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doLocation() {
        confirmXtUplad(xtTermSelectMStc);// 去拜访
    }

    /**
     * 生成临时表,并实现页面跳转
     *
     * @param type         1:跳转Fragment  2:跳转Activity
     * @param selectedList 用户选择的终端
     */
    public void breakNextLayout(int type, List<XtTermSelectMStc> selectedList) {

        if (TOFRAGMENT == type) {
            if (selectedList.size() > 0) {
                // 清空购物车表数据  // 购物车表ddtype 1:协同  2:追溯
                //xtSelectService.deleteCartData("MST_TERMINALINFO_M_CART","2");
                // xtSelectService.deleteCartData("MST_TERMINALINFO_M_ZSCART", "2");
                // 复制终端临时表
                for (XtTermSelectMStc xtselect : selectedList) {
                    copyMstTerminalinfoMZsCart(xtselect);
                }
                // 销毁当前Fragment
                supportFragmentManager.popBackStack();

                /*Bundle bundle = new Bundle();
                bundle.putSerializable("fromFragment", "ZsTermSelectFragment");
                //bundle.putSerializable("mitValcheckterM", mitValcheckterMs.get(0));
                ZsTermCartFragment zsTermCartFragment = new ZsTermCartFragment();
                zsTermCartFragment.setArguments(bundle);

                // 跳转终端购物车
                changeHomeFragment(zsTermCartFragment, "xttermcartfragment");*/
                changeHomeFragment(new ZsTermCartFragment(), "xttermcartfragment");
                PrefUtils.putBoolean(getActivity(), GlobalValues.ZS_CART_SYNC, false);// false 追溯购物车 需要同步
                PrefUtils.putString(getActivity(), GlobalValues.ZS_CART_TIME, DateUtil.getDateTimeStr(7));// 添加追溯文件夹时间

                Toast.makeText(getActivity(), "终端夹添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "请先添加终端", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 查找终端,并复制到终端临时表
    public void copyMstTerminalinfoMTemp(XtTermSelectMStc xtselect) {
        MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(xtselect.getTerminalkey());
        xtSelectService.toCopyMstTerminalinfoMData(term);
    }

    // 查找终端,并复制到终端购物车
    public void copyMstTerminalinfoMZsCart(XtTermSelectMStc termSelectMStc) {
        MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(termSelectMStc.getTerminalkey());
        if (term != null) {
            xtSelectService.toCopyMstTerminalinfoMZsCartData(term, "2");
        }
    }


    // 条目点击 确定拜访一家终端
    private void confirmXtUplad(final XtTermSelectMStc termSelectMStc) {
        String termName = termSelectMStc.getTerminalname();
        // 普通窗口
        mAlertViewExt = new AlertView("追溯:" + termName, null, "取消", new String[]{"确定"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "前往拜访：是");

                            // 复制到终端购物车
                            copyMstTerminalinfoMZsCart(xtTermSelectMStc);

                            if (selectedList.contains(termSelectMStc)) {
                                //selectedList.remove(termSelectMStc);
                                //termSelectMStc.setIsSelectToCart("0");
                                //imageView.setImageResource(R.drawable.icon_visit_add);
                                confirmTv.setText("确定" + "(" + selectedList.size() + ")");

                            } else {
                                selectedList.add(termSelectMStc);
                                termSelectMStc.setIsSelectToCart("1");
                                //imageView.setImageResource(R.drawable.icon_select_minus);
                                confirmTv.setText("确定" + "(" + selectedList.size() + ")");

                            }
                            selectAdapter.notifyDataSetChanged();

                            List<String> termKeyLst = new ArrayList<String>();
                            termKeyLst.add(termSelectMStc.getTerminalkey());
                            String json = JsonUtil.toJson(termKeyLst);
                            String content = "{" +
                                    "terminalkeys:'" + json + "'," +
                                    "tablename:'" + "MST_VISITDATA_M" + "'" +
                                    "}";

                            getDataByHttp("opt_get_dates2", "MST_VISITDATA_M", content);
                        }
                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "前往拜访：否");
                    }
                });
        mAlertViewExt.show();
    }

    // 条目点击 是否删除/上传这家记录
    private void deleteOrXtUplad(final MitValterM mitValterM) {
        final XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
        // 普通窗口
        mAlertViewExt = new AlertView("检测到这家终端上次数据未上传", null, null, new String[]{"删除", "上传"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "前往拜访：删除");
                            xtUploadService.deleteZs(mitValterM.getId(), mitValterM.getTerminalkey(), 1);
                            initTermListData(routeKey);
                            setSelectTerm();// 设置已添加购物车的符号
                            setItemAdapterListener();
                        } else if (1 == position) {
                            DbtLog.logUtils(TAG, "前往拜访：上传");
                            // 如果网络可用
                            if (NetStatusUtil.isNetValid(getActivity())) {
                                xtUploadService.upload_zs_visit(false, mitValterM.getId(), 1, 0);
                            } else {
                                // 提示修改网络
                                Toast.makeText(getContext(), "网络异常,请先检查网络连接", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "前往拜访：否");
                    }
                });
        mAlertViewExt.show();
    }


    /**
     * 请求路线下的所有终端
     *
     * @param optcode   请求码
     * @param tableName 请求的表
     * @param content   请求json
     */
    void getDataByHttp(final String optcode, final String tableName, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                        // 保存登录信息
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            // 请求路线下所有终端列表
                            if ("opt_get_dates2".equals(optcode) && "MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                parseTermListJson(formjson);
                                Toast.makeText(getActivity(), "路线下终端列表请求成功", Toast.LENGTH_SHORT).show();
                            }

                            // 请求某个终端上次拜访详情
                            if ("opt_get_dates2".equals(optcode) && "MST_VISITDATA_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                MainService mainService = new MainService(getActivity(), null);
                                mainService.parseTermDetailInfoJson(formjson);
                                startXtVisitShopActivity();

                            }

                        } else {
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 解析路线key下的终端
    private void parseTermListJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class);
        initTermListData(routeKey);
        setSelectTerm();// 设置已添加购物车的符号
        setItemAdapterListener();
    }
    // 解析终端名称模糊搜索下的终端
    private void parseSearchTermListJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class,1);
        initTermListData(routeKey);
        setSelectTerm();// 设置已添加购物车的符号
        setItemAdapterListener();
    }

    // 解析路线key下的终端 what 是否需要清除该表,再插入  0:不需要  1需要删除
    private void parseTermListJson(String json, int what) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();
        MainService service = new MainService(getActivity(), null);

        if ("[]".equals(MST_TERMINALINFO_M) || TextUtils.isEmpty(MST_TERMINALINFO_M)) {// 返回数据为空
            startrow = -1;
            endrow = -1;
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M_DOWN", MstTerminalinfoMDown.class, 1);
        } else {
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class, what);
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M_DOWN", MstTerminalinfoMDown.class, 1);
        }

        /*initTermListData(routeKey);
        setSelectTerm();// 设置已添加购物车的符号
        setItemAdapterListener();*/
    }


    // 跳转巡店拜访
    private void startXtVisitShopActivity() {
        List<MstVisitM> mstVisitMS = xtSelectService.getMstVisitMList(xtTermSelectMStc.getTerminalkey());
        if (mstVisitMS.size() > 0) {
            Intent intent = new Intent(getActivity(), ZsVisitShopActivity.class);
            intent.putExtra("isFirstVisit", "1");// 非第一次拜访1
            intent.putExtra("termStc", xtTermSelectMStc);
            intent.putExtra("mitValcheckterM", mitValcheckterMs.get(0));
            intent.putExtra("seeFlag", "0"); // 0拜访 1查看标识
            startActivity(intent);

            Toast.makeText(getActivity(), "该终端数据请求成功", Toast.LENGTH_SHORT).show();

            //PrefUtils.putBoolean(getActivity(),GlobalValues.ZS_CART_SYNC,false);// false 追溯购物车 需要同步
        } else {
            //
            Toast.makeText(getActivity(), "该终端从未拜访,不能追溯", Toast.LENGTH_SHORT).show();
        }

    }

    MyHandler handler;


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsTermGetFragment> fragmentRef;

        public MyHandler(ZsTermGetFragment fragment) {
            fragmentRef = new SoftReference<ZsTermGetFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsTermGetFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }


            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0://  结束上传  刷新本页面
                    fragment.shuaxinXtTermSelect(0);
                    break;
                case GlobalValues.SINGLE_UP_SUC://  协同拜访上传成功
                    fragment.shuaxinXtTermSelect(1);
                    break;
                case GlobalValues.SINGLE_UP_FAIL://  协同拜访上传失败
                    fragment.shuaxinXtTermSelect(2);
                    break;

            }
        }
    }

    // 结束上传  刷新页面  0:确定上传  1上传成功  2上传失败
    private void shuaxinXtTermSelect(int upType) {
        if (1 == upType) {
            Toast.makeText(MyApplication.getAppContext(), "上传成功", Toast.LENGTH_SHORT).show();
        } else if (2 == upType) {
            Toast.makeText(MyApplication.getAppContext(), "上传失败", Toast.LENGTH_SHORT).show();
        }
        initTermListData(routeKey);// 重新读取终端列表
        setSelectTerm();// 设置已添加购物车的符号
        setItemAdapterListener();// 适配器处理
    }

    // 设置当前界面添加符号
    private void setSelectTerm() {
        List<XtTermSelectMStc> selectedList2 = new ArrayList<XtTermSelectMStc>();
        List<XtTermSelectMStc> selectedList3 = new ArrayList<XtTermSelectMStc>();
        for (XtTermSelectMStc selectMStc : termList) {
            for (XtTermSelectMStc term : selectedList) {
                if (selectMStc.getTerminalkey().equals(term.getTerminalkey())) {
                    selectedList2.add(selectMStc);
                    selectedList3.add(term);
                    selectMStc.setIsSelectToCart("1");
                }
            }
        }
        selectedList.removeAll(selectedList3);// 清空
        selectedList.addAll(selectedList2);
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
}
