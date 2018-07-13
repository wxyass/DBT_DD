package et.tsingtaopad.dd.ddxt.chatvie;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：VieStatusAdapter.java</br>
 * 功能描述: 巡店拜访--聊竞品，竞品情况Adapter</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtVieStatusAdapter extends BaseAdapter implements OnFocusChangeListener {
    
    private Activity context;
    private List<XtChatVieStc> dataLst;
    private int delPosition = -1;
    
    public XtVieStatusAdapter(Activity context, List<XtChatVieStc> dataLst) {
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
        Log.d("vieStatus ", position+"===  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_chatvie_viestatus, null);
            holder.proName = (TextView)convertView.findViewById(R.id.item_viestatus_tv_proname);
            holder.mothSellNumEt = (EditText)convertView.findViewById(R.id.item_viestatus_et_monthsell);
            holder.currStoreEt = (EditText)convertView.findViewById(R.id.item_viestatus_et_currstore);
            holder.describeEt = (EditText)convertView.findViewById(R.id.item_viestatus_et_describe);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        XtChatVieStc item = dataLst.get(position);
        holder.proName.setHint(item.getProId());
        holder.proName.setText(item.getProName());
        if (ConstValues.FLAG_0.equals(item.getMonthSellNum())) {
            holder.mothSellNumEt.setHint(item.getMonthSellNum());
            holder.mothSellNumEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getMonthSellNum())){
            holder.mothSellNumEt.setText(item.getMonthSellNum());
        }else{
            holder.mothSellNumEt.setHint(R.string.hit_input);
            holder.mothSellNumEt.setText(null);
        }
        holder.mothSellNumEt.setTag(position);
        holder.mothSellNumEt.setOnFocusChangeListener(this);
        if (ConstValues.FLAG_0.equals(item.getCurrStore())) {
            holder.currStoreEt.setHint(item.getCurrStore());
            holder.currStoreEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getCurrStore())){
            holder.currStoreEt.setText(item.getCurrStore());
        }else{
            holder.currStoreEt.setHint(R.string.hit_input);
            holder.currStoreEt.setText(null);
        }
        holder.currStoreEt.setTag(position);
        holder.currStoreEt.setOnFocusChangeListener(this);
        holder.describeEt.setText(item.getDescribe());
        holder.describeEt.setTag(position);
        holder.describeEt.setOnFocusChangeListener(this);
        return convertView;
    }

    private class ViewHolder {
        private TextView proName;
        private EditText mothSellNumEt;
        private EditText currStoreEt;
        private EditText describeEt;
    }
    
    public void onFocusChange(View v, boolean hasFocus) {
        EditText et = (EditText)v;
        int position = (Integer)v.getTag();
        if (position == delPosition) return;
        if (position > delPosition && delPosition != -1) {
            position = position -1;
        }
        if (position > -1) {
            XtChatVieStc stc = dataLst.get(position);
            String content = et.getText().toString();
            switch (et.getId()) {
            case R.id.viestatus_et_monthsell:
                stc.setMonthSellNum(content);
                break;
                
            case R.id.viestatus_et_currstore:
                stc.setCurrStore(content);
                break;
                
            case R.id.viestatus_et_describle:
                stc.setDescribe(content);
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
