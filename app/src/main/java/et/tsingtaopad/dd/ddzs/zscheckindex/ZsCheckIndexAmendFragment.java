package et.tsingtaopad.dd.ddzs.zscheckindex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.adapter.AlertKeyValueAdapter;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.view.alertview.AlertView;
import et.tsingtaopad.dd.ddxt.checking.XtCheckIndexService;
import et.tsingtaopad.dd.ddxt.checking.domain.XtCheckIndexCalculateStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 追溯查指标 合作是否执行到位 是否高质量配送 占有率  数据修改界面
 */

public class ZsCheckIndexAmendFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    ZsCheckIndexFragment.MyHandler handler;

    private Button sureBtn;

    protected XtCheckIndexCalculateStc checkIndexCalculateStc;//
    private XtCheckIndexService service;

    private TextView zdzs_check_index_tv_title;
    private TextView zdzs_check_index_tv_con1;


    private LinearLayout zdzs_check_index_ll_head;
    private RelativeLayout zdzs_check_index_rl_sp;
    private TextView zdzs_check_index_rl_dd_title;
    private TextView zdzs_check_index_rl_dd_con1_sp;

    private EditText zdzs_check_index_et_report;

    public ZsCheckIndexAmendFragment() {

    }

    @SuppressLint("ValidFragment")
    public ZsCheckIndexAmendFragment(ZsCheckIndexFragment.MyHandler handler) {
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
        checkIndexCalculateStc = (XtCheckIndexCalculateStc) bundle.getSerializable("checkIndexCalculateStc");

        service = new XtCheckIndexService(getActivity(), null);

        initData();
    }

    private void initData() {


        // 业代指标名称
        zdzs_check_index_tv_title.setText(checkIndexCalculateStc.getIndexName());
        // 督导指标名称
        zdzs_check_index_rl_dd_title.setText(checkIndexCalculateStc.getIndexName());

        // 业代指标值
        if ("666b74b3-b221-4920-b549-d9ec39a463fd".equals(checkIndexCalculateStc.getIndexId())) {// 合作是否到位
            //tempLst = service.queryNoProIndexValueId1();// 此处报错
            if ("9019cf03-4572-4559-9971-48a27a611c3d".equals(checkIndexCalculateStc.getIndexValueId())) {// 合作是否到位 是
                zdzs_check_index_tv_con1.setText("是");
            }else{
                zdzs_check_index_tv_con1.setText("否");
            }
        } else if ("df2e88c9-246f-40e2-b6e5-08cdebf8c281".equals(checkIndexCalculateStc.getIndexId())) {// 是否高质量配送
            //tempLst = service.queryNoProIndexValueId2();
            if ("460647a9-283a-44ea-b11f-42efe1fd62e4".equals(checkIndexCalculateStc.getIndexValueId())) {// 是否高质量配送
                zdzs_check_index_tv_con1.setText("是");
            }else{
                zdzs_check_index_tv_con1.setText("否");
            }
        } else if ("59802090-02ac-4146-9cc3-f09570c36a26".equals(checkIndexCalculateStc.getIndexId())) {// 我品占有率
            zdzs_check_index_tv_con1.setText(service.getCheckStatusName(checkIndexCalculateStc.getIndexValueId()));
            // 督导数据
            zdzs_check_index_ll_head.setVisibility(View.VISIBLE);
            zhanyoulvIndexValueId = checkIndexCalculateStc.getDdacresult();
            zdzs_check_index_rl_dd_con1_sp.setText(service.getCheckStatusName(zhanyoulvIndexValueId));
        }


        // 督导备注
        zdzs_check_index_et_report.setText(checkIndexCalculateStc.getDdremark());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_tv_title:// 标题
                break;
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();// 取消
                break;
            case R.id.zdzs_check_index_rl_sp:// 占有率选择
                alertShowzyl();
                break;


            case R.id.zdzs_check_index_bt_save:// 确定
                saveValue();
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }


    private void saveValue() {

        checkIndexCalculateStc.setValchecktypeflag("N");//

        // 我品占有率
        if ("59802090-02ac-4146-9cc3-f09570c36a26".equals(checkIndexCalculateStc.getIndexId())) {// 我品占有率
            // 督导占有率 数据
            checkIndexCalculateStc.setDdacresult(zhanyoulvIndexValueId);
        }

        // 备注
        String report = zdzs_check_index_et_report.getText().toString();
        checkIndexCalculateStc.setDdremark(report);

        // 当稽查选择错误时
        int count = PrefUtils.getInt(getActivity(),"valterErrorCount",0);
        count++;
        PrefUtils.putInt(getActivity(),"valterErrorCount",count);


        handler.sendEmptyMessage(ZsCheckIndexFragment.INIT_HPZ_AMEND);

    }

    private AlertView mAlertViewExt;//窗口拓展例子
    String zhanyoulvIndexValueId;//当前终端单店占有率 对应cstatuskey
    public void alertShowzyl() {
        final List<KvStc> tempLst = service.queryNoProIndexValueId31();
        mAlertViewExt = new AlertView("请选择单店占有率", null,
                null, null, null, getActivity(), AlertView.Style.ActionSheet, null);
        ViewGroup areaextView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alert_list_form, null);
        ListView arealistview = (ListView) areaextView.findViewById(R.id.alert_list);
        AlertKeyValueAdapter areakeyValueAdapter = new AlertKeyValueAdapter(getActivity(), tempLst,
                new String[]{"key", "value"}, zhanyoulvIndexValueId);
        arealistview.setAdapter(areakeyValueAdapter);
        arealistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //xtZhanyoulvTv.setText(tempLst.get(position).getValue());
                zhanyoulvIndexValueId = tempLst.get(position).getKey();
                zdzs_check_index_rl_dd_con1_sp.setText(service.getCheckStatusName(zhanyoulvIndexValueId));
                mAlertViewExt.dismiss();
            }
        });
        mAlertViewExt.addExtView(areaextView);
        mAlertViewExt.setCancelable(true).setOnDismissListener(null);
        mAlertViewExt.show();
    }

    // 监听返回键
    @Override
    public boolean onBackPressed() {
        if (mAlertViewExt != null && mAlertViewExt.isShowing()) {
            mAlertViewExt.dismiss();
            return true;
        } else {
            return super.onBackPressed();
        }
    }
}
