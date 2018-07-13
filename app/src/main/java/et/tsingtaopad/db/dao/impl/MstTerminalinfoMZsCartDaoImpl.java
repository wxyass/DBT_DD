package et.tsingtaopad.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import et.tsingtaopad.db.dao.MstTerminalinfoMZsCartDao;
import et.tsingtaopad.db.table.MstTerminalinfoMZsCart;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 追溯终端购物车的DAO层</br>
 */
public class MstTerminalinfoMZsCartDaoImpl extends BaseDaoImpl<MstTerminalinfoMZsCart, String> implements MstTerminalinfoMZsCartDao {

    public MstTerminalinfoMZsCartDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstTerminalinfoMZsCart.class);
    }



}
