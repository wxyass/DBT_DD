package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstBrandseriesM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_BRANDSERIES_M")
public class MstBrandseriesM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String serieskey;
	@DatabaseField
	private String seriesceod;
	@DatabaseField
	private String seriesname;
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

	// Constructors

	/** default constructor */
	public MstBrandseriesM() {
	}

	/** minimal constructor */
	public MstBrandseriesM(String serieskey) {
		this.serieskey = serieskey;
	}

	/** full constructor */
	public MstBrandseriesM(String serieskey, String seriesceod, String seriesname, String status, String sisconsistent, Date scondate,
			String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version,
			Date credate, String creuser, Date updatetime, String updateuser) {
		this.serieskey = serieskey;
		this.seriesceod = seriesceod;
		this.seriesname = seriesname;
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
	}

	// Property accessors

	public String getSerieskey() {
		return this.serieskey;
	}

	public void setSerieskey(String serieskey) {
		this.serieskey = serieskey;
	}

	public String getSeriesceod() {
		return this.seriesceod;
	}

	public void setSeriesceod(String seriesceod) {
		this.seriesceod = seriesceod;
	}

	public String getSeriesname() {
		return this.seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
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

}