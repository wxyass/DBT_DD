package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstRouteMDaoImpl;


/**
 * MstRouteM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_ROUTE_M", daoClass = MstRouteMDaoImpl.class)
public class MstRouteM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	String routekey;
	@DatabaseField
	String gridkey;
	@DatabaseField
	String routecode;
	@DatabaseField
	String routename;
	@DatabaseField
	String routedesc;
	@DatabaseField
	String routestatus;
	@DatabaseField
	String sisconsistent;
	@DatabaseField
	Date scondate;
	@DatabaseField(defaultValue = "0")
	String padisconsistent;
	@DatabaseField
	Date padcondate;
	@DatabaseField
	String comid;
	@DatabaseField
	String remarks;
	@DatabaseField
	String orderbyno;
	@DatabaseField
	BigDecimal version;
	@DatabaseField
	Date credate;
	@DatabaseField
	String creuser;
	@DatabaseField
	Date updatetime;
	@DatabaseField
	String updateuser;
	@DatabaseField(defaultValue = "0")
	private String deleteflag;

	// Constructors

	/** default constructor */
	public MstRouteM() {
	}

	/** minimal constructor */
	public MstRouteM(String routekey) {
		this.routekey = routekey;
	}

	/** full constructor */
	public MstRouteM(String routekey, String gridkey, String routecode, String routename, String routedesc, String routestatus, String sisconsistent,
			Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag,
			BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.routekey = routekey;
		this.gridkey = gridkey;
		this.routecode = routecode;
		this.routename = routename;
		this.routedesc = routedesc;
		this.routestatus = routestatus;
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

	public String getRoutecode() {
		return this.routecode;
	}

	public void setRoutecode(String routecode) {
		this.routecode = routecode;
	}

	public String getRoutename() {
		return this.routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getRoutedesc() {
		return this.routedesc;
	}

	public void setRoutedesc(String routedesc) {
		this.routedesc = routedesc;
	}

	public String getRoutestatus() {
		return this.routestatus;
	}

	public void setRoutestatus(String routestatus) {
		this.routestatus = routestatus;
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