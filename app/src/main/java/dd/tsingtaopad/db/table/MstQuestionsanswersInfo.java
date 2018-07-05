package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstQuestionsanswersInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_QUESTIONSANSWERS_INFO")
public class MstQuestionsanswersInfo implements java.io.Serializable {

	// Fields
	
	@DatabaseField(canBeNull = false, id = true)
	private String qakey;
	@DatabaseField
	private String qaqcontent;
	@DatabaseField
	private String qaquser;
	@DatabaseField
	private String qaqdate;
	@DatabaseField
	private String qatype;
	@DatabaseField
	private String qaacontent;
	@DatabaseField
	private String qaauserid;
	@DatabaseField
	private String qaadate;
	@DatabaseField
	private String qaadegree;
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
	@DatabaseField
	private String mobile;

	// Constructors

	/** default constructor */
	public MstQuestionsanswersInfo() {
	}

	/** minimal constructor */
	public MstQuestionsanswersInfo(String qakey) {
		this.qakey = qakey;
	}

	/** full constructor */
	public MstQuestionsanswersInfo(String qakey, String qaqcontent, String qaquser, String qaqdate, String qatype, String qaacontent, String qaauserid, String qaadate, String qaadegree, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser, String mobile) {
		this.qakey = qakey;
		this.qaqcontent = qaqcontent;
		this.qaquser = qaquser;
		this.qaqdate = qaqdate;
		this.qatype = qatype;
		this.qaacontent = qaacontent;
		this.qaauserid = qaauserid;
		this.qaadate = qaadate;
		this.qaadegree = qaadegree;
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
		this.mobile = mobile;
	}

	// Property accessors

	public String getQakey() {
		return this.qakey;
	}

	public void setQakey(String qakey) {
		this.qakey = qakey;
	}

	public String getQaqcontent() {
		return this.qaqcontent;
	}

	public void setQaqcontent(String qaqcontent) {
		this.qaqcontent = qaqcontent;
	}

	public String getQaquser() {
		return this.qaquser;
	}

	public void setQaquser(String qaquser) {
		this.qaquser = qaquser;
	}

	public String getQaqdate() {
		return this.qaqdate;
	}

	public void setQaqdate(String qaqdate) {
		this.qaqdate = qaqdate;
	}

	public String getQatype() {
		return this.qatype;
	}

	public void setQatype(String qatype) {
		this.qatype = qatype;
	}

	public String getQaacontent() {
		return this.qaacontent;
	}

	public void setQaacontent(String qaacontent) {
		this.qaacontent = qaacontent;
	}

	public String getQaauserid() {
		return this.qaauserid;
	}

	public void setQaauserid(String qaauserid) {
		this.qaauserid = qaauserid;
	}

	public String getQaadate() {
		return this.qaadate;
	}

	public void setQaadate(String qaadate) {
		this.qaadate = qaadate;
	}

	public String getQaadegree() {
		return this.qaadegree;
	}

	public void setQaadegree(String qaadegree) {
		this.qaadegree = qaadegree;
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}