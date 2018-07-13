/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValsupplyMTempDao;
import et.tsingtaopad.db.table.MitValsupplyMTemp;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValsupplyMTempDaoImpl extends BaseDaoImpl<MitValsupplyMTemp, String> implements MitValsupplyMTempDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValsupplyMTempDaoImpl(ConnectionSource connectionSource,
                                    Class<MitValsupplyMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
