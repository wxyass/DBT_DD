package dd.tsingtaopad.dd.dddealplan;

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

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.dd.dddealplan.domain.DealStc;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdDealPlanAdapter extends BaseAdapter {

    private Activity context;
    private List<DealStc> dataLst;
    private IClick listener;

    public DdDealPlanAdapter(Activity context, List<DealStc> dataLst, IClick listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dealplan, null);
            holder.termnameRl = (RelativeLayout) convertView.findViewById(R.id.item_dealplan_rl_termname);
            holder.termname = (TextView) convertView.findViewById(R.id.item_dealplan_termname);
            holder.ll_plan = (LinearLayout) convertView.findViewById(R.id.item_dealplan_ll_plan);// 其他行
            holder.grid = (TextView) convertView.findViewById(R.id.item_dealplan_grid);
            holder.route = (TextView) convertView.findViewById(R.id.item_dealplan_route);
            holder.ydname = (TextView) convertView.findViewById(R.id.item_dealplan_ydname);
            holder.question = (TextView) convertView.findViewById(R.id.item_dealplan_question);
            holder.amendplan = (TextView) convertView.findViewById(R.id.item_dealplan_amendplan);
            holder.measure = (TextView) convertView.findViewById(R.id.item_dealplan_measure);
            holder.checktime = (TextView) convertView.findViewById(R.id.item_dealplan_checktime);

            holder.checkstatusRl = (RelativeLayout) convertView.findViewById(R.id.item_dealplan_rl_checkstatus);
            holder.checkstatus = (TextView) convertView.findViewById(R.id.item_dealplan_checkstatus);
            holder.operation = (TextView) convertView.findViewById(R.id.item_dealplan_operation);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DealStc item = dataLst.get(position);

        holder.termname.setText(item.getTerminalname());
        holder.route.setText(item.getRoutename());
        holder.grid.setText(item.getGridname());
        holder.ydname.setText(item.getUsername());
        holder.question.setText(item.getContent());
        holder.amendplan.setText(item.getRepairremark());
        holder.measure.setText(item.getCheckcontent());
        holder.checktime.setText(item.getRepairtime().substring(0,10));

        if ("1".equals(item.getRepairstatus())) {
            holder.checkstatus.setText("未通过");
            holder.operation.setText("修改计划");
        } else if ("2".equals(item.getRepairstatus())) {
            holder.checkstatus.setText("已通过");
            holder.operation.setText("已通过");
        } else {
            holder.checkstatus.setText("未复查");
            holder.operation.setText("复查");
        }

        if("0".equals(item.getIsshow())){// 0不展示   1展示
            holder.ll_plan.setVisibility(View.GONE);
        }else{
            holder.ll_plan.setVisibility(View.VISIBLE);
        }

        holder.termnameRl.setTag(position);
        holder.termnameRl.setOnClickListener(listener);
        holder.checkstatusRl.setTag(position);
        holder.checkstatusRl.setOnClickListener(listener);

        return convertView;
    }

    private class ViewHolder {
        private RelativeLayout termnameRl;
        private TextView termname;
        private LinearLayout ll_plan;
        private TextView grid;
        private TextView route;
        private TextView ydname;
        private TextView question;
        private TextView amendplan;
        private TextView measure;
        private TextView checktime;
        private RelativeLayout checkstatusRl;
        private TextView checkstatus;
        private TextView operation;

    }

}