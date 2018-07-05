package dd.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2018/5/17.
 */

public class ZsTzLedger implements Serializable {

    private static final long serialVersionUID = -5554665617737144201L;

    private String valaddaccountid;// 主表主键
    private String valaddaccountproid;// 附表主键
    private String valsupplyid;// 终端追溯终端表ID

    private String valagencyid;//经销商ID
    private String valagencyname;// 经销商名称
    private String valterid;// 终端ID
    private String valtername;// 终端名称
    private String valproid;//产品ID
    private String valproname;//  产品名称
    private String valprostatus;//  稽查状态

    private String valprotime;// 台账日期
    private String valpronumfalg;// 进货量正确与否
    private String valpronum;// 进货量原值
    private String valprotruenum;// 进货量正确值
    private String valproremark;// 备注

    public String getValaddaccountid() {
        return valaddaccountid;
    }

    public void setValaddaccountid(String valaddaccountid) {
        this.valaddaccountid = valaddaccountid;
    }

    public String getValaddaccountproid() {
        return valaddaccountproid;
    }

    public void setValaddaccountproid(String valaddaccountproid) {
        this.valaddaccountproid = valaddaccountproid;
    }

    public String getValsupplyid() {
        return valsupplyid;
    }

    public void setValsupplyid(String valsupplyid) {
        this.valsupplyid = valsupplyid;
    }

    public String getValagencyid() {
        return valagencyid;
    }

    public void setValagencyid(String valagencyid) {
        this.valagencyid = valagencyid;
    }

    public String getValagencyname() {
        return valagencyname;
    }

    public void setValagencyname(String valagencyname) {
        this.valagencyname = valagencyname;
    }

    public String getValterid() {
        return valterid;
    }

    public void setValterid(String valterid) {
        this.valterid = valterid;
    }

    public String getValtername() {
        return valtername;
    }

    public void setValtername(String valtername) {
        this.valtername = valtername;
    }

    public String getValproid() {
        return valproid;
    }

    public void setValproid(String valproid) {
        this.valproid = valproid;
    }

    public String getValproname() {
        return valproname;
    }

    public void setValproname(String valproname) {
        this.valproname = valproname;
    }

    public String getValprostatus() {
        return valprostatus;
    }

    public void setValprostatus(String valprostatus) {
        this.valprostatus = valprostatus;
    }

    public String getValprotime() {
        return valprotime;
    }

    public void setValprotime(String valprotime) {
        this.valprotime = valprotime;
    }

    public String getValpronumfalg() {
        return valpronumfalg;
    }

    public void setValpronumfalg(String valpronumfalg) {
        this.valpronumfalg = valpronumfalg;
    }

    public String getValpronum() {
        return valpronum;
    }

    public void setValpronum(String valpronum) {
        this.valpronum = valpronum;
    }

    public String getValprotruenum() {
        return valprotruenum;
    }

    public void setValprotruenum(String valprotruenum) {
        this.valprotruenum = valprotruenum;
    }

    public String getValproremark() {
        return valproremark;
    }

    public void setValproremark(String valproremark) {
        this.valproremark = valproremark;
    }
}
