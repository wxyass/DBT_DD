package et.tsingtaopad.dd.dddealplan.make;

import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.dd.ddxt.term.select.IXtTermSelectClick;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DdDealSelectAdapter.java</br>
 * 作者：ywm   </br>
 * 创建时间：2014-1-19</br>
 * 功能描述:终端列表adapter </br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdDealSelectAdapter extends BaseAdapter implements OnClickListener {

    private Activity context;
    private List<XtTermSelectMStc> dataLst;
    private List<XtTermSelectMStc> seqTermList;
    private TextView confirmBt;
    private String termId;
    private IXtTermSelectClick mListener;
    private int selectItem = -1;
    private boolean isUpdate;//是否处于修改状态


    public DdDealSelectAdapter(Activity context, List<XtTermSelectMStc> seqTermList, List<XtTermSelectMStc> termialLst,
                               TextView confirmBt, String termId, IXtTermSelectClick mListener) {
        this.context = context;
        this.seqTermList = seqTermList;
        this.dataLst = termialLst;
        this.confirmBt = confirmBt;
        this.termId = termId;
        this.mListener = mListener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        DdDealSelectAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new DdDealSelectAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_termselect, null);
            holder.terminalSequenceEt = (EditText) convertView.findViewById(R.id.item_termselect_et_sequence);
            holder.terminalNameTv = (TextView) convertView.findViewById(R.id.item_termselect_tv_name);
            holder.visitDateTv = (TextView) convertView.findViewById(R.id.item_termselect_tv_visitdate);// 拜访时间汇总
            holder.terminalRb = (RadioButton) convertView.findViewById(R.id.item_termselect_rb);// 条目选中标记
            holder.terminalTypeTv = (TextView) convertView.findViewById(R.id.item_termselect_tv_type);// 渠道类型

            holder.updateIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_update);// 上传成功标识
            holder.updateFailIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_update_fail);// 上传成功标识

            holder.mineIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_mime);
            holder.mineProtocolIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_mineprotocol);
            holder.vieIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_vie);
            holder.vieProtocolIv = (ImageView) convertView.findViewById(R.id.item_termselect_iv_vieprotocol);

            holder.addTerm = (ImageView) convertView.findViewById(R.id.item_termselect_iv_addterm);

            holder.itermLayout = (LinearLayout) convertView.findViewById(R.id.item_termselect_ll);// 整体条目
            holder.itemCoverV = convertView.findViewById(R.id.item_termselect_v_cover);// 失效终端底色

            holder.terminalSequenceEt.addTextChangedListener(new DdDealSelectAdapter.MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, DdDealSelectAdapter.ViewHolder holder) {
                    int position = (Integer) holder.terminalSequenceEt.getTag();
                    saveEditValue(s.toString(), position);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (DdDealSelectAdapter.ViewHolder) convertView.getTag();
        }
        holder.terminalSequenceEt.setTag(position);

        XtTermSelectMStc item = dataLst.get(position);
        holder.terminalNameTv.setHint(item.getTerminalkey());
        //是否允许修改排序
        if (isUpdate) {
            holder.terminalSequenceEt.setEnabled(true);
            holder.terminalSequenceEt.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.terminalSequenceEt.setEnabled(false);
        }
        //失效终端且未审核通过的变灰,不可编辑顺序，不可选中
        if ("3".equals(item.getStatus())) {
            holder.terminalSequenceEt.setEnabled(false);
            holder.terminalSequenceEt.setBackgroundColor(Color.WHITE);
            holder.itemCoverV.setVisibility(View.VISIBLE);
            holder.itemCoverV.getBackground().setAlpha(50);
            holder.terminalRb.setEnabled(false);
            holder.terminalRb.setOnClickListener(null);
            holder.terminalRb.setTag(position);
            holder.itermLayout.setOnClickListener(null);
            holder.itermLayout.setTag(position);
            holder.addTerm.setVisibility(View.GONE);
        } else {
            holder.itemCoverV.setVisibility(View.GONE);
            holder.terminalRb.setOnClickListener(this);
            holder.terminalRb.setTag(position);
            //holder.itermLayout.setOnClickListener(this);
            holder.itermLayout.setTag(position);
            holder.addTerm.setVisibility(View.VISIBLE);
        }

        //  整改计划  添加终端(原购物车+号)
        holder.addTerm.setTag(position);
        holder.addTerm.setOnClickListener(mListener);
        if("1".equals(item.getIsSelectToCart())){
            holder.addTerm.setImageResource(R.drawable.icon_select_minus);
        }else{
            holder.addTerm.setImageResource(R.drawable.icon_visit_add);
        }

        holder.terminalSequenceEt.setText(item.getSequence());
        holder.terminalNameTv.setText(item.getTerminalname());
        holder.terminalTypeTv.setText(item.getTerminalType());

        // 今日拜访时间
        holder.visitDateTv.setVisibility(View.GONE);

        // 上传标记  SyncFlag对应拜访表Padisconsistent
        holder.updateIv.setVisibility(View.GONE);
        holder.updateFailIv.setVisibility(View.GONE);

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

        return convertView;
    }

    private class ViewHolder {
        private EditText terminalSequenceEt;
        private RadioButton terminalRb;
        private TextView terminalNameTv;
        private TextView visitDateTv;
        private TextView terminalTypeTv;
        private ImageView updateIv;
        private ImageView updateFailIv;
        private ImageView mineIv;
        private ImageView mineProtocolIv;
        private ImageView vieIv;
        private ImageView vieProtocolIv;
        private LinearLayout itermLayout;
        private View itemCoverV;
        private ImageView addTerm;
    }

    @Override
    public void onClick(View v) {
        /*int position = Integer.parseInt(v.getTag().toString());
        setSelectItem(position);
        notifyDataSetChanged();
        confirmBt.setVisibility(View.VISIBLE);
        confirmBt.setTag(dataLst.get(position));*/
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    /**
     * @return the isUpdate
     */
    public boolean isUpdate() {
        return isUpdate;
    }

    /**
     * @param isUpdate the isUpdate to set
     */
    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    private void saveEditValue(String str, int position) {
        XtTermSelectMStc option = dataLst.get(position);
        if (isUpdate && !str.equals(option.getSequence())) {
            resetSeq(option.getTerminalkey(), str);
        }
        option.setSequence(str);
    }

    /***
     * 重新终端设置顺序
     * @param termKey
     * @param newSeq
     */
    private void resetSeq(String termKey, String newSeq) {
        for (int i = 0; i < seqTermList.size(); i++) {
            XtTermSelectMStc term = seqTermList.get(i);
            if (termKey.equals(term.getTerminalkey())) {
                if (CheckUtil.isBlankOrNull(newSeq)) {
                    seqTermList.remove(term);
                    term.setSequence(newSeq);
                    seqTermList.add(term);
                } else {
                    int newSeq_i = Integer.parseInt(newSeq);
                    if (newSeq_i >= seqTermList.size() - 1) {
                        seqTermList.remove(term);
                        term.setSequence(newSeq);
                        seqTermList.add(term);
                    } else {
                        seqTermList.remove(term);
                        term.setSequence(newSeq);
                        if (newSeq_i == 0) {
                            seqTermList.add(0, term);
                        } else {
                            seqTermList.add(newSeq_i - 1, term);
                        }
                    }
                }
                break;
            }
        }
    }

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
