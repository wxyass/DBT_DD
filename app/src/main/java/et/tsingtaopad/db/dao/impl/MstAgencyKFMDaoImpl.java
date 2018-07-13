package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

import et.tsingtaopad.db.dao.MstAgencyKFMDao;
import et.tsingtaopad.db.table.MstAgencyKFM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstAgencyKFMDaoImpl extends 
            BaseDaoImpl<MstAgencyKFM, String> implements MstAgencyKFMDao {

    public MstAgencyKFMDaoImpl(ConnectionSource
                        connectionSource) throws SQLException {
        super(connectionSource, MstAgencyKFM.class);
    }

    /**
	 * 查询经销商开发表所有记录
	 * 
	 * @param helper
	 * @param status 0有效经销商  1无效经销商
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryMstAgencyKFMLst(SQLiteOpenHelper helper,String status) {
		ArrayList<MstAgencyKFM> lst = new ArrayList<MstAgencyKFM>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_AGENCYKF_M where status = ?");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {
			status });
		MstAgencyKFM mstAgencyKFM;
		while (cursor.moveToNext()) {
			mstAgencyKFM = new MstAgencyKFM();
			
			mstAgencyKFM.setAgencykfkey(cursor.getString(cursor.getColumnIndex("agencykfkey")));
			mstAgencyKFM.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			mstAgencyKFM.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
			mstAgencyKFM.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstAgencyKFM.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			mstAgencyKFM.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstAgencyKFM.setArea(cursor.getString(cursor.getColumnIndex("area")));
			mstAgencyKFM.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			mstAgencyKFM.setCarnum(cursor.getString(cursor.getColumnIndex("carnum")));
			mstAgencyKFM.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
			mstAgencyKFM.setKfdate(cursor.getString(cursor.getColumnIndex("kfdate")));
			mstAgencyKFM.setStatus(cursor.getString(cursor.getColumnIndex("status")));
			mstAgencyKFM.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			mstAgencyKFM.setCreateuser(cursor.getString(cursor.getColumnIndex("createuser")));
			mstAgencyKFM.setUpdatedate(cursor.getString(cursor.getColumnIndex("updatedate")));
			mstAgencyKFM.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
			mstAgencyKFM.setUpload(cursor.getString(cursor.getColumnIndex("upload")));
			// 新加6个字段
			mstAgencyKFM.setPersion(cursor.getString(cursor.getColumnIndex("persion")));
			mstAgencyKFM.setBusiness(cursor.getString(cursor.getColumnIndex("business")));
			mstAgencyKFM.setIsone(cursor.getInt(cursor.getColumnIndex("isone")));
			mstAgencyKFM.setCoverterms(cursor.getString(cursor.getColumnIndex("coverterms")));
			mstAgencyKFM.setSupplyterms(cursor.getString(cursor.getColumnIndex("supplyterms")));
			mstAgencyKFM.setPassdate(cursor.getString(cursor.getColumnIndex("passdate")));
			
			//lst.add(mstAgencyKFM);
			lst.add(0,mstAgencyKFM);
		}
		return lst;
	}
    /**
	 * 查询二级区域下的 经销商开发
	 *
	 * @param helper
	 * @param areaid 二级区域ID
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryZsMstAgencyKFMLst(SQLiteOpenHelper helper,String areaid) {
		ArrayList<MstAgencyKFM> lst = new ArrayList<MstAgencyKFM>();

		StringBuffer buffer = new StringBuffer();
		buffer.append(" select * from MST_AGENCYKF_M visita " +
				"   left join mst_grid_m g on g.gridkey = visita.gridkey" +
				"   where g.areaid= ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {areaid });
		MstAgencyKFM mstAgencyKFM;
		while (cursor.moveToNext()) {
			mstAgencyKFM = new MstAgencyKFM();

			mstAgencyKFM.setAgencykfkey(cursor.getString(cursor.getColumnIndex("agencykfkey")));
			mstAgencyKFM.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			mstAgencyKFM.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
			mstAgencyKFM.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstAgencyKFM.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			mstAgencyKFM.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstAgencyKFM.setArea(cursor.getString(cursor.getColumnIndex("area")));
			mstAgencyKFM.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			mstAgencyKFM.setCarnum(cursor.getString(cursor.getColumnIndex("carnum")));
			mstAgencyKFM.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
			mstAgencyKFM.setKfdate(cursor.getString(cursor.getColumnIndex("kfdate")));
			mstAgencyKFM.setStatus(cursor.getString(cursor.getColumnIndex("status")));
			mstAgencyKFM.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			mstAgencyKFM.setCreateuser(cursor.getString(cursor.getColumnIndex("createuser")));
			mstAgencyKFM.setUpdatedate(cursor.getString(cursor.getColumnIndex("updatedate")));
			mstAgencyKFM.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
			mstAgencyKFM.setUpload(cursor.getString(cursor.getColumnIndex("upload")));
			// 新加6个字段
			mstAgencyKFM.setPersion(cursor.getString(cursor.getColumnIndex("persion")));
			mstAgencyKFM.setBusiness(cursor.getString(cursor.getColumnIndex("business")));
			mstAgencyKFM.setIsone(cursor.getInt(cursor.getColumnIndex("isone")));
			mstAgencyKFM.setCoverterms(cursor.getString(cursor.getColumnIndex("coverterms")));
			mstAgencyKFM.setSupplyterms(cursor.getString(cursor.getColumnIndex("supplyterms")));
			mstAgencyKFM.setPassdate(cursor.getString(cursor.getColumnIndex("passdate")));

			//lst.add(mstAgencyKFM);
			lst.add(0,mstAgencyKFM);
		}
		return lst;
	}

	/**
	 * 更新一条经销商开发记录
	 * (根据经销商开发主键,更新上传状态为1(已上传))
	 * @param helper
	 * @param agencykfkey 经销商开发主键
	 */
	@Override
	public void updataUploadbyAgencykfkey(SQLiteOpenHelper helper,
			String agencykfkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_AGENCYKF_M SET upload = 1 where agencykfkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { agencykfkey });
	}

	/**
	 * 删除一条经销商开发记录
	 * 
	 * @param helper
	 * @param agencykfkey
	 */
	@Override
	public void deleteAgencykfRecord(SQLiteOpenHelper helper, String agencykfkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_AGENCYKF_M WHERE agencykfkey = ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { agencykfkey });
	}
	
	
	/**
	 * 根据经销商名称 模糊查询
	 * 
	 * @param helper
	 * @param AgencyName
	 * @return
	 */
	@Override
	public ArrayList<MstAgencyKFM> queryAgencykfbyAgencyName(
			SQLiteOpenHelper helper, String AgencyName) {
		ArrayList<MstAgencyKFM> lst = new ArrayList<MstAgencyKFM>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_AGENCYKF_M where status = 0 AND agencyname ");
		buffer.append(" LIKE '%" + AgencyName + "%' ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), null);
		MstAgencyKFM mstAgencyKFM;
		while (cursor.moveToNext()) {
			
			mstAgencyKFM = new MstAgencyKFM();
			
			mstAgencyKFM.setAgencykfkey(cursor.getString(cursor.getColumnIndex("agencykfkey")));
			mstAgencyKFM.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			mstAgencyKFM.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
			mstAgencyKFM.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstAgencyKFM.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			mstAgencyKFM.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstAgencyKFM.setArea(cursor.getString(cursor.getColumnIndex("area")));
			mstAgencyKFM.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			mstAgencyKFM.setCarnum(cursor.getString(cursor.getColumnIndex("carnum")));
			mstAgencyKFM.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
			mstAgencyKFM.setKfdate(cursor.getString(cursor.getColumnIndex("kfdate")));
			mstAgencyKFM.setStatus(cursor.getString(cursor.getColumnIndex("status")));
			mstAgencyKFM.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			mstAgencyKFM.setCreateuser(cursor.getString(cursor.getColumnIndex("createuser")));
			mstAgencyKFM.setUpdatedate(cursor.getString(cursor.getColumnIndex("updatedate")));
			mstAgencyKFM.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
			//mstAgencyKFM.setUpload(cursor.getString(cursor.getColumnIndex("upload")));
			//↓---经销商开发 新加字段 模糊搜索------------------------------------------------------
			mstAgencyKFM.setPersion(cursor.getString(cursor.getColumnIndex("persion")));
			mstAgencyKFM.setBusiness(cursor.getString(cursor.getColumnIndex("business")));
			mstAgencyKFM.setIsone(cursor.getInt(cursor.getColumnIndex("isone")));
			mstAgencyKFM.setCoverterms(cursor.getString(cursor.getColumnIndex("coverterms")));
			mstAgencyKFM.setSupplyterms(cursor.getString(cursor.getColumnIndex("supplyterms")));
			mstAgencyKFM.setPassdate(cursor.getString(cursor.getColumnIndex("passdate")));
			//↑---经销商开发 新加字段 模糊搜索------------------------------------------------------
			
			lst.add(mstAgencyKFM);
		}
		return lst;
	
	}

	@Override
	public void updatastatusbyAgencykfkey(SQLiteOpenHelper helper,
			String agencykfkey) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_AGENCYKF_M SET status = 1 where agencykfkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { agencykfkey });
	}
	
	/**
	 * 根据是否上传状态 查询所有未上传表记录
	 * 
	 * @param helper
	 * @param upload 0未上传    1,null已上传
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryMstAgencyKFMLstbynotupload(SQLiteOpenHelper helper,String upload) {
		
		ArrayList<MstAgencyKFM> lst = new ArrayList<MstAgencyKFM>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_AGENCYKF_M where upload = ?");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {
			upload });
		MstAgencyKFM mstAgencyKFM;
		while (cursor.moveToNext()) {
			mstAgencyKFM = new MstAgencyKFM();
			
			mstAgencyKFM.setAgencykfkey(cursor.getString(cursor.getColumnIndex("agencykfkey")));
			mstAgencyKFM.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			mstAgencyKFM.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
			mstAgencyKFM.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstAgencyKFM.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			mstAgencyKFM.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstAgencyKFM.setArea(cursor.getString(cursor.getColumnIndex("area")));
			mstAgencyKFM.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			mstAgencyKFM.setCarnum(cursor.getString(cursor.getColumnIndex("carnum")));
			mstAgencyKFM.setProductname(cursor.getString(cursor.getColumnIndex("productname")));
			mstAgencyKFM.setKfdate(cursor.getString(cursor.getColumnIndex("kfdate")));
			mstAgencyKFM.setStatus(cursor.getString(cursor.getColumnIndex("status")));
			mstAgencyKFM.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			mstAgencyKFM.setCreateuser(cursor.getString(cursor.getColumnIndex("createuser")));
			mstAgencyKFM.setUpdatedate(cursor.getString(cursor.getColumnIndex("updatedate")));
			mstAgencyKFM.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
			mstAgencyKFM.setUpload(cursor.getString(cursor.getColumnIndex("upload")));
			
			//↓---经销商开发 新加字段 离线上传------------------------------------------------------
			mstAgencyKFM.setPersion(cursor.getString(cursor.getColumnIndex("persion")));
			mstAgencyKFM.setBusiness(cursor.getString(cursor.getColumnIndex("business")));
			mstAgencyKFM.setIsone(cursor.getInt(cursor.getColumnIndex("isone")));
			mstAgencyKFM.setCoverterms(cursor.getString(cursor.getColumnIndex("coverterms")));
			mstAgencyKFM.setSupplyterms(cursor.getString(cursor.getColumnIndex("supplyterms")));
			mstAgencyKFM.setPassdate(cursor.getString(cursor.getColumnIndex("passdate")));
			//↑---经销商开发 新加字段 离线上传------------------------------------------------------
			
			//lst.add(mstAgencyKFM);
			lst.add(0,mstAgencyKFM);
		}
		return lst;
	}

	
}
