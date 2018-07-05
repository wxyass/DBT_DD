package dd.tsingtaopad.dd.ddzs.zsinvoicing;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.db.table.MitValsupplyMTemp;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：InvoicingCheckGoodsAdapter.java</br>
 * 功能描述: 巡店拜访--进销存，核查进销存Adapter</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ZsInvoicingAdapter extends
                    BaseAdapter implements OnFocusChangeListener ,OnClickListener {

    private Activity context;
    private List<MitValsupplyMTemp> dataLst;
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



    public ZsInvoicingAdapter(Activity context, List<MitValsupplyMTemp> dataLst, IClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener=listener;
        
        
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
        Log.d("check ", position+"  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zdzs_invoicing, null);
            holder.ll_all = (LinearLayout)convertView.findViewById(R.id.item_zs_invoicing_ll_all);// 整体
            holder.productNameTv = (TextView)convertView.findViewById(R.id.item_zs_invoicing_tv_proname);// 产品名称
            holder.agencyTv = (TextView)convertView.findViewById(R.id.item_zs_invoicing_tv_agencyname);// 经销商名称
            holder.statueTv = (TextView)convertView.findViewById(R.id.item_zs_invoicing_tv_statue);// 未稽查tv
            holder.statueRl = (RelativeLayout)convertView.findViewById(R.id.item_zs_invoicing_Rl_statue);// 未稽查Rl
            holder.channelPriceEt = (TextView)convertView.findViewById(R.id.item_zs_invoicing_et_prevstore);// 渠道价
            holder.sellPriceEt = (TextView)convertView.findViewById(R.id.item_zs_invoicing_et_daysellnum);// 零售价
            holder.prevNumEt = (TextView)convertView.findViewById(R.id.item_zs_invoicing_et_prevnum);// 订单量
            holder.addcardEt = (TextView)convertView.findViewById(R.id.item_zs_invoicing_et_addcard);// 累计卡
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        final MitValsupplyMTemp item = dataLst.get(position);



        // 产品名称
        holder.productNameTv.setHint(item.getValpro());
        holder.productNameTv.setText(item.getValproname());

        // 未稽查
        holder.statueRl.setTag(position);
        holder.statueRl.setOnClickListener(listener);

        //订单量(原名称是上周期进货总量)
        if (ConstValues.FLAG_0.equals(item.getValsdd())) {
            holder.prevNumEt.setHint(item.getValsdd());
        } else {
            holder.prevNumEt.setText(item.getValsdd());
        }
        
        //累计卡)
        if (ConstValues.FLAG_0.equals(item.getValsljk())) {
            holder.addcardEt.setHint("0");
        } else if("0.0".equals(item.getValsljk())){
            holder.addcardEt.setHint("0");
        }else {
            holder.addcardEt.setText(item.getValsljk());
        }

        if("Y".equals(item.getValaddagencysupply())){// 是否督导新增
            holder.statueTv.setText("督导新增");
            holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_notcheck));
        }else{// 业代的供货数据
            // 未稽查
            if("N".equals(item.getValagencysupplyflag())){
                holder.statueTv.setText("错误");
                holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_error));
            }else if("Y".equals(item.getValagencysupplyflag())){
                holder.statueTv.setText("正确");
                holder.statueTv.setTextColor(context.getResources().getColor(R.color.zdzs_dd_yes));
            }else{
                holder.statueTv.setText("未稽查");
                holder.statueTv.setTextColor(context.getResources().getColor(R.color.gray_color_666666));
            }

            // 背景
            if("Y".equals(item.getValproerror())){
                holder.ll_all.setBackgroundColor(Color.LTGRAY);
                holder.statueTv.setText("失效");
            }else{
                holder.ll_all.setBackgroundColor(Color.WHITE);
            }
        }
        

        // 经销商
        holder.agencyTv.setHint(item.getValagency());
        holder.agencyTv.setText(item.getValagencyname());


        // 渠道价
        if (ConstValues.FLAG_0.equals(item.getValsqd())) {
            holder.channelPriceEt.setHint(item.getValsqd());
            holder.channelPriceEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getValsqd())){
            holder.channelPriceEt.setText(item.getValsqd());
        }else{
            holder.channelPriceEt.setHint(item.getValsqd());
            holder.channelPriceEt.setText(null);
        }
        holder.channelPriceEt.setTag(position);
        //holder.channelPriceEt.setOnFocusChangeListener(this);

        // 零售价
        if (ConstValues.FLAG_0.equals(item.getValsls())) {
            holder.sellPriceEt.setHint(item.getValsls());
            holder.sellPriceEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getValsls())){
            holder.sellPriceEt.setText(item.getValsls());
        }else{
            holder.sellPriceEt.setHint(item.getValsls());
            holder.sellPriceEt.setText(null);
        }
        holder.sellPriceEt.setTag(position);
        

        
        
        return convertView;
    }

    private class ViewHolder {
        private LinearLayout ll_all;
        private TextView productNameTv;
        private TextView agencyTv;
        private TextView statueTv;
        private RelativeLayout statueRl;
        private TextView prevNumEt;
        //private TextView prevNumSumTV;
        private TextView channelPriceEt;
        private TextView sellPriceEt;
        private TextView addcardEt;
        //private Button firstdate;
    }
    
    public void onFocusChange(View v, boolean hasFocus) {
        EditText et = (EditText)v;
        int position = (Integer)v.getTag();
        if (position == delPosition) return;
        if (position > delPosition && delPosition != -1) {
            position = position -1;
        }
        if (position > -1) {
            MitValsupplyMTemp stc = dataLst.get(position);
            String content = et.getText().toString();
            switch (et.getId()) {
            case R.id.checkgoods_et_prevnum:// //订单量//
                stc.setValsdd(content);
                break;
                
            case R.id.checkgoods_et_daysell:// //日销量
                stc.setValsrxl(content);
                break;
                
            default:
    
                break;
            }
        }
    }

    public int getDelPosition() {
        return delPosition;
    }

    public void setDelPosition(int delPosition) {
        this.delPosition = delPosition;
    }

	@Override
	public void onClick(View v) {
		final Button dateBtn = (Button) v;
		int position = (Integer) v.getTag();
		final MitValsupplyMTemp stc = dataLst.get(position);
		switch (dateBtn.getId()) {
		// 最早生产时间
		case R.id.checkgoods_et_firstdate:
			DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(),
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							calendar.set(year, monthOfYear, dayOfMonth);
							yearr = year;
							month = monthOfYear;
							day = dayOfMonth;
							if (dayOfMonth < 10) {
								aday = "0" + dayOfMonth;
							} else {
								aday = Integer.toString(dayOfMonth);
							}
							dateselect = (Integer.toString(year)
									+ String.format("%02d", monthOfYear + 1) + aday);
							dateselects = (Integer.toString(year)
									+ String.format("%02d", monthOfYear + 1)
									+ aday + "000000");
							dateselectx = (Integer.toString(year)
									+ String.format("%02d", monthOfYear + 1)
									+ aday + "235959");
							selectDate = (Integer.toString(year) + "-"
									+ String.format("%02d", monthOfYear + 1)
									+ "-" + aday);
							dateBtn.setText(selectDate);
							//stc.setFristdate(selectDate);
						}
					}, yearr, month, day);
			if (!dateDialog.isShowing()) {
				dateDialog.show();
			}

			break;

		default:

			break;
		}
	}
}