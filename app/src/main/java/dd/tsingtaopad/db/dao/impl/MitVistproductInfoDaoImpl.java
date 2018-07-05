package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitVistproductInfoDao;
import dd.tsingtaopad.db.table.MitVistproductInfo;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitVistproductInfoDaoImpl extends BaseDaoImpl<MitVistproductInfo, String> implements MitVistproductInfoDao {

	public MitVistproductInfoDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, MitVistproductInfo.class);
	}


}
