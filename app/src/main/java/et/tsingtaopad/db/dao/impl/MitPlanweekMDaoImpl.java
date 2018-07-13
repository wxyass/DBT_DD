package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitPlanweekMDao;
import et.tsingtaopad.db.table.MitPlanweekM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitPlanweekMDaoImpl extends BaseDaoImpl<MitPlanweekM, String> implements MitPlanweekMDao {

    public MitPlanweekMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitPlanweekM.class);
    }

}
