package et.tsingtaopad.home.initadapter;

import android.Manifest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstSynckvM;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.login.domain.LoginSession;

/**
 * Created by yangwenmin on 2017/12/25.
 * 系统常量配置,将旧Dbtplus的ConstValues类,拆成2部分:
 * ①et.tsingtaopad.core.util.dbtutil.ConstValues  //定义一些不变的常量(除handler)
 * ②et.tsingtaopad.GlobalValues. // 放置一些集合,使用前会将数据加载到内存中
 */

public class GlobalValues {

    // 登录信息缓存Key
    public static final String LOGINSESSIONKEY = "loginSesion";
    // 打招呼是否读取完整  true:不完整   false:完整
    public static final String SAYHIREADY = "sayhiready";
    // 判断购物车是协同,还是追溯  1协同  2追溯
    public static final String DDXTZS = "ddxtzs";
    //今日要事是否允许提醒
    public static boolean isDayThingWarn = false;

    // 协同拜访上传成功
    public static final int SINGLE_UP_SUC = 110;
    // 协同拜访上传失败
    public static final int SINGLE_UP_FAIL = 111;

    // 最后一次点击路线的时间
    public static final String ROUNTE_TIME = "route_time";
    // 购物车是否已经同步数据  false:没有  true:已同步
    public static final String XT_CART_SYNC = "xtcartsync";
    public static final String ZS_CART_SYNC = "zscartsync";
    public static final String XT_CART_TIME = "xtcarttime";
    public static final String ZS_CART_TIME = "zscarttime";

    // TODO hongen 删除new LoginSession()  登录Session
    public static LoginSession loginSession = new LoginSession();

    // KV表, K:表名， V：kv对象
    public static Map<String, MstSynckvM> kvMap = new HashMap<String, MstSynckvM>();

    // 数据字典
    public static Map<String, List<KvStc>> dataDicMap = new HashMap<String, List<KvStc>>();

    // 省市县
    public static List<KvStc> provLst = new ArrayList<KvStc>();

    // 所属线路
    public static List<MstRouteM> lineLst = new ArrayList<MstRouteM>();

    // 我品经销商供货关系
    public static List<KvStc> agencyMineLst = new ArrayList<KvStc>();

    // 竞品经销商供货关系
    public static List<KvStc> agencyVieLst = new ArrayList<KvStc>();

    // 指标、指标值关联关系
    public static List<KvStc> indexLst = new ArrayList<KvStc>();

    // 可拜访经销
    public static List<KvStc> agencyVisitLst = new ArrayList<KvStc>();

    //连接mq服务器创建的连接
    //public static IMqttClient mqttClient = null;

    //连接mq服务器创建的连接
    public static HashMap<String, Integer> AuthorityMap = new HashMap<String, Integer>();

    // 打招呼填写信息是否完整
    public static boolean isSayHiSure = true;

    // 权限相关 ↓--------------------------------------------------------------------------
    /**
     * 权限常量相关
     */
    public static final int WRITE_READ_EXTERNAL_CODE = 0x01;// 读写内存卡权限请求码
    public static final int HARDWEAR_CAMERA_CODE = 0x02;// 相机权限请求码
    public static final int LOCAL_CODE = 0x03;// 定位请求码
    public static final int WRITE_LOCAL_CODE = 0x04;// 拍照+ 读写

    // 读写内存卡权限
    public static final String[] WRITE_READ_EXTERNAL_PERMISSION = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    // 相机权限
    public static final String[] HARDWEAR_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    // 定位权限
    public static final String[] LOCAL_PERMISSION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

    public static final String[] WRITE_EXTERNAL_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    // 权限相关 ↑--------------------------------------------------------------------------
}
