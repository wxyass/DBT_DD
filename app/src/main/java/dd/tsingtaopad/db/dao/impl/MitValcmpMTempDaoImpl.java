/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValcmpMTempDao;
import dd.tsingtaopad.db.table.MitValcmpMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValcmpMTempDaoImpl extends BaseDaoImpl<MitValcmpMTemp, String> implements MitValcmpMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValcmpMTempDaoImpl(ConnectionSource connectionSource,
                                 Class<MitValcmpMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
