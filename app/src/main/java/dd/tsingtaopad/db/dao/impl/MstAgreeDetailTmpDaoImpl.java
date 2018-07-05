package dd.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dd.tsingtaopad.db.dao.MstAgreeDetailTmpDao;
import dd.tsingtaopad.db.table.MstAgreeDetailTmp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstAgreeDetailTmpDaoImpl extends BaseDaoImpl<MstAgreeDetailTmp, String> implements MstAgreeDetailTmpDao {

    public MstAgreeDetailTmpDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstAgreeDetailTmp.class);
    }

}
