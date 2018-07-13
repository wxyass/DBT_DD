package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitCameraInfoMDao;
import et.tsingtaopad.db.table.MitCameraInfoM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitCameraInfoMDaoImpl extends BaseDaoImpl<MitCameraInfoM, String> implements MitCameraInfoMDao {

    public MitCameraInfoMDaoImpl(ConnectionSource
                        connectionSource) throws SQLException {
        super(connectionSource, MitCameraInfoM.class);
    }

}
