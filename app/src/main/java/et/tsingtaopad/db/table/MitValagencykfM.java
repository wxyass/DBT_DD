package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitvalagencykfMDaoImpl;


/**
 * 经销商开发核查表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALAGENCYKF_M", daoClass = MitvalagencykfMDaoImpl.class)
public class MitValagencykfM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String gridkey;
    @DatabaseField
    private String agencyname ;//
    @DatabaseField
    private String agencynameflag ;//
    @DatabaseField
    private String agencyrealname ;//
    @DatabaseField
    private String agencynameremark ;//,
    @DatabaseField
    private String contact ;//
    @DatabaseField
    private String contactflag  ;//
    @DatabaseField
    private String contactreal  ;//
    @DatabaseField
    private String contactremark  ;//
    @DatabaseField
    private String mobile  ;//
    @DatabaseField
    private String mobileflag ;//
    @DatabaseField
    private String mobilereal ;//
    @DatabaseField
    private String mobileremark   ;//
    @DatabaseField
    private String address ;//
    @DatabaseField
    private String addressflag  ;//
    @DatabaseField
    private String addressreal  ;//
    @DatabaseField
    private String addressremark  ;//
    @DatabaseField
    private String area  ;//
    @DatabaseField
    private String areaflag   ;//
    @DatabaseField
    private String areareal   ;//
    @DatabaseField
    private String arearemark ;//
    @DatabaseField
    private String money ;//
    @DatabaseField
    private String moneyflag ;//
    @DatabaseField
    private String moneyreal  ;//
    @DatabaseField
    private String moneyremark  ;//
    @DatabaseField
    private String carnum  ;//
    @DatabaseField
    private String carnumflag ;//
    @DatabaseField
    private String carnumreal ;//
    @DatabaseField
    private String carnumremark   ;//
    @DatabaseField
    private String productname  ;//
    @DatabaseField
    private String productnameflag  ;//
    @DatabaseField
    private String productnamereal  ;//
    @DatabaseField
    private String productnameremark  ;//
    @DatabaseField
    private String business   ;//
    @DatabaseField
    private String businessflag   ;//
    @DatabaseField
    private String businessreal   ;//
    @DatabaseField
    private String businessremark ;//

    @DatabaseField
    private String persion   ;//
    @DatabaseField
    private String persionflag   ;//
    @DatabaseField
    private String persionreal   ;//
    @DatabaseField
    private String persionremark ;//

    @DatabaseField
    private String status  ;//
    @DatabaseField
    private String statusflag ;//
    @DatabaseField
    private String statusremark  ;//
    @DatabaseField
    private String coverterms;//
    @DatabaseField
    private String covertermflag ;//
    @DatabaseField
    private String covertermreal  ;//
    @DatabaseField
    private String covertermremark ;//
    @DatabaseField
    private String supplyterms ;//
    @DatabaseField
    private String supplytermsflag ;//
    @DatabaseField
    private String supplytermsreal ;//
    @DatabaseField
    private String supplytermsremark ;//
    @DatabaseField
    private String kfdate  ;//
    @DatabaseField
    private String kfdateflag;//
    @DatabaseField
    private String kfdatereal ;//
    @DatabaseField
    private String kfdateremark   ;//
    @DatabaseField
    private String passdate   ;//
    @DatabaseField
    private String passdateflag   ;//
    @DatabaseField
    private String passdatereal  ;//
    @DatabaseField
    private String passdateremark ;//
    @DatabaseField
    private String remark  ;//
    @DatabaseField
    private String creuser ;//
    @DatabaseField
    private String creuserareaid ;//
    @DatabaseField
    private Date credate ;//
    @DatabaseField
    private String updateuser;//
    @DatabaseField
    private Date updatedate ;//
    @DatabaseField
    private String uploadflag ;//
    @DatabaseField
    private String padisconsistent ;//



    /**
     * default constructor
     */
    public MitValagencykfM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getAgencynameflag() {
        return agencynameflag;
    }

    public void setAgencynameflag(String agencynameflag) {
        this.agencynameflag = agencynameflag;
    }

    public String getAgencyrealname() {
        return agencyrealname;
    }

    public void setAgencyrealname(String agencyrealname) {
        this.agencyrealname = agencyrealname;
    }

    public String getAgencynameremark() {
        return agencynameremark;
    }

    public void setAgencynameremark(String agencynameremark) {
        this.agencynameremark = agencynameremark;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactflag() {
        return contactflag;
    }

    public void setContactflag(String contactflag) {
        this.contactflag = contactflag;
    }

    public String getContactreal() {
        return contactreal;
    }

    public void setContactreal(String contactreal) {
        this.contactreal = contactreal;
    }

    public String getContactremark() {
        return contactremark;
    }

    public void setContactremark(String contactremark) {
        this.contactremark = contactremark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileflag() {
        return mobileflag;
    }

    public void setMobileflag(String mobileflag) {
        this.mobileflag = mobileflag;
    }

    public String getMobilereal() {
        return mobilereal;
    }

    public void setMobilereal(String mobilereal) {
        this.mobilereal = mobilereal;
    }

    public String getMobileremark() {
        return mobileremark;
    }

    public void setMobileremark(String mobileremark) {
        this.mobileremark = mobileremark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressflag() {
        return addressflag;
    }

    public void setAddressflag(String addressflag) {
        this.addressflag = addressflag;
    }

    public String getAddressreal() {
        return addressreal;
    }

    public void setAddressreal(String addressreal) {
        this.addressreal = addressreal;
    }

    public String getAddressremark() {
        return addressremark;
    }

    public void setAddressremark(String addressremark) {
        this.addressremark = addressremark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaflag() {
        return areaflag;
    }

    public void setAreaflag(String areaflag) {
        this.areaflag = areaflag;
    }

    public String getAreareal() {
        return areareal;
    }

    public void setAreareal(String areareal) {
        this.areareal = areareal;
    }

    public String getArearemark() {
        return arearemark;
    }

    public void setArearemark(String arearemark) {
        this.arearemark = arearemark;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoneyflag() {
        return moneyflag;
    }

    public void setMoneyflag(String moneyflag) {
        this.moneyflag = moneyflag;
    }

    public String getMoneyreal() {
        return moneyreal;
    }

    public void setMoneyreal(String moneyreal) {
        this.moneyreal = moneyreal;
    }

    public String getMoneyremark() {
        return moneyremark;
    }

    public void setMoneyremark(String moneyremark) {
        this.moneyremark = moneyremark;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getCarnumflag() {
        return carnumflag;
    }

    public void setCarnumflag(String carnumflag) {
        this.carnumflag = carnumflag;
    }

    public String getCarnumreal() {
        return carnumreal;
    }

    public void setCarnumreal(String carnumreal) {
        this.carnumreal = carnumreal;
    }

    public String getCarnumremark() {
        return carnumremark;
    }

    public void setCarnumremark(String carnumremark) {
        this.carnumremark = carnumremark;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductnameflag() {
        return productnameflag;
    }

    public void setProductnameflag(String productnameflag) {
        this.productnameflag = productnameflag;
    }

    public String getProductnamereal() {
        return productnamereal;
    }

    public void setProductnamereal(String productnamereal) {
        this.productnamereal = productnamereal;
    }

    public String getProductnameremark() {
        return productnameremark;
    }

    public void setProductnameremark(String productnameremark) {
        this.productnameremark = productnameremark;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusinessflag() {
        return businessflag;
    }

    public void setBusinessflag(String businessflag) {
        this.businessflag = businessflag;
    }

    public String getBusinessreal() {
        return businessreal;
    }

    public void setBusinessreal(String businessreal) {
        this.businessreal = businessreal;
    }

    public String getBusinessremark() {
        return businessremark;
    }

    public void setBusinessremark(String businessremark) {
        this.businessremark = businessremark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusflag() {
        return statusflag;
    }

    public void setStatusflag(String statusflag) {
        this.statusflag = statusflag;
    }

    public String getStatusremark() {
        return statusremark;
    }

    public void setStatusremark(String statusremark) {
        this.statusremark = statusremark;
    }

    public String getCoverterms() {
        return coverterms;
    }

    public void setCoverterms(String coverterms) {
        this.coverterms = coverterms;
    }

    public String getCovertermflag() {
        return covertermflag;
    }

    public void setCovertermflag(String covertermflag) {
        this.covertermflag = covertermflag;
    }

    public String getCovertermreal() {
        return covertermreal;
    }

    public void setCovertermreal(String covertermreal) {
        this.covertermreal = covertermreal;
    }

    public String getCovertermremark() {
        return covertermremark;
    }

    public void setCovertermremark(String covertermremark) {
        this.covertermremark = covertermremark;
    }

    public String getSupplyterms() {
        return supplyterms;
    }

    public void setSupplyterms(String supplyterms) {
        this.supplyterms = supplyterms;
    }

    public String getSupplytermsflag() {
        return supplytermsflag;
    }

    public void setSupplytermsflag(String supplytermsflag) {
        this.supplytermsflag = supplytermsflag;
    }

    public String getSupplytermsreal() {
        return supplytermsreal;
    }

    public void setSupplytermsreal(String supplytermsreal) {
        this.supplytermsreal = supplytermsreal;
    }

    public String getSupplytermsremark() {
        return supplytermsremark;
    }

    public void setSupplytermsremark(String supplytermsremark) {
        this.supplytermsremark = supplytermsremark;
    }

    public String getKfdate() {
        return kfdate;
    }

    public void setKfdate(String kfdate) {
        this.kfdate = kfdate;
    }

    public String getKfdateflag() {
        return kfdateflag;
    }

    public void setKfdateflag(String kfdateflag) {
        this.kfdateflag = kfdateflag;
    }

    public String getKfdatereal() {
        return kfdatereal;
    }

    public void setKfdatereal(String kfdatereal) {
        this.kfdatereal = kfdatereal;
    }

    public String getKfdateremark() {
        return kfdateremark;
    }

    public void setKfdateremark(String kfdateremark) {
        this.kfdateremark = kfdateremark;
    }

    public String getPassdate() {
        return passdate;
    }

    public void setPassdate(String passdate) {
        this.passdate = passdate;
    }

    public String getPassdateflag() {
        return passdateflag;
    }

    public void setPassdateflag(String passdateflag) {
        this.passdateflag = passdateflag;
    }

    public String getPassdatereal() {
        return passdatereal;
    }

    public void setPassdatereal(String passdatereal) {
        this.passdatereal = passdatereal;
    }

    public String getPassdateremark() {
        return passdateremark;
    }

    public void setPassdateremark(String passdateremark) {
        this.passdateremark = passdateremark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getPersion() {
        return persion;
    }

    public void setPersion(String persion) {
        this.persion = persion;
    }

    public String getPersionflag() {
        return persionflag;
    }

    public void setPersionflag(String persionflag) {
        this.persionflag = persionflag;
    }

    public String getPersionreal() {
        return persionreal;
    }

    public void setPersionreal(String persionreal) {
        this.persionreal = persionreal;
    }

    public String getPersionremark() {
        return persionremark;
    }

    public void setPersionremark(String persionremark) {
        this.persionremark = persionremark;
    }
}