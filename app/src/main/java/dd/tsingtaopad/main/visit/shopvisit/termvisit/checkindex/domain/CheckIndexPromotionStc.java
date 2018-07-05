package dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/6.
 * 功能描述: 查指标 -- 促销活动部分 -- 显示数据结构</br>
 */
public class CheckIndexPromotionStc implements Serializable {

    private static final long serialVersionUID = -4531050847262997094L;
    
    // 终端参与活动记录主键 
    private String recordKey;// 督导活动终端表主键
    
    // 活动达成状态
    private String isAccomplish;
    
    // 区域id
    private String areaid;
    
    // 是否拍照类型
    private String ispictype;
    
    // 活动主键
    private String promotKey;
    
    // 活动类型
    private String promotType;
    
    // 活动名称
    private String promotName;
    
    // 活动开始时间
    private String startDate;
    
    // 活动结束时间
    private String endDate;
    
    // 活动关联产品主键
    private String proId;
    
    // 活动关联产品名称
    private String proName;
    
    // 拜访ID
    private String visitId;

    // 督导活动终端表主键
    //private String id;

    // 业代达成组数
    private String reachNum;

    // 是否达成正确与否
    private String valistruefalg;
    // 达成组数正确与否
    private String valistruenumflag;//

    // 督导达成组数
    private String valistruenumval;

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public String getIsAccomplish() {
        return isAccomplish;
    }

    public void setIsAccomplish(String isAccomplish) {
        this.isAccomplish = isAccomplish;
    }

    public String getPromotKey() {
        return promotKey;
    }

    public void setPromotKey(String promotKey) {
        this.promotKey = promotKey;
    }

    public String getPromotType() {
        return promotType;
    }

    public void setPromotType(String promotType) {
        this.promotType = promotType;
    }

    public String getPromotName() {
        return promotName;
    }

    public void setPromotName(String promotName) {
        this.promotName = promotName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

	/**
	 * @return the reachNum
	 */
	public String getReachNum() {
		return reachNum;
	}

	/**
	 * @param reachNum the reachNum to set
	 */
	public void setReachNum(String reachNum) {
		this.reachNum = reachNum;
	}

	/**
	 * @return the ispictype
	 */
	public String getIspictype() {
		return ispictype;
	}

	/**
	 * @param ispictype the ispictype to set
	 */
	public void setIspictype(String ispictype) {
		this.ispictype = ispictype;
	}

	/**
	 * @return the areaid
	 */
	public String getAreaid() {
		return areaid;
	}

	/**
	 * @param areaid the areaid to set
	 */
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

    public String getValistruefalg() {
        return valistruefalg;
    }

    public void setValistruefalg(String valistruefalg) {
        this.valistruefalg = valistruefalg;
    }

    public String getValistruenumflag() {
        return valistruenumflag;
    }

    public void setValistruenumflag(String valistruenumflag) {
        this.valistruenumflag = valistruenumflag;
    }

    public String getValistruenumval() {
        return valistruenumval;
    }

    public void setValistruenumval(String valistruenumval) {
        this.valistruenumval = valistruenumval;
    }

}
