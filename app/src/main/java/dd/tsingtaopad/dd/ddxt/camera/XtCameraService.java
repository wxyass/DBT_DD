package dd.tsingtaopad.dd.ddxt.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.DateUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MstCameraiInfoMDao;
import dd.tsingtaopad.db.dao.MstPictypeMDao;
import dd.tsingtaopad.db.table.MitValpicMTemp;
import dd.tsingtaopad.db.table.MstCameraInfoMTemp;
import dd.tsingtaopad.db.table.MstTerminalinfoMCart;
import dd.tsingtaopad.db.table.MstTerminalinfoMTemp;
import dd.tsingtaopad.db.table.MstTerminalinfoMZsCart;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtCameraService extends XtShopVisitService {

    private final String TAG = "XtCameraService";

    public XtCameraService(Context context, Handler handler) {
        super(context, handler);
    }






    // 保存照片记录到数据库
    public String savePicData(CameraInfoStc cameraDataStc, String picname, String imagefileString,
                              String termId, MstTerminalinfoMTemp termTemp, String visitKey,MstTerminalInfoMStc mstTerminalInfoMStc) {

        DbtLog.logUtils(TAG, "queryCurrentPicRecord()-获取当天拍照记录");
        List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();
        MstCameraInfoMTemp mstCameraInfoMTemp = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstCameraInfoMTemp, String> proItemTempDao = helper.getMstCameraInfoMTempDao();

            if (CheckUtil.isBlankOrNull(cameraDataStc.getCamerakey()) || "".equals(cameraDataStc.getCamerakey())) {
                mstCameraInfoMTemp = new MstCameraInfoMTemp();
                mstCameraInfoMTemp.setCamerakey(FunUtil.getUUID());// 图片主键
                mstCameraInfoMTemp.setTerminalkey(termId);// 终端主键
                mstCameraInfoMTemp.setVisitkey(visitKey); // 拜访主键
                mstCameraInfoMTemp.setPictypekey(cameraDataStc.getPictypekey());// 图片类型主键(UUID)
                mstCameraInfoMTemp.setPicname(picname);// 图片名称
                mstCameraInfoMTemp.setLocalpath(termTemp.getTerminalname());// 本地图片路径 -> 改为终端名称
                mstCameraInfoMTemp.setNetpath(picname);// 请求网址
                mstCameraInfoMTemp.setCameradata(DateUtil.getDateTimeStr(1));// 拍照时间
                mstCameraInfoMTemp.setIsupload("0");// 是否已上传 0未上传  1已上传
                mstCameraInfoMTemp.setIstakecamera("1");//
                //mstCameraInfoMTemp.setPicindex();// 图片所在角标
                mstCameraInfoMTemp.setPictypename(cameraDataStc.getPictypename());// 图片类型(中文名称)
                mstCameraInfoMTemp.setSureup("0");// 确定上传 0确定上传 1未确定上传
                mstCameraInfoMTemp.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                mstCameraInfoMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());
                mstCameraInfoMTemp.setAreapid(mstTerminalInfoMStc.getAreapid());
                mstCameraInfoMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());
                mstCameraInfoMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());
                proItemTempDao.create(mstCameraInfoMTemp);
            } else {// 更新照片记录
                mstCameraInfoMTemp = new MstCameraInfoMTemp();
                mstCameraInfoMTemp.setCamerakey(cameraDataStc.getCamerakey());// 图片主键
                mstCameraInfoMTemp.setTerminalkey(cameraDataStc.getTerminalkey());// 终端主键
                mstCameraInfoMTemp.setVisitkey(cameraDataStc.getVisitkey()); // 拜访主键
                mstCameraInfoMTemp.setPictypekey(cameraDataStc.getPictypekey());// 图片类型主键(UUID)
                mstCameraInfoMTemp.setPicname(picname);// 图片名称
                mstCameraInfoMTemp.setLocalpath(picname);// 本地图片路径
                mstCameraInfoMTemp.setNetpath(picname);// 请求网址(原先ftp上传用,现已不用)
                mstCameraInfoMTemp.setCameradata(DateUtil.getDateTimeStr(1));// 拍照时间
                mstCameraInfoMTemp.setIsupload("0");// 是否已上传 0未上传  1已上传
                mstCameraInfoMTemp.setIstakecamera("1");//
                //mstCameraInfoMTemp.setPicindex();// 图片所在角标
                mstCameraInfoMTemp.setPictypename(cameraDataStc.getPictypename());// 图片类型(中文名称)
                mstCameraInfoMTemp.setSureup("0");// 确定上传 0确定上传 1未确定上传
                mstCameraInfoMTemp.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                mstCameraInfoMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());
                mstCameraInfoMTemp.setAreapid(mstTerminalInfoMStc.getAreapid());
                mstCameraInfoMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());
                mstCameraInfoMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());
                proItemTempDao.createOrUpdate(mstCameraInfoMTemp);
            }


        } catch (Exception e) {
            Log.e(TAG, "获取拜访拍照临时表DAO对象失败", e);
        }

        return mstCameraInfoMTemp.getCamerakey();
    }

    // 保存照片记录到追溯 数据库
    public String saveZsPicData(MitValpicMTemp valpicMTemp, String picname, String imagefileString,
                                String termId, MstTerminalinfoMZsCart termCart, String valterid, MstTerminalInfoMStc mstTerminalInfoMStc) {

        DbtLog.logUtils(TAG, "queryCurrentPicRecord()-获取当天拍照记录");
        //List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();
        MitValpicMTemp mitValpicMTemp = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValpicMTemp, String> proItemTempDao = helper.getMitValpicMTempDao();

            if (CheckUtil.isBlankOrNull(valpicMTemp.getId()) || "".equals(valpicMTemp.getId())) {
                mitValpicMTemp = new MitValpicMTemp();
                mitValpicMTemp.setId(FunUtil.getUUID());// 图片主键
                mitValpicMTemp.setValterid(valterid); // 追溯主键
                mitValpicMTemp.setPictypekey(valpicMTemp.getPictypekey());// 图片类型主键(UUID)
                mitValpicMTemp.setPicname(picname);// 图片名称
                //mitValpicMTemp.setTerminalname(termCart.getTerminalname());// 本地图片路径 -> 改为终端名称
                mitValpicMTemp.setTerminalname(mstTerminalInfoMStc.getTerminalname());// 本地图片路径 -> 改为终端名称
                mitValpicMTemp.setCameradata(DateUtil.getDateTimeStr(1));// 拍照时间
                mitValpicMTemp.setPictypename(valpicMTemp.getPictypename());// 图片类型(中文名称)
                mitValpicMTemp.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                mitValpicMTemp.setAreapid(mstTerminalInfoMStc.getAreapid());// 大区
                mitValpicMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());// 二级区域
                mitValpicMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());//定格
                mitValpicMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());// 路线
                mitValpicMTemp.setTerminalkey(termId);// 终端主键
                proItemTempDao.create(mitValpicMTemp);
            } else {// 更新照片记录
                /*mstCameraInfoMTemp = new MitValpicMTemp();
                mstCameraInfoMTemp.setId(valpicMTemp.getId());// 图片主键
                mstCameraInfoMTemp.setValterid(valpicMTemp.getValterid()); // 追溯主键
                mstCameraInfoMTemp.setPictypekey(valpicMTemp.getPictypekey());// 图片类型主键(UUID)
                mstCameraInfoMTemp.setPicname(picname);// 图片名称
                mstCameraInfoMTemp.setPictypename(valpicMTemp.getPictypename());// 图片类型(中文名称)
                mstCameraInfoMTemp.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                mstCameraInfoMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());// 二级区域
                mstCameraInfoMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());//定格
                mstCameraInfoMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());// 路线
                mstCameraInfoMTemp.setTerminalkey(valpicMTemp.getTerminalkey());// 终端主键*/

                valpicMTemp.setPicname(picname);// 图片名称
                valpicMTemp.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                proItemTempDao.createOrUpdate(mitValpicMTemp);
            }
        } catch (Exception e) {
            Log.e(TAG, "获取拜访拍照临时表DAO对象失败", e);
        }

        return mitValpicMTemp.getId();
    }

}
