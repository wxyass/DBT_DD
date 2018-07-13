package et.tsingtaopad.dd.ddzs.zscheckindex;

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

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsCheckPromoAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsCheckIndexFragment.MyHandler handler;

    private Button sureBtn;


    protected int position;// 页面类型,比如: "1"
    protected String termId = "";// 终端id
    protected String mitValterMTempKey = "";// 追溯主表临时表key
    protected List<CheckIndexPromotionStc> dataLst;//

    private TextView zdzs_check_promo_tv_title;
    private TextView zdzs_check_promo_tv_con1;

    private TextView zdzs_check_promo_et_title;
    private EditText zdzs_check_promo_et_con1;

    private EditText zdzs_check_promo_et_report;

    private CheckIndexPromotionStc checkIndexPromotionStc;

    public ZsCheckPromoAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsCheckPromoAmendFragment(ZsCheckIndexFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_check_promo_amend, container, false);
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


        zdzs_check_promo_tv_title = (TextView) view.findViewById(R.id.zdzs_check_promo_tv_title);
        zdzs_check_promo_tv_con1 = (TextView) view.findViewById(R.id.zdzs_check_promo_tv_con1);
        zdzs_check_promo_et_title = (TextView) view.findViewById(R.id.zdzs_check_promo_et_title);
        zdzs_check_promo_et_con1 = (EditText) view.findViewById(R.id.zdzs_check_promo_et_con1);
        zdzs_check_promo_et_report = (EditText) view.findViewById(R.id.zdzs_check_promo_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_check_promo_bt_save);

        sureBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入正确数据");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        termId = bundle.getString("termId");
        mitValterMTempKey = bundle.getString("mitValterMTempKey");//追溯主键
        dataLst = (List<CheckIndexPromotionStc>) bundle.getSerializable("dataLst");

        initData();
    }

    private void initData() {

        checkIndexPromotionStc = dataLst.get(position);
        // 业代达成组数
        zdzs_check_promo_tv_con1.setText(checkIndexPromotionStc.getReachNum());
        // 督导达成组数
        zdzs_check_promo_et_con1.setText(checkIndexPromotionStc.getValistruenumval());
        // 督导备注
        //zdzs_check_promo_et_report.setText(checkIndexPromotionStc.getValagencysupplyremark());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;


            case R.id.zdzs_check_promo_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    private void saveValue() {

        checkIndexPromotionStc.setValistruenumflag("N");// 达成组数正确与否

        // 督导达成组数
        String qdinfo = zdzs_check_promo_et_con1.getText().toString();
        checkIndexPromotionStc.setValistruenumval(qdinfo);

        // 备注
        String report = zdzs_check_promo_et_report.getText().toString();
        //checkIndexPromotionStc.setValagencysupplyremark(report);

        handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_AMEND);

    }
}
