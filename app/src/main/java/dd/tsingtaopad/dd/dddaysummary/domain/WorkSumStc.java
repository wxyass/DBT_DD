package dd.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class WorkSumStc implements Serializable{
    
    private static final long serialVersionUID = 354488046223065993L;

    private String dicname;
    private String ifaccntcount;
    private String totalterm;

    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname;
    }

    public String getIfaccntcount() {
        return ifaccntcount;
    }

    public void setIfaccntcount(String ifaccntcount) {
        this.ifaccntcount = ifaccntcount;
    }

    public String getTotalterm() {
        return totalterm;
    }

    public void setTotalterm(String totalterm) {
        this.totalterm = totalterm;
    }
}
