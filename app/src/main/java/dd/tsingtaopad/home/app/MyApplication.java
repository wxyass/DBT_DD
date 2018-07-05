package dd.tsingtaopad.home.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.app.Latte;
import dd.tsingtaopad.core.net.HttpUrl;
import dd.tsingtaopad.core.net.interceptors.DebugInterceptor;
import dd.tsingtaopad.core.util.dbtutil.FileUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.icon.FontEcModule;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Application应用
 * <p>
 * Created by yangwenmin on 2017/10/14.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        // 通过全局配置器,配置参数
        Latte.init(this)// 配置ApplicationContext,全局handler
                .withIcon(new FontAwesomeModule())// 配置字体图标
                .withIcon(new FontEcModule())// 配置另一种字体图标
                .withApiHost(HttpUrl.API_HOST)// 配置ApiHost
                .withInterceptor(new DebugInterceptor("test", R.raw.test))// 拦截url请求中包含test的url请求
                .configure();// 修改→配置完成的标记true

        // 配置Fragmention库
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式  BUBBLE:直接显示  SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                        DbtLog.write("Exception1208", e.toString());
                        FileUtil.writeTxt(e.toString(), FileUtil.getSDPath() + "/Exception1208.txt");

                    }
                })
                .install();

        // 崩溃收集
        CrashHandler.getInstance().init(this);
        Utils.init(this);

        // 适配
        ScreenAdapterTools.init(this);

    }

    //旋转适配,如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScreenAdapterTools.getInstance().reset(this);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
