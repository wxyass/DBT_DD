package et.tsingtaopad.dd.ddagencycheck.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：AgencyProStc.java</br>
 * 作者：吴欣伟   </br>
 * 创建时间：2013-12-3</br>      
 * 功能描述: 经销商拜访所需产品结构体</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class AgencyProStc implements Serializable{

    private static final long serialVersionUID = 1748988097676121334L;

    private String proName;
    private String proId;
    
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProId() {
        return proId;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
