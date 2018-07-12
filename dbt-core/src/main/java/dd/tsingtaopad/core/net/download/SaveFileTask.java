package dd.tsingtaopad.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;


import java.io.File;
import java.io.InputStream;

import dd.tsingtaopad.core.app.Latte;
import dd.tsingtaopad.core.net.callback.IRequest;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.callback.OnDownLoadProgress;
import dd.tsingtaopad.core.util.file.FileTool;
import okhttp3.ResponseBody;

/**
 * 异步执行下载成功后的保存操作
 * Created by yangwenmin on 2017/10/17.
 */

public class SaveFileTask extends AsyncTask<Object, Integer, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private OnDownLoadProgress ONDOWNLOADPROGRESS;
    private long fileLength;// 文件的大小

    public SaveFileTask(IRequest request, ISuccess success,OnDownLoadProgress onDownLoadProgress) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ONDOWNLOADPROGRESS = onDownLoadProgress;
    }

    @Override
    protected File doInBackground(Object... params) {
        // 下载路径
        String downloadDir = (String) params[0];
        // 后缀名
        String extension = (String) params[1];
        // 请求体
        final ResponseBody body = (ResponseBody) params[2];
        // 文件名称
        String name = (String) params[3];
        // 得到输入流
        final InputStream is = body.byteStream();
        //
        if (downloadDir == null || "".equals(downloadDir)) {
            downloadDir = "down_loads";
        }
        //
        if (extension == null || "".equals(extension)) {
            extension= "";
        }

        // 进度回调
        WriteToDiskCallBack writeToDiskCallBack =  new WriteToDiskCallBack() {
            @Override
            public void onProgressUpdate(int progress) {// 子线程
                SaveFileTask.this.onProgressUpdate(progress);
            }
        };

        fileLength = body.contentLength();

        // 创建文件
        if(name==null){
            // 总大小,下载进度回调
            return FileTool.writeToDisk(is,downloadDir,extension.toUpperCase(),extension,fileLength,writeToDiskCallBack);
        }else{
            return FileTool.writeToDisk(is,downloadDir,name,fileLength,writeToDiskCallBack);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (ONDOWNLOADPROGRESS != null) {
            ONDOWNLOADPROGRESS.onProgressUpdate(fileLength, values[0]);
        }
    }

    // 执行完子线程回到主线程后做的操作
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
        //InstallAPK(file);
    }

    // 若是.apk文件, 直接安装
    private void autoInstallApk(File file){
        if(FileTool.getExtension(file.getPath()).equals("apk")){// 后缀名是apk
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        }
    }

    /**
     * 安装apk
     *
     * @param apkfile
     */
    private void InstallAPK(File apkfile) {
        /*File apkfile = new File(apkpath);
        if (!apkfile.exists()) {
            return;
        }*/

        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(Latte.getApplicationContext(), "dd.tsingtaopad.provider", apkfile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}
