package dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain;

import android.net.Uri;

import dd.tsingtaopad.db.table.MstCameraInfoM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 查询 图片表(拜访拍照表)中已拍图片记录得到的对象,用于初始化拍照界面,和重拍时删除角标所在记录</br>
 */
public class CameraInfoStc extends MstCameraInfoM {

	private String areaid;// 区域id
	private String focus;// 清晰度
	private String orderno ;// 顺序
	private Uri uri ;// 照片路径


	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Uri getUri() {
		return uri;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}
}
