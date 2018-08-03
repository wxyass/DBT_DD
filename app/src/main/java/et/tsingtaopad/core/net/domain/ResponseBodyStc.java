package et.tsingtaopad.core.net.domain;

/**
 * Created by yangwenmin on 2018/1/8.
 */

public class ResponseBodyStc implements java.io.Serializable{
    private static final long serialVersionUID = -4807562545922726434L;
    private String content;

    public ResponseBodyStc() { /* compiled code */ }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
