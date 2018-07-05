package dd.tsingtaopad.business.first.bean;

/**
 * Created by yangwenmin on 2018/4/8.
 */

public class GvTop {

    private String username;// 业代名称
    private String termcount;// 数量
    private String rankname;// 冠军名称

    public GvTop() {
    }

    public GvTop(String username, String termcount, String rankname) {
        this.username = username;
        this.termcount = termcount;
        this.rankname = rankname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTermcount() {
        return termcount;
    }

    public void setTermcount(String termcount) {
        this.termcount = termcount;
    }

    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname;
    }
}
