/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.dao.MitVisitMDao;
import dd.tsingtaopad.db.table.MitVisitM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MitVisitMDaoImpl extends BaseDaoImpl<MitVisitM, String> implements MitVisitMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitVisitMDaoImpl(ConnectionSource connectionSource,
                            Class<MitVisitM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	/***
	 * 根据终端key 获取未上传记录
	 * @param helper
	 * @param terminalkey  终端key
	 * @return
	 */
	public List<MitVisitM> queryXtMitVisitM(SQLiteOpenHelper helper, String terminalkey) {
		List<MitVisitM> lst = new ArrayList<MitVisitM>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MIT_VISIT_M where terminalkey = ? and padisconsistent = '0' ");

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
		MitVisitM item;
		while (cursor.moveToNext()) {
			item = new MitVisitM();
			item.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
			item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			lst.add(item);
		}
		return lst;
	}


}
