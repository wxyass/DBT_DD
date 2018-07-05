package dd.tsingtaopad.main.visit.agencyvisit.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 选择经销商结构体</br>
 */
public class AgencySelectStc implements Serializable{
    
    private static final long serialVersionUID = 354588046223035993L;
    
    //经销商主键
    private String agencyKey;
    
    //经销商名称
    private String agencyName;
    
    //地址
    private String addr;
    
    //联系电话
    private String phone;

    //经销商负责人
    private String contact;
    //经销商编码
    private String agencycode;

    public String getAgencyKey() {
        return agencyKey;
    }

    public void setAgencyKey(String agencyKey) {
        this.agencyKey = agencyKey;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAgencycode() {
        return agencycode;
    }

    public void setAgencycode(String agencycode) {
        this.agencycode = agencycode;
    }
}
