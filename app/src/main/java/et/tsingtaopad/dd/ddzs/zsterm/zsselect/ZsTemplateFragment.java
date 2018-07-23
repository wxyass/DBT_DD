package et.tsingtaopad.dd.ddzs.zsterm.zsselect;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.db.table.MitValcheckterM;
import et.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;

/**
 * 督导模板
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsTemplateFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "ZsTemplateFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_TEMPLATE_SUC = 2001;//
    public static final int DD_TEMPLATE_FAIL = 2002;//

    private XtTermSelectService xtSelectService;
    private List<MitValcheckterM> mitValcheckterMs;
    private MitValcheckterM mitValcheckterM;


    private ScrollView template_scrollview;
    private LinearLayout template_ll_vaildter;
    private CheckBox template_cb_vaildter;
    private LinearLayout template_ll_vaildvisit;
    private CheckBox common_multiple_cb_vaildvisit;
    private LinearLayout template_ll_ifmine;
    private CheckBox common_multiple_cb_ifmine;
    private LinearLayout template_ll_salesarea;
    private CheckBox common_multiple_cb_salesarea;
    private LinearLayout template_ll_terworkstatus;
    private CheckBox common_multiple_cb_terworkstatus;
    private LinearLayout template_ll_tername;
    private CheckBox common_multiple_cb_tername;
    private LinearLayout template_ll_terminalcode;
    private CheckBox common_multiple_cb_terminalcode;
    private LinearLayout template_ll_terlevel;
    private CheckBox common_multiple_cb_terlevel;
    private LinearLayout template_ll_routecode;
    private CheckBox common_multiple_cb_routecode;
    private LinearLayout template_ll_province;
    private CheckBox common_multiple_cb_province;
    private LinearLayout template_ll_city;
    private CheckBox common_multiple_cb_city;
    private LinearLayout template_ll_country;
    private CheckBox common_multiple_cb_country;
    private LinearLayout template_ll_address;
    private CheckBox common_multiple_cb_address;
    private LinearLayout template_ll_contact;
    private CheckBox common_multiple_cb_contact;
    private LinearLayout template_ll_mobile;
    private CheckBox common_multiple_cb_mobile;
    private LinearLayout template_ll_cylie;
    private CheckBox common_multiple_cb_cylie;
    private LinearLayout template_ll_visitorder;
    private CheckBox common_multiple_cb_visitorder;
    private LinearLayout template_ll_hvolume;
    private CheckBox common_multiple_cb_hvolume;
    private LinearLayout template_ll_zvolume;
    private CheckBox common_multiple_cb_zvolume;
    private LinearLayout template_ll_pvolume;
    private CheckBox common_multiple_cb_pvolume;
    private LinearLayout template_ll_lvolume;
    private CheckBox common_multiple_cb_lvolume;
    private LinearLayout template_ll_areatype;
    private CheckBox common_multiple_cb_areatype;
    private LinearLayout template_ll_sellchannel;
    private CheckBox common_multiple_cb_sellchannel;
    private LinearLayout template_ll_mainchannel;
    private CheckBox common_multiple_cb_mainchannel;
    private LinearLayout template_ll_minorchannel;
    private CheckBox common_multiple_cb_minorchannel;
    private LinearLayout template_ll_visituser;
    private CheckBox common_multiple_cb_visituser;
    private LinearLayout template_ll_addsupply;
    private CheckBox common_multiple_cb_addsupply;
    private LinearLayout template_ll_proerror;
    private CheckBox common_multiple_cb_proerror;
    private LinearLayout template_ll_agencyerror;
    private CheckBox common_multiple_cb_agencyerror;
    private LinearLayout template_ll_dataerror;
    private CheckBox common_multiple_cb_dataerror;
    private LinearLayout template_ll_iffleeing;
    private CheckBox common_multiple_cb_iffleeing;
    private LinearLayout template_ll_distrbution;
    private CheckBox common_multiple_cb_distrbution;
    private LinearLayout template_ll_goodsvivi;
    private CheckBox common_multiple_cb_goodsvivi;
    private LinearLayout template_ll_provivi;
    private CheckBox common_multiple_cb_provivi;
    private LinearLayout template_ll_icevivi;
    private CheckBox common_multiple_cb_icevivi;
    private LinearLayout template_ll_salespromotion;
    private CheckBox common_multiple_cb_salespromotion;
    private LinearLayout template_ll_grouppro;
    private CheckBox common_multiple_cb_grouppro;
    private LinearLayout template_ll_cooperation;
    private CheckBox common_multiple_cb_cooperation;
    private LinearLayout template_ll_highps;
    private CheckBox common_multiple_cb_highps;
    private LinearLayout template_ll_prooccupy;
    private CheckBox common_multiple_cb_prooccupy;
    private LinearLayout template_ll_addcmp;
    private CheckBox common_multiple_cb_addcmp;
    private LinearLayout template_ll_cmperror;
    private CheckBox common_multiple_cb_cmperror;
    private LinearLayout template_ll_cmpagencyerror;
    private CheckBox common_multiple_cb_cmpagencyerror;
    private LinearLayout template_ll_cmpdataerror;
    private CheckBox common_multiple_cb_cmpdataerror;
    private LinearLayout template_ll_ifcmp;
    private CheckBox common_multiple_cb_ifcmp;
    private LinearLayout template_ll_visinote;
    private CheckBox common_multiple_cb_visinote;
    private Button dd_template_bt_next;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_template, container, false);
        initView(view);
        // 初始化数据
        // initData();
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
        backBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);


        template_scrollview = (ScrollView) view.findViewById(R.id.template_scrollview);
        template_ll_vaildter = (LinearLayout)view. findViewById(R.id.template_ll_vaildter);
        template_cb_vaildter = (CheckBox) view.findViewById(R.id.template_cb_vaildter);
        template_ll_vaildvisit = (LinearLayout) view.findViewById(R.id.template_ll_vaildvisit);
        common_multiple_cb_vaildvisit = (CheckBox)view. findViewById(R.id.common_multiple_cb_vaildvisit);
        template_ll_ifmine = (LinearLayout) view.findViewById(R.id.template_ll_ifmine);
        common_multiple_cb_ifmine = (CheckBox) view.findViewById(R.id.common_multiple_cb_ifmine);
        template_ll_salesarea = (LinearLayout)view. findViewById(R.id.template_ll_salesarea);
        common_multiple_cb_salesarea = (CheckBox)view. findViewById(R.id.common_multiple_cb_salesarea);
        template_ll_terworkstatus = (LinearLayout)view. findViewById(R.id.template_ll_terworkstatus);
        common_multiple_cb_terworkstatus = (CheckBox) view.findViewById(R.id.common_multiple_cb_terworkstatus);
        template_ll_tername = (LinearLayout) view.findViewById(R.id.template_ll_tername);
        common_multiple_cb_tername = (CheckBox) view.findViewById(R.id.common_multiple_cb_tername);
        template_ll_terminalcode = (LinearLayout) view.findViewById(R.id.template_ll_terminalcode);
        common_multiple_cb_terminalcode = (CheckBox) view.findViewById(R.id.common_multiple_cb_terminalcode);
        template_ll_terlevel = (LinearLayout) view.findViewById(R.id.template_ll_terlevel);
        common_multiple_cb_terlevel = (CheckBox) view.findViewById(R.id.common_multiple_cb_terlevel);
        template_ll_routecode = (LinearLayout)view. findViewById(R.id.template_ll_routecode);
        common_multiple_cb_routecode = (CheckBox)view. findViewById(R.id.common_multiple_cb_routecode);
        template_ll_province = (LinearLayout) view.findViewById(R.id.template_ll_province);
        common_multiple_cb_province = (CheckBox) view.findViewById(R.id.common_multiple_cb_province);
        template_ll_city = (LinearLayout) view.findViewById(R.id.template_ll_city);
        common_multiple_cb_city = (CheckBox) view.findViewById(R.id.common_multiple_cb_city);
        template_ll_country = (LinearLayout)view. findViewById(R.id.template_ll_country);
        common_multiple_cb_country = (CheckBox)view. findViewById(R.id.common_multiple_cb_country);
        template_ll_address = (LinearLayout)view. findViewById(R.id.template_ll_address);
        common_multiple_cb_address = (CheckBox) view.findViewById(R.id.common_multiple_cb_address);
        template_ll_contact = (LinearLayout) view.findViewById(R.id.template_ll_contact);
        common_multiple_cb_contact = (CheckBox)view. findViewById(R.id.common_multiple_cb_contact);
        template_ll_mobile = (LinearLayout) view.findViewById(R.id.template_ll_mobile);
        common_multiple_cb_mobile = (CheckBox) view.findViewById(R.id.common_multiple_cb_mobile);
        template_ll_cylie = (LinearLayout)view. findViewById(R.id.template_ll_cylie);
        common_multiple_cb_cylie = (CheckBox) view.findViewById(R.id.common_multiple_cb_cylie);
        template_ll_visitorder = (LinearLayout) view.findViewById(R.id.template_ll_visitorder);
        common_multiple_cb_visitorder = (CheckBox)view. findViewById(R.id.common_multiple_cb_visitorder);
        template_ll_hvolume = (LinearLayout) view.findViewById(R.id.template_ll_hvolume);
        common_multiple_cb_hvolume = (CheckBox) view.findViewById(R.id.common_multiple_cb_hvolume);
        template_ll_zvolume = (LinearLayout)view. findViewById(R.id.template_ll_zvolume);
        common_multiple_cb_zvolume = (CheckBox) view.findViewById(R.id.common_multiple_cb_zvolume);
        template_ll_pvolume = (LinearLayout) view.findViewById(R.id.template_ll_pvolume);
        common_multiple_cb_pvolume = (CheckBox) view.findViewById(R.id.common_multiple_cb_pvolume);
        template_ll_lvolume = (LinearLayout) view.findViewById(R.id.template_ll_lvolume);
        common_multiple_cb_lvolume = (CheckBox)view. findViewById(R.id.common_multiple_cb_lvolume);
        template_ll_areatype = (LinearLayout) view.findViewById(R.id.template_ll_areatype);
        common_multiple_cb_areatype = (CheckBox) view.findViewById(R.id.common_multiple_cb_areatype);
        template_ll_sellchannel = (LinearLayout) view.findViewById(R.id.template_ll_sellchannel);
        common_multiple_cb_sellchannel = (CheckBox)view. findViewById(R.id.common_multiple_cb_sellchannel);
        template_ll_mainchannel = (LinearLayout) view.findViewById(R.id.template_ll_mainchannel);
        common_multiple_cb_mainchannel = (CheckBox) view.findViewById(R.id.common_multiple_cb_mainchannel);
        template_ll_minorchannel = (LinearLayout) view.findViewById(R.id.template_ll_minorchannel);
        common_multiple_cb_minorchannel = (CheckBox) view.findViewById(R.id.common_multiple_cb_minorchannel);
        template_ll_visituser = (LinearLayout) view.findViewById(R.id.template_ll_visituser);
        common_multiple_cb_visituser = (CheckBox)view. findViewById(R.id.common_multiple_cb_visituser);
        template_ll_addsupply = (LinearLayout)view. findViewById(R.id.template_ll_addsupply);
        common_multiple_cb_addsupply = (CheckBox)view. findViewById(R.id.common_multiple_cb_addsupply);
        template_ll_proerror = (LinearLayout) view.findViewById(R.id.template_ll_proerror);
        common_multiple_cb_proerror = (CheckBox) view.findViewById(R.id.common_multiple_cb_proerror);
        template_ll_agencyerror = (LinearLayout)view. findViewById(R.id.template_ll_agencyerror);
        common_multiple_cb_agencyerror = (CheckBox)view. findViewById(R.id.common_multiple_cb_agencyerror);
        template_ll_dataerror = (LinearLayout) view.findViewById(R.id.template_ll_dataerror);
        common_multiple_cb_dataerror = (CheckBox) view.findViewById(R.id.common_multiple_cb_dataerror);
        template_ll_iffleeing = (LinearLayout) view.findViewById(R.id.template_ll_iffleeing);
        common_multiple_cb_iffleeing = (CheckBox)view.findViewById(R.id.common_multiple_cb_iffleeing);
        template_ll_distrbution = (LinearLayout)view. findViewById(R.id.template_ll_distrbution);
        common_multiple_cb_distrbution = (CheckBox) view.findViewById(R.id.common_multiple_cb_distrbution);
        template_ll_goodsvivi = (LinearLayout) view.findViewById(R.id.template_ll_goodsvivi);
        common_multiple_cb_goodsvivi = (CheckBox)view. findViewById(R.id.common_multiple_cb_goodsvivi);
        template_ll_provivi = (LinearLayout) view.findViewById(R.id.template_ll_provivi);
        common_multiple_cb_provivi = (CheckBox)view. findViewById(R.id.common_multiple_cb_provivi);
        template_ll_icevivi = (LinearLayout)view. findViewById(R.id.template_ll_icevivi);
        common_multiple_cb_icevivi = (CheckBox) view.findViewById(R.id.common_multiple_cb_icevivi);
        template_ll_salespromotion = (LinearLayout) view.findViewById(R.id.template_ll_salespromotion);
        common_multiple_cb_salespromotion = (CheckBox) view.findViewById(R.id.common_multiple_cb_salespromotion);
        template_ll_grouppro = (LinearLayout) view.findViewById(R.id.template_ll_grouppro);
        common_multiple_cb_grouppro = (CheckBox)view. findViewById(R.id.common_multiple_cb_grouppro);
        template_ll_cooperation = (LinearLayout)view. findViewById(R.id.template_ll_cooperation);
        common_multiple_cb_cooperation = (CheckBox)view. findViewById(R.id.common_multiple_cb_cooperation);
        template_ll_highps = (LinearLayout) view.findViewById(R.id.template_ll_highps);
        common_multiple_cb_highps = (CheckBox)view. findViewById(R.id.common_multiple_cb_highps);
        template_ll_prooccupy = (LinearLayout) view.findViewById(R.id.template_ll_prooccupy);
        common_multiple_cb_prooccupy = (CheckBox) view.findViewById(R.id.common_multiple_cb_prooccupy);
        template_ll_addcmp = (LinearLayout) view.findViewById(R.id.template_ll_addcmp);
        common_multiple_cb_addcmp = (CheckBox) view.findViewById(R.id.common_multiple_cb_addcmp);
        template_ll_cmperror = (LinearLayout)view. findViewById(R.id.template_ll_cmperror);
        common_multiple_cb_cmperror = (CheckBox) view.findViewById(R.id.common_multiple_cb_cmperror);
        template_ll_cmpagencyerror = (LinearLayout)view. findViewById(R.id.template_ll_cmpagencyerror);
        common_multiple_cb_cmpagencyerror = (CheckBox) view.findViewById(R.id.common_multiple_cb_cmpagencyerror);
        template_ll_cmpdataerror = (LinearLayout) view.findViewById(R.id.template_ll_cmpdataerror);
        common_multiple_cb_cmpdataerror = (CheckBox)view. findViewById(R.id.common_multiple_cb_cmpdataerror);
        template_ll_ifcmp = (LinearLayout) view.findViewById(R.id.template_ll_ifcmp);
        common_multiple_cb_ifcmp = (CheckBox)view. findViewById(R.id.common_multiple_cb_ifcmp);
        template_ll_visinote = (LinearLayout)view. findViewById(R.id.template_ll_visinote);
        common_multiple_cb_visinote = (CheckBox) view.findViewById(R.id.common_multiple_cb_visinote);
        dd_template_bt_next = (Button) view.findViewById(R.id.dd_template_bt_next);

        template_ll_vaildter.setOnClickListener(this);
        template_ll_vaildvisit.setOnClickListener(this);
        template_ll_ifmine.setOnClickListener(this);
        template_ll_salesarea.setOnClickListener(this);
        template_ll_terworkstatus.setOnClickListener(this);
        template_ll_tername.setOnClickListener(this);
        template_ll_terminalcode.setOnClickListener(this);
        template_ll_terlevel.setOnClickListener(this);
        template_ll_routecode.setOnClickListener(this);
        template_ll_province.setOnClickListener(this);
        template_ll_city.setOnClickListener(this);
        template_ll_country.setOnClickListener(this);
        template_ll_address.setOnClickListener(this);
        template_ll_contact.setOnClickListener(this);
        template_ll_mobile.setOnClickListener(this);
        template_ll_cylie.setOnClickListener(this);
        template_ll_visitorder.setOnClickListener(this);
        template_ll_hvolume.setOnClickListener(this);
        template_ll_zvolume.setOnClickListener(this);
        template_ll_pvolume.setOnClickListener(this);
        template_ll_lvolume.setOnClickListener(this);
        template_ll_areatype.setOnClickListener(this);
        template_ll_sellchannel.setOnClickListener(this);
        template_ll_mainchannel.setOnClickListener(this);
        template_ll_minorchannel.setOnClickListener(this);
        template_ll_visituser.setOnClickListener(this);
        template_ll_addsupply.setOnClickListener(this);
        template_ll_proerror.setOnClickListener(this);
        template_ll_agencyerror.setOnClickListener(this);
        template_ll_dataerror.setOnClickListener(this);
        template_ll_iffleeing.setOnClickListener(this);
        template_ll_distrbution.setOnClickListener(this);
        template_ll_goodsvivi.setOnClickListener(this);
        template_ll_provivi.setOnClickListener(this);
        template_ll_icevivi.setOnClickListener(this);
        template_ll_salespromotion.setOnClickListener(this);
        template_ll_grouppro.setOnClickListener(this);
        template_ll_cooperation.setOnClickListener(this);
        template_ll_highps.setOnClickListener(this);
        template_ll_prooccupy.setOnClickListener(this);
        template_ll_addcmp.setOnClickListener(this);
        template_ll_cmperror.setOnClickListener(this);
        template_ll_cmpagencyerror.setOnClickListener(this);
        template_ll_cmpdataerror.setOnClickListener(this);
        template_ll_ifcmp.setOnClickListener(this);
        template_ll_visinote.setOnClickListener(this);

        dd_template_bt_next.setOnClickListener(this);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

    }

    private void initData() {
        titleTv.setText("配置稽查项");
        confirmTv.setText("保存");

        xtSelectService = new XtTermSelectService(getActivity());

        // // 获取追溯模板 大区id
        String areapid = PrefUtils.getString(getActivity(), "departmentid", "");
        mitValcheckterMs = xtSelectService.getValCheckterMList(areapid);

        if (mitValcheckterMs.size() > 0) {
            mitValcheckterM = mitValcheckterMs.get(0);
            if("Y".equals(mitValcheckterM.getVaildter())){
                template_cb_vaildter.setChecked(true);
            }else{
                template_cb_vaildter.setChecked(false);
            }
            // template_cb_vaildter.setChecked("Y".equals(mitValcheckterM.getVaildter()) ? true : false);


            //common_multiple_cb_vaildvisit.setChecked("Y".equals(mitValcheckterM.getVaildter()) ? true : false);
            if("Y".equals(mitValcheckterM.getVaildvisit())){
                common_multiple_cb_vaildvisit.setChecked(true);
            }else{
                common_multiple_cb_vaildvisit.setChecked(false);
            }

            // common_multiple_cb_ifmine.setChecked("Y".equals(mitValcheckterM.getIfmine()) ? true : false);
            if("Y".equals(mitValcheckterM.getIfmine())){
                common_multiple_cb_ifmine.setChecked(true);
            }else{
                common_multiple_cb_ifmine.setChecked(false);
            }

            // common_multiple_cb_salesarea.setChecked("Y".equals(mitValcheckterM.getSalesarea()) ? true : false);
            if("Y".equals(mitValcheckterM.getSalesarea())){
                common_multiple_cb_salesarea.setChecked(true);
            }else{
                common_multiple_cb_salesarea.setChecked(false);
            }

            // common_multiple_cb_terworkstatus.setChecked("Y".equals(mitValcheckterM.getTerworkstatus()) ? true : false);
            if("Y".equals(mitValcheckterM.getTerworkstatus())){
                common_multiple_cb_terworkstatus.setChecked(true);
            }else{
                common_multiple_cb_terworkstatus.setChecked(false);
            }

            // common_multiple_cb_tername.setChecked("Y".equals(mitValcheckterM.getTername()) ? true : false);
            if("Y".equals(mitValcheckterM.getTername())){
                common_multiple_cb_tername.setChecked(true);
            }else{
                common_multiple_cb_tername.setChecked(false);
            }

            // common_multiple_cb_terminalcode.setChecked("Y".equals(mitValcheckterM.getTerminalcode()) ? true : false);
            if("Y".equals(mitValcheckterM.getTerminalcode())){
                common_multiple_cb_terminalcode.setChecked(true);
            }else{
                common_multiple_cb_terminalcode.setChecked(false);
            }

            // common_multiple_cb_terlevel.setChecked("Y".equals(mitValcheckterM.getTerlevel()) ? true : false);
            if("Y".equals(mitValcheckterM.getTerlevel())){
                common_multiple_cb_terlevel.setChecked(true);
            }else{
                common_multiple_cb_terlevel.setChecked(false);
            }

            common_multiple_cb_routecode.setChecked("Y".equals(mitValcheckterM.getRoutecode()) ? true : false);
            common_multiple_cb_province.setChecked("Y".equals(mitValcheckterM.getProvince()) ? true : false);
            common_multiple_cb_city.setChecked("Y".equals(mitValcheckterM.getCity()) ? true : false);
            common_multiple_cb_country.setChecked("Y".equals(mitValcheckterM.getCountry()) ? true : false);
            common_multiple_cb_address.setChecked("Y".equals(mitValcheckterM.getAddress()) ? true : false);
            common_multiple_cb_contact.setChecked("Y".equals(mitValcheckterM.getContact()) ? true : false);
            common_multiple_cb_mobile.setChecked("Y".equals(mitValcheckterM.getMobile()) ? true : false);
            common_multiple_cb_cylie.setChecked("Y".equals(mitValcheckterM.getCylie()) ? true : false);
            common_multiple_cb_visitorder.setChecked("Y".equals(mitValcheckterM.getVisitorder()) ? true : false);
            common_multiple_cb_hvolume.setChecked("Y".equals(mitValcheckterM.getHvolume()) ? true : false);
            common_multiple_cb_zvolume.setChecked("Y".equals(mitValcheckterM.getZvolume()) ? true : false);
            common_multiple_cb_pvolume.setChecked("Y".equals(mitValcheckterM.getPvolume()) ? true : false);
            common_multiple_cb_lvolume.setChecked("Y".equals(mitValcheckterM.getLvolume()) ? true : false);
            common_multiple_cb_areatype.setChecked("Y".equals(mitValcheckterM.getAreatype()) ? true : false);
            common_multiple_cb_sellchannel.setChecked("Y".equals(mitValcheckterM.getSellchannel()) ? true : false);
            common_multiple_cb_mainchannel.setChecked("Y".equals(mitValcheckterM.getMainchannel()) ? true : false);
            common_multiple_cb_minorchannel.setChecked("Y".equals(mitValcheckterM.getMinorchannel()) ? true : false);
            common_multiple_cb_visituser.setChecked("Y".equals(mitValcheckterM.getVisituser()) ? true : false);
            common_multiple_cb_addsupply.setChecked("Y".equals(mitValcheckterM.getAddsupply()) ? true : false);
            common_multiple_cb_proerror.setChecked("Y".equals(mitValcheckterM.getProerror()) ? true : false);
            common_multiple_cb_agencyerror.setChecked("Y".equals(mitValcheckterM.getAgencyerror()) ? true : false);
            common_multiple_cb_dataerror.setChecked("Y".equals(mitValcheckterM.getDataerror()) ? true : false);
            common_multiple_cb_iffleeing.setChecked("Y".equals(mitValcheckterM.getIffleeing()) ? true : false);
            common_multiple_cb_distrbution.setChecked("Y".equals(mitValcheckterM.getDistrbution()) ? true : false);
            common_multiple_cb_goodsvivi.setChecked("Y".equals(mitValcheckterM.getGoodsvivi()) ? true : false);
            common_multiple_cb_provivi.setChecked("Y".equals(mitValcheckterM.getProvivi()) ? true : false);
            common_multiple_cb_icevivi.setChecked("Y".equals(mitValcheckterM.getIcevivi()) ? true : false);
            common_multiple_cb_salespromotion.setChecked("Y".equals(mitValcheckterM.getSalespromotion()) ? true : false);
            common_multiple_cb_grouppro.setChecked("Y".equals(mitValcheckterM.getGrouppro()) ? true : false);
            common_multiple_cb_cooperation.setChecked("Y".equals(mitValcheckterM.getCooperation()) ? true : false);
            common_multiple_cb_highps.setChecked("Y".equals(mitValcheckterM.getHighps()) ? true : false);
            common_multiple_cb_prooccupy.setChecked("Y".equals(mitValcheckterM.getProoccupy()) ? true : false);
            common_multiple_cb_addcmp.setChecked("Y".equals(mitValcheckterM.getAddcmp()) ? true : false);
            common_multiple_cb_cmperror.setChecked("Y".equals(mitValcheckterM.getCmperror()) ? true : false);
            common_multiple_cb_cmpagencyerror.setChecked("Y".equals(mitValcheckterM.getCmpagencyerror()) ? true : false);
            common_multiple_cb_cmpdataerror.setChecked("Y".equals(mitValcheckterM.getCmpdataerror()) ? true : false);
            common_multiple_cb_ifcmp.setChecked("Y".equals(mitValcheckterM.getIfcmp()) ? true : false);
            common_multiple_cb_visinote.setChecked("Y".equals(mitValcheckterM.getVisinote()) ? true : false);
        }else {
            mitValcheckterM = new MitValcheckterM();
            mitValcheckterM.setId(FunUtil.getUUID());
            mitValcheckterM.setAreaid(areapid);
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.template_ll_vaildter://
                /*if(template_cb_vaildter.isChecked()){// 是选中状态
                    template_cb_vaildter.setChecked(false);
                }else{// 是未选中状态
                    template_cb_vaildter.setChecked(true);
                }*/
                checkAbc(template_cb_vaildter);
                break;
            case R.id.template_ll_vaildvisit://
                checkAbc(common_multiple_cb_vaildvisit);
                break;
            case R.id.template_ll_ifmine://
                checkAbc(common_multiple_cb_ifmine);
                break;
            case R.id.template_ll_salesarea://
                checkAbc(common_multiple_cb_salesarea);
                break;
            case R.id.template_ll_terworkstatus://
                checkAbc(common_multiple_cb_terworkstatus);
                break;
            case R.id.template_ll_tername:// //
                checkAbc(common_multiple_cb_tername);
                break;
            case R.id.template_ll_terminalcode://
                checkAbc(common_multiple_cb_terminalcode);
                break;
            case R.id.template_ll_terlevel://
                checkAbc(common_multiple_cb_terlevel);
                break;
            case R.id.template_ll_routecode://
                checkAbc(common_multiple_cb_routecode);
                break;
            case R.id.template_ll_province://
                checkAbc(common_multiple_cb_province);
                break;
            case R.id.template_ll_city://
                checkAbc(common_multiple_cb_city);
                break;
            case R.id.template_ll_country://
                checkAbc(common_multiple_cb_country);
                break;
            case R.id.template_ll_address://
                checkAbc(common_multiple_cb_address);
                break;
            case R.id.template_ll_contact://
                checkAbc(common_multiple_cb_contact);
                break;
            case R.id.template_ll_mobile://
                checkAbc(common_multiple_cb_mobile);
                break;
            case R.id.template_ll_cylie://
                checkAbc(common_multiple_cb_cylie);
                break;
            case R.id.template_ll_visitorder://
                checkAbc(common_multiple_cb_visitorder);
                break;
            case R.id.template_ll_hvolume://
                checkAbc(common_multiple_cb_hvolume);
                break;
            case R.id.template_ll_zvolume://
                checkAbc(common_multiple_cb_zvolume);
                break;
            case R.id.template_ll_pvolume://
                checkAbc(common_multiple_cb_pvolume);
                break;
            case R.id.template_ll_lvolume://
                checkAbc(common_multiple_cb_lvolume);
                break;
            case R.id.template_ll_areatype://
                checkAbc(common_multiple_cb_areatype);
                break;
            case R.id.template_ll_sellchannel://
                checkAbc(common_multiple_cb_sellchannel);
                break;
            case R.id.template_ll_mainchannel://
                checkAbc(common_multiple_cb_mainchannel);
                break;
            case R.id.template_ll_minorchannel://
                checkAbc(common_multiple_cb_minorchannel);
                break;
            case R.id.template_ll_visituser://
                checkAbc(common_multiple_cb_visituser);
                break;
            case R.id.template_ll_addsupply://
                checkAbc(common_multiple_cb_addsupply);
                break;
            case R.id.template_ll_proerror://
                checkAbc(common_multiple_cb_proerror);
                break;
            case R.id.template_ll_agencyerror://
                checkAbc(common_multiple_cb_agencyerror);
                break;
            case R.id.template_ll_dataerror://
                checkAbc(common_multiple_cb_dataerror);
                break;
            case R.id.template_ll_iffleeing://
                checkAbc(common_multiple_cb_iffleeing);
                break;
            case R.id.template_ll_distrbution://
                checkAbc(common_multiple_cb_terworkstatus);
                break;
            case R.id.template_ll_goodsvivi://
                checkAbc(common_multiple_cb_goodsvivi);
                break;
            case R.id.template_ll_provivi://
                checkAbc(common_multiple_cb_provivi);
                break;
            case R.id.template_ll_icevivi://
                checkAbc(common_multiple_cb_icevivi);
                break;
            case R.id.template_ll_salespromotion://
                checkAbc(common_multiple_cb_salespromotion);
                break;
            case R.id.template_ll_grouppro://
                checkAbc(common_multiple_cb_grouppro);
                break;
            case R.id.template_ll_cooperation://
                checkAbc(common_multiple_cb_cooperation);
                break;
            case R.id.template_ll_highps://
                checkAbc(common_multiple_cb_highps);
                break;
            case R.id.template_ll_prooccupy://
                checkAbc(common_multiple_cb_prooccupy);
                break;
            case R.id.template_ll_addcmp://
                checkAbc(common_multiple_cb_addcmp);
                break;
            case R.id.template_ll_cmperror://
                checkAbc(common_multiple_cb_cmperror);
                break;
            case R.id.template_ll_cmpagencyerror://
                checkAbc(common_multiple_cb_cmpagencyerror);
                break;
            case R.id.template_ll_cmpdataerror://
                checkAbc(common_multiple_cb_cmpdataerror);
                break;
            case R.id.template_ll_ifcmp://
                checkAbc(common_multiple_cb_ifcmp);
                break;
            case R.id.template_ll_visinote://
                checkAbc(common_multiple_cb_visinote);
                break;


            case R.id.top_navigation_rl_confirm:// 保存
            case R.id.dd_template_bt_next:// 保存
                saveValue();
                break;

            default:
                break;
        }
    }

    private void checkAbc(CheckBox template_cb_vaildter) {
        if(template_cb_vaildter.isChecked()){// 是选中状态
            template_cb_vaildter.setChecked(false);
        }else{// 是未选中状态
            template_cb_vaildter.setChecked(true);
        }
    }


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsTemplateFragment> fragmentRef;

        public MyHandler(ZsTemplateFragment fragment) {
            fragmentRef = new SoftReference<ZsTemplateFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsTemplateFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_TEMPLATE_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_TEMPLATE_FAIL: //
                    //fragment.showAdapter();
                    break;
            }
        }
    }


    // 保存数据
    private void saveValue() {

        mitValcheckterM.setVaildter(template_cb_vaildter.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setVaildvisit(common_multiple_cb_vaildvisit.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setIfmine(common_multiple_cb_ifmine.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setSalesarea(common_multiple_cb_salesarea.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setTerworkstatus(common_multiple_cb_terworkstatus.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setTerminalcode(common_multiple_cb_terminalcode.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setRoutecode(common_multiple_cb_routecode.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setTername(common_multiple_cb_tername.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setTerlevel(common_multiple_cb_terlevel.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setProvince(common_multiple_cb_province.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCity(common_multiple_cb_city.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCountry(common_multiple_cb_country.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setAddress(common_multiple_cb_address.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setContact(common_multiple_cb_contact.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setMobile(common_multiple_cb_mobile.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCylie(common_multiple_cb_cylie.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setVisitorder(common_multiple_cb_visitorder.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setHvolume(common_multiple_cb_hvolume.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setZvolume(common_multiple_cb_zvolume.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setPvolume(common_multiple_cb_pvolume.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setLvolume(common_multiple_cb_lvolume.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setAreatype(common_multiple_cb_areatype.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setSellchannel(common_multiple_cb_sellchannel.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setMainchannel(common_multiple_cb_mainchannel.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setMinorchannel(common_multiple_cb_minorchannel.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setVisituser(common_multiple_cb_visituser.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setAddsupply(common_multiple_cb_addsupply.isChecked() ? ConstValues.Y : ConstValues.N);
        // mitValcheckterM.setLosesupply(common_multiple_cb_proerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setIffleeing(common_multiple_cb_iffleeing.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setProerror(common_multiple_cb_proerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setAgencyerror(common_multiple_cb_agencyerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setDataerror(common_multiple_cb_dataerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setDistrbution(common_multiple_cb_distrbution.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setGoodsvivi(common_multiple_cb_goodsvivi.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setProvivi(common_multiple_cb_provivi.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setIcevivi(common_multiple_cb_icevivi.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setSalespromotion(common_multiple_cb_salespromotion.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setGrouppro(common_multiple_cb_grouppro.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCooperation(common_multiple_cb_cooperation.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setHighps(common_multiple_cb_highps.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setProoccupy(common_multiple_cb_prooccupy.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setAddcmp(common_multiple_cb_addcmp.isChecked() ? ConstValues.Y : ConstValues.N);
        // mitValcheckterM.setLosecmp(common_multiple_cb_cmperror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCmperror(common_multiple_cb_cmperror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCmpagencyerror(common_multiple_cb_cmpagencyerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setCmpdataerror(common_multiple_cb_cmpdataerror.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setIfcmp(common_multiple_cb_ifcmp.isChecked() ? ConstValues.Y : ConstValues.N);
        mitValcheckterM.setVisinote(common_multiple_cb_visinote.isChecked() ? ConstValues.Y : ConstValues.N);


        xtSelectService.saveZsTemplate(mitValcheckterM);


        Toast.makeText(getActivity(),"模板配置成功",Toast.LENGTH_SHORT).show();
        supportFragmentManager.popBackStack();
    }

}
