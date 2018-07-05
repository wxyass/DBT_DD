package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MitValagreedetailMTempDaoImpl;


/**
 * 终端追溯协议对付信息表 临时表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALAGREEDETAIL_M_TEMP", daoClass = MitValagreedetailMTempDaoImpl.class)
public class MitValagreedetailMTemp implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String valterid ;//
    @DatabaseField
    private String valagreeid ;//
    @DatabaseField
    private String prokey ;//
    @DatabaseField
    private String proname ;//

    @DatabaseField
    private String cashtype ;//
    @DatabaseField
    private String commoney ;//
    @DatabaseField
    private String trunnum  ;//
    @DatabaseField
    private String price ;//
    @DatabaseField
    private String cashdate;//
    @DatabaseField
    private String agreedetailflag ;//
    @DatabaseField
    private String erroritem  ;//
    @DatabaseField
    private String remarks  ;//
    @DatabaseField
    private String uploadflag ;//
    @DatabaseField
    private String padisconsistent ;//

    @DatabaseField
    private String truepro ;//
    @DatabaseField
    private String truemoney ;//
    @DatabaseField
    private String truenum ;//


    /**
     * default constructor
     */
    public MitValagreedetailMTemp() {
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

    public String getValagreeid() {
        return valagreeid;
    }

    public void setValagreeid(String valagreeid) {
        this.valagreeid = valagreeid;
    }

    public String getProkey() {
        return prokey;
    }

    public void setProkey(String prokey) {
        this.prokey = prokey;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getCashtype() {
        return cashtype;
    }

    public void setCashtype(String cashtype) {
        this.cashtype = cashtype;
    }

    public String getCommoney() {
        return commoney;
    }

    public void setCommoney(String commoney) {
        this.commoney = commoney;
    }

    public String getTrunnum() {
        return trunnum;
    }

    public void setTrunnum(String trunnum) {
        this.trunnum = trunnum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCashdate() {
        return cashdate;
    }

    public void setCashdate(String cashdate) {
        this.cashdate = cashdate;
    }

    public String getAgreedetailflag() {
        return agreedetailflag;
    }

    public void setAgreedetailflag(String agreedetailflag) {
        this.agreedetailflag = agreedetailflag;
    }

    public String getErroritem() {
        return erroritem;
    }

    public void setErroritem(String erroritem) {
        this.erroritem = erroritem;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getTruepro() {
        return truepro;
    }

    public void setTruepro(String truepro) {
        this.truepro = truepro;
    }

    public String getTruemoney() {
        return truemoney;
    }

    public void setTruemoney(String truemoney) {
        this.truemoney = truemoney;
    }

    public String getTruenum() {
        return truenum;
    }

    public void setTruenum(String truenum) {
        this.truenum = truenum;
    }
}