package et.tsingtaopad.dd.ddxt.shopvisit;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitRepairterMDao;
import et.tsingtaopad.db.dao.MitValaddaccountMDao;
import et.tsingtaopad.db.dao.MitValcmpotherMTempDao;
import et.tsingtaopad.db.dao.MitValterMTempDao;
import et.tsingtaopad.db.dao.MstAgencysupplyInfoDao;
import et.tsingtaopad.db.dao.MstCameraiInfoMDao;
import et.tsingtaopad.db.dao.MstCheckexerecordInfoDao;
import et.tsingtaopad.db.dao.MstGroupproductMDao;
import et.tsingtaopad.db.dao.MstGroupproductMTempDao;
import et.tsingtaopad.db.dao.MstPictypeMDao;
import et.tsingtaopad.db.dao.MstPromotionsmDao;
import et.tsingtaopad.db.dao.MstTermLedgerInfoDao;
import et.tsingtaopad.db.dao.MstTerminalinfoMCartDao;
import et.tsingtaopad.db.dao.MstTerminalinfoMDao;
import et.tsingtaopad.db.dao.MstTerminalinfoMTempDao;
import et.tsingtaopad.db.dao.MstTerminalinfoMZsCartDao;
import et.tsingtaopad.db.dao.MstVisitMDao;
import et.tsingtaopad.db.dao.MstVisitMTempDao;
import et.tsingtaopad.db.dao.MstVistproductInfoDao;
import et.tsingtaopad.db.table.MitRepairM;
import et.tsingtaopad.db.table.MitRepaircheckM;
import et.tsingtaopad.db.table.MitRepairterM;
import et.tsingtaopad.db.table.MitValaddaccountM;
import et.tsingtaopad.db.table.MitValaddaccountMTemp;
import et.tsingtaopad.db.table.MitValaddaccountproMTemp;
import et.tsingtaopad.db.table.MitValagreeMTemp;
import et.tsingtaopad.db.table.MitValagreedetailMTemp;
import et.tsingtaopad.db.table.MitValcheckitemMTemp;
import et.tsingtaopad.db.table.MitValchecktypeMTemp;
import et.tsingtaopad.db.table.MitValcmpMTemp;
import et.tsingtaopad.db.table.MitValcmpotherMTemp;
import et.tsingtaopad.db.table.MitValgroupproMTemp;
import et.tsingtaopad.db.table.MitValpicMTemp;
import et.tsingtaopad.db.table.MitValpromotionsMTemp;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.db.table.MitValterMTemp;
import et.tsingtaopad.db.table.MstAgencysupplyInfo;
import et.tsingtaopad.db.table.MstAgencysupplyInfoTemp;
import et.tsingtaopad.db.table.MstAgreeDetailTmp;
import et.tsingtaopad.db.table.MstAgreeTmp;
import et.tsingtaopad.db.table.MstCameraInfoM;
import et.tsingtaopad.db.table.MstCameraInfoMTemp;
import et.tsingtaopad.db.table.MstCheckexerecordInfo;
import et.tsingtaopad.db.table.MstCheckexerecordInfoTemp;
import et.tsingtaopad.db.table.MstCmpsupplyInfo;
import et.tsingtaopad.db.table.MstCmpsupplyInfoTemp;
import et.tsingtaopad.db.table.MstCollectionexerecordInfo;
import et.tsingtaopad.db.table.MstCollectionexerecordInfoTemp;
import et.tsingtaopad.db.table.MstGroupproductM;
import et.tsingtaopad.db.table.MstGroupproductMTemp;
import et.tsingtaopad.db.table.MstPromotermInfo;
import et.tsingtaopad.db.table.MstPromotermInfoTemp;
import et.tsingtaopad.db.table.MstPromotionsM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstTermLedgerInfo;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.db.table.MstTerminalinfoMCart;
import et.tsingtaopad.db.table.MstTerminalinfoMTemp;
import et.tsingtaopad.db.table.MstTerminalinfoMZsCart;
import et.tsingtaopad.db.table.MstVisitM;
import et.tsingtaopad.db.table.MstVisitMTemp;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.db.table.MstVistproductInfoTemp;
import et.tsingtaopad.db.table.PadCheckaccomplishInfo;
import et.tsingtaopad.dd.dddealplan.domain.DealStc;
import et.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;
import et.tsingtaopad.dd.ddxt.checking.domain.XtCheckIndexCalculateStc;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndex;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProIndexValue;
import et.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import et.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzItemIndex;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzLedger;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexCalculateStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexQuicklyStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;


/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtShopVisitService {

    private final String TAG = "XtShopVisitService";

    protected Context context;
    protected Handler handler;

    private String prevVisitId;// 获取上次拜访主键

    private String prevVisitDate; // 上次拜访日期

    public XtShopVisitService(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    /***
     * 查询图片类型表中所有记录 协同用
     */
    public List<CameraInfoStc> queryPictypeMAll() {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        List<CameraInfoStc> valueLst = new ArrayList<CameraInfoStc>();
        try {

            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstPictypeMDao dao = (MstPictypeMDao) helper.getMstpictypeMDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            valueLst = dao.queryAllPictype(helper);
            connection.commit(null);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "查询图片类型表中所有记录", e);
        }
        return valueLst;
    }

    /***
     * 查询图片类型表中所有记录 用于追溯
     */
    public List<MitValpicMTemp> queryZsPictypeMAll() {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        List<MitValpicMTemp> valueLst = new ArrayList<MitValpicMTemp>();
        try {

            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstPictypeMDao dao = (MstPictypeMDao) helper.getMstpictypeMDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            valueLst = dao.queryZsAllPictype(helper);
            connection.commit(null);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "查询图片类型表中所有记录", e);
        }
        return valueLst;
    }

    /**
     * 获取当天拍照记录 协同
     *
     * @param terminalkey 终端主键
     * @param visitKey    拜访主键
     * @return
     */
    public List<CameraInfoStc> queryCurrentPicRecord(String terminalkey, String visitKey) {
        DbtLog.logUtils(TAG, "queryCurrentPicRecord()-获取当天拍照记录");
        List<CameraInfoStc> lst = new ArrayList<CameraInfoStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstCameraiInfoMDao dao = (MstCameraiInfoMDao) helper.getMstCameraiInfoMDao();
            lst = dao.queryCurrentCameraLst(helper, terminalkey, visitKey);

        } catch (Exception e) {
            Log.e(TAG, "获取拜访拍照临时表DAO对象失败", e);
        }
        return lst;
    }
    /**
     * 获取当天追溯拍照记录
     *
     * @param terminalkey 终端主键
     * @param valterid    追溯主键
     * @return
     */
    public List<MitValpicMTemp> queryZsCurrentPicRecord(String terminalkey, String valterid) {
        DbtLog.logUtils(TAG, "queryCurrentPicRecord()-获取当天拍照记录");
        List<MitValpicMTemp> lst = new ArrayList<MitValpicMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstCameraiInfoMDao dao = (MstCameraiInfoMDao) helper.getMstCameraiInfoMDao();
            lst = dao.queryZsCurrentCameraLst(helper, terminalkey, valterid);

        } catch (Exception e) {
            Log.e(TAG, "获取拜访拍照临时表DAO对象失败", e);
        }
        return lst;
    }

    /**
     * 获取并初始化拜访线路
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<MstRouteM> initXtMstRoute(String termid) {

        List<MstRouteM> mstRouteList = new ArrayList<MstRouteM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();

            String querySql = " select ro.* from mst_route_m ro join  (select r.gridkey from MST_TERMINALINFO_M_CART t " +
                    "  left join mst_route_m r on r.routekey = t.routekey " +
                    "  where t.terminalkey= ?) a on a.gridkey=ro.gridkey  " +
                    "  where ro.routestatus=0 order by ro.routename asc";
            Cursor cursor = db.rawQuery(querySql, new String[]{termid});
            MstRouteM item;
            while (cursor.moveToNext()) {
                item = new MstRouteM();
                item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
                item.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
                item.setRoutecode(cursor.getString(cursor.getColumnIndex("routecode")));
                item.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
                item.setRoutedesc(cursor.getString(cursor.getColumnIndex("routedesc")));
                item.setRoutestatus(cursor.getString(cursor.getColumnIndex("routestatus")));
                item.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
                item.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setComid(cursor.getString(cursor.getColumnIndex("comid")));
                item.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
                item.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
                item.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
                item.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));

                mstRouteList.add(item);
            }
        } catch (Exception e) {
            Log.e(TAG, "获取线路信息失败", e);
        }
        return mstRouteList;
    }

    /**
     * 获取并初始化拜访线路
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<MstRouteM> initZsMstRoute(String termid) {

        List<MstRouteM> mstRouteList = new ArrayList<MstRouteM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();

            String querySql = " select ro.* from mst_route_m ro join  (select r.gridkey from MST_TERMINALINFO_M_ZSCART t " +
                    "  left join mst_route_m r on r.routekey = t.routekey " +
                    "  where t.terminalkey= ?) a on a.gridkey=ro.gridkey  " +
                    "  where ro.routestatus=0 order by ro.routename asc";
            Cursor cursor = db.rawQuery(querySql, new String[]{termid});
            MstRouteM item;
            while (cursor.moveToNext()) {
                item = new MstRouteM();
                item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
                item.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
                item.setRoutecode(cursor.getString(cursor.getColumnIndex("routecode")));
                item.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
                item.setRoutedesc(cursor.getString(cursor.getColumnIndex("routedesc")));
                item.setRoutestatus(cursor.getString(cursor.getColumnIndex("routestatus")));
                item.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
                item.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setComid(cursor.getString(cursor.getColumnIndex("comid")));
                item.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
                item.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
                item.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
                item.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));

                mstRouteList.add(item);
            }
        } catch (Exception e) {
            Log.e(TAG, "获取线路信息失败", e);
        }
        return mstRouteList;
    }

    /**
     * 获取终端信息
     * 有错误
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalInfoMStc findTermKeyById(String termId) {

        MstTerminalInfoMStc termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            //termInfo = dao.findById(helper, termId);
            termInfo = dao.findKeyById(helper, termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }

    /***
     * 更新协同拜访经纬度
     * @param visitId
     * @param longitude 经度
     * @param latitude  维度
     * @param gpsStatus
     */
    public void updateXtGps(String visitId, double longitude, double latitude, String gpsStatus) {
        try {
            if (0 != latitude && 0 != longitude) {
                DatabaseHelper helper = DatabaseHelper.getHelper(context);

                MstVisitMTempDao visitTempDao = helper.getDao(MstVisitMTemp.class);


                StringBuffer buffer = new StringBuffer();
                buffer.append("update mst_visit_m_temp set longitude=?, ");
                buffer.append("latitude=?, gpsstatus=? ");
                buffer.append("where visitkey=? ");
                String[] args = new String[4];
                args[0] = String.valueOf(longitude);
                args[1] = String.valueOf(latitude);
                args[2] = gpsStatus;
                args[3] = visitId;
                visitTempDao.executeRaw(buffer.toString(), args);
            }

        } catch (SQLException e) {
            Log.e(TAG, "更新拜访GPS信息失败", e);
        }
    }
    /***
     * 更新终端追溯经纬度
     * @param visitId
     * @param longitude 经度
     * @param latitude  维度
     */
    public void updateZsGps(String visitId, double longitude, double latitude) {
        try {
            if (0 != latitude && 0 != longitude) {
                DatabaseHelper helper = DatabaseHelper.getHelper(context);

                MstVisitMTempDao visitTempDao = helper.getDao(MstVisitMTemp.class);


                StringBuffer buffer = new StringBuffer();
                buffer.append("update mit_valter_m_temp set lon=?, ");
                buffer.append("lat=? ");
                buffer.append("where id = ? ");
                String[] args = new String[3];
                args[0] = String.valueOf(longitude);
                args[1] = String.valueOf(latitude);
                args[2] = visitId;
                visitTempDao.executeRaw(buffer.toString(), args);
            }

        } catch (SQLException e) {
            Log.e(TAG, "更新拜访GPS信息失败", e);
        }
    }

    /**
     * 获取某终端的最新拜访记录信息
     *
     * @param termId     终端ID
     * @param isEndVisit 是否结束拜访-true：上次结束拜访，false：所有拜访
     * @return
     */
    public MstVisitM findNewLastVisit(String termId, boolean isEndVisit) {

        MstVisitM visitM = null;
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        MstVisitMDao visitDao;
        try {
            visitDao = (MstVisitMDao) helper.getMstVisitMDao();
            QueryBuilder<MstVisitM, String> qBuilder = visitDao.queryBuilder();
            if (isEndVisit) {
                qBuilder.where().eq("terminalkey", termId).and().eq("padisconsistent", "1");
            } else {
                qBuilder.where().eq("terminalkey", termId);
            }
            qBuilder.orderBy("visitdate", false);
            visitM = qBuilder.queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访请表DAO异常", e);
        }
        return visitM;
    }

    // 获取产品组合临时表数据 根据终端key
    public MstGroupproductMTemp findMstGroupproductMTempByid(String termcode) {
        // 获取产品组合临时表数据
        List<MstGroupproductMTemp> stcLst = new ArrayList<MstGroupproductMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstGroupproductMTempDao dao = helper.getDao(MstGroupproductMTemp.class);
            stcLst = dao.queryMstGroupproductMByTerminalcode(helper, termcode);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }
        return stcLst.get(0);
    }

    // 获取产品组合临时表数据 根据终端key
    public MitValgroupproMTemp findZsMitValgroupproMTempByid(String valterid) {
        // 获取产品组合临时表数据
        List<MitValgroupproMTemp> stcLst = new ArrayList<MitValgroupproMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstGroupproductMTempDao dao = helper.getDao(MstGroupproductMTemp.class);
            stcLst = dao.queryZsMitValgroupproMTempByValterid(helper, valterid);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }
        return stcLst.get(0);
    }

    // 复制协同各个临时表  mstTerminalInfoMStc:大区id 二级区域id,定格id,路线id,终端id
    public String toCopyData(XtTermSelectMStc termStc, MstTerminalInfoMStc mstTerminalInfoMStc) {
        // 从拜访主表中获取最后一次拜访数据(从业代主表获取)
        MstVisitM mstVisitM = findNewLastVisit(termStc.getTerminalkey(), false);
        MstVisitMTemp visitMTemp = null;

        // 记录当前时间 作为拜访开始日期
        String visitDate = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");

        // 获取上次拜访主键
        prevVisitId = mstVisitM.getVisitkey();
        // 上次拜访日期
        prevVisitDate = mstVisitM.getVisitdate();

        // 事务控制
        AndroidDatabaseConnection connection = null;
        // 开始复制
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstVisitM, String> visitDao = helper.getMstVisitMDao();
            Dao<MstVisitMTemp, String> visitTempDao = helper.getMstVisitMTempDao();

            Dao<MstTerminalinfoM, String> terminalinfoMDao = helper.getMstTerminalinfoMDao();
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao = helper.getMstTerminalinfoMTempDao();

            Dao<MstVistproductInfo, String> proDao = helper.getMstVistproductInfoDao();
            Dao<MstVistproductInfoTemp, String> proTempDao = helper.getMstVistproductInfoTempDao();

            MstAgencysupplyInfoDao asupplyDao = helper.getDao(MstAgencysupplyInfo.class);
            Dao<MstAgencysupplyInfo, String> agencyDao = helper.getDao(MstAgencysupplyInfo.class);
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao = helper.getMstAgencysupplyInfoTempDao();

            Dao<MstCmpsupplyInfo, String> cmpSupplyDao = helper.getMstCmpsupplyInfoDao();
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao = helper.getMstCmpsupplyInfoTempDao();

            Dao<MstCheckexerecordInfo, String> valueDao = helper.getMstCheckexerecordInfoDao();
            Dao<MstCheckexerecordInfoTemp, String> valueTempDao = helper.getMstCheckexerecordInfoTempDao();

            Dao<MstCollectionexerecordInfo, String> collectionDao = helper.getMstCollectionexerecordInfoDao();
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao = helper.getMstCollectionexerecordInfoTempDao();

            Dao<MstPromotermInfo, String> promDao = helper.getMstPromotermInfoDao();
            Dao<MstPromotermInfoTemp, String> promTempDao = helper.getMstPromotermInfoTempDao();

            Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao = helper.getPadCheckaccomplishInfoDao();

            Dao<MitValterMTemp, String> mitValterMTempDao = helper.getMitValterMTempDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 复制拜访表
            // 如果上次拜访为空，则是本终端的第一次拜访
            if (mstVisitM == null) {
                visitMTemp = new MstVisitMTemp();
                visitMTemp.setVisitkey(FunUtil.getUUID());
                visitMTemp.setTerminalkey(termStc.getTerminalkey());
                visitMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());
                visitMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());
                visitMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());
                visitMTemp.setVisitdate(visitDate);
                visitMTemp.setStatus(ConstValues.FLAG_1);
                visitMTemp.setSisconsistent(ConstValues.FLAG_0);
                visitMTemp.setPadisconsistent(ConstValues.FLAG_0);
                visitMTemp.setOrderbyno(ConstValues.FLAG_0);// 同一次是0 返回时会改为1
                visitMTemp.setUserid(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setCreuser(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setUpdateuser(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setUploadFlag(ConstValues.FLAG_0);
                visitTempDao.create(visitMTemp);

                // 复制visitproduct_temp表 //初始化拜访产品表
                addProductInfoToTemp(visitMTemp.getTerminalkey(), visitMTemp.getVisitkey(), agencyDao, proTempDao);

                // 复制到拉链表临时表  只有当天重复拜访才会复制上次数据,若是当天第一次拜访,只复制与产品无关的指标
                if (visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    // 复制到拉链表临时表 MstCheckexerecordInfoTemp 全部复制
                    createMstCheckexerecordInfoTemp(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                } else {
                    // 复制到拉链表临时表 当天第一次拜访,MstCheckexerecordInfoTemp 只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp1(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                }

            } else {
                // 非第一次拜访该终端
                visitMTemp = new MstVisitMTemp();

                // 如果上次拜访日期与本次拜访不是同一天(竞品拜访记录清空)
                if (!visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    visitMTemp.setRemarks("");
                } else {
                    visitMTemp.setRemarks(mstVisitM.getRemarks());
                }

                // 复制拜访主表数据，到临时表MST_VISIT_M_TEMP(拜访主表)
                visitMTemp.setVisitkey(FunUtil.getUUID());
                visitMTemp.setTerminalkey(termStc.getTerminalkey());
                visitMTemp.setRoutekey(termStc.getRoutekey());
                visitMTemp.setVisitdate(visitDate);
                //visitMTemp.setStatus(ConstValues.FLAG_1);
                visitMTemp.setStatus(mstVisitM.getStatus());// 上次是否有效拜访
                visitMTemp.setLongitude(mstVisitM.getLongitude());
                visitMTemp.setLatitude(mstVisitM.getLatitude());
                visitMTemp.setVisituser(mstVisitM.getVisituser());// 为null
                visitMTemp.setVisitposition(mstVisitM.getVisitposition());
                visitMTemp.setEnddate(null);
                visitMTemp.setSisconsistent(ConstValues.FLAG_0);
                visitMTemp.setScondate(null);
                visitMTemp.setPadisconsistent(ConstValues.FLAG_0);
                visitMTemp.setOrderbyno(ConstValues.FLAG_0);// 同一次是0 返回时会改为1
                visitMTemp.setPadcondate(null);
                visitMTemp.setUploadFlag(ConstValues.FLAG_0);
                visitMTemp.setCredate(null);
                visitMTemp.setUpdatetime(null);
                visitMTemp.setUserid(PrefUtils.getString(context, "userid", ""));// 待定
                visitMTemp.setGridkey(mstVisitM.getGridkey());// 待定
                visitMTemp.setAreaid(mstVisitM.getAreaid());// 待定
                visitTempDao.create(visitMTemp);

                // 复制visitproduct_temp表
                List<MstVistproductInfo> proLst = proDao.queryForEq("visitkey", prevVisitId);
                if (!CheckUtil.IsEmpty(proLst)) {
                    //修复进销存界面因经销商不给该定格供货所以产品不显示 ,但是查指标界面还显示此商品的BUG
                    List<MstAgencysupplyInfo> agencysupply = asupplyDao.agencysupply(helper, visitMTemp.getTerminalkey());
                    // 通过终端获取此终端供货关系
                    // List<MstAgencysupplyInfo> agencysupplyList=getAgencySupplyInfoList(mstVisitM.getTerminalkey(), agencyDao);
                    Set<String> agencysupplySet = new HashSet<String>();
                    for (MstAgencysupplyInfo info : agencysupply) {
                        String key = info.getUpperkey() + info.getProductkey();// 经销商key+产品key
                        agencysupplySet.add(key);
                    }

                    MstVistproductInfoTemp vistproductInfoTemp = null;
                    for (MstVistproductInfo product : proLst) {
                        if (!ConstValues.FLAG_1.equals(product.getDeleteflag())) {
                            String key = product.getAgencykey() + product.getProductkey();
                            if (CheckUtil.isBlankOrNull(product.getProductkey()) || agencysupplySet.contains(key)) {// productkey为空:表示竞品记录
                                vistproductInfoTemp = new MstVistproductInfoTemp();
                                vistproductInfoTemp.setRecordkey(FunUtil.getUUID());
                                vistproductInfoTemp.setVisitkey(visitMTemp.getVisitkey());

                                // (隔天清零)如果上次拜访日期与本次拜访不是同一天
                                if (!visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                                    //上次库存
                                    vistproductInfoTemp.setPronum(product.getCurrnum());
                                    //日销量
                                    vistproductInfoTemp.setSalenum(null);
                                    //订单量(原 上周期进货总量)
                                    vistproductInfoTemp.setPurcnum(null);
                                    //当前库存 (本次库存)
                                    vistproductInfoTemp.setCurrnum(null);

                                } else {// 当日重复拜访
                                    //上次库存
                                    vistproductInfoTemp.setPronum(product.getPronum());
                                    //日销量
                                    vistproductInfoTemp.setSalenum(product.getSalenum());
                                    //订单量(原 上周期进货总量)
                                    vistproductInfoTemp.setPurcnum(product.getPurcnum());
                                    //当前库存 (本次库存)
                                    vistproductInfoTemp.setCurrnum(product.getCurrnum());

                                }

                                vistproductInfoTemp.setProductkey(product.getProductkey());
                                vistproductInfoTemp.setCmpproductkey(product.getCmpproductkey());
                                vistproductInfoTemp.setCmpcomkey(product.getCmpcomkey());
                                vistproductInfoTemp.setAgencykey(product.getAgencykey());
                                vistproductInfoTemp.setAgencyname(product.getAgencyname());// 用户输入的竞品经销商
                                vistproductInfoTemp.setPurcprice(product.getPurcprice());
                                vistproductInfoTemp.setRetailprice(product.getRetailprice());
                                //vistproductInfoTemp.setPronum(product.getPronum());
                                vistproductInfoTemp.setRemarks(product.getRemarks());
                                vistproductInfoTemp.setOrderbyno(product.getOrderbyno());
                                vistproductInfoTemp.setCreuser(product.getCreuser());
                                vistproductInfoTemp.setUpdateuser(product.getUpdateuser());
                                vistproductInfoTemp.setFristdate(product.getFristdate());
                                vistproductInfoTemp.setAddcard(product.getAddcard());// 累计卡
                                //
                                vistproductInfoTemp.setSisconsistent(ConstValues.FLAG_0);
                                vistproductInfoTemp.setScondate(null);
                                vistproductInfoTemp.setPadisconsistent(ConstValues.FLAG_0);
                                vistproductInfoTemp.setPadcondate(null);
                                vistproductInfoTemp.setDeleteflag(ConstValues.FLAG_0);
                                vistproductInfoTemp.setCredate(null);
                                vistproductInfoTemp.setUpdatetime(null);

                                proTempDao.create(vistproductInfoTemp);
                            }
                        }
                    }
                }

                // 重复拜访,复制拉链表,活动表
                if (visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    // 复制拉链表 MstCheckexerecordInfoTemp 全部复制   只有当天重复拜访才会复制上次数据,若是当天第一次拜访,只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                    // 复制活动拜访终端表  true:当天重复拜访
                    createMstPromotermInfoTemp(helper, prevVisitId, visitDate, visitMTemp, promDao, promTempDao, true);
                    //
                    // 复制采集项表
                    createMstCollectionexerecordInfoTemp(collectionDao, collectionTempDao, padCheckaccomplishInfoDao, prevVisitId, termStc, visitMTemp);

                } else {// 当天第一次拜访
                    // 复制拉链表 MstCheckexerecordInfoTemp 当天第一次拜访,MstCheckexerecordInfoTemp 只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp1(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                    // 复制活动拜访终端表 false:当天第一次拜访
                    createMstPromotermInfoTemp(helper, prevVisitId, visitDate, visitMTemp, promDao, promTempDao, false);

                }
            }

            // 复制终端临时表
            MstTerminalinfoMCart term = findTermById(termStc.getTerminalkey());
            // MstTerminalinfoM term = findMstTerminalinfoMById(termStc.getTerminalkey());
            // MstTerminalinfoM term = findMstTerminalinfoMById(termStc.getTerminalkey());
            MstTerminalinfoMTemp terminalinfoMTemp = null;
            if (term != null) {
                terminalinfoMTemp = new MstTerminalinfoMTemp();
                terminalinfoMTemp.setTerminalkey(term.getTerminalkey());
                terminalinfoMTemp.setRoutekey(term.getRoutekey());
                terminalinfoMTemp.setTerminalcode(term.getTerminalcode());
                terminalinfoMTemp.setTerminalname(term.getTerminalname());
                terminalinfoMTemp.setProvince(term.getProvince());
                terminalinfoMTemp.setCity(term.getCity());
                terminalinfoMTemp.setCounty(term.getCounty());
                terminalinfoMTemp.setAddress(term.getAddress());
                terminalinfoMTemp.setContact(term.getContact());
                terminalinfoMTemp.setMobile(term.getMobile());
                terminalinfoMTemp.setTlevel(term.getTlevel());
                terminalinfoMTemp.setSequence(term.getSequence());
                terminalinfoMTemp.setCycle(term.getCycle());
                terminalinfoMTemp.setHvolume(term.getHvolume());
                terminalinfoMTemp.setMvolume(term.getMvolume());
                terminalinfoMTemp.setPvolume(term.getPvolume());
                terminalinfoMTemp.setLvolume(term.getLvolume());
                terminalinfoMTemp.setStatus(term.getStatus());
                terminalinfoMTemp.setSellchannel(term.getSellchannel());
                terminalinfoMTemp.setMainchannel(term.getMainchannel());
                terminalinfoMTemp.setMinorchannel(term.getMinorchannel());
                terminalinfoMTemp.setAreatype(term.getAreatype());
                terminalinfoMTemp.setSisconsistent(term.getSisconsistent());
                terminalinfoMTemp.setScondate(term.getScondate());
                terminalinfoMTemp.setPadisconsistent(term.getPadisconsistent());
                terminalinfoMTemp.setPadcondate(term.getPadcondate());
                terminalinfoMTemp.setComid(term.getComid());
                terminalinfoMTemp.setRemarks(term.getRemarks());
                terminalinfoMTemp.setOrderbyno(term.getOrderbyno());
                terminalinfoMTemp.setVersion(term.getVersion());
                terminalinfoMTemp.setCredate(term.getCredate());
                terminalinfoMTemp.setCreuser(term.getCreuser());
                terminalinfoMTemp.setSelftreaty(term.getSelftreaty());
                terminalinfoMTemp.setCmpselftreaty(term.getCmpselftreaty());
                terminalinfoMTemp.setUpdatetime(term.getUpdatetime());
                terminalinfoMTemp.setUpdateuser(term.getUpdateuser());
                terminalinfoMTemp.setDeleteflag(term.getDeleteflag());
                terminalinfoMTemp.setIfminedate(term.getIfminedate());
                terminalinfoMTemp.setIfmine(term.getIfmine());
                terminalinfoMTempDao.create(terminalinfoMTemp);
            }

            //MstTerminalinfoMTemp terminalinfoMTemp = findTermTempById(termStc.getTerminalkey());

            // 复制 产品组合是否达标 (有则复制,没有则新建)
            createMstGroupproductMTemp(terminalinfoMTemp, visitMTemp.getVisitkey());

            // 复制我品供货关系临时表
            List<MstAgencysupplyInfo> agencysupply = asupplyDao.agencysupply(helper, visitMTemp.getTerminalkey());
            int size = agencysupply.size();
            if (size > 0) {
                MstAgencysupplyInfoTemp agencysupplyInfoTemp = null;
                for (MstAgencysupplyInfo supply : agencysupply) {
                    agencysupplyInfoTemp = new MstAgencysupplyInfoTemp();
                    agencysupplyInfoTemp.setAsupplykey(supply.getAsupplykey());
                    agencysupplyInfoTemp.setStatus("0");// 0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
                    agencysupplyInfoTemp.setInprice(supply.getInprice());
                    agencysupplyInfoTemp.setReprice(supply.getReprice());
                    agencysupplyInfoTemp.setProductkey(supply.getProductkey());
                    agencysupplyInfoTemp.setLowerkey(supply.getLowerkey());
                    agencysupplyInfoTemp.setLowertype(supply.getLowertype());
                    agencysupplyInfoTemp.setUpperkey(supply.getUpperkey());
                    agencysupplyInfoTemp.setUppertype(supply.getUppertype());
                    agencysupplyInfoTemp.setSiebelkey(supply.getSiebelkey());
                    agencysupplyInfoTemp.setSisconsistent(supply.getSisconsistent());
                    agencysupplyInfoTemp.setScondate(supply.getScondate());
                    agencysupplyInfoTemp.setPadisconsistent("1");// 复制过来为1 ,复制回去时变了的为0
                    agencysupplyInfoTemp.setPadcondate(supply.getPadcondate());
                    agencysupplyInfoTemp.setComid(supply.getComid());
                    agencysupplyInfoTemp.setRemarks(supply.getRemarks());
                    agencysupplyInfoTemp.setOrderbyno(supply.getOrderbyno());
                    agencysupplyInfoTemp.setVersion(supply.getVersion());
                    agencysupplyInfoTemp.setCredate(supply.getCredate());
                    agencysupplyInfoTemp.setCreuser(supply.getCreuser());
                    agencysupplyInfoTemp.setUpdatetime(supply.getUpdatetime());
                    agencysupplyInfoTemp.setUpdateuser(supply.getUpdateuser());
                    agencysupplyInfoTemp.setDeleteflag(supply.getDeleteflag());
                    agencyTempDao.create(agencysupplyInfoTemp);
                }
            }

            // 复制竞品供货关系临时表
            List<MstCmpsupplyInfo> cmpSupplyLst = cmpSupplyDao.queryForEq("terminalkey", termStc.getTerminalkey());
            int cmpSupplySize = cmpSupplyLst.size();
            if (cmpSupplySize > 0) {
                MstCmpsupplyInfoTemp mstCmpsupplyInfoTemp = null;
                for (MstCmpsupplyInfo cmpsupply : cmpSupplyLst) {
                    mstCmpsupplyInfoTemp = new MstCmpsupplyInfoTemp();
                    mstCmpsupplyInfoTemp.setCmpsupplykey(cmpsupply.getCmpsupplykey());
                    mstCmpsupplyInfoTemp.setCmpproductkey(cmpsupply.getCmpproductkey());
                    mstCmpsupplyInfoTemp.setCmpcomkey(cmpsupply.getCmpcomkey());
                    mstCmpsupplyInfoTemp.setTerminalkey(cmpsupply.getTerminalkey());
                    mstCmpsupplyInfoTemp.setStatus("0");// 0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
                    mstCmpsupplyInfoTemp.setInprice(cmpsupply.getInprice());
                    mstCmpsupplyInfoTemp.setReprice(cmpsupply.getReprice());
                    mstCmpsupplyInfoTemp.setCmpinvaliddate(cmpsupply.getCmpinvaliddate());
                    mstCmpsupplyInfoTemp.setSisconsistent(cmpsupply.getSisconsistent());
                    mstCmpsupplyInfoTemp.setScondate(cmpsupply.getScondate());
                    mstCmpsupplyInfoTemp.setPadisconsistent("1");// 复制过来为1 ,复制回去时变了的为0
                    mstCmpsupplyInfoTemp.setPadcondate(cmpsupply.getPadcondate());
                    mstCmpsupplyInfoTemp.setComid(cmpsupply.getComid());
                    mstCmpsupplyInfoTemp.setRemarks(cmpsupply.getRemarks());
                    mstCmpsupplyInfoTemp.setOrderbyno(cmpsupply.getOrderbyno());
                    mstCmpsupplyInfoTemp.setVersion(cmpsupply.getVersion());
                    mstCmpsupplyInfoTemp.setCredate(cmpsupply.getCredate());
                    mstCmpsupplyInfoTemp.setCreuser(cmpsupply.getCreuser());
                    mstCmpsupplyInfoTemp.setUpdatetime(cmpsupply.getUpdatetime());
                    mstCmpsupplyInfoTemp.setUpdateuser(cmpsupply.getUpdateuser());
                    mstCmpsupplyInfoTemp.setDeleteflag(cmpsupply.getDeleteflag());
                    cmpSupplyTempDao.create(mstCmpsupplyInfoTemp);
                }
            }


            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "复制数据出错", e);
            try {
                connection.rollback(null);
                //ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        return visitMTemp.getVisitkey();
    }

    // 复制追溯各个临时表  mstTerminalInfoMStc:大区id 二级区域id,定格id,路线id,终端id
    public List<String> toCopyZsData(XtTermSelectMStc termStc, MstTerminalInfoMStc mstTerminalInfoMStc) {
        // 从拜访主表中获取最后一次拜访数据(从业代主表获取)
        MstVisitM mstVisitM = findNewLastVisit(termStc.getTerminalkey(), false);

        // MstVisitMTemp visitMTemp = null;
        MitValterMTemp mitValterMTemp = null;

        // 记录当前时间 作为拜访开始日期
        String visitDate = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");

        // 获取上次拜访主键
        prevVisitId = mstVisitM.getVisitkey();
        // 上次拜访日期
        prevVisitDate = mstVisitM.getVisitdate();

        // 事务控制
        AndroidDatabaseConnection connection = null;
        // 开始复制
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstVisitM, String> visitDao = helper.getMstVisitMDao();
            Dao<MstVisitMTemp, String> visitTempDao = helper.getMstVisitMTempDao();

            Dao<MstTerminalinfoM, String> terminalinfoMDao = helper.getMstTerminalinfoMDao();
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao = helper.getMstTerminalinfoMTempDao();

            Dao<MstVistproductInfo, String> proDao = helper.getMstVistproductInfoDao();
            Dao<MstVistproductInfoTemp, String> proTempDao = helper.getMstVistproductInfoTempDao();

            MstAgencysupplyInfoDao asupplyDao = helper.getDao(MstAgencysupplyInfo.class);
            Dao<MstAgencysupplyInfo, String> agencyDao = helper.getDao(MstAgencysupplyInfo.class);
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao = helper.getMstAgencysupplyInfoTempDao();

            Dao<MstCmpsupplyInfo, String> cmpSupplyDao = helper.getMstCmpsupplyInfoDao();
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao = helper.getMstCmpsupplyInfoTempDao();

            Dao<MstCheckexerecordInfo, String> valueDao = helper.getMstCheckexerecordInfoDao();
            Dao<MstCheckexerecordInfoTemp, String> valueTempDao = helper.getMstCheckexerecordInfoTempDao();

            Dao<MstCollectionexerecordInfo, String> collectionDao = helper.getMstCollectionexerecordInfoDao();
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao = helper.getMstCollectionexerecordInfoTempDao();

            Dao<MstPromotermInfo, String> promDao = helper.getMstPromotermInfoDao();
            Dao<MstPromotermInfoTemp, String> promTempDao = helper.getMstPromotermInfoTempDao();

            Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao = helper.getPadCheckaccomplishInfoDao();

            Dao<MitValterMTemp, String> mitValterMTempDao = helper.getMitValterMTempDao();
            Dao<MitValsupplyMTemp, String> mitValsupplyMTempDao = helper.getMitValsupplyMTempDao();

            //Dao<MitValcmpM, String> mitValcmpMDao = helper.getMitValcmpMDao();
            Dao<MitValcmpMTemp, String> mitValcmpMTempDao = helper.getMitValcmpMTempDao();
            Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao = helper.getMitValchecktypeMTempDao();
            Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao = helper.getMitValcheckitemMTempDao();
            Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao = helper.getMitValcmpotherMTempDao();
            Dao<MitValgroupproMTemp, String> mitValgroupproMTempDao = helper.getMitValgroupproMTempDao();

            Dao<MitValpromotionsMTemp, String> mitValpromotionsMTempDao = helper.getMitValpromotionsMTempDao();

            Dao<MitValaddaccountMTemp, String> mitValaddaccountMTempDao = helper.getMitValaddaccountMTempDao();
            Dao<MitValaddaccountproMTemp, String> mitValaddaccountproMTempDao = helper.getMitValaddaccountproMTempDao();

            Dao<MstAgreeTmp, String> mstAgreeTmpDao = helper.getMstAgreeTmpDao();
            Dao<MstAgreeDetailTmp, String> mstAgreeDetailTmpDao = helper.getMstAgreeDetailTmpDao();

            Dao<MitValagreeMTemp, String> mitValagreeMTempDao = helper.getMitValagreeMTempDao();
            Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao = helper.getMitValagreedetailMTempDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 复制拜访表
            // 如果上次拜访为空，则是本终端的第一次拜访
            /*if (mstVisitM == null) {
                visitMTemp = new MstVisitMTemp();
                visitMTemp.setVisitkey(FunUtil.getUUID());
                visitMTemp.setTerminalkey(termStc.getTerminalkey());
                visitMTemp.setRoutekey(mstTerminalInfoMStc.getRoutekey());
                visitMTemp.setGridkey(mstTerminalInfoMStc.getGridkey());
                visitMTemp.setAreaid(mstTerminalInfoMStc.getAreaid());
                visitMTemp.setVisitdate(visitDate);
                visitMTemp.setStatus(ConstValues.FLAG_1);
                visitMTemp.setSisconsistent(ConstValues.FLAG_0);
                visitMTemp.setPadisconsistent(ConstValues.FLAG_0);
                visitMTemp.setOrderbyno(ConstValues.FLAG_0);// 同一次是0 返回时会改为1
                visitMTemp.setUserid(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setCreuser(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setUpdateuser(PrefUtils.getString(context, "userid", ""));
                visitMTemp.setUploadFlag(ConstValues.FLAG_0);
                visitTempDao.create(visitMTemp);

                // 复制visitproduct_temp表 //初始化拜访产品表
                addProductInfoToTemp(visitMTemp.getTerminalkey(), visitMTemp.getVisitkey(), agencyDao, proTempDao);

                // 复制到拉链表临时表  只有当天重复拜访才会复制上次数据,若是当天第一次拜访,只复制与产品无关的指标
                if (visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    // 复制到拉链表临时表 MstCheckexerecordInfoTemp 全部复制
                    createMstCheckexerecordInfoTemp(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                } else {
                    // 复制到拉链表临时表 当天第一次拜访,MstCheckexerecordInfoTemp 只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp1(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                }

            } else {
                // 非第一次拜访该终端
                visitMTemp = new MstVisitMTemp();

                // 如果上次拜访日期与本次拜访不是同一天(竞品拜访记录清空)
                if (!visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    visitMTemp.setRemarks("");
                } else {
                    visitMTemp.setRemarks(mstVisitM.getRemarks());
                }

                // 复制拜访主表数据，到临时表MST_VISIT_M_TEMP(拜访主表)
                visitMTemp.setVisitkey(FunUtil.getUUID());
                visitMTemp.setTerminalkey(termStc.getTerminalkey());
                visitMTemp.setRoutekey(termStc.getRoutekey());
                visitMTemp.setVisitdate(visitDate);
                //visitMTemp.setStatus(ConstValues.FLAG_1);
                visitMTemp.setStatus(mstVisitM.getStatus());// 上次是否有效拜访
                visitMTemp.setLongitude(mstVisitM.getLongitude());
                visitMTemp.setLatitude(mstVisitM.getLatitude());
                visitMTemp.setVisituser(mstVisitM.getVisituser());// 为null
                visitMTemp.setVisitposition(mstVisitM.getVisitposition());
                visitMTemp.setEnddate(null);
                visitMTemp.setSisconsistent(ConstValues.FLAG_0);
                visitMTemp.setScondate(null);
                visitMTemp.setPadisconsistent(ConstValues.FLAG_0);
                visitMTemp.setOrderbyno(ConstValues.FLAG_0);// 同一次是0 返回时会改为1
                visitMTemp.setPadcondate(null);
                visitMTemp.setUploadFlag(ConstValues.FLAG_0);
                visitMTemp.setCredate(null);
                visitMTemp.setUpdatetime(null);
                visitMTemp.setUserid(PrefUtils.getString(context, "userid", ""));// 待定
                visitMTemp.setGridkey(mstVisitM.getGridkey());// 待定
                visitMTemp.setAreaid(mstVisitM.getAreaid());// 待定
                visitTempDao.create(visitMTemp);

                // 复制visitproduct_temp表
                List<MstVistproductInfo> proLst = proDao.queryForEq("visitkey", prevVisitId);
                if (!CheckUtil.IsEmpty(proLst)) {
                    //修复进销存界面因经销商不给该定格供货所以产品不显示 ,但是查指标界面还显示此商品的BUG
                    List<MstAgencysupplyInfo> agencysupply = asupplyDao.agencysupply(helper, visitMTemp.getTerminalkey());
                    // 通过终端获取此终端供货关系
                    // List<MstAgencysupplyInfo> agencysupplyList=getAgencySupplyInfoList(mstVisitM.getTerminalkey(), agencyDao);
                    Set<String> agencysupplySet = new HashSet<String>();
                    for (MstAgencysupplyInfo info : agencysupply) {
                        String key = info.getUpperkey() + info.getProductkey();// 经销商key+产品key
                        agencysupplySet.add(key);
                    }

                    MstVistproductInfoTemp vistproductInfoTemp = null;
                    for (MstVistproductInfo product : proLst) {
                        if (!ConstValues.FLAG_1.equals(product.getDeleteflag())) {
                            String key = product.getAgencykey() + product.getProductkey();
                            if (CheckUtil.isBlankOrNull(product.getProductkey()) || agencysupplySet.contains(key)) {// productkey为空:表示竞品记录
                                vistproductInfoTemp = new MstVistproductInfoTemp();
                                vistproductInfoTemp.setRecordkey(FunUtil.getUUID());
                                vistproductInfoTemp.setVisitkey(visitMTemp.getVisitkey());

                                //上次库存
                                vistproductInfoTemp.setPronum(product.getPronum());
                                //日销量
                                vistproductInfoTemp.setSalenum(product.getSalenum());
                                //订单量(原 上周期进货总量)
                                vistproductInfoTemp.setPurcnum(product.getPurcnum());
                                //当前库存 (本次库存)
                                vistproductInfoTemp.setCurrnum(product.getCurrnum());

                                vistproductInfoTemp.setProductkey(product.getProductkey());
                                vistproductInfoTemp.setCmpproductkey(product.getCmpproductkey());
                                vistproductInfoTemp.setCmpcomkey(product.getCmpcomkey());
                                vistproductInfoTemp.setAgencykey(product.getAgencykey());
                                vistproductInfoTemp.setAgencyname(product.getAgencyname());// 用户输入的竞品经销商
                                vistproductInfoTemp.setPurcprice(product.getPurcprice());
                                vistproductInfoTemp.setRetailprice(product.getRetailprice());
                                //vistproductInfoTemp.setPronum(product.getPronum());
                                vistproductInfoTemp.setRemarks(product.getRemarks());
                                vistproductInfoTemp.setOrderbyno(product.getOrderbyno());
                                vistproductInfoTemp.setCreuser(product.getCreuser());
                                vistproductInfoTemp.setUpdateuser(product.getUpdateuser());
                                vistproductInfoTemp.setFristdate(product.getFristdate());
                                vistproductInfoTemp.setAddcard(product.getAddcard());// 累计卡
                                //
                                vistproductInfoTemp.setSisconsistent(ConstValues.FLAG_0);
                                vistproductInfoTemp.setScondate(null);
                                vistproductInfoTemp.setPadisconsistent(ConstValues.FLAG_0);
                                vistproductInfoTemp.setPadcondate(null);
                                vistproductInfoTemp.setDeleteflag(ConstValues.FLAG_0);
                                vistproductInfoTemp.setCredate(null);
                                vistproductInfoTemp.setUpdatetime(null);

                                proTempDao.create(vistproductInfoTemp);
                            }
                        }
                    }
                }

                // 重复拜访,复制拉链表,活动表
                if (visitDate.substring(0, 8).equals(prevVisitDate.substring(0, 8))) {
                    // 复制拉链表 MstCheckexerecordInfoTemp 全部复制   只有当天重复拜访才会复制上次数据,若是当天第一次拜访,只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                    // 复制活动拜访终端表  true:当天重复拜访
                    // createMstPromotermInfoTemp(helper, prevVisitId, visitDate, visitMTemp, promDao, promTempDao, true);
                    //

                } else {// 当天第一次拜访
                    // 复制拉链表 MstCheckexerecordInfoTemp 当天第一次拜访,MstCheckexerecordInfoTemp 只复制与产品无关的指标
                    createMstCheckexerecordInfoTemp1(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
                    // 复制活动拜访终端表 false:当天第一次拜访
                    // createMstPromotermInfoTemp(helper, prevVisitId, visitDate, visitMTemp, promDao, promTempDao, false);
                    // 复制采集项表
                    createMstCollectionexerecordInfoTemp(collectionDao, collectionTempDao, padCheckaccomplishInfoDao, prevVisitId, termStc, visitMTemp);
                }
            }*/

            // 复制终端临时表
            // MstTerminalinfoMCart term = findTermById(termStc.getTerminalkey());
            MstTerminalinfoMZsCart term = findZsTermById(termStc.getTerminalkey());
            //MstTerminalinfoM term = findMstTerminalinfoMById(termStc.getTerminalkey());
            MstTerminalinfoMTemp terminalinfoMTemp = null;
            if (term != null) {
                terminalinfoMTemp = new MstTerminalinfoMTemp();
                terminalinfoMTemp.setTerminalkey(term.getTerminalkey());
                terminalinfoMTemp.setRoutekey(term.getRoutekey());
                terminalinfoMTemp.setTerminalcode(term.getTerminalcode());
                terminalinfoMTemp.setTerminalname(term.getTerminalname());
                terminalinfoMTemp.setProvince(term.getProvince());
                terminalinfoMTemp.setCity(term.getCity());
                terminalinfoMTemp.setCounty(term.getCounty());
                terminalinfoMTemp.setAddress(term.getAddress());
                terminalinfoMTemp.setContact(term.getContact());
                terminalinfoMTemp.setMobile(term.getMobile());
                terminalinfoMTemp.setTlevel(term.getTlevel());
                terminalinfoMTemp.setSequence(term.getSequence());
                terminalinfoMTemp.setCycle(term.getCycle());
                terminalinfoMTemp.setHvolume(term.getHvolume());
                terminalinfoMTemp.setMvolume(term.getMvolume());
                terminalinfoMTemp.setPvolume(term.getPvolume());
                terminalinfoMTemp.setLvolume(term.getLvolume());
                terminalinfoMTemp.setStatus(term.getStatus());
                terminalinfoMTemp.setSellchannel(term.getSellchannel());
                terminalinfoMTemp.setMainchannel(term.getMainchannel());
                terminalinfoMTemp.setMinorchannel(term.getMinorchannel());
                terminalinfoMTemp.setAreatype(term.getAreatype());
                terminalinfoMTemp.setSisconsistent(term.getSisconsistent());
                terminalinfoMTemp.setScondate(term.getScondate());
                terminalinfoMTemp.setPadisconsistent(term.getPadisconsistent());
                terminalinfoMTemp.setPadcondate(term.getPadcondate());
                terminalinfoMTemp.setComid(term.getComid());
                terminalinfoMTemp.setRemarks(term.getRemarks());
                terminalinfoMTemp.setOrderbyno(term.getOrderbyno());
                terminalinfoMTemp.setVersion(term.getVersion());
                terminalinfoMTemp.setCredate(term.getCredate());
                terminalinfoMTemp.setCreuser(term.getCreuser());
                terminalinfoMTemp.setSelftreaty(term.getSelftreaty());
                terminalinfoMTemp.setCmpselftreaty(term.getCmpselftreaty());
                terminalinfoMTemp.setUpdatetime(term.getUpdatetime());
                terminalinfoMTemp.setUpdateuser(term.getUpdateuser());
                terminalinfoMTemp.setDeleteflag(term.getDeleteflag());
                terminalinfoMTemp.setIfminedate(term.getIfminedate());
                terminalinfoMTemp.setIfmine(term.getIfmine());
                terminalinfoMTempDao.create(terminalinfoMTemp);
            }

            // 复制追溯主表
            if (term != null) {
                mitValterMTemp = new MitValterMTemp();
                mitValterMTemp.setId(FunUtil.getUUID());// 追溯主键
                mitValterMTemp.setTerminalkey(term.getTerminalkey());// 终端KEY
                mitValterMTemp.setVisitdate(visitDate);
                mitValterMTemp.setVidstartdate(visitDate);// 拜访开始时间
                mitValterMTemp.setVidter(term.getStatus());// 是否有效终端原值
                mitValterMTemp.setVidvisit(mstVisitM.getStatus());// 是否有效拜访原值
                mitValterMTemp.setVidifmine(term.getIfmine());// 我品店招原值
                mitValterMTemp.setVidifminedate(term.getIfminedate());//店招时间原值
                mitValterMTemp.setVidisself(mstVisitM.getIsself());// 销售产品范围我品原值
                mitValterMTemp.setVidiscmp(mstVisitM.getIscmp());// 销售产品范围竞品原值
                mitValterMTemp.setVidselftreaty(term.getSelftreaty());// 终端合作状态我品原值
                mitValterMTemp.setVidcmptreaty(term.getCmpselftreaty());// 终端合作状态竞品原值
                mitValterMTemp.setVidterminalcode(term.getTerminalcode());// 终端编码原值
                mitValterMTemp.setVidroutekey(term.getRoutekey());// 所属路线原值
                mitValterMTemp.setVidterlevel(term.getTlevel());//终端等级
                mitValterMTemp.setVidtername(term.getTerminalname());//终端名称原值
                mitValterMTemp.setVidcountry(term.getCounty());//所属县原值
                mitValterMTemp.setVidaddress(term.getAddress());//地址原值
                mitValterMTemp.setVidcontact(term.getContact());//联系人原值
                mitValterMTemp.setVidmobile(term.getMobile());//电话原值
                mitValterMTemp.setVidsequence(term.getSequence());//拜访顺序原值
                mitValterMTemp.setVidcycle(term.getCycle());//拜访周期原值
                mitValterMTemp.setVidareatype(term.getAreatype());//区域类型原值
                mitValterMTemp.setVidhvolume(term.getHvolume());//高档容量原值
                mitValterMTemp.setVidzvolume(term.getMvolume());//中档容量原值
                mitValterMTemp.setVidpvolume(term.getPvolume());//普档容量原值
                mitValterMTemp.setVidlvolume(term.getLvolume());//底档容量原值
                mitValterMTemp.setVidminchannel(term.getMinorchannel());//次渠道原值
                mitValterMTemp.setVidvisituser(mstVisitM.getVisitposition());//拜访对象原值key
                mitValterMTemp.setVidvisitotherval(mstVisitM.getVisituser());//拜访对象原值value
                //mitValterMTemp.setPadisconsistent(term.getPadisconsistent());//

                // ----------
                /*mitValterMTemp.setCredate(term.getCredate());
                mitValterMTemp.setCreuser(term.getCreuser());
                mitValterMTemp.setUpdateuser(term.getUpdateuser());
                mitValterMTemp.setUpdatedate(term.getUpdatetime());*/
                //---------------

                mitValterMTempDao.create(mitValterMTemp);
            }


            //MstTerminalinfoMTemp terminalinfoMTemp = findTermTempById(termStc.getTerminalkey());

            // 复制 产品组合是否达标 (有则复制,没有则新建)
            // createMstGroupproductMTemp(terminalinfoMTemp, mstVisitM.getVisitkey());

            // 复制我品供货关系临时表
            /*List<MstAgencysupplyInfo> agencysupply = asupplyDao.agencysupply(helper, visitMTemp.getTerminalkey());
            int size = agencysupply.size();
            if (size > 0) {
                MstAgencysupplyInfoTemp agencysupplyInfoTemp = null;
                for (MstAgencysupplyInfo supply : agencysupply) {
                    agencysupplyInfoTemp = new MstAgencysupplyInfoTemp();
                    agencysupplyInfoTemp.setAsupplykey(supply.getAsupplykey());
                    agencysupplyInfoTemp.setStatus("0");// 0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
                    agencysupplyInfoTemp.setInprice(supply.getInprice());
                    agencysupplyInfoTemp.setReprice(supply.getReprice());
                    agencysupplyInfoTemp.setProductkey(supply.getProductkey());
                    agencysupplyInfoTemp.setLowerkey(supply.getLowerkey());
                    agencysupplyInfoTemp.setLowertype(supply.getLowertype());
                    agencysupplyInfoTemp.setUpperkey(supply.getUpperkey());
                    agencysupplyInfoTemp.setUppertype(supply.getUppertype());
                    agencysupplyInfoTemp.setSiebelkey(supply.getSiebelkey());
                    agencysupplyInfoTemp.setSisconsistent(supply.getSisconsistent());
                    agencysupplyInfoTemp.setScondate(supply.getScondate());
                    agencysupplyInfoTemp.setPadisconsistent("1");// 复制过来为1 ,复制回去时变了的为0
                    agencysupplyInfoTemp.setPadcondate(supply.getPadcondate());
                    agencysupplyInfoTemp.setComid(supply.getComid());
                    agencysupplyInfoTemp.setRemarks(supply.getRemarks());
                    agencysupplyInfoTemp.setOrderbyno(supply.getOrderbyno());
                    agencysupplyInfoTemp.setVersion(supply.getVersion());
                    agencysupplyInfoTemp.setCredate(supply.getCredate());
                    agencysupplyInfoTemp.setCreuser(supply.getCreuser());
                    agencysupplyInfoTemp.setUpdatetime(supply.getUpdatetime());
                    agencysupplyInfoTemp.setUpdateuser(supply.getUpdateuser());
                    agencysupplyInfoTemp.setDeleteflag(supply.getDeleteflag());
                    agencyTempDao.create(agencysupplyInfoTemp);
                }
            }

            // 复制竞品供货关系临时表
            List<MstCmpsupplyInfo> cmpSupplyLst = cmpSupplyDao.queryForEq("terminalkey", termStc.getTerminalkey());
            int cmpSupplySize = cmpSupplyLst.size();
            if (cmpSupplySize > 0) {
                MstCmpsupplyInfoTemp mstCmpsupplyInfoTemp = null;
                for (MstCmpsupplyInfo cmpsupply : cmpSupplyLst) {
                    mstCmpsupplyInfoTemp = new MstCmpsupplyInfoTemp();
                    mstCmpsupplyInfoTemp.setCmpsupplykey(cmpsupply.getCmpsupplykey());
                    mstCmpsupplyInfoTemp.setCmpproductkey(cmpsupply.getCmpproductkey());
                    mstCmpsupplyInfoTemp.setCmpcomkey(cmpsupply.getCmpcomkey());
                    mstCmpsupplyInfoTemp.setTerminalkey(cmpsupply.getTerminalkey());
                    mstCmpsupplyInfoTemp.setStatus("0");// 0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
                    mstCmpsupplyInfoTemp.setInprice(cmpsupply.getInprice());
                    mstCmpsupplyInfoTemp.setReprice(cmpsupply.getReprice());
                    mstCmpsupplyInfoTemp.setCmpinvaliddate(cmpsupply.getCmpinvaliddate());
                    mstCmpsupplyInfoTemp.setSisconsistent(cmpsupply.getSisconsistent());
                    mstCmpsupplyInfoTemp.setScondate(cmpsupply.getScondate());
                    mstCmpsupplyInfoTemp.setPadisconsistent("1");// 复制过来为1 ,复制回去时变了的为0
                    mstCmpsupplyInfoTemp.setPadcondate(cmpsupply.getPadcondate());
                    mstCmpsupplyInfoTemp.setComid(cmpsupply.getComid());
                    mstCmpsupplyInfoTemp.setRemarks(cmpsupply.getRemarks());
                    mstCmpsupplyInfoTemp.setOrderbyno(cmpsupply.getOrderbyno());
                    mstCmpsupplyInfoTemp.setVersion(cmpsupply.getVersion());
                    mstCmpsupplyInfoTemp.setCredate(cmpsupply.getCredate());
                    mstCmpsupplyInfoTemp.setCreuser(cmpsupply.getCreuser());
                    mstCmpsupplyInfoTemp.setUpdatetime(cmpsupply.getUpdatetime());
                    mstCmpsupplyInfoTemp.setUpdateuser(cmpsupply.getUpdateuser());
                    mstCmpsupplyInfoTemp.setDeleteflag(cmpsupply.getDeleteflag());
                    cmpSupplyTempDao.create(mstCmpsupplyInfoTemp);
                }
            }*/

            // 复制追溯进销存
            queryInvocingValSupplyTemp(mitValsupplyMTempDao, prevVisitId, term.getTerminalkey(), mitValterMTemp.getId());


            // 复制追溯聊竞品
            queryChatVieValVieSupplyTemp(mitValcmpMTempDao, prevVisitId, term.getTerminalkey(), mitValterMTemp.getId());

            // 复制活动拜访终端表 false:当天第一次拜访
            createMitPromotermInfoTemp(helper, mitValterMTemp.getId(), promDao, mitValpromotionsMTempDao);

            // 复制到拉链表临时表 -> 追溯拉链表临时表   // 复制采集项表 -> 追溯 采集项表
            createZsMstCheckexerecordInfoTemp(prevVisitId, term.getTerminalkey(), visitDate,
                    // visitMTemp,
                    valueDao, mitValchecktypeMTempDao, mitValterMTemp.getId(),

                    collectionDao, mitValcheckitemMTempDao, padCheckaccomplishInfoDao, termStc
            );


            // 复制采集项表 -> 追溯 采集项表
            // createZsMstCollectionexerecordInfoTemp(collectionDao, mitValcheckitemMTempDao, padCheckaccomplishInfoDao, prevVisitId, termStc, mitValterMTemp.getId());

            // 复制 终端追溯竞品附表 临时表
            createZsMitValcmpotherMTemp(mitValcmpotherMTempDao, mstVisitM, mitValterMTemp.getId());

            //  复制 追溯产品组合表
            createMitGroupproMTemp(mitValgroupproMTempDao, term, mitValterMTemp.getId());

            // 复制追溯台账主表 临时表
            createMitValaddaccountMTemp(mitValaddaccountMTempDao, mitValaddaccountproMTempDao, term, mitValterMTemp.getId());

            // 终端追溯协议主表 临时表
            // createMitValagreeMTemp(mstAgreeTmpDao, mitValagreeMTempDao, term, mitValterMTemp.getId());

            List<MstAgreeTmp> list = new ArrayList<MstAgreeTmp>();
            QueryBuilder<MstAgreeTmp, String> qb = mstAgreeTmpDao.queryBuilder();
            Where<MstAgreeTmp, String> where = qb.where();
            where.eq("terminalkey", term.getTerminalkey());
            list = qb.query();

            MitValagreeMTemp mitValagreeMTemp = null;
            if(list.size()>0){
                MstAgreeTmp mstAgreeTmp=list.get(0);
                mitValagreeMTemp = new MitValagreeMTemp();
                mitValagreeMTemp.setId(FunUtil.getUUID());
                mitValagreeMTemp.setValterid(mitValterMTemp.getId());// 追溯主表id
                mitValagreeMTemp.setAgreecode(mstAgreeTmp.getAgreecd());//协议编码
                mitValagreeMTemp.setAgencyname(mstAgreeTmp.getAgencyname());//供货商名称
                mitValagreeMTemp.setAgencykey(mstAgreeTmp.getSuppierid());//供货商ID
                mitValagreeMTemp.setMoneyagency(mstAgreeTmp.getPaymentagencyname());//垫付费用经销商
                mitValagreeMTemp.setMoneyagencykey(mstAgreeTmp.getDealid());//垫付费用经销商ID
                mitValagreeMTemp.setStartdate(mstAgreeTmp.getStartdate());//开始时间
                //mitValagreeMTemp.setStartdateflag(mstAgreeTmp.get) ;//开始时间是否正确
                //mitValagreeMTemp.setStartdateremark(mstAgreeTmp.get);//开始时间备注
                mitValagreeMTemp.setEnddate(mstAgreeTmp.getEnddate());//结束时间
                //mitValagreeMTemp.setEnddateflag(mstAgreeTmp.get) ;//结束时间是否正确
                //mitValagreeMTemp.setEnddateremark(mstAgreeTmp.get) ;//结束时间备注
                mitValagreeMTemp.setPaytype(mstAgreeTmp.getPaytype());//支付周期类型
                mitValagreeMTemp.setContact(mstAgreeTmp.getSigner());//联系人
                mitValagreeMTemp.setMobile(mstAgreeTmp.getMobile());//联系电话
                mitValagreeMTemp.setNotes(mstAgreeTmp.getNotes());//主要条款
                //mitValagreeMTemp.setNotesflag(mstAgreeTmp.get) ;//主要条款是否正确
                //mitValagreeMTemp.setNotesremark(mstAgreeTmp.get) ;//主要条款备注
                mitValagreeMTempDao.create(mitValagreeMTemp);
            }


            // 终端追溯协议对付信息表 临时表
            if(mitValagreeMTemp!=null){
                createMitValagreedetailMTemp(mstAgreeDetailTmpDao, mitValagreedetailMTempDao, term, mitValterMTemp.getId(),mitValagreeMTemp.getId());
            }


            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "复制数据出错", e);
            try {
                connection.rollback(null);
                //ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

        List<String> keys = new ArrayList<>();
        keys.add(mstVisitM.getVisitkey());// 业代上次拜访主键
        keys.add(mitValterMTemp.getId());//
        keys.add(prevVisitId);// 业代上次拜访主键
        return keys;
    }

    // 终端追溯协议对付信息表 临时表
    private void createMitValagreedetailMTemp(Dao<MstAgreeDetailTmp, String> mstAgreeDetailTmpDao,
                                              Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao,
                                              //MstTerminalinfoMCart term,
                                              // MstTerminalinfoM term,
                                              MstTerminalinfoMZsCart term,
                                              String valterid,
                                              String mitValagreeMId) {

        List<MstAgreeDetailTmp> list = new ArrayList<MstAgreeDetailTmp>();
        try {
            QueryBuilder<MstAgreeDetailTmp, String> qb = mstAgreeDetailTmpDao.queryBuilder();
            Where<MstAgreeDetailTmp, String> where = qb.where();
            where.eq("terminalkey", term.getTerminalkey());
            list = qb.query();

            MitValagreedetailMTemp mitValagreedetailMTemp;
            for (MstAgreeDetailTmp mstAgreeDetailTmp : list) {
                mitValagreedetailMTemp = new MitValagreedetailMTemp();
                mitValagreedetailMTemp.setId(FunUtil.getUUID());
                mitValagreedetailMTemp.setValterid(valterid);// 追溯主表id
                //mitValagreedetailMTemp.setValagreeid(mstAgreeDetailTmp.getAgreeid());//终端追溯协议主表ID
                mitValagreedetailMTemp.setValagreeid(mitValagreeMId);//终端追溯协议主表ID
                mitValagreedetailMTemp.setProkey(mstAgreeDetailTmp.getProductkey());//产品KEY
                mitValagreedetailMTemp.setProname(mstAgreeDetailTmp.getProname());//产品名称
                mitValagreedetailMTemp.setCashtype(mstAgreeDetailTmp.getCashtype());//对付形式
                mitValagreedetailMTemp.setCommoney(mstAgreeDetailTmp.getAmount());//实际公司承担金额
                mitValagreedetailMTemp.setTrunnum(mstAgreeDetailTmp.getQty());//实际数量
                mitValagreedetailMTemp.setPrice(mstAgreeDetailTmp.getPrice());//单价
                mitValagreedetailMTemp.setCashdate(mstAgreeDetailTmp.getCashdate());//兑付日期
                //mitValagreedetailMTemp.setAgreedetailflag(mstAgreeDetailTmp.get);//正确与否
                //mitValagreedetailMTemp.setErroritem(mstAgreeDetailTmp.get);//错误项
                //mitValagreedetailMTemp.setRemarks(mstAgreeDetailTmp.get);//备注

                /*mitValagreedetailMTemp.setUploadflag(mstAgreeDetailTmp.get);//
                mitValagreedetailMTemp.setPadisconsistent(mstAgreeDetailTmp.get);//*/

                mitValagreedetailMTempDao.create(mitValagreedetailMTemp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 终端追溯协议主表 临时表
    private void createMitValagreeMTemp(Dao<MstAgreeTmp, String> mstAgreeTmpDao,
                                        Dao<MitValagreeMTemp, String> mitValagreeMTempDao,
                                        MstTerminalinfoMCart term,
                                        String valterid) {


        try {
            List<MstAgreeTmp> list = new ArrayList<MstAgreeTmp>();
            QueryBuilder<MstAgreeTmp, String> qb = mstAgreeTmpDao.queryBuilder();
            Where<MstAgreeTmp, String> where = qb.where();
            where.eq("terminalkey", term.getTerminalkey());
            list = qb.query();

            MitValagreeMTemp mitValagreeMTemp;
            if(list.size()>0){
                MstAgreeTmp mstAgreeTmp=list.get(0);
                mitValagreeMTemp = new MitValagreeMTemp();
                mitValagreeMTemp.setId(FunUtil.getUUID());
                mitValagreeMTemp.setValterid(valterid);// 追溯主表id
                mitValagreeMTemp.setAgreecode(mstAgreeTmp.getAgreecd());//协议编码
                mitValagreeMTemp.setAgencyname(mstAgreeTmp.getAgencyname());//供货商名称
                mitValagreeMTemp.setAgencykey(mstAgreeTmp.getSuppierid());//供货商ID
                mitValagreeMTemp.setMoneyagency(mstAgreeTmp.getPaymentagencyname());//垫付费用经销商
                mitValagreeMTemp.setMoneyagencykey(mstAgreeTmp.getDealid());//垫付费用经销商ID
                mitValagreeMTemp.setStartdate(mstAgreeTmp.getStartdate());//开始时间
                //mitValagreeMTemp.setStartdateflag(mstAgreeTmp.get) ;//开始时间是否正确
                //mitValagreeMTemp.setStartdateremark(mstAgreeTmp.get);//开始时间备注
                mitValagreeMTemp.setEnddate(mstAgreeTmp.getEnddate());//结束时间
                //mitValagreeMTemp.setEnddateflag(mstAgreeTmp.get) ;//结束时间是否正确
                //mitValagreeMTemp.setEnddateremark(mstAgreeTmp.get) ;//结束时间备注
                mitValagreeMTemp.setPaytype(mstAgreeTmp.getPaytype());//支付周期类型
                mitValagreeMTemp.setContact(mstAgreeTmp.getSigner());//联系人
                mitValagreeMTemp.setMobile(mstAgreeTmp.getMobile());//联系电话
                mitValagreeMTemp.setNotes(mstAgreeTmp.getNotes());//主要条款
                //mitValagreeMTemp.setNotesflag(mstAgreeTmp.get) ;//主要条款是否正确
                //mitValagreeMTemp.setNotesremark(mstAgreeTmp.get) ;//主要条款备注

                /*mitValagreeMTemp.setCreuser(mstAgreeTmp.get)  ;//
                mitValagreeMTemp.setCredate(mstAgreeTmp.get)  ;//
                mitValagreeMTemp.setUpdateuser(mstAgreeTmp.get) ;//
                mitValagreeMTemp.setUpdatedate(mstAgreeTmp.get) ;//

                mitValagreeMTemp.setUploadflag(mstAgreeTmp.get) ;//
                mitValagreeMTemp.setPadisconsistent(mstAgreeTmp.get);//*/

                mitValagreeMTempDao.create(mitValagreeMTemp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 复制追溯台账主表 临时表   复制追溯台账附表 临时表
    private void createMitValaddaccountMTemp(Dao<MitValaddaccountMTemp, String> mitValaddaccountMTempDao,
                                             Dao<MitValaddaccountproMTemp, String> mitValaddaccountproMTempDao,
                                             //MstTerminalinfoMCart term,
                                             // MstTerminalinfoM term,
                                             MstTerminalinfoMZsCart term,
                                             String id) {

        List<MstTermLedgerInfo> list = new ArrayList<MstTermLedgerInfo>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTermLedgerInfoDao ledgerInfoDao = helper.getDao(MstTermLedgerInfo.class);
            QueryBuilder<MstTermLedgerInfo, String> qb = ledgerInfoDao.queryBuilder();
            Where<MstTermLedgerInfo, String> where = qb.where();
            where.eq("terminalkey", term.getTerminalkey());
            qb.orderBy("productkey", true);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 组建成界面显示所需要的数据结构
        List<ZsTzItemIndex> proIndexLst = new ArrayList<ZsTzItemIndex>();
        ZsTzItemIndex zsTzItemIndex = new ZsTzItemIndex();//
        String productkey = "";
        MitValaddaccountproMTemp indexValueItem;

        for (MstTermLedgerInfo ledgerInfo : list) {
            if (!productkey.equals(ledgerInfo.getProductkey())) {
                zsTzItemIndex = new ZsTzItemIndex();
                zsTzItemIndex.setId(FunUtil.getUUID());
                zsTzItemIndex.setValsupplyid(id);// 终端追溯终端表ID
                zsTzItemIndex.setValagencyid(ledgerInfo.getAgencykey());//经销商ID
                zsTzItemIndex.setValagencyname(ledgerInfo.getAgencyname());// 经销商名称
                zsTzItemIndex.setValterid(ledgerInfo.getTerminalkey());// 终端ID
                zsTzItemIndex.setValtername(ledgerInfo.getTerminalname());// 终端名称
                zsTzItemIndex.setValproid(ledgerInfo.getProductkey());//产品ID
                zsTzItemIndex.setValproname(ledgerInfo.getProname());//  产品名称
                zsTzItemIndex.setUploadflag("0");//
                zsTzItemIndex.setPadisconsistent("0");//
                zsTzItemIndex.setIndexValueLst(new ArrayList<MitValaddaccountproMTemp>());
                proIndexLst.add(zsTzItemIndex);
                productkey = ledgerInfo.getProductkey();
            }
            // 复制附表
            indexValueItem = new MitValaddaccountproMTemp();
            indexValueItem.setId(FunUtil.getUUID());//
            indexValueItem.setValterid(id);// 追溯主表ID
            indexValueItem.setValaddaccountid(zsTzItemIndex.getId());// 终端进货台账主表ID
            indexValueItem.setValprotime(ledgerInfo.getPurchasetime());// 台账日期
            //indexValueItem.setValpronumfalg();// 进货量正确与否
            indexValueItem.setValpronum(ledgerInfo.getPurchase());// 进货量原值
            indexValueItem.setValprotruenum(ledgerInfo.getPurchase());// 进货量正确值
            // indexValueItem.setValproremark();// 备注
            indexValueItem.setUploadflag("0");//
            indexValueItem.setPadisconsistent("0");//
            zsTzItemIndex.getIndexValueLst().add(indexValueItem);
        }
        try {
            MitValaddaccountMTemp valaddaccountMTemp;
            for (ZsTzItemIndex tzItemIndex : proIndexLst) {
                valaddaccountMTemp = new MitValaddaccountMTemp();
                valaddaccountMTemp.setId(tzItemIndex.getId());
                valaddaccountMTemp.setValsupplyid(tzItemIndex.getValsupplyid());// 终端追溯终端表ID
                valaddaccountMTemp.setValagencyid(tzItemIndex.getValagencyid());//经销商ID
                valaddaccountMTemp.setValagencyname(tzItemIndex.getValagencyname());// 经销商名称
                valaddaccountMTemp.setValterid(tzItemIndex.getValterid());// 终端ID
                valaddaccountMTemp.setValtername(tzItemIndex.getValtername());// 终端名称
                valaddaccountMTemp.setValproid(tzItemIndex.getValproid());//产品ID
                valaddaccountMTemp.setValproname(tzItemIndex.getValproname());//  产品名称
                valaddaccountMTemp.setUploadflag("0");//
                valaddaccountMTemp.setPadisconsistent("0");//
                mitValaddaccountMTempDao.create(valaddaccountMTemp);
                for (MitValaddaccountproMTemp valaddaccountproMTemp : tzItemIndex.getIndexValueLst()) {
                    mitValaddaccountproMTempDao.create(valaddaccountproMTemp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 复制追溯产品组合表
    private void createMitGroupproMTemp(Dao<MitValgroupproMTemp, String> mitValgroupproMTempDao,
                                        //MstTerminalinfoMCart term,
                                        MstTerminalinfoMZsCart term,
                                        String valterid) {

        List<MstGroupproductM> listvo = queryZsMstGroupproductM(term.getTerminalcode());
        MitValgroupproMTemp voTemp = new MitValgroupproMTemp();

        try {
            if (listvo.size() > 0) {
                MstGroupproductM mstGroupproductM = listvo.get(0);
                voTemp.setId(FunUtil.getUUID());
                voTemp.setValterid(valterid);// 追溯主键
                voTemp.setValgrouppro(mstGroupproductM.getIfrecstand());// 产品组合是否达标原值
                /*voTemp.setValgroupproflag();// 产品组合是否达标正确与否
                voTemp.setValgroupproremark();// 产品组合是否达标备注*/
                voTemp.setGproductid(mstGroupproductM.getGproductid());
                voTemp.setTerminalcode(mstGroupproductM.getTerminalcode());
                voTemp.setPadisconsistent("0");// 未上传
                mitValgroupproMTempDao.create(voTemp);
            } else {
                voTemp.setId(FunUtil.getUUID());
                voTemp.setValterid(valterid);// 追溯主键
                voTemp.setPadisconsistent("0");// 未上传
                mitValgroupproMTempDao.create(voTemp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 复制追溯聊竞品附表 临时表
    private void createZsMitValcmpotherMTemp(Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao,
                                             MstVisitM mstVisitM, String valterid) {

        try {
            MitValcmpotherMTemp mitValcmpotherMTemp = new MitValcmpotherMTemp();
            mitValcmpotherMTemp.setId(FunUtil.getUUID());
            mitValcmpotherMTemp.setValterid(valterid);
            mitValcmpotherMTemp.setValistruecmpval(mstVisitM.getIscmpcollapse());
            mitValcmpotherMTempDao.create(mitValcmpotherMTemp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 从临时表中 获取某次拜访的我品的进销存数据情况
     *
     * @param //helper
     * @param previsitId 拜访主键
     * @return
     */
    public void queryInvocingValSupplyTemp(Dao<MitValsupplyMTemp, String> mitValsupplyMTempDao,
                                           String previsitId, String termKey, String mitValterMTempKey) {

        List<XtInvoicingStc> lst = new ArrayList<XtInvoicingStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            lst = dao.queryInvocingProByTemp(helper, previsitId, termKey);

            MitValsupplyMTemp mitValsupplyMTemp;
            for (XtInvoicingStc xtInvoicingStc : lst) {
                mitValsupplyMTemp = new MitValsupplyMTemp();
                mitValsupplyMTemp.setId(FunUtil.getUUID());
                mitValsupplyMTemp.setValterid(mitValterMTempKey);// 追溯主表id
                mitValsupplyMTemp.setValsuplyid(xtInvoicingStc.getAsupplykey());// 供货关系id
                mitValsupplyMTemp.setValaddagencysupply("N");// 是否新增供货关系
                mitValsupplyMTemp.setValsqd(xtInvoicingStc.getChannelPrice());// 渠道价
                mitValsupplyMTemp.setValsls(xtInvoicingStc.getSellPrice());// 零售价
                mitValsupplyMTemp.setValsdd(xtInvoicingStc.getPrevNum());//订单量//
                mitValsupplyMTemp.setValsrxl(xtInvoicingStc.getDaySellNum());//日销量
                mitValsupplyMTemp.setValsljk(xtInvoicingStc.getAddcard());//累计卡
                mitValsupplyMTemp.setValter(termKey);//终端
                mitValsupplyMTemp.setValpro(xtInvoicingStc.getProId());//产品key
                mitValsupplyMTemp.setValproname(xtInvoicingStc.getProName());//产品name
                mitValsupplyMTemp.setValagency(xtInvoicingStc.getAgencyId());//经销商key
                mitValsupplyMTemp.setValagencyname(xtInvoicingStc.getAgencyName());//经销商name

                mitValsupplyMTemp.setValagencysupplyqd(xtInvoicingStc.getChannelPrice());//供货关系正确渠道价,要展示并上传所以这里直接使用业代的
                mitValsupplyMTemp.setValagencysupplyls(xtInvoicingStc.getSellPrice());//供货关系正确零售价,要展示并上传所以这里直接使用业代的

                /*mitValsupplyMTemp.setValagencysupplyflag();//供货关系正确与否
                mitValsupplyMTemp.setValproerror();//品项有误
                mitValsupplyMTemp.setValagencyerror();//经销商有误
                mitValsupplyMTemp.setValtrueagency();//正确的经销商
                mitValsupplyMTemp.setValdataerror();//数据有误
                mitValsupplyMTemp.setValiffleeing();//是否窜货

                mitValsupplyMTemp.setValagencysupplydd();//供货关系正确订单量
                mitValsupplyMTemp.setValagencysupplysrxl();//供货关系正确日销量
                mitValsupplyMTemp.setValagencysupplyljk();//供货关系正确累计卡
                mitValsupplyMTemp.setValagencysupplyremark();//供货关系备注*/
                mitValsupplyMTempDao.create(mitValsupplyMTemp);
            }

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }
    }


    /**
     * 从临时表中 获取某次拜访的竞品数据情况
     *
     * @param //helper
     * @param previsitId 拜访主键
     * @return
     */
    public void queryChatVieValVieSupplyTemp(Dao<MitValcmpMTemp, String> mitValcmpMTempDao,
                                             String previsitId, String termKey, String mitValterMTempKey) {

        List<XtChatVieStc> lst = new ArrayList<XtChatVieStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            lst = dao.queryZsVieProByTemp(helper, previsitId, termKey);

            MitValcmpMTemp mitValcmpMTemp;
            for (XtChatVieStc xtInvoicingStc : lst) {
                mitValcmpMTemp = new MitValcmpMTemp();
                mitValcmpMTemp.setId(FunUtil.getUUID());
                mitValcmpMTemp.setValterid(mitValterMTempKey);// 追溯主表id
                mitValcmpMTemp.setValaddagencysupply("N");// 是否新增供货关系
                mitValcmpMTemp.setValagencysupplyid(xtInvoicingStc.getCmpsupplykey());// 供货关系ID
                mitValcmpMTemp.setValcmpjdj(xtInvoicingStc.getChannelPrice());// 竞品进店价
                mitValcmpMTemp.setValcmplsj(xtInvoicingStc.getSellPrice());//竞品零售价
                mitValcmpMTemp.setValcmpsales(xtInvoicingStc.getMonthSellNum());//竞品销量
                mitValcmpMTemp.setValcmpkc(xtInvoicingStc.getCurrStore());//竞品库存
                mitValcmpMTemp.setValcmpremark(xtInvoicingStc.getDescribe());//竞品描述
                mitValcmpMTemp.setValcmpagency(xtInvoicingStc.getAgencyName());//竞品供货商
                mitValcmpMTemp.setValcmpid(xtInvoicingStc.getProId());//竞品ID
                mitValcmpMTemp.setValcmpname(xtInvoicingStc.getProName());//竞品ID
                mitValcmpMTemp.setValiscmpter(termKey);//终端
                /*mitValsupplyMTemp.setValagencysupplyflag();//供货关系正确与否
                mitValsupplyMTemp.setValproerror();//品项有误
                mitValsupplyMTemp.setValagencyerror();//经销商有误
                mitValsupplyMTemp.setValtrueagency();//正确的经销商
                mitValsupplyMTemp.setValdataerror();//数据有误
                mitValsupplyMTemp.setValiffleeing();//是否窜货
                mitValsupplyMTemp.setValagencysupplyqd();//供货关系正确渠道价
                mitValsupplyMTemp.setValagencysupplyls();//供货关系正确零售价
                mitValsupplyMTemp.setValagencysupplydd();//供货关系正确订单量
                mitValsupplyMTemp.setValagencysupplysrxl();//供货关系正确日销量
                mitValsupplyMTemp.setValagencysupplyljk();//供货关系正确累计卡
                mitValsupplyMTemp.setValagencysupplyremark();//供货关系备注*/
                mitValcmpMTempDao.create(mitValcmpMTemp);
            }
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }
    }

    //

    /***
     * 初始化拜访产品表
     * @param termid
     * @param Vistid
     * @param agencyDao
     * @param //proDao
     */
    private void addProductInfoToTemp(String termid, String Vistid,
                                      Dao<MstAgencysupplyInfo, String> agencyDao,
                                      Dao<MstVistproductInfoTemp, String> proTempDao) {
        try {
            List<MstAgencysupplyInfo> valueLst = getAgencySupplyInfoList(termid, agencyDao);
            if (!CheckUtil.IsEmpty(valueLst)) {
                MstVistproductInfoTemp itemTemp;
                for (MstAgencysupplyInfo item : valueLst) {
                    itemTemp = new MstVistproductInfoTemp();
                    itemTemp.setRecordkey(FunUtil.getUUID());
                    itemTemp.setVisitkey(Vistid);
                    itemTemp.setProductkey(item.getProductkey());
                    itemTemp.setAgencykey(item.getUpperkey());
                    itemTemp.setCredate(DateUtil.getNow());
                    itemTemp.setUpdatetime(DateUtil.getNow());
                    //itemTemp.setCreuser(ConstValues.loginSession.getUserCode());
                    itemTemp.setCreuser(PrefUtils.getString(context, "userCode", ""));
                    //itemTemp.setUpdateuser(ConstValues.loginSession.getUserCode());
                    itemTemp.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                    itemTemp.setPurcprice(0.00);
                    itemTemp.setRetailprice(0.00);
                    itemTemp.setPronum(0.00);
                    itemTemp.setCurrnum(0.00);
                    itemTemp.setSalenum(0.00);
                    proTempDao.create(itemTemp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * 通过终端获取此终端供货关系
     * @param termid
     * @param agencyDao
     * @return
     */
    private List<MstAgencysupplyInfo> getAgencySupplyInfoList(String termid,
                                                              Dao<MstAgencysupplyInfo, String> agencyDao) {
        List<MstAgencysupplyInfo> list = new ArrayList<MstAgencysupplyInfo>();
        try {
            QueryBuilder<MstAgencysupplyInfo, String> qb = agencyDao.queryBuilder();
            Where<MstAgencysupplyInfo, String> where = qb.where();
            where.eq("lowerkey", termid);
            where.and();
            where.eq("status", "0");
            where.and();
            where.eq("lowertype", "2");
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param prevVisitId  上次拜访主键key
     * @param visitDate
     * @param visitMTemp
     * @param valueDao
     * @param valueTempDao
     * @throws SQLException
     */
    private void createMstCheckexerecordInfoTemp(String prevVisitId, String visitDate,
                                                 MstVisitMTemp visitMTemp,
                                                 Dao<MstCheckexerecordInfo, String> valueDao,
                                                 Dao<MstCheckexerecordInfoTemp, String> valueTempDao)
            throws SQLException {
        createMstCheckexerecordInfoTemp1(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
        createMstCheckexerecordInfoTemp2(prevVisitId, visitDate, visitMTemp, valueDao, valueTempDao);
    }

    private void createZsMstCheckexerecordInfoTemp(String prevVisitId, String terminalkey, String visitDate,
                                                   // MstVisitMTemp visitMTemp,
                                                   Dao<MstCheckexerecordInfo, String> valueDao,
                                                   Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao, String vidterid,

                                                   Dao<MstCollectionexerecordInfo, String> collectionDao,
                                                   Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao,
                                                   Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao,
                                                   XtTermSelectMStc termStc)
            throws SQLException {
        // 无关
        createZsMstCheckexerecordInfoTemp1(prevVisitId, valueDao, mitValchecktypeMTempDao, vidterid);
        // 复制与产品有关的指标值
        createZsMstCheckexerecordInfoTemp2(prevVisitId, terminalkey, valueDao, mitValchecktypeMTempDao, vidterid,
                collectionDao, mitValcheckitemMTempDao, padCheckaccomplishInfoDao, termStc);
    }


    /**
     * 复制 促销活动终端临时表
     *
     * @param helper
     * @param prevVisitId 上次拜访主键key
     * @param visitDate   这次拜访时间
     * @param visitMTemp  这次拜访记录
     * @param promDao
     * @param promTempDao
     * @param iscomflag
     * @throws SQLException
     */
    private void createMstPromotermInfoTemp(DatabaseHelper helper, String prevVisitId, String visitDate,
                                            MstVisitMTemp visitMTemp, Dao<MstPromotermInfo, String> promDao,
                                            Dao<MstPromotermInfoTemp, String> promTempDao, boolean iscomflag)
            throws SQLException {

        // 复制终端参加活动状态表，MST_PROMOTERM_INFO(终端参加活动信息表)
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");
        QueryBuilder<MstPromotionsM, String> promoQb = helper.getMstPromotionsMDao().queryBuilder();
        Where<MstPromotionsM, String> promoWr = promoQb.where();
        promoWr.and(promoWr.ge("enddate", currDay), promoWr.le("startdate", currDay));
        List<MstPromotionsM> promoLst = promoQb.query();
        List<String> promoIdLst = FunUtil.getPropertyByName(promoLst, "promotkey", String.class);
        List<MstPromotermInfo> promTermLst = promDao.queryForEq("visitkey", prevVisitId);
        if (!CheckUtil.IsEmpty(promTermLst)) {
            MstPromotermInfoTemp promotermInfoTemp = null;
            for (MstPromotermInfo item : promTermLst) {
                if (!ConstValues.FLAG_1.equals(item.getDeleteflag()) && promoIdLst.contains(item.getPtypekey())) {
                    promotermInfoTemp = new MstPromotermInfoTemp();
                    promotermInfoTemp.setPtypekey(item.getPtypekey());
                    promotermInfoTemp.setTerminalkey(item.getTerminalkey());
                    /*if (iscomflag) {// 重复拜访
                        promotermInfoTemp.setIsaccomplish(item.getIsaccomplish());// 是否达成
                        promotermInfoTemp.setRemarks(item.getRemarks());// 达成数组
                    } else {
                        promotermInfoTemp.setIsaccomplish("0");// 第一次拜访默认 未达成
                        promotermInfoTemp.setRemarks(null);
                    }*/
                    promotermInfoTemp.setIsaccomplish(item.getIsaccomplish());// 是否达成
                    promotermInfoTemp.setRemarks(item.getRemarks());// 达成数组

                    promotermInfoTemp.setCreuser(item.getCreuser());
                    promotermInfoTemp.setUpdateuser(item.getUpdateuser());

                    promotermInfoTemp.setRecordkey(FunUtil.getUUID());
                    promotermInfoTemp.setVisitkey(visitMTemp.getVisitkey());
                    promotermInfoTemp.setStartdate(visitDate);
                    promotermInfoTemp.setSisconsistent(ConstValues.FLAG_0);
                    promotermInfoTemp.setScondate(null);
                    promotermInfoTemp.setPadisconsistent(ConstValues.FLAG_0);
                    promotermInfoTemp.setPadcondate(null);
                    promotermInfoTemp.setDeleteflag(ConstValues.FLAG_0);
                    promotermInfoTemp.setCredate(null);
                    promotermInfoTemp.setUpdatetime(null);
                    promTempDao.create(promotermInfoTemp);
                }
            }
        }
    }


    /**
     * 复制追溯 活动终端表
     *
     * @param helper
     * @param promDao
     * @throws SQLException
     */
    private void createMitPromotermInfoTemp(DatabaseHelper helper, String valterid,
                                            Dao<MstPromotermInfo, String> promDao,
                                            Dao<MitValpromotionsMTemp, String> mitValpromotionsMTempDao)
            throws SQLException {

        // 复制终端参加活动状态表，MST_PROMOTERM_INFO(终端参加活动信息表)
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");
        QueryBuilder<MstPromotionsM, String> promoQb = helper.getMstPromotionsMDao().queryBuilder();
        Where<MstPromotionsM, String> promoWr = promoQb.where();
        promoWr.and(promoWr.ge("enddate", currDay), promoWr.le("startdate", currDay));
        List<MstPromotionsM> promoLst = promoQb.query();
        List<String> promoIdLst = FunUtil.getPropertyByName(promoLst, "promotkey", String.class);
        List<MstPromotermInfo> promTermLst = promDao.queryForEq("visitkey", prevVisitId);

        if (!CheckUtil.IsEmpty(promTermLst)) {
            MitValpromotionsMTemp promotermInfoTemp = null;
            for (MstPromotermInfo item : promTermLst) {
                if (!ConstValues.FLAG_1.equals(item.getDeleteflag()) && promoIdLst.contains(item.getPtypekey())) {
                    promotermInfoTemp = new MitValpromotionsMTemp();
                    promotermInfoTemp.setId(FunUtil.getUUID());//
                    promotermInfoTemp.setValterid(valterid);// 终端追溯主表ID
                    promotermInfoTemp.setValpromotionsid(item.getPtypekey());//    促销活动主键/ valpromotionsid
                    promotermInfoTemp.setTerminalkey(item.getTerminalkey());
                    promotermInfoTemp.setValistrue(item.getIsaccomplish());// 是否达成原值
                    promotermInfoTemp.setValistruenum(item.getRemarks());// 达成数组
                    promotermInfoTemp.setVisitkey(item.getVisitkey());// 拜访主键
                    //promotermInfoTemp.setCreuser(item.getCreuser());
                    //promotermInfoTemp.setUpdateuser(item.getUpdateuser());
                    promotermInfoTemp.setCredate(null);
                    promotermInfoTemp.setUpdatedate(null);
                    mitValpromotionsMTempDao.create(promotermInfoTemp);
                }
            }
        }
    }

    /**
     * 插入非关联产品指标表
     *
     * @param prevVisitId  上次拜访主键key
     * @param valueDao
     * @param valueTempDao
     * @throws SQLException
     */
    private void createMstCheckexerecordInfoTemp1(String prevVisitId, String visitDate,
                                                  MstVisitMTemp visitMTemp, Dao<MstCheckexerecordInfo, String> valueDao,
                                                  Dao<MstCheckexerecordInfoTemp, String> valueTempDao)
            throws SQLException {
        // 复制指标结果表，MST_CHECKEXERECORD_INFO(拜访指标执行记录表)
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("visitkey", prevVisitId);
        valueWr.and();
        valueWr.isNull("productkey");
        List<MstCheckexerecordInfo> valueLst = valueQb.query();
        if (!CheckUtil.IsEmpty(valueLst)) {
            MstCheckexerecordInfoTemp itemTemp;
            for (MstCheckexerecordInfo item : valueLst) {
                itemTemp = new MstCheckexerecordInfoTemp();
                itemTemp.setRecordkey(FunUtil.getUUID());
                itemTemp.setVisitkey(visitMTemp.getVisitkey());
                itemTemp.setProductkey(item.getProductkey());
                itemTemp.setCheckkey(item.getCheckkey());
                itemTemp.setAcresult(item.getAcresult());
                itemTemp.setStartdate(item.getStartdate());
                itemTemp.setEnddate(visitDate);
                itemTemp.setTerminalkey(item.getTerminalkey());
                valueTempDao.create(itemTemp);
            }
        }
    }

    /**
     * 插入非关联产品指标表
     *
     * @param prevVisitId 上次拜访主键key
     * @param valueDao
     * @throws SQLException
     */
    private void createZsMstCheckexerecordInfoTemp1(String prevVisitId,
                                                    Dao<MstCheckexerecordInfo, String> valueDao,
                                                    Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao, String vidterid)
            throws SQLException {
        // 复制指标结果表，MST_CHECKEXERECORD_INFO(拜访指标执行记录表)
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("visitkey", prevVisitId);
        valueWr.and();
        valueWr.isNull("productkey");
        List<MstCheckexerecordInfo> valueLst = valueQb.query();

        if (!CheckUtil.IsEmpty(valueLst)) {
            MitValchecktypeMTemp itemTemp;
            for (MstCheckexerecordInfo item : valueLst) {
                itemTemp = new MitValchecktypeMTemp();
                itemTemp.setId(FunUtil.getUUID());
                itemTemp.setValterid(vidterid);
                itemTemp.setVisitkey(prevVisitId);
                itemTemp.setValchecktypeid(item.getRecordkey());// 拉链表主键
                itemTemp.setProductkey(item.getProductkey());// 产品id
                itemTemp.setValchecktype(item.getCheckkey());// 指标id
                itemTemp.setAcresult(item.getAcresult());// 指标值
                itemTemp.setTerminalkey(item.getTerminalkey());
                mitValchecktypeMTempDao.create(itemTemp);
            }
        }
    }

    /**
     * 插入产品指标表
     *
     * @param prevVisitId  上次拜访主键key
     * @param visitDate
     * @param visitMTemp
     * @param valueDao
     * @param valueTempDao
     * @throws SQLException
     */
    private void createMstCheckexerecordInfoTemp2(String prevVisitId, String visitDate,
                                                  MstVisitMTemp visitMTemp, Dao<MstCheckexerecordInfo, String> valueDao,
                                                  Dao<MstCheckexerecordInfoTemp, String> valueTempDao)
            throws SQLException {
        // 复制指标结果表，MST_CHECKEXERECORD_INFO(拜访指标执行记录表)
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("terminalkey", visitMTemp.getTerminalkey());
        valueWr.and();
        valueWr.eq("enddate", "30001201");
        valueWr.and();
        valueWr.isNotNull("productkey");
        valueWr.and();
        valueWr.ne("deleteflag", ConstValues.delFlag);

        valueQb.orderBy("terminalkey", true);
        valueQb.orderBy("checkkey", true);
        valueQb.orderBy("productkey", true);
        valueQb.orderBy("updatetime", false);
        valueQb.orderBy("siebelid", false);
        valueQb.orderBy("acresult", false);
        List<MstCheckexerecordInfo> valueLst = valueQb.query();
        Map<String, MstCheckexerecordInfo> map = new HashMap<String, MstCheckexerecordInfo>();
        if (!CheckUtil.IsEmpty(valueLst)) {
            MstCheckexerecordInfoTemp itemTemp;
            for (MstCheckexerecordInfo item : valueLst) {
                String key = item.getTerminalkey() + "-" + item.getCheckkey() + "-" + item.getProductkey();
                if (!map.containsKey(key)) {
                    map.put(key, item);
                    itemTemp = new MstCheckexerecordInfoTemp();
                    itemTemp.setRecordkey(FunUtil.getUUID());
                    itemTemp.setVisitkey(visitMTemp.getVisitkey());
                    itemTemp.setProductkey(item.getProductkey());
                    itemTemp.setCheckkey(item.getCheckkey());
                    itemTemp.setAcresult(item.getAcresult());
                    itemTemp.setStartdate(item.getStartdate());
                    itemTemp.setEnddate(visitDate);
                    itemTemp.setTerminalkey(item.getTerminalkey());
                    valueTempDao.create(itemTemp);
                } else {
                    item.setDeleteflag(ConstValues.delFlag);
                    item.setSisconsistent(ConstValues.FLAG_0);
                    item.setPadisconsistent(ConstValues.FLAG_0);
                    item.setResultstatus(new BigDecimal(1));
                    valueDao.update(item);
                }
            }
        }
    }

    /**
     * 插入产品指标表
     *
     * @param prevVisitId 上次拜访主键key
     * @param valueDao
     * @throws SQLException
     */
    private void createZsMstCheckexerecordInfoTemp2(String prevVisitId,
                                                    String terminalkey, Dao<MstCheckexerecordInfo, String> valueDao,
                                                    Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao, String vidterid,
                                                    Dao<MstCollectionexerecordInfo, String> collectionDao,
                                                    Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao,
                                                    Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao,
                                                    XtTermSelectMStc termStc)
            throws SQLException {
        // 复制指标结果表，MST_CHECKEXERECORD_INFO(拜访指标执行记录表)
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("terminalkey", terminalkey);
        valueWr.and();
        valueWr.eq("enddate", "30001201");
        valueWr.and();
        valueWr.isNotNull("productkey");
        valueWr.and();
        valueWr.ne("deleteflag", ConstValues.delFlag);

        valueQb.orderBy("terminalkey", true);
        valueQb.orderBy("checkkey", true);
        valueQb.orderBy("productkey", true);
        valueQb.orderBy("updatetime", false);
        valueQb.orderBy("siebelid", false);
        valueQb.orderBy("acresult", false);
        List<MstCheckexerecordInfo> valueLst = valueQb.query();
        Map<String, MstCheckexerecordInfo> map = new HashMap<String, MstCheckexerecordInfo>();
        if (!CheckUtil.IsEmpty(valueLst)) {
            MitValchecktypeMTemp itemTemp = null;
            for (MstCheckexerecordInfo item : valueLst) {
                String key = item.getTerminalkey() + "-" + item.getCheckkey() + "-" + item.getProductkey();
                if (!map.containsKey(key)) {
                    map.put(key, item);
                    itemTemp = new MitValchecktypeMTemp();
                    itemTemp.setId(FunUtil.getUUID());
                    itemTemp.setValterid(vidterid);
                    itemTemp.setVisitkey(prevVisitId);
                    itemTemp.setValchecktypeid(item.getRecordkey());// 拉链表主键
                    itemTemp.setProductkey(item.getProductkey());// 产品id
                    itemTemp.setValchecktype(item.getCheckkey());// 指标id
                    itemTemp.setAcresult(item.getAcresult());// 指标值
                    itemTemp.setTerminalkey(item.getTerminalkey());
                    mitValchecktypeMTempDao.create(itemTemp);
                } else {
                    /*item.setDeleteflag(ConstValues.delFlag);
                    item.setSisconsistent(ConstValues.FLAG_0);
                    item.setPadisconsistent(ConstValues.FLAG_0);
                    item.setResultstatus(new BigDecimal(1));
                    valueDao.update(item);*/
                }
                // 复制采集项表 -> 追溯 采集项表
                createZsMstCollectionexerecordInfoTemp(collectionDao, mitValcheckitemMTempDao, padCheckaccomplishInfoDao, prevVisitId, termStc, vidterid, itemTemp.getId(), itemTemp.getProductkey(), itemTemp.getValchecktype());

            }
        }
    }

    /**
     * 插入产品指标表 (错误)
     *
     * @param prevVisitId 上次拜访主键key
     * @param valueDao
     * @throws SQLException
     */
    private void createZsMstCheckexerecordInfoTemp3(String prevVisitId,
                                                    String terminalkey, Dao<MstCheckexerecordInfo, String> valueDao,
                                                    Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao, String vidterid,
                                                    Dao<MstCollectionexerecordInfo, String> collectionDao,
                                                    Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao,
                                                    Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao)
            throws SQLException {
        // 复制指标结果表，MST_CHECKEXERECORD_INFO(拜访指标执行记录表)
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("terminalkey", terminalkey);
        valueWr.and();
        valueWr.eq("enddate", "30001201");
        valueWr.and();
        valueWr.isNotNull("productkey");
        valueWr.and();
        valueWr.ne("deleteflag", ConstValues.delFlag);

        valueQb.orderBy("terminalkey", true);
        valueQb.orderBy("checkkey", true);
        valueQb.orderBy("productkey", true);
        valueQb.orderBy("updatetime", false);
        valueQb.orderBy("siebelid", false);
        valueQb.orderBy("acresult", false);
        List<MstCheckexerecordInfo> valueLst = valueQb.query();
        Map<String, MstCheckexerecordInfo> map = new HashMap<String, MstCheckexerecordInfo>();
        if (!CheckUtil.IsEmpty(valueLst)) {
            MitValchecktypeMTemp itemTemp = null;
            for (MstCheckexerecordInfo item : valueLst) {
                String key = item.getTerminalkey() + "-" + item.getCheckkey() + "-" + item.getProductkey();
                if (!map.containsKey(key)) {
                    map.put(key, item);
                    itemTemp = new MitValchecktypeMTemp();
                    itemTemp.setId(FunUtil.getUUID());
                    itemTemp.setValterid(vidterid);
                    itemTemp.setVisitkey(prevVisitId);
                    itemTemp.setValchecktypeid(item.getRecordkey());// 拉链表主键
                    itemTemp.setProductkey(item.getProductkey());// 产品id
                    itemTemp.setValchecktype(item.getCheckkey());// 指标id
                    itemTemp.setAcresult(item.getAcresult());// 指标值
                    itemTemp.setTerminalkey(item.getTerminalkey());
                    mitValchecktypeMTempDao.create(itemTemp);
                } else {
                    item.setDeleteflag(ConstValues.delFlag);
                    item.setSisconsistent(ConstValues.FLAG_0);
                    item.setPadisconsistent(ConstValues.FLAG_0);
                    item.setResultstatus(new BigDecimal(1));
                    valueDao.update(item);
                }
            }
        }
    }

    // 复制采集项表
    private void createMstCollectionexerecordInfoTemp(Dao<MstCollectionexerecordInfo, String> collectionDao,
                                                      Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao,
                                                      Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao,
                                                      String prevVisitId, XtTermSelectMStc termStc, MstVisitMTemp visitMTemp) {
        try {
            // 复制指标结果表，MST_COLLECTIONEXERECORD_INFO(拜访指标执行采集项记录表)
            QueryBuilder<MstCollectionexerecordInfo, String> collectionQB = collectionDao.queryBuilder();
            Where<MstCollectionexerecordInfo, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", prevVisitId);
            collectionWhere.and();
            collectionWhere.ne("deleteflag", ConstValues.FLAG_1);
            collectionWhere.and();
            collectionWhere.isNotNull("checkkey");
            collectionWhere.and();
            collectionWhere.ne("checkkey", "");
            collectionQB.orderBy("productkey", true);
            collectionQB.orderBy("colitemkey", true);
            collectionQB.orderBy("checkkey", false);
            collectionQB.orderBy("updatetime", false);
            List<MstCollectionexerecordInfo> collectionList = collectionQB.query();

            if (!CheckUtil.IsEmpty(collectionList)) {
                //数据库查询
                //根据渠道查询拜访指标执行采集项记录表,按采集项+产品放入map
                QueryBuilder<PadCheckaccomplishInfo, String> valueQb = padCheckaccomplishInfoDao.queryBuilder();
                Where<PadCheckaccomplishInfo, String> valueWr = valueQb.where();
                valueWr.eq("minorchannel", termStc.getMinorchannel());
                List<PadCheckaccomplishInfo> valueLst = valueQb.query();
                Map<String, String> colitemProductkeys = new HashMap<String, String>();
                if (valueLst != null) {
                    for (int i = 0; i < valueLst.size(); i++) {
                        colitemProductkeys.put(valueLst.get(i).getColitemkey() + valueLst.get(i).getProductkey(), "");
                    }
                }

                List<String> list = new ArrayList<String>();
                //根据有效的采集项和产品进行复制
                for (MstCollectionexerecordInfo item : collectionList) {
                    String key = item.getColitemkey() + item.getProductkey();
                    MstCollectionexerecordInfoTemp mstCollectionexerecordInfoTemp = null;
                    if (colitemProductkeys.containsKey(key)) {//此产品存在于指标采集项模板
                        if (!list.contains(key)) {//防止重复去重
                            list.add(key);
                            mstCollectionexerecordInfoTemp = new MstCollectionexerecordInfoTemp();
                            mstCollectionexerecordInfoTemp.setColrecordkey(FunUtil.getUUID());
                            mstCollectionexerecordInfoTemp.setVisitkey(visitMTemp.getVisitkey());

                            mstCollectionexerecordInfoTemp.setProductkey(item.getProductkey());
                            mstCollectionexerecordInfoTemp.setCheckkey(item.getCheckkey());
                            mstCollectionexerecordInfoTemp.setColitemkey(item.getColitemkey());
                            mstCollectionexerecordInfoTemp.setAddcount(item.getAddcount());
                            mstCollectionexerecordInfoTemp.setTotalcount(item.getTotalcount());
                            mstCollectionexerecordInfoTemp.setComid(item.getComid());
                            mstCollectionexerecordInfoTemp.setRemarks(item.getRemarks());
                            // mstCollectionexerecordInfoTemp.setFreshness(item.getFreshness());// 新鲜度
                            mstCollectionexerecordInfoTemp.setOrderbyno(item.getOrderbyno());
                            mstCollectionexerecordInfoTemp.setVersion(item.getVersion());
                            mstCollectionexerecordInfoTemp.setCreuser(item.getCreuser());
                            mstCollectionexerecordInfoTemp.setUpdateuser(item.getUpdateuser());
                            mstCollectionexerecordInfoTemp.setBianhualiang(item.getBianhualiang());//变化量 (用于最后上传时 现有量变化量必须填值,才能上传)
                            mstCollectionexerecordInfoTemp.setXianyouliang(item.getXianyouliang());// 现有量 (用于最后上传时 现有量变化量必须填值,才能上传)

                            mstCollectionexerecordInfoTemp.setSisconsistent(ConstValues.FLAG_0);
                            mstCollectionexerecordInfoTemp.setScondate(null);
                            mstCollectionexerecordInfoTemp.setPadisconsistent(ConstValues.FLAG_0);
                            mstCollectionexerecordInfoTemp.setPadcondate(null);
                            mstCollectionexerecordInfoTemp.setDeleteflag(ConstValues.FLAG_0);
                            mstCollectionexerecordInfoTemp.setCredate(null);
                            mstCollectionexerecordInfoTemp.setUpdatetime(null);
                            collectionTempDao.create(mstCollectionexerecordInfoTemp);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 复制采集项表 -> 追溯采集项表
    private void createZsMstCollectionexerecordInfoTemp(Dao<MstCollectionexerecordInfo, String> collectionDao,
                                                        Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao,
                                                        Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao,
                                                        String prevVisitId, XtTermSelectMStc termStc, String vidterid,
                                                        String recordkey, String productkey, String checkkey) {
        try {
            // 复制指标结果表，MST_COLLECTIONEXERECORD_INFO(拜访指标执行采集项记录表)
            QueryBuilder<MstCollectionexerecordInfo, String> collectionQB = collectionDao.queryBuilder();
            Where<MstCollectionexerecordInfo, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", prevVisitId);
            collectionWhere.and();
            collectionWhere.eq("productkey", productkey);
            collectionWhere.and();
            collectionWhere.eq("checkkey", checkkey);
            collectionWhere.and();
            collectionWhere.ne("deleteflag", ConstValues.FLAG_1);
            collectionWhere.and();
            collectionWhere.isNotNull("checkkey");
            collectionWhere.and();
            collectionWhere.ne("checkkey", "");
            collectionQB.orderBy("productkey", true);
            collectionQB.orderBy("colitemkey", true);
            collectionQB.orderBy("checkkey", false);
            collectionQB.orderBy("updatetime", false);
            List<MstCollectionexerecordInfo> collectionList = collectionQB.query();

            //if (!CheckUtil.IsEmpty(collectionList)) {
            if (collectionList.size()>0) {
                //数据库查询
                //根据渠道查询拜访指标执行采集项记录表,按采集项+产品放入map
                QueryBuilder<PadCheckaccomplishInfo, String> valueQb = padCheckaccomplishInfoDao.queryBuilder();
                Where<PadCheckaccomplishInfo, String> valueWr = valueQb.where();
                valueWr.eq("minorchannel", termStc.getMinorchannel());
                List<PadCheckaccomplishInfo> valueLst = valueQb.query();
                Map<String, String> colitemProductkeys = new HashMap<String, String>();
                if (valueLst != null) {
                    for (int i = 0; i < valueLst.size(); i++) {
                        colitemProductkeys.put(valueLst.get(i).getColitemkey() + valueLst.get(i).getProductkey(), "");
                    }
                }

                List<String> list = new ArrayList<String>();
                //根据有效的采集项和产品进行复制
                for (MstCollectionexerecordInfo item : collectionList) {
                    String key = item.getColitemkey() + item.getProductkey();

                    MitValcheckitemMTemp mitValcheckitemMTemp = null;
                    if (colitemProductkeys.containsKey(key)) {//此产品存在于指标采集项模板
                        if (!list.contains(key)) {//防止重复去重
                            list.add(key);
                            mitValcheckitemMTemp = new MitValcheckitemMTemp();
                            mitValcheckitemMTemp.setId(FunUtil.getUUID());
                            mitValcheckitemMTemp.setValterid(vidterid);// 追溯表主键
                            mitValcheckitemMTemp.setValchecktypeid(recordkey);// 督导拉链表主键
                            mitValcheckitemMTemp.setVisitkey(prevVisitId);
                            mitValcheckitemMTemp.setValitemid(item.getColrecordkey());// 采集项表主键
                            mitValcheckitemMTemp.setColitemkey(item.getColitemkey());// 采集项名称
                            mitValcheckitemMTemp.setCheckkey(item.getCheckkey());// 指标 varchar2(36) null,
                            //mitValcheckitemMTemp.setAddcount(item.getAddcount());//  变化量varchar2(36) null,
                            //mitValcheckitemMTemp.setTotalcount(item.getTotalcount());// 现有量 varchar2(36) null,
                            mitValcheckitemMTemp.setProductkey(item.getProductkey()); // 产品key varchar2(36) null,
                            // Double sd = item.getTotalcount();
                            double total = FunUtil.isBlankOrNullToDouble(item.getTotalcount());
                            double add = FunUtil.isBlankOrNullToDouble(item.getAddcount());
                            double plus = add + total;
                            mitValcheckitemMTemp.setValitem(plus + "");// 采集项原值结果量
                            // mitValcheckitemMTemp.setValitemval(item.getAddcount() + item.getTotalcount() + "");// 采集项正确结果量
                            mitValcheckitemMTemp.setCreuser(item.getCreuser());
                            mitValcheckitemMTemp.setUpdateuser(item.getUpdateuser());
                            mitValcheckitemMTemp.setCredate(null);
                            mitValcheckitemMTemp.setUpdatedate(null);
                            mitValcheckitemMTempDao.create(mitValcheckitemMTemp);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 复制产品组合是否达标临时表
    private void createMstGroupproductMTemp(MstTerminalinfoMTemp termTemp, String visitkey) {
        // 从产品组合是否达标表 搂取该终端的一条数据,今天之前的包含今天 判断该终端产品组合是否达标
        // 先查询MstGroupproductM表  判断终端该指标是否达标
        List<MstGroupproductM> listvo = queryMstGroupproductM(termTemp.getTerminalcode(), DateUtil.getDateTimeStr(7) + " 00:00:00");

        MstGroupproductM vo = null;
        MstGroupproductMTemp voTemp = new MstGroupproductMTemp();

        // 有上次数据
        if (listvo.size() > 0) {
            // 数据是今天创建的
            if ((DateUtil.getDateTimeStr(7) + " 00:00:00").equals(listvo.get(0).getStartdate())) {
                vo = listvo.get(0);
                voTemp.setGproductid(vo.getGproductid());
                voTemp.setTerminalcode(vo.getTerminalcode());
                voTemp.setTerminalname(vo.getTerminalname());
                voTemp.setIfrecstand(vo.getIfrecstand());// 取上次的
                voTemp.setStartdate(vo.getStartdate());
                voTemp.setEnddate(vo.getEnddate());
                voTemp.setCreateusereng(vo.getCreateusereng());
                voTemp.setCreatedate(vo.getCreatedate());
                voTemp.setUpdateusereng(vo.getUpdateusereng());
                voTemp.setUpdatetime(vo.getUpdatetime());
                voTemp.setUpdateusereng(vo.getUpdateusereng());
                voTemp.setUploadflag("0");// 不上传
                voTemp.setPadisconsistent("0");// 未上传
                voTemp.setVisitkey(visitkey);
                createMstGroupproductM(voTemp);
                // 之前创建的一条数据
            } else {
                vo = listvo.get(0);// 先复制再重新赋值
                voTemp.setGproductid(FunUtil.getUUID());
                voTemp.setTerminalcode(termTemp.getTerminalcode());
                voTemp.setTerminalname(termTemp.getTerminalname());
                voTemp.setIfrecstand(vo.getIfrecstand());// 取上次的
                voTemp.setStartdate(DateUtil.getDateTimeStr(7) + " 00:00:00");
                voTemp.setEnddate("3000-12-01" + " 00:00:00");
                voTemp.setCreateusereng(PrefUtils.getString(context, "userGongHao", "21000"));
                voTemp.setCreatedate(DateUtil.getDateTimeStr(8));
                voTemp.setUpdateusereng(PrefUtils.getString(context, "userGongHao", "21000"));
                voTemp.setUpdatetime(DateUtil.getDateTimeStr(8));
                voTemp.setUpdateusereng(PrefUtils.getString(context, "userGongHao", "21000"));
                voTemp.setUploadflag("0");// 不上传
                voTemp.setPadisconsistent("0");// 未上传
                voTemp.setVisitkey(visitkey);
                createMstGroupproductM(voTemp);
            }
        }
        // 没有上次数据
        else {
            // 插入一条今天新数据
            voTemp.setGproductid(FunUtil.getUUID());
            voTemp.setTerminalcode(termTemp.getTerminalcode());
            voTemp.setTerminalname(termTemp.getTerminalname());
            voTemp.setIfrecstand("N");// 未达标
            voTemp.setStartdate(DateUtil.getDateTimeStr(7) + " 00:00:00");
            voTemp.setEnddate("3000-12-01" + " 00:00:00");
            voTemp.setCreateusereng(PrefUtils.getString(context, "userGongHao", "20000"));
            voTemp.setCreatedate(DateUtil.getDateTimeStr(8));
            voTemp.setUpdateusereng(PrefUtils.getString(context, "userGongHao", "20000"));
            voTemp.setUpdatetime(DateUtil.getDateTimeStr(8));
            voTemp.setUpdateusereng(PrefUtils.getString(context, "userGongHao", "20000"));
            voTemp.setUploadflag("0");// 不上传
            voTemp.setPadisconsistent("0");// 未上传
            voTemp.setVisitkey(visitkey);
            createMstGroupproductM(voTemp);
        }
    }

    /**
     * @param groupproductMTemp
     */
    public void createMstGroupproductM(MstGroupproductMTemp groupproductMTemp) {
        //AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstGroupproductMTemp, String> indexValueDao = helper.getMstGroupproductMTempDao();

            //connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            //connection.setAutoCommit(false);

            indexValueDao.create(groupproductMTemp);
            //connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取失败", e);
        }
    }

    /**
     * 通过terminalcode查询MstGroupproductM表
     *
     * @return
     */
    public List<MstGroupproductM> queryMstGroupproductM(String terminalcode, String startdate) {
        // 获取分项采集数据
        List<MstGroupproductM> stcLst = new ArrayList<MstGroupproductM>();
        //AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstGroupproductMDao dao = helper.getDao(MstGroupproductM.class);
            //MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            //connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            //connection.setAutoCommit(false);
            stcLst = dao.queryMstGroupproductMByCreatedate(helper, terminalcode, startdate);
            //connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取失败", e);
        }
        return stcLst;
    }

    /**
     * 用于追溯
     * 通过terminalcode查询MstGroupproductM表
     *
     * @return
     */
    public List<MstGroupproductM> queryZsMstGroupproductM(String terminalcode) {
        // 获取分项采集数据
        List<MstGroupproductM> stcLst = new ArrayList<MstGroupproductM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstGroupproductMDao dao = helper.getDao(MstGroupproductM.class);
            stcLst = dao.queryZsMstGroupproductMByCreatedate(helper, terminalcode);
        } catch (SQLException e) {
            Log.e(TAG, "获取失败", e);
        }
        return stcLst;
    }

    /**
     * 获取终端临时表 记录信息
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalinfoMTemp findTermTempById(String termId) {

        MstTerminalinfoMTemp termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMTempDao dao = helper.getDao(MstTerminalinfoMTemp.class);
            termInfo = dao.queryForId(termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }
    /**
     * 获取终端主表数据 记录信息
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalinfoM findMstTerminalinfoMById(String termId) {

        MstTerminalinfoM termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            termInfo = dao.queryForId(termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }
    /**
     * 获取终端主表数据 记录信息
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalinfoM findMstTerminalinfoMCartById(String termId) {

        MstTerminalinfoM termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            termInfo = dao.queryForId(termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }

    /**
     * 获取终端购物车 记录信息
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalinfoMCart findTermById(String termId) {

        MstTerminalinfoMCart termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMCartDao dao = helper.getDao(MstTerminalinfoMCart.class);
            termInfo = dao.queryForId(termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }
    /**
     * 获取追溯终端购物车 记录信息
     *
     * @param termId 终端ID
     * @return
     */
    public MstTerminalinfoMZsCart findZsTermById(String termId) {

        MstTerminalinfoMZsCart termInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMZsCartDao dao = helper.getDao(MstTerminalinfoMZsCart.class);
            termInfo = dao.queryForId(termId);

        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return termInfo;
    }

    /**
     * 获取拜访临时表信息
     *
     * @param visitId 拜访请表信息ID
     * @return
     */
    public MstVisitMTemp findVisitTempById(String visitId) {

        MstVisitMTemp visitInfo = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVisitMTempDao dao = helper.getDao(MstVisitMTemp.class);
            visitInfo = dao.queryForId(visitId);

        } catch (SQLException e) {
            Log.e(TAG, "获取拜访主表DAO对象失败", e);
        }
        return visitInfo;
    }

    /**
     * 获取追溯主表临时表记录
     *
     * @param MitValterMTempId 拜访请表信息ID
     * @return
     */
    public MitValterMTemp findMitValterMTempById(String MitValterMTempId) {

        MitValterMTemp mitValterMTemp = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValterMTempDao dao = helper.getDao(MitValterMTemp.class);
            mitValterMTemp = dao.queryForId(MitValterMTempId);

        } catch (SQLException e) {
            Log.e(TAG, "获取拜访主表DAO对象失败", e);
        }
        return mitValterMTemp;
    }

    /**
     * 获取 终端追溯竞品附表 临时表记录
     *
     * @param mitValterMTempId 拜访请表信息ID
     * @return
     */
    public MitValcmpotherMTemp findMitValcmpotherMTempById(String mitValterMTempId) {

        MitValcmpotherMTemp mitValcmpotherMTemp = null;
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        MitValcmpotherMTempDao dao;
        try {
            dao = (MitValcmpotherMTempDao) helper.getMitValcmpotherMTempDao();
            QueryBuilder<MitValcmpotherMTemp, String> qBuilder = dao.queryBuilder();
            qBuilder.where().eq("valterid", mitValterMTempId);
            mitValcmpotherMTemp = qBuilder.queryForFirst();

        } catch (SQLException e) {
            Log.e(TAG, "获取拜访主表DAO对象失败", e);
        }
        return mitValcmpotherMTemp;

    }


    // 删除临时表数据
    public void deleteData() {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try {
            deleteTable(helper, "MST_VISIT_M_TEMP");
            deleteTable(helper, "MST_TERMINALINFO_M_TEMP");
            deleteTable(helper, "MST_AGENCYSUPPLY_INFO_TEMP");
            deleteTable(helper, "MST_VISTPRODUCT_INFO_TEMP");
            deleteTable(helper, "MST_CMPSUPPLY_INFO_TEMP");
            deleteTable(helper, "MST_CHECKEXERECORD_INFO_TEMP");
            deleteTable(helper, "MST_COLLECTIONEXERECORD_INFO_TEMP");
            deleteTable(helper, "MST_PROMOTERM_INFO_TEMP");
            deleteTable(helper, "MST_CAMERAINFO_M_TEMP");
            deleteTable(helper, "MST_VISITMEMO_INFO_TEMP");
            deleteTable(helper, "MST_GROUPPRODUCT_M_TEMP");

            deleteTable(helper, "MIT_VALTER_M_TEMP");// 追溯主表临时表
            deleteTable(helper, "MIT_VALSUPPLY_M_TEMP");// 追溯进销存表临时表
            deleteTable(helper, "MIT_VALCMP_M_TEMP");// 追溯聊竞品临时表
            deleteTable(helper, "MIT_VALPROMOTIONS_M_TEMP");// 追溯促销活动终端表临时表
            deleteTable(helper, "MIT_VALCHECKTYPE_M_TEMP");// 追溯拉链表临时表
            deleteTable(helper, "MIT_VALCHECKITEM_M_TEMP");// 追溯采集项表临时表
            deleteTable(helper, "MIT_VALCMPOTHER_M_TEMP");// 终端追溯竞品附表 临时表
            deleteTable(helper, "MIT_VALPIC_M_TEMP");// 终端追溯图片表 临时表

            deleteTable(helper, "MIT_VALADDACCOUNT_M_TEMP");// 终端进货台账主表 临时表
            deleteTable(helper, "MIT_VALADDACCOUNTPRO_M_TEMP");// 终端追溯台账产品详情表 临时表

            deleteTable(helper, "MIT_VALAGREE_M_TEMP");// 终端追溯协议主表 临时表
            deleteTable(helper, "MIT_VALAGREEDETAIL_M_TEMP");// 终端追溯协议对付信息表 临时表

            //deleteTable(helper, "MST_CHECKGROUP_INFO");
            //deleteTable(helper, "MST_CHECKGROUP_INFO_TEMP");
        } catch (Exception e) {
            Log.e(TAG, "删除临时表数据失败", e);
        }
    }

    public static void deleteTable(DatabaseHelper dbHelper, String tabname) {
        String sql = "DELETE FROM " + tabname + ";";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    //---------------------------------------------

    /**
     * 获取分项采集页面显示数据
     *
     * @param visitId   本次拜访主键
     * @param termId    本次拜访终端ID
     * @param channelId 本次拜访终端所属渠道
     * @param seeFlag   查看标识
     * @return
     */
    public List<XtProIndex> queryCalculateIndex(String visitId, String termId, String channelId, String seeFlag) {

        // 获取分项采集数据
        List<CheckIndexCalculateStc> stcLst = new ArrayList<CheckIndexCalculateStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            stcLst = dao.queryCalculateIndexTemp(helper, visitId, termId, channelId, seeFlag);
            //stcLst = dao.queryZsCalculateIndexTemp(helper, visitId, termId, channelId, seeFlag);

            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 组建成界面显示所需要的数据结构
        List<XtProIndex> proIndexLst = new ArrayList<XtProIndex>();
        String indexId = "";
        XtProIndex indexItem = new XtProIndex();// XtProItem
        XtProIndexValue indexValueItem;
        for (CheckIndexCalculateStc item : stcLst) {
            //
            if (!indexId.equals(item.getIndexId())) {
                indexItem = new XtProIndex();
                indexItem.setIndexId(item.getIndexId());
                indexItem.setIndexName(item.getIndexName());
                indexItem.setIndexType(item.getIndexType());
                indexItem.setIndexValueLst(new ArrayList<XtProIndexValue>());
                proIndexLst.add(indexItem);
                indexId = item.getIndexId();
            }
            indexValueItem = new XtProIndexValue();
            indexValueItem.setIndexResultId(item.getRecordId());
            indexValueItem.setIndexId(item.getIndexId());
            indexValueItem.setIndexType(item.getIndexType());
            indexValueItem.setIndexValueId(item.getIndexValueId());
            indexValueItem.setIndexValueName(item.getIndexValueName());
            indexValueItem.setProId(item.getProId());
            indexValueItem.setProName(item.getProName());
            indexItem.getIndexValueLst().add(indexValueItem);
        }
        return proIndexLst;
    }

    /**
     * 获取追溯分项采集页面显示数据
     *
     * @param previsitId 本次拜访主键
     * @param termId     本次拜访终端ID
     * @param channelId  本次拜访终端所属渠道
     * @param seeFlag    查看标识
     * @return
     */
    public List<XtProIndex> queryZsCalculateIndex(String previsitId, String termId, String channelId, String seeFlag) {

        // 获取分项采集数据
        List<CheckIndexCalculateStc> stcLst = new ArrayList<CheckIndexCalculateStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            stcLst = dao.queryZsCalculateIndexTemp(helper, previsitId, termId, channelId, seeFlag);

            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 组建成界面显示所需要的数据结构
        List<XtProIndex> proIndexLst = new ArrayList<XtProIndex>();
        String indexId = "";
        XtProIndex indexItem = new XtProIndex();// XtProItem
        XtProIndexValue indexValueItem;
        for (CheckIndexCalculateStc item : stcLst) {
            //
            if (!indexId.equals(item.getIndexId())) {
                indexItem = new XtProIndex();
                indexItem.setIndexId(item.getIndexId());
                indexItem.setIndexName(item.getIndexName());
                indexItem.setIndexType(item.getIndexType());
                indexItem.setIndexValueLst(new ArrayList<XtProIndexValue>());
                proIndexLst.add(indexItem);
                indexId = item.getIndexId();
            }
            indexValueItem = new XtProIndexValue();
            indexValueItem.setIndexResultId(item.getRecordId());
            indexValueItem.setIndexId(item.getIndexId());
            indexValueItem.setIndexType(item.getIndexType());
            indexValueItem.setIndexValueId(item.getIndexValueId());
            indexValueItem.setIndexValueName(item.getIndexValueName());
            indexValueItem.setProId(item.getProId());
            indexValueItem.setProName(item.getProName());
            indexValueItem.setId(item.getId());// // 终端追溯查指标表主键
            indexValueItem.setValchecktypeflag(item.getValchecktypeflag());// 终端追溯 指标正确与否
            indexValueItem.setDdacresult(item.getDdacresult());// 终端追溯 督导自动计算指标值
            indexValueItem.setDdremark(item.getDdremark());// 终端追溯 督导指标备注
            indexItem.getIndexValueLst().add(indexValueItem);
        }
        return proIndexLst;
    }

    /**
     * 获取分项采集部分的产品指标对应的采集项目数据
     *
     * @param visitId   拜访主键
     * @param channelId 渠道ID
     * @return
     */
    public List<XtProItem> queryCalculateItem(String visitId, String channelId) {

        // 获取分项采集各指标对应的采集项目数据
        List<CheckIndexQuicklyStc> stcLst = new ArrayList<CheckIndexQuicklyStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            stcLst = dao.queryCalculateItemTemp(helper, visitId, channelId);

            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 组建界面要显示的数据
        List<XtProItem> proItemLst = new ArrayList<XtProItem>();
        XtProItem proItem = new XtProItem();
        String Id = "", proItemId;
        for (CheckIndexQuicklyStc stc : stcLst) {
            proItemId = stc.getProductkey() + stc.getColitemkey();
            if (!Id.equals(proItemId)) {
                proItem = new XtProItem();
                proItem.setColRecordKey(stc.getColRecordId());
                proItem.setCheckkey(stc.getCheckkey());
                proItem.setItemId(stc.getColitemkey());
                proItem.setItemName(stc.getColitemname());
                proItem.setProId(stc.getProductkey());
                proItem.setProName(stc.getProName());
                proItem.setChangeNum(stc.getChangeNum());
                proItem.setFinalNum(stc.getFinalNum());
                proItem.setXianyouliang(stc.getXianyouliang());
                proItem.setBianhualiang(stc.getBianhualiang());
                // proItem.setFreshness(FunUtil.isNullSetDate(stc.getFreshness()));// 新鲜度
                proItem.setIndexIdLst(new ArrayList<String>());
                proItemLst.add(proItem);
                Id = proItemId;
            }
            if (!proItem.getIndexIdLst().contains(stc.getCheckkey())) {
                proItem.getIndexIdLst().add(stc.getCheckkey());
            }
        }
        return proItemLst;
    }

    /**
     * 获取分项采集部分的产品指标对应的采集项目数据
     *
     * @param visitId   拜访主键
     * @param channelId 渠道ID
     * @return
     */
    public List<XtProItem> queryZsCalculateItem(String visitId, String channelId) {

        // 获取分项采集各指标对应的采集项目数据
        List<CheckIndexQuicklyStc> stcLst = new ArrayList<CheckIndexQuicklyStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            stcLst = dao.queryZsCalculateItemTemp(helper, visitId, channelId);

            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 组建界面要显示的数据
        List<XtProItem> proItemLst = new ArrayList<XtProItem>();
        XtProItem proItem = new XtProItem();
        String Id = "", proItemId;
        for (CheckIndexQuicklyStc stc : stcLst) {
            proItemId = stc.getProductkey() + stc.getColitemkey();
            if (!Id.equals(proItemId)) {
                proItem = new XtProItem();
                proItem.setColRecordKey(stc.getColRecordId());// 业代采集项表主键
                proItem.setId(stc.getId());// 督导采集项表主键
                proItem.setValchecktypeid(stc.getValchecktypeid());// 督导拉链项表主键
                proItem.setCheckkey(stc.getCheckkey());
                proItem.setItemId(stc.getColitemkey());
                proItem.setItemName(stc.getColitemname());
                proItem.setProId(stc.getProductkey());
                proItem.setProName(stc.getProName());
                proItem.setChangeNum(stc.getChangeNum());
                proItem.setFinalNum(stc.getFinalNum());
                proItem.setValitem(stc.getValitem());
                proItem.setValitemval(stc.getValitemval());
                proItem.setValitemremark(stc.getValitemremark());
                proItem.setXianyouliang(stc.getXianyouliang());
                proItem.setBianhualiang(stc.getBianhualiang());
                //proItem.setFreshness(FunUtil.isNullSetDate(stc.getFreshness()));// 新鲜度
                proItem.setIndexIdLst(new ArrayList<String>());
                proItemLst.add(proItem);
                Id = proItemId;
            }
            if (!proItem.getIndexIdLst().contains(stc.getCheckkey())) {
                proItem.getIndexIdLst().add(stc.getCheckkey());
            }
        }
        return proItemLst;
    }

    /**
     * 获取巡店拜访-查指标的分项采集部分的与产品无关的指标数据
     *
     * @param visitId   拜访ID
     * @param channelId 本次拜访终端的次渠道ID
     * @param seeFlag   查看操作标识
     * @return
     */
    public List<XtCheckIndexCalculateStc> queryNoProIndex12(
            String visitId, String channelId, String seeFlag) {
        // 获取分项采集数据
        List<XtCheckIndexCalculateStc> stcLst = new ArrayList<XtCheckIndexCalculateStc>();
        stcLst.add(new XtCheckIndexCalculateStc("666b74b3-b221-4920-b549-d9ec39a463fd", "1", "合作执行是否到位", "8d36d1e5-c776-452e-8893-589ad786d71d"));
        stcLst.add(new XtCheckIndexCalculateStc("59802090-02ac-4146-9cc3-f09570c36a26", "4", "我品单店占有率", "eeffb1af-51c0-4954-98de-2cc62043e4d2"));
        stcLst.add(new XtCheckIndexCalculateStc("df2e88c9-246f-40e2-b6e5-08cdebf8c281", "1", "是否高质量配送", "bf600cfe-f70d-4170-857d-65dd59740d57"));

        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);

            MstCheckexerecordInfoDao dao = helper.getDao(MstCheckexerecordInfo.class);
            Dao<MstCheckexerecordInfoTemp, String> CheckexerecordInfoTempdao = helper.getMstCheckexerecordInfoTempDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            //
            if (ConstValues.FLAG_1.equals(seeFlag)) {//查看模式

                List<MstCheckexerecordInfo> Checkexerecordlist = dao.queryForEq("visitkey", visitId);
                for (MstCheckexerecordInfo mstCheckexerecordInfo : Checkexerecordlist) {
                    for (XtCheckIndexCalculateStc CheckIndexCalculate : stcLst) {
                        if (mstCheckexerecordInfo.getCheckkey().equals(CheckIndexCalculate.getIndexId())) {
                            CheckIndexCalculate.setVisitId(mstCheckexerecordInfo.getVisitkey());
                            CheckIndexCalculate.setRecordId(mstCheckexerecordInfo.getRecordkey());
                            CheckIndexCalculate.setIndexValueId(mstCheckexerecordInfo.getAcresult());
                        }
                    }
                }
            } else {
                List<MstCheckexerecordInfoTemp> Checkexerecordlist = CheckexerecordInfoTempdao.queryForEq("visitkey", visitId);
                for (MstCheckexerecordInfoTemp mstCheckexerecordInfo : Checkexerecordlist) {
                    for (XtCheckIndexCalculateStc CheckIndexCalculate : stcLst) {
                        if (mstCheckexerecordInfo.getCheckkey().equals(CheckIndexCalculate.getIndexId())) {
                            CheckIndexCalculate.setVisitId(mstCheckexerecordInfo.getVisitkey());
                            CheckIndexCalculate.setRecordId(mstCheckexerecordInfo.getRecordkey());
                            if (mstCheckexerecordInfo.getAcresult() != null) {
                                // 如果上次拜访不为空,此指标取上次拜访的值 // 修改新终端进入时,合作状态为空的bug 20160809
                                CheckIndexCalculate.setIndexValueId(mstCheckexerecordInfo.getAcresult());
                            }
                        }
                    }
                }
            }

            connection.commit(null);

        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        return stcLst;
    }

    /**
     * 获取巡店拜访-查指标的分项采集部分的与产品无关的指标数据
     *
     * @param previsitId 拜访ID
     * @param channelId  本次拜访终端的次渠道ID
     * @param seeFlag    查看操作标识
     * @return
     */
    public List<XtCheckIndexCalculateStc> queryZsNoProIndex(
            String previsitId, String channelId, String seeFlag) {
        // 获取分项采集数据
        List<XtCheckIndexCalculateStc> stcLst = new ArrayList<XtCheckIndexCalculateStc>();
        stcLst.add(new XtCheckIndexCalculateStc("666b74b3-b221-4920-b549-d9ec39a463fd", "1", "合作执行是否到位", "8d36d1e5-c776-452e-8893-589ad786d71d"));
        stcLst.add(new XtCheckIndexCalculateStc("df2e88c9-246f-40e2-b6e5-08cdebf8c281", "1", "是否高质量配送", "bf600cfe-f70d-4170-857d-65dd59740d57"));
        stcLst.add(new XtCheckIndexCalculateStc("59802090-02ac-4146-9cc3-f09570c36a26", "4", "我品单店占有率", "eeffb1af-51c0-4954-98de-2cc62043e4d2"));

        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);

            MstCheckexerecordInfoDao dao = helper.getDao(MstCheckexerecordInfo.class);
            //Dao<MstCheckexerecordInfoTemp, String> CheckexerecordInfoTempdao = helper.getMstCheckexerecordInfoTempDao();
            Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempdao = helper.getMitValchecktypeMTempDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            //
            /*if (ConstValues.FLAG_1.equals(seeFlag)) {//查看模式

                List<MstCheckexerecordInfo> Checkexerecordlist = dao.queryForEq("visitkey", visitId);
                for (MstCheckexerecordInfo mstCheckexerecordInfo : Checkexerecordlist) {
                    for (XtCheckIndexCalculateStc CheckIndexCalculate : stcLst) {
                        if (mstCheckexerecordInfo.getCheckkey().equals(CheckIndexCalculate.getIndexId())) {
                            CheckIndexCalculate.setVisitId(mstCheckexerecordInfo.getVisitkey());
                            CheckIndexCalculate.setRecordId(mstCheckexerecordInfo.getRecordkey());
                            CheckIndexCalculate.setIndexValueId(mstCheckexerecordInfo.getAcresult());
                        }
                    }
                }
            } else {
                List<MstCheckexerecordInfoTemp> Checkexerecordlist = CheckexerecordInfoTempdao.queryForEq("visitkey", visitId);
                for (MstCheckexerecordInfoTemp mstCheckexerecordInfo : Checkexerecordlist) {
                    for (XtCheckIndexCalculateStc CheckIndexCalculate : stcLst) {
                        if (mstCheckexerecordInfo.getCheckkey().equals(CheckIndexCalculate.getIndexId())) {
                            CheckIndexCalculate.setVisitId(mstCheckexerecordInfo.getVisitkey());
                            CheckIndexCalculate.setRecordId(mstCheckexerecordInfo.getRecordkey());
                            if (mstCheckexerecordInfo.getAcresult() != null) {
                                // 如果上次拜访不为空,此指标取上次拜访的值 // 修改新终端进入时,合作状态为空的bug 20160809
                                CheckIndexCalculate.setIndexValueId(mstCheckexerecordInfo.getAcresult());
                            }
                        }
                    }
                }
            }*/

            List<MitValchecktypeMTemp> Checkexerecordlist = mitValchecktypeMTempdao.queryForEq("visitkey", previsitId);
            for (MitValchecktypeMTemp valchecktypeMTemp : Checkexerecordlist) {
                for (XtCheckIndexCalculateStc CheckIndexCalculate : stcLst) {
                    if (valchecktypeMTemp.getValchecktype().equals(CheckIndexCalculate.getIndexId())) {
                        CheckIndexCalculate.setVisitId(valchecktypeMTemp.getVisitkey());// 业代拉链表visitkey
                        CheckIndexCalculate.setRecordId(valchecktypeMTemp.getValchecktypeid());// 业代拉链表主键
                        if (valchecktypeMTemp.getAcresult() != null) {
                            // 如果上次拜访不为空,此指标取上次拜访的值 // 修改新终端进入时,合作状态为空的bug 20160809
                            CheckIndexCalculate.setIndexValueId(valchecktypeMTemp.getAcresult());
                        }
                        CheckIndexCalculate.setId(valchecktypeMTemp.getId());// 追溯拉链表id
                        CheckIndexCalculate.setDdacresult(valchecktypeMTemp.getDdacresult());// 追溯拉链表id
                        CheckIndexCalculate.setDdremark(valchecktypeMTemp.getDdremark());// 追溯拉链表id
                        CheckIndexCalculate.setValchecktypeflag(valchecktypeMTemp.getValchecktypeflag());//// 指标正确与否
                    }
                }
            }

            connection.commit(null);

        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        return stcLst;
    }

    /**
     * 保存查指标页面数据
     *
     * @param visitId       拜访主键
     * @param termId        终端ID,终端key,
     * @param calculateLst  指标、指标值记录结果
     * @param proItemLst    产品、采集项记录结果
     * @param noProIndexLst 与产品无关的指标采集值
     */
    public void saveCheckIndex(String visitId, String termId, List<XtProIndex> calculateLst,
                               List<XtProItem> proItemLst, List<XtCheckIndexCalculateStc> noProIndexLst) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstCheckexerecordInfoTemp, String> indexValueDao = helper.getMstCheckexerecordInfoTempDao();
            Dao<MstCollectionexerecordInfo, String> proItemDao = helper.getMstCollectionexerecordInfoDao();
            Dao<MstCollectionexerecordInfoTemp, String> proItemTempDao = helper.getMstCollectionexerecordInfoTempDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 拜访指标执行记录表更新语句及参数
            String[] args = new String[3];
            args[0] = visitId;
            StringBuffer buffer = new StringBuffer();
            buffer.append("update mst_checkexerecord_info_temp set ");
            buffer.append("visitkey = ?, acresult = ?, padisconsistent = '0' ");
            buffer.append("where recordkey = ? ");

            // 保存拜访指标执行记录表
            MstCheckexerecordInfoTemp indexInfo;
            for (XtProIndex indexItem : calculateLst) {
                for (XtProIndexValue valueItem : indexItem.getIndexValueLst()) {

                    // 如果true,是新增
                    if (CheckUtil.isBlankOrNull(valueItem.getIndexResultId())) {
                        valueItem.setIndexResultId(FunUtil.getUUID());
                        indexInfo = new MstCheckexerecordInfoTemp();
                        indexInfo.setRecordkey(valueItem.getIndexResultId());
                        indexInfo.setVisitkey(visitId);
                        indexInfo.setProductkey(valueItem.getProId());
                        indexInfo.setCheckkey(valueItem.getIndexId());
                        indexInfo.setChecktype(valueItem.getIndexType());
                        indexInfo.setAcresult(valueItem.getIndexValueId());
                        indexInfo.setIscom(ConstValues.FLAG_0);
                        indexInfo.setTerminalkey(termId);
                        indexInfo.setPadisconsistent(ConstValues.FLAG_0);
                        indexInfo.setDeleteflag(ConstValues.FLAG_0);
                        //indexInfo.setCreuser(ConstValues.loginSession.getUserCode());
                        indexInfo.setCreuser(PrefUtils.getString(context, "userCode", ""));
                        //indexInfo.setUpdateuser(ConstValues.loginSession.getUserCode());
                        indexInfo.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                        indexValueDao.create(indexInfo);

                        // 否则更新
                    } else {
                        args[1] = valueItem.getIndexValueId();
                        args[2] = valueItem.getIndexResultId();
                        indexValueDao.executeRaw(buffer.toString(), args);
                    }
                }
            }

            // 清楚本地mst_collectionexerecord_info 的数据， 并插入新的数据
            proItemDao.executeRaw("delete from mst_collectionexerecord_info_temp where visitkey = ?", visitId);

            // 保产品及对应的采集项数据
            Map<String, Integer> proStockMap = new HashMap<String, Integer>();
            int currNum, changeNum;
            MstCollectionexerecordInfoTemp itemInfo;
            for (XtProItem proItem : proItemLst) {

                for (String chekcKey : proItem.getIndexIdLst()) {

                    proItem.setColRecordKey(FunUtil.getUUID());
                    itemInfo = new MstCollectionexerecordInfoTemp();
                    itemInfo.setColrecordkey(proItem.getColRecordKey());
                    itemInfo.setVisitkey(visitId);
                    itemInfo.setProductkey(proItem.getProId());
                    itemInfo.setCheckkey(chekcKey);
                    itemInfo.setColitemkey(proItem.getItemId());
                    itemInfo.setAddcount(proItem.getChangeNum());//
                    itemInfo.setTotalcount(proItem.getFinalNum());
                    itemInfo.setBianhualiang(proItem.getBianhualiang());
                    itemInfo.setXianyouliang(proItem.getXianyouliang());
                    //itemInfo.setFreshness(proItem.getFreshness());// 新鲜度
                    // itemInfo.setFreshness(FunUtil.isDateString(proItem.getFreshness()));// 新鲜度
                    itemInfo.setPadisconsistent(ConstValues.FLAG_0);
                    itemInfo.setDeleteflag(ConstValues.FLAG_0);
                    //itemInfo.setCreuser(ConstValues.loginSession.getUserCode());
                    itemInfo.setCreuser(PrefUtils.getString(context, "userCode", ""));
                    //itemInfo.setUpdateuser(ConstValues.loginSession.getUserCode());
                    itemInfo.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                    proItemTempDao.create(itemInfo);
                }

                // 记录每个产品的现有库存
                if (PropertiesUtil.getProperties("checkItem_stock", "").equals(proItem.getItemId())
                        || PropertiesUtil.getProperties("checkItem_stock1", "").equals(proItem.getItemId())) {
                    currNum = proItem.getFinalNum() == null ? 0 : proItem.getFinalNum().intValue();
                    changeNum = proItem.getChangeNum() == null ? 0 : proItem.getChangeNum().intValue();
                    proStockMap.put(proItem.getProId(), currNum + changeNum);
                }
            }

            // 更新我品的现有库存
            buffer = new StringBuffer();
            buffer.append("update mst_vistproduct_info_temp set currnum = ?,padisconsistent = '0' ");
            buffer.append("where productkey = ? and visitkey = ? ");
            args = new String[3];
            args[2] = visitId;
            for (String proId : proStockMap.keySet()) {
                args[0] = proStockMap.get(proId).toString();
                args[1] = proId;
                proItemDao.executeRaw(buffer.toString(), args);
            }

            // 保存或更新与产品无关的指标的指标值数据
            args = new String[3];
            args[0] = visitId;
            buffer = new StringBuffer();
            buffer.append("update mst_checkexerecord_info_temp set  ");
            buffer.append("visitkey = ?, acresult = ? ,padisconsistent = '0' ");
            buffer.append("where recordkey = ? ");

            String currDate = DateUtil.formatDate(new Date(), "yyyyMMdd");
            // 保存拜访指标执行记录表
            for (XtCheckIndexCalculateStc itemStc : noProIndexLst) {

                // 如果true,是新增
                if (!visitId.equals(itemStc.getVisitId())) {
                    itemStc.setVisitId(visitId);
                    indexInfo = new MstCheckexerecordInfoTemp();
                    indexInfo.setRecordkey(FunUtil.getUUID());
                    indexInfo.setVisitkey(visitId);
                    indexInfo.setStartdate(currDate);
                    indexInfo.setEnddate(currDate);
                    indexInfo.setCheckkey(itemStc.getIndexId());
                    indexInfo.setChecktype(itemStc.getIndexType());
                    indexInfo.setAcresult(itemStc.getIndexValueId());
                    indexInfo.setIscom(ConstValues.FLAG_0);
                    indexInfo.setTerminalkey(termId);
                    indexInfo.setPadisconsistent(ConstValues.FLAG_0);
                    indexInfo.setDeleteflag(ConstValues.FLAG_0);
                    //indexInfo.setCreuser(ConstValues.loginSession.getUserCode());
                    indexInfo.setCreuser(PrefUtils.getString(context, "userCode", ""));
                    //indexInfo.setUpdateuser(ConstValues.loginSession.getUserCode());
                    indexInfo.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                    indexValueDao.create(indexInfo);

                    // 否则更新
                } else {
                    args[1] = itemStc.getIndexValueId();
                    args[2] = itemStc.getRecordId();
                    indexValueDao.executeRaw(buffer.toString(), args);
                }
            }

            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存进销存数据发生异常", e);
            ViewUtil.sendMsg(context, R.string.checkindex_save_fail);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
    }

    /**
     * 保存 追溯查指标页面数据
     *
     * @param preVisitkey   拜访主键
     * @param termId        终端ID,终端key,
     * @param calculateLst  指标、指标值记录结果
     * @param proItemLst    产品、采集项记录结果
     * @param noProIndexLst 与产品无关的指标采集值
     */
    public void saveZsCheckIndex(String preVisitkey, String valterid, String termId, List<XtProIndex> calculateLst,
                                 List<XtProItem> proItemLst, List<XtCheckIndexCalculateStc> noProIndexLst) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValchecktypeMTemp, String> indexValueDao = helper.getMitValchecktypeMTempDao();
            Dao<MitValcheckitemMTemp, String> proItemDao = helper.getMitValcheckitemMTempDao();
            //Dao<MstCollectionexerecordInfoTemp, String> proItemTempDao = helper.getMstCollectionexerecordInfoTempDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 有产品的指标
            MitValchecktypeMTemp indexInfo;
            for (XtProIndex indexItem : calculateLst) {
                for (XtProIndexValue valueItem : indexItem.getIndexValueLst()) {
                    indexInfo = new MitValchecktypeMTemp();
                    indexInfo.setId(valueItem.getId());//
                    indexInfo.setValterid(valterid);// 终端追溯主表ID
                    indexInfo.setVisitkey(preVisitkey);// 终端追溯主表ID
                    indexInfo.setValchecktype(valueItem.getIndexId());//  指标类型 5d,6d....  varchar2(36)                   null,
                    indexInfo.setValchecktypeid(valueItem.getIndexResultId());//  指标ID  拉链表主键  varchar2(36)                   null,
                    indexInfo.setProductkey(valueItem.getProId());//
                    indexInfo.setAcresult(valueItem.getIndexValueId());//
                    indexInfo.setTerminalkey(termId);//
                    indexInfo.setDdremark(valueItem.getDdremark());// 督导备注
                    indexInfo.setDdacresult(valueItem.getDdacresult());// 督导自动计算值
                    indexInfo.setValchecktypeflag(valueItem.getValchecktypeflag());// 指标正确与否   char(1)                        null,
                    indexValueDao.createOrUpdate(indexInfo);
                }
            }

            // 保产品及对应的采集项数据
            MitValcheckitemMTemp itemInfo;
            for (XtProItem proItem : proItemLst) {
                for (String chekcKey : proItem.getIndexIdLst()) {

                    itemInfo = new MitValcheckitemMTemp();
                    itemInfo.setId(proItem.getId());// 督导采集项表主键
                    itemInfo.setValterid(valterid);// 追溯表ID
                    itemInfo.setValchecktypeid(proItem.getValchecktypeid());// 终端追溯查指标表ID
                    itemInfo.setVisitkey(preVisitkey);// 拜访主键
                    // itemInfo.setValitemid(proItem.getColRecordKey());//  采集项主键ID varchar2(36) null,
                    itemInfo.setValitemid(proItem.getItemId());//  采集项主键ID varchar2(36) null,
                    itemInfo.setColitemkey(proItem.getItemId());// 采集项key varchar2(36) null,
                    itemInfo.setCheckkey(chekcKey);// 指标 varchar2(36) null,
                    //itemInfo.setAddcount();//  变化量varchar2(36) null,
                    //itemInfo.setTotalcount();// 现有量 varchar2(36) null,
                    itemInfo.setProductkey(proItem.getProId());// 产品key varchar2(36) null,
                    itemInfo.setValitem(proItem.getValitem());// 采集项原值结果量 varchar2(10) null,
                    itemInfo.setValitemval(proItem.getValitemval());// 采集项正确值结果量 varchar2(10) null,
                    itemInfo.setValitemremark(proItem.getValitemremark());// 采集项备注 varchar2(300) null,
                    proItemDao.createOrUpdate(itemInfo);
                }
            }

            // 无产品的指标
            for (XtCheckIndexCalculateStc itemStc : noProIndexLst) {

                indexInfo = new MitValchecktypeMTemp();
                indexInfo.setId(itemStc.getId());//
                indexInfo.setValterid(valterid);// 终端追溯主表ID
                indexInfo.setVisitkey(preVisitkey);// 终端追溯主表ID
                indexInfo.setValchecktype(itemStc.getIndexId());//  指标类型 5d,6d....  varchar2(36)                   null,
                indexInfo.setValchecktypeid(itemStc.getRecordId());//  指标ID  拉链表主键  varchar2(36)                   null,
                //indexInfo.setProductkey(valueItem.getProId());//
                indexInfo.setAcresult(itemStc.getIndexValueId());//
                indexInfo.setTerminalkey(termId);//
                indexInfo.setDdremark(itemStc.getDdremark());// 督导备注
                indexInfo.setDdacresult(itemStc.getDdacresult());// 督导自动计算值
                indexInfo.setValchecktypeflag(itemStc.getValchecktypeflag());// 指标正确与否   char(1)                        null,
                indexValueDao.createOrUpdate(indexInfo);
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

    /**
     * 获取协同拜访-查指标的促销活动页面部分的数据
     *
     * @param //prevVisitId 拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param termLevel     本次拜访终端的终端类型ID(终端等级)
     * @return
     */
    public List<CheckIndexPromotionStc> queryPromotion(String visitId, String channelId, String termLevel) {
        // 获取分项采集数据
        List<CheckIndexPromotionStc> stcLst = new ArrayList<CheckIndexPromotionStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstPromotionsmDao dao = helper.getDao(MstPromotionsM.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            stcLst = dao.queryXtPromotionByterm(helper, visitId, channelId, termLevel);
            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 处理组合进店的情况
        List<CheckIndexPromotionStc> tempLst = new ArrayList<CheckIndexPromotionStc>();
        String promotionKey = "";
        CheckIndexPromotionStc itemStc = new CheckIndexPromotionStc();
        for (CheckIndexPromotionStc item : stcLst) {
            if (!promotionKey.equals(item.getPromotKey())) {
                promotionKey = item.getPromotKey();
                itemStc = item;
                tempLst.add(itemStc);
            } else {
                itemStc.setProId(itemStc.getProId() + "," + item.getProId());
                itemStc.setProName(itemStc.getProName() + "\n" + item.getProName());
            }

        }

        return tempLst;
    }

    /**
     * 获取终端追溯-查指标的促销活动页面部分的数据
     *
     * @param //prevVisitId 拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param termLevel     本次拜访终端的终端类型ID(终端等级)
     * @return
     */
    public List<CheckIndexPromotionStc> queryZsPromotion(String visitId, String channelId, String termLevel) {
        // 获取分项采集数据
        List<CheckIndexPromotionStc> stcLst = new ArrayList<CheckIndexPromotionStc>();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstPromotionsmDao dao = helper.getDao(MstPromotionsM.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            //stcLst = dao.queryXtPromotionByterm(helper, visitId, channelId, termLevel);
            stcLst = dao.queryZsPromotionByterm(helper, visitId, channelId, termLevel);
            connection.commit(null);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访产品-我品竞品表DAO对象失败", e);
        }

        // 处理组合进店的情况
        List<CheckIndexPromotionStc> tempLst = new ArrayList<CheckIndexPromotionStc>();
        String promotionKey = "";
        CheckIndexPromotionStc itemStc = new CheckIndexPromotionStc();
        for (CheckIndexPromotionStc item : stcLst) {
            if (!promotionKey.equals(item.getPromotKey())) {
                promotionKey = item.getPromotKey();
                itemStc = item;
                tempLst.add(itemStc);
            } else {
                itemStc.setProId(itemStc.getProId() + "," + item.getProId());
                itemStc.setProName(itemStc.getProName() + "\n" + item.getProName());
            }

        }

        return tempLst;
    }


    /**
     * 更新拜访离店时间及是否要上传标志
     * <p>
     * 复制临时表数据 到业代的正式表中
     *
     * @param visitId      拜访主键
     * @param termId       终端主键
     * @param visitEndDate 离店时间
     * @param uploadFlag   是否要上传标志
     */
    public void confirmXtUpload(String visitId, String termId, String terminalcode, String visitEndDate, String uploadFlag) {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVisitMDao dao = helper.getDao(MstVisitM.class);
            MstVisitMTempDao visitTempDao = helper.getDao(MstVisitMTemp.class);
            // 获取当前拜访的指标结果记录情况
            Dao<MstCheckexerecordInfoTemp, String> tempDao = helper.getMstCheckexerecordInfoTempDao();
            Dao<MstCheckexerecordInfo, String> checkInfoDao = helper.getMstCheckexerecordInfoDao();

            Dao<MstCollectionexerecordInfo, String> collectionDao = helper.getMstCollectionexerecordInfoDao();
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao = helper.getMstCollectionexerecordInfoTempDao();

            Dao<MstCameraInfoM, String> cameraDao = helper.getMstCameraiInfoMDao();
            Dao<MstCameraInfoMTemp, String> cameraTempDao = helper.getMstCameraInfoMTempDao();

            Dao<MstPromotermInfo, String> promDao = helper.getMstPromotermInfoDao();
            Dao<MstPromotermInfoTemp, String> promTempDao = helper.getMstPromotermInfoTempDao();

            Dao<MstVistproductInfo, String> proDao = helper.getMstVistproductInfoDao();
            Dao<MstVistproductInfoTemp, String> proTempDao = helper.getMstVistproductInfoTempDao();

            Dao<MstTerminalinfoM, String> terminalinfoMDao = helper.getMstTerminalinfoMDao();
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao = helper.getMstTerminalinfoMTempDao();

            Dao<MstAgencysupplyInfo, String> agencyDao = helper.getMstAgencysupplyInfoDao();
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao = helper.getMstAgencysupplyInfoTempDao();

            Dao<MstCmpsupplyInfo, String> cmpSupplyDao = helper.getMstCmpsupplyInfoDao();
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao = helper.getMstCmpsupplyInfoTempDao();


            Dao<MstGroupproductM, String> groupproDao = helper.getMstGroupproductMDao();
            Dao<MstGroupproductMTemp, String> groupproTempDao = helper.getMstGroupproductMTempDao();


            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            MstVisitMTemp visitTemp = visitTempDao.queryForId(visitId);
            // 复制拜访主表
            createMstVisitMByMstVisitMTemp(dao, visitTemp);
            // 复制拉链表  如果结束拜访, 则生成最新的终端指标结果记录表
            createMstcheckexerecordinfoByMstCheckexerecordInfoTemp(checkInfoDao, tempDao, visitId, termId, visitEndDate);
            // 复制采集项表
            createMstCollectionexerecordInfoByMstCollectionexerecordInfoTemp(collectionDao, collectionTempDao, visitId);
            // 复制图片表
            createMstCameraInfoMByMstCameraInfoMTemp(cameraDao, cameraTempDao, visitId, termId, visitEndDate);

            // 复制促销活动终端表
            createMstPromotermInfoByMstPromotermInfoTemp(promDao, promTempDao, visitId, termId, visitEndDate);

            // 拜访产品表
            createMstVistproductInfoByMstVistproductInfoTemp(proDao, proTempDao, visitId, termId, visitEndDate);

            // 复制终端表
            createMstTerminalinfoMByMstTerminalinfoMTemp(terminalinfoMDao, terminalinfoMTempDao, visitId, termId, visitEndDate);

            // 复制我品供货关系表
            createMstAgencysupplyInfoByMstAgencysupplyInfoTemp(agencyDao, agencyTempDao, visitId, termId, visitEndDate);

            // 复制竞品供货关系表
            createMstCmpsupplyInfoByMstCmpsupplyInfoTemp(cmpSupplyDao, cmpSupplyTempDao, visitId, termId, visitEndDate);

            // 复制产品组合是否达标表
            createMstGroupproductMByMstGroupproductMTemp(groupproDao, groupproTempDao, visitId, termId, terminalcode, visitEndDate);

            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "更新拜访离店时间及是否要上传标志失败", e);
            try {
                connection.rollback(null);
                ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 复制产品组合是否达标表
    private void createMstGroupproductMByMstGroupproductMTemp(
            Dao<MstGroupproductM, String> groupproDao,
            Dao<MstGroupproductMTemp, String> groupproTempDao, String visitId, String termId, String terminalcode, String visitEndDate) {

        try {
            QueryBuilder<MstGroupproductMTemp, String> collectionQB = groupproTempDao.queryBuilder();
            Where<MstGroupproductMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalcode", terminalcode);
            List<MstGroupproductMTemp> mstGroupproductMTemps = collectionQB.query();
            if (mstGroupproductMTemps.size() > 0) {
                for (MstGroupproductMTemp item : mstGroupproductMTemps) {
                    MstGroupproductM info = new MstGroupproductM();
                    info.setGproductid(item.getGproductid());
                    info.setTerminalcode(item.getTerminalcode());
                    info.setTerminalname(item.getTerminalname());
                    info.setIfrecstand(item.getIfrecstand());
                    info.setStartdate(item.getStartdate());// 默认: 当天时间 yyyy-MM-dd +"  00:00:00"
                    info.setEnddate(item.getEnddate());//  默认: "3000-12-01"+"  00:00:00"
                    info.setCreateusereng(item.getCreateusereng());
                    info.setCreatedate(item.getCreatedate());
                    info.setUpdateusereng(item.getUpdateusereng());
                    info.setVisitkey(item.getVisitkey());
                    info.setUploadflag("1");//  0:该条记录不上传  1:该条记录需要上传 ;
                    info.setPadisconsistent("0");
                    info.setUpdatetime(item.getUpdatetime());

                    groupproDao.createOrUpdate(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }

    }

    // 复制竞品供货关系表
    private void createMstCmpsupplyInfoByMstCmpsupplyInfoTemp(
            Dao<MstCmpsupplyInfo, String> cmpSupplyDao,
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCmpsupplyInfoTemp, String> collectionQB = cmpSupplyTempDao.queryBuilder();
            Where<MstCmpsupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstCmpsupplyInfoTemp> mstCmpsupplyInfoTemps = collectionQB.query();
            if (mstCmpsupplyInfoTemps.size() > 0) {
                for (MstCmpsupplyInfoTemp item : mstCmpsupplyInfoTemps) {
                    MstCmpsupplyInfo info = new MstCmpsupplyInfo();
                    info.setCmpsupplykey(item.getCmpsupplykey());
                    info.setStatus(item.getStatus());//0 新增未审核 1 有效 2 无效（通过审核设置） 3 申请未审核(有效->无效)
                    info.setInprice(item.getInprice());
                    info.setReprice(item.getReprice());
                    info.setCmpproductkey(item.getCmpproductkey());
                    info.setCmpcomkey(item.getCmpcomkey());
                    info.setTerminalkey(item.getTerminalkey());
                    info.setCmpinvaliddate(item.getCmpinvaliddate());

                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent("0");
                    info.setPadcondate(item.getPadcondate());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setOrderbyno(item.getOrderbyno());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setVersion(item.getVersion());
                    info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());
                    cmpSupplyDao.createOrUpdate(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }
    }

    // 复制我品供货关系表
    private void createMstAgencysupplyInfoByMstAgencysupplyInfoTemp(
            Dao<MstAgencysupplyInfo, String> agencyDao,
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstAgencysupplyInfoTemp, String> collectionQB = agencyTempDao.queryBuilder();
            Where<MstAgencysupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("lowerkey", termId);
            List<MstAgencysupplyInfoTemp> mstAgencysupplyInfoTemps = collectionQB.query();
            if (mstAgencysupplyInfoTemps.size() > 0) {
                for (MstAgencysupplyInfoTemp item : mstAgencysupplyInfoTemps) {
                    MstAgencysupplyInfo info = new MstAgencysupplyInfo();
                    info.setAsupplykey(item.getAsupplykey());
                    info.setStatus(item.getStatus());//0 新增未审核 1 有效 2 无效（通过审核设置） 3 申请未审核(有效->无效)
                    info.setInprice(item.getInprice());
                    info.setReprice(item.getReprice());
                    info.setProductkey(item.getProductkey());
                    info.setLowerkey(item.getLowerkey());
                    info.setLowertype(item.getLowertype());
                    info.setUpperkey(item.getUpperkey());
                    info.setUppertype(item.getUppertype());
                    info.setSiebelkey(item.getSiebelkey());

                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent("0");
                    info.setPadcondate(item.getPadcondate());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setOrderbyno(item.getOrderbyno());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setVersion(item.getVersion());
                    info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());
                    agencyDao.createOrUpdate(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到我品供货关系正式表失败", e);

        }
    }

    // 复制终端表
    private void createMstTerminalinfoMByMstTerminalinfoMTemp(
            Dao<MstTerminalinfoM, String> terminalinfoMDao,
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstTerminalinfoMTemp, String> collectionQB = terminalinfoMTempDao.queryBuilder();
            Where<MstTerminalinfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstTerminalinfoMTemp> mstTerminalinfoMTemps = collectionQB.query();
            if (mstTerminalinfoMTemps.size() > 0) {
                for (MstTerminalinfoMTemp item : mstTerminalinfoMTemps) {
                    MstTerminalinfoM info = new MstTerminalinfoM();

                    info.setTerminalkey(item.getTerminalkey());
                    info.setRoutekey(item.getRoutekey());
                    info.setTerminalcode(item.getTerminalcode());
                    info.setTerminalname(item.getTerminalname());
                    info.setProvince(item.getProvince());
                    info.setCity(item.getCity());
                    info.setCounty(item.getCounty());
                    info.setAddress(item.getAddress());
                    info.setContact(item.getContact());
                    info.setMobile(item.getMobile());
                    info.setTlevel(item.getTlevel());
                    info.setSequence(item.getSequence());
                    info.setCycle(item.getCycle());
                    info.setHvolume(item.getHvolume());
                    info.setMvolume(item.getMvolume());
                    info.setPvolume(item.getPvolume());
                    info.setLvolume(item.getLvolume());
                    info.setStatus(item.getStatus());//0 新增未审核 1 有效 2 无效（通过审核设置） 3 申请未审核(有效->无效)
                    info.setSellchannel(item.getSellchannel());
                    info.setMainchannel(item.getMainchannel());
                    info.setMinorchannel(item.getMinorchannel());
                    info.setAreatype(item.getAreatype());
                    info.setCmpselftreaty(item.getCmpselftreaty());
                    info.setIfminedate(item.getIfminedate());//店招时间
                    info.setIfmine(item.getIfmine());//店招

                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent(item.getPadisconsistent());
                    info.setPadcondate(item.getPadcondate());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setOrderbyno(item.getOrderbyno());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setVersion(item.getVersion());
                    info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());
                    terminalinfoMDao.createOrUpdate(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到终端正式表失败", e);

        }

    }

    // 拜访产品表
    private void createMstVistproductInfoByMstVistproductInfoTemp(
            Dao<MstVistproductInfo, String> proDao,
            Dao<MstVistproductInfoTemp, String> proTempDao, String visitId, String termId, String visitEndDate) {
        try {
            QueryBuilder<MstVistproductInfoTemp, String> collectionQB = proTempDao.queryBuilder();
            Where<MstVistproductInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstVistproductInfoTemp> mstVistproductInfoTemps = collectionQB.query();
            if (mstVistproductInfoTemps.size() > 0) {
                for (MstVistproductInfoTemp item : mstVistproductInfoTemps) {
                    MstVistproductInfo info = new MstVistproductInfo();
                    info.setRecordkey(item.getRecordkey());
                    info.setVisitkey(item.getVisitkey());
                    info.setProductkey(item.getProductkey());
                    info.setCmpproductkey(item.getCmpproductkey());
                    info.setCmpcomkey(item.getCmpcomkey());
                    info.setAgencykey(item.getAgencykey());
                    info.setAgencyname(item.getAgencyname());// 用户输入的竞品经销商
                    info.setPurcprice(item.getPurcprice());
                    info.setRetailprice(item.getRetailprice());
                    info.setPurcnum(item.getPurcnum());
                    info.setPronum(item.getPronum());
                    info.setCurrnum(item.getCurrnum());
                    info.setSalenum(item.getSalenum());
                    info.setFristdate(item.getFristdate());
                    info.setAddcard(item.getAddcard());// 累计卡
                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent(item.getPadisconsistent());
                    info.setPadcondate(item.getPadcondate());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setOrderbyno(item.getOrderbyno());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setVersion(item.getVersion());
                    info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());
                    proDao.create(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到拜访产品正式表失败", e);

        }

    }

    // 复制促销活动终端表
    private void createMstPromotermInfoByMstPromotermInfoTemp(
            Dao<MstPromotermInfo, String> promDao,
            Dao<MstPromotermInfoTemp, String> promTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstPromotermInfoTemp, String> collectionQB = promTempDao.queryBuilder();
            Where<MstPromotermInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstPromotermInfoTemp> mstPromotermInfoTemps = collectionQB.query();
            if (mstPromotermInfoTemps.size() > 0) {
                for (MstPromotermInfoTemp item : mstPromotermInfoTemps) {
                    MstPromotermInfo info = new MstPromotermInfo();
                    info.setRecordkey(item.getRecordkey());
                    info.setPtypekey(item.getPtypekey());
                    info.setTerminalkey(item.getTerminalkey());
                    info.setVisitkey(item.getVisitkey());
                    info.setStartdate(item.getStartdate());
                    info.setIsaccomplish(item.getIsaccomplish());
                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent(item.getPadisconsistent());
                    info.setPadcondate(item.getPadcondate());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setOrderbyno(item.getOrderbyno());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setVersion(item.getVersion());
                    info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());
                    promDao.create(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到促销活动终端正式表失败", e);

        }


    }

    // // 复制图片表
    private void createMstCameraInfoMByMstCameraInfoMTemp(
            Dao<MstCameraInfoM, String> cameraDao,
            Dao<MstCameraInfoMTemp, String> cameraTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCameraInfoMTemp, String> collectionQB = cameraTempDao.queryBuilder();
            Where<MstCameraInfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstCameraInfoMTemp> cameraInfoMTemps = collectionQB.query();
            if (cameraInfoMTemps.size() > 0) {
                for (MstCameraInfoMTemp item : cameraInfoMTemps) {
                    MstCameraInfoM info = new MstCameraInfoM();
                    info.setCamerakey(item.getCamerakey());// 图片主键
                    info.setTerminalkey(item.getTerminalkey());// 终端主键
                    info.setVisitkey(item.getVisitkey()); // 拜访主键
                    info.setPictypekey(item.getPictypekey());// 图片类型主键(UUID)
                    info.setPicname(item.getPicname());// 图片名称
                    info.setLocalpath(item.getLocalpath());// 本地图片路径
                    info.setNetpath(item.getNetpath());// 请求网址(原先ftp上传用,现已不用)
                    info.setCameradata(item.getCameradata());// 拍照时间
                    info.setIsupload(item.getIsupload());// 是否已上传 0未上传  1已上传
                    info.setIstakecamera(item.getIstakecamera());//
                    info.setPicindex(item.getPicindex());// 图片所在角标
                    info.setPictypename(item.getPictypename());// 图片类型(中文名称)
                    info.setSureup(item.getSureup());// 确定上传 0确定上传 1未确定上传
                    info.setImagefileString(item.getImagefileString());// 将图片文件转成String保存在数据库
                    cameraDao.create(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到图片正式表失败", e);

        }
    }

    // 复制采集项表
    private void createMstCollectionexerecordInfoByMstCollectionexerecordInfoTemp(
            Dao<MstCollectionexerecordInfo, String> collectionDao,
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao, String visitId) {
        try {
            QueryBuilder<MstCollectionexerecordInfoTemp, String> collectionQB = collectionTempDao.queryBuilder();
            Where<MstCollectionexerecordInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            collectionWhere.and();
            //collectionWhere.ne("deleteflag", ConstValues.FLAG_1);
            //collectionWhere.and();
            collectionWhere.isNotNull("checkkey");
            collectionWhere.and();
            collectionWhere.ne("checkkey", "");
            collectionQB.orderBy("productkey", true);
            collectionQB.orderBy("colitemkey", true);
            collectionQB.orderBy("checkkey", false);
            collectionQB.orderBy("updatetime", false);
            List<MstCollectionexerecordInfoTemp> collectiontempList = collectionQB.query();
            if (collectiontempList.size() > 0) {
                for (MstCollectionexerecordInfoTemp item : collectiontempList) {
                    MstCollectionexerecordInfo info = new MstCollectionexerecordInfo();
                    info.setColrecordkey(item.getColrecordkey());
                    info.setVisitkey(item.getVisitkey());

                    info.setProductkey(item.getProductkey());
                    info.setCheckkey(item.getCheckkey());
                    info.setColitemkey(item.getColitemkey());
                    info.setAddcount(item.getAddcount());
                    info.setTotalcount(item.getTotalcount());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    // info.setFreshness(item.getFreshness());// 新鲜度
                    info.setOrderbyno(item.getOrderbyno());
                    info.setVersion(item.getVersion());
                    info.setCreuser(item.getCreuser());
                    info.setUpdateuser(item.getUpdateuser());
                    info.setBianhualiang(item.getBianhualiang());//变化量 (用于最后上传时 现有量变化量必须填值,才能上传)
                    info.setXianyouliang(item.getXianyouliang());// 现有量 (用于最后上传时 现有量变化量必须填值,才能上传)

                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent(item.getPadisconsistent());
                    info.setPadcondate(item.getPadcondate());
                    info.setDeleteflag(item.getDeleteflag());
                    info.setCredate(item.getCredate());
                    info.setUpdatetime(item.getUpdatetime());
                    collectionDao.create(info);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "复制到采集项正式表失败", e);

        }
    }

    // 复制拉链表
    private void createMstcheckexerecordinfoByMstCheckexerecordInfoTemp(
            Dao<MstCheckexerecordInfo, String> checkInfoDao, Dao<MstCheckexerecordInfoTemp, String> tempDao,
            String visitId, String termId, String visitEndDate) {

        try {
            // 只留年月日
            String endDate = visitEndDate.substring(0, 8);

            // 临时表 visitEndDate(根据visitkey)
            List<MstCheckexerecordInfoTemp> tempAllList = null;

            tempAllList = getCheckExerecordTempListByVisitid(tempDao, visitId);

            if (!CheckUtil.IsEmpty(tempAllList)) {
                //不关联产品的指标 比如:合作配送占有率（临时表的） false表示没有productkey
                List<MstCheckexerecordInfoTemp> noProTempList = getCheckExerecordTemp(tempAllList, false);
                for (MstCheckexerecordInfoTemp tempItem : noProTempList) {
                    MstCheckexerecordInfo item = new MstCheckexerecordInfo();
                    item.setRecordkey(tempItem.getRecordkey());
                    item.setVisitkey(tempItem.getVisitkey());
                    item.setCheckkey(tempItem.getCheckkey());
                    item.setChecktype(tempItem.getChecktype());
                    item.setAcresult(tempItem.getAcresult());
                    item.setIscom(tempItem.getIscom());
                    item.setStartdate(endDate);
                    item.setEnddate(endDate);
                    item.setTerminalkey(tempItem.getTerminalkey());
                    item.setPadisconsistent(ConstValues.FLAG_0);
                    item.setPadcondate(null);
                    item.setCreuser(PrefUtils.getString(context, "userCode", ""));
                    item.setUpdateuser(item.getCreuser());
                    checkInfoDao.create(item);
                }
                //关联产品的指标 比如:铺货状态,冰冻化（临时表的） true表示有productkey
                List<MstCheckexerecordInfoTemp> proTempList = getCheckExerecordTemp(tempAllList, true);

                if (proTempList != null && proTempList.size() > 0) {
                    //关联产品的指标 比如:铺货状态,冰冻化（临时表的） key：终端-指标-产品,value;拉链临时表记录
                    Map<String, MstCheckexerecordInfoTemp> proTempMap = new HashMap<String, MstCheckexerecordInfoTemp>();
                    for (MstCheckexerecordInfoTemp temp : proTempList) {
                        String key = temp.getTerminalkey() + "-" + temp.getCheckkey() + "-" + temp.getProductkey();
                        if (!proTempMap.containsKey(key)) {
                            proTempMap.put(key, temp);
                        }
                    }
                    proTempList.clear();
                    // 去重
                    for (String key : proTempMap.keySet()) {
                        MstCheckexerecordInfoTemp temp = proTempMap.get(key);
                        proTempList.add(temp);
                    }
                    //获取 拉链正式表 本终端上一次的数据 （拉链表记录 关联产品的指标）
                    List<MstCheckexerecordInfo> proList = getCheckExerecordList(checkInfoDao, termId);

                    // 实例化要最终指标结果集对象
                    boolean addFlag = false;// 是否在拉链正式表中插入新纪录
                    MstCheckexerecordInfo item;
                    // 遍历拉链临时表的指标 MstCheckexerecordInfoTemp
                    for (MstCheckexerecordInfoTemp tempItem : proTempList) {
                        addFlag = false;
                        String acresultTemp = tempItem.getAcresult();
                        String checkKeyTemp = tempItem.getCheckkey();
                        if (CheckUtil.isBlankOrNull(acresultTemp) || "-1".equals(acresultTemp)) {
                            if (PropertiesUtil.getProperties("check_puhuo").equals(checkKeyTemp)) {//#铺货状态
                                acresultTemp = "301";
                            } else if (PropertiesUtil.getProperties("check_daoju").equals(checkKeyTemp)) {//#道具生动化
                                acresultTemp = "307";
                            } else if (PropertiesUtil.getProperties("check_chanpin").equals(checkKeyTemp)) {//#产品生动化
                                acresultTemp = "309";
                            } else if (PropertiesUtil.getProperties("check_bingdong").equals(checkKeyTemp)) {//#冰冻化
                                acresultTemp = "311";
                            }
                        }
                        // 遍历正式表的指标
                        for (MstCheckexerecordInfo valueItem : proList) {
                            // MstCheckexerecordInfo
                            // 如果是同一产品的相同指标
                            if (tempItem.getProductkey().equals(valueItem.getProductkey()) && tempItem.getCheckkey().equals(valueItem.getCheckkey())) {

                                addFlag = true;
                                // 拉链临时表deleteflag字段为1的  删除产品的指标
                                if (ConstValues.FLAG_1.equals(tempItem.getDeleteflag())) {
                                    // 如果结束日期与开始日期相同，则只更新不做插入数据
                                    if (endDate.equals(valueItem.getStartdate())) {
                                        valueItem.setAcresult(getDefaultAcresult(valueItem.getCheckkey()));// 默认指标
                                        valueItem.setStartdate(endDate);
                                        valueItem.setEnddate("30001201");
                                        valueItem.setSisconsistent(ConstValues.FLAG_0);
                                        valueItem.setScondate(null);
                                        valueItem.setPadisconsistent(ConstValues.FLAG_0);
                                        valueItem.setPadcondate(null);
                                        valueItem.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                        valueItem.setUpdateuser(valueItem.getCreuser());
                                        valueItem.setResultstatus(new BigDecimal(0));
                                        checkInfoDao.update(valueItem);
                                    } else {
                                        // 更新前一个值的结束时间
                                        valueItem.setEnddate(endDate);
                                        valueItem.setResultstatus(new BigDecimal(1));
                                        valueItem.setSisconsistent(ConstValues.FLAG_0);
                                        valueItem.setScondate(null);
                                        valueItem.setPadisconsistent(ConstValues.FLAG_0);
                                        valueItem.setPadcondate(null);
                                        valueItem.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                        valueItem.setUpdateuser(valueItem.getCreuser());
                                        valueItem.setResultstatus(new BigDecimal(1));
                                        checkInfoDao.update(valueItem);

                                        // 插入一新指标结果
                                        item = new MstCheckexerecordInfo();
                                        item.setRecordkey(FunUtil.getUUID());
                                        item.setVisitkey(tempItem.getVisitkey());// 修改为 "temp" -> tempItem.getVisitkey()
                                        item.setProductkey(valueItem.getProductkey());
                                        item.setCheckkey(valueItem.getCheckkey());
                                        item.setChecktype(valueItem.getChecktype());
                                        item.setAcresult(getDefaultAcresult(valueItem.getCheckkey()));// 默认指标
                                        item.setIscom(valueItem.getIscom());
                                        item.setStartdate(endDate);
                                        item.setEnddate("30001201");
                                        item.setTerminalkey(valueItem.getTerminalkey());
                                        item.setSisconsistent(ConstValues.FLAG_0);
                                        item.setPadisconsistent(ConstValues.FLAG_0);
                                        item.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                        item.setUpdateuser(item.getCreuser());
                                        item.setResultstatus(new BigDecimal(0));
                                        checkInfoDao.create(item);
                                    }
                                } else {
                                    // 如果指标结果不同
                                    if (!acresultTemp.equals(valueItem.getAcresult())) {

                                        // 如果结束日期与开始日期相同，则只更新不做插入数据
                                        if (endDate.equals(valueItem.getStartdate())) {
                                            // 更新前一个值的结束时间
                                            valueItem.setAcresult(acresultTemp);
                                            valueItem.setSisconsistent(ConstValues.FLAG_0);
                                            valueItem.setScondate(null);
                                            valueItem.setPadisconsistent(ConstValues.FLAG_0);
                                            valueItem.setPadcondate(null);
                                            valueItem.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                            valueItem.setUpdateuser(valueItem.getCreuser());
                                            valueItem.setResultstatus(new BigDecimal(0));
                                            checkInfoDao.update(valueItem);
                                        } else {
                                            // 更新前一个值的结束时间
                                            valueItem.setEnddate(endDate);
                                            valueItem.setResultstatus(new BigDecimal(1));
                                            valueItem.setSisconsistent(ConstValues.FLAG_0);
                                            valueItem.setScondate(null);
                                            valueItem.setPadisconsistent(ConstValues.FLAG_0);
                                            valueItem.setPadcondate(null);
                                            valueItem.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                            valueItem.setUpdateuser(valueItem.getCreuser());
                                            valueItem.setResultstatus(new BigDecimal(1));
                                            checkInfoDao.update(valueItem);

                                            // 插入一新指标结果
                                            item = new MstCheckexerecordInfo();
                                            item.setRecordkey(FunUtil.getUUID());
                                            item.setVisitkey(tempItem.getVisitkey());// 修改为 temp -> tempItem.getVisitkey()
                                            item.setProductkey(valueItem.getProductkey());
                                            item.setCheckkey(valueItem.getCheckkey());
                                            item.setChecktype(valueItem.getChecktype());
                                            item.setAcresult(acresultTemp);
                                            item.setIscom(valueItem.getIscom());
                                            item.setStartdate(endDate);
                                            item.setEnddate("30001201");
                                            item.setTerminalkey(valueItem.getTerminalkey());
                                            item.setSisconsistent(ConstValues.FLAG_0);
                                            item.setPadisconsistent(ConstValues.FLAG_0);
                                            item.setCreuser(PrefUtils.getString(context, "userCode", ""));
                                            item.setUpdateuser(item.getCreuser());
                                            item.setResultstatus(new BigDecimal(0));
                                            checkInfoDao.create(item);
                                        }
                                    }
                                }
                                break;
                            }
                        }

                        // 如果之前没有，则为新境
                        if (!addFlag) {
                            item = new MstCheckexerecordInfo();
                            item.setRecordkey(FunUtil.getUUID());
                            item.setVisitkey(tempItem.getVisitkey());// 修改为 temp -> tempItem.getVisitkey()
                            item.setProductkey(tempItem.getProductkey());
                            item.setCheckkey(tempItem.getCheckkey());
                            item.setChecktype(tempItem.getChecktype());
                            item.setAcresult(acresultTemp);
                            item.setIscom(tempItem.getIscom());
                            item.setStartdate(endDate);
                            item.setEnddate("30001201");
                            item.setTerminalkey(tempItem.getTerminalkey());
                            item.setCreuser(PrefUtils.getString(context, "userCode", ""));
                            item.setUpdateuser(item.getCreuser());
                            item.setResultstatus(new BigDecimal(0));
                            checkInfoDao.create(item);
                        }
                    }
                }
                // 删除临时数据
                //tempDao.delete(tempAllList);
            }
            delRepeatMstCollectionexerecordInfo(visitId);
        } catch (SQLException e) {
            Log.e(TAG, "复制到拉链正式表失败", e);
            e.printStackTrace();
        }
    }

    // 复制拜访主表  MstVisitMTemp → MstVisitM
    private void createMstVisitMByMstVisitMTemp(MstVisitMDao dao, MstVisitMTemp visitTemp) {
        if (visitTemp != null) {
            try {
                MstVisitM mstVisitM = new MstVisitM();
                mstVisitM.setVisitkey(visitTemp.getVisitkey());
                mstVisitM.setTerminalkey(visitTemp.getTerminalkey());
                mstVisitM.setRoutekey(visitTemp.getRoutekey());
                mstVisitM.setGridkey(visitTemp.getGridkey());
                mstVisitM.setAreaid(visitTemp.getAreaid());
                mstVisitM.setVisitdate(visitTemp.getVisitdate());
                mstVisitM.setEnddate(visitTemp.getEnddate());
                mstVisitM.setTempkey(visitTemp.getTempkey());
                mstVisitM.setUserid(visitTemp.getUserid());
                mstVisitM.setVisituser(visitTemp.getVisituser());
                mstVisitM.setIsself(visitTemp.getIsself());
                mstVisitM.setIscmp(visitTemp.getIscmp());
                mstVisitM.setSelftreaty(visitTemp.getSelftreaty());
                mstVisitM.setCmptreaty(visitTemp.getCmptreaty());
                mstVisitM.setStatus(visitTemp.getStatus());

                mstVisitM.setIshdistribution(visitTemp.getIshdistribution());
                mstVisitM.setExetreaty(visitTemp.getExetreaty());
                mstVisitM.setSelfoccupancy(visitTemp.getSelfoccupancy());
                mstVisitM.setIscmpcollapse(visitTemp.getIscmpcollapse());

                mstVisitM.setSisconsistent(visitTemp.getSisconsistent());
                mstVisitM.setScondate(visitTemp.getScondate());

                mstVisitM.setPadcondate(visitTemp.getPadcondate());

                mstVisitM.setComid(visitTemp.getComid());
                mstVisitM.setRemarks(visitTemp.getRemarks());
                mstVisitM.setOrderbyno(visitTemp.getOrderbyno());// 同一次是0 返回时会改为1
                mstVisitM.setDeleteflag(visitTemp.getDeleteflag());
                mstVisitM.setVersion(visitTemp.getVersion());

                mstVisitM.setCredate(visitTemp.getCredate());
                mstVisitM.setCreuser(visitTemp.getCreuser());
                mstVisitM.setUpdatetime(visitTemp.getUpdatetime());
                mstVisitM.setUpdateuser(visitTemp.getUpdateuser());

                mstVisitM.setLongitude(visitTemp.getLongitude());
                mstVisitM.setLatitude(visitTemp.getLatitude());
                mstVisitM.setGpsstatus(visitTemp.getGpsstatus());
                mstVisitM.setIfminedate(visitTemp.getIfminedate());
                mstVisitM.setIfmine(visitTemp.getIfmine());
                mstVisitM.setVisitposition(visitTemp.getVisitposition());
                mstVisitM.setPadisconsistent("0");
                mstVisitM.setUploadFlag("1");
                dao.create(mstVisitM);
            } catch (SQLException e) {
                Log.e(TAG, "复制到拜访主表正式表失败", e);
                e.printStackTrace();
            }
        }
    }


    /***
     * 获取指标正式表集合
     获取当前终端各指标的最新指标结果状态
     * @param //tempDao
     * @param //visitId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unused")
    private List<MstCheckexerecordInfo> getCheckExerecordList(Dao<MstCheckexerecordInfo, String> valueDao,
                                                              String termId) throws SQLException {
        QueryBuilder<MstCheckexerecordInfo, String> valueQb = valueDao.queryBuilder();
        Where<MstCheckexerecordInfo, String> valueWr = valueQb.where();
        valueWr.eq("terminalkey", termId);
        valueWr.and();
        valueWr.eq("enddate", "30001201");
        valueWr.and();
        valueWr.ne("deleteflag", ConstValues.delFlag);
        valueWr.and();
        valueWr.isNotNull("productkey");
        List<MstCheckexerecordInfo> valueLst = valueQb.query();
        return valueLst;
    }

    /***
     * 获取当前指标集合
     * @param tempDao
     * @param visitId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unused")
    private List<MstCheckexerecordInfoTemp> getCheckExerecordTempListByVisitid(Dao<MstCheckexerecordInfoTemp, String> tempDao,
                                                                               String visitId) throws SQLException {
        QueryBuilder<MstCheckexerecordInfoTemp, String> tempValueQb = tempDao.queryBuilder();
        Where<MstCheckexerecordInfoTemp, String> tempValueWr = tempValueQb.where();
        tempValueWr.eq("visitkey", visitId);
        tempValueQb.orderBy("terminalkey", true);
        tempValueQb.orderBy("checkkey", true);
        tempValueQb.orderBy("productkey", true);
        tempValueQb.orderBy("enddate", false);
        tempValueQb.orderBy("startdate", false);
        tempValueQb.orderBy("acresult", false);
        List<MstCheckexerecordInfoTemp> tempList = tempValueQb.query();
        return tempList;
    }


    /***
     * 获取指标临时表集合
     * @param tempList   指标临时表集合
     * @param isPro      是否关联产品
     */
    @SuppressWarnings({"unchecked", "unused"})
    private List<MstCheckexerecordInfoTemp> getCheckExerecordTemp(List<MstCheckexerecordInfoTemp> tempList, boolean isPro) {
        List<MstCheckexerecordInfoTemp> temps = new ArrayList<MstCheckexerecordInfoTemp>();
        for (MstCheckexerecordInfoTemp temp : tempList) {

            if (isPro) {// 关联产品
                if (!CheckUtil.isBlankOrNull(temp.getProductkey())) {
                    temps.add(temp);
                }
            } else {// 非关联产品
                if (CheckUtil.isBlankOrNull(temp.getProductkey())) {
                    temps.add(temp);
                }
            }
        }
        return temps;
    }

    /***
     * 获取指标值得默认值
     * @param checkKey
     * @return
     */
    private String getDefaultAcresult(String checkKey) {
        String acresult = "";
        if (PropertiesUtil.getProperties("check_puhuo").equals(checkKey)) {//#铺货状态
            acresult = "301";
        } else if (PropertiesUtil.getProperties("check_daoju").equals(checkKey)) {//#道具生动化
            acresult = "307";
        } else if (PropertiesUtil.getProperties("check_chanpin").equals(checkKey)) {//#产品生动化
            acresult = "309";
        } else if (PropertiesUtil.getProperties("check_bingdong").equals(checkKey)) {//#冰冻化
            acresult = "311";
        }
        return acresult;
    }

    /***
     * 去除拜访指标采集项重复
     * @param visitkey
     */
    public void delRepeatMstCollectionexerecordInfo(String visitkey) {
        try {
            Dao<MstCollectionexerecordInfo, String> collectionDao = DatabaseHelper.getHelper(context).getMstCollectionexerecordInfoDao();
            QueryBuilder<MstCollectionexerecordInfo, String> collectionQB = collectionDao.queryBuilder();
            Where<MstCollectionexerecordInfo, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitkey);
            collectionWhere.and();
            collectionWhere.ne("deleteflag", ConstValues.FLAG_1);
            collectionQB.orderBy("productkey", true);
            collectionQB.orderBy("colitemkey", true);
            collectionQB.orderBy("checkkey", false);
            collectionQB.orderBy("updatetime", false);
            List<MstCollectionexerecordInfo> collectionList = collectionQB.query();
            List<String> list = new ArrayList<String>();
            for (MstCollectionexerecordInfo collection : collectionList) {
                if (CheckUtil.isBlankOrNull(collection.getCheckkey())) {
                    collectionDao.delete(collection);
                } else {
                    String key = collection.getColitemkey() + collection.getProductkey() + collection.getCheckkey();
                    if (!list.contains(key)) {
                        list.add(key);
                    } else {
                        collectionDao.delete(collection);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 通过终端key 获取此终端 进货台账
     * @param terminalkey
     * @return
     */
    public List<ZsTzItemIndex> queryValTermLedger(String terminalkey) {
        List<ZsTzItemIndex> lst = new ArrayList<ZsTzItemIndex>();
        List<ZsTzLedger> zsTzLedgers = new ArrayList<ZsTzLedger>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValaddaccountMDao dao = helper.getDao(MitValaddaccountM.class);
            zsTzLedgers = dao.queryValTzByTemp(helper, terminalkey);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        // 组建成界面显示所需要的数据结构
        ZsTzItemIndex zsTzItemIndex = new ZsTzItemIndex();//
        String productkey = "";
        MitValaddaccountproMTemp indexValueItem;

        for (ZsTzLedger tzLedger : zsTzLedgers) {
            if (!productkey.equals(tzLedger.getValproid())) {
                zsTzItemIndex = new ZsTzItemIndex();
                zsTzItemIndex.setId(tzLedger.getValaddaccountid());
                zsTzItemIndex.setValsupplyid(tzLedger.getValsupplyid());// 终端追溯终端表ID
                zsTzItemIndex.setValagencyid(tzLedger.getValagencyid());//经销商ID
                zsTzItemIndex.setValagencyname(tzLedger.getValagencyname());// 经销商名称
                zsTzItemIndex.setValterid(tzLedger.getValterid());// 终端ID
                zsTzItemIndex.setValtername(tzLedger.getValtername());// 终端名称
                zsTzItemIndex.setValproid(tzLedger.getValproid());//产品ID
                zsTzItemIndex.setValproname(tzLedger.getValproname());//  产品名称
                zsTzItemIndex.setValprostatus(tzLedger.getValprostatus());//  稽查状态
                zsTzItemIndex.setIndexValueLst(new ArrayList<MitValaddaccountproMTemp>());
                lst.add(zsTzItemIndex);
                productkey = tzLedger.getValproid();
            }
            // 复制附表
            indexValueItem = new MitValaddaccountproMTemp();
            indexValueItem.setId(tzLedger.getValaddaccountproid());//
            indexValueItem.setValterid(tzLedger.getValsupplyid());//
            indexValueItem.setValaddaccountid(tzLedger.getValaddaccountid());// 终端进货台账主表ID
            indexValueItem.setValprotime(tzLedger.getValprotime());// 台账日期
            indexValueItem.setValpronumfalg(tzLedger.getValpronumfalg());// 进货量正确与否
            indexValueItem.setValpronum(tzLedger.getValpronum());// 进货量原值
            indexValueItem.setValprotruenum(tzLedger.getValprotruenum());// 进货量正确值
            indexValueItem.setValproremark(tzLedger.getValproremark());// 备注
            zsTzItemIndex.getIndexValueLst().add(indexValueItem);
        }

        return lst;
    }

    public void saveDealMitRepairM(MitRepairM repairM, MitRepaircheckM mitRepaircheckM,MitRepairterM mitRepairterM) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitRepairM, String> mitRepairMDao = helper.getDao(MitRepairM.class);
            Dao<MitRepaircheckM, String> mitRepaircheckMDao = helper.getDao(MitRepaircheckM.class);
            Dao<MitRepairterM, String> mitRepairterMDao = helper.getDao(MitRepairterM.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            mitRepairMDao.createOrUpdate(repairM);
            mitRepaircheckMDao.createOrUpdate(mitRepaircheckM);
            mitRepairterMDao.createOrUpdate(mitRepairterM);

            connection.commit(null);
        } catch (Exception e) {
            DbtLog.logUtils(TAG, e.getMessage());
            e.printStackTrace();
            Log.e(TAG, "追溯保存整顿计划发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚整顿计划主表发生异常", e1);
            }
        }
    }

    /***
     * 查询某个终端的整改计划
     */
    public List<DealStc> getTermDealPlan(String terminalkey) {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        List<DealStc> valueLst = new ArrayList<DealStc>();
        try {

            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitRepairterMDao dao = helper.getDao(MitRepairterM.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            valueLst = dao.queryTermDealPlan(helper,terminalkey);// queryTermDealPlan
            connection.commit(null);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "查询图片类型表中所有记录", e);
        }
        return valueLst;
    }

}
