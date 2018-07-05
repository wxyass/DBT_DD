package dd.tsingtaopad.business.visit.bean;

/**
 * Created by yangwenmin on 2018/4/8.
 */

public class VisitContentStc {

    private String terminalkey;// 终端key
    private String terminalname;// 终端名称
    private String userkey;// 业代key
    private String username;// 业代名称
    private String padisconsistent;// 上传状态
    private String address;// 终端地址


    public String getTerminalkey() {
        return terminalkey;
    }

    public void setTerminalkey(String terminalkey) {
        this.terminalkey = terminalkey;
    }

    public String getTerminalname() {
        return terminalname;
    }

    public void setTerminalname(String terminalname) {
        this.terminalname = terminalname;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
