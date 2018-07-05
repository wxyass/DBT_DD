package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MstAgreeTmpDao;
import dd.tsingtaopad.db.table.MstAgreeTmp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstAgreeTmpDaoImpl extends BaseDaoImpl<MstAgreeTmp, String> implements MstAgreeTmpDao {

    public MstAgreeTmpDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstAgreeTmp.class);
    }

}
