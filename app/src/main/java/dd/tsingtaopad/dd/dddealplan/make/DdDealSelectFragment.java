package dd.tsingtaopad.dd.dddealplan.make;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.R;
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
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.view.dropdownmenu.DropBean;
import dd.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import dd.tsingtaopad.db.table.MitRepairM;
import dd.tsingtaopad.db.table.MitRepairterM;
import dd.tsingtaopad.db.table.MstGridM;
import dd.tsingtaopad.db.table.MstMarketareaM;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.db.table.MstTerminalinfoM;
import dd.tsingtaopad.dd.ddxt.term.select.IXtTermSelectClick;
import dd.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.home.app.MainService;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdDealSelectFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final String TAG = "DdDealPlanFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    private dd.tsingtaopad.core.view.dropdownmenu.DropdownButton areaBtn;
    private dd.tsingtaopad.core.view.dropdownmenu.DropdownButton gridBtn;
    private dd.tsingtaopad.core.view.dropdownmenu.DropdownButton routeBtn;
    private EditText select_et_search;
    private Button select_bt_search;
    private Button select_bt_update;
    private RelativeLayout select_Rl_add;
    private Button select_bt_add;
    private LinearLayout select_ll_lv;
    private ListView select_lv;

    private List<DropBean> areaList;
    private List<DropBean> gridList;
    private List<DropBean> routeList;

    private XtTermSelectService xtSelectService;

    private String routeKey;

    private List<XtTermSelectMStc> termList;

    private List<XtTermSelectMStc> selectedList = new ArrayList<XtTermSelectMStc>();// 当前选中的终端

    private MitRepairM repairM;

    /*private String areakey;// 二级区域id
    private String areakeyselect = "";// 二级区域id*/

    private String gridkey;// 进入界面 督导操作生成的定格key

    private String gridkeyselect = "";// 读取数据库,获取的定格key

    private List<MitRepairterM> mitRepairterMSelects;

    DdDealMakeFragment.MyHandler handler;

    public DdDealSelectFragment() {
    }

    @SuppressLint("ValidFragment")
    public DdDealSelectFragment(DdDealMakeFragment.MyHandler handler) {
        this.handler =handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_dealselect, container, false);
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

        areaBtn = (dd.tsingtaopad.core.view.dropdownmenu.DropdownButton) view.findViewById(R.id.zgjh_deal_select_area);
        gridBtn = (dd.tsingtaopad.core.view.dropdownmenu.DropdownButton) view.findViewById(R.id.zgjh_deal_select_grid);
        routeBtn = (dd.tsingtaopad.core.view.dropdownmenu.DropdownButton) view.findViewById(R.id.zgjh_deal_select_route);

        // 搜索
        select_et_search = (EditText) view.findViewById(R.id.zgjh_deal_select_et_search);
        select_bt_search = (Button) view.findViewById(R.id.zgjh_deal_select_bt_search);
        select_bt_update = (Button) view.findViewById(R.id.zgjh_deal_select_bt_update);

        // 全部添加
        select_Rl_add = (RelativeLayout) view.findViewById(R.id.zgjh_deal_select_Rl_add);
        select_bt_add = (Button) view.findViewById(R.id.zgjh_deal_select_bt_add);

        // 终端集合
        select_ll_lv = (LinearLayout) view.findViewById(R.id.zgjh_deal_select_ll_lv);
        select_lv = (ListView) view.findViewById(R.id.zgjh_deal_select_lv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("选择整改终端");
        confirmTv.setText("确定");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        repairM = (MitRepairM) bundle.getSerializable("repairM");// 整顿计划主表

        xtSelectService = new XtTermSelectService(getActivity());

        // 查询已选择的终端
        mitRepairterMSelects = xtSelectService.queryDealSelectTerminal(repairM.getId());
        if(mitRepairterMSelects.size()>0){
            gridkeyselect =mitRepairterMSelects.get(0).getGridkey();

            for (MitRepairterM mitRepairterM:mitRepairterMSelects) {
                XtTermSelectMStc xtTermSelectMStc = new XtTermSelectMStc();
                xtTermSelectMStc.setRoutekey(mitRepairterM.getRoutekey());// 路线key
                xtTermSelectMStc.setTerminalkey(mitRepairterM.getTerminalkey());// 终端key
                xtTermSelectMStc.setTerminalname(mitRepairterM.getTerminalname());// 终端name
                selectedList.add(xtTermSelectMStc);
            }
        }


        // 设置三个下拉按钮的假数据
        initSomeData();

        // 三个下拉按钮的点击监听
        setDropdownItemSelectListener();

        // 设置终端列表数据 假数据
        // initTermListData("1-63UNEX");

        // 设置终端条目适配器,及条目点击事件
        setItemAdapterListener();

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

                    /*selectedList.clear();
                    confirmTv.setText("确定");
                    // 终端列表显示,之后放到下拉选择后显示
                    select_ll_lv.setVisibility(View.INVISIBLE);*/
                    select_ll_lv.setVisibility(View.INVISIBLE);
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
                    gridkey = gridList.get(Postion).getKey();
                    List<MstRouteM> valueLst = xtSelectService.getMstRouteMList(gridkey);
                    for (MstRouteM mstRouteM : valueLst) {
                        routeList.add(new DropBean(mstRouteM.getRoutename(), mstRouteM.getRoutekey()));
                    }
                    routeBtn.setData(routeList);

                    if(!gridkeyselect.equals(gridkey)){// 当选中的定格key与原先的定格key不相等时
                        selectedList.clear();
                        confirmTv.setText("确定");
                        // 终端列表显示,之后放到下拉选择后显示
                        select_ll_lv.setVisibility(View.INVISIBLE);
                    }
                    gridkeyselect = gridkey;
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

    DdDealSelectAdapter selectAdapter;

    //  设置终端条目适配器,及条目点击事件
    private void setItemAdapterListener() {
        // 设置适配器 加号按钮点击事件
        selectAdapter = new DdDealSelectAdapter(getActivity(), termList, termList, confirmTv, null, new IXtTermSelectClick() {
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
        select_lv.setAdapter(selectAdapter);
        //termRouteLv.setSelector(R.color.bg_content_color_gray);
        // 条目点击事件
        select_lv.setOnItemClickListener(this);
        // 终端列表显示,之后放到下拉选择后显示
        select_ll_lv.setVisibility(View.VISIBLE);
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
                            if ("MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                parseTermListJson(formjson);
                                // Toast.makeText(getActivity(), "路线下终端列表请求成功", Toast.LENGTH_SHORT).show();
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

    // 设置 终端列表数据(请求该条路线后 展示)
    private void initTermListData(String routekey) {

        termList = new ArrayList<XtTermSelectMStc>();
        // 绑定TermList数据
        List<String> routes = new ArrayList<String>();
        routes.add(routekey);// 不同路线
        termList.clear();
        termList = xtSelectService.queryTerminal(routes);
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
                saveValue();
                break;
            default:
                break;
        }
    }

    // 保存终端数据
    private void saveValue() {
        if("".equals(routeKey)|| TextUtils.isEmpty(routeKey)){//
            Toast.makeText(getActivity(),"至少选择一条路线",Toast.LENGTH_SHORT).show();
        }else{
            // 保存到数据库中
            xtSelectService.saveMitRepairterM(repairM,gridkey,selectedList,routeKey);
            handler.sendEmptyMessage(DdDealMakeFragment.MAKEPLAN_UP_SUC);
            supportFragmentManager.popBackStack();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //xtTermSelectMStc = termList.get(position);

        // Toast.makeText(getActivity(), termList.get(position).getTerminalname(), Toast.LENGTH_SHORT).show();

        // 检测条数是否已上传  // 该终端协同数据是否全部上传
        /*List<MitVisitM> terminalList = xtSelectService.getXtMitValterM(xtTermSelectMStc.getTerminalkey());
        if(terminalList.size()>0){// 未上传,弹窗上传
            deleteOrXtUplad(terminalList.get(0));
        }else{// 已上传 去拜访
            // 弹出提示 是否拜访这家终端
            confirmXtUplad(xtTermSelectMStc);// 拜访
        }*/
    }

}
