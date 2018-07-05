package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MstProductMDaoImpl;


/**
 * MstProductM entity. @author MyEclipse Persistence Tools
 */

@DatabaseTable(tableName = "MST_PRODUCT_M", daoClass = MstProductMDaoImpl.class)
public class MstProductM implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String productkey;
	@DatabaseField
	private String procode;
	@DatabaseField
	private String proname;
	@DatabaseField
	private String prosname;
	@DatabaseField
	private String protype;
	@DatabaseField
	private String brandclasskey;
	@DatabaseField
	private String brandserieskey;
	@DatabaseField
	private String centerkey;
	@DatabaseField
	private String detailserieskey;
	@DatabaseField
	private String status;
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
	public MstProductM() {
	}

	/** minimal constructor */
	public MstProductM(String productkey) {
		this.productkey = productkey;
	}

	/** full constructor */
	public MstProductM(String productkey, String procode, String proname, String prosname, String protype, String brandclasskey, String brandserieskey, String centerkey, String detailserieskey, String status, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.productkey = productkey;
		this.procode = procode;
		this.proname = proname;
		this.prosname = prosname;
		this.protype = protype;
		this.brandclasskey = brandclasskey;
		this.brandserieskey = brandserieskey;
		this.centerkey = centerkey;
		this.detailserieskey = detailserieskey;
		this.status = status;
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

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getProcode() {
		return this.procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getProsname() {
		return this.prosname;
	}

	public void setProsname(String prosname) {
		this.prosname = prosname;
	}

	public String getProtype() {
		return this.protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}

	public String getBrandclasskey() {
		return this.brandclasskey;
	}

	public void setBrandclasskey(String brandclasskey) {
		this.brandclasskey = brandclasskey;
	}

	public String getBrandserieskey() {
		return this.brandserieskey;
	}

	public void setBrandserieskey(String brandserieskey) {
		this.brandserieskey = brandserieskey;
	}

	public String getCenterkey() {
		return this.centerkey;
	}

	public void setCenterkey(String centerkey) {
		this.centerkey = centerkey;
	}

	public String getDetailserieskey() {
		return this.detailserieskey;
	}

	public void setDetailserieskey(String detailserieskey) {
		this.detailserieskey = detailserieskey;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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