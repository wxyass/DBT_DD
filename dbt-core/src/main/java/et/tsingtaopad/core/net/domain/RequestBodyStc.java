package et.tsingtaopad.core.net.domain;

/**
 * Created by yangwenmin on 2018/1/8.
 */

public class RequestBodyStc implements java.io.Serializable{
    private static final long serialVersionUID = -4591676831315875574L;
    private String content;
    public RequestBodyStc() { /* compiled code */ }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
