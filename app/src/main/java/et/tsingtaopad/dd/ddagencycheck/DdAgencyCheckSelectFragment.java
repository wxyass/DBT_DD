package et.tsingtaopad.dd.ddagencycheck;

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

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.view.dropdownmenu.DropBean;
import et.tsingtaopad.core.view.dropdownmenu.DropdownButton;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.dd.ddagencyres.DdAgencySelectService;
import et.tsingtaopad.main.visit.agencyvisit.domain.AgencySelectStc;

/**
 * 经销商库存盘点 选择经销商
 * Created by yangwenmin on 2018/3/12.
 */

public class DdAgencyCheckSelectFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdAgencyCheckSelectFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    MyHandler handler;

    public static final int DD_AGENCY_CHECKSELECT_SUC = 2401;//
    public static final int DD_AGENCY_CHECKSELECT_FAIL = 2402;//


    private DropdownButton areaBtn;
    private ListView agencyLv;

    private DdAgencySelectService service;
    private List<DropBean> areaList;

    private List<AgencySelectStc> selectLst; //可选择的经销商集合

    private AgencySelectStc agencySelectStc; //选择的经销商

    DdAgencyCheckSelectAdapter agencyAdapter; // 经销商适配器

    private String secondAreaid;// 二级区域ID


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_agencycheckselect, container, false);
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

        areaBtn = (et.tsingtaopad.core.view.dropdownmenu.DropdownButton) view.findViewById(R.id.agency_check_ddb_area);
        agencyLv = (ListView) view.findViewById(R.id.agency_check_select_lv_list);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);

        // 初始化数据
        initData();

        initAreaData();

        setDropdownListener();

    }

    private void initData() {
        titleTv.setText("选择经销商");
        confirmTv.setText("盘点");

        service = new DdAgencySelectService(getActivity());
    }

    // 下来菜单设置数据  设置区域数据
    private void initAreaData() {
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
                    Toast.makeText(getActivity(), "请选择区域", Toast.LENGTH_SHORT).show();
                    selectLst.clear();
                    agencyAdapter.notifyDataSetChanged();
                } else {
                    // 展示经销商
                    secondAreaid = areaList.get(Postion).getKey();
                    selectLst = service.queryZsDdagencySelectLst(areaList.get(Postion).getKey());
                    agencyAdapter = new DdAgencyCheckSelectAdapter(getActivity(), selectLst, confirmBtn, "");
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

                agencySelectStc = (AgencySelectStc) confirmBtn.getTag();

                Bundle bundle = new Bundle();
                bundle.putSerializable("agencyselectstc", agencySelectStc);
                bundle.putSerializable("secondAreaid", secondAreaid);
                DdAgencyCheckContentFragment agencyCheckContentFragment = new DdAgencyCheckContentFragment();
                agencyCheckContentFragment.setArguments(bundle);
                // 跳转 经销商库存盘点 填充数据
                changeHomeFragment(agencyCheckContentFragment, "ddagencycheckcontentfragment");
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
        SoftReference<DdAgencyCheckSelectFragment> fragmentRef;

        public MyHandler(DdAgencyCheckSelectFragment fragment) {
            fragmentRef = new SoftReference<DdAgencyCheckSelectFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdAgencyCheckSelectFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case DD_AGENCY_CHECKSELECT_SUC:
                    //fragment.showAddProSuc(products, agency);
                    break;
                case DD_AGENCY_CHECKSELECT_FAIL: // 督导输入数据后
                    //fragment.showAdapter();
                    break;
            }
        }
    }

}
