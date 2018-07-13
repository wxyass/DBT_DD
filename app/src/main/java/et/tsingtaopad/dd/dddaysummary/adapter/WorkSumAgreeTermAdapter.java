package et.tsingtaopad.dd.dddaysummary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.dd.dddaysummary.domain.WorkSumStc;

/**
 * 2列ListView的适配器
 *
 * @author tongleer.com
 */
public class WorkSumAgreeTermAdapter extends BaseAdapter {
    protected Context context;
    protected ArrayList<WorkSumStc> list;

    public WorkSumAgreeTermAdapter(Context context, ArrayList<WorkSumStc> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        if (list.size() % 2 > 0) {
            return list.size() / 2 + 1;
        } else {
            return list.size() / 2;// 如果是双数直接减半  (比如有8个对象,显示4行)
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_operation_worksum, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_sell01);
            vh.tv2 = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_num01);
            vh.tv3 = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_sell02);
            vh.tv4 = (TextView) convertView.findViewById(R.id.item_operation_workplan_tv_num02);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        int distance = list.size() - position * 2;// 8
        int cellCount = distance >= 2 ? 2 : distance;// 2
        final List<WorkSumStc> itemList = list.subList(position * 2, position * 2 + cellCount);// 0 2
        if (itemList.size() > 0) {
            vh.tv1.setText(itemList.get(0).getDicname());
            vh.tv2.setText(itemList.get(0).getIfaccntcount());// 追溯协议店数量
            /*vh.tv1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemList.get(0), Toast.LENGTH_SHORT).show();
                }
            });*/
            if (itemList.size() > 1) {
                vh.tv3.setVisibility(View.VISIBLE);
                vh.tv4.setVisibility(View.VISIBLE);
                vh.tv3.setText(itemList.get(1).getDicname());
                vh.tv4.setText(itemList.get(1).getIfaccntcount());// 追溯协议店数量
                /*vh.tv2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(1), Toast.LENGTH_SHORT).show();
                    }
                });*/
            } else {
                vh.tv3.setVisibility(View.INVISIBLE);
                vh.tv4.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    /**
     * 封装ListView中item控件以优化ListView
     *
     * @author tongleer
     */
    public static class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
    }
}
