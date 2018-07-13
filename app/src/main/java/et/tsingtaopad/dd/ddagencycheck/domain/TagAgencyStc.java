package et.tsingtaopad.dd.ddagencycheck.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：TransferAgencyStc.java</br>
 * 作者：吴欣伟   </br>
 * 创建时间：2013-12-3</br>      
 * 功能描述: 可调货的经销结构体</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class TagAgencyStc implements Serializable{

    private static final long serialVersionUID = -6709724739175761515L;
    
    private String agencyName;
    private String agencyId;
    
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
