package et.tsingtaopad.util;

import android.content.Context;

import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;

/**
 * Created by yangwenmin on 2018/1/12.
 */

public class requestHeadUtil {

    /**
     * 创建请求实体对象的Head部分(默认信息)
     *
     * @param context
     * @return
     */
    public static RequestHeadStc parseRequestHead(Context context) {
        // 组建请求Json
        RequestHeadStc requestHeadStc = new RequestHeadStc();
        requestHeadStc.setUsercode(PrefUtils.getString(context,"usercode",""));
        requestHeadStc.setPassword(PrefUtils.getString(context,"userPwd",""));
        requestHeadStc.setUsertype(PrefUtils.getString(context,"usertype",""));
        requestHeadStc.setGridkey(PrefUtils.getString(context,"gridkey",""));
        requestHeadStc.setVersion(DbtLog.getVersion());
        requestHeadStc.setBigareaid(PrefUtils.getString(context,"bigareaid",""));
        requestHeadStc.setSecareaid(PrefUtils.getString(context,"secareaid",""));
        requestHeadStc.setSecareaid(PrefUtils.getString(context,"departmentid",""));
        return requestHeadStc;
    }
}
