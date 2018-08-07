package et.tsingtaopad.dd.ddzs.zsterm.zscart;

import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.dd.ddxt.term.cart.XtTermCartService;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.listviewintf.IClick;

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
public class DdTermCartSdlvAdapter extends BaseAdapter {

    private final String TAG = "XtTermCartAdapter";

    private Activity context;
    private List<XtTermSelectMStc> dataLst;
    private String termId;
    private int selectItem = -1;

    public DdTermCartSdlvAdapter(Activity context,
                                 List<XtTermSelectMStc> termialLst,
                                 String termId
    ) {
        this.context = context;
        this.dataLst = termialLst;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_termcart_sdlv, null);
            holder.termcart_ll = (LinearLayout) convertView.findViewById(R.id.item_sdlv_termcart_ll);
            holder.terminalSequenceEt = (TextView) convertView.findViewById(R.id.item_sdlv_termcart_et_sequence);
            holder.terminalNameTv = (TextView) convertView.findViewById(R.id.item_sdlv_termcart_tv_name);
            holder.visitTimeTv = (TextView) convertView.findViewById(R.id.item_sdlv_termcart_tv_visittime);
            holder.terminalTypeTv = (TextView) convertView.findViewById(R.id.item_sdlv_termcart_tv_type);// 渠道类型

            holder.updateIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_update);// 上传成功标识
            holder.updateFailIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_update_fail);// 上传失败标识
            holder.iserrorIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_iserror);// 错误

            holder.mineIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_mime);
            holder.mineProtocolIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_mineprotocol);
            holder.vieIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_vie);
            holder.vieProtocolIv = (ImageView) convertView.findViewById(R.id.item_sdlv_termcart_iv_vieprotocol);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XtTermSelectMStc item = dataLst.get(position);

        // 排序
        // holder.terminalSequenceEt.setBackgroundColor(context.getResources().getColor(R.color.bg_app));
        // holder.terminalSequenceEt.setText(item.getSequence());
        holder.terminalSequenceEt.setText((position + 1) + "");
        // holder.terminalSequenceEt.setTag(position);

        // 终端名称
        holder.terminalNameTv.setText(item.getTerminalname());
        holder.terminalNameTv.setHint(item.getTerminalkey());
        // holder.itermLayout.setTag(position);

        // 渠道类型
        holder.terminalTypeTv.setText(item.getTerminalType());

        // 修改成 现在的整改图章(从整改计划终端表 关联整改计划主表 获取status)
        if ("1".equals(item.getIserror()) || "0".equals(item.getIserror())) {
            holder.iserrorIv.setVisibility(View.VISIBLE);
        } else {
            holder.iserrorIv.setVisibility(View.GONE);
        }

        // 上传标记  SyncFlag对应拜访表Padisconsistent
        if (ConstValues.FLAG_1.equals(item.getUploadFlag()) && ConstValues.FLAG_1.equals(item.getSyncFlag())) {
            // 结束上传  上传成功
            holder.terminalNameTv.setTextColor(Color.RED);
            holder.updateIv.setVisibility(View.VISIBLE);
            holder.updateFailIv.setVisibility(View.GONE);
        } else if (ConstValues.FLAG_1.equals(item.getUploadFlag()) && ConstValues.FLAG_0.equals(item.getSyncFlag())) {
            // 结束上传  上传失败
            holder.terminalNameTv.setTextColor(Color.YELLOW);
            holder.updateIv.setVisibility(View.GONE);// 后面修改
            holder.updateFailIv.setVisibility(View.VISIBLE);
        } else {
            holder.terminalNameTv.setTextColor(Color.BLACK);
            holder.updateIv.setVisibility(View.GONE);
            holder.updateFailIv.setVisibility(View.GONE);
        }

        // 拜访时长
        if (!CheckUtil.isBlankOrNull(item.getVisitTime())) {
            holder.visitTimeTv.setVisibility(View.VISIBLE);
            holder.visitTimeTv.setText(DateUtil.dividetime(item.getVisitTime()) + "-" + DateUtil.dividetime(item.getEndDate()));
        } else {
            holder.visitTimeTv.setVisibility(View.VISIBLE);
            holder.visitTimeTv.setText("今日未拜访");
        }

        // 我品
        if (ConstValues.FLAG_1.equals(item.getMineFlag())) {
            holder.mineIv.setVisibility(View.VISIBLE);
        } else {
            holder.mineIv.setVisibility(View.INVISIBLE);
        }

        // 我品协议店
        if (ConstValues.FLAG_1.equals(item.getMineProtocolFlag())) {
            holder.mineProtocolIv.setVisibility(View.VISIBLE);
        } else {
            holder.mineProtocolIv.setVisibility(View.INVISIBLE);
        }

        // 竞品
        if (ConstValues.FLAG_1.equals(item.getVieFlag())) {
            holder.vieIv.setVisibility(View.VISIBLE);
        } else {
            holder.vieIv.setVisibility(View.INVISIBLE);
        }

        // 竞品协议店
        if (ConstValues.FLAG_1.equals(item.getVieProtocolFlag())) {
            holder.vieProtocolIv.setVisibility(View.VISIBLE);
        } else {
            holder.vieProtocolIv.setVisibility(View.INVISIBLE);
        }

        // 当结束拜访,拜访界面消失,显示购物车的时候,用到这个判断
        if (selectItem == -1 && item.getTerminalkey().equals(termId)) {
            selectItem = position;
        }

        // 整体的字变色
        if (position == selectItem) {
            // 将选中的条目,变成灰色
            holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            holder.visitTimeTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            // 选中的条目,根据是否今日拜访过,显示拜访时间
            if (!CheckUtil.isBlankOrNull(item.getVisitTime())) {
                holder.visitTimeTv.setVisibility(View.VISIBLE);
            }
        } else {
            // 未选中的条目,变成白色
            if (ConstValues.FLAG_1.equals(item.getUploadFlag())) {
                // 已提交过的
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
                holder.visitTimeTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
            } else if (ConstValues.FLAG_0.equals(item.getUploadFlag())) {
                // 已拜访过未上传的
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
                holder.visitTimeTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
            } else {
                // 未拜访过的
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
                holder.visitTimeTv.setTextColor(context.getResources().getColor(R.color.gray_color_cccccc));
            }
        }

        /*AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(300);
        convertView.startAnimation(alphaAnimation);*/

        return convertView;
    }

    private class ViewHolder {
        private LinearLayout termcart_ll;
        private TextView terminalSequenceEt;
        private TextView terminalNameTv;
        private TextView visitTimeTv;
        private TextView terminalTypeTv;
        private ImageView updateIv;
        private ImageView updateFailIv;
        private ImageView iserrorIv;
        private ImageView mineIv;
        private ImageView mineProtocolIv;
        private ImageView vieIv;
        private ImageView vieProtocolIv;
    }
}
