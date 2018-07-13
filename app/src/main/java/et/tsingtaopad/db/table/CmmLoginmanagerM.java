package et.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmLoginmanagerM entity. @author MyEclipse Persistence Tools
 */

public class CmmLoginmanagerM implements java.io.Serializable {

	// Fields

	private String userid;
	private String startdate;
	private String enddate;
	private Long loginnum;
	private Long errornum;
	private Date lastlogintime;
	private String lastloginip;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;
	private String password;
	private String nickname;

	// Constructors

	/** default constructor */
	public CmmLoginmanagerM() {
	}

	/** minimal constructor */
	public CmmLoginmanagerM(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public CmmLoginmanagerM(String userid, String startdate, String enddate,
			Long loginnum, Long errornum, Date lastlogintime,
			String lastloginip, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser, String password, String nickname) {
		this.userid = userid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.loginnum = loginnum;
		this.errornum = errornum;
		this.lastlogintime = lastlogintime;
		this.lastloginip = lastloginip;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.password = password;
		this.nickname = nickname;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getLoginnum() {
		return this.loginnum;
	}

	public void setLoginnum(Long loginnum) {
		this.loginnum = loginnum;
	}

	public Long getErrornum() {
		return this.errornum;
	}

	public void setErrornum(Long errornum) {
		this.errornum = errornum;
	}

	public Date getLastlogintime() {
		return this.lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return this.lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}