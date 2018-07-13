package et.tsingtaopad.dd.ddaddterm;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.AlertKeyValueAdapter;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DbtUtils;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.db.table.MitTerminalM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.dd.ddxt.updata.XtUploadService;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * 督导漏店补录
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAddTermFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAddTermFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_ADD_TERM_SUC = 2501;//
    public static final int DD_ADD_TERM_FAIL = 2502;//
    public static final int DD_ADD_TERM_PROGRESS_OPEN = 2503;//
    public static final int DD_ADD_TERM_PROGRESS_CLOSE = 2504;//

    public static final int TERMSELLCHANNEL = 2505;// 销售渠道
    public static final int TERMTMAINCHANNEL = 2506;// 主渠道
    public static final int TERMMINORCHANNEL = 2507;// 次渠道
    public static final int SHOWPROVINCE = 2508;// 省
    public static final int SHOWCITY = 2509;// 市
    public static final int SHOWCOUNTRY = 25010;// 县
    public static final int TERMAREAID = 25011;// 区域
    public static final int TERMGRIDID = 25012;// 定格
    public static final int TERMROUDE = 25013;// 路线

    private EditText termnameEt;

    private RelativeLayout termareaidRl;
    private TextView termareaidTv;
    private RelativeLayout termgrididRl;
    private TextView termgrididTv;
    private RelativeLayout termroudeRl;
    private TextView termroudeTv;

    private RelativeLayout termlvRl;
    private TextView termlvTv;

    private RelativeLayout termprovinceRl;
    private TextView termprovinceTv;
    private RelativeLayout termcityRl;
    private TextView termcityTv;
    private RelativeLayout termcountryRl;
    private TextView termcountryTv;

    private EditText termaddressEt;
    private EditText termcontactEt;
    private EditText termphoneEt;
    private EditText termcycleEt;
    private EditText termsequenceEt;
    private EditText termhvolumeEt;
    private EditText termmvolumeEt;
    private EditText termpvolumeEt;
    private EditText termlvolumeEt;

    private RelativeLayout termareaRl;
    private TextView termareaTv;

    private RelativeLayout termsellchannelRl;
    private TextView termsellchannelTv;

    private RelativeLayout termtmainchannelRl;
    private TextView termtmainchannelTv;

    private RelativeLayout termminorchannelRl;
    private TextView termminorchannelTv;

    private Button addtermBtn;
    private DdAddTermService service;

    private AlertView mAlertViewExt;//窗口拓展例子

    private List<MstRouteM> mstRouteList = new ArrayList<>();
    private List<KvStc> dataDicByTermLevelLst = new ArrayList<>();
    private List<KvStc> dataDicByAreaTypeLst = new ArrayList<>();
    private List<KvStc> sellchanneldataLst = new ArrayList<>();
    private List<KvStc> visitpositionLst = new ArrayList<>();
    private List<KvStc> sellchannelLst = new ArrayList<>();
    private List<KvStc> mainchannelLst = new ArrayList<>();
    private List<KvStc> minorchannelLst = new ArrayList<>();

    private List<KvStc> provinceList = new ArrayList<>();
    private List<KvStc> cityList = new ArrayList<>();
    private List<KvStc> countryList = new ArrayList<>();

    private List<KvStc> areaList = new ArrayList<>();
    private List<KvStc> gridList = new ArrayList<>();
    private List<KvStc> routeList = new ArrayList<>();

    private String sdf = "123";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_addterm, container, false);
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
        //confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        termnameEt = (EditText) view.findViewById(R.id.dd_addterm_termname);

        termroudeRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termroude);
        termroudeTv = (TextView) view.findViewById(R.id.dd_addterm_termroude);

        termareaidRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termareaid);
        termareaidTv = (TextView) view.findViewById(R.id.dd_addterm_termareaid);

        termgrididRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termgridid);
        termgrididTv = (TextView) view.findViewById(R.id.dd_addterm_termgridid);

        termlvRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termlv);
        termlvTv = (TextView) view.findViewById(R.id.dd_addterm_termlv);

        termprovinceRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termprovince);
        termprovinceTv = (TextView) view.findViewById(R.id.dd_addterm_termprovince);

        termcityRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termcity);
        termcityTv = (TextView) view.findViewById(R.id.dd_addterm_termcity);

        termcountryRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termcountry);
        termcountryTv = (TextView) view.findViewById(R.id.dd_addterm_termcountry);

        termaddressEt = (EditText) view.findViewById(R.id.dd_addterm_termaddress);
        termcontactEt = (EditText) view.findViewById(R.id.dd_addterm_termcontact);
        termphoneEt = (EditText) view.findViewById(R.id.dd_addterm_termphone);
        termcycleEt = (EditText) view.findViewById(R.id.dd_addterm_termcycle);
        termsequenceEt = (EditText) view.findViewById(R.id.dd_addterm_termsequence);
        termhvolumeEt = (EditText) view.findViewById(R.id.dd_addterm_termhvolume);
        termmvolumeEt = (EditText) view.findViewById(R.id.dd_addterm_termmvolume);
        termpvolumeEt = (EditText) view.findViewById(R.id.dd_addterm_termpvolume);
        termlvolumeEt = (EditText) view.findViewById(R.id.dd_addterm_termlvolume);

        termareaRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termarea);
        termareaTv = (TextView) view.findViewById(R.id.dd_addterm_termarea);

        termsellchannelRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termsellchannel);
        termsellchannelTv = (TextView) view.findViewById(R.id.dd_addterm_termsellchannel);

        termtmainchannelRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termtmainchannel);
        termtmainchannelTv = (TextView) view.findViewById(R.id.dd_addterm_termtmainchannel);

        termminorchannelRl = (RelativeLayout) view.findViewById(R.id.dd_addterm_rl_termminorchannel);
        termminorchannelTv = (TextView) view.findViewById(R.id.dd_addterm_termminorchannel);

        addtermBtn = (Button) view.findViewById(R.id.dd_addterm_bt_next);

        termprovinceRl.setOnClickListener(this);
        termcityRl.setOnClickListener(this);
        termcountryRl.setOnClickListener(this);
        termareaidRl.setOnClickListener(this);
        termgrididRl.setOnClickListener(this);
        termroudeRl.setOnClickListener(this);
        termlvRl.setOnClickListener(this);
        termareaRl.setOnClickListener(this);
        termsellchannelRl.setOnClickListener(this);
        termtmainchannelRl.setOnClickListener(this);
        termminorchannelRl.setOnClickListener(this);
        addtermBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

    }

    private void initData() {
        titleTv.setText("漏店补录");
        service = new DdAddTermService(getActivity(), handler);

        // 终端名称 过滤
        termnameEt.addTextChangedListener(new TextWatcher() {
            private int cou = 0;
            int selectionEnd = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cou = before + count;
                String editable = termnameEt.getText().toString();
                String str = CheckUtil.stringFilter(editable); // 过滤特殊字符
                if (!editable.equals(str)) {
                    Toast.makeText(getActivity(), "不能输入特殊字符", Toast.LENGTH_SHORT).show();
                    termnameEt.setText(str);
                }
                termnameEt.setSelection(termnameEt.length());
                cou = termnameEt.length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cou > 20) {
                    selectionEnd = termnameEt.getSelectionEnd();
                    s.delete(20, selectionEnd);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定

                break;
            case R.id.dd_addterm_rl_termareaid:// 区域
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread termareaid = new Thread() {
                    @Override
                    public void run() {
                        try {
                            areaList.clear();
                            List<MstMarketareaM> valueLst = service.getMstMarketareaMList(PrefUtils.getString(getActivity(), "departmentid", ""));
                            for (MstMarketareaM mstMarketareaM : valueLst) {
                                areaList.add(new KvStc(mstMarketareaM.getAreaid(), mstMarketareaM.getAreaname(), mstMarketareaM.getAreapid()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMAREAID);
                        }
                    }
                };
                termareaid.start();
                break;
            case R.id.dd_addterm_rl_termgridid:// 定格
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread termgridid = new Thread() {
                    @Override
                    public void run() {
                        try {
                            gridList.clear();
                            List<MstGridM> valueLst = service.getMstGridMList((String) termareaidTv.getTag());
                            for (MstGridM mstMarketareaM : valueLst) {
                                gridList.add(new KvStc(mstMarketareaM.getGridkey(), mstMarketareaM.getGridname(), ""));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMGRIDID);
                        }
                    }
                };
                termgridid.start();
                break;
            case R.id.dd_addterm_rl_termroude:// 路线
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread termroude = new Thread() {
                    @Override
                    public void run() {
                        try {
                            routeList.clear();
                            List<MstRouteM> valueLst = service.getMstRouteMList((String) termgrididTv.getTag());
                            for (MstRouteM mstMarketareaM : valueLst) {
                                routeList.add(new KvStc(mstMarketareaM.getRoutekey(), mstMarketareaM.getRoutename(), ""));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMROUDE);
                        }
                    }
                };
                termroude.start();
                break;
            case R.id.dd_addterm_rl_termlv:// 等级
                // 终端等级集合
                dataDicByTermLevelLst = service.initDataDicByTermLevel();
                mAlertViewExt = new AlertView("请选择终端等级", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
                ViewGroup lvextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
                ListView lvlistview = (ListView) lvextView.findViewById(R.id.alert_list);
                AlertKeyValueAdapter lvkeyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDicByTermLevelLst,
                        new String[]{"key", "value"}, null);
                lvlistview.setAdapter(lvkeyValueAdapter);
                lvlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        termlvTv.setText(dataDicByTermLevelLst.get(position).getValue());
                        termlvTv.setTag(dataDicByTermLevelLst.get(position).getKey());
                        mAlertViewExt.dismiss();
                    }
                });
                mAlertViewExt.addExtView(lvextView);
                mAlertViewExt.setCancelable(true).setOnDismissListener(null);
                mAlertViewExt.show();
                break;
            case R.id.dd_addterm_rl_termprovince:// 省
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread province = new Thread() {
                    @Override
                    public void run() {
                        try {
                            provinceList = service.queryChildForArea("-1");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.SHOWPROVINCE);
                        }
                    }
                };
                province.start();

                break;
            case R.id.dd_addterm_rl_termcity:// 市
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread city = new Thread() {
                    @Override
                    public void run() {
                        try {
                            //  市
                            cityList = service.queryChildForArea((String) termprovinceTv.getTag());
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.SHOWCITY);
                        }
                    }
                };
                city.start();
                break;
            case R.id.dd_addterm_rl_termcountry:// 县
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread country = new Thread() {
                    @Override
                    public void run() {
                        try {
                            // 县
                            countryList = service.queryChildForArea((String) termcityTv.getTag());
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.SHOWCOUNTRY);
                        }
                    }
                };
                country.start();
                break;
            case R.id.dd_addterm_rl_termarea:// 区域类型

                // 区域类型集合
                dataDicByAreaTypeLst = service.initDataDicByAreaType();
                mAlertViewExt = new AlertView("请选择区域类型", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
                ViewGroup areaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
                ListView arealistview = (ListView) areaextView.findViewById(R.id.alert_list);
                AlertKeyValueAdapter areakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDicByAreaTypeLst,
                        new String[]{"key", "value"}, null);
                arealistview.setAdapter(areakeyValueAdapter);
                arealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        termareaTv.setText(dataDicByAreaTypeLst.get(position).getValue());
                        termareaTv.setTag(dataDicByAreaTypeLst.get(position).getKey());
                        mAlertViewExt.dismiss();
                    }
                });
                mAlertViewExt.addExtView(areaextView);
                mAlertViewExt.setCancelable(true).setOnDismissListener(null);
                mAlertViewExt.show();

                break;
            case R.id.dd_addterm_rl_termsellchannel:// 销售渠道
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            // 销售渠道集合
                            sellchanneldataLst = service.initDataDicBySellChannel();
                            // 销售渠道集合
                            sellchannelLst = getSellChannelList(sellchanneldataLst);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMSELLCHANNEL);
                        }
                    }
                };
                thread.start();

                break;
            case R.id.dd_addterm_rl_termtmainchannel:// 主渠道
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread mainchannel = new Thread() {
                    @Override
                    public void run() {
                        try {
                            // 主渠道集合
                            mainchannelLst = getMainchannelLst(sellchannelLst);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMTMAINCHANNEL);
                        }
                    }
                };
                mainchannel.start();
                break;
            case R.id.dd_addterm_rl_termminorchannel:// 次渠道
                handler.sendEmptyMessage(DdAddTermFragment.DD_ADD_TERM_PROGRESS_OPEN);
                Thread minorchannel = new Thread() {
                    @Override
                    public void run() {
                        try {
                            // 次渠道集合
                            minorchannelLst = getMinorchannelLst(mainchannelLst);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            handler.sendEmptyMessage(DdAddTermFragment.TERMMINORCHANNEL);
                        }
                    }
                };
                minorchannel.start();
                break;

            case R.id.dd_addterm_bt_next:// 保存
                saveValue();

                break;

            default:
                break;
        }
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
            if (kvstc.getKey().equals(termsellchannelTv.getTag())) {
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
            if (kvstc.getKey().equals(termtmainchannelTv.getTag())) {
                minor = kvstc;
            }
        }
        // 返回某个主渠道下的次渠道集合
        return getSellChannelList(minor.getChildLst());
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdAddTermFragment> fragmentRef;

        public MyHandler(DdAddTermFragment fragment) {
            fragmentRef = new SoftReference<DdAddTermFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdAddTermFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_ADD_TERM_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_ADD_TERM_FAIL: //
                    //fragment.showAdapter();
                    break;
                case DD_ADD_TERM_PROGRESS_OPEN:// 开启滚动条
                    fragment.showAddTermDialog();
                    break;
                case DD_ADD_TERM_PROGRESS_CLOSE: // 关闭滚动条
                    fragment.closeAddTermDialog();
                    break;
                case TERMSELLCHANNEL: // 展示 销售渠道
                    fragment.closeAddTermDialog();
                    fragment.showSellchannelLst();
                    break;
                case TERMTMAINCHANNEL: // 展示 主渠道
                    fragment.closeAddTermDialog();
                    fragment.showTermtmainchannel();
                    break;
                case TERMMINORCHANNEL: // 展示 次渠道
                    fragment.closeAddTermDialog();
                    fragment.showTermminorchannel();
                    break;
                case SHOWPROVINCE: // 展示 省
                    fragment.closeAddTermDialog();
                    fragment.showProvince();
                    break;
                case SHOWCITY: // 展示 市
                    fragment.closeAddTermDialog();
                    fragment.showCity();
                    break;
                case SHOWCOUNTRY: // 展示 县
                    fragment.closeAddTermDialog();
                    fragment.showCountry();
                    break;

                case TERMAREAID: // 展示 区域
                    fragment.closeAddTermDialog();
                    fragment.showTermareaid();
                    break;
                case TERMGRIDID: // 展示 定格
                    fragment.closeAddTermDialog();
                    fragment.showTermgridid();
                    break;
                case TERMROUDE: // 展示 路线
                    fragment.closeAddTermDialog();
                    fragment.showTermroude();
                    break;
            }
        }
    }

    private AlertDialog dialog;

    // 展示滚动条
    public void showAddTermDialog() {
        dialog = new AlertDialog.Builder(getActivity()).setCancelable(false).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.sync_progress, null);
        TextView dialog_tv_sync = (TextView) view.findViewById(R.id.dialog_tv_sync);
        dialog_tv_sync.setText("正在查找数据");
        dialog.setView(view, 0, 0, 0, 0);
        dialog.setCancelable(false); // 是否可以通过返回键 关闭
        dialog.show();
    }

    // 关闭滚动条
    public void closeAddTermDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        //gridAdapter.notifyDataSetChanged();
    }

    // 展示销售渠道
    private void showSellchannelLst() {
        mAlertViewExt = new AlertView("请选择销售渠道", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup sellchannelareaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView sellchannelarealistview = (ListView) sellchannelareaextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter sellchannelareakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), sellchannelLst,
                new String[]{"key", "value"}, null);
        sellchannelarealistview.setAdapter(sellchannelareakeyValueAdapter);
        sellchannelarealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termsellchannelTv.setText(sellchannelLst.get(position).getValue());
                termsellchannelTv.setTag(sellchannelLst.get(position).getKey());

                termtmainchannelTv.setText("");
                termtmainchannelTv.setTag("");

                termminorchannelTv.setText("");
                termminorchannelTv.setTag("");

                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(sellchannelareaextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 展示主渠道
    private void showTermtmainchannel() {
        mAlertViewExt = new AlertView("请选择主渠道", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup mainchannelareaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView mainchannelarealistview = (ListView) mainchannelareaextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter mainchannelareakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), mainchannelLst,
                new String[]{"key", "value"}, null);
        mainchannelarealistview.setAdapter(mainchannelareakeyValueAdapter);
        mainchannelarealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termtmainchannelTv.setText(mainchannelLst.get(position).getValue());
                termtmainchannelTv.setTag(mainchannelLst.get(position).getKey());

                termminorchannelTv.setText("");
                termminorchannelTv.setTag("");

                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(mainchannelareaextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 展示次渠道
    private void showTermminorchannel() {
        mAlertViewExt = new AlertView("请选择次渠道", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup minorchannelareaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView minorchannelarealistview = (ListView) minorchannelareaextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter minorchannelareakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), minorchannelLst,
                new String[]{"key", "value"}, null);
        minorchannelarealistview.setAdapter(minorchannelareakeyValueAdapter);
        minorchannelarealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termminorchannelTv.setText(minorchannelLst.get(position).getValue());
                termminorchannelTv.setTag(minorchannelLst.get(position).getKey());
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(minorchannelareaextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }


    // 选择省
    private void showProvince() {
        final List<KvStc> dataDic = provinceList;
        mAlertViewExt = new AlertView("请选择省", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termprovinceTv.setText(dataDic.get(position).getValue());
                termprovinceTv.setTag(dataDic.get(position).getKey());

                termcityTv.setText("");
                termcityTv.setTag("");

                termcountryTv.setText("");
                termcountryTv.setTag("");

                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择市
    private void showCity() {
        final List<KvStc> dataDic = cityList;
        mAlertViewExt = new AlertView("请选择市", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termcityTv.setText(dataDic.get(position).getValue());
                termcityTv.setTag(dataDic.get(position).getKey());

                termcountryTv.setText("");
                termcountryTv.setTag("");
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择县
    private void showCountry() {
        final List<KvStc> dataDic = countryList;
        mAlertViewExt = new AlertView("请选择县", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termcountryTv.setText(dataDic.get(position).getValue());
                termcountryTv.setTag(dataDic.get(position).getKey());
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择区域
    private void showTermareaid() {

        final List<KvStc> dataDic = areaList;
        mAlertViewExt = new AlertView("请选择区域", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termareaidTv.setText(dataDic.get(position).getValue());
                termareaidTv.setTag(dataDic.get(position).getKey());

                termgrididTv.setText("");
                termgrididTv.setTag("");

                termroudeTv.setText("");
                termroudeTv.setTag("");
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择定格
    private void showTermgridid() {

        final List<KvStc> dataDic = gridList;
        mAlertViewExt = new AlertView("请选择定格", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termgrididTv.setText(dataDic.get(position).getValue());
                termgrididTv.setTag(dataDic.get(position).getKey());

                termroudeTv.setText("");
                termroudeTv.setTag("");
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 选择路线
    private void showTermroude() {

        final List<KvStc> dataDic = routeList;
        mAlertViewExt = new AlertView("请选择路线", null, null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView listview = (ListView) extView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(getActivity(), dataDic,
                new String[]{"key", "value"}, null);
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                termroudeTv.setText(dataDic.get(position).getValue());
                termroudeTv.setTag(dataDic.get(position).getKey());
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

    String termname;
    String termareaid;
    String termgridid;
    String termroude;
    String termlv;
    String termprovince;
    String termcity;
    String termcountry;
    String termaddress;
    String termcontact;
    String termphone;
    String termcycle;
    String termsequence;
    String termhvolume;
    String termmvolume;
    String termpvolume;
    String termlvolume;
    String termarea;
    String termsellchannel;
    String termtmainchannel;
    String termminorchannel;

    // 保存数据
    private void saveValue() {
        termname = termnameEt.getText().toString();
        termareaid = (String) termareaidTv.getTag();
        termgridid = (String) termgrididTv.getTag();
        termroude = (String) termroudeTv.getTag();
        termlv = (String) termlvTv.getTag();
        termprovince = (String) termprovinceTv.getTag();
        termcity = (String) termcityTv.getTag();
        termcountry = (String) termcountryTv.getTag();
        termaddress = termaddressEt.getText().toString();
        termcontact = termcontactEt.getText().toString();
        termphone = termphoneEt.getText().toString();
        termcycle = termcycleEt.getText().toString();
        termsequence = termsequenceEt.getText().toString();
        termhvolume = termhvolumeEt.getText().toString();
        termmvolume = termmvolumeEt.getText().toString();
        termpvolume = termpvolumeEt.getText().toString();
        termlvolume = termlvolumeEt.getText().toString();
        termarea = (String) termareaTv.getTag();
        termsellchannel = (String) termsellchannelTv.getTag();
        termtmainchannel = (String) termtmainchannelTv.getTag();
        termminorchannel = (String) termminorchannelTv.getTag();

        if (!checkAddTerm()) {
            return;
        }

        MitTerminalM mitTerminalM = new MitTerminalM();
        // mitTerminalM.setTerminalkey();
        // mitTerminalM.setTerminalcode();
        mitTerminalM.setId(FunUtil.getUUID());
        mitTerminalM.setTerminalname(termname);
        mitTerminalM.setRoutekey(termroude);
        mitTerminalM.setAreatype(termarea);
        mitTerminalM.setTlevel(termlv);
        mitTerminalM.setProvince(termprovince);
        mitTerminalM.setCity(termcity);
        mitTerminalM.setCounty(termcountry);
        mitTerminalM.setAddress(termaddress);
        mitTerminalM.setContact(termcontact);
        mitTerminalM.setMobile(termphone);
        mitTerminalM.setSequence(termsequence);
        mitTerminalM.setCycle(termcycle);
        mitTerminalM.setHvolume(termhvolume);
        mitTerminalM.setZvolume(termmvolume);
        mitTerminalM.setPvolume(termpvolume);
        mitTerminalM.setDvolume(termlvolume);
        mitTerminalM.setSellchannel(termsellchannel);
        mitTerminalM.setMainchannel(termtmainchannel);
        mitTerminalM.setMinorchannel(termminorchannel);
        mitTerminalM.setUploadflag("1");
        mitTerminalM.setPadisconsistent("0");

        // 保存数据库
        service.saveMitTerminalM(mitTerminalM);

        // 上传数据
        XtUploadService xtUploadService = new XtUploadService(getActivity(), null);
        xtUploadService.uploadMitTerminalM(false, mitTerminalM, 1);

        supportFragmentManager.popBackStack();
    }

    private boolean checkAddTerm() {
        boolean right = true;
        if (termname == null || "".equals(termname.trim())) {
            Toast.makeText(getActivity(), "请输入终端名称", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termarea == null || "".equals(termarea.trim())) {
            Toast.makeText(getActivity(), "请选择区域类型", Toast.LENGTH_SHORT).show();
            right = false;
        }else if (termroude == null || "".equals(termroude.trim())) {
            Toast.makeText(getActivity(), "请选择所属路线", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termlv == null || "".equals(termlv.trim())) {
            Toast.makeText(getActivity(), "请选择终端等级", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termcity == null || "".equals(termcity.trim())) {
            Toast.makeText(getActivity(), "请选择所在市", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termcountry == null || "".equals(termcountry.trim())) {
            Toast.makeText(getActivity(), "请选择所在县", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termaddress == null || "".equals(termaddress.trim())) {
            Toast.makeText(getActivity(), "请输入地址", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termcontact == null || "".equals(termcontact.trim())) {
            Toast.makeText(getActivity(), "请输入联系人", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termphone == null || "".equals(termphone.trim())) {
            Toast.makeText(getActivity(), "请输入电话", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termsellchannel == null || "".equals(termsellchannel.trim())) {
            Toast.makeText(getActivity(), "请选择销售渠道", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termtmainchannel == null || "".equals(termtmainchannel.trim())) {
            Toast.makeText(getActivity(), "请选择主渠道", Toast.LENGTH_SHORT).show();
            right = false;
        } else if (termminorchannel == null || "".equals(termminorchannel.trim())) {
            Toast.makeText(getActivity(), "请选择次渠道", Toast.LENGTH_SHORT).show();
            right = false;
        }


        return right;
    }


}
