package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * MstPlanrouteInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PLANROUTE_INFO")
public class MstPlanrouteInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String planroutekey;
	@DatabaseField
	private String plankey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String sisconsistent;
	@DatabaseField
	private Date scondate;
	@DatabaseField
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

	// Constructors

	/** default constructor */
	public MstPlanrouteInfo() {
	}

	/** minimal constructor */
	public MstPlanrouteInfo(String planroutekey) {
		this.planroutekey = planroutekey;
	}

	/** full constructor */
	public MstPlanrouteInfo(String planroutekey, String plankey, String checkkey, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, Integer version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.planroutekey = planroutekey;
		this.plankey = plankey;
		this.checkkey = checkkey;
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

	public String getPlanroutekey() {
		return this.planroutekey;
	}

	public void setPlanroutekey(String planroutekey) {
		this.planroutekey = planroutekey;
	}

	public String getPlankey() {
		return this.plankey;
	}

	public void setPlankey(String plankey) {
		this.plankey = plankey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
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

	public Integer getVersion() {
		return this.version;
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

}