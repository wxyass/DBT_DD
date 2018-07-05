package dd.tsingtaopad.dd.ddweekplan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.db.table.MitPlanweekM;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddweekplan.domain.DayPlanStc;
import dd.tsingtaopad.listviewintf.ILongClick;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class DdDayPlanFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "DdDayPlanFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private Button addTv;
    private Button submitTv;
    private TextView desTv;
    private ListView planLv;
    private DayDetailAdapter detailAdapter;
    private MitPlanweekM weekplan;
    private WeekPlanService service;
    private String weekDateStart;
    private String weekDateEnd;
    private DayPlanStc dayplanstc;

    DdWeekPlanFragment.MyHandler handler;

    //
    public static final int DAYPLAN_UP_SUC = 310001;
    //
    public static final int DAYPLAN_UP_FAIL = 310002;

    List<DayDetailStc> dayDetailStcs = new ArrayList<DayDetailStc>();

    public DdDayPlanFragment() {
    }

    @SuppressLint("ValidFragment")
    public DdDayPlanFragment(DdWeekPlanFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_dayplan, container, false);
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

        addTv = (Button) view.findViewById(R.id.dd_dayplan_bt_add);
        submitTv = (Button) view.findViewById(R.id.dd_dayplan_bt_submit);
        planLv = (ListView) view.findViewById(R.id.dd_dayplan_lv_details);
        desTv = (TextView) view.findViewById(R.id.dd_dayplan_tv_des);
        addTv.setOnClickListener(this);
        submitTv.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("制定计划");

        service = new WeekPlanService(getActivity());

        initData();
    }

    private void initData() {

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        dayplanstc = (DayPlanStc) bundle.getSerializable("dayplanstc");
        weekplan = (MitPlanweekM) bundle.getSerializable("weekplan");
        weekDateStart = (String) bundle.getSerializable("weekDateStart");
        weekDateEnd = (String) bundle.getSerializable("weekDateEnd");

        /*DayDetailStc detailStc = new DayDetailStc();*/
        // dayDetailStcs.add((DayDetailStc) dayplanstc.getDetailStcs());
        dayDetailStcs.addAll(dayplanstc.getDetailStcs());
        detailAdapter = new DayDetailAdapter(getActivity(), dayDetailStcs,weekplan, new ILongClick() {
            @Override
            public void listViewItemLongClick(int position, View v) {
                dayDetailStcs.remove(position);
                Toast.makeText(getActivity(), "删除" + position + "项", Toast.LENGTH_SHORT).show();
                detailAdapter.notifyDataSetChanged();
            }
        });
        planLv.setAdapter(detailAdapter);

        if ("2".equals(dayplanstc.getState()) || "3".equals(dayplanstc.getState())) {// 0未制定1未提交2待审核3审核通过4未通过
            addTv.setVisibility(View.INVISIBLE);
            submitTv.setVisibility(View.INVISIBLE);

            if (dayDetailStcs.size() > 0) {// 日计划详情个数 大于0
                desTv.setVisibility(View.INVISIBLE);
            } else {
                desTv.setVisibility(View.VISIBLE);
            }

        } else {
            addTv.setVisibility(View.VISIBLE);
            submitTv.setVisibility(View.VISIBLE);
            desTv.setVisibility(View.INVISIBLE);
        }

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

                // Toast.makeText(getActivity(), "弹出日历", Toast.LENGTH_SHORT).show();

                break;
            case R.id.dd_dayplan_bt_add:// 新增计划
                DayDetailStc detailStc = new DayDetailStc();
                List<String> checknames = new ArrayList<>();
                detailStc.setValchecknameLv(checknames);
                dayDetailStcs.add(detailStc);
                detailAdapter.notifyDataSetChanged();
                break;
            case R.id.dd_dayplan_bt_submit:// 保存计划
                saveDayDetailPlan();
                // Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }

    // 保存每日的计划
    private void saveDayDetailPlan() {
        if (checkdayDetailStcs(dayDetailStcs)) {
            service.saveTable(weekplan, dayplanstc, dayDetailStcs, weekDateStart, weekDateEnd);
            //handler.sendEmptyMessage(ConstValues.WAIT0);
            ConstValues.handler.sendEmptyMessage(ConstValues.WAIT0);
            supportFragmentManager.popBackStack();
        } else {
            Toast.makeText(getActivity(), "各项输入不完整", Toast.LENGTH_SHORT).show();
        }
    }

    // 保存前的检测
    private boolean checkdayDetailStcs(List<DayDetailStc> dayDetailStcs) {
        boolean ischeck = true;
        for (DayDetailStc detailStc : dayDetailStcs) {
            //if (detailStc.getValchecknameLv().size()<=0||detailStc.getValchecknameLv()==null
            if ("".equals(detailStc.getValcheckkey()) || detailStc.getValcheckkey() == null
                    || "".equals(detailStc.getValareakey()) || detailStc.getValareakey() == null
                    || "".equals(detailStc.getValgridkey()) || detailStc.getValgridkey() == null
                    || "".equals(detailStc.getValroutekeys()) || detailStc.getValroutekeys() == null) {
                ischeck = false;
                break;
            }
        }
        return ischeck;
    }


}
