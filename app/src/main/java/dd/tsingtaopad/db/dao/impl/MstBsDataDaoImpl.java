package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MstBsDataDao;
import dd.tsingtaopad.db.table.MstBsData;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 线路档案主表DAO层实现</br>
 */
public class MstBsDataDaoImpl extends 
            BaseDaoImpl<MstBsData, String> implements MstBsDataDao {

	public MstBsDataDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, MstBsData.class);
	}
	
}
