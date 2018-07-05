package dd.tsingtaopad.dd.ddxt.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

import dd.tsingtaopad.core.R;
import dd.tsingtaopad.core.ui.camera.CameraImageBean;
import dd.tsingtaopad.core.ui.camera.RequestCodes;
import dd.tsingtaopad.core.util.file.FileTool;

//import com.diabin.latte.util.file.FileUtil;

/**
 * Created by yangwenmin on 2017/12/23.
 * 照片处理类
 */

public class XtCameraHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final Fragment DELEGATE;
    private String picName;

    public XtCameraHandler(Fragment delegate) {
        this.DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
        this.DELEGATE = delegate;
    }

    // 展示弹窗: 拍照,相册,取消
    public final void beginCameraDialog(String picname) {
        DIALOG.show();
        picName = picname;
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);// 设置动画
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.photodialog_btn_cancel) {// 取消
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_native) {// 查看
            // pickPhoto();
            lookPic();
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {// 拍照
            takePhoto();
            DIALOG.cancel();
        }

    }

    private void lookPic() {
        Intent intent = new Intent(DELEGATE.getActivity(), XtShowPicActivity.class);
        intent.putExtra("picname", picName);// 非第一次拜访1
        DELEGATE.startActivity(intent);
    }

    // 打开相机
    public void takePhoto() {
        final String currentPhotoName = getPhotoName(); // IMG_20180109_125134.jpg
        //final String currentPhotoName = DateUtil.formatDate(new Date(), null) + ".jpg"; // 20180109125134.jpg
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tempFile = new File(FileTool.CAMERA_PHOTO_DIR, currentPhotoName);
        //final File tempFile = new File(FileUtil.getPhotoPath()+currentPhotoName);

        Uri fileuri;
        // 兼容7.0及以上的写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //intent = toCameraByFileProvider(intent,tempFile);
            //intent = toCameraByContentResolver(intent,tempFile,currentPhotoName);

            /*fileuri = toCameraByContentResolverUri(tempFile,currentPhotoName);
            CameraImageBean cameraImageBean = CameraImageBean.getInstance();
            cameraImageBean.setmPath(fileuri);
            cameraImageBean.setPicname(currentPhotoName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileuri);
            DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHONE);*/


            intent = toCameraByContentResolver(intent,tempFile,currentPhotoName);
            DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHONE);
        } else {
            fileuri = Uri.fromFile(tempFile);// 将File转为Uri
            //CameraImageBean.getInstance().setmPath(fileUri);
            CameraImageBean cameraImageBean = CameraImageBean.getInstance();
            cameraImageBean.setmPath(fileuri);
            cameraImageBean.setPicname(currentPhotoName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileuri);
            DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHONE);
        }
    }


    // 打开相册
    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult(Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHONE);
    }


    // 获取图片名称(随机命名,包含后缀,比如: IMG_20171223_112844.jpg)
    private String getPhotoName() {
        return FileTool.getFileNameByTime("IMG", "jpg");
    }

    // Android 7.0调用系统相机适配 通过FileProvider来实现
    private Intent toCameraByFileProvider(Intent intent,File tempFile){
        //通过FileProvider创建一个content类型的Uri
        Uri imageUri = FileProvider.getUriForFile(DELEGATE.getContext(), "et.tsingtaopad.provider", tempFile);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //将拍取的照片保存到指定URI
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        CameraImageBean.getInstance().setFile(tempFile);
        return intent;
    }

    // Android 7.0调用系统相机适配 使用FileUtils库中的方法转化
    private Intent toCameraByContentResolver(Intent intent,File tempFile,String currentPhotoName){
        final ContentValues contentValues = new ContentValues(1);// ?
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());//?
        final Uri uri = DELEGATE.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        // 需要将Uri路径转化为实际路径?
        final File realFile = FileUtils.getFileByPath(FileTool.getRealFilePath(DELEGATE.getContext(), uri));
        // 将File转为Uri
        final Uri realUri = Uri.fromFile(realFile);
        //将拍取的照片保存到指定URI
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        CameraImageBean cameraImageBean = CameraImageBean.getInstance();
        cameraImageBean.setmPath(realUri);
        cameraImageBean.setPicname(currentPhotoName);
        return intent;
    }

    private Uri toCameraByContentResolverUri(File tempFile,String currentPhotoName){
        final ContentValues contentValues = new ContentValues(1);// ?
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());//?
        final Uri uri = DELEGATE.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        // 需要将Uri路径转化为实际路径?
        final File realFile = FileUtils.getFileByPath(FileTool.getRealFilePath(DELEGATE.getContext(), uri));
        // 将File转为Uri
        final Uri realUri = Uri.fromFile(realFile);

        return realUri;
    }
}
