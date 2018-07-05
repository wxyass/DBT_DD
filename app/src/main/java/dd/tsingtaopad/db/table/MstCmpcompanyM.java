package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MstCmpCompanyMDaoImpl;


/**
 * MstCmpcompanyM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CMPCOMPANY_M", daoClass = MstCmpCompanyMDaoImpl.class)
public class MstCmpcompanyM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String cmpcomkey;
	@DatabaseField
	private String cmpcomcode;
	@DatabaseField
	private String cmpcomname;
	@DatabaseField
	private String cmpcomdesc;
	@DatabaseField
	private String cmpcomstatus;
	@DatabaseField
	private String cmpcomlogopath;
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
	public MstCmpcompanyM() {
	}

	/** minimal constructor */
	public MstCmpcompanyM(String cmpcomkey) {
		this.cmpcomkey = cmpcomkey;
	}

	/** full constructor */
	public MstCmpcompanyM(String cmpcomkey, String cmpcomcode, String cmpcomname, String cmpcomdesc, String cmpcomstatus, String cmpcomlogopath,
			String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno,
			String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.cmpcomkey = cmpcomkey;
		this.cmpcomcode = cmpcomcode;
		this.cmpcomname = cmpcomname;
		this.cmpcomdesc = cmpcomdesc;
		this.cmpcomstatus = cmpcomstatus;
		this.cmpcomlogopath = cmpcomlogopath;
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

	public String getCmpcomkey() {
		return this.cmpcomkey;
	}

	public void setCmpcomkey(String cmpcomkey) {
		this.cmpcomkey = cmpcomkey;
	}

	public String getCmpcomcode() {
		return this.cmpcomcode;
	}

	public void setCmpcomcode(String cmpcomcode) {
		this.cmpcomcode = cmpcomcode;
	}

	public String getCmpcomname() {
		return this.cmpcomname;
	}

	public void setCmpcomname(String cmpcomname) {
		this.cmpcomname = cmpcomname;
	}

	public String getCmpcomdesc() {
		return this.cmpcomdesc;
	}

	public void setCmpcomdesc(String cmpcomdesc) {
		this.cmpcomdesc = cmpcomdesc;
	}

	public String getCmpcomstatus() {
		return this.cmpcomstatus;
	}

	public void setCmpcomstatus(String cmpcomstatus) {
		this.cmpcomstatus = cmpcomstatus;
	}

	public String getCmpcomlogopath() {
		return this.cmpcomlogopath;
	}

	public void setCmpcomlogopath(String cmpcomlogopath) {
		this.cmpcomlogopath = cmpcomlogopath;
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