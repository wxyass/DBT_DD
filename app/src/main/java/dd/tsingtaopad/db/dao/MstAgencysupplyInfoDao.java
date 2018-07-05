package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MstAgencysupplyInfo;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public interface MstAgencysupplyInfoDao extends Dao<MstAgencysupplyInfo, String> {
	
	public List<MstAgencysupplyInfo> agencysupply(SQLiteOpenHelper helper, String terminalkey);

	
}
