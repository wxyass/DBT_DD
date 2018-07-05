package dd.tsingtaopad.dd.ddxt.chatvie.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：ChatVieStc.java</br>
 * 功能描述: 用于巡店拜访下聊竞品数据显示的数据结构</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtChatVieStc implements Serializable {

    private static final long serialVersionUID = -5554665617737144200L;

    // 拜访产品表主键
    private String recordId;
    
    // 对应的拜访日期
    private String visitDate;
    
    // 产品主键
    private String proId;
    
    // 产品名称
    private String proName;
    
    // 经销商ID
    private String agencyId;
    
    // 经销商名称
    private String agencyName;
    
    // 公司ID
    private String commpayId;
    
    // 渠道价
    private String channelPrice;
    
    // 零售价
    private String sellPrice;
    
    // 当前库存
    private String currStore;
    
    // 月销量
    private String monthSellNum;
    
    // 描述
    private String describe;

    // 供货关系id
    private String cmpsupplykey;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
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

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getCommpayId() {
        return commpayId;
    }

    public void setCommpayId(String commpayId) {
        this.commpayId = commpayId;
    }

    public String getChannelPrice() {
        return channelPrice;
    }

    public void setChannelPrice(String channelPrice) {
        this.channelPrice = channelPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getCurrStore() {
        return currStore;
    }

    public void setCurrStore(String currStore) {
        this.currStore = currStore;
    }

    public String getMonthSellNum() {
        return monthSellNum;
    }

    public void setMonthSellNum(String monthSellNum) {
        this.monthSellNum = monthSellNum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCmpsupplykey() {
        return cmpsupplykey;
    }

    public void setCmpsupplykey(String cmpsupplykey) {
        this.cmpsupplykey = cmpsupplykey;
    }
}
