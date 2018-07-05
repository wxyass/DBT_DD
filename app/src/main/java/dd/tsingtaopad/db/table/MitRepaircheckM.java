package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MitRepaircheckMDaoImpl;


/**
 * 整改计划审核表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_REPAIRCHECK_M", daoClass = MitRepaircheckMDaoImpl.class)
public class MitRepaircheckM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String repairid;//整改计划主表ID
    @DatabaseField
    private String status;//整改状态
    @DatabaseField
    private String repairtime;//整改日期
    @DatabaseField
    private String uploadflag;//varchar2(1) null,
    @DatabaseField
    private String padisconsistent;//varchar2(1) null
    @DatabaseField
    private String credate;//varchar2(1) null

    // Constructors

    /**
     * default constructor
     */
    public MitRepaircheckM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepairid() {
        return repairid;
    }

    public void setRepairid(String repairid) {
        this.repairid = repairid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepairtime() {
        return repairtime;
    }

    public void setRepairtime(String repairtime) {
        this.repairtime = repairtime;
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

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }
}