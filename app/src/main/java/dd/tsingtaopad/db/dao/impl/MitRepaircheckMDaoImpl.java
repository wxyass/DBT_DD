package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MitRepaircheckMDao;
import dd.tsingtaopad.db.table.MitRepaircheckM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitRepaircheckMDaoImpl extends BaseDaoImpl<MitRepaircheckM, String> implements MitRepaircheckMDao {

    public MitRepaircheckMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitRepaircheckM.class);
    }

}
