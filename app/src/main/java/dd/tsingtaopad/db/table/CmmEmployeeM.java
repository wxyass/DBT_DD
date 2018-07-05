package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * CmmEmployeeM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "CMM_EMPLOYEE_M")
public class CmmEmployeeM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, columnName = "USERID", canBeNull = false)
	private String userid;
	@DatabaseField(columnName = "USERNAME")
	private String username;
	@DatabaseField(columnName = "GENDER")
	private String gender;
	@DatabaseField(columnName = "IDCODE")
	private String idcode;
	@DatabaseField(columnName = "TEL")
	private String tel;
	@DatabaseField(columnName = "MOBILE")
	private String mobile;
	@DatabaseField(columnName = "PROVINCE")
	private String province;
	@DatabaseField(columnName = "CITY")
	private String city;
	@DatabaseField(columnName = "COUNTY")
	private String county;
	@DatabaseField(columnName = "TOWNS")
	private String towns;
	@DatabaseField(columnName = "ADDRESS")
	private String address;
	@DatabaseField(columnName = "USERENGNAME")
	private String userengname;
	@DatabaseField(columnName = "FAXNO")
	private String faxno;
	@DatabaseField(columnName = "BIRTHDAY")
	private String birthday;
	@DatabaseField(columnName = "PHOTONAME")
	private String photoname;
	@DatabaseField(columnName = "PHOTOPATH")
	private String photopath;
	@DatabaseField(columnName = "EMAIL")
	private String email;
	@DatabaseField(columnName = "STATUS")
	private String status;
	@DatabaseField(columnName = "POSITIONID")
	private String positionid;
	@DatabaseField(columnName = "DEPARTMENTID")
	private String departmentid;
	@DatabaseField(columnName = "ISCHECK")
	private String ischeck;
	@DatabaseField(columnName = "ENDDATE")
	private String enddate;
	@DatabaseField(columnName = "LEAVEREASON")
	private String leavereason;
	@DatabaseField(columnName = "LOCKREASON")
	private String lockreason;
	@DatabaseField(columnName = "LOCKDATE")
	private String lockdate;
	@DatabaseField(columnName = "UNLOCKDATE")
	private String unlockdate;
	@DatabaseField(columnName = "REMARKS")
	private String remarks;
	@DatabaseField(columnName = "DELETEFLAG")
	private String deleteflag;
	@DatabaseField(columnName = "VERSION")
	private Long version;
	@DatabaseField(columnName = "CREDATE")
	private Date credate;
	@DatabaseField(columnName = "CREUSER")
	private String creuser;
	@DatabaseField(columnName = "UPDATETIME")
	private Date updatetime;
	@DatabaseField(columnName = "UPDATEUSER")
	private String updateuser;
	@DatabaseField(columnName = "GRIDID")
	private String gridId;

	// Constructors

	/** default constructor */
	public CmmEmployeeM() {
	}

	/** minimal constructor */
	public CmmEmployeeM(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public CmmEmployeeM(String userid, String username, String gender, String idcode, String tel, String mobile, String province, String city, String county, String towns, String address, String userengname, String faxno, String birthday, String photoname, String photopath, String email, String status, String positionid, String departmentid, String ischeck, String enddate, String leavereason, String lockreason, String lockdate, String unlockdate, String remarks, String deleteflag, Long version, Date credate, String creuser, Date updatetime, String updateuser, String gridId) {
		this.userid = userid;
		this.username = username;
		this.gender = gender;
		this.idcode = idcode;
		this.tel = tel;
		this.mobile = mobile;
		this.province = province;
		this.city = city;
		this.county = county;
		this.towns = towns;
		this.address = address;
		this.userengname = userengname;
		this.faxno = faxno;
		this.birthday = birthday;
		this.photoname = photoname;
		this.photopath = photopath;
		this.email = email;
		this.status = status;
		this.positionid = positionid;
		this.departmentid = departmentid;
		this.ischeck = ischeck;
		this.enddate = enddate;
		this.leavereason = leavereason;
		this.lockreason = lockreason;
		this.lockdate = lockdate;
		this.unlockdate = unlockdate;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.gridId = gridId;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdcode() {
		return this.idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTowns() {
		return this.towns;
	}

	public void setTowns(String towns) {
		this.towns = towns;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserengname() {
		return this.userengname;
	}

	public void setUserengname(String userengname) {
		this.userengname = userengname;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhotoname() {
		return this.photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getPhotopath() {
		return this.photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPositionid() {
		return this.positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

	public String getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getLeavereason() {
		return this.leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public String getLockreason() {
		return this.lockreason;
	}

	public void setLockreason(String lockreason) {
		this.lockreason = lockreason;
	}

	public String getLockdate() {
		return this.lockdate;
	}

	public void setLockdate(String lockdate) {
		this.lockdate = lockdate;
	}

	public String getUnlockdate() {
		return this.unlockdate;
	}

	public void setUnlockdate(String unlockdate) {
		this.unlockdate = unlockdate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCredate() {
		return this.credate;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

	public String getCreuser() {
		return this.creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getGridId() {
		return this.gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

}