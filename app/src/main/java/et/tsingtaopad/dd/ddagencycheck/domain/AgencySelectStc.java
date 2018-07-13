package et.tsingtaopad.dd.ddagencycheck.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：VisitAgencyStc.java</br>
 * 作者：吴欣伟   </br>
 * 创建时间：2013-11-27</br>      
 * 功能描述: 选择经销商结构体</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
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
    
}
