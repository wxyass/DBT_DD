package dd.tsingtaopad.dd.ddzs.zsagree;

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

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.db.table.MitValagreeMTemp;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 进销存 录入正确数据
 */

public class ZsAgreeAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsAgreeFragment.MyHandler handler;

    private Button sureBtn;


    protected int type;// 类型:

    private EditText zdzs_agree_amend_dd_et_report;

    private MitValagreeMTemp mitValagreeMTemp;

    public ZsAgreeAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsAgreeAmendFragment(ZsAgreeFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zdzs_agree_amend, container, false);
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

        zdzs_agree_amend_dd_et_report = (EditText) view.findViewById(R.id.zdzs_agree_amend_dd_et_report);
        sureBtn = (Button) view.findViewById(R.id.zdzs_agree_amend_dd_bt_save);
        sureBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("录入数据");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        mitValagreeMTemp = (MitValagreeMTemp) bundle.getSerializable("mitValagreeMTemp");

        initData();
    }

    private void initData() {
        if (type == ZsAgreeFragment.AGREE_STARTDATE) {// 开始时间
            zdzs_agree_amend_dd_et_report.setText(mitValagreeMTemp.getStartdateremark());// 备注
        } else if (type == ZsAgreeFragment.AGREE_ENDDATE) {//结束时间
            zdzs_agree_amend_dd_et_report.setText(mitValagreeMTemp.getEnddateremark());// 备注
        } else if (type == ZsAgreeFragment.AGREE_CONTENT) {// 主要协议
            zdzs_agree_amend_dd_et_report.setText(mitValagreeMTemp.getNotesremark());// 备注
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;

            case R.id.zdzs_agree_amend_dd_bt_save:// 确定
                saveValue();

                break;

            default:
                break;
        }
    }

    private void saveValue() {

        // 备注
        String report = zdzs_agree_amend_dd_et_report.getText().toString();

        if (type == ZsAgreeFragment.AGREE_STARTDATE) {// 开始时间
            mitValagreeMTemp.setStartdateremark(report);
            mitValagreeMTemp.setStartdateflag("N");
        } else if (type == ZsAgreeFragment.AGREE_ENDDATE) {//结束时间
            mitValagreeMTemp.setEnddateremark(report);
            mitValagreeMTemp.setEnddateflag("N");
        } else if (type == ZsAgreeFragment.AGREE_CONTENT) {// 主要协议
            mitValagreeMTemp.setNotesremark(report);
            mitValagreeMTemp.setNotesflag("N");
        }

        handler.sendEmptyMessage(ZsAgreeFragment.AGREE_AMEND_SUC);

        supportFragmentManager.popBackStack();
    }

}
