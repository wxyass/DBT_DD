package dd.tsingtaopad.core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * Created by yangwenmin on 2017/12/23.
 * 请求存储码
 */

public class RequestCodes {
    public static final int TAKE_PHONE = 4;// 去拍照
    public static final int PICK_PHONE = 5;// 选择照片
    public static final int RESULT_REQUEST_CODE = 8;// 选择照片
    public static final int CROP_PHONE = UCrop.REQUEST_CROP;// 去剪裁
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;// 剪裁失败
    public static final int SCAN = 7;// 去扫描

}
