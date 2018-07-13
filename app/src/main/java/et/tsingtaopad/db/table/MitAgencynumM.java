package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitAgencynumMDaoImpl;


/**
 * 经销商库存盘点主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_AGENCYNUM_M", daoClass = MitAgencynumMDaoImpl.class)
public class MitAgencynumM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String agencyid;
    @DatabaseField
    private String agencyareaid;// 经销商所属二级区域ID

    @DatabaseField
    private String creuser;//
    @DatabaseField
    private String creuserareaid;//    创建人所属区域
    @DatabaseField
    private Date credate;//
    @DatabaseField
    private String updateuser;//
    @DatabaseField
    private Date updatedate;//
    @DatabaseField
    private String uploadflag;//是否上传 0:不上传  1: 需上传
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传 varchar2(1) null,



    /**
     * default constructor
     */
    public MitAgencynumM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
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

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getAgencyareaid() {
        return agencyareaid;
    }

    public void setAgencyareaid(String agencyareaid) {
        this.agencyareaid = agencyareaid;
    }
}