package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MitPlandayvalMDaoImpl;


/**
 * 日计划追溯项主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_PLANDAYVAL_M", daoClass = MitPlandayvalMDaoImpl.class)
public class MitPlandayvalM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String planweekid;// 周计划主键
    @DatabaseField
    private String plandaydetailid;// 详细日计划主键
    @DatabaseField
    private String plancomtype;// 追溯项或者路线类型
    @DatabaseField
    private String plancomid;// 追溯项ID/路线ID
    @DatabaseField
    private String uploadflag;//
    @DatabaseField
    private String padisconsistent;//
    @DatabaseField
    private String planareaid ;// 追溯区域
    @DatabaseField
    private String plangridid ;// 追溯定格


    // Constructors

    /**
     * default constructor
     */
    public MitPlandayvalM() {
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

    public String getPlandaydetailid() {
        return plandaydetailid;
    }

    public void setPlandaydetailid(String plandaydetailid) {
        this.plandaydetailid = plandaydetailid;
    }

    public String getPlancomtype() {
        return plancomtype;
    }

    public void setPlancomtype(String plancomtype) {
        this.plancomtype = plancomtype;
    }

    public String getPlancomid() {
        return plancomid;
    }

    public void setPlancomid(String plancomid) {
        this.plancomid = plancomid;
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
}