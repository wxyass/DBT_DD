package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CmmDatadicM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "CMM_DATADIC_M")
public class CmmDatadicM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String diccode;
	@DatabaseField
	private String dicname;
	@DatabaseField
	private String parentcode;
	@DatabaseField
	private String dicexplan;
	@DatabaseField
	private String dictype;
	@DatabaseField
	private String langcode;
	@DatabaseField
	private String langflag;
	@DatabaseField
	private String orderbyno;
	@DatabaseField
	private String remarks;
	@DatabaseField
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
	public CmmDatadicM() {
	}

	/** minimal constructor */
	public CmmDatadicM(String diccode) {
		this.diccode = diccode;
	}

	/** full constructor */
	public CmmDatadicM(String diccode, String dicname, String parentcode, String dicexplan, String dictype, String langcode, String langflag, String orderbyno, String remarks, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.diccode = diccode;
		this.dicname = dicname;
		this.parentcode = parentcode;
		this.dicexplan = dicexplan;
		this.dictype = dictype;
		this.langcode = langcode;
		this.langflag = langflag;
		this.orderbyno = orderbyno;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getDiccode() {
		return this.diccode;
	}

	public void setDiccode(String diccode) {
		this.diccode = diccode;
	}

	public String getDicname() {
		return this.dicname;
	}

	public void setDicname(String dicname) {
		this.dicname = dicname;
	}

	public String getParentcode() {
		return this.parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getDicexplan() {
		return this.dicexplan;
	}

	public void setDicexplan(String dicexplan) {
		this.dicexplan = dicexplan;
	}

	public String getDictype() {
		return this.dictype;
	}

	public void setDictype(String dictype) {
		this.dictype = dictype;
	}

	public String getLangcode() {
		return this.langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public String getLangflag() {
		return this.langflag;
	}

	public void setLangflag(String langflag) {
		this.langflag = langflag;
	}

	public String getOrderbyno() {
		return this.orderbyno;
	}

	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
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