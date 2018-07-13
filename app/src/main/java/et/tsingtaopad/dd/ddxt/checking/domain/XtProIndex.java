package et.tsingtaopad.dd.ddxt.checking.domain;

import java.io.Serializable;
import java.util.List;

public class XtProIndex implements Serializable {

    private static final long serialVersionUID = 4156799315664210568L;
    
    // 动态指标ID
    private String indexId;
    
    // 动态指标名称
    private String indexName;
    
    // 指标类型
    private String indexType;
    
    // 具体产品的指标值集合
    private List<XtProIndexValue> indexValueLst;

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public List<XtProIndexValue> getIndexValueLst() {
        return indexValueLst;
    }

    public void setIndexValueLst(List<XtProIndexValue> indexValueLst) {
        this.indexValueLst = indexValueLst;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
