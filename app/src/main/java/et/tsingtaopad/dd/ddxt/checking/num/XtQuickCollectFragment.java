package et.tsingtaopad.dd.ddxt.checking.num;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.dd.ddxt.checking.XtCheckIndexFragment;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddxt.checking.domain.XtQuicklyProItem;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 快速采集 指标的现有量,变化量 填写界面
 */

public class XtQuickCollectFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private LinearLayout quicklyDialogLv;
    private List<XtProItem> tempLst;
    XtCheckIndexFragment.MyHandler handler;
    private XtQuickCollectService xtQuickCollectService;
    private Button sureBtn;
    private List<XtQuicklyProItem> quicklyProItemLst;

    public XtQuickCollectFragment() {
    }

    @SuppressLint("ValidFragment")
    public XtQuickCollectFragment(XtCheckIndexFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_quickcollect, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        quicklyDialogLv = (LinearLayout) view.findViewById(R.id.xtbf_quicklydialog_lv);
        sureBtn = (Button) view.findViewById(R.id.xtbf_quickcollect_bt_next);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("快速采集");
         xtQuickCollectService = new XtQuickCollectService(getActivity(),null);
        List<XtProItem> proItemLst = new ArrayList<XtProItem>();

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        proItemLst = (List<XtProItem>)bundle.getSerializable("proItemLst");
        quicklyProItemLst = xtQuickCollectService.initQuicklyProItem(proItemLst);

        quicklyDialogLv.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<quicklyProItemLst.size();i++){
            View layout = LayoutInflater.from(getActivity()).inflate(R.layout.xtbf_quicklydialog_lvitem_o,null);
            TextView indexNameTv = (TextView)layout.findViewById(R.id.xtbf_quicklydialog_tv_itemname);
            ListView proItemLv = (ListView)layout.findViewById(R.id.xtbf_quicklydialog_lv_pro);
            XtQuicklyProItem item = quicklyProItemLst.get(i);
            indexNameTv.setHint(item.getItemId());
            indexNameTv.setText(item.getItemName());
            proItemLv.setAdapter(new XtQuicklyDialogItemAdapter(getActivity(), item.getProItemLst(),item.getItemId()));
            ViewUtil.setListViewHeight(proItemLv);
            quicklyDialogLv.addView(layout);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.xtbf_quickcollect_bt_next:
                saveValue(v);
                break;
            default:
                break;
        }
    }

    private void saveValue(View v) {

        if (ViewUtil.isDoubleClick(v.getId(), 2500)) return;
        XtQuicklyProItem itemI;
        XtProItem itemJ;
        List<XtProItem> itemJLst;
        ListView itemLv;
        EditText itemEt;
        EditText itemEt2;
        TextView itemTv;
        int isAllIn = 0;
        for (int i = 0; i < quicklyProItemLst.size(); i++) {
            itemI = quicklyProItemLst.get(i);
            itemJLst = itemI.getProItemLst();
            itemLv =(ListView) quicklyDialogLv.getChildAt(i).findViewById(R.id.xtbf_quicklydialog_lv_pro);
            for (int j = 0; j < itemJLst.size(); j++) {
                itemJ = itemJLst.get(j);

                // 获取文本框的值
                itemEt = (EditText)itemLv.getChildAt(j).findViewById(R.id.xtbf_quicklydialog_et_changenum);
                itemJ.setChangeNum((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
                //itemJ.setChangeNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
                itemJ.setBianhualiang(itemEt.getText().toString());// 现有量
                itemEt2 = (EditText)itemLv.getChildAt(j).findViewById(R.id.xtbf_quicklydialog_et_finalnum);
                itemJ.setFinalNum((FunUtil.isBlankOrNullToDouble(itemEt2.getText().toString())));
                //itemJ.setFinalNum(Double.valueOf(FunUtil.isNullToZero(itemEt2.getText().toString())));
                itemJ.setXianyouliang(itemEt2.getText().toString());// 现有量

                if("".equals(FunUtil.isNullSetSpace(itemEt.getText().toString()))||"".equals(FunUtil.isNullSetSpace(itemEt2.getText().toString()))){
                    isAllIn=1;
                }
            }
        }
        if (isAllIn == 1) {
            Toast.makeText(getActivity(), "所有的现有量,变化量必须填值(没货填0)", Toast.LENGTH_SHORT).show();
            return;
        }
        ViewUtil.hideSoftInputFromWindow(getActivity(),v);
        //quicklyDialog.cancel();

        handler.sendEmptyMessage(XtCheckIndexFragment.INPUT_SUC);

        // 关闭界面
        supportFragmentManager.popBackStack();

    }
}
