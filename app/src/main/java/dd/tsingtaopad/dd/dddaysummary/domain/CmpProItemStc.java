package dd.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class CmpProItemStc implements Serializable{
    
    private static final long serialVersionUID = 354445646251465993L;

    private String dicname;
    private String termratio;
    private String totalterm;
    private String trueterm;


    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname;
    }

    public String getTermratio() {
        return termratio;
    }

    public void setTermratio(String termratio) {
        this.termratio = termratio;
    }

    public String getTotalterm() {
        return totalterm;
    }

    public void setTotalterm(String totalterm) {
        this.totalterm = totalterm;
    }

    public String getTrueterm() {
        return trueterm;
    }

    public void setTrueterm(String trueterm) {
        this.trueterm = trueterm;
    }
}
