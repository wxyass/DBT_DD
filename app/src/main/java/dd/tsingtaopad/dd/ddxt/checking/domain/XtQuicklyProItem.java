package dd.tsingtaopad.dd.ddxt.checking.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：QuicklyProItem.java</br>
 * 作者：hongen   </br>
 * 创建时间：2013-12-17</br>      
 * 功能描述: 快速采集一级数据结构</br>      
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtQuicklyProItem implements Serializable{

    private static final long serialVersionUID = 7684826624412377693L;
    
    // 采集项ID
    private String itemId;
    
    // 采集项名称
    private String itemName;
    
    // 指标下产品
    private List<XtProItem> proItemLst;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<XtProItem> getProItemLst() {
        return proItemLst;
    }

    public void setProItemLst(List<XtProItem> proItemLst) {
        this.proItemLst = proItemLst;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
