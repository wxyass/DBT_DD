package dd.tsingtaopad.business.first;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.business.first.bean.LvTop;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.listviewintf.IClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class FirstLvTopAdapter extends BaseAdapter {

    private Activity context;
    private List<LvTop> dataLst;
    private IClick listener;

    public FirstLvTopAdapter(Activity context, List<LvTop> dataLst, IClick listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_first_lv, null);
            holder.tv_ranking = (TextView) convertView.findViewById(R.id.item_first_tv_ranking);
            holder.tv_rankingname = (TextView) convertView.findViewById(R.id.item_first_tv_rankingname);
            holder.tv_rankingnum = (TextView) convertView.findViewById(R.id.item_first_tv_rankingnum);
            holder.tv_rankingall = (TextView) convertView.findViewById(R.id.item_first_tv_rankingall);
            holder.tv_unit = (TextView) convertView.findViewById(R.id.item_first_tv_unit);
            holder.rankingbar = (ProgressBar) convertView.findViewById(R.id.item_first_probar_rankingbar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final LvTop item = dataLst.get(position);

        int ranknum = 0;
        int totalnum = 0;
        try {
            ranknum = Integer.parseInt(FunUtil.isBlankOrNullTo(item.getRanknum(), "0"));
            totalnum = Integer.parseInt(FunUtil.isBlankOrNullTo(item.getTotalnum(), "0"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        holder.tv_ranking.setText(FunUtil.isBlankOrNullTo(item.getRank(), "0"));
        holder.tv_rankingname.setText(item.getRankname());
        holder.tv_rankingnum.setText(FunUtil.isBlankOrNullTo(item.getRanknum(), "0"));
        holder.tv_rankingall.setText(FunUtil.isBlankOrNullTo(item.getTotalnum(), "0"));
        holder.tv_unit.setText(" " + item.getContent());
        holder.rankingbar.setMax(totalnum);
        holder.rankingbar.setProgress(ranknum);

        return convertView;
    }

    private class ViewHolder {

        private TextView tv_ranking;
        private TextView tv_rankingname;
        private TextView tv_rankingnum;
        private TextView tv_rankingall;
        private TextView tv_unit;
        private ProgressBar rankingbar;

    }

}