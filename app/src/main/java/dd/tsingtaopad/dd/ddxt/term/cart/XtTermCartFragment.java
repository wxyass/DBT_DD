package dd.tsingtaopad.dd.ddxt.term.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.net.HttpUrl;
import dd.tsingtaopad.core.net.RestClient;
import dd.tsingtaopad.core.net.callback.IError;
import dd.tsingtaopad.core.net.callback.IFailure;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.domain.RequestHeadStc;
import dd.tsingtaopad.core.net.domain.RequestStructBean;
import dd.tsingtaopad.core.net.domain.ResponseStructBean;
import dd.tsingtaopad.core.ui.loader.LatteLoader;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnDismissListener;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.table.MitVisitM;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtVisitShopActivity;
import dd.tsingtaopad.dd.ddxt.term.cart.adapter.XtTermCartAdapter;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;
import dd.tsingtaopad.home.app.MainService;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.main.visit.shopvisit.term.domain.TermSequence;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class XtTermCartFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "XtTermCartFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    private TextView lineTv;
    private TextView dateTv;
    private TextView dayTv;
    private EditText searchEt;
    private Button searchBtn;
    private Button updateBtn;
    private Button addBtn;
    private ListView termCartLv;

    private XtTermCartService cartService;
    private List<XtTermSelectMStc> termList= new ArrayList<XtTermSelectMStc>();
    private List<XtTermSelectMStc> termAllList= new ArrayList<XtTermSelectMStc>();
    private XtTermCartAdapter termCartAdapter;
    private List<XtTermSelectMStc> seqTermList;

    private XtTermSelectMStc termStc;
    private String termId;
    private Map<String, String> termPinyinMap;
    private List<XtTermSelectMStc> tempLst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_termcart, container, false);
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

        lineTv = (TextView) view.findViewById(R.id.xtbf_termcart_tv_visitline);
        dateTv = (TextView) view.findViewById(R.id.xtbf_termcart_tv_visitline_date);
        dayTv = (TextView) view.findViewById(R.id.xtbf_termcart_tv_visitline_day);
        searchEt = (EditText) view.findViewById(R.id.xtbf_termcart_et_search);
        searchBtn = (Button) view.findViewById(R.id.xtbf_termcart_bt_search);
        updateBtn = (Button) view.findViewById(R.id.xtbf_termcart_bt_update);
        addBtn = (Button) view.findViewById(R.id.xtbf_termcart_bt_add);
        termCartLv = (ListView) view.findViewById(R.id.xtbf_termcart_lv);

        searchBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);
        ConstValues.handler = handler;

        titleTv.setText("今日终端拜访列表");
        confirmTv.setText("拜访");
        cartService = new XtTermCartService(getActivity());


        // 获取从上个界面传递过来的数据
        /*String fromFragment = (String) getArguments().get("fromFragment");
        if("XtTermSelectFragment".equals(fromFragment)){// 如果从选择终端过来,设置需要同步
            // 购物车是否已经同步数据  false:没有  true:已同步
            PrefUtils.putBoolean(getActivity(),GlobalValues.XT_CART_SYNC,false);
        }*/

        // 初始化页面数据
        initData();

        //seqTermList = getNewMstTermListMStc();
    }

    // 初始化页面数据
    private void initData() {

        // 设置终端数据 // 判断购物车是协同,还是追溯  1协同  2追溯
        /*if("1".equals(PrefUtils.getString(getActivity(), GlobalValues.DDXTZS,""))){
            termList = cartService.queryCartTermList();
        }*/
        termList = cartService.queryCartTermList();// termList:只是协同用到的终端
        termAllList = cartService.queryAllCartTermList();// termAllList:协同+追溯所有终端

        // 终端拼音集合
        termPinyinMap = cartService.getAllTermPinyin(termList);

        // 若巡店拜访页面销毁了,
        if (termStc != null) {
            for (XtTermSelectMStc term : termList) {
                if (termStc.getTerminalkey().equals(term.getTerminalkey())) {
                    termStc = term;
                }
            }
        }

        // 设置数据,适配器
        searchTerm();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:

                if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    confirmVisit();// 去拜访
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
                }

                break;
            case R.id.xtbf_termcart_bt_search:
                searchTerm();
                break;
            case R.id.xtbf_termcart_bt_update:
                termCartAdapter.setUpdate(true);
                String s = updateBtn.getText().toString();
                if ("排序".equals(s)) {
                    updateBtn.setText("保存");
                    termCartAdapter.setUpdate(true);
                    termCartAdapter.notifyDataSetChanged();
                } else {
                    updateTermSeq();
                    updateBtn.setText("排序");
                    termCartAdapter.setUpdate(false);
                    searchTerm();
                    Toast.makeText(getActivity(),"排序保存成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xtbf_termcart_bt_add:// 更新数据
                // 更新前不可点击,更新后可以点击
                // 组建json  请求终端上次拜访详情
                buildJson();


                break;
            default:
                break;
        }
    }

    @Override
    public void doLocation() {
        confirmVisit();// 去拜访
    }

    // 删除或拜访
    private void confirmVisit(){
        // 购物车是否已经同步数据  false:没有  true:已同步
        boolean issync = PrefUtils.getBoolean(getActivity(),GlobalValues.XT_CART_SYNC,false);
        if(issync){// 已同步
            termStc = (XtTermSelectMStc)confirmBtn.getTag();
            // 该终端协同数据是否全部上传
            List<MitVisitM> terminalList = cartService.getXtMitValterM(termStc.getTerminalkey());
            if(terminalList.size()>0){// 未上传
                deleteOrXtUplad(terminalList.get(0));
            }else{// 已上传
                LatteLoader.showLoading(getActivity());// 处理数据中  ,在XtVisitShopActivity的initVIew中关闭
                Intent intent = new Intent(getActivity(), XtVisitShopActivity.class);
                intent.putExtra("isFirstVisit", "1");// 非第一次拜访1
                intent.putExtra("termStc", termStc);
                intent.putExtra("seeFlag", "0"); // 0拜访 1查看标识
                startActivity(intent);
            }
        }else{// 未同步
            Toast.makeText(getActivity(),"请先点击下载",Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 查询
     *
     * @return
     */
    public void searchTerm() {

        seqTermList = getNewMstTermListMStc();
        if (termStc == null || "3".equals(termStc.getStatus())) {
            termId = "";
        } else {
            termId = termStc.getTerminalkey();
            if (confirmBtn.getTag() != null) {
                XtTermSelectMStc termStc2 = (XtTermSelectMStc) confirmBtn.getTag();
                if (termStc2.getTerminalkey().equals(termStc.getTerminalkey())) {
                    confirmBtn.setTag(termStc);
                }
            }
        }

        // 根据搜索,查找终端列表
        if (!CheckUtil.isBlankOrNull(searchEt.getText().toString())) {
            tempLst = cartService.searchTermByname(getNewMstTermListMStc(), searchEt.getText().toString().replace(" ", ""), termPinyinMap);
        } else {// 没有搜索
            tempLst = getNewMstTermListMStc();
        }

        // 设置适配器
        termCartAdapter = new XtTermCartAdapter(getActivity(), seqTermList, tempLst, confirmBtn, termId,"1");
        termCartLv.setAdapter(termCartAdapter);

        // 若巡店拜访页面销毁了,根据下面判断 拜访按钮是否出现
        List<String> termIdLst = FunUtil.getPropertyByName(tempLst, "terminalkey", String.class);
        if (termStc != null && termIdLst.contains(termId)) {
            confirmBtn.setVisibility(View.VISIBLE);
            confirmBtn.setTag(termStc);
        } else {
            confirmBtn.setVisibility(View.INVISIBLE);
        }
    }


    AlertView mAlertViewExt;
    // 条目点击 是否删除/上传这家记录
    private void deleteOrXtUplad(final MitVisitM mitVisitM) {
        final XtUploadService xtUploadService = new XtUploadService(getActivity(),null);
        // 普通窗口
        mAlertViewExt = new AlertView("检测到这家终端上次数据未上传", null, null, new String[]{"删除","上传"}, null, getActivity(), AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "前往拜访：删除");
                            xtUploadService.deleteXt(mitVisitM.getVisitkey(),mitVisitM.getTerminalkey());
                            initData();
                            // ConstValues.handler.sendEmptyMessage(ConstValues.WAIT0);
                        }else if(1 == position){
                            DbtLog.logUtils(TAG, "前往拜访：上传");
                            // 如果网络可用
                            if (NetStatusUtil.isNetValid(getActivity())) {
                                xtUploadService.upload_xt_visit(false,mitVisitM.getVisitkey(),1,0);
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


    /***
     * 终端集合封装一个新的终端集合（防御联动修改）
     * @return
     */
    private List<XtTermSelectMStc> getNewMstTermListMStc() {
        List<XtTermSelectMStc> termList_new = new ArrayList<XtTermSelectMStc>();
        if (termList != null) {
            for (XtTermSelectMStc item : termList) {
                XtTermSelectMStc item_new = new XtTermSelectMStc();
                item_new.setRoutekey(item.getRoutekey());
                item_new.setTerminalkey(item.getTerminalkey());
                item_new.setTerminalcode(item.getTerminalcode());
                item_new.setTerminalname(item.getTerminalname());
                item_new.setStatus(item.getStatus());
                item_new.setSequence(item.getSequence());
                item_new.setMineFlag(item.getMineFlag());
                item_new.setVieFlag(item.getVieFlag());
                item_new.setMineProtocolFlag(item.getMineProtocolFlag());
                item_new.setVieProtocolFlag(item.getVieProtocolFlag());
                item_new.setSyncFlag(item.getSyncFlag());
                item_new.setUploadFlag(item.getUploadFlag());
                item_new.setMinorchannel(item.getMinorchannel());
                item_new.setTerminalType(item.getTerminalType());
                item_new.setVisitTime(item.getVisitTime());
                termList_new.add(item_new);
            }
        }
        return termList_new;
    }

    /***
     * 修改终端顺序
     */
    private void updateTermSeq() {
        termList = seqTermList;// seqTermList:修改后的
        List<TermSequence> termSequenceList = new ArrayList<TermSequence>();
        for (int i = 0; i < termList.size(); i++) {
            XtTermSelectMStc term = termList.get(i);
            if (!((i + 1) + "").equals(term.getSequence())) {
                // 根据终端在集合中的顺序,重新排序(从1开始)
                term.setSequence((i + 1) + "");
            }
            TermSequence termSequence = new TermSequence();
            termSequence.setSequence(term.getSequence());
            termSequence.setTerminalkey(term.getTerminalkey());
            termSequenceList.add(termSequence);
        }
        // 保存到数据库
        cartService.updateXtTermSequence(termSequenceList);
    }

    // 组建json  请求终端上次拜访详情
    private void buildJson() {
        List<String> termKeyLst = new ArrayList<String>();
        for (XtTermSelectMStc xtTermSelectMStc:termAllList) {
            termKeyLst.add(xtTermSelectMStc.getTerminalkey());
        }
        String json = JsonUtil.toJson(termKeyLst);// ["1-AW46W7","1-B6FF9Z","1-84RFW5","1-DIOQDH","1-AX2BVT"]

        String content  = "{"+
                "terminalkeys:'"+json+"'," +
                "tablename:'"+"MST_VISITDATA_M"+"'" +
                "}";
        getTermData("opt_get_dates2","MST_VISITDATA_M",content);
    }

    // 发送全部更新请求  请求终端上次拜访详情
    void getTermData(final String optcode, final String tableName,String content) {

        // 组建请求Json
        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        //requestHeadStc.setUsercode("50000");
        //requestHeadStc.setPassword("a1234567");
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
                        //Toast.makeText(getActivity(), resObj.getResBody().getContent()+""+resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        // 保存登录信息
                        if(ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())){
                            // 保存信息
                            if("opt_get_dates2".equals(optcode)&&"MST_VISITDATA_M".equals(tableName)){
                                String formjson = resObj.getResBody().getContent();
                                MainService mainService = new MainService(getActivity(), null);
                                mainService.parseTermDetailInfoJson(formjson);
                                // 购物车是否已经同步数据  false:没有  true:已同步
                                PrefUtils.putBoolean(getActivity(),GlobalValues.XT_CART_SYNC,true);
                                Toast.makeText(getActivity(), "该列表终端数据请求成功", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "请求错误", Toast.LENGTH_SHORT).show();
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

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<XtTermCartFragment> fragmentRef;

        public MyHandler(XtTermCartFragment fragment) {
            fragmentRef = new SoftReference<XtTermCartFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            XtTermCartFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0://  结束上传  刷新本页面
                    fragment.shuaxinXtTermCart(0);
                    break;
                case GlobalValues.SINGLE_UP_SUC://  协同拜访上传成功
                    fragment.shuaxinXtTermCart(1);
                    break;
                case GlobalValues.SINGLE_UP_FAIL://  协同拜访上传失败
                    fragment.shuaxinXtTermCart(2);
                    break;
            }
        }
    }

    // 结束上传  刷新页面  0:确定上传  1上传成功  2上传失败
    private void shuaxinXtTermCart(int upType) {
        if(1==upType){
            Toast.makeText(getActivity(),"上传成功",Toast.LENGTH_SHORT).show();
        }else if(2==upType){
            Toast.makeText(getActivity(),"上传失败",Toast.LENGTH_SHORT).show();
        }
        initData();
    }
}
