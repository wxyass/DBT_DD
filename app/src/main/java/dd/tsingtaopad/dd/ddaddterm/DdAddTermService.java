package dd.tsingtaopad.dd.ddaddterm;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.util.dbtutil.ViewUtil;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.CmmAreaM;
import dd.tsingtaopad.db.table.CmmDatadicM;
import dd.tsingtaopad.db.table.MitTerminalM;
import dd.tsingtaopad.db.table.MstAgencyinfoM;
import dd.tsingtaopad.db.table.MstGridM;
import dd.tsingtaopad.db.table.MstMarketareaM;
import dd.tsingtaopad.db.table.MstRouteM;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class DdAddTermService extends XtShopVisitService {

    private final String TAG = "XtSayhiService";
    DdAddTermFragment.MyHandler handler;

    public DdAddTermService(Context context, DdAddTermFragment.MyHandler handler) {
        super(context, handler);
        this.handler = handler;
    }

    /**
     * 获取数据字典信息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CmmDatadicM> initDataDictionary() {
        List<CmmDatadicM> dataDicLst = new ArrayList<CmmDatadicM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            QueryBuilder<CmmDatadicM, String> qBuilder =
                    helper.getCmmDatadicMDao().queryBuilder();
            Where<CmmDatadicM, String> where = qBuilder.where();
            where.or(where.isNull("deleteflag"), where.eq("deleteflag", "0"));
            qBuilder.orderBy("parentcode", true).orderBy("orderbyno", true);
            dataDicLst = qBuilder.query();
        } catch (SQLException e) {
            Log.e(TAG, "初始化数据字典失败", e);
        }

        return dataDicLst;
    }

    /**
     * 初始化终端等级
     */
    public List<KvStc> initDataDicByTermLevel() {
        List<CmmDatadicM> dataDicLst = initDataDictionary();
        // 获取数据字典表中区域字典对应的父ID
        String termLevel = PropertiesUtil.getProperties("datadic_termLevel");

        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (termLevel.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode(),
                        item.getDicname(), item.getParentcode()));
            } else if (kvLst.size() > 0) {
                break;
            }
        }
        return kvLst;
    }

    /**
     * 初始化销售渠道
     */
    public List<KvStc> initDataDicBySellChannel() {

        // 获取数据字典表中区域字典对应的父ID
        String sellChannel = PropertiesUtil.getProperties("datadic_sellChannel");
        List<KvStc> kvLst = new ArrayList<KvStc>();
        kvLst = queryChildForDataDic(sellChannel, 1);
        return kvLst;
    }

    /**
     *  递归遍历数据字典- 销售渠道
     *
     * @param parentCode    父ID
     * @param level         当前等级  1
     * @return
     */
    private List<KvStc> queryChildForDataDic(String parentCode, int level) {
        List<CmmDatadicM> dataDicLst = initDataDictionary();// 数据源
        List<KvStc> kvLst = null;
        if (level <= 3) {
            kvLst = new ArrayList<KvStc>();
            KvStc kvItem = new KvStc();
            int nextLevel = level + 1;
            for (CmmDatadicM dataItem : dataDicLst) {
                if (parentCode.equals(dataItem.getParentcode())) {
                    kvItem = new KvStc(dataItem.getDiccode(),dataItem.getDicname(), dataItem.getParentcode());
                    kvItem.setChildLst(queryChildForDataDic(dataItem.getDiccode(), nextLevel));
                    kvLst.add(kvItem);
                }
            }

            // 添加请选择
            /*if (level <= 3) {
                kvItem = new KvStc("-1", "请选择", "-1");
            }
            if (level <= 2) {
                kvItem.getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            if (level <= 1) {
                kvItem.getChildLst().get(0).getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            kvLst.add(0,kvItem);*/
        }
        return kvLst;
    }

    /**
     * 初始化终端区域类型
     */
    public List<KvStc> initDataDicByAreaType() {
        List<CmmDatadicM> dataDicLst = initDataDictionary();

        // 获取数据字典表中区域字典对应的父ID
        String areaType=PropertiesUtil.getProperties("datadic_areaType");

        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (areaType.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode()
                        , item.getDicname(), item.getParentcode()));
            } else if (kvLst.size() > 0) {
                break;
            }
        }
        return kvLst;
    }

    /**
     *  递归遍历区域树
     *
     * @param parentId      父ID
     * @return
     */
    public List<KvStc> queryChildForArea(String parentId) {

        List<CmmAreaM> areaLst = new ArrayList<CmmAreaM>();
        MstAgencyinfoM agencyM = new MstAgencyinfoM();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);

            // 获取省市省
            Dao<CmmAreaM, String> cmmAreaMDao = helper.getCmmAreaMDao();
            QueryBuilder<CmmAreaM, String> qBuilder = cmmAreaMDao.queryBuilder();
            Where<CmmAreaM, String> where = qBuilder.where();
            where.or(where.isNull("deleteflag"), where.eq("deleteflag", "0"));
            qBuilder.orderBy("areacode", true).orderBy("orderbyno", true);
            areaLst = qBuilder.query();

        } catch (Exception e) {
            Log.e(TAG, "获取经销售商信息失败", e);
        }

        List<KvStc> kvLst = new ArrayList<KvStc>();
        KvStc kvItem = new KvStc();
        for (CmmAreaM areaItem : areaLst) {
            if (parentId.equals(FunUtil.isBlankOrNullTo(areaItem.getParentcode(), "-1"))) {
                kvItem = new KvStc(areaItem.getAreacode(), areaItem.getAreaname(), areaItem.getParentcode());
                kvLst.add(kvItem);
            }
        }
        return kvLst;
    }

    /***
     * 获取二级区域列表
     * @param //tempDao
     * @param //visitId
     * @return
     * @throws SQLException
     */
    public List<MstMarketareaM> getMstMarketareaMList(String areapid) {
        AndroidDatabaseConnection connection = null;
        List<MstMarketareaM> valueLst = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstMarketareaM, String> valueDao = helper.getMstMarketareaMDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);


            QueryBuilder<MstMarketareaM, String> valueQb = valueDao.queryBuilder();
            Where<MstMarketareaM, String> valueWr = valueQb.where();
            valueWr.eq("areapid", areapid);
            valueLst = valueQb.query();
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "查看拜访记录去除重复指标出错", e);
            try {
                connection.rollback(null);
                ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return valueLst;
    }

    /***
     * 获取某个二级区域  的定格列表
     * @param //tempDao
     * @param //visitId
     * @return
     * @throwsException
     */
    public List<MstGridM> getMstGridMList(String areaid) {
        AndroidDatabaseConnection connection = null;
        List<MstGridM> valueLst = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstGridM, String> valueDao = helper.getMstGridMDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);


            QueryBuilder<MstGridM, String> valueQb = valueDao.queryBuilder();
            Where<MstGridM, String> valueWr = valueQb.where();
            valueWr.eq("areaid", areaid);
            valueLst = valueQb.query();
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "查看拜访记录去除重复指标出错", e);
            try {
                connection.rollback(null);
                ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return valueLst;
    }

    /***
     * 获取某个定格  的路线列表
     * @param //tempDao
     * @param //visitId
     * @return
     * @throwsException
     */
    public List<MstRouteM> getMstRouteMList(String gridkey) {
        AndroidDatabaseConnection connection = null;
        List<MstRouteM> valueLst = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstRouteM, String> valueDao = helper.getMstRouteMDao();
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);


            QueryBuilder<MstRouteM, String> valueQb = valueDao.queryBuilder();
            Where<MstRouteM, String> valueWr = valueQb.where();
            valueWr.eq("gridkey", gridkey);
            valueLst = valueQb.query();
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "查看拜访记录去除重复指标出错", e);
            try {
                connection.rollback(null);
                ViewUtil.sendMsg(context, R.string.agencyvisit_msg_failsave);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return valueLst;
    }


    // 保存督导新增终端
    public void saveMitTerminalM(MitTerminalM info) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitTerminalM, String> mitTerminalMDao = helper.getDao(MitTerminalM.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);


            info.setCreuserareaid(PrefUtils.getString(context,"departmentid",""));
            info.setCredate(new Date());
            info.setUpdatedate(new Date());
            info.setCreuser(PrefUtils.getString(context,"userid",""));
            info.setUpdateuser(PrefUtils.getString(context,"userid",""));
            info.setUploadflag("1");//是否上传 0:不上传  1: 需上传
            info.setPadisconsistent("0");// 是否已上传  0:未上传 1:已上传
            mitTerminalMDao.create(info);

            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存督导新增终端 数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚督导新增终端 数据发生异常", e1);
            }
        }
    }
}
