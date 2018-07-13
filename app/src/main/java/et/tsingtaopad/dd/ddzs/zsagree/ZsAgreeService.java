package et.tsingtaopad.dd.ddzs.zsagree;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitValagreeMTempDao;
import et.tsingtaopad.db.dao.MitValagreedetailMTempDao;
import et.tsingtaopad.db.table.MitValagreeMTemp;
import et.tsingtaopad.db.table.MitValagreedetailMTemp;
import et.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class ZsAgreeService extends XtShopVisitService {

    private final String TAG = "XtInvoicingService";

    public ZsAgreeService(Context context, Handler handler) {
        super(context, handler);
    }









    /***
     * 终端追溯协议主表 临时表
     * @param valterid
     * @return
     */
    public List<MitValagreeMTemp> queryMitValagreeMTemp(String valterid) {
        List<MitValagreeMTemp> list = new ArrayList<MitValagreeMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValagreeMTempDao mitValsupplyMTempDao = helper.getDao(MitValagreeMTemp.class);
            QueryBuilder<MitValagreeMTemp, String> qb = mitValsupplyMTempDao.queryBuilder();
            Where<MitValagreeMTemp, String> where = qb.where();
            where.eq("valterid", valterid);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /***
     * 终端追溯协议对付信息表 临时表
     * @param valterid
     * @return
     */
    public List<MitValagreedetailMTemp> queryMitValagreedetailMTemp(String valterid) {
        List<MitValagreedetailMTemp> list = new ArrayList<MitValagreedetailMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValagreedetailMTempDao mitValsupplyMTempDao = helper.getDao(MitValagreedetailMTemp.class);
            QueryBuilder<MitValagreedetailMTemp, String> qb = mitValsupplyMTempDao.queryBuilder();
            Where<MitValagreedetailMTemp, String> where = qb.where();
            where.eq("valterid", valterid);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveZsAgree(MitValagreeMTemp mitValagreeMTemp, List<MitValagreedetailMTemp> valagreedetailMTemps) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValagreeMTemp, String> valagreeMTempDao = helper.getMitValagreeMTempDao();
            Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao = helper.getMitValagreedetailMTempDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            valagreeMTempDao.createOrUpdate(mitValagreeMTemp);
            // 无产品的指标
            for (MitValagreedetailMTemp itemStc : valagreedetailMTemps) {
                mitValagreedetailMTempDao.createOrUpdate(itemStc);
            }
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存追溯查指标数据发生异常", e);
            //ViewUtil.sendMsg(context, R.string.checkindex_save_fail);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚追溯查指标数据发生异常", e1);
            }
        }
    }
}
