package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValagreeMDao;
import et.tsingtaopad.db.table.MitValagreeM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValagreeMDaoImpl extends BaseDaoImpl<MitValagreeM, String> implements MitValagreeMDao {

    public MitValagreeMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValagreeM.class);
    }

}
