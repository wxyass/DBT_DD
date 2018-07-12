package dd.tsingtaopad.dd.ddzs.zschatvie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.db.table.MitValcmpotherMTemp;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 打招呼 录入正确数据
 */

public class ZsWjVieAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsChatvieFragment.MyHandler handler;

    private Button sureBtn;

    protected MitValcmpotherMTemp mitValcmpotherMTemp;// 追溯主表信息


    /*private RelativeLayout zdzs_sayhi_amend_rl;
    private TextView zdzs_sayhi_amend_rl_yd_title;
    private TextView zdzs_sayhi_amend_rl_con1;
    private TextView zdzs_sayhi_amend_rl_con2;
    private TextView zdzs_sayhi_amend_rl_statue;

    private LinearLayout zdzs_sayhi_amend_rl_ll_head;
    private RelativeLayout zdzs_sayhi_amend_rl_dd_head;

    private RelativeLayout zdzs_sayhi_amend_rl_dd_et;
    private TextView zdzs_sayhi_amend_rl_dd_title_et;
    private EditText zdzs_sayhi_amend_rl_dd_con1_et;
    private TextView zdzs_sayhi_amend_rl_con2_et;

    private RelativeLayout zdzs_sayhi_amend_rl_dd_sp;
    private TextView zdzs_sayhi_amend_rl_dd_title_sp;
    private TextView zdzs_sayhi_amend_rl_dd_con1_sp;// 内容1
    private EditText zdzs_sayhi_amend_rl_dd_con1_et_sp;//内容1  输入框
    //private TextView zdzs_sayhi_amend_rl_dd_con2_sp;

    private EditText zdzs_sayhi_amend_dd_et_report;*/

    private RelativeLayout zdzs_chatvie_wj_amend_rl;
    private TextView zdzs_chatvie_wj_amend_rl_yd_title;
    private TextView zdzs_chatvie_wj_amend_rl_con1;
    private TextView zdzs_chatvie_wj_amend_rl_con2;
    private TextView zdzs_chatvie_wj_amend_rl_statue;
    private EditText zdzs_chatvie_wj_amend_dd_et_report;
    private Button zdzs_chatvie_wj_amend_dd_bt_save;

    //private ZsSayhiService xtSayhiService;

    public ZsWjVieAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsWjVieAmendFragment(ZsChatvieFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_chatvie_wj, container, false);
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

        zdzs_chatvie_wj_amend_rl = (RelativeLayout) view.findViewById(R.id.zdzs_chatvie_wj_amend_rl);

        zdzs_chatvie_wj_amend_rl_yd_title = (TextView)view. findViewById(R.id.zdzs_chatvie_wj_amend_rl_yd_title);
        zdzs_chatvie_wj_amend_rl_con1 = (TextView) view.findViewById(R.id.zdzs_chatvie_wj_amend_rl_con1);
        zdzs_chatvie_wj_amend_rl_con2 = (TextView)view. findViewById(R.id.zdzs_chatvie_wj_amend_rl_con2);

        zdzs_chatvie_wj_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_chatvie_wj_amend_dd_et_report);

        sureBtn = (Button) view.findViewById(R.id.zdzs_chatvie_wj_amend_dd_bt_save);

        sureBtn.setOnClickListener(this);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入正确数据");

        //xtSayhiService = new ZsSayhiService(getActivity(), handler);

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        mitValcmpotherMTemp = (MitValcmpotherMTemp) bundle.getSerializable("mitValcmpotherMTemp");

        initData();

    }

    private void initData() {
        // 标题
        zdzs_chatvie_wj_amend_rl_yd_title.setText("是否瓦解竞品");

        // 是否瓦解竞品
        if (ConstValues.FLAG_1.equals(mitValcmpotherMTemp.getValistruecmpval())) {
            zdzs_chatvie_wj_amend_rl_con2.setText("是");
        } else {
            zdzs_chatvie_wj_amend_rl_con2.setText("否");
        }

        zdzs_chatvie_wj_amend_dd_et_report.setText(mitValcmpotherMTemp.getValiscmpremark());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_chatvie_wj_amend_dd_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    // 确定保存
    private void saveValue() {

        // 是否成功瓦解竞品正确与否
        mitValcmpotherMTemp.setValistrueflag("N");

        // 保存是否正确,备注内容
        String remark = zdzs_chatvie_wj_amend_dd_et_report.getText().toString();
        mitValcmpotherMTemp.setValiscmpremark(remark);

        handler.sendEmptyMessage(ZsChatvieFragment.INIT_WJ_AMEND);

    }
}
