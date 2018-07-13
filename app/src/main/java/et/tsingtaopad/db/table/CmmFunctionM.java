package et.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmFunctionM entity. @author MyEclipse Persistence Tools
 */

public class CmmFunctionM implements java.io.Serializable {

	// Fields

	private String functionid;
	private String parentid;
	private String syscode;
	private String subsyscode;
	private String businesscode;
	private String funname;
	private String funexplain;
	private String funstate;
	private String starturl;
	private String startdate;
	private String enddate;
	private String picturepath;
	private String sequenceno;
	private String remarks;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmFunctionM() {
	}

	/** minimal constructor */
	public CmmFunctionM(String functionid) {
		this.functionid = functionid;
	}

	/** full constructor */
	public CmmFunctionM(String functionid, String parentid, String syscode,
			String subsyscode, String businesscode, String funname,
			String funexplain, String funstate, String starturl,
			String startdate, String enddate, String picturepath,
			String sequenceno, String remarks, String deleteflag, Long version,
			Date credate, String creuser, Date updatetime, String updateuser) {
		this.functionid = functionid;
		this.parentid = parentid;
		this.syscode = syscode;
		this.subsyscode = subsyscode;
		this.businesscode = businesscode;
		this.funname = funname;
		this.funexplain = funexplain;
		this.funstate = funstate;
		this.starturl = starturl;
		this.startdate = startdate;
		this.enddate = enddate;
		this.picturepath = picturepath;
		this.sequenceno = sequenceno;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSyscode() {
		return this.syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public String getSubsyscode() {
		return this.subsyscode;
	}

	public void setSubsyscode(String subsyscode) {
		this.subsyscode = subsyscode;
	}

	public String getBusinesscode() {
		return this.businesscode;
	}

	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}

	public String getFunname() {
		return this.funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public String getFunexplain() {
		return this.funexplain;
	}

	public void setFunexplain(String funexplain) {
		this.funexplain = funexplain;
	}

	public String getFunstate() {
		return this.funstate;
	}

	public void setFunstate(String funstate) {
		this.funstate = funstate;
	}

	public String getStarturl() {
		return this.starturl;
	}

	public void setStarturl(String starturl) {
		this.starturl = starturl;
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

	public String getPicturepath() {
		return this.picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getSequenceno() {
		return this.sequenceno;
	}

	public void setSequenceno(String sequenceno) {
		this.sequenceno = sequenceno;
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