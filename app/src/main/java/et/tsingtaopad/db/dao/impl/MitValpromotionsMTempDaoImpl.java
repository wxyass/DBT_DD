/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValpromotionsMTempDao;
import et.tsingtaopad.db.table.MitValpromotionsMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValpromotionsMTempDaoImpl extends BaseDaoImpl<MitValpromotionsMTemp, String> implements MitValpromotionsMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValpromotionsMTempDaoImpl(ConnectionSource connectionSource,
                                        Class<MitValpromotionsMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
