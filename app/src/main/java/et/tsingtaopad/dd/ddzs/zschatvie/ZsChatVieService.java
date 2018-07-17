package et.tsingtaopad.dd.ddzs.zschatvie;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitValcmpMTempDao;
import et.tsingtaopad.db.dao.MstVistproductInfoDao;
import et.tsingtaopad.db.dao.MstVistproductInfoTempDao;
import et.tsingtaopad.db.table.MitValcmpMTemp;
import et.tsingtaopad.db.table.MitValcmpotherMTemp;
import et.tsingtaopad.db.table.MstCmpsupplyInfoTemp;
import et.tsingtaopad.db.table.MstVisitMTemp;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.db.table.MstVistproductInfoTemp;
import et.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;
import et.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class ZsChatVieService extends XtShopVisitService {

    private final String TAG = "XtChatVieService";

    public ZsChatVieService(Context context, Handler handler) {
        super(context, handler);
    }

    /***
     * 删除竞品重复拜访产品
     * @param //helper
     * @param //visitId
     * @param //isChatViw
     */
    public void delRepeatVistProduct(String visitkey){
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoTempDao dao = helper.getDao(MstVistproductInfoTemp.class);
            QueryBuilder<MstVistproductInfoTemp, String> qb=dao.queryBuilder();
            Where<MstVistproductInfoTemp, String> where=  qb.where();
            where.eq("visitkey", visitkey);
            where.and();
            where.isNull("productkey");
            where.and();
            where.ne("deleteflag", "1");
            qb.orderBy("productkey", true);
            qb.orderBy("updatetime", false);
            List<MstVistproductInfoTemp> valueLst =qb.query();
            Map<String, MstVistproductInfoTemp> map=new HashMap<String, MstVistproductInfoTemp>();
            if (!CheckUtil.IsEmpty(valueLst)) {
                for (MstVistproductInfoTemp item : valueLst) {
                    String key=item.getCmpproductkey();
                    if(!map.containsKey(key)){
                        map.put(key, item);
                    }else{
                        dao.delete(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "删除重复拜访产品", e);
        }
    }

    /**
     * 从临时表中 获取某次拜访的我品的进销存数据情况
     *
     * @param //helper
     * @param visitId   拜访主键
     * @return
     */
    public List<XtChatVieStc> queryVieProTemp(String visitId) {

        List<XtChatVieStc> lst = new ArrayList<XtChatVieStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            lst = dao.queryXtVieProByTemp(helper, visitId);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        return lst;
    }

    /**
     * 保存销存页面数据，MST_VISTPRODUCT_INFO
     *
     * @param dataLst       我品进度销存数据
     * @param visitId       拜访主键
     * @param termId        终端主键
     * @param visitM        拜访主表相关信息
     */
    public void saveXtVie(List<XtChatVieStc> dataLst,
                        String visitId, String termId, MstVisitMTemp visitM) {

        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            /*Dao<MstVistproductInfoTemp, String> proDao = helper.getMstVistproductInfoDao();
            Dao<MstVisitMTemp, String> visitDao = helper.getMstVisitMDao();
            Dao<MstCmpsupplyInfoTemp, String> supplyDao = helper.getMstCmpsupplyInfoDao();*/
            Dao<MstVistproductInfoTemp, String> proDao = helper.getDao(MstVistproductInfoTemp.class);
            Dao<MstVisitMTemp, String> visitDao = helper.getDao(MstVisitMTemp.class);
            Dao<MstCmpsupplyInfoTemp, String> supplyDao = helper.getDao(MstCmpsupplyInfoTemp.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 维护拜访产品-竞品记录
            StringBuffer buffer;
            MstVistproductInfoTemp proItem;
            for(XtChatVieStc item : dataLst) {

                // 新增
                buffer = new StringBuffer();
                if (CheckUtil.isBlankOrNull(item.getRecordId())) {
                    proItem = new MstVistproductInfoTemp();
                    proItem.setRecordkey(FunUtil.getUUID());
                    proItem.setVisitkey(visitId);
                    proItem.setCmpproductkey(item.getProId());
                    proItem.setCmpcomkey(item.getCommpayId());
                    proItem.setAgencykey(item.getAgencyId());
                    proItem.setAgencyname(item.getAgencyName());
                    proItem.setPurcprice(Double.valueOf(FunUtil.isNullToZero(item.getChannelPrice())));
                    proItem.setRetailprice(Double.valueOf(FunUtil.isNullToZero(item.getSellPrice())));
                    proItem.setCurrnum(Double.valueOf(FunUtil.isNullToZero(item.getCurrStore())));
                    proItem.setSalenum(Double.valueOf(FunUtil.isNullToZero(item.getMonthSellNum())));
                    proItem.setPadisconsistent(ConstValues.FLAG_0);
                    proItem.setDeleteflag(ConstValues.FLAG_0);
                    //proItem.setCreuser(ConstValues.loginSession.getUserCode());
                    //proItem.setCreuser(PrefUtils.getString(context, "userCode", ""));
                    //proItem.setUpdateuser(ConstValues.loginSession.getUserCode());
                    //proItem.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                    proItem.setRemarks(item.getDescribe());
                    proDao.create(proItem);

                    // 更新
                } else {
                    buffer.append("update mst_vistproduct_info_temp set ");
                    buffer.append("purcprice=?, retailprice=?, ");
                    buffer.append(" currnum=?, salenum=?, remarks=?, agencykey=?, agencyname=?,");
                    buffer.append("padisconsistent='0' ");
                    buffer.append("where recordkey=? ");
                    String[] args = new String[8];
                    args[0] = FunUtil.isNullToZero(item.getChannelPrice());
                    args[1] = FunUtil.isNullToZero(item.getSellPrice());
                    args[2] = FunUtil.isNullToZero(item.getCurrStore());
                    args[3] = FunUtil.isNullToZero(item.getMonthSellNum());
                    args[4] = FunUtil.isNullSetSpace(item.getDescribe());
                    args[5] = FunUtil.isNullSetSpace(item.getAgencyId());
                    args[6] = FunUtil.isNullSetSpace(item.getAgencyName());
                    args[7] = FunUtil.isNullSetSpace(item.getRecordId());
                    proDao.executeRaw(buffer.toString(), args);
                }
            }

            // 维护竞品供货关系表
            List<MstCmpsupplyInfoTemp> supplyLst = supplyDao.queryForEq("terminalkey", termId);
            MstCmpsupplyInfoTemp supply;
            for (XtChatVieStc pro : dataLst) {
                supply = null;
                for (MstCmpsupplyInfoTemp item : supplyLst) {
                    if (pro.getProId().equals(item.getCmpproductkey())) {
                        supply = item;
                        break;
                    }
                }
                if (supply == null) {
                    supply = new MstCmpsupplyInfoTemp();
                    supply.setCmpsupplykey(FunUtil.getUUID());
                    supply.setCmpproductkey(pro.getProId());
                    supply.setTerminalkey(termId);
                    //supply.setCreuser(ConstValues.loginSession.getUserCode());
                    supply.setCreuser(PrefUtils.getString(context, "userCode", ""));
                }
                supply.setCmpcomkey(pro.getAgencyId());
                supply.setStatus(ConstValues.FLAG_0);
                supply.setInprice(pro.getChannelPrice());
                supply.setReprice(pro.getSellPrice());
                supply.setPadisconsistent(ConstValues.FLAG_0);
                supply.setDeleteflag(ConstValues.FLAG_0);
                //supply.setUpdateuser(ConstValues.loginSession.getUserCode());
                supply.setUpdateuser(PrefUtils.getString(context, "userCode", ""));
                supplyDao.createOrUpdate(supply);
            }

            // 更新拜访主表拜访记录
            buffer = new StringBuffer();
            buffer.append("update mst_visit_m_temp set status=?, iscmpcollapse=?, remarks=? ,padisconsistent = '0' ");
            buffer.append("where visitkey= ? ");
            visitDao.executeRaw(buffer.toString(), new String[] {
                    visitM.getStatus(), visitM.getIscmpcollapse(), visitM.getRemarks(), visitId});

            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存聊竞品数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚聊竞品数据发生异常", e1);
            }
        }
    }
    /**
     * 保存追溯聊竞品页面数据，MitValcmpMTemp,MitValcmpotherMTemp
     *
     * @param dataLst       竞品数据
     * @param mitValcmpotherMTemp
     */
    public void saveZsVie(List<MitValcmpMTemp> dataLst,
                        MitValcmpotherMTemp mitValcmpotherMTemp) {

        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValcmpMTemp, String> mitValcmpMTempDao = helper.getDao(MitValcmpMTemp.class);
            Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao = helper.getDao(MitValcmpotherMTemp.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            for (MitValcmpMTemp cmppro : dataLst) {
                mitValcmpMTempDao.createOrUpdate(cmppro);
            }
            mitValcmpotherMTempDao.createOrUpdate(mitValcmpotherMTemp);
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存聊竞品数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚聊竞品数据发生异常", e1);
            }
        }
    }
    /**
     * 保存追溯原聊竞品页面中的拜访记录 ，MitValcmpMTemp,MitValcmpotherMTemp
     *
     * @param mitValcmpotherMTemp
     */
    public void saveZsVisitremark(MitValcmpotherMTemp mitValcmpotherMTemp) {

        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao = helper.getDao(MitValcmpotherMTemp.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            mitValcmpotherMTempDao.createOrUpdate(mitValcmpotherMTemp);
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存聊竞品数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚聊竞品数据发生异常", e1);
            }
        }
    }

    /**
     * 删除经销商与终端的产品供应关系
     *
     * @param recordKey 拜访产品-竞品我品记录表主键
     * @param termId    终端ID
     * @param proId     产品ID
     */
    public boolean deleteSupply(String recordKey, String termId, String proId) {
        boolean isFlag=false;
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstVistproductInfo, String> proDao =
                    helper.getDao(MstVistproductInfo.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 删除拜访产品-竞品我品记录表，相关数据
            StringBuffer buffer = new StringBuffer();
            buffer.append("delete from mst_vistproduct_info_temp "); // 直接删除,因为记录是跟着visitkey的
            buffer.append("where RECORDKEY=? ");
            proDao.executeRaw(buffer.toString(), new String[] {recordKey});
            //            StringBuffer buffer = new StringBuffer();
            //            buffer.append("update mst_vistproduct_info set ");
            //            buffer.append("padisconsistent ='0', deleteflag = '1' ");
            //            buffer.append("where recordkey=? ");
            //            proDao.executeRaw(buffer.toString(), new String[] {recordKey});

            // 更新竞品供货关系
            String currDate = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
            buffer = new StringBuffer();
            buffer.append("update mst_cmpsupply_info_temp set ");
            buffer.append("status ='1', cmpinvaliddate = ? ,padisconsistent ='0' ");
            buffer.append("where terminalkey = ? and cmpproductkey = ? ");
            proDao.executeRaw(buffer.toString(), new String[] {currDate, termId, proId});

            connection.commit(null);
            isFlag=true;
        } catch (Exception e) {
            isFlag=false;
            DbtLog.logUtils(TAG,"解除供货关系失败");
            DbtLog.logUtils(TAG,e.getMessage());
            e.printStackTrace();
            Log.e(TAG, "保存进销存数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
        return isFlag;
    }

    public List<MitValcmpMTemp> queryValVieSupplyTemp(String valterid) {
        List<MitValcmpMTemp> list = new ArrayList<MitValcmpMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValcmpMTempDao mitValsupplyMTempDao = helper.getDao(MitValcmpMTemp.class);
            QueryBuilder<MitValcmpMTemp, String> qb = mitValsupplyMTempDao.queryBuilder();
            Where<MitValcmpMTemp, String> where = qb.where();
            where.eq("valterid", valterid);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
