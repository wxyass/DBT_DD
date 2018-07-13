package et.tsingtaopad.db.table;

// default package

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstProductshowM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PRODUCTSHOW_M")
public class MstProductshowM implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String showkey;
	@DatabaseField
	private String proname;
	@DatabaseField
	private String propic;
	@DatabaseField
	private String prodetail;
	@DatabaseField
	private String showstatus;
	@DatabaseField
	private String norms;
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
	private String proprice;

	// Constructors

	/** default constructor */
	public MstProductshowM() {
	}

	/** minimal constructor */
	public MstProductshowM(String showkey) {
		this.showkey = showkey;
	}

	/** full constructor */
	public MstProductshowM(String showkey, String proname, String propic, String prodetail, String showstatus, String norms, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser, String proprice) {
		this.showkey = showkey;
		this.proname = proname;
		this.propic = propic;
		this.prodetail = prodetail;
		this.showstatus = showstatus;
		this.norms = norms;
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
		this.proprice = proprice;
	}

	// Property accessors

	public String getShowkey() {
		return this.showkey;
	}

	public void setShowkey(String showkey) {
		this.showkey = showkey;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getPropic() {
		return this.propic;
	}

	public void setPropic(String propic) {
		this.propic = propic;
	}

	public String getProdetail() {
		return this.prodetail;
	}

	public void setProdetail(String prodetail) {
		this.prodetail = prodetail;
	}

	public String getShowstatus() {
		return this.showstatus;
	}

	public void setShowstatus(String showstatus) {
		this.showstatus = showstatus;
	}

	public String getNorms() {
		return this.norms;
	}

	public void setNorms(String norms) {
		this.norms = norms;
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

	public String getProprice() {
		return proprice;
	}

	public void setProprice(String proprice) {
		this.proprice = proprice;
	}

}