package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValaddaccountproMDao;
import et.tsingtaopad.db.table.MitValaddaccountproM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValaddaccountproMDaoImpl extends BaseDaoImpl<MitValaddaccountproM, String> implements MitValaddaccountproMDao {

    public MitValaddaccountproMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValaddaccountproM.class);
    }

}
