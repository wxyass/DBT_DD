package dd.tsingtaopad.dd.ddzs.zsterm.zsselect;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.adapter.DayDetailSelectKeyValueAdapter;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.business.visit.bean.AreaGridRoute;
import dd.tsingtaopad.core.net.HttpUrl;
import dd.tsingtaopad.core.net.RestClient;
import dd.tsingtaopad.core.net.callback.IError;
import dd.tsingtaopad.core.net.callback.IFailure;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.domain.RequestHeadStc;
import dd.tsingtaopad.core.net.domain.RequestStructBean;
import dd.tsingtaopad.core.net.domain.ResponseStructBean;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnDismissListener;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.core.view.dropdownmenu.DropBean;
import dd.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import dd.tsingtaopad.core.view.refresh.UltraRefreshListView;
import dd.tsingtaopad.core.view.refresh.UltraRefreshListener;
import dd.tsingtaopad.db.table.MitValcheckterM;
import dd.tsingtaopad.db.table.MitValterM;
import dd.tsingtaopad.db.table.MstGridM;
import dd.tsingtaopad.db.table.MstMarketareaM;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.db.table.MstTerminalinfoM;
import dd.tsingtaopad.db.table.MstVisitM;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtAddInvocingService;
import dd.tsingtaopad.dd.ddxt.term.select.IXtTermSelectClick;
import dd.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import dd.tsingtaopad.dd.ddxt.term.select.adapter.XtTermSelectAdapter;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;
import dd.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import dd.tsingtaopad.dd.ddzs.zsterm.zscart.ZsTermCartFragment;
import dd.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.TermSpecialStc;
import dd.tsingtaopad.home.app.MainService;
import dd.tsingtaopad.home.app.MyApplication;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.util.requestHeadUtil;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 专项追溯 用于终端筛选
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsTermSpecialFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final String TAG = "ZsTermSpecialFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    private DropdownButton areaBtn;
    private DropdownButton gridBtn;
    private DropdownButton routeBtn;
    private List<DropBean> areaList;
    private List<DropBean> gridList;
    private List<DropBean> routeList;
    private List<XtTermSelectMStc> termList;


    private LinearLayout termRouteLl;
    private PtrClassicFrameLayout mPtrFrame;
    private UltraRefreshListView termRouteLv;
    private EditText termNameEt;
    private Button searchBtn;
    private RelativeLayout mineBtn;
    private RelativeLayout vieBtn;
    private TextView mine_con1;
    private TextView vie_con1;
    private CheckBox agreeRb;
    private Button termsearchBtn;
    private Button addAllTermBtn;

    private XtTermSelectMStc xtTermSelectMStc;
    private List<MitValcheckterM> mitValcheckterMs;

    private XtTermSelectService xtSelectService;
    private XtAddInvocingService addInvocingService;
    private TermSpecialStc specialStc;
    // MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(xtselect.getTerminalkey());
    //private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据
    private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据

    private int TOFRAGMENT = 1;
    private int TOACTIVITY = 2;


    private AlertView mAlertViewExt;//窗口拓展

    private String areaKey ="";
    private String gridKey="";
    private String routeKey="";

    private int pager = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_termspecial, container, false);
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

        areaBtn = (DropdownButton) view.findViewById(R.id.xtbf_termspecial_area);
        gridBtn = (DropdownButton) view.findViewById(R.id.xtbf_termspecial_grid);
        routeBtn = (DropdownButton) view.findViewById(R.id.xtbf_termspecial_route);
        searchBtn = (Button) view.findViewById(R.id.xtbf_termspecial_bt_search);
         termNameEt = (EditText) view.findViewById(R.id.xtbf_termspecial_et_search);

        addAllTermBtn = (Button) view.findViewById(R.id.xtbf_termspecial_bt_add);
        termRouteLl = (LinearLayout) view.findViewById(R.id.xtbf_termspecial_ll_lv);
        mPtrFrame = ((PtrClassicFrameLayout) view.findViewById(R.id.ultra_ptr));
        termRouteLv = (UltraRefreshListView) view.findViewById(R.id.xtbf_termspecial_lv);

        mineBtn = (RelativeLayout) view.findViewById(R.id.xtbf_termspecial_mine);
        vieBtn = (RelativeLayout) view.findViewById(R.id.xtbf_termspecial_vie);
        mine_con1 = (TextView) view.findViewById(R.id.xtbf_termspecial_mine_con1);
        vie_con1 = (TextView) view.findViewById(R.id.xtbf_termspecial_vie_con1);
        agreeRb = (CheckBox) view.findViewById(R.id.xtbf_termspecial_agree);
        termsearchBtn = (Button) view.findViewById(R.id.xtbf_termspecial_search);


        mPtrFrame.setPtrHandler(termRouteLv);
        addAllTermBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mineBtn.setOnClickListener(this);
        vieBtn.setOnClickListener(this);
        termsearchBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText(R.string.zdzs_selectterm);
        confirmTv.setText("确定");
        handler = new MyHandler(this);
        ConstValues.handler = handler;

        xtSelectService = new XtTermSelectService(getActivity());
        addInvocingService = new XtAddInvocingService(getActivity(),null);
         specialStc = new TermSpecialStc();

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
        if(!DateUtil.getDateTimeStr(7).equals(addTime)){
            selectedList.clear();
            // 终端夹隔天清零
            xtSelectService.deleteCartData("MST_TERMINALINFO_M_ZSCART", "2");
        }

        setSelectTerm();// 设置已添加购物车的符号

        // 设置终端条目适配器,及条目点击事件
        // setItemAdapterListener();
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

        // // 获取追溯模板 大区id
        String areapid = PrefUtils.getString(getActivity(), "departmentid", "");
        mitValcheckterMs = xtSelectService.getValCheckterMList(areapid);
        //mitValcheckterM = mitValcheckterMs.get(0);


        if (selectedList.size() > 0) {
            confirmTv.setText("确定" + "(" + selectedList.size() + ")");
        }


        //设置上拉下拉的数据刷新回调接口
        termRouteLv.setUltraRefreshListener(new UltraRefreshListener() {
            // 上拉加载
            @Override
            public void addMore() {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 获取新数据
                        getTermData();
                        // 关闭上拉加载的动画
                        //stopResh();
                        //termRouteLv.refreshComplete();


                    }
                },1000);
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
                },1000);
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
                /*String content = "{routekey:'" + routeKey + "'," + "tablename:'MST_TERMINALINFO_M'" + "}";
                getDataByHttp("opt_get_dates2", "MST_TERMINALINFO_M", content);
                PrefUtils.putString(getActivity(), GlobalValues.ROUNTE_TIME, DateUtil.getDateTimeStr(7));*/
            }
        });
    }

    // 设置终端列表数据 假数据
    private void initTermListData(String routekey) {

        // 绑定TermList数据
        List<String> routes = new ArrayList<String>();
        routes.add(routekey);// 不同路线
        termList.clear();
        termList.addAll(xtSelectService.queryZsTerminal(routes));
        // termList = xtSelectService.queryZsTerminal(routes);
    }

    private XtTermSelectAdapter selectAdapter;

    //  设置终端条目适配器,及条目点击事件
    private void setItemAdapterListener1() {
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
        List<MstMarketareaM> valueLst = xtSelectService.getMstMarketareaMList(
                PrefUtils.getString(getActivity(), "departmentid", ""));
        for (MstMarketareaM mstMarketareaM : valueLst) {
            areaList.add(new DropBean(mstMarketareaM.getAreaname(), mstMarketareaM.getAreaid()));
        }

        gridList.add(new DropBean("请选择定格"));
        routeList.add(new DropBean("请选择路线"));
    }

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定
                if (mitValcheckterMs.size() > 0) {// 配置了督导模板
                    // 生成临时表,跳转终端购物车
                    breakNextLayout(TOFRAGMENT, selectedList);
                    PrefUtils.putString(getActivity(), GlobalValues.DDXTZS, "2");
                } else {
                    Toast.makeText(getActivity(), "请先联系文员,配置督导模板", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xtbf_termspecial_bt_add:// 全部添加
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
            case R.id.xtbf_termspecial_bt_search:// 查询
                Toast.makeText(getActivity(),"未查到终端,请到相关路线下寻找",Toast.LENGTH_SHORT).show();
                break;
            case R.id.xtbf_termspecial_mine:// 我品
                alertZsShow3();
                break;
            case R.id.xtbf_termspecial_vie:// 竞品
                alertZsShow4();
                break;
            case R.id.xtbf_termspecial_search:// 查询
                pager = 0;
                getTermData();
                break;
            default:
                break;
        }
    }

    // 获取最新的终端数据
    private void getTermData() {
        if(agreeRb.isChecked()){
            specialStc.setIsagree("1");
        }else{
            specialStc.setIsagree("0");
        }
        specialStc.setAreakey(areaKey);
        specialStc.setGridkey(gridKey);
        specialStc.setRoutekey(routeKey);
        specialStc.setTablename("'MST_TERMINALINFO_M");
        specialStc.setPager(pager);
        pager++;
        specialStc.setCount(100);
        specialStc.setTermname(termNameEt.getText().toString());

        String json = JsonUtil.toJson(specialStc);
        // String json = "{routekey:'" + routeKey + "'," + "tablename:'MST_TERMINALINFO_M'" + "}";

        getTermDataByUrl("opt_get_dates2", "MST_TERMINALINFO_M", json);

    }

    // 我品
    public void alertZsShow3() {

        final List<KvStc> typeLst  = addInvocingService.getProList();

        // 如果追溯项大于0,添加全选按钮
        if(typeLst.size()>0){
            typeLst.add(0, new KvStc("-1", "全选", "-1"));
        }

        // 加载视图
        View extView = LayoutInflater.from(getActivity()).inflate(R.layout.alert_daydetail_form, null);

        final ListView dataLv = (ListView) extView.findViewById(R.id.alert_daydetal_lv);
        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");


        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        if(!TextUtils.isEmpty(specialStc.getMineprokey())){
            selectedId = Arrays.asList(specialStc.getMineprokey().split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : typeLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }

        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(getActivity(),typeLst,
                new String[]{"key","value"}, null);
        dataLv.setAdapter(sadapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int posi, long arg3) {
                CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String checkkey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String checkname = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                if(0 == posi){// 全选
                    if(itemCB.isChecked()){// 是选中状态
                        StringBuffer key = new StringBuffer();
                        StringBuffer name = new StringBuffer();
                        for (KvStc stc : typeLst){
                            if(!"-1".equals(stc.getParentKey())){
                                key.append(stc.getKey());
                                key.append(",");
                                name.append(stc.getValue());
                                name.append(",");
                            }
                            stc.setIsDefault("1");
                        }
                        specialStc.setMineprokey(key.toString());
                        specialStc.setMineproname(name.toString());
                    }else{// 是未选中状态
                        for (KvStc stc : typeLst){
                            stc.setIsDefault("0");
                        }
                        specialStc.setMineprokey("");
                        specialStc.setMineproname("");
                    }
                }else{
                    if (itemCB.isChecked()) {
                        specialStc.setMineprokey(FunUtil.isBlankOrNullTo(specialStc.getMineprokey(),"")  + checkkey);
                        specialStc.setMineproname(FunUtil.isBlankOrNullTo(specialStc.getMineproname(),"") + checkname);
                        ((KvStc)typeLst.get(posi)).setIsDefault("1");
                    } else {
                        specialStc.setMineprokey(FunUtil.isBlankOrNullTo(specialStc.getMineprokey(),"") .replace(checkkey, ""));
                        specialStc.setMineproname(FunUtil.isBlankOrNullTo(specialStc.getMineproname(),"").replace(checkname, ""));
                        ((KvStc)typeLst.get(posi)).setIsDefault("0");
                        ((KvStc)typeLst.get(0)).setIsDefault("0");
                    }
                }

                mine_con1.setText(specialStc.getMineproname());

                sadapter.notifyDataSetChanged();
            }
        });


        // 显示对话框
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(extView, 0, 0, 0, 0);
        dialog.setCancelable(true);
        dialog.show();


        // 确定
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });
    }
    // 竞品
    public void alertZsShow4() {

        final List<KvStc> typeLst  = addInvocingService.getVieProList();

        // 如果追溯项大于0,添加全选按钮
        if(typeLst.size()>0){
            typeLst.add(0, new KvStc("-1", "全选", "-1"));
        }

        // 加载视图
        View extView = LayoutInflater.from(getActivity()).inflate(R.layout.alert_daydetail_form, null);

        final ListView dataLv = (ListView) extView.findViewById(R.id.alert_daydetal_lv);
        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择竞品");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");


        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        if(!TextUtils.isEmpty(specialStc.getMineprokey())){
            selectedId = Arrays.asList(specialStc.getMineprokey().split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : typeLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }

        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(getActivity(),typeLst,
                new String[]{"key","value"}, null);
        dataLv.setAdapter(sadapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int posi, long arg3) {
                CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String checkkey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String checkname = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                if(0 == posi){// 全选
                    if(itemCB.isChecked()){// 是选中状态
                        StringBuffer key = new StringBuffer();
                        StringBuffer name = new StringBuffer();
                        for (KvStc stc : typeLst){
                            if(!"-1".equals(stc.getParentKey())){
                                key.append(stc.getKey());
                                key.append(",");
                                name.append(stc.getValue());
                                name.append(",");
                            }
                            stc.setIsDefault("1");
                        }
                        specialStc.setVieprokey(key.toString());
                        specialStc.setVieproname(name.toString());
                    }else{// 是未选中状态
                        for (KvStc stc : typeLst){
                            stc.setIsDefault("0");
                        }
                        specialStc.setVieprokey("");
                        specialStc.setVieproname("");
                    }
                }else{
                    if (itemCB.isChecked()) {
                        specialStc.setVieprokey(FunUtil.isBlankOrNullTo(specialStc.getVieprokey(),"")  + checkkey);
                        specialStc.setVieproname(FunUtil.isBlankOrNullTo(specialStc.getVieproname(),"") + checkname);
                        ((KvStc)typeLst.get(posi)).setIsDefault("1");
                    } else {
                        specialStc.setVieprokey(FunUtil.isBlankOrNullTo(specialStc.getVieprokey(),"") .replace(checkkey, ""));
                        specialStc.setVieproname(FunUtil.isBlankOrNullTo(specialStc.getVieproname(),"").replace(checkname, ""));
                        ((KvStc)typeLst.get(posi)).setIsDefault("0");
                        ((KvStc)typeLst.get(0)).setIsDefault("0");
                    }
                }

                vie_con1.setText(specialStc.getVieproname());

                sadapter.notifyDataSetChanged();
            }
        });


        // 显示对话框
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(extView, 0, 0, 0, 0);
        dialog.setCancelable(true);
        dialog.show();


        // 确定
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });
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
            Toast.makeText(getActivity(), "请先联系文员,配置督导模板", Toast.LENGTH_SHORT).show();
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
        if(term!=null){
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

                            // 请求某个终端 上次拜访详情
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
                            //setItemAdapterListener();
                            selectAdapter.notifyDataSetChanged();
                        } else if (1 == position) {
                            DbtLog.logUtils(TAG, "前往拜访：上传");
                            // 如果网络可用
                            if (NetStatusUtil.isNetValid(getActivity())) {
                                xtUploadService.upload_zs_visit(false, mitValterM.getId(), 1,0);
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
     * 请求某个终端 上次拜访详情
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

    /**
     * 根据条件请求终端
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
                                parseTermListJson(formjson,0);// 0:不需要  1需要删除
                                initTermListData("");
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

    // 解析路线key下的终端
    private void parseTermListJson(String json) {
        parseTermListJson(json,1);// what 是否需要清除该表,再插入  0:不需要  1需要删除
    }

    // 解析路线key下的终端 what 是否需要清除该表,再插入  0:不需要  1需要删除
    private void parseTermListJson(String json,int what) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class,what);

        /*initTermListData(routeKey);
        setSelectTerm();// 设置已添加购物车的符号
        setItemAdapterListener();*/
    }


    // 跳转巡店拜访
    private void startXtVisitShopActivity() {
        List<MstVisitM> mstVisitMS = xtSelectService.getMstVisitMList(xtTermSelectMStc.getTerminalkey());
        if(mstVisitMS.size()>0){
            Intent intent = new Intent(getActivity(), ZsVisitShopActivity.class);
            intent.putExtra("isFirstVisit", "1");// 非第一次拜访1
            intent.putExtra("termStc", xtTermSelectMStc);
            intent.putExtra("mitValcheckterM", mitValcheckterMs.get(0));
            intent.putExtra("seeFlag", "0"); // 0拜访 1查看标识
            startActivity(intent);

            Toast.makeText(getActivity(), "该终端数据请求成功", Toast.LENGTH_SHORT).show();

            //PrefUtils.putBoolean(getActivity(),GlobalValues.ZS_CART_SYNC,false);// false 追溯购物车 需要同步
        }else{
            //
            Toast.makeText(getActivity(),"该终端从未拜访,不能追溯",Toast.LENGTH_SHORT).show();
        }

    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsTermSpecialFragment> fragmentRef;

        public MyHandler(ZsTermSpecialFragment fragment) {
            fragmentRef = new SoftReference<ZsTermSpecialFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsTermSpecialFragment fragment = fragmentRef.get();
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
        //setItemAdapterListener();// 适配器处理
        selectAdapter.notifyDataSetChanged();
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
