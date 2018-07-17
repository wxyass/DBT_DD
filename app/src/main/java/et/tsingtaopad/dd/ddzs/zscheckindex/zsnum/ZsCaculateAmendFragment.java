package et.tsingtaopad.dd.ddzs.zscheckindex.zsnum;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.ui.loader.LatteLoader;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddzs.zscheckindex.ZsCheckIndexFragment;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 单个指标的现有量,变化量 填写界面
 */

public class ZsCaculateAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private ListView colitemLv;
    private List<XtProItem> tempLst;
    ZsCheckIndexFragment.MyHandler handler;
    XtProIndexValue xtProIndexValue;
    private Button sureBtn;
    private String valchecktypeflag;

    public ZsCaculateAmendFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZsCaculateAmendFragment(XtProIndexValue xtProIndexValue, ZsCheckIndexFragment.MyHandler handler) {
        this.handler = handler;
        this.xtProIndexValue = xtProIndexValue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_caculate, container, false);
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

        colitemLv = (ListView) view.findViewById(R.id.zdzs_caculate_lv_colitem);
        sureBtn = (Button) view.findViewById(R.id.zdzs_caculate_bt_next);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        tempLst = new ArrayList<XtProItem>();
        tempLst = (List<XtProItem>) bundle.getSerializable("tempLst");
        String proName = bundle.getString("proName");
         valchecktypeflag = bundle.getString("valchecktypeflag");
        titleTv.setText(proName);

        ZsCalculateAmendAdapter zsCalculateAmendAdapter = new ZsCalculateAmendAdapter(getActivity(), tempLst);
        colitemLv.setAdapter(zsCalculateAmendAdapter);

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
            case R.id.zdzs_caculate_bt_next:// 保存
                saveValue(v);

                break;

            default:
                break;
        }
    }

    private void saveValue(View v) {

        if (ViewUtil.isDoubleClick(v.getId(), 2500))
            return;

        LatteLoader.showLoading(getActivity());// 处理数据中,在ZsCheckIndexFragment的autoZsCalculateSuc中关闭 和下面ischeckin检测


        XtProItem item = null;
        EditText itemEt = null;
        boolean ischeckin = true;// 如果是5d,并且填写值了才能保存
        for (int i = 0; i < tempLst.size(); i++) {
            item = tempLst.get(i);
            item.setCheckkey(xtProIndexValue.getIndexId());
            // 获取采集文本框内容
            itemEt = (EditText) colitemLv.getChildAt(i).findViewById(R.id.item_zs_calculatedialog_et_finalnum);
            //item.setValitemval((FunUtil.isBlankOrNullToDouble(itemEt.getText().toString())));
            item.setValitemval(itemEt.getText().toString());

            // 如果是5d,并且填写值了才能保存
            if("ad3030fb-e42e-47f8-a3ec-4229089aab5d".equals(item.getCheckkey())){
                if("".equals(item.getValitemval())){
                    // 跳出 提醒
                    ischeckin = false;
                    break;
                }
            }
        }

        // // 如果是5d,并且填写值了才能保存
        if(!ischeckin){
            Toast.makeText(getActivity(),"铺货状态必须填值",Toast.LENGTH_SHORT).show();
            LatteLoader.stopLoading();
            return;
        }

        // 自动计算
        Bundle bundle = new Bundle();
        if (item != null) {
            bundle.putString("proId", item.getProId());
            bundle.putString("indexId", xtProIndexValue.getIndexId());
        }

        // 计算稽查错误数
        if("N".equals(xtProIndexValue.getValchecktypeflag())&&"Y".equals(valchecktypeflag)){// 上次是N,这次是Y
            int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
            count--;
            PrefUtils.putInt(getActivity(),"valterErrorCount",count);
        }else if((!"N".equals(xtProIndexValue.getValchecktypeflag()))&&"N".equals(valchecktypeflag)){// 上次不是N,这次是N
            int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
            count++;
            PrefUtils.putInt(getActivity(),"valterErrorCount",count);
        }


        // 修改对错
        xtProIndexValue.setValchecktypeflag(valchecktypeflag);

        Message msg = new Message();
        msg.what = ZsCheckIndexFragment.INIT_INDEX_AUTO_AMEND;// 自动计算
        msg.setData(bundle);
        handler.sendMessage(msg);

        //handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_INDEX_AMEND);
        supportFragmentManager.popBackStack();
    }
}
