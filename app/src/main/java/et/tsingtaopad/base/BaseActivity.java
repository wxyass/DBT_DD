package et.tsingtaopad.base;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import et.tsingtaopad.core.util.exit.ExitAppUtils;
import et.tsingtaopad.fragmentback.HandleBackUtil;
import et.tsingtaopad.home.app.MyApplication;
import et.tsingtaopad.home.initadapter.GlobalValues;

/**
  * 
  * 项目名称：营销移动智能工作平台 </br>
  * 文件名：BaseCoreActivity.java</br>
  * 作者：yass   </br>
  * 创建时间：2017-9-3</br>
  * 功能描述: </br>      
  * 版本 V 1.0</br>               
  * 修改履历</br>
  * 日期      原因  BUG号    修改人 修改版本</br>
  */
public class BaseActivity extends AppCompatActivity {
	private final static String TAG = "BaseCoreActivity";
	protected static final int LOCKMESSAGEID = 13;
	private long lockTaskDelay = 120 * 60 * 1000;//锁屏时间
	//private long lockTaskDelay =1 * 60 * 1000;//锁屏时间
	protected static Timer lockTimer = new Timer();
	protected static LockTask lockTask;
	public static boolean isTimeToLock = false;
	private MyApplication application;
	final class LockTask extends TimerTask { 

		@Override
		public void run() {
			Log.i(TAG, "time down show dialog lock");
			handler.sendEmptyMessage(LOCKMESSAGEID);
		
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case LOCKMESSAGEID:
				isTimeToLock = true;
				break;
			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ExitAppUtils.getInstance().createActivity(this);
		lockTask = new LockTask();
	}

	@Override
	protected void onResume() {
		super.onResume();
		unLockScreen();
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		lockScreen();// 把之前的锁销毁,创建新锁
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			View currentFocus = this.getCurrentFocus();
			if (currentFocus != null) {
				IBinder windowToken = currentFocus.getWindowToken();
				imm.hideSoftInputFromWindow(windowToken, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDestroy() {
		lockTask.cancel();
		//lockTimer.cancel();
		super.onDestroy();
		ExitAppUtils.getInstance().destroyActivity(this);
	}

	public void unLockScreen() {
		if (isTimeToLock) {
			isTimeToLock = false;
			/*Intent intent = new Intent(this, LockScreenActivity.class);
			startActivity(intent);*/
		}
		lockTask.cancel();
		lockTimer.cancel();
		Log.i(TAG, "unlockScreen  将原任务从队列中移除");

	}

	public void lockScreen() {

		if (lockTask != null) {
			Log.i(TAG, "lockScreen 将原任务从队列中移除");
			lockTask.cancel(); //将原任务从队列中移除
		}
		Log.i(TAG, "lockScreen");
		lockTask = new LockTask();
		lockTimer = new Timer();
		lockTimer.schedule(lockTask, lockTaskDelay);
	}
	
	@Override  
    protected void onStop() {  
            // TODO Auto-generated method stub  
            super.onStop();  

            /*if (!isAppOnForeground()) {  
                    //app 进入后台  
            	handler.sendEmptyMessage(LOCKMESSAGEID);
            } */ 
    }
	
	/** 
     * 程序是否在前台运行 
     *  
     * @return 
     */  
    public boolean isAppOnForeground() {  
            // Returns a list of application processes that are running on the  
            // device  
               
            ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);  
            String packageName = getApplicationContext().getPackageName();  

            // 正在运行的程序
            List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();  
            if (appProcesses == null)  
                    return false;  

            for (RunningAppProcessInfo appProcess : appProcesses) {  
            	// com.android.gallery3d
            		
            	// 相机
                /*Intent infoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
                String res = getPackageManager().resolveActivity(infoIntent, 0).activityInfo.packageName;
                if (appProcess.processName.equals(res)) {
                	// com.thundersoft.ucam
                	return true;  
                } */
                // The name of the process that this object is associated with.  
                if (appProcess.processName.equals(packageName) && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {  
                        return true;  
                } 
            }  

            return false;  
    }
    
    /**
     * 判断程序是否在前台运行
     * 
     * @param packageName
     * @return
     */
	public boolean isOpen(String packageName) {
		if (packageName.equals("") | packageName == null)
			return false;
		ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for (RunningAppProcessInfo runinfo : runningAppProcesses) {
			String pn = runinfo.processName;
			if (pn.equals(packageName) && runinfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
				return true;
		}
		return false;
	}




	// 权限相关 ↓--------------------------------------------------------------------------

	 /**
	  * 判断是否有指定的权限
	  */
	 public boolean hasPermission(String... permissions) {

		 for (String permisson : permissions) {
			 if (ContextCompat.checkSelfPermission(getApplicationContext(), permisson)
					 != PackageManager.PERMISSION_GRANTED) {
				 return false;
			 }
		 }
		 return true;
	 }

	 /**
	  * 申请指定的权限.
	  */
	 public void requestPermission(int code, String... permissions) {

		 if (Build.VERSION.SDK_INT >= 23) {
			 requestPermissions(permissions, code);
			 // startAppSettings();
		 }
	 }

	 /**
	  * 启动当前应用设置页面
	  */
	 private void startAppSettings() {
		 Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		 intent.setData(Uri.parse("package:" + getPackageName()));
		 startActivity(intent);
	 }

	 // 定义几个常量

	 @Override
	 public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		 switch (requestCode) {
			 case GlobalValues.HARDWEAR_CAMERA_CODE:
				 if (grantResults.length > 0
						 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					 doOpenCamera();
				 }else{
					 Toast.makeText(getApplicationContext(),"请先开启相机权限",Toast.LENGTH_SHORT).show();
				 }
				 break;
			 case GlobalValues.WRITE_READ_EXTERNAL_CODE:
				 if (grantResults.length > 0
						 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					 doWriteSDCard();
				 }else{
					 Toast.makeText(getApplicationContext(),"请先开启读写存储权限",Toast.LENGTH_SHORT).show();
				 }
				 break;
			 case GlobalValues.LOCAL_CODE:
				 if (grantResults.length > 0
						 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					 doLocation();
				 }else{
					 Toast.makeText(getApplicationContext(),"请先开启定位权限",Toast.LENGTH_SHORT).show();
				 }
				 break;
			 case GlobalValues.WRITE_LOCAL_CODE:
				 if (grantResults.length > 0
						 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					 doCameraWriteSD();
				 }else{
					 Toast.makeText(getApplicationContext(),"请先开启读写存储权限",Toast.LENGTH_SHORT).show();
				 }
				 break;
		 }
	 }

	 public void doCameraWriteSD() {

	 }

	 // 定位
	 public void doLocation() {
	 }


	 // 拍照
	 public void doOpenCamera() {

	 }

	 // 读写SD卡业务逻辑,由具体的子类实现
	 public void doWriteSDCard() {

	 }

	// 权限相关 ↑--------------------------------------------------------------------------

	 @Override
	 public void onBackPressed() {
		 if (!HandleBackUtil.handleBackPress(this)) {
			 super.onBackPressed();
		 }
	 }

}
