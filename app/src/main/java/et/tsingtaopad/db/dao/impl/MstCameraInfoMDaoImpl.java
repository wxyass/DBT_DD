package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstCameraiInfoMDao;
import et.tsingtaopad.db.table.MitValpicMTemp;
import et.tsingtaopad.db.table.MstCameraInfoM;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.MstCameraListMStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstCameraInfoMDaoImpl extends 
            BaseDaoImpl<MstCameraInfoM, String> implements MstCameraiInfoMDao {

    public MstCameraInfoMDaoImpl(ConnectionSource
                        connectionSource) throws SQLException {
        super(connectionSource, MstCameraInfoM.class);
    }

	/**
	 * 根据终端key和拜访key查询记录
	 */
	@Override
	public List<CameraInfoStc> queryCurrentCameraLst(SQLiteOpenHelper helper,
													 String terminalkey, String visitKey) {

        List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();
        
        StringBuffer buffer = new StringBuffer();
        //buffer.append("select * from MST_CAMERAINFO_M_TEMP where terminalkey = ? and cameradata = ? and istakecamera = ? and isupload = ? and visitkey = ?");
        buffer.append("select * from MST_CAMERAINFO_M_TEMP where terminalkey = ?  and visitkey = ?");
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey,visitKey});
		CameraInfoStc cameradatastc;
        while (cursor.moveToNext()) {
        	cameradatastc = new CameraInfoStc();
        	cameradatastc.setCamerakey(cursor.getString(cursor.getColumnIndex("camerakey")));
        	cameradatastc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
        	cameradatastc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
        	cameradatastc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));

			cameradatastc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			cameradatastc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
           lst.add(cameradatastc);
        }
        //db.close();
        return lst;
    }
	/**
	 * 根据终端key和拜访key查询记录
	 */
	@Override
	public List<MitValpicMTemp> queryZsCurrentCameraLst(SQLiteOpenHelper helper,
														String terminalkey, String valterid) {

        List<MitValpicMTemp> lst = new ArrayList<MitValpicMTemp>();

        StringBuffer buffer = new StringBuffer();
        //buffer.append("select * from MST_CAMERAINFO_M_TEMP where terminalkey = ? and cameradata = ? and istakecamera = ? and isupload = ? and visitkey = ?");
        buffer.append("select * from MIT_VALPIC_M_TEMP where terminalkey = ?  and valterid = ?");
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey,valterid});
		MitValpicMTemp cameradatastc;
        while (cursor.moveToNext()) {
        	cameradatastc = new MitValpicMTemp();
        	cameradatastc.setId(cursor.getString(cursor.getColumnIndex("id")));
        	//cameradatastc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
        	//cameradatastc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
        	cameradatastc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			cameradatastc.setValterid(cursor.getString(cursor.getColumnIndex("valterid")));
			cameradatastc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));

			cameradatastc.setPicname(cursor.getString(cursor.getColumnIndex("picname")));// 图片名称
			cameradatastc.setPictypename(cursor.getString(cursor.getColumnIndex("pictypename")));// 图片类型(中文名称)
			//cameradatastc.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
			cameradatastc.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));// 二级区域
			cameradatastc.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));//定格
			cameradatastc.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));// 路线

           lst.add(cameradatastc);
        }
        //db.close();
        return lst;
    }

	/**
	 * 查询所有未上传的图片记录
	 * 
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	@Override
	public List<MstCameraListMStc> getCameraListNoIsuploadByVisitkey(
			SQLiteOpenHelper helper,  String isupload,String visitKey) {

		List<MstCameraListMStc> lst = new ArrayList<MstCameraListMStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where isupload = ? AND sureup = ? AND visitkey = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {isupload, "0" ,visitKey});
		MstCameraListMStc mstCameraListMStc;
		while (cursor.moveToNext()) {
			mstCameraListMStc = new MstCameraListMStc();
			mstCameraListMStc.setCamerakey(cursor.getString(cursor.getColumnIndex("camerakey")));
			mstCameraListMStc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			mstCameraListMStc.setCameradata(cursor.getString(cursor.getColumnIndex("cameradata")));
			mstCameraListMStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			mstCameraListMStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstCameraListMStc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
			mstCameraListMStc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
			mstCameraListMStc.setPicname(cursor.getString(cursor.getColumnIndex("picname")));
			mstCameraListMStc.setImagefileString(cursor.getString(cursor.getColumnIndex("imagefileString")));
			lst.add(mstCameraListMStc); 
		}
		//db.close();
		return lst;
	}
	/**
	 * 查询所有未上传的图片记录
	 * 
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	@Override
	public List<MstCameraListMStc> getCameraListNoIsuploadNoByVisitkey(
			SQLiteOpenHelper helper,  String isupload) {
		
		List<MstCameraListMStc> lst = new ArrayList<MstCameraListMStc>();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where isupload = ? AND sureup = ?  ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {isupload, "0" });
		MstCameraListMStc mstCameraListMStc;
		while (cursor.moveToNext()) {
			mstCameraListMStc = new MstCameraListMStc();
			mstCameraListMStc.setCamerakey(cursor.getString(cursor.getColumnIndex("camerakey")));
			mstCameraListMStc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			mstCameraListMStc.setCameradata(cursor.getString(cursor.getColumnIndex("cameradata")));
			mstCameraListMStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			mstCameraListMStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstCameraListMStc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
			mstCameraListMStc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
			mstCameraListMStc.setPicname(cursor.getString(cursor.getColumnIndex("picname")));
			mstCameraListMStc.setImagefileString(cursor.getString(cursor.getColumnIndex("imagefileString")));
			lst.add(mstCameraListMStc); 
		}
		//db.close();
		return lst;
	}
	
	
	/**
	 * 查询所有未上传的图片的visitkey
	 * 
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	@Override
	public List<String> getVisitkeyByisupload(SQLiteOpenHelper helper,  String isupload) {
		
		List<String> lst = new ArrayList<String>();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select  distinct visitkey  from MST_CAMERAINFO_M where isupload = ? AND sureup = ?  ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {isupload, "0" });
		String visitkey;
		while (cursor.moveToNext()) {
			//mstCameraListMStc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			visitkey = cursor.getString(cursor.getColumnIndex("visitkey"));
			lst.add(visitkey); 
		}
		//db.close();
		return lst;
	}
	

	/**
	 * 根据 图片主键 更新该条记录是否上传  0已上传  1未上传s
	 */
	@Override
	public void updataUploadbyCameraKey(SQLiteOpenHelper helper, String camerakey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_CAMERAINFO_M SET isupload = 1 where camerakey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { camerakey });
		//db.close();
	}

	/**
	 * 根据图片主键和本地路径删除一条记录
	 */
	@Override
	public void deleteCameraRecord(SQLiteOpenHelper helper, String localpath,
			String camerakey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_CAMERAINFO_M WHERE localpath = ? and camerakey = ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { localpath, camerakey });
		//db.close();
		
	}
	/**
	 * 根据图片主键删除一条记录
	 */
	@Override
	public void deletePicByCamerakey(SQLiteOpenHelper helper,String camerakey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_CAMERAINFO_M WHERE camerakey = ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { camerakey });
		//db.close();
		
	}
	
	/**
	 * 根据(图片类型 终端key visitkey)删除一条记录
	 */
	@Override
	public void deletePicByCameratype(SQLiteOpenHelper helper,String cameratype,String termkey,String visitkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_CAMERAINFO_M WHERE pictypekey = ? and terminalkey = ? and visitkey = ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { cameratype, termkey, visitkey});
		//db.close();
		
	}
	
	/**
	 * 根据(终端key visitkey)删除 图片记录
	 */
	@Override
	public void deletePicBytermkeyandvisitkey(SQLiteOpenHelper helper,String termkey,String visitkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_CAMERAINFO_M WHERE  terminalkey = ? and visitkey <> ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {  termkey, visitkey});
		//db.close();
		
	}

	/**
	 * 将图片表的记录 上传状态全部改为1(已上传)
	 */
	@Override
	public void updataUploadstatus(SQLiteOpenHelper helper) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_CAMERAINFO_M SET isupload = ?  ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {"1"});
		//db.close();
	}

	/**
	 * 根据拜访主键将此次拜访图片表的所有未上传图片记录的  确定上传sureup状态全部改为0(确定上传)
	 * (全部更改)
	 * 0确定上传(离线拜访后会上传)  1未确定上传(离线拜访后不会上传)
	 * @param helper
	 */
	@Override
	public void updataSureupstatus(SQLiteOpenHelper helper,String visitId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_CAMERAINFO_M SET sureup = ?  WHERE isupload = ? AND visitkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {"0","0",visitId});
		//db.close();
	}

	/**
	 * 根据图片主键 获取图片的本地路径
	 * 
	 * @param helper
	 * @param camerakey
	 */
	@Override
	public String getLocalpathbyCameraKey(SQLiteOpenHelper helper,
			String camerakey) {
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select * from MST_CAMERAINFO_M where camerakey = ? ");
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{camerakey});
        String localpath="";
        while (cursor.moveToNext()) {
        	localpath = cursor.getString(cursor.getColumnIndex("localpath"));
        }
        return localpath;
	}
	
	/**
	 * 根据(图片类型,终端key,visitkey) 获取图片的本地路径
	 * 
	 * @param helper
	 * @param cameratype
	 * @param termkey
	 * @param visitkey
	 * @return
	 */
	@Override
	public String getLocalpathbyCameratype(DatabaseHelper helper, String cameratype, String termkey, String visitkey)
	 {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where pictypekey = ? and terminalkey = ? and visitkey = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[]{cameratype,termkey,visitkey});
		String localpath="";
		while (cursor.moveToNext()) {
			localpath = cursor.getString(cursor.getColumnIndex("localpath"));
		}
		return localpath;
	}

	@Override
	public List<MstCameraListMStc> getCameraListNoIsuploadAndVisitkey(
			DatabaseHelper helper, String isupload, String visitkey) {


		List<MstCameraListMStc> lst = new ArrayList<MstCameraListMStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where isupload = ? AND sureup = ? AND visitkey = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {
				 isupload, "0" ,visitkey});
		MstCameraListMStc mstCameraListMStc;
		while (cursor.moveToNext()) {
			mstCameraListMStc = new MstCameraListMStc();
			mstCameraListMStc.setCamerakey(cursor.getString(cursor.getColumnIndex("camerakey")));
			mstCameraListMStc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			mstCameraListMStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			mstCameraListMStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstCameraListMStc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
			mstCameraListMStc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
			mstCameraListMStc.setPicname(cursor.getString(cursor.getColumnIndex("picname")));
			mstCameraListMStc.setCameradata(cursor.getString(cursor.getColumnIndex("cameradata")));
			mstCameraListMStc.setImagefileString(cursor.getString(cursor.getColumnIndex("imagefileString")));
			lst.add(mstCameraListMStc); 
		}
		return lst;
	
	}

	@Override
	public List<String> queryVisitKey(SQLiteOpenHelper helper, String isupload) {

		List<String> lst = new ArrayList<String>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where isupload = ? GROUP BY visitkey");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] { "0" });

		while (cursor.moveToNext()) {
			String visitykey = cursor.getString(cursor
					.getColumnIndex("visitkey"));
			lst.add(visitykey);
		}
		return lst;
	}
		
	/**
	 * 查询所有未上传的图片记录
	 * 
	 * @param helper
	 * @param isupload 0未上传 1已上传
	 * @return
	 */
	@Override
	public List<MstCameraListMStc> getCameraListNoIsupload(
			SQLiteOpenHelper helper,  String isupload) {

		List<MstCameraListMStc> lst = new ArrayList<MstCameraListMStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_CAMERAINFO_M where isupload = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {
				 isupload });
		MstCameraListMStc mstCameraListMStc;
		while (cursor.moveToNext()) {
			mstCameraListMStc = new MstCameraListMStc();
			mstCameraListMStc.setCamerakey(cursor.getString(cursor.getColumnIndex("camerakey")));
			mstCameraListMStc.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			mstCameraListMStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			mstCameraListMStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstCameraListMStc.setLocalpath(cursor.getString(cursor.getColumnIndex("localpath")));
			mstCameraListMStc.setPicindex(cursor.getString(cursor.getColumnIndex("picindex")));
			mstCameraListMStc.setPicname(cursor.getString(cursor.getColumnIndex("picname")));
			mstCameraListMStc.setCameradata(cursor.getString(cursor.getColumnIndex("cameradata")));
			lst.add(mstCameraListMStc); 
		}
		return lst;
	}

	@Override
	public void deletePicByCamerakeyATerminal(SQLiteOpenHelper helper, String pictypekey, String terminalkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_CAMERAINFO_M_TEMP WHERE pictypekey = ? AND terminalkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { pictypekey, terminalkey});
		//db.close();
		
	}

	@Override
	public void insertMstCameraiInfoM(SQLiteOpenHelper helper,MstCameraInfoM info) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into MST_CAMERAINFO_M(camerakey, terminalkey,visitkey,pictypekey,picname,localpath,netpath,cameradata,isupload,istakecamera,picindex,pictypename,sureup,imagefileString) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new Object[]{info.getCamerakey(), info.getTerminalkey(),info.getVisitkey(),info.getPictypekey(),info.getPicname(),info.getLocalpath(),info.getNetpath(),info.getCameradata(),info.getIsupload(),info.getIstakecamera(),info.getPicindex(),info.getPictypename(),info.getSureup(),info.getImagefileString()});  
		//db.close(); 
		
	}
	

}
