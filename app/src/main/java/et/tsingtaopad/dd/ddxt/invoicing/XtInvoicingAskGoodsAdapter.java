package et.tsingtaopad.dd.ddxt.invoicing;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import et.tsingtaopad.listviewintf.ILongClick;

/**
 * Created by yangwenmin on 2018/3/19.
 */

public class XtInvoicingAskGoodsAdapter extends BaseAdapter {//implements  View.OnFocusChangeListener{


    private final String TAG = "XtInvoicingAskGoodsAdapter";
    private Activity context;
    private List<XtInvoicingStc> dataLst;
    ListView askGoodsLv;
    ListView checkGoodsLv;

    private String termId;
    private String visitId;
    private int delPosition = -1;
    private AlertDialog dialog;
    private String seeFlag;
    private ILongClick listener;

    public XtInvoicingAskGoodsAdapter(Activity context, String seeFlag, List<XtInvoicingStc> dataLst,
                                      String termId, String visitId,
                                      ListView askGoodsLv, ListView checkGoodsLv, ILongClick listener) {
        this.context = context;
        this.seeFlag = seeFlag;
        this.dataLst = dataLst;
        this.termId = termId;
        this.visitId = visitId;
        this.askGoodsLv=askGoodsLv;
        this.checkGoodsLv=checkGoodsLv;
        this.listener=listener;
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
        Log.d("ask ", position+"  "+ DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss:SSS"));
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_xtbf_invoicing_askgoods, null);
            holder.itemaskgoodsLl = (LinearLayout)convertView.findViewById(R.id.item_askgoods_ll);
            holder.productNameTv = (TextView)convertView.findViewById(R.id.item_askgoods_tv_proname);
            holder.agencyTv = (TextView)convertView.findViewById(R.id.item_askgoods_tv_agencyname);
            holder.channelPriceEt = (EditText)convertView.findViewById(R.id.item_askgoods_et_qudao);
            holder.sellPriceEt = (EditText)convertView.findViewById(R.id.item_askgoods_et_lingshou);

            //holder.deleteIv = (Button)convertView.findViewById(R.id.askgoods_bt_delete);

            /*convertView = LayoutInflater.from(context).inflate(R.layout.invoicing_askgoods_lvitem, null);
            holder.productNameTv = (TextView)convertView.findViewById(R.id.askgoods_tv_proname);
            holder.channelPriceEt = (EditText)convertView.findViewById(R.id.askgoods_et_channelprice);
            holder.sellPriceEt = (EditText)convertView.findViewById(R.id.askgoods_et_sellproce);
            holder.agencyTv = (TextView)convertView.findViewById(R.id.askgoods_tv_agency);*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        XtInvoicingStc  item = dataLst.get(position);
        /*holder.deleteIv.setTag(position);
        if(ConstValues.FLAG_1.equals(seeFlag)){
            //属于查看状态，这里不进行操作
        }else{
            //不属于查看状态 ，点击事件有效
            holder.deleteIv.setOnClickListener(this);
        }*/
        holder.productNameTv.setHint(item.getProId());
        holder.productNameTv.setText(item.getProName());
        if (ConstValues.FLAG_0.equals(item.getChannelPrice())) {
            holder.channelPriceEt.setHint(item.getChannelPrice());
            holder.channelPriceEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getChannelPrice())){
            holder.channelPriceEt.setText(item.getChannelPrice());
        }else{
            holder.channelPriceEt.setHint(R.string.hit_input);
            holder.channelPriceEt.setText(null);
        }
        holder.channelPriceEt.setTag(position);
        //holder.channelPriceEt.setOnFocusChangeListener(this);
        if (ConstValues.FLAG_0.equals(item.getSellPrice())) {
            holder.sellPriceEt.setHint(item.getSellPrice());
            holder.sellPriceEt.setText(null);
        } else if(!CheckUtil.isBlankOrNull(item.getSellPrice())){
            holder.sellPriceEt.setText(item.getSellPrice());
        }else{
            holder.sellPriceEt.setHint(R.string.hit_input);
            holder.sellPriceEt.setText(null);
        }
        holder.sellPriceEt.setTag(position);
        //holder.sellPriceEt.setOnFocusChangeListener(this);
        holder.agencyTv.setHint(item.getAgencyId());
        holder.agencyTv.setText(item.getAgencyName());

        holder.itemaskgoodsLl.setTag(position);
        holder.itemaskgoodsLl.setOnLongClickListener(listener);

        return convertView;
    }

    private class ViewHolder {
        private LinearLayout itemaskgoodsLl;
        private TextView productNameTv;
        private TextView agencyTv;
        private EditText channelPriceEt;
        private EditText sellPriceEt;

        private Button deleteIv;
    }

    /**
     * 单击删除

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.askgoods_bt_delete:
                DbtLog.logUtils(TAG,"删除");
                if (dialog == null || !dialog.isShowing()) {
                    delPosition = (Integer)v.getTag();
                    final XtInvoicingStc item = dataLst.get(delPosition);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(R.string.dialog_title);
                    builder.setMessage("是否删除"+'"'+item.getProName()+'"');
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.dialog_bt_sure, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DbtLog.logUtils(TAG,"删除产品:"+item.getProName()+"、产品key："+item.getProId());
                            // 删除对应的数据
                            if (!CheckUtil.isBlankOrNull(item.getRecordId())) {
                                DbtLog.logUtils(TAG,"解除供货关系");
                                InvoicingService service = new InvoicingService(context, null);
                                boolean isFlag=service.deleteSupply(item.getRecordId(), termId, visitId, item.getProId());
                                if(isFlag){
                                    // 删除界面listView相应行
                                    dataLst.remove(delPosition);
                                    notifyDataSetChanged();
                                    checkAdapter.setDelPosition(delPosition);
                                    checkAdapter.notifyDataSetChanged();
                                    ViewUtil.setListViewHeight(askGoodsLv);
                                    ViewUtil.setListViewHeight(checkGoodsLv);
                                }else{
                                    Toast.makeText(context, "删除产品失败!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // 删除界面listView相应行
                                dataLst.remove(delPosition);
                                notifyDataSetChanged();
                                checkAdapter.setDelPosition(delPosition);
                                checkAdapter.notifyDataSetChanged();
                                ViewUtil.setListViewHeight(askGoodsLv);
                                ViewUtil.setListViewHeight(checkGoodsLv);
                            }

                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(R.string.dialog_bt_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DbtLog.logUtils(TAG,"删除取消");
                            dialog.dismiss();
                        }
                    });
                    dialog = builder.create();
                    dialog.show();
                }
                break;

            default:
                break;
        }
    }
     */

    /*@Override
    public void onFocusChange(View v, boolean hasFocus) {

        boolean hasFocus2 = hasFocus;

        EditText et = (EditText)v;
        int position = (Integer)v.getTag();
        if (position == delPosition) return;
        if (position > delPosition && delPosition != -1) {
            position = position -1;
        }
        if (position > -1) {
            XtInvoicingStc stc = dataLst.get(position);
            String content = et.getText().toString();
            String num = FunUtil.getDecimalsData(content);
            switch (et.getId()) {
                case R.id.item_askgoods_et_qudao:
                    // 渠道价-判断小数点
                    stc.setChannelPrice(num);
                    et.setText(num);
                    break;
                case R.id.item_askgoods_et_lingshou:
                    // 零售价-判断小数点
                    stc.setSellPrice(num);
                    et.setText(num);// 页面
                    break;

                default:
                    break;
            }
        }
    }*/

    public int getDelPosition() {
        return delPosition;
    }

    public void setDelPosition(int delPosition) {
        this.delPosition = delPosition;
    }

}
