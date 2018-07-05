package dd.tsingtaopad.core.ui.camera;

import android.net.Uri;

import java.io.File;

/**
 * Created by yangwenmin on 2017/12/23.
 * 存储一些中间值
 */

public class CameraImageBean {

    private Uri mPath = null;// 图片路径
    private File file = null;// 图片file
    private String picname = null;// 图片名称

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getmPath() {
        return mPath;
    }

    public void setmPath(Uri mPath) {
        this.mPath = mPath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }
}
