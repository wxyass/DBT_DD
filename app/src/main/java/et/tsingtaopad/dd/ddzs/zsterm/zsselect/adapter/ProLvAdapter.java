package et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/7/18.
 */

public class ProLvAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<KvStc> mDatas;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public ProLvAdapter(Context context, List<KvStc> datas) {

        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法才是重点，我们要为它编写一个ViewHolder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.procheck_pro_lvitem, parent, false); //加载布局
            holder = new ViewHolder();

            holder.titleTv = (TextView) convertView.findViewById(R.id.checkterm_pro_item);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        KvStc bean = mDatas.get(position);
        holder.titleTv.setText(bean.getValue());

        return convertView;
    }

    private class ViewHolder {
        TextView titleTv;
    }
}
