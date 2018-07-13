package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstTerminalinfoMTempDaoImpl;


/**
 * MstTerminalinfoMTemp entity. @author MyEclipse Persistence Tools
 * 终端信息临时表
 */
@DatabaseTable(tableName = "MST_TERMINALINFO_M_TEMP", daoClass = MstTerminalinfoMTempDaoImpl.class)
public class MstTerminalinfoMTemp implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String terminalkey;
	@DatabaseField
	private String routekey;
	@DatabaseField
	private String terminalcode;
	@DatabaseField
	private String terminalname;
	@DatabaseField
	private String province;
	@DatabaseField
	private String city;
	@DatabaseField
	private String county;
	@DatabaseField
	private String address;
	@DatabaseField
	private String contact;
	@DatabaseField
	private String mobile;
	@DatabaseField
	private String tlevel;
	@DatabaseField
	private String sequence;
	@DatabaseField
	private String cycle;
	@DatabaseField
	private String hvolume;
	@DatabaseField
	private String mvolume;
	@DatabaseField
	private String pvolume;//
	@DatabaseField
	private String lvolume;
	@DatabaseField
	private String status;//0 新增未审核 1 有效 2 无效（通过审核设置） 3 申请未审核(有效->无效)
	@DatabaseField
	private String sellchannel;
	@DatabaseField
	private String mainchannel;
	@DatabaseField
	private String minorchannel;
	@DatabaseField
	private String areatype;
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
	private Date credate;
	@DatabaseField
	private String creuser;
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;

	@DatabaseField
	private String selftreaty;
	@DatabaseField
	private String cmpselftreaty;
	@DatabaseField
	private String ifminedate;//店招时间
	@DatabaseField
	private String ifmine;//店招

	// Constructors

	/** default constructor */
	public MstTerminalinfoMTemp() {
	}

	/** minimal constructor */
	public MstTerminalinfoMTemp(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	/** full constructor */
	public MstTerminalinfoMTemp(String terminalkey, String routekey, String terminalcode, String terminalname, String province, String city,
                                String county, String address, String contact, String mobile, String tlevel, String sequence, String cycle, String hvolume,
                                String mvolume, String pvolume, String lvolume, String status, String sellchannel, String mainchannel, String minorchannel, String areatype,
                                String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno,
                                String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.terminalkey = terminalkey;
		this.routekey = routekey;
		this.terminalcode = terminalcode;
		this.terminalname = terminalname;
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.contact = contact;
		this.mobile = mobile;
		this.tlevel = tlevel;
		this.sequence = sequence;
		this.cycle = cycle;
		this.hvolume = hvolume;
		this.mvolume = mvolume;
		this.pvolume = pvolume;
		this.lvolume = lvolume;
		this.status = status;
		this.sellchannel = sellchannel;
		this.mainchannel = mainchannel;
		this.minorchannel = minorchannel;
		this.areatype = areatype;
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

	public String getTerminalkey() {
		return this.terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getRoutekey() {
		return this.routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getTerminalcode() {
		return this.terminalcode;
	}

	public void setTerminalcode(String terminalcode) {
		this.terminalcode = terminalcode;
	}

	public String getTerminalname() {
		return this.terminalname;
	}
	//终端名称

	public void setTerminalname(String terminalname) {
		this.terminalname = terminalname;
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

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTlevel() {
		return this.tlevel;
	}

	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getCycle() {
		return this.cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getHvolume() {
		return this.hvolume;
	}

	public void setHvolume(String hvolume) {
		this.hvolume = hvolume;
	}

	public String getMvolume() {
		return this.mvolume;
	}

	public void setMvolume(String mvolume) {
		this.mvolume = mvolume;
	}

	public String getPvolume() {
        return pvolume;
    }

    public void setPvolume(String pvolume) {
        this.pvolume = pvolume;
    }

    public String getLvolume() {
		return this.lvolume;
	}

	public void setLvolume(String lvolume) {
		this.lvolume = lvolume;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellchannel() {
		return this.sellchannel;
	}

	public void setSellchannel(String sellchannel) {
		this.sellchannel = sellchannel;
	}

	public String getMainchannel() {
		return this.mainchannel;
	}

	public void setMainchannel(String mainchannel) {
		this.mainchannel = mainchannel;
	}

	public String getMinorchannel() {
		return this.minorchannel;
	}

	public void setMinorchannel(String minorchannel) {
		this.minorchannel = minorchannel;
	}

	public String getAreatype() {
		return this.areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
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

	/**
	 * @return the selftreaty
	 */
	public String getSelftreaty() {
		return selftreaty;
	}

	/**
	 * @param selftreaty the selftreaty to set
	 */
	public void setSelftreaty(String selftreaty) {
		this.selftreaty = selftreaty;
	}

	/**
	 * @return the cmpselftreaty
	 */
	public String getCmpselftreaty() {
		return cmpselftreaty;
	}

	/**
	 * @param cmpselftreaty the cmpselftreaty to set
	 */
	public void setCmpselftreaty(String cmpselftreaty) {
		this.cmpselftreaty = cmpselftreaty;
	}

	/**
	 * @return the ifminedate
	 */
	public String getIfminedate() {
		return ifminedate;
	}

	/**
	 * @param ifminedate the ifminedate to set
	 */
	public void setIfminedate(String ifminedate) {
		this.ifminedate = ifminedate;
	}

	/**
	 * @return the ifmine
	 */
	public String getIfmine() {
		return ifmine;
	}

	/**
	 * @param ifmine the ifmine to set
	 */
	public void setIfmine(String ifmine) {
		this.ifmine = ifmine;
	}
	
	
	
}