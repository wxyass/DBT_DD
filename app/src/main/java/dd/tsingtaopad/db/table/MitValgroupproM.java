package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValgroupproMDaoImpl;


/**
 * 追溯产品组合表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALGROUPPRO_M", daoClass = MitValgroupproMDaoImpl.class)
public class MitValgroupproM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String valgrouppro ;// 产品组合是否达标原值
    @DatabaseField
    private String valgroupproflag ;// 产品组合是否达标正确与否
    @DatabaseField
    private String valgroupproremark ;// 产品组合是否达标备注
    @DatabaseField
    private String creuser;//
    @DatabaseField
    private Date credate ;//
    @DatabaseField
    private String updateuser;//
    @DatabaseField
    private Date updatedate ;//

    @DatabaseField
    private String gproductid;// 业代产品组合表主键
    @DatabaseField
    private String terminalcode ;//终端编码

    /**
     * default constructor
     */
    public MitValgroupproM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public String getValterid() {
        return valterid;
    }

    public void setValterid(String valterid) {
        this.valterid = valterid;
    }

    public String getValgrouppro() {
        return valgrouppro;
    }

    public void setValgrouppro(String valgrouppro) {
        this.valgrouppro = valgrouppro;
    }

    public String getValgroupproflag() {
        return valgroupproflag;
    }

    public void setValgroupproflag(String valgroupproflag) {
        this.valgroupproflag = valgroupproflag;
    }

    public String getValgroupproremark() {
        return valgroupproremark;
    }

    public void setValgroupproremark(String valgroupproremark) {
        this.valgroupproremark = valgroupproremark;
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

    public String getGproductid() {
        return gproductid;
    }

    public void setGproductid(String gproductid) {
        this.gproductid = gproductid;
    }

    public String getTerminalcode() {
        return terminalcode;
    }

    public void setTerminalcode(String terminalcode) {
        this.terminalcode = terminalcode;
    }
}