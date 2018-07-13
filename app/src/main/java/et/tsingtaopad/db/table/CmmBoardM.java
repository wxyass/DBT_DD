package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * CmmBoardM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "CMM_BOARD_M")
public class CmmBoardM implements java.io.Serializable {

	// Fields

	@DatabaseField(id = true, canBeNull = false)
	private String messageid;
	@DatabaseField
	private String messtitle;
	@DatabaseField
	private String messagedesc;
	@DatabaseField
	private String startdate;
	@DatabaseField
	private String enddate;
	@DatabaseField
	private String isfeedback;
	@DatabaseField
	private String ispublic;
	@DatabaseField
	private Long callcount;
	@DatabaseField
	private String torolecode;
	@DatabaseField
	private String comcode;
	@DatabaseField
	private String priorityflag;
	@DatabaseField
	private String remarks;
	@DatabaseField
	private String deleteflag;
	@DatabaseField
	private Integer version;
	@DatabaseField
	private Date credate;
	@DatabaseField
	private String creuser;
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;
	@DatabaseField
	private String username;

	// Constructors

	/** full constructor */
	public CmmBoardM(String messageid, String messtitle, String messagedesc, String startdate, String enddate, String isfeedback, String ispublic, Long callcount, String torolecode, String comcode, String priorityflag, String remarks, String deleteflag, Integer version, Date credate, String creuser, Date updatetime, String updateuser, String username) {
		this.messageid = messageid;
		this.messtitle = messtitle;
		this.messagedesc = messagedesc;
		this.startdate = startdate;
		this.enddate = enddate;
		this.isfeedback = isfeedback;
		this.ispublic = ispublic;
		this.callcount = callcount;
		this.torolecode = torolecode;
		this.comcode = comcode;
		this.priorityflag = priorityflag;
		this.remarks = remarks;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.username = username;
	}

	// Property accessors
	public CmmBoardM(String messageid) {
		this.messageid = messageid;
	}

	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getMesstitle() {
		return this.messtitle;
	}

	public void setMesstitle(String messtitle) {
		this.messtitle = messtitle;
	}

	public String getMessagedesc() {
		return this.messagedesc;
	}

	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
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

	public String getIsfeedback() {
		return this.isfeedback;
	}

	public void setIsfeedback(String isfeedback) {
		this.isfeedback = isfeedback;
	}

	public Long getCallcount() {
		return this.callcount;
	}

	public void setCallcount(Long callcount) {
		this.callcount = callcount;
	}

	public String getTorolecode() {
		return this.torolecode;
	}

	public void setTorolecode(String torolecode) {
		this.torolecode = torolecode;
	}

	public String getComcode() {
		return this.comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}

	public String getPriorityflag() {
		return this.priorityflag;
	}

	public void setPriorityflag(String priorityflag) {
		this.priorityflag = priorityflag;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
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

	public String getIspublic() {
		return ispublic;
	}

	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CmmBoardM() {
	}

}