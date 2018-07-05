/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MstVisitMTempDao;
import dd.tsingtaopad.db.table.MstVisitMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MstVisitMTempDaoImpl extends BaseDaoImpl<MstVisitMTemp, String> implements MstVisitMTempDao {


	public MstVisitMTempDaoImpl(ConnectionSource connectionSource,Class<MstVisitMTemp> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}


}
