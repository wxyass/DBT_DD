package dd.tsingtaopad.business.system;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.text.DecimalFormat;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.net.RestClient;
import dd.tsingtaopad.core.net.callback.IError;
import dd.tsingtaopad.core.net.callback.IFailure;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.callback.OnDownLoadProgress;

/**
 * 下载apk
 * Created by yangwenmin on 2018/3/12.
 */

public class DownApkFragment extends BaseFragmentSupport  {

    private final String TAG = "DownApkFragment";

    public static final int SHOWDOWNLOADDIALOG = 88; // 弹出进度条,设置最大值100 //显示正在下载的对话框
    public static final int UPDATEDOWNLOADDIALOG = 99;// 设置进度条  //刷新正在下载对话框的内容
    public static final int DOWNLOADFINISHED = 66;// 下载完成开始安装 //下载完成后进行的操作


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_downapk, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);
        initData();
    }

    // 初始化数据
    private void initData() {
        downLoadApk();
    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<DownApkFragment> fragmentRef;

        public MyHandler(DownApkFragment fragment) {
            fragmentRef = new SoftReference<DownApkFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DownApkFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case SHOWDOWNLOADDIALOG:
                    fragment.showDownloadDialog();
                    break;
                case UPDATEDOWNLOADDIALOG: // 督导输入数据后
                    fragment.showDownloading(msg);
                    break;
                case DOWNLOADFINISHED: // 督导输入数据后
                    fragment.stopDownloadDialog();
                    break;
            }
        }
    }

    // 下载apk
    private void downLoadApk() {

        Message msg = Message.obtain();
        msg.what = SHOWDOWNLOADDIALOG;
        handler.sendMessage(msg);

        RestClient.builder()
                .url(apkUrl)
                // .params("data", jsonZip)
                // .loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //用handle通知主线程 下载完成 -> 开始安装
                        Message finishedMsg = Message.obtain();
                        finishedMsg.what = DOWNLOADFINISHED;
                        // finishedMsg.obj = downPath+apkName;// 文件路径
                        handler.sendMessage(finishedMsg);
                    }
                })
                .onDownLoadProgress(new OnDownLoadProgress() {
                    @Override
                    public void onProgressUpdate(long fileLength, int downLoadedLength) {
                        // 用handle通知主线程刷新进度, progress: 是1-100的正整数
                        Message updateMsg = Message.obtain();
                        updateMsg.what = UPDATEDOWNLOADDIALOG;
                        updateMsg.obj = fileLength;
                        updateMsg.arg1= downLoadedLength;
                        handler.sendMessage(updateMsg);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .name(apkName)
                .dir(downPath)
                .builde()
                .download();
    }

    /**
     * 显示正在下载的对话框
     */
    String apkUrl = "http://oss.wxyass.com/tscs2.4.3.1.0.apk";
    String apkName = "tscs2.4.3.1.0.apk";
    String downPath = "dbt";// apk的存放位置

    //private HttpHandler httpHandler;
    private AlertDialog downloadDialog;//正在下载的对话框
    private TextView tvCur;//当前下载的百分比
    private ProgressBar pb;//下载的进度条
    private TextView tvCanCel;//停止下载
    private TextView tvHidden;//隐藏对话框的按钮
    private int contentLength;//要下载文件的大小
    private boolean isDownloading = false;//是否正在下载
    private boolean isCancel = false;//是否取消升级
    private DecimalFormat df = new DecimalFormat("###.00");//设置结果保留两位小数

    private void showDownloadDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setCancelable(false);// 不可消失
        downloadDialog = adb.create();
        View view = View.inflate(getActivity(), R.layout.download_dialog_layout, null);
        downloadDialog.setView(view, 0, 0, 0, 0);
        tvCur = (TextView) view.findViewById(R.id.tv_cursize);
        tvCanCel = (TextView) view.findViewById(R.id.tv_cancel);
        tvHidden = (TextView) view.findViewById(R.id.tv_hidden);
        pb = (ProgressBar) view.findViewById(R.id.download_pb);

        downloadDialog.show();
    }

    private void showDownloading(android.os.Message msg) {
        //ProgressBar progress1 = downloadDialog.findViewById(R.id.download_pb);
        long totalSize = (long) msg.obj;// 总进度
        int curSize = (int) msg.arg1;// 获取当前进度
        pb.setMax((int)totalSize);
        pb.setProgress(curSize);

        /*progress1.setMax(100);
        progress1.setProgress(38);*/
    }

    private void stopDownloadDialog() {
        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
        isDownloading = false;
        if (downloadDialog.isShowing()) {
            downloadDialog.dismiss();
            supportFragmentManager.popBackStack();
        }
    }
}
