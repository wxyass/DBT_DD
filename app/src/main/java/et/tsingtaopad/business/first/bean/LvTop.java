package et.tsingtaopad.business.first.bean;

/**
 * Created by yangwenmin on 2018/4/8.
 */

public class LvTop {

    private String rank;// 排名
    private String rankname;// 排行name
    private String ranknum;// 当前个数
    private String totalnum;// 总数
    private String content;// 单位

    public LvTop() {
    }

    public LvTop(String rank, String rankname, String ranknum, String totalnum, String content) {
        this.rank = rank;
        this.rankname = rankname;
        this.ranknum = ranknum;
        this.totalnum = totalnum;
        this.content = content;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname;
    }

    public String getRanknum() {
        return ranknum;
    }

    public void setRanknum(String ranknum) {
        this.ranknum = ranknum;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
