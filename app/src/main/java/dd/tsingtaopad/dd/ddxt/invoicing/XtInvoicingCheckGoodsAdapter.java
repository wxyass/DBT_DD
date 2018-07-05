package dd.tsingtaopad.dd.ddxt.invoicing;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.TextView;


import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：InvoicingCheckGoodsAdapter.java</br>
 * 功能描述: 巡店拜访--进销存，核查进销存Adapter</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtInvoicingCheckGoodsAdapter extends
                    BaseAdapter implements OnFocusChangeListener ,OnClickListener {
    
    private Activity context;
    private List<XtInvoicingStc> dataLst;
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
 	
 	
    
    public XtInvoicingCheckGoodsAdapter(Activity context, List<XtInvoicingStc> dataLst) {
        this.context = context;
        this.dataLst = dataLst;
        
        
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_invoicing_checkgoods, null);
            holder.productNameTv = (TextView)convertView.findViewById(R.id.item_checkgoods_tv_proname);// 产品名称
            holder.agencyTv = (TextView)convertView.findViewById(R.id.item_checkgoods_tv_agencyname);// 经销商名称
            holder.prevNumEt = (EditText)convertView.findViewById(R.id.item_checkgoods_et_prevnum);// 订单量
            //holder.prevNumSumTV = (TextView)convertView.findViewById(R.id.checkgoods_et_prevnumsum);// 本月合计
            holder.prevStoreTv = (TextView)convertView.findViewById(R.id.item_checkgoods_et_prevstore);// 上次库存
            holder.daySellEt = (EditText)convertView.findViewById(R.id.item_checkgoods_et_daysellnum);// 日销量
            //holder.firstdate = (Button)convertView.findViewById(R.id.checkgoods_et_firstdate);// 最早生产日期
            holder.addcardEt = (EditText)convertView.findViewById(R.id.item_askgoods_et_addcard);// 累计卡
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        final XtInvoicingStc item = dataLst.get(position);
        holder.productNameTv.setHint(item.getProId());
        holder.productNameTv.setText(item.getProName());
        //订单量(原名称是上周期进货总量)
        if (ConstValues.FLAG_0.equals(item.getPrevNum())) {
            holder.prevNumEt.setHint(item.getPrevNum());
        } else {
            holder.prevNumEt.setText(item.getPrevNum());
        }
        
        //累计卡)
        if (ConstValues.FLAG_0.equals(item.getAddcard())) {
            holder.addcardEt.setHint("请输入");
        } else if("0.0".equals(item.getAddcard())){
            holder.addcardEt.setHint("请输入");
        }else {
            holder.addcardEt.setText(item.getAddcard());
        }
        
        //上周期进货总量总和 // 本月合计
        //holder.prevNumSumTV.setText(item.getPrevNumSum());
        
        holder.prevNumEt.setTag(position);
        holder.prevNumEt.setOnFocusChangeListener(this);
        //上次库存
        holder.prevStoreTv.setText(item.getPrevStore());
        //日销量
        if (ConstValues.FLAG_0.equals(item.getDaySellNum())) {
            holder.daySellEt.setHint(item.getDaySellNum());
        } else {
            holder.daySellEt.setText(item.getDaySellNum());
        }
        holder.daySellEt.setTag(position);
        holder.daySellEt.setOnFocusChangeListener(this);

        holder.agencyTv.setHint(item.getAgencyId());
        holder.agencyTv.setText(item.getAgencyName());
        

        
        
        return convertView;
    }

    private class ViewHolder {
        private TextView productNameTv;
        private TextView agencyTv;
        private EditText prevNumEt;
        //private TextView prevNumSumTV;
        private TextView prevStoreTv;
        private EditText daySellEt;
        private EditText addcardEt;
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
            XtInvoicingStc stc = dataLst.get(position);
            String content = et.getText().toString();
            switch (et.getId()) {
            case R.id.checkgoods_et_prevnum:
                stc.setPrevNum(content);
                break;
                
            case R.id.checkgoods_et_daysell:
                stc.setDaySellNum(content);
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
		final XtInvoicingStc stc = dataLst.get(position);
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
							stc.setFristdate(selectDate);
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