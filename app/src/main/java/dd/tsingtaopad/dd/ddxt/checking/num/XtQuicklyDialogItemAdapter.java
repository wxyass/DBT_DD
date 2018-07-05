package dd.tsingtaopad.dd.ddxt.checking.num;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProItem;


/**
 * 功能描述: 快速采集二级Adapter</br>
 */
public class XtQuicklyDialogItemAdapter extends BaseAdapter {

    private Context context;
    private List<XtProItem> dataLst;
    private String itemid;// 指标id 库存101 堆头105

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

    public XtQuicklyDialogItemAdapter(Context context, List<XtProItem> dataLst, String itemid) {
        this.context = context;
        this.dataLst = dataLst;
        this.itemid = itemid;
        
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.xtbf_quicklydialog_lvitem_t, null);
            holder.proNameTv = (TextView)convertView.findViewById(R.id.xtbf_quicklydialog_tv_proname);
            holder.changeNumEt = (EditText)convertView.findViewById(R.id.xtbf_quicklydialog_et_changenum);
            holder.finalNumEt = (EditText)convertView.findViewById(R.id.xtbf_quicklydialog_et_finalnum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        XtProItem item = dataLst.get(position);
        holder.proNameTv.setHint(item.getProId());
        holder.proNameTv.setText(item.getProName());
        DecimalFormat df = new DecimalFormat("0");
        String changeNum = "";
        
        // 改变量
        if (item.getChangeNum() != null) {
            changeNum = df.format(item.getChangeNum());
        }
        if ("0".equals(changeNum)) {
            holder.changeNumEt.setHint(null);
            holder.changeNumEt.setText("0");
        } else {
            holder.changeNumEt.setText(changeNum);
        }
        
        // 现有量
        String finalNum = "";
        if (item.getFinalNum() != null) {
            finalNum = df.format(item.getFinalNum());
        }
        if ("0".equals(finalNum)) {
            holder.finalNumEt.setHint(null);
            holder.finalNumEt.setText("0");
        } else {
            holder.finalNumEt.setText(finalNum);
        }
        
        return convertView;
    }

    private class ViewHolder {
        private TextView proNameTv;
        private EditText changeNumEt;
        private EditText finalNumEt;
    }
}
