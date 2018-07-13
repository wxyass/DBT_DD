package et.tsingtaopad.db.table;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPlantempcheckInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PLANTEMPCHECK_INFO")
public class MstPlantempcheckInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(id = true, canBeNull = false)
	private String plancheckkey;
	@DatabaseField
	private String plantempkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String checktype;
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

	// Constructors

	/** default constructor */
	public MstPlantempcheckInfo() {
	}

	/** minimal constructor */
	public MstPlantempcheckInfo(String plancheckkey) {
		this.plancheckkey = plancheckkey;
	}

	/** full constructor */
	public MstPlantempcheckInfo(String plancheckkey, String plantempkey, String checkkey, String checktype, String sisconsistent, Date scondate, String padisconsistent,
			Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.plancheckkey = plancheckkey;
		this.plantempkey = plantempkey;
		this.checkkey = checkkey;
		this.checktype = checktype;
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

	public String getPlancheckkey() {
		return this.plancheckkey;
	}

	public void setPlancheckkey(String plancheckkey) {
		this.plancheckkey = plancheckkey;
	}

	public String getPlantempkey() {
		return this.plantempkey;
	}

	public void setPlantempkey(String plantempkey) {
		this.plantempkey = plantempkey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getChecktype() {
		return this.checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
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