package et.tsingtaopad.dd.ddzs.zscheckindex;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.listviewintf.IClick;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;

/**
 * 文件名：XtPromotionAdapter.java</br>
 * 功能描述: 巡店拜访-查指标-促销活动Adapter</br>
 */
public class ZsPromotionAdapter extends BaseAdapter {

    private Activity context;
    private List<CheckIndexPromotionStc> dataLst;
    private int currentItem = -1;
    private IClick listener;

    public ZsPromotionAdapter(Activity context, List<CheckIndexPromotionStc> dataLst, IClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener=listener;
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
    public CheckIndexPromotionStc getItem(int arg0) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zdzs_checkindex_promotion, null);

            holder.promotionLl = (LinearLayout) convertView.findViewById(R.id.item_zs_checkindex_ll_promo);// 第一行
            holder.promotionNameTv = (TextView) convertView.findViewById(R.id.item_zs_checkindex_tv_promotionname);// 活动名称
            holder.showProTv = (TextView) convertView.findViewById(R.id.item_zs_checkindex_tv_proname);// 产品名称

            holder.reachnumLl = (LinearLayout) convertView.findViewById(R.id.item_zs_checkindex_ll_reachnum);//第二行
            holder.statueTv = (TextView)convertView.findViewById(R.id.item_zs_checkindex_tv_statue);// 未稽查tv
            holder.statueRl = (RelativeLayout)convertView.findViewById(R.id.item_zs_checkindex_rl_statue);// 未稽查Rl
            holder.reachnum = (TextView) convertView.findViewById(R.id.item_zs_checkindex_et_zushu);//达成组数

            holder.proLl = (LinearLayout) convertView.findViewById(R.id.item_zs_checkindex_ll_pro);//产品展示,整体
            holder.proNameTv = (TextView) convertView.findViewById(R.id.item_zs_checkindex_lv_proname);//产品展示.Tv

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CheckIndexPromotionStc item = dataLst.get(position);
        holder.promotionNameTv.setHint(item.getPromotKey());
        holder.promotionNameTv.setText(item.getPromotName());// 活动名称
        holder.proNameTv.setText(item.getProName());// 产品名称默认关闭


        // 对促销活动按钮 初始化
        if (ConstValues.FLAG_1.equals(item.getIsAccomplish())) {
            holder.reachnum.setVisibility(View.VISIBLE);
            holder.reachnum.setText(item.getReachNum());
        } else {
            holder.reachnum.setVisibility(View.VISIBLE);
            holder.reachnum.setText(null);
            holder.reachnum.setHint("0");
        }

        // 未稽查
        if("N".equals(item.getValistruenumflag())){// 达成组数正确与否
            holder.statueTv.setText("错误");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_error));
        }else if("Y".equals(item.getValistruenumflag())){
            holder.statueTv.setText("正确");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_yes));
        }else{
            holder.statueTv.setText("未稽查");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.gray_color_666666));
        }

        // 是否达成监听
        /*holder.statusSw.setOnLongSwitchChangedListener(new MySwitchChangedListener(holder) {
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
        });*/

        holder.promotionLl.setTag(position);

        // 是否展示产品列表
        if(currentItem == position){
            holder.proLl.setVisibility(View.VISIBLE);
        }else{
            holder.proLl.setVisibility(View.GONE);
        }
        holder.promotionLl.setOnClickListener(new OnClickListener() {
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

        // 未稽查
        holder.reachnumLl.setTag(position);
        holder.reachnumLl.setOnClickListener(listener);

        return convertView;
    }

    private class ViewHolder {
        private LinearLayout promotionLl;
        private TextView promotionNameTv;
        private TextView showProTv;
        private TextView statueTv;
        private RelativeLayout statueRl;
        private LinearLayout reachnumLl;
        private TextView reachnum;
        private LinearLayout proLl;
        private TextView proNameTv;
    }

}
