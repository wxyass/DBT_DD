package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MitMitMorningmeetingMDao;
import et.tsingtaopad.db.table.MitMorningmeetingM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitMorningmeetingMDaoImpl extends BaseDaoImpl<MitMorningmeetingM, String> implements MitMitMorningmeetingMDao {

    public MitMorningmeetingMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitMorningmeetingM.class);
    }

}
