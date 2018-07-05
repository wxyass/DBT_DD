package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MitPlandayMDaoImpl;


/**
 * 日计划主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_PLANDAY_M", daoClass = MitPlandayMDaoImpl.class)
public class MitPlandayM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String planweekid;//周计划主键
    @DatabaseField
    private String plandate;//日计划日期
    @DatabaseField
    private String uploadflag;//varchar2(1) null,
    @DatabaseField
    private String padisconsistent;// varchar2(1) null
    @DatabaseField
    private String status ;//状态

    // Constructors

    /**
     * default constructor
     */
    public MitPlandayM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanweekid() {
        return planweekid;
    }

    public void setPlanweekid(String planweekid) {
        this.planweekid = planweekid;
    }

    public String getPlandate() {
        return plandate;
    }

    public void setPlandate(String plandate) {
        this.plandate = plandate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}