package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MitRepairMDaoImpl;


/**
 * 整改计划主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_REPAIR_M", daoClass = MitRepairMDaoImpl.class)
public class MitRepairM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//

    @DatabaseField
    private String gridkey;//定格
    @DatabaseField
    private String userid;// 业代ID
    @DatabaseField
    private String content;//问题描述
    @DatabaseField
    private String repairremark;//改进计划
    @DatabaseField
    private String checkcontent;//考核措施
    @DatabaseField
    private String creuser;//追溯人
    @DatabaseField
    private String creuserareaid;//追溯人所属区域
    @DatabaseField
    private String credate;//创建日期
    @DatabaseField
    private String updateuser;//更新人
    @DatabaseField
    private String updatedate;//更新时间
    @DatabaseField
    private String uploadflag;//
    @DatabaseField
    private String padisconsistent;//
    @DatabaseField
    private String status;//0未复查1未通过2已通过

    // Constructors

    /**
     * default constructor
     */
    public MitRepairM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRepairremark() {
        return repairremark;
    }

    public void setRepairremark(String repairremark) {
        this.repairremark = repairremark;
    }

    public String getCheckcontent() {
        return checkcontent;
    }

    public void setCheckcontent(String checkcontent) {
        this.checkcontent = checkcontent;
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



    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}