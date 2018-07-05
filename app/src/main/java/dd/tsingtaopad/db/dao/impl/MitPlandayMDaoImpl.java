package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitPlandayMDao;
import dd.tsingtaopad.db.table.MitPlandayM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitPlandayMDaoImpl extends BaseDaoImpl<MitPlandayM, String> implements MitPlandayMDao {

    public MitPlandayMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitPlandayM.class);
    }

}
