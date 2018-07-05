package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCmpbrandsM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CMPBRANDS_M")
public class MstCmpbrandsM implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String cmpbrandkey;
	@DatabaseField
	private String cmpcomkey;
	@DatabaseField
	private String cmpbrandcode;
	@DatabaseField
	private String cmpbrandname;
	@DatabaseField
	private String cmpdesc;
	@DatabaseField
	private String status;
	@DatabaseField
	private String logopath;
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
	@DatabaseField(defaultValue = "0")
	private String deleteflag;

	// Constructors

	/** default constructor */
	public MstCmpbrandsM() {
	}

	/** minimal constructor */
	public MstCmpbrandsM(String cmpbrandkey) {
		this.cmpbrandkey = cmpbrandkey;
	}

	/** full constructor */
	public MstCmpbrandsM(String cmpbrandkey, String cmpcomkey, String cmpbrandcode, String cmpbrandname, String cmpdesc, String status,
			String logopath, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks,
			String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.cmpbrandkey = cmpbrandkey;
		this.cmpcomkey = cmpcomkey;
		this.cmpbrandcode = cmpbrandcode;
		this.cmpbrandname = cmpbrandname;
		this.cmpdesc = cmpdesc;
		this.status = status;
		this.logopath = logopath;
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

	public String getCmpbrandkey() {
		return this.cmpbrandkey;
	}

	public void setCmpbrandkey(String cmpbrandkey) {
		this.cmpbrandkey = cmpbrandkey;
	}

	public String getCmpcomkey() {
		return this.cmpcomkey;
	}

	public void setCmpcomkey(String cmpcomkey) {
		this.cmpcomkey = cmpcomkey;
	}

	public String getCmpbrandcode() {
		return this.cmpbrandcode;
	}

	public void setCmpbrandcode(String cmpbrandcode) {
		this.cmpbrandcode = cmpbrandcode;
	}

	public String getCmpbrandname() {
		return this.cmpbrandname;
	}

	public void setCmpbrandname(String cmpbrandname) {
		this.cmpbrandname = cmpbrandname;
	}

	public String getCmpdesc() {
		return this.cmpdesc;
	}

	public void setCmpdesc(String cmpdesc) {
		this.cmpdesc = cmpdesc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogopath() {
		return this.logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
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