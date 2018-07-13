/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstGroupproductMDao;
import et.tsingtaopad.db.table.MstGroupproductM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MstGroupproductMDaoImpl extends BaseDaoImpl<MstGroupproductM, String> implements MstGroupproductMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MstGroupproductMDaoImpl(ConnectionSource connectionSource,
			Class<MstGroupproductM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	@Override
	public List<MstGroupproductM> queryMstGroupproductMByCreatedate(DatabaseHelper helper, String terminalcode, String startdate) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select mgm.gproductid, mgm.terminalcode, mgm.terminalname, mgm.ifrecstand,mgm.startdate, "
				+ " mgm.enddate,mgm.createusereng,mgm.createdate, mgm.updateusereng,mgm.updatetime,mgm.uploadflag,mgm.padisconsistent "
				+ " from MST_GROUPPRODUCT_M mgm where  mgm.terminalcode = ? and mgm.startdate <= ? order by mgm.createdate DESC limit 1";
		// 1 bdt.line_id=?
		Cursor cursor = db.rawQuery(sql, new String[] {terminalcode,startdate});
		List<MstGroupproductM> listvo = new ArrayList<MstGroupproductM>();
		while (cursor.moveToNext()) {
			MstGroupproductM vo = new MstGroupproductM();
			vo.setGproductid(cursor.getString(cursor.getColumnIndex("gproductid")));
			vo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			vo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			vo.setIfrecstand(cursor.getString(cursor.getColumnIndex("ifrecstand")));
			vo.setStartdate(cursor.getString(cursor.getColumnIndex("startdate")));
			vo.setEnddate(cursor.getString(cursor.getColumnIndex("enddate")));
			vo.setCreateusereng(cursor.getString(cursor.getColumnIndex("createusereng")));
			vo.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			vo.setUpdateusereng(cursor.getString(cursor.getColumnIndex("updateusereng")));
			vo.setUpdatetime(cursor.getString(cursor.getColumnIndex("updatetime")));
			vo.setUpdateusereng(cursor.getString(cursor.getColumnIndex("updateusereng")));
			vo.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadflag")));
			vo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			listvo.add(vo);
		}
		return listvo;
	}

	/**
	 * 用于追溯 根据terminalcode查找数据
	 * @param helper
	 * @param terminalcode
	 * @return
	 */
	@Override
	public List<MstGroupproductM> queryZsMstGroupproductMByCreatedate(DatabaseHelper helper, String terminalcode) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select mgm.gproductid, mgm.terminalcode, mgm.terminalname, mgm.ifrecstand,mgm.startdate, "
				+ " mgm.enddate,mgm.createusereng,mgm.createdate, mgm.updateusereng,mgm.updatetime,mgm.uploadflag,mgm.padisconsistent "
				+ " from MST_GROUPPRODUCT_M mgm where  mgm.terminalcode = ?  order by mgm.createdate DESC limit 1";
		// 1 bdt.line_id=?
		Cursor cursor = db.rawQuery(sql, new String[] {terminalcode});
		List<MstGroupproductM> listvo = new ArrayList<MstGroupproductM>();
		while (cursor.moveToNext()) {
			MstGroupproductM vo = new MstGroupproductM();
			vo.setGproductid(cursor.getString(cursor.getColumnIndex("gproductid")));
			vo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			vo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			vo.setIfrecstand(cursor.getString(cursor.getColumnIndex("ifrecstand")));
			vo.setStartdate(cursor.getString(cursor.getColumnIndex("startdate")));
			vo.setEnddate(cursor.getString(cursor.getColumnIndex("enddate")));
			vo.setCreateusereng(cursor.getString(cursor.getColumnIndex("createusereng")));
			vo.setCreatedate(cursor.getString(cursor.getColumnIndex("createdate")));
			vo.setUpdateusereng(cursor.getString(cursor.getColumnIndex("updateusereng")));
			vo.setUpdatetime(cursor.getString(cursor.getColumnIndex("updatetime")));
			vo.setUpdateusereng(cursor.getString(cursor.getColumnIndex("updateusereng")));
			vo.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadflag")));
			vo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			listvo.add(vo);
		}
		return listvo;
	}

    
}
