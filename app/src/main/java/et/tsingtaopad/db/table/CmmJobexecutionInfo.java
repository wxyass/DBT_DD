package et.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmJobexecutionInfo entity. @author MyEclipse Persistence Tools
 */

public class CmmJobexecutionInfo implements java.io.Serializable {

	// Fields

	private String recordid;
	private String jobmodel;
	private Date jobstart;
	private Date jobend;
	private String jobstatus;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmJobexecutionInfo() {
	}

	/** minimal constructor */
	public CmmJobexecutionInfo(String recordid) {
		this.recordid = recordid;
	}

	/** full constructor */
	public CmmJobexecutionInfo(String recordid, String jobmodel, Date jobstart,
			Date jobend, String jobstatus, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.recordid = recordid;
		this.jobmodel = jobmodel;
		this.jobstart = jobstart;
		this.jobend = jobend;
		this.jobstatus = jobstatus;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getRecordid() {
		return this.recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getJobmodel() {
		return this.jobmodel;
	}

	public void setJobmodel(String jobmodel) {
		this.jobmodel = jobmodel;
	}

	public Date getJobstart() {
		return this.jobstart;
	}

	public void setJobstart(Date jobstart) {
		this.jobstart = jobstart;
	}

	public Date getJobend() {
		return this.jobend;
	}

	public void setJobend(Date jobend) {
		this.jobend = jobend;
	}

	public String getJobstatus() {
		return this.jobstatus;
	}

	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
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