package et.tsingtaopad.business.operation.bean;

/**
 * Created by yangwenmin on 2018/4/8.
 */

public class OperationDealplanStc {

    private String credate;// 终端key
    private String totalnum;// 终端名称
    private String username;// 业代key

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
