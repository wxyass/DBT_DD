package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitPlandaydetailMDao;
import et.tsingtaopad.db.table.MitPlandaydetailM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitPlandaydetailMDaoImpl extends BaseDaoImpl<MitPlandaydetailM, String> implements MitPlandaydetailMDao {

    public MitPlandaydetailMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitPlandaydetailM.class);
    }

}
