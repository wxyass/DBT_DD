package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MstAgencyinfoMDaoImpl;


/**
 * MstAgencyinfoM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGENCYINFO_M", daoClass = MstAgencyinfoMDaoImpl.class)
public class MstAgencyinfoM implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String agencykey;
	@DatabaseField
	private String agencycode;
	@DatabaseField
	private String agencyname;
	@DatabaseField
	private String agencystatus;
	@DatabaseField
	private String agencyparent;
	@DatabaseField
	private String agencytype;
	@DatabaseField
	private String agencylevel;
	@DatabaseField
	private String isfranchise;
	@DatabaseField
	private String customertype;
	@DatabaseField
	private String pricekey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String province;
	@DatabaseField
	private String city;
	@DatabaseField
	private String county;
	@DatabaseField
	private String address;
	@DatabaseField
	private String beerstartdate;
	@DatabaseField
	private String miansell;
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
	public MstAgencyinfoM() {
	}

	/** minimal constructor */
	public MstAgencyinfoM(String agencykey) {
		this.agencykey = agencykey;
	}

	/** full constructor */
	public MstAgencyinfoM(String agencykey, String agencycode, String agencyname, String agencystatus, String agencyparent, String agencytype,
			String agencylevel, String isfranchise, String customertype, String pricekey, String gridkey, String province, String city,
			String county, String address, String beerstartdate, String miansell, String contact, String gender, String mobile, String sisconsistent,
			Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag,
			BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.agencykey = agencykey;
		this.agencycode = agencycode;
		this.agencyname = agencyname;
		this.agencystatus = agencystatus;
		this.agencyparent = agencyparent;
		this.agencytype = agencytype;
		this.agencylevel = agencylevel;
		this.isfranchise = isfranchise;
		this.customertype = customertype;
		this.pricekey = pricekey;
		this.gridkey = gridkey;
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.beerstartdate = beerstartdate;
		this.miansell = miansell;
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

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public String getAgencycode() {
		return this.agencycode;
	}

	public void setAgencycode(String agencycode) {
		this.agencycode = agencycode;
	}

	public String getAgencyname() {
		return this.agencyname;
	}

	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}

	public String getAgencystatus() {
		return this.agencystatus;
	}

	public void setAgencystatus(String agencystatus) {
		this.agencystatus = agencystatus;
	}

	public String getAgencyparent() {
		return this.agencyparent;
	}

	public void setAgencyparent(String agencyparent) {
		this.agencyparent = agencyparent;
	}

	public String getAgencytype() {
		return this.agencytype;
	}

	public void setAgencytype(String agencytype) {
		this.agencytype = agencytype;
	}

	public String getAgencylevel() {
		return this.agencylevel;
	}

	public void setAgencylevel(String agencylevel) {
		this.agencylevel = agencylevel;
	}

	public String getIsfranchise() {
		return this.isfranchise;
	}

	public void setIsfranchise(String isfranchise) {
		this.isfranchise = isfranchise;
	}

	public String getCustomertype() {
		return this.customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getPricekey() {
		return this.pricekey;
	}

	public void setPricekey(String pricekey) {
		this.pricekey = pricekey;
	}

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBeerstartdate() {
		return this.beerstartdate;
	}

	public void setBeerstartdate(String beerstartdate) {
		this.beerstartdate = beerstartdate;
	}

	public String getMiansell() {
		return this.miansell;
	}

	public void setMiansell(String miansell) {
		this.miansell = miansell;
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