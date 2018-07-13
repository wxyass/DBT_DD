package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitTerminalMDao;
import et.tsingtaopad.db.table.MitTerminalM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitTerminalMDaoImpl extends BaseDaoImpl<MitTerminalM, String> implements MitTerminalMDao {

    public MitTerminalMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitTerminalM.class);
    }

}
