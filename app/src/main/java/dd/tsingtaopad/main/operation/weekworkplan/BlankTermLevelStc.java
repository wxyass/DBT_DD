package dd.tsingtaopad.main.operation.weekworkplan;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：BlankTermLevelStc.java</br>
 * 作者：wuxinwei   </br>
 * 创建时间：2014-2-18</br>      
 * 功能描述: </br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class BlankTermLevelStc implements Serializable {

    private static final long serialVersionUID = 3463736961125445836L;

    private String key;
    private String value;
    private String rate;
    
    public BlankTermLevelStc() {
        
    }
    
    public BlankTermLevelStc(String key, String value, String rate) {
        this.key = key;
        this.value = value;
        this.rate = rate;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    
}
