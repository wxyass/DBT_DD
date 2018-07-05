package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.dao.MstPictypeMDao;
import dd.tsingtaopad.db.table.MitValpicMTemp;
import dd.tsingtaopad.db.table.MstPictypeM;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstPictypeMDaoImpl extends BaseDaoImpl<MstPictypeM, String>
		implements MstPictypeMDao {

	public MstPictypeMDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, MstPictypeM.class);
	}

	/**
	 * 查询图片类型表中所有记录,用于初始化要拍几张图片
	 */
	@Override
	public List<CameraInfoStc> queryAllPictype(SQLiteOpenHelper helper) {
		List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select areaid,pictypekey,pictypename,focus,orderno from MST_PICTYPE_M ORDER BY orderno");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {});
		CameraInfoStc pictypeDataStc;
		while (cursor.moveToNext()) {
			pictypeDataStc = new CameraInfoStc();
			pictypeDataStc.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			pictypeDataStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			pictypeDataStc.setPictypename(cursor.getString(cursor.getColumnIndex("pictypename")));
			pictypeDataStc.setFocus(cursor.getString(cursor.getColumnIndex("focus")));
			pictypeDataStc.setOrderno(cursor.getString(cursor.getColumnIndex("orderno")));
			lst.add(pictypeDataStc);
		}
		return lst;
	}

	/**
	 * 查询图片类型表中所有记录,用于追溯初始化要拍几张图片
	 */
	@Override
	public List<MitValpicMTemp> queryZsAllPictype(SQLiteOpenHelper helper) {
		List<MitValpicMTemp> lst = new ArrayList<MitValpicMTemp>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select areaid,pictypekey,pictypename,focus,orderno from MST_PICTYPE_M ORDER BY orderno");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {});
		MitValpicMTemp valpicMTemp;
		while (cursor.moveToNext()) {
			valpicMTemp = new MitValpicMTemp();
			//pictypeDataStc.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			valpicMTemp.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			valpicMTemp.setPictypename(cursor.getString(cursor.getColumnIndex("pictypename")));
			//pictypeDataStc.setFocus(cursor.getString(cursor.getColumnIndex("focus")));
			//pictypeDataStc.setOrderno(cursor.getString(cursor.getColumnIndex("orderno")));
			lst.add(valpicMTemp);
		}
		return lst;
	}

	/**
	 * 查询图片类型表中所有记录,用于初始化要拍几张图片
	 */
	@Override
	public List<CameraInfoStc> queryPictypeM(SQLiteOpenHelper helper) {
		List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select areaid,pictypekey,pictypename,focus,orderno from MST_PICTYPE_M ORDER BY orderno");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {});
		CameraInfoStc pictypeDataStc;
		while (cursor.moveToNext()) {
			pictypeDataStc = new CameraInfoStc();
			pictypeDataStc.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			pictypeDataStc.setPictypekey(cursor.getString(cursor.getColumnIndex("pictypekey")));
			pictypeDataStc.setPictypename(cursor.getString(cursor.getColumnIndex("pictypename")));
			pictypeDataStc.setFocus(cursor.getString(cursor.getColumnIndex("focus")));
			pictypeDataStc.setOrderno(cursor.getString(cursor.getColumnIndex("orderno")));
			lst.add(pictypeDataStc);
		}
		return lst;
	}
}
