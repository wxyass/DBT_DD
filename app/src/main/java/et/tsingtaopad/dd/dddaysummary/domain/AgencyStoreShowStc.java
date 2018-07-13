package et.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class AgencyStoreShowStc implements Serializable{
    
    private static final long serialVersionUID = 354488446253465993L;

    private String agencyname;

    private List<AgencyStoreItemStc> agencyStoreItemStcs;

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public List<AgencyStoreItemStc> getAgencyStoreItemStcs() {
        return agencyStoreItemStcs;
    }

    public void setAgencyStoreItemStcs(List<AgencyStoreItemStc> agencyStoreItemStcs) {
        this.agencyStoreItemStcs = agencyStoreItemStcs;
    }
}
