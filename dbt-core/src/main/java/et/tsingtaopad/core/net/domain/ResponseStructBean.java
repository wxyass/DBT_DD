package et.tsingtaopad.core.net.domain;

/**
 * Created by yangwenmin on 2018/1/8.
 */

public class ResponseStructBean implements java.io.Serializable {
    private static final long serialVersionUID = -1633194767519681742L;
    private ResponseHeadStc ResHead;
    private ResponseBodyStc ResBody;

    public ResponseStructBean() { /* compiled code */ }

    public ResponseHeadStc getResHead() {
        return ResHead;
    }

    public void setResHead(ResponseHeadStc resHead) {
        ResHead = resHead;
    }

    public ResponseBodyStc getResBody() {
        return ResBody;
    }

    public void setResBody(ResponseBodyStc resBody) {
        ResBody = resBody;
    }
}
