package et.tsingtaopad.dd.ddxt.camera;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.file.FileTool;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;


/**
 * 功能描述: 拍照GridView适配器,展示每张缩略图</br>
 */
public class XtCameraAdapter extends BaseAdapter {

	private Context context;
    private List<CameraInfoStc> dataLst;

	public XtCameraAdapter(Context context, List<CameraInfoStc> valueLst ) {
        this.context = context;
        this.dataLst = valueLst;
    }

	// item的个数
	@Override
	public int getCount() {
		return dataLst.size();
	}

	// 根据位置获取对象
	@Override
	public CameraInfoStc getItem(int position) {
		return dataLst.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// 初始化每一个item的布局(待优化)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.camera_takepic_item, null);
		ImageView picIv = (ImageView) view.findViewById(R.id.camera_iv_pic);
		TextView descTv = (TextView) view.findViewById(R.id.camera_tv_desc);


		CameraInfoStc camerainfostc = (CameraInfoStc)dataLst.get(position);
		
		descTv.setText(camerainfostc.getPictypename());
		if (camerainfostc.getPicname()==null) {// 没拍照
			Glide.with(context)
					.load(R.drawable.bg_camera)
					.into(picIv);
		}else{// 已拍照
			final File tempFile = new File(FileTool.CAMERA_PHOTO_DIR, camerainfostc.getPicname());
			//final File tempFile = new File(FileUtil.getPhotoPath()+camerainfostc.getLocalpath());


			Uri fileUri = null;
			// // 兼容7.0及以上的写法
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
				/*final ContentValues contentValues = new ContentValues(1);// ?
				String path = tempFile.getPath();
				contentValues.put(MediaStore.Images.Media.DATA, path);//?
				//final Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
				//final Uri uri = context.getApplicationContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
				final Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", tempFile);
				// 需要将Uri路径转化为实际路径?
				final File realFile = FileUtils.getFileByPath(FileTool.getRealFilePath(context, uri));
				// 将File转为Uri
				fileUri = Uri.fromFile(realFile);*/

				fileUri = Uri.fromFile(tempFile);// 将File转为Uri

			}else{
				fileUri = Uri.fromFile(tempFile);// 将File转为Uri
			}


			Glide.with(context)
					.load(fileUri)
					.into(picIv);
		}
		return view;
	}

}
