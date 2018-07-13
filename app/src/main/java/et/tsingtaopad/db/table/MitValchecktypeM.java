package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitValchecktypeMDaoImpl;


/**
 * 追溯拉链表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALCHECKTYPE_M", daoClass = MitValchecktypeMDaoImpl.class)
public class MitValchecktypeM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String visitkey;// 终端追溯主表ID
    @DatabaseField
    private String valchecktype;//  指标类型 5d,6d....  varchar2(36)                   null,
    @DatabaseField
    private String valchecktypeid;//  指标ID  拉链表主键  varchar2(36)                   null,
    @DatabaseField
    private String productkey;//    varchar2(36)                   null,
    @DatabaseField
    private String acresult;//    varchar2(36)                   null,
    @DatabaseField
    private String terminalkey;//    varchar2(36)                   null,
    @DatabaseField
    private String valchecktypeflag;// 指标正确与否   char(1)                        null,
    @DatabaseField
    private String valgrouppro;//  产品组合是否达标原值    char(1)                        null,
    @DatabaseField
    private String valgroupproflag;//   产品组合是否达标正确与否 char(1)                        null,
    @DatabaseField
    private String valgroupproremark;// 产品组合是否达标备注  varchar2(300)                  null,
    @DatabaseField
    private String valhz;//   合作执行是否到位原值          char(1)                        null,
    @DatabaseField
    private String valhzflag;//   合作执行是否到位正确与否   char(1)                        null,
    @DatabaseField
    private String valhzremark;//  合作执行是否到位备注    varchar2(300)                  null,
    @DatabaseField
    private String valgzlps;// 是否高质量配送原值        char(1)                        null,
    @DatabaseField
    private String valgzlpsflag;//  是否高质量配送正确与否    char(1)                        null,
    @DatabaseField
    private String valgzlpsremark;//  是否高质量配送备注   varchar2(300)                  null,
    @DatabaseField
    private String valzyl;//    我品单店占有率原值        char(1)                        null,
    @DatabaseField
    private String valzylflag;//   我品单店占有率正确与否    char(1)                        null,
    @DatabaseField
    private String valzylremark;//  我品单店占有率备注    varchar2(300)                  null,
    @DatabaseField
    private String creuser;//     创建人       varchar2(128)                  null,
    @DatabaseField
    private Date credate;//           date                           null,
    @DatabaseField
    private String updateuser;//       varchar2(128)                  null,
    @DatabaseField
    private Date updatedate;//       date                           null
    @DatabaseField
    private String ddacresult;//  督导指标值     varchar2(128)                  null,
    @DatabaseField
    private String ddremark;//   追溯备注    varchar2(128)                  null,


    /**
     * default constructor
     */
    public MitValchecktypeM() {
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

    public String getValchecktype() {
        return valchecktype;
    }

    public void setValchecktype(String valchecktype) {
        this.valchecktype = valchecktype;
    }

    public String getValchecktypeid() {
        return valchecktypeid;
    }

    public void setValchecktypeid(String valchecktypeid) {
        this.valchecktypeid = valchecktypeid;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

    public String getAcresult() {
        return acresult;
    }

    public void setAcresult(String acresult) {
        this.acresult = acresult;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getValchecktypeflag() {
        return valchecktypeflag;
    }

    public void setValchecktypeflag(String valchecktypeflag) {
        this.valchecktypeflag = valchecktypeflag;
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

    public String getValhz() {
        return valhz;
    }

    public void setValhz(String valhz) {
        this.valhz = valhz;
    }

    public String getValhzflag() {
        return valhzflag;
    }

    public void setValhzflag(String valhzflag) {
        this.valhzflag = valhzflag;
    }

    public String getValhzremark() {
        return valhzremark;
    }

    public void setValhzremark(String valhzremark) {
        this.valhzremark = valhzremark;
    }

    public String getValgzlps() {
        return valgzlps;
    }

    public void setValgzlps(String valgzlps) {
        this.valgzlps = valgzlps;
    }

    public String getValgzlpsflag() {
        return valgzlpsflag;
    }

    public void setValgzlpsflag(String valgzlpsflag) {
        this.valgzlpsflag = valgzlpsflag;
    }

    public String getValgzlpsremark() {
        return valgzlpsremark;
    }

    public void setValgzlpsremark(String valgzlpsremark) {
        this.valgzlpsremark = valgzlpsremark;
    }

    public String getValzyl() {
        return valzyl;
    }

    public void setValzyl(String valzyl) {
        this.valzyl = valzyl;
    }

    public String getValzylflag() {
        return valzylflag;
    }

    public void setValzylflag(String valzylflag) {
        this.valzylflag = valzylflag;
    }

    public String getValzylremark() {
        return valzylremark;
    }

    public void setValzylremark(String valzylremark) {
        this.valzylremark = valzylremark;
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

    public String getDdacresult() {
        return ddacresult;
    }

    public void setDdacresult(String ddacresult) {
        this.ddacresult = ddacresult;
    }

    public String getDdremark() {
        return ddremark;
    }

    public void setDdremark(String ddremark) {
        this.ddremark = ddremark;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}