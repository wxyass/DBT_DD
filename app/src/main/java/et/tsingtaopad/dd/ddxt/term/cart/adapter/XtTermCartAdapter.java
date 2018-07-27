package et.tsingtaopad.dd.ddxt.term.cart.adapter;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.core.view.alertview.OnDismissListener;
import et.tsingtaopad.core.view.alertview.OnItemClickListener;
import et.tsingtaopad.dd.ddxt.term.cart.XtTermCartService;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;

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
public class XtTermCartAdapter extends BaseAdapter implements OnClickListener,View.OnLongClickListener {

    private final String TAG = "XtTermCartAdapter";

    private Activity context;
    private List<XtTermSelectMStc> dataLst;
    private List<XtTermSelectMStc> seqTermList;
    private RelativeLayout confirmBt;
    private String termId;
    private String type;// 1协同  2追溯
    private int selectItem = -1;
    private boolean isUpdate;//是否处于修改状态

    public XtTermCartAdapter(Activity context, List<XtTermSelectMStc> seqTermList,
                             List<XtTermSelectMStc> termialLst, RelativeLayout confirmBt, String termId,String type) {
        this.context = context;
        this.seqTermList = seqTermList;
        this.dataLst = termialLst;
        this.confirmBt = confirmBt;
        this.termId = termId;
        this.type = type;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_termcart, null);
            holder.terminalSequenceEt = (EditText) convertView.findViewById(R.id.item_termcart_et_sequence);
            holder.terminalNameTv = (TextView) convertView.findViewById(R.id.item_termcart_tv_name);
            // holder.visitDateTv = (TextView) convertView.findViewById(R.id.item_termcart_tv_visitdate);// 拜访时间汇总
            holder.terminalRb = (RadioButton) convertView.findViewById(R.id.item_termcart_rb);// 条目选中标记
            holder.terminalTypeTv = (TextView) convertView.findViewById(R.id.item_termcart_tv_type);// 渠道类型

            holder.updateIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_update);// 上传成功标识
            holder.updateFailIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_update_fail);// 上传失败标识

            holder.mineIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_mime);
            holder.mineProtocolIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_mineprotocol);
            holder.vieIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_vie);
            holder.vieProtocolIv = (ImageView) convertView.findViewById(R.id.item_termcart_iv_vieprotocol);

            holder.itermLayout = (LinearLayout) convertView.findViewById(R.id.item_termcart_ll);// 整体条目
            holder.itemCoverV = convertView.findViewById(R.id.item_termcart_v_cover);// 失效终端底色

            holder.terminalSequenceEt.addTextChangedListener(new MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, ViewHolder holder) {
                    int position = (Integer) holder.terminalSequenceEt.getTag();
                    saveEditValue(s.toString(), position);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 排序
        holder.terminalSequenceEt.setTag(position);

        XtTermSelectMStc item = dataLst.get(position);
        holder.terminalNameTv.setHint(item.getTerminalkey());
        //是否允许修改
        if (isUpdate) {
            holder.terminalSequenceEt.setEnabled(true);
            holder.terminalSequenceEt.setBackgroundColor(context.getResources().getColor(R.color.bg_app));
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
            holder.itermLayout.setOnLongClickListener(null);
            holder.itermLayout.setTag(position);
        } else {
            holder.itemCoverV.setVisibility(View.GONE);
            holder.terminalRb.setOnClickListener(this);
            holder.terminalRb.setTag(position);
            holder.itermLayout.setOnClickListener(this);
            holder.itermLayout.setOnLongClickListener(this);
            holder.itermLayout.setTag(position);
        }
        holder.terminalSequenceEt.setText(item.getSequence());
        holder.terminalNameTv.setText(item.getTerminalname());
        holder.terminalTypeTv.setText(item.getTerminalType());
        /*if (!CheckUtil.isBlankOrNull(item.getVisitTime())) {
            holder.visitDateTv.setVisibility(View.VISIBLE);
            holder.visitDateTv.setText(item.getVisitTime());
        } else {
            holder.visitDateTv.setVisibility(View.GONE);
        }*/

        // 上传标记
        /*if (ConstValues.FLAG_1.equals(item.getSyncFlag())) {
            holder.terminalNameTv.setTextColor(Color.RED);
            holder.updateIv.setVisibility(View.VISIBLE);
        } else if (ConstValues.FLAG_0.equals(item.getSyncFlag())) {
            holder.terminalNameTv.setTextColor(Color.YELLOW);
            holder.updateIv.setVisibility(View.INVISIBLE);
        } else {
            holder.terminalNameTv.setTextColor(Color.BLACK);
            holder.updateIv.setVisibility(View.INVISIBLE);
        }*/

        // 上传标记  SyncFlag对应拜访表Padisconsistent
        if(ConstValues.FLAG_1.equals(item.getUploadFlag())&&ConstValues.FLAG_1.equals(item.getSyncFlag())){
            // 结束上传  上传成功
            holder.terminalNameTv.setTextColor(Color.RED);
            holder.updateIv.setVisibility(View.VISIBLE);
            holder.updateFailIv.setVisibility(View.GONE);
        }else if(ConstValues.FLAG_1.equals(item.getUploadFlag())&&ConstValues.FLAG_0.equals(item.getSyncFlag())){
            // 结束上传  上传失败
            holder.terminalNameTv.setTextColor(Color.YELLOW);
            holder.updateIv.setVisibility(View.GONE);// 后面修改
            holder.updateFailIv.setVisibility(View.VISIBLE);
        }else {
            holder.terminalNameTv.setTextColor(Color.BLACK);
            holder.updateIv.setVisibility(View.GONE);
            holder.updateFailIv.setVisibility(View.GONE);
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
            holder.itermLayout.setBackgroundColor(context.getResources().getColor(R.color.bg_content_color_gray));
            holder.terminalRb.setChecked(true);
            holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            //holder.visitDateTv.setTextColor(context.getResources().getColor(R.color.font_color_green));
            // 选中的条目,根据是否今日拜访过,显示拜访时间
            if (!CheckUtil.isBlankOrNull(item.getVisitTime())) {
                //holder.visitDateTv.setVisibility(View.VISIBLE);
            }
        } else {
            // 未选中的条目,变成白色
            holder.itermLayout.setBackgroundColor(Color.WHITE);
            holder.terminalRb.setChecked(false);
            if (ConstValues.FLAG_1.equals(item.getUploadFlag())) {
                // 已提交过的
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
                //holder.visitDateTv.setTextColor(context.getResources().getColor(R.color.termlst_sync_font_color));
            } else if (ConstValues.FLAG_0.equals(item.getUploadFlag())) {
                // 已拜访过未上传的
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
                //holder.visitDateTv.setTextColor(context.getResources().getColor(R.color.termlst_insync_font_color));
            } else {
                // 未拜访过的
                holder.terminalNameTv.setTextColor(Color.BLACK);
                holder.terminalNameTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
                holder.terminalTypeTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
                //holder.visitDateTv.setTextColor(context.getResources().getColor(R.color.listview_item_font_color));
            }
            // 未选中的条目,将拜访时间隐藏
            //holder.visitDateTv.setVisibility(View.GONE);
        }

        return convertView;
    }



    private class ViewHolder {
        private EditText terminalSequenceEt;
        private RadioButton terminalRb;
        private TextView terminalNameTv;
        // private TextView visitDateTv;
        private TextView terminalTypeTv;
        private ImageView updateIv;
        private ImageView updateFailIv;
        private ImageView mineIv;
        private ImageView mineProtocolIv;
        private ImageView vieIv;
        private ImageView vieProtocolIv;
        private LinearLayout itermLayout;
        private View itemCoverV;
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

    @Override
    public boolean onLongClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        // Toast.makeText(context,"点击了"+ position,Toast.LENGTH_SHORT).show();
        deleteTerm(position);//
        return true;
    }

    private AlertView mAlertViewExt;//窗口拓展例子
    // 长按删除终端
    private void deleteTerm(final int posi) {
        final XtTermSelectMStc termSelectMStc = dataLst.get(posi);
        // 普通窗口
        mAlertViewExt = new AlertView("删除终端: "+termSelectMStc.getTerminalname(), null, "取消", new String[]{"确定"}, null, context, AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "删除终端：是");
                            // 删除对应的数据
                            if (!CheckUtil.isBlankOrNull(termSelectMStc.getTerminalkey())) {
                                DbtLog.logUtils(TAG,"删除终端");
                                XtTermCartService service = new XtTermCartService(context);
                                boolean isFlag = service.deleteXtTermCart(termSelectMStc.getTerminalkey(),type);
                                if(isFlag){
                                    // 删除界面listView相应行
                                    dataLst.remove(posi);
                                    notifyDataSetChanged();
                                    // ViewUtil.setListViewHeight(checkGoodsLv);
                                }else{
                                    Toast.makeText(context, "删除终端失败!", Toast.LENGTH_SHORT).show();
                                }
                            }else{

                            }
                        }

                    }
                })
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Object o) {
                        DbtLog.logUtils(TAG, "删除供货关系：否");
                    }
                });
        mAlertViewExt.show();
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
     * 是否排序
     * @param isUpdate the isUpdate to set
     */
    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    // 当用户修改顺序时,保存排序
    private void saveEditValue(String str, int position) {
        XtTermSelectMStc xttermselectmstc = dataLst.get(position);
        if (isUpdate && !str.equals(xttermselectmstc.getSequence())) {
            // 修改序号后,设置该终端在集合中的顺序,以便根据顺序排序
            resetSeq(xttermselectmstc.getTerminalkey(), str);
        }
        //xttermselectmstc.setSequence(str);
    }

    // 修改序号后,设置该终端在集合中的顺序,以便根据顺序排序
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
