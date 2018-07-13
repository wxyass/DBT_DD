package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstPromotionsmDaoImpl;


/**
 * MstPromotionsM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PROMOTIONS_M", daoClass=MstPromotionsmDaoImpl.class)
public class MstPromotionsM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String promotkey;
	@DatabaseField
	private String promottype;
	@DatabaseField
	private String promotcode;
	@DatabaseField
	private String promotname;
	@DatabaseField
	private String promotconten;
	@DatabaseField
	private String promotlevel;
	@DatabaseField
	private String startdate;
	@DatabaseField
	private String enddate;
	@DatabaseField
	private String status;
	@DatabaseField
	private String areaid;
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
	private String ispictype;
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;
	@DatabaseField(defaultValue = "0")
	private String deleteflag;

	// Constructors

	/** default constructor */
	public MstPromotionsM() {
	}

	/** minimal constructor */
	public MstPromotionsM(String promotkey) {
		this.promotkey = promotkey;
	}

	/** full constructor */
	public MstPromotionsM(String promotkey, String promottype, String promotcode, String promotname, String promotconten, String promotlevel,
			String startdate, String enddate, String status, String areaid, String sisconsistent, Date scondate, String padisconsistent,
			Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser,
			String ispictype, Date updatetime, String updateuser) {
		this.promotkey = promotkey;
		this.promottype = promottype;
		this.promotcode = promotcode;
		this.promotname = promotname;
		this.promotconten = promotconten;
		this.promotlevel = promotlevel;
		this.startdate = startdate;
		this.enddate = enddate;
		this.status = status;
		this.areaid = areaid;
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
		this.ispictype = ispictype;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getPromotkey() {
		return this.promotkey;
	}

	public void setPromotkey(String promotkey) {
		this.promotkey = promotkey;
	}

	public String getPromottype() {
		return this.promottype;
	}

	public void setPromottype(String promottype) {
		this.promottype = promottype;
	}

	public String getPromotcode() {
		return this.promotcode;
	}

	public void setPromotcode(String promotcode) {
		this.promotcode = promotcode;
	}

	public String getPromotname() {
		return this.promotname;
	}

	public void setPromotname(String promotname) {
		this.promotname = promotname;
	}

	public String getPromotconten() {
		return this.promotconten;
	}

	public void setPromotconten(String promotconten) {
		this.promotconten = promotconten;
	}

	public String getPromotlevel() {
		return this.promotlevel;
	}

	public void setPromotlevel(String promotlevel) {
		this.promotlevel = promotlevel;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
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

	/**
	 * @return the ispictype
	 */
	public String getIspictype() {
		return ispictype;
	}

	/**
	 * @param ispictype the ispictype to set
	 */
	public void setIspictype(String ispictype) {
		this.ispictype = ispictype;
	}
	
}