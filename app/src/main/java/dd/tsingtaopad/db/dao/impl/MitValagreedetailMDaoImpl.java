package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitValagreedetailMDao;
import dd.tsingtaopad.db.table.MitValagreedetailM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValagreedetailMDaoImpl extends BaseDaoImpl<MitValagreedetailM, String> implements MitValagreedetailMDao {

    public MitValagreedetailMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValagreedetailM.class);
    }

}
