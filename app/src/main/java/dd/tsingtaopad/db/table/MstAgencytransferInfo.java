package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstAgencytransferInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGENCYTRANSFER_INFO")
public class MstAgencytransferInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(id = true, canBeNull = false)
	private String trankey;
	@DatabaseField
	private String agevisitkey;
	@DatabaseField
	private String agencykey;
	@DatabaseField
	private String tagencykey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String trandate;
	@DatabaseField
	private Double tranin;
	@DatabaseField
	private Double tranout;
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
	public MstAgencytransferInfo() {
	}

	/** minimal constructor */
	public MstAgencytransferInfo(String trankey) {
		this.trankey = trankey;
	}

	/** full constructor */
	public MstAgencytransferInfo(String trankey, String agevisitkey, String agencykey, String tagencykey, String productkey, String trandate,
			Double tranin, Double tranout, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid,
			String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.trankey = trankey;
		this.agevisitkey = agevisitkey;
		this.agencykey = agencykey;
		this.tagencykey = tagencykey;
		this.productkey = productkey;
		this.trandate = trandate;
		this.tranin = tranin;
		this.tranout = tranout;
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

	public String getTrankey() {
		return this.trankey;
	}

	public void setTrankey(String trankey) {
		this.trankey = trankey;
	}

	public String getAgevisitkey() {
		return this.agevisitkey;
	}

	public void setAgevisitkey(String agevisitkey) {
		this.agevisitkey = agevisitkey;
	}

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public String getTagencykey() {
		return this.tagencykey;
	}

	public void setTagencykey(String tagencykey) {
		this.tagencykey = tagencykey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getTrandate() {
		return this.trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public Double getTranin() {
		return this.tranin;
	}

	public void setTranin(Double tranin) {
		this.tranin = tranin;
	}

	public Double getTranout() {
		return this.tranout;
	}

	public void setTranout(Double tranout) {
		this.tranout = tranout;
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