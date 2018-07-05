package dd.tsingtaopad.dd.ddxt.invoicing.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/6.
 * 功能描述: 进销存 -- 数据显示的数据结构</br>
 */
public class XtInvoicingStc implements Serializable
{

    private static final long serialVersionUID = -5554665617737144200L;

    // 拜访产品表主键
    private String recordId;

    // 供货关系主键
    private String asupplykey;

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

    // 渠道价
    private String channelPrice;

    // 零售价
    private String sellPrice;

    // 订单量(原名称是上周期进货总量)
    private String prevNum;

    // 上周期进货总量总和
    private String prevNumSum;

    // 上次库存
    private String prevStore;

    // 当前库存
    private String currStore;

    // 日销量
    private String daySellNum;
    
    // 最早生产日期
    private String fristdate;
    
    // 累计卡
    private String addcard;

    public String getPrevNumSum()
    {
        return prevNumSum;
    }

    public void setPrevNumSum(String prevNumSum)
    {
        this.prevNumSum = prevNumSum;
    }

    public String getRecordId()
    {
        return recordId;
    }

    public void setRecordId(String recordId)
    {
        this.recordId = recordId;
    }

    public String getVisitDate()
    {
        return visitDate;
    }

    public void setVisitDate(String visitDate)
    {
        this.visitDate = visitDate;
    }

    public String getProId()
    {
        return proId;
    }

    public void setProId(String proId)
    {
        this.proId = proId;
    }

    public String getProName()
    {
        return proName;
    }

    public void setProName(String proName)
    {
        this.proName = proName;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String getChannelPrice()
    {
        return channelPrice;
    }

    public void setChannelPrice(String channelPrice)
    {
        this.channelPrice = channelPrice;
    }

    public String getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public String getPrevNum()
    {
        return prevNum;
    }

    public void setPrevNum(String prevNum)
    {
        this.prevNum = prevNum;
    }

    public String getPrevStore()
    {
        return prevStore;
    }

    public void setPrevStore(String prevStore)
    {
        this.prevStore = prevStore;
    }

    public String getCurrStore()
    {
        return currStore;
    }

    public void setCurrStore(String currStore)
    {
        this.currStore = currStore;
    }

    public String getDaySellNum()
    {
        return daySellNum;
    }

    public void setDaySellNum(String daySellNum)
    {
        this.daySellNum = daySellNum;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

	/**
	 * @return the fristdate
	 */
	public String getFristdate() {
		return fristdate;
	}

	/**
	 * @param fristdate the fristdate to set
	 */
	public void setFristdate(String fristdate) {
		this.fristdate = fristdate;
	}

	/**
	 * @return the addcard
	 */
	public String getAddcard() {
		return addcard;
	}

	/**
	 * @param addcard the addcard to set
	 */
	public void setAddcard(String addcard) {
		this.addcard = addcard;
	}

    public String getAsupplykey() {
        return asupplykey;
    }

    public void setAsupplykey(String asupplykey) {
        this.asupplykey = asupplykey;
    }
}
