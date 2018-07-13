package et.tsingtaopad.dd.ddzs.zscheckindex.zsnum;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;

/**
 * 项目名称：营销移动智能工作平台 </br> 文件名：QuicklyDialogAdapter.java</br> 作者：hongen </br>
 * 创建时间：2013-12-17</br> 功能描述: 分项采集列表中计算弹出框中列表的Adapter</br> 版本 V 1.0</br>
 * 修改履历</br> 日期 原因 BUG号 修改人 修改版本</br>
 */
public class ZsCalculateAmendAdapter extends BaseAdapter {

	private Activity context;
	private List<XtProItem> dataLst;


	public ZsCalculateAmendAdapter(Activity context, List<XtProItem> dataLst) {
		this.context = context;
		this.dataLst = dataLst;
		
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
			return null;
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_zdzs_checkindex_index_third, null);
			holder.itemNameTv = (TextView) convertView.findViewById(R.id.item_zs_calculatedialog_tv_itemname);// 库存
			holder.ydNumTv = (TextView) convertView.findViewById(R.id.item_zs_calculatedialog_tv_xy);// 业代结果量
			holder.ddNumEt = (EditText) convertView.findViewById(R.id.item_zs_calculatedialog_et_finalnum);// 督导结果量
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		XtProItem item = dataLst.get(position);
		// 采集项名称
		holder.itemNameTv.setTag(item.getItemId());
		holder.itemNameTv.setText(item.getItemName());

		// 业代结果量
		holder.ydNumTv.setText(item.getValitem());
		// 督导结果量
		holder.ddNumEt.setText(item.getValitemval());

		return convertView;
	}

	private class ViewHolder {
		private TextView itemNameTv;
		private EditText ddNumEt;
		private TextView ydNumTv;
	}

}
