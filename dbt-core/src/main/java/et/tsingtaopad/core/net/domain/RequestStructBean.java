package et.tsingtaopad.core.net.domain;

/**
 * Created by yangwenmin on 2018/1/8.
 */

public class RequestStructBean implements java.io.Serializable{
    private static final long serialVersionUID = -4816486072636881494L;
    private RequestHeadStc ReqHead;
    private RequestBodyStc ReqBody;

    public RequestStructBean() { /* compiled code */ }

    public RequestHeadStc getReqHead() {
        return ReqHead;
    }

    public void setReqHead(RequestHeadStc reqHead) {
        ReqHead = reqHead;
    }

    public RequestBodyStc getReqBody() {
        return ReqBody;
    }

    public void setReqBody(RequestBodyStc reqBody) {
        ReqBody = reqBody;
    }
}
