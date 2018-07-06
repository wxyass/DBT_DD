package dd.tsingtaopad.dd.ddzs.zssayhi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DbtUtils;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.table.MitValterMTemp;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.db.table.MstTerminalinfoMTemp;
import dd.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import dd.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import dd.tsingtaopad.home.app.MyApplication;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsSayhiFragment extends XtBaseVisitFragment implements View.OnClickListener {

    private final String TAG = "ZsSayhiFragment";

    private ZsSayhiService zsSayhiService;
    private MstTerminalinfoMTemp termInfoTemp;
    //private MstVisitMTemp visitMTemp;
    private MitValterMTemp mitValterMTemp;

    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    private String ifminedate;

    private AlertView mAlertViewExt;//窗口拓展例子

    private List<MstRouteM> mstRouteList = new ArrayList<>();
    private List<KvStc> dataDicByTermLevelLst = new ArrayList<>();
    private List<KvStc> dataDicByAreaTypeLst = new ArrayList<>();
    private List<KvStc> sellchanneldataLst = new ArrayList<>();
    private List<KvStc> visitpositionLst = new ArrayList<>();
    private List<KvStc> sellchannelLst = new ArrayList<>();
    private List<KvStc> mainchannelLst = new ArrayList<>();
    private List<KvStc> minorchannelLst = new ArrayList<>();

    //public static final int FINISH_SUC = 11;// 初始化成功发送信息到主UI (废弃)
    public static final int NOT_TERMSTATUS = 12;// 失效终端
    public static final int DATA_ARROR = 13;// 打招呼页面信息不全
    public static final int SHOW_INIT_PROGRESS = 14;// 数据初始化-开启滚动条
    public static final int CLOSE_INIT_PROGRESS = 15;// 初始化成功-关闭滚动条
    public static final int INIT_DATA = 16;// 修改完数据 刷新界面
    MyHandler handler;


    // ------------------------------------------------------------
    private ScrollView sayhi_scrollview;
    private RelativeLayout zdzs_sayhi_rl_termstatus;
    private TextView zdzs_sayhi_tv_termstatus_con1;
    private TextView zdzs_sayhi_tv_termstatus_statue;

    private RelativeLayout zdzs_sayhi_rl_visitstatus;
    private TextView zdzs_sayhi_tv_visitstatus_con1;
    private TextView zdzs_sayhi_tv_visitstatus_statue;

    private RelativeLayout zdzs_sayhi_rl_wopindianzhao;
    private TextView zdzs_sayhi_rl_wopindianzhao_con1;
    private TextView zdzs_sayhi_rl_wopindianzhao_con2;
    private TextView zdzs_sayhi_rl_wopindianzhao_statue;

    private RelativeLayout zdzs_sayhi_rl_selfstatus;
    private TextView zdzs_sayhi_rl_selfstatus_con1;
    private TextView zdzs_sayhi_rl_selfstatus_con2;
    private TextView zdzs_sayhi_rl_selfstatus_statue;

    private RelativeLayout zdzs_sayhi_rl_cmpstatus;
    private TextView zdzs_sayhi_rl_cmpstatus_con1;
    private TextView zdzs_sayhi_rl_cmpstatus_con2;
    private TextView zdzs_sayhi_rl_cmpstatus_statue;

    private RelativeLayout zdzs_sayhi_rl_selfprotocol;
    private TextView zdzs_sayhi_rl_selfprotocol_con1;
    private TextView zdzs_sayhi_rl_selfprotocol_con2;
    private TextView zdzs_sayhi_rl_selfprotocol_statue;

    private RelativeLayout zdzs_sayhi_rl_cmpprotocol;
    private TextView zdzs_sayhi_rl_cmpprotocol_con1;
    private TextView zdzs_sayhi_rl_cmpprotocol_con2;
    private TextView zdzs_sayhi_rl_cmpprotocol_statue;

    private RelativeLayout zdzs_sayhi_rl_termname;
    private TextView zdzs_sayhi_rl_termname_con1;
    private TextView zdzs_sayhi_rl_termname_statue;

    private RelativeLayout zdzs_sayhi_rl_termcode;
    private TextView zdzs_sayhi_rl_termcode_con1;
    private TextView zdzs_sayhi_rl_termcode_statue;
    private RelativeLayout zdzs_sayhi_rl_belongline;
    private TextView zdzs_sayhi_rl_belongline_con1;
    private TextView zdzs_sayhi_rl_belongline_statue;
    private RelativeLayout zdzs_sayhi_rl_level;
    private TextView zdzs_sayhi_rl_level_con1;
    private TextView zdzs_sayhi_rl_level_statue;
    private RelativeLayout zdzs_sayhi_rl_prov;
    private TextView zdzs_sayhi_rl_prov_con1;
    private TextView zdzs_sayhi_rl_prov_statue;
    private RelativeLayout zdzs_sayhi_rl_city;
    private TextView zdzs_sayhi_rl_city_con1;
    private TextView zdzs_sayhi_rl_city_statue;
    private RelativeLayout zdzs_sayhi_rl_country;
    private TextView zdzs_sayhi_rl_country_con1;
    private TextView zdzs_sayhi_rl_country_statue;
    private RelativeLayout zdzs_sayhi_rl_address;
    private TextView zdzs_sayhi_rl_address_con1;
    private TextView zdzs_sayhi_rl_address_statue;
    private RelativeLayout zdzs_sayhi_rl_person;
    private TextView zdzs_sayhi_rl_person_con1;
    private TextView zdzs_sayhi_rl_person_statue;
    private RelativeLayout zdzs_sayhi_rl_tel;
    private TextView zdzs_sayhi_rl_tel_con1;
    private TextView zdzs_sayhi_rl_tel_statue;
    private RelativeLayout zdzs_sayhi_rl_cycle;
    private TextView zdzs_sayhi_rl_cycle_con1;
    private TextView zdzs_sayhi_rl_cycle_statue;
    private RelativeLayout zdzs_sayhi_rl_sequence;
    private TextView zdzs_sayhi_rl_sequence_con1;
    private TextView zdzs_sayhi_rl_sequence_statue;
    private RelativeLayout zdzs_sayhi_rl_hvolume;
    private TextView zdzs_sayhi_rl_hvolume_con1;
    private TextView zdzs_sayhi_rl_hvolume_statue;
    private RelativeLayout zdzs_sayhi_rl_mvolume;
    private TextView zdzs_sayhi_rl_mvolume_con1;
    private TextView zdzs_sayhi_rl_mvolume_statue;
    private RelativeLayout zdzs_sayhi_rl_pvolume;
    private TextView zdzs_sayhi_rl_pvolume_con1;
    private TextView zdzs_sayhi_rl_pvolume_statue;
    private RelativeLayout zdzs_sayhi_rl_lvolume;
    private TextView zdzs_sayhi_rl_lvolume_con1;
    private TextView zdzs_sayhi_rl_lvolume_statue;
    private RelativeLayout zdzs_sayhi_rl_totalvolume;
    private TextView zdzs_sayhi_rl_totalvolume_con1;
    private TextView zdzs_sayhi_rl_totalvolume_statue;
    private RelativeLayout zdzs_sayhi_rl_areatype;
    private TextView zdzs_sayhi_rl_areatype_con1;
    private TextView zdzs_sayhi_rl_areatype_statue;
    private RelativeLayout zdzs_sayhi_rl_sellchannel;
    private TextView zdzs_sayhi_rl_sellchannel_con1;
    private TextView zdzs_sayhi_rl_sellchannel_statue;
    private RelativeLayout zdzs_sayhi_rl_mainchannel;
    private TextView zdzs_sayhi_rl_mainchannel_con1;
    private TextView zdzs_sayhi_rl_mainchannel_statue;
    private RelativeLayout zdzs_sayhi_rl_minorchannel;
    private TextView zdzs_sayhi_rl_minorchannel_con1;
    private TextView zdzs_sayhi_rl_minorchannel_statue;
    private RelativeLayout zdzs_sayhi_rl_visitperson;
    private TextView zdzs_sayhi_rl_visitperson_con1;
    private TextView zdzs_sayhi_rl_visitperson_statue;


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsSayhiFragment> fragmentRef;

        public MyHandler(ZsSayhiFragment fragment) {
            fragmentRef = new SoftReference<ZsSayhiFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsSayhiFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }


            // 处理UI 变化
            switch (msg.what) {
                /*case FINISH_SUC:
                    fragment.initData2();
                    break;*/
                case NOT_TERMSTATUS:
                    fragment.closeXtTermstatusSw();
                    break;
                case DATA_ARROR:
                    int res = (int) msg.obj;
                    fragment.showErrorMsg(res);

                    break;
                case SHOW_INIT_PROGRESS:
                    fragment.showXtSayHiDialog();
                    break;
                case CLOSE_INIT_PROGRESS:
                    fragment.closeXtSayHiDialog();
                    fragment.initData3();
                    break;

                case INIT_DATA:
                    fragment.initData3();
                    break;
            }
        }
    }

    // 展示错误信息
    private void showErrorMsg(int res) {
        Toast.makeText(getActivity(), getString(res), Toast.LENGTH_SHORT).show();
        //requestFocus(res);
    }


    // 取消终端失效
    private void closeXtTermstatusSw() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_sayhi, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        //-----------------------------------------------------------
        sayhi_scrollview = (ScrollView) view.findViewById(R.id.sayhi_scrollview);
        zdzs_sayhi_rl_termstatus = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_termstatus);
        zdzs_sayhi_tv_termstatus_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_tv_termstatus_con1);
        zdzs_sayhi_tv_termstatus_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_tv_termstatus_statue);

        zdzs_sayhi_rl_visitstatus = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_visitstatus);
        zdzs_sayhi_tv_visitstatus_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_tv_visitstatus_con1);
        zdzs_sayhi_tv_visitstatus_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_tv_visitstatus_statue);

        zdzs_sayhi_rl_wopindianzhao = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_wopindianzhao);
        zdzs_sayhi_rl_wopindianzhao_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_wopindianzhao_con1);
        zdzs_sayhi_rl_wopindianzhao_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_wopindianzhao_con2);
        zdzs_sayhi_rl_wopindianzhao_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_wopindianzhao_statue);

        // 我品销售
        zdzs_sayhi_rl_selfstatus = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_selfstatus);
        zdzs_sayhi_rl_selfstatus_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfstatus_con1);
        zdzs_sayhi_rl_selfstatus_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfstatus_con2);
        zdzs_sayhi_rl_selfstatus_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfstatus_statue);

        // 竞品销售
        zdzs_sayhi_rl_cmpstatus = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_cmpstatus);
        zdzs_sayhi_rl_cmpstatus_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpstatus_con1);
        zdzs_sayhi_rl_cmpstatus_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpstatus_con2);
        zdzs_sayhi_rl_cmpstatus_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpstatus_statue);

        // 我品合作
        zdzs_sayhi_rl_selfprotocol = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_selfprotocol);
        zdzs_sayhi_rl_selfprotocol_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfprotocol_con1);
        zdzs_sayhi_rl_selfprotocol_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfprotocol_con2);
        zdzs_sayhi_rl_selfprotocol_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_selfprotocol_statue);

        // 竞品合作
        zdzs_sayhi_rl_cmpprotocol = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_cmpprotocol);
        zdzs_sayhi_rl_cmpprotocol_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpprotocol_con1);
        zdzs_sayhi_rl_cmpprotocol_con2 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpprotocol_con2);
        zdzs_sayhi_rl_cmpprotocol_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cmpprotocol_statue);


        zdzs_sayhi_rl_termname = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_termname);
        zdzs_sayhi_rl_termname_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_termname_con1);
        zdzs_sayhi_rl_termname_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_termname_statue);

        zdzs_sayhi_rl_termcode = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_termcode);
        zdzs_sayhi_rl_termcode_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_termcode_con1);
        zdzs_sayhi_rl_termcode_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_termcode_statue);

        zdzs_sayhi_rl_belongline = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_belongline);
        zdzs_sayhi_rl_belongline_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_belongline_con1);
        zdzs_sayhi_rl_belongline_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_belongline_statue);

        zdzs_sayhi_rl_level = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_level);
        zdzs_sayhi_rl_level_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_level_con1);
        zdzs_sayhi_rl_level_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_level_statue);

        zdzs_sayhi_rl_prov = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_prov);
        zdzs_sayhi_rl_prov_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_prov_con1);
        zdzs_sayhi_rl_prov_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_prov_statue);

        zdzs_sayhi_rl_city = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_city);
        zdzs_sayhi_rl_city_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_city_con1);
        zdzs_sayhi_rl_city_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_city_statue);

        zdzs_sayhi_rl_country = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_country);
        zdzs_sayhi_rl_country_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_country_con1);
        zdzs_sayhi_rl_country_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_country_statue);

        zdzs_sayhi_rl_address = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_address);
        zdzs_sayhi_rl_address_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_address_con1);
        zdzs_sayhi_rl_address_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_address_statue);

        zdzs_sayhi_rl_person = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_person);
        zdzs_sayhi_rl_person_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_person_con1);
        zdzs_sayhi_rl_person_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_person_statue);

        zdzs_sayhi_rl_tel = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_tel);
        zdzs_sayhi_rl_tel_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_tel_con1);
        zdzs_sayhi_rl_tel_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_tel_statue);

        zdzs_sayhi_rl_cycle = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_cycle);
        zdzs_sayhi_rl_cycle_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cycle_con1);
        zdzs_sayhi_rl_cycle_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_cycle_statue);

        zdzs_sayhi_rl_sequence = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_sequence);
        zdzs_sayhi_rl_sequence_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_sequence_con1);
        zdzs_sayhi_rl_sequence_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_sequence_statue);

        zdzs_sayhi_rl_hvolume = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_hvolume);
        zdzs_sayhi_rl_hvolume_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_hvolume_con1);
        zdzs_sayhi_rl_hvolume_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_hvolume_statue);

        zdzs_sayhi_rl_mvolume = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_mvolume);
        zdzs_sayhi_rl_mvolume_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_mvolume_con1);
        zdzs_sayhi_rl_mvolume_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_mvolume_statue);

        zdzs_sayhi_rl_pvolume = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_pvolume);
        zdzs_sayhi_rl_pvolume_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_pvolume_con1);
        zdzs_sayhi_rl_pvolume_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_pvolume_statue);

        zdzs_sayhi_rl_lvolume = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_lvolume);
        zdzs_sayhi_rl_lvolume_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_lvolume_con1);
        zdzs_sayhi_rl_lvolume_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_lvolume_statue);

        zdzs_sayhi_rl_totalvolume = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_totalvolume);
        zdzs_sayhi_rl_totalvolume_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_totalvolume_con1);
        zdzs_sayhi_rl_totalvolume_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_totalvolume_statue);

        zdzs_sayhi_rl_areatype = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_areatype);
        zdzs_sayhi_rl_areatype_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_areatype_con1);
        zdzs_sayhi_rl_areatype_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_areatype_statue);

        zdzs_sayhi_rl_sellchannel = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_sellchannel);
        zdzs_sayhi_rl_sellchannel_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_sellchannel_con1);
        zdzs_sayhi_rl_sellchannel_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_sellchannel_statue);

        zdzs_sayhi_rl_mainchannel = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_mainchannel);
        zdzs_sayhi_rl_mainchannel_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_mainchannel_con1);
        zdzs_sayhi_rl_mainchannel_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_mainchannel_statue);

        zdzs_sayhi_rl_minorchannel = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_minorchannel);
        zdzs_sayhi_rl_minorchannel_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_minorchannel_con1);
        zdzs_sayhi_rl_minorchannel_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_minorchannel_statue);

        zdzs_sayhi_rl_visitperson = (RelativeLayout) view.findViewById(R.id.zdzs_sayhi_rl_visitperson);
        zdzs_sayhi_rl_visitperson_con1 = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_visitperson_con1);
        zdzs_sayhi_rl_visitperson_statue = (TextView) view.findViewById(R.id.zdzs_sayhi_rl_visitperson_statue);

        zdzs_sayhi_rl_termstatus.setOnClickListener(this);
        zdzs_sayhi_rl_visitstatus.setOnClickListener(this);
        zdzs_sayhi_rl_wopindianzhao.setOnClickListener(this);

        zdzs_sayhi_rl_selfstatus.setOnClickListener(this);
        zdzs_sayhi_rl_cmpstatus.setOnClickListener(this);
        zdzs_sayhi_rl_selfprotocol.setOnClickListener(this);
        zdzs_sayhi_rl_cmpprotocol.setOnClickListener(this);


        zdzs_sayhi_rl_termname.setOnClickListener(this);
        // zdzs_sayhi_rl_termcode.setOnClickListener(this);
        zdzs_sayhi_rl_address.setOnClickListener(this);
        zdzs_sayhi_rl_person.setOnClickListener(this);
        zdzs_sayhi_rl_tel.setOnClickListener(this);
        zdzs_sayhi_rl_sequence.setOnClickListener(this);

        zdzs_sayhi_rl_cycle.setOnClickListener(this);
        zdzs_sayhi_rl_hvolume.setOnClickListener(this);
        zdzs_sayhi_rl_mvolume.setOnClickListener(this);
        zdzs_sayhi_rl_pvolume.setOnClickListener(this);
        zdzs_sayhi_rl_lvolume.setOnClickListener(this);

        zdzs_sayhi_rl_belongline.setOnClickListener(this);
        zdzs_sayhi_rl_level.setOnClickListener(this);
        // zdzs_sayhi_rl_country.setOnClickListener(this);
        zdzs_sayhi_rl_areatype.setOnClickListener(this);
        zdzs_sayhi_rl_minorchannel.setOnClickListener(this);
        // zdzs_sayhi_rl_visitperson.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);
        zsSayhiService = new ZsSayhiService(getActivity(), handler);

        // 根据追溯模板 设置各个控件是否显示
        initViewVisible();

        PrefUtils.putBoolean(getActivity(), GlobalValues.SAYHIREADY, true);
        // 初始化页面数据
        initViewData();// 子线程查找数据

    }

    // 根据追溯模板 设置各个控件是否显示
    private void initViewVisible() {

        // 是否有效终端
        if ("Y".equals(mitValcheckterM.getVaildter())) {
            zdzs_sayhi_rl_termstatus.setVisibility(View.VISIBLE);
        }
        // 是否有效拜访
        if ("Y".equals(mitValcheckterM.getVaildvisit())) {
            zdzs_sayhi_rl_visitstatus.setVisibility(View.VISIBLE);
        }
        // 是否我品店招
        if ("Y".equals(mitValcheckterM.getIfmine())) {
            zdzs_sayhi_rl_wopindianzhao.setVisibility(View.VISIBLE);
        }
        // 销售产品范围
        if ("Y".equals(mitValcheckterM.getSalesarea())) {
            zdzs_sayhi_rl_selfstatus.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_cmpstatus.setVisibility(View.VISIBLE);
        }
        // 终端合作状态
        if ("Y".equals(mitValcheckterM.getTerworkstatus())) {
            zdzs_sayhi_rl_selfprotocol.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_cmpprotocol.setVisibility(View.VISIBLE);
        }

        // 终端编码
        if ("Y".equals(mitValcheckterM.getTerminalcode())) {
            zdzs_sayhi_rl_termcode.setVisibility(View.VISIBLE);
        }
        // 所属路线
        if ("Y".equals(mitValcheckterM.getRoutecode())) {
            zdzs_sayhi_rl_belongline.setVisibility(View.VISIBLE);
        }
        // 终端名称
        if ("Y".equals(mitValcheckterM.getTername())) {
            zdzs_sayhi_rl_termname.setVisibility(View.VISIBLE);
        }
        // 终端等级
        if ("Y".equals(mitValcheckterM.getTerlevel())) {
            zdzs_sayhi_rl_level.setVisibility(View.VISIBLE);
        }
        // 县
        if ("Y".equals(mitValcheckterM.getCountry())) {
            zdzs_sayhi_rl_prov.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_city.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_country.setVisibility(View.VISIBLE);
        }
        // 地址
        if ("Y".equals(mitValcheckterM.getAddress())) {
            zdzs_sayhi_rl_address.setVisibility(View.VISIBLE);
        }
        // 联系人
        if ("Y".equals(mitValcheckterM.getContact())) {
            zdzs_sayhi_rl_person.setVisibility(View.VISIBLE);
        }
        // 电话
        if ("Y".equals(mitValcheckterM.getMobile())) {
            zdzs_sayhi_rl_tel.setVisibility(View.VISIBLE);
        }
        // 拜访周期
        if ("Y".equals(mitValcheckterM.getCylie())) {
            zdzs_sayhi_rl_cycle.setVisibility(View.VISIBLE);
        }
        // 拜访顺序
        if ("Y".equals(mitValcheckterM.getVisitorder())) {
            zdzs_sayhi_rl_sequence.setVisibility(View.VISIBLE);
        }
        // 高档容量
        if ("Y".equals(mitValcheckterM.getHvolume())) {
            zdzs_sayhi_rl_hvolume.setVisibility(View.VISIBLE);
        }
        // 中档容量
        if ("Y".equals(mitValcheckterM.getZvolume())) {
            zdzs_sayhi_rl_mvolume.setVisibility(View.VISIBLE);
        }
        // 普档容量
        if ("Y".equals(mitValcheckterM.getPvolume())) {
            zdzs_sayhi_rl_pvolume.setVisibility(View.VISIBLE);
        }
        // 底档容量
        if ("Y".equals(mitValcheckterM.getLvolume())) {
            zdzs_sayhi_rl_lvolume.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_totalvolume.setVisibility(View.VISIBLE);// 总容量
        }
        // 区域类型
        if ("Y".equals(mitValcheckterM.getAreatype())) {
            zdzs_sayhi_rl_areatype.setVisibility(View.VISIBLE);
        }
        // 销售次渠道
        if ("Y".equals(mitValcheckterM.getSellchannel())) {
            zdzs_sayhi_rl_sellchannel.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_mainchannel.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_minorchannel.setVisibility(View.VISIBLE);
        }
        // 拜访对象
        if ("Y".equals(mitValcheckterM.getVisituser())) {
            zdzs_sayhi_rl_visitperson.setVisibility(View.VISIBLE);
        }
    }

    // 子线程查找数据
    private void initViewData() {

        // 弹出提示对话框
        Message message = new Message();
        message.what = SHOW_INIT_PROGRESS;
        handler.sendMessage(message);// 提示:图片正在保存,请稍后

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Message message = new Message();
                    message.what = CLOSE_INIT_PROGRESS;
                    handler.sendMessage(message);// 提示:图片正在保存,请稍后
                }
            }
        };
        thread.start();
    }

    private AlertDialog dialog;

    /**
     * 展示滚动条
     */
    public void showXtSayHiDialog() {
        dialog = new AlertDialog.Builder(getActivity()).setCancelable(false).create();
        dialog.setView(getActivity().getLayoutInflater().inflate(R.layout.dealwith_progress, null), 0, 0, 0, 0);
        dialog.setCancelable(false); // 是否可以通过返回键 关闭
        dialog.show();
    }

    public void closeXtSayHiDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        //gridAdapter.notifyDataSetChanged();
    }

    // 子线程-初始化数据
    private void initData() {

        termInfoTemp = zsSayhiService.findTermTempById(termId);// 终端临时表记录
        // visitMTemp = zsSayhiService.findVisitTempById(visitId);// 拜访临时表记录
        mitValterMTemp = zsSayhiService.findMitValterMTempById(mitValterMTempKey);// 追溯临时表记录


        /*// 路线集合
        mstRouteList = xtSayhiService.initXtMstRoute(termId);
        // 终端等级集合
        dataDicByTermLevelLst = xtSayhiService.initDataDicByTermLevel();
        // 区域类型集合
        dataDicByAreaTypeLst = xtSayhiService.initDataDicByAreaType();
        // 销售渠道集合
        sellchanneldataLst = xtSayhiService.initDataDicBySellChannel();
        visitpositionLst = xtSayhiService.initVisitPosition();
        // 销售渠道集合
        sellchannelLst = getSellChannelList(sellchanneldataLst);
        // 主渠道集合
        mainchannelLst = getMainchannelLst(sellchannelLst);*/

        //设置时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            aday = "0" + day;
        } else {
            aday = Integer.toString(day);
        }
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 把选择控件也设置成系统时间
        Date date = calendar.getTime();
        ifminedate = sDateFormat.format(date);
    }

    // UI线程-展示控件数据(原使用拜访临时表)
    /*private void initData2() {
        // 是否有效终端
        if (ConstValues.FLAG_1.equals(termInfoTemp.getStatus())) {
            zdzs_sayhi_tv_termstatus_con1.setText("是");
        } else {
            zdzs_sayhi_tv_termstatus_con1.setText("否");
        }

        // 设置界面数据
        if (visitMTemp != null) {
            // 是否有效拜访 //进行判断(如果Status拜访状态为1 或者 拜访状态不为空设置为选中状态)
            if (ConstValues.FLAG_1.equals(visitMTemp.getStatus()) || CheckUtil.isBlankOrNull(visitMTemp.getStatus())) {
                zdzs_sayhi_tv_visitstatus_con1.setText("是");
            } else {
                zdzs_sayhi_tv_visitstatus_con1.setText("否");
            }

            // 是否我品店招
            if (ConstValues.FLAG_1.equals(termInfoTemp.getIfmine())) {
                zdzs_sayhi_rl_wopindianzhao_con1.setText("是");
                zdzs_sayhi_rl_wopindianzhao_con2.setVisibility(View.VISIBLE);
                zdzs_sayhi_rl_wopindianzhao_con2.setText(termInfoTemp.getIfminedate());
            } else {//当店招未选中的时候,隐藏对应的控件 文字
                zdzs_sayhi_rl_wopindianzhao_con1.setText("否");
                zdzs_sayhi_rl_wopindianzhao_con2.setText(ifminedate);
                zdzs_sayhi_rl_wopindianzhao_con2.setVisibility(View.GONE);
            }

            //销售产品范围 我品: 是1 否0
            if(ConstValues.FLAG_1.equals(visitMTemp.getIsself())){
                zdzs_sayhi_rl_selfstatus_con1.setText("是");
            }else {
                zdzs_sayhi_rl_selfstatus_con1.setText("否");
            }
            //销售产品范围 竞品: 是1 否0
            if(ConstValues.FLAG_1.equals(visitMTemp.getIscmp())){
                zdzs_sayhi_rl_cmpstatus_con1.setText("是");
            }else {
                zdzs_sayhi_rl_cmpstatus_con1.setText("否");
            }
            // 终端合作状态 我品: 是1 否0
            if(ConstValues.FLAG_1.equals(termInfoTemp.getSelftreaty())){
                zdzs_sayhi_rl_selfprotocol_con1.setText("是");
            }else {
                zdzs_sayhi_rl_selfprotocol_con1.setText("否");
            }
            // 终端合作状态 竞品: 是1 否0
            if(ConstValues.FLAG_1.equals(termInfoTemp.getCmpselftreaty())){
                zdzs_sayhi_rl_cmpprotocol_con1.setText("是");
            }else {
                zdzs_sayhi_rl_cmpprotocol_con1.setText("否");
            }

            // 拜访对象
            zdzs_sayhi_rl_visitperson_con1.setText(visitMTemp.getVisituser());
        }

        if (termInfoTemp != null) {

            // 保留修改关的拜访顺序，用于判定是不更改同线路下的各终端的拜访顺序
            //prevSequence = termInfoTemp.getSequence();
            zdzs_sayhi_rl_termcode_con1.setText(termInfoTemp.getTerminalcode());
            zdzs_sayhi_rl_termname_con1.setText(termInfoTemp.getTerminalname());
            zdzs_sayhi_rl_address_con1.setText(termInfoTemp.getAddress());
            zdzs_sayhi_rl_person_con1.setText(termInfoTemp.getContact());
            zdzs_sayhi_rl_tel_con1.setText(termInfoTemp.getMobile());
            zdzs_sayhi_rl_sequence_con1.setText(termInfoTemp.getSequence());
            zdzs_sayhi_rl_cycle_con1.setText(termInfoTemp.getCycle());
            // 高中普低,总
            Long tvolume = 0l;
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(termInfoTemp.getHvolume()))) {
                zdzs_sayhi_rl_hvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getHvolume()));
            } else {
                zdzs_sayhi_rl_hvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getHvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((termInfoTemp.getHvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(termInfoTemp.getMvolume()))) {
                zdzs_sayhi_rl_mvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getMvolume()));
            } else {
                zdzs_sayhi_rl_mvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getMvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((termInfoTemp.getMvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(termInfoTemp.getPvolume()))) {
                zdzs_sayhi_rl_pvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getPvolume()));
            } else {
                zdzs_sayhi_rl_pvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getPvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((termInfoTemp.getPvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(termInfoTemp.getLvolume()))) {
                zdzs_sayhi_rl_lvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getLvolume()));
            } else {
                zdzs_sayhi_rl_lvolume_con1.setText(FunUtil.isNullToZero(termInfoTemp.getLvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero(termInfoTemp.getLvolume());
            }
            zdzs_sayhi_rl_totalvolume_con1.setText(String.valueOf(tvolume));

            // 所属线路
            zdzs_sayhi_rl_belongline_con1.setText(xtSayhiService.getRouteName(termInfoTemp.getRoutekey()));

            // 区域类型
            zdzs_sayhi_rl_areatype_con1.setText(xtSayhiService.getDatadicName(termInfoTemp.getAreatype()));

            // 老板老板娘
            //xttermpersion.setText(visitMTemp.getVisitposition());
            zdzs_sayhi_rl_visitperson_con1.setText(visitMTemp.getVisituser());

            // 终端等级
            zdzs_sayhi_rl_level_con1.setText(xtSayhiService.getDatadicName(termInfoTemp.getTlevel()));

            // 销售渠道
            zdzs_sayhi_rl_sellchannel_con1.setText(xtSayhiService.getDatadicName(termInfoTemp.getSellchannel()));
            zdzs_sayhi_rl_mainchannel_con1.setText(xtSayhiService.getDatadicName(termInfoTemp.getMainchannel()));
            zdzs_sayhi_rl_minorchannel_con1.setText(xtSayhiService.getDatadicName(termInfoTemp.getMinorchannel()));

            // 获取省市县数据
            zdzs_sayhi_rl_prov_con1.setText(xtSayhiService.getAreaName(termInfoTemp.getProvince()));
            zdzs_sayhi_rl_city_con1.setText(xtSayhiService.getAreaName(termInfoTemp.getCity()));
            zdzs_sayhi_rl_country_con1.setText(xtSayhiService.getAreaName(termInfoTemp.getCounty()));


        }
        PrefUtils.putBoolean(getActivity(),GlobalValues.SAYHIREADY,true);
    }*/

    // UI线程-展示控件数据(现使用追溯临时表)
    private void initData3() {

        // 是否有效终端
        //if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidter())) {
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidter())) {
            zdzs_sayhi_tv_termstatus_con1.setText("是");
        } else {
            zdzs_sayhi_tv_termstatus_con1.setText("否");
        }

        // 设置界面数据
        /*if (visitMTemp != null) {

        }*/

        // 是否有效拜访 //进行判断(如果Status拜访状态为1 或者 拜访状态不为空设置为选中状态)
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidvisit()) || !CheckUtil.isBlankOrNull(mitValterMTemp.getVidvisit())) {
            zdzs_sayhi_tv_visitstatus_con1.setText("是");
        } else {
            zdzs_sayhi_tv_visitstatus_con1.setText("否");
        }

        // 是否我品店招
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidifmine())) {
            zdzs_sayhi_rl_wopindianzhao_con1.setText("是");
            zdzs_sayhi_rl_wopindianzhao_con2.setVisibility(View.VISIBLE);
            zdzs_sayhi_rl_wopindianzhao_con2.setText(mitValterMTemp.getVidifminedate());
        } else {//当店招未选中的时候,隐藏对应的控件 文字
            zdzs_sayhi_rl_wopindianzhao_con1.setText("否");
            zdzs_sayhi_rl_wopindianzhao_con2.setText(ifminedate);
            zdzs_sayhi_rl_wopindianzhao_con2.setVisibility(View.GONE);
        }

        //销售产品范围 我品: 是1 否0
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidisself())) {
            zdzs_sayhi_rl_selfstatus_con1.setText("是");
        } else {
            zdzs_sayhi_rl_selfstatus_con1.setText("否");
        }
        //销售产品范围 竞品: 是1 否0
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidisself())) {
            zdzs_sayhi_rl_cmpstatus_con1.setText("是");
        } else {
            zdzs_sayhi_rl_cmpstatus_con1.setText("否");
        }
        // 终端合作状态 我品: 是1 否0
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidselftreaty())) {
            zdzs_sayhi_rl_selfprotocol_con1.setText("是");
        } else {
            zdzs_sayhi_rl_selfprotocol_con1.setText("否");
        }
        // 终端合作状态 竞品: 是1 否0
        if (ConstValues.FLAG_1.equals(mitValterMTemp.getVidcmptreaty())) {
            zdzs_sayhi_rl_cmpprotocol_con1.setText("是");
        } else {
            zdzs_sayhi_rl_cmpprotocol_con1.setText("否");
        }

        // 拜访对象
        //zdzs_sayhi_rl_visitperson_con1.setText(mitValterMTemp.getVidvisituser());
        String visitposition = mitValterMTemp.getVidvisituser();
        if (!("-1".equals(visitposition) || "66AA9D3A55374232891C964350610930".equals(visitposition))) { //"-1",其他 根据原先用户是什么,不做处理
            String visitpositionName = zsSayhiService.getVisitpositionName(visitposition);
            zdzs_sayhi_rl_visitperson_con1.setText(visitpositionName);
        } else {
            zdzs_sayhi_rl_visitperson_con1.setText(mitValterMTemp.getVidvisitotherval());// 其他
        }


        if (termInfoTemp != null) {

            // 保留修改关的拜访顺序，用于判定是不更改同线路下的各终端的拜访顺序
            //prevSequence = termInfoTemp.getSequence();
            zdzs_sayhi_rl_termcode_con1.setText(mitValterMTemp.getVidterminalcode());
            zdzs_sayhi_rl_termname_con1.setText(mitValterMTemp.getVidtername());
            zdzs_sayhi_rl_address_con1.setText(mitValterMTemp.getVidaddress());
            zdzs_sayhi_rl_person_con1.setText(mitValterMTemp.getVidcontact());
            zdzs_sayhi_rl_tel_con1.setText(mitValterMTemp.getVidmobile());
            zdzs_sayhi_rl_sequence_con1.setText(mitValterMTemp.getVidsequence());
            zdzs_sayhi_rl_cycle_con1.setText(mitValterMTemp.getVidcycle());
            // 高中普低,总
            Long tvolume = 0l;
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(mitValterMTemp.getVidhvolume()))) {
                zdzs_sayhi_rl_hvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidhvolume()));
            } else {
                zdzs_sayhi_rl_hvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidhvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((mitValterMTemp.getVidhvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(mitValterMTemp.getVidzvolume()))) {
                zdzs_sayhi_rl_mvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidzvolume()));
            } else {
                zdzs_sayhi_rl_mvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidzvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((mitValterMTemp.getVidzvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(mitValterMTemp.getVidpvolume()))) {
                zdzs_sayhi_rl_pvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidpvolume()));
            } else {
                zdzs_sayhi_rl_pvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidpvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero((mitValterMTemp.getVidpvolume()));
            }
            if (ConstValues.FLAG_0.equals(FunUtil.isNullToZero(mitValterMTemp.getVidlvolume()))) {
                zdzs_sayhi_rl_lvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidlvolume()));
            } else {
                zdzs_sayhi_rl_lvolume_con1.setText(FunUtil.isNullToZero(mitValterMTemp.getVidlvolume()));
                tvolume = tvolume + FunUtil.isNullSetZero(mitValterMTemp.getVidlvolume());
            }
            zdzs_sayhi_rl_totalvolume_con1.setText(String.valueOf(tvolume));

            // 所属线路
            zdzs_sayhi_rl_belongline_con1.setText(zsSayhiService.getRouteName(mitValterMTemp.getVidroutekey()));

            // 区域类型
            zdzs_sayhi_rl_areatype_con1.setText(zsSayhiService.getDatadicName(mitValterMTemp.getVidareatype()));

            // 老板老板娘
            //xttermpersion.setText(visitMTemp.getVisitposition());
            //zdzs_sayhi_rl_visitperson_con1.setText(mitValterMTemp.getVidvisituser());

            // 终端等级
            zdzs_sayhi_rl_level_con1.setText(zsSayhiService.getDatadicName(termInfoTemp.getTlevel()));

            // 销售渠道
            zdzs_sayhi_rl_sellchannel_con1.setText(zsSayhiService.getDatadicName(termInfoTemp.getSellchannel()));
            zdzs_sayhi_rl_mainchannel_con1.setText(zsSayhiService.getDatadicName(termInfoTemp.getMainchannel()));
            zdzs_sayhi_rl_minorchannel_con1.setText(zsSayhiService.getDatadicName(mitValterMTemp.getVidminchannel()));

            // 获取省市县数据
            zdzs_sayhi_rl_prov_con1.setText(zsSayhiService.getAreaName(termInfoTemp.getProvince()));
            zdzs_sayhi_rl_city_con1.setText(zsSayhiService.getAreaName(termInfoTemp.getCity()));
            zdzs_sayhi_rl_country_con1.setText(zsSayhiService.getAreaName(mitValterMTemp.getVidcountry()));

        }
        ;

        // 是否有效终端
        zdzs_sayhi_tv_termstatus_statue.setText(getYdInfo(mitValterMTemp.getVidterflag()));
        zdzs_sayhi_tv_termstatus_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidterflag()));

        // 是否有效拜访
        zdzs_sayhi_tv_visitstatus_statue.setText(getYdInfo(mitValterMTemp.getVidvisitflag()));
        zdzs_sayhi_tv_visitstatus_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidvisitflag()));

        // 是否我品店招  d
        zdzs_sayhi_rl_wopindianzhao_statue.setText(getYdInfo(mitValterMTemp.getVidifmineflag()));
        zdzs_sayhi_rl_wopindianzhao_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidifmineflag()));

        // 我品销售范围
        zdzs_sayhi_rl_selfstatus_statue.setText(getYdInfo(mitValterMTemp.getVidisselfflag()));
        zdzs_sayhi_rl_selfstatus_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidisselfflag()));
        // 竞品销售
        zdzs_sayhi_rl_cmpstatus_statue.setText(getYdInfo(mitValterMTemp.getVidiscmpflag()));
        zdzs_sayhi_rl_cmpstatus_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidiscmpflag()));
        // 我品合作
        zdzs_sayhi_rl_selfprotocol_statue.setText(getYdInfo(mitValterMTemp.getVidselftreatyflag()));
        zdzs_sayhi_rl_selfprotocol_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidselftreatyflag()));
        // 竞品合作
        zdzs_sayhi_rl_cmpprotocol_statue.setText(getYdInfo(mitValterMTemp.getVidcmptreatyflag()));
        zdzs_sayhi_rl_cmpprotocol_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidcmptreatyflag()));

        // 终端名称
        zdzs_sayhi_rl_termname_statue.setText(getYdInfo(mitValterMTemp.getVidternameflag()));
        zdzs_sayhi_rl_termname_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidternameflag()));
        // 终端编码
        zdzs_sayhi_rl_termcode_statue.setText(getYdInfo(mitValterMTemp.getVidtercodeflag()));
        zdzs_sayhi_rl_termcode_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidtercodeflag()));
        // 路线
        zdzs_sayhi_rl_belongline_statue.setText(getYdInfo(mitValterMTemp.getVidrtekeyflag()));
        zdzs_sayhi_rl_belongline_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidrtekeyflag()));
        // 终端等级
        zdzs_sayhi_rl_level_statue.setText(getYdInfo(mitValterMTemp.getVidtervidterlevelflag()));//
        zdzs_sayhi_rl_level_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidtervidterlevelflag()));

        // 县
        zdzs_sayhi_rl_country_statue.setText(getYdInfo(mitValterMTemp.getVidcountryflag()));
        zdzs_sayhi_rl_country_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidcountryflag()));

        zdzs_sayhi_rl_address_statue.setText(getYdInfo(mitValterMTemp.getVidaddressflag()));
        zdzs_sayhi_rl_address_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidaddressflag()));

        zdzs_sayhi_rl_person_statue.setText(getYdInfo(mitValterMTemp.getVidcontactflag()));
        zdzs_sayhi_rl_person_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidcontactflag()));

        zdzs_sayhi_rl_tel_statue.setText(getYdInfo(mitValterMTemp.getVidmobileflag()));
        zdzs_sayhi_rl_tel_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidmobileflag()));

        zdzs_sayhi_rl_cycle_statue.setText(getYdInfo(mitValterMTemp.getVidcycleflag()));
        zdzs_sayhi_rl_cycle_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidcycleflag()));

        zdzs_sayhi_rl_sequence_statue.setText(getYdInfo(mitValterMTemp.getVidsequenceflag()));
        zdzs_sayhi_rl_sequence_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidsequenceflag()));

        zdzs_sayhi_rl_hvolume_statue.setText(getYdInfo(mitValterMTemp.getVidhvolumeflag()));
        zdzs_sayhi_rl_hvolume_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidhvolumeflag()));

        zdzs_sayhi_rl_mvolume_statue.setText(getYdInfo(mitValterMTemp.getVidzvolumeflag()));
        zdzs_sayhi_rl_mvolume_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidzvolumeflag()));

        zdzs_sayhi_rl_pvolume_statue.setText(getYdInfo(mitValterMTemp.getVidpvolumeflag()));
        zdzs_sayhi_rl_pvolume_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidpvolumeflag()));

        zdzs_sayhi_rl_lvolume_statue.setText(getYdInfo(mitValterMTemp.getVidlvolumeflag()));
        zdzs_sayhi_rl_lvolume_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidlvolumeflag()));

        zdzs_sayhi_rl_totalvolume_statue.setText(getYdInfo(mitValterMTemp.getVidterflag()));//
        zdzs_sayhi_rl_totalvolume_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidterflag()));

        zdzs_sayhi_rl_areatype_statue.setText(getYdInfo(mitValterMTemp.getVidareatypeflag()));
        zdzs_sayhi_rl_areatype_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidareatypeflag()));

        // 次渠道
        zdzs_sayhi_rl_minorchannel_statue.setText(getYdInfo(mitValterMTemp.getVidminchannelflag()));
        zdzs_sayhi_rl_minorchannel_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidminchannelflag()));

        zdzs_sayhi_rl_visitperson_statue.setText(getYdInfo(mitValterMTemp.getVidvisituserflag()));
        zdzs_sayhi_rl_visitperson_statue.setTextColor(getYdInfoColor(mitValterMTemp.getVidvisituserflag()));

        PrefUtils.putBoolean(getActivity(), GlobalValues.SAYHIREADY, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;

            case R.id.zdzs_sayhi_rl_termstatus:
                alertShow3(mitValterMTemp, "是否有效终端", "vidter", "",
                        "", "setVidterflag", "setVidterremaek");
                break;
            case R.id.zdzs_sayhi_rl_visitstatus:
                alertShow3(mitValterMTemp, "是否有效拜访", "vidvisit", "",
                        "", "setVidvisitflag", "setVidvisitremark");
                break;
            //
            case R.id.zdzs_sayhi_rl_wopindianzhao:
                alertShow3(mitValterMTemp, "是否我品店招", "vidifmine", "",
                        "", "setVidifmineflag", "setVidifminermark");
                break;

            // 是否我品销售范围
            case R.id.zdzs_sayhi_rl_selfstatus:
                alertShow3(mitValterMTemp, "是否我品销售范围", "vidisself", "",
                        "", "setVidisselfflag", "setVidisselfremark");
                break;

            // 是否竞品销售范围
            case R.id.zdzs_sayhi_rl_cmpstatus:
                alertShow3(mitValterMTemp, "是否竞品销售范围", "vidiscmp", "",
                        "", "setVidiscmpflag", "setVidiscmpremark");
                break;

            // 是否我品终端合作
            case R.id.zdzs_sayhi_rl_selfprotocol:
                alertShow3(mitValterMTemp, "是否我品终端合作", "vidselftreaty", "",
                        "", "setVidselftreatyflag", "setVidselftreatyremark");
                break;

            // 是否竞品终端合作
            case R.id.zdzs_sayhi_rl_cmpprotocol:
                alertShow3(mitValterMTemp, "是否竞品终端合作", "vidcmptreaty", "",
                        "", "setVidcmptreatyflag", "setVidcmptreatyremark");
                break;


            // 终端名称
            case R.id.zdzs_sayhi_rl_termname:
                alertShow3(mitValterMTemp, "终端名称", "vidtername", "vidternameval",
                        "setVidternameval", "setVidternameflag", "setVidternameremark");
                break;
            case R.id.zdzs_sayhi_rl_termcode:
                alertShow3(mitValterMTemp, "终端编码", "vidterminalcode", "vidtercodeval",
                        "setVidtercodeval", "setVidtercodeflag", "setVidtercoderemark");
                break;
            case R.id.zdzs_sayhi_rl_address:
                alertShow3(mitValterMTemp, "地址", "vidaddress", "vidaddressval",
                        "setVidaddressval", "setVidaddressflag", "setVidaddressremark");
                break;
            case R.id.zdzs_sayhi_rl_person:
                alertShow3(mitValterMTemp, "联系人", "vidcontact", "vidcontactval",
                        "setVidcontactval", "setVidcontactflag", "setVidcontactremark");
                break;
            case R.id.zdzs_sayhi_rl_tel:
                alertShow3(mitValterMTemp, "电话", "vidmobile", "vidmobileval",
                        "setVidmobileval", "setVidmobileflag", "setVidmobileremark");
                break;
            case R.id.zdzs_sayhi_rl_sequence:
                alertShow3(mitValterMTemp, "拜访顺序", "vidsequence", "vidsequenceval",
                        "setVidsequenceval", "setVidsequenceflag", "setVidvidsequenceremark");
                break;


            case R.id.zdzs_sayhi_rl_cycle:
                alertShow3(mitValterMTemp, "拜访周期", "vidcycle", "vidcycleval",
                        "setVidcycleval", "setVidcycleflag", "setVidcycleremark2");
                break;
            case R.id.zdzs_sayhi_rl_hvolume:
                alertShow3(mitValterMTemp, "高档容量", "vidhvolume", "vidhvolumeval",
                        "setVidhvolumeval", "setVidhvolumeflag", "setVidhvolumeremark");
                break;
            case R.id.zdzs_sayhi_rl_mvolume:
                alertShow3(mitValterMTemp, "中档容量", "vidzvolume", "vidzvolumeval",
                        "setVidzvolumeval", "setVidzvolumeflag", "setVidxvolumeremark");
                break;
            case R.id.zdzs_sayhi_rl_pvolume:
                alertShow3(mitValterMTemp, "普档容量", "vidpvolume", "vidpvolumeval",
                        "setVidpvolumeval", "setVidpvolumeflag", "setVidpvolumeremark");
                break;
            case R.id.zdzs_sayhi_rl_lvolume:
                alertShow3(mitValterMTemp, "低档容量", "vidlvolume", "vidlvolumeval",
                        "setVidlvolumeval", "setVidlvolumeflag", "setVidlvolumeremark");
                break;


            case R.id.zdzs_sayhi_rl_belongline:
                alertShow3(mitValterMTemp, "所属路线", "vidroutekey", "vidrtekeyval",
                        "setVidrtekeyval", "setVidrtekeyflag", "setVidroutremark");
                break;
            case R.id.zdzs_sayhi_rl_level:
                alertShow3(mitValterMTemp, "终端等级", "vidterlevel", "vidtervidterlevelval",
                        "setVidtervidterlevelval", "setVidtervidterlevelflag", "setidtervidterlevelremark");
                break;
            case R.id.zdzs_sayhi_rl_country:
                alertShow4(mitValterMTemp, "县", "vidcountry", "vidcountryval",
                        termInfoTemp.getCity(),
                        "setVidcountryval", "setVidcountryflag", "setVidcountryremark");
                break;
            case R.id.zdzs_sayhi_rl_areatype:
                alertShow3(mitValterMTemp, "区域类型", "vidareatype", "vidareatypeval",
                        "setVidareatypeval", "setVidareatypeflag", "setVidareatyperemark");
                break;
            case R.id.zdzs_sayhi_rl_minorchannel:
                alertShow4(mitValterMTemp, "次渠道", "vidminchannel", "vidminchannelval",
                        termInfoTemp.getMainchannel(),
                        "setVidminchannelval", "setVidminchannelflag", "setVidminchannelremark");
                break;
            case R.id.zdzs_sayhi_rl_visitperson:
                alertShow3(mitValterMTemp, "拜访对象", "vidvisituser", "vidvisituserval",
                        "setVidvisituserval", "setVidvisituserflag", "setVidvisituserremark");
                break;

            default:
                break;
        }
    }

    /**
     * 正确 错误(去修正)
     * 参数1: 标题 ×
     * 参数2: 主体内容    ×
     * 参数3: 取消按钮    ×
     * 参数4: 高亮按钮 数组 √
     * 参数5: 普通按钮 数组 √
     * 参数6: 上下文 √
     * 参数7: 弹窗类型 (正常取消,确定按钮)   √
     * 参数8: 条目点击监听  √
     */
    public void alertShow3(final MitValterMTemp mitValterMTemp, final String titleName,
                           final String ydkey, final String onlyddkey,
                           final String ddkey, final String ddflag,
                           final String ddremark) {
        new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确
                            FunUtil.setFieldValue(mitValterMTemp, ddflag, "Y");
                            handler.sendEmptyMessage(ZsSayhiFragment.INIT_DATA);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putString("titleName", titleName);// 标题内容,比如:是否有效终端
                            bundle.putString("ydkey", ydkey);// 对象属性,比如: vidroutekey(属性)
                            bundle.putString("onlyddkey", onlyddkey);// 对象属性,比如: vidroutekey(属性)
                            bundle.putString("setDdValue", ddkey);// 对象方法名,比如: setVidrtekeyval(督导输入的值)
                            bundle.putString("setDdFlag", ddflag);// 对象方法名,比如: setVidrtekeyflag(正确与否)
                            bundle.putString("setDdRemark", ddremark);// 对象方法名,比如: setVidroutremark(备注)
                            //bundle.putString("type", type);// 页面类型,比如: "1"
                            bundle.putString("termId", termId);// 页面类型,比如: "1"
                            bundle.putString("mitValterMTempKey", mitValterMTempKey);
                            bundle.putSerializable("mitValterMTemp", mitValterMTemp);
                            ZsSayhiAmendFragment zsSayhiAmendFragment = new ZsSayhiAmendFragment(handler);
                            zsSayhiAmendFragment.setArguments(bundle);
                            ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            zsVisitShopActivity.changeXtvisitFragment(zsSayhiAmendFragment, "zssayhiamendfragment");
                        }

                    }
                }).setCancelable(true).show();
    }


    // 需要传递县,次渠道父id的弹窗
    public void alertShow4(final MitValterMTemp mitValterMTemp, final String titleName,
                           final String ydkey, final String onlyddkey, final String ydparentkey,
                           final String ddkey, final String ddflag,
                           final String ddremark//,final String type
    ) {
        new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确
                            FunUtil.setFieldValue(mitValterMTemp, ddflag, "Y");
                            handler.sendEmptyMessage(ZsSayhiFragment.INIT_DATA);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putString("titleName", titleName);// 标题内容,比如:是否有效终端
                            bundle.putString("ydkey", ydkey);// 对象属性,比如: vidroutekey(属性)
                            bundle.putString("onlyddkey", onlyddkey);// 对象属性,比如: vidroutekey(属性)
                            bundle.putString("ydparentkey", ydparentkey);// 对象属性,比如: vidroutekey(属性)
                            bundle.putString("setDdValue", ddkey);// 对象方法名,比如: setVidrtekeyval(督导输入的值)
                            bundle.putString("setDdFlag", ddflag);// 对象方法名,比如: setVidrtekeyflag(正确与否)
                            bundle.putString("setDdRemark", ddremark);// 对象方法名,比如: setVidroutremark(备注)
                            //bundle.putString("type", type);// 页面类型,比如: "1"
                            bundle.putString("termId", termId);// 页面类型,比如: "1"
                            bundle.putString("mitValterMTempKey", mitValterMTempKey);
                            bundle.putSerializable("mitValterMTemp", mitValterMTemp);
                            ZsSayhiAmendFragment zsSayhiAmendFragment = new ZsSayhiAmendFragment(handler);
                            zsSayhiAmendFragment.setArguments(bundle);
                            ZsVisitShopActivity zsVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            zsVisitShopActivity.changeXtvisitFragment(zsSayhiAmendFragment, "zsamendfragment");
                        }

                    }
                }).setCancelable(true).show();
    }

    /***
     * 删除无效渠道
     *
     * @param kvStcList
     * @return
     */
    private List<KvStc> getSellChannelList(List<KvStc> kvStcList) {
        List<KvStc> list = new ArrayList<KvStc>();
        for (KvStc kvStc : kvStcList) {
            if (!DbtUtils.getInvalidChannelList().contains(kvStc.getKey())) {
                list.add(kvStc);
            }
        }
        return list;
    }

    /**
     * 获取主渠道集合
     *
     * @param sellchannelLst 销售渠道集合
     * @return
     */
    private List<KvStc> getMainchannelLst(List<KvStc> sellchannelLst) {
        KvStc sell = new KvStc();
        for (KvStc kvstc : sellchannelLst) {
            if (kvstc.getKey().equals(termInfoTemp.getSellchannel())) {
                sell = kvstc;
            }
        }
        // 返回某个销售渠道下的主渠道集合
        return getSellChannelList(sell.getChildLst());
    }

    /**
     * 获取次渠道集合
     *
     * @param mainchannelLst 主渠道集合
     * @return
     */
    private List<KvStc> getMinorchannelLst(List<KvStc> mainchannelLst) {
        KvStc minor = new KvStc();
        for (KvStc kvstc : mainchannelLst) {
            if (kvstc.getKey().equals(termInfoTemp.getMainchannel())) {
                minor = kvstc;
            }
        }
        // 返回某个主渠道下的次渠道集合
        return getSellChannelList(minor.getChildLst());
    }


    /*@Override
    public void onItemClick(Object o, int position) {
    }

    @Override
    public void onDismiss(Object o) {
    }*/

    @Override
    //失去焦点时调用
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");
        Long time5 = new Date().getTime();

        // 如果是查看操作，则不做数据校验及数据处理
        if (ConstValues.FLAG_1.equals(seeFlag))
            return;

        if (!PrefUtils.getBoolean(getActivity(), GlobalValues.SAYHIREADY, false))
            return;

        // 将追溯数据 保存到追溯主表临时表中
        zsSayhiService.updateMitValterMTemp(mitValterMTemp);

        Long time6 = new Date().getTime();
        Log.e("Optimization", "保存数据" + (time6 - time5));
    }

    // 判定业代录入的状态
    private String getYdInfo(String flag) {
        String con = "";
        if ("N".equals(flag)) {
            con = "错误";
        } else if ("Y".equals(flag)) {
            con = "正确";
        } else {
            con = "未稽查";
        }
        return con;
    }

    private int getYdInfoColor(String flag) {
        int color;
        if ("N".equals(flag)) {
            color = getResources().getColor(R.color.zdzs_dd_error);
        } else if ("Y".equals(flag)) {
            color = getResources().getColor(R.color.zdzs_dd_yes);
        } else {
            color = getResources().getColor(R.color.gray_color_666666);
        }
        return color;
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
