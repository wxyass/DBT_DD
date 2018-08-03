package et.tsingtaopad.core.base.middle;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import et.tsingtaopad.core.base.mysupport.MySupportFragment;
import et.tsingtaopad.core.ui.camera.CameraImageBean;
import et.tsingtaopad.core.ui.camera.LatteCamera;
import et.tsingtaopad.core.ui.camera.RequestCodes;
import et.tsingtaopad.core.util.callback.CallbackManager;
import et.tsingtaopad.core.util.callback.CallbackType;
import et.tsingtaopad.core.util.callback.IGlobalCallback;
import et.tsingtaopad.core.util.file.FileTool;
/*import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;*/

/**
 * 中间层: 权限判定
 * Created by yangwenmin on 2017/12/6.
 */
// @RuntimePermissions
public abstract class PermissionCheckerDelegate extends MySupportFragment {

    /*//不是直接调用方法  // 通过在方法上添加注解,表示这个方法要执行的代码需要权限,Rebuild后,会生成真正能调用的方法
    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.start(this);
    }

    // 这是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
    }

    // OnPermissionDenied: 当用户拒绝相机权限时,调用该方法
    @OnPermissionDenied({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    // OnNeverAskAgain: 当用户选择不再询问相机权限后,不管用户选择了拒绝还是同意,调用该方法,之后不再询问该权限
    @OnNeverAskAgain({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraNever() {
        Toast.makeText(getContext(), "不再询问", Toast.LENGTH_SHORT).show();
    }

    // 调用辅助类处理放回的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case RequestCodes.TAKE_PHONE:// 拍照相机 回来的
                    // 获取照片文件路径
                    final Uri resultUri = CameraImageBean.getInstance().getmPath();
                    /*File tempFile = CameraImageBean.getInstance().getFile();
                    Uri resultUri = getResultUri(getContext(),tempFile);*/

                    // 处理图片:  裁剪 压缩 水印
                    /*Intent intent = startPhotoZoom(resultUri);
                    startActivityForResult(intent, RequestCodes.RESULT_REQUEST_CODE);*/

                    final IGlobalCallback<Uri> callbacktakephone = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if(callbacktakephone!=null){
                        callbacktakephone.executeCallback(resultUri);
                    }
                    /*// 去剪裁,将原图覆盖掉
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(480,640)
                            .start(getContext(),this);*/

                    break;
                case RequestCodes.PICK_PHONE:// 相册 回来的
                    if(data != null){
                        // 获取从相册选择的图片路径
                        final Uri pickPath = data.getData();
                        // 从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.creatCropFile().getPath();
                        // 去剪裁,在新位置生成一张剪裁后的图片
                        UCrop.of(pickPath,Uri.parse(pickCropPath))
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }
                    break;
                case RequestCodes.CROP_PHONE:// 剪裁成功 回来的
                    // 获取裁剪后的图片路径
                    final Uri cropUri = UCrop.getOutput(data);
                    // 拿到剪裁后的数据进行处理
                    final IGlobalCallback<Uri> callbackcrop = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if(callbackcrop!=null){
                        callbackcrop.executeCallback(cropUri);
                    }

                    break;
                case RequestCodes.CROP_ERROR:// 剪裁失败 回来的
                    Toast.makeText(getContext(), "剪裁出错了", Toast.LENGTH_SHORT).show();
                    break;

                case RequestCodes.RESULT_REQUEST_CODE:// 自定义裁剪回来的
                    /*final Uri codeUri = CameraImageBean.getInstance().getmPath();
                    final IGlobalCallback<Uri> callbacktakephone = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if(callbacktakephone!=null){
                        callbacktakephone.executeCallback(codeUri);
                    }*/
                    break;
                default:
                    break;
            }

        }
    }

    // 通过图片File 获取文件Uri
    private Uri getResultUri(Context context, File tempFile){
        final ContentValues contentValues = new ContentValues(1);// ?
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());//?
        final Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        // 需要将Uri路径转化为实际路径?
        final File realFile = FileUtils.getFileByPath(FileTool.getRealFilePath(context, uri));
        final Uri resultUri = Uri.fromFile(realFile);// 将File转为Uri
        return resultUri;
    }

    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Context ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 640f;//这里设置高度为640f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 25) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 1;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

}
