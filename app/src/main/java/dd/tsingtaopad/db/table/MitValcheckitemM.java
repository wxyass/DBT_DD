package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValcheckitemMDaoImpl;


/**
 * 追溯采集项表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALCHECKITEM_M", daoClass = MitValcheckitemMDaoImpl.class)
public class MitValcheckitemM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String valchecktypeid;// 终端追溯查指标表ID
    @DatabaseField
    private String valitemid ;//  采集项表主键 varchar2(36) null,
    @DatabaseField
    private String colitemkey  ;// 采集项key   varchar2(36) null,
    @DatabaseField
    private String checkkey ;// 指标 varchar2(36) null,
    @DatabaseField
    private String addcount;//  变化量varchar2(36) null,
    @DatabaseField
    private String totalcount;// 现有量 varchar2(36) null,
    @DatabaseField
    private String productkey ;// 产品key varchar2(36) null,
    @DatabaseField
    private String valitem  ;// 采集项原值结果量 varchar2(10) null,
    @DatabaseField
    private String valitemval ;// 采集项正确值结果量 varchar2(10) null,
    @DatabaseField
    private String valitemremark ;// 采集项备注 varchar2(300) null,
    @DatabaseField
    private String creuser ;//  varchar2(128) null,
    @DatabaseField
    private Date credate ;//  date  null,
    @DatabaseField
    private String updateuser ;// varchar2(128) null,
    @DatabaseField
    private String visitkey ;// 拜访主键
    @DatabaseField
    private Date updatedate ;// date  null


    /**
     * default constructor
     */
    public MitValcheckitemM() {
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

    public String getValitemid() {
        return valitemid;
    }

    public void setValitemid(String valitemid) {
        this.valitemid = valitemid;
    }

    public String getColitemkey() {
        return colitemkey;
    }

    public void setColitemkey(String colitemkey) {
        this.colitemkey = colitemkey;
    }

    public String getCheckkey() {
        return checkkey;
    }

    public void setCheckkey(String checkkey) {
        this.checkkey = checkkey;
    }

    public String getAddcount() {
        return addcount;
    }

    public void setAddcount(String addcount) {
        this.addcount = addcount;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

    public String getValitem() {
        return valitem;
    }

    public void setValitem(String valitem) {
        this.valitem = valitem;
    }

    public String getValitemval() {
        return valitemval;
    }

    public void setValitemval(String valitemval) {
        this.valitemval = valitemval;
    }

    public String getValitemremark() {
        return valitemremark;
    }

    public void setValitemremark(String valitemremark) {
        this.valitemremark = valitemremark;
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

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public String getValchecktypeid() {
        return valchecktypeid;
    }

    public void setValchecktypeid(String valchecktypeid) {
        this.valchecktypeid = valchecktypeid;
    }
}