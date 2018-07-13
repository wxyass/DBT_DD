package et.tsingtaopad.dd.ddzs.zsinvoicing;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitValsupplyMTempDao;
import et.tsingtaopad.db.dao.MstVistproductInfoDao;
import et.tsingtaopad.db.dao.MstVistproductInfoTempDao;
import et.tsingtaopad.db.table.MitValaddaccountMTemp;
import et.tsingtaopad.db.table.MitValaddaccountproMTemp;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.db.table.MstCheckexerecordInfoTemp;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.db.table.MstVistproductInfoTemp;
import et.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import et.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzItemIndex;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class ZsInvoicingService extends XtShopVisitService {

    private final String TAG = "XtInvoicingService";

    public ZsInvoicingService(Context context, Handler handler) {
        super(context, handler);
    }


    /***
     * 删除重复拜访产品（相同产品、供货商只保留一个）
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
            where.isNotNull("productkey");
            where.and();
            where.ne("deleteflag", "1");
            qb.orderBy("productkey", true);
            qb.orderBy("agencykey", true);
            qb.orderBy("updatetime", false);
            List<MstVistproductInfoTemp> valueLst =qb.query();
            Map<String, MstVistproductInfoTemp> map=new HashMap<String, MstVistproductInfoTemp>();
            if (!CheckUtil.IsEmpty(valueLst)) {
                for (MstVistproductInfoTemp item : valueLst) {
                    String key=item.getProductkey()+item.getAgencykey();
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
    public List<XtInvoicingStc> queryMineProFromTemp(String visitId, String termKey) {

        List<XtInvoicingStc> lst = new ArrayList<XtInvoicingStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            lst = dao.queryXtMineProByTemp(helper, visitId,termKey);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }
        return lst;
    }

    /**
     * 从临时表中 获取某次拜访的我品的进销存数据情况
     *
     * @param //helper
     * @param visitId   拜访主键
     * @return
     */
    public List<MitValsupplyMTemp> queryMineProToValSupplyTemp(String visitId, String termKey,String mitValterMTempKey) {

        List<XtInvoicingStc> lst = new ArrayList<XtInvoicingStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstVistproductInfoDao dao = helper.getDao(MstVistproductInfo.class);
            lst = dao.queryXtMineProByTemp(helper, visitId,termKey);
        } catch (SQLException e) {
            Log.e(TAG, "获取终端表DAO对象失败", e);
        }

        List<MitValsupplyMTemp> mitValsupplyMTemps = new ArrayList<MitValsupplyMTemp>();
        MitValsupplyMTemp mitValsupplyMTemp;
        for (XtInvoicingStc xtInvoicingStc:lst) {
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
            mitValsupplyMTemps.add(mitValsupplyMTemp);

        }
        return mitValsupplyMTemps;
    }


    /***
     * 通过终端获取此终端供货关系
     * @param valterid
     * @return
     */
    public List<MitValsupplyMTemp> queryValSupplyTemp(String valterid) {
        List<MitValsupplyMTemp> list = new ArrayList<MitValsupplyMTemp>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitValsupplyMTempDao mitValsupplyMTempDao = helper.getDao(MitValsupplyMTemp.class);
            QueryBuilder<MitValsupplyMTemp, String> qb = mitValsupplyMTempDao.queryBuilder();
            Where<MitValsupplyMTemp, String> where = qb.where();
            where.eq("valterid", valterid);
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 新增供货关系指标记录表进行更新
     *
     * 在这里只做了一个处理, 当有用户在进销存 删除一个供货关系时,又再次添加了这个产品的供货关系,
     * 这个方法对这种情况作了处理,会将这个产品的指标设为空白或不合格(都是-1)
     *
     * @param visitId
     * @param proId
     */
    public void updateMstcheckexerecordInfoTempDeleteflag(String visitId,String proId){
        // 新增供货关系时，对指标记录表进行更新
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstCheckexerecordInfoTemp, String> tempDao = helper.getDao(MstCheckexerecordInfoTemp.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            QueryBuilder<MstCheckexerecordInfoTemp, String> qb = tempDao.queryBuilder();
            qb.where().eq("visitkey", visitId).and()
                    .eq("productkey", proId).and()
                    .eq("deleteflag", "1");
            List<MstCheckexerecordInfoTemp> list = qb.query();
            StringBuffer buffer = new StringBuffer();
            buffer = new StringBuffer();
            //
            if (list != null && list.size()>0)
            {
                //acresult='-1'如果是产品删除后  再新增产品  状态值就改为-1  请选择(指标状态查询处可体现)
                buffer.append("update mst_checkexerecord_info_temp set deleteflag='0', acresult='-1'");
                //buffer.append("update mst_checkexerecord_info_temp set deleteflag='0' ");
            }else{
                buffer.append("update mst_checkexerecord_info_temp set deleteflag='0' ");
            }
            buffer.append("where visitkey=? and productkey=? ");
            tempDao.executeRaw(buffer.toString(), new String[] {visitId, proId});
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "新增供货关系时指标记录表数据更新发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚指标记录表数据更新发生异常", e1);
            }
        }
    }

    /**
     * 保存追溯进销存页面数据，MitValsupplyMTemp
     *
     * @param dataLst       我品进度销存数据
     */
    public void saveZsInvoicing(List<MitValsupplyMTemp> dataLst) {
        if (CheckUtil.IsEmpty(dataLst)) {
            return;
        }
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValsupplyMTemp, String> supplyDao = helper.getDao(MitValsupplyMTemp.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            for (MitValsupplyMTemp pro : dataLst) {
                supplyDao.createOrUpdate(pro);
            }
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存进销存数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
    }

    public void saveZsInvoicingTz(List<ZsTzItemIndex> termLedgerInfos) {
        if (CheckUtil.IsEmpty(termLedgerInfos)) {
            return;
        }
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitValaddaccountMTemp, String> supplyDao = helper.getDao(MitValaddaccountMTemp.class);
            Dao<MitValaddaccountproMTemp, String> valaddaccountproMTempDao = helper.getDao(MitValaddaccountproMTemp.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);
            MitValaddaccountMTemp valaddaccountMTemp ;
            for (ZsTzItemIndex pro : termLedgerInfos) {
                // valaddaccountMTemp = new MitValaddaccountMTemp();
                supplyDao.createOrUpdate(pro);
                for (MitValaddaccountproMTemp mTemp :pro.getIndexValueLst()){
                    valaddaccountproMTempDao.createOrUpdate(mTemp);
                }
            }
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存进销存数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
    }
}
