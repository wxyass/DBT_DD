package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPlanforuserM entity. @author MyEclipse Persistence Tools
 */
//MST_PLANFORUSER_M(人员计划主表)
@DatabaseTable(tableName = "MST_PLANFORUSER_M")
public class MstPlanforuserM implements java.io.Serializable {

	// Fields

	@DatabaseField(id = true, canBeNull = false)
	private String plankey;//f
	@DatabaseField
	private String plantempkey;//
	@DatabaseField
	private String plantitle;//
	@DatabaseField
	private String userid;//
	@DatabaseField
	private String linekey;//
	@DatabaseField
	private String planamf;//
	@DatabaseField
	private String planamt;//
	@DatabaseField
	private String planpmf;//
	@DatabaseField
	private String planpmt;//
	@DatabaseField
	private String plandate;//
	@DatabaseField
	private String planstatus;//
	@DatabaseField
	private Long pscount;
	@DatabaseField
	private String plantype;
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
	private Date credate;//
	@DatabaseField
	private String creuser;//
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;

	// Constructors

	/** default constructor */
	public MstPlanforuserM() {
	}

	/** minimal constructor */
	public MstPlanforuserM(String plankey) {
		this.plankey = plankey;
	}

	/** full constructor */
	public MstPlanforuserM(String plankey, String plantempkey, String plantitle, String userid, String linekey, String planamf, String planamt, String planpmf, String planpmt,
			String plandate, String planstatus, Long pscount, String plantype, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks,
			String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.plankey = plankey;
		this.plantempkey = plantempkey;
		this.plantitle = plantitle;
		this.userid = userid;
		this.linekey = linekey;
		this.planamf = planamf;
		this.planamt = planamt;
		this.planpmf = planpmf;
		this.planpmt = planpmt;
		this.plandate = plandate;
		this.planstatus = planstatus;
		this.pscount = pscount;
		this.plantype = plantype;
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
		this.plantype = plantype;
	}

	// Property accessors

	public String getPlankey() {
		return this.plankey;
	}

	public void setPlankey(String plankey) {
		this.plankey = plankey;
	}

	public String getPlantempkey() {
		return this.plantempkey;
	}

	public void setPlantempkey(String plantempkey) {
		this.plantempkey = plantempkey;
	}

	public String getPlantitle() {
		return this.plantitle;
	}

	public void setPlantitle(String plantitle) {
		this.plantitle = plantitle;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLinekey() {
		return this.linekey;
	}

	public void setLinekey(String linekey) {
		this.linekey = linekey;
	}

	public String getPlanamf() {
		return this.planamf;
	}

	public void setPlanamf(String planamf) {
		this.planamf = planamf;
	}

	public String getPlanamt() {
		return this.planamt;
	}

	public void setPlanamt(String planamt) {
		this.planamt = planamt;
	}

	public String getPlanpmf() {
		return this.planpmf;
	}

	public void setPlanpmf(String planpmf) {
		this.planpmf = planpmf;
	}

	public String getPlanpmt() {
		return this.planpmt;
	}

	public void setPlanpmt(String planpmt) {
		this.planpmt = planpmt;
	}

	public String getPlandate() {
		return this.plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getPlanstatus() {
		return this.planstatus;
	}

	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}

	public Long getPscount() {
		return this.pscount;
	}

	public void setPscount(Long pscount) {
		this.pscount = pscount;
	}

	public String getPlantype() {
        return plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
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