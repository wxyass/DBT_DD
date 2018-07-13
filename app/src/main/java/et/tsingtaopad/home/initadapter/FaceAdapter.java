package et.tsingtaopad.home.initadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import et.tsingtaopad.R;


/**
 * 功能描述: 巡店拜访-查指标的分项采集部分一级Adapter</br>
 */
public class FaceAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> images;

    /**
     *  构造方法
     *  
     * @param context
     * @param images
     */
    public FaceAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images = images;
    }

    // item的个数
    @Override
    public int getCount() {
        return images.size();
    }

    // 根据位置获取对象
    @Override
    public Integer getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 初始化每一个item的布局
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.visit_gv_item, null);
        ImageView iv_visit = (ImageView) view.findViewById(R.id.iv_visit);
        View iv_visit_right = (View) view.findViewById(R.id.iv_visit_right);
        View iv_visit_below = (View) view.findViewById(R.id.iv_visit_below);

        // 根据不同位置设置分割线是否出现
        if ((position + 1) % 3 == 0) {//3
            iv_visit_right.setVisibility(view.GONE);
            iv_visit_below.setVisibility(view.VISIBLE);
        } else {// 1 2
            iv_visit_right.setVisibility(view.VISIBLE);
            iv_visit_below.setVisibility(view.VISIBLE);
        }
        iv_visit.setImageResource(images.get(position));
        return view;
    }
}
