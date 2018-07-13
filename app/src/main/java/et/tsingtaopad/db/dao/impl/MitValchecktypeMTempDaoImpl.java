/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValchecktypeMTempDao;
import et.tsingtaopad.db.table.MitValchecktypeMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValchecktypeMTempDaoImpl extends BaseDaoImpl<MitValchecktypeMTemp, String> implements MitValchecktypeMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValchecktypeMTempDaoImpl(ConnectionSource connectionSource,
                                       Class<MitValchecktypeMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
