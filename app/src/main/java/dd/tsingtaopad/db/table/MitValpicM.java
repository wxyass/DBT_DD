package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValpicMDaoImpl;


/**
 * 追溯拍照表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALPIC_M", daoClass = MitValpicMDaoImpl.class)
public class MitValpicM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String padisconsistent;// 是否已上传  0:未上传 1:已上传
    @DatabaseField
    private String valterid;// 终端追溯主表ID
    @DatabaseField
    private String imagefileString ;// 图片流 varchar2(4000) null,
    @DatabaseField
    private String pictypekey ;// 照片类型 varchar2(36) null,
    @DatabaseField
    private String pictypename;// 图片类型(中文名称)
    @DatabaseField
    private String picname ;// 照片名称 varchar2(36) null,
    @DatabaseField
    private String picpath ;// 照片路劲 varchar2(36) null,
    @DatabaseField
    private String areaid ;// 二级区域ID varchar2(36) null,
    @DatabaseField
    private String gridkey ;//定格id  varchar2(36) null,
    @DatabaseField
    private String routekey ;//路线id  varchar2(36) null,
    @DatabaseField
    private String terminalkey;//  终端id varchar2(36) null,
    @DatabaseField
    private String creuser;//  varchar2(128) null,
    @DatabaseField
    private Date credate ;// date null,
    @DatabaseField
    private String updateuser;//  varchar2(128) null,
    @DatabaseField
    private Date updatedate ;// date null

    @DatabaseField
    private String terminalname;//  终端名称
    @DatabaseField
    private String cameradata;//  拍照时间

    @DatabaseField
    private String areapid ;// 大区


    // Constructors

    /**
     * default constructor
     */
    public MitValpicM() {
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

    public String getImagefileString() {
        return imagefileString;
    }

    public void setImagefileString(String imagefileString) {
        this.imagefileString = imagefileString;
    }

    public String getPictypekey() {
        return pictypekey;
    }

    public void setPictypekey(String pictypekey) {
        this.pictypekey = pictypekey;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }

    public String getRoutekey() {
        return routekey;
    }

    public void setRoutekey(String routekey) {
        this.routekey = routekey;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
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

    public String getPictypename() {
        return pictypename;
    }

    public void setPictypename(String pictypename) {
        this.pictypename = pictypename;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public String getTerminalname() {
        return terminalname;
    }

    public void setTerminalname(String terminalname) {
        this.terminalname = terminalname;
    }

    public String getCameradata() {
        return cameradata;
    }

    public void setCameradata(String cameradata) {
        this.cameradata = cameradata;
    }

    public String getAreapid() {
        return areapid;
    }

    public void setAreapid(String areapid) {
        this.areapid = areapid;
    }
}