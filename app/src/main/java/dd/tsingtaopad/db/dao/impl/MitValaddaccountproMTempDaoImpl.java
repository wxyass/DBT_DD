package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValaddaccountproMTempDao;
import dd.tsingtaopad.db.table.MitValaddaccountproMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValaddaccountproMTempDaoImpl extends BaseDaoImpl<MitValaddaccountproMTemp, String> implements MitValaddaccountproMTempDao {

    public MitValaddaccountproMTempDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValaddaccountproMTemp.class);
    }

}
