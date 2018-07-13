package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

import et.tsingtaopad.db.dao.impl.MstVisitMTempDaoImpl;


/**
 * MstVisitMTemp entity. @author MyEclipse Persistence Tools
 */
//MST_VISIT_M_TEMP(拜访主表的临时表)
@DatabaseTable(tableName = "MST_VISIT_M_TEMP", daoClass = MstVisitMTempDaoImpl.class)
public class MstVisitMTemp implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String visitkey;
	@DatabaseField
	private String terminalkey;
	@DatabaseField
	private String routekey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String areaid;
	@DatabaseField
	private String visitdate;
	@DatabaseField
	private String enddate;
	@DatabaseField
	private String tempkey;
	@DatabaseField
	private String userid;
	@DatabaseField
	private String visituser;
	@DatabaseField
	private String isself;
	@DatabaseField
	private String iscmp;
	@DatabaseField
	private String selftreaty;
	@DatabaseField
	private String cmptreaty;
	@DatabaseField
	private String status;
	@DatabaseField
	private String ishdistribution;
	@DatabaseField
	private String exetreaty;
	@DatabaseField
	private String selfoccupancy;
	@DatabaseField
	private String iscmpcollapse;



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
	private String orderbyno;// 0:默认为0表示正常  1:拜访返回了,再次进入需更新拜访时间
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
	private String longitude;
	@DatabaseField
	private String latitude;
	@DatabaseField
	private String gpsstatus;
	@DatabaseField
	private String ifminedate;//店招时间
	@DatabaseField
	private String ifmine;//店招
	@DatabaseField
	private String visitposition;//老板老板娘
	// 单击上传按钮的状态。0/null：未上单击上传按钮、 1：已单击上传按钮
    @DatabaseField(defaultValue = "0")
	private String uploadFlag;

	// Constructors

	/** default constructor */
	public MstVisitMTemp() {
	}

	/** minimal constructor */
	public MstVisitMTemp(String visitkey) {
		this.visitkey = visitkey;
	}

	/** full constructor */
	public MstVisitMTemp(String visitkey, String terminalkey, String routekey, String gridkey, String areaid, String visitdate, String enddate, String tempkey, String userid, String visituser,
                         String isself, String iscmp, String selftreaty, String cmptreaty, String status, String ishdistribution, String exetreaty,
                         String selfoccupancy, String iscmpcollapse, String longitude, String latitude, String gpsstatus, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid,
                         String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime, String updateuser, String uploadFlag, String ifminedate, String ifmine) {
		this.visitkey = visitkey;
		this.terminalkey = terminalkey;
		this.routekey = routekey;
		this.gridkey = gridkey;
		this.areaid = areaid;
		this.visitdate = visitdate;
		this.enddate = enddate;
		this.tempkey = tempkey;
		this.userid = userid;
		this.visituser = visituser;
		this.isself = isself;
		this.iscmp = iscmp;
		this.selftreaty = selftreaty;
		this.cmptreaty = cmptreaty;
		this.status = status;
		this.ishdistribution = ishdistribution;
		this.exetreaty = exetreaty;
		this.selfoccupancy = selfoccupancy;
		this.iscmpcollapse = iscmpcollapse;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gpsstatus = gpsstatus;
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
		this.uploadFlag = uploadFlag;
		this.ifmine = ifmine;
		this.ifminedate = ifminedate;
	}

	// Property accessors

	public String getVisitkey() {
		return this.visitkey;
	}

	public void setVisitkey(String visitkey) {
		this.visitkey = visitkey;
	}

	public String getTerminalkey() {
		return this.terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getRoutekey() {
		return this.routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getVisitdate() {
		return this.visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getTempkey() {
        return tempkey;
    }

    public void setTempkey(String tempkey) {
        this.tempkey = tempkey;
    }

    public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getVisituser() {
		return this.visituser;
	}

	public void setVisituser(String visituser) {
		this.visituser = visituser;
	}

	public String getIsself() {
		return this.isself;
	}

	public void setIsself(String isself) {
		this.isself = isself;
	}

	public String getIscmp() {
		return this.iscmp;
	}

	public void setIscmp(String iscmp) {
		this.iscmp = iscmp;
	}

	public String getSelftreaty() {
		return this.selftreaty;
	}

	public void setSelftreaty(String selftreaty) {
		this.selftreaty = selftreaty;
	}

	public String getCmptreaty() {
		return this.cmptreaty;
	}

	public void setCmptreaty(String cmptreaty) {
		this.cmptreaty = cmptreaty;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIshdistribution() {
		return this.ishdistribution;
	}

	public void setIshdistribution(String ishdistribution) {
		this.ishdistribution = ishdistribution;
	}

	public String getExetreaty() {
		return this.exetreaty;
	}

	public void setExetreaty(String exetreaty) {
		this.exetreaty = exetreaty;
	}

	public String getSelfoccupancy() {
		return this.selfoccupancy;
	}

	public void setSelfoccupancy(String selfoccupancy) {
		this.selfoccupancy = selfoccupancy;
	}

	public String getIscmpcollapse() {
		return this.iscmpcollapse;
	}

	public void setIscmpcollapse(String iscmpcollapse) {
		this.iscmpcollapse = iscmpcollapse;
	}

	public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGpsstatus() {
        return gpsstatus;
    }

    public void setGpsstatus(String gpsstatus) {
        this.gpsstatus = gpsstatus;
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

    public String getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

	public String getIfminedate() {
		return ifminedate;
	}

	public void setIfminedate(String ifminedate) {
		this.ifminedate = ifminedate;
	}

	public String getIfmine() {
		return ifmine;
	}

	public void setIfmine(String ifmine) {
		this.ifmine = ifmine;
	}

	/**
	 * @return the visitposition
	 */
	public String getVisitposition() {
		return visitposition;
	}

	/**
	 * @param visitposition the visitposition to set
	 */
	public void setVisitposition(String visitposition) {
		this.visitposition = visitposition;
	}
	
    
}