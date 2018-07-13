package et.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class DdProCheckShowStc implements Serializable{
    
    private static final long serialVersionUID = 354488056253065993L;

    private String proname;
    private List<DdProCheckItemStc> ddProCheckItemStcs;

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public List<DdProCheckItemStc> getDdProCheckItemStcs() {
        return ddProCheckItemStcs;
    }

    public void setDdProCheckItemStcs(List<DdProCheckItemStc> ddProCheckItemStcs) {
        this.ddProCheckItemStcs = ddProCheckItemStcs;
    }
}
