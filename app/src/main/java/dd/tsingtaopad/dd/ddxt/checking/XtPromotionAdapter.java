package dd.tsingtaopad.dd.ddxt.checking;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;
import dd.tsingtaopad.view.DdSlideSwitch;

/**
 * 文件名：XtPromotionAdapter.java</br>
 * 功能描述: 巡店拜访-查指标-促销活动Adapter</br>
 */
public class XtPromotionAdapter extends BaseAdapter {

    private Activity context;
    private List<CheckIndexPromotionStc> dataLst;
    private String visitDate;
    private String seeFlag;
    private int currentItem = -1;

    public XtPromotionAdapter(Activity context, List<CheckIndexPromotionStc> dataLst, String visitDate, String seeFlag) {
        this.context = context;
        this.dataLst = dataLst;
        this.visitDate = visitDate;
        this.seeFlag = seeFlag;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("promotion ", position + "  " + DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_checkindex_promotion, null);
            holder.promotionNameTv = (TextView) convertView.findViewById(R.id.item_xt_checkindex_tv_promotionname);// 活动名称
            holder.showProTv = (TextView) convertView.findViewById(R.id.item_xt_checkindex_tv_proname);// 产品名称
            holder.statusSw = (dd.tsingtaopad.view.DdSlideSwitch) convertView.findViewById(R.id.item_xt_checkindex_sw_isacomplish);
            holder.reachnum = (EditText) convertView.findViewById(R.id.item_xt_checkindex_et_zushu);//达成组数
            holder.proLl = (LinearLayout) convertView.findViewById(R.id.item_xt_checkindex_ll_pro);//产品展示,整体
            holder.proNameTv = (TextView) convertView.findViewById(R.id.item_xt_checkindex_lv_proname);//产品展示.Tv
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.statusSw.setTag(position);
        holder.showProTv.setTag(position);

        CheckIndexPromotionStc item = dataLst.get(position);
        holder.promotionNameTv.setHint(item.getPromotKey());
        holder.promotionNameTv.setText(item.getPromotName());// 活动名称
        holder.proNameTv.setText(item.getProName());// 产品名称默认关闭

        // 添加促销活动 隔天默认关闭 //20160301(原因:需求设计如此)
        /*String todaytime = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        String accepttime = visitDate.substring(0, 10);
        if (todaytime.equals(accepttime)) {// 当天 多次进入这家终端
            if (ConstValues.FLAG_1.equals(item.getIsAccomplish())) {
                holder.statusSw.setStatus(true);
                holder.reachnum.setVisibility(View.VISIBLE);
                holder.reachnum.setText(item.getReachNum());

            } else {
                holder.statusSw.setStatus(false);
                holder.reachnum.setVisibility(View.INVISIBLE);
                holder.reachnum.setText(null);
            }
        } else {// 上次拜访 不是当天 (即:当天第一次拜访)
            holder.statusSw.setStatus(false);
            holder.reachnum.setVisibility(View.INVISIBLE);
            holder.reachnum.setText(null);
        }*/
        // 对促销活动按钮 初始化
        if (ConstValues.FLAG_1.equals(item.getIsAccomplish())) {
            holder.statusSw.setStatus(true);
            holder.reachnum.setVisibility(View.VISIBLE);
            holder.reachnum.setText(item.getReachNum());

        } else {
            holder.statusSw.setStatus(false);
            holder.reachnum.setVisibility(View.INVISIBLE);
            holder.reachnum.setText(null);
        }

        // 是否达成监听
        holder.statusSw.setOnLongSwitchChangedListener(new MySwitchChangedListener(holder) {
            @Override
            public void afterTextChanged(DdSlideSwitch obj, int status, ViewHolder holder) {
                if (status == DdSlideSwitch.SWITCH_ON) {
                    holder.reachnum.setVisibility(View.VISIBLE);// 达成组数
                    dataLst.get(position).setIsAccomplish(ConstValues.FLAG_1);// 活动设为达成
                } else {
                    holder.reachnum.setVisibility(View.INVISIBLE);// 达成组数
                    holder.reachnum.setText(null);// 达成组数
                    dataLst.get(position).setIsAccomplish(ConstValues.FLAG_0);// 活动设为未达成
                }
            }
        });

        // 是否展示产品列表
        if(currentItem == position){
            holder.proLl.setVisibility(View.VISIBLE);
        }else{
            holder.proLl.setVisibility(View.GONE);
        }
        holder.showProTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (Integer) v.getTag();
                if (tag == currentItem) {
                    currentItem = -1;
                } else {
                    currentItem = tag;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView promotionNameTv;
        private TextView showProTv;
        private dd.tsingtaopad.view.DdSlideSwitch statusSw;
        private EditText reachnum;
        private LinearLayout proLl;
        private TextView proNameTv;
    }

    abstract class MySwitchChangedListener implements DdSlideSwitch.OnLongSwitchChangedListener {
        private ViewHolder mHolder;
        public MySwitchChangedListener(ViewHolder holder) {
            mHolder = holder;
        }
        @Override
        public void onLongSwitchChanged(DdSlideSwitch obj, int status) {
            afterTextChanged(obj, status, mHolder);
        }
        public abstract void afterTextChanged(DdSlideSwitch obj, int status, ViewHolder holder);
    }

}
