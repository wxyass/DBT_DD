package et.tsingtaopad.initconstvalues.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwenmin on 2017/12/6.
 */
public class KvStc implements Serializable {

    private static final long serialVersionUID = 3322789047184484841L;

    private String key; // 该对象的key
    
    private String value; // 该对象的value

    private String parentKey; // 父级的key
    
    private String isDefault; // 该对象是否 是默认对象
    
    private List<KvStc> childLst = new ArrayList<KvStc>(); // 子类对象集合
    
    public KvStc() {
        
    }

    public KvStc(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KvStc(String key, String value, String parentKey) {
        this.key = key;
        this.value = value;
        this.parentKey = parentKey;
    }
    
    public KvStc(String key, String value, String parentKey, String isDefault) {
        this.key = key;
        this.value = value;
        this.parentKey = parentKey;
        this.isDefault = isDefault;
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

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public List<KvStc> getChildLst() {
        return childLst;
    }

    public void setChildLst(List<KvStc> childLst) {
        this.childLst = childLst;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
