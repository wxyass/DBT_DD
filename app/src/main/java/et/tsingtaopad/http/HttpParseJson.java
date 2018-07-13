package et.tsingtaopad.http;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import Decoder.BASE64Decoder;
import et.tsingtaopad.core.net.domain.RequestBodyStc;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.GZIP;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.ZipHelper;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;

/**
 * Created by yangwenmin on 2017/12/7.
 */

public class HttpParseJson {

    public static final String TAG = "HttpParseJson";

    /**
     * 解压、解析网络请求返回结果(老框架 解压方式)
     *
     * 返回结果 用新的解压方式解压(161127)
     *
     * @param resContent    网络请求返回结果
     * @return
     */
    public static ResponseStructBean parseRes(String resContent) {


        //      //配合朱志凯测试给他提取json语句进行压力测试
        //      FileTool.writeTxt(JsonUtil.toJson(resContent),FileTool.getSDPath()+"/ceshi.txt");
        ResponseStructBean resObj = new ResponseStructBean();
        //判断字符串是否为空或者null
        if (!CheckUtil.isBlankOrNull(resContent)) {


            String json;
            try {
                json = GZIP.uncompress2(new BASE64Decoder().decodeBuffer(resContent));
                //FileTool.writeTxt(json, FileTool.getSDPath()+"/login4.txt");
                resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                // 促使内存释放
                json = null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    	  /*try {

        	  // 解密
        	  //String json3 = URLDecoder.decode(resContent, "GBK");
        	  // 解压
        	  byte[] buffer = new byte[0];
              buffer = resContent.getBytes("ISO-8859-1");
              json = ZipHelper.unZipByteToString(buffer);

              // 先base64解密 再解压成json
  			  //json =GZIP.uncompress2(base.decodeBuffer(resContent));

              FileTool.writeTxt(json, FileTool.getSDPath()+"/login4.txt");
              resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
              // 促使内存释放
              json = null;

          } catch (UnsupportedEncodingException e) {
              Log.e(TAG, "处理网络返回结果失败", e);
              if(resObj.getResHead().getStatus().equals("N")){

              }else{
              	resObj.getResHead().setStatus(ConstValues.ERROR);
                  resObj.getResBody().setContent(
                          PropertiesUtil.getProperties("msg_err_netfail"));
              }

          }*/

        } else {
            resObj = new ResponseStructBean();
            resObj.getResHead().setStatus(ConstValues.ERROR);
            resObj.getResBody().setContent(
                    PropertiesUtil.getProperties("msg_err_netfail"));
        }
        return resObj;
    }

    /**
     * 解压、解析网络请求返回结果
     *
     * 返回结果 用新的解压方式解压
     *
     * @param resContent    网络请求返回结果
     * @return
     */
    public static ResponseStructBean parseJsonRes(String resContent) {
        //
        DbtLog.logUtils(TAG, "解压前");
        DbtLog.logUtils(TAG, resContent);
        //      FileTool.writeTxt(JsonUtil.toJson(resContent),FileTool.getSDPath()+"/ceshi.txt");
        ResponseStructBean resObj = new ResponseStructBean();
        //判断字符串是否为空或者null
        if (!CheckUtil.isBlankOrNull(resContent)) {
            String json;
            try {
                // 解压
                byte[] buffer = new byte[0];
                buffer = resContent.getBytes("ISO-8859-1");
                json = ZipHelper.unZipByteToString(buffer);

                DbtLog.logUtils(TAG, "解压后");
                DbtLog.logUtils(TAG, json);

                resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                // 促使内存释放
                json = null;

            } catch (UnsupportedEncodingException e) {
                Log.e("HttpParseJson", "处理网络返回结果失败", e);
                if(resObj.getResHead().getStatus().equals("N")){

                }else{
                    resObj.getResHead().setStatus(ConstValues.ERROR);
                    resObj.getResBody().setContent(PropertiesUtil.getProperties("msg_err_netfail"));
                }

            }

        } else {
            resObj = new ResponseStructBean();
            resObj.getResHead().setStatus(ConstValues.ERROR);
            resObj.getResBody().setContent(PropertiesUtil.getProperties("msg_err_netfail"));
        }
        return resObj;
    }

    /**
     * 解压、解析网络请求返回结果 (新框架 使用的一种解压方式 解压返回结果成String)
     *
     * 返回结果 用新的解压方式解压
     *
     * @param resContent    网络请求返回结果
     * @return
     */
    public static String parseJsonResToString(String resContent) {
        //
        DbtLog.logUtils(TAG, "解压前");
        DbtLog.logUtils(TAG, resContent);
        String json = "";
        //判断字符串是否为空或者null
        /*if (!CheckUtil.isBlankOrNull(resContent)) {
            try {
                // 解压
                byte[] buffer = new byte[0];
                buffer = resContent.getBytes("ISO-8859-1");
                json = ZipHelper.unZipByteToString(buffer);

                DbtLog.logUtils(TAG, "解压后");
                DbtLog.logUtils(TAG, json);


            } catch (UnsupportedEncodingException e) {
                Log.e("HttpParseJson", "处理网络返回结果失败", e);
            }

        } else {
            json = "";
        }*/
        json = resContent;
        return json;
    }

    /**
     * 创建请求实体对象
     * @param requestHeadStc   头部信息(放用户信息)
     * @param resContent       请求json
     * @return  返回:请求实体对象
     */
    public static RequestStructBean parseRequestStructBean(RequestHeadStc requestHeadStc,String resContent) {
        // 组建请求Json
        RequestStructBean reqObj = new RequestStructBean();

        RequestBodyStc requestBodyStc = new RequestBodyStc();
        requestBodyStc.setContent(resContent);

        reqObj.setReqHead(requestHeadStc);
        reqObj.setReqBody(requestBodyStc);
        
        return reqObj;
    }

    /**
     * 压缩请求json
     *
     * @param reqObj
     * @return
     */
    public static String parseRequestJson(RequestStructBean reqObj){
        String jsonZip = "";

        try {
            jsonZip = JsonUtil.toJson(reqObj);
            // 加压缩
            //jsonZip = new String(ZipHelper.zipString(jsonZip), "ISO-8859-1");


            //配合朱志凯测试给他提取json语句进行压力测试
            //FileTool.writeTxt(JsonUtil.toJson(reqObj),FileTool.getSDPath()+"/ceshi.txt");
        } catch (Exception e1) {
            //Log.e(TAG, "压缩上传数据JSON失败", e1);
        }
        return jsonZip;
    }

}
