package et.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmOperateM entity. @author MyEclipse Persistence Tools
 */

public class CmmOperateM implements java.io.Serializable {

	// Fields

	private String operateid;
	private String operatename;
	private String operateexplain;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmOperateM() {
	}

	/** minimal constructor */
	public CmmOperateM(String operateid) {
		this.operateid = operateid;
	}

	/** full constructor */
	public CmmOperateM(String operateid, String operatename,
			String operateexplain, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.operateid = operateid;
		this.operatename = operatename;
		this.operateexplain = operateexplain;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getOperateid() {
		return this.operateid;
	}

	public void setOperateid(String operateid) {
		this.operateid = operateid;
	}

	public String getOperatename() {
		return this.operatename;
	}

	public void setOperatename(String operatename) {
		this.operatename = operatename;
	}

	public String getOperateexplain() {
		return this.operateexplain;
	}

	public void setOperateexplain(String operateexplain) {
		this.operateexplain = operateexplain;
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