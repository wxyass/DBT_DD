package et.tsingtaopad.dd.ddxt.updata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.ViewUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitValterMDao;
import et.tsingtaopad.db.dao.MitValterMTempDao;
import et.tsingtaopad.db.dao.MitVisitMDao;
import et.tsingtaopad.db.dao.MstVisitMDao;
import et.tsingtaopad.db.dao.MstVisitMTempDao;
import et.tsingtaopad.db.table.MitAgencysupplyInfo;
import et.tsingtaopad.db.table.MitCameraInfoM;
import et.tsingtaopad.db.table.MitCheckexerecordInfo;
import et.tsingtaopad.db.table.MitCmpsupplyInfo;
import et.tsingtaopad.db.table.MitCollectionexerecordInfo;
import et.tsingtaopad.db.table.MitGroupproductM;
import et.tsingtaopad.db.table.MitPromotermInfo;
import et.tsingtaopad.db.table.MitTerminalinfoM;
import et.tsingtaopad.db.table.MitValaddaccountM;
import et.tsingtaopad.db.table.MitValaddaccountMTemp;
import et.tsingtaopad.db.table.MitValaddaccountproM;
import et.tsingtaopad.db.table.MitValaddaccountproMTemp;
import et.tsingtaopad.db.table.MitValagreeM;
import et.tsingtaopad.db.table.MitValagreeMTemp;
import et.tsingtaopad.db.table.MitValagreedetailM;
import et.tsingtaopad.db.table.MitValagreedetailMTemp;
import et.tsingtaopad.db.table.MitValcheckitemM;
import et.tsingtaopad.db.table.MitValcheckitemMTemp;
import et.tsingtaopad.db.table.MitValchecktypeM;
import et.tsingtaopad.db.table.MitValchecktypeMTemp;
import et.tsingtaopad.db.table.MitValcmpM;
import et.tsingtaopad.db.table.MitValcmpMTemp;
import et.tsingtaopad.db.table.MitValcmpotherM;
import et.tsingtaopad.db.table.MitValcmpotherMTemp;
import et.tsingtaopad.db.table.MitValgroupproM;
import et.tsingtaopad.db.table.MitValgroupproMTemp;
import et.tsingtaopad.db.table.MitValpicM;
import et.tsingtaopad.db.table.MitValpicMTemp;
import et.tsingtaopad.db.table.MitValpromotionsM;
import et.tsingtaopad.db.table.MitValpromotionsMTemp;
import et.tsingtaopad.db.table.MitValsupplyM;
import et.tsingtaopad.db.table.MitValsupplyMTemp;
import et.tsingtaopad.db.table.MitValterM;
import et.tsingtaopad.db.table.MitValterMTemp;
import et.tsingtaopad.db.table.MitVisitM;
import et.tsingtaopad.db.table.MitVistproductInfo;
import et.tsingtaopad.db.table.MstAgencysupplyInfo;
import et.tsingtaopad.db.table.MstAgencysupplyInfoTemp;
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
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.db.table.MstTerminalinfoMTemp;
import et.tsingtaopad.db.table.MstVisitM;
import et.tsingtaopad.db.table.MstVisitMTemp;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.db.table.MstVistproductInfoTemp;


/**
 * 将临时表数据 copy到协同表
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtShopCopyService {

    private final String TAG = "XtShopVisitService";

    protected Context context;
    protected Handler handler;

	private String prevVisitId;// 获取上次拜访主键

	private String prevVisitDate; // 上次拜访日期

    public XtShopCopyService(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    /**
     * 更新拜访离店时间及是否要上传标志
     *
     * @param visitId      拜访主键
     * @param termId       终端主键
     * @param visitEndDate 离店时间
     * @param uploadFlag   是否要上传标志
     */
    public void copyXtUpload(String visitId, String termId, String terminalcode,String visitEndDate, String uploadFlag) {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();
            MstVisitMDao dao = helper.getDao(MstVisitM.class);
            MstVisitMTempDao visitTempDao = helper.getDao(MstVisitMTemp.class);
            MitVisitMDao mitVisitMDao = helper.getDao(MitVisitM.class);

            // 获取当前拜访的指标结果记录情况
            Dao<MstCheckexerecordInfoTemp, String> tempDao = helper.getMstCheckexerecordInfoTempDao();
            Dao<MstCheckexerecordInfo, String> checkInfoDao = helper.getMstCheckexerecordInfoDao();
            Dao<MitCheckexerecordInfo, String> mitCheckInfoDao = helper.getMitCheckexerecordInfoDao();

            Dao<MstCollectionexerecordInfo, String> collectionDao = helper.getMstCollectionexerecordInfoDao();
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao = helper.getMstCollectionexerecordInfoTempDao();
            Dao<MitCollectionexerecordInfo, String> mitCollectionDao = helper.getMitCollectionexerecordInfoDao();

            Dao<MstCameraInfoM, String> cameraDao = helper.getMstCameraiInfoMDao();
            Dao<MstCameraInfoMTemp, String> cameraTempDao = helper.getMstCameraInfoMTempDao();
            Dao<MitCameraInfoM, String> mitCameraDao = helper.getMitCameraInfoMDao();

            Dao<MstPromotermInfo, String> promDao = helper.getMstPromotermInfoDao();
            Dao<MstPromotermInfoTemp, String> promTempDao = helper.getMstPromotermInfoTempDao();
            Dao<MitPromotermInfo, String> mitPromotermDao = helper.getMitPromotermInfoDao();

            Dao<MstVistproductInfo, String> proDao = helper.getMstVistproductInfoDao();
            Dao<MstVistproductInfoTemp, String> proTempDao = helper.getMstVistproductInfoTempDao();
            Dao<MitVistproductInfo, String> mitVistproductDao = helper.getMitVistproductInfoDao();

            Dao<MstTerminalinfoM, String> terminalinfoMDao = helper.getMstTerminalinfoMDao();
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao = helper.getMstTerminalinfoMTempDao();
            Dao<MitTerminalinfoM, String> mitTerminalDao = helper.getMitTerminalinfoMDao();

            Dao<MstAgencysupplyInfo, String> agencyDao = helper.getMstAgencysupplyInfoDao();
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao = helper.getMstAgencysupplyInfoTempDao();
            Dao<MitAgencysupplyInfo, String> mitAgencysupplyDao = helper.getMitAgencysupplyInfoDao();

            Dao<MstCmpsupplyInfo, String> cmpSupplyDao = helper.getMstCmpsupplyInfoDao();
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao = helper.getMstCmpsupplyInfoTempDao();
            Dao<MitCmpsupplyInfo, String> mitCmpsupplyDao = helper.getMitCmpsupplyInfoDao();


            Dao<MstGroupproductM, String> groupproDao = helper.getMstGroupproductMDao();
            Dao<MstGroupproductMTemp, String> groupproTempDao = helper.getMstGroupproductMTempDao();
            Dao<MitGroupproductM, String> mitGroupproMDao = helper.getMitGroupproductMDao();


            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            MstVisitMTemp visitTemp = visitTempDao.queryForId(visitId);
            // 复制拜访主表
            //createMstVisitMByMstVisitMTemp(dao,mitVisitMDao, visitTemp);
            createMitVisitMByMstVisitMTemp(dao,mitVisitMDao, visitTemp);

            // 复制拉链表  如果结束拜访, 则生成最新的终端指标结果记录表
            //createMstcheckexerecordinfoByMstCheckexerecordInfoTemp(checkInfoDao, tempDao, visitId, termId, visitEndDate);
            createMitcheckexerecordByMstCheckexerecordTemp(checkInfoDao,mitCheckInfoDao, tempDao, visitId, termId, visitEndDate);
            // 复制采集项表
            //createMstCollectionexerecordInfoByMstCollectionexerecordInfoTemp(collectionDao,collectionTempDao,visitId);
            createMitCollectionexerecordByMstCollectionexerecordTemp(collectionDao,collectionTempDao,mitCollectionDao,visitId);
            // 复制图片表
            //createMstCameraInfoMByMstCameraInfoMTemp(cameraDao,cameraTempDao, visitId, termId, visitEndDate);
            createMitCameraInfoMByMstCameraInfoMTemp(cameraDao,cameraTempDao,mitCameraDao, visitId, termId, visitEndDate);

            // 复制促销活动终端表
            //createMstPromotermInfoByMstPromotermInfoTemp(promDao,promTempDao, visitId, termId, visitEndDate);
            createMitPromotermInfoByMstPromotermInfoTemp(promDao,promTempDao,mitPromotermDao, visitId, termId, visitEndDate);

            // 拜访产品表
            //createMstVistproductInfoByMstVistproductInfoTemp(proDao,proTempDao, visitId, termId, visitEndDate);
            createMitVistproductInfoByMstVistproductInfoTemp(proDao,proTempDao,mitVistproductDao, visitId, termId, visitEndDate);

            // 复制终端表
            //createMstTerminalinfoMByMstTerminalinfoMTemp(terminalinfoMDao,terminalinfoMTempDao, visitId, termId, visitEndDate);
            createMitTerminalinfoMByMstTerminalinfoMTemp(terminalinfoMDao,terminalinfoMTempDao,mitTerminalDao, visitId, termId, visitEndDate);

            // 处理协同购物车表
            updateMstTerminalinfoMCart(db,"MST_TERMINALINFO_M_CART",termId);


            // 复制我品供货关系表
            //createMstAgencysupplyInfoByMstAgencysupplyInfoTemp(agencyDao,agencyTempDao, visitId, termId, visitEndDate);
            createMitAgencysupplyInfoByMstAgencysupplyInfoTemp(agencyDao,agencyTempDao,mitAgencysupplyDao, visitId, termId, visitEndDate);

            // 复制竞品供货关系表
            //createMstCmpsupplyInfoByMstCmpsupplyInfoTemp(cmpSupplyDao,cmpSupplyTempDao, visitId, termId, visitEndDate);
            createMitCmpsupplyInfoByMstCmpsupplyInfoTemp(cmpSupplyDao,cmpSupplyTempDao,mitCmpsupplyDao, visitId, termId, visitEndDate);

            // 复制产品组合是否达标表
            //createMstGroupproductMByMstGroupproductMTemp(groupproDao,groupproTempDao, visitId, termId,terminalcode, visitEndDate);
            createMitGroupproductMByMstGroupproductMTemp(groupproDao,groupproTempDao,mitGroupproMDao, visitId, termId,terminalcode, visitEndDate);

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

    // 根据Terminalkey 删除记录
    private void updateMstTerminalinfoMCart(SQLiteDatabase db, String tableName, String terminalkey) {
        // 删除表记录
        String sql = "update " + tableName + " set visitflag = '1'   where terminalkey = '" + terminalkey + "' ";
        db.execSQL(sql);
    }

    // 复制产品组合是否达标表 到业代产品组合是否达标表
    private void createMstGroupproductMByMstGroupproductMTemp(
            Dao<MstGroupproductM, String> groupproDao,
            Dao<MstGroupproductMTemp, String> groupproTempDao,
            String visitId, String termId,String terminalcode, String visitEndDate) {

        try {
            QueryBuilder<MstGroupproductMTemp, String> collectionQB = groupproTempDao.queryBuilder();
            Where<MstGroupproductMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalcode", terminalcode);
            List<MstGroupproductMTemp> mstGroupproductMTemps = collectionQB.query();
            if(mstGroupproductMTemps.size()>0){
                for (MstGroupproductMTemp item:mstGroupproductMTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }

    }

    // 复制产品组合是否达标表 到协同产品组合是否达标表
    private void createMitGroupproductMByMstGroupproductMTemp(
            Dao<MstGroupproductM, String> groupproDao,
            Dao<MstGroupproductMTemp, String> groupproTempDao,
            Dao<MitGroupproductM, String> mitGroupproMDao,
            String visitId, String termId,String terminalcode, String visitEndDate) {

        try {
            QueryBuilder<MstGroupproductMTemp, String> collectionQB = groupproTempDao.queryBuilder();
            Where<MstGroupproductMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalcode", terminalcode);
            List<MstGroupproductMTemp> mstGroupproductMTemps = collectionQB.query();
            if(mstGroupproductMTemps.size()>0){
                for (MstGroupproductMTemp item:mstGroupproductMTemps) {
                    MitGroupproductM info = new MitGroupproductM();
                    info.setGproductid(item.getGproductid());
                    info.setTerminalcode(item.getTerminalcode());
                    info.setTerminalname(item.getTerminalname());
                    info.setIfrecstand(item.getIfrecstand());
                    info.setStartdate(item.getStartdate());// 默认: 当天时间 yyyy-MM-dd +"  00:00:00"
                    info.setEnddate(item.getEnddate());//  默认: "3000-12-01"+"  00:00:00"
                    info.setVisitkey(item.getVisitkey());
                    info.setUploadflag("1");//  0:该条记录不上传  1:该条记录需要上传 ;
                    info.setPadisconsistent("0");
                    info.setUpdatetime(item.getUpdatetime());
                    info.setCreateusereng(item.getCreateusereng());
                    /*info.setCreatedate(item.getCreatedate());
                    info.setUpdateusereng(item.getUpdateusereng());

                    info.setCreatedate(new Date());
                    info.setUpdatetime(new Date());*/
                    info.setCreateusereng(PrefUtils.getString(context,"userid",""));
                    info.setUpdateusereng(PrefUtils.getString(context,"userid",""));

                    mitGroupproMDao.createOrUpdate(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }

    }

    // 复制竞品供货关系表 到业代竞品供货关系表
    private void createMstCmpsupplyInfoByMstCmpsupplyInfoTemp(
            Dao<MstCmpsupplyInfo, String> cmpSupplyDao,
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCmpsupplyInfoTemp, String> collectionQB = cmpSupplyTempDao.queryBuilder();
            Where<MstCmpsupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstCmpsupplyInfoTemp> mstCmpsupplyInfoTemps = collectionQB.query();
            if(mstCmpsupplyInfoTemps.size()>0){
                for (MstCmpsupplyInfoTemp item:mstCmpsupplyInfoTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }
    }
    // 复制竞品供货关系表 到协同竞品供货关系表
    private void createMitCmpsupplyInfoByMstCmpsupplyInfoTemp(
            Dao<MstCmpsupplyInfo, String> cmpSupplyDao,
            Dao<MstCmpsupplyInfoTemp, String> cmpSupplyTempDao,
            Dao<MitCmpsupplyInfo, String> mitCmpsupplyDao,
            String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCmpsupplyInfoTemp, String> collectionQB = cmpSupplyTempDao.queryBuilder();
            Where<MstCmpsupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstCmpsupplyInfoTemp> mstCmpsupplyInfoTemps = collectionQB.query();
            if(mstCmpsupplyInfoTemps.size()>0){
                for (MstCmpsupplyInfoTemp item:mstCmpsupplyInfoTemps) {
                    MitCmpsupplyInfo info = new MitCmpsupplyInfo();
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
                    /*info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());*/

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitCmpsupplyDao.createOrUpdate(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到竞品供货关系正式表失败", e);

        }
    }

    // 复制我品供货关系表 到业代我品供货关系表
    private void createMstAgencysupplyInfoByMstAgencysupplyInfoTemp(
            Dao<MstAgencysupplyInfo, String> agencyDao,
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstAgencysupplyInfoTemp, String> collectionQB = agencyTempDao.queryBuilder();
            Where<MstAgencysupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("lowerkey", termId);
            List<MstAgencysupplyInfoTemp> mstAgencysupplyInfoTemps = collectionQB.query();
            if(mstAgencysupplyInfoTemps.size()>0){
                for (MstAgencysupplyInfoTemp item:mstAgencysupplyInfoTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到我品供货关系正式表失败", e);

        }
    }

    // 复制我品供货关系表 到协同我品供货关系表
    private void createMitAgencysupplyInfoByMstAgencysupplyInfoTemp(
            Dao<MstAgencysupplyInfo, String> agencyDao,
            Dao<MstAgencysupplyInfoTemp, String> agencyTempDao,
            Dao<MitAgencysupplyInfo, String> mitAgencysupplyDao,
            String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstAgencysupplyInfoTemp, String> collectionQB = agencyTempDao.queryBuilder();
            Where<MstAgencysupplyInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("lowerkey", termId);
            List<MstAgencysupplyInfoTemp> mstAgencysupplyInfoTemps = collectionQB.query();
            if(mstAgencysupplyInfoTemps.size()>0){
                for (MstAgencysupplyInfoTemp item:mstAgencysupplyInfoTemps) {
                    MitAgencysupplyInfo info = new MitAgencysupplyInfo();
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
                    /*info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());*/

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitAgencysupplyDao.createOrUpdate(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到我品供货关系正式表失败", e);

        }
    }

    // 复制终端表 到业代终端表
    private void createMstTerminalinfoMByMstTerminalinfoMTemp(
            Dao<MstTerminalinfoM, String> terminalinfoMDao,
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstTerminalinfoMTemp, String> collectionQB = terminalinfoMTempDao.queryBuilder();
            Where<MstTerminalinfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstTerminalinfoMTemp> mstTerminalinfoMTemps = collectionQB.query();
            if(mstTerminalinfoMTemps.size()>0){
                for (MstTerminalinfoMTemp item:mstTerminalinfoMTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到终端正式表失败", e);

        }

    }

    // 复制终端表 到协同终端表
    private void createMitTerminalinfoMByMstTerminalinfoMTemp(
            Dao<MstTerminalinfoM, String> terminalinfoMDao,
            Dao<MstTerminalinfoMTemp, String> terminalinfoMTempDao,
            Dao<MitTerminalinfoM, String> mitTerminalDao,
            String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstTerminalinfoMTemp, String> collectionQB = terminalinfoMTempDao.queryBuilder();
            Where<MstTerminalinfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("terminalkey", termId);
            List<MstTerminalinfoMTemp> mstTerminalinfoMTemps = collectionQB.query();
            if(mstTerminalinfoMTemps.size()>0){
                for (MstTerminalinfoMTemp item:mstTerminalinfoMTemps) {
                    MitTerminalinfoM info = new MitTerminalinfoM();

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
                    info.setSelftreaty(item.getSelftreaty());
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
                    /*info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());*/

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitTerminalDao.createOrUpdate(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到终端正式表失败", e);

        }

    }

    // 拜访产品表 到业代拜访产品表
    private void createMstVistproductInfoByMstVistproductInfoTemp(
            Dao<MstVistproductInfo, String> proDao,
            Dao<MstVistproductInfoTemp, String> proTempDao, String visitId, String termId, String visitEndDate) {
        try {
            QueryBuilder<MstVistproductInfoTemp, String> collectionQB = proTempDao.queryBuilder();
            Where<MstVistproductInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstVistproductInfoTemp> mstVistproductInfoTemps = collectionQB.query();
            if(mstVistproductInfoTemps.size()>0){
                for (MstVistproductInfoTemp item:mstVistproductInfoTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到拜访产品正式表失败", e);

        }

    }

    // 拜访产品表 到协同拜访产品表
    private void createMitVistproductInfoByMstVistproductInfoTemp(
            Dao<MstVistproductInfo, String> proDao,
            Dao<MstVistproductInfoTemp, String> proTempDao,
            Dao<MitVistproductInfo, String> mitVistproductDao,
            String visitId, String termId, String visitEndDate) {
        try {
            QueryBuilder<MstVistproductInfoTemp, String> collectionQB = proTempDao.queryBuilder();
            Where<MstVistproductInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstVistproductInfoTemp> mstVistproductInfoTemps = collectionQB.query();
            if(mstVistproductInfoTemps.size()>0){
                for (MstVistproductInfoTemp item:mstVistproductInfoTemps) {
                    MitVistproductInfo info = new MitVistproductInfo();
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
                    /*info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());*/

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitVistproductDao.create(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到拜访产品正式表失败", e);

        }

    }

    // 复制促销活动终端表 到业代促销活动终端表
    private void createMstPromotermInfoByMstPromotermInfoTemp(
            Dao<MstPromotermInfo, String> promDao,
            Dao<MstPromotermInfoTemp, String> promTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstPromotermInfoTemp, String> collectionQB = promTempDao.queryBuilder();
            Where<MstPromotermInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstPromotermInfoTemp> mstPromotermInfoTemps = collectionQB.query();
            if(mstPromotermInfoTemps.size()>0){
                for (MstPromotermInfoTemp item:mstPromotermInfoTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到促销活动终端正式表失败", e);

        }


    }

    // 复制促销活动终端表 到协同促销活动终端表
    private void createMitPromotermInfoByMstPromotermInfoTemp(
            Dao<MstPromotermInfo, String> promDao,
            Dao<MstPromotermInfoTemp, String> promTempDao,
            Dao<MitPromotermInfo, String> mitPromotermDao,
            String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstPromotermInfoTemp, String> collectionQB = promTempDao.queryBuilder();
            Where<MstPromotermInfoTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstPromotermInfoTemp> mstPromotermInfoTemps = collectionQB.query();
            if(mstPromotermInfoTemps.size()>0){
                for (MstPromotermInfoTemp item:mstPromotermInfoTemps) {
                    MitPromotermInfo info = new MitPromotermInfo();
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
                    /*info.setCredate(item.getCredate());
                    info.setCreuser(item.getCreuser());
                    info.setUpdatetime(item.getUpdatetime());
                    info.setUpdateuser(item.getUpdateuser());*/

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitPromotermDao.create(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到促销活动终端正式表失败", e);

        }


    }

    // // 复制图片表 到业代图片表
    private void createMstCameraInfoMByMstCameraInfoMTemp(
            Dao<MstCameraInfoM, String> cameraDao,
            Dao<MstCameraInfoMTemp, String> cameraTempDao, String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCameraInfoMTemp, String> collectionQB = cameraTempDao.queryBuilder();
            Where<MstCameraInfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstCameraInfoMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MstCameraInfoMTemp item:cameraInfoMTemps) {
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

        }catch (Exception e){
            Log.e(TAG, "复制到图片正式表失败", e);

        }
    }
    // // 复制图片表 到协同图片表
    private void createMitCameraInfoMByMstCameraInfoMTemp(
            Dao<MstCameraInfoM, String> cameraDao,
            Dao<MstCameraInfoMTemp, String> cameraTempDao,
            Dao<MitCameraInfoM, String> mitCameraDao,
            String visitId, String termId, String visitEndDate) {

        try {
            QueryBuilder<MstCameraInfoMTemp, String> collectionQB = cameraTempDao.queryBuilder();
            Where<MstCameraInfoMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("visitkey", visitId);
            List<MstCameraInfoMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MstCameraInfoMTemp item:cameraInfoMTemps) {
                    MitCameraInfoM info = new MitCameraInfoM();
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
                    info.setAreaid(item.getAreaid());
                    info.setAreapid(item.getAreapid());
                    info.setGridkey(item.getGridkey());
                    info.setRoutekey(item.getRoutekey());
                    mitCameraDao.create(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到图片正式表失败", e);

        }
    }

    // 复制采集项表 到业代采集项表
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
            if(collectiontempList.size()>0){
                for (MstCollectionexerecordInfoTemp item:collectiontempList) {
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
                    info.setFreshness(item.getFreshness());// 新鲜度
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

        }catch (Exception e){
            Log.e(TAG, "复制到采集项正式表失败", e);

        }
    }

    // 复制采集项表 到协同采集项表
    private void createMitCollectionexerecordByMstCollectionexerecordTemp(
            Dao<MstCollectionexerecordInfo, String> collectionDao,
            Dao<MstCollectionexerecordInfoTemp, String> collectionTempDao,
            Dao<MitCollectionexerecordInfo, String> mitCollectionDao,
            String visitId) {
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
            if(collectiontempList.size()>0){
                for (MstCollectionexerecordInfoTemp item:collectiontempList) {
                    MitCollectionexerecordInfo info = new MitCollectionexerecordInfo();
                    info.setColrecordkey(item.getColrecordkey());
                    info.setVisitkey(item.getVisitkey());

                    info.setProductkey(item.getProductkey());
                    info.setCheckkey(item.getCheckkey());
                    info.setColitemkey(item.getColitemkey());
                    info.setAddcount(item.getAddcount());
                    info.setTotalcount(item.getTotalcount());
                    info.setComid(item.getComid());
                    info.setRemarks(item.getRemarks());
                    info.setFreshness(item.getFreshness());// 新鲜度
                    info.setOrderbyno(item.getOrderbyno());
                    info.setVersion(item.getVersion());
                    info.setBianhualiang(item.getBianhualiang());//变化量 (用于最后上传时 现有量变化量必须填值,才能上传)
                    info.setXianyouliang(item.getXianyouliang());// 现有量 (用于最后上传时 现有量变化量必须填值,才能上传)

                    info.setSisconsistent(item.getSisconsistent());
                    info.setScondate(item.getScondate());
                    info.setPadisconsistent(item.getPadisconsistent());
                    info.setPadcondate(item.getPadcondate());
                    info.setDeleteflag(item.getDeleteflag());

                    info.setCredate(new Date());
                    info.setUpdatetime(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitCollectionDao.create(info);
                }
            }

        }catch (Exception e){
            Log.e(TAG, "复制到采集项正式表失败", e);

        }
    }

    // 复制拉链表 到业代拉链表
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

    // 复制拉链表 到协同拉链表
    private void createMitcheckexerecordByMstCheckexerecordTemp(
            Dao<MstCheckexerecordInfo, String> mstcheckInfoDao,
            Dao<MitCheckexerecordInfo, String> mitCheckInfoDao,
            Dao<MstCheckexerecordInfoTemp, String> tempDao,
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
                    MitCheckexerecordInfo item = new MitCheckexerecordInfo();
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

                    item.setCredate(new Date());
                    item.setUpdatetime(new Date());
                    item.setCreuser(PrefUtils.getString(context,"userid",""));
                    item.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitCheckInfoDao.create(item);
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
                    List<MstCheckexerecordInfo> proList = getCheckExerecordList(mstcheckInfoDao, termId);

                    // 实例化要最终指标结果集对象
                    boolean addFlag = false;// 是否在拉链正式表中插入新纪录
                    MitCheckexerecordInfo item;
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
                                    // 插入一新指标结果
                                    item = new MitCheckexerecordInfo();
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
                                    /*item.setCreuser(PrefUtils.getString(context, "userid", ""));
                                    item.setUpdateuser(item.getCreuser());*/
                                    item.setResultstatus(new BigDecimal(0));

                                    item.setCredate(new Date());
                                    item.setUpdatetime(new Date());
                                    item.setCreuser(PrefUtils.getString(context,"userid",""));
                                    item.setUpdateuser(PrefUtils.getString(context,"userid",""));

                                    mitCheckInfoDao.create(item);
                                } else {
                                    // 如果指标结果不同
                                    if (!acresultTemp.equals(valueItem.getAcresult())) {

                                        // 插入一新指标结果
                                        item = new MitCheckexerecordInfo();
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
                                        item.setResultstatus(new BigDecimal(0));

                                        item.setCredate(new Date());
                                        item.setUpdatetime(new Date());
                                        item.setCreuser(PrefUtils.getString(context,"userid",""));
                                        item.setUpdateuser(PrefUtils.getString(context,"userid",""));

                                        mitCheckInfoDao.create(item);
                                    }
                                }
                                break;
                            }
                        }

                        // 如果之前没有，则为新境
                        if (!addFlag) {
                            item = new MitCheckexerecordInfo();
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
                            item.setResultstatus(new BigDecimal(0));

                            item.setCredate(new Date());
                            item.setUpdatetime(new Date());
                            item.setCreuser(PrefUtils.getString(context,"userid",""));
                            item.setUpdateuser(PrefUtils.getString(context,"userid",""));

                            mitCheckInfoDao.create(item);
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
    private void createMstVisitMByMstVisitMTemp(MstVisitMDao dao,MitVisitMDao mitVisitMDao, MstVisitMTemp visitTemp) {
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
                //mitVisitMDao.create(mstVisitM);
            } catch (SQLException e) {
                Log.e(TAG, "复制到拜访主表正式表失败", e);
                e.printStackTrace();
            }
        }
    }

    // 复制拜访主表 到协同拜访主表  MstVisitMTemp → MitVisitM
    private void createMitVisitMByMstVisitMTemp(MstVisitMDao dao,MitVisitMDao mitVisitMDao, MstVisitMTemp visitTemp) {
        if (visitTemp != null) {
            try {
                MitVisitM mstVisitM = new MitVisitM();
                mstVisitM.setVisitkey(visitTemp.getVisitkey());
                mstVisitM.setTerminalkey(visitTemp.getTerminalkey());
                mstVisitM.setRoutekey(visitTemp.getRoutekey());
                mstVisitM.setGridkey(visitTemp.getGridkey());
                mstVisitM.setAreaid(visitTemp.getAreaid());
                mstVisitM.setVisitdate(visitTemp.getVisitdate());
                mstVisitM.setEnddate(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));// 拜访结束时间
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

                mstVisitM.setCredate(new Date());
                mstVisitM.setUpdatetime(new Date());
                mstVisitM.setCreuser(PrefUtils.getString(context,"userid",""));
                mstVisitM.setUpdateuser(PrefUtils.getString(context,"userid",""));

                //dao.create(mstVisitM);
                mitVisitMDao.create(mstVisitM);
            } catch (SQLException e) {
                Log.e(TAG, "复制到拜访主表正式表失败", e);
                e.printStackTrace();
            }
        }
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


    /**
     * 追溯 离店时间 及 是否要上传标志
     *
     * @param valterid      追溯主键
     */
    public void copyZsUpload(String valterid) {
        // 事务控制
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 复制督导追溯数据
            MitValterMTempDao valterMTempDao = helper.getDao(MitValterMTemp.class);
            MitValterMDao valterMDao = helper.getDao(MitValterM.class);

            // 复制追溯拉链表
            Dao<MitValchecktypeM, String> mitValchecktypeMDao = helper.getMitValchecktypeMDao();
            Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao = helper.getMitValchecktypeMTempDao();

            // 复制追溯采集项表
            Dao<MitValcheckitemM, String> mitValcheckitemMDao = helper.getMitValcheckitemMDao();
            Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao = helper.getMitValcheckitemMTempDao();

            // 复制追溯终端活动表
            Dao<MitValpromotionsM, String> mitValpromotionsMDao = helper.getMitValpromotionsMDao();
            Dao<MitValpromotionsMTemp, String> mitValpromotionsMTempDao = helper.getMitValpromotionsMTempDao();

            // 复制追溯产品组合表
            Dao<MitValgroupproM, String> mitValgroupproMDao = helper.getMitValgroupproMDao();
            Dao<MitValgroupproMTemp, String> mitValgroupproMTempDao = helper.getMitValgroupproMTempDao();

            // 复制追溯进销存表
            Dao<MitValsupplyM, String> mitValsupplyMDao = helper.getMitValsupplyMDao();
            Dao<MitValsupplyMTemp, String> mitValsupplyMTempDao = helper.getMitValsupplyMTempDao();

            // 复制追溯聊竞品表
            Dao<MitValcmpM, String> mitValcmpMDao = helper.getMitValcmpMDao();
            Dao<MitValcmpMTemp, String> mitValcmpMTempDao = helper.getMitValcmpMTempDao();

            // 复制追溯聊竞品附表
            Dao<MitValcmpotherM, String> mitValcmpotherMDao = helper.getMitValcmpotherMDao();
            Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao = helper.getMitValcmpotherMTempDao();

            // 复制追溯图片表
            Dao<MitValpicM, String> mitValpicMDao = helper.getMitValpicMDao();
            Dao<MitValpicMTemp, String> mitValpicMTemppDao = helper.getMitValpicMTempDao();

            // 复制 终端进货台账主表
            Dao<MitValaddaccountM, String> mitValaddaccountMDao = helper.getMitValaddaccountMDao();
            Dao<MitValaddaccountMTemp, String> mitValaddaccountMTempDao = helper.getMitValaddaccountMTempDao();
            // 复制 终端追溯台账产品详情表
            Dao<MitValaddaccountproM, String> mitValaddaccountproMDao = helper.getMitValaddaccountproMDao();
            Dao<MitValaddaccountproMTemp, String> mitValaddaccountproMTempDao = helper.getMitValaddaccountproMTempDao();


            Dao<MitValagreeM, String> valagreeMDao = helper.getMitValagreeMDao();
            Dao<MitValagreeMTemp, String> valagreeMTempDao = helper.getMitValagreeMTempDao();

            Dao<MitValagreedetailM, String> mitValagreedetailMDao = helper.getMitValagreedetailMDao();
            Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao = helper.getMitValagreedetailMTempDao();

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 复制追溯表数据--------------------------------------------------------------------------------
            // 复制追溯主表
            MitValterMTemp valterMTemp = valterMTempDao.queryForId(valterid);
            createMitValterMByTemp(valterMDao,valterMTemp);

            // 复制追溯拉链表
            createMitValchecktypeMByTemp(mitValchecktypeMDao,mitValchecktypeMTempDao,valterid);

            // 复制追溯采集项表
            createMitValcheckitemMByTemp(mitValcheckitemMDao,mitValcheckitemMTempDao,valterid);

            // 复制追溯终端活动表
            createMitValpromotionsMByTemp(mitValpromotionsMDao,mitValpromotionsMTempDao,valterid);

            // 处理追溯购物车表
            updateMstTerminalinfoMCart(db,"MST_TERMINALINFO_M_ZSCART",valterMTemp.getTerminalkey());

            // 复制追溯产品组合表
            createMitValgroupproMByTemp(mitValgroupproMDao,mitValgroupproMTempDao,valterid);

            // 复制追溯进销存表
            createMitValsupplyMByTemp(mitValsupplyMDao,mitValsupplyMTempDao,valterid);

            // 复制追溯聊竞品表
            createMitValcmpMByTemp(mitValcmpMDao,mitValcmpMTempDao,valterid);

            // 复制追溯聊竞品附表
            createMitValcmpotherMByTemp(mitValcmpotherMDao,mitValcmpotherMTempDao,valterid);

            // 复制追溯图片表
            createMitValpicMByTemp(mitValpicMDao,mitValpicMTemppDao,valterid);

            // 复制 终端进货台账主表
            createMitValaddaccountMByTemp(mitValaddaccountMDao,mitValaddaccountMTempDao,valterid);
            createMitValaddaccountproMTempByTemp(mitValaddaccountproMDao,mitValaddaccountproMTempDao,valterid);

            // 复制 协议主表
            createMitValagreeMByTemp(valagreeMDao,valagreeMTempDao,valterid);
            // 复制 协议产品详情主表
            createMitValagreedetailMTempByTemp(mitValagreedetailMDao,mitValagreedetailMTempDao,valterid);

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

    // 复制督导协议主表
    private void createMitValagreeMByTemp(Dao<MitValagreeM, String> valagreeMDao,
                                          Dao<MitValagreeMTemp, String> valagreeMTempDao,
                                          String valterid) {
        try {
            QueryBuilder<MitValagreeMTemp, String> collectionQB = valagreeMTempDao.queryBuilder();
            Where<MitValagreeMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValagreeMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValagreeMTemp item:cameraInfoMTemps) {
                    MitValagreeM info = new MitValagreeM();
                    info.setId(item.getId());// 主键
                    info.setValterid(item.getValterid());// 终端ID
                    info.setAgreecode(item.getAgreecode());//协议编码
                    info.setAgencyname(item.getAgencyname());//供货商名称
                    info.setAgencykey(item.getAgencykey()) ;//供货商ID
                    info.setMoneyagency(item.getMoneyagency());//垫付费用经销商
                    info.setMoneyagencykey(item.getMoneyagencykey());//垫付费用经销商ID
                    info.setStartdate(item.getStartdate());//开始时间
                    info.setStartdateflag(item.getStartdateflag()) ;//开始时间是否正确
                    info.setStartdateremark(item.getStartdateremark());//开始时间备注
                    info.setEnddate(item.getEnddate());//结束时间
                    info.setEnddateflag(item.getEnddateflag()) ;//结束时间是否正确
                    info.setEnddateremark(item.getEnddateremark()) ;//结束时间备注
                    info.setPaytype(item.getPaytype());//支付周期类型
                    info.setContact(item.getContact());//联系人
                    info.setMobile(item.getMobile());//联系电话
                    info.setNotes(item.getNotes());//主要条款
                    info.setNotesflag(item.getNotesflag()) ;//主要条款是否正确
                    info.setNotesremark(item.getNotesremark()) ;//主要条款备注

                    info.setUploadflag("1");
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    valagreeMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制督导协议主表", e);
        }

    }

    // 复制 终端追溯协议对付信息表
    private void createMitValagreedetailMTempByTemp(Dao<MitValagreedetailM, String> mitValagreedetailMDao,
                                                    Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao,
                                                    String valterid) {

        try {
            QueryBuilder<MitValagreedetailMTemp, String> collectionQB = mitValagreedetailMTempDao.queryBuilder();
            Where<MitValagreedetailMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValagreedetailMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValagreedetailMTemp item:cameraInfoMTemps) {
                    MitValagreedetailM info = new MitValagreedetailM();
                    info.setId(item.getId());// 主键
                    info.setValterid(item.getValterid());// 终端ID
                    info.setValagreeid(item.getValagreeid());//终端追溯协议主表ID
                    info.setProkey(item.getProkey());//产品KEY
                    info.setProname(item.getProname());//产品名称
                    info.setCashtype(item.getCashtype());//对付形式
                    info.setCommoney(item.getCommoney());//实际公司承担金额
                    info.setTrunnum(item.getTrunnum());//实际数量
                    info.setPrice(item.getPrice());//单价
                    info.setCashdate(item.getCashdate());//兑付日期
                    info.setAgreedetailflag(item.getAgreedetailflag());//正确与否
                    info.setErroritem(item.getErroritem());//错误项
                    info.setRemarks(item.getRemarks());//备注
                    info.setTruepro(item.getTruepro());//正确的产品名称
                    info.setTruemoney(item.getTruemoney());//正确的承担金额
                    info.setTruenum(item.getTruenum());//正确的实际数量

                    info.setUploadflag("1");
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    mitValagreedetailMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制督导终端追溯协议对付信息表", e);
        }
    }


    private void createMitValaddaccountproMTempByTemp(Dao<MitValaddaccountproM, String> mitValaddaccountproMDao,
                                                      Dao<MitValaddaccountproMTemp, String> mitValaddaccountproMTempDao,
                                                      String valterid) {
        try {
            QueryBuilder<MitValaddaccountproMTemp, String> collectionQB = mitValaddaccountproMTempDao.queryBuilder();
            Where<MitValaddaccountproMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValaddaccountproMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValaddaccountproMTemp item:cameraInfoMTemps) {
                    MitValaddaccountproM info = new MitValaddaccountproM();
                    info.setId(item.getId());// 主键
                    info.setValterid(item.getValterid());// 终端ID

                    info.setValaddaccountid(item.getValaddaccountid());// 终端进货台账主表ID
                    info.setValprotime(item.getValprotime());// 台账日期
                    info.setValpronumfalg(item.getValpronumfalg());// 进货量正确与否
                    info.setValpronum(item.getValpronum());// 进货量原值
                    info.setValprotruenum(item.getValprotruenum());// 进货量正确值
                    info.setValproremark(item.getValproremark());// 备注

                    info.setUploadflag("1");
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    mitValaddaccountproMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯图片表失败", e);
        }
    }

    // 复制 终端进货台账主表
    private void createMitValaddaccountMByTemp(Dao<MitValaddaccountM, String> mitValaddaccountMDao,
                                               Dao<MitValaddaccountMTemp, String> mitValaddaccountMTempDao,
                                               String valterid) {

        try {
            QueryBuilder<MitValaddaccountMTemp, String> collectionQB = mitValaddaccountMTempDao.queryBuilder();
            Where<MitValaddaccountMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valsupplyid", valterid);
            List<MitValaddaccountMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValaddaccountMTemp item:cameraInfoMTemps) {
                    MitValaddaccountM info = new MitValaddaccountM();
                    info.setId(item.getId());// 主键

                    info.setValsupplyid(item.getValsupplyid());// 追溯主表ID
                    info.setValagencyid(item.getValagencyid());//经销商ID
                    info.setValagencyname(item.getValagencyname());// 经销商名称
                    info.setValterid(item.getValterid());// 终端ID
                    info.setValtername(item.getValtername());// 终端名称
                    info.setValproid(item.getValproid());//产品ID
                    info.setValproname(item.getValproname());//  产品名称
                    info.setValprostatus(item.getValprostatus());//  稽查状态

                    info.setUploadflag("1");
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValaddaccountMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯图片表失败", e);
        }
    }


    // 复制追溯主表 临时表到主表
    private void createMitValterMByTemp(MitValterMDao valterMDao, MitValterMTemp valterMTemp) {
        if (valterMTemp != null) {
            try {
                MitValterM mitValterM = new MitValterM();
                mitValterM.setId(valterMTemp.getId());// 追溯主键
                mitValterM.setTerminalkey(valterMTemp.getTerminalkey());// 终端KEY
                mitValterM.setVisitdate(valterMTemp.getVisitdate());
                mitValterM.setVidstartdate(valterMTemp.getVidstartdate());////拜访开始时间
                mitValterM.setVidenddate(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));// //拜访结束时间
                mitValterM.setVidter(valterMTemp.getVidter());// 是否有效终端原值
                mitValterM.setVidterflag(valterMTemp.getVidterflag());// 是否有效终端正确与否
                mitValterM.setVidterremaek(valterMTemp.getVidterremaek());// 是否有效终端错误备注内容

                mitValterM.setVidvisit(valterMTemp.getVidvisit());// 是否有效拜访原值
                mitValterM.setVidvisitflag(valterMTemp.getVidvisitflag());// 是否有效拜访正确与否
                mitValterM.setVidvisitremark(valterMTemp.getVidvisitremark());// 是否有效拜访错误备注内容

                mitValterM.setVidifmine(valterMTemp.getVidifmine());// 我品店招原值
                mitValterM.setVidifmineflag(valterMTemp.getVidifmineflag());// 我品店招原值正确与否
                mitValterM.setVidifminermark(valterMTemp.getVidifminermark());// 我品店招错误备注内容

                mitValterM.setVidifminedate(valterMTemp.getVidifminedate());//店招时间原值
                mitValterM.setVidifminedateflag(valterMTemp.getVidifminedateflag());// 店招时间原值正确与否
                mitValterM.setVidifminedatermark(valterMTemp.getVidifminedatermark());// 店招时间错误备注内容

                mitValterM.setVidisself(valterMTemp.getVidisself());// 销售产品范围我品原值
                mitValterM.setVidisselfflag(valterMTemp.getVidisselfflag());// 销售产品范围我品正确与否
                mitValterM.setVidisselfremark(valterMTemp.getVidisselfremark());// 销售产品范围我品备注内容

                mitValterM.setVidiscmp(valterMTemp.getVidiscmp());// 销售产品范围竞品原值
                mitValterM.setVidiscmpflag(valterMTemp.getVidiscmpflag());// 销售产品范围竞品正确与否
                mitValterM.setVidiscmpremark(valterMTemp.getVidiscmpremark());// 销售产品范围竞品备注内容

                mitValterM.setVidselftreaty(valterMTemp.getVidselftreaty());// 终端合作状态我品原值
                mitValterM.setVidselftreatyflag(valterMTemp.getVidselftreatyflag());// 终端合作状态我品正确与否
                mitValterM.setVidselftreatyremark(valterMTemp.getVidselftreatyremark());// 终端合作状态我品备注内容

                mitValterM.setVidcmptreaty(valterMTemp.getVidcmptreaty());// 终端合作状态竞品原值
                mitValterM.setVidcmptreatyflag(valterMTemp.getVidcmptreatyflag());// 终端合作状态竞品原值正确与否
                mitValterM.setVidcmptreatyremark(valterMTemp.getVidcmptreatyremark());// 终端合作状态竞品备注内容

                mitValterM.setVidterminalcode(valterMTemp.getVidterminalcode());// 终端编码原值
                mitValterM.setVidtercodeflag(valterMTemp.getVidtercodeflag());// 终端编码正确与否
                mitValterM.setVidtercodeval(valterMTemp.getVidtercodeval());// 终端编码正确值
                mitValterM.setVidtercoderemark(valterMTemp.getVidtercoderemark());// 终端编码备注内容

                mitValterM.setVidroutekey(valterMTemp.getVidroutekey());// 所属路线原值
                mitValterM.setVidrtekeyflag(valterMTemp.getVidrtekeyflag());// 所属路线原值正确与否
                mitValterM.setVidrtekeyval(valterMTemp.getVidrtekeyval());// 所属路线正确值
                mitValterM.setVidroutremark(valterMTemp.getVidroutremark());// 所属路线备注内容

                mitValterM.setVidterlevel(valterMTemp.getVidterlevel());//终端等级
                mitValterM.setVidtervidterlevelflag(valterMTemp.getVidtervidterlevelflag());// 终端等级正确与否
                mitValterM.setVidtervidterlevelval(valterMTemp.getVidtervidterlevelval());// 终端等级正确值
                mitValterM.setVidtervidterlevelremark(valterMTemp.getVidtervidterlevelremark());// 终端等级备注内容

                mitValterM.setVidtername(valterMTemp.getVidtername());//终端名称原值
                mitValterM.setVidternameflag(valterMTemp.getVidternameflag());// 终端名称正确与否
                mitValterM.setVidternameval(valterMTemp.getVidternameval());// 终端名称正确值
                mitValterM.setVidternameremark(valterMTemp.getVidternameremark());// 终端名称备注内容

                mitValterM.setVidcountry(valterMTemp.getVidcountry());//所属县原值
                mitValterM.setVidcountryflag(valterMTemp.getVidcountryflag());// 所属县原值正确与否
                mitValterM.setVidcountryval(valterMTemp.getVidcountryval());// 所属县正确值
                mitValterM.setVidcountryremark(valterMTemp.getVidcountryremark());// 所属县备注内容

                mitValterM.setVidaddress(valterMTemp.getVidaddress());//地址原值
                mitValterM.setVidaddressflag(valterMTemp.getVidaddressflag());// 地址原值正确与否
                mitValterM.setVidaddressval(valterMTemp.getVidaddressval());// 地址正确值
                mitValterM.setVidaddressremark(valterMTemp.getVidaddressremark());// 地址备注内容

                mitValterM.setVidcontact(valterMTemp.getVidcontact());//联系人原值
                mitValterM.setVidcontactflag(valterMTemp.getVidcontactflag());// 联系人原值正确与否
                mitValterM.setVidcontactval(valterMTemp.getVidcontactval());// 联系人正确值
                mitValterM.setVidcontactremark(valterMTemp.getVidcontactremark());// 联系人备注内容

                mitValterM.setVidmobile(valterMTemp.getVidmobile());//电话原值
                mitValterM.setVidmobileflag(valterMTemp.getVidmobileflag());// 电话原值正确与否
                mitValterM.setVidmobileval(valterMTemp.getVidmobileval());// 电话正确值
                mitValterM.setVidmobileremark(valterMTemp.getVidmobileremark());// 电话备注内容

                mitValterM.setVidsequence(valterMTemp.getVidsequence());//拜访顺序原值
                mitValterM.setVidsequenceflag(valterMTemp.getVidsequenceflag());// 拜访顺序原值正确与否
                mitValterM.setVidsequenceval(valterMTemp.getVidsequenceval());// 拜访顺序正确值
                mitValterM.setVidvidsequenceremark(valterMTemp.getVidvidsequenceremark());// 拜访顺序备注内容

                mitValterM.setVidcycle(valterMTemp.getVidcycle());//拜访周期原值
                mitValterM.setVidcycleflag(valterMTemp.getVidcycleflag());// 拜访周期原值正确与否
                mitValterM.setVidcycleval(valterMTemp.getVidcycleval());// 拜访周期正确值
                mitValterM.setVidcycleremark2(valterMTemp.getVidcycleremark2());// 拜访周期备注内容

                mitValterM.setVidareatype(valterMTemp.getVidareatype());//区域类型原值
                mitValterM.setVidareatypeflag(valterMTemp.getVidareatypeflag());// 区域类型正确与否
                mitValterM.setVidareatypeval(valterMTemp.getVidareatypeval());// 区域类型正确值
                mitValterM.setVidareatyperemark(valterMTemp.getVidareatyperemark());// 区域类型备注内容

                mitValterM.setVidhvolume(valterMTemp.getVidhvolume());//高档容量原值
                mitValterM.setVidhvolumeflag(valterMTemp.getVidhvolumeflag());// 高档容量正确与否
                mitValterM.setVidhvolumeval(valterMTemp.getVidhvolumeval());// 高档容量正确值
                mitValterM.setVidhvolumeremark(valterMTemp.getVidhvolumeremark());// 高档容量备注内容

                mitValterM.setVidzvolume(valterMTemp.getVidzvolume());//中档容量原值
                mitValterM.setVidzvolumeflag(valterMTemp.getVidzvolumeflag());// 中档容量正确与否
                mitValterM.setVidzvolumeval(valterMTemp.getVidzvolumeval());// 中档容量正确值
                mitValterM.setVidxvolumeremark(valterMTemp.getVidxvolumeremark());// 中档容量备注内容

                mitValterM.setVidpvolume(valterMTemp.getVidpvolume());//普档容量原值
                mitValterM.setVidpvolumeflag(valterMTemp.getVidpvolumeflag());// 普档容量正确与否
                mitValterM.setVidpvolumeval(valterMTemp.getVidpvolumeval());// 普档容量正确值
                mitValterM.setVidpvolumeremark(valterMTemp.getVidpvolumeremark());// 普档容量备注内容

                mitValterM.setVidlvolume(valterMTemp.getVidlvolume());//底档容量原值
                mitValterM.setVidlvolumeflag(valterMTemp.getVidlvolumeflag());// 底档容量正确与否
                mitValterM.setVidlvolumeval(valterMTemp.getVidlvolumeval());// 底档容量正确值
                mitValterM.setVidlvolumeremark(valterMTemp.getVidlvolumeremark());// 底档容量备注内容

                mitValterM.setVidminchannel(valterMTemp.getVidminchannel());//次渠道原值
                mitValterM.setVidminchannelflag(valterMTemp.getVidminchannelflag());// 次渠道正确与否
                mitValterM.setVidminchannelval(valterMTemp.getVidminchannelval());// 次渠道正确值
                mitValterM.setVidminchannelremark(valterMTemp.getVidminchannelremark());// 次渠道正确值备注内容

                mitValterM.setVidvisituser(valterMTemp.getVidvisituser());//拜访对象原值key
                mitValterM.setVidvisituserflag(valterMTemp.getVidvisituserflag());// 拜访对象原值正确与否
                mitValterM.setVidvisituserval(valterMTemp.getVidvisituserval());// 拜访对象正确值
                mitValterM.setVidvisituserremark(valterMTemp.getVidvisituserremark());// 拜访对象备注内容

                mitValterM.setVidvisitotherval(valterMTemp.getVidvisitotherval());//拜访对象原值value
                mitValterM.setVidvisitottrueval(valterMTemp.getVidvisitottrueval());// 拜访对象其他VALUE正确值
                mitValterM.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                mitValterM.setLon(valterMTemp.getLon());// 经度
                mitValterM.setLat(valterMTemp.getLat());// 纬度

                mitValterM.setCredate(new Date());
                mitValterM.setUpdatedate(new Date());
                mitValterM.setCreuser(PrefUtils.getString(context,"userid",""));
                mitValterM.setUpdateuser(PrefUtils.getString(context,"userid",""));

                valterMDao.create(mitValterM);
            } catch (SQLException e) {
                Log.e(TAG, "复制到拜访主表正式表失败", e);
                e.printStackTrace();
            }
        }
    }

    // 复制追溯拉链表
    private void createMitValchecktypeMByTemp(Dao<MitValchecktypeM, String> mitValchecktypeMDao,
                                              Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao,
                                              String valterid) {

        try {
            QueryBuilder<MitValchecktypeMTemp, String> collectionQB = mitValchecktypeMTempDao.queryBuilder();
            Where<MitValchecktypeMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValchecktypeMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValchecktypeMTemp item:cameraInfoMTemps) {
                    MitValchecktypeM info = new MitValchecktypeM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setVisitkey(item.getVisitkey());// 终端追溯主表ID
                    info.setValchecktype(item.getValchecktype());//  指标类型 5d,6d....
                    info.setValchecktypeid(item.getValchecktypeid());//  指标ID  拉链表主键
                    info.setProductkey(item.getProductkey());//
                    info.setAcresult(item.getAcresult());// 业代原值
                    info.setTerminalkey(item.getTerminalkey());//
                    info.setDdremark(item.getDdremark());// 督导备注
                    info.setDdacresult(item.getDdacresult());// 督导自动计算值
                    info.setValchecktypeflag(item.getValchecktypeflag());// 指标正确与否   char(1)
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValchecktypeMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }

    // 复制追溯采集项表
    private void createMitValcheckitemMByTemp(Dao<MitValcheckitemM, String> mitValcheckitemMDao,
                                              Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao,
                                              String valterid) {
        try {
            QueryBuilder<MitValcheckitemMTemp, String> collectionQB = mitValcheckitemMTempDao.queryBuilder();
            Where<MitValcheckitemMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValcheckitemMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValcheckitemMTemp item:cameraInfoMTemps) {
                    MitValcheckitemM info = new MitValcheckitemM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setValchecktypeid(item.getValchecktypeid()); // 督导拉链表主键
                    info.setVisitkey(item.getVisitkey()); ;// 拜访主键
                    info.setValitemid(item.getValitemid()); ;//  采集项主键ID
                    info.setColitemkey(item.getColitemkey());  ;// 采集项key
                    info.setCheckkey(item.getCheckkey()); ;// 指标 varchar2(36)
                    //itemInfo.setAddcount();//  变化量
                    //itemInfo.setTotalcount();// 现有量
                    info.setProductkey(item.getProductkey()); ;// 产品key
                    info.setValitem(item.getValitem());  ;// 采集项原值结果量
                    info.setValitemval(item.getValitemval()); ;// 采集项正确值结果量
                    info.setValitemremark(item.getValitemremark()); ;// 采集项备注
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValcheckitemMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }

    // 复制追溯终端活动表
    private void createMitValpromotionsMByTemp(Dao<MitValpromotionsM, String> mitValpromotionsMDao,
                                               Dao<MitValpromotionsMTemp, String> mitValpromotionsMTempDao,
                                               String valterid) {
        try {
            QueryBuilder<MitValpromotionsMTemp, String> collectionQB = mitValpromotionsMTempDao.queryBuilder();
            Where<MitValpromotionsMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValpromotionsMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValpromotionsMTemp item:cameraInfoMTemps) {
                    MitValpromotionsM info = new MitValpromotionsM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setValpromotionsid(item.getValpromotionsid());//    促销活动主键/ valpromotionsid
                    info.setTerminalkey(item.getTerminalkey());
                    //info.setValistrue(item.getIsaccomplish());// 是否达成原值
                    info.setValistruenum(item.getValistruenum());// 业代达成数组
                    info.setValistruenumflag(item.getValistruenumflag());// 达成数组是否正确
                    info.setValistruenumval(item.getValistruenumval());// 督导达成数组正确值
                    info.setVisitkey(item.getVisitkey());// 拜访主键
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValpromotionsMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }


    // 复制 追溯产品组合
    private void createMitValgroupproMByTemp(Dao<MitValgroupproM, String> mitValgroupproMDao,
                                             Dao<MitValgroupproMTemp, String> mitValgroupproMTempDao,
                                             String valterid) {
        try {
            QueryBuilder<MitValgroupproMTemp, String> collectionQB = mitValgroupproMTempDao.queryBuilder();
            Where<MitValgroupproMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValgroupproMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValgroupproMTemp vo:cameraInfoMTemps) {
                    MitValgroupproM info = new MitValgroupproM();
                    info.setId(vo.getId());
                    info.setValterid(vo.getValterid());
                    info.setValgrouppro(vo.getValgrouppro());
                    info.setValgroupproflag(vo.getValgroupproflag());
                    info.setValgroupproremark(vo.getValgroupproremark());
                    info.setGproductid(vo.getGproductid());
                    info.setTerminalcode(vo.getTerminalcode());
                    info.setPadisconsistent("0");// 未上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValgroupproMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }

    // 复制追溯进销存表
    private void createMitValsupplyMByTemp(Dao<MitValsupplyM, String> mitValsupplyMDao,
                                           Dao<MitValsupplyMTemp, String> mitValsupplyMTempDao,
                                           String valterid) {
        try {
            QueryBuilder<MitValsupplyMTemp, String> collectionQB = mitValsupplyMTempDao.queryBuilder();
            Where<MitValsupplyMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValsupplyMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValsupplyMTemp item:cameraInfoMTemps) {
                    MitValsupplyM info = new MitValsupplyM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setValsuplyid(item.getValsuplyid());// 供货关系id
                    info.setValaddagencysupply(item.getValaddagencysupply());// 是否新增供货关系
                    info.setValsqd(item.getValsqd());// 渠道价
                    info.setValsls(item.getValsls());// 零售价
                    info.setValsdd(item.getValsdd());//订单量//
                    info.setValsrxl(item.getValsrxl());//日销量
                    info.setValsljk(item.getValsljk());//累计卡
                    info.setValter(item.getValter());//终端
                    info.setValpro(item.getValpro());//产品key
                    info.setValproname(item.getValproname());//产品name
                    info.setValagency(item.getValagency());//经销商key
                    info.setValagencyname(item.getValagencyname());//经销商name
                    info.setValagencysupplyflag(item.getValagencysupplyflag());//供货关系正确与否
                    info.setValproerror(item.getValproerror());//品项有误
                    info.setValagencyerror(item.getValagencyerror());//经销商有误
                    info.setValtrueagency(item.getValtrueagency());//正确的经销商key
                    info.setValtrueagencyname(item.getValtrueagencyname());//正确的经销商name
                    info.setValdataerror(item.getValdataerror());//数据有误
                    info.setValiffleeing(item.getValiffleeing());//稽查是否窜货 Y/N
                    info.setValiffleeingremark(item.getValiffleeingremark());//是否窜货填写的内容
                    info.setValagencysupplyqd(item.getValagencysupplyqd());//供货关系正确渠道价
                    info.setValagencyqdflag(item.getValagencyqdflag());//供货关系正确渠道价状态
                    info.setValagencysupplyls(item.getValagencysupplyls());//供货关系正确零售价
                    info.setValagencylsflag(item.getValagencylsflag());//供货关系正确零售价状态
                    info.setValagencysupplydd(item.getValagencysupplydd());//供货关系正确订单量
                    info.setValagencysupplysrxl(item.getValagencysupplysrxl());//供货关系正确日销量
                    info.setValagencysupplyljk(item.getValagencysupplyljk());//供货关系正确累计卡
                    info.setValagencysupplyremark(item.getValagencysupplyremark());//供货关系备注
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValsupplyMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }

    // 复制追溯聊竞品表
    private void createMitValcmpMByTemp(Dao<MitValcmpM, String> mitValcmpMDao,
                                        Dao<MitValcmpMTemp, String> mitValcmpMTempDao,
                                        String valterid) {
        try {
            QueryBuilder<MitValcmpMTemp, String> collectionQB = mitValcmpMTempDao.queryBuilder();
            Where<MitValcmpMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValcmpMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValcmpMTemp item:cameraInfoMTemps) {
                    MitValcmpM info = new MitValcmpM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setValaddagencysupply(item.getValaddagencysupply());// 是否新增供货关系
                    info.setValagencysupplyid(item.getValagencysupplyid());// 供货关系ID
                    info.setValcmpjdj(item.getValcmpjdj());// 竞品进店价
                    info.setValcmplsj(item.getValcmplsj());//竞品零售价
                    info.setValcmpsales(item.getValcmpsales());//竞品销量
                    info.setValcmpkc(item.getValcmpkc());//竞品库存
                    info.setValcmpremark(item.getValcmpremark());//竞品描述
                    info.setValcmpagency(item.getValcmpagency());//竞品供货商
                    info.setValcmpid(item.getValcmpid());//竞品ID
                    info.setValcmpname(item.getValcmpname());//竞品ID
                    info.setValiscmpter(item.getValiscmpter());//终端
                    info.setValagencysupplyflag(item.getValagencysupplyflag());//供货关系正确与否
                    info.setValproerror(item.getValproerror());//品项有误
                    info.setValagencyerror(item.getValagencyerror());//经销商有误
                    info.setValdataerror(item.getValdataerror());//数据有误
                    info.setValcmpagencyval(item.getValcmpagencyval());//正确的经销商
                    info.setValcmpjdjval(item.getValcmpjdjval());//正确竞品进店价
                    info.setValcmplsjval(item.getValcmplsjval());//正确竞品零售价
                    info.setValcmpsalesval(item.getValcmpsalesval());//正确竞品销量
                    info.setValcmpkcval(item.getValcmpkcval());//正确竞品库存
                    info.setValcmpremarkval(item.getValcmpremarkval());//正确竞品描述
                    info.setValcmpsupremark(item.getValcmpsupremark());//竞品供货关系备注
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValcmpMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }

    }

    // 复制追溯聊竞品附表
    private void createMitValcmpotherMByTemp(Dao<MitValcmpotherM, String> mitValcmpotherMDao,
                                             Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao,
                                             String valterid) {
        try {
            QueryBuilder<MitValcmpotherMTemp, String> collectionQB = mitValcmpotherMTempDao.queryBuilder();
            Where<MitValcmpotherMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValcmpotherMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValcmpotherMTemp item:cameraInfoMTemps) {
                    MitValcmpotherM info = new MitValcmpotherM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setValistrueflag(item.getValistrueflag());// 是否成功瓦解竞品正确与否
                    info.setValistruecmpval(item.getValistruecmpval());// 是否成功瓦解竞品原值
                    info.setValiscmpremark(item.getValiscmpremark());// 是否成功瓦解竞品备注
                    info.setValvisitremark(item.getValvisitremark());// 拜访记录
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValcmpotherMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯聊竞品附表失败", e);
        }
    }

    // 复制追溯图片表
    private void createMitValpicMByTemp(Dao<MitValpicM, String> mitValpicMDao,
                                        Dao<MitValpicMTemp, String> mitValpicMTemppDao,
                                        String valterid) {
        try {
            QueryBuilder<MitValpicMTemp, String> collectionQB = mitValpicMTemppDao.queryBuilder();
            Where<MitValpicMTemp, String> collectionWhere = collectionQB.where();
            collectionWhere.eq("valterid", valterid);
            List<MitValpicMTemp> cameraInfoMTemps = collectionQB.query();
            if(cameraInfoMTemps.size()>0){
                for (MitValpicMTemp item:cameraInfoMTemps) {
                    MitValpicM info = new MitValpicM();
                    info.setId(item.getId());// 图片主键
                    info.setValterid(item.getValterid()); // 追溯主键
                    info.setTerminalname(item.getTerminalname()); // 终端名称
                    info.setCameradata(item.getCameradata()); // 拍照时间
                    info.setPictypekey(item.getPictypekey());// 图片类型主键(UUID)
                    info.setPicname(item.getPicname());// 图片名称
                    info.setPictypename(item.getPictypename());// 图片类型(中文名称)
                    info.setImagefileString(item.getImagefileString());// 将图片文件转成String保存在数据库
                    info.setAreapid(item.getAreapid());// 大区
                    info.setAreaid(item.getAreaid());// 二级区域
                    info.setGridkey(item.getGridkey());//定格
                    info.setRoutekey(item.getRoutekey());// 路线
                    info.setTerminalkey(item.getTerminalkey());// 终端主键
                    info.setPadisconsistent("0");// 是否已上传 0:未上传 1:已上传

                    info.setCredate(new Date());
                    info.setUpdatedate(new Date());
                    info.setCreuser(PrefUtils.getString(context,"userid",""));
                    info.setUpdateuser(PrefUtils.getString(context,"userid",""));

                    mitValpicMDao.create(info);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "复制追溯图片表失败", e);
        }
    }

}
