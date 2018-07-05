package dd.tsingtaopad.db.table;

// default package

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCmpagencyInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CMPAGENCY_INFO")
public class MstCmpagencyInfo implements java.io.Serializable {

	// Fields

    @DatabaseField(canBeNull = false, id = true)
	private String cmpagencykey;
    @DatabaseField
	private String cmpagencycode;
    @DatabaseField
	private String cmpagencyname;
    @DatabaseField
	private String cmpagencystatus;
    @DatabaseField
	private String cmpagencydetail;
    @DatabaseField
	private String areaid;
    @DatabaseField
	private String contact;
    @DatabaseField
	private String gender;
    @DatabaseField
	private String mobile;
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
	private BigDecimal version;
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
	public MstCmpagencyInfo() {
	}

	/** minimal constructor */
	public MstCmpagencyInfo(String cmpagencykey) {
		this.cmpagencykey = cmpagencykey;
	}

	/** full constructor */
	public MstCmpagencyInfo(String cmpagencykey, String cmpagencycode,
			String cmpagencyname, String cmpagencystatus,
			String cmpagencydetail, String areaid, String contact,
			String gender, String mobile, String sisconsistent, Date scondate,
			String padisconsistent, Date padcondate, String comid,
			String remarks, String orderbyno, String deleteflag,
			BigDecimal version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.cmpagencykey = cmpagencykey;
		this.cmpagencycode = cmpagencycode;
		this.cmpagencyname = cmpagencyname;
		this.cmpagencystatus = cmpagencystatus;
		this.cmpagencydetail = cmpagencydetail;
		this.areaid = areaid;
		this.contact = contact;
		this.gender = gender;
		this.mobile = mobile;
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

	public String getCmpagencykey() {
		return this.cmpagencykey;
	}

	public void setCmpagencykey(String cmpagencykey) {
		this.cmpagencykey = cmpagencykey;
	}

	public String getCmpagencycode() {
		return this.cmpagencycode;
	}

	public void setCmpagencycode(String cmpagencycode) {
		this.cmpagencycode = cmpagencycode;
	}

	public String getCmpagencyname() {
		return this.cmpagencyname;
	}

	public void setCmpagencyname(String cmpagencyname) {
		this.cmpagencyname = cmpagencyname;
	}

	public String getCmpagencystatus() {
		return this.cmpagencystatus;
	}

	public void setCmpagencystatus(String cmpagencystatus) {
		this.cmpagencystatus = cmpagencystatus;
	}

	public String getCmpagencydetail() {
		return this.cmpagencydetail;
	}

	public void setCmpagencydetail(String cmpagencydetail) {
		this.cmpagencydetail = cmpagencydetail;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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