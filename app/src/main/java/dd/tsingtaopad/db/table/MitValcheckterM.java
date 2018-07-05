package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValcheckterMDaoImpl;


/**
 * 追溯终端配置表
 *
 * MitValcheckterM entity. @author MyEclipse Persistence Tools
 */
//MitValcheckterM
@DatabaseTable(tableName = "MIT_VALCHECKTER_M", daoClass = MitValcheckterMDaoImpl.class)
public class MitValcheckterM implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;
    @DatabaseField
    private String areaid;
    @DatabaseField
    private String vaildter;
    @DatabaseField
    private String vaildvisit;
    @DatabaseField
    private String ifmine;
    @DatabaseField
    private String salesarea;
    @DatabaseField
    private String terworkstatus;
    @DatabaseField
    private String terminalcode;
    @DatabaseField
    private String routecode;
    @DatabaseField
    private String tername;
    @DatabaseField
    private String terlevel;
    @DatabaseField
    private String province;
    @DatabaseField
    private String city;
    @DatabaseField
    private String country;
    @DatabaseField
    private String address;
    @DatabaseField
    private String contact;
    @DatabaseField
    private String mobile;
    @DatabaseField
    private String cylie;
    @DatabaseField
    private String visitorder;
    @DatabaseField
    private String hvolume;
    @DatabaseField
    private String zvolume;
    @DatabaseField
    private String pvolume;
    @DatabaseField
    private String lvolume;
    @DatabaseField
    private String areatype;
    @DatabaseField
    private String sellchannel;
    @DatabaseField
    private String mainchannel;
    @DatabaseField
    private String minorchannel;
    @DatabaseField
    private String visituser;
    @DatabaseField
    private String addsupply;
    @DatabaseField
    private String losesupply;
    @DatabaseField
    private String iffleeing;
    @DatabaseField
    private String proerror;
    @DatabaseField
    private String agencyerror;
    @DatabaseField
    private String dataerror;
    @DatabaseField
    private String distrbution;
    @DatabaseField
    private String goodsvivi;
    @DatabaseField
    private String provivi;
    @DatabaseField
    private String icevivi;
    @DatabaseField
    private String salespromotion;
    @DatabaseField
    private String grouppro;
    @DatabaseField
    private String cooperation;
    @DatabaseField
    private String highps;
    @DatabaseField
    private String prooccupy;
    @DatabaseField
    private String addcmp;
    @DatabaseField
    private String losecmp;
    @DatabaseField
    private String cmperror;
    @DatabaseField
    private String cmpagencyerror;
    @DatabaseField
    private String cmpdataerror;
    @DatabaseField
    private String ifcmp;
    @DatabaseField
    private String visinote;
    @DatabaseField
    private String creuser;
    @DatabaseField
    private Date credate;
    @DatabaseField
    private String updateuser;
    @DatabaseField
    private Date updatedate;
    @DatabaseField
    private String padisconsistent;

    // Constructors
    /**
     * default constructor
     */
    public MitValcheckterM() {
    }

    public MitValcheckterM(String id, String areaid, String vaildter, String vaildvisit, String ifmine, String salesarea, String terworkstatus, String terminalcode, String routecode, String tername, String terlevel, String province, String city, String country, String address, String contact, String mobile, String cylie, String visitorder, String hvolume, String zvolume, String pvolume, String lvolume, String areatype, String sellchannel, String mainchannel, String minorchannel, String visituser, String addsupply, String losesupply, String iffleeing, String proerror, String agencyerror, String dataerror, String distrbution, String goodsvivi, String provivi, String icevivi, String salespromotion, String grouppro, String cooperation, String highps, String prooccupy, String addcmp, String losecmp, String cmperror, String cmpagencyerror, String cmpdataerror, String ifcmp, String visinote, String creuser, Date credate, String updateuser, Date updatedate) {
        this.id = id;
        this.areaid = areaid;
        this.vaildter = vaildter;
        this.vaildvisit = vaildvisit;
        this.ifmine = ifmine;
        this.salesarea = salesarea;
        this.terworkstatus = terworkstatus;
        this.terminalcode = terminalcode;
        this.routecode = routecode;
        this.tername = tername;
        this.terlevel = terlevel;
        this.province = province;
        this.city = city;
        this.country = country;
        this.address = address;
        this.contact = contact;
        this.mobile = mobile;
        this.cylie = cylie;
        this.visitorder = visitorder;
        this.hvolume = hvolume;
        this.zvolume = zvolume;
        this.pvolume = pvolume;
        this.lvolume = lvolume;
        this.areatype = areatype;
        this.sellchannel = sellchannel;
        this.mainchannel = mainchannel;
        this.minorchannel = minorchannel;
        this.visituser = visituser;
        this.addsupply = addsupply;
        this.losesupply = losesupply;
        this.iffleeing = iffleeing;
        this.proerror = proerror;
        this.agencyerror = agencyerror;
        this.dataerror = dataerror;
        this.distrbution = distrbution;
        this.goodsvivi = goodsvivi;
        this.provivi = provivi;
        this.icevivi = icevivi;
        this.salespromotion = salespromotion;
        this.grouppro = grouppro;
        this.cooperation = cooperation;
        this.highps = highps;
        this.prooccupy = prooccupy;
        this.addcmp = addcmp;
        this.losecmp = losecmp;
        this.cmperror = cmperror;
        this.cmpagencyerror = cmpagencyerror;
        this.cmpdataerror = cmpdataerror;
        this.ifcmp = ifcmp;
        this.visinote = visinote;
        this.creuser = creuser;
        this.credate = credate;
        this.updateuser = updateuser;
        this.updatedate = updatedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getVaildter() {
        return vaildter;
    }

    public void setVaildter(String vaildter) {
        this.vaildter = vaildter;
    }

    public String getVaildvisit() {
        return vaildvisit;
    }

    public void setVaildvisit(String vaildvisit) {
        this.vaildvisit = vaildvisit;
    }

    public String getIfmine() {
        return ifmine;
    }

    public void setIfmine(String ifmine) {
        this.ifmine = ifmine;
    }

    public String getSalesarea() {
        return salesarea;
    }

    public void setSalesarea(String salesarea) {
        this.salesarea = salesarea;
    }

    public String getTerworkstatus() {
        return terworkstatus;
    }

    public void setTerworkstatus(String terworkstatus) {
        this.terworkstatus = terworkstatus;
    }

    public String getTerminalcode() {
        return terminalcode;
    }

    public void setTerminalcode(String terminalcode) {
        this.terminalcode = terminalcode;
    }

    public String getRoutecode() {
        return routecode;
    }

    public void setRoutecode(String routecode) {
        this.routecode = routecode;
    }

    public String getTername() {
        return tername;
    }

    public void setTername(String tername) {
        this.tername = tername;
    }

    public String getTerlevel() {
        return terlevel;
    }

    public void setTerlevel(String terlevel) {
        this.terlevel = terlevel;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCylie() {
        return cylie;
    }

    public void setCylie(String cylie) {
        this.cylie = cylie;
    }

    public String getVisitorder() {
        return visitorder;
    }

    public void setVisitorder(String visitorder) {
        this.visitorder = visitorder;
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

    public String getLvolume() {
        return lvolume;
    }

    public void setLvolume(String lvolume) {
        this.lvolume = lvolume;
    }

    public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype;
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

    public String getVisituser() {
        return visituser;
    }

    public void setVisituser(String visituser) {
        this.visituser = visituser;
    }

    public String getAddsupply() {
        return addsupply;
    }

    public void setAddsupply(String addsupply) {
        this.addsupply = addsupply;
    }

    public String getLosesupply() {
        return losesupply;
    }

    public void setLosesupply(String losesupply) {
        this.losesupply = losesupply;
    }

    public String getIffleeing() {
        return iffleeing;
    }

    public void setIffleeing(String iffleeing) {
        this.iffleeing = iffleeing;
    }

    public String getProerror() {
        return proerror;
    }

    public void setProerror(String proerror) {
        this.proerror = proerror;
    }

    public String getAgencyerror() {
        return agencyerror;
    }

    public void setAgencyerror(String agencyerror) {
        this.agencyerror = agencyerror;
    }

    public String getDataerror() {
        return dataerror;
    }

    public void setDataerror(String dataerror) {
        this.dataerror = dataerror;
    }

    public String getDistrbution() {
        return distrbution;
    }

    public void setDistrbution(String distrbution) {
        this.distrbution = distrbution;
    }

    public String getGoodsvivi() {
        return goodsvivi;
    }

    public void setGoodsvivi(String goodsvivi) {
        this.goodsvivi = goodsvivi;
    }

    public String getProvivi() {
        return provivi;
    }

    public void setProvivi(String provivi) {
        this.provivi = provivi;
    }

    public String getIcevivi() {
        return icevivi;
    }

    public void setIcevivi(String icevivi) {
        this.icevivi = icevivi;
    }

    public String getSalespromotion() {
        return salespromotion;
    }

    public void setSalespromotion(String salespromotion) {
        this.salespromotion = salespromotion;
    }

    public String getGrouppro() {
        return grouppro;
    }

    public void setGrouppro(String grouppro) {
        this.grouppro = grouppro;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    public String getHighps() {
        return highps;
    }

    public void setHighps(String highps) {
        this.highps = highps;
    }

    public String getProoccupy() {
        return prooccupy;
    }

    public void setProoccupy(String prooccupy) {
        this.prooccupy = prooccupy;
    }

    public String getAddcmp() {
        return addcmp;
    }

    public void setAddcmp(String addcmp) {
        this.addcmp = addcmp;
    }

    public String getLosecmp() {
        return losecmp;
    }

    public void setLosecmp(String losecmp) {
        this.losecmp = losecmp;
    }

    public String getCmperror() {
        return cmperror;
    }

    public void setCmperror(String cmperror) {
        this.cmperror = cmperror;
    }

    public String getCmpagencyerror() {
        return cmpagencyerror;
    }

    public void setCmpagencyerror(String cmpagencyerror) {
        this.cmpagencyerror = cmpagencyerror;
    }

    public String getCmpdataerror() {
        return cmpdataerror;
    }

    public void setCmpdataerror(String cmpdataerror) {
        this.cmpdataerror = cmpdataerror;
    }

    public String getIfcmp() {
        return ifcmp;
    }

    public void setIfcmp(String ifcmp) {
        this.ifcmp = ifcmp;
    }

    public String getVisinote() {
        return visinote;
    }

    public void setVisinote(String visinote) {
        this.visinote = visinote;
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

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}