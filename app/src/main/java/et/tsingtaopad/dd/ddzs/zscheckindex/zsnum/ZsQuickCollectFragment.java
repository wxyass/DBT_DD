package et.tsingtaopad.dd.ddzs.zscheckindex.zsnum;

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

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddxt.checking.domain.XtQuicklyProItem;
import et.tsingtaopad.dd.ddxt.checking.num.XtQuickCollectService;
import et.tsingtaopad.dd.ddzs.zscheckindex.ZsCheckIndexFragment;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 快速采集 指标的现有量,变化量 填写界面
 */

public class ZsQuickCollectFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private LinearLayout quicklyDialogLv;
    private List<XtProItem> tempLst;
    ZsCheckIndexFragment.MyHandler handler;
    private XtQuickCollectService xtQuickCollectService;
    private Button sureBtn;
    private List<XtQuicklyProItem> quicklyProItemLst;
    private List<XtProIndex> calculateLst;

    public ZsQuickCollectFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZsQuickCollectFragment(ZsCheckIndexFragment.MyHandler handler) {
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
        calculateLst = (List<XtProIndex>)bundle.getSerializable("calculateLst");//List<XtProIndex> calculateLst
        quicklyProItemLst = xtQuickCollectService.initQuicklyProItem(proItemLst);

        quicklyDialogLv.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<quicklyProItemLst.size();i++){
            View layout = LayoutInflater.from(getActivity()).inflate(R.layout.zdzs_quicklydialog_lvitem_o,null);
            TextView indexNameTv = (TextView)layout.findViewById(R.id.zdzs_quicklydialog_tv_itemname);
            ListView proItemLv = (ListView)layout.findViewById(R.id.zdzs_quicklydialog_lv_pro);
            XtQuicklyProItem item = quicklyProItemLst.get(i);
            indexNameTv.setHint(item.getItemId());
            indexNameTv.setText(item.getItemName());
            proItemLv.setAdapter(new ZsQuicklyDialogItemAdapter(getActivity(), item.getProItemLst(),item.getItemId()));
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
        for (int i = 0; i < quicklyProItemLst.size(); i++) {
            itemI = quicklyProItemLst.get(i);
            itemJLst = itemI.getProItemLst();
            itemLv =(ListView) quicklyDialogLv.getChildAt(i).findViewById(R.id.zdzs_quicklydialog_lv_pro);
            for (int j = 0; j < itemJLst.size(); j++) {
                itemJ = itemJLst.get(j);

                // 获取文本框的值
                itemEt = (EditText)itemLv.getChildAt(j).findViewById(R.id.item_zs_quick_et_finalnum);
                //itemJ.setChangeNum((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
                itemJ.setValitemval(itemEt.getText().toString());// 督导结果量

            }
        }

        ViewUtil.hideSoftInputFromWindow(getActivity(),v);
        //quicklyDialog.cancel();

        for (XtProIndex xtProIndex:calculateLst) {
            for (XtProIndexValue xtProIndexValue:xtProIndex.getIndexValueLst()) {
                xtProIndexValue.setValchecktypeflag("N");// // 终端追溯 指标正确与否
            }
        }

        handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_INDEX_AMEND);

        /*handler.sendEmptyMessage(XtCheckIndexFragment.INPUT_SUC);
        */
        supportFragmentManager.popBackStack();

    }
}
