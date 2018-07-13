package et.tsingtaopad.business.visit;

import android.content.Context;
import android.os.Handler;


/**
 * 文件名：VisitService.java</br>
 * 功能描述: </br>
 */
public class VisitService {

    private final String TAG = "VisitService";

    protected Context context;
    protected Handler handler;

    public VisitService(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }


}
