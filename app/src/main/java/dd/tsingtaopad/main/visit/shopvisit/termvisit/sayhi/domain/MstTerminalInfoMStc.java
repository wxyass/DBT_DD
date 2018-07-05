package dd.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain;


import dd.tsingtaopad.db.table.MstTerminalinfoM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 打招呼 -- 终端信息
 */
public class MstTerminalInfoMStc extends MstTerminalinfoM {
    
    private String provName;
    
    private String cityName;
    
    private String countryName;
    
    private String sellChannelName;
    
    private String mainChannelName;
    
    private String minorChannelName;

    private String areaid;// 二级区域id
    private String areapid;// 大区id
    private String gridkey;// 定格key

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSellChannelName() {
        return sellChannelName;
    }

    public void setSellChannelName(String sellChannelName) {
        this.sellChannelName = sellChannelName;
    }

    public String getMainChannelName() {
        return mainChannelName;
    }

    public void setMainChannelName(String mainChannelName) {
        this.mainChannelName = mainChannelName;
    }

    public String getMinorChannelName() {
        return minorChannelName;
    }

    public void setMinorChannelName(String minorChannelName) {
        this.minorChannelName = minorChannelName;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreapid() {
        return areapid;
    }

    public void setAreapid(String areapid) {
        this.areapid = areapid;
    }

    public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }
}
