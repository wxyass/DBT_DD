package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstProductareaInfo entity. @author MyEclipse Persistence Tools
 */

@DatabaseTable(tableName = "MST_PRODUCTAREA_INFO")
public class MstProductareaInfo implements java.io.Serializable {

	@DatabaseField(id = true, canBeNull = false)
	String proareakey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String areaid;
	@DatabaseField
	private String status;
	@DatabaseField
	private String stdate;
	@DatabaseField
	private String statusdesc;
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
	public MstProductareaInfo() {
	}

	/** minimal constructor */
	public MstProductareaInfo(String proareakey) {
		this.proareakey = proareakey;
	}

	/** full constructor */
	public MstProductareaInfo(String proareakey, String productkey, String areaid, String status, String stdate, String statusdesc,
			String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno,
			String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.proareakey = proareakey;
		this.productkey = productkey;
		this.areaid = areaid;
		this.status = status;
		this.stdate = stdate;
		this.statusdesc = statusdesc;
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

	public String getProareakey() {
		return this.proareakey;
	}

	public void setProareakey(String proareakey) {
		this.proareakey = proareakey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStdate() {
		return this.stdate;
	}

	public void setStdate(String stdate) {
		this.stdate = stdate;
	}

	public String getStatusdesc() {
		return this.statusdesc;
	}

	public void setStatusdesc(String statusdesc) {
		this.statusdesc = statusdesc;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MstProductareaInfo))
			return false;
		MstProductareaInfo castOther = (MstProductareaInfo) other;

		return ((this.getProareakey() == castOther.getProareakey()) || (this.getProareakey() != null && castOther.getProareakey() != null && this
				.getProareakey().equals(castOther.getProareakey())))
				&& ((this.getProductkey() == castOther.getProductkey()) || (this.getProductkey() != null && castOther.getProductkey() != null && this
						.getProductkey().equals(castOther.getProductkey())))
				&& ((this.getAreaid() == castOther.getAreaid()) || (this.getAreaid() != null && castOther.getAreaid() != null && this.getAreaid()
						.equals(castOther.getAreaid())))
				&& ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null && castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getStdate() == castOther.getStdate()) || (this.getStdate() != null && castOther.getStdate() != null && this.getStdate()
						.equals(castOther.getStdate())))
				&& ((this.getStatusdesc() == castOther.getStatusdesc()) || (this.getStatusdesc() != null && castOther.getStatusdesc() != null && this
						.getStatusdesc().equals(castOther.getStatusdesc())))
				&& ((this.getSisconsistent() == castOther.getSisconsistent()) || (this.getSisconsistent() != null
						&& castOther.getSisconsistent() != null && this.getSisconsistent().equals(castOther.getSisconsistent())))
				&& ((this.getScondate() == castOther.getScondate()) || (this.getScondate() != null && castOther.getScondate() != null && this
						.getScondate().equals(castOther.getScondate())))
				&& ((this.getPadisconsistent() == castOther.getPadisconsistent()) || (this.getPadisconsistent() != null
						&& castOther.getPadisconsistent() != null && this.getPadisconsistent().equals(castOther.getPadisconsistent())))
				&& ((this.getPadcondate() == castOther.getPadcondate()) || (this.getPadcondate() != null && castOther.getPadcondate() != null && this
						.getPadcondate().equals(castOther.getPadcondate())))
				&& ((this.getComid() == castOther.getComid()) || (this.getComid() != null && castOther.getComid() != null && this.getComid().equals(
						castOther.getComid())))
				&& ((this.getRemarks() == castOther.getRemarks()) || (this.getRemarks() != null && castOther.getRemarks() != null && this
						.getRemarks().equals(castOther.getRemarks())))
				&& ((this.getOrderbyno() == castOther.getOrderbyno()) || (this.getOrderbyno() != null && castOther.getOrderbyno() != null && this
						.getOrderbyno().equals(castOther.getOrderbyno())))
				&& ((this.getDeleteflag() == castOther.getDeleteflag()) || (this.getDeleteflag() != null && castOther.getDeleteflag() != null && this
						.getDeleteflag().equals(castOther.getDeleteflag())))
				&& ((this.getVersion() == castOther.getVersion()) || (this.getVersion() != null && castOther.getVersion() != null && this
						.getVersion().equals(castOther.getVersion())))
				&& ((this.getCredate() == castOther.getCredate()) || (this.getCredate() != null && castOther.getCredate() != null && this
						.getCredate().equals(castOther.getCredate())))
				&& ((this.getCreuser() == castOther.getCreuser()) || (this.getCreuser() != null && castOther.getCreuser() != null && this
						.getCreuser().equals(castOther.getCreuser())))
				&& ((this.getUpdatetime() == castOther.getUpdatetime()) || (this.getUpdatetime() != null && castOther.getUpdatetime() != null && this
						.getUpdatetime().equals(castOther.getUpdatetime())))
				&& ((this.getUpdateuser() == castOther.getUpdateuser()) || (this.getUpdateuser() != null && castOther.getUpdateuser() != null && this
						.getUpdateuser().equals(castOther.getUpdateuser())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getProareakey() == null ? 0 : this.getProareakey().hashCode());
		result = 37 * result + (getProductkey() == null ? 0 : this.getProductkey().hashCode());
		result = 37 * result + (getAreaid() == null ? 0 : this.getAreaid().hashCode());
		result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result + (getStdate() == null ? 0 : this.getStdate().hashCode());
		result = 37 * result + (getStatusdesc() == null ? 0 : this.getStatusdesc().hashCode());
		result = 37 * result + (getSisconsistent() == null ? 0 : this.getSisconsistent().hashCode());
		result = 37 * result + (getScondate() == null ? 0 : this.getScondate().hashCode());
		result = 37 * result + (getPadisconsistent() == null ? 0 : this.getPadisconsistent().hashCode());
		result = 37 * result + (getPadcondate() == null ? 0 : this.getPadcondate().hashCode());
		result = 37 * result + (getComid() == null ? 0 : this.getComid().hashCode());
		result = 37 * result + (getRemarks() == null ? 0 : this.getRemarks().hashCode());
		result = 37 * result + (getOrderbyno() == null ? 0 : this.getOrderbyno().hashCode());
		result = 37 * result + (getDeleteflag() == null ? 0 : this.getDeleteflag().hashCode());
		result = 37 * result + (getVersion() == null ? 0 : this.getVersion().hashCode());
		result = 37 * result + (getCredate() == null ? 0 : this.getCredate().hashCode());
		result = 37 * result + (getCreuser() == null ? 0 : this.getCreuser().hashCode());
		result = 37 * result + (getUpdatetime() == null ? 0 : this.getUpdatetime().hashCode());
		result = 37 * result + (getUpdateuser() == null ? 0 : this.getUpdateuser().hashCode());
		return result;
	}

}