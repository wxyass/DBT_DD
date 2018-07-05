/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValchecktypeMDao;
import dd.tsingtaopad.db.table.MitValchecktypeM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValchecktypeMDaoImpl extends BaseDaoImpl<MitValchecktypeM, String> implements MitValchecktypeMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValchecktypeMDaoImpl(ConnectionSource connectionSource,
                                   Class<MitValchecktypeM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
