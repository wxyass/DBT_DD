package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.MitAgencyproMDaoImpl;


/**
 * 经销商判断产品表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_AGENCYPRO_M", daoClass = MitAgencyproMDaoImpl.class)
public class MitAgencyproM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String agencynumid;// 经销商库存盘点主表ID
    @DatabaseField
    private String proid;//产品ID
    @DatabaseField
    private String stocktotal;//期末库存
    @DatabaseField
    private String stockfact ;//实际库存
    @DatabaseField
    private String remark  ;//备注
    @DatabaseField
    private String uploadflag;//
    @DatabaseField
    private String padisconsistent ;//



    /**
     * default constructor
     */
    public MitAgencyproM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencynumid() {
        return agencynumid;
    }

    public void setAgencynumid(String agencynumid) {
        this.agencynumid = agencynumid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getStocktotal() {
        return stocktotal;
    }

    public void setStocktotal(String stocktotal) {
        this.stocktotal = stocktotal;
    }

    public String getStockfact() {
        return stockfact;
    }

    public void setStockfact(String stockfact) {
        this.stockfact = stockfact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadflag() {
        return uploadflag;
    }

    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }
}