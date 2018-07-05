/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValcmpotherMTempDao;
import dd.tsingtaopad.db.table.MitValcmpotherMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValcmpotherMTempDaoImpl extends BaseDaoImpl<MitValcmpotherMTemp, String> implements MitValcmpotherMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValcmpotherMTempDaoImpl(ConnectionSource connectionSource,
                                      Class<MitValcmpotherMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
