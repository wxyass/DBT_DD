package dd.tsingtaopad.dd.ddzs.zsshopvisit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.adapter.DayDetailSelectKeyValueAdapter;
import dd.tsingtaopad.base.BaseActivity;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.ui.loader.LatteLoader;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnDismissListener;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.table.MitRepairM;
import dd.tsingtaopad.db.table.MitRepaircheckM;
import dd.tsingtaopad.db.table.MitRepairterM;
import dd.tsingtaopad.db.table.MitValcheckterM;
import dd.tsingtaopad.db.table.MitValpicMTemp;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.db.table.MstVisitM;
import dd.tsingtaopad.dd.dddealplan.domain.DealStc;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtCheckIndexCalculateStc;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.dd.ddxt.updata.XtShopCopyService;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;
import dd.tsingtaopad.dd.ddzs.zscamera.ZsCameraFragment;
import dd.tsingtaopad.dd.ddzs.zschatvie.ZsChatvieFragment;
import dd.tsingtaopad.dd.ddzs.zscheckindex.ZsCheckIndexFragment;
import dd.tsingtaopad.dd.ddzs.zsagree.ZsAgreeFragment;
import dd.tsingtaopad.dd.ddzs.zsinvoicing.ZsInvoicingFragment;
import dd.tsingtaopad.dd.ddzs.zssayhi.ZsSayhiFragment;
import dd.tsingtaopad.fragmentback.HandleBackUtil;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsVisitShopActivity extends BaseActivity implements View.OnClickListener,TabHost.OnTabChangeListener {

    private final String TAG = "ZsVisitShopActivity";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;


    private AppCompatTextView mDataTv;// 上次拜访时间: 2018-03-12
    private AppCompatTextView mDayTv;// 距今几天: 8
    //private AppCompatTextView mTextTv;
    private FrameLayout mContentFl;// 需要替换的Fragment : 打招呼,聊竞品...
    private AppCompatImageView mMemoImg;// 客情备忘录

    private PopupWindow mPopWindow;

    private String seeFlag;// 1:查看 ,0:拜访
    private String isFirstVisit;//  是否第一次拜访  0:第一次拜访   1:重复拜访
    private XtTermSelectMStc termStc;

    private int fragmentType;
    private XtShopVisitService xtShopVisitService;
    private String preVisitkey;// 获取上次拜访主键
    private String prevVisitDate; // 上次拜访日期

    List<XtProIndex> calculateLst = new ArrayList<XtProIndex>();
    List<KvStc> indexValuelst = new ArrayList<KvStc>();
    List<XtProItem> proItemLst = new ArrayList<XtProItem>();
    List<XtCheckIndexCalculateStc> noProIndexLst = new ArrayList<XtCheckIndexCalculateStc>();

    private RadioButton xtvisit_rb_sayhi;
    private RadioButton xtvisit_rb_invoicing;
    private RadioButton xtvisit_rb_checkindex;
    private RadioButton xtvisit_rb_chatvie;
    private RadioButton xtvisit_rb_camera;
    private RadioButton xtvisit_rb_other;

    private String visitId;
    private String mitValterMTempKey;
    private String channelId;

    private FragmentTabHost tabHost;
    private Class fragmentArray[] = { ZsSayhiFragment.class,
            ZsInvoicingFragment.class, ZsAgreeFragment.class,ZsCheckIndexFragment.class,
            ZsChatvieFragment.class ,ZsCameraFragment.class};

    private int imageViewArray[] = { R.drawable.bt_shopvisit_sayhi,
            R.drawable.bt_shopvisit_invoicing,R.drawable.bt_shopvisit_agreement,
            R.drawable.bt_shopvisit_checkindex, R.drawable.bt_shopvisit_chatvie,
            R.drawable.bt_shopvisit_camera };

    MstTerminalInfoMStc mstTerminalInfoMStc;
    List<MitValcheckterM>  mitValcheckterMs;
    MitValcheckterM  mitValcheckterM;

    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    private String selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsvisitshop);
        initView();
        initData();

        if (hasPermission(GlobalValues.LOCAL_PERMISSION)) {
            // 拥有了此权限,那么直接执行业务逻辑
            registerGPS();
        } else {
            // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
            requestPermission(GlobalValues.LOCAL_CODE, GlobalValues.LOCAL_PERMISSION);
        }
    }

    // 初始化控件
    private void initView() {
        backBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        //titleTv.setOnClickListener(this);

        mDataTv = (AppCompatTextView) findViewById(R.id.zs_tv_date);
        mDayTv = (AppCompatTextView) findViewById(R.id.zs_tv_day);
        //mTextTv = (AppCompatTextView) findViewById(R.id.xtvisit_tv_textView2);
        mContentFl = (FrameLayout) findViewById(R.id.zs_fl_content);
        mMemoImg = (AppCompatImageView) findViewById(R.id.zs_bt_zhenggai);

        mMemoImg.setOnClickListener(this);

        LatteLoader.stopLoading(); // 若有进度条,关闭
    }

    // 初始化数据
    private void initData() {

        confirmTv.setText("确定");

        // 获取系统时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        // 获取参数“终端信息”
        Bundle bundle = getIntent().getExtras();
        seeFlag = bundle.getString("seeFlag");
        isFirstVisit = bundle.getString("isFirstVisit");
        termStc = (XtTermSelectMStc) bundle.getSerializable("termStc");
        mitValcheckterM = (MitValcheckterM) bundle.getSerializable("mitValcheckterM");

        titleTv.setTextSize(20);
        titleTv.setText(termStc.getTerminalname());

        xtShopVisitService = new XtShopVisitService(getApplicationContext(), null);
        // 从拜访主表中获取最后一次拜访数据
        MstVisitM preMstVisitM = xtShopVisitService.findNewLastVisit(termStc.getTerminalkey(), false);

        // // 获取追溯模板 大区id
        /*String areapid = PrefUtils.getString(this,"departmentid","");
        mitValcheckterMs = xtShopVisitService.getValCheckterMList(areapid);
        mitValcheckterM = mitValcheckterMs.get(0);*/

        // 获取上次拜访主键
        preVisitkey = preMstVisitM.getVisitkey();
        prevVisitDate = preMstVisitM.getVisitdate();
        // 复制各个临时表
        configVisitData(bundle);
        // 获取终端区域id,定格key,路线id
        mstTerminalInfoMStc = xtShopVisitService.findTermKeyById(termStc.getTerminalkey());

        // 展示打招呼页面
        /*XtSayhiFragment xtSayhiFragment = new XtSayhiFragment();
        xtSayhiFragment.setArguments(returnBundle());
        changeFragment(xtSayhiFragment, "xtsayhifragment");*/
         /*xtCameraFragmentf = new XtCameraFragment();
        xtCameraFragmentf.setArguments(returnBundle());
        changeFragment(xtCameraFragmentf, "xtcamerafragment");
        fragmentType = 5;*/

        // 初始化顶部TabHost
        initBandleDate(returnBundle());
    }

    // 初始化顶部TabHost
    private void initBandleDate(Bundle bundle) {
        // 初始化TabHost
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);

        tabHost.setup(ZsVisitShopActivity.this, getSupportFragmentManager(), R.id.xtvisit_fl_content);
        tabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < fragmentArray.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(String.valueOf(i)).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec, fragmentArray[i], bundle);
        }
        tabHost.setOnTabChangedListener(this);// 用来监听 打招呼、进销存、差指标、聊竞品 页面的操作事件
    }

    // 初始化顶部TabHost图片
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.platform_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(imageViewArray[index]);
        //imageView.setImageResource(R.drawable.bg_select_green);
        return view;
    }


    // 配置 拜访时 所需要的各个临时表数据
    private void configVisitData(Bundle bundle) {

        // 先删除
        xtShopVisitService.deleteData();

        // 再复制
        List<String> keys = xtShopVisitService.toCopyZsData(termStc,mstTerminalInfoMStc);
        visitId = keys.get(0);
        mitValterMTempKey = keys.get(1);
        preVisitkey = keys.get(2);
        channelId = termStc.getMinorchannel();

        // 保存初始数据(上面虽然复制了数据库,但有些表是没有复制的,在这里把一些数据)
        // 获取分项采集页面显示数据 // 获取指标采集前3列数据 (比如:铺货状态,道具生动化,产品生动化,冰冻化 及各个对应的产品)
        // mst_vistproduct_info_temp左关联mst_checkexerecord_info_temp
        // 注意:是从拉链表临时表中取值的 (但当天第一次拜访 拉链表的临时表都为null(因为toCopyData没复制),所以没值)(若是查看从拉链表正表取值)
        calculateLst = xtShopVisitService.queryCalculateIndex(visitId, termStc.getTerminalkey(), channelId, seeFlag);
        // (查出所有采集项数据)
        proItemLst = xtShopVisitService.queryCalculateItem(visitId, channelId);
        // 获取与产品无关的指标, 从临时表中读取
        noProIndexLst = xtShopVisitService.queryNoProIndex12(visitId, channelId, seeFlag);
        // 保存查指标页面的数据
        xtShopVisitService.saveCheckIndex(visitId, termStc.getTerminalkey(), calculateLst, proItemLst, noProIndexLst);

        String sd = "sd";
        // 获取分项采集页面显示数据 // 获取指标采集前3列数据 // 铺货状态,道具生动化,产品生动化,冰冻化
        //calculateLst = xtShopVisitService.queryCalculateIndex("da85a369f6344b46a8f4579aba5e095a", "1-AW46W7","39DD41A3991E8C68E05010ACE0016FCD", "");
        // 获取分项采集部分的产品指标对应的采集项目数据 // 因为在ShopVisitActivity生成了供货关系,此时就能关联出各个产品的采集项,现有量变化量为0
        //proItemLst = service.queryCalculateItem("da85a369f6344b46a8f4579aba5e095a", "39DD41A3991E8C68E05010ACE0016FCD");

    }


    // 按钮点击 监听
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.top_navigation_rl_back:// 返回
                this.backFinish();
                break;
            case R.id.top_navigation_rl_confirm://
                if(checkTakeCamera()){// 必须拍照
                    confirmXtUplad();
                }else{
                    Toast.makeText(getApplicationContext(), "拍照任务未完成,不能上传", Toast.LENGTH_SHORT).show();
                }
                break;
            // 客情备忘录
            case R.id.zs_bt_zhenggai:

                alertShow7();
                break;

            /*case R.id.xtvisit_rb_sayhi:
                if (fragmentType == 1) {
                    return;
                } else {
                    fragmentType = 1;
                    XtSayhiFragment xtSayhiFragment = new XtSayhiFragment();
                    xtSayhiFragment.setArguments(returnBundle());
                    changeFragment(xtSayhiFragment, "xtsayhifragment");
                }
                break;
            case R.id.xtvisit_rb_invoicing:
                if (fragmentType == 2) {
                    return;
                } else {
                    fragmentType = 2;
                    XtInvoicingFragment xtInvoicingFragment = new XtInvoicingFragment();
                    xtInvoicingFragment.setArguments(returnBundle());
                    changeFragment(xtInvoicingFragment, "xtinvoicingfragment");
                }
                break;
            case R.id.xtvisit_rb_checkindex:
                if (fragmentType == 3) {
                    return;
                } else {
                    fragmentType = 3;
                    XtCheckIndexFragment xtCheckIndexFragment = new XtCheckIndexFragment();
                    xtCheckIndexFragment.setArguments(returnBundle());
                    changeFragment(xtCheckIndexFragment, "xtcheckindexfragment");
                }
                break;
            case R.id.xtvisit_rb_chatvie:
                if (fragmentType == 4) {
                    return;
                } else {
                    fragmentType = 4;
                    XtChatvieFragment xtChatvieFragment = new XtChatvieFragment();
                    xtChatvieFragment.setArguments(returnBundle());
                    changeFragment(xtChatvieFragment, "xtchatviefragment");
                }
                break;
            case R.id.xtvisit_rb_camera:
                if (fragmentType == 5) {
                    return;
                } else {
                    if(GlobalValues.isSayHiSure){
                        fragmentType = 5;
                        XtCameraFragment xtCameraFragment = new XtCameraFragment();
                        xtCameraFragment.setArguments(returnBundle());
                        changeFragment(xtCameraFragment, "xtcamerafragment");
                    }
                }
                break;*/
            default:
                break;
        }
    }

    // 整改计划  从中间弹出
    public void alertShow7() {

        // 查询数据
        String terminalkey = mstTerminalInfoMStc.getTerminalkey();
        final List<DealStc> valueLst =  xtShopVisitService.getTermDealPlan(terminalkey);

        ViewGroup extView = (ViewGroup) LayoutInflater.from(ZsVisitShopActivity.this).inflate(R.layout.fragment_dd_dealmake, null);

        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        RelativeLayout rl_termname = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_termname);
        ImageView img_termname_arrow = (ImageView) extView.findViewById(R.id.zgjh_make_termname_arrow);
        img_termname_arrow.setVisibility(View.INVISIBLE);
        TextView termname = (TextView) extView.findViewById(R.id.zgjh_make_termname);

        RelativeLayout rl_grid = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_grid);
        TextView grid = (TextView) extView.findViewById(R.id.zgjh_make_grid);
        RelativeLayout rl_route = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_route);
        TextView routename = (TextView) extView.findViewById(R.id.zgjh_make_route);

        RelativeLayout rl_ydname = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_ydname);
        TextView ydname = (TextView) extView.findViewById(R.id.zgjh_make_ydname);

        RelativeLayout rl_question = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_question);
        final EditText question = (EditText) extView.findViewById(R.id.zgjh_make_question);

        RelativeLayout rl_amendplan = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_amendplan);
        final EditText amendplan = (EditText) extView.findViewById(R.id.zgjh_make_amendplan);

        RelativeLayout rl_measure = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_measure);
        final EditText measure = (EditText) extView.findViewById(R.id.zgjh_make_measure);

        RelativeLayout rl_checktime = (RelativeLayout) extView.findViewById(R.id.zgjh_make_rl_checktime);
        final TextView checktime = (TextView) extView.findViewById(R.id.zgjh_make_checktime);

        Button submit = (Button) extView.findViewById(R.id.zgjh_make_submit);

        rl_checktime.setOnClickListener(this);
        submit.setOnClickListener(this);

        title.setText("添加整改");

        termname.setText(mstTerminalInfoMStc.getTerminalname());
        grid.setText(mstTerminalInfoMStc.getGridname());
        routename.setText(mstTerminalInfoMStc.getRoutename());
        ydname.setText(mstTerminalInfoMStc.getUsername());

        if(valueLst.size()>0){
            DealStc dealStc = valueLst.get(0);
            question.setText(dealStc.getContent());
            amendplan.setText(dealStc.getRepairremark());
            measure.setText(dealStc.getCheckcontent());
            checktime.setText(dealStc.getRepairtime().substring(0,10));
        }

        // 显示对话框
        final AlertDialog dealdialog = new AlertDialog.Builder(ZsVisitShopActivity.this).create();
        dealdialog.setView(extView, 0, 0, 0, 0);
        dealdialog.setCancelable(true);
        dealdialog.show();

        // 选择时间
        rl_checktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // Toast.makeText(getActivity(), "选择核查时间", Toast.LENGTH_SHORT).show();
                DatePickerDialog dateDialog = new DatePickerDialog(ZsVisitShopActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        yearr = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        if (dayOfMonth < 10) {
                            aday = "0" + dayOfMonth;
                        } else {
                            aday = Integer.toString(dayOfMonth);
                        }

                        selectDate = (Integer.toString(year) + "-" + String.format("%02d", monthOfYear + 1) + "-" + aday);

                        checktime.setText(selectDate);

                    }
                }, yearr, month, day);
                if (!dateDialog.isShowing()) {
                    dateDialog.show();
                }
            }
        });

        // 确定
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(!checkTermName(checktime.getText().toString())){
                    return;
                }

                // 整顿计划 主表
                MitRepairM repairM = new MitRepairM();
                // repairM.setId(FunUtil.getUUID());
                repairM.setGridkey(mstTerminalInfoMStc.getGridkey());//定格
                repairM.setUserid(mstTerminalInfoMStc.getUserid());// 业代ID
                repairM.setContent(question.getText().toString());//问题描述
                repairM.setRepairremark(amendplan.getText().toString());//改进计划
                repairM.setCheckcontent(measure.getText().toString());//考核措施
                repairM.setCreuser(PrefUtils.getString(ZsVisitShopActivity.this, "userid", ""));//追溯人
                repairM.setCreuserareaid(PrefUtils.getString(ZsVisitShopActivity.this, "departmentid", ""));//追溯人所属区域
                repairM.setCredate(DateUtil.getDateTimeStr(8));//创建日期
                repairM.setUpdateuser(PrefUtils.getString(ZsVisitShopActivity.this, "userid", ""));//更新人
                repairM.setUpdatedate(DateUtil.getDateTimeStr(8));//更新时间
                repairM.setUploadflag("1");
                repairM.setPadisconsistent("0");
                repairM.setStatus("0");// 刚制定 状态为  0未复查1未通过2已通过

                MitRepaircheckM mitRepaircheckM = new MitRepaircheckM();
                //mitRepaircheckM.setId(FunUtil.getUUID());//
                //mitRepaircheckM.setRepairid(repairM.getId());//整改计划主表ID
                mitRepaircheckM.setStatus("0");//整改状态
                mitRepaircheckM.setRepairtime(checktime.getText().toString());//整改日期
                mitRepaircheckM.setUploadflag("1");
                mitRepaircheckM.setPadisconsistent("0");
                mitRepaircheckM.setCredate(DateUtil.getDateTimeStr(8));

                MitRepairterM mitRepairterM = new MitRepairterM();
                // mitRepairterM.setId(FunUtil.getUUID());
                // mitRepairterM.setRepairid(repairM.getId());
                mitRepairterM.setGridkey(mstTerminalInfoMStc.getGridkey());//
                mitRepairterM.setRoutekey(mstTerminalInfoMStc.getRoutekey());//
                mitRepairterM.setTerminalkey(mstTerminalInfoMStc.getTerminalkey());//
                mitRepairterM.setTerminalname(mstTerminalInfoMStc.getTerminalname());//
                mitRepairterM.setUploadflag("1");//
                mitRepairterM.setPadisconsistent("0");//

                // 设置id, 若有整改计划,复用之前的id   若没有整改计划新建id
                if(valueLst.size()>0){
                    DealStc dealStc = valueLst.get(0);
                    repairM.setId(dealStc.getRepairid());
                    mitRepaircheckM.setId(dealStc.getRepaircheckid());//
                    mitRepaircheckM.setRepairid(dealStc.getRepairid());//整改计划主表ID
                    mitRepairterM.setId(dealStc.getRepairterid());
                    mitRepairterM.setRepairid(dealStc.getRepairid());
                }else{
                    repairM.setId(FunUtil.getUUID());
                    mitRepaircheckM.setId(FunUtil.getUUID());//
                    mitRepaircheckM.setRepairid(repairM.getId());//整改计划主表ID
                    mitRepairterM.setId(FunUtil.getUUID());
                    mitRepairterM.setRepairid(repairM.getId());
                }

                xtShopVisitService.saveDealMitRepairM(repairM,mitRepaircheckM,mitRepairterM);

                // 上传整顿计划
                XtUploadService xtUploadService = new XtUploadService(ZsVisitShopActivity.this,null);
                xtUploadService.upload_repair(false,repairM,mitRepaircheckM,1);

                dealdialog.dismiss();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dealdialog.dismiss();
            }
        });
    }

    private boolean checkTermName(String checktime) {
        long b = DateUtil.parse(DateUtil.getDateTimeStr(7), "yyyy-MM-dd").getTime();// 当前时间
        boolean ishaveName = true;
        /*if(TextUtils.isEmpty(termname.getText().toString())){
            ishaveName = false;
            Toast.makeText(getActivity(),"请选择整改终端",Toast.LENGTH_SHORT).show();
        }else*/ if(TextUtils.isEmpty(checktime)){
            ishaveName = false;
            Toast.makeText(ZsVisitShopActivity.this,"请选择复查时间",Toast.LENGTH_SHORT).show();
        }else if(b > DateUtil.parse(checktime, "yyyy-MM-dd").getTime()){
            // 校验复查时间不能小于当前时间
            ishaveName = false;
            Toast.makeText(ZsVisitShopActivity.this,"复查时间不能小于当前时间",Toast.LENGTH_SHORT).show();
        }
        return ishaveName;
    }

    // 替换XtVisitShopActivity中的Fragment布局
    public void changeFragment(BaseFragmentSupport fragment, String tag) {
        // 获取Fragment管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        // 当前要添加的Fragment需要放的容器位置
        // 要替换的fragment
        // 标记
        beginTransaction.replace(R.id.zs_fl_content, fragment, tag);
        // 提交事物
        beginTransaction.commit();
    }

    // 替换整个XtVisitShopActivity布局
    public void changeXtvisitFragment(BaseFragmentSupport fragment, String tag) {
        // 获取Fragment管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        // 当前要添加的Fragment需要放的容器位置
        // 要替换的fragment
        // 标记
        beginTransaction.replace(R.id.zs_container, fragment, tag);
        beginTransaction.addToBackStack(null);
        // 提交事物
        beginTransaction.commit();
    }

    // 组建打招呼、进销存、聊竞品、查指标页面所需参数
    private Bundle returnBundle() {

        // 组建打招呼、进销存、聊竞品、查指标页面所需参数
        Bundle bundle = new Bundle();
        bundle.putSerializable("termId", termStc.getTerminalkey());
        bundle.putSerializable("termname", termStc.getTerminalname());
        bundle.putSerializable("channelId", termStc.getMinorchannel());// 次渠道
        bundle.putSerializable("termStc", termStc);
        bundle.putSerializable("visitKey", visitId);//visitId
        bundle.putSerializable("preVisitkey", preVisitkey);//visitId
        bundle.putSerializable("seeFlag", seeFlag);// 默认0   0:拜访 1:查看
        bundle.putSerializable("mitValterMTempKey", mitValterMTempKey);// 追溯主键
        bundle.putSerializable("mitValcheckterM", mitValcheckterM);// 追溯主键

        bundle.putSerializable("visitDate", "");// visitDate上一次的拜访时间(用于促销活动 状态隔天关闭)
        bundle.putSerializable("lastTime", "");// lastTime上一次的拜访时间(用于促销活动 状态隔天关闭)
        return bundle;
    }

    @Override
    public void onTabChanged(String tabId) {

        if (tabId.equals("0")) {
            DbtLog.logUtils(TAG, "XtSayHiFragment:打招呼");
        } else if (tabId.equals("1")) {
            DbtLog.logUtils(TAG, "XtInvoicingFragment:进销存");
        } else if (tabId.equals("2")) {
            DbtLog.logUtils(TAG, "XtCheckIndexFragment:查指标");
        } else if (tabId.equals("3")) {
            DbtLog.logUtils(TAG, "XtChatVieFragment:聊竞品");
        }else if (tabId.equals("4")) {
            DbtLog.logUtils(TAG, "XtCameraFragment:拍照");
        }

        // 如果是查看操作，则不做数据校验
        if (ConstValues.FLAG_1.equals(seeFlag))
            return;

    }


    private AlertView mAlertViewExt;//窗口拓展例子

    private void confirmXtUplad() {
        ZsVisitShopActivity.this.onPause();
        // 普通窗口
        mAlertViewExt = new AlertView("上传拜访数据?", null, "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "结束拜访：是");

                            // 检测现有量变化量是否为空  true:全不为空 可以上传   false:有为空的 不可上传
                            if (!checkCollectionexrecord()) {// 未填写现有量变化量
                                Toast.makeText(getApplicationContext(), "所有的现有量,变化量必须填值(没货填0)", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // 更新GPS坐标  更新到拜访临时表
                            xtShopVisitService.updateZsGps(mitValterMTempKey, longitude, latitude);

                            // 复制正表
                            //1  "MST_VISIT_M_TEMP"   原样复制create,并把enddate附上值   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传 有visitkey
                            //3  "MST_TERMINALINFO_M_TEMP"   原样复制createor   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传
                            //4  "MST_AGENCYSUPPLY_INFO_TEMP"  原样复制createor  注意padisconsistent字段的取值
                            //2  "MST_VISTPRODUCT_INFO_TEMP" 原样复制create   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传  有visitkey
                            //4  "MST_CMPSUPPLY_INFO_TEMP"   原样复制createor   注意padisconsistent字段的取值0
                            //1  "MST_CHECKEXERECORD_INFO_TEMP  视情况复制
                            //1  "MST_COLLECTIONEXERECORD_INFO_TEMP"   原样复制create   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传  有visitkey
                            //2  "MST_PROMOTERM_INFO_TEMP"   原样复制create   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传  有visitkey
                            //2  "MST_CAMERAINFO_M_TEMP"  原样复制create   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传  有visitkey
                            //  "MST_VISITMEMO_INFO_TEMP"
                            //5  "MST_GROUPPRODUCT_M_TEMP"  原样复制createor   是否已上传用padisconsistent字段控制  0:还未上传  1:已上传
                            //  "MST_CHECKGROUP_INFO"
                            //  "MST_CHECKGROUP_INFO_TEMP"

                            // 开始复制 追溯 离店时间及是否要上传标志
                            XtShopCopyService xtShopCopyService = new XtShopCopyService(getApplicationContext(),null);
                            xtShopCopyService.copyZsUpload(mitValterMTempKey);

                            // 上传协同拜访数据
                            XtUploadService xtUploadService = new XtUploadService(getApplicationContext(),null);
                            xtUploadService.upload_zs_visit(false,mitValterMTempKey,1,0);

                            ConstValues.handler.sendEmptyMessage(ConstValues.WAIT0);

                            ZsVisitShopActivity.this.finish();
                        }

                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "结束拜访：否");
                    }
                });
        mAlertViewExt.show();
    }

    /**
     * 检测该终端本次拜访是否已拍照
     *
     * @return true:已拍照  false:未拍照
     */
    private boolean checkTakeCamera() {
        List<MitValpicMTemp>   valueLst = new ArrayList<MitValpicMTemp>();
        List<MitValpicMTemp>  piclst = new ArrayList<MitValpicMTemp>();

        // 已拍张数
        piclst = xtShopVisitService.queryZsCurrentPicRecord(termStc.getTerminalkey(), mitValterMTempKey);
        // 后台配置需拍多少张
        valueLst = xtShopVisitService.queryZsPictypeMAll();

        int piccount = piclst.size();
        if (valueLst.size() == 0) {// 没配照片且没促销活动,允许上传  &&piclst.size()>=piccount
            return true;
        } else if (valueLst.size() > 0 && piccount > 0) {//
            return true;
        }
        return false;
    }

    private void backFinish() {
        // 普通窗口
        mAlertViewExt = new AlertView("若返回,这次追溯数据不会保存", null, "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "返回拜访：是");
                            ZsVisitShopActivity.this.finish();
                        }
                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "返回拜访：否");
                    }
                });
        mAlertViewExt.show();
    }

    // 检测现有量变化量是否为空  true:全不为空 可以上传   false:有为空的 不可上传
    private boolean checkCollectionexrecord(){

        boolean isallIn = true;
        // (查出所有采集项)
        /*List<ProItem> proItemLst = xtShopVisitService.queryCalculateItem(visitId, channelId);
        for (ProItem proitem : proItemLst) {
            if("".equals(FunUtil.isNullSetSpace(proitem.getBianhualiang()))||"".equals(FunUtil.isNullSetSpace(proitem.getXianyouliang()))){
                isallIn = false;// 为空不能上传
                break;
            }
        }*/
        return isallIn;
    }

    // 监听返回键
    @Override
    public void onBackPressed() {
        // 确定返回上一界面  先检查栈中的fragment是否监听了返回键
        if (!HandleBackUtil.handleBackPress(this)) {
            backFinish();
        }
    }

    // 原生经纬度 处理 --------------------------------------------------------

    private double longitude;// 经度
    private double latitude;// 维度
    public LocationManager lm;

    // 拥有定位权限 开启注册定位
    @Override
    public void doLocation() {
        registerGPS();
    }

    private void registerGPS() {

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //判断GPS是否正常启动
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0);
            return;
        }

        //为获取地理位置信息时设置查询条件
        String bestProvider = lm.getBestProvider(getCriteria(), true);
        //获取位置信息
        //如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
        Location location = lm.getLastKnownLocation(bestProvider);
        //        Location location= lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        updateView(location);
        //监听状态
        lm.addGpsStatusListener(listener);
        //绑定监听，有4个参数
        //参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
        //参数2，位置信息更新周期，单位毫秒
        //参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
        //参数4，监听
        //备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新

        // 1秒更新一次，或最小位移变化超过1米更新一次；
        //注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        //        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
    }

    //位置监听
    private LocationListener locationListener = new LocationListener() {

        /**
         * 位置信息变化时触发
         */
        public void onLocationChanged(Location location) {
            updateView(location);
            Log.i(TAG, "时间：" + location.getTime());
            Log.i(TAG, "经度：" + location.getLongitude());
            Log.i(TAG, "纬度：" + location.getLatitude());
            Log.i(TAG, "海拔：" + location.getAltitude());
        }

        /**
         * GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    Log.i(TAG, "当前GPS状态为可见状态");
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    Log.i(TAG, "当前GPS状态为服务区外状态");
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.i(TAG, "当前GPS状态为暂停服务状态");
                    break;
            }
        }

        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            Location location = lm.getLastKnownLocation(provider);
            updateView(location);
        }

        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
            updateView(null);
        }
    };

    //状态监听
    GpsStatus.Listener listener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            switch (event) {
                //第一次定位
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    Log.i(TAG, "第一次定位");
                    break;
                //卫星状态改变
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Log.i(TAG, "卫星状态改变");
                    //获取当前状态
                    /*GpsStatus gpsStatus=lm.getGpsStatus(null);
                    //获取卫星颗数的默认最大值
                    int maxSatellites = gpsStatus.getMaxSatellites();
                    //创建一个迭代器保存所有卫星
                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                    int count = 0;
                    while (iters.hasNext() && count <= maxSatellites) {
                        GpsSatellite s = iters.next();
                        count++;
                    }
                    System.out.println("搜索到："+count+"颗卫星");
                    tv_gps.setText("搜索到："+count+"颗卫星");*/
                    break;
                //定位启动
                case GpsStatus.GPS_EVENT_STARTED:
                    Log.i(TAG, "定位启动");
                    break;
                //定位结束
                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.i(TAG, "定位结束");
                    break;
            }
        }

        ;
    };

    /**
     * 实时更新文本内容
     *
     * @param location
     */
    private void updateView(Location location) {
        if (location != null) {
            // 经度
            longitude = location.getLongitude();
            // 纬度
            latitude = location.getLatitude();
        } else {
            //清空EditText对象
            //editText.getEditableText().clear();
        }
    }

    /**
     * 返回查询条件
     *
     * @return
     */
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(locationListener);
    }

}
