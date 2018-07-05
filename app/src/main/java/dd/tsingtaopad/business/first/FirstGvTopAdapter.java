package dd.tsingtaopad.business.first;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.business.first.bean.GvTop;


/**
 * 功能描述:
 */
public class FirstGvTopAdapter extends BaseAdapter {

	private Context context;
    private List<GvTop> dataLst;

	public FirstGvTopAdapter(Context context, List<GvTop> valueLst ) {
        this.context = context;
        this.dataLst = valueLst;
    }

	// item的个数
	@Override
	public int getCount() {
		return dataLst.size();
	}

	// 根据位置获取对象
	@Override
	public GvTop getItem(int position) {
		return dataLst.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// 初始化每一个item的布局(待优化)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.item_first_gv, null);
		TextView tv_ydname = (TextView) view.findViewById(R.id.item_first_tv_ydname);
		TextView tv_num = (TextView) view.findViewById(R.id.item_first_tv_num);
		TextView tv_topname = (TextView) view.findViewById(R.id.item_first_tv_topname);

		GvTop camerainfostc = (GvTop)dataLst.get(position);

		tv_ydname.setText(camerainfostc.getUsername());
		tv_num.setText(camerainfostc.getTermcount());
		tv_topname.setText(camerainfostc.getRankname());

		return view;
	}

}
