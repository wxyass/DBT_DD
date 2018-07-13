/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValcmpotherMDao;
import et.tsingtaopad.db.table.MitValcmpotherM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValcmpotherMDaoImpl extends BaseDaoImpl<MitValcmpotherM, String> implements MitValcmpotherMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValcmpotherMDaoImpl(ConnectionSource connectionSource,
                                  Class<MitValcmpotherM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
