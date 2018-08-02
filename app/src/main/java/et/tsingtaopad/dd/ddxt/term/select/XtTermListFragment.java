package et.tsingtaopad.dd.ddxt.term.select;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
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
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.core.view.dropdownmenu.DropBean;
import et.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import et.tsingtaopad.db.table.MitVisitM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.dd.ddxt.shopvisit.XtVisitShopActivity;
import et.tsingtaopad.dd.ddxt.term.cart.XtTermCartFragment;
import et.tsingtaopad.dd.ddxt.term.select.adapter.XtTermSelectAdapter;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class XtTermListFragment extends BaseFragmentSupport implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final String TAG = "XtTermSelectFragment";

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
    private ListView termRouteLv;
    private EditText searchEt;
    private Button searchBtn;

    private XtTermSelectMStc xtTermSelectMStc;

    private XtTermSelectService xtSelectService;

    private AlertView mAlertViewExt;//窗口拓展

    private String areaKey = "";
    private String gridKey = "";
    private String routeKey = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_termlist, container, false);
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

        areaBtn = (DropdownButton) view.findViewById(R.id.xtbf_termlist_area);
        gridBtn = (DropdownButton) view.findViewById(R.id.xtbf_termlist_grid);
        routeBtn = (DropdownButton) view.findViewById(R.id.xtbf_termlist_route);
        searchEt = (EditText) view.findViewById(R.id.xtbf_termlist_et_search);
        searchBtn = (Button) view.findViewById(R.id.xtbf_termlist_bt_search);
        termRouteLl = (LinearLayout) view.findViewById(R.id.xtbf_termlist_ll_lv);
        termRouteLv = (ListView) view.findViewById(R.id.xtbf_termlist_lv);
        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText(R.string.xtbf_selectterm);
        confirmTv.setText("确定");
        handler = new MyHandler(this);
        ConstValues.handler = handler;

        xtSelectService = new XtTermSelectService(getActivity());

        // 菜单设置数据  设置区域数据  三个下拉按钮的点击监听
        setDropdownItemSelectListener();

        termList = new ArrayList<XtTermSelectMStc>();

        // 设置终端列表数据 假数据
        // initTermListData("");


        // 终端夹隔天清零
        String addTime = PrefUtils.getString(getActivity(), GlobalValues.XT_CART_TIME, DateUtil.getDateTimeStr(7));
        if (!DateUtil.getDateTimeStr(7).equals(addTime)) {
            // 隔天清空购物车表数据
            xtSelectService.deleteCartData("MST_TERMINALINFO_M_CART", "1");
        }

        // 设置终端条目适配器,及条目点击事件
        setItemAdapterListener();

    }

    // 三个下拉按钮的点击监听
    private void setDropdownItemSelectListener() {

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

    // 设置终端列表数据
    private void initTermListData(String routekey) {

        termList = new ArrayList<XtTermSelectMStc>();
        // 绑定TermList数据
        List<String> routes = new ArrayList<String>();
        routes.add(routekey);// 不同路线
        termList.clear();
        termList = xtSelectService.queryXtTestTerminal(routes);// 参数没用
    }

    XtTermSelectAdapter selectAdapter;

    //  设置终端条目适配器,及条目点击事件
    private void setItemAdapterListener() {
        // 设置适配器 加号按钮点击事件
        selectAdapter = new XtTermSelectAdapter(getActivity(), termList, termList, confirmTv, null, new IXtTermSelectClick() {
            @Override
            public void imageViewClick(int position, View v) {

                // 点击之前的加号按钮 也是拜访终端  (点击每个条目 也是拜访终端)

                xtTermSelectMStc = termList.get(position);

                // 检测条数是否已上传  // 该终端协同数据是否全部上传
                List<MitVisitM> terminalList = xtSelectService.getXtMitValterM(xtTermSelectMStc.getTerminalkey());
                if (terminalList.size() > 0) {// 未上传,弹窗上传
                    deleteOrXtUplad(terminalList.get(0));
                } else {// 已上传 去拜访
                    // 弹出提示 是否拜访这家终端
                    if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
                        // 拥有了此权限,那么直接执行业务逻辑
                        confirmXtUplad(xtTermSelectMStc);// 拜访
                    } else {
                        // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                        requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
                    }

                }
            }
        });
        // 设置适配器
        termRouteLv.setAdapter(selectAdapter);
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
            case R.id.xtbf_termlist_bt_search:// 查询
                searchTerm();
                break;
            default:
                break;
        }
    }

    // 模糊搜多终端
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
                            // 根据终端名称 条件请求终端
                            if ("opt_terminalbyterminalname_3".equals(optcode) && "MST_TERMINALINFO_M".equals(tableName)) {
                                String formjson = resObj.getResBody().getContent();
                                parseSearchTermListJson(formjson);// 0:不需要  1需要删除
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

    // 解析终端名称模糊搜索下的终端
    private void parseSearchTermListJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String MST_TERMINALINFO_M = emp.getMST_TERMINALINFO_M();

        if ("[]".equals(MST_TERMINALINFO_M) || TextUtils.isEmpty(MST_TERMINALINFO_M)) {// 返回数据为空
            Toast.makeText(getActivity(), "未搜索到任何终端", Toast.LENGTH_SHORT).show();
            termList.clear();
            selectAdapter.notifyDataSetChanged();
        } else {
            MainService service = new MainService(getActivity(), null);
            service.createOrUpdateTable(MST_TERMINALINFO_M, "MST_TERMINALINFO_M", MstTerminalinfoM.class, 1);
            initTermListData(routeKey);
            setItemAdapterListener();
        }
    }

    // listview的条目点击事件  单独拜访
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 点击每个条目 也是拜访终端  (点击之前的加号按钮 也是拜访终端)

        xtTermSelectMStc = termList.get(position);

        // 检测条数是否已上传  // 该终端协同数据是否全部上传
        List<MitVisitM> terminalList = xtSelectService.getXtMitValterM(xtTermSelectMStc.getTerminalkey());
        if (terminalList.size() > 0) {// 未上传,弹窗上传
            deleteOrXtUplad(terminalList.get(0));
        } else {// 已上传 去拜访
            // 弹出提示 是否拜访这家终端
            if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
                // 拥有了此权限,那么直接执行业务逻辑
                confirmXtUplad(xtTermSelectMStc);// 拜访
            } else {
                // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
            }

        }
    }

    @Override
    public void doLocation() {
        confirmXtUplad(xtTermSelectMStc);// 拜访
    }

    // 条目点击 是否删除/上传这家记录
    private void deleteOrXtUplad(final MitVisitM mitVisitM) {
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
                            xtUploadService.deleteXt(mitVisitM.getVisitkey(), mitVisitM.getTerminalkey());
                            initTermListData(routeKey);
                            setItemAdapterListener();
                        } else if (1 == position) {
                            DbtLog.logUtils(TAG, "前往拜访：上传");
                            // 如果网络可用
                            if (NetStatusUtil.isNetValid(getActivity())) {
                                xtUploadService.upload_xt_visit(false, mitVisitM.getVisitkey(), 1, 0);
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

    // 条目点击 确定拜访一家终端
    private void confirmXtUplad(final XtTermSelectMStc termSelectMStc) {
        String termName = termSelectMStc.getTerminalname();

        // 普通窗口
        mAlertViewExt = new AlertView("拜访: " + termName, null, "取消", new String[]{"确定"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "前往拜访：是");

                            // 清空购物车表数据  // 购物车表ddtype 1:协同  2:追溯   (因为每次请求终端数据,会将之前的终端表数据删除,当复制购物车表时,会复制null,所以不再删除购物车表了)
                            xtSelectService.deleteCartData("MST_TERMINALINFO_M_CART", "1");

                            // 复制到终端购物车
                            copyMstTerminalinfoMCart(xtTermSelectMStc);

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

    // 查找终端,并复制到终端购物车
    public void copyMstTerminalinfoMCart(XtTermSelectMStc termSelectMStc) {
        xtSelectService.toMstTerminalinfoMCartData(termSelectMStc, "1");
    }

    /**
     * 请求路线下的所有终端 或者 某一终端上次拜访详情
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
                                Toast.makeText(getActivity(), "该终端数据请求成功", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            termList.clear();
                            selectAdapter.notifyDataSetChanged();


                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        termList.clear();
                        selectAdapter.notifyDataSetChanged();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        termList.clear();
                        selectAdapter.notifyDataSetChanged();
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
        setItemAdapterListener();
    }


    // 跳转巡店拜访
    private void startXtVisitShopActivity() {
        Intent intent = new Intent(getActivity(), XtVisitShopActivity.class);
        intent.putExtra("isFirstVisit", "1");// 非第一次拜访1
        intent.putExtra("termStc", xtTermSelectMStc);
        intent.putExtra("seeFlag", "0"); // 0拜访 1查看标识
        startActivity(intent);
    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<XtTermListFragment> fragmentRef;

        public MyHandler(XtTermListFragment fragment) {
            fragmentRef = new SoftReference<XtTermListFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            XtTermListFragment fragment = fragmentRef.get();
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
            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
        } else if (2 == upType) {
            Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
        }
        initTermListData(routeKey);// 重新读取终端列表
        setItemAdapterListener();// 适配器处理
    }

}
