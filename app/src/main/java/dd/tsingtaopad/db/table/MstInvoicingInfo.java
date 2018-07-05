package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstInvoicingInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_INVOICING_INFO")
public class MstInvoicingInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String invoicingkey;
	@DatabaseField
	private String agevisitkey;
	@DatabaseField
	private String agencykey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String recorddate;
	@DatabaseField
	private Double prestorenum;//初期库存
	@DatabaseField
	private Double indirecout;// 分销 --> 转入转出（分销量）
	@DatabaseField
	private Double innum; // 进货量 --> 进货量（到货确认）
	@DatabaseField
	private Double direcout;// 直销量--> 每日赠酒销量
	@DatabaseField
	private Double selfsales;//协议店销量    
	@DatabaseField
	private Double unselfsales;//非协议店销量
	@DatabaseField
	private Double othersales;//其他销量  
	@DatabaseField
	private Double storenum;// 期末库存
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

	// Constructors

	/** default constructor */
	public MstInvoicingInfo() {
	}

	/** minimal constructor */
	public MstInvoicingInfo(String invoicingkey) {
		this.invoicingkey = invoicingkey;
	}

	/** full constructor */
	public MstInvoicingInfo(String invoicingkey, String agevisitkey, String agencykey, String productkey, String recorddate, Double innum,
			Double direcout, Double indirecout, Double storenum, Double prestorenum,String sisconsistent, Date scondate, String padisconsistent, Date padcondate,
			String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.invoicingkey = invoicingkey;
		this.agevisitkey = agevisitkey;
		this.agencykey = agencykey;
		this.productkey = productkey;
		this.recorddate = recorddate;
		this.innum = innum;
		this.direcout = direcout;
		this.indirecout = indirecout;
		this.storenum = storenum;
		this.prestorenum = prestorenum;
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

	public String getInvoicingkey() {
		return this.invoicingkey;
	}

	public void setInvoicingkey(String invoicingkey) {
		this.invoicingkey = invoicingkey;
	}

	public String getAgevisitkey() {
		return this.agevisitkey;
	}

	public void setAgevisitkey(String agevisitkey) {
		this.agevisitkey = agevisitkey;
	}

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getRecorddate() {
		return this.recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	public Double getInnum() {
		return this.innum;
	}

	public void setInnum(Double innum) {
		this.innum = innum;
	}

	public Double getDirecout() {
		return this.direcout;
	}

	public void setDirecout(Double direcout) {
		this.direcout = direcout;
	}

	public Double getIndirecout() {
		return this.indirecout;
	}

	public void setIndirecout(Double indirecout) {
		this.indirecout = indirecout;
	}

	public Double getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}
    
	public Double getPrestorenum() {
		return prestorenum;
	}

	public void setPrestorenum(Double prestorenum) {
		this.prestorenum = prestorenum;
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
	 * @return the selfsales
	 */
	public Double getSelfsales() {
		return selfsales;
	}

	/**
	 * @param selfsales the selfsales to set
	 */
	public void setSelfsales(Double selfsales) {
		this.selfsales = selfsales;
	}

	/**
	 * @return the unselfsales
	 */
	public Double getUnselfsales() {
		return unselfsales;
	}

	/**
	 * @param unselfsales the unselfsales to set
	 */
	public void setUnselfsales(Double unselfsales) {
		this.unselfsales = unselfsales;
	}

	/**
	 * @return the othersales
	 */
	public Double getOthersales() {
		return othersales;
	}

	/**
	 * @param othersales the othersales to set
	 */
	public void setOthersales(Double othersales) {
		this.othersales = othersales;
	}
	
	
}