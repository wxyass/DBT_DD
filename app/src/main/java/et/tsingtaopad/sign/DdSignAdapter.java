package et.tsingtaopad.sign;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FileUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.listviewintf.IClick;
import et.tsingtaopad.sign.bean.SignStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdSignAdapter extends BaseAdapter {

    private Activity context;
    private List<SignStc> dataLst;
    private IClick listener;

    public DdSignAdapter(Activity context, List<SignStc> dataLst, IClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener = listener;
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
            return 0;
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
        Log.d("check ", position + "  " + DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dd_sign, null);
            holder.attencetime = (TextView) convertView.findViewById(R.id.item_sign_time);// 时间
            holder.attencetype = (TextView) convertView.findViewById(R.id.item_sign_type);// 打卡类型
            holder.address = (TextView) convertView.findViewById(R.id.item_sign_address);// 地址
            holder.remark = (TextView) convertView.findViewById(R.id.item_sign_reason);// 元音
            holder.tv03_pic = (ImageView) convertView.findViewById(R.id.tv03_pic);// 拍照图片
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SignStc item = dataLst.get(position);

        holder.attencetime.setText(item.getAttencetime());
        holder.address.setText(item.getAddress());
        holder.remark.setText(item.getRemark());
        if ("0".equals(item.getAttencetype())) {
            holder.attencetype.setText("上班打卡");
        } else {
            holder.attencetype.setText("下班打卡");
        }


        final File tempFile = new File(FileUtil.getSignPath(), FunUtil.isBlankOrNullTo(item.getPicname(),""));// IMG_20180731_132657
        //final File tempFile = new File(FileUtil.getPhotoPath()+camerainfostc.getLocalpath());
        Uri fileUri = null;


        fileUri = Uri.fromFile(tempFile);// 将File转为Uri
        Glide.with(context)
                .load(fileUri)
                .into(holder.tv03_pic);

        return convertView;
    }

    private class ViewHolder {
        private TextView attencetime;
        private TextView attencetype;
        private TextView address;
        private TextView remark;
        private ImageView tv03_pic;
    }

}