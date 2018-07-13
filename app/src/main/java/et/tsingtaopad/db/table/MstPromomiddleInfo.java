package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstPromotermInfoDaoImpl;


/**
 * MstPromomiddleInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PROMOMIDDLE_INFO", daoClass=MstPromotermInfoDaoImpl.class)
public class MstPromomiddleInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String promomiddlekey;
	@DatabaseField
	private String ptypekey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String routekey;
	@DatabaseField
	private String tlevel;
	@DatabaseField
	private String completeterms;
	@DatabaseField
	private Integer completenum;
	@DatabaseField
	private String notcomterms;
	@DatabaseField
	private Integer notcomnum;
	@DatabaseField
	private Date searchdate;
	@DatabaseField
	private String startdate;
	@DatabaseField
	private String enddate;
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
	public MstPromomiddleInfo() {
	}

	/** minimal constructor */
	public MstPromomiddleInfo(String promomiddlekey) {
		this.promomiddlekey = promomiddlekey;
	}

	/** full constructor */
	public MstPromomiddleInfo(String promomiddlekey, String ptypekey, String gridkey, String routekey, String tlevel, String completeterms, Integer completenum,
			String notcomterms, Integer notcomnum, Date searchdate, String startdate, String enddate, String sisconsistent, Date scondate, String padisconsistent, Date padcondate,
			String comid, String remarks, String orderbyno, String deleteflag, Integer version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.promomiddlekey = promomiddlekey;
		this.ptypekey = ptypekey;
		this.gridkey = gridkey;
		this.routekey = routekey;
		this.tlevel = tlevel;
		this.completeterms = completeterms;
		this.completenum = completenum;
		this.notcomterms = notcomterms;
		this.notcomnum = notcomnum;
		this.searchdate = searchdate;
		this.startdate = startdate;
		this.enddate = enddate;
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

	public String getPromomiddlekey() {
		return this.promomiddlekey;
	}

	public void setPromomiddlekey(String promomiddlekey) {
		this.promomiddlekey = promomiddlekey;
	}

	public String getPtypekey() {
		return this.ptypekey;
	}

	public void setPtypekey(String ptypekey) {
		this.ptypekey = ptypekey;
	}

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getRoutekey() {
		return this.routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getTlevel() {
		return this.tlevel;
	}

	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}

	public String getCompleteterms() {
		return this.completeterms;
	}

	public void setCompleteterms(String completeterms) {
		this.completeterms = completeterms;
	}

	public Integer getCompletenum() {
		return this.completenum;
	}

	public void setCompletenum(Integer completenum) {
		this.completenum = completenum;
	}

	public String getNotcomterms() {
		return this.notcomterms;
	}

	public void setNotcomterms(String notcomterms) {
		this.notcomterms = notcomterms;
	}

	public Integer getNotcomnum() {
		return this.notcomnum;
	}

	public void setNotcomnum(Integer notcomnum) {
		this.notcomnum = notcomnum;
	}

	public Date getSearchdate() {
		return this.searchdate;
	}

	public void setSearchdate(Date searchdate) {
		this.searchdate = searchdate;
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