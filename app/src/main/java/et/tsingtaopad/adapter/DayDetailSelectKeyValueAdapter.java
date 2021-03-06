package et.tsingtaopad.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
@SuppressLint("UseSparseArrays")
@SuppressWarnings("rawtypes")
public class DayDetailSelectKeyValueAdapter extends BaseAdapter {
    
    private Activity context;
    private List dataLst;
    private String[] fieldName;
    private List<String> selectedId;//存状态.
 

    /**
     * 构造函数
     * 
     * @param context
     * @param dataLst       要显示的数据源
     * @param fieldName     fileName[0]:主键属性名称、fileName[1]:显示内容属性名称、
     * @param selects    默认选项对应的主键值
     */
    public DayDetailSelectKeyValueAdapter(Activity context,
                                          List dataLst, String fieldName[], String selects) {
        this.context = context;
        this.dataLst = dataLst;
        this.fieldName = fieldName;
        
     // 绑定列表数据
        if (!TextUtils.isEmpty(selects)) {
            selectedId =Arrays.asList(selects.toString().toString().replace("||", ",").replace("|", "").split(","));
        } 
        
        if(CheckUtil.IsEmpty(selectedId)){
           this.selectedId=new ArrayList<String>();
        }else{
           this.selectedId=new ArrayList<String>(selectedId);
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.common_multiple_lvitem, null);
            holder.itemTv = (TextView)convertView.findViewById(R.id.common_multiple_tv_lvitem);
            holder.itemCb = (CheckBox)convertView.findViewById(R.id.common_multiple_cb_lvitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.itemCb.setTag(position);
        KvStc item = (KvStc)dataLst.get(position);
        
        holder.itemTv.setHint(item.getKey());
        holder.itemTv.setText(item.getValue());
    
        if ("1".equals(item.getIsDefault())) {
            holder.itemCb.setChecked(true);
        } else {
            holder.itemCb.setChecked(false);
        }
        //addListener(holder, position,item);
        return convertView;
    }
    
    private void addListener(ViewHolder holder,final int position,final KvStc item){
        holder.itemCb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

              @Override
              public void onCheckedChanged(CompoundButton buttonView,
                          boolean isChecked) {
                    if(isChecked){
                    	item.setIsDefault("1");
                    }
                    else{
                    	//未选中时移除位置
                    	item.setIsDefault("0");
                    }
              } 
             
        });
    }
 
    private class ViewHolder {
        private TextView itemTv;
        private CheckBox itemCb;
    }
}
