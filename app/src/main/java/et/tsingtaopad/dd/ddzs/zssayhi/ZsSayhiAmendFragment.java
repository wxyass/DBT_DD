package et.tsingtaopad.dd.ddzs.zssayhi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.AlertKeyValueAdapter;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.db.table.MitValterMTemp;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 打招呼 录入正确数据
 */

public class ZsSayhiAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsSayhiFragment.MyHandler handler;

    private Button sureBtn;

    protected String titleName = "";// 标题内容,比如:是否有效终端
    protected String ydkey = "";// 对象属性,比如: vidroutekey(属性)
    protected String ydparentkey = "";// 对象属性,比如: vidroutekey(属性)
    protected String setDdValue = "";// 对象方法名,比如: setVidrtekeyval(督导输入的值)
    protected String setDdFlag = "";// 对象方法名,比如: setVidrtekeyflag(正确与否)
    protected String setDdRemark = "";// 对象方法名,比如: setVidroutremark(备注)
    //protected String type = "";// 页面类型,比如: "1"
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected MitValterMTemp mitValterMTemp;// 追溯主表信息


    private RelativeLayout zdzs_sayhi_amend_rl;
    private TextView zdzs_sayhi_amend_rl_yd_title;
    private TextView zdzs_sayhi_amend_rl_con1;
    private TextView zdzs_sayhi_amend_rl_con2;
    private TextView zdzs_sayhi_amend_rl_statue;

    private LinearLayout zdzs_sayhi_amend_rl_ll_head;
    private RelativeLayout zdzs_sayhi_amend_rl_dd_head;

    private RelativeLayout zdzs_sayhi_amend_rl_dd_et;
    private TextView zdzs_sayhi_amend_rl_dd_title_et;
    private EditText zdzs_sayhi_amend_rl_dd_con1_et;
    private TextView zdzs_sayhi_amend_rl_con2_et;

    private RelativeLayout zdzs_sayhi_amend_rl_dd_sp;
    private TextView zdzs_sayhi_amend_rl_dd_title_sp;
    private TextView zdzs_sayhi_amend_rl_dd_con1_sp;// 内容1
    private EditText zdzs_sayhi_amend_rl_dd_con1_et_sp;//内容1  输入框
    //private TextView zdzs_sayhi_amend_rl_dd_con2_sp;

    private EditText zdzs_sayhi_amend_dd_et_report;

    private ZsSayhiService xtSayhiService;

    // 0,1类型
    //String oneType = "vidter,vidvisit,vidifmine,vidisself,vidiscmp,vidselftreaty,vidcmptreaty";

    // 输入框类型
    //String twoType = "termname,termcode,address,person,tel,sequence";
    // 天/次
    //String fourType = "cycle";
    // 件/年
    //String fiveType = "hvolume,mvolume,pvolume,lvolume";

    // 所属路线
    //String routeType = "vidroutekey";
    // 终端等级
    //String LvType = "vidterlevel";
    // 县
    //String countryType = "vidcountry";
    // 区域类型
    //String areaType = "vidareatype";
    // 次渠道
    //String channerlType = "vidminchannel";
    // 拜访对象
    //String persionType = "vidvisituser";


    public ZsSayhiAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsSayhiAmendFragment(ZsSayhiFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_sayhi_amend, container, false);
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

        zdzs_sayhi_amend_rl = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_amend_rl);// 业代数据Rl
        zdzs_sayhi_amend_rl_yd_title = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_yd_title);//
        zdzs_sayhi_amend_rl_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_con1);
        zdzs_sayhi_amend_rl_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_con2);
        zdzs_sayhi_amend_rl_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_statue);

        zdzs_sayhi_amend_rl_ll_head = (LinearLayout) view.findViewById(R.id.zdzs_sayhi_amend_rl_ll_head);
        zdzs_sayhi_amend_rl_dd_head = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_head);

        zdzs_sayhi_amend_rl_dd_et = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_et);
        zdzs_sayhi_amend_rl_dd_title_et = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_title_et);
        zdzs_sayhi_amend_rl_dd_con1_et = (EditText) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_con1_et);
        zdzs_sayhi_amend_rl_con2_et = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_con2_et);

        zdzs_sayhi_amend_rl_dd_sp = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_sp);
        zdzs_sayhi_amend_rl_dd_title_sp = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_title_sp);
        zdzs_sayhi_amend_rl_dd_con1_sp = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_con1_sp);
        zdzs_sayhi_amend_rl_dd_con1_et_sp = (EditText) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_con1_et_sp);
        //zdzs_sayhi_amend_rl_dd_con2_sp = (TextView) view.findViewById(R.id.zdzs_sayhi_amend_rl_dd_con2_sp);

        zdzs_sayhi_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_sayhi_amend_dd_et_report);

        sureBtn = (Button) view.findViewById(R.id.zdzs_sayhi_amend_dd_bt_save);

        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入正确数据");

        xtSayhiService = new ZsSayhiService(getActivity(), handler);

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        titleName = bundle.getString("titleName");
        ydkey = bundle.getString("ydkey");
        ydparentkey = bundle.getString("ydparentkey");
        setDdValue = bundle.getString("setDdValue");
        setDdFlag = bundle.getString("setDdFlag");
        setDdRemark = bundle.getString("setDdRemark");
        //type = bundle.getString("type");
        termId = bundle.getString("termId");
        mitValterMTempKey = bundle.getString("mitValterMTempKey");//bundle.putSerializable("mitValterMTempKey", mitValterMTempKey);// 追溯主键
        mitValterMTemp = (MitValterMTemp) bundle.getSerializable("mitValterMTemp");

        initData();

    }

    private void initData() {
        // 标题
        zdzs_sayhi_amend_rl_yd_title.setText(titleName);
        zdzs_sayhi_amend_rl_dd_title_et.setText(titleName);
        zdzs_sayhi_amend_rl_dd_title_sp.setText(titleName);

        // 是否有效终端
        if ("vidter".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidter())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidterremaek());
        }
        // 是否有效拜访
        if ("vidvisit".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidvisit())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidvisitremark());
        }
        // 是否我品店招
        if ("vidifmine".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidifmine())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidifminermark());
        }
        // 是否我品销售范围
        if ("vidisself".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidisself())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidisselfremark());
        }
        // 是否竞品销售范围
        if ("vidiscmp".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidiscmp())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidiscmpremark());
        }
        // 是否我品终端合作
        if ("vidselftreaty".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidselftreaty())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidselftreatyremark());
        }
        // 是否竞品终端合作
        if ("vidcmptreaty".equals(ydkey)) {
            if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidcmptreaty())) {
                zdzs_sayhi_amend_rl_con1.setText("是");
            } else {
                zdzs_sayhi_amend_rl_con1.setText("否");
            }
            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidcmptreatyremark());
        }

        // 终端名称
        if ("vidtername".equals(ydkey)) {
            // 业代终端名称
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidtername());
            // 督导终端名称
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 30 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidternameval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidternameremark());
        }
        // 终端编码
        if ("vidterminalcode".equals(ydkey)) {
            // 业代终端编码
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidterminalcode());
            // 督导终端编码
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 36 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidtercodeval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidtercoderemark());
        }
        // 地址
        if ("vidaddress".equals(ydkey)) {
            // 业代地址
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidaddress());
            // 督导地址
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 50 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidaddressval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidaddressremark());
        }
        // 联系人
        if ("vidcontact".equals(ydkey)) {
            // 业代联系人
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidcontact());
            // 督导联系人
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidcontactval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidcontactremark());
        }
        // 电话
        if ("vidmobile".equals(ydkey)) {
            // 业代电话
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidmobile());
            // 督导电话
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 30 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidmobileval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidmobileremark());
        }
        // 拜访顺序
        if ("vidsequence".equals(ydkey)) {
            // 业代拜访顺序
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidsequence());
            // 督导拜访顺序
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 3 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidsequenceval());

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidvidsequenceremark());
        }

        // 拜访周期
        if ("vidcycle".equals(ydkey)) {
            // 业代拜访周期
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidcycle());
            zdzs_sayhi_amend_rl_con2.setText("天/次");
            // 督导拜访周期
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setMaxWidth(10);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 2 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidcycleval());
            zdzs_sayhi_amend_rl_con2_et.setText("天/次");

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidcycleremark2());
        }

        // 高档容量
        if ("vidhvolume".equals(ydkey)) {
            // 业代高档容量
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidhvolume());
            zdzs_sayhi_amend_rl_con2.setText("件/年");
            // 督导高档容量
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setWidth(10);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidhvolumeval());
            zdzs_sayhi_amend_rl_con2_et.setText("件/年");

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidhvolumeremark());
        }
        // 中档容量
        if ("vidzvolume".equals(ydkey)) {
            // 业代中档容量
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidzvolume());
            zdzs_sayhi_amend_rl_con2.setText("件/年");
            // 督导中档容量
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setWidth(10);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidzvolumeval());
            zdzs_sayhi_amend_rl_con2_et.setText("件/年");

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidxvolumeremark());
        }
        // 普档容量
        if ("vidpvolume".equals(ydkey)) {
            // 业代普档容量
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidpvolume());
            zdzs_sayhi_amend_rl_con2.setText("件/年");
            // 督导普档容量
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setWidth(10);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidpvolumeval());
            zdzs_sayhi_amend_rl_con2_et.setText("件/年");

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidpvolumeremark());
        }
        // 低档容量
        if ("vidlvolume".equals(ydkey)) {
            // 业代低档容量
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidlvolume());
            zdzs_sayhi_amend_rl_con2.setText("件/年");
            // 督导低档容量
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_et.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_et.setWidth(10);
            zdzs_sayhi_amend_rl_dd_con1_et.setInputType(InputType.TYPE_CLASS_NUMBER);//输入类型为数字文本
            zdzs_sayhi_amend_rl_dd_con1_et.setFilters( new InputFilter[]{ new  InputFilter.LengthFilter( 10 )});// 设置输入长度
            zdzs_sayhi_amend_rl_dd_con1_et.setText(mitValterMTemp.getVidlvolumeval());
            zdzs_sayhi_amend_rl_con2_et.setText("件/年");

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidlvolumeremark());
        }

        // 所属路线
        if ("vidroutekey".equals(ydkey)) {
            // 业代所属路线
            zdzs_sayhi_amend_rl_con1.setText(xtSayhiService.getRouteName(mitValterMTemp.getVidroutekey()));
            // 督导所属路线
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(xtSayhiService.getRouteName(mitValterMTemp.getVidrtekeyval()));
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectRouteMRight(xtSayhiService.initXtMstRoute(termId),
                            mitValterMTemp.getVidrtekeyval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidroutremark());
        }
        // 终端等级
        if ("vidterlevel".equals(ydkey)) {
            // 业代终端等级
            zdzs_sayhi_amend_rl_con1.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidterlevel()));
            // 督导终端等级
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidtervidterlevelval()));
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectValueRight(xtSayhiService.initDataDicByTermLevel(),
                            mitValterMTemp.getVidtervidterlevelval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidtervidterlevelremark());
        }
        // 县
        if ("vidcountry".equals(ydkey)) {
            // 业代县
            zdzs_sayhi_amend_rl_con1.setText(xtSayhiService.getAreaName(mitValterMTemp.getVidcountry()));
            // 督导县
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(xtSayhiService.getAreaName(mitValterMTemp.getVidcountryval()));
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectValueRight(xtSayhiService.queryChildForArea(ydparentkey),
                            mitValterMTemp.getVidcountryval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidcountryremark());
        }
        // 区域类型
        if ("vidareatype".equals(ydkey)) {
            // 业代区域类型
            zdzs_sayhi_amend_rl_con1.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidareatype()));
            // 督导区域类型
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidareatypeval()));
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectValueRight(xtSayhiService.initDataDicByAreaType(),
                            mitValterMTemp.getVidareatypeval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidareatyperemark());
        }
        // 次渠道
        if ("vidminchannel".equals(ydkey)) {
            // 业代次渠道
            zdzs_sayhi_amend_rl_con1.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidminchannel()));
            // 督导次渠道
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(xtSayhiService.getDatadicName(mitValterMTemp.getVidminchannelval()));
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectValueRight(xtSayhiService.queryChildListDic(ydparentkey),
                            mitValterMTemp.getVidminchannelval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidminchannelremark());
        }
        // 拜访对象
        if ("vidvisituser".equals(ydkey)) {
            // 业代拜访对象
            zdzs_sayhi_amend_rl_con1.setText(mitValterMTemp.getVidvisitotherval());// 其他

            // 督导拜访对象
            zdzs_sayhi_amend_rl_ll_head.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_sp.setVisibility(View.VISIBLE);
            zdzs_sayhi_amend_rl_dd_con1_sp.setText(mitValterMTemp.getVidvisitottrueval());
            zdzs_sayhi_amend_rl_dd_sp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectVisitPosition(xtSayhiService.initZsVisitPosition(),mitValterMTemp.getVidvisituserval());
                }
            });

            zdzs_sayhi_amend_dd_et_report.setText(mitValterMTemp.getVidvisituserremark());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_sayhi_amend_dd_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    // 确定保存
    private void saveValue() {


        // 保存督导终端名称
        if ("vidtername".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidternameval(termName);
        }
        // 保存督导终端编码
        if ("vidterminalcode".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidtercodeval(termName);
        }
        // 保存督导地址
        if ("vidaddress".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidaddressval(termName);
        }
        // 保存督导联系人
        if ("vidcontact".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidcontactval(termName);
        }
        // 保存督导电话
        if ("vidmobile".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidmobileval(termName);
        }
        // 保存督导拜访顺序
        if ("vidsequence".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidsequenceval(termName);
        }

        // 保存督导拜访周期
        if ("vidcycle".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidcycleval(termName);
        }

        // 保存督导高档容量
        if ("vidhvolume".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidhvolumeval(termName);
        }
        // 保存督导中档容量
        if ("vidzvolume".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidzvolumeval(termName);
        }
        // 保存督导普档容量
        if ("vidpvolume".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidpvolumeval(termName);
        }
        // 保存督导低档容量
        if ("vidlvolume".equals(ydkey)) {
            String termName = zdzs_sayhi_amend_rl_dd_con1_et.getText().toString();
            mitValterMTemp.setVidlvolumeval(termName);
        }

        /*// 保存督导所属路线
        if ("vidroutekey".contains(ydkey)) {
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            setFieldValue(mitValterMTemp, setDdValue, key);
        }
        // 保存督导终端等级
        if ("vidterlevel".contains(ydkey)) {
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            setFieldValue(mitValterMTemp, setDdValue, key);
        }
        // 保存督导县
        if ("vidcountry".contains(ydkey)) {
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            setFieldValue(mitValterMTemp, setDdValue, key);
        }
        // 保存督导区域类型
        if ("vidareatype".contains(ydkey)) {
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            setFieldValue(mitValterMTemp, setDdValue, key);
        }
        // 保存督导次渠道
        if ("vidminchannel".contains(ydkey)) {
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            setFieldValue(mitValterMTemp, setDdValue, key);
        }*/

        // 保存督导拜访对象
        if ("vidvisituser".contains(ydkey)) {
            // 保存督导拜访对象的key
            String key = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getTag();
            FunUtil.setFieldValue(mitValterMTemp, setDdValue, key);// 督导正确的key

            // 保存督导拜访对象的Value
            String tv = (String) zdzs_sayhi_amend_rl_dd_con1_sp.getText();
            String et = (String) zdzs_sayhi_amend_rl_dd_con1_et_sp.getText().toString();
            if ("66AA9D3A55374232891C964350610930".equals(key)) {// 其他
                mitValterMTemp.setVidvisitottrueval(et);
            } else {
                FunUtil.setFieldValue(mitValterMTemp, setDdValue, key);
                mitValterMTemp.setVidvisitottrueval(tv);
            }
        }

        // 保存是否正确,备注内容
        String remark = zdzs_sayhi_amend_dd_et_report.getText().toString();
        FunUtil.setFieldValue(mitValterMTemp, setDdFlag, "N");
        FunUtil.setFieldValue(mitValterMTemp, setDdRemark, remark);

        // 当稽查选择错误时
        int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
        count++;
        PrefUtils.putInt(getActivity(),"valterErrorCount",count);

        handler.sendEmptyMessage(ZsSayhiFragment.INIT_DATA);
    }

    AlertView mAlertViewExt;

    // 路线选择  弹窗
    private void setSelectRouteMRight(final List<MstRouteM> mstRouteList, String routekey) {
        mAlertViewExt = new AlertView("请选择路线", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), mstRouteList,
                new String[]{"routekey", "routename"}, routekey);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                zdzs_sayhi_amend_rl_dd_con1_sp.setText(mstRouteList.get(position).getRoutename());
                zdzs_sayhi_amend_rl_dd_con1_sp.setTag(mstRouteList.get(position).getRoutekey());
                mitValterMTemp.setVidrtekeyval(mstRouteList.get(position).getRoutekey());
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择 县 终端等级 区域 次渠道  弹窗
    private void setSelectValueRight(final List<KvStc> dataDic, String routekey) {
        mAlertViewExt = new AlertView("请正确值", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, routekey);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                zdzs_sayhi_amend_rl_dd_con1_sp.setText(dataDic.get(position).getValue());
                zdzs_sayhi_amend_rl_dd_con1_sp.setTag(dataDic.get(position).getKey());
                // 终端等级
                if ("vidterlevel".equals(ydkey)) {
                    mitValterMTemp.setVidtervidterlevelval(dataDic.get(position).getKey());
                }
                // 县
                if ("vidcountry".equals(ydkey)) {
                    mitValterMTemp.setVidcountryval(dataDic.get(position).getKey());
                }
                // 区域类型
                if ("vidareatype".equals(ydkey)) {
                    mitValterMTemp.setVidareatypeval(dataDic.get(position).getKey());
                }
                // 次渠道
                if ("vidminchannel".equals(ydkey)) {
                    mitValterMTemp.setVidminchannelval(dataDic.get(position).getKey());
                }
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 拜访对象 弹窗
    private void setSelectVisitPosition(final List<KvStc> dataDic, String routekey) {
        mAlertViewExt = new AlertView("请正确值", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, routekey);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("66AA9D3A55374232891C964350610930".equals(dataDic.get(position).getKey())) {// 选择其他
                    zdzs_sayhi_amend_rl_dd_con1_sp.setVisibility(View.GONE);// 默认tv
                    zdzs_sayhi_amend_rl_dd_con1_et_sp.setVisibility(View.VISIBLE);// 输入框
                    zdzs_sayhi_amend_rl_dd_con1_sp.setText(dataDic.get(position).getValue());
                    zdzs_sayhi_amend_rl_dd_con1_sp.setTag(dataDic.get(position).getKey());
                    zdzs_sayhi_amend_rl_dd_con1_et_sp.setTag(dataDic.get(position).getKey());
                    mitValterMTemp.setVidvisituserval(dataDic.get(position).getKey());
                } else {
                    zdzs_sayhi_amend_rl_dd_con1_sp.setVisibility(View.VISIBLE);// 输入框
                    zdzs_sayhi_amend_rl_dd_con1_et_sp.setVisibility(View.GONE);// 默认tv
                    zdzs_sayhi_amend_rl_dd_con1_sp.setText(dataDic.get(position).getValue());
                    zdzs_sayhi_amend_rl_dd_con1_sp.setTag(dataDic.get(position).getKey());
                    zdzs_sayhi_amend_rl_dd_con1_et_sp.setTag(dataDic.get(position).getKey());
                    mitValterMTemp.setVidvisituserval(dataDic.get(position).getKey());
                }
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
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
