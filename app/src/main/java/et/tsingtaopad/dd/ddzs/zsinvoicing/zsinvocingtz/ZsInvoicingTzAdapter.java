package et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzItemIndex;
import et.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：InvoicingCheckGoodsAdapter.java</br>
 * 功能描述: 巡店拜访--进销存，核查进销存Adapter</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ZsInvoicingTzAdapter extends
                    BaseAdapter implements OnFocusChangeListener ,OnClickListener {

    private Activity context;
    private List<ZsTzItemIndex> dataLst;
    private int delPosition = -1;
    private IClick listener;

    public ZsInvoicingTzAdapter(Activity context, List<ZsTzItemIndex> dataLst, IClick listener) {
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
    public Object getItem(int arg0) {
        if (CheckUtil.IsEmpty(dataLst)) {
            return 0;
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
        Log.d("check ", position+"  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zdzs_invoicing_tz, null);
            holder.ll_all = (LinearLayout)convertView.findViewById(R.id.item_zs_invoicing_tz_ll_all);// 整体
            holder.productNameTv = (TextView)convertView.findViewById(R.id.item_zs_invoicing_tz_tv_proname);// 产品名称
            holder.statueTv = (TextView)convertView.findViewById(R.id.item_zs_invoicing_tz_tv_statue);// 未稽查tv
            holder.statueRl = (RelativeLayout)convertView.findViewById(R.id.item_zs_invoicing_tz_rl_statue);// 未稽查Rl
            holder.valueLv = (et.tsingtaopad.view.NoScrollListView)convertView.findViewById(R.id.item_zs_invoicing_tz_lv);// 进货量

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        final ZsTzItemIndex item = dataLst.get(position);

        // 产品名称
        holder.productNameTv.setHint(item.getValproid());
        holder.productNameTv.setText(item.getValproname());

        // 未稽查
        holder.statueRl.setTag(position);
        holder.statueRl.setOnClickListener(listener);

        // 未稽查
        if("N".equals(item.getValprostatus())){
            holder.statueTv.setText("错误");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_error));
        }else if("Y".equals(item.getValprostatus())){
            holder.statueTv.setText("正确");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_yes));
        }else{
            holder.statueTv.setText("未稽查");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.gray_color_666666));
        }

        // (上下文,有几个产品,指标标准,所有的采集项数据,当前指标key)
        holder.valueLv.setAdapter(new ZsInvoicingTznumAdapter(context, item.getIndexValueLst()));
        return convertView;
    }



    private class ViewHolder {
        private LinearLayout ll_all;
        private TextView productNameTv;
        private TextView statueTv;
        private RelativeLayout statueRl;
        private et.tsingtaopad.view.NoScrollListView valueLv;
    }
    


    public int getDelPosition() {
        return delPosition;
    }

    public void setDelPosition(int delPosition) {
        this.delPosition = delPosition;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}
}