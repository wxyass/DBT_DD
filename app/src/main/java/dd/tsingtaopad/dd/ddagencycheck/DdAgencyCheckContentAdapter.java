package dd.tsingtaopad.dd.ddagencycheck;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.db.table.MstInvoicingInfo;
import dd.tsingtaopad.dd.ddagencycheck.domain.ZsInOutSaveStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：InvoicingCheckGoodsAdapter.java</br>
 * 功能描述: 巡店拜访--进销存，核查进销存Adapter</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdAgencyCheckContentAdapter extends
                    BaseAdapter implements OnFocusChangeListener {

    private Activity context;
    List<ZsInOutSaveStc> dataLst;
    private int delPosition = -1;

    // 时间控件



    public DdAgencyCheckContentAdapter(Activity context, List<ZsInOutSaveStc> dataLst) {
        this.context = context;
        this.dataLst = dataLst;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_agency_check_content, null);
            holder.ll_all = (LinearLayout)convertView.findViewById(R.id.item_agency_check_content_ll_all);// 整体
            holder.productNameTv = (TextView)convertView.findViewById(R.id.item_agency_check_content_tv_proname);// 产品名称
            holder.procodeTv = (TextView)convertView.findViewById(R.id.item_agency_check_content_tv_agencyname);// 产品编码
            holder.channelPriceEt = (TextView)convertView.findViewById(R.id.item_agency_check_content_et_prevstore);// 期末库存
            holder.realStoreEt = (EditText)convertView.findViewById(R.id.item_agency_check_content_et_daysellnum);// 实际库存
            holder.desEt = (EditText)convertView.findViewById(R.id.item_agency_check_content_et_des);// 备注
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        final ZsInOutSaveStc item = dataLst.get(position);

        // 产品名称
        holder.productNameTv.setHint(item.getProname());
        holder.productNameTv.setText(item.getProname());

        // 产品编码
        holder.procodeTv.setHint("未输入产品编码");
        holder.procodeTv.setText(item.getProcode());

        // 期末库存
        if (ConstValues.FLAG_0.equals(item.getStorenum())) {
            holder.channelPriceEt.setHint(getInt(item.getStorenum())+"");
            holder.channelPriceEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(getInt(item.getStorenum())+"")){
            holder.channelPriceEt.setText(getInt(item.getStorenum())+"");
        }else{
            holder.channelPriceEt.setHint(R.string.hit_input);
            holder.channelPriceEt.setText(null);
        }
        holder.channelPriceEt.setTag(position);
        //holder.channelPriceEt.setOnFocusChangeListener(this);

        // 实际库存
        if (ConstValues.FLAG_0.equals(item.getRealstore())) {
            holder.realStoreEt.setHint(item.getRealstore()+"");
            holder.realStoreEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getRealstore()+"")){
            holder.realStoreEt.setText(item.getRealstore()+"");
        }else{
            holder.realStoreEt.setHint(R.string.hit_input);
            holder.realStoreEt.setText(null);
        }
        holder.realStoreEt.setTag(position);

        // 备注信息
        holder.desEt.setText(item.getDes());
        
        return convertView;
    }

    public static int getInt(double number){
        BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

    private class ViewHolder {
        private LinearLayout ll_all;
        private TextView productNameTv;
        private TextView procodeTv;
        private TextView channelPriceEt;
        private EditText realStoreEt;
        private EditText desEt;
    }
    
    public void onFocusChange(View v, boolean hasFocus) {
        EditText et = (EditText)v;
        int position = (Integer)v.getTag();
        if (position == delPosition) return;
        if (position > delPosition && delPosition != -1) {
            position = position -1;
        }
        if (position > -1) {
            MstInvoicingInfo stc = dataLst.get(position);
            String content = et.getText().toString();
            switch (et.getId()) {
            case R.id.checkgoods_et_prevnum:
                //stc.setPrevNum(content);
                break;
                
            case R.id.checkgoods_et_daysell:
                //stc.setDaySellNum(content);
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

}