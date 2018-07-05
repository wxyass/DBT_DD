package dd.tsingtaopad.core.net.domain;

/**
 * Created by yangwenmin on 2018/1/8.
 */

public class ResponseHeadStc implements java.io.Serializable{
    private static final long serialVersionUID = -4807562545922726434L;
    private String optcode;
    private String status;
    private String content;

    public ResponseHeadStc() { /* compiled code */ }

    public String getOptcode() {
        return optcode;
    }

    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
