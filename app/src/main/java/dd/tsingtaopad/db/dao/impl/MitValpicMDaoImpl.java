/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValpicMDao;
import dd.tsingtaopad.db.table.MitValpicM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValpicMDaoImpl extends BaseDaoImpl<MitValpicM, String> implements MitValpicMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValpicMDaoImpl(ConnectionSource connectionSource,
                             Class<MitValpicM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
