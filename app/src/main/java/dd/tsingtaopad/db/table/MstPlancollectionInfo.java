package dd.tsingtaopad.db.table;

// default package

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPlancollectionInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PLANCOLLECTION_INFO")
public class MstPlancollectionInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String pcolitemkey;
	@DatabaseField
	private String pcheckkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String colitemkey;
	@DatabaseField(defaultValue = "0")
	private String plantype;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String termnames;
	@DatabaseField
	private String termlevel;
	@DatabaseField
	private Long termnum;
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
	private String ordernum;

	// Constructors
	public MstPlancollectionInfo(){}

	/** full constructor */
	public MstPlancollectionInfo(String pcolitemkey, String pcheckkey, String checkkey, String colitemkey, String plantype, String productkey, String termnames, Long termnum,String termlevel, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.pcolitemkey = pcolitemkey;
		this.pcheckkey = pcheckkey;
		this.checkkey = checkkey;
		this.colitemkey = colitemkey;
		this.plantype = plantype;
		this.productkey = productkey;
		this.termnames = termnames;
		this.termnum = termnum;
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
		this.termlevel = termlevel;
	}

	// Property accessors

	public String getPcolitemkey() {
		return this.pcolitemkey;
	}

	public void setPcolitemkey(String pcolitemkey) {
		this.pcolitemkey = pcolitemkey;
	}

	public String getPcheckkey() {
		return this.pcheckkey;
	}

	public void setPcheckkey(String pcheckkey) {
		this.pcheckkey = pcheckkey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getColitemkey() {
		return this.colitemkey;
	}

	public void setColitemkey(String colitemkey) {
		this.colitemkey = colitemkey;
	}

	public String getPlantype() {
		return this.plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getTermnames() {
		return this.termnames;
	}

	public void setTermnames(String termnames) {
		this.termnames = termnames;
	}

	public Long getTermnum() {
		return this.termnum;
	}

	public void setTermnum(Long termnum) {
		this.termnum = termnum;
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
	public String getTermlevel() {
		return termlevel;
	}

	public void setTermlevel(String termlevel) {
		this.termlevel = termlevel;
	}

	/**
	 * @return the ordernum
	 */
	public String getOrdernum() {
		return ordernum;
	}

	/**
	 * @param ordernum the ordernum to set
	 */
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
}