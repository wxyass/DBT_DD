package dd.tsingtaopad.dd.ddzs.zsterm.zsselect.domain;


import dd.tsingtaopad.db.table.MstTerminalinfoM;

/**
 * 筛选终端结构体
 */
public class TermSpecialStc {

    private static final long serialVersionUID = -7593093452791252366L;

    private String areakey;
    private String areaname;
    private String gridkey;
    private String gridname;
    private String routekey;
    private String routename;
    private String mineprokey;
    private String mineproname;
    private String vieprokey;
    private String vieproname;
    private String isagree;// 0:未选中 不是协议店   1选中 是协议店
    private String termname;
    private String tablename;
    private String pager;// 第几页

    public String getAreakey() {
        return areakey;
    }

    public void setAreakey(String areakey) {
        this.areakey = areakey;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getGridkey() {
        return gridkey;
    }

    public void setGridkey(String gridkey) {
        this.gridkey = gridkey;
    }

    public String getGridname() {
        return gridname;
    }

    public void setGridname(String gridname) {
        this.gridname = gridname;
    }

    public String getRoutekey() {
        return routekey;
    }

    public void setRoutekey(String routekey) {
        this.routekey = routekey;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getMineprokey() {
        return mineprokey;
    }

    public void setMineprokey(String mineprokey) {
        this.mineprokey = mineprokey;
    }

    public String getMineproname() {
        return mineproname;
    }

    public void setMineproname(String mineproname) {
        this.mineproname = mineproname;
    }

    public String getVieprokey() {
        return vieprokey;
    }

    public void setVieprokey(String vieprokey) {
        this.vieprokey = vieprokey;
    }

    public String getVieproname() {
        return vieproname;
    }

    public void setVieproname(String vieproname) {
        this.vieproname = vieproname;
    }

    public String getIsagree() {
        return isagree;
    }

    public void setIsagree(String isagree) {
        this.isagree = isagree;
    }

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }
}
