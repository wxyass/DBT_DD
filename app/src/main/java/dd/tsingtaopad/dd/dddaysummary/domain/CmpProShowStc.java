package dd.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class CmpProShowStc implements Serializable{
    
    private static final long serialVersionUID = 354445646253065993L;

    private String cmpproname;

    private List<CmpProItemStc> cmpProItemStcs;

    public String getCmpproname() {
        return cmpproname;
    }

    public void setCmpproname(String cmpproname) {
        this.cmpproname = cmpproname;
    }

    public List<CmpProItemStc> getCmpProItemStcs() {
        return cmpProItemStcs;
    }

    public void setCmpProItemStcs(List<CmpProItemStc> cmpProItemStcs) {
        this.cmpProItemStcs = cmpProItemStcs;
    }
}
