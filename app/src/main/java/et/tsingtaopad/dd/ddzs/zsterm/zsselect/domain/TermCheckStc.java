package et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain;


import java.util.ArrayList;

/**
 * 筛选终端结构体
 */
public class TermCheckStc {

    private static final long serialVersionUID = -7593093452791252366L;

    private String bigid;// 大区id
    private String secid;// 二级区域id
    private String gridid;// 定格id
    private String routeid;// 路线id

    private int startrow;// 起始条目
    private int endrow;// 结束条目


    private String mineshop;// 我品店招
    private String minearea;// 销售我范围
    private String cmparea;// 竞品销售范围
    private String minetreaty;// 我品合作
    private String cmptreaty;// 竞品合作

    private String termaddress;// 地址
    private String termcycle;// 拜访周期

    private ArrayList<String> sell;// 销售次渠道
    private ArrayList<String> puhuolst;// 铺货
    private ArrayList<String> termlvlst;// 终端等级
    private ArrayList<String> arealst;// 区域类型

    private String checkzh;// 产品组合达标
    private String checkhz;//合作执行到位
    private String checkgao;// 高质量配送

    private String prokey;// 铺货状态 勾选的产品key
    private String cmpprokey;// 竞品信息 勾选的产品key
    private ArrayList<ProSellStc> pricelst;// 价格体系数据集合


    public String getMineshop() {
        return mineshop;
    }

    public void setMineshop(String mineshop) {
        this.mineshop = mineshop;
    }

    public String getMinearea() {
        return minearea;
    }

    public void setMinearea(String minearea) {
        this.minearea = minearea;
    }

    public String getCmparea() {
        return cmparea;
    }

    public void setCmparea(String cmparea) {
        this.cmparea = cmparea;
    }

    public String getMinetreaty() {
        return minetreaty;
    }

    public void setMinetreaty(String minetreaty) {
        this.minetreaty = minetreaty;
    }

    public String getCmptreaty() {
        return cmptreaty;
    }

    public void setCmptreaty(String cmptreaty) {
        this.cmptreaty = cmptreaty;
    }





    public String getTermaddress() {
        return termaddress;
    }

    public void setTermaddress(String termaddress) {
        this.termaddress = termaddress;
    }

    public String getTermcycle() {
        return termcycle;
    }

    public void setTermcycle(String termcycle) {
        this.termcycle = termcycle;
    }



    public ArrayList<String> getSell() {
        return sell;
    }

    public void setSell(ArrayList<String> sell) {
        this.sell = sell;
    }

    public String getCheckzh() {
        return checkzh;
    }

    public void setCheckzh(String checkzh) {
        this.checkzh = checkzh;
    }

    public String getCheckhz() {
        return checkhz;
    }

    public void setCheckhz(String checkhz) {
        this.checkhz = checkhz;
    }

    public String getCheckgao() {
        return checkgao;
    }

    public void setCheckgao(String checkgao) {
        this.checkgao = checkgao;
    }

    public String getProkey() {
        return prokey;
    }

    public void setProkey(String prokey) {
        this.prokey = prokey;
    }

    public ArrayList<String> getPuhuolst() {
        return puhuolst;
    }

    public void setPuhuolst(ArrayList<String> puhuolst) {
        this.puhuolst = puhuolst;
    }

    public ArrayList<String> getTermlvlst() {
        return termlvlst;
    }

    public void setTermlvlst(ArrayList<String> termlvlst) {
        this.termlvlst = termlvlst;
    }

    public ArrayList<String> getArealst() {
        return arealst;
    }

    public void setArealst(ArrayList<String> arealst) {
        this.arealst = arealst;
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getGridid() {
        return gridid;
    }

    public void setGridid(String gridid) {
        this.gridid = gridid;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public int getStartrow() {
        return startrow;
    }

    public void setStartrow(int startrow) {
        this.startrow = startrow;
    }

    public int getEndrow() {
        return endrow;
    }

    public void setEndrow(int endrow) {
        this.endrow = endrow;
    }

    public String getCmpprokey() {
        return cmpprokey;
    }

    public void setCmpprokey(String cmpprokey) {
        this.cmpprokey = cmpprokey;
    }

    public ArrayList<ProSellStc> getPricelst() {
        return pricelst;
    }

    public void setPricelst(ArrayList<ProSellStc> pricelst) {
        this.pricelst = pricelst;
    }
}
