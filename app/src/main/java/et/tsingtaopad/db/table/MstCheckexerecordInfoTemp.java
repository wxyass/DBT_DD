package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCheckexerecordInfo entity. @author MyEclipse Persistence Tools
 * 用于指标结果计算的临时表
 */
@DatabaseTable(tableName = "MST_CHECKEXERECORD_INFO_TEMP")
public class MstCheckexerecordInfoTemp implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String recordkey;
	@DatabaseField
	private String visitkey;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String checktype;
	@DatabaseField
	private String acresult;
	@DatabaseField
	private String iscom;
	@DatabaseField
	private String cstatuskey;
	@DatabaseField
	private String isauto;
	@DatabaseField
	private String exestatus;
	@DatabaseField
	private String startdate;
	@DatabaseField
	private String enddate;
	@DatabaseField
	private String terminalkey;
	@DatabaseField
	private String sisconsistent;
	@DatabaseField
	private Date scondate;
	@DatabaseField
	private String siebelid;
	@DatabaseField
	private BigDecimal resultstatus;
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
	public MstCheckexerecordInfoTemp() {
	}

	/** minimal constructor */
	public MstCheckexerecordInfoTemp(String recordkey) {
		this.recordkey = recordkey;
	}

	/** full constructor */
	public MstCheckexerecordInfoTemp(String recordkey, String visitkey, String productkey, String checkkey, String checktype, String acresult, String iscom, String cstatuskey, String isauto,
			String exestatus, String startdate, String enddate, String terminalkey, String sisconsistent, Date scondate, String siebelid, BigDecimal resultstatus, String padisconsistent,
			Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser,
			Date updatetime, String updateuser) {
		this.recordkey = recordkey;
		this.visitkey = visitkey;
		this.productkey = productkey;
		this.checkkey = checkkey;
		this.checktype = checktype;
		this.acresult = acresult;
		this.iscom = iscom;
		this.cstatuskey = cstatuskey;
		this.isauto = isauto;
		this.exestatus = exestatus;
		this.startdate = startdate;
		this.enddate = enddate;
		this.terminalkey = terminalkey;
		this.sisconsistent = sisconsistent;
		this.scondate = scondate;
		this.siebelid = siebelid;
		this.resultstatus = resultstatus;
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

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public String getAcresult() {
        return acresult;
    }

    public void setAcresult(String acresult) {
        this.acresult = acresult;
    }

    public String getIscom() {
        return iscom;
    }

    public void setIscom(String iscom) {
        this.iscom = iscom;
    }

    public String getCstatuskey() {
		return this.cstatuskey;
	}

	public void setCstatuskey(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	public String getIsauto() {
		return this.isauto;
	}

	public void setIsauto(String isauto) {
		this.isauto = isauto;
	}

	public String getExestatus() {
		return this.exestatus;
	}

	public void setExestatus(String exestatus) {
		this.exestatus = exestatus;
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

	public String getTerminalkey() {
		return this.terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
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

    public String getSiebelid() {
        return siebelid;
    }

    public void setSiebelid(String siebelid) {
        this.siebelid = siebelid;
    }

    public BigDecimal getResultstatus() {
        return resultstatus;
    }

    public void setResultstatus(BigDecimal resultstatus) {
        this.resultstatus = resultstatus;
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