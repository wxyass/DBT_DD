package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCheckmiddleInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CHECKMIDDLE_INFO")
public class MstCheckmiddleInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String checkmiddlekey;
	@DatabaseField
	private String routekey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String cstatuskey;
	@DatabaseField
	private String checkname;
	@DatabaseField
	private String cstatusname;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String proname;
	@DatabaseField
	private String searchdate;
	@DatabaseField
	private String searchType;
	@DatabaseField
	private String prorate;
	@DatabaseField
	private String oldrate;
	@DatabaseField
	private Long change;
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

	// Constructors

	/** default constructor */
	public MstCheckmiddleInfo() {
	}

	/** minimal constructor */
	public MstCheckmiddleInfo(String checkmiddlekey) {
		this.checkmiddlekey = checkmiddlekey;
	}

	/** full constructor */
	public MstCheckmiddleInfo(String checkmiddlekey, String routekey, String gridkey, String checkkey, String cstatuskey, String productkey,
			String proname, String searchdate, String searchType, String prorate, String oldrate, Long change, String sisconsistent, Date scondate,
			String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version,
			Date credate, String creuser, Date updatetime, String updateuser,String checkname,String cstatusname) {
		this.checkmiddlekey = checkmiddlekey;
		this.routekey = routekey;
		this.gridkey = gridkey;
		this.checkkey = checkkey;
		this.cstatuskey = cstatuskey;
		this.productkey = productkey;
		this.proname = proname;
		this.searchdate = searchdate;
		this.searchType = searchType;
		this.prorate = prorate;
		this.oldrate = oldrate;
		this.change = change;
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
		this.checkname = checkname;
		this.cstatusname = cstatusname;
	}

	// Property accessors

	public String getCheckmiddlekey() {
		return this.checkmiddlekey;
	}

	public void setCheckmiddlekey(String checkmiddlekey) {
		this.checkmiddlekey = checkmiddlekey;
	}

	public String getRoutekey() {
		return this.routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getCstatuskey() {
		return this.cstatuskey;
	}

	public void setCstatuskey(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getSearchdate() {
		return this.searchdate;
	}

	public void setSearchdate(String searchdate) {
		this.searchdate = searchdate;
	}

	public String getSearchType() {
		return this.searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getProrate() {
		return this.prorate;
	}

	public void setProrate(String prorate) {
		this.prorate = prorate;
	}

	public String getOldrate() {
		return this.oldrate;
	}

	public void setOldrate(String oldrate) {
		this.oldrate = oldrate;
	}

	public Long getChange() {
		return this.change;
	}

	public void setChange(Long change) {
		this.change = change;
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

	public String getCheckname() {
		return checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}

	public String getCstatusname() {
		return cstatusname;
	}

	public void setCstatusname(String cstatusname) {
		this.cstatusname = cstatusname;
	}
}