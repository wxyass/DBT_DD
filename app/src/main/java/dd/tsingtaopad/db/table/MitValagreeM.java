package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValagreeMDaoImpl;


/**
 * 终端追溯协议主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALAGREE_M", daoClass = MitValagreeMDaoImpl.class)
public class MitValagreeM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String valterid ;//varchar2(36) null,
    @DatabaseField
    private String agreecode ;// varchar2(128) null,
    @DatabaseField
    private String agencyname ;//varchar2(128) null,
    @DatabaseField
    private String agencykey  ;//varchar2(36) null,
    @DatabaseField
    private String moneyagency  ;//varchar2(128) null,
    @DatabaseField
    private String moneyagencykey ;// varchar2(36) null,
    @DatabaseField
    private String startdate ;// date null,
    @DatabaseField
    private String startdateflag ;//char(1) null,
    @DatabaseField
    private String startdateremark;// varchar2(500) null,
    @DatabaseField
    private String enddate  ;//date null,
    @DatabaseField
    private String enddateflag ;// char(1) null,
    @DatabaseField
    private String enddateremark ;//varchar2(500) null,
    @DatabaseField
    private String paytype  ;//varchar2(36) null,
    @DatabaseField
    private String contact ;// varchar2(50) null,
    @DatabaseField
    private String mobile ;//varchar2(50) null,
    @DatabaseField
    private String notes ;//varchar2(2000)  null,
    @DatabaseField
    private String notesflag ;// char(1) null,
    @DatabaseField
    private String notesremark ;// varchar2(500) null,
    @DatabaseField
    private String creuser  ;//varchar2(128) null,
    @DatabaseField
    private Date credate  ;//date null,
    @DatabaseField
    private String updateuser ;//varchar2(128) null,
    @DatabaseField
    private Date updatedate ;//date null,
    @DatabaseField
    private String uploadflag ;//char(1) null,
    @DatabaseField
    private String padisconsistent;// char(1) null

    /**
     * default constructor
     */
    public MitValagreeM() {
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

    public String getAgreecode() {
        return agreecode;
    }

    public void setAgreecode(String agreecode) {
        this.agreecode = agreecode;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getAgencykey() {
        return agencykey;
    }

    public void setAgencykey(String agencykey) {
        this.agencykey = agencykey;
    }

    public String getMoneyagency() {
        return moneyagency;
    }

    public void setMoneyagency(String moneyagency) {
        this.moneyagency = moneyagency;
    }

    public String getMoneyagencykey() {
        return moneyagencykey;
    }

    public void setMoneyagencykey(String moneyagencykey) {
        this.moneyagencykey = moneyagencykey;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStartdateflag() {
        return startdateflag;
    }

    public void setStartdateflag(String startdateflag) {
        this.startdateflag = startdateflag;
    }

    public String getStartdateremark() {
        return startdateremark;
    }

    public void setStartdateremark(String startdateremark) {
        this.startdateremark = startdateremark;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEnddateflag() {
        return enddateflag;
    }

    public void setEnddateflag(String enddateflag) {
        this.enddateflag = enddateflag;
    }

    public String getEnddateremark() {
        return enddateremark;
    }

    public void setEnddateremark(String enddateremark) {
        this.enddateremark = enddateremark;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotesflag() {
        return notesflag;
    }

    public void setNotesflag(String notesflag) {
        this.notesflag = notesflag;
    }

    public String getNotesremark() {
        return notesremark;
    }

    public void setNotesremark(String notesremark) {
        this.notesremark = notesremark;
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

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
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