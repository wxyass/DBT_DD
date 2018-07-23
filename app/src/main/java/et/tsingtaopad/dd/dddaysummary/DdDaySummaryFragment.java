package et.tsingtaopad.dd.dddaysummary;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.dd.dddaysummary.fragment.AgencyStoreFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.BaseDataFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.CmpProFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.ProCheckFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.ProPriceFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.PromotionFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.WorkPlanFragment;
import et.tsingtaopad.dd.dddaysummary.fragment.WorkSumFragment;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdDaySummaryFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdDaySummaryFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    //
    public static final int DAYSUMMARY_UP_SUC = 3201;
    //
    public static final int DAYSUMMARY_UP_FAIL = 3202;
    public static final String DDDAYSUMMARYFRAGMENT_TODAYTIME = "dddaysummaryfragmenttodaytime";
    public static final String DDDAYSUMMARYFRAGMENT_CURRENTTIME = "dddaysummaryfragmentcurrenttime";

    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<String> tabList = new ArrayList<>();
    private List<Fragment> alFragment = new ArrayList<>();

    private String currenttime;// 2011-04-11
    private String todaytime;// 2011-04-11

    private String aday;
    private Calendar calendar;
    private int yearr;
    private int month;
    private int day;
    TabAdapter tabAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_daysummary, container, false);
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
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("日工作记录");
        handler = new MyHandler(this);
        ConstValues.handler = handler;
        confirmTv.setText("日历");

        // 获取系统时间
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        currenttime = DateUtil.getDateTimeStr(7);
        todaytime = DateUtil.getDateTimeStr(7);

        PrefUtils.putString(getActivity(), DDDAYSUMMARYFRAGMENT_CURRENTTIME, currenttime);
        PrefUtils.putString(getActivity(), DDDAYSUMMARYFRAGMENT_TODAYTIME, todaytime);


        initData();
    }

    private void initData() {
        WorkPlanFragment workPlanFragment = new WorkPlanFragment();
        WorkSumFragment workSumFragment = new WorkSumFragment();
        BaseDataFragment baseDataFragment = new BaseDataFragment();
        ProCheckFragment proCheckFragment = new ProCheckFragment();
        ProPriceFragment proPriceFragment = new ProPriceFragment();
        PromotionFragment promotionFragment = new PromotionFragment();
        CmpProFragment cmpProFragment = new CmpProFragment();
        AgencyStoreFragment agencyStoreFragment = new AgencyStoreFragment();
        alFragment.clear();
        // alFragment.add(workPlanFragment);
        alFragment.add(workSumFragment);
        alFragment.add(baseDataFragment);
        alFragment.add(proCheckFragment);
        alFragment.add(proPriceFragment);
        alFragment.add(promotionFragment);
        alFragment.add(cmpProFragment);
        alFragment.add(agencyStoreFragment);

        addData();

        // tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter = new TabAdapter(getChildFragmentManager());
        //1.TabLayout和Viewpager关联
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选的时候回调
                viewpager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab未被选择的时候回调
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab重新选择的时候回调
            }
        });
        //2.ViewPager滑动关联tabLayout
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        //设置tabLayout的标签来自于PagerAdapter
        tablayout.setTabsFromPagerAdapter(tabAdapter);
        //viewpager设置适配器
        viewpager.setAdapter(tabAdapter);
    }


    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:// 确定

                if (ViewUtil.isDoubleClick(v.getId(), 2000))
                    return;
                // 日历
                //Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        yearr = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        if (dayOfMonth < 10) {
                            aday = "0" + dayOfMonth;
                        } else {
                            aday = Integer.toString(dayOfMonth);
                        }
                        currenttime = (Integer.toString(year) + "-" + String.format("%02d", monthOfYear + 1) + "-" + aday);

                        PrefUtils.putString(getActivity(), DDDAYSUMMARYFRAGMENT_CURRENTTIME, currenttime);
                        //PrefUtils.putString(getActivity(),DDDAYSUMMARYFRAGMENT_TODAYTIME,todaytime);
                        // 刚进入 获取打卡信息
                        // getSignData();
                        // viewpager.notifyAll();
                        initData();

                    }
                }, yearr, month, day);
                if (!dateDialog.isShowing()) {
                    dateDialog.show();
                }

                break;
            default:
                break;
        }
    }


    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DdDaySummaryFragment> fragmentRef;

        public MyHandler(DdDaySummaryFragment fragment) {
            fragmentRef = new SoftReference<DdDaySummaryFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DdDaySummaryFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }


            // 处理UI 变化
            switch (msg.what) {
                case DAYSUMMARY_UP_SUC://
                    fragment.shuaxinXtTermSelect(1);
                    break;
                case DAYSUMMARY_UP_FAIL://
                    fragment.shuaxinXtTermSelect(2);
                    break;

            }
        }
    }

    // 结束上传  刷新页面  0:确定上传  1上传成功  2上传失败
    private void shuaxinXtTermSelect(int upType) {

    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = alFragment.get(position);
            /*Bundle bundle=new Bundle();
            bundle.putString("text",tabList.get(position));
            fragment.setArguments(bundle);*/
            return fragment;
        }

        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }
    }

    private void addData() {
        tabList.clear();
        // tabList.add("工作计划");
        tabList.add("工作汇总");
        tabList.add("基础数据群");
        tabList.add("我品铺货数据");
        tabList.add("我品价格数据");
        tabList.add("促销活动数据");
        tabList.add("追溯竞品数据");
        tabList.add("经销商库存");

    }

}
