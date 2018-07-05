package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmMessagetipsM entity. @author MyEclipse Persistence Tools
 */

public class CmmMessagetipsM implements java.io.Serializable {

	// Fields

	private String msgid;
	private String msgcontentzh;
	private String msgcontenten;
	private String msgcontentoh;
	private String moduleid;
	private String msgtpye;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmMessagetipsM() {
	}

	/** minimal constructor */
	public CmmMessagetipsM(String msgid) {
		this.msgid = msgid;
	}

	/** full constructor */
	public CmmMessagetipsM(String msgid, String msgcontentzh,
			String msgcontenten, String msgcontentoh, String moduleid,
			String msgtpye, String remarks, String deleteflag, Long version,
			Date credate, String creuser, Date updatetime, String updateuser) {
		this.msgid = msgid;
		this.msgcontentzh = msgcontentzh;
		this.msgcontenten = msgcontenten;
		this.msgcontentoh = msgcontentoh;
		this.moduleid = moduleid;
		this.msgtpye = msgtpye;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getMsgcontentzh() {
		return this.msgcontentzh;
	}

	public void setMsgcontentzh(String msgcontentzh) {
		this.msgcontentzh = msgcontentzh;
	}

	public String getMsgcontenten() {
		return this.msgcontenten;
	}

	public void setMsgcontenten(String msgcontenten) {
		this.msgcontenten = msgcontenten;
	}

	public String getMsgcontentoh() {
		return this.msgcontentoh;
	}

	public void setMsgcontentoh(String msgcontentoh) {
		this.msgcontentoh = msgcontentoh;
	}

	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getMsgtpye() {
		return this.msgtpye;
	}

	public void setMsgtpye(String msgtpye) {
		this.msgtpye = msgtpye;
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