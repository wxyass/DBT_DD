package dd.tsingtaopad.dd.ddxt.term.select.domain;


import dd.tsingtaopad.db.table.MstTerminalinfoM;

/**
 *  终端列表结构体
 */
public class XtTermSelectMStc extends MstTerminalinfoM
{

    private static final long serialVersionUID = -7593099472791252366L;
    // 线路名称
    private String routename;
    // 终端类型
    private String terminalType;

    // 数据上传标识
    private String syncFlag;

    // 是否我品标识
    private String mineFlag;

    // 我品合作状态标识
    private String mineProtocolFlag;

    // 是否竞品标识
    private String vieFlag;

    // 竞品合作状态
    private String vieProtocolFlag;

    // 进店时间
    private String visitDate;

    // 离店时间
    private String endDate;

    // 拜访时间
    private String visitTime;

    // 结束拜访上传按钮状态
    private String uploadFlag;
    // 是否加入购物车
    private String isSelectToCart;

    //是否选中加入了购物车   0:未加入  1:已加入
    private String terminalStatus;

    public String getRoutename()
    {
        return routename;
    }

    public void setRoutename(String routename)
    {
        this.routename = routename;
    }

    public String getTerminalType()
    {
        return terminalType;
    }

    public void setTerminalType(String terminalType)
    {
        this.terminalType = terminalType;
    }

    public String getSyncFlag()
    {
        return syncFlag;
    }

    public void setSyncFlag(String syncFlag)
    {
        this.syncFlag = syncFlag;
    }

    public String getMineFlag()
    {
        return mineFlag;
    }

    public void setMineFlag(String mineFlag)
    {
        this.mineFlag = mineFlag;
    }

    public String getMineProtocolFlag()
    {
        return mineProtocolFlag;
    }

    public void setMineProtocolFlag(String mineProtocolFlag)
    {
        this.mineProtocolFlag = mineProtocolFlag;
    }

    public String getVieFlag()
    {
        return vieFlag;
    }

    public void setVieFlag(String vieFlag)
    {
        this.vieFlag = vieFlag;
    }

    public String getVieProtocolFlag()
    {
        return vieProtocolFlag;
    }

    public void setVieProtocolFlag(String vieProtocolFlag)
    {
        this.vieProtocolFlag = vieProtocolFlag;
    }

    public String getVisitDate()
    {
        return visitDate;
    }

    public void setVisitDate(String visitDate)
    {
        this.visitDate = visitDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getVisitTime()
    {
        return visitTime;
    }

    public void setVisitTime(String visitTime)
    {
        this.visitTime = visitTime;
    }

    public String getUploadFlag()
    {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag)
    {
        this.uploadFlag = uploadFlag;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public String getTerminalStatus()
    {
        return terminalStatus;
    }

    public void setTerminalStatus(String terminalStatus)
    {
        this.terminalStatus = terminalStatus;
    }

    public String getIsSelectToCart() {
        return isSelectToCart;
    }

    public void setIsSelectToCart(String isSelectToCart) {
        this.isSelectToCart = isSelectToCart;
    }
}
