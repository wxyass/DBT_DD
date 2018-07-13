package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstAgencysupplyInfoTemp entity. @author MyEclipse Persistence Tools
 */
//MST_AGENCYSUPPLY_INFO_TEMP(经销商供货关系信息表 临时表)
@DatabaseTable(tableName = "MIT_AGENCYSUPPLY_INFO")
public class MitAgencysupplyInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(id = true, canBeNull = false)
	private String asupplykey;
	@DatabaseField
	private String status;
	@DatabaseField
	private String inprice;
	@DatabaseField
	private String reprice;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String lowerkey;
	@DatabaseField
	private String lowertype;
	@DatabaseField
	private String upperkey;
	@DatabaseField
	private String uppertype;
	@DatabaseField
	private String siebelkey;
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
	public MitAgencysupplyInfo() {
	}

	/** minimal constructor */
	public MitAgencysupplyInfo(String asupplykey) {
		this.asupplykey = asupplykey;
	}

	/** full constructor */
	public MitAgencysupplyInfo(String asupplykey, String status, String inprice, String reprice, String productkey, String lowerkey,
                               String lowertype, String upperkey, String uppertype, String siebelkey, String sisconsistent, Date scondate, String padisconsistent,
                               Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser,
                               Date updatetime, String updateuser) {
		this.asupplykey = asupplykey;
		this.status = status;
		this.inprice = inprice;
		this.reprice = reprice;
		this.productkey = productkey;
		this.lowerkey = lowerkey;
		this.lowertype = lowertype;
		this.upperkey = upperkey;
		this.uppertype = uppertype;
		this.siebelkey = siebelkey;
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

	public String getAsupplykey() {
		return this.asupplykey;
	}

	public void setAsupplykey(String asupplykey) {
		this.asupplykey = asupplykey;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInprice() {
		return this.inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public String getReprice() {
		return this.reprice;
	}

	public void setReprice(String reprice) {
		this.reprice = reprice;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getLowerkey() {
		return this.lowerkey;
	}

	public void setLowerkey(String lowerkey) {
		this.lowerkey = lowerkey;
	}

	public String getLowertype() {
		return this.lowertype;
	}

	public void setLowertype(String lowertype) {
		this.lowertype = lowertype;
	}

	public String getUpperkey() {
		return this.upperkey;
	}

	public void setUpperkey(String upperkey) {
		this.upperkey = upperkey;
	}

	public String getUppertype() {
		return this.uppertype;
	}

	public void setUppertype(String uppertype) {
		this.uppertype = uppertype;
	}

	public String getSiebelkey() {
		return this.siebelkey;
	}

	public void setSiebelkey(String siebelkey) {
		this.siebelkey = siebelkey;
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