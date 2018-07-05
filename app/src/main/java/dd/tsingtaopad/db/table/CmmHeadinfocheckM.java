package dd.tsingtaopad.db.table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CmmHeadinfocheckM entity. @author MyEclipse Persistence Tools
 */

public class CmmHeadinfocheckM implements java.io.Serializable {

	// Fields

	private String usercode;
	private String password;
	private String logincomcode;
	private String systemcode;
	private String remarks;
	private String deleteflag;
	private BigDecimal version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmHeadinfocheckM() {
	}

	/** minimal constructor */
	public CmmHeadinfocheckM(String usercode) {
		this.usercode = usercode;
	}

	/** full constructor */
	public CmmHeadinfocheckM(String usercode, String password,
			String logincomcode, String systemcode, String remarks,
			String deleteflag, BigDecimal version, Date credate,
			String creuser, Date updatetime, String updateuser) {
		this.usercode = usercode;
		this.password = password;
		this.logincomcode = logincomcode;
		this.systemcode = systemcode;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogincomcode() {
		return this.logincomcode;
	}

	public void setLogincomcode(String logincomcode) {
		this.logincomcode = logincomcode;
	}

	public String getSystemcode() {
		return this.systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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