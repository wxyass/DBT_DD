package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstGridM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_GRID_M")
public class MstGridM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String gridkey;
	@DatabaseField
	private String areaid;
	@DatabaseField
	private String gridcode;
	@DatabaseField
	private String gridname;
	@DatabaseField
	private String gridstatus;
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

	@DatabaseField
	private String username;
	@DatabaseField
	private String userid;

	// Constructors

	/** default constructor */
	public MstGridM() {
	}

	/** minimal constructor */
	public MstGridM(String gridkey) {
		this.gridkey = gridkey;
	}

	/** full constructor */
	public MstGridM(String gridkey, String areaid, String gridcode, String gridname, String gridstatus, String sisconsistent, Date scondate,
			String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version,
			Date credate, String creuser, Date updatetime, String updateuser) {
		this.gridkey = gridkey;
		this.areaid = areaid;
		this.gridcode = gridcode;
		this.gridname = gridname;
		this.gridstatus = gridstatus;
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

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getGridcode() {
		return this.gridcode;
	}

	public void setGridcode(String gridcode) {
		this.gridcode = gridcode;
	}

	public String getGridname() {
		return this.gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
	}

	public String getGridstatus() {
		return this.gridstatus;
	}

	public void setGridstatus(String gridstatus) {
		this.gridstatus = gridstatus;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}