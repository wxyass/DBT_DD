/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValsupplyMDao;
import dd.tsingtaopad.db.table.MitValsupplyM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValsupplyMDaoImpl extends BaseDaoImpl<MitValsupplyM, String> implements MitValsupplyMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValsupplyMDaoImpl(ConnectionSource connectionSource,
                                Class<MitValsupplyM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
