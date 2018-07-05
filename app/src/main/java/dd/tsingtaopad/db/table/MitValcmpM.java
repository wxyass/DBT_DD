package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValcmpMDaoImpl;


/**
 * 追溯聊竞品供货关系表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALCMP_M", daoClass = MitValcmpMDaoImpl.class)
public class MitValcmpM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String valaddagencysupply;//   是否新增竞品供货关系
    @DatabaseField
    private String valagencysupplyid;//    供货关系ID
    @DatabaseField
    private String valcmpjdj;//    竞品进店价
    @DatabaseField
    private String valcmplsj;//    竞品零售价
    @DatabaseField
    private String valcmpsales;//  竞品销量
    @DatabaseField
    private String valcmpkc;//竞品库存
    @DatabaseField
    private String valcmpremark;//  竞品描述
    @DatabaseField
    private String valcmpagency;//   竞品供货商
    @DatabaseField
    private String valcmpid;// 竞品ID
    @DatabaseField
    private String valcmpname;// 竞品名称
    @DatabaseField
    private String valiscmpter;//终端
    @DatabaseField
    private String valagencysupplyflag;// 供货关系正确与否
    @DatabaseField
    private String valproerror;// 品项有误
    @DatabaseField
    private String valagencyerror;// 经销商有误
    @DatabaseField
    private String valdataerror;//  数据有误
    @DatabaseField
    private String valcmpjdjval;//   正确竞品进店价
    @DatabaseField
    private String valcmplsjval;//   正确竞品零售价
    @DatabaseField
    private String valcmpsalesval;// 正确竞品销量
    @DatabaseField
    private String valcmpkcval;// 正确竞品库存
    @DatabaseField
    private String valcmpsupremark;// 竞品供货关系备注
    @DatabaseField
    private String valcmpremarkval;// 正确竞品描述
    @DatabaseField
    private String valistrueflag;//  是否成功瓦解竞品正确与否
    @DatabaseField
    private String valistruecmpval;//是否成功瓦解竞品原值
    @DatabaseField
    private String valiscmpremark;// 是否成功瓦解竞品备注
    @DatabaseField
    private String valvisitremark;// 拜访记录
    @DatabaseField
    private String valcmpagencyval;// 竞品经销商正确值

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
    public MitValcmpM() {
    }

    public MitValcmpM(String id, String valterid, String valaddagencysupply, String valagencysupplyid, String valcmpjdj, String valcmplsj, String valcmpsales, String valcmpkc, String valcmpremark, String valcmpagency, String valcmpid, String valiscmpter, String valagencysupplyflag, String valproerror, String valagencyerror, String valdataerror, String valcmpjdjval, String valcmplsjval, String valcmpsalesval, String valcmpkcval, String valcmpsupremark, String valcmpremarkval, String valistrueflag, String valistruecmpval, String valiscmpremark, String valvisitremark) {
        this.id = id;
        this.valterid = valterid;
        this.valaddagencysupply = valaddagencysupply;
        this.valagencysupplyid = valagencysupplyid;
        this.valcmpjdj = valcmpjdj;
        this.valcmplsj = valcmplsj;
        this.valcmpsales = valcmpsales;
        this.valcmpkc = valcmpkc;
        this.valcmpremark = valcmpremark;
        this.valcmpagency = valcmpagency;
        this.valcmpid = valcmpid;
        this.valiscmpter = valiscmpter;
        this.valagencysupplyflag = valagencysupplyflag;
        this.valproerror = valproerror;
        this.valagencyerror = valagencyerror;
        this.valdataerror = valdataerror;
        this.valcmpjdjval = valcmpjdjval;
        this.valcmplsjval = valcmplsjval;
        this.valcmpsalesval = valcmpsalesval;
        this.valcmpkcval = valcmpkcval;
        this.valcmpsupremark = valcmpsupremark;
        this.valcmpremarkval = valcmpremarkval;
        this.valistrueflag = valistrueflag;
        this.valistruecmpval = valistruecmpval;
        this.valiscmpremark = valiscmpremark;
        this.valvisitremark = valvisitremark;
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

    public String getValaddagencysupply() {
        return valaddagencysupply;
    }

    public void setValaddagencysupply(String valaddagencysupply) {
        this.valaddagencysupply = valaddagencysupply;
    }

    public String getValagencysupplyid() {
        return valagencysupplyid;
    }

    public void setValagencysupplyid(String valagencysupplyid) {
        this.valagencysupplyid = valagencysupplyid;
    }

    public String getValcmpjdj() {
        return valcmpjdj;
    }

    public void setValcmpjdj(String valcmpjdj) {
        this.valcmpjdj = valcmpjdj;
    }

    public String getValcmplsj() {
        return valcmplsj;
    }

    public void setValcmplsj(String valcmplsj) {
        this.valcmplsj = valcmplsj;
    }

    public String getValcmpsales() {
        return valcmpsales;
    }

    public void setValcmpsales(String valcmpsales) {
        this.valcmpsales = valcmpsales;
    }

    public String getValcmpkc() {
        return valcmpkc;
    }

    public void setValcmpkc(String valcmpkc) {
        this.valcmpkc = valcmpkc;
    }

    public String getValcmpremark() {
        return valcmpremark;
    }

    public void setValcmpremark(String valcmpremark) {
        this.valcmpremark = valcmpremark;
    }

    public String getValcmpagency() {
        return valcmpagency;
    }

    public void setValcmpagency(String valcmpagency) {
        this.valcmpagency = valcmpagency;
    }

    public String getValcmpid() {
        return valcmpid;
    }

    public void setValcmpid(String valcmpid) {
        this.valcmpid = valcmpid;
    }

    public String getValiscmpter() {
        return valiscmpter;
    }

    public void setValiscmpter(String valiscmpter) {
        this.valiscmpter = valiscmpter;
    }

    public String getValagencysupplyflag() {
        return valagencysupplyflag;
    }

    public void setValagencysupplyflag(String valagencysupplyflag) {
        this.valagencysupplyflag = valagencysupplyflag;
    }

    public String getValproerror() {
        return valproerror;
    }

    public void setValproerror(String valproerror) {
        this.valproerror = valproerror;
    }

    public String getValagencyerror() {
        return valagencyerror;
    }

    public void setValagencyerror(String valagencyerror) {
        this.valagencyerror = valagencyerror;
    }

    public String getValdataerror() {
        return valdataerror;
    }

    public void setValdataerror(String valdataerror) {
        this.valdataerror = valdataerror;
    }

    public String getValcmpjdjval() {
        return valcmpjdjval;
    }

    public void setValcmpjdjval(String valcmpjdjval) {
        this.valcmpjdjval = valcmpjdjval;
    }

    public String getValcmplsjval() {
        return valcmplsjval;
    }

    public void setValcmplsjval(String valcmplsjval) {
        this.valcmplsjval = valcmplsjval;
    }

    public String getValcmpsalesval() {
        return valcmpsalesval;
    }

    public void setValcmpsalesval(String valcmpsalesval) {
        this.valcmpsalesval = valcmpsalesval;
    }

    public String getValcmpkcval() {
        return valcmpkcval;
    }

    public void setValcmpkcval(String valcmpkcval) {
        this.valcmpkcval = valcmpkcval;
    }

    public String getValcmpsupremark() {
        return valcmpsupremark;
    }

    public void setValcmpsupremark(String valcmpsupremark) {
        this.valcmpsupremark = valcmpsupremark;
    }

    public String getValcmpremarkval() {
        return valcmpremarkval;
    }

    public void setValcmpremarkval(String valcmpremarkval) {
        this.valcmpremarkval = valcmpremarkval;
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

    public String getValcmpname() {
        return valcmpname;
    }

    public void setValcmpname(String valcmpname) {
        this.valcmpname = valcmpname;
    }

    public String getValcmpagencyval() {
        return valcmpagencyval;
    }

    public void setValcmpagencyval(String valcmpagencyval) {
        this.valcmpagencyval = valcmpagencyval;
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
}