/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValcheckitemMTempDao;
import et.tsingtaopad.db.table.MitValcheckitemMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValcheckitemMTempDaoImpl extends BaseDaoImpl<MitValcheckitemMTemp, String> implements MitValcheckitemMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValcheckitemMTempDaoImpl(ConnectionSource connectionSource,
                                       Class<MitValcheckitemMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
