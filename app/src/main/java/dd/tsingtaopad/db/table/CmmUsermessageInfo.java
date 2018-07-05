package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmUsermessageInfo entity. @author MyEclipse Persistence Tools
 */

public class CmmUsermessageInfo implements java.io.Serializable {

	// Fields

	private String umsgid;
	private String userid;
	private String msgsender;
	private String msgstatus;
	private String msgtype;
	private String msgcaption;
	private String msgcontent;
	private Date senddate;
	private Date fristreaddate;
	private Date readdate;
	private String orderno;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmUsermessageInfo() {
	}

	/** minimal constructor */
	public CmmUsermessageInfo(String umsgid) {
		this.umsgid = umsgid;
	}

	/** full constructor */
	public CmmUsermessageInfo(String umsgid, String userid, String msgsender,
			String msgstatus, String msgtype, String msgcaption,
			String msgcontent, Date senddate, Date fristreaddate,
			Date readdate, String orderno, String remarks, String deleteflag,
			Long version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.umsgid = umsgid;
		this.userid = userid;
		this.msgsender = msgsender;
		this.msgstatus = msgstatus;
		this.msgtype = msgtype;
		this.msgcaption = msgcaption;
		this.msgcontent = msgcontent;
		this.senddate = senddate;
		this.fristreaddate = fristreaddate;
		this.readdate = readdate;
		this.orderno = orderno;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getUmsgid() {
		return this.umsgid;
	}

	public void setUmsgid(String umsgid) {
		this.umsgid = umsgid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMsgsender() {
		return this.msgsender;
	}

	public void setMsgsender(String msgsender) {
		this.msgsender = msgsender;
	}

	public String getMsgstatus() {
		return this.msgstatus;
	}

	public void setMsgstatus(String msgstatus) {
		this.msgstatus = msgstatus;
	}

	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgcaption() {
		return this.msgcaption;
	}

	public void setMsgcaption(String msgcaption) {
		this.msgcaption = msgcaption;
	}

	public String getMsgcontent() {
		return this.msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public Date getSenddate() {
		return this.senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public Date getFristreaddate() {
		return this.fristreaddate;
	}

	public void setFristreaddate(Date fristreaddate) {
		this.fristreaddate = fristreaddate;
	}

	public Date getReaddate() {
		return this.readdate;
	}

	public void setReaddate(Date readdate) {
		this.readdate = readdate;
	}

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
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