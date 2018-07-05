package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MstAgreeDetailTmpDaoImpl;


/**
 * 协议产品表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_AGREE_DETAIL_TMP", daoClass = MstAgreeDetailTmpDaoImpl.class)
public class MstAgreeDetailTmp implements java.io.Serializable {

    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String amount;//
    @DatabaseField
    private String cashdate;//
    @DatabaseField
    private String cashtype;//
    @DatabaseField
    private String price;//
    @DatabaseField
    private String proname;//
    @DatabaseField
    private String qty;//

    @DatabaseField
    private String agreeid;//
    @DatabaseField
    private String terminalkey;//
    @DatabaseField
    private String productkey	;//

    // Constructors

    /**
     * default constructor
     */
    public MstAgreeDetailTmp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCashdate() {
        return cashdate;
    }

    public void setCashdate(String cashdate) {
        this.cashdate = cashdate;
    }

    public String getCashtype() {
        return cashtype;
    }

    public void setCashtype(String cashtype) {
        this.cashtype = cashtype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAgreeid() {
        return agreeid;
    }

    public void setAgreeid(String agreeid) {
        this.agreeid = agreeid;
    }

    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }
}