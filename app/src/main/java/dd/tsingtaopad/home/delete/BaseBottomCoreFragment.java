package dd.tsingtaopad.home.delete;

import java.util.HashMap;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.base.BaseCoreFragment;
import dd.tsingtaopad.home.initadapter.GlobalValues;

/**
 * Created by yangwenmin on 2017/12/11.
 */

public class BaseBottomCoreFragment extends BaseCoreFragment {

    public HashMap<String, Integer> authorityMap;

    public void initAuthority() {
        // 初始化按钮图片对应
        authorityMap = new HashMap<String, Integer>();
        // 初始化按钮图片对应
        authorityMap.put("1000000", R.drawable.bt_visit_shopvist);// 巡店拜访
        authorityMap.put("1000001", R.drawable.bt_visit_addterm);// 新增终端
        authorityMap.put("1000002", R.drawable.bt_visit_termdetail);// 终端进货明细
        authorityMap.put("1000003", R.drawable.bt_visit_agency);// 经销商拜访
        authorityMap.put("1000004", R.drawable.bt_visit_store);// 经销商库存
        authorityMap.put("1000005", R.drawable.bt_visit_agencykf);// 经销商开发
        authorityMap.put("1000006", R.drawable.bt_visit_sync);// 数据同步
        authorityMap.put("1000007", R.drawable.bt_visit_termtaizhang);// 终端进货台账录入
        authorityMap.put("1000008", R.drawable.bt_visit_showpro);// 产品展示
        authorityMap.put("1000009", R.drawable.bt_visit_addterm_other);// 其它

        authorityMap.put("1000010", R.drawable.bt_operation_workplan);// 日/周工作计划
        authorityMap.put("1000011", R.drawable.bt_operation_workdetail);// 日工作推进(标准)
        authorityMap.put("1000012", R.drawable.bt_operation_workweek);// 周工作总结
        authorityMap.put("1000013", R.drawable.bt_visit_work);// 日工作记录查询
        authorityMap.put("1000014", R.drawable.bt_operation_omnipotent);// 万能铺货率查询
        authorityMap.put("1000015", R.drawable.bt_operation_indexsearch);// 指标状态查询
        authorityMap.put("1000016", R.drawable.bt_operation_promotion);// 促销活动查询
        authorityMap.put("1000017", R.drawable.bt_operation_workdetailsd);// 日工作推进(山东) 不用了

        authorityMap.put("1000018", R.drawable.bt_business_notice);// 通知公告
        authorityMap.put("1000019", R.drawable.bt_business_question);// 问题反馈
        authorityMap.put("1000020", R.drawable.bt_syssetting_update);// 检查更新
        authorityMap.put("1000021", R.drawable.bt_syssetting_modify_pwd);// 修改密码
        authorityMap.put("1000022", R.drawable.bt_syssetting_info);// 关于系统

        authorityMap.put("1000023", R.drawable.bt_operation_dingdan);// 当日订单查询
        authorityMap.put("1000024", R.drawable.bt_operation_load);// 路线卡查询
        authorityMap.put("1000025", R.drawable.bt_operation_taizhang);// 终端进货台账查询
        GlobalValues.AuthorityMap = authorityMap;

    }

}
