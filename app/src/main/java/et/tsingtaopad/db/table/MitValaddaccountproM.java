package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MitValaddaccountproMDaoImpl;


/**
 * 终端追溯台账产品详情表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_VALADDACCOUNTPRO_M", daoClass = MitValaddaccountproMDaoImpl.class)
public class MitValaddaccountproM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//
    @DatabaseField
    private String valterid;// 追溯主表ID
    @DatabaseField
    private String valaddaccountid;// 终端进货台账主表ID
    @DatabaseField
    private String valprotime;// 台账日期
    @DatabaseField
    private String valpronumfalg;// 进货量正确与否
    @DatabaseField
    private String valpronum;// 进货量原值
    @DatabaseField
    private String valprotruenum;// 进货量正确值
    @DatabaseField
    private String valproremark;// 备注
    @DatabaseField
    private String uploadflag;//
    @DatabaseField
    private String padisconsistent;//


    /**
     * default constructor
     */
    public MitValaddaccountproM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValaddaccountid() {
        return valaddaccountid;
    }

    public void setValaddaccountid(String valaddaccountid) {
        this.valaddaccountid = valaddaccountid;
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

    public String getValterid() {
        return valterid;
    }

    public void setValterid(String valterid) {
        this.valterid = valterid;
    }
}