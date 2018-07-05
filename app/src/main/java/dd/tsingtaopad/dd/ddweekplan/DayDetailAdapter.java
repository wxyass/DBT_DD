package dd.tsingtaopad.dd.ddweekplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.adapter.AlertKeyValueAdapter;
import dd.tsingtaopad.adapter.DayDetailSelectKeyValueAdapter;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.alertview.AlertView;
import dd.tsingtaopad.core.view.alertview.OnDismissListener;
import dd.tsingtaopad.core.view.alertview.OnItemClickListener;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.CmmDatadicM;
import dd.tsingtaopad.db.table.MitPlanweekM;
import dd.tsingtaopad.db.table.MstGridM;
import dd.tsingtaopad.db.table.MstMarketareaM;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.listviewintf.ILongClick;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DayDetailAdapter extends BaseAdapter implements View.OnClickListener, View.OnLongClickListener {

    private final String TAG = "DayDetailAdapter";

    private Activity context;
    private List<DayDetailStc> dataLst;
    private ILongClick listener;
    private AlertView mAlertViewExt;//窗口拓展例子
    private DayDetailStc dayDetailStc;
    private XtTermSelectService xtSelectService;
    private MitPlanweekM weekplan;
    private boolean isChecking;// true:正在审核/审核通过   false:未制定,未提交,未通过

    public DayDetailAdapter(Activity context, List<DayDetailStc> dataLst, MitPlanweekM weekplan,ILongClick listener) {
        this.context = context;
        this.dataLst = dataLst;
        this.listener = listener;
        this.weekplan = weekplan;
        xtSelectService = new XtTermSelectService(context);

        if ("2".equals(weekplan.getStatus()) || "3".equals(weekplan.getStatus())) {// 0未制定1未提交2待审核3审核通过4未通过
            isChecking = true;
        } else {
            isChecking = false;
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_weekplan_daydetail, null);
            holder.ll_all = (LinearLayout) convertView.findViewById(R.id.item_daydetail_ll_all);
            holder.rl_check = (RelativeLayout) convertView.findViewById(R.id.item_daydetail_rl_check);
            holder.tv_checkname = (TextView) convertView.findViewById(R.id.item_daydetail_tv_check);
            holder.rl_areaid = (RelativeLayout) convertView.findViewById(R.id.item_daydetail_rl_areaid);
            holder.tv_areaname = (TextView) convertView.findViewById(R.id.item_daydetail_tv_areaid);
            holder.tv_gridkey = (RelativeLayout) convertView.findViewById(R.id.item_daydetail_tv_gridkey);
            holder.tv_valgridname = (TextView) convertView.findViewById(R.id.item_daydetail_tv_valgridkey);
            holder.rl_routekey = (RelativeLayout) convertView.findViewById(R.id.item_daydetail_rl_routekey);
            holder.tv_routename = (TextView) convertView.findViewById(R.id.item_daydetail_tv_routekey);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DayDetailStc item = dataLst.get(position);

        holder.ll_all.setTag(position);
        holder.ll_all.setOnLongClickListener(listener);

        // 追溯项
        if(item.getValcheckname()!=null){
            String check = item.getValcheckname();
            if (check.endsWith(",")) {
                check = check.substring(0,check.length() - 1);
            }
            holder.tv_checkname .setText(check);
        }else{
            holder.tv_checkname.setText("");
        }

        // 追溯区域
        holder.tv_areaname.setText(item.getValareaname());
        // 追溯定格
        holder.tv_valgridname.setText(item.getValgridname());
        // 追溯路线
        /*if(item.getValroutenames()!=null){
            //holder.tv_routename.setText(item.getValroutenames().substring(0,item.getValroutenames().length()-1));
            holder.tv_routename.setText(item.getValroutenames());
        }else{
            holder.tv_routename.setText("");
        }*/

        if(item.getValroutenames()!=null){
            String route = item.getValroutenames();
            if (route.endsWith(",")) {
                route = route.substring(0,route.length() - 1);
            }
            holder.tv_routename .setText(route);
        }else{
            holder.tv_routename.setText("");
        }


        holder.rl_check.setTag(position);
        holder.rl_areaid.setTag(position);
        holder.tv_gridkey.setTag(position);
        holder.rl_routekey.setTag(position);
        holder.rl_check.setOnClickListener(this);
        holder.rl_areaid.setOnClickListener(this);
        holder.tv_gridkey.setOnClickListener(this);
        holder.rl_routekey.setOnClickListener(this);

        holder.rl_check.setOnLongClickListener(this);
        holder.rl_areaid.setOnLongClickListener(this);
        holder.tv_gridkey.setOnLongClickListener(this);
        holder.rl_routekey.setOnLongClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int posi = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_daydetail_rl_check:// 追溯项
                // alertShow3(posi);
                if(!isChecking){
                    //alertShow7(posi);
                    alertZsShow3(posi);
                }else{
                    Toast.makeText(context,"审核状态下不可修改",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.item_daydetail_rl_areaid:// 追溯区域

                if(!isChecking){
                    alertShow4(posi);
                }else{
                    Toast.makeText(context,"审核状态下不可修改",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item_daydetail_tv_gridkey:// 追溯定格
                if(!isChecking){
                    alertShow5(posi);
                }else{
                    Toast.makeText(context,"审核状态下不可修改",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item_daydetail_rl_routekey:// 追溯路线
                if(!isChecking){
                    // alertShow6(posi); 从下方弹出
                    alertShow7(posi);// 从中间弹出
                }else{
                    Toast.makeText(context,"审核状态下不可修改",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        int posi = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_daydetail_rl_check:// 追溯项
            case R.id.item_daydetail_rl_areaid:// 追溯区域
            case R.id.item_daydetail_tv_gridkey:// 追溯定格
            case R.id.item_daydetail_rl_routekey:// 追溯路线
                // 删除该条日计划详情
                deletesupply(posi,dataLst.get(posi));
                break;
            default:
                break;
        }
        return true;
    }

    private class ViewHolder {
        private LinearLayout ll_all;
        private RelativeLayout rl_check;
        private TextView tv_checkname;
        private RelativeLayout rl_areaid;
        private TextView tv_areaname;
        private RelativeLayout tv_gridkey;
        private TextView tv_valgridname;
        private RelativeLayout rl_routekey;
        private TextView tv_routename;
    }

    // 长按删除我品供货关系弹窗
    private void deletesupply(final int posi ,final DayDetailStc dayDetailStc) {
        //String proName = xtInvoicingStc.getProName();
        // 普通窗口
        mAlertViewExt = new AlertView("删除该条计划", null, "取消", new String[]{"确定"}, null, context, AlertView.Style.Alert,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        //Toast.makeText(getApplicationContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                        if (0 == position) {// 确定按钮:0   取消按钮:-1
                            //if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
                            DbtLog.logUtils(TAG, "删除供货关系：是");
                            // 删除对应的数据
                            if (!CheckUtil.isBlankOrNull(dayDetailStc.getDaykey())) {
                                DbtLog.logUtils(TAG,"解除供货关系");
                                WeekPlanService service = new WeekPlanService(context);
                                boolean isFlag = service.deleteDayDetail(dayDetailStc);
                                if(isFlag){
                                    // 删除界面listView相应行
                                    dataLst.remove(posi);
                                    notifyDataSetChanged();
                                    /*askAdapter.setDelPosition(posi);
                                    checkGoodsAdapter.notifyDataSetChanged();

                                    ViewUtil.setListViewHeight(askGoodsLv);
                                    ViewUtil.setListViewHeight(checkGoodsLv);*/
                                }else{
                                    Toast.makeText(context, "删除产品失败!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // 删除界面listView相应行
                                dataLst.remove(posi);
                                notifyDataSetChanged();
                                /*askAdapter.setDelPosition(posi);
                                checkGoodsAdapter.notifyDataSetChanged();

                                ViewUtil.setListViewHeight(askGoodsLv);
                                ViewUtil.setListViewHeight(checkGoodsLv);*/
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


    // 新追溯项
    public void alertZsShow3(final int position) {

        final DayDetailStc item = dataLst.get(position);

        final List<KvStc> typeLst  = initRetrospect();

        // 如果追溯项大于0,添加全选按钮
        if(typeLst.size()>0){
            typeLst.add(0, new KvStc("-1", "全选", "-1"));
        }

        // 加载视图
        View extView = LayoutInflater.from(context).inflate(R.layout.alert_daydetail_form, null);

        final ListView dataLv = (ListView) extView.findViewById(R.id.alert_daydetal_lv);
        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");


        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        if(!TextUtils.isEmpty(item.getValcheckkey())){
            selectedId = Arrays.asList(item.getValcheckkey().split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : typeLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }

        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(context,typeLst,
                new String[]{"key","value"}, item.getValroutekeys());
        dataLv.setAdapter(sadapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int posi, long arg3) {
                CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String checkkey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String checkname = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                if(0 == posi){// 全选
                    if(itemCB.isChecked()){// 是选中状态
                        StringBuffer key = new StringBuffer();
                        StringBuffer name = new StringBuffer();
                        for (KvStc stc : typeLst){
                            if(!"-1".equals(stc.getParentKey())){
                                key.append(stc.getKey());
                                key.append(",");
                                name.append(stc.getValue());
                                name.append(",");
                            }
                            stc.setIsDefault("1");
                        }
                        item.setValcheckkey(key.toString());
                        item.setValcheckname(name.toString());
                    }else{// 是未选中状态
                        for (KvStc stc : typeLst){
                            stc.setIsDefault("0");
                        }
                        item.setValcheckkey("");
                        item.setValcheckname("");
                    }
                }else{
                    if (itemCB.isChecked()) {
                        item.setValcheckkey(FunUtil.isBlankOrNullTo(item.getValcheckkey(),"")  + checkkey);
                        item.setValcheckname(FunUtil.isBlankOrNullTo(item.getValcheckname(),"") + checkname);
                        ((KvStc)typeLst.get(posi)).setIsDefault("1");
                    } else {
                        item.setValcheckkey(FunUtil.isBlankOrNullTo(item.getValcheckkey(),"") .replace(checkkey, ""));
                        item.setValcheckname(FunUtil.isBlankOrNullTo(item.getValcheckname(),"").replace(checkname, ""));
                        ((KvStc)typeLst.get(posi)).setIsDefault("0");
                        ((KvStc)typeLst.get(0)).setIsDefault("0");
                    }
                }


                sadapter.notifyDataSetChanged();
            }
        });


        // 显示对话框
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(extView, 0, 0, 0, 0);
        dialog.setCancelable(true);
        dialog.show();


        // 确定
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                /*item.setValroutekeys(item.getValroutekeys().substring(0,item.getValroutekeys().length()-1));
                item.setValroutenames(item.getValroutenames().substring(0,item.getValroutenames().length()-1));*/

                dialog.dismiss();
                notifyDataSetChanged();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });
    }


    // 追溯区域
    public void alertShow4(final int position) {

        final DayDetailStc item = dataLst.get(position);
        final List<MstMarketareaM> valueLst = xtSelectService.getMstMarketareaMList(PrefUtils.getString(context, "departmentid", ""));

        mAlertViewExt = new AlertView("请选择区域", null, null, null,
                null, context, AlertView.Style.ActionSheet, null);

        ViewGroup lvextView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.alert_list_form, null);
        ListView lvlistview = (ListView) lvextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter lvkeyValueAdapter = new AlertKeyValueAdapter(context, valueLst,
                new String[]{"areaid", "areaname"}, item.getValareakey());
        lvlistview.setAdapter(lvkeyValueAdapter);
        lvlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item.setValareaname(valueLst.get(position).getAreaname());
                item.setValareakey(valueLst.get(position).getAreaid());
                mAlertViewExt.dismiss();

                item.setValgridkey("");
                item.setValgridname("");
                item.setValroutekeys("");
                item.setValroutenames("");

                notifyDataSetChanged();
            }
        });


        mAlertViewExt.addExtView(lvextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();
    }

    // 追溯定格
    public void alertShow5(final int position) {

        final DayDetailStc item = dataLst.get(position);
        final List<MstGridM> valueLst = xtSelectService.getMstGridMList(FunUtil.isBlankOrNullTo(item.getValareakey(),""));

        mAlertViewExt = new AlertView("请选择定格", null, null, null,
                null, context, AlertView.Style.ActionSheet, null);

        ViewGroup lvextView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.alert_list_form, null);
        ListView lvlistview = (ListView) lvextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter lvkeyValueAdapter = new AlertKeyValueAdapter(context, valueLst,
                new String[]{"gridkey", "gridname"}, item.getValgridkey());
        lvlistview.setAdapter(lvkeyValueAdapter);
        lvlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item.setValgridkey(valueLst.get(position).getGridkey());
                item.setValgridname(valueLst.get(position).getGridname());
                mAlertViewExt.dismiss();
                item.setValroutekeys("");
                item.setValroutenames("");
                notifyDataSetChanged();
            }
        });


        mAlertViewExt.addExtView(lvextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();
    }

    // 追溯路线  从下方弹出
    public void alertShow6(final int position) {

        final DayDetailStc item = dataLst.get(position);
        List<MstRouteM> valueLst = xtSelectService.getMstRouteMList(FunUtil.isBlankOrNullTo(item.getValgridkey(),""));
        final List<KvStc> typeLst = new ArrayList<KvStc>();
        for (MstRouteM routeM : valueLst) {
            KvStc kvStc =new KvStc();
            kvStc.setKey(routeM.getRoutekey());
            kvStc.setValue(routeM.getRoutename());
            kvStc.setIsDefault("");
            typeLst.add(kvStc);
        }

        mAlertViewExt = new AlertView(null, null, null, null,null, context, AlertView.Style.ActionSheet, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.alert_dayroute_form, null);

        final ListView dataLv = (ListView) extView.findViewById(R.id.alert_dayroute_lv);
        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");

        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        if(!TextUtils.isEmpty(item.getValroutekeys())){
            selectedId = Arrays.asList(item.getValroutekeys().split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : typeLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }

        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(context,
                typeLst, new String[]{"key","value"}, item.getValroutekeys());
        dataLv.setAdapter(sadapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String routekey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String routename = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";
                if (itemCB.isChecked()) {
                    item.setValroutekeys(FunUtil.isBlankOrNullTo(item.getValroutekeys(),"")  + routekey);
                    item.setValroutenames(FunUtil.isBlankOrNullTo(item.getValroutenames(),"") + routename);
                    ((KvStc)typeLst.get(arg2)).setIsDefault("1");
                } else {
                    item.setValroutekeys(FunUtil.isBlankOrNullTo(item.getValroutekeys(),"") .replace(routekey, ""));
                    item.setValroutenames(FunUtil.isBlankOrNullTo(item.getValroutenames(),"").replace(routename, ""));
                    ((KvStc)typeLst.get(arg2)).setIsDefault("0");
                }
                sadapter.notifyDataSetChanged();
            }
        });


        // 确定
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                /*item.setValroutekeys(item.getValroutekeys().substring(0,item.getValroutekeys().length()-1));
                item.setValroutenames(item.getValroutenames().substring(0,item.getValroutenames().length()-1));*/

                mAlertViewExt.dismiss();
                notifyDataSetChanged();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mAlertViewExt.dismiss();
            }
        });



        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                DbtLog.logUtils(TAG, "取消选择结果");
            }
        });
        mAlertViewExt.show();
    }

    // 追溯路线  从中间弹出
    public void alertShow7(final int position) {

        final DayDetailStc item = dataLst.get(position);
        List<MstRouteM> valueLst = xtSelectService.getMstRouteMList(FunUtil.isBlankOrNullTo(item.getValgridkey(),""));
        final List<KvStc> typeLst = new ArrayList<KvStc>();
        for (MstRouteM routeM : valueLst) {
            KvStc kvStc =new KvStc();
            kvStc.setKey(routeM.getRoutekey());
            kvStc.setValue(routeM.getRoutename());
            kvStc.setIsDefault("");
            typeLst.add(kvStc);
        }
        // 如果路线大于0,添加全选按钮
        if(typeLst.size()>0){
            typeLst.add(0, new KvStc("-1", "全选", "-1"));
        }

        ViewGroup extView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.alert_dayroute_form, null);

        final ListView dataLv = (ListView) extView.findViewById(R.id.alert_dayroute_lv);
        RelativeLayout rl_back1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_back);
        android.support.v7.widget.AppCompatTextView bt_back1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_back);
        android.support.v7.widget.AppCompatTextView title = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_tv_title);
        RelativeLayout rl_confirm1 = (RelativeLayout) extView.findViewById(R.id.top_navigation_rl_confirm);
        android.support.v7.widget.AppCompatTextView bt_confirm1 = (android.support.v7.widget.AppCompatTextView) extView.findViewById(R.id.top_navigation_bt_confirm);

        title.setText("选择结果");
        rl_confirm1.setVisibility(View.VISIBLE);
        bt_confirm1.setText("确定");

        // 获取已选中的集合
        List<String>  selectedId = new ArrayList<String>();
        if(!TextUtils.isEmpty(item.getValroutekeys())){
            selectedId = Arrays.asList(item.getValroutekeys().split(","));
        }

        // 标记选中状态
        for (KvStc kvstc : typeLst) {
            for (String itemselect : selectedId) {
                if (kvstc.getKey().equals(itemselect)) {
                    kvstc.setIsDefault("1");// 0:没选中 1已选中
                }
            }
        }

        final DayDetailSelectKeyValueAdapter sadapter = new DayDetailSelectKeyValueAdapter(context,
                typeLst, new String[]{"key","value"}, item.getValroutekeys());

        dataLv.setAdapter(sadapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                CheckBox itemCB = (CheckBox) arg1.findViewById(R.id.common_multiple_cb_lvitem);
                TextView itemTv = (TextView) arg1.findViewById(R.id.common_multiple_tv_lvitem);
                itemCB.toggle();//点击整行可以显示效果

                String routekey = FunUtil.isBlankOrNullTo(itemTv.getHint(), " ") + ",";
                String routename = FunUtil.isBlankOrNullTo(itemTv.getText().toString(), " ") + ",";

                if(0 == arg2){// 全选
                    if(itemCB.isChecked()){// 是选中状态
                        StringBuffer key = new StringBuffer();
                        StringBuffer name = new StringBuffer();
                        for (KvStc stc : typeLst){
                            if(!"-1".equals(stc.getParentKey())){
                                key.append(stc.getKey());
                                key.append(",");
                                name.append(stc.getValue());
                                name.append(",");
                            }
                            stc.setIsDefault("1");
                        }
                        item.setValroutekeys(key.toString());
                        item.setValroutenames(name.toString());
                    }else{// 是未选中状态
                        for (KvStc stc : typeLst){
                            stc.setIsDefault("0");
                        }
                        item.setValroutekeys("");
                        item.setValroutenames("");
                    }
                }else{
                    if (itemCB.isChecked()) {
                        item.setValroutekeys(FunUtil.isBlankOrNullTo(item.getValroutekeys(),"")  + routekey);
                        item.setValroutenames(FunUtil.isBlankOrNullTo(item.getValroutenames(),"") + routename);
                        ((KvStc)typeLst.get(arg2)).setIsDefault("1");
                    } else {
                        item.setValroutekeys(FunUtil.isBlankOrNullTo(item.getValroutekeys(),"") .replace(routekey, ""));
                        item.setValroutenames(FunUtil.isBlankOrNullTo(item.getValroutenames(),"").replace(routename, ""));
                        ((KvStc)typeLst.get(arg2)).setIsDefault("0");
                        ((KvStc)typeLst.get(0)).setIsDefault("0");
                    }
                }
                sadapter.notifyDataSetChanged();
            }
        });

        // 显示对话框
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(extView, 0, 0, 0, 0);
        dialog.setCancelable(true);
        dialog.show();

        // 确定
        rl_confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                /*item.setValroutekeys(item.getValroutekeys().substring(0,item.getValroutekeys().length()-1));
                item.setValroutenames(item.getValroutenames().substring(0,item.getValroutenames().length()-1));*/

                dialog.dismiss();
                notifyDataSetChanged();
            }
        });

        // 取消
        rl_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialog.dismiss();
            }
        });
    }



    /**
     * 初始化追溯项
     */
    private List<KvStc> initRetrospect() {

        List<CmmDatadicM> dataDicLst = new ArrayList<CmmDatadicM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            QueryBuilder<CmmDatadicM, String> qBuilder =
                    helper.getCmmDatadicMDao().queryBuilder();
            Where<CmmDatadicM, String> where = qBuilder.where();
            where.or(where.isNull("deleteflag"),
                    where.eq("deleteflag", "0"),
                    where.eq("parentcode", "39DD41A399348C68E05010ACE0016FCA"));
            qBuilder.orderBy("parentcode", true).orderBy("orderbyno", true);
            dataDicLst = qBuilder.query();
        } catch (SQLException e) {
            Log.e(TAG, "初始化数据字典失败", e);
        }

        // 获取数据字典表中区域字典对应的父ID
        String areaType=PropertiesUtil.getProperties("retrospect_item");

        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (areaType.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode(), item.getDicname(), item.getParentcode()));
            } /*else if (kvLst.size() > 0) {
                break;
            }*/
        }
        return kvLst;
    }


}