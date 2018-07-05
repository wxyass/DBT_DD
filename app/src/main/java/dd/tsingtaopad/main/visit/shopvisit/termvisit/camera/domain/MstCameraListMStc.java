package dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 查询所有未上传的图片记录,所需的json对象</br>
 */
public class MstCameraListMStc {

	/**
	 * 
	 */
	private static final long serialVersionUID = -46512233755308905L;

	// 图片主键
	private String camerakey;//
	// 终端主键
	private String terminalkey;//
	// 拜访主键
	private String visitkey;//
	// 路线主键
	private String routekey;
	// 图片类型主键
	private String pictypekey;//
	// 图片名称
	private String picname;//
	// 本地路径
	private String localpath;//
	// 服务器路径
	private String netpath;
	// 拍照时间
	private String cameradata;//
	// 是否上传过  0:未上传  1:上传过
	private String isupload;
	// 此位置是否拍过照  0:未拍过  1:已拍过
	private String istakecamera;
	// 此位置角标
	private String picindex;//
	//
	private String ImagefileString;//
	// 
	
	/**
	 * @return the camerakey
	 */
	public String getCamerakey() {
		return camerakey;
	}
	/**
	 * @return the routekey
	 */
	public String getRoutekey() {
		return routekey;
	}
	/**
	 * @param routekey the routekey to set
	 */
	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}
	/**
	 * @param camerakey the camerakey to set
	 */
	public void setCamerakey(String camerakey) {
		this.camerakey = camerakey;
	}
	/**
	 * @return the terminalkey
	 */
	public String getTerminalkey() {
		return terminalkey;
	}
	/**
	 * @param terminalkey the terminalkey to set
	 */
	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}
	/**
	 * @return the visitkey
	 */
	public String getVisitkey() {
		return visitkey;
	}
	/**
	 * @param visitkey the visitkey to set
	 */
	public void setVisitkey(String visitkey) {
		this.visitkey = visitkey;
	}
	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}
	/**
	 * @param picname the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
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
	 * @return the netpath
	 */
	public String getNetpath() {
		return netpath;
	}
	/**
	 * @param netpath the netpath to set
	 */
	public void setNetpath(String netpath) {
		this.netpath = netpath;
	}
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
	 * @return the isupload
	 */
	public String getIsupload() {
		return isupload;
	}
	/**
	 * @param isupload the isupload to set
	 */
	public void setIsupload(String isupload) {
		this.isupload = isupload;
	}
	/**
	 * @return the istakecamera
	 */
	public String getIstakecamera() {
		return istakecamera;
	}
	/**
	 * @param istakecamera the istakecamera to set
	 */
	public void setIstakecamera(String istakecamera) {
		this.istakecamera = istakecamera;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	/**
	 * @return the imagefileString
	 */
	public String getImagefileString() {
		return ImagefileString;
	}
	/**
	 * @param imagefileString the imagefileString to set
	 */
	public void setImagefileString(String imagefileString) {
		ImagefileString = imagefileString;
	}
	
}
