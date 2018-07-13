package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MstTerminalinfoMTempDao;
import et.tsingtaopad.db.table.MstTerminalinfoMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端表的DAO层</br>
 */
public class MstTerminalinfoMTempDaoImpl extends BaseDaoImpl<MstTerminalinfoMTemp, String> implements MstTerminalinfoMTempDao {

    public MstTerminalinfoMTempDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstTerminalinfoMTemp.class);
    }



}
