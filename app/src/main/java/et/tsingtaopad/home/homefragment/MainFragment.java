package et.tsingtaopad.home.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.business.first.FirstFragment;
import et.tsingtaopad.business.system.SystemFragment;
import et.tsingtaopad.business.visit.VisitFragment;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.home.initadapter.MyFragmentPagerAdapter;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class MainFragment extends BaseFragmentSupport {
    public static final String TAG = "MainFragment";

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton homeRb;//
    private RadioButton visitRb;//
    // private RadioButton operationRb;//
    private RadioButton systemRb;//

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dbt, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        DbtLog.logUtils(TAG,"initView");
        /**
         * RadioGroup部分
         */
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        homeRb = (RadioButton) view.findViewById(R.id.rb_chat);
        visitRb = (RadioButton) view.findViewById(R.id.rb_contacts);
        // operationRb = (RadioButton) view.findViewById(R.id.rb_discovery);
        systemRb = (RadioButton) view.findViewById(R.id.rb_me);
        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_chat) {
                    // setCurrentItem第二个参数控制页面切换动画  true:打开/false:关闭
                    viewPager.setCurrentItem(0, false);
                } else if (checkedId == R.id.rb_contacts) {
                    viewPager.setCurrentItem(1, false);
                }
                /*else if (checkedId == R.id.rb_discovery) {
                    viewPager.setCurrentItem(2, false);
                } */
                else if (checkedId == R.id.rb_me) {
                    viewPager.setCurrentItem(2, false);
                }
            }
        });

        /**
         * ViewPager部分
         */
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        FirstFragment homeFragment = new FirstFragment();
        VisitFragment visitFragment = new VisitFragment();
        // OperationFragment operationFragment = new OperationFragment();
        SystemFragment systemFragment = new SystemFragment();
        List<Fragment> alFragment = new ArrayList<Fragment>();
        alFragment.add(homeFragment);
        alFragment.add(visitFragment);
        // alFragment.add(operationFragment);
        alFragment.add(systemFragment);

        //ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), alFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_chat);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_contacts);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_me);
                        break;
                    /*case 3:
                        radioGroup.check(R.id.rb_me); // rb_discovery
                        break;*/
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //记录用户首次点击返回键的时间
    private long firstTime=0;
    // 监听返回键  返回true: 消耗事件,不在传递
    @Override
    public boolean onBackPressed() {
        boolean isexit = false;
        long secondTime=System.currentTimeMillis();
        if(secondTime-firstTime>2000){
            Toast.makeText(getActivity(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            firstTime=secondTime;
            isexit =  true;
        }else{
            System.exit(0);
        }
        return isexit;
    }

}
