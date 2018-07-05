package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MstAgencyKFMDaoImpl;


/**
 * MstAgencyinfoM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGENCYKF_M", daoClass = MstAgencyKFMDaoImpl.class)
//@DatabaseTable(tableName = "MST_AGENCYKF_M")
public class MstAgencyKFM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String agencykfkey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String agencyname;
	@DatabaseField
	private String contact;
	@DatabaseField
	private String mobile;
	@DatabaseField
	private String address;
	@DatabaseField
	private String area;
	@DatabaseField
	private String money;
	@DatabaseField
	private String carnum;
	@DatabaseField
	private String productname;
	@DatabaseField
	private String kfdate;
	@DatabaseField
	private String status;
	@DatabaseField
	private String createdate;
	@DatabaseField
	private String createuser;
	@DatabaseField
	private String updatedate;
	@DatabaseField
	private String updateuser;
	@DatabaseField
	private String upload;
	
	@DatabaseField
	private String persion; //人员
	@DatabaseField
	private String business;//经营状况	
	@DatabaseField
	private int    isone;//是否数一数二经销商
	@DatabaseField
	private String coverterms;//覆盖终端	
	@DatabaseField
	private String supplyterms;//直供终端
	@DatabaseField
	private String passdate;//达成时间	
	

	// Constructors
	/** default constructor */
	public MstAgencyKFM() {
	}

	/** minimal constructor */
	public MstAgencyKFM(String agencykey) {
		this.agencykfkey = agencykfkey;
	}

	/** full constructor */
	public MstAgencyKFM(String agencykfkey, String gridkey, String agencyname,
			String contact, String mobile, String address, String area,
			String money, String carnum, String productname, String kfdate,
			String status, String createdate, String createuser,
			String updatedate, String updateuser, String upload) {
		super();
		this.agencykfkey = agencykfkey;
		this.gridkey = gridkey;
		this.agencyname = agencyname;
		this.contact = contact;
		this.mobile = mobile;
		this.address = address;
		this.area = area;
		this.money = money;
		this.carnum = carnum;
		this.productname = productname;
		this.kfdate = kfdate;
		this.status = status;
		this.createdate = createdate;
		this.createuser = createuser;
		this.updatedate = updatedate;
		this.updateuser = updateuser;
		this.upload = upload;
	}
	
	/**
	 * @return the upload
	 */
	public String getUpload() {
		return upload;
	}

	
	

	/**
	 * @param upload the upload to set
	 */
	public void setUpload(String upload) {
		this.upload = upload;
	}

	/**
	 * @return the agencykfkey
	 */
	public String getAgencykfkey() {
		return agencykfkey;
	}

	

	/**
	 * @param agencykfkey the agencykfkey to set
	 */
	public void setAgencykfkey(String agencykfkey) {
		this.agencykfkey = agencykfkey;
	}

	/**
	 * @return the gridkey
	 */
	public String getGridkey() {
		return gridkey;
	}

	/**
	 * @param gridkey the gridkey to set
	 */
	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	/**
	 * @return the agencyname
	 */
	public String getAgencyname() {
		return agencyname;
	}

	/**
	 * @param agencyname the agencyname to set
	 */
	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the carnum
	 */
	public String getCarnum() {
		return carnum;
	}

	/**
	 * @param carnum the carnum to set
	 */
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}

	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}

	/**
	 * @return the kfdate
	 */
	public String getKfdate() {
		return kfdate;
	}

	/**
	 * @param kfdate the kfdate to set
	 */
	public void setKfdate(String kfdate) {
		this.kfdate = kfdate;
	}

	/**
	 * @return the status2
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status2 the status2 to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the updatedata
	 */
	public String getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedata the updatedata to set
	 */
	public void setUpdatedate(String updatedata) {
		this.updatedate = updatedata;
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
	 * @return the persion
	 */
	public String getPersion() {
		return persion;
	}

	/**
	 * @param persion the persion to set
	 */
	public void setPersion(String persion) {
		this.persion = persion;
	}

	/**
	 * @return the business
	 */
	public String getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(String business) {
		this.business = business;
	}

	/**
	 * @return the isone
	 */
	public int getIsone() {
		return isone;
	}

	/**
	 * @param isone the isone to set
	 */
	public void setIsone(int isone) {
		this.isone = isone;
	}

	/**
	 * @return the coverterms
	 */
	public String getCoverterms() {
		return coverterms;
	}

	/**
	 * @param coverterms the coverterms to set
	 */
	public void setCoverterms(String coverterms) {
		this.coverterms = coverterms;
	}

	/**
	 * @return the supplyterms
	 */
	public String getSupplyterms() {
		return supplyterms;
	}

	/**
	 * @param supplyterms the supplyterms to set
	 */
	public void setSupplyterms(String supplyterms) {
		this.supplyterms = supplyterms;
	}

	/**
	 * @return the passdate
	 */
	public String getPassdate() {
		return passdate;
	}

	/**
	 * @param passdate the passdate to set
	 */
	public void setPassdate(String passdate) {
		this.passdate = passdate;
	}

}