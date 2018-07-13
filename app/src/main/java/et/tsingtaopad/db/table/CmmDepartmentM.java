package et.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmDepartmentM entity. @author MyEclipse Persistence Tools
 */

public class CmmDepartmentM implements java.io.Serializable {

	// Fields
	private String departmentid;
	private String departmentname;
	private String parentdep;
	private String describe;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmDepartmentM() {
	}

	/** minimal constructor */
	public CmmDepartmentM(String departmentid) {
		this.departmentid = departmentid;
	}

	/** full constructor */
	public CmmDepartmentM(String departmentid, String departmentname, String parentdep, String describe, String remarks, String deleteflag, Long version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.departmentid = departmentid;
		this.departmentname = departmentname;
		this.parentdep = parentdep;
		this.describe = describe;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return this.departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getParentdep() {
		return this.parentdep;
	}

	public void setParentdep(String parentdep) {
		this.parentdep = parentdep;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

}