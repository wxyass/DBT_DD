package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValcmpotherMDaoImpl;


/**
 * 追溯聊竞品供货关附表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALCMPOTHER_M", daoClass = MitValcmpotherMDaoImpl.class)
public class MitValcmpotherM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String valistrueflag ;//是否成功瓦解竞品正确与否
    @DatabaseField
    private String valistruecmpval ;//是否成功瓦解竞品原值
    @DatabaseField
    private String valiscmpremark ;//是否成功瓦解竞品备注
    @DatabaseField
    private String valvisitremark ;//拜访记录
    @DatabaseField
    private String creuser ;//varchar2(128) null,
    @DatabaseField
    private Date credate ;//date null,
    @DatabaseField
    private String updateuser ;//varchar2(128) null,
    @DatabaseField
    private Date updatedate ;//date null


    // Constructors

    /**
     * default constructor
     */
    public MitValcmpotherM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValterid() {
        return valterid;
    }

    public void setValterid(String valterid) {
        this.valterid = valterid;
    }

    public String getValistrueflag() {
        return valistrueflag;
    }

    public void setValistrueflag(String valistrueflag) {
        this.valistrueflag = valistrueflag;
    }

    public String getValistruecmpval() {
        return valistruecmpval;
    }

    public void setValistruecmpval(String valistruecmpval) {
        this.valistruecmpval = valistruecmpval;
    }

    public String getValiscmpremark() {
        return valiscmpremark;
    }

    public void setValiscmpremark(String valiscmpremark) {
        this.valiscmpremark = valiscmpremark;
    }

    public String getValvisitremark() {
        return valvisitremark;
    }

    public void setValvisitremark(String valvisitremark) {
        this.valvisitremark = valvisitremark;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
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

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}