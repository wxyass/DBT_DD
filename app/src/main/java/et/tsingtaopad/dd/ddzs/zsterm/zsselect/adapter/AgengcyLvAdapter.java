package et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/7/18.
 */

public class AgengcyLvAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<KvStc> mDatas;
    private Context mContext;
    private ListView mlv;
    private TextView mProlv_tv;
    private LinearLayout mProlv_ll;
    private LinearLayout mll_proprice1;
    private LinearLayout mll_proprice2;
    private TextView mProname_lv;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public AgengcyLvAdapter(Context context, List<KvStc> datas,ListView termcheck_pro_lv,TextView prolv_tv,
                            LinearLayout prolv_ll,TextView proname_lv,LinearLayout ll_proprice1,LinearLayout ll_proprice2) {

        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
        mlv = termcheck_pro_lv;
        mProlv_tv = prolv_tv;
        mProlv_ll = prolv_ll;
        mProname_lv = proname_lv;
        mll_proprice1 = ll_proprice1;
        mll_proprice2 = ll_proprice2;
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
        ProLvAdapter proLvAdapter = new ProLvAdapter(mContext,bean.getChildLst());
        holder.pro_Lv.setAdapter(proLvAdapter);

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
               // Toast.makeText(mContext,"选中"+dateLst.get(position).getValue(),Toast.LENGTH_SHORT).show() ;

                mProname_lv.setText(dateLst.get(position).getValue());
                mProname_lv.setTag(dateLst.get(position).getKey());
                String pro = mProlv_tv.getText().toString();
                if ("收起".equals(pro)) {
                    mProlv_tv.setText("打开");
                    mProlv_ll.setVisibility(View.GONE);
                } else {
                    mProlv_tv.setText("收起");
                    mProlv_ll.setVisibility(View.VISIBLE);
                }

                mll_proprice1.setVisibility(View.VISIBLE);
                mll_proprice2.setVisibility(View.VISIBLE);
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
