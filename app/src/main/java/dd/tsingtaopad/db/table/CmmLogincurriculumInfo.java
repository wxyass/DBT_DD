package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmLogincurriculumInfo entity. @author MyEclipse Persistence Tools
 */

public class CmmLogincurriculumInfo implements java.io.Serializable {

	// Fields

	private String curloingno;
	private String userid;
	private String terminalno;
	private String marid;
	private String loginip;
	private String logingroup;
	private Date logindate;
	private String loginmac;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmLogincurriculumInfo() {
	}

	/** minimal constructor */
	public CmmLogincurriculumInfo(String curloingno) {
		this.curloingno = curloingno;
	}

	/** full constructor */
	public CmmLogincurriculumInfo(String curloingno, String userid,
			String terminalno, String marid, String loginip, String logingroup,
			Date logindate, String loginmac, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.curloingno = curloingno;
		this.userid = userid;
		this.terminalno = terminalno;
		this.marid = marid;
		this.loginip = loginip;
		this.logingroup = logingroup;
		this.logindate = logindate;
		this.loginmac = loginmac;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getCurloingno() {
		return this.curloingno;
	}

	public void setCurloingno(String curloingno) {
		this.curloingno = curloingno;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTerminalno() {
		return this.terminalno;
	}

	public void setTerminalno(String terminalno) {
		this.terminalno = terminalno;
	}

	public String getMarid() {
		return this.marid;
	}

	public void setMarid(String marid) {
		this.marid = marid;
	}

	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getLogingroup() {
		return this.logingroup;
	}

	public void setLogingroup(String logingroup) {
		this.logingroup = logingroup;
	}

	public Date getLogindate() {
		return this.logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public String getLoginmac() {
		return this.loginmac;
	}

	public void setLoginmac(String loginmac) {
		this.loginmac = loginmac;
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