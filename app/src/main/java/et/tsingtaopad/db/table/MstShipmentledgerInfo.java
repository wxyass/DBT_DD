package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstShipmentledgerInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_SHIPMENTLEDGER_INFO")
public class MstShipmentledgerInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	String ledgerkey;
	@DatabaseField
	String agencykey;
	@DatabaseField
	String terminalkey;
	@DatabaseField
	String productkey;
	@DatabaseField
	String slcount;
	@DatabaseField
	String sldate;
	@DatabaseField
	String price;
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
	public MstShipmentledgerInfo() {
	}

	/** minimal constructor */
	public MstShipmentledgerInfo(String ledgerkey) {
		this.ledgerkey = ledgerkey;
	}

	/** full constructor */
	public MstShipmentledgerInfo(String ledgerkey, String agencykey, String terminalkey, String productkey, String slcount, String sldate,
			String price, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks,
			String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.ledgerkey = ledgerkey;
		this.agencykey = agencykey;
		this.terminalkey = terminalkey;
		this.productkey = productkey;
		this.slcount = slcount;
		this.sldate = sldate;
		this.price = price;
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

	public String getLedgerkey() {
		return this.ledgerkey;
	}

	public void setLedgerkey(String ledgerkey) {
		this.ledgerkey = ledgerkey;
	}

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public String getTerminalkey() {
		return this.terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getSlcount() {
		return this.slcount;
	}

	public void setSlcount(String slcount) {
		this.slcount = slcount;
	}

	public String getSldate() {
		return this.sldate;
	}

	public void setSldate(String sldate) {
		this.sldate = sldate;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
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