package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCheckstatusInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CHECKSTATUS_INFO")
public class MstCheckstatusInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String cstatuskey;
	@DatabaseField
	private String cstatuscode;
	@DatabaseField
	private String cstatusname;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String cstatustype;
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
	public MstCheckstatusInfo() {
	}

	/** minimal constructor */
	public MstCheckstatusInfo(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	/** full constructor */
	public MstCheckstatusInfo(String cstatuskey, String cstatuscode, String cstatusname, String checkkey, String cstatustype, String sisconsistent,
			Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag,
			BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.cstatuskey = cstatuskey;
		this.cstatuscode = cstatuscode;
		this.cstatusname = cstatusname;
		this.checkkey = checkkey;
		this.cstatustype = cstatustype;
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

	public String getCstatuskey() {
		return this.cstatuskey;
	}

	public void setCstatuskey(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	public String getCstatuscode() {
		return this.cstatuscode;
	}

	public void setCstatuscode(String cstatuscode) {
		this.cstatuscode = cstatuscode;
	}

	public String getCstatusname() {
		return this.cstatusname;
	}

	public void setCstatusname(String cstatusname) {
		this.cstatusname = cstatusname;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getCstatustype() {
		return this.cstatustype;
	}

	public void setCstatustype(String cstatustype) {
		this.cstatustype = cstatustype;
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