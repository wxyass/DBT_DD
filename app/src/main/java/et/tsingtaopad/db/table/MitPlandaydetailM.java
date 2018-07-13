package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MitPlandaydetailMDaoImpl;


/**
 * 详细日计划
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_PLANDAYDETAIL_M", daoClass = MitPlandaydetailMDaoImpl.class)
public class MitPlandaydetailM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String planweekid ;// 周计划主键
    @DatabaseField
    private String plandayid  ;// 日计划主键
    @DatabaseField
    private String planareaid ;// 追溯区域
    @DatabaseField
    private String plangridid ;// 追溯定格
    @DatabaseField
    private String uploadflag ;//
    @DatabaseField
    private String padisconsistent ;//


    // Constructors

    /**
     * default constructor
     */
    public MitPlandaydetailM() {
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

    public String getPlandayid() {
        return plandayid;
    }

    public void setPlandayid(String plandayid) {
        this.plandayid = plandayid;
    }

    public String getPlanareaid() {
        return planareaid;
    }

    public void setPlanareaid(String planareaid) {
        this.planareaid = planareaid;
    }

    public String getPlangridid() {
        return plangridid;
    }

    public void setPlangridid(String plangridid) {
        this.plangridid = plangridid;
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