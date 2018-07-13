package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstInvalidapplayInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_INVALIDAPPLAY_INFO")
public class MstInvalidapplayInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String applaykey;
	@DatabaseField
	private String visitkey;
	@DatabaseField
	private String terminalkey;
	@DatabaseField
	private String status;
	@DatabaseField
	private String visitdate;
	@DatabaseField
	private String applaytype;
	@DatabaseField
	private String applaycause;
	@DatabaseField
	private String content;
	@DatabaseField
	private String applayuser;
	@DatabaseField
	private String applaydate;
	@DatabaseField
	private String audituser;
	@DatabaseField
	private String auditdate;
	@DatabaseField
	private String auditcontent;
	@DatabaseField
	private String sisconsistent;
	@DatabaseField
	private Date scondate;
	@DatabaseField(defaultValue = "0")
	private String padisconsistent;
	@DatabaseField
	private Date padcondate;
	@DatabaseField
	private String comid;
	@DatabaseField
	private String remarks;
	@DatabaseField
	private String orderbyno;
	@DatabaseField(defaultValue = "0")
	private String deleteflag;
	@DatabaseField
	private BigDecimal version;
	@DatabaseField
	private Date credate;
	@DatabaseField
	private String creuser;
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;

	// Constructors

	/** default constructor */
	public MstInvalidapplayInfo() {
	}

	/** minimal constructor */
	public MstInvalidapplayInfo(String applaykey) {
		this.applaykey = applaykey;
	}

	/** full constructor */
	public MstInvalidapplayInfo(String applaykey, String visitkey, String terminalkey, String status, String visitdate, String applaytype, String applaycause, String content, String applayuser, String applaydate, String audituser, String auditdate, String auditcontent, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.applaykey = applaykey;
		this.visitkey = visitkey;
		this.terminalkey = terminalkey;
		this.status = status;
		this.visitdate = visitdate;
		this.applaytype = applaytype;
		this.applaycause = applaycause;
		this.content = content;
		this.applayuser = applayuser;
		this.applaydate = applaydate;
		this.audituser = audituser;
		this.auditdate = auditdate;
		this.auditcontent = auditcontent;
		this.sisconsistent = sisconsistent;
		this.scondate = scondate;
		this.padisconsistent = padisconsistent;
		this.padcondate = padcondate;
		this.comid = comid;
		this.remarks = remarks;
		this.orderbyno = orderbyno;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getApplaykey() {
		return this.applaykey;
	}

	public void setApplaykey(String applaykey) {
		this.applaykey = applaykey;
	}

	public String getVisitkey() {
		return this.visitkey;
	}

	public void setVisitkey(String visitkey) {
		this.visitkey = visitkey;
	}

	public String getTerminalkey() {
		return this.terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVisitdate() {
		return this.visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getApplaytype() {
		return this.applaytype;
	}

	public void setApplaytype(String applaytype) {
		this.applaytype = applaytype;
	}

	public String getApplaycause() {
		return this.applaycause;
	}

	public void setApplaycause(String applaycause) {
		this.applaycause = applaycause;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getApplayuser() {
		return this.applayuser;
	}

	public void setApplayuser(String applayuser) {
		this.applayuser = applayuser;
	}

	public String getApplaydate() {
		return this.applaydate;
	}

	public void setApplaydate(String applaydate) {
		this.applaydate = applaydate;
	}

	public String getAudituser() {
		return this.audituser;
	}

	public void setAudituser(String audituser) {
		this.audituser = audituser;
	}

	public String getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	public String getAuditcontent() {
		return this.auditcontent;
	}

	public void setAuditcontent(String auditcontent) {
		this.auditcontent = auditcontent;
	}

	public String getSisconsistent() {
		return this.sisconsistent;
	}

	public void setSisconsistent(String sisconsistent) {
		this.sisconsistent = sisconsistent;
	}

	public Date getScondate() {
		return this.scondate;
	}

	public void setScondate(Date scondate) {
		this.scondate = scondate;
	}

	public String getPadisconsistent() {
		return this.padisconsistent;
	}

	public void setPadisconsistent(String padisconsistent) {
		this.padisconsistent = padisconsistent;
	}

	public Date getPadcondate() {
		return this.padcondate;
	}

	public void setPadcondate(Date padcondate) {
		this.padcondate = padcondate;
	}

	public String getComid() {
		return this.comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderbyno() {
		return this.orderbyno;
	}

	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
	}

	public String getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
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