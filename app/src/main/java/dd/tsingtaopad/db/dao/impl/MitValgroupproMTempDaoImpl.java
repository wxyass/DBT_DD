/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValgroupproMTempDao;
import dd.tsingtaopad.db.table.MitValgroupproMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValgroupproMTempDaoImpl extends BaseDaoImpl<MitValgroupproMTemp, String> implements MitValgroupproMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValgroupproMTempDaoImpl(ConnectionSource connectionSource,
                                      Class<MitValgroupproMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}
}
