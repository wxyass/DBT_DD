package et.tsingtaopad.dd.ddzs.zsterm.zsselect.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/7/18.
 */

public class ProCheckLvAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ProSellStc> mDatas;

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public ProCheckLvAdapter(Context context, List<ProSellStc> datas) {

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
            convertView = mInflater.inflate(R.layout.procheck_proselect_lvitem, parent, false); //加载布局
            holder = new ViewHolder();

            holder.titleTv = (TextView) convertView.findViewById(R.id.checkterm_proselect_item);
            holder.qdjlowEt = (EditText) convertView.findViewById(R.id.zdzs_proselect_qdjlow);
            holder.qdjnumEt = (EditText) convertView.findViewById(R.id.zdzs_proselect_qdjnum);
            holder.lsjlowEt = (EditText) convertView.findViewById(R.id.zdzs_proselect_lsjlow);
            holder.lsjnumEt = (EditText) convertView.findViewById(R.id.zdzs_proselect_lsjnum);

            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }

        final ProSellStc bean = mDatas.get(position);
        holder.titleTv.setText(bean.getValue());
        holder.qdjlowEt.setText(bean.getQudaolow());
        holder.qdjnumEt.setText(bean.getQudaonum());
        holder.lsjlowEt.setText(bean.getLingshoulow());
        holder.lsjnumEt.setText(bean.getLingshounum());

        holder.qdjlowEt.addTextChangedListener(new MyTextWatcher(holder) {
            @Override
            public void afterTextChanged(Editable s, ViewHolder holder) {
                bean.setQudaolow(s.toString());
            }
        });
        holder.qdjnumEt.addTextChangedListener(new MyTextWatcher(holder) {
            @Override
            public void afterTextChanged(Editable s, ViewHolder holder) {
                bean.setQudaonum(s.toString());
            }
        });
        holder.lsjlowEt.addTextChangedListener(new MyTextWatcher(holder) {
            @Override
            public void afterTextChanged(Editable s, ViewHolder holder) {
                bean.setLingshoulow(s.toString());
            }
        });
        holder.lsjnumEt.addTextChangedListener(new MyTextWatcher(holder) {
            @Override
            public void afterTextChanged(Editable s, ViewHolder holder) {
                bean.setLingshounum(s.toString());
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView titleTv;
        EditText qdjlowEt;
        EditText qdjnumEt;
        EditText lsjlowEt;
        EditText lsjnumEt;
    }

    // 排序输入监听
    abstract class MyTextWatcher implements TextWatcher {
        public MyTextWatcher(ViewHolder holder) {
            mHolder = holder;
        }

        private ViewHolder mHolder;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            afterTextChanged(s, mHolder);
        }

        public abstract void afterTextChanged(Editable s, ViewHolder holder);
    }
}
