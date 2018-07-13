package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitValagreeMTempDao;
import et.tsingtaopad.db.table.MitValagreeMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValagreeMTempDaoImpl extends BaseDaoImpl<MitValagreeMTemp, String> implements MitValagreeMTempDao {

    public MitValagreeMTempDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValagreeMTemp.class);
    }

}
