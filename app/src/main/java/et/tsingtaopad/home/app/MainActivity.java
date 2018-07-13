package et.tsingtaopad.home.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.ref.SoftReference;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseActivity;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.business.system.version.DownApkFragment;
import et.tsingtaopad.business.system.version.VersionService;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.fragmentback.HandleBackUtil;
import et.tsingtaopad.home.homefragment.MainFragment;
import et.tsingtaopad.home.initadapter.GlobalValues;

public class MainActivity extends BaseActivity {

    /*String apkUrl = "http://oss.wxyass.com/tscs2.4.3.1.0.apk";
    String apkName = "tscs2.4.3.1.0.apk";
    String downPath = "dbt";// apk的存放位置*/
    private VersionService versionService;
    private String departmentid;// 大区id
    private String userid;// 用户id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 应用打开时,创建或更新数据库
        // new DatabaseHelper(this).getWritableDatabase();

        // 测试SharedPreferences
        PrefUtils.putString(getApplicationContext(), "ceshi", "ceshi");

        // 处理主Activity业务
        showFragmentAll();
    }


    // 处理主Activity业务
    private void showFragmentAll() {

        dealOther();

        //一开始进入程序,就往容器中替换Fragment
        changeFragment(MainFragment.newInstance(), "mainfragment");
        //changeFragment(new XtTermCartFragment(), "mainfragment");
        //changeFragment(new XtTermSelectFragment(), "mainfragment");
        //changeFragment(new ZsSayhiFragment(), "mainfragment");
        //changeFragment(new ZsInvoicingFragment(), "mainfragment");
    }

    /**
     * 登录后的处理操作
     */
    private void dealOther() {

        handler = new MyHandler(this);
        ConstValues.handler = handler;

        //启动服务
        Intent service = new Intent(this, AutoUpService.class);
        startService(service);

        // 检查版本
        versionService = new VersionService(this, handler);
        departmentid = PrefUtils.getString(this, "departmentid", "");
        userid = PrefUtils.getString(this, "userid", "");
        versionService.getUrlData(departmentid, userid);
    }


    public void changeFragment(BaseFragmentSupport fragment, String tag) {
        // 获取Fragment管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        // 当前要添加的Fragment需要放的容器位置
        // 要替换的fragment
        // 标记
        beginTransaction.replace(R.id.home_container, fragment, tag);
        beginTransaction.addToBackStack(null);
        // 提交事物
        beginTransaction.commit();
    }

    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // 获取Fragment管理者
            FragmentManager fragmentManager = getSupportFragmentManager();
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 1) {
                // 立即回退一步
                fragmentManager.popBackStackImmediate();
            } else {
                finish();
            }
        }
        return true;
    }*/

    // 监听返回键
    @Override
    public void onBackPressed() {
        // 确定返回上一界面  先检查栈中的fragment是否监听了返回键
        if (!HandleBackUtil.handleBackPress(this)) {
            // 获取Fragment管理者
            FragmentManager fragmentManager = getSupportFragmentManager();
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 1) {
                // 立即回退一步
                fragmentManager.popBackStackImmediate();
            } else {
                finish();
            }
        }
    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<MainActivity> fragmentRef;

        public MyHandler(MainActivity fragment) {
            fragmentRef = new SoftReference<MainActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case ConstValues.WAIT0://  结束上传  刷新本页面
                    //fragment.shuaxinXtTermSelect(0);
                    break;
                case GlobalValues.SINGLE_UP_SUC://  协同拜访上传成功
                    //fragment.shuaxinXtTermSelect(1);
                    break;
                case GlobalValues.SINGLE_UP_FAIL://  协同拜访上传失败
                    //fragment.shuaxinXtTermSelect(2);
                    break;

                case ConstValues.WAIT5: // 升级进度弹窗
                    Bundle bundle = msg.getData();
                    String apkUrl = (String) bundle.getSerializable("apkUrl");
                    String apkName = (String) bundle.getSerializable("apkName");
                    fragment.startFrag(apkUrl,apkName);
                    break;
                case ConstValues.WAIT6: // 无需升级
                    // fragment.showToa();
                    break;

            }
        }
    }

    // 跳转到下载apk
    private void startFrag(String apkUrl,String apkName) {
        Bundle bundle = new Bundle();
        bundle.putString("apkUrl", apkUrl);//
        bundle.putString("apkName", apkName);//
        DownApkFragment downApkFragment = new DownApkFragment();
        downApkFragment.setArguments(bundle);
        changeFragment(downApkFragment, "downapkfragment");
    }
}
