package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstVistproductInfoDaoImpl;


/**
 * MstVistproductInfo entity. @author MyEclipse Persistence Tools
 */
//MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表)
@DatabaseTable(tableName = "MST_VISTPRODUCT_INFO", daoClass=MstVistproductInfoDaoImpl.class)
public class MstVistproductInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String recordkey;
	@DatabaseField
	private String visitkey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String cmpproductkey;
	@DatabaseField
	private String cmpcomkey;
	@DatabaseField
	private String agencykey;
	@DatabaseField
	private String agencyname;// 用户输入的竞品经销商
	@DatabaseField
	private Double purcprice;
	@DatabaseField
	private Double retailprice;
	@DatabaseField
	private Double purcnum;
	@DatabaseField
	private Double pronum;
	@DatabaseField
	private Double currnum;
	@DatabaseField
	private Double salenum;
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
	private String fristdate;
	@DatabaseField
	private Double addcard;// 累计卡

	// Constructors

	/** default constructor */
	public MstVistproductInfo() {
	}

	/** minimal constructor */
	public MstVistproductInfo(String recordkey) {
		this.recordkey = recordkey;
	}

	/** full constructor */
	public MstVistproductInfo(String recordkey, String visitkey, String productkey, String cmpproductkey, String cmpcomkey, String agencykey,
			Double purcprice, Double retailprice, Double purcnum, Double pronum, Double currnum, Double salenum, String sisconsistent, Date scondate,
			String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version,
			Date credate, String creuser, Date updatetime, String updateuser) {
		this.recordkey = recordkey;
		this.visitkey = visitkey;
		this.productkey = productkey;
		this.cmpproductkey = cmpproductkey;
		this.cmpcomkey = cmpcomkey;
		this.agencykey = agencykey;
		this.purcprice = purcprice;
		this.retailprice = retailprice;
		this.purcnum = purcnum;
		this.pronum = pronum;
		this.currnum = currnum;
		this.salenum = salenum;
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

	public String getRecordkey() {
		return this.recordkey;
	}

	public void setRecordkey(String recordkey) {
		this.recordkey = recordkey;
	}

	public String getVisitkey() {
		return this.visitkey;
	}

	public void setVisitkey(String visitkey) {
		this.visitkey = visitkey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getCmpproductkey() {
		return this.cmpproductkey;
	}

	public void setCmpproductkey(String cmpproductkey) {
		this.cmpproductkey = cmpproductkey;
	}

	public String getCmpcomkey() {
		return this.cmpcomkey;
	}

	public void setCmpcomkey(String cmpcomkey) {
		this.cmpcomkey = cmpcomkey;
	}

	public String getAgencykey() {
		return this.agencykey;
	}

	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}

	public Double getPurcprice() {
		return this.purcprice;
	}

	public void setPurcprice(Double purcprice) {
		this.purcprice = purcprice;
	}

	public Double getRetailprice() {
		return this.retailprice;
	}

	public void setRetailprice(Double retailprice) {
		this.retailprice = retailprice;
	}

	public Double getPurcnum() {
		return this.purcnum;
	}

	public void setPurcnum(Double purcnum) {
		this.purcnum = purcnum;
	}

	public Double getPronum() {
		return this.pronum;
	}

	public void setPronum(Double pronum) {
		this.pronum = pronum;
	}

	public Double getCurrnum() {
		return this.currnum;
	}

	public void setCurrnum(Double currnum) {
		this.currnum = currnum;
	}

	public Double getSalenum() {
		return this.salenum;
	}

	public void setSalenum(Double salenum) {
		this.salenum = salenum;
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
	 * @return the agencyname
	 */
	public String getAgencyname() {
		return agencyname;
	}

	/**
	 * @param agencyname the agencyname to set
	 */
	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}

	/**
	 * @return the fristdate
	 */
	public String getFristdate() {
		return fristdate;
	}

	/**
	 * @param fristdate the fristdate to set
	 */
	public void setFristdate(String fristdate) {
		this.fristdate = fristdate;
	}

	/**
	 * @return the addcard
	 */
	public Double getAddcard() {
		return addcard;
	}

	/**
	 * @param addcard the addcard to set
	 */
	public void setAddcard(Double addcard) {
		this.addcard = addcard;
	}

}