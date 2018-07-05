package dd.tsingtaopad.main.visit.shopvisit.line.domain;


import dd.tsingtaopad.db.table.MstRouteM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 巡店拜访_线路列表结构体</br>
 */
public class MstRouteMStc extends MstRouteM {

    private static final long serialVersionUID = -3860644634709772039L;
    
    // 上次拜访日期
    private String prevDate;
    
    // 计划拜访日期
    private String planDate;

    public String getPlanDate() {
        return planDate;
    }

    public String getPrevDate() {
        return prevDate;
    }

    public void setPrevDate(String prevDate) {
        this.prevDate = prevDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
