package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitAgencynumMDao;
import dd.tsingtaopad.db.table.MitAgencynumM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitAgencynumMDaoImpl extends BaseDaoImpl<MitAgencynumM, String> implements MitAgencynumMDao {

    public MitAgencynumMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitAgencynumM.class);
    }

}
