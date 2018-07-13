package et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain;

import java.io.Serializable;

public class ProIndexValue implements Serializable {

    private static final long serialVersionUID = 4156799315664210568L;
    
    // 指标结果表主键
    private String indexResultId;
    
    // 产品Id
    private String proId;
    
    // 产品名称
    private String proName;
    
    // 所属指标主键
    private String indexId;
    
    // 指标类型
    private String indexType;
    
    // 上次动态指标值ID
    private String indexValueOldId;
    
    // 动态指标值ID
    private String indexValueId;
    
    // 动态指标值名称
    private String indexValueName;
    
    public String getIndexResultId() {
        return indexResultId;
    }

    public void setIndexResultId(String indexResultId) {
        this.indexResultId = indexResultId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getIndexValueOldId() {
        return indexValueOldId;
    }

    public void setIndexValueOldId(String indexValueOldId) {
        this.indexValueOldId = indexValueOldId;
    }

    public String getIndexValueId() {
        return indexValueId;
    }

    public void setIndexValueId(String indexValueId) {
        this.indexValueId = indexValueId;
    }

    public String getIndexValueName() {
        return indexValueName;
    }

    public void setIndexValueName(String indexValueName) {
        this.indexValueName = indexValueName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
