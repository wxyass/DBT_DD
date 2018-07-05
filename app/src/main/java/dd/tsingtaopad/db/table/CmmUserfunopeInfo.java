package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmUserfunopeInfo entity. @author MyEclipse Persistence Tools
 */

public class CmmUserfunopeInfo implements java.io.Serializable {

	// Fields

	private CmmUserfunopeInfoId id;
	private String invalidflag;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmUserfunopeInfo() {
	}

	/** minimal constructor */
	public CmmUserfunopeInfo(CmmUserfunopeInfoId id) {
		this.id = id;
	}

	/** full constructor */
	public CmmUserfunopeInfo(CmmUserfunopeInfoId id, String invalidflag,
			String remarks, String deleteflag, Long version, Date credate,
			String creuser, Date updatetime, String updateuser) {
		this.id = id;
		this.invalidflag = invalidflag;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public CmmUserfunopeInfoId getId() {
		return this.id;
	}

	public void setId(CmmUserfunopeInfoId id) {
		this.id = id;
	}

	public String getInvalidflag() {
		return this.invalidflag;
	}

	public void setInvalidflag(String invalidflag) {
		this.invalidflag = invalidflag;
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

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
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