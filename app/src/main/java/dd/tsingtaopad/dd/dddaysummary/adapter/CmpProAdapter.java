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
import dd.tsingtaopad.dd.dddaysummary.domain.CmpProShowStc;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class CmpProAdapter extends BaseAdapter implements View.OnClickListener{

    private final String TAG = "DayDetailAdapter";

    private Activity context;
    private List<CmpProShowStc> dataLst;
    private IClick listener;

    public CmpProAdapter(Activity context, List<CmpProShowStc> dataLst, IClick listener) {
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
            holder.procheck_01 = (TextView) convertView.findViewById(R.id.item_operation_procheck_tv01);
            holder.procheck_02 = (TextView) convertView.findViewById(R.id.item_operation_procheck_tv02);
            holder.procheck_03 = (TextView) convertView.findViewById(R.id.item_operation_procheck_tv03);
            holder.procheck_lv = (dd.tsingtaopad.view.NoScrollListView) convertView.findViewById(R.id.item_operation_procheck_lv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CmpProShowStc item = dataLst.get(position);

        holder.procheck_01.setText("品种");
        holder.procheck_02.setText("真实/追溯");
        holder.procheck_03.setText("真实率");

        // 产品名称
        holder.tv_proname.setText(item.getCmpproname());
        holder.procheck_lv.setAdapter(new CmpProItemAdapter(context,item.getCmpProItemStcs(),null));


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
        private TextView procheck_01;
        private TextView procheck_02;
        private TextView procheck_03;
        private dd.tsingtaopad.view.NoScrollListView procheck_lv;
    }
}