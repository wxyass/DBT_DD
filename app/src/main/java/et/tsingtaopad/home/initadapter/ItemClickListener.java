package et.tsingtaopad.home.initadapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import et.tsingtaopad.R;
import et.tsingtaopad.core.base.BaseCoreFragment;
import et.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;

/**
 * Created by yangwenmin on 2017/11/25.
 */

public class ItemClickListener implements AdapterView.OnItemClickListener {


    private final BaseCoreFragment DELEGATE;
    private ArrayList<Integer> images;

    // 构造
    public ItemClickListener(BaseCoreFragment delegate, ArrayList<Integer> images) {
        this.DELEGATE = delegate;
        this.images = images;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long rowid) {
        Integer i = images.get(position);
        // 巡店拜访
        if (i.equals(R.drawable.bt_visit_shopvist)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(LineListCoreFragment.newInstance());
            // 新增终端
        } else if (i.equals(R.drawable.bt_visit_addterm)) {

            // 终端进货明细
        } else if (i.equals(R.drawable.bt_visit_termdetail)) {

            // 经销商拜访
        } else if (i.equals(R.drawable.bt_visit_agency)) {

            // 经销商库存
        } else if (i.equals(R.drawable.bt_visit_store)) {
            // ((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(AgencyStorageCoreFragment.newInstance());
            // 经销商开发
        } else if (i.equals(R.drawable.bt_visit_agencykf)) {

            // 手动同步
        } else if (i.equals(R.drawable.bt_visit_sync)) {
            syncData();

            // 终端进货台账
        } else if (i.equals(R.drawable.bt_visit_termtaizhang)) {

            // 产品展示
        } else if (i.equals(R.drawable.bt_visit_showpro)) {

            // 其它
        } else if (i.equals(R.drawable.bt_visit_addterm_other)) {


            // 日/周工作计划
        } else if (i.equals(R.drawable.bt_operation_workplan)) {

            // 订单详细
        } else if (i.equals(R.drawable.bt_operation_dingdan)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(OrdersSearchCoreFragment.newInstance());

            // 日工作推进(标准)
        } else if (i.equals(R.drawable.bt_operation_workdetail)) {

            // 周工作总结
        } else if (i.equals(R.drawable.bt_operation_workdetail)) {

            // 日工作记录
        } else if (i.equals(R.drawable.bt_visit_work)) {

            // 万能铺货率查询
        } else if (i.equals(R.drawable.bt_operation_omnipotent)) {

            // 指标状态查询
        } else if (i.equals(R.drawable.bt_operation_indexsearch)) {

            // 促销活动查询
        } else if (i.equals(R.drawable.bt_operation_promotion)) {

            // 日工作推进(山东)
        } else if (i.equals(R.drawable.bt_operation_workdetailsd)) {


            // 通知公告
        } else if (i.equals(R.drawable.bt_business_notice)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(NoticeCoreFragment.newInstance());

            // 问题反馈
        } else if (i.equals(R.drawable.bt_business_question)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(QueryFeedbackCoreFragment.newInstance());
            // 检查更新
        } else if (i.equals(R.drawable.bt_syssetting_update)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(UpdateCoreFragment.newInstance());
            // 修改密码
        } else if (i.equals(R.drawable.bt_syssetting_modify_pwd)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(ModifyPwdCoreFragment.newInstance());
            // 关于系统
        } else if (i.equals(R.drawable.bt_syssetting_info)) {
            //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragment(AboutInfoCoreFragment.newInstance());

            // 扫描二维码
        } else {

        }
    }

    /**
     * 同步数据
     */
    private void syncData() {
        // 如果网络可用
        if (NetStatusUtil.isNetValid(DELEGATE.getContext())) {
            // 根据后台标识   "0":需清除数据 ,"1":不需清除数据,直接同步
            if ("0".equals(PrefUtils.getString(DELEGATE.getContext(),"isdel",""))) {//IsDel
                //弹窗是否删除之前所有数据
                showNotifyDialog();
            } else {
                //((MainCoreFragment) DELEGATE.getParentFragment()).startBrotherFragmentDontHideSelf(DownLoadCoreFragment.newInstance());
            }
        } else {
            // 提示修改网络
            AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
            builder.setTitle("网络错误");
            builder.setMessage("请连接好网络再同步数据");
            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DELEGATE.startActivityForResult(
                                            new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS),
                                            0);
                        }
                    }).create().show();
            builder.setCancelable(false); // 是否可以通过返回键 关闭
        }
    }

    private AlertDialog dialog1;
    /**
     * 弹窗显示需清除数据
     *
     */
    public void showNotifyDialog(){

        //提示删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setTitle("初始化");
        builder.setMessage("初次登陆，您的账号需要初始化。");
        builder.setCancelable(false);
        //builder.setCanceledOnTouchOutside(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                /*// 缓冲界面
                dialog1 = new DialogUtil().progressDialog(DELEGATE.getContext(), R.string.dialog_msg_delete);
                dialog1.setCancelable(false);
                dialog1.setCanceledOnTouchOutside(false);
                dialog1.show();


                // 网络请求 传递参数
                HttpUtil httpUtil = new HttpUtil(60*1000);
                httpUtil.configResponseTextCharset("ISO-8859-1");

                //
                StringBuffer buffer = new StringBuffer();
                //buffer.append("{userid:'").append(ConstValues.loginSession.getUserGongHao());
                buffer.append("{userid:'").append(PrefUtils.getString(getActivity(), "userGongHao", ""));
                buffer.append("', isdel:'").append("1").append("'}");

                // qingqiu
                httpUtil.send("opt_get_status", buffer.toString(),new RequestCallBack<String>() {
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        ResponseStructBean resObj = HttpUtil.parseRes(responseInfo.result);
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            // 删除数据库表数据 然后直接同步
                            // new DeleteTools().deleteDatabase(getActivity());

                            // 删除数据库表数据 然后重启
                            new DeleteTools().deleteDatabaseAll(getActivity());

                            // 删除缓存数据
                            new DataCleanManager().cleanSharedPreference(getActivity());

                            // 删除bug文件夹
                            String bugPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dbt/et.tsingtaopad" + "/bug/" ;
                            FileUtil.deleteFile(new File(bugPath));

                            // 删除log文件夹
                            String logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dbt/et.tsingtaopad" + "/log/" ;
                            FileUtil.deleteFile(new File(logPath));

                            // 重新启动本应用
                            restartApplication();
                            android.os.Process.killProcess(android.os.Process.myPid());

                            // 关闭缓冲界面
                            Message message = new Message();
                            message.what = ConstValues.WAIT2;
                            handler.sendMessage(message);
                        }
                    }

                    @Override
                    public void onFailure(HttpException error,
                                          String msg) {

                    }
                });*/
            }
        }).create().show();

        //builder.setCancelable(false); // 是否可以通过返回键 关闭

        // 直接show();
        //builder.show();
    }
}
