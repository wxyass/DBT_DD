package dd.tsingtaopad.dd.ddxt.term.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import dd.tsingtaopad.core.util.pinyin.PinYin4jUtil;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MstTerminalinfoMDao;
import dd.tsingtaopad.db.table.MstAgencysupplyInfo;
import dd.tsingtaopad.db.table.MstTerminalinfoM;
import dd.tsingtaopad.dd.ddxt.term.XtTermService;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.main.visit.shopvisit.term.domain.TermSequence;


/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：XtTermCartService.java</br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
@SuppressLint("DefaultLocale")
public class XtTermCartService extends XtTermService{

    private final String TAG = "XtTermCartService";

    private Context context;

    public XtTermCartService(Context context) {
        super(context);
        // this.context = context;
    }

    /**
     * 获取购物车终端列表 获取相应的数据列表数据(协同)
     *
     * @return
     */
    public List<XtTermSelectMStc> queryCartTermList() {

        List<XtTermSelectMStc> terminalList = new ArrayList<XtTermSelectMStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            List<XtTermSelectMStc> termlst = dao.queryCartTermLst(helper);
            terminalList.addAll(termlst);
        } catch (SQLException e) {
            Log.e(TAG, "获取线路表DAO对象失败", e);
        }
        return terminalList;
    }

    /**
     * 获取购物车终端列表 获取相应的数据列表数据(协同+追溯 所有的终端)
     *
     * @return
     */
    public List<XtTermSelectMStc> queryAllCartTermList() {

        List<XtTermSelectMStc> terminalList = new ArrayList<XtTermSelectMStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            List<XtTermSelectMStc> termlst = dao.queryAllCartTermLst(helper);
            terminalList.addAll(termlst);
        } catch (SQLException e) {
            Log.e(TAG, "获取线路表DAO对象失败", e);
        }
        return terminalList;
    }

    /**
     * 获取购物车终端列表 获取相应的数据列表数据(追溯)
     *
     * @return
     */
    public List<XtTermSelectMStc> queryZsCartTermList() {

        List<XtTermSelectMStc> terminalList = new ArrayList<XtTermSelectMStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            List<XtTermSelectMStc> termlst = dao.queryZsCartTermLst(helper);
            terminalList.addAll(termlst);
        } catch (SQLException e) {
            Log.e(TAG, "获取线路表DAO对象失败", e);
        }
        return terminalList;
    }




    /**
     * 按条件查询终端列表
     *
     * @param termLst       线路下所有终端
     * @param searchStr     查询条件
     * @param termPinyinMap 各终端名称的拼音
     */
    public List<XtTermSelectMStc> searchTermByname(List<XtTermSelectMStc> termLst, String searchStr, Map<String, String> termPinyinMap) {

        List<XtTermSelectMStc> tempLst = new ArrayList<XtTermSelectMStc>();
        if (!CheckUtil.IsEmpty(termLst)) {
            if (CheckUtil.isBlankOrNull(searchStr)) {
                tempLst = termLst;
            } else {
                searchStr = searchStr.toLowerCase();
                for (XtTermSelectMStc item : termLst) {
                    Pattern pattern = Pattern.compile("[a-z]*");
                    if (pattern.matcher(searchStr).matches()) {
                        String pinyin = termPinyinMap.get(item.getTerminalkey());
                        if (pinyin.indexOf("," + searchStr) > -1 || pinyin.contains(searchStr)) {
                            tempLst.add(item);
                        }
                    } else {
                        if (item.getTerminalname().contains(searchStr)) {
                            tempLst.add(item);
                        }
                    }
                }
            }
        }
        return tempLst;
    }

    /**
     * 获取各终端名称的拼音
     *
     * @param termLst 终端列表
     */
    public Map<String, String> getAllTermPinyin(List<XtTermSelectMStc> termLst) {
        Map<String, String> termPinyinMap = new HashMap<String, String>();
        for (XtTermSelectMStc item : termLst) {
            termPinyinMap.put(item.getTerminalkey(), "," + PinYin4jUtil.converterToFirstSpell(item.getTerminalname()).toLowerCase());
        }

        return termPinyinMap;
    }





    /***
     * 协同 购物车更新终端顺序 不上传
     * @param list
     */
    public void updateXtTermSequence(List<TermSequence> list) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            connection = new AndroidDatabaseConnection(database, true);
            Log.e(TAG, "更改巡店拜访顺序");
            connection.setAutoCommit(false);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            //dao.updateTermSequence(helper, list);
            dao.updateXtTempSequence(helper, list);
            connection.commit(null);
            //sendTermSequenceRequest(list);
        } catch (SQLException e) {
            Log.e(TAG, "更改巡店拜访顺序失败", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /***
     * 追溯 购物车更新终端顺序 不上传
     * @param list
     */
    public void updateZsTermSequence(List<TermSequence> list) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            connection = new AndroidDatabaseConnection(database, true);
            Log.e(TAG, "更改巡店拜访顺序");
            connection.setAutoCommit(false);
            MstTerminalinfoMDao dao = helper.getDao(MstTerminalinfoM.class);
            //dao.updateTermSequence(helper, list);
            dao.updateXtTempSequence(helper, list);
            connection.commit(null);
            //sendTermSequenceRequest(list);
        } catch (SQLException e) {
            Log.e(TAG, "更改巡店拜访顺序失败", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }




    /**
     * 删除协同购物车
     *
     * @param terminalkey 终端ID
     */
    public boolean deleteXtTermCart(String terminalkey, String type) {
        boolean isFlag = false;
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstAgencysupplyInfo, String> supplyDao = helper.getDao(MstAgencysupplyInfo.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 删除拜访产品-竞品我品记录表，相关数据
            StringBuffer buffer = new StringBuffer();
            if ("1".equals(type)) { // 删除协同终端夹
                buffer.append("delete from mst_terminalinfo_m_cart ");
            } else if ("2".equals(type)) {// 删除追溯终端夹
                buffer.append("delete from mst_terminalinfo_m_zscart ");
            }
            buffer.append("where terminalkey = ? ");
            supplyDao.executeRaw(buffer.toString(), new String[]{terminalkey});

            connection.commit(null);
            isFlag = true;

        } catch (Exception e) {
            isFlag = false;
            DbtLog.logUtils(TAG, "解除终端失败");
            DbtLog.logUtils(TAG, e.getMessage());
            e.printStackTrace();
            Log.e(TAG, "解除终端发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
        return isFlag;
    }

}
