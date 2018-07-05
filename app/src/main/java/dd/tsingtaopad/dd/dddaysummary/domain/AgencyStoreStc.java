package dd.tsingtaopad.dd.dddaysummary.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class AgencyStoreStc implements Serializable{
    
    private static final long serialVersionUID = 354488046253465993L;

    private String agencyname;
    private String proname;
    private String stockfact;
    private String stocktotal;
    private String differentvalue;

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getStockfact() {
        return stockfact;
    }

    public void setStockfact(String stockfact) {
        this.stockfact = stockfact;
    }

    public String getStocktotal() {
        return stocktotal;
    }

    public void setStocktotal(String stocktotal) {
        this.stocktotal = stocktotal;
    }

    public String getDifferentvalue() {
        return differentvalue;
    }

    public void setDifferentvalue(String differentvalue) {
        this.differentvalue = differentvalue;
    }
}
