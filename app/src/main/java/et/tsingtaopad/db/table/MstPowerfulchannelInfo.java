package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * MstPowerfulchannelInfo entity. @author MyEclipse Persistence Tools
 */

@DatabaseTable(tableName = "MST_POWERFULCHANNEL_INFO")
public class MstPowerfulchannelInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String powerfulchannkey;
	@DatabaseField
	private String gridkey;
	@DatabaseField
	private String routekey;
	@DatabaseField
	private String productkey;
	@DatabaseField
    private String productname;
	@DatabaseField
	private String sellchannel;
	@DatabaseField
	private String mainchannel;
	@DatabaseField
	private String minorchannel;
	@DatabaseField
	private String sellchannelcode;
	@DatabaseField
	private String mainchannelcode;
	@DatabaseField
	private String minorchannelcode;
	@DatabaseField
	private String searchdate;
	@DatabaseField
	private Integer selectterms;
	@DatabaseField
	private Integer blank;
	@DatabaseField
	private Integer distribution;
	@DatabaseField
	private Integer effectdis;
	@DatabaseField
	private Integer effectsale;
	@DatabaseField
	private Integer prevdistribution;
	@DatabaseField
	private Integer preveffectdis;
	@DatabaseField
	private Integer preveffectsale;
	@DatabaseField
	private Integer disterms;
	@DatabaseField
	private Integer distrate;
	@DatabaseField
	private Integer predisrate;
	@DatabaseField
	private Integer change;
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
	public MstPowerfulchannelInfo() {
	}

	/** minimal constructor */
	public MstPowerfulchannelInfo(String powerfulchannkey) {
		this.powerfulchannkey = powerfulchannkey;
	}

	/** full constructor */
	public MstPowerfulchannelInfo(String powerfulchannkey, String gridkey, String routekey, String productkey, String sellchannel, String mainchannel, String minorchannel, String searchdate, Integer selectterms, Integer blank, Integer distribution, Integer effectdis, Integer effectsale,Integer prevdistribution, Integer preveffectdis, Integer preveffectsale, Integer disterms, Integer distrate, Integer predisrate, Integer change, String sisconsistent, Date scondate, String padisconsistent, Date padcondate, String comid, String remarks, String orderbyno, String deleteflag, Integer version, Date credate, String creuser, Date updatetime, String updateuser) {
		this.powerfulchannkey = powerfulchannkey;
		this.gridkey = gridkey;
		this.routekey = routekey;
		this.productkey = productkey;
		this.sellchannel = sellchannel;
		this.mainchannel = mainchannel;
		this.minorchannel = minorchannel;
		this.searchdate = searchdate;
		this.selectterms = selectterms;
		this.blank = blank;
		this.distribution = distribution;
		this.effectdis = effectdis;
		this.effectsale = effectsale;
		this.prevdistribution = prevdistribution;
		this.preveffectdis = preveffectdis;
		this.preveffectsale = preveffectsale;
		this.disterms = disterms;
		this.distrate = distrate;
		this.predisrate = predisrate;
		this.change = change;
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

	public String getPowerfulchannkey() {
		return this.powerfulchannkey;
	}

	public void setPowerfulchannkey(String powerfulchannkey) {
		this.powerfulchannkey = powerfulchannkey;
	}

	public String getGridkey() {
		return this.gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getRoutekey() {
		return this.routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public String getSellchannel() {
		return this.sellchannel;
	}

	public void setSellchannel(String sellchannel) {
		this.sellchannel = sellchannel;
	}

	public String getMainchannel() {
		return this.mainchannel;
	}

	public void setMainchannel(String mainchannel) {
		this.mainchannel = mainchannel;
	}

	public String getMinorchannel() {
		return this.minorchannel;
	}

	public void setMinorchannel(String minorchannel) {
		this.minorchannel = minorchannel;
	}
	
    public String getSellchannelcode() {
        return sellchannelcode;
    }

    public void setSellchannelcode(String sellchannelcode) {
        this.sellchannelcode = sellchannelcode;
    }

    public String getMainchannelcode() {
        return mainchannelcode;
    }

    public void setMainchannelcode(String mainchannelcode) {
        this.mainchannelcode = mainchannelcode;
    }

    public String getMinorchannelcode() {
        return minorchannelcode;
    }

    public void setMinorchannelcode(String minorchannelcode) {
        this.minorchannelcode = minorchannelcode;
    }

    public String getSearchdate() {
		return this.searchdate;
	}

	public void setSearchdate(String searchdate) {
		this.searchdate = searchdate;
	}

	public Integer getSelectterms() {
		return this.selectterms;
	}

	public void setSelectterms(Integer selectterms) {
		this.selectterms = selectterms;
	}

	public Integer getBlank() {
		return this.blank;
	}

	public void setBlank(Integer blank) {
		this.blank = blank;
	}

	public Integer getDistribution() {
		return this.distribution;
	}

	public void setDistribution(Integer distribution) {
		this.distribution = distribution;
	}

	public Integer getEffectdis() {
		return this.effectdis;
	}

	public void setEffectdis(Integer effectdis) {
		this.effectdis = effectdis;
	}

	public Integer getEffectsale() {
		return this.effectsale;
	}

	public void setEffectsale(Integer effectsale) {
		this.effectsale = effectsale;
	}
	
	public Integer getPrevdistribution() {
		return prevdistribution;
	}

	public void setPrevdistribution(Integer prevdistribution) {
		this.prevdistribution = prevdistribution;
	}

	public Integer getPreveffectdis() {
		return preveffectdis;
	}

	public void setPreveffectdis(Integer preveffectdis) {
		this.preveffectdis = preveffectdis;
	}

	public Integer getPreveffectsale() {
		return preveffectsale;
	}

	public void setPreveffectsale(Integer preveffectsale) {
		this.preveffectsale = preveffectsale;
	}

	public Integer getDisterms() {
		return this.disterms;
	}

	public void setDisterms(Integer disterms) {
		this.disterms = disterms;
	}

	public Integer getDistrate() {
		return this.distrate;
	}

	public void setDistrate(Integer distrate) {
		this.distrate = distrate;
	}

	public Integer getPredisrate() {
		return this.predisrate;
	}

	public void setPredisrate(Integer predisrate) {
		this.predisrate = predisrate;
	}

	public Integer getChange() {
		return this.change;
	}

	public void setChange(Integer change) {
		this.change = change;
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