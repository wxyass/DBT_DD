package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MstAgencyvisitMDaoImpl;


/**
 * MstAgencyvisitM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGENCYVISIT_M", daoClass = MstAgencyvisitMDaoImpl.class)
public class MstAgencyvisitM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String agevisitkey;
	@DatabaseField
	private String agencykey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String agevisitdate;
	@DatabaseField
	private String enddate;
	@DatabaseField
	private String userid;
	@DatabaseField
	private String visituser;
    @DatabaseField
	private String status;
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
	
	// 单击上传按钮的状态。0/null：未上单击上传按钮、 1：已单击上传按钮
    @DatabaseField(defaultValue = "0")
    private String uploadFlag;
	// Constructors

	/** default constructor */
	public MstAgencyvisitM() {
	}

	/** minimal constructor */
	public MstAgencyvisitM(String agevisitkey) {
		this.agevisitkey = agevisitkey;
	}

	/** full constructor */
	public MstAgencyvisitM(String agevisitkey, String agencykey, String gridkey, String agevisitdate, String enddate, String userid,
			String visituser, String status, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid,
			String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser,String uploadFlag) {
		this.agevisitkey = agevisitkey;
		this.agencykey = agencykey;
		this.gridkey = gridkey;
		this.agevisitdate = agevisitdate;
		this.enddate = enddate;
		this.userid = userid;
		this.visituser = visituser;
		this.status = status;
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
		this.uploadFlag = uploadFlag;
	}

	// Property accessors

	public String getAgevisitkey() {
		return this.agevisitkey;
	}

	public void setAgevisitkey(String agevisitkey) {
		this.agevisitkey = agevisitkey;
	}

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getAgevisitdate() {
		return this.agevisitdate;
	}

	public void setAgevisitdate(String agevisitdate) {
		this.agevisitdate = agevisitdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getVisituser() {
		return this.visituser;
	}

	public void setVisituser(String visituser) {
		this.visituser = visituser;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    public String getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

}