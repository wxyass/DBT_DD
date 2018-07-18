package et.tsingtaopad.dd.ddzs.zsterm.zsselect;

import android.content.Intent;
import android.graphics.Color;
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
import java.util.List;

import et.tsingtaopad.R;
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
import et.tsingtaopad.db.table.MitValcheckterM;
import et.tsingtaopad.db.table.MitValterM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstTerminalinfoM;
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
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.AgengcyLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.ProLvAdapter;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.TermCheckStc;
import et.tsingtaopad.home.app.MainActivity;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.home.app.MyApplication;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.util.requestHeadUtil;
import et.tsingtaopad.view.TagLayout;

/**
 * 督导筛选终端
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsTermCheckFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener
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
    private ListView termRouteLv;
    private Button searchBtn;
    private Button addAllTermBtn;
    private DrawerLayout mDrawerLayout;

    private XtTermSelectMStc xtTermSelectMStc;
    private List<MitValcheckterM> mitValcheckterMs;
    private ArrayList<String> sellTag = new ArrayList<>();

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

    private String routeKey;

    private TagLayout flower_tags;
    private RelativeLayout rl_sure;
    private RadioButton rb_check;
    private RadioButton rb_price;
    private RadioButton rb_cmppro;
    private ListView termcheck_pro_lv;
    private TextView proname_lv;


    private CheckBox mZdzs_termcheck_mineshop;//
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

    private CheckBox mZdzs_termcheck_checkzh;//
    private CheckBox mZdzs_termcheck_checkhz;//
    private CheckBox mZdzs_termcheck_checkgao;//
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
    private int pagercount = 100;// 每次请求的个数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_termcheck, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        checkTv = (TextView) view.findViewById(R.id.top_navigation_tv_check);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        checkTv.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        checkTv.setOnClickListener(this);

        areaBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_area);
        gridBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_grid);
        routeBtn = (DropdownButton) view.findViewById(R.id.xtbf_termcheck_route);
        termcheck_lou = (RelativeLayout) view.findViewById(R.id.xtbf_termcheck_lou);
        searchBtn = (Button) view.findViewById(R.id.xtbf_termcheck_bt_search);
        addAllTermBtn = (Button) view.findViewById(R.id.xtbf_termcheck_bt_add);
        termRouteLl = (LinearLayout) view.findViewById(R.id.xtbf_termcheck_ll_lv);
        termRouteLv = (ListView) view.findViewById(R.id.xtbf_termcheck_lv);
        addAllTermBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        termcheck_lou.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawerlayout);

        // 终端容量 收起
        termcheck_num = (TextView) view.findViewById(R.id.zdzs_termcheck_get02);
        termcheck_num.setOnClickListener(this);
        ll_termcheck_num = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_termnum);

        // 产品信息 收起
        prolv_tv = (TextView) view.findViewById(R.id.zdzs_termcheck_get06);
        prolv_tv.setOnClickListener(this);
        prolv_ll = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_prolv);

        cb_ka = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_ka);
        cb_s = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_s);
        cb_nl = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_nl);
        cb_op = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_op);
        cb_sc = (CheckBox) view.findViewById(R.id.zdzs_termcheck_cb_sc);

        cb_ka.setOnCheckedChangeListener(this);
        cb_s.setOnCheckedChangeListener(this);
        cb_nl.setOnCheckedChangeListener(this);
        cb_op.setOnCheckedChangeListener(this);
        cb_sc.setOnCheckedChangeListener(this);

        // 所有的销售次渠道
        flower_tags = (TagLayout) view.findViewById(R.id.flower_tags);
        rl_sure = (RelativeLayout) view.findViewById(R.id.zdzs_termcheck_rl_sure);
        rl_sure.setOnClickListener(this);

        // 铺货状态
        rb_check = (RadioButton) view.findViewById(R.id.rb_check);
        rb_price = (RadioButton) view.findViewById(R.id.rb_price);
        rb_cmppro = (RadioButton) view.findViewById(R.id.rb_cmppro);

        rb_check.setOnCheckedChangeListener(this);
        rb_price.setOnCheckedChangeListener(this);
        rb_cmppro.setOnCheckedChangeListener(this);

        // 底端产品列表
        termcheck_pro_lv = (ListView) view.findViewById(R.id.zdzs_termcheck_pro);
        proname_lv = (TextView) view.findViewById(R.id.zdzs_termcheck_proname);

        mZdzs_termcheck_mineshop = (CheckBox) view.findViewById(R.id.zdzs_termcheck_mineshop);
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

        mZdzs_termcheck_hlow = (EditText) view.findViewById(R.id.zdzs_termcheck_hlow);
        mZdzs_termcheck_hnum = (EditText) view.findViewById(R.id.zdzs_termcheck_hnum);
        mZdzs_termcheck_zlow = (EditText) view.findViewById(R.id.zdzs_termcheck_zlow);
        mZdzs_termcheck_znum = (EditText) view.findViewById(R.id.zdzs_termcheck_znum);
        mZdzs_termcheck_plow = (EditText) view.findViewById(R.id.zdzs_termcheck_plow);
        mZdzs_termcheck_pnum = (EditText) view.findViewById(R.id.zdzs_termcheck_pnum);
        mZdzs_termcheck_llow = (EditText) view.findViewById(R.id.zdzs_termcheck_llow);
        mZdzs_termcheck_lnum = (EditText) view.findViewById(R.id.zdzs_termcheck_lnum);

        mZdzs_termcheck_checkzh = (CheckBox) view.findViewById(R.id.zdzs_termcheck_checkzh);
        mZdzs_termcheck_checkhz = (CheckBox) view.findViewById(R.id.zdzs_termcheck_checkhz);
        mZdzs_termcheck_checkgao = (CheckBox) view.findViewById(R.id.zdzs_termcheck_checkgao);
        mZdzs_termcheck_zyllow = (EditText) view.findViewById(R.id.zdzs_termcheck_zyllow);
        mZdzs_termcheck_zylnum = (EditText) view.findViewById(R.id.zdzs_termcheck_zylnum);

        mZdzs_termcheck_checkid = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_checkid);
        zdzs_termcheck_black = (CheckBox) view.findViewById(R.id.zdzs_termcheck_black);
        zdzs_termcheck_puhuo = (CheckBox) view.findViewById(R.id.zdzs_termcheck_puhuo);
        zdzs_termcheck_youxiao = (CheckBox) view.findViewById(R.id.zdzs_termcheck_youxiao);
        zdzs_termcheck_xiaoshou = (CheckBox) view.findViewById(R.id.zdzs_termcheck_xiaoshou);
        zdzs_termcheck_black.setOnCheckedChangeListener(this);
        zdzs_termcheck_puhuo.setOnCheckedChangeListener(this);
        zdzs_termcheck_youxiao.setOnCheckedChangeListener(this);
        zdzs_termcheck_xiaoshou.setOnCheckedChangeListener(this);

        mZdzs_termcheck_qdjlow = (EditText) view.findViewById(R.id.zdzs_termcheck_qdjlow);
        mZdzs_termcheck_qdjnum = (EditText) view.findViewById(R.id.zdzs_termcheck_qdjnum);
        mZdzs_termcheck_lsjlow = (EditText) view.findViewById(R.id.zdzs_termcheck_lsjlow);
        mZdzs_termcheck_lsjnum = (EditText) view.findViewById(R.id.zdzs_termcheck_lsjnum);


        ll_proprice1 = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_proprice1);
        ll_proprice2 = (LinearLayout) view.findViewById(R.id.zdzs_termcheck_ll_proprice2);

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
        List<KvStc> dataDicLst = xtSelectService.initSecondSell();
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

            // tv.setBackgroundResource(R.drawable.text_background);
            flower_tags.addView(checkBox);
        }


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

                    List<MstGridM> valueLst = xtSelectService.getMstGridMList(areaList.get(Postion).getKey());
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
                    List<MstRouteM> valueLst = xtSelectService.getMstRouteMList(gridList.get(Postion).getKey());
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
        termList = xtSelectService.queryZsTerminal(routes);
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
            case R.id.zdzs_termcheck_cb_ka:
                if (isChecked) {
                    Toast.makeText(getActivity(), "渠道ka", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zdzs_termcheck_cb_s:
                if (isChecked) {
                    Toast.makeText(getActivity(), "渠道s", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zdzs_termcheck_cb_op:
                if (isChecked) {
                    Toast.makeText(getActivity(), "渠道op", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zdzs_termcheck_cb_nl:
                if (isChecked) {
                    Toast.makeText(getActivity(), "渠道nl", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zdzs_termcheck_cb_sc:
                if (isChecked) {
                    Toast.makeText(getActivity(), "渠道sc", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zdzs_termcheck_black:
            case R.id.zdzs_termcheck_puhuo:
            case R.id.zdzs_termcheck_youxiao:
            case R.id.zdzs_termcheck_xiaoshou:
                if (isChecked) {
                    prolv_ll.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.rb_check:// 铺货状态
                if (isChecked) {
                    // prolv_tv.setText("收起");
                    // prolv_ll.setVisibility(View.VISIBLE);
                    mZdzs_termcheck_checkid.setVisibility(View.VISIBLE);
                    proname_lv.setText("");
                    proname_lv.setTag("");
                    checkStc.setCheckprotype("1");
                    ll_proprice1.setVisibility(View.GONE);
                    ll_proprice2.setVisibility(View.GONE);

                    // Toast.makeText(getActivity(),"铺货状态",Toast.LENGTH_SHORT).show();
                    proLst = addInvocingService.getProList();
                    ProLvAdapter lvAdapter = new ProLvAdapter(getActivity(), proLst);
                    termcheck_pro_lv.setAdapter(lvAdapter);
                    ViewUtil.setListViewHeight(termcheck_pro_lv);

                    termcheck_pro_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Toast.makeText(getActivity(), "点击了" + proLst.get(position).getValue() , Toast.LENGTH_SHORT).show();

                            proname_lv.setText(proLst.get(position).getValue());
                            proname_lv.setTag(proLst.get(position).getKey());
                            String pro = prolv_tv.getText().toString();
                            if ("收起".equals(pro)) {
                                prolv_tv.setText("打开");
                                prolv_ll.setVisibility(View.GONE);
                            } else {
                                prolv_tv.setText("收起");
                                prolv_ll.setVisibility(View.VISIBLE);
                            }

                            ll_proprice1.setVisibility(View.VISIBLE);
                            ll_proprice2.setVisibility(View.VISIBLE);

                        }
                    });
                }

                break;
            case R.id.rb_price:// 价格体系
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    mZdzs_termcheck_checkid.setVisibility(View.GONE);
                    zdzs_termcheck_black.setChecked(false);
                    ;
                    zdzs_termcheck_puhuo.setChecked(false);
                    zdzs_termcheck_youxiao.setChecked(false);
                    zdzs_termcheck_xiaoshou.setChecked(false);
                    proname_lv.setText("");
                    proname_lv.setTag("");
                    checkStc.setCheckprotype("2");

                    ll_proprice1.setVisibility(View.GONE);
                    ll_proprice2.setVisibility(View.GONE);

                    // Toast.makeText(getActivity(),"价格体系",Toast.LENGTH_SHORT).show();
                    proLst = addInvocingService.getProList();
                    ProLvAdapter lvAdapter = new ProLvAdapter(getActivity(), proLst);
                    termcheck_pro_lv.setAdapter(lvAdapter);
                    ViewUtil.setListViewHeight(termcheck_pro_lv);

                    termcheck_pro_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Toast.makeText(getActivity(), "点击了" + proLst.get(position).getValue() , Toast.LENGTH_SHORT).show();

                            proname_lv.setText(proLst.get(position).getValue());
                            proname_lv.setTag(proLst.get(position).getKey());
                            String pro = prolv_tv.getText().toString();
                            if ("收起".equals(pro)) {
                                prolv_tv.setText("打开");
                                prolv_ll.setVisibility(View.GONE);
                            } else {
                                prolv_tv.setText("收起");
                                prolv_ll.setVisibility(View.VISIBLE);
                            }

                            ll_proprice1.setVisibility(View.VISIBLE);
                            ll_proprice2.setVisibility(View.VISIBLE);

                        }
                    });
                }

                break;
            case R.id.rb_cmppro:// 竞品信息
                if (isChecked) {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
                    mZdzs_termcheck_checkid.setVisibility(View.GONE);
                    zdzs_termcheck_black.setChecked(false);
                    ;
                    zdzs_termcheck_puhuo.setChecked(false);
                    zdzs_termcheck_youxiao.setChecked(false);
                    zdzs_termcheck_xiaoshou.setChecked(false);
                    proname_lv.setText("");
                    proname_lv.setTag("");
                    checkStc.setCheckprotype("3");

                    ll_proprice1.setVisibility(View.GONE);
                    ll_proprice2.setVisibility(View.GONE);

                    // Toast.makeText(getActivity(),"竞品信息",Toast.LENGTH_SHORT).show();
                    agencyLst = xtAddChatVieService.getAgencyVie();
                    lvAdapter = new AgengcyLvAdapter(getActivity(), agencyLst, termcheck_pro_lv, prolv_tv, prolv_ll,
                            proname_lv,ll_proprice1,ll_proprice2);
                    termcheck_pro_lv.setAdapter(lvAdapter);
                    ViewUtil.setListViewHeight(termcheck_pro_lv);

                    /*termcheck_pro_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            KvStc bean = agencyLst.get(position);
                            if("1".equals(bean.getIsDefault())){
                                bean.setIsDefault("0");
                            }else{
                                bean.setIsDefault("1");
                            }
                            lvAdapter.notifyDataSetChanged();
                            ViewUtil.setListViewHeight(termcheck_pro_lv);


                        }
                    });*/

                }
                break;

            default:
                break;
        }
    }

    AgengcyLvAdapter lvAdapter;

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.zdzs_termcheck_rl_sure:// 确定按钮
                //sellTag.toString();
                startrow = 0;// 开始行
                endrow = pagercount;// 结束行
                getTermList();
                break;
            case R.id.top_navigation_tv_check:// 配置模板
                changeHomeFragment(new ZsTemplateFragment(), "xttermcartfragment");
                break;
            case R.id.xtbf_termcheck_lou:// 筛选终端
                Toast.makeText(getActivity(), "请筛选终端", Toast.LENGTH_SHORT).show();
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
            case R.id.zdzs_termcheck_get06:// 产品信息 收起
                String pro = prolv_tv.getText().toString();
                if ("收起".equals(pro)) {
                    prolv_tv.setText("打开");
                    prolv_ll.setVisibility(View.GONE);
                } else {
                    prolv_tv.setText("收起");
                    prolv_ll.setVisibility(View.VISIBLE);
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
            case R.id.xtbf_termselect_bt_add:// 全部添加
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
            case R.id.xtbf_termselect_bt_search:// 查询
                Toast.makeText(getActivity(), "未查到终端,请到相关路线下寻找", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    // 发起请求
    private void getTermList() {
        if (mZdzs_termcheck_mineshop.isChecked()) {
            checkStc.setMineshop("Y");
        }
        if (mZdzs_termcheck_minearea.isChecked()) {
            checkStc.setMinearea("Y");
        }
        if (mZdzs_termcheck_cmparea.isChecked()) {
            checkStc.setCmparea("Y");
        }
        if (mZdzs_termcheck_minetreaty.isChecked()) {
            checkStc.setMinetreaty("Y");
        }
        if (mZdzs_termcheck_cmptreaty.isChecked()) {
            checkStc.setCmptreaty("Y");
        }
        if (mZdzs_termcheck_termone.isChecked()) {
            checkStc.setTermone("Y");
        }
        if (mZdzs_termcheck_termtwo.isChecked()) {
            checkStc.setTermtwo("Y");
        }
        if (mZdzs_termcheck_termthree.isChecked()) {
            checkStc.setTermthree("Y");
        }
        if (mZdzs_termcheck_termfour.isChecked()) {
            checkStc.setTermfour("Y");
        }
        if (mZdzs_termcheck_termcq.isChecked()) {
            checkStc.setTermcq("Y");
        }
        if (mZdzs_termcheck_termxz.isChecked()) {
            checkStc.setTermxz("Y");
        }
        if (mZdzs_termcheck_termcj.isChecked()) {
            checkStc.setTermcj("Y");
        }
        if (mZdzs_termcheck_termddb.isChecked()) {
            checkStc.setTermddb("Y");
        }
        if (mZdzs_termcheck_checkzh.isChecked()) {
            checkStc.setCheckzh("Y");
        }
        if (mZdzs_termcheck_checkhz.isChecked()) {
            checkStc.setCheckhz("Y");
        }
        if (mZdzs_termcheck_checkgao.isChecked()) {
            checkStc.setCheckgao("Y");
        }


        if (zdzs_termcheck_black.isChecked()) {
            checkStc.setBlack("Y");
        }
        if (zdzs_termcheck_puhuo.isChecked()) {
            checkStc.setPuhuo("Y");
        }
        if (zdzs_termcheck_youxiao.isChecked()) {
            checkStc.setYouxiao("Y");
        }
        if (zdzs_termcheck_xiaoshou.isChecked()) {
            checkStc.setXiaoshou("Y");
        }

        checkStc.setTermaddress(mZdzs_termcheck_termaddress.getText().toString());
        checkStc.setTermcycle(mZdzs_termcheck_termcycle.getText().toString());
        checkStc.setHlow(mZdzs_termcheck_hlow.getText().toString());
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
        checkStc.setLsjnum(mZdzs_termcheck_lsjnum.getText().toString());


        checkStc.setSell(sellTag);
        checkStc.setProkey((String) proname_lv.getTag());

        String json = JsonUtil.toJson(checkStc);
        getTermDataByUrl("opt_get_dates2", "MST_TERMINALINFO_M", json);
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
                            if ("opt_get_dates2".equals(optcode) && "MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                parseTermListJson(formjson, 0);// 0:不需要  1需要删除
                                initTermListData("");
                                setSelectTerm();// 设置已添加购物车的符号
                                selectAdapter.notifyDataSetChanged();
                                // termRouteLv.refreshComplete();
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

    // 解析路线key下的终端 what 是否需要清除该表,再插入  0:不需要  1需要删除
    private void parseTermListJson(String json, int what) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        if ("".equals(MST_TERMINALINFO_M) || TextUtils.isEmpty(MST_TERMINALINFO_M)) {// 返回数据为空
            startrow = -1;
            endrow = -1;
        } else {
            MainService service = new MainService(getActivity(), null);
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class, what);
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
        SoftReference<ZsTermCheckFragment> fragmentRef;

        public MyHandler(ZsTermCheckFragment fragment) {
            fragmentRef = new SoftReference<ZsTermCheckFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsTermCheckFragment fragment = fragmentRef.get();
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
}
