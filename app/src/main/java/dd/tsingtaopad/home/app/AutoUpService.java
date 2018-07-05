package dd.tsingtaopad.home.app;

import java.util.Timer;
import java.util.TimerTask;

import dd.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.IBinder;
import android.util.Log;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名:MainService
 * 作者：@王士铭  </br>
 * 创建时间： 2013-12-5 上午9:26:50</br>
 * 功能描述: 监听网络变化 并上传数据</br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期    2013-12-5 上午9:26:50  原因  BUG号    修改人@王士铭 修改版本</br>
 */
public class AutoUpService extends Service {

    private MainServiceReceiver mMainServiceReceiver;
    private final String TAG = "MainService";
    // public static final String EXIT_UPDATE =
    // "com.dbt.tsingtao.exit_update";// 退出更新广播acition
    // public static final String EXIT_APP = "com.dbt.tsingtao.exit";// 退出广播
    private int firstLogin = 0;
    private long timerTaskDelay = 1000 * 60 * 60;// 定时任务执行间隔时间 1小时
    private Timer timer;
    private XtUploadService upload;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        firstLogin = 0;
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mMainServiceReceiver = new MainServiceReceiver();// 网络改变监听
        registerReceiver(mMainServiceReceiver, filter);// 注册监听
        timer = new Timer();
        upload = new XtUploadService(getApplicationContext(), null);
        UploadTimerTask task = new UploadTimerTask();
        timer.schedule(task, timerTaskDelay, timerTaskDelay);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMainServiceReceiver);
        if (timer != null) {
            timer.cancel();
        }
    }

    // 每间隔一段  上传数据
    class UploadTimerTask extends TimerTask {

        @Override
        public void run() {

            try {
                Log.i(TAG, "UploadTimerTask upload");
                if (NetStatusUtil.isNetValid(getApplicationContext())) {
                    DbtLog.logUtils(TAG, "上传数据-UploadTimerTask-定时");
                    upload.uploadTables(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class MainServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            ConnectivityManager connctMananger = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {

                NetworkInfo activeNetworkInfo = connctMananger.getActiveNetworkInfo();

                if (null != activeNetworkInfo && activeNetworkInfo.getState() == State.CONNECTED) {

                    try {
                        Log.i(TAG, "net work upload");
                        DbtLog.logUtils(TAG, "上传数据-onReceive-监听网络变化");
                        upload.uploadTables(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
