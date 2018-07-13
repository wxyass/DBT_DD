package et.tsingtaopad.dd.dddaysummary.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.dddaysummary.domain.DdDayPlanStc;
import et.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class WorkPlanAdapter extends BaseAdapter implements View.OnClickListener {

    private final String TAG = "DayDetailAdapter";

    private Activity context;
    private List<DdDayPlanStc> dataLst;
    private IClick listener;

    public WorkPlanAdapter(Activity context, List<DdDayPlanStc> dataLst, IClick listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_operation_workplan, null);
            holder.ll_all = (LinearLayout) convertView.findViewById(R.id.item_operation_workplan_ll_all);
            holder.rl_check = (RelativeLayout) convertView.findViewById(R.id.item_operation_workplan_rl_check);
            holder.tv_checkname = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_check);
            holder.rl_areaid = (RelativeLayout) convertView.findViewById(R.id.item_operation_workplan_rl_areaid);
            holder.tv_areaname = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_areaid);
            holder.tv_gridkey = (RelativeLayout) convertView.findViewById(R.id.item_operation_workplan_tv_gridkey);
            holder.tv_valgridname = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_valgridkey);
            holder.rl_routekey = (RelativeLayout) convertView.findViewById(R.id.item_operation_workplan_rl_routekey);
            holder.tv_routename = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_routekey);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DdDayPlanStc item = dataLst.get(position);

        // 追溯项
        holder.tv_checkname.setText(item.getDicname());
        // 追溯区域
        holder.tv_areaname.setText(item.getAreaname());
        // 追溯定格
        holder.tv_valgridname.setText(item.getGridname());
        // 追溯路线
        holder.tv_routename.setText(item.getRoutename());

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
        private LinearLayout ll_all;
        private RelativeLayout rl_check;
        private TextView tv_checkname;
        private RelativeLayout rl_areaid;
        private TextView tv_areaname;
        private RelativeLayout tv_gridkey;
        private TextView tv_valgridname;
        private RelativeLayout rl_routekey;
        private TextView tv_routename;
    }
}