package dd.tsingtaopad.dd.dddealplan.remake;

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
import dd.tsingtaopad.dd.dddealplan.remake.domain.ReCheckTimeStc;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdCheckTimeAdapter extends BaseAdapter {

    private Activity context;
    private List<ReCheckTimeStc> dataLst;
    private IClick listener;

    public DdCheckTimeAdapter(Activity context, List<ReCheckTimeStc> dataLst, IClick listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_remark_checktime, null);
            holder.measure = (TextView) convertView.findViewById(R.id.item_re_status_show);
            holder.checktime = (TextView) convertView.findViewById(R.id.item_re_checktime_show);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ReCheckTimeStc item = dataLst.get(position);

        // 核查时间
        holder.checktime.setText(item.getRepairtime().substring(0,10));

        if ("1".equals(item.getStatus())) {
            holder.measure.setText("未通过");
        } else if ("2".equals(item.getStatus())) {
            holder.measure.setText("已通过");
        } else {
            holder.measure.setText("未复查");
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView measure;
        private TextView checktime;

    }

}