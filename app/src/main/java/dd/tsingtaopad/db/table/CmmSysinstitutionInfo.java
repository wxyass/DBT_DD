package dd.tsingtaopad.db.table;

import java.util.Date;

/**
 * CmmSysinstitutionInfo entity. @author MyEclipse Persistence Tools
 */

public class CmmSysinstitutionInfo implements java.io.Serializable {

	// Fields

	private String instid;
	private String instname;
	private String instshortname;
	private String province;
	private String city;
	private String county;
	private String towns;
	private String postcode;
	private String parentid;
	private String linktel;
	private String contact;
	private String fax;
	private String email;
	private String instpic;
	private String introduction;
	private String remarks;
	private String orderbyno;
	private String deleteflag;
	private Long version;
	private Date credate;
	private String creuser;
	private Date updatetime;
	private String updateuser;

	// Constructors

	/** default constructor */
	public CmmSysinstitutionInfo() {
	}

	/** minimal constructor */
	public CmmSysinstitutionInfo(String instid) {
		this.instid = instid;
	}

	/** full constructor */
	public CmmSysinstitutionInfo(String instid, String instname,
			String instshortname, String province, String city, String county,
			String towns, String postcode, String parentid, String linktel,
			String contact, String fax, String email, String instpic,
			String introduction, String remarks, String orderbyno,
			String deleteflag, Long version, Date credate, String creuser,
			Date updatetime, String updateuser) {
		this.instid = instid;
		this.instname = instname;
		this.instshortname = instshortname;
		this.province = province;
		this.city = city;
		this.county = county;
		this.towns = towns;
		this.postcode = postcode;
		this.parentid = parentid;
		this.linktel = linktel;
		this.contact = contact;
		this.fax = fax;
		this.email = email;
		this.instpic = instpic;
		this.introduction = introduction;
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

	public String getInstid() {
		return this.instid;
	}

	public void setInstid(String instid) {
		this.instid = instid;
	}

	public String getInstname() {
		return this.instname;
	}

	public void setInstname(String instname) {
		this.instname = instname;
	}

	public String getInstshortname() {
		return this.instshortname;
	}

	public void setInstshortname(String instshortname) {
		this.instshortname = instshortname;
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

	public String getTowns() {
		return this.towns;
	}

	public void setTowns(String towns) {
		this.towns = towns;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getLinktel() {
		return this.linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstpic() {
		return this.instpic;
	}

	public void setInstpic(String instpic) {
		this.instpic = instpic;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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