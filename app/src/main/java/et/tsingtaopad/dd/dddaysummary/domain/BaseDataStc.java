package et.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class BaseDataStc implements Serializable{
    
    private static final long serialVersionUID = 354488046255465993L;

    //基础数据群
    private String basicstotalterm;
    private String basicstrueterm;
    private String basicstermratio;

    //协议数据
    private String totalaccnt;
    private String trueaccnt;
    private String accntratio;

    //活动
    private String trueactivity;
    private String totalactivity;
    private String activityratio;

    //价格
    private String truesupply;
    private String totalsupply;
    private String supplyratio;

    //网络
    private String totalnet;
    private String truenet;
    private String netratio;

    //竞品
    private String truecmpsupply;
    private String totalcmpsupply;
    private String cmpsupplyratio;

    private String truedatagroup;
    private String totaldatagroup;
    private String datagroupratio;

    public String getBasicstotalterm() {
        return basicstotalterm;
    }

    public void setBasicstotalterm(String basicstotalterm) {
        this.basicstotalterm = basicstotalterm;
    }

    public String getBasicstrueterm() {
        return basicstrueterm;
    }

    public void setBasicstrueterm(String basicstrueterm) {
        this.basicstrueterm = basicstrueterm;
    }

    public String getBasicstermratio() {
        return basicstermratio;
    }

    public void setBasicstermratio(String basicstermratio) {
        this.basicstermratio = basicstermratio;
    }

    public String getTotalaccnt() {
        return totalaccnt;
    }

    public void setTotalaccnt(String totalaccnt) {
        this.totalaccnt = totalaccnt;
    }

    public String getTrueaccnt() {
        return trueaccnt;
    }

    public void setTrueaccnt(String trueaccnt) {
        this.trueaccnt = trueaccnt;
    }

    public String getAccntratio() {
        return accntratio;
    }

    public void setAccntratio(String accntratio) {
        this.accntratio = accntratio;
    }

    public String getTrueactivity() {
        return trueactivity;
    }

    public void setTrueactivity(String trueactivity) {
        this.trueactivity = trueactivity;
    }

    public String getTotalactivity() {
        return totalactivity;
    }

    public void setTotalactivity(String totalactivity) {
        this.totalactivity = totalactivity;
    }

    public String getActivityratio() {
        return activityratio;
    }

    public void setActivityratio(String activityratio) {
        this.activityratio = activityratio;
    }

    public String getTruesupply() {
        return truesupply;
    }

    public void setTruesupply(String truesupply) {
        this.truesupply = truesupply;
    }

    public String getTotalsupply() {
        return totalsupply;
    }

    public void setTotalsupply(String totalsupply) {
        this.totalsupply = totalsupply;
    }

    public String getSupplyratio() {
        return supplyratio;
    }

    public void setSupplyratio(String supplyratio) {
        this.supplyratio = supplyratio;
    }

    public String getTotalnet() {
        return totalnet;
    }

    public void setTotalnet(String totalnet) {
        this.totalnet = totalnet;
    }

    public String getTruenet() {
        return truenet;
    }

    public void setTruenet(String truenet) {
        this.truenet = truenet;
    }

    public String getNetratio() {
        return netratio;
    }

    public void setNetratio(String netratio) {
        this.netratio = netratio;
    }

    public String getTruecmpsupply() {
        return truecmpsupply;
    }

    public void setTruecmpsupply(String truecmpsupply) {
        this.truecmpsupply = truecmpsupply;
    }

    public String getTotalcmpsupply() {
        return totalcmpsupply;
    }

    public void setTotalcmpsupply(String totalcmpsupply) {
        this.totalcmpsupply = totalcmpsupply;
    }

    public String getCmpsupplyratio() {
        return cmpsupplyratio;
    }

    public void setCmpsupplyratio(String cmpsupplyratio) {
        this.cmpsupplyratio = cmpsupplyratio;
    }

    public String getTruedatagroup() {
        return truedatagroup;
    }

    public void setTruedatagroup(String truedatagroup) {
        this.truedatagroup = truedatagroup;
    }

    public String getTotaldatagroup() {
        return totaldatagroup;
    }

    public void setTotaldatagroup(String totaldatagroup) {
        this.totaldatagroup = totaldatagroup;
    }

    public String getDatagroupratio() {
        return datagroupratio;
    }

    public void setDatagroupratio(String datagroupratio) {
        this.datagroupratio = datagroupratio;
    }
}
