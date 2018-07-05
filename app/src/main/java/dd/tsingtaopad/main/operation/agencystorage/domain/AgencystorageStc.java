package dd.tsingtaopad.main.operation.agencystorage.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 经销商库存 -- 结构体</br>
 */
public class AgencystorageStc implements Serializable {

    private static final long serialVersionUID = 5892454202608273171L;

    private String agencyName;
    private String proName;
    private String proCode;
    private String storenum;
    private String ingoodsnum;
    private String salenum;
    private String date;
    private String agencyCode;
    private String agencyUser;
    private String phone;
    private String creentstorenmu;
    private String creentingoodsnum;
    private String creentsalenum;
    private String precreentstorenum;
    private String prestorenum;
    
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProCode() {
        return proCode;
    }
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
    public String getStorenum() {
        return storenum;
    }
    public void setStorenum(String storenum) {
        this.storenum = storenum;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAgencyCode() {
        return agencyCode;
    }
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
    public String getAgencyUser() {
        return agencyUser;
    }
    public void setAgencyUser(String agencyUser) {
        this.agencyUser = agencyUser;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	public String getIngoodsnum() {
		return ingoodsnum;
	}
	public void setIngoodsnum(String ingoodsnum) {
		this.ingoodsnum = ingoodsnum;
	}
	public String getSalenum() {
		return salenum;
	}
	public void setSalenum(String salenum) {
		this.salenum = salenum;
	}
	public String getCreentstorenmu() {
		return creentstorenmu;
	}
	public void setCreentstorenmu(String creentstorenmu) {
		this.creentstorenmu = creentstorenmu;
	}
	public String getCreentingoodsnum() {
		return creentingoodsnum;
	}
	public void setCreentingoodsnum(String creentingoodsnum) {
		this.creentingoodsnum = creentingoodsnum;
	}
	public String getCreentsalenum() {
		return creentsalenum;
	}
	public void setCreentsalenum(String creentsalenum) {
		this.creentsalenum = creentsalenum;
	}
	public String getPrecreentstorenum() {
		return precreentstorenum;
	}
	public void setPrecreentstorenum(String precreentstorenum) {
		this.precreentstorenum = precreentstorenum;
	}
	public String getPrestorenum() {
		return prestorenum;
	}
	public void setPrestorenum(String prestorenum) {
		this.prestorenum = prestorenum;
	}
	
}
