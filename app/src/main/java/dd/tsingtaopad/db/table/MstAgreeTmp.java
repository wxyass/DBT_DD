package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MstAgreeTmpDaoImpl;


/**
 * 协议主表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGREE_TMP", daoClass = MstAgreeTmpDaoImpl.class)
public class MstAgreeTmp implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String agencyname;//
    @DatabaseField
    private String agreecd;//
    @DatabaseField
    private String dealid;//
    @DatabaseField
    private String enddate;//
    @DatabaseField
    private String mobile;//
    @DatabaseField
    private String notes;
    @DatabaseField
    private String paymentagencyname;//
    @DatabaseField
    private String paytype;//
    @DatabaseField
    private String signer;//
    @DatabaseField
    private String startdate;//
    @DatabaseField
    private String suppierid;//

    @DatabaseField
    private String terminalkey	;//

    // Constructors

    /**
     * default constructor
     */
    public MstAgreeTmp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getAgreecd() {
        return agreecd;
    }

    public void setAgreecd(String agreecd) {
        this.agreecd = agreecd;
    }

    public String getDealid() {
        return dealid;
    }

    public void setDealid(String dealid) {
        this.dealid = dealid;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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

    public String getPaymentagencyname() {
        return paymentagencyname;
    }

    public void setPaymentagencyname(String paymentagencyname) {
        this.paymentagencyname = paymentagencyname;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getSuppierid() {
        return suppierid;
    }

    public void setSuppierid(String suppierid) {
        this.suppierid = suppierid;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }
}