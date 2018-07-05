package dd.tsingtaopad.dd.ddzs.zsagree;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.ViewUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.table.MitValagreeMTemp;
import dd.tsingtaopad.db.table.MitValagreedetailMTemp;
import dd.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import dd.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsAgreeFragment extends XtBaseVisitFragment implements View.OnClickListener {

    private final String TAG = "ZsAgreeFragment";

    MyHandler handler;

    public static final int AGREE_AMEND_SUC = 71;//
    public static final int AGREE_AMEND_DETAIL = 72;//


    public static final int AGREE_STARTDATE = 73;//
    public static final int AGREE_ENDDATE = 74;//
    public static final int AGREE_CONTENT = 75;//


    private LinearLayout agree_ll_all;
    private LinearLayout agree_ll_tv;
    private TextView tv_agreecode_con1;
    private TextView tv_agencyname_con1;
    private TextView rl_moneyagency_con1;
    private RelativeLayout rl_startdate;
    private TextView rl_startdate_con1;
    private TextView rl_startdate_statue;
    private RelativeLayout rl_enddate;
    private TextView rl_enddate_con1;
    private TextView rl_enddate_statue;
    private TextView rl_paytype_con1;
    private TextView rl_contact_con1;
    private TextView rl_mobile_con1;
    private RelativeLayout rl_notes;
    private TextView rl_notes_con1;
    private TextView rl_notes_statue;
    private ListView lv_cash;
    private ZsAgreeAdapter zsAgreeAdapter;
    private ZsAgreeService zsAgreeService;
    private MitValagreeMTemp mitValagreeMTemp;

    private List<MitValagreeMTemp> valagreeMTemps;
    private List<MitValagreedetailMTemp> valagreedetailMTemps;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_agree, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        agree_ll_all = (LinearLayout) view.findViewById(R.id.zs_agree_ll_all);
        agree_ll_tv = (LinearLayout) view.findViewById(R.id.zs_agree_ll_tv);
        tv_agreecode_con1 = (TextView) view.findViewById(R.id.zdzs_agree_tv_agreecode_con1);

        tv_agencyname_con1 = (TextView) view.findViewById(R.id.zdzs_agree_tv_agencyname_con1);

        rl_moneyagency_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_moneyagency_con1);

        rl_startdate = (RelativeLayout) view.findViewById(R.id.zdzs_agree_rl_startdate);
        rl_startdate_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_startdate_con1);
        rl_startdate_statue = (TextView) view.findViewById(R.id.zdzs_agree_rl_startdate_statue);

        rl_enddate = (RelativeLayout) view.findViewById(R.id.zdzs_agree_rl_enddate);
        rl_enddate_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_enddate_con1);
        rl_enddate_statue = (TextView) view.findViewById(R.id.zdzs_agree_rl_enddate_statue);

        rl_paytype_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_paytype_con1);

        rl_contact_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_contact_con1);

        rl_mobile_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_mobile_con1);

        rl_notes = (RelativeLayout) view.findViewById(R.id.zdzs_agree_rl_notes);
        rl_notes_con1 = (TextView) view.findViewById(R.id.zdzs_agree_rl_notes_con1);
        rl_notes_statue = (TextView) view.findViewById(R.id.zdzs_agree_rl_notes_statue);

        lv_cash = (ListView) view.findViewById(R.id.zdzs_agree_lv_cash);

        rl_startdate.setOnClickListener(this);
        rl_enddate.setOnClickListener(this);
        rl_notes.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        zsAgreeService = new ZsAgreeService(getActivity(), null);
        // 初始化数据
        initData();

    }

    // // 初始化数据
    private void initData() {

        valagreeMTemps = zsAgreeService.queryMitValagreeMTemp(mitValterMTempKey);
        valagreedetailMTemps = zsAgreeService.queryMitValagreedetailMTemp(mitValterMTempKey);

        if (valagreeMTemps.size() > 0) {// 该终端有协议
            agree_ll_tv.setVisibility(View.GONE);
            agree_ll_all.setVisibility(View.VISIBLE);
            mitValagreeMTemp = valagreeMTemps.get(0);// 协议,每个终端只有一条
            //
            initViewData();
            //
            initViewStatus();
            //
            initLvData();

        } else {
            agree_ll_all.setVisibility(View.GONE);
            agree_ll_tv.setVisibility(View.VISIBLE);
        }
    }

    // 显示ListView的数据
    private void initLvData() {
        //
        zsAgreeAdapter = new ZsAgreeAdapter(getActivity(), valagreedetailMTemps, new IClick() {
            @Override
            public void listViewItemClick(int position, View v) {
                alertShow6(position);
            }
        });
        lv_cash.setAdapter(zsAgreeAdapter);
        ViewUtil.setListViewHeight(lv_cash);
    }

    // 显示页面数据
    private void initViewData() {
        tv_agreecode_con1.setText(mitValagreeMTemp.getAgreecode());
        tv_agencyname_con1.setText(mitValagreeMTemp.getAgencyname());
        rl_moneyagency_con1.setText(mitValagreeMTemp.getMoneyagency());
        rl_startdate_con1.setText(mitValagreeMTemp.getStartdate().substring(0, 10));
        rl_enddate_con1.setText(mitValagreeMTemp.getEnddate().substring(0, 10));
        rl_paytype_con1.setText(mitValagreeMTemp.getPaytype());
        rl_contact_con1.setText(mitValagreeMTemp.getContact());
        rl_mobile_con1.setText(mitValagreeMTemp.getMobile());
        rl_notes_con1.setText(mitValagreeMTemp.getNotes());
    }

    // 显示数据状态
    private void initViewStatus() {
        // 开始时间 未稽查
        if ("N".equals(mitValagreeMTemp.getStartdateflag())) {
            rl_startdate_statue.setText("错误");
            rl_startdate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_error));
        } else if ("Y".equals(mitValagreeMTemp.getStartdateflag())) {
            rl_startdate_statue.setText("正确");
            rl_startdate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_yes));
        } else {
            rl_startdate_statue.setText("未稽查");
            rl_startdate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_notcheck));
        }

        // 结束时间 未稽查
        if ("N".equals(mitValagreeMTemp.getEnddateflag())) {
            rl_enddate_statue.setText("错误");
            rl_enddate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_error));
        } else if ("Y".equals(mitValagreeMTemp.getEnddateflag())) {
            rl_enddate_statue.setText("正确");
            rl_enddate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_yes));
        } else {
            rl_enddate_statue.setText("未稽查");
            rl_enddate_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_notcheck));
        }

        // 主要协议 未稽查
        if ("N".equals(mitValagreeMTemp.getNotesflag())) {
            rl_notes_statue.setText("错误");
            rl_notes_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_error));
        } else if ("Y".equals(mitValagreeMTemp.getNotesflag())) {
            rl_notes_statue.setText("正确");
            rl_notes_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_yes));
        } else {
            rl_notes_statue.setText("未稽查");
            rl_notes_statue.setTextColor(getResources().getColor(R.color.zdzs_dd_notcheck));
        }
    }


    // 核查产品兑换信息
    public void alertShow6(final int posi) {
        /*new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "品种有误", "承担金额有误", "实际数量有误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (0 == position) {// 正确
                            valagreedetailMTemps.get(posi).setAgreedetailflag("Y");
                            handler.sendEmptyMessage(ZsAgreeFragment.AGREE_AMEND_DETAIL);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("type", position);// 1 品种有误  2 承担金额有误  3 实际数量有误
                            bundle.putSerializable("MitValagreedetailMTemp", valagreedetailMTemps.get(posi));
                            ZsAgreeDetailAmendFragment xtaddinvoicingfragment = new ZsAgreeDetailAmendFragment(handler);
                            xtaddinvoicingfragment.setArguments(bundle);
                            ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            xtVisitShopActivity.changeXtvisitFragment(xtaddinvoicingfragment, "zsagreedetailamendfragment");
                        }

                    }
                }).setCancelable(true).show();*/

        new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确
                            valagreedetailMTemps.get(posi).setAgreedetailflag("Y");
                            handler.sendEmptyMessage(ZsAgreeFragment.AGREE_AMEND_DETAIL);
                        } else if (1 == position) {// 跳转数据录入
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("type", position);// 1 品种有误  2 承担金额有误  3 实际数量有误
                            bundle.putSerializable("MitValagreedetailMTemp", valagreedetailMTemps.get(posi));
                            ZsAgreeDetailAmendFragment xtaddinvoicingfragment = new ZsAgreeDetailAmendFragment(handler);
                            xtaddinvoicingfragment.setArguments(bundle);
                            ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            xtVisitShopActivity.changeXtvisitFragment(xtaddinvoicingfragment, "zsagreedetailamendfragment");
                        }
                    }
                }).setCancelable(true).show();
    }

    // 核查协议基本信息
    public void alertShow5(final int type) {
        new AlertView("请选择核查结果", null, "取消", null,
                new String[]{"正确", "错误"},
                getActivity(), AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (0 == position) {// 正确
                            // Toast.makeText(getActivity(), type + " 正确", Toast.LENGTH_SHORT).show();
                            if (type == AGREE_STARTDATE) {// 开始时间
                                mitValagreeMTemp.setStartdateflag("Y");
                            } else if (type == AGREE_ENDDATE) {//结束时间
                                mitValagreeMTemp.setEnddateflag("Y");
                            } else if (type == AGREE_CONTENT) {// 主要协议
                                mitValagreeMTemp.setNotesflag("Y");
                            }
                            initViewStatus();
                        } else if (1 == position) {// 错误
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("type", type);
                            bundle.putSerializable("mitValagreeMTemp", mitValagreeMTemp);
                            ZsAgreeAmendFragment xtaddinvoicingfragment = new ZsAgreeAmendFragment(handler);
                            xtaddinvoicingfragment.setArguments(bundle);
                            ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity) getActivity();
                            xtVisitShopActivity.changeXtvisitFragment(xtaddinvoicingfragment, "zsagreeamendfragment");
                        }

                    }
                }).setCancelable(true).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.zdzs_agree_rl_startdate:
                alertShow5(AGREE_STARTDATE);
                break;
            case R.id.zdzs_agree_rl_enddate:
                alertShow5(AGREE_ENDDATE);
                break;
            case R.id.zdzs_agree_rl_notes:
                alertShow5(AGREE_CONTENT);
                break;
            default:
                break;
        }
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsAgreeFragment> fragmentRef;

        public MyHandler(ZsAgreeFragment fragment) {
            fragmentRef = new SoftReference<ZsAgreeFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsAgreeFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case AGREE_AMEND_SUC:// 主要协议 错误备注返回  刷新页面
                    fragment.initViewStatus();
                    break;
                case AGREE_AMEND_DETAIL: // 督导输入数据后
                    fragment.refreshLvData();
                    break;
            }
        }
    }

    private void refreshLvData() {
        zsAgreeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");
        // 保存追溯
        zsAgreeService.saveZsAgree(mitValagreeMTemp, valagreedetailMTemps);
    }
}
