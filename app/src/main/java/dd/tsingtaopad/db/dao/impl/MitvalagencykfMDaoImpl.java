package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitvalagencykfMDao;
import dd.tsingtaopad.db.table.MitValagencykfM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitvalagencykfMDaoImpl extends BaseDaoImpl<MitValagencykfM, String> implements MitvalagencykfMDao {

    public MitvalagencykfMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValagencykfM.class);
    }

}
