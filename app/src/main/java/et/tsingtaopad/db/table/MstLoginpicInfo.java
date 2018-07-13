package et.tsingtaopad.db.table;


import java.math.BigDecimal;
import java.util.Date;

/**
 * MstLoginpicInfo entity. @author MyEclipse Persistence Tools
 */

public class MstLoginpicInfo implements java.io.Serializable {

    // Fields

    private String pickey;
    private String picurl;
    private String picdetail;
    private String picstatus;
    private String sisconsistent;
    private Date scondate;
    private String padisconsistent;
    private Date padcondate;
    private String comid;
    private String remarks;
    private String orderbyno;
    private String deleteflag;
    private BigDecimal version;
    private Date credate;
    private String creuser;
    private Date updatetime;
    private String updateuser;

    // Constructors

    /** default constructor */
    public MstLoginpicInfo() {
    }

    /** minimal constructor */
    public MstLoginpicInfo(String pickey) {
        this.pickey = pickey;
    }

    /** full constructor */
    public MstLoginpicInfo(String pickey, String picurl, String picdetail,
            String picstatus, String sisconsistent, Date scondate,
            String padisconsistent, Date padcondate, String comid,
            String remarks, String orderbyno, String deleteflag,
            BigDecimal version, Date credate, String creuser, Date updatetime,
            String updateuser) {
        this.pickey = pickey;
        this.picurl = picurl;
        this.picdetail = picdetail;
        this.picstatus = picstatus;
        this.sisconsistent = sisconsistent;
        this.scondate = scondate;
        this.padisconsistent = padisconsistent;
        this.padcondate = padcondate;
        this.comid = comid;
        this.remarks = remarks;
        this.orderbyno = orderbyno;
        this.deleteflag = deleteflag;
        this.version = version;
        this.credate = credate;
        this.creuser = creuser;
        this.updatetime = updatetime;
        this.updateuser = updateuser;
    }

    // Property accessors

    public String getPickey() {
        return this.pickey;
    }

    public void setPickey(String pickey) {
        this.pickey = pickey;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPicdetail() {
        return this.picdetail;
    }

    public void setPicdetail(String picdetail) {
        this.picdetail = picdetail;
    }

    public String getPicstatus() {
        return this.picstatus;
    }

    public void setPicstatus(String picstatus) {
        this.picstatus = picstatus;
    }

    public String getSisconsistent() {
        return this.sisconsistent;
    }

    public void setSisconsistent(String sisconsistent) {
        this.sisconsistent = sisconsistent;
    }

    public Date getScondate() {
        return this.scondate;
    }

    public void setScondate(Date scondate) {
        this.scondate = scondate;
    }

    public String getPadisconsistent() {
        return this.padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public Date getPadcondate() {
        return this.padcondate;
    }

    public void setPadcondate(Date padcondate) {
        this.padcondate = padcondate;
    }

    public String getComid() {
        return this.comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrderbyno() {
        return this.orderbyno;
    }

    public void setOrderbyno(String orderbyno) {
        this.orderbyno = orderbyno;
    }

    public String getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    public BigDecimal getVersion() {
        return this.version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Date getCredate() {
        return this.credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getCreuser() {
        return this.creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return this.updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

}