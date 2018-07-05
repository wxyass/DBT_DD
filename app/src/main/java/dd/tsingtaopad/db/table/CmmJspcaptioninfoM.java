package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmJspcaptioninfoM entity. @author MyEclipse Persistence Tools
 */

public class CmmJspcaptioninfoM implements java.io.Serializable {

	// Fields

	private String captionid;
	private String moduleid;
	private String sequenceno;
	private String caption;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmJspcaptioninfoM() {
	}

	/** minimal constructor */
	public CmmJspcaptioninfoM(String captionid) {
		this.captionid = captionid;
	}

	/** full constructor */
	public CmmJspcaptioninfoM(String captionid, String moduleid,
			String sequenceno, String caption, String remarks,
			String deleteflag, Long version, Date credate, String creuser,
			Date updatetime, String updateuser) {
		this.captionid = captionid;
		this.moduleid = moduleid;
		this.sequenceno = sequenceno;
		this.caption = caption;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getCaptionid() {
		return this.captionid;
	}

	public void setCaptionid(String captionid) {
		this.captionid = captionid;
	}

	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getSequenceno() {
		return this.sequenceno;
	}

	public void setSequenceno(String sequenceno) {
		this.sequenceno = sequenceno;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
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