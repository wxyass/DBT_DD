package dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ReflectUtil;

@SuppressWarnings("rawtypes")
public class XtProKeyValueAdapter extends BaseAdapter {
    
    private Activity context;
    private List dataLst;
    private String[] fieldName;
    private int[] backGroundId;
    private int selectItemId = -1;
    private HashMap<Integer,Integer> selectcolor = new HashMap<Integer, Integer>();
    private ArrayList<String> seleteds = new ArrayList<String>();
    
    /**
     * @param context       上下文环境
     * @param dataLst       要显示的数据源
     * @param fieldName     fieldName[0]：显示信息对应的主键的属性名称、 fieldName[1]：显示信息对应的属性名称
     * @param backGroundId  backGroundId[0]:默认背景、 backGroundId[1]：选择行的背景
     */
    public XtProKeyValueAdapter(Activity context, List dataLst, String[] fieldName, int[] backGroundId) {
        this.context = context;
        this.dataLst = dataLst;
        this.fieldName = fieldName;
        this.backGroundId = backGroundId;
    }

    @Override
    public int getCount() {
        if (CheckUtil.IsEmpty(dataLst)) {
            return 0;
            
        } else {
        	for (int i = 0; i < dataLst.size(); i++) {
        		selectcolor.put(i, 0);
			}
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
        Object item = dataLst.get(position);
        holder.itemTv.setTag(ReflectUtil.getFieldValueByName(fieldName[0], item).toString());
        holder.itemTv.setText(ReflectUtil.getFieldValueByName(fieldName[1], item).toString());
        
        // 更改条目背景图片
        holder.itemTv.setBackgroundResource(backGroundId[0]);
        if (seleteds.contains(Integer.toString(position))) {
        	holder.itemTv.setBackgroundResource(backGroundId[1]);
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
    
	/**
	 * 添加到选中集合
	 * 
	 * @param position
	 */
	public void addSelected(int position) {
		
		String abc = Integer.toString(position);
		if(seleteds.contains(abc)){
			seleteds.remove(abc);
        }else{
        	seleteds.add(abc);
        }
	}

}
