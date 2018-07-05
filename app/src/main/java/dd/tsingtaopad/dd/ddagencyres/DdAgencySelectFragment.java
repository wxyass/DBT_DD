package dd.tsingtaopad.dd.ddagencyres;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.view.dropdownmenu.DropBean;
import dd.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import dd.tsingtaopad.db.table.MstAgencyKFM;
import dd.tsingtaopad.db.table.MstMarketareaM;

/**
 * 经销商资料库 选择经销商
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAgencySelectFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAgencySelectFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_AGENCY_SELECT_SUC = 2301;//
    public static final int DD_AGENCY_SELECT_FAIL = 2302;//

    private DropdownButton areaBtn;
    private ListView agencyLv;
    private List<DropBean> areaList;

    private DdAgencySelectService service;

    private ArrayList<MstAgencyKFM> kFMLst;// 经销商集合
    private MstAgencyKFM mstAgencyKFM;// 选中的经销商


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_agencyselect, container, false);
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
        // confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        areaBtn = (DropdownButton) view.findViewById(R.id.agency_select_ddb_area);
        agencyLv = (ListView) view.findViewById(R.id.agency_select_lv_list);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

        // 下拉按钮的点击监听
        setDropdownListener();
    }

    private void initData() {
        titleTv.setText("选择经销商");
        confirmTv.setText("查询");

        service = new DdAgencySelectService(getActivity());

        // 初始化区域  下拉菜单设置数据  设置区域数据
        areaList = new ArrayList<>();
        areaList.add(new DropBean("请选择区域"));
        String bigAreaId = PrefUtils.getString(getActivity(), "departmentid", "");
        List<MstMarketareaM> valueLst = service.getMstMarketareaMList(bigAreaId);
        for (MstMarketareaM mstMarketareaM : valueLst) {
            areaList.add(new DropBean(mstMarketareaM.getAreaname(), mstMarketareaM.getAreaid()));
        }
    }

    // 下拉按钮的点击监听
    private void setDropdownListener() {

        areaBtn.setData(areaList);

        areaBtn.setText("选择区域");

        // 区域选择
        areaBtn.setOnDropItemSelectListener(new DropdownButton.OnDropItemSelectListener() {
            @Override
            public void onDropItemSelect(int Postion) {
                //Toast.makeText(getContext(), "您选择了 " + areaList.get(Postion).getName(), Toast.LENGTH_SHORT).show();
                if (Postion == 0) {
                    Toast.makeText(getActivity(), "清空当前界面的经销商", Toast.LENGTH_SHORT).show();
                } else {
                    // 展示经销商
                    kFMLst = service.queryDdMstAgencyKFMAll(areaList.get(Postion).getKey());
                    DdAgencySelectAdapter agencyAdapter = new DdAgencySelectAdapter(getActivity(), kFMLst, confirmBtn, "");
                    agencyLv.setAdapter(agencyAdapter);
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定

                mstAgencyKFM = (MstAgencyKFM) confirmBtn.getTag();
                Bundle bundle = new Bundle();
                bundle.putSerializable("mstagencykfm", mstAgencyKFM);
                DdAgencyContentFragment agencyContentFragment = new DdAgencyContentFragment();
                agencyContentFragment.setArguments(bundle);
                // 跳转 经销商库存盘点 填充数据
                changeHomeFragment(agencyContentFragment, "ddagencycontentfragment");
                break;

            default:
                break;
        }
    }

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdAgencySelectFragment> fragmentRef;

        public MyHandler(DdAgencySelectFragment fragment) {
            fragmentRef = new SoftReference<DdAgencySelectFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdAgencySelectFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_AGENCY_SELECT_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_AGENCY_SELECT_FAIL: // 督导输入数据后
                    //fragment.showAdapter();
                    break;
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");

        // 保存追溯 进销存数据  MitValsupplyMTemp
        //invoicingService.saveZsInvoicing(dataLst);
    }

}
