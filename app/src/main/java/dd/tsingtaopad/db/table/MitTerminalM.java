package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitTerminalMDaoImpl;


/**
 * 督导新增终端表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_TERMINAL_M", daoClass = MitTerminalMDaoImpl.class)
public class MitTerminalM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String terminalkey;
    @DatabaseField
    private String terminalcode ;//
    @DatabaseField
    private String terminalname ;//
    @DatabaseField
    private String routekey ;//
    @DatabaseField
    private String areatype ;//
    @DatabaseField
    private String tlevel  ;//
    @DatabaseField
    private String province ;//
    @DatabaseField
    private String city ;//
    @DatabaseField
    private String county  ;//
    @DatabaseField
    private String address;//
    @DatabaseField
    private String contact ;//
    @DatabaseField
    private String mobile  ;//
    @DatabaseField
    private String sequence ;//
    @DatabaseField
    private String cycle  ;//
    @DatabaseField
    private String hvolume ;//
    @DatabaseField
    private String zvolume ;//
    @DatabaseField
    private String pvolume ;//
    @DatabaseField
    private String dvolume ;//
    @DatabaseField
    private String sellchannel ;//
    @DatabaseField
    private String mainchannel ;//
    @DatabaseField
    private String minorchannel ;//
    @DatabaseField
    private String creuser ;//
    @DatabaseField
    private String creuserareaid  ;//
    @DatabaseField
    private Date credate ;//
    @DatabaseField
    private String updateuser  ;//
    @DatabaseField
    private Date updatedate  ;//
    @DatabaseField
    private String uploadflag ;//
    @DatabaseField
    private String padisconsistent ;//



    /**
     * default constructor
     */
    public MitTerminalM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getTerminalcode() {
        return terminalcode;
    }

    public void setTerminalcode(String terminalcode) {
        this.terminalcode = terminalcode;
    }

    public String getTerminalname() {
        return terminalname;
    }

    public void setTerminalname(String terminalname) {
        this.terminalname = terminalname;
    }

    public String getRoutekey() {
        return routekey;
    }

    public void setRoutekey(String routekey) {
        this.routekey = routekey;
    }

    public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype;
    }

    public String getTlevel() {
        return tlevel;
    }

    public void setTlevel(String tlevel) {
        this.tlevel = tlevel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getHvolume() {
        return hvolume;
    }

    public void setHvolume(String hvolume) {
        this.hvolume = hvolume;
    }

    public String getZvolume() {
        return zvolume;
    }

    public void setZvolume(String zvolume) {
        this.zvolume = zvolume;
    }

    public String getPvolume() {
        return pvolume;
    }

    public void setPvolume(String pvolume) {
        this.pvolume = pvolume;
    }

    public String getDvolume() {
        return dvolume;
    }

    public void setDvolume(String dvolume) {
        this.dvolume = dvolume;
    }

    public String getSellchannel() {
        return sellchannel;
    }

    public void setSellchannel(String sellchannel) {
        this.sellchannel = sellchannel;
    }

    public String getMainchannel() {
        return mainchannel;
    }

    public void setMainchannel(String mainchannel) {
        this.mainchannel = mainchannel;
    }

    public String getMinorchannel() {
        return minorchannel;
    }

    public void setMinorchannel(String minorchannel) {
        this.minorchannel = minorchannel;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public String getCreuserareaid() {
        return creuserareaid;
    }

    public void setCreuserareaid(String creuserareaid) {
        this.creuserareaid = creuserareaid;
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