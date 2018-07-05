package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * 图片表 临时表
 * MstCameraInfoMTemp entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CAMERAINFO_M_TEMP")
public class MstCameraInfoMTemp implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String camerakey;// 图片主键
	@DatabaseField
	private String terminalkey;// 终端主键
	@DatabaseField
	private String visitkey; // 拜访主键
	@DatabaseField
	private String pictypekey;// 图片类型主键(UUID)
	@DatabaseField
	private String picname;// 图片名称
	@DatabaseField
	private String localpath;// 本地图片路径
	@DatabaseField
	private String netpath;// 请求网址(原先ftp上传用,现已不用)
	@DatabaseField
	private String cameradata;// 拍照时间
	@DatabaseField
	private String isupload;// 是否已上传 0未上传  1已上传
	@DatabaseField
	private String istakecamera;//
	@DatabaseField
	private String picindex;// 图片所在角标
	@DatabaseField
	private String pictypename;// 图片类型(中文名称)
	@DatabaseField
	private String sureup;// 确定上传 0确定上传 1未确定上传
	@DatabaseField
	private String imagefileString;// 将图片文件转成String保存在数据库

	@DatabaseField
	private String areaid;
	@DatabaseField
	private String areapid;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String routekey;



	// Constructors

	/** default constructor */
	public MstCameraInfoMTemp() {
	}

	/** minimal constructor */
	public MstCameraInfoMTemp(String camerakey) {
		this.camerakey = camerakey;
	}

	/**
	 * @param camerakey
	 * @param terminalkey
	 * @param picname
	 * @param localpath
	 * @param netpath
	 * @param cameradata
	 * @param isupload
	 * @param istakecamera
	 * @param picindex
	 */
	public MstCameraInfoMTemp(String camerakey, String terminalkey, String picname,
                              String localpath, String netpath, String cameradata,
                              String isupload, String istakecamera, String picindex) {
		super();
		this.camerakey = camerakey;
		this.terminalkey = terminalkey;
		this.picname = picname;
		this.localpath = localpath;
		this.netpath = netpath;
		this.cameradata = cameradata;
		this.isupload = isupload;
		this.istakecamera = istakecamera;
		this.picindex = picindex;
	}

	
	/**
	 * @return the pictypename
	 */
	public String getPictypename() {
		return pictypename;
	}

	/**
	 * @param pictypename the pictypename to set
	 */
	public void setPictypename(String pictypename) {
		this.pictypename = pictypename;
	}

	/**
	 * @return the camerakey
	 */
	public String getCamerakey() {
		return camerakey;
	}

	/**
	 * @param camerakey
	 *            the camerakey to set
	 */
	public void setCamerakey(String camerakey) {
		this.camerakey = camerakey;
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
	 * @return the terminalkey
	 */
	public String getTerminalkey() {
		return terminalkey;
	}

	/**
	 * @param terminalkey
	 *            the terminalkey to set
	 */
	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}

	/**
	 * @param picname
	 *            the picname to set
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
	 * @param localpath
	 *            the localpath to set
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
	 * @param netpath
	 *            the netpath to set
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
	 * @param cameradata
	 *            the cameradata to set
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
	 * @param isupload
	 *            the isupload to set
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
	 * @param istakecamera
	 *            the istakecamera to set
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
	 * @param picindex
	 *            the picindex to set
	 */
	public void setPicindex(String picindex) {
		this.picindex = picindex;
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
		return imagefileString;
	}

	/**
	 * @param imagefileString the imagefileString to set
	 */
	public void setImagefileString(String imagefileString) {
		this.imagefileString = imagefileString;
	}

	/**
	 * @return the sureup
	 */
	public String getSureup() {
		return sureup;
	}

	/**
	 * @param sureup the sureup to set
	 */
	public void setSureup(String sureup) {
		this.sureup = sureup;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreapid() {
		return areapid;
	}

	public void setAreapid(String areapid) {
		this.areapid = areapid;
	}

	public String getGridkey() {
		return gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getRoutekey() {
		return routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}
}