package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitValaddaccountMDaoImpl;


/**
 * 终端进货台账主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALADDACCOUNT_M", daoClass = MitValaddaccountMDaoImpl.class)
public class MitValaddaccountM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//

    @DatabaseField
    private String valsupplyid;// 终端追溯终端表ID
    @DatabaseField
    private String valagencyid;//经销商ID
    @DatabaseField
    private String valagencyname;// 经销商名称
    @DatabaseField
    private String valterid;// 终端ID
    @DatabaseField
    private String valtername;// 终端名称
    @DatabaseField
    private String valproid;//产品ID
    @DatabaseField
    private String valproname;//  产品名称
    @DatabaseField
    private String valprostatus;//  稽查状态
    @DatabaseField
    private String creuser;//
    @DatabaseField
    private Date credate;//
    @DatabaseField
    private String updateuser;//
    @DatabaseField
    private Date updatedate;//
    @DatabaseField
    private String uploadflag;//
    @DatabaseField
    private String padisconsistent;//



    /**
     * default constructor
     */
    public MitValaddaccountM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValsupplyid() {
        return valsupplyid;
    }

    public void setValsupplyid(String valsupplyid) {
        this.valsupplyid = valsupplyid;
    }

    public String getValagencyid() {
        return valagencyid;
    }

    public void setValagencyid(String valagencyid) {
        this.valagencyid = valagencyid;
    }

    public String getValagencyname() {
        return valagencyname;
    }

    public void setValagencyname(String valagencyname) {
        this.valagencyname = valagencyname;
    }

    public String getValterid() {
        return valterid;
    }

    public void setValterid(String valterid) {
        this.valterid = valterid;
    }

    public String getValtername() {
        return valtername;
    }

    public void setValtername(String valtername) {
        this.valtername = valtername;
    }

    public String getValproid() {
        return valproid;
    }

    public void setValproid(String valproid) {
        this.valproid = valproid;
    }

    public String getValproname() {
        return valproname;
    }

    public void setValproname(String valproname) {
        this.valproname = valproname;
    }

    public String getValprostatus() {
        return valprostatus;
    }

    public void setValprostatus(String valprostatus) {
        this.valprostatus = valprostatus;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
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