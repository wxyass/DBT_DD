package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitRepairMDao;
import dd.tsingtaopad.db.table.MitRepairM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitRepairMDaoImpl extends BaseDaoImpl<MitRepairM, String> implements MitRepairMDao {

    public MitRepairMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitRepairM.class);
    }

}
