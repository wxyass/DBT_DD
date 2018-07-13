package et.tsingtaopad.dd.dddaysummary.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.dddaysummary.domain.DdProCheckShowStc;
import et.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ProCheckAdapter extends BaseAdapter implements View.OnClickListener{

    private final String TAG = "DayDetailAdapter";

    private Activity context;
    private List<DdProCheckShowStc> dataLst;
    private IClick listener;

    public ProCheckAdapter(Activity context, List<DdProCheckShowStc> dataLst, IClick listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_operation_procheck, null);
            holder.tv_proname = (TextView) convertView.findViewById(R.id.item_operation_procheck_proname);
            holder.procheck_lv = (et.tsingtaopad.view.NoScrollListView) convertView.findViewById(R.id.item_operation_procheck_lv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DdProCheckShowStc item = dataLst.get(position);

        // 产品名称
        holder.tv_proname.setText(item.getProname());
        holder.procheck_lv.setAdapter(new ProCheckItemAdapter(context,item.getDdProCheckItemStcs(),null));


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
        private TextView tv_proname;
        private et.tsingtaopad.view.NoScrollListView procheck_lv;
    }
}