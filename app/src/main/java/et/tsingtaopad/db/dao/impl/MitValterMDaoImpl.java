/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.dao.MitValterMDao;
import et.tsingtaopad.db.table.MitValterM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValterMDaoImpl extends BaseDaoImpl<MitValterM, String> implements MitValterMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValterMDaoImpl(ConnectionSource connectionSource,
                             Class<MitValterM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	/***
	 * 根据终端key 获取未上传记录
	 * @param helper
	 * @param terminalkey  终端key
	 * @return
	 */
	public List<MitValterM> queryZsMitValterMData(SQLiteOpenHelper helper, String terminalkey) {
		List<MitValterM> lst = new ArrayList<MitValterM>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MIT_VALTER_M where terminalkey = ? and padisconsistent = '0' ");

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
		MitValterM item;
		while (cursor.moveToNext()) {
			item = new MitValterM();
			item.setId(cursor.getString(cursor.getColumnIndex("id")));
			item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			lst.add(item);
		}
		return lst;
	}


}
