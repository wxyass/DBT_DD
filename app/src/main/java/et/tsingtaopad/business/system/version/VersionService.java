package et.tsingtaopad.business.system.version;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import et.tsingtaopad.business.system.repwd.domain.ApkStc;
import et.tsingtaopad.core.net.HttpUrl;
import et.tsingtaopad.core.net.RestClient;
import et.tsingtaopad.core.net.callback.IError;
import et.tsingtaopad.core.net.callback.IFailure;
import et.tsingtaopad.core.net.callback.ISuccess;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 文件名：VersionService.java</br>
 */
public class VersionService {
    
    public static final String TAG = "VersionService";
    protected Context context;
    protected Handler handler;

    String apkUrl = "http://oss.wxyass.com/tscs2.4.3.1.0.apk";
    String apkName = "tscs2.4.3.1.0.apk";
    String downPath = "dbt";// apk的存放位置

    public VersionService(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    // 获取是否需要升级
    public void getUrlData( String departmentid, String userid) {


        String content = "{" +
                "areaid:'" + departmentid + "'," +
                "softversion:'" + DbtLog.getVersion() + "'," +
                "creuser:'" + userid + "'" +
                "}";

        String optcode = "opt_update_version";

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);

                        if ("".equals(json) || json == null) {
                            Toast.makeText(context, "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                // 保存信息
                                String formjson = resObj.getResBody().getContent();
                                parseTableJson(formjson);

                            } else {
                                Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }

    // 解析数据
    private void parseTableJson(String formjson) {
        if(TextUtils.isEmpty(formjson)){
            handler.sendEmptyMessage(ConstValues.WAIT6);
            // Toast.makeText(context, "已是最新版本,无需更新", Toast.LENGTH_SHORT).show();
        }else{
            ApkStc info = JsonUtil.parseJson(formjson, ApkStc.class);

            apkUrl = info.getSoftpath();
            apkName = info.getSoftversion()+".apk";

            // 下载apk
            /*Bundle bundle = new Bundle();
            bundle.putString("apkUrl", apkUrl);//
            bundle.putString("apkName", apkName);//
            DownApkFragment downApkFragment = new DownApkFragment();
            downApkFragment.setArguments(bundle);
            // downLoadApk();
            handler.sendEmptyMessage(ConstValues.WAIT5);*/

            Bundle bundle = new Bundle();
            bundle.putString("apkUrl", apkUrl);
            bundle.putString("apkName", apkName);
            Message msg = new Message();
            msg.what = ConstValues.WAIT5;//
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        // 下载apk
        /*Bundle bundle = new Bundle();
        bundle.putString("apkUrl", apkUrl);
        bundle.putString("apkName", apkName);
        Message msg = new Message();
        msg.what = ConstValues.WAIT5;//
        msg.setData(bundle);
        handler.sendMessage(msg);*/

    }
}
