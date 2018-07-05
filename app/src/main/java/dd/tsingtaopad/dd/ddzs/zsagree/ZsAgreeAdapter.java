package dd.tsingtaopad.dd.ddzs.zsagree;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.db.table.MitValagreedetailMTemp;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：InvoicingCheckGoodsAdapter.java</br>
 * 功能描述: 巡店拜访--进销存，核查进销存Adapter</br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ZsAgreeAdapter extends BaseAdapter {

    private Activity context;
    private List<MitValagreedetailMTemp> dataLst;
    private int delPosition = -1;

    // 时间控件
    private String selectDate;
    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    private String dateselect;
    private String dateselects;
    private String dateselectx;
    private IClick listener;


    public ZsAgreeAdapter(Activity context, List<MitValagreedetailMTemp> dataLst, IClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener = listener;


        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
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
        Log.d("check ", position + "  " + DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zdzs_agree, null);

            holder.ll_all = (LinearLayout) convertView.findViewById(R.id.item_zs_agree_ll_all);// 整体
            holder.tv_proname = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_proname);// 产品名称
            holder.Rl_statue = (RelativeLayout) convertView.findViewById(R.id.item_zs_agree_rl_statue);// 未稽查 全部
            holder.tv_statue = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_statue);// 未稽查
            holder.tv_cashtype = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_cashtype);// 兑付形式
            holder.tv_commoney = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_commoney);// 兑付金额
            holder.tv_trunnum = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_trunnum);// 实际数量
            holder.tv_price = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_price);// 单价
            holder.tv_cashdate = (TextView) convertView.findViewById(R.id.item_zs_agree_tv_cashdate);// 兑付日期

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MitValagreedetailMTemp item = dataLst.get(position);

        holder.tv_proname.setText(item.getProname());// 产品名称
        holder.tv_cashtype.setText(item.getCashtype());// 兑付形式
        holder.tv_commoney.setText("¥ "+item.getCommoney());// 兑付金额
        holder.tv_trunnum.setText(item.getTrunnum()); // 实际数量
        holder.tv_price.setText("¥ "+item.getPrice()); // 单价
        holder.tv_cashdate.setText(item.getCashdate());// 兑付日期

        // 未稽查
        if("N".equals(item.getAgreedetailflag())){
            holder.tv_statue.setText("错误");
            holder.tv_statue.setTextColor(context.getResources().getColor(R.color.zdzs_dd_error));
        }else if("Y".equals(item.getAgreedetailflag())){
            holder.tv_statue.setText("正确");
            holder.tv_statue.setTextColor(context.getResources().getColor(R.color.zdzs_dd_yes));
        }else{
            holder.tv_statue.setText("未稽查");
            holder.tv_statue.setTextColor(context.getResources().getColor(R.color.gray_color_666666));
        }

        holder.ll_all.setTag(position);
        holder.ll_all.setOnClickListener(listener);

        return convertView;
    }

    private class ViewHolder {
        private LinearLayout ll_all;
        private TextView tv_proname;
        private RelativeLayout Rl_statue;
        private TextView tv_statue;
        private TextView tv_cashtype;
        private TextView tv_commoney;
        private TextView tv_trunnum;
        private TextView tv_price;
        private TextView tv_cashdate;
    }


}