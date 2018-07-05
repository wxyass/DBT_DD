package dd.tsingtaopad.dd.dddaysummary.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.dd.dddaysummary.domain.DdProCheckItemStc;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ProCheckItemAdapter extends BaseAdapter implements View.OnClickListener{

    private final String TAG = "DayDetailAdapter";

    private Activity context;
    private List<DdProCheckItemStc> dataLst;
    private IClick listener;

    public ProCheckItemAdapter(Activity context, List<DdProCheckItemStc> dataLst, IClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener = listener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_operation_procheckitem, null);
            holder.tv_type = (TextView) convertView.findViewById(R.id.item_operation_procheckitem_tv_type);
            holder.tv_num = (TextView) convertView.findViewById(R.id.item_operation_procheckitem_tv_num);
            holder.tv_numlv = (TextView) convertView.findViewById(R.id.item_operation_procheckitem_tv_numlv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DdProCheckItemStc item = dataLst.get(position);

        // 产品名称
        holder.tv_type.setText(item.getDicname());
        holder.tv_num.setText(item.getTrueterm()+"/"+item.getTotalterm());
        holder.tv_numlv.setText(item.getTermratio());


        return convertView;
    }

    @Override
    public void onClick(View v) {
        int posi = (int) v.getTag();
        switch (v.getId()) {

            default:
                break;
        }
    }

    private class ViewHolder {
        private TextView tv_type;
        private TextView tv_num;
        private TextView tv_numlv;
    }
}