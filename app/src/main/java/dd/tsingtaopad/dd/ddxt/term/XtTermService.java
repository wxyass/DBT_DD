package dd.tsingtaopad.dd.ddxt.term;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitValterMDao;
import dd.tsingtaopad.db.dao.MitVisitMDao;
import dd.tsingtaopad.db.table.MitValcheckterM;
import dd.tsingtaopad.db.table.MitValterM;
import dd.tsingtaopad.db.table.MitVisitM;
import dd.tsingtaopad.db.table.MstVisitM;


/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtTermService {

    private final String TAG = "XtTermService";

    protected Context context;

    public XtTermService(Context context) {
        this.context = context;
    }

    /***
     * 根据终端key, 查找业代的拜访记录
     * @param terminalkey
     * @return
     */
    public List<MstVisitM> getMstVisitMList(String terminalkey) {
        List<MstVisitM> list = new ArrayList<MstVisitM>();
        try {
            Dao<MstVisitM, String> mstVisitMDao = DatabaseHelper.getHelper(context).getMstVisitMDao();
            QueryBuilder<MstVisitM, String> qb = mstVisitMDao.queryBuilder();
            Where<MstVisitM, String> where = qb.where();
            where.eq("terminalkey", terminalkey);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据终端key 获取上传信息(追溯)
     *
     * @param terminalkey 终端key
     * @return
     */
    public List<MitValterM> getZsMitValterM(String terminalkey) {

        List<MitValterM> terminalList = new ArrayList<MitValterM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValterMDao dao = helper.getDao(MitValterM.class);
            List<MitValterM> termlst = dao.queryZsMitValterMData(helper, terminalkey);
            terminalList.addAll(termlst);
        } catch (SQLException e) {
            Log.e(TAG, "获取线路表DAO对象失败", e);
        }
        return terminalList;
    }

    /**
     * 根据终端key 获取上传信息(协同)
     *
     * @param terminalkey 终端key
     * @return
     */
    public List<MitVisitM> getXtMitValterM(String terminalkey) {

        List<MitVisitM> terminalList = new ArrayList<MitVisitM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitVisitMDao dao = helper.getDao(MitVisitM.class);
            List<MitVisitM> termlst = dao.queryXtMitVisitM(helper, terminalkey);
            terminalList.addAll(termlst);
        } catch (SQLException e) {
            Log.e(TAG, "获取线路表DAO对象失败", e);
        }
        return terminalList;
    }

    /***
     * 通过大区ID 获取追溯模板表
     * @param areapid
     * @return
     */
    public List<MitValcheckterM> getValCheckterMList(String areapid) {
        List<MitValcheckterM> list = new ArrayList<MitValcheckterM>();
        try {
            Dao<MitValcheckterM, String> mitValcheckterMDao = DatabaseHelper.getHelper(context).getMitValcheckterMDao();
            QueryBuilder<MitValcheckterM, String> qb = mitValcheckterMDao.queryBuilder();
            Where<MitValcheckterM, String> where = qb.where();
            where.eq("areaid", areapid);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
