package et.tsingtaopad.dd.ddxt.checking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddxt.checking.num.XtCaculateFragment;
import et.tsingtaopad.dd.ddxt.shopvisit.XtVisitShopActivity;
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
public class XtCaculateAdapter extends BaseAdapter {

    private Activity context;
    private List<XtProIndex> dataLst;
    private List<KvStc> indexValuelst;
    private List<KvStc> valueLst;
    private List<XtProItem> itemLst;
    XtCheckIndexFragment.MyHandler handler;

    /**
     *  构造方法
     *  
     * @param context
     * @param dataLst       分项采集部分要显示的数据
     * @param indexValuelst 指标、指标值关系数据
     * @param itemLst       每个产品对应的采集项及数据
     */
    public XtCaculateAdapter(Activity context, List<XtProIndex> dataLst,
                             List<KvStc> indexValuelst, List<XtProItem> itemLst,XtCheckIndexFragment.MyHandler handler) {
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
        holder.indexValueLv.setAdapter(new XtCaculateItemAdapter(context, item.getIndexValueLst(), valueLst, itemLst,item.getIndexId()));
        //ViewUtil.setListViewHeight(holder.indexValueLv);
        holder.indexValueLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XtVisitShopActivity xtVisitShopActivity = (XtVisitShopActivity)context;

                XtProIndexValue xtProIndexValue = item.getIndexValueLst().get(position);
                String proName = xtProIndexValue.getProName();

                // 获取该产品采集项
                List<XtProItem> tempLst = new ArrayList<XtProItem>();
                //TODO
                for (XtProItem item : itemLst) {
                    if (xtProIndexValue.getProId().equals(item.getProId()) && item.getIndexIdLst().contains(xtProIndexValue.getIndexId())) {
                        tempLst.add(item);
                    }
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("tempLst", (Serializable) tempLst);
                bundle.putString("proName", proName);
                XtCaculateFragment xtCaculateFragment = new XtCaculateFragment(xtProIndexValue,handler);
                xtCaculateFragment.setArguments(bundle);
                xtVisitShopActivity.changeXtvisitFragment(xtCaculateFragment,"xtnuminputfragment");
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView indexNameTv;
        private ListView indexValueLv;
    }


}
