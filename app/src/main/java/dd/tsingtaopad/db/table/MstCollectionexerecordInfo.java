package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCollectionexerecordInfo entity. @author MyEclipse Persistence Tools
 */

@DatabaseTable(tableName = "MST_COLLECTIONEXERECORD_INFO")
public class MstCollectionexerecordInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String colrecordkey;
	@DatabaseField
	private String visitkey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String colitemkey;
	@DatabaseField
	private Double addcount;
	@DatabaseField
	private Double totalcount;
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
	private String freshness;// 新鲜度
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
	private String bianhualiang;//变化量 (用于最后上传时 现有量变化量必须填值,才能上传)
	@DatabaseField
	private String xianyouliang;// 现有量 (用于最后上传时 现有量变化量必须填值,才能上传)

	// Constructors



	/** default constructor */
	public MstCollectionexerecordInfo() {
	}

	/** minimal constructor */
	public MstCollectionexerecordInfo(String colrecordkey) {
		this.colrecordkey = colrecordkey;
	}

	/**
     * @param colrecordkey
     * @param visitkey
     * @param productkey
     * @param checkkey
     * @param colitemkey
     * @param addcount
     * @param totalcount
     * @param sisconsistent
     * @param scondate
     * @param padisconsistent
     * @param padcondate
     * @param comid
     * @param remarks
     * @param orderbyno
     * @param deleteflag
     * @param version
     * @param credate
     * @param creuser
     * @param updatetime
     * @param updateuser
     */
    public MstCollectionexerecordInfo(String colrecordkey, String visitkey, String productkey, String checkkey, String colitemkey, Double addcount, Double totalcount, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser, String bianhualiang, String xianyouliang)
    {
        super();
        this.colrecordkey = colrecordkey;
        this.visitkey = visitkey;
        this.productkey = productkey;
        this.checkkey = checkkey;
        this.colitemkey = colitemkey;
        this.addcount = addcount;
        this.totalcount = totalcount;
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
        this.bianhualiang = bianhualiang;
        this.xianyouliang = xianyouliang;
    }

    public String getColrecordkey() {
		return this.colrecordkey;
	}

	public void setColrecordkey(String colrecordkey) {
		this.colrecordkey = colrecordkey;
	}

	/**
     * @return the checkkey
     */
    public String getCheckkey()
    {
        return checkkey;
    }

    /**
     * @param checkkey the checkkey to set
     */
    public void setCheckkey(String checkkey)
    {
        this.checkkey = checkkey;
    }

    public String getVisitkey() {
        return visitkey;
    }

    public void setVisitkey(String visitkey) {
        this.visitkey = visitkey;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

    public String getColitemkey() {
		return this.colitemkey;
	}

	public void setColitemkey(String colitemkey) {
		this.colitemkey = colitemkey;
	}

	public Double getAddcount() {
		return this.addcount;
	}

	public void setAddcount(Double addcount) {
		this.addcount = addcount;
	}

	public Double getTotalcount() {
		return this.totalcount;
	}

	public void setTotalcount(Double totalcount) {
		this.totalcount = totalcount;
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
	 * @return the freshness
	 */
	public String getFreshness() {
		return freshness;
	}

	/**
	 * @param freshness the freshness to set
	 */
	public void setFreshness(String freshness) {
		this.freshness = freshness;
	}

	

	/**
	 * @return the bianhualiang
	 */
	public String getBianhualiang() {
		return bianhualiang;
	}

	/**
	 * @param bianhualiang the bianhualiang to set
	 */
	public void setBianhualiang(String bianhualiang) {
		this.bianhualiang = bianhualiang;
	}

	/**
	 * @return the xianyouliang
	 */
	public String getXianyouliang() {
		return xianyouliang;
	}

	/**
	 * @param xianyouliang the xianyouliang to set
	 */
	public void setXianyouliang(String xianyouliang) {
		this.xianyouliang = xianyouliang;
	}
	
	
	
}