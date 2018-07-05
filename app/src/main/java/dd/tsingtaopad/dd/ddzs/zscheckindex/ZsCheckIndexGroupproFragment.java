package dd.tsingtaopad.dd.ddzs.zscheckindex;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.db.table.MitValgroupproMTemp;
import dd.tsingtaopad.dd.ddxt.checking.XtCheckIndexService;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 追溯查指标 产品组合  数据修改界面
 */

public class ZsCheckIndexGroupproFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsCheckIndexFragment.MyHandler handler;

    private Button sureBtn;

    //protected XtCheckIndexCalculateStc checkIndexCalculateStc;//
    protected MitValgroupproMTemp mitValgroupproMTemp;//
    private XtCheckIndexService service;

    private TextView zdzs_check_index_tv_title;
    private TextView zdzs_check_index_tv_con1;


    private LinearLayout zdzs_check_index_ll_head;
    private RelativeLayout zdzs_check_index_rl_sp;
    private TextView zdzs_check_index_rl_dd_title;
    private TextView zdzs_check_index_rl_dd_con1_sp;

    private EditText zdzs_check_index_et_report;

    public ZsCheckIndexGroupproFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsCheckIndexGroupproFragment(ZsCheckIndexFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_check_index_amend, container, false);
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


        zdzs_check_index_tv_title = (TextView) view.findViewById(R.id.zdzs_check_index_tv_title);
        zdzs_check_index_tv_con1 = (TextView) view.findViewById(R.id.zdzs_check_index_tv_con1);

        zdzs_check_index_ll_head = (LinearLayout) view.findViewById(R.id.zdzs_check_index_ll_head);
        zdzs_check_index_rl_sp = (RelativeLayout) view.findViewById(R.id.zdzs_check_index_rl_sp);
        zdzs_check_index_rl_dd_title = (TextView) view.findViewById(R.id.zdzs_check_index_rl_dd_title);
        zdzs_check_index_rl_dd_con1_sp = (TextView) view.findViewById(R.id.zdzs_check_index_rl_dd_con1_sp);

        zdzs_check_index_et_report = (EditText) view.findViewById(R.id.zdzs_check_index_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_check_index_bt_save);

        sureBtn.setOnClickListener(this);
        zdzs_check_index_rl_sp.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入正确数据");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        mitValgroupproMTemp = (MitValgroupproMTemp) bundle.getSerializable("mitValgroupproMTemp");
        service = new XtCheckIndexService(getActivity(), null);

        initData();
    }

    private void initData() {


        // 业代指标名称
        zdzs_check_index_tv_title.setText("产品组合是否达成");
        // 督导指标名称
        zdzs_check_index_rl_dd_title.setText("产品组合是否达成");

        // 业代指标值
        if ("N".equals(mitValgroupproMTemp.getValgrouppro())) {
            zdzs_check_index_tv_con1.setText("否");
        }else if("Y".equals(mitValgroupproMTemp.getValgrouppro())){
            zdzs_check_index_tv_con1.setText("是");
        }else{
            zdzs_check_index_tv_con1.setText("无数据");
        }

        // 督导备注
        zdzs_check_index_et_report.setText(mitValgroupproMTemp.getValgroupproremark());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_check_index_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }


    // 保存信息
    private void saveValue() {

        mitValgroupproMTemp.setValgroupproflag("N");// 产品组合是否达标正确与否

        // 备注
        String report = zdzs_check_index_et_report.getText().toString();
        mitValgroupproMTemp.setValgroupproremark(report);

        handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_GROUPPRO_AMEND);

    }
}
