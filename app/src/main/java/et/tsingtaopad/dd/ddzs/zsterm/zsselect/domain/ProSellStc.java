package et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2017/12/6.
 */
public class ProSellStc implements Serializable {

    private static final long serialVersionUID = 3322789047123484841L;

    private String key; // 该对象的key

    private String value; // 该对象的value

    private String parentKey; // 父级的key

    private String isDefault; // 该对象是否 是默认对象

    // private List<ProSellStc> childLst = new ArrayList<ProSellStc>(); // 子类对象集合


    private String qudaonum; // 渠道最高值
    private String qudaolow; // 渠道最低值
    private String lingshounum; // 零售最高值
    private String lingshoulow; // 零售最低值

    public ProSellStc() {

    }

    public ProSellStc(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ProSellStc(String key, String value, String parentKey) {
        this.key = key;
        this.value = value;
        this.parentKey = parentKey;
    }

    public ProSellStc(String key, String value, String parentKey, String isDefault) {
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getQudaonum() {
        return qudaonum;
    }

    public void setQudaonum(String qudaonum) {
        this.qudaonum = qudaonum;
    }

    public String getQudaolow() {
        return qudaolow;
    }

    public void setQudaolow(String qudaolow) {
        this.qudaolow = qudaolow;
    }

    public String getLingshounum() {
        return lingshounum;
    }

    public void setLingshounum(String lingshounum) {
        this.lingshounum = lingshounum;
    }

    public String getLingshoulow() {
        return lingshoulow;
    }

    public void setLingshoulow(String lingshoulow) {
        this.lingshoulow = lingshoulow;
    }
}
