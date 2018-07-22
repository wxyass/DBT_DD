package et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.DayDetailSelectKeyValueAdapter;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/7/18.
 */

public class AgencyCmpLvAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<KvStc> mDatas;
    private Activity mContext;
    private ListView mlv;
    private TextView mProlv_tv;
    private LinearLayout mProlv_ll;
    //private LinearLayout mll_proprice1;
    //private LinearLayout mll_proprice2;
    private TextView mProname_lv;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public AgencyCmpLvAdapter(Activity context, List<KvStc> datas,
                              ListView termcheck_pro_lv,// 展示产品的listView
                              TextView prolv_tv,// 收起俩字
                              LinearLayout prolv_ll,// 用于包含LV的LL
                              TextView proname_lv// 铺货状态 的产品key 用于字符串拼接
                              ) {

        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
        mlv = termcheck_pro_lv;
        mProlv_tv = prolv_tv;
        mProlv_ll = prolv_ll;
        mProname_lv = proname_lv;
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
            convertView = mInflater.inflate(R.layout.procheck_agency_lvitem, parent, false); //加载布局
            holder = new ViewHolder();

            holder.checkterm_ll_item = (RelativeLayout) convertView.findViewById(R.id.checkterm_ll_item);
            holder.titleTv = (TextView) convertView.findViewById(R.id.checkterm_agency_item);
            holder.ll_prolv = (LinearLayout) convertView.findViewById(R.id.zdzs_termcheck_ll_agency_prolv_item);
            holder.pro_Lv = (ListView) convertView.findViewById(R.id.zdzs_termcheck_agency_item);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        final KvStc bean = mDatas.get(position);
        holder.titleTv.setText(bean.getValue());
        final List<KvStc> dateLst =  bean.getChildLst();


        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        String selectpro = (String)mProname_lv.getTag();// 已经选中的产品key
        if(!TextUtils.isEmpty(selectpro)){
            selectedId = Arrays.asList(selectpro.split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : dateLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }



        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(mContext,dateLst,
                new String[]{"key","value"}, null);
        holder.pro_Lv.setAdapter(sadapter);


        holder.checkterm_ll_item.setOnClickListener(new MyOnClickListener(holder) {
            @Override
            public void afterTextChanged(View v, ViewHolder holder) {
                if("1".equals(bean.getIsDefault())){
                    bean.setIsDefault("0");
                }else{
                    bean.setIsDefault("1");
                }
                notifyDataSetChanged();
                ViewUtil.setListViewHeight(mlv);
            }
        });

        if("1".equals(bean.getIsDefault())){
            holder.ll_prolv.setVisibility(View.VISIBLE);
        }else{
            holder.ll_prolv.setVisibility(View.GONE);
        }

        ViewUtil.setListViewHeight(holder.pro_Lv);

        holder.pro_Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*mProname_lv.setText(dateLst.get(position).getValue());
                mProname_lv.setTag(dateLst.get(position).getKey());
                String pro = mProlv_tv.getText().toString();
                if ("收起".equals(pro)) {
                    mProlv_tv.setText("打开");
                    mProlv_ll.setVisibility(View.GONE);
                } else {
                    mProlv_tv.setText("收起");
                    mProlv_ll.setVisibility(View.VISIBLE);
                }*/

                CheckBox itemCB = (CheckBox) view.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) view.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String checkkey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String checkname = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                if (itemCB.isChecked()) {
                    mProname_lv.setTag(((String)mProname_lv.getTag())  + checkkey);
                    ((KvStc)dateLst.get(position)).setIsDefault("1");
                    KvStc stc = (KvStc)dateLst.get(position);
                    ProSellStc proSellStc = new ProSellStc();
                    proSellStc.setKey(stc.getKey());
                    proSellStc.setValue(stc.getValue());
                    //checkSelectLst.add(proSellStc);
                } else {
                    // checkSelectLst.remove((KvStc)proLst.get(posi));;
                    mProname_lv.setTag(((String)mProname_lv.getTag()) .replace(checkkey, ""));
                    ((KvStc)dateLst.get(position)).setIsDefault("0");
                    /*for (ProSellStc kvStc : checkSelectLst){
                        if(itemTv.getHint().equals(kvStc.getKey())){
                            checkSelectLst.remove(kvStc);
                            break;
                        }
                    }*/
                }
                sadapter.notifyDataSetChanged();

            }
        });


        return convertView;
    }


    private class ViewHolder {
        RelativeLayout checkterm_ll_item;
        TextView titleTv;
        LinearLayout ll_prolv;
        ListView pro_Lv;
    }

    abstract class MyOnClickListener implements View.OnClickListener{

        private ViewHolder mHolder;

        public MyOnClickListener(ViewHolder holder) {
            mHolder = holder;
        }

        public abstract void afterTextChanged(View v,ViewHolder holder);

        @Override
        public void onClick(View v) {
            afterTextChanged(v,mHolder);
        }
    }
}
