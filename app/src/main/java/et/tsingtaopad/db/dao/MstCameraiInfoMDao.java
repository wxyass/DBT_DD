package et.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MitValpicMTemp;
import et.tsingtaopad.db.table.MstCameraInfoM;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.MstCameraListMStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 拜访拍照表的DAO层</br>
 */
public interface MstCameraiInfoMDao extends Dao<MstCameraInfoM, String> {

	/**
	 * 获取当天终端拍照记录
	 *
	 * 用于：巡店拜访 -- 拍照
	 *
	 * @param helper
	 * @param terminalkey
	 * @return
	 */
	public List<CameraInfoStc> queryCurrentCameraLst(SQLiteOpenHelper helper,
													 String terminalkey, String visitKey);
	/**
	 * 获取当天终端拍照记录
	 *
	 * 用于：终端追溯 -- 拍照
	 *
	 * @param helper
	 * @param terminalkey
	 * @return
	 */
	public List<MitValpicMTemp> queryZsCurrentCameraLst(SQLiteOpenHelper helper,
														String terminalkey, String valterid);

	/**
	 * 查询所有未上传的图片记录
	 *
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	public List<MstCameraListMStc> getCameraListNoIsuploadByVisitkey(
            SQLiteOpenHelper helper, String isupload, String visitKey);
	/**
	 * 查询所有未上传的图片记录
	 *
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	public List<MstCameraListMStc> getCameraListNoIsuploadNoByVisitkey(
            SQLiteOpenHelper helper, String isupload);


	public List<String> getVisitkeyByisupload(SQLiteOpenHelper helper, String isupload);

	/**
	 * 根据图片主键 更新一条图片记录的上传状态
	 * (根据图片主键,更新上传状态为1(已上传))
	 * @param helper
	 * @param camerakey
	 */
	public void updataUploadbyCameraKey(
            SQLiteOpenHelper helper, String camerakey);

	/**
	 * 根据图片主键 获取图片的本地路径
	 *
	 * @param helper
	 * @param camerakey
	 */
	public String getLocalpathbyCameraKey(
            SQLiteOpenHelper helper, String camerakey);
	/**
	 * 根据(图片类型 终端key visitkey)删除一条记录
	 */
	public void deletePicByCameratype(SQLiteOpenHelper helper, String cameratype, String termkey, String visitkey) ;

	/**
	 * 根据(终端key visitkey)删除 图片记录
	 */
	public void deletePicBytermkeyandvisitkey(SQLiteOpenHelper helper, String termkey, String visitkey);

	/**
	 * 将图片表的记录 上传状态全部改为1(已上传)
	 * (全部更改)
	 * @param helper
	 */
	public void updataUploadstatus(SQLiteOpenHelper helper);

	/**
	 * 根据拜访主键将图片表的 未上传图片的  确定上传状态全部改为0(确定上传)
	 * (全部更改)
	 * 0确定上传(离线拜访后会上传)  1未确定上传(离线拜访后不会上传)
	 * @param helper
	 */
	public void updataSureupstatus(SQLiteOpenHelper helper, String visitId);


	/**
	 * 根据图片主键和本地路径删除一条记录
	 *
	 * @param helper
	 * @param localpath
	 * @param camerakey
	 */
	public void deleteCameraRecord(
            SQLiteOpenHelper helper, String localpath, String camerakey);


	/**
	 * 根据图片主键删除一条记录
	 *
	 * @param helper
	 * @param camerakey
	 * @param camerakey
	 */
	public void deletePicByCamerakey(SQLiteOpenHelper helper, String camerakey);

	/**
	 * 根据图片类型 终端key 删除一条记录
	 *
	 * @param helper
	 * @param pictypekey
	 * @param terminalkey
	 */
	public void deletePicByCamerakeyATerminal(SQLiteOpenHelper helper, String pictypekey, String terminalkey);

	/**
	 * 根据拜访主键 上传状态,查询需要上传的图片
	 *
	 * @param helper
	 * @param isupload 上传状态 0:未上传  1:已上传
	 * @param visitkey  拜访主键
	 * @return
	 */
	public List<MstCameraListMStc> getCameraListNoIsuploadAndVisitkey(
			DatabaseHelper helper, String isupload, String visitkey);


	/**
	 * 获取拜访主键
	 *
	 * @param isupload
	 * @return
	 */
	public List<String> queryVisitKey(SQLiteOpenHelper helper, String isupload);

	/**
	 * 查询所有未上传的图片记录
	 *
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	public List<MstCameraListMStc> getCameraListNoIsupload(
            SQLiteOpenHelper helper, String isupload);

	/**
	 * 根据(图片类型,终端key,visitkey) 获取图片的本地路径
	 *
	 * @param helper
	 * @param cameratype
	 * @param termkey
	 * @param visitkey
	 * @return
	 */
	public String getLocalpathbyCameratype(DatabaseHelper helper, String cameratype, String termkey, String visitkey);

	/**
	 * @param info
	 */
	public void insertMstCameraiInfoM(SQLiteOpenHelper helper, MstCameraInfoM info);


}
