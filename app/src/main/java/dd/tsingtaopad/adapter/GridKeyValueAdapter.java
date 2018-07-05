package dd.tsingtaopad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.ReflectUtil;


/**
 * 功能描述: Spinner的Adapter的扩展应用</br>
 */
@SuppressWarnings("rawtypes")
public class GridKeyValueAdapter extends BaseAdapter {

    private Context context;
    private List dataLst;
    private String[] fieldName;
    private String selectedId = null;

    /**
     * 构造函数
     *
     * @param context
     * @param dataLst       要显示的数据源
     * @param fieldName     fileName[0]:主键属性名称、fileName[1]:显示内容属性名称、fileName[2]:默认标识属性名称
     * @param selectedId    默认选项对应的主键值
     */
    public GridKeyValueAdapter(Context context, List dataLst, String fieldName[], String selectedId) {
        this.context = context;
        this.dataLst = dataLst;
        this.fieldName = fieldName;
        this.selectedId = selectedId;
        
        // 处理默认选项的问题
        if (!CheckUtil.IsEmpty(dataLst) && CheckUtil.isBlankOrNull(selectedId)) {
            if (fieldName.length >= 3) {
                String isDefault = fieldName[2];
                String defVal = "";
                for (Object obj : dataLst) {
                    defVal = FunUtil.isNullSetSpace(ReflectUtil.getFieldValueByName(isDefault, obj)).toString();
                    if (ConstValues.FLAG_1.equals(defVal)) {
                        this.selectedId = ReflectUtil.getFieldValueByName(fieldName[0], obj).toString();
                        break;
                    }
                }
            }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_alert_gird, null);
            holder.itemTv = (TextView)convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        Object item = dataLst.get(position);

        holder.itemTv.setHint(FunUtil.isNullSetSpace(ReflectUtil.getFieldValueByName(fieldName[0], item)).toString());
        holder.itemTv.setTag(FunUtil.isNullSetSpace(ReflectUtil.getFieldValueByName(fieldName[0], item)).toString());
        holder.itemTv.setText(FunUtil.isNullSetSpace(ReflectUtil.getFieldValueByName(fieldName[1], item)).toString());

        /*// 修改默认选中条目的颜色
        if (!CheckUtil.isBlankOrNull(selectedId)) {
            if(selectedId.equals(FunUtil.isNullSetSpace(ReflectUtil.getFieldValueByName(fieldName[0], item)).toString())){
                holder.itemTv.setTextColor(context.getResources().getColor(R.color.green));
            }else {
                holder.itemTv.setTextColor(context.getResources().getColor(R.color.lightblack));
            }
        }*/
        return convertView;
    }

    private class ViewHolder {
        private TextView itemTv;
    }
}
