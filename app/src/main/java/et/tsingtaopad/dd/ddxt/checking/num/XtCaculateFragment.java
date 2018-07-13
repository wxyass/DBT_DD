package et.tsingtaopad.dd.ddxt.checking.num;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 单个指标的现有量,变化量 填写界面
 */

public class XtCaculateFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private ListView colitemLv;
    private List<XtProItem> tempLst;
    XtCheckIndexFragment.MyHandler handler;
    XtProIndexValue xtProIndexValue;
    private Button sureBtn;

    public XtCaculateFragment() {
    }

    @SuppressLint("ValidFragment")
    public XtCaculateFragment(XtProIndexValue xtProIndexValue, XtCheckIndexFragment.MyHandler handler) {
        this.handler = handler;
        this.xtProIndexValue = xtProIndexValue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_caculate, container, false);
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
        // confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        colitemLv = (ListView) view.findViewById(R.id.xtbf_caculate_lv_colitem);
        sureBtn = (Button) view.findViewById(R.id.xtbf_caculate_bt_next);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        tempLst = new ArrayList<XtProItem>();
        tempLst = (List<XtProItem>) bundle.getSerializable("tempLst");
        String proName = bundle.getString("proName");
        titleTv.setText(proName);

        XtCalculateIndexItemPuhuoAdapter xtCalculateIndexItemPuhuoAdapter = new XtCalculateIndexItemPuhuoAdapter(getActivity(), tempLst);
        colitemLv.setAdapter(xtCalculateIndexItemPuhuoAdapter);

        ViewUtil.setListViewHeight(colitemLv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;
            case R.id.top_navigation_rl_confirm:// 确定
                saveValue(v);
                break;
            case R.id.xtbf_caculate_bt_next:// 保存
                saveValue(v);
                break;

            default:
                break;
        }
    }

    private void saveValue(View v) {

        if (ViewUtil.isDoubleClick(v.getId(), 2500))
            return;
        XtProItem item = null;
        EditText itemEt = null;
        EditText itemEt2 = null;
        TextView itemTv = null;
        int isAllIn = 0;
        for (int i = 0; i < tempLst.size(); i++) {
            item = tempLst.get(i);
            item.setCheckkey(xtProIndexValue.getIndexId());
            // 获取采集文本框内容
            itemEt = (EditText) colitemLv.getChildAt(i).findViewById(R.id.item_xt_calculatedialog_et_finalnum);
            item.setFinalNum((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
            //item.setFinalNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
            item.setXianyouliang(itemEt.getText().toString());// 现有量

            itemEt2 = (EditText) colitemLv.getChildAt(i).findViewById(R.id.item_xt_calculatedialog_et_changenum);
            item.setChangeNum((FunUtil.isBlankOrNullToDouble(itemEt2.getText().toString())));
            //item.setChangeNum(Double.valueOf(FunUtil.isNullToZero(itemEt.getText().toString())));
            item.setBianhualiang(itemEt2.getText().toString());// 变化量

            /*itemTv = (TextView) colitemLv.getChildAt(i).findViewById(R.id.calculatedialog_et_xinxiandu);
            item.setFreshness(FunUtil.isNullToZero(itemTv.getText().toString()));*/

            //
            if ("".equals(FunUtil.isNullSetSpace(itemEt.getText().toString())) || "".equals(FunUtil.isNullSetSpace(itemEt2.getText().toString()))) {
                isAllIn = 1;
            }
        }

        if (isAllIn == 1) {
            Toast.makeText(getActivity(), "所有的现有量,变化量必须填值(没货填0)", Toast.LENGTH_SHORT).show();
            return;
        }

        //calculateDialog.cancel();

        // 自动计算
        Bundle bundle = new Bundle();
        if (item != null) {
            bundle.putString("proId", item.getProId());
            bundle.putString("indexId", xtProIndexValue.getIndexId());
        }
        Message msg = new Message();
        msg.what = XtCheckIndexFragment.INPUT_SUC;
        msg.setData(bundle);
        handler.sendMessage(msg);

        // 关闭界面
        supportFragmentManager.popBackStack();

    }
}
