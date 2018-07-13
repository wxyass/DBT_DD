/**
 * 
 */
package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValgroupproMDao;
import et.tsingtaopad.db.table.MitValgroupproM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:
 */
public class MitValgroupproMDaoImpl extends BaseDaoImpl<MitValgroupproM, String> implements MitValgroupproMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MitValgroupproMDaoImpl(ConnectionSource connectionSource,
                                  Class<MitValgroupproM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
