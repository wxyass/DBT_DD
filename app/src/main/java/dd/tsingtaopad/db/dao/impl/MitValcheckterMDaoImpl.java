/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValcheckterMDao;
import dd.tsingtaopad.db.table.MitValcheckterM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValcheckterMDaoImpl extends BaseDaoImpl<MitValcheckterM, String> implements MitValcheckterMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValcheckterMDaoImpl(ConnectionSource connectionSource,
                                  Class<MitValcheckterM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
