package et.tsingtaopad.dd.ddxt.camera;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseActivity;
import et.tsingtaopad.core.util.file.FileTool;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class XtShowPicActivity extends BaseActivity  {

    private final String TAG = "XtShowPicActivity";
    private com.github.chrisbanes.photoview.PhotoView iv_photo1;
    private String picname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xt_camera_showpic);
        initView();
        initData();
        
    }

    private void initData() {

        // 获取参数“终端信息”
        Bundle bundle = getIntent().getExtras();
        picname = bundle.getString("picname");
        File file = new File(FileTool.CAMERA_PHOTO_DIR + picname);

        Uri fileuri;
        // 兼容7.0及以上的写法
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //intent = toCameraByFileProvider(intent,tempFile);
            //intent = toCameraByContentResolver(intent,tempFile,currentPhotoName);
            fileuri = toCameraByContentResolverUri(file,picname);
        } else {
            fileuri = Uri.fromFile(file);// 将File转为Uri
            //CameraImageBean.getInstance().setmPath(fileUri);
        }*/

        fileuri = Uri.fromFile(file);// 将File转为Uri

        try {
            // 读取uri所在的图片
            if (fileuri != null) {
                //photoBmp = MediaStore.Images.Media.getBitmap(ac.getContentResolver(), mImageCaptureUri);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileuri);
                iv_photo1.setImageBitmap(bitmap);
            }


        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            e.printStackTrace();
        }

    }

    private void initView() {
        iv_photo1 = (com.github.chrisbanes.photoview.PhotoView) findViewById(R.id.xt_showpic_iv_photo);
    }

    private Uri toCameraByContentResolverUri(File tempFile,String currentPhotoName){
        final ContentValues contentValues = new ContentValues(1);// ?
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());//?
        final Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        // 需要将Uri路径转化为实际路径?
        final File realFile = FileUtils.getFileByPath(FileTool.getRealFilePath(this, uri));
        // 将File转为Uri
        final Uri realUri = Uri.fromFile(realFile);

        return realUri;
    }

}
