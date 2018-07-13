package et.tsingtaopad.db.table;

// default package

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstShowpicInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_SHOWPIC_INFO")
public class MstShowpicInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String showpickey;
	@DatabaseField
	private String showkey;
	@DatabaseField
	private String showpicurl;
	@DatabaseField
	private String showpicdetail;
	@DatabaseField
	private String showpicstatus;
	@DatabaseField
	private String netcontent;
	@DatabaseField
	private String stock;
	@DatabaseField
	private String maltosedu;
	@DatabaseField
	private String alcoholic;
	@DatabaseField
	private String standards;
	@DatabaseField
	private String levels;
	@DatabaseField
	private String temp;
	@DatabaseField
	private String drinktemp;
	@DatabaseField
	private String safedate;
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
	public MstShowpicInfo() {
	}

	/** minimal constructor */
	public MstShowpicInfo(String showpickey) {
		this.showpickey = showpickey;
	}

	/** full constructor */
	public MstShowpicInfo(String showpickey, String showkey, String showpicurl, String showpicdetail, String showpicstatus, String netcontent, String stock, String maltosedu, String alcoholic, String standards, String levels, String temp, String drinktemp, String safedate, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.showpickey = showpickey;
		this.showkey = showkey;
		this.showpicurl = showpicurl;
		this.showpicdetail = showpicdetail;
		this.showpicstatus = showpicstatus;
		this.netcontent = netcontent;
		this.stock = stock;
		this.maltosedu = maltosedu;
		this.alcoholic = alcoholic;
		this.standards = standards;
		this.levels = levels;
		this.temp = temp;
		this.drinktemp = drinktemp;
		this.safedate = safedate;
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

	public String getShowpickey() {
		return this.showpickey;
	}

	public void setShowpickey(String showpickey) {
		this.showpickey = showpickey;
	}

	public String getShowkey() {
		return this.showkey;
	}

	public void setShowkey(String showkey) {
		this.showkey = showkey;
	}

	public String getShowpicurl() {
		return this.showpicurl;
	}

	public void setShowpicurl(String showpicurl) {
		this.showpicurl = showpicurl;
	}

	public String getShowpicdetail() {
		return this.showpicdetail;
	}

	public void setShowpicdetail(String showpicdetail) {
		this.showpicdetail = showpicdetail;
	}

	public String getShowpicstatus() {
		return this.showpicstatus;
	}

	public void setShowpicstatus(String showpicstatus) {
		this.showpicstatus = showpicstatus;
	}

	public String getNetcontent() {
		return this.netcontent;
	}

	public void setNetcontent(String netcontent) {
		this.netcontent = netcontent;
	}

	public String getStock() {
		return this.stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getMaltosedu() {
		return this.maltosedu;
	}

	public void setMaltosedu(String maltosedu) {
		this.maltosedu = maltosedu;
	}

	public String getAlcoholic() {
		return this.alcoholic;
	}

	public void setAlcoholic(String alcoholic) {
		this.alcoholic = alcoholic;
	}

	public String getStandards() {
		return this.standards;
	}

	public void setStandards(String standards) {
		this.standards = standards;
	}

	public String getLevels() {
		return this.levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getTemp() {
		return this.temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getDrinktemp() {
		return this.drinktemp;
	}

	public void setDrinktemp(String drinktemp) {
		this.drinktemp = drinktemp;
	}

	public String getSafedate() {
		return this.safedate;
	}

	public void setSafedate(String safedate) {
		this.safedate = safedate;
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