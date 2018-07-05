package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValagreedetailMTempDao;
import dd.tsingtaopad.db.table.MitValagreedetailMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValagreedetailMTempDaoImpl extends BaseDaoImpl<MitValagreedetailMTemp, String> implements MitValagreedetailMTempDao {

    public MitValagreedetailMTempDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValagreedetailMTemp.class);
    }

}
