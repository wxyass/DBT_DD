package dd.tsingtaopad.db.table;

// default package

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstSoftupdateM entity. @author MyEclipse Persistence Tools
 */

public class MstSoftupdateM implements java.io.Serializable {

	// Fields

	private String softkey;
	private String softversion;
	private BigDecimal softsize;
	private String softpath;
	private String softexplain;
	private String sisconsistent;
	private Date scondate;
	private String padisconsistent;
	private Date padcondate;
	private String comid;
	private String remarks;
	private String orderbyno;
	private String deleteflag;
	private BigDecimal version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public MstSoftupdateM() {
	}

	/** minimal constructor */
	public MstSoftupdateM(String softkey) {
		this.softkey = softkey;
	}

	/** full constructor */
	public MstSoftupdateM(String softkey, String softversion,
			BigDecimal softsize, String softpath, String softexplain,
			String sisconsistent, Date scondate, String padisconsistent,
			Date padcondate, String comid, String remarks, String orderbyno,
			String deleteflag, BigDecimal version, Date credate,
			String creuser, Date updatetime, String updateuser) {
		this.softkey = softkey;
		this.softversion = softversion;
		this.softsize = softsize;
		this.softpath = softpath;
		this.softexplain = softexplain;
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

	public String getSoftkey() {
		return this.softkey;
	}

	public void setSoftkey(String softkey) {
		this.softkey = softkey;
	}

	public String getSoftversion() {
		return this.softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public BigDecimal getSoftsize() {
		return this.softsize;
	}

	public void setSoftsize(BigDecimal softsize) {
		this.softsize = softsize;
	}

	public String getSoftpath() {
		return this.softpath;
	}

	public void setSoftpath(String softpath) {
		this.softpath = softpath;
	}

	public String getSoftexplain() {
		return this.softexplain;
	}

	public void setSoftexplain(String softexplain) {
		this.softexplain = softexplain;
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