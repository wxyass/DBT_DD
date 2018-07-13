package et.tsingtaopad.dd.ddxt.checking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.initconstvalues.domain.KvStc;


/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：CaculateItemAdapter.java</br>
 * 作者：hongen   </br>
 * 创建时间：2013-12-13</br>      
 * 功能描述: 巡店拜访-查指标的分项采集部分二级Adapter</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtCaculateItemAdapter extends BaseAdapter implements OnClickListener {
    
    private Activity context;
    private List<XtProIndexValue> dataLst;
    private List<KvStc> indexValuelst;
    private List<XtProItem> proItemLst;
    private String IndexId;
    
    // 弹出框
    private AlertDialog calculateDialog;
    //private NoScrollListView calculateDialogLv;
    private List<XtProItem> tempLst;
//    private TextView proTv;
    
    /**
     * 构造函数
     * 
     * @param context
     * @param dataLst       产品及指标值显示数据
     * @param indexValuelst 当前指标对应的指标值集合  指标标准
     * @param //itemLst       当前产品下对应的采集项
     */
    public XtCaculateItemAdapter(Activity context, List<XtProIndexValue> dataLst, List<KvStc> indexValuelst, List<XtProItem> proItemLst,String IndexId) {
        this.context = context;
        this.dataLst = dataLst;// 有几个产品,及其指标值
        this.indexValuelst = indexValuelst;// 指标标准
        this.IndexId = IndexId;// 当前指标
        if (CheckUtil.IsEmpty(proItemLst)) {
            this.proItemLst = new ArrayList<XtProItem>();
        } else {
            this.proItemLst = proItemLst;// 所有采集项数据集合
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
        Log.d("calculateItem ", position+"  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_checkindex_index_t, null);
            holder.proTv = (TextView)convertView.findViewById(R.id.item_xt_checkindex_proname);
            //holder.indexValueRg = (RadioGroup)convertView.findViewById(R.id.caculate_rg_indexvalue);//展示指标值集合
            //holder.indexValueEt = (EditText)convertView.findViewById(R.id.caculate_et_indexvalue);
            //holder.indexValueNumEt = (EditText)convertView.findViewById(R.id.caculate_et_indexvalue_num);
            //holder.indexValueSp = (Button)convertView.findViewById(R.id.caculate_bt_indexvalue);//指标值集合
            holder.indexValueTv = (TextView)convertView.findViewById(R.id.item_xt_checkindex_indexvalue);
            //holder.calculateBt = (Button)convertView.findViewById(R.id.caculate_bt_caculate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        XtProIndexValue item = dataLst.get(position);
        holder.proTv.setHint(item.getProId());
        holder.proTv.setText(item.getProName());
        //holder.calculateBt.setTag(position);
        //holder.calculateBt.setOnClickListener(this);

        // 判定显示方式及初始化显示数据 0:计算单选、 1:单选、 2:文本框、 3:数值、 4:下拉单选
        /*if (ConstValues.FLAG_1.equals(item.getIndexType())) {
            holder.indexValueRg.setVisibility(View.VISIBLE);
            RadioButton rb;
            boolean isExist = false;
            for (KvStc kvItem : indexValuelst) {
                for (int i = 0; i < holder.indexValueRg.getChildCount(); i++) {
                    rb = (RadioButton)holder.indexValueRg.getChildAt(i);
                    if (kvItem.getKey().equals(rb.getHint().toString())) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    rb = new RadioButton(context);
                    rb.setTextColor(context.getResources()
                            .getColor(R.color.listview_item_font_color));
                    rb.setTextSize(17);
                    rb.setText(kvItem.getValue());
                    rb.setHint(kvItem.getKey());
                    holder.indexValueRg.addView(rb);
                }
            }

        } else if (ConstValues.FLAG_2.equals(item.getIndexType())) {
            holder.indexValueEt.setVisibility(View.VISIBLE);

        } else if (ConstValues.FLAG_3.equals(item.getIndexType())) {
            holder.indexValueNumEt.setVisibility(View.VISIBLE);

        } else*/ if (ConstValues.FLAG_4.equals(item.getIndexType()) || ConstValues.FLAG_0.equals(item.getIndexType())) {
        	//修改铺货状态指标变化量小于WEB配置的变化量结果显示为空白, 修改产品生动化变化量小于WEB配置的变化量值为不合格
            if (!CheckUtil.IsEmpty(indexValuelst) && "-1".equals(FunUtil.isBlankOrNullTo(item.getIndexValueId(), "-1"))) {
                /*item.setIndexValueId(indexValuelst.get(0).getKey());
                item.setIndexValueName(indexValuelst.get(0).getValue());*/
            	item.setIndexValueId("313");// 什么都不是
                item.setIndexValueName("");
            }
        	
            holder.indexValueTv.setVisibility(View.VISIBLE);
            holder.indexValueTv.setTag(item.getIndexValueId());
            holder.indexValueTv.setText(item.getIndexValueName());
            // 铺货状态 结果设置为不可手动更改(只能通过业代手填数据,经PAD自动计算得出铺货状态) 20160316
            if(!"ad3030fb-e42e-47f8-a3ec-4229089aab5d".equals(item.getIndexId())){
            	holder.indexValueTv.setOnClickListener(this);
            }

        } else {
            holder.indexValueTv.setVisibility(View.VISIBLE);
            
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView proTv;
        //private RadioGroup indexValueRg;
        //private EditText indexValueEt;
        //private EditText indexValueNumEt;
        //private Button indexValueSp;
        private TextView indexValueTv;
        //private Button calculateBt;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.item_xt_checkindex_indexvalue:
        	//单选弹出框
            //DialogUtil.showSingleDialog((Activity)context, (Button)v, indexValuelst, new String[]{"key","value","isDefault"});
            Toast.makeText(context,"指标点击反馈",Toast.LENGTH_SHORT).show();
            break;

        /*case R.id.caculate_bt_caculate:
            if (calculateDialog != null && calculateDialog.isShowing()) return;

            // 加判断 是不是铺货状态,弹出不同列的弹窗
            if("ad3030fb-e42e-47f8-a3ec-4229089aab5d".equals(IndexId)){// 铺货状态
            	
            	// 加载弹出窗口layout
                View itemForm = context.getLayoutInflater().inflate(R.layout.checkindex_calculate_puhuo,null);
                
                // 获取产品信息
                int position = (Integer)v.getTag();
                final XtProIndexValue indexValue = dataLst.get(position);
                TextView proTv = (TextView)itemForm.findViewById(R.id.calculatedialog_tv_proname);
                proTv.setHint(indexValue.getProId());
                proTv.setText(indexValue.getProName());
                
                // 实现化并弹出窗口
                calculateDialog = new AlertDialog.Builder(context).setCancelable(false).create();
                calculateDialog.setView(itemForm, 0, 0, 0, 0);
                calculateDialog.show();
                
                // 获取该产品采集项
                tempLst = new ArrayList<XtProItem>();
                //TODO
                for (XtProItem item : proItemLst) {
                    if (indexValue.getProId().equals(item.getProId()) && item.getIndexIdLst().contains(indexValue.getIndexId())) {
                        tempLst.add(item);
                    }
                }
                
                calculateDialogLv = (NoScrollListView)itemForm.findViewById(R.id.calculatedialog_lv_pro);
                calculateDialogLv.setAdapter(new CalculateIndexItemPuhuoAdapter(context, tempLst));
                
                // 确定
                Button sureBt = (Button)itemForm.findViewById(R.id.calculatedialog_bt_sure);
                sureBt.setOnClickListener(new OnClickListener() {
                    @Override
    				public void onClick(View arg0) {
                    	if (ViewUtil.isDoubleClick(arg0.getId(), 2500)) return;
                        XtProItem item = null;
                        EditText itemEt = null;
                        EditText itemEt2 = null;
                        TextView itemTv = null;
                        int isAllIn = 0;
                        for (int i = 0; i < tempLst.size(); i++) {
                            item = tempLst.get(i);
                            item.setCheckkey(indexValue.getIndexId());
                            // 获取采集文本框内容
                            itemEt = (EditText)calculateDialogLv.getChildAt(i).findViewById(R.id.calculatedialog_et_changenum);
                            item.setChangeNum((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
                            //item.setChangeNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
                            item.setBianhualiang(itemEt.getText().toString());// 变化量
                            itemEt2 = (EditText)calculateDialogLv.getChildAt(i).findViewById(R.id.calculatedialog_et_finalnum);
                            item.setFinalNum((FunUtil.isBlankOrNullToDouble(itemEt2.getText().toString())));
                            //item.setFinalNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
                            item.setXianyouliang(itemEt.getText().toString());// 现有量
                            
                            itemTv = (TextView)calculateDialogLv.getChildAt(i).findViewById(R.id.calculatedialog_et_xinxiandu);
                            item.setFreshness(FunUtil.isNullToZero(itemTv.getText().toString()));
                            
                         // 
                            if("".equals(FunUtil.isNullSetSpace(itemEt.getText().toString()))||"".equals(FunUtil.isNullSetSpace(itemEt2.getText().toString()))){
                            	isAllIn=1;
                            }
                        }
                        
                        if (isAllIn == 1) {
        					Toast.makeText(context, "所有的现有量,变化量必须填值(没货填0)", Toast.LENGTH_SHORT).show();
        					return;
        				}
                        
                        calculateDialog.cancel();
                        
                        // 自动计算
                        Bundle bundle = new Bundle();
                        if (item != null) {
                            bundle.putString("proId", item.getProId());
                            bundle.putString("indexId", indexValue.getIndexId());
                        }
                        Message msg = new Message();
                        msg.what = ConstValues.WAIT5;
                        msg.setData(bundle);
                        ConstValues.handler.sendMessage(msg);
                    }
                });
                
                // 取消
                Button cancelBt = (Button)itemForm.findViewById(R.id.calculatedialog_bt_cancel);
                cancelBt.setOnClickListener(new OnClickListener() {
                    @Override
    				public void onClick(View v) {
                        calculateDialog.cancel();
                    }
                });
                
            } else {// 非铺货状态(比如:生动化,冰冻化...)
            	
            	// 加载弹出窗口layout
                View itemForm = context.getLayoutInflater().inflate(R.layout.checkindex_calculatedialog,null);
                
                // 获取产品信息
                int position = (Integer)v.getTag();
                final XtProIndexValue indexValue = dataLst.get(position);
                TextView proTv = (TextView)itemForm.findViewById(R.id.calculatedialog_tv_proname);
                proTv.setHint(indexValue.getProId());
                proTv.setText(indexValue.getProName());
                
                // 实现化并弹出窗口
                calculateDialog = new AlertDialog.Builder(context).setCancelable(false).create();
                calculateDialog.setView(itemForm, 0, 0, 0, 0);
                calculateDialog.show();
                
                // 获取该产品采集项
                tempLst = new ArrayList<XtProItem>();
                //TODO
                for (XtProItem item : proItemLst) {
                    if (indexValue.getProId().equals(item.getProId()) && item.getIndexIdLst().contains(indexValue.getIndexId())) {
                        tempLst.add(item);
                    }
                }
                
                calculateDialogLv = (NoScrollListView)itemForm.findViewById(R.id.calculatedialog_lv_pro);
                calculateDialogLv.setAdapter(new CalculateIndexItemAdapter(context, tempLst));
                
                // 确定
                Button sureBt = (Button)itemForm.findViewById(R.id.calculatedialog_bt_sure);
                sureBt.setOnClickListener(new OnClickListener() {
                    @Override
    				public void onClick(View arg0) {
                    	if (ViewUtil.isDoubleClick(arg0.getId(), 2500)) return;
                        XtProItem item = null;
                        EditText itemEt = null;
                        EditText itemEt2 = null;
                        int isAllIn = 0;
                        for (int i = 0; i < tempLst.size(); i++) {
                            item = tempLst.get(i);
                            item.setCheckkey(indexValue.getIndexId());
                            // 获取采集文本框内容
                            itemEt = (EditText)calculateDialogLv.getChildAt(i).findViewById(R.id.calculatedialog_et_changenum);
                            item.setChangeNum((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
                            //item.setChangeNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
                            item.setBianhualiang(itemEt.getText().toString());// 变化量
                            itemEt2 = (EditText)calculateDialogLv.getChildAt(i).findViewById(R.id.calculatedialog_et_finalnum);
                            item.setFinalNum((FunUtil.isBlankOrNullToDouble(itemEt2.getText().toString())));
                            //item.setFinalNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
                            item.setXianyouliang(itemEt.getText().toString());// 现有量
                            
                            // 
                            if("".equals(FunUtil.isNullSetSpace(itemEt.getText().toString()))||"".equals(FunUtil.isNullSetSpace(itemEt2.getText().toString()))){
                            	isAllIn=1;
                            }
                        }
                        
                        if (isAllIn == 1) {
        					Toast.makeText(context, "所有的现有量,变化量必须填值(没货填0)", Toast.LENGTH_SHORT).show();
        					return;
        				}
                        
                        calculateDialog.cancel();
                        
                        // 自动计算
                        Bundle bundle = new Bundle();
                        if (item != null) {
                            bundle.putString("proId", item.getProId());
                            bundle.putString("indexId", indexValue.getIndexId());
                        }
                        Message msg = new Message();
                        msg.what = ConstValues.WAIT5;
                        msg.setData(bundle);
                        ConstValues.handler.sendMessage(msg);
                    }
                });
                
                // 取消
                Button cancelBt = (Button)itemForm.findViewById(R.id.calculatedialog_bt_cancel);
                cancelBt.setOnClickListener(new OnClickListener() {
                    @Override
    				public void onClick(View v) {
                        calculateDialog.cancel();
                    }
                });
            }
            
            break;*/

        default:
            break;
        }
        
    }
}
