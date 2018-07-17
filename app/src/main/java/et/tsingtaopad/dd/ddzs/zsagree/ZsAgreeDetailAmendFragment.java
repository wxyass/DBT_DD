package et.tsingtaopad.dd.ddzs.zsagree;

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
import android.widget.Toast;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.db.table.MitValagreedetailMTemp;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsAgreeDetailAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsAgreeFragment.MyHandler handler;

    private Button sureBtn;


    protected int type;// 类型:

    private EditText zdzs_agreedetail_amend_dd_et_report;

    private MitValagreedetailMTemp mitValagreedetailMTemp;


    private TextView tv_product;
    private TextView tv_price ;
    private TextView tv_num ;
    private EditText et_product;
    private EditText et_price ;
    private EditText et_num;

    public ZsAgreeDetailAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsAgreeDetailAmendFragment(ZsAgreeFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_agreedetail_amend, container, false);
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

        tv_product = (TextView) view.findViewById(R.id.zdzs_agreedetail_amend_tv_product);
        tv_price = (TextView) view.findViewById(R.id.zdzs_agreedetail_amend_tv_price);
        tv_num = (TextView) view.findViewById(R.id.zdzs_agreedetail_amend_tv_num);
        et_product = (EditText) view.findViewById(R.id.zdzs_agreedetail_amend_dd_et_product);
        et_price = (EditText) view.findViewById(R.id.zdzs_agreedetail_amend_dd_et_price);
        et_num = (EditText) view.findViewById(R.id.zdzs_agreedetail_amend_dd_et_num);
        zdzs_agreedetail_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_agreedetail_amend_dd_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_agreedetail_amend_dd_bt_save);
        sureBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入数据");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        mitValagreedetailMTemp = (MitValagreedetailMTemp) bundle.getSerializable("MitValagreedetailMTemp");
        initData();
    }

    private void initData() {
        tv_product.setText(mitValagreedetailMTemp.getProname());
        tv_price.setText("¥ "+mitValagreedetailMTemp.getCommoney());
        tv_num.setText(mitValagreedetailMTemp.getTrunnum());
        et_product.setText(mitValagreedetailMTemp.getTruepro());
        et_price.setText(mitValagreedetailMTemp.getTruemoney());
        et_num.setText(mitValagreedetailMTemp.getTruenum());
        zdzs_agreedetail_amend_dd_et_report.setText(mitValagreedetailMTemp.getRemarks());// 备注
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_agreedetail_amend_dd_bt_save:// 确定
                saveValue();

                break;

            default:
                break;
        }
    }

    private void saveValue() {



        // 备注
        String product = et_product.getText().toString();
        String price = et_price.getText().toString();
        String num = et_num.getText().toString();
        String report = zdzs_agreedetail_amend_dd_et_report.getText().toString();

        // 检测3项中 必须填写一项
        if(checkAgree(product,price,num)){

            /*if (type == 1) {// 1 品种有误
                mitValagreedetailMTemp.setErroritem("0");
                mitValagreedetailMTemp.setAgreedetailflag("N");
            } else if (type == 2) {//2 承担金额有误
                mitValagreedetailMTemp.setErroritem("1");
                mitValagreedetailMTemp.setAgreedetailflag("N");
            } else if (type == 3) {// 3 实际数量有误
                mitValagreedetailMTemp.setErroritem("2");
                mitValagreedetailMTemp.setAgreedetailflag("N");
            }*/

            mitValagreedetailMTemp.setAgreedetailflag("N");

            mitValagreedetailMTemp.setTruepro(product);
            mitValagreedetailMTemp.setTruemoney(price);
            mitValagreedetailMTemp.setTruenum(num);
            mitValagreedetailMTemp.setRemarks(report);

            // 当稽查选择错误时
            int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
            count++;
            PrefUtils.putInt(getActivity(),"valterErrorCount",count);

            handler.sendEmptyMessage(ZsAgreeFragment.AGREE_AMEND_DETAIL);

            supportFragmentManager.popBackStack();
        }else{
            Toast.makeText(getActivity(),"有误数据必须填写一项",Toast.LENGTH_SHORT).show();
        }

    }

    // 检测3项是否填值了
    private boolean checkAgree(String product,String price,String num) {
        boolean isin = true;
        if("".equals(product)&&"".equals(price)&&"".equals(num)){
            isin = false;
        }
        return isin;
    }

}
