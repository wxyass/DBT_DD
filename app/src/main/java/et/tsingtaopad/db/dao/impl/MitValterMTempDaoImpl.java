/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValterMTempDao;
import et.tsingtaopad.db.table.MitValterMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValterMTempDaoImpl extends BaseDaoImpl<MitValterMTemp, String> implements MitValterMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValterMTempDaoImpl(ConnectionSource connectionSource,
                                 Class<MitValterMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
