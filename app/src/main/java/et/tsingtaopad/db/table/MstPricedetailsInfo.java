package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPricedetailsInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PRICEDETAILS_INFO")
public class MstPricedetailsInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	String detailskey;
	@DatabaseField
	String pricekey;
	@DatabaseField
	String productkey;
	@DatabaseField
	Double price;
	@DatabaseField
	String startdate;
	@DatabaseField
	String enddate;
	@DatabaseField
	String sisconsistent;
	@DatabaseField
	Date scondate;
	@DatabaseField(defaultValue = "0")
	String padisconsistent;
	@DatabaseField
	Date padcondate;
	@DatabaseField
	String comid;
	@DatabaseField
	String remarks;
	@DatabaseField
	String orderbyno;
	@DatabaseField
	BigDecimal version;
	@DatabaseField
	Date credate;
	@DatabaseField
	String creuser;
	@DatabaseField
	Date updatetime;
	@DatabaseField
	String updateuser;
	@DatabaseField(defaultValue = "0")
	private String deleteflag;

	// Constructors

	/** default constructor */
	public MstPricedetailsInfo() {
	}

	/** minimal constructor */
	public MstPricedetailsInfo(String detailskey) {
		this.detailskey = detailskey;
	}

	/** full constructor */
	public MstPricedetailsInfo(String detailskey, String pricekey, String productkey, Double price, String startdate, String enddate,
			String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno,
			String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.detailskey = detailskey;
		this.pricekey = pricekey;
		this.productkey = productkey;
		this.price = price;
		this.startdate = startdate;
		this.enddate = enddate;
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

	public String getDetailskey() {
		return this.detailskey;
	}

	public void setDetailskey(String detailskey) {
		this.detailskey = detailskey;
	}

	public String getPricekey() {
		return this.pricekey;
	}

	public void setPricekey(String pricekey) {
		this.pricekey = pricekey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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