package et.tsingtaopad.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.main.operation.workplan.domain.VpLvItemStc;


@SuppressWarnings("rawtypes")
public class ListViewProductAdapter extends BaseAdapter {
    
    private Activity context;
    private List dataLst;
    
    private int selectItemId = 0;
    
    /**
     * @param context       上下文环境
     * @param dataLst       要显示的数据源
     * @param //fieldName     fieldName[0]：显示信息对应的主键的属性名称、 fieldName[1]：显示信息对应的属性名称
     * @param //backGroundId  backGroundId[0]:默认背景、 backGroundId[1]：选择行的背景
     */
    public ListViewProductAdapter(Activity context, List<VpLvItemStc> dataLst) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.keyvalue_item, null);
            holder.itemTv = (TextView)convertView.findViewById(R.id.keyvalue_tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        VpLvItemStc item = (VpLvItemStc)dataLst.get(position);
        holder.itemTv.setText(item.getName());
        if (position == selectItemId) {
        	convertView.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.bg_listitem));
        } else {
        	convertView.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView itemTv;
    }
    
    public int getSelectItemId() {
        return selectItemId;
    }

    public void setSelectItemId(int selectItemId) {
        this.selectItemId = selectItemId;
    }
}
