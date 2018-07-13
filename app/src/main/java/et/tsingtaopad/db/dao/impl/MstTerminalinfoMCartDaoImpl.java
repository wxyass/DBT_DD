package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MstTerminalinfoMCartDao;
import et.tsingtaopad.db.table.MstTerminalinfoMCart;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 协同终端购物车的DAO层</br>
 */
public class MstTerminalinfoMCartDaoImpl extends BaseDaoImpl<MstTerminalinfoMCart, String> implements MstTerminalinfoMCartDao {

    public MstTerminalinfoMCartDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstTerminalinfoMCart.class);
    }



}
