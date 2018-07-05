/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitGroupproductMDao;
import dd.tsingtaopad.db.table.MitGroupproductM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MitGroupproductMDaoImpl extends BaseDaoImpl<MitGroupproductM, String> implements MitGroupproductMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitGroupproductMDaoImpl(ConnectionSource connectionSource,
                                   Class<MitGroupproductM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
