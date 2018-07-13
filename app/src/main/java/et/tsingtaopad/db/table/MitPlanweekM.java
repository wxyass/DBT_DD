package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitPlanweekMDaoImpl;


/**
 * 周计划主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_PLANWEEK_M", daoClass = MitPlanweekMDaoImpl.class)
public class MitPlanweekM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String status ;//状态
    @DatabaseField
    private String starttime  ;//周开始日期
    @DatabaseField
    private String endtime ;//周结束日期
    @DatabaseField
    private String creuser ;//创建人
    @DatabaseField
    private String creuserareaid ;//创建人所属区域
    @DatabaseField
    private Date credate ;//创建日期
    @DatabaseField
    private String updateuser;// 更新人
    @DatabaseField
    private Date updatedate ;// 更新时间
    @DatabaseField
    private String uploadflag ;//,
    @DatabaseField
    private String padisconsistent;//


    // Constructors

    /**
     * default constructor
     */
    public MitPlanweekM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public String getCreuserareaid() {
        return creuserareaid;
    }

    public void setCreuserareaid(String creuserareaid) {
        this.creuserareaid = creuserareaid;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUploadflag() {
        return uploadflag;
    }

    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}