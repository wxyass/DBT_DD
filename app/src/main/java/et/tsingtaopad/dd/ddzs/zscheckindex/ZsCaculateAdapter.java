package et.tsingtaopad.dd.ddzs.zscheckindex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddzs.zscheckindex.zsnum.ZsCaculateAmendFragment;
import et.tsingtaopad.dd.ddzs.zssayhi.ZsSayhiFragment;
import et.tsingtaopad.dd.ddzs.zsshopvisit.ZsVisitShopActivity;
import et.tsingtaopad.initconstvalues.domain.KvStc;


/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：CaculateAdapter.java</br>
 * 作者：hongen   </br>
 * 创建时间：2013-12-13</br>      
 * 功能描述: 巡店拜访-查指标的分项采集部分一级Adapter</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ZsCaculateAdapter extends BaseAdapter {

    private final String TAG = "ZsCaculateAdapter";

    private Activity context;
    private List<XtProIndex> dataLst;
    private List<KvStc> indexValuelst;
    private List<KvStc> valueLst;
    private List<XtProItem> itemLst;
    ZsCheckIndexFragment.MyHandler handler;

    /**
     *  构造方法
     *
     * @param context
     * @param dataLst       分项采集部分要显示的数据
     * @param indexValuelst 指标、指标值关系数据
     * @param itemLst       每个产品对应的采集项及数据
     */
    public ZsCaculateAdapter(Activity context, List<XtProIndex> dataLst,
                             List<KvStc> indexValuelst, List<XtProItem> itemLst, ZsCheckIndexFragment.MyHandler handler) {
        this.context = context;
        this.dataLst = dataLst; //

        this.itemLst = itemLst;// 所有采集项数据
        if (CheckUtil.IsEmpty(indexValuelst)) {
            this.indexValuelst = new ArrayList<KvStc>();
        } else {
            this.indexValuelst = indexValuelst;
        }
        this.handler = handler; //
    }

    @Override
    public int getCount() {
        if (CheckUtil.IsEmpty(dataLst)) {
            return 0;
        } else {
            return dataLst.size();
        }
    }

    @Override
    public Object getItem(int arg0) {
        if (CheckUtil.IsEmpty(dataLst)) {
            return null;
        } else {
            return dataLst.get(arg0);
        }
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("calculate ", position+"===  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_checkindex_index, null);
            holder.indexNameTv = (TextView) convertView.findViewById(R.id.item_xt_caculate_tv_indexvalue);
            holder.indexValueLv = (ListView) convertView.findViewById(R.id.item_xt_caculate_lv_indexvalue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 获取每一个指标对象
        final XtProIndex item = dataLst.get(position);
        holder.indexNameTv.setHint(item.getIndexId());
        holder.indexNameTv.setText(item.getIndexName());
        for (KvStc kvItem : indexValuelst) {
            if (kvItem.getKey().equals(item.getIndexId())) {
                valueLst = kvItem.getChildLst();
            }
        }
        // (上下文,有几个产品,指标标准,所有的采集项数据,当前指标key)
        holder.indexValueLv.setAdapter(new ZsCaculateItemAdapter(context, item.getIndexValueLst(), valueLst, itemLst,item.getIndexId()));
        //ViewUtil.setListViewHeight(holder.indexValueLv);
        holder.indexValueLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 请选择结果
                alertShow3(item,position);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView indexNameTv;
        private ListView indexValueLv;
    }


    private AlertView mAlertViewExt;//窗口拓展例子

    /**
     *
     * @param item 那个指标
     * @param posi 指标里的第几个产品
     */
    public void alertShow3(final XtProIndex item,int posi) {


        final XtProIndexValue xtProIndexValue = item.getIndexValueLst().get(posi);
        final String proName = xtProIndexValue.getProName();

        // 获取该产品采集项
        final List<XtProItem> tempLst = new ArrayList<XtProItem>();// 用于下个页面展示
        final List<KvStc> tempLstsureOrFail = new ArrayList<>();// 用于弹窗展示
        //TODO
        for (XtProItem xtProItem : itemLst) {
            if (xtProIndexValue.getProId().equals(xtProItem.getProId())
                    && xtProItem.getIndexIdLst().contains(xtProIndexValue.getIndexId())) {

                tempLst.add(xtProItem);
                // 库存: 100  xtProItem.getItemName()+": "+(xtProItem.getChangeNum()+xtProItem.getFinalNum())
                //tempLstsureOrFail.add(new KvStc("",xtProItem.getItemName()+": "+(xtProItem.getChangeNum()+xtProItem.getFinalNum()),"-1"));
                tempLstsureOrFail.add(new KvStc("",xtProItem.getItemName()+": "+(xtProItem.getValitem()),"-1"));
            }
        }


        /*
        List<KvStc> sureOrFail = new ArrayList<>();
        sureOrFail.add(new KvStc("zhengque","正确","-1"));
        sureOrFail.add(new KvStc("cuowu","错误(去修正)","-1"));

        mAlertViewExt = new AlertView("请选择结果", null, null, null, null, context, AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.zs_checkindex_alert_list_form, null);
        et.tsingtaopad.view.MyGridView gridview = (et.tsingtaopad.view.MyGridView) extView.findViewById(R.id.zs_checkindex_alert_grid);
        ListView listview = (ListView) extView.findViewById(R.id.zs_checkindex_alert_list);

        // gridview
        GridKeyValueAdapter gridKeyValueAdapter = new GridKeyValueAdapter(context, tempLstsureOrFail,
                new String[]{"key", "value"}, "zhengque");
        gridview.setAdapter(gridKeyValueAdapter);

        // listview
        AlertKeyValueAdapter keyValueAdapter = new AlertKeyValueAdapter(context, sureOrFail,
                new String[]{"key", "value"}, "zhengque");
        listview.setAdapter(keyValueAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mAlertViewExt.dismiss();

                if(0==position){
                   //LatteLoader.showLoading(context);// 处理数据中,在ZsCheckIndexFragment的showIndexAdapter中关闭
                    // 修改对错
                    //xtProIndexValue.setValchecktypeflag("Y");
                    //handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_INDEX_AMEND);



                    ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity)context;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tempLst", (Serializable) tempLst);
                    bundle.putString("proName", proName);
                    bundle.putString("valchecktypeflag", "Y");
                    ZsCaculateAmendFragment xtCaculateFragment = new ZsCaculateAmendFragment(xtProIndexValue,handler);
                    xtCaculateFragment.setArguments(bundle);
                    xtVisitShopActivity.changeXtvisitFragment(xtCaculateFragment,"xtnuminputfragment");
                }
                else if(1==position){
                    ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity)context;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tempLst", (Serializable) tempLst);
                    bundle.putString("proName", proName);
                    bundle.putString("valchecktypeflag", "N");
                    ZsCaculateAmendFragment xtCaculateFragment = new ZsCaculateAmendFragment(xtProIndexValue,handler);
                    xtCaculateFragment.setArguments(bundle);
                    xtVisitShopActivity.changeXtvisitFragment(xtCaculateFragment,"xtnuminputfragment");
                }


            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();*/


        new AlertView("请选择核查结果", null, "未稽查", null,
                new String[]{"正确", "错误"},
                context, AlertView.Style.ActionSheet,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        // Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 正确
                            ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity)context;
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("tempLst", (Serializable) tempLst);
                            bundle.putString("proName", proName);
                            bundle.putString("valchecktypeflag", "Y");
                            ZsCaculateAmendFragment xtCaculateFragment = new ZsCaculateAmendFragment(xtProIndexValue,handler);
                            xtCaculateFragment.setArguments(bundle);
                            xtVisitShopActivity.changeXtvisitFragment(xtCaculateFragment,"xtnuminputfragment");
                        } else if (1 == position) {// 跳转数据录入
                            ZsVisitShopActivity xtVisitShopActivity = (ZsVisitShopActivity)context;
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("tempLst", (Serializable) tempLst);
                            bundle.putString("proName", proName);
                            bundle.putString("valchecktypeflag", "N");
                            ZsCaculateAmendFragment xtCaculateFragment = new ZsCaculateAmendFragment(xtProIndexValue,handler);
                            xtCaculateFragment.setArguments(bundle);
                            xtVisitShopActivity.changeXtvisitFragment(xtCaculateFragment,"xtnuminputfragment");
                        }
                        else if (-1 == position) {// 跳转数据录入

                            // 修改对错
                            xtProIndexValue.setValchecktypeflag("");

                            handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_INDEX_AUTO_AMEND);
                        }

                    }
                }).setCancelable(true).show();
    }

}
