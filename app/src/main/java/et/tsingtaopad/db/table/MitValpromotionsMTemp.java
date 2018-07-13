package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitValpromotionsMTempDaoImpl;


/**
 * 追溯活动终端表临时表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALPROMOTIONS_M_TEMP", daoClass = MitValpromotionsMTempDaoImpl.class)
public class MitValpromotionsMTemp implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String valpromotionsid;//    促销活动主键 varchar2(36)                   null,
    @DatabaseField
    private String valistrue;// 是否达成原值    char(1)                        null,
    @DatabaseField
    private String valistruefalg;// 是否达成正确与否  char(1)                        null,
    @DatabaseField
    private String valistrueval;//是否达成正确值    char(1)                        null,
    @DatabaseField
    private String valistruenum;//  达成组数    varchar2(10)                   null,
    @DatabaseField
    private String valistruenumflag;// 达成组数正确与否  char(10)                       null,
    @DatabaseField
    private String valistruenumval;// 达成组数正确值   char(10)                       null,
    @DatabaseField
    private String creuser;//   创建人    varchar2(128)                  null,
    @DatabaseField
    private Date credate;//   创建日期     date                           null,
    @DatabaseField
    private String updateuser;//  更新人   varchar2(128)                  null,
    @DatabaseField
    private Date updatedate;//  更新时间    date                           null

    @DatabaseField
    private String visitkey;//     varchar2(128)                  null,

    @DatabaseField
    private String terminalkey;//     varchar2(128)                  null,


    // Constructors

    /**
     * default constructor
     */
    public MitValpromotionsMTemp() {
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

    public String getValpromotionsid() {
        return valpromotionsid;
    }

    public void setValpromotionsid(String valpromotionsid) {
        this.valpromotionsid = valpromotionsid;
    }

    public String getValistrue() {
        return valistrue;
    }

    public void setValistrue(String valistrue) {
        this.valistrue = valistrue;
    }

    public String getValistruefalg() {
        return valistruefalg;
    }

    public void setValistruefalg(String valistruefalg) {
        this.valistruefalg = valistruefalg;
    }

    public String getValistrueval() {
        return valistrueval;
    }

    public void setValistrueval(String valistrueval) {
        this.valistrueval = valistrueval;
    }

    public String getValistruenum() {
        return valistruenum;
    }

    public void setValistruenum(String valistruenum) {
        this.valistruenum = valistruenum;
    }

    public String getValistruenumflag() {
        return valistruenumflag;
    }

    public void setValistruenumflag(String valistruenumflag) {
        this.valistruenumflag = valistruenumflag;
    }

    public String getValistruenumval() {
        return valistruenumval;
    }

    public void setValistruenumval(String valistruenumval) {
        this.valistruenumval = valistruenumval;
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

    public String getVisitkey() {
        return visitkey;
    }

    public void setVisitkey(String visitkey) {
        this.visitkey = visitkey;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}