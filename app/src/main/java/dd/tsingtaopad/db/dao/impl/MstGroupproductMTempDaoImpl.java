/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MstGroupproductMTempDao;
import dd.tsingtaopad.db.table.MitValgroupproMTemp;
import dd.tsingtaopad.db.table.MstGroupproductMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MstGroupproductMTempDaoImpl extends BaseDaoImpl<MstGroupproductMTemp, String> implements MstGroupproductMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MstGroupproductMTempDaoImpl(ConnectionSource connectionSource,
                                       Class<MstGroupproductMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


	@Override
	public List<MstGroupproductMTemp> queryMstGroupproductMByTerminalcode(DatabaseHelper helper, String terminalcode) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select mgm.gproductid, mgm.terminalcode, mgm.terminalname, mgm.ifrecstand,mgm.startdate, "
				+ " mgm.enddate,mgm.createusereng,mgm.createdate, mgm.updateusereng,mgm.updatetime,mgm.uploadflag,mgm.padisconsistent "
				+ " from MST_GROUPPRODUCT_M_TEMP mgm where  mgm.terminalcode = ?  order by mgm.createdate DESC limit 1";
		// 1 bdt.line_id=?
		Cursor cursor = db.rawQuery(sql, new String[] {terminalcode});
		List<MstGroupproductMTemp> listvo = new ArrayList<MstGroupproductMTemp>();
		while (cursor.moveToNext()) {
			MstGroupproductMTemp vo = new MstGroupproductMTemp();
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
			vo.setUploadflag(cursor.getString(cursor.getColumnIndex("uploadflag")));
			vo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			listvo.add(vo);
		}
		return listvo;
	}
	@Override
	public List<MitValgroupproMTemp> queryZsMitValgroupproMTempByValterid(DatabaseHelper helper, String valterid) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select *   from MIT_VALGROUPPRO_M_TEMP  where  valterid = ?  ";
		// 1 bdt.line_id=?
		Cursor cursor = db.rawQuery(sql, new String[] {valterid});
		List<MitValgroupproMTemp> listvo = new ArrayList<MitValgroupproMTemp>();
		while (cursor.moveToNext()) {
			MitValgroupproMTemp vo = new MitValgroupproMTemp();
			vo.setId(cursor.getString(cursor.getColumnIndex("id")));
			vo.setGproductid(cursor.getString(cursor.getColumnIndex("gproductid")));
			vo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			vo.setValterid(cursor.getString(cursor.getColumnIndex("valterid")));// 追溯主键
			vo.setValgrouppro(cursor.getString(cursor.getColumnIndex("valgrouppro")));// 产品组合是否达标原值
            vo.setValgroupproflag(cursor.getString(cursor.getColumnIndex("valgroupproflag")));// 产品组合是否达标正确与否
            vo.setValgroupproremark(cursor.getString(cursor.getColumnIndex("valgroupproremark")));// 产品组合是否达标备注
			listvo.add(vo);
		}
		return listvo;
	}

    
}
