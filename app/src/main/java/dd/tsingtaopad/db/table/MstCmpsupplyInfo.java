package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * MstCmpsupplyInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_CMPSUPPLY_INFO")
public class MstCmpsupplyInfo implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String cmpsupplykey;
    @DatabaseField
    private String cmpproductkey;
    @DatabaseField
    private String cmpcomkey;
    @DatabaseField
    private String terminalkey;
    @DatabaseField
    private String inprice;
    @DatabaseField
    private String reprice;
    @DatabaseField
    private String status;
    @DatabaseField
    private String cmpinvaliddate;
    @DatabaseField
    private String sisconsistent;
    @DatabaseField
    private Date scondate;
    @DatabaseField(defaultValue = "0")
    private String padisconsistent;
    @DatabaseField
    private Date padcondate;
    @DatabaseField
    private String comid;
    @DatabaseField
    private String remarks;
    @DatabaseField
    private String orderbyno;
    @DatabaseField(defaultValue = "0")
    private String deleteflag;
    @DatabaseField
    private Integer version;
    @DatabaseField
    private Date credate;
    @DatabaseField
    private String creuser;
    @DatabaseField
    private Date updatetime;
    @DatabaseField
    private String updateuser;

    // Constructors

    /** default constructor */
    public MstCmpsupplyInfo() {
    }

    /** minimal constructor */
    public MstCmpsupplyInfo(String cmpsupplykey) {
        this.cmpsupplykey = cmpsupplykey;
    }

    /** full constructor */
    public MstCmpsupplyInfo(String cmpsupplykey, String cmpproductkey,
            String cmpcomkey, String terminalkey, String inprice,
            String reprice, String status, String cmpinvaliddate,
            String sisconsistent, Date scondate, String padisconsistent,
            Date padcondate, String comid, String remarks, String orderbyno,
            String deleteflag, Integer version, Date credate, String creuser,
            Date updatetime, String updateuser) {
        this.cmpsupplykey = cmpsupplykey;
        this.cmpproductkey = cmpproductkey;
        this.cmpcomkey = cmpcomkey;
        this.terminalkey = terminalkey;
        this.inprice = inprice;
        this.reprice = reprice;
        this.status = status;
        this.cmpinvaliddate = cmpinvaliddate;
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

    public String getCmpsupplykey() {
        return this.cmpsupplykey;
    }

    public void setCmpsupplykey(String cmpsupplykey) {
        this.cmpsupplykey = cmpsupplykey;
    }

    public String getCmpproductkey() {
        return this.cmpproductkey;
    }

    public void setCmpproductkey(String cmpproductkey) {
        this.cmpproductkey = cmpproductkey;
    }

    public String getCmpcomkey() {
        return this.cmpcomkey;
    }

    public void setCmpcomkey(String cmpcomkey) {
        this.cmpcomkey = cmpcomkey;
    }

    public String getTerminalkey() {
        return this.terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getInprice() {
        return this.inprice;
    }

    public void setInprice(String inprice) {
        this.inprice = inprice;
    }

    public String getReprice() {
        return this.reprice;
    }

    public void setReprice(String reprice) {
        this.reprice = reprice;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCmpinvaliddate() {
        return this.cmpinvaliddate;
    }

    public void setCmpinvaliddate(String cmpinvaliddate) {
        this.cmpinvaliddate = cmpinvaliddate;
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

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
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