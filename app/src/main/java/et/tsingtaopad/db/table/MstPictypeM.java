package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MstPictypeMDaoImpl;


/**
 * 拜访拍照表
 * MstAgencyinfoM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PICTYPE_M", daoClass = MstPictypeMDaoImpl.class)
public class MstPictypeM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String pictypekey;
	@DatabaseField
	private String pictypename;
	@DatabaseField
	private String areaid;
	@DatabaseField
	private String orderno;
	@DatabaseField
	private String status;
	@DatabaseField
	private String focus;
	@DatabaseField
	private String createuser;
	@DatabaseField
	private String updateuser;
	@DatabaseField
	private String createdate;
	@DatabaseField
	private String updatedate;

	// Constructors

	/** default constructor */
	public MstPictypeM() {
	}

	/** minimal constructor */
	public MstPictypeM(String camerakey) {
		this.pictypekey = pictypekey;
	}

	/**
	 * @param pictypekey
	 * @param pictypename
	 * @param areaid
	 * @param orderno
	 * @param status
	 * @param focus
	 * @param createuser
	 * @param updateuser
	 * @param createdate
	 * @param updatedate
	 */
	public MstPictypeM(String pictypekey, String pictypename, String areaid,
			String orderno, String status, String focus, String createuser,
			String updateuser, String createdate, String updatedate) {
		super();
		this.pictypekey = pictypekey;
		this.pictypename = pictypename;
		this.areaid = areaid;
		this.orderno = orderno;
		this.status = status;
		this.focus = focus;
		this.createuser = createuser;
		this.updateuser = updateuser;
		this.createdate = createdate;
		this.updatedate = updatedate;
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
	 * @return the areaid
	 */
	public String getAreaid() {
		return areaid;
	}

	/**
	 * @param areaid the areaid to set
	 */
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	/**
	 * @return the orderno
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * @param orderno the orderno to set
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the focus
	 */
	public String getFocus() {
		return focus;
	}

	/**
	 * @param focus the focus to set
	 */
	public void setFocus(String focus) {
		this.focus = focus;
	}

	/**
	 * @return the createuser
	 */
	public String getCreateuser() {
		return createuser;
	}

	/**
	 * @param createuser the createuser to set
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the updateuser
	 */
	public String getUpdateuser() {
		return updateuser;
	}

	/**
	 * @param updateuser the updateuser to set
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the updatedate
	 */
	public String getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}