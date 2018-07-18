package et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain;


import java.util.ArrayList;

/**
 * 筛选终端结构体
 */
public class TermCheckStc {

    private static final long serialVersionUID = -7593093452791252366L;

    private String mineshop;// 我品店招
    private String minearea;// 销售我范围
    private String cmparea;// 竞品销售范围
    private String minetreaty;// 我品合作
    private String cmptreaty;// 竞品合作
    private String termone;// 1级终端
    private String termtwo;// 2级
    private String termthree;// 3
    private String termfour;//4
    private String termcq;// 城区
    private String termxz;// 乡镇
    private String termcj;// 村级
    private String termddb;// 大店部
    private String termaddress;// 地址
    private String termcycle;// 拜访周期

    private String hnum;// 高档容量 最高值
    private String hlow;//高档容量 最低值
    private String znum;//中档容量最高值
    private String zlow;//中档容量最低值
    private String pnum;//普档容量最高值
    private String plow;//普档容量最低值
    private String lnum;//低档容量最高值
    private String llow;//低档容量最低值

    private ArrayList<String> sell;// 销售次渠道

    private String checkzh;// 产品组合达标
    private String checkhz;//合作执行到位
    private String checkgao;// 高质量配送
    private String zylnum;// 占有率最高值
    private String zyllow;// 占有率最低值

    private String checkprotype;// 1铺货状态  2价格体系  3竞品信息
    private String prokey;// 产品key
    private String qdjnum;// 渠道价 最高值
    private String qdjlow;// 渠道价 最低值
    private String lsjnum;// 零售价  最高值
    private String lsjlow;// 零售价  最低值

    private String black;// 空白
    private String puhuo;// 铺货
    private String youxiao;// 有效铺货
    private String xiaoshou;// 有效销售


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

    public String getTermone() {
        return termone;
    }

    public void setTermone(String termone) {
        this.termone = termone;
    }

    public String getTermtwo() {
        return termtwo;
    }

    public void setTermtwo(String termtwo) {
        this.termtwo = termtwo;
    }

    public String getTermthree() {
        return termthree;
    }

    public void setTermthree(String termthree) {
        this.termthree = termthree;
    }

    public String getTermfour() {
        return termfour;
    }

    public void setTermfour(String termfour) {
        this.termfour = termfour;
    }

    public String getTermcq() {
        return termcq;
    }

    public void setTermcq(String termcq) {
        this.termcq = termcq;
    }

    public String getTermxz() {
        return termxz;
    }

    public void setTermxz(String termxz) {
        this.termxz = termxz;
    }

    public String getTermcj() {
        return termcj;
    }

    public void setTermcj(String termcj) {
        this.termcj = termcj;
    }

    public String getTermddb() {
        return termddb;
    }

    public void setTermddb(String termddb) {
        this.termddb = termddb;
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

    public String getHnum() {
        return hnum;
    }

    public void setHnum(String hnum) {
        this.hnum = hnum;
    }

    public String getHlow() {
        return hlow;
    }

    public void setHlow(String hlow) {
        this.hlow = hlow;
    }

    public String getZnum() {
        return znum;
    }

    public void setZnum(String znum) {
        this.znum = znum;
    }

    public String getZlow() {
        return zlow;
    }

    public void setZlow(String zlow) {
        this.zlow = zlow;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getPlow() {
        return plow;
    }

    public void setPlow(String plow) {
        this.plow = plow;
    }

    public String getLnum() {
        return lnum;
    }

    public void setLnum(String lnum) {
        this.lnum = lnum;
    }

    public String getLlow() {
        return llow;
    }

    public void setLlow(String llow) {
        this.llow = llow;
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

    public String getZylnum() {
        return zylnum;
    }

    public void setZylnum(String zylnum) {
        this.zylnum = zylnum;
    }

    public String getZyllow() {
        return zyllow;
    }

    public void setZyllow(String zyllow) {
        this.zyllow = zyllow;
    }

    public String getCheckprotype() {
        return checkprotype;
    }

    public void setCheckprotype(String checkprotype) {
        this.checkprotype = checkprotype;
    }

    public String getProkey() {
        return prokey;
    }

    public void setProkey(String prokey) {
        this.prokey = prokey;
    }

    public String getQdjnum() {
        return qdjnum;
    }

    public void setQdjnum(String qdjnum) {
        this.qdjnum = qdjnum;
    }

    public String getQdjlow() {
        return qdjlow;
    }

    public void setQdjlow(String qdjlow) {
        this.qdjlow = qdjlow;
    }

    public String getLsjnum() {
        return lsjnum;
    }

    public void setLsjnum(String lsjnum) {
        this.lsjnum = lsjnum;
    }

    public String getLsjlow() {
        return lsjlow;
    }

    public void setLsjlow(String lsjlow) {
        this.lsjlow = lsjlow;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getPuhuo() {
        return puhuo;
    }

    public void setPuhuo(String puhuo) {
        this.puhuo = puhuo;
    }

    public String getYouxiao() {
        return youxiao;
    }

    public void setYouxiao(String youxiao) {
        this.youxiao = youxiao;
    }

    public String getXiaoshou() {
        return xiaoshou;
    }

    public void setXiaoshou(String xiaoshou) {
        this.xiaoshou = xiaoshou;
    }
}
