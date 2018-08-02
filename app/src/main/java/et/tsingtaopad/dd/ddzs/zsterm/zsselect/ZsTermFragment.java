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
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter.ProCheckLvAdapter;
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
 * 终端列表
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsTermFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener
         {

    private final String TAG = "ZsTermFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private TextView checkTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    private DropdownButton areaBtn;
    private DropdownButton gridBtn;
    private DropdownButton routeBtn;
    private RelativeLayout termcheck_lou;
    private Button termcheck_louterm;
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

    private XtTermSelectService xtSelectService;
    ;
    private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前adapter的数据

    private int TOFRAGMENT = 1;
    private int TOACTIVITY = 2;

    private AlertView mAlertViewExt;//窗口拓展
    TermCheckStc checkStc;

    private int startrow = -1;// 开始行
    private int endrow = -1;// 结束行
    private int pagercount = 50;// 每次请求的个数

    private String areaKey = "";
    private String gridKey = "";
    private String routeKey = "";

    private PtrClassicFrameLayout mPtrFrame;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_term, container, false);
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
        checkTv.setVisibility(View.INVISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        checkTv.setOnClickListener(this);

        // 区域,定格,路线
        areaBtn = (DropdownButton) view.findViewById(R.id.zdzs_term_area);
        gridBtn = (DropdownButton) view.findViewById(R.id.zdzs_term_grid);
        routeBtn = (DropdownButton) view.findViewById(R.id.zdzs_term_route);
        // 筛选按钮
        termcheck_lou = (RelativeLayout) view.findViewById(R.id.zdzs_term_lou);
        termcheck_louterm = (Button) view.findViewById(R.id.zdzs_term_bt_louterm);
        // 搜索 放大镜按钮
        searchEt = (EditText) view.findViewById(R.id.zdzs_term_et_search);
        searchBtn = (Button) view.findViewById(R.id.zdzs_term_bt_search);
        // 全部添加
        addAllTermBtn = (Button) view.findViewById(R.id.zdzs_term_bt_add);
        // 全部删除
        removeAllTermBtn = (Button) view.findViewById(R.id.zdzs_term_bt_remove);
        // 终端列表
        termRouteLl = (LinearLayout) view.findViewById(R.id.zdzs_term_ll_lv);
        termRouteLv = (UltraRefreshListView) view.findViewById(R.id.zdzs_term_lv);
        addAllTermBtn.setOnClickListener(this);
        removeAllTermBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        termcheck_lou.setOnClickListener(this);
        termcheck_louterm.setOnClickListener(this);

        // 上拉加载
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.zdzs_term_drawerlayout);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.zdzs_term_ptr);
        mPtrFrame.setPtrHandler(termRouteLv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText(R.string.zdzs_selectterm);
        confirmTv.setText("确定");
        handler = new MyHandler(this);
        ConstValues.handler = handler;

        xtSelectService = new XtTermSelectService(getActivity());


        // 获取传递过来的数据
        Bundle bundle = getArguments();
        // checkStc = new TermCheckStc();
        checkStc = (TermCheckStc)bundle.getSerializable("checkStc");

        // 每次进来 设置发起请求的从0条开始 第一次筛选 先清空Mst_terminal_m表 因为若是已经选了路线了,这张表就会有数据了,展示的筛选结果就不对了
        xtSelectService.deleteMst_terminal_m();
        startrow = 0;// 开始行
        endrow = pagercount;// 结束行

        // 三个下拉按钮的数据 以及点击监听 (区域定格路线 )
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

        // 获取追溯模板 大区id
        String areapid = PrefUtils.getString(getActivity(), "departmentid", "");
        mitValcheckterMs = xtSelectService.getValCheckterMList(areapid);

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

        // 获取第一页数据
        getTermList();
    }

    // 三个下拉按钮的点击监听
    private void setDropdownItemSelectListener() {

        // 预先设置3个下拉按钮的假数据
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


        // 赋值
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
                    gridKey = "";
                    routeKey = "";
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
                    routeKey = "";
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
                routeKey = routeList.get(Postion).getKey();

                // 请求路线下的所有终端
                /*String content = "{routekey:'" + routeKey + "'," + "tablename:'MST_TERMINALINFO_M'" + "}";
                getDataByHttp("opt_get_dates2", "MST_TERMINALINFO_M", content);
                PrefUtils.putString(getActivity(), GlobalValues.ROUNTE_TIME, DateUtil.getDateTimeStr(7));*/
            }
        });
    }

    // 设置终端列表数据
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
                boolean isselect = false;
                for (XtTermSelectMStc xtTermSelectMStc : selectedList) {// 遍历终端文件夹 是否有这个终端
                    if (xtTermSelectMStc.getTerminalkey().equals(item.getTerminalkey())) {
                        isselect = true;
                        selectedList.remove(xtTermSelectMStc);
                        item.setIsSelectToCart("0");
                        // imageView.setImageResource(R.drawable.icon_visit_add);
                        imageView.setImageDrawable(null);
                        confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                        break;
                    }
                }

                if (!isselect) { // 如果终端夹中没有这个终端,将其添加到终端夹中
                    selectedList.add(item);
                    item.setIsSelectToCart("1");
                    // imageView.setImageResource(R.drawable.icon_select_minus);
                    imageView.setImageResource(R.drawable.icon_visit_pitchon);
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

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;

            case R.id.top_navigation_rl_confirm:// 确定  跳转终端购物车
                if (mitValcheckterMs.size() > 0) {// 配置了督导模板
                    // 生成临时表,跳转终端购物车
                    breakNextLayout(TOFRAGMENT, selectedList);
                    PrefUtils.putString(getActivity(), GlobalValues.DDXTZS, "2");
                } else {
                    Toast.makeText(getActivity(), "请先配置督导模板", Toast.LENGTH_SHORT).show();
                    changeHomeFragment(new ZsTemplateFragment(), "zstemplatefragment");
                }
                break;

            case R.id.xtbf_termcheck_bt_search:// 放大镜搜索  终端名称 模糊搜多终端
                searchTerm();
                break;

            /*case R.id.zdzs_termcheck_rl_reset:// 筛选重置按钮
                // reSetTermSelect();
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
                changeHomeFragment(new ZsTemplateFragment(), "zstemplatefragment");
                break;*/


            case R.id.zdzs_term_lou:// 筛选终端
            case R.id.zdzs_term_bt_louterm:// 筛选终端
                // 第一次筛选 先清空Mst_terminal_m表 因为若是已经选了路线了,这张表就会有数据了,展示的筛选结果就不对了
                xtSelectService.deleteMst_terminal_m();
                termList.clear();// 将展示在页面上的集合 数据删除
                startrow = 0;// 开始行
                endrow = pagercount;// 结束行
                getTermList();
                break;

            case R.id.zdzs_term_bt_add:// 全部添加
                for (XtTermSelectMStc selectMStc : termList) {
                    Iterator<XtTermSelectMStc> it = selectedList.iterator();
                    while(it.hasNext()){
                        XtTermSelectMStc x = it.next();
                        if(x.getTerminalkey().equals(selectMStc.getTerminalkey())){
                            it.remove();
                        }
                    }
                    selectMStc.setIsSelectToCart("1");
                }
                selectedList.addAll(termList);
                selectAdapter.notifyDataSetChanged();
                confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                break;
            case R.id.zdzs_term_bt_remove:// 全部删除
                for (XtTermSelectMStc selectMStc : termList) {
                    selectMStc.setIsSelectToCart("0");
                }

                selectedList.clear();
                selectAdapter.notifyDataSetChanged();
                xtSelectService.deleteData("MST_TERMINALINFO_M_ZSCART");// 清空购物车
                confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                break;
            default:
                break;
        }
    }

    // 终端名称 模糊搜多终端
    private void searchTerm() {

        String termname = searchEt.getText().toString();
        if ("".equals(termname) || TextUtils.isEmpty(termname)) {
            Toast.makeText(getActivity(), "请输入终端名称", Toast.LENGTH_SHORT).show();
        } else {
            String json = "{bigid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "', " +
                    "secid:'" + areaKey + "'," +
                    "gridid:'" + gridKey + "'," +
                    "routeid:'" + routeKey + "'," +
                    "terminalname:'" + termname + "'}";
            getTermDataByTermName("opt_terminalbyterminalname_3", "MST_TERMINALINFO_M", json);
        }
    }

    /**
     *  终端名称 模糊搜多终端   根据终端名称 条件请求终端
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

    // 发起请求 获取每一页数据
    private void getTermList() {

        // 判断是否需要发起请求
        if (startrow == -1) {

        } else {
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
        }
    }

    /**
     * 根据条件请求终端 在筛选页面将条件设好  发起请求
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
                                parseTermListJsonToTermDown(formjson);// 解析json
                                // initTermListData("");
                                // refreshTermListData();// 读取刚刚下载的终端数据,并添加到list中
                                termList.addAll(xtSelectService.queryShaixuanTerminal()); // 读取刚刚下载的终端数据,并添加到list中
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

    // listview的条目点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // 根据点击 是否勾选
        XtTermSelectMStc item = termList.get(position);
        boolean isselect = false;
        for (XtTermSelectMStc xtTermSelectMStc : selectedList) {// 遍历终端文件夹 是否有这个终端
            if (xtTermSelectMStc.getTerminalkey().equals(item.getTerminalkey())) {
                isselect = true;
                selectedList.remove(xtTermSelectMStc);
                item.setIsSelectToCart("0");
                confirmTv.setText("确定" + "(" + selectedList.size() + ")");
                break;
            }
        }

        if (!isselect) { // 如果终端夹中没有这个终端,将其添加到终端夹中
            selectedList.add(item);
            item.setIsSelectToCart("1");
            confirmTv.setText("确定" + "(" + selectedList.size() + ")");
        }

        selectAdapter.notifyDataSetChanged();
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
                changeHomeFragment(new ZsTermCartFragment(), "zstermcartfragment");
                PrefUtils.putBoolean(getActivity(), GlobalValues.ZS_CART_SYNC, false);// false 追溯购物车 需要同步
                PrefUtils.putString(getActivity(), GlobalValues.ZS_CART_TIME, DateUtil.getDateTimeStr(7));// 添加追溯文件夹时间

                Toast.makeText(getActivity(), "终端夹添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "请先添加终端", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 查找终端,并复制到终端购物车
    public void copyMstTerminalinfoMZsCart(XtTermSelectMStc termSelectMStc) {
        /*MstTerminalinfoM term = xtSelectService.findTermByTerminalkey(termSelectMStc.getTerminalkey());
        if (term != null) {
            xtSelectService.toCopyMstTerminalinfoMZsCartData(term, "2");
        }*/
        xtSelectService.toMstTerminalinfoMZsCartData(termSelectMStc, "2");
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
     * 请求路线下的所有终端  或者  获取某一家终端的上次拜访记录
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
                            termList.clear();
                            selectAdapter.notifyDataSetChanged();// 清空当前终端列表
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        termList.clear();
                        selectAdapter.notifyDataSetChanged();// 清空当前终端列表
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        termList.clear();
                        selectAdapter.notifyDataSetChanged();// 清空当前终端列表
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

    // 终端名称 模糊搜多终端  解析终端名称模糊搜索下的终端
    private void parseSearchTermListJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        if ("[]".equals(MST_TERMINALINFO_M) || TextUtils.isEmpty(MST_TERMINALINFO_M)) {// 返回数据为空
            Toast.makeText(getActivity(), "未搜索到任何终端", Toast.LENGTH_SHORT).show();
            termList.clear();
            selectAdapter.notifyDataSetChanged();// 清空当前终端列表
        } else {
            MainService service = new MainService(getActivity(), null);
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class, 1);
            initTermListData(routeKey);
            setSelectTerm();// 设置已添加购物车的符号
            setItemAdapterListener();
        }

    }

    // 解析路线key下的终端 what 是否需要清除该表,再插入  0:不需要  1需要删除
    private void parseTermListJsonToTermDown(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();
        MainService service = new MainService(getActivity(), null);

        if ("[]".equals(MST_TERMINALINFO_M) || TextUtils.isEmpty(MST_TERMINALINFO_M)) {// 返回数据为空
            startrow = -1;
            endrow = -1;
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M_DOWN", MstTerminalinfoMDown.class, 1);
        } else {
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class, 0);
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
        SoftReference<ZsTermFragment> fragmentRef;

        public MyHandler(ZsTermFragment fragment) {
            fragmentRef = new SoftReference<ZsTermFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsTermFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }


            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0://  结束上传  刷新本页面
                    fragment.shuaxinXtTermSelect(0);
                    break;
                case GlobalValues.SINGLE_UP_SUC://  上传成功
                    fragment.shuaxinXtTermSelect(1);
                    break;
                case GlobalValues.SINGLE_UP_FAIL://  上传失败
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
