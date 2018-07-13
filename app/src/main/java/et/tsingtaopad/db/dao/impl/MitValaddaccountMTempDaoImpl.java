package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValaddaccountMTempDao;
import et.tsingtaopad.db.table.MitValaddaccountMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValaddaccountMTempDaoImpl extends BaseDaoImpl<MitValaddaccountMTemp, String> implements MitValaddaccountMTempDao {

    public MitValaddaccountMTempDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValaddaccountMTemp.class);
    }

}
