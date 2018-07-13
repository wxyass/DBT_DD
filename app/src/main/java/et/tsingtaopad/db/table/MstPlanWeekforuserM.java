package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstPlanforuserM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_PLANWEEKFORUSER_M")
public class MstPlanWeekforuserM implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    @DatabaseField(id = true, canBeNull = false)
    private String plankey;//主键    
    @DatabaseField
    private String gridkey;//定格主键
    @DatabaseField
    private String plantitle;//
    @DatabaseField
    private String userid;//
    @DatabaseField
    private String startdate;//周计划开始时间
    @DatabaseField
    private String enddate;//周计划结束时间
    @DatabaseField
    private String planstatus;//0未审核 1 未通过 2 未制订 3 审核通过 4 已提交 5 自动通过
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
    private Date credate;//
    @DatabaseField
    private String creuser;//
    @DatabaseField
    private Date updatetime;
    @DatabaseField
    private String updateuser;
    // 单击上传按钮的状态。0/null：未上单击上传按钮、 1：已单击上传按钮(暂时未用) 
    @DatabaseField(defaultValue = "0")
    private String uploadFlag;

    // Constructors

    /** default constructor */
    public MstPlanWeekforuserM()
    {
    }

    public String getPlankey()
    {
        return plankey;
    }

    public void setPlankey(String plankey)
    {
        this.plankey = plankey;
    }

    public String getPlantitle()
    {
        return plantitle;
    }

    public void setPlantitle(String plantitle)
    {
        this.plantitle = plantitle;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getPlanstatus()
    {
        return planstatus;
    }

    public void setPlanstatus(String planstatus)
    {
        this.planstatus = planstatus;
    }

    public String getSisconsistent()
    {
        return sisconsistent;
    }

    public void setSisconsistent(String sisconsistent)
    {
        this.sisconsistent = sisconsistent;
    }

    public Date getScondate()
    {
        return scondate;
    }

    public void setScondate(Date scondate)
    {
        this.scondate = scondate;
    }

    public String getPadisconsistent()
    {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent)
    {
        this.padisconsistent = padisconsistent;
    }

    public Date getPadcondate()
    {
        return padcondate;
    }

    public void setPadcondate(Date padcondate)
    {
        this.padcondate = padcondate;
    }

    public String getComid()
    {
        return comid;
    }

    public void setComid(String comid)
    {
        this.comid = comid;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getOrderbyno()
    {
        return orderbyno;
    }

    public void setOrderbyno(String orderbyno)
    {
        this.orderbyno = orderbyno;
    }

    public String getDeleteflag()
    {
        return deleteflag;
    }

    public void setDeleteflag(String deleteflag)
    {
        this.deleteflag = deleteflag;
    }

    public BigDecimal getVersion()
    {
        return version;
    }

    public void setVersion(BigDecimal version)
    {
        this.version = version;
    }

    public Date getCredate()
    {
        return credate;
    }

    public void setCredate(Date credate)
    {
        this.credate = credate;
    }

    public String getCreuser()
    {
        return creuser;
    }

    public void setCreuser(String creuser)
    {
        this.creuser = creuser;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }

    public String getUpdateuser()
    {
        return updateuser;
    }

    public void setUpdateuser(String updateuser)
    {
        this.updateuser = updateuser;
    }

    public String getUploadFlag()
    {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag)
    {
        this.uploadFlag = uploadFlag;
    }

    public String getGridkey()
    {
        return gridkey;
    }

    public void setGridkey(String gridkey)
    {
        this.gridkey = gridkey;
    }

}