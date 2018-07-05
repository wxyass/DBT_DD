package dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 查询 图片表(拜访拍照表)中已拍图片记录得到的对象,用于初始化拍照界面,和重拍时删除角标所在记录</br>
 */
public class CameraDataStc {
	
	private String camerakey;	// 图片主键
	private String picindex;	// 图片所在角标
	private String localpath ;	// 图片本地保存路径
	private String cameradata;	// 拍照时间
	private String termkey;		// 终端key
	private String pictypekey;  // 图片类型
	
	/**
	 * @return the cameradata
	 */
	public String getCameradata() {
		return cameradata;
	}
	/**
	 * @param cameradata the cameradata to set
	 */
	public void setCameradata(String cameradata) {
		this.cameradata = cameradata;
	}
	/**
	 * @return the termkey
	 */
	public String getTermkey() {
		return termkey;
	}
	/**
	 * @param termkey the termkey to set
	 */
	public void setTermkey(String termkey) {
		this.termkey = termkey;
	}
	/**
	 * @return the camerakey
	 */
	public String getCamerakey() {
		return camerakey;
	}
	/**
	 * @param camerakey the camerakey to set
	 */
	public void setCamerakey(String camerakey) {
		this.camerakey = camerakey;
	}
	/**
	 * @return the picindex
	 */
	public String getPicindex() {
		return picindex;
	}
	/**
	 * @param picindex the picindex to set
	 */
	public void setPicindex(String picindex) {
		this.picindex = picindex;
	}
	/**
	 * @return the localpath
	 */
	public String getLocalpath() {
		return localpath;
	}
	/**
	 * @param localpath the localpath to set
	 */
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	/**
	 * @return the pictypekey
	 */
	public String getPictypekey() {
		return pictypekey;
	}
	/**
	 * @param pictypekey the pictypekey to set
	 */
	public void setPictypekey(String pictypekey) {
		this.pictypekey = pictypekey;
	}
	
}
