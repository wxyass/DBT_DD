package dd.tsingtaopad.dd.ddxt.checking;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.adapter.AlertKeyValueAdapter;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.ViewUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnDismissListener;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.table.MstGroupproductMTemp;
import dd.tsingtaopad.db.table.MstTerminalinfoMTemp;
import dd.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtCheckIndexCalculateStc;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import dd.tsingtaopad.dd.ddxt.checking.num.XtQuickCollectFragment;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtVisitShopActivity;
import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class XtCheckIndexFragment extends XtBaseVisitFragment implements View.OnClickListener ,OnItemClickListener, OnDismissListener {

    private final String TAG = "XtCheckIndexFragment";

    private AppCompatButton mXtvisit_btn_pro;

    private ListView calculateLv;
    private ListView promotionLv;
    private Button quickCollectBt;

    private XtCheckIndexService service;

    private List<CheckIndexPromotionStc> promotionLst;
    private MstTerminalinfoMTemp term;

    public static final int INPUT_SUC = 3;
    MyHandler handler;
    XtCaculateAdapter xtCaculateAdapter;

    private dd.tsingtaopad.view.DdSlideSwitch xtProstatusSw;
    private dd.tsingtaopad.view.DdSlideSwitch xtHezuoSw;
    private dd.tsingtaopad.view.DdSlideSwitch xtPeisongSw;
    private RelativeLayout xtZhanyoulvRl;
    private TextView xtZhanyoulvTv;

    private AlertView mAlertViewExt;//窗口拓展例子

    String zhanyoulvIndexValueId;//当前终端单店占有率 对应cstatuskey
    MstGroupproductMTemp vo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_checkindex, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        //采项分集listView
        calculateLv = (ListView) view.findViewById(R.id.xtbf_checkindex_lv_calculate);
        quickCollectBt = (Button) view.findViewById(R.id.xtbf_checkindex_bt_quickcollect);
        promotionLv = (ListView) view.findViewById(R.id.xtbf_checkindex_lv_promotion);

        xtProstatusSw = (dd.tsingtaopad.view.DdSlideSwitch) view.findViewById(R.id.xtbf_noproindex_sw_prostatus);
        xtHezuoSw = (dd.tsingtaopad.view.DdSlideSwitch) view.findViewById(R.id.xtbf_noproindex_sw_hezuo);
        xtPeisongSw = (dd.tsingtaopad.view.DdSlideSwitch) view.findViewById(R.id.xtbf_noproindex_sw_peisong);
        xtZhanyoulvRl = (RelativeLayout) view.findViewById(R.id.xtbf_sayhi_rl_termminorchannel);
        xtZhanyoulvTv = (TextView) view.findViewById(R.id.xtbf_noproindex_sp_zhanyoulv);

        quickCollectBt.setOnClickListener(this);
        xtZhanyoulvRl.setOnClickListener(this);
    }

    List<XtProIndex> calculateLst = new ArrayList<XtProIndex>();
    List<KvStc> indexValuelst = new ArrayList<KvStc>();
    List<XtProItem> proItemLst = new ArrayList<XtProItem>();

    List<XtCheckIndexCalculateStc> noProIndexLst;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Toast.makeText(getActivity(), "查指标" + "/" + termId + "/" + termName, Toast.LENGTH_SHORT).show();

        // 指标模拟数据
        service = new XtCheckIndexService(getActivity(), null);
        handler = new MyHandler(this);
        // 查询最新终端临时表数据
        term = service.findTermTempById(termId);
        // 获取最新的终端数据

        channelId = term.getMinorchannel();// 次渠道


        // 获取分项采集页面显示数据 // 获取指标采集前3列数据 // 铺货状态,道具生动化,产品生动化,冰冻化
        calculateLst = service.queryCalculateIndex(visitId, termId, term.getMinorchannel(), seeFlag);
        // 获取分项采集部分的产品指标对应的采集项目数据 // 因为在ShopVisitActivity生成了供货关系,此时就能关联出各个产品的采集项,现有量变化量为0
        proItemLst = service.queryCalculateItem(visitId, channelId);

        // 初始化指标、指标值树级关系 对象
        service.initCheckTypeStatus();
        indexValuelst = GlobalValues.indexLst;

        xtCaculateAdapter = new XtCaculateAdapter(getActivity(), calculateLst, indexValuelst, proItemLst, handler);
        calculateLv.setAdapter(xtCaculateAdapter);
        ViewUtil.setListViewHeight(calculateLv);

        // 促销活动
        promotionLst = service.queryPromotion(visitId, term.getSellchannel(), term.getTlevel());
        XtPromotionAdapter xtPromotionAdapter = new XtPromotionAdapter(getActivity(), promotionLst, "2018-03-30", seeFlag);
        promotionLv.setAdapter(xtPromotionAdapter);

        // 其他信息
        noProIndexLst = service.queryNoProIndex12(visitId, term.getMinorchannel(), seeFlag);
        // 初始化 合作是否到位 占有率 高质量配送数据
        for (XtCheckIndexCalculateStc item : noProIndexLst) {
            // 重新查询指标关联指标值 //因为原先的这3个指标 不再关联指标值(pad_checktype_m不再同步这三个指标)
            // 交由pad直接初始化 // ywm 20160407
            if ("666b74b3-b221-4920-b549-d9ec39a463fd".equals(item.getIndexId())) {// 合作是否到位
                //tempLst = service.queryNoProIndexValueId1();// 此处报错
                if ("9019cf03-4572-4559-9971-48a27a611c3d".equals(item.getIndexValueId())) {// 合作是否到位 是
                    xtHezuoSw.setStatus(true);
                }else{
                    xtHezuoSw.setStatus(false);
                }
            } else if ("df2e88c9-246f-40e2-b6e5-08cdebf8c281".equals(item.getIndexId())) {// 是否高质量配送
                //tempLst = service.queryNoProIndexValueId2();
                if ("460647a9-283a-44ea-b11f-42efe1fd62e4".equals(item.getIndexValueId())) {// 是否高质量配送
                    xtPeisongSw.setStatus(true);
                }else{
                    xtPeisongSw.setStatus(false);
                }
            } else if ("59802090-02ac-4146-9cc3-f09570c36a26".equals(item.getIndexId())) {// 我品占有率
                 zhanyoulvIndexValueId = item.getIndexValueId();
                xtZhanyoulvTv.setText(service.getCheckStatusName(zhanyoulvIndexValueId));
            }
        }

        // 产品组合是否达标
        vo = service.findMstGroupproductMTempByid(term.getTerminalcode());
        if (CheckUtil.isBlankOrNull(vo.getIfrecstand()) || "N".equals(vo.getIfrecstand()) || "null".equals(vo.getIfrecstand())) {
            xtProstatusSw.setStatus(false);
        }else{
            xtProstatusSw.setStatus(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xtbf_checkindex_bt_quickcollect:// 快速采集
                if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;

                Bundle bundle = new Bundle();
                bundle.putSerializable("termId", termStc.getTerminalkey());
                bundle.putSerializable("termname", termStc.getTerminalname());
                bundle.putSerializable("channelId", termStc.getMinorchannel());// 次渠道
                bundle.putSerializable("termStc", termStc);
                bundle.putSerializable("visitKey", visitId);//visitId
                bundle.putSerializable("proItemLst", (Serializable) proItemLst);// 默认0   0:拜访 1:查看

                XtQuickCollectFragment xtquickcollectfragment = new XtQuickCollectFragment(handler);
                xtquickcollectfragment.setArguments(bundle);

                XtVisitShopActivity xtVisitShopActivity = (XtVisitShopActivity) getActivity();
                xtVisitShopActivity.changeXtvisitFragment(xtquickcollectfragment, "xtnuminputfragment");
                break;

            case R.id.xtbf_sayhi_rl_termminorchannel:// 单店占有率
                final List<KvStc> tempLst = service.queryNoProIndexValueId31();
                mAlertViewExt = new AlertView("请选择单店占有率", null,
                        null, null, null, getActivity(), AlertView.Style.ActionSheet, this);
                ViewGroup areaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
                ListView arealistview = (ListView) areaextView.findViewById(R.id.alert_list);
                AlertKeyValueAdapter areakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), tempLst,
                        new String[]{"key", "value"}, zhanyoulvIndexValueId);
                arealistview.setAdapter(areakeyValueAdapter);
                arealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        xtZhanyoulvTv.setText(tempLst.get(position).getValue());
                        zhanyoulvIndexValueId = tempLst.get(position).getKey();
                        //termInfoTemp.setAreatype(tempLst.get(position).getKey());
                        mAlertViewExt.dismiss();
                    }
                });
                mAlertViewExt.addExtView(areaextView);
                mAlertViewExt.setCancelable(true).setOnDismissListener(this);
                mAlertViewExt.show();

                break;

            default:
                break;
        }
    }

    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {

    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<XtCheckIndexFragment> fragmentRef;

        public MyHandler(XtCheckIndexFragment fragment) {
            fragmentRef = new SoftReference<XtCheckIndexFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            XtCheckIndexFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            Bundle bundle = msg.getData();


            switch (msg.what) {
                case INPUT_SUC:// 自动计算指标值
                    fragment.autoCalculateSuc(bundle);
                    break;
            }
        }
    }

    /**
     * 自动计算指标值
     */
    public void autoCalculateSuc(Bundle bundle) {

        String proId = "";
        String indexId = "";
        if (bundle != null) {
            proId = FunUtil.isBlankOrNullTo(bundle.getString("proId"), "-1");// 产品主键
            indexId = FunUtil.isBlankOrNullTo(bundle.getString("indexId"), "-1");// 指标主键: ad3030fb-e42e-47f8-a3ec-4229089aab5d
        }
        service.calculateIndex(channelId, proItemLst, calculateLst, proId, indexId);
        xtCaculateAdapter.notifyDataSetChanged();
        ViewUtil.setListViewHeight(calculateLv);
    }

    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");
        long time3 = new Date().getTime();
        // 如果是查看操作，则不做数据校验及数据处理
        if (ConstValues.FLAG_1.equals(seeFlag)) return;
        //if(isLoadingData) return;

        XtProIndex indexItem;
        XtProIndexValue valueItem;
        ListView indexValueLv;
        KvStc kvItem;
        RadioGroup resultRg;
        RadioButton resultRb;
        EditText resultEt;
        Spinner resultSp;

        // 遍历lv，获取各指标的指标值
        for (int i = 0; i < calculateLst.size(); i++) {
            if (calculateLv == null || calculateLv.getChildAt(i) == null || calculateLv.getChildAt(i).findViewById(R.id.caculate_lv_indexvalue) == null)
                continue;
            indexItem = calculateLst.get(i);
            indexValueLv = (ListView) calculateLv.getChildAt(i).findViewById(R.id.item_xt_caculate_lv_indexvalue);
            if (indexValueLv == null)
                continue;
            for (int j = 0; j < indexItem.getIndexValueLst().size(); j++) {
                valueItem = indexItem.getIndexValueLst().get(j);
                if (valueItem == null)
                    continue;

                /*if (ConstValues.FLAG_4.equals(valueItem.getIndexType())
                        || ConstValues.FLAG_0.equals(valueItem.getIndexType())) {
                    if (indexValueLv.getChildAt(j).findViewById(
                            R.id.caculate_bt_indexvalue) == null) continue;
                    valueItem.setIndexValueId(FunUtil.isBlankOrNullTo(
                            indexValueLv.getChildAt(j).findViewById(R.id.caculate_bt_indexvalue).getTag(), "-1"));
                }*/

                if (indexValueLv.getChildAt(j).findViewById(R.id.item_xt_checkindex_indexvalue) == null)
                    continue;
                valueItem.setIndexValueId(FunUtil.isBlankOrNullTo(indexValueLv.getChildAt(j).findViewById(R.id.item_xt_checkindex_indexvalue).getTag(), "-1"));
            }
        }

        // 遍历获取与产品无关指标采集数据部分
        XtCheckIndexCalculateStc itemStc;
        View itemV;

        // 根据页面上用户选的值  保存数据到表
        for(int i = 0; i < noProIndexLst.size(); i++) {
            itemStc = noProIndexLst.get(i);
            if ("666b74b3-b221-4920-b549-d9ec39a463fd".equals(itemStc.getIndexId())) {// 合作是否到位
                if (xtHezuoSw.getStatus()) {// 合作是否到位
                    itemStc.setIndexValueId("9019cf03-4572-4559-9971-48a27a611c3d");//合作是否到位 shi
                } else  {
                    itemStc.setIndexValueId("8d36d1e5-c776-452e-8893-589ad786d71d");		// 合作是否到位否															// 是
                }
            } else if ("df2e88c9-246f-40e2-b6e5-08cdebf8c281".equals(itemStc.getIndexId())) {// 是否高质量配送
                if (xtPeisongSw.getStatus()) {// 合作是否到位
                    itemStc.setIndexValueId("460647a9-283a-44ea-b11f-42efe1fd62e4");//是否高质量配送 shi
                } else  {
                    itemStc.setIndexValueId("bf600cfe-f70d-4170-857d-65dd59740d57");		// 是否高质量配送否															// 是
                }

            } else if ("59802090-02ac-4146-9cc3-f09570c36a26".equals(itemStc.getIndexId())) {// 我品占有率
                //kvItem = (KvStc)zhanyoulvSp.getSelectedItem();
                itemStc.setIndexValueId(zhanyoulvIndexValueId);
            }
        }

        // 保存查指标页面的数据
        service.saveCheckIndex(visitId, termId, calculateLst, proItemLst, noProIndexLst);

        // 获取产品组合是否达标值
        /*for (int k = 0; k < groupRg.getChildCount(); k++) {
        	RadioButton groupRgChildRb = (RadioButton)groupRg.getChildAt(k);
            if (groupRgChildRb.isChecked()) {
            	vo.setIfrecstand(groupRgChildRb.getHint().toString());
                break;
            }
        }*/

        if(xtProstatusSw.getStatus()){
            vo.setIfrecstand("Y");// 产品组合  未达标
        }else{
            vo.setIfrecstand("N");// 产品组合  达标
        }
        // 保存产品组合是否达标
        service.saveMstGroupproductMTemp(vo);


        // 遍历活动状态的达成情况
        dd.tsingtaopad.view.DdSlideSwitch statusSw;
        EditText reachNum;
        for (int i = 0; i < promotionLst.size(); i++) {
            itemV = promotionLv.getChildAt(i);
            if (itemV == null || itemV.findViewById(R.id.item_xt_checkindex_sw_isacomplish) == null) continue;
            statusSw = (dd.tsingtaopad.view.DdSlideSwitch)itemV.findViewById(R.id.item_xt_checkindex_sw_isacomplish);
            reachNum = (EditText)itemV.findViewById(R.id.item_xt_checkindex_et_zushu);
            if (statusSw.getStatus()) {
                promotionLst.get(i).setIsAccomplish(ConstValues.FLAG_1);//达成
            } else {
                promotionLst.get(i).setIsAccomplish(ConstValues.FLAG_0);//未达成

                // 根据是否有拍照,删除
                // 删除记录
                /*String pictypekey = promotionLst.get(i).getPromotKey();
                AndroidDatabaseConnection connection = null;
                try {
                    DatabaseHelper helper = DatabaseHelper.getHelper(getActivity());
                    MstCameraiInfoMDao dao = (MstCameraiInfoMDao) helper.getMstCameraiInfoMDao();
                    connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
                    connection.setAutoCommit(false);
                    // 删除该记录
                    dao.deletePicByCamerakeyATerminal(helper, pictypekey,termId);
                    connection.commit(null);

                } catch (Exception e) {
                    Log.e(TAG, "删除图片表记录失败", e);
                    try {
                        connection.rollback(null);
                    } catch (Exception e1) {
                        Log.e(TAG, "删除图片表记录失败", e1);
                    }
                }*/
                // 删除本地照片(以为每次确定上传都会删除文件夹,就不做操作了)
            }
            promotionLst.get(i).setReachNum(reachNum.getText().toString());

        }
        service.saveXtPromotionTemp(visitId, termId, promotionLst);
        long time4= new Date().getTime();
        Log.e("Optimization", "查指标执行数据库"+(time4-time3));
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
