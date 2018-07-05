package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmRoleM entity. @author MyEclipse Persistence Tools
 */

public class CmmRoleM implements java.io.Serializable {

	// Fields

	private String roleid;
	private String rolename;
	private String roletype;
	private String roleexplain;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmRoleM() {
	}

	/** minimal constructor */
	public CmmRoleM(String roleid) {
		this.roleid = roleid;
	}

	/** full constructor */
	public CmmRoleM(String roleid, String rolename, String roletype,
			String roleexplain, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.roleid = roleid;
		this.rolename = rolename;
		this.roletype = roletype;
		this.roleexplain = roleexplain;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoletype() {
		return this.roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getRoleexplain() {
		return this.roleexplain;
	}

	public void setRoleexplain(String roleexplain) {
		this.roleexplain = roleexplain;
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