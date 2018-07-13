package et.tsingtaopad.dd.ddagencyres;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.db.table.MstAgencyKFM;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：TermListAdapter.java</br>
 * 作者：hongen   </br>
 * 创建时间：2014-1-19</br>
 * 功能描述:终端列表adapter </br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdAgencySelectAdapter extends BaseAdapter implements OnClickListener {

    private Activity context;
    private List<MstAgencyKFM> dataLst;
    private RelativeLayout confirmBt;
    private String termId;
    private int selectItem = -1;

    public DdAgencySelectAdapter(Activity context,
                                 List<MstAgencyKFM> termialLst,
                                 RelativeLayout confirmBt,
                                 String termId) {
        this.context = context;
        this.dataLst = termialLst;
        this.confirmBt = confirmBt;
        this.termId = termId;
    }

    @Override
    public int getCount() {

        if (CheckUtil.IsEmpty(this.dataLst)) {
            return 0;
        } else {
            return this.dataLst.size();
        }
    }

    @Override
    public Object getItem(int arg0) {

        if (CheckUtil.IsEmpty(this.dataLst)) {
            return null;

        } else {
            return this.dataLst.get(arg0);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_agencyselect, null);
            holder.agencyNameTv = (TextView) convertView.findViewById(R.id.item_agencyselect_tv_name);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.item_agencyselect_tv_type);// 渠道类型

            holder.updateIv = (ImageView) convertView.findViewById(R.id.item_agencyselect_iv_update);// 上传成功标识
            holder.updateFailIv = (ImageView) convertView.findViewById(R.id.item_agencyselect_iv_update_fail);// 上传失败标识

            holder.itermLayout = (LinearLayout) convertView.findViewById(R.id.item_agencyselect_ll);// 整体条目

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itermLayout.setOnClickListener(this);
        holder.itermLayout.setTag(position);

        MstAgencyKFM item = dataLst.get(position);
        holder.agencyNameTv.setHint(item.getAgencyname());

        holder.agencyNameTv.setText(item.getAgencyname());
        holder.phoneTv.setText(item.getMobile());

        /*// 上传标记  SyncFlag对应拜访表Padisconsistent
        if(ConstValues.FLAG_1.equals(item.getUploadFlag())&&ConstValues.FLAG_1.equals(item.getSyncFlag())){
            // 结束上传  上传成功
            holder.agencyNameTv.setTextColor(Color.RED);
            holder.updateIv.setVisibility(View.VISIBLE);
            holder.updateFailIv.setVisibility(View.GONE);
        }else if(ConstValues.FLAG_1.equals(item.getUploadFlag())&&ConstValues.FLAG_0.equals(item.getSyncFlag())){
            // 结束上传  上传失败
            holder.agencyNameTv.setTextColor(Color.YELLOW);
            holder.updateIv.setVisibility(View.GONE);// 后面修改
            holder.updateFailIv.setVisibility(View.VISIBLE);
        }else {
            holder.agencyNameTv.setTextColor(Color.BLACK);
            holder.updateIv.setVisibility(View.GONE);
            holder.updateFailIv.setVisibility(View.GONE);
        }

        // 当结束拜访,拜访界面消失,显示购物车的时候,用到这个判断
        if (selectItem == -1 && item.getTerminalkey().equals(termId)) {
            selectItem = position;
        }*/

        // 整体的字变色
        if (position == selectItem) {
            // 将选中的条目,变成灰色
            holder.itermLayout.setBackgroundColor(context.getResources().getColor(R.color.bg_content_color_gray));
            holder.agencyNameTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            holder.phoneTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
        } else {
            // 未选中的条目,变成白色
            holder.itermLayout.setBackgroundColor(Color.WHITE);
            holder.agencyNameTv.setTextColor(context.getResources().getColor(R.color.gray_color_333333));
            holder.phoneTv.setTextColor(context.getResources().getColor(R.color.gray));
            /*if (ConstValues.FLAG_1.equals(item.getUploadFlag())) {
                // 已提交过的
                holder.agencyNameTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
                holder.phoneTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
            } else if (ConstValues.FLAG_0.equals(item.getUploadFlag())) {
                // 已拜访过未上传的
                holder.agencyNameTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
                holder.phoneTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
            } else {
                // 未拜访过的
                holder.agencyNameTv.setTextColor(Color.BLACK);
                holder.agencyNameTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
                holder.phoneTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
            }*/
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView agencyNameTv;
        private TextView phoneTv;
        private ImageView updateIv;
        private ImageView updateFailIv;
        private LinearLayout itermLayout;
    }


    @Override
    public void onClick(View v) {
        // 设置条目的点击事件
        int position = Integer.parseInt(v.getTag().toString());
        setSelectItem(position);
        notifyDataSetChanged();
        confirmBt.setVisibility(View.VISIBLE);
        confirmBt.setTag(dataLst.get(position));
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

}
