/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValpicMTempDao;
import et.tsingtaopad.db.table.MitValpicMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValpicMTempDaoImpl extends BaseDaoImpl<MitValpicMTemp, String> implements MitValpicMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValpicMTempDaoImpl(ConnectionSource connectionSource,
                                 Class<MitValpicMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
