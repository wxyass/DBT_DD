package dd.tsingtaopad.dd.ddxt.updata;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;


import dd.tsingtaopad.business.visit.bean.AreaGridRoute;
import dd.tsingtaopad.core.net.HttpUrl;
import dd.tsingtaopad.core.net.RestClient;
import dd.tsingtaopad.core.net.callback.IError;
import dd.tsingtaopad.core.net.callback.IFailure;
import dd.tsingtaopad.core.net.callback.ISuccess;
import dd.tsingtaopad.core.net.domain.RequestHeadStc;
import dd.tsingtaopad.core.net.domain.RequestStructBean;
import dd.tsingtaopad.core.net.domain.ResponseStructBean;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.FileUtil;
import dd.tsingtaopad.core.util.dbtutil.JsonUtil;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.core.util.file.FileTool;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitCameraInfoMDao;
import dd.tsingtaopad.db.dao.MitPlandayvalMDao;
import dd.tsingtaopad.db.dao.MitValterMDao;
import dd.tsingtaopad.db.dao.MstAgencyKFMDao;
import dd.tsingtaopad.db.dao.MstCameraiInfoMDao;
import dd.tsingtaopad.db.table.MitAgencynumM;
import dd.tsingtaopad.db.table.MitAgencyproM;
import dd.tsingtaopad.db.table.MitAgencysupplyInfo;
import dd.tsingtaopad.db.table.MitCameraInfoM;
import dd.tsingtaopad.db.table.MitCheckexerecordInfo;
import dd.tsingtaopad.db.table.MitCmpsupplyInfo;
import dd.tsingtaopad.db.table.MitCollectionexerecordInfo;
import dd.tsingtaopad.db.table.MitGroupproductM;
import dd.tsingtaopad.db.table.MitPlandayM;
import dd.tsingtaopad.db.table.MitPlandaydetailM;
import dd.tsingtaopad.db.table.MitPlandayvalM;
import dd.tsingtaopad.db.table.MitPlanweekM;
import dd.tsingtaopad.db.table.MitPromotermInfo;
import dd.tsingtaopad.db.table.MitRepairM;
import dd.tsingtaopad.db.table.MitRepaircheckM;
import dd.tsingtaopad.db.table.MitRepairterM;
import dd.tsingtaopad.db.table.MitTerminalM;
import dd.tsingtaopad.db.table.MitTerminalinfoM;
import dd.tsingtaopad.db.table.MitValaddaccountM;
import dd.tsingtaopad.db.table.MitValaddaccountproM;
import dd.tsingtaopad.db.table.MitValagencykfM;
import dd.tsingtaopad.db.table.MitValagreeM;
import dd.tsingtaopad.db.table.MitValagreedetailM;
import dd.tsingtaopad.db.table.MitValcheckitemM;
import dd.tsingtaopad.db.table.MitValchecktypeM;
import dd.tsingtaopad.db.table.MitValcmpM;
import dd.tsingtaopad.db.table.MitValcmpotherM;
import dd.tsingtaopad.db.table.MitValgroupproM;
import dd.tsingtaopad.db.table.MitValpicM;
import dd.tsingtaopad.db.table.MitValpromotionsM;
import dd.tsingtaopad.db.table.MitValsupplyM;
import dd.tsingtaopad.db.table.MitValterM;
import dd.tsingtaopad.db.table.MitVisitM;
import dd.tsingtaopad.db.table.MitVistproductInfo;
import dd.tsingtaopad.db.table.MstAgencysupplyInfo;
import dd.tsingtaopad.db.table.MstAgencytransferInfo;
import dd.tsingtaopad.db.table.MstAgencyvisitM;
import dd.tsingtaopad.db.table.MstCameraInfoMTemp;
import dd.tsingtaopad.db.table.MstCheckexerecordInfo;
import dd.tsingtaopad.db.table.MstCmpsupplyInfo;
import dd.tsingtaopad.db.table.MstCollectionexerecordInfo;
import dd.tsingtaopad.db.table.MstGroupproductM;
import dd.tsingtaopad.db.table.MstInvalidapplayInfo;
import dd.tsingtaopad.db.table.MstInvoicingInfo;
import dd.tsingtaopad.db.table.MstPlanTerminalM;
import dd.tsingtaopad.db.table.MstPlanWeekforuserM;
import dd.tsingtaopad.db.table.MstPlancheckInfo;
import dd.tsingtaopad.db.table.MstPlancollectionInfo;
import dd.tsingtaopad.db.table.MstPlanforuserM;
import dd.tsingtaopad.db.table.MstPlanrouteInfo;
import dd.tsingtaopad.db.table.MstPromotermInfo;
import dd.tsingtaopad.db.table.MstQuestionsanswersInfo;
import dd.tsingtaopad.db.table.MstTerminalinfoM;
import dd.tsingtaopad.db.table.MstVisitM;
import dd.tsingtaopad.db.table.MstVisitmemoInfo;
import dd.tsingtaopad.db.table.MstVistproductInfo;
import dd.tsingtaopad.db.table.MstWorksummaryInfo;

import dd.tsingtaopad.home.initadapter.GlobalValues;
import dd.tsingtaopad.http.HttpParseJson;
import dd.tsingtaopad.util.requestHeadUtil;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：XtUploadService.java</br>
 * 作者：wxyass   </br>
 * 创建时间：2018年3月20日</br>
 * 功能描述: </br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class XtUploadService {

    public DatabaseHelper helper;
    private final String TAG = "UploadDataService";
    private Context context;
    private Handler handler;
    private Dao<MstVisitmemoInfo, String> mstVisitmemoInfoDao = null;
    private Dao<MstVisitM, String> mstVisitMDao = null;
    private Dao<MitVisitM, String> mitVisitMDao = null;//1
    private Dao<MstQuestionsanswersInfo, String> mstQuestionsanswersInfoDao = null;
    private Dao<MstWorksummaryInfo, String> mstWorksummaryInfoDao = null;
    private Dao<MstTerminalinfoM, String> mstTerminalinfoMDao = null;
    private Dao<MitTerminalinfoM, String> mitTerminalinfoMDao = null;//2
    private Dao<MstInvoicingInfo, String> mstInvoicingInfoDao = null;
    private Dao<MstAgencytransferInfo, String> mstAgencytransferInfoDao = null;
    private Dao<MstVistproductInfo, String> mstVistproductInfoDao = null;
    private Dao<MitVistproductInfo, String> mitVistproductInfoDao = null;//3
    private Dao<MitCheckexerecordInfo, String> mitCheckexerecordInfoDao = null;//4
    private Dao<MstCheckexerecordInfo, String> mstCheckexerecordInfoDao = null;
    private Dao<MstInvalidapplayInfo, String> mstInvalidapplayInfoDao = null;
    private Dao<MstPromotermInfo, String> mstPromotermInfoDao = null;
    private Dao<MitPromotermInfo, String> mitPromotermInfoDao = null;//5
    private Dao<MstCollectionexerecordInfo, String> mstCollectionexerecordInfoDao = null;
    private Dao<MitCollectionexerecordInfo, String> mitCollectionexerecordInfoDao = null;//6
    private Dao<MstPlancheckInfo, String> mstPlancheckInfoDao = null;
    private Dao<MstPlancollectionInfo, String> mstPlancollectionInfoDao = null;
    private Dao<MstPlanforuserM, String> mstPlanforuserMDao = null;
    private Dao<MstPlanWeekforuserM, String> mstPlanWeekforuserMDao = null;
    private Dao<MitAgencysupplyInfo, String> mitAgencysupplyInfoDao = null;//7
    private Dao<MstAgencysupplyInfo, String> mstAgencysupplyInfoDao = null;
    private Dao<MstAgencyvisitM, String> mstAgencyvisitMDao = null;
    private Dao<MstCmpsupplyInfo, String> mstCmpsupplyInfoDao = null;//
    private Dao<MitCmpsupplyInfo, String> mitCmpsupplyInfoDao = null;//8
    private Dao<MstPlanrouteInfo, String> mstPlanrouteInfodDao = null;
    private MstCameraiInfoMDao mstCameraiInfoMDao = null;
    private Dao<MstCameraInfoMTemp, String> cameraTempDao = null;
    private MitCameraInfoMDao mitCameraInfoMDao = null;//9
    private MstAgencyKFMDao mstAgencyKFMDao = null;
    private Dao<MstPlanTerminalM, String> mstPlanTerminalMDao = null;
    private Dao<MstGroupproductM, String> mstGroupproductMDao = null;
    private Dao<MitGroupproductM, String> mitGroupproductMDao = null;//10


    private MitValterMDao valterMDao = null;
    private Dao<MitValchecktypeM, String> mitValchecktypeMDao = null;
    private Dao<MitValcheckitemM, String> mitValcheckitemMDao = null;
    private Dao<MitValpromotionsM, String> mitValpromotionsMDao = null;
    private Dao<MitValsupplyM, String> mitValsupplyMDao = null;
    private Dao<MitValcmpM, String> mitValcmpMDao = null;
    private Dao<MitValcmpotherM, String> mitValcmpotherMDao = null;
    private Dao<MitValpicM, String> mitValpicMDao = null;
    private Dao<MitTerminalM, String> mitTerminalMDao = null;
    private Dao<MitValagencykfM, String> mitValagencykfMDao = null;
    private Dao<MitAgencynumM, String> mitAgencynumMDao = null;
    private Dao<MitAgencyproM, String> mitAgencyproMDao = null;

    private Dao<MitValgroupproM, String> mitValgroupproMDao = null;
    private Dao<MitValaddaccountM, String> mitValaddaccountMDao = null;
    private Dao<MitValaddaccountproM, String> mitValaddaccountproMDao = null;

    Dao<MitPlanweekM, String> mitPlanweekMDao = null;
    Dao<MitPlandayM, String> mitPlandayMDao = null;
    Dao<MitPlandaydetailM, String> mitPlandaydetailMDao = null;
    MitPlandayvalMDao mitPlandayvalMDao = null;

    private Dao<MitRepairM, String> mitRepairMDao = null;
    private Dao<MitRepairterM, String> mitRepairterMDao = null;
    private Dao<MitRepaircheckM, String> mitRepaircheckMDao = null;

    Dao<MitValagreeM, String> valagreeMDao = null;
    Dao<MitValagreedetailM, String> mitValagreedetailMDao = null;

    public XtUploadService(Context context, Handler handler) {
        this.handler = handler;
        this.context = context;
        helper = DatabaseHelper.getHelper(context);
        try {
            mstVisitmemoInfoDao = helper.getMstVisitmemoInfoDao();
            mstQuestionsanswersInfoDao = helper.getMstQuestionsanswersInfoDao();
            mstVisitMDao = helper.getMstVisitMDao();
            mitVisitMDao = helper.getMitVisitMDao();
            mstWorksummaryInfoDao = helper.getMstWorksummaryInfoDao();
            mstTerminalinfoMDao = helper.getMstTerminalinfoMDao();
            mitTerminalinfoMDao = helper.getMitTerminalinfoMDao();
            mstInvoicingInfoDao = helper.getMstInvoicingInfoDao();
            mstAgencytransferInfoDao = helper.getMstAgencytransferInfoDao();
            mstVistproductInfoDao = helper.getMstVistproductInfoDao();
            mitVistproductInfoDao = helper.getMitVistproductInfoDao();
            mstCheckexerecordInfoDao = helper.getMstCheckexerecordInfoDao();
            mitCheckexerecordInfoDao = helper.getMitCheckexerecordInfoDao();
            mstInvalidapplayInfoDao = helper.getMstInvalidapplayInfoDao();
            mstCollectionexerecordInfoDao = helper.getMstCollectionexerecordInfoDao();
            mitCollectionexerecordInfoDao = helper.getMitCollectionexerecordInfoDao();
            mstPromotermInfoDao = helper.getMstPromotermInfoDao();
            mitPromotermInfoDao = helper.getMitPromotermInfoDao();
            mstPlancheckInfoDao = helper.getMstPlancheckInfoDao();
            mstPlanTerminalMDao = helper.getMstPlanTerminalM();

            mstPlancollectionInfoDao = helper.getMstPlancollectionInfoDao();
            mstPlanforuserMDao = helper.getMstPlanforuserMDao();
            mstPlanWeekforuserMDao = helper.getMstPlanWeekforuserMDao();
            mstAgencysupplyInfoDao = helper.getMstAgencysupplyInfoDao();
            mitAgencysupplyInfoDao = helper.getMitAgencysupplyInfoDao();
            mstAgencyvisitMDao = helper.getMstAgencyvisitMDao();
            mstCmpsupplyInfoDao = helper.getMstCmpsupplyInfoDao();
            mitCmpsupplyInfoDao = helper.getMitCmpsupplyInfoDao();
            mstPlanrouteInfodDao = helper.getMstPlanrouteInfoDao();
            mstCameraiInfoMDao = (MstCameraiInfoMDao) helper.getMstCameraiInfoMDao();
            cameraTempDao = helper.getMstCameraInfoMTempDao();
            mitCameraInfoMDao = (MitCameraInfoMDao) helper.getMitCameraInfoMDao();
            mstAgencyKFMDao = (MstAgencyKFMDao) helper.getMstAgencyKFMDao();
            mstGroupproductMDao = helper.getMstGroupproductMDao();
            mitGroupproductMDao = helper.getMitGroupproductMDao();


            // 复制督导追溯数据
            valterMDao = helper.getDao(MitValterM.class);

            // 复制追溯拉链表
            mitValchecktypeMDao = helper.getMitValchecktypeMDao();

            // 复制追溯采集项表
            mitValcheckitemMDao = helper.getMitValcheckitemMDao();

            // 复制追溯终端活动表
            mitValpromotionsMDao = helper.getMitValpromotionsMDao();

            // 复制追溯产品组合表
            mitValgroupproMDao = helper.getMitValgroupproMDao();

            // 复制追溯进销存表
            mitValsupplyMDao = helper.getMitValsupplyMDao();

            // 复制追溯聊竞品表
            mitValcmpMDao = helper.getMitValcmpMDao();

            // 复制追溯聊竞品附表
            mitValcmpotherMDao = helper.getMitValcmpotherMDao();

            // 复制追溯图片表
            mitValpicMDao = helper.getMitValpicMDao();

            // 复制督导新增终端
            mitTerminalMDao = helper.getMitTerminalMDao();
            //
            mitValagencykfMDao = helper.getMitValagencykfMDao();
            //
            mitAgencynumMDao = helper.getMitAgencynumMDao();

            mitAgencyproMDao = helper.getMitAgencyproMDao();

            //
            mitValaddaccountMDao = helper.getMitValaddaccountMDao();
            mitValaddaccountproMDao = helper.getMitValaddaccountproMDao();

            mitPlanweekMDao = helper.getMitPlanweekMDao();
            mitPlandayMDao = helper.getMitPlandayMDao();
            mitPlandaydetailMDao = helper.getMitPlandaydetailMDao();
            mitPlandayvalMDao = helper.getDao(MitPlandayvalM.class);

            mitRepairMDao = helper.getDao(MitRepairM.class);
            mitRepairterMDao = helper.getDao(MitRepairterM.class);
            mitRepaircheckMDao = helper.getDao(MitRepaircheckM.class);

            valagreeMDao = helper.getMitValagreeMDao();
            mitValagreedetailMDao = helper.getMitValagreedetailMDao();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传数据
     *
     * @param isNeedExit true 的时候 上传数据后退出程序 ，不需要退出程序 请用false
     */
    public void uploadTables(boolean isNeedExit) {
        upload_xt_visit(isNeedExit, null, 1,1);// 上传协同
        upload_zs_visit(isNeedExit, null, 1,1);// 上传追溯
        uploadMitTerminalM(isNeedExit, null, 1);// 上传督导新增终端  //
        uploadMitValagencykfM(isNeedExit, null, 1);// 上传经销商资料库  //
        uploadMitAgencynumM(isNeedExit, null, null, 1);// 上传经销商库存盘点 //
        uploadWeekPlan(isNeedExit, null, 1);// 上传周计划
        upload_repair(isNeedExit, null, null, 1);// 上传整顿计划
    }

    /**
     * @return true 表示都上全完了，false 表示还有未上传数据
     * @throws SQLException
     */
    public boolean isAllEmpty() {
        try {
            //巡店拜访检查
            List<MstVisitM> visits = mstVisitMDao.queryForEq("padisconsistent", "0");
            if (!visits.isEmpty()) {
                return false;
            }

            // 追溯检查
            List<MitValterM> mitValterMS = valterMDao.queryForEq("padisconsistent", "0");
            if (!mitValterMS.isEmpty()) {
                return false;
            }

            // 督导新增终端
            Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
            terminalKeyMap.put("uploadflag", "1");
            terminalKeyMap.put("padisconsistent", "0");
            List<MitTerminalM> terminalMS = mitTerminalMDao.queryForFieldValues(terminalKeyMap);
            if (!terminalMS.isEmpty()) {
                return false;
            }

            // 经销商资料库
            List<MitValagencykfM> valagencykfMS = mitValagencykfMDao.queryForFieldValues(terminalKeyMap);
            if (!valagencykfMS.isEmpty()) {
                return false;
            }

            // 经销商库存盘点
            List<MitAgencynumM> agencynumMS = mitAgencynumMDao.queryForFieldValues(terminalKeyMap);
            List<MitAgencyproM> agencyproMS = mitAgencyproMDao.queryForFieldValues(terminalKeyMap);
            if (!agencynumMS.isEmpty()) {
                return false;
            }
            if (!agencyproMS.isEmpty()) {
                return false;
            }

            // 周计划
            List<MitPlanweekM> planweekMS = mitPlanweekMDao.queryForEq("padisconsistent", "0");
            if (!planweekMS.isEmpty()) {
                return false;
            }

            // 整顿计划
            List<MitRepairM> repairMS = mitRepairMDao.queryForFieldValues(terminalKeyMap);
            if (!repairMS.isEmpty()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    List<MitVisitM> visits = new ArrayList<MitVisitM>();
    List<MitVisitM> visitsall = new ArrayList<MitVisitM>();
    List<MitVistproductInfo> mVistproductInfos = new ArrayList<MitVistproductInfo>();
    List<MstInvalidapplayInfo> mInvalidapplayInfos_visit = new ArrayList<MstInvalidapplayInfo>();
    List<MitPromotermInfo> mPromotermInfos = new ArrayList<MitPromotermInfo>();
    List<MitTerminalinfoM> mTerminalinfoMs_visit = new ArrayList<MitTerminalinfoM>();
    List<MitCheckexerecordInfo> mCheckexerecordInfos = new ArrayList<MitCheckexerecordInfo>();
    List<MitCollectionexerecordInfo> mCollectionexerecordInfos = new ArrayList<MitCollectionexerecordInfo>();
    List<MitAgencysupplyInfo> mAgencysupplyInfos = new ArrayList<MitAgencysupplyInfo>();
    List<MitCmpsupplyInfo> cmpsupplyInfos = new ArrayList<MitCmpsupplyInfo>();
    List<MitCameraInfoM> mitCameraInfoMs = new ArrayList<MitCameraInfoM>();// 上传图片列表
    List<MitGroupproductM> mMstGroupproductMs = new ArrayList<MitGroupproductM>(); // 上传产品组合是否达标

    public void upload_xt_visit(final boolean isNeedExit, final String visitKey, final int whatId,final int type) {
        try {
            MitVisitM visit = null;
            if (visitKey != null && !visitKey.trim().equals("")) {
                visit = mitVisitMDao.queryForId(visitKey);
                if (visit != null) {
                    // String visitDate = DateUtil.formatDate(new Date(),"yyyyMMddHHmmss");
                    // visit.setEnddate(visitDate);
                    visits.add(visit);
                    visitsall.add(visit);
                    String terminalkey = visit.getTerminalkey();

                    Map<String, Object> visitKeyMap = new HashMap<String, Object>();
                    visitKeyMap.put("visitkey", visitKey);
                    visitKeyMap.put("padisconsistent", "0");

                    // 图片上传专用map
                    Map<String, Object> visitKeyisuploadMap = new HashMap<String, Object>();
                    visitKeyisuploadMap.put("visitkey", visitKey);
                    visitKeyisuploadMap.put("isupload", "0");

                    Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                    terminalKeyMap.put("terminalkey", terminalkey);
                    terminalKeyMap.put("padisconsistent", "0");

                    // MST_INVALIDAPPLAY_INFO(终端申请表)
                    mInvalidapplayInfos_visit = mstInvalidapplayInfoDao.queryForFieldValues(visitKeyMap);
                    // MST_PROMOTERM_INFO(终端参加活动信息表)MstPromotermInfo
                    mPromotermInfos = mitPromotermInfoDao.queryForFieldValues(visitKeyMap);
                    // MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表)MstVistproductInfo
                    mVistproductInfos = mitVistproductInfoDao.queryForFieldValues(visitKeyMap);
                    // MST_COLLECTIONEXERECORD_INFO(拜访指标执行采集项记录表)MstCollectionexerecordInfo
                    mCollectionexerecordInfos = mitCollectionexerecordInfoDao.queryForFieldValues(visitKeyMap);
                    for (int i = 0; i < mCollectionexerecordInfos.size(); i++) {
                        if (null != mCollectionexerecordInfos.get(i).getUpdateuser()) {
                            if ("0-1".equals(mCollectionexerecordInfos.get(i).getUpdateuser().trim())) {
                                mCollectionexerecordInfos.get(i).setUpdateuser(visit.getUserid());
                            }
                        }
                    }
                    // MST_TERMINALINFO_M(终端档案主表)MstTerminalinfoM
                    mTerminalinfoMs_visit = mitTerminalinfoMDao.queryForEq("padisconsistent", "0");
                    // MST_CHECKEXERECORD_INFO(拜访指标执行记录表)MstCheckexerecordInfo
                    mCheckexerecordInfos = mitCheckexerecordInfoDao.queryForFieldValues(terminalKeyMap);
                    // mTerminalinfoMs_visit =
                    // mstTerminalinfoMDao.queryForFieldValues(terminalKeyMap);

                    // MST_CMPSUPPLY_INFO(竞品供货关系表)
                    cmpsupplyInfos = mitCmpsupplyInfoDao.queryForFieldValues(terminalKeyMap);

                    // 照片
                    mitCameraInfoMs = mitCameraInfoMDao.queryForFieldValues(visitKeyisuploadMap);

                    // MstGroupproductM (产品组合是否达标)
                    //mMstGroupproductMs = mstGroupproductMDao.queryForFieldValues(terminalKeyMap);
                    // 存放所有MstGroupproductM表的未上传记录
                    QueryBuilder<MitGroupproductM, String> MstGroupproductMQB1 = mitGroupproductMDao.queryBuilder();
                    Where<MitGroupproductM, String> mstGroupproductMWhere1 = MstGroupproductMQB1.where();
                    mstGroupproductMWhere1.eq("uploadflag", "1");
                    mstGroupproductMWhere1.and();
                    mstGroupproductMWhere1.eq("padisconsistent", "0");
                    mstGroupproductMWhere1.and();
                    mstGroupproductMWhere1.eq("visitkey", visitKey);

                    MstGroupproductMQB1.orderBy("createdate", true);
                    mMstGroupproductMs = MstGroupproductMQB1.query();

                    Map<String, Object> lowerkeyMap = new HashMap<String, Object>();
                    lowerkeyMap.put("lowerkey", terminalkey);
                    lowerkeyMap.put("padisconsistent", "0");
                    // MST_AGENCYSUPPLY_INFO(经销商供货关系信息表)MstAgencysupplyInfo
                    mAgencysupplyInfos = mitAgencysupplyInfoDao.queryForFieldValues(lowerkeyMap);
                }

            } else {
                // Map<String, Object> hashMap = new HashMap<String, Object>();
                // hashMap.put("uploadFlag", "1");
                // hashMap.put("padisconsistent", "0");
                // visits = mstVisitMDao.queryForFieldValues(hashMap);

                // 只存放所有终端的最新一次拜访记录(分组)
                QueryBuilder<MitVisitM, String> visitQB = mitVisitMDao.queryBuilder();
                Where<MitVisitM, String> visitWhere = visitQB.where();
                visitWhere.eq("uploadFlag", "1");
                visitWhere.and();
                visitWhere.eq("padisconsistent", "0");
                //productQb.groupBy(DBConst.PROD_PARENT_PRODVAR_ID);
                // 离线拜访时,针对同一家终端多次拜访,只上传最后一次拜访数据
                visitQB.groupBy("terminalkey");
                visitQB.orderBy("terminalkey", true);
                visitQB.orderBy("visitdate", false);
                visits = visitQB.query();


                // 存放今天所有终端所有次数的拜访记录
                QueryBuilder<MitVisitM, String> visitQB1 = mitVisitMDao.queryBuilder();
                Where<MitVisitM, String> visitWhere1 = visitQB1.where();
                visitWhere1.eq("uploadFlag", "1");
                visitWhere1.and();
                visitWhere1.eq("padisconsistent", "0");
                //productQb.groupBy(DBConst.PROD_PARENT_PRODVAR_ID);
                visitQB1.orderBy("terminalkey", true);
                visitQB1.orderBy("visitdate", false);
                visitsall = visitQB1.query();


                if (visits != null && !visits.isEmpty()) {
                    // MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表)MstVistproductInfo
                    mVistproductInfos = mitVistproductInfoDao.queryForEq("padisconsistent", "0");
                    // MST_INVALIDAPPLAY_INFO(终端申请表)MstInvalidapplayInfo
                    // 此处不上传终端申请失效数据
                    // mInvalidapplayInfos_visit =
                    // mstInvalidapplayInfoDao.queryForEq("padisconsistent",
                    // "0");
                    // MST_TERMINALINFO_M(终端档案主表)MstTerminalinfoM
                    // 只上传非终端申请失效数据
                    // mTerminalinfoMs_visit =
                    // mstTerminalinfoMDao.queryForEq("padisconsistent", "0");
                    QueryBuilder<MitTerminalinfoM, String> termQb = mitTerminalinfoMDao.queryBuilder();
                    Where<MitTerminalinfoM, String> termWhere = termQb.where();
                    termWhere.eq("padisconsistent", "0");
                    termWhere.and();
                    termWhere.ne("status", ConstValues.FLAG_3);
                    mTerminalinfoMs_visit = termQb.query();
                    // MST_PROMOTERM_INFO(终端参加活动信息表)MstPromotermInfo
                    mPromotermInfos = mitPromotermInfoDao.queryForEq("padisconsistent", "0");
                    // MST_CHECKEXERECORD_INFO(拜访指标执行记录表)MstCheckexerecordInfo
                    mCheckexerecordInfos = mitCheckexerecordInfoDao.queryForEq("padisconsistent", "0");
                    if (null != visit) {
                        for (int i = 0; i < mCheckexerecordInfos.size(); i++) {
                            if (null != mCheckexerecordInfos.get(i).getUpdateuser()) {
                                if ("0-1".equals(mCheckexerecordInfos.get(i).getUpdateuser().trim())) {
                                    mCheckexerecordInfos.get(i).setUpdateuser(visit.getUserid());
                                }
                            }
                        }
                    }
                    // MST_COLLECTIONEXERECORD_INFO(拜访指标执行采集项记录表)MstCollectionexerecordInfo
                    mCollectionexerecordInfos = mitCollectionexerecordInfoDao.queryForEq("padisconsistent", "0");
                    // MST_AGENCYSUPPLY_INFO(经销商供货关系信息表)MstAgencysupplyInfo
                    mAgencysupplyInfos = mitAgencysupplyInfoDao.queryForEq("padisconsistent", "0");
                    // MST_CMPSUPPLY_INFO(竞品供货关系表)
                    cmpsupplyInfos = mitCmpsupplyInfoDao.queryForEq("padisconsistent", "0");

                    // 照片
                    mitCameraInfoMs = mitCameraInfoMDao.queryForEq("isupload", "0");

                    // 存放所有MstGroupproductM表的未上传记录
                    QueryBuilder<MitGroupproductM, String> MstGroupproductMQB1 = mitGroupproductMDao.queryBuilder();
                    Where<MitGroupproductM, String> mstGroupproductMWhere1 = MstGroupproductMQB1.where();
                    mstGroupproductMWhere1.eq("uploadflag", "1");
                    mstGroupproductMWhere1.and();
                    mstGroupproductMWhere1.eq("padisconsistent", "0");
                    MstGroupproductMQB1.orderBy("createdate", true);
                    mMstGroupproductMs = MstGroupproductMQB1.query();

                }
            }
            if (visits != null && !visits.isEmpty()) {
                List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
                // 根据表结果组织数据关系
                for (MitVisitM mstVisitm : visits) {
                    Map<String, String> childDatas = new HashMap<String, String>();
                    mstVisitm.setPadisconsistent("1");
                    List<MitVisitM> mstVisitMs = new ArrayList<MitVisitM>();
                    //String visitsss = JsonUtil.toJson(mstVisitm);
                    mstVisitMs.add(mstVisitm);
                    //savFile(visitsss, "MstVisits");// 采集项记录表
                    //FileUtil.writeTxt(visitsss,FileUtil.getSDPath()+"/MstVisits2.txt");//上传巡店拜访的json
                    //childDatas.put("MIT_VISIT_M", JsonUtil.toJson(mstVisitm));
                    childDatas.put("MIT_VISIT_M", JsonUtil.toJson(mstVisitMs));

                    String visitkey = mstVisitm.getVisitkey();
                    String terminalkey = mstVisitm.getTerminalkey();
                    String terminalcode = "";
                    List<MitTerminalinfoM> childTerminalinfoMs = new ArrayList<MitTerminalinfoM>();
                    for (MitTerminalinfoM mTerminalinfoM : mTerminalinfoMs_visit) {
                        if (terminalkey.equals(mTerminalinfoM.getTerminalkey())) {
                            terminalcode = mTerminalinfoM.getTerminalcode();
                            mTerminalinfoM.setPadisconsistent("1");
                            childTerminalinfoMs.add(mTerminalinfoM);
                        }
                    }
                    childDatas.put("MIT_TERMINALINFO_M", JsonUtil.toJson(childTerminalinfoMs));
                    /*String childTerminalinfoMsqwe = JsonUtil.toJson(childTerminalinfoMs);
                    FileUtil.writeTxt(childTerminalinfoMsqwe, FileUtil.getSDPath()+"/childTerminalinfoMsqwe.txt");*/

                    List<MitGroupproductM> childMstGroupproductMs = new ArrayList<MitGroupproductM>();
                    for (MitGroupproductM mstgroupproductm : mMstGroupproductMs) {
                        if (visitkey.equals(mstgroupproductm.getVisitkey())) {
                            mstgroupproductm.setPadisconsistent("1");
                            childMstGroupproductMs.add(mstgroupproductm);
                        }
                    }
                    childDatas.put("MIT_GROUPPRODUCT_M", JsonUtil.toJson(childMstGroupproductMs));

                    List<MitCmpsupplyInfo> childCmpsupplyInfos = new ArrayList<MitCmpsupplyInfo>();
                    for (MitCmpsupplyInfo childCmpsupplyInfo : cmpsupplyInfos) {
                        if (terminalkey.equals(childCmpsupplyInfo.getTerminalkey())) {
                            childCmpsupplyInfo.setPadisconsistent("1");
                            childCmpsupplyInfos.add(childCmpsupplyInfo);
                        }
                    }
                    childDatas.put("MIT_CMPSUPPLY_INFO", JsonUtil.toJson(childCmpsupplyInfos));

                    // 终端档案申请表
                    List<MstInvalidapplayInfo> childInvalidapplayInfos = new ArrayList<MstInvalidapplayInfo>();
                    for (MstInvalidapplayInfo mInvalidapplayInfo : mInvalidapplayInfos_visit) {
                        if (visitkey.equals(mInvalidapplayInfo.getVisitkey())) {
                            mInvalidapplayInfo.setPadisconsistent("1");
                            childInvalidapplayInfos.add(mInvalidapplayInfo);
                        }
                    }
                    childDatas.put("MIT_INVALIDAPPLAY_INFO", JsonUtil.toJson(childInvalidapplayInfos));

                    // MST_PROMOTERM_INFO(终端参加活动信息表)
                    List<MitPromotermInfo> childPromotermInfos = new ArrayList<MitPromotermInfo>();
                    for (MitPromotermInfo mPromotermInfo : mPromotermInfos) {
                        if (visitkey.equals(mPromotermInfo.getVisitkey())) {
                            mPromotermInfo.setPadisconsistent("1");
                            childPromotermInfos.add(mPromotermInfo);
                        }
                    }
                    childDatas.put("MIT_PROMOTERM_INFO", JsonUtil.toJson(childPromotermInfos));

                    // MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表)
                    List<MitVistproductInfo> childMstVistproductInfos = new ArrayList<MitVistproductInfo>();
                    for (MitVistproductInfo mVistproductInfo : mVistproductInfos) {
                        if (visitkey.equals(mVistproductInfo.getVisitkey())) {
                            mVistproductInfo.setPadisconsistent("1");
                            childMstVistproductInfos.add(mVistproductInfo);
                        }
                    }
                    String json = JsonUtil.toJson(childMstVistproductInfos);
                    //FileUtil.writeTxt(json, FileUtil.getSDPath()+"/mVistproductInfos0808.txt");
                    childDatas.put("MIT_VISTPRODUCT_INFO", JsonUtil.toJson(childMstVistproductInfos));


                    // MST_VISTPRODUCT_INFO(照片)
                    List<MitCameraInfoM> mitCameras = new ArrayList<MitCameraInfoM>();
                    for (MitCameraInfoM mitCameraInfoM : mitCameraInfoMs) {
                        if (visitkey.equals(mitCameraInfoM.getVisitkey())) {
                            mitCameraInfoM.setIsupload("1");
                            mitCameras.add(mitCameraInfoM);
                        }
                    }
                    //String json = JsonUtil.toJson(mitCameras);
                    //FileUtil.writeTxt(json, FileUtil.getSDPath()+"/mVistproductInfos0808.txt");
                    childDatas.put("MIT_VISITPIC_INFO", JsonUtil.toJson(mitCameras));


                    // MstAgencysupplyInfo MST_AGENCYSUPPLY_INFO(经销商供货关系信息表)
                    List<MitAgencysupplyInfo> childMstAgencysupplyInfos = new ArrayList<MitAgencysupplyInfo>();
                    for (MitAgencysupplyInfo mAgencysupplyInfo : mAgencysupplyInfos) {
                        if (terminalkey.equals(mAgencysupplyInfo.getLowerkey())) {
                            mAgencysupplyInfo.setPadisconsistent("1");
                            mAgencysupplyInfo.setOrderbyno("1");// 上传时将今天新增的供货关系标记改为1
                            childMstAgencysupplyInfos.add(mAgencysupplyInfo);
                        }
                    }
                    childDatas.put("MIT_AGENCYSUPPLY_INFO", JsonUtil.toJson(childMstAgencysupplyInfos));

                    // MST_COLLECTIONEXERECORD_INFO(拜访指标执行采集项记录表)
                    List<MitCollectionexerecordInfo> childMstCollectionexerecordInfos = new ArrayList<MitCollectionexerecordInfo>();

                    for (MitCollectionexerecordInfo mCollectionexerecordInfo : mCollectionexerecordInfos) {
                        if (visitkey.equals(mCollectionexerecordInfo.getVisitkey())) {
                            mCollectionexerecordInfo.setPadisconsistent("1");
                            childMstCollectionexerecordInfos.add(mCollectionexerecordInfo);
                        }
                    }
                    String collectionexerecord = JsonUtil.toJson(childMstCollectionexerecordInfos);
                    //FileUtil.writeTxt(collectionexerecord, FileUtil.getSDPath()+"/collectionexerecord1.txt");
                    childDatas.put("MIT_COLLECTIONEXERECORD_INFO", JsonUtil.toJson(childMstCollectionexerecordInfos));

                    // MST_CHECKEXERECORD_INFO(拜访指标执行记录表)MstCheckexerecordInfo
                    List<MitCheckexerecordInfo> childMstCheckexerecordInfos = new ArrayList<MitCheckexerecordInfo>();
                    for (MitCheckexerecordInfo mCheckexerecordInfo : mCheckexerecordInfos) {
                        if (null != visit) {
                            mCheckexerecordInfo.setUpdateuser(visit.getUserid());
                        }
                        // 修改多家离线上传数据错误bug
                        if (terminalkey.equals(mCheckexerecordInfo.getTerminalkey())) {

                            // 只上传离线拜访的最后一次
                            if ("temp".equals(mCheckexerecordInfo.getVisitkey())) {
                                mCheckexerecordInfo.setPadisconsistent("1");
                                childMstCheckexerecordInfos.add(mCheckexerecordInfo);
                            }
                            if (visitkey.equals(mCheckexerecordInfo.getVisitkey())) {
                                mCheckexerecordInfo.setPadisconsistent("1");
                                childMstCheckexerecordInfos.add(mCheckexerecordInfo);
                            }
                        }
                    }

                    //String checkexerecord = JsonUtil.toJson(childMstCheckexerecordInfos);
                    //savFile(checkexerecord, "MST_CHECKEXERECORD_INFO");// 拉链表
                    //FileUtil.writeTxt(checkexerecord, FileUtil.getSDPath()+"/checkexerecord3213.txt");
                    childDatas.put("MIT_CHECKEXERECORD_INFO", JsonUtil.toJson(childMstCheckexerecordInfos));

                    mainDatas.add(childDatas);
                }

                // 添加
                String json = JsonUtil.toJson(mainDatas);
                //FileUtil.writeTxt(json,FileUtil.getSDPath()+"/shopvisit1016.txt");//上传巡店拜访的json
                // System.out.println("巡店拜访"+json);
                Log.i(TAG, "巡店拜访send list size" + mainDatas.size() + json);

                upVisitDataInService("opt_save_visit", "", json,type);

            } else {
                if (isNeedExit) {
                    //上传所有的巡店拜访
                    // handler.sendEmptyMessage(SystemFragment.CLOSEPROGRESS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    List<MitValterM> mitValterMs = new ArrayList<MitValterM>();
    List<MitValterM> mitValterMsall = new ArrayList<MitValterM>();
    List<MitValchecktypeM> mitValchecktypeMs = new ArrayList<MitValchecktypeM>();
    List<MitValcheckitemM> mitValcheckitemMs = new ArrayList<MitValcheckitemM>();
    List<MitValpromotionsM> mitValpromotionsMs = new ArrayList<MitValpromotionsM>();
    List<MitValsupplyM> mitValsupplyMs = new ArrayList<MitValsupplyM>();
    List<MitValcmpM> mitValcmpMs = new ArrayList<MitValcmpM>();
    List<MitValcmpotherM> mitValcmpotherMs = new ArrayList<MitValcmpotherM>();
    List<MitValpicM> mitValpicMs = new ArrayList<MitValpicM>();
    List<MitValgroupproM> mitValgroupproMs = new ArrayList<MitValgroupproM>();
    List<MitValaddaccountM> mitValaddaccountMs = new ArrayList<MitValaddaccountM>();
    List<MitValaddaccountproM> mitValaddaccountproMs = new ArrayList<MitValaddaccountproM>();

    List<MitValagreeM> mitValagreeMs = new ArrayList<MitValagreeM>();
    List<MitValagreedetailM> mitValagreedetailMs = new ArrayList<MitValagreedetailM>();


    /**
     * 上传追溯数据
     * @param isNeedExit
     * @param valterid
     * @param whatId
     * @param type  0正常上传  1离线上传
     */
    public void upload_zs_visit(final boolean isNeedExit, final String valterid, final int whatId,final int type) {
        try {
            MitValterM mitValterM = null;
            if (valterid != null && !valterid.trim().equals("")) {
                mitValterM = valterMDao.queryForId(valterid);
                if (mitValterM != null) {
                    mitValterMs.add(mitValterM);
                    mitValterMsall.add(mitValterM);
                    Map<String, Object> valteridMap = new HashMap<String, Object>();
                    valteridMap.put("valterid", valterid);
                    valteridMap.put("padisconsistent", "0");
                    mitValchecktypeMs = mitValchecktypeMDao.queryForFieldValues(valteridMap);
                    mitValcheckitemMs = mitValcheckitemMDao.queryForFieldValues(valteridMap);
                    mitValpromotionsMs = mitValpromotionsMDao.queryForFieldValues(valteridMap);
                    mitValsupplyMs = mitValsupplyMDao.queryForFieldValues(valteridMap);
                    mitValcmpMs = mitValcmpMDao.queryForFieldValues(valteridMap);
                    mitValcmpotherMs = mitValcmpotherMDao.queryForFieldValues(valteridMap);
                    mitValpicMs = mitValpicMDao.queryForFieldValues(valteridMap);
                    mitValgroupproMs = mitValgroupproMDao.queryForFieldValues(valteridMap);

                    mitValagreeMs = valagreeMDao.queryForFieldValues(valteridMap);
                    mitValagreedetailMs = mitValagreedetailMDao.queryForFieldValues(valteridMap);

                    Map<String, Object> idMap = new HashMap<String, Object>();
                    idMap.put("valsupplyid", valterid);
                    idMap.put("padisconsistent", "0");
                    mitValaddaccountMs = mitValaddaccountMDao.queryForFieldValues(idMap);
                    mitValaddaccountproMs = mitValaddaccountproMDao.queryForFieldValues(valteridMap);
                    String sfd = "";
                }
            } else {
                // 只存放所有终端的最新一次拜访记录(分组)
                QueryBuilder<MitValterM, String> visitQB = valterMDao.queryBuilder();
                Where<MitValterM, String> visitWhere = visitQB.where();
                visitWhere.eq("padisconsistent", "0");
                //productQb.groupBy(DBConst.PROD_PARENT_PRODVAR_ID);
                // 离线拜访时,针对同一家终端多次拜访,只上传最后一次拜访数据
                visitQB.groupBy("terminalkey");
                visitQB.orderBy("terminalkey", true);
                mitValterMs = visitQB.query();


                // 存放今天所有终端所有次数的拜访记录
                QueryBuilder<MitValterM, String> visitQB1 = valterMDao.queryBuilder();
                Where<MitValterM, String> visitWhere1 = visitQB1.where();
                visitWhere1.eq("padisconsistent", "0");
                //productQb.groupBy(DBConst.PROD_PARENT_PRODVAR_ID);
                visitQB1.orderBy("terminalkey", true);
                mitValterMsall = visitQB1.query();


                if (mitValterMs != null && !mitValterMs.isEmpty()) {
                    mitValchecktypeMs = mitValchecktypeMDao.queryForEq("padisconsistent", "0");
                    mitValcheckitemMs = mitValcheckitemMDao.queryForEq("padisconsistent", "0");
                    mitValpromotionsMs = mitValpromotionsMDao.queryForEq("padisconsistent", "0");
                    mitValsupplyMs = mitValsupplyMDao.queryForEq("padisconsistent", "0");
                    mitValcmpMs = mitValcmpMDao.queryForEq("padisconsistent", "0");
                    mitValcmpotherMs = mitValcmpotherMDao.queryForEq("padisconsistent", "0");
                    mitValpicMs = mitValpicMDao.queryForEq("padisconsistent", "0");
                    mitValgroupproMs = mitValgroupproMDao.queryForEq("padisconsistent", "0");
                    mitValaddaccountMs = mitValaddaccountMDao.queryForEq("padisconsistent", "0");
                    mitValaddaccountproMs = mitValaddaccountproMDao.queryForEq("padisconsistent", "0");

                    mitValagreeMs = valagreeMDao.queryForEq("padisconsistent", "0");
                    mitValagreedetailMs = mitValagreedetailMDao.queryForEq("padisconsistent", "0");
                }
            }

            if (mitValterMs != null && !mitValterMs.isEmpty()) {
                List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
                // 根据表结果组织数据关系
                for (MitValterM valterM : mitValterMs) {
                    Map<String, String> childDatas = new HashMap<String, String>();
                    String valtermid = valterM.getId();
                    String terminalkey = valterM.getTerminalkey();

                    // 1 追溯主表
                    List<MitValterM> mstVisitMs = new ArrayList<MitValterM>();
                    valterM.setPadisconsistent("1");
                    mstVisitMs.add(valterM);
                    childDatas.put("MIT_VALTER_M", JsonUtil.toJson(mstVisitMs));

                    // 2 追溯拉链表
                    List<MitValchecktypeM> childValchecktypeMs = new ArrayList<MitValchecktypeM>();
                    for (MitValchecktypeM mitValchecktypeM : mitValchecktypeMs) {
                        if (valtermid.equals(mitValchecktypeM.getValterid())) {
                            mitValchecktypeM.setPadisconsistent("1");
                            childValchecktypeMs.add(mitValchecktypeM);
                        }
                    }
                    childDatas.put("MIT_VALCHECKTYPE_M", JsonUtil.toJson(childValchecktypeMs));

                    // 3 追溯采集项表
                    List<MitValcheckitemM> childValcheckitems = new ArrayList<MitValcheckitemM>();
                    for (MitValcheckitemM mitValcheckitemM : mitValcheckitemMs) {
                        if (valtermid.equals(mitValcheckitemM.getValterid())) {
                            mitValcheckitemM.setPadisconsistent("1");
                            childValcheckitems.add(mitValcheckitemM);
                        }
                    }
                    childDatas.put("MIT_VALCHECKITEM_M", JsonUtil.toJson(childValcheckitems));

                    // 4 追溯促销活动终端表
                    List<MitValpromotionsM> childValpromotionsMs = new ArrayList<MitValpromotionsM>();
                    for (MitValpromotionsM mitValpromotionsM : mitValpromotionsMs) {
                        if (valtermid.equals(mitValpromotionsM.getValterid())) {
                            mitValpromotionsM.setPadisconsistent("1");
                            childValpromotionsMs.add(mitValpromotionsM);
                        }
                    }
                    childDatas.put("MIT_VALPROMOTIONS_M", JsonUtil.toJson(childValpromotionsMs));

                    // 5 追溯产品组合表
                    List<MitValgroupproM> childValgroupproMs = new ArrayList<MitValgroupproM>();
                    for (MitValgroupproM mitValgroupproM : mitValgroupproMs) {
                        if (valtermid.equals(mitValgroupproM.getValterid())) {
                            mitValgroupproM.setPadisconsistent("1");
                            childValgroupproMs.add(mitValgroupproM);
                        }
                    }
                    childDatas.put("MIT_VALGROUPPRO_M", JsonUtil.toJson(childValgroupproMs));


                    // 6 追溯进销存 我品供货关系
                    List<MitValsupplyM> childValsupplyMs = new ArrayList<MitValsupplyM>();
                    for (MitValsupplyM mitValsupplyM : mitValsupplyMs) {
                        if (valtermid.equals(mitValsupplyM.getValterid())) {
                            mitValsupplyM.setPadisconsistent("1");
                            childValsupplyMs.add(mitValsupplyM);
                        }
                    }
                    childDatas.put("MIT_VALSUPPLY_M", JsonUtil.toJson(childValsupplyMs));

                    // 7 追溯聊竞品 竞品品供货关系
                    List<MitValcmpM> childValcmpMs = new ArrayList<MitValcmpM>();
                    for (MitValcmpM mitValcmpM : mitValcmpMs) {
                        if (valtermid.equals(mitValcmpM.getValterid())) {
                            mitValcmpM.setPadisconsistent("1");
                            childValcmpMs.add(mitValcmpM);
                        }
                    }
                    childDatas.put("MIT_VALCMP_M", JsonUtil.toJson(childValcmpMs));

                    // 8 追溯聊竞品附表 竞品品供货关系附表
                    List<MitValcmpotherM> childValcmpothers = new ArrayList<MitValcmpotherM>();
                    for (MitValcmpotherM mitValcmpotherM : mitValcmpotherMs) {
                        if (valtermid.equals(mitValcmpotherM.getValterid())) {
                            mitValcmpotherM.setPadisconsistent("1");
                            childValcmpothers.add(mitValcmpotherM);
                        }
                    }
                    childDatas.put("MIT_VALCMPOTHER_M", JsonUtil.toJson(childValcmpothers));

                    // 9 追溯图片表
                    List<MitValpicM> childValpicMs = new ArrayList<MitValpicM>();
                    for (MitValpicM mitValpicM : mitValpicMs) {
                        if (valtermid.equals(mitValpicM.getValterid())) {
                            mitValpicM.setPadisconsistent("1");
                            childValpicMs.add(mitValpicM);
                        }
                    }
                    childDatas.put("MIT_VALPIC_M", JsonUtil.toJson(childValpicMs));

                    // 10 终端进货台账主表
                    List<MitValaddaccountM> childValaddaccountMs = new ArrayList<MitValaddaccountM>();
                    for (MitValaddaccountM mitValpicM : mitValaddaccountMs) {
                        if (valtermid.equals(mitValpicM.getValsupplyid())) {
                            mitValpicM.setPadisconsistent("1");
                            childValaddaccountMs.add(mitValpicM);
                        }
                    }
                    childDatas.put("MIT_VALADDACCOUNT_M", JsonUtil.toJson(childValaddaccountMs));

                    // 11 终端追溯台账产品详情表
                    List<MitValaddaccountproM> childValaddaccountproMs = new ArrayList<MitValaddaccountproM>();
                    for (MitValaddaccountproM mitValpicM : mitValaddaccountproMs) {
                        if (valtermid.equals(mitValpicM.getValterid())) {
                            mitValpicM.setPadisconsistent("1");
                            childValaddaccountproMs.add(mitValpicM);
                        }
                    }
                    childDatas.put("MIT_VALADDACCOUNTPRO_M", JsonUtil.toJson(childValaddaccountproMs));

                    // 12  终端追溯协议主表  MIT_VALAGREE_M
                    List<MitValagreeM> mitValagreeMS = new ArrayList<MitValagreeM>();
                    for (MitValagreeM mitValagreeM : mitValagreeMs) {
                        if (valtermid.equals(mitValagreeM.getValterid())) {
                            mitValagreeM.setPadisconsistent("1");
                            mitValagreeMS.add(mitValagreeM);
                        }
                    }
                    childDatas.put("MIT_VALAGREE_M", JsonUtil.toJson(mitValagreeMS));

                    // 13  终端追溯协议对付信息表  MIT_VALAGREEDETAIL_M
                    List<MitValagreedetailM> valagreedetailMS = new ArrayList<MitValagreedetailM>();
                    for (MitValagreedetailM valagreedetailM : mitValagreedetailMs) {
                        if (valtermid.equals(valagreedetailM.getValterid())) {
                            valagreedetailM.setPadisconsistent("1");
                            valagreedetailMS.add(valagreedetailM);
                        }
                    }
                    childDatas.put("MIT_VALAGREEDETAIL_M", JsonUtil.toJson(valagreedetailMS));

                    mainDatas.add(childDatas);
                }

                // 添加
                String json = JsonUtil.toJson(mainDatas);
                //FileUtil.writeTxt(json,FileUtil.getSDPath()+"/shopvisit1016.txt");//上传巡店拜访的json
                // System.out.println("巡店拜访"+json);
                Log.i(TAG, "终端追溯send list size" + mainDatas.size() + json);

                upZsDataInService("opt_save_zdzs", "", json,type);

            } else {
                if (isNeedExit) {
                    //上传所有的巡店拜访
                    //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 用户点击返回  删除文件夹中的照片
    public void deleteDICM(String visitKey) {

        // 图片上传专用map
        Map<String, Object> visitKeyisuploadMap = new HashMap<String, Object>();
        visitKeyisuploadMap.put("visitkey", visitKey);
        visitKeyisuploadMap.put("isupload", "0");

        // 照片s
        // 图片列表
        List<MstCameraInfoMTemp> mstCameraInfoMTemps = new ArrayList<MstCameraInfoMTemp>();
        try {
            mstCameraInfoMTemps = cameraTempDao.queryForFieldValues(visitKeyisuploadMap);
            // 删除文件夹下的照片
            for (MstCameraInfoMTemp mTerminalinfoM : mstCameraInfoMTemps) {
                String picname = mTerminalinfoM.getPicname();
                // 删除文件夹照片
                FileUtil.deleteFile(new File(FileTool.CAMERA_PHOTO_DIR + picname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 上传协同拜访数据
     *
     * @param optcode
     * @param tableName
     * @param content
     */
    /**
     * 上传协同拜访数据
     * @param optcode 请求码
     * @param tableName 请求的表
     * @param content 请求json
     * @param type  0正常上传   1离线上传
     */
    void upVisitDataInService(final String optcode, final String tableName, String content,final int type) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                        // 处理成功上传
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            //
                            // 处理上传后的数据,该删删,该处理
                            String formjson = resObj.getResBody().getContent();
                            parseUpData(formjson);
                            if(0==type){
                                ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                            }

                        } else {
                            // Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        if(0==type){
                            ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }

                        //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                        if(0==type){
                            ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }
                        //Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();

                    }
                })
                .builde()
                .post();
    }

    /**
     * 上传终端追溯数据
     *
     * @param optcode   请求码
     * @param tableName 请求的表
     * @param content   请求json
     */
    void upZsDataInService(final String optcode, final String tableName, String content,final int type) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        if ("".equals(json) || json == null) {
                            Toast.makeText(context, "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                //
                                // 处理上传后的数据,该删删,该处理
                                String formjson = resObj.getResBody().getContent();
                                parseZsUpData(formjson);
                                if(0==type){// 0正常上传   1离线上传
                                    ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                                }

                            } else {
                                // Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        if(0==type){// 0正常上传   1离线上传
                            ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        if(0==type){// 0正常上传    1离线上传
                            ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }

                    }
                })
                .builde()
                .post();
    }

    // 上传成功后,解析数据 删除各个协同表数据
    private void parseUpData(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String mit_visit_m = emp.getMIT_VISIT_M();
        List<MitVisitM> mstVisitMs = (List<MitVisitM>) JsonUtil.parseList(mit_visit_m, MitVisitM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 修改协同的拜访主表 状态为上传成功
            for (MitVisitM visit : visitsall) {
                visit.setPadisconsistent("1");
                mitVisitMDao.createOrUpdate(visit);
            }
            // 修改协同我品供货关系 状态为上传成功 (因为没有visitkey,需要上传成功的标记)
            for (MitAgencysupplyInfo mAgencysupplyInfo : mAgencysupplyInfos) {
                mAgencysupplyInfo.setPadisconsistent("1");
                mAgencysupplyInfo.setOrderbyno("1");// 上传成功将今天新增的供货关系标记改为1
                mitAgencysupplyInfoDao.createOrUpdate(mAgencysupplyInfo);
            }
            // 修改协同竞品供货关系 状态为上传成功 (因为没有visitkey,需要上传成功的标记)
            for (MitCmpsupplyInfo mAgencysupplyInfo : cmpsupplyInfos) {
                mAgencysupplyInfo.setPadisconsistent("1");
                mAgencysupplyInfo.setOrderbyno("1");// 上传成功将今天新增的供货关系标记改为1
                mitCmpsupplyInfoDao.createOrUpdate(mAgencysupplyInfo);
            }

            // 修改协同拉链表 状态为上传成功 (因为没有visitkey,需要上传成功的标记)
            for (MitCheckexerecordInfo mCheckexerecordInfo : mCheckexerecordInfos) {
                mCheckexerecordInfo.setPadisconsistent("1");
                mitCheckexerecordInfoDao.createOrUpdate(mCheckexerecordInfo);
            }
            // 修改协同终端表 状态为上传成功 (因为没有visitkey,需要上传成功的标记)
            for (MitTerminalinfoM mTerminalinfoM : mTerminalinfoMs_visit) {
                mTerminalinfoM.setPadisconsistent("1");
                mitTerminalinfoMDao.createOrUpdate(mTerminalinfoM);
            }

            // 删除文件夹下的照片
            for (MitCameraInfoM mTerminalinfoM : mitCameraInfoMs) {

                String picname = mTerminalinfoM.getPicname();
                // 删除文件夹照片
                /*String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dbt/et.tsingtaopad" + "/photo/";
                FileUtil.deleteFile(new File(sdcardPath));*/
                // 删除原图
                FileUtil.deleteFile(new File(FileTool.CAMERA_PHOTO_DIR + picname));
            }

            for (MitVisitM mitVisitM : mstVisitMs) {

                String terminalkey = mitVisitM.getTerminalkey();
                String visitkey = mitVisitM.getVisitkey();
                // 删除协同该终端其余的拜访记录  padisconsistent = 1
                deleteMitVisitM(db, "MIT_VISIT_M", terminalkey, visitkey);


                // 根据VisitKey 删除图片表
                deleteTableInfoByVisitKey(db, "MIT_CAMERAINFO_M", terminalkey, visitkey);

                // 根据VisitKey 删除协同采集项
                deleteTableInfoByVisitKey(db, "MIT_COLLECTIONEXERECORD_INFO", terminalkey, visitkey);

                // 根据VisitKey 删除协同产品组合
                deleteTableInfoByVisitKey(db, "MIT_GROUPPRODUCT_M", terminalkey, visitkey);

                // 根据VisitKey 删除协同活动终端
                deleteTableInfoByVisitKey(db, "MIT_PROMOTERM_INFO", terminalkey, visitkey);

                // 根据VisitKey 删除协同拜访产品表
                deleteTableInfoByVisitKey(db, "MIT_VISTPRODUCT_INFO", terminalkey, visitkey);


                // 根据Terminalkey 删除 协同我品供货关系 padisconsistent = '1'
                deleteTableInfoByLowerkey(db, "MIT_AGENCYSUPPLY_INFO ", terminalkey, visitkey);

                // 根据Terminalkey 删除协同竞品供货关系  padisconsistent = '1'
                deleteTableInfoByTerminalkey(db, "MIT_CHECKEXERECORD_INFO", terminalkey, visitkey);

                // 根据Terminalkey 删除协同拉链表  padisconsistent = '1'
                deleteTableInfoByTerminalkey(db, "MIT_CMPSUPPLY_INFO", terminalkey, visitkey);

                // 根据Terminalkey 删除协同终端表  padisconsistent = '1'
                deleteTableInfoByTerminalkey(db, "MIT_TERMINALINFO_M", terminalkey, visitkey);

                // 删除协同 客情备忘
                //deleteTableInfoByTerminalkey(db,"MIT_VISITMEMO_INFO",terminalkey,visitkey);

                //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除协同数据 包含协同主表
    public void deleteXt(String visitkey, String terminalkey) {
        SQLiteDatabase db = helper.getWritableDatabase();
        deleteTableInfoByVisitKey(db, "MIT_VISIT_M", terminalkey, visitkey);
        deleteTableInfoByVisitKey(db, "MIT_CAMERAINFO_M", terminalkey, visitkey);
        deleteTableInfoByVisitKey(db, "MIT_COLLECTIONEXERECORD_INFO", terminalkey, visitkey);
        deleteTableInfoByVisitKey(db, "MIT_GROUPPRODUCT_M", terminalkey, visitkey);
        deleteTableInfoByVisitKey(db, "MIT_PROMOTERM_INFO", terminalkey, visitkey);
        deleteTableInfoByVisitKey(db, "MIT_VISTPRODUCT_INFO", terminalkey, visitkey);
        deleteXtTableInfoByLowerkey(db, "MIT_AGENCYSUPPLY_INFO ", terminalkey, visitkey);
        deleteXtTableInfoByTerminalkey(db, "MIT_CHECKEXERECORD_INFO", terminalkey, visitkey);
        deleteXtTableInfoByTerminalkey(db, "MIT_CMPSUPPLY_INFO", terminalkey, visitkey);
        deleteXtTableInfoByTerminalkey(db, "MIT_TERMINALINFO_M", terminalkey, visitkey);
    }

    // 追溯上传成功后,解析数据 删除各个协同表数据
    private void parseZsUpData(String json) {

        // 解析区域定格路线信息
        List<MitValterM> valterMs = (List<MitValterM>) JsonUtil.parseList(json, MitValterM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 修改协同的拜访主表 状态为上传成功
            for (MitValterM mitValterM : mitValterMs) {
                mitValterM.setPadisconsistent("1");
                valterMDao.createOrUpdate(mitValterM);
            }

            // 删除文件夹下的照片
            for (MitValpicM mTerminalinfoM : mitValpicMs) {

                String picname = mTerminalinfoM.getPicname();
                // 删除文件夹照片
                /*String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dbt/et.tsingtaopad" + "/photo/";
                FileUtil.deleteFile(new File(sdcardPath));*/
                // 删除原图
                FileUtil.deleteFile(new File(FileTool.CAMERA_PHOTO_DIR + picname));
            }

            for (MitValterM valterM : mitValterMs) {

                String valterId = valterM.getId();
                String terminalkey = valterM.getTerminalkey();

                // 删除追溯时的数据 包含追溯主表
                deleteZs(valterId, terminalkey, 0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //

    /**
     * 删除追溯时的数据 包含追溯主表
     *
     * @param valterId
     * @param terminalkey
     * @param type        0:上传成功后,通知删除(除本次外)  1:这条记录不上传了,通知全部删除
     */
    public void deleteZs(String valterId, String terminalkey, int type) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (0 == type) {
            // 删除追溯该终端(除本次外)其余的拜访记录
            deleteZsMitVisitM(db, "MIT_VALTER_M", terminalkey, valterId);
        } else if (1 == type) {
            String sql = "delete from MIT_VALTER_M  where id = '" + valterId + "'";
            db.execSQL(sql);
        }
        deleteZsTable(db, "MIT_VALCHECKTYPE_M", valterId);
        deleteZsTable(db, "MIT_VALCHECKITEM_M", valterId);
        deleteZsTable(db, "MIT_VALPROMOTIONS_M", valterId);
        deleteZsTable(db, "MIT_VALGROUPPRO_M", valterId);
        deleteZsTable(db, "MIT_VALSUPPLY_M", valterId);
        deleteZsTable(db, "MIT_VALCMP_M", valterId);
        deleteZsTable(db, "MIT_VALCMPOTHER_M", valterId);
        deleteZsTable(db, "MIT_VALPIC_M", valterId);
        deleteZsTable(db, "MIT_VALAGREE_M", valterId);
        deleteZsTable(db, "MIT_VALAGREEDETAIL_M", valterId);
    }


    // 删除协同拜访主表(条件: terminalkey相等 visitkey不相等 padisconsistent=1 )
    private void deleteMitVisitM(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {

        // 删除表记录
        String sql = "delete from " + tableName +
                "  where terminalkey = '" + terminalkey +
                "' and visitkey <> '" + visitkey + "'" +
                "  and  padisconsistent = 1 ";
        db.execSQL(sql);
    }

    // 根据VisitKey 删除记录
    private void deleteTableInfoByVisitKey(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {
        // 删除表记录
        String sql = "delete from " + tableName + "  where visitkey = '" + visitkey + "'";
        db.execSQL(sql);
    }

    // 根据Terminalkey 删除记录
    private void deleteTableInfoByTerminalkey(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {
        // 删除表记录
        String sql = "delete from " + tableName + "  where terminalkey = '" + terminalkey + "' and padisconsistent = '1'";
        db.execSQL(sql);
    }

    // 根据lowerkey 删除记录
    private void deleteTableInfoByLowerkey(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {
        // 删除表记录
        String sql = "delete from " + tableName + "  where lowerkey = '" + terminalkey + "' and padisconsistent = '1'";
        db.execSQL(sql);
    }

    // 根据Terminalkey 删除记录
    private void deleteXtTableInfoByTerminalkey(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {
        // 删除表记录
        String sql = "delete from " + tableName + "  where terminalkey = '" + terminalkey + "' ";
        db.execSQL(sql);
    }

    // 根据lowerkey 删除记录
    private void deleteXtTableInfoByLowerkey(SQLiteDatabase db, String tableName, String terminalkey, String visitkey) {
        // 删除表记录
        String sql = "delete from " + tableName + "  where lowerkey = '" + terminalkey + "' ";
        db.execSQL(sql);
    }


    // 删除追溯主表(条件: terminalkey相等 visitkey不相等 padisconsistent=1 )
    private void deleteZsMitVisitM(SQLiteDatabase db, String tableName, String terminalkey, String valterid) {

        // 删除表记录
        String sql = "delete from " + tableName +
                "  where terminalkey = '" + terminalkey +
                "' and id <> '" + valterid + "'" +
                "  and  padisconsistent = 1 ";
        db.execSQL(sql);
    }

    // 删除追溯表数据拜访主表(条件: terminalkey相等 visitkey不相等 padisconsistent=1 )
    private void deleteZsTable(SQLiteDatabase db, String tableName, String valterid) {

        // 删除表记录
        // 删除表记录
        String sql = "delete from " + tableName + "  where valterid = '" + valterid + "'";
        db.execSQL(sql);
    }

    // 上传督导新增终端
    public void uploadMitTerminalM(boolean isNeedExit, MitTerminalM mitTerminalM, int i) {

        List<MitTerminalM> mitTerminalMS = new ArrayList<MitTerminalM>();
        Map<String, String> childDatas = new HashMap<String, String>();

        try {
            //
            if (mitTerminalM != null) {
                mitTerminalMS.add(mitTerminalM);
            } else {
                Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                terminalKeyMap.put("uploadflag", "1");
                terminalKeyMap.put("padisconsistent", "0");
                mitTerminalMS = mitTerminalMDao.queryForFieldValues(terminalKeyMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (mitTerminalMS != null && !mitTerminalMS.isEmpty() && mitTerminalMS.size()>0) {

            childDatas.put("MIT_TERMINAL_M", JsonUtil.toJson(mitTerminalMS));
            List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
            mainDatas.add(childDatas);
            String json = JsonUtil.toJson(mainDatas);
            String optcode = "opt_save_mitterminalm";// 督导新增终端

            // 组建请求Json
            RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
            requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
            RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, json);

            // 压缩请求数据
            String jsonZip = HttpParseJson.parseRequestJson(reqObj);

            RestClient.builder()
                    .url(HttpUrl.IP_END)
                    .params("data", jsonZip)
                    //.loader(context)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            String json = HttpParseJson.parseJsonResToString(response);
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            if (TextUtils.isEmpty(json)) {
                                Toast.makeText(context, "返回数据为null", Toast.LENGTH_SHORT).show();
                            } else {
                                // 处理成功上传
                                if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                    //
                                    // 处理上传后的数据,该删删,该处理
                                    String formjson = resObj.getResBody().getContent();
                                    parseMitTerminalM(formjson);
                                    // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                                    Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                            Toast.makeText(context, "上传失败!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }
                    })
                    .builde()
                    .post();

        } else {
            if (isNeedExit) {
                //上传所有的巡店拜访
                //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
            }
        }
    }

    // 处理督导删除新增终端
    private void parseMitTerminalM(String formjson) {

        // 删除这条记录
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
        String mit_terminal_m = emp.getMIT_TERMINAL_M();
        List<MitTerminalM> mitTerminalMS = (List<MitTerminalM>) JsonUtil.parseList(mit_terminal_m, MitTerminalM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 处理督导删除新增终端
            for (MitTerminalM mitTerminalM : mitTerminalMS) {
                // 删除表记录
                String sql = "delete from  MIT_TERMINAL_M   where id = '" + mitTerminalM.getId() + "'";
                db.execSQL(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 上传 经销商开发核查表
    public void uploadMitValagencykfM(boolean isNeedExit, MitValagencykfM valagencykf, int i) {
        List<MitValagencykfM> valagencykfMS = new ArrayList<MitValagencykfM>();

        try {
            //
            if (valagencykf != null) {
                valagencykfMS.add(valagencykf);
            } else {
                Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                terminalKeyMap.put("uploadflag", "1");
                terminalKeyMap.put("padisconsistent", "0");
                valagencykfMS = mitValagencykfMDao.queryForFieldValues(terminalKeyMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (valagencykfMS != null && !valagencykfMS.isEmpty() && valagencykfMS.size()>0) {

            Map<String, String> childDatas = new HashMap<String, String>();
            childDatas.put("MIT_VALAGENCYKF_M", JsonUtil.toJson(valagencykfMS));
            List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
            mainDatas.add(childDatas);
            String json = JsonUtil.toJson(mainDatas);
            String optcode = "opt_save_mitvalagencykfm";// 经销商开发核查表

            // 组建请求Json
            RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
            requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
            RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, json);

            // 压缩请求数据
            String jsonZip = HttpParseJson.parseRequestJson(reqObj);


            RestClient.builder()
                    .url(HttpUrl.IP_END)
                    .params("data", jsonZip)
                    //.loader(context)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            String json = HttpParseJson.parseJsonResToString(response);
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            if (TextUtils.isEmpty(json)) {
                                Toast.makeText(context, "返回数据为null", Toast.LENGTH_SHORT).show();
                            } else {
                                // 处理成功上传
                                if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                    //
                                    // 处理上传后的数据,该删删,该处理
                                    String formjson = resObj.getResBody().getContent();
                                    parseMitValagencykfM(formjson);
                                    // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                                    Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                            Toast.makeText(context, "上传失败!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }
                    })
                    .builde()
                    .post();
        } else {
            if (isNeedExit) {
                //上传所有的巡店拜访
                //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
            }
        }
    }

    // 处理 经销商开发核查表
    private void parseMitValagencykfM(String formjson) {
        // 删除这条记录
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
        String Mit_Valagencykf_M = emp.getMIT_VALAGENCYKF_M();
        List<MitValagencykfM> valagencykfMS = (List<MitValagencykfM>) JsonUtil.parseList(Mit_Valagencykf_M, MitValagencykfM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 处理经销商开发核查表  删除
            for (MitValagencykfM mitTerminalM : valagencykfMS) {
                // 删除表记录
                String sql = "delete from  MIT_VALAGENCYKF_M   where id = '" + mitTerminalM.getId() + "'";
                db.execSQL(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 上传 督导 经销商库存盘点
    public void uploadMitAgencynumM(boolean isNeedExit, MitAgencynumM mitAgencynumM, List<MitAgencyproM> mitAgencyproMS, int i) {
        List<MitAgencynumM> agencynumMS = new ArrayList<MitAgencynumM>();
        List<MitAgencyproM> agencyproMS = new ArrayList<MitAgencyproM>();
        Map<String, String> childDatas = new HashMap<String, String>();

        try {
            //
            if (mitAgencynumM != null) {
                agencynumMS.add(mitAgencynumM);
                agencyproMS = mitAgencyproMS;
            } else {
                Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                terminalKeyMap.put("uploadflag", "1");
                terminalKeyMap.put("padisconsistent", "0");
                agencynumMS = mitAgencynumMDao.queryForFieldValues(terminalKeyMap);
                agencyproMS = mitAgencyproMDao.queryForFieldValues(terminalKeyMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (agencynumMS != null && !agencynumMS.isEmpty() && agencynumMS.size()>0) {

            childDatas.put("MIT_AGENCYNUM_M", JsonUtil.toJson(agencynumMS));
            childDatas.put("MIT_AGENCYPRO_M", JsonUtil.toJson(agencyproMS));
            List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
            mainDatas.add(childDatas);
            String json = JsonUtil.toJson(mainDatas);

            String optcode = "opt_save_mitagencynumm";// 督导 经销商库存盘点

            // 组建请求Json
            RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
            requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
            RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, json);

            // 压缩请求数据
            String jsonZip = HttpParseJson.parseRequestJson(reqObj);

            RestClient.builder()
                    .url(HttpUrl.IP_END)
                    .params("data", jsonZip)
                    //.loader(context)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            String json = HttpParseJson.parseJsonResToString(response);
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);

                            if (TextUtils.isEmpty(json)) {
                                Toast.makeText(context, "返回数据为null", Toast.LENGTH_SHORT).show();
                            } else {
                                // 处理成功上传
                                if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                    //
                                    // 处理上传后的数据,该删删,该处理
                                    String formjson = resObj.getResBody().getContent();
                                    parseMitAgencynumM(formjson);
                                    // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                                    Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                            Toast.makeText(context, "上传失败!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                            // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        }
                    })
                    .builde()
                    .post();
        } else {
            if (isNeedExit) {
                //上传所有的巡店拜访
                //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
            }
        }
    }

    // 处理 督导经销商库存盘点
    private void parseMitAgencynumM(String formjson) {

        // 删除这条记录
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
        String MIT_AGENCYNUM_M = emp.getMIT_AGENCYNUM_M();
        List<MitAgencynumM> agencynumMS = (List<MitAgencynumM>) JsonUtil.parseList(MIT_AGENCYNUM_M, MitAgencynumM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 处理经销商开发核查表  删除
            for (MitAgencynumM mitTerminalM : agencynumMS) {
                // 删除表记录
                String sql = "delete from  MIT_AGENCYNUM_M   where id = '" + mitTerminalM.getId() + "'";
                db.execSQL(sql);

                // 删除表记录
                String sqlid = "delete from  MIT_AGENCYPRO_M   where agencynumid = '" + mitTerminalM.getId() + "'";
                db.execSQL(sqlid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    List<MitPlanweekM> mitPlanweekMS = new ArrayList<MitPlanweekM>();
    List<MitPlandayM> mitPlandayMS = new ArrayList<MitPlandayM>();
    List<MitPlandaydetailM> plandaydetailMS = new ArrayList<MitPlandaydetailM>();
    List<MitPlandayvalM> plandayvalMS = new ArrayList<MitPlandayvalM>();

    // 上传 周计划
    public void uploadWeekPlan(final boolean isNeedExit, final String valterid, final int whatId) {
        try {
            MitPlanweekM mitValterM = null;
            if (valterid != null && !valterid.trim().equals("")) {
                mitValterM = mitPlanweekMDao.queryForId(valterid);

                if (mitValterM != null) {
                    mitPlanweekMS.add(mitValterM);
                    // mitPlanweekMS.add(mitValterM);

                    Map<String, Object> valteridMap = new HashMap<String, Object>();
                    valteridMap.put("planweekid", valterid);

                    mitPlandayMS = mitPlandayMDao.queryForFieldValues(valteridMap);
                    plandaydetailMS = mitPlandaydetailMDao.queryForFieldValues(valteridMap);
                    plandayvalMS = mitPlandayvalMDao.queryForFieldValues(valteridMap);

                }
            } else {
                // 只存放所有终端的最新一次拜访记录(分组)
                QueryBuilder<MitPlanweekM, String> visitQB = mitPlanweekMDao.queryBuilder();
                Where<MitPlanweekM, String> visitWhere = visitQB.where();
                visitWhere.eq("padisconsistent", "0");
                mitPlanweekMS = visitQB.query();

                if (mitPlanweekMS != null && !mitPlanweekMS.isEmpty()) {
                    for (MitPlanweekM valterM : mitPlanweekMS) {
                        mitPlandayMS.addAll(mitPlandayMDao.queryForEq("planweekid", "0"));
                        plandaydetailMS.addAll(mitPlandaydetailMDao.queryForEq("planweekid", "0"));
                        plandayvalMS.addAll(mitPlandayvalMDao.queryForEq("planweekid", "0"));
                    }
                }
            }

            if (mitPlanweekMS != null && !mitPlanweekMS.isEmpty()) {
                List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
                // 根据表结果组织数据关系
                for (MitPlanweekM valterM : mitPlanweekMS) {
                    Map<String, String> childDatas = new HashMap<String, String>();

                    String valtermid = valterM.getId();

                    // 1 周计划主表
                    List<MitPlanweekM> mstVisitMs = new ArrayList<MitPlanweekM>();
                    valterM.setPadisconsistent("1");
                    valterM.setStatus("2");// 0未制定1未提交2待审核3审核通过4未通过
                    mstVisitMs.add(valterM);
                    childDatas.put("MIT_PLANWEEK_M", JsonUtil.toJson(mstVisitMs));

                    // 2 日计划主表
                    List<MitPlandayM> childValchecktypeMs = new ArrayList<MitPlandayM>();
                    for (MitPlandayM mitValchecktypeM : mitPlandayMS) {
                        if (valtermid.equals(mitValchecktypeM.getPlanweekid())) {
                            mitValchecktypeM.setPadisconsistent("1");
                            childValchecktypeMs.add(mitValchecktypeM);
                        }
                    }
                    childDatas.put("MIT_PLANDAY_M", JsonUtil.toJson(childValchecktypeMs));

                    // 3 日计划详情表
                    List<MitPlandaydetailM> childValcheckitems = new ArrayList<MitPlandaydetailM>();
                    for (MitPlandaydetailM mitValcheckitemM : plandaydetailMS) {
                        if (valtermid.equals(mitValcheckitemM.getPlanweekid())) {
                            mitValcheckitemM.setPadisconsistent("1");
                            childValcheckitems.add(mitValcheckitemM);
                        }
                    }
                    childDatas.put("MIT_PLANDAYDETAIL_M", JsonUtil.toJson(childValcheckitems));

                    // 4 日计划详情表附表
                    List<MitPlandayvalM> childValpromotionsMs = new ArrayList<MitPlandayvalM>();
                    for (MitPlandayvalM mitValpromotionsM : plandayvalMS) {
                        if (valtermid.equals(mitValpromotionsM.getPlanweekid())) {
                            mitValpromotionsM.setPadisconsistent("1");
                            childValpromotionsMs.add(mitValpromotionsM);
                        }
                    }
                    childDatas.put("MIT_PLANDAYVAL_M", JsonUtil.toJson(childValpromotionsMs));

                    mainDatas.add(childDatas);
                }

                // 添加
                String json = JsonUtil.toJson(mainDatas);
                Log.i(TAG, "终端追溯send list size" + mainDatas.size() + json);

                upWeekPlanService("opt_save_ddplanweek", "", json);

            } else {
                if (isNeedExit) {
                    //上传所有的巡店拜访
                    //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 上传周计划
    private void upWeekPlanService(String optcode, String s, String json) {
        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, json);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        if ("".equals(json) || json == null) {
                            Toast.makeText(context, "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存登录信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                //
                                // 处理上传后的数据,该删删,该处理
                                String formjson = resObj.getResBody().getContent();
                                parseMitplanweekm(formjson);
                                /*ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);*/
                                ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);

                            } else {
                                Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        //Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                        ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                    }
                })
                .builde()
                .post();
    }

    private void parseMitplanweekm(String formjson) {

        // 删除这条记录
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
        String MIT_PLANWEEK_M = emp.getMIT_PLANWEEK_M();
        List<MitPlanweekM> mitPlanweekMS = (List<MitPlanweekM>) JsonUtil.parseList(MIT_PLANWEEK_M, MitPlanweekM.class);

        try {
            //DatabaseHelper helper = DatabaseHelper.getHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            // 处理周计划表
            for (MitPlanweekM mitPlanweekM : mitPlanweekMS) {
                mitPlanweekM.setPadisconsistent("1");// 上传成功
                mitPlanweekM.setUploadflag("1");// 确定上传
                mitPlanweekM.setStatus("2");// 0未制定1未提交2待审核3审核通过4未通过
                mitPlanweekMDao.createOrUpdate(mitPlanweekM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    List<MitRepairM> mitRepairMs = new ArrayList<MitRepairM>();
    List<MitRepaircheckM> mitRepaircheckMs = new ArrayList<MitRepaircheckM>();
    List<MitRepairterM> mitRepairterMs = new ArrayList<MitRepairterM>();

    // 上传整顿计划
    public void upload_repair(boolean isNeedExit, MitRepairM repairM, MitRepaircheckM mitRepaircheckM, int i) {
        try {
            //
            if (repairM != null) {
                mitRepairMs.add(repairM);

                Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                terminalKeyMap.put("repairid", repairM.getId());
                mitRepairterMs = mitRepairterMDao.queryForFieldValues(terminalKeyMap);
                mitRepaircheckMs = mitRepaircheckMDao.queryForFieldValues(terminalKeyMap);

            } else {
                Map<String, Object> terminalKeyMap = new HashMap<String, Object>();
                terminalKeyMap.put("uploadflag", "1");
                terminalKeyMap.put("padisconsistent", "0");
                mitRepairMs = mitRepairMDao.queryForFieldValues(terminalKeyMap);
                mitRepairterMs = mitRepairterMDao.queryForFieldValues(terminalKeyMap);
                mitRepaircheckMs = mitRepaircheckMDao.queryForFieldValues(terminalKeyMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (mitRepairMs != null && !mitRepairMs.isEmpty()) {
            List<Map<String, String>> mainDatas = new ArrayList<Map<String, String>>();
            // 根据表结果组织数据关系
            for (MitRepairM mitRepairM : mitRepairMs) {
                Map<String, String> childDatas = new HashMap<String, String>();
                String valtermid = mitRepairM.getId();

                // 1 整改计划主表
                List<MitRepairM> mitRepairMS = new ArrayList<MitRepairM>();
                mitRepairM.setPadisconsistent("1");
                mitRepairMS.add(mitRepairM);
                childDatas.put("MIT_REPAIR_M", JsonUtil.toJson(mitRepairMS));

                // 2 整改计划核查表
                List<MitRepaircheckM> mitRepaircheckMS = new ArrayList<MitRepaircheckM>();
                for (MitRepaircheckM repaircheckM : mitRepaircheckMs) {
                    if (valtermid.equals(repaircheckM.getRepairid())) {
                        repaircheckM.setPadisconsistent("1");
                        mitRepaircheckMS.add(repaircheckM);
                    }
                }
                childDatas.put("MIT_REPAIRCHECK_M", JsonUtil.toJson(mitRepaircheckMS));

                // 3 整改计划 终端表
                List<MitRepairterM> mitRepairterMS = new ArrayList<MitRepairterM>();
                for (MitRepairterM mitRepairterM : mitRepairterMs) {
                    if (valtermid.equals(mitRepairterM.getRepairid())) {
                        mitRepairterM.setPadisconsistent("1");
                        mitRepairterMS.add(mitRepairterM);
                    }
                }
                childDatas.put("MIT_REPAIRTER_M", JsonUtil.toJson(mitRepairterMS));
                mainDatas.add(childDatas);
            }

            // 添加
            String json = JsonUtil.toJson(mainDatas);
            //FileUtil.writeTxt(json,FileUtil.getSDPath()+"/shopvisit1016.txt");//上传巡店拜访的json
            // System.out.println("巡店拜访"+json);
            Log.i(TAG, "整改计划send list size" + mainDatas.size() + json);

            upRepairService("opt_save_mitrepairm", "", json);

        } else {
            if (isNeedExit) {
                //上传所有的巡店拜访
                //handler.sendEmptyMessage(TitleLayout.UPLOAD_DATA);
            }
        }
    }

    private void upRepairService(String optcode, String s, String json) {
        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, json);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);

                        if (TextUtils.isEmpty(json)) {
                            Toast.makeText(context, "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            // 处理成功上传
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                //
                                // 处理上传后的数据,该删删,该处理
                                String formjson = resObj.getResBody().getContent();
                                parseRepair(formjson);
                                // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_SUC);
                                Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                        Toast.makeText(context, "上传失败!", Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                        // ConstValues.handler.sendEmptyMessage(GlobalValues.SINGLE_UP_FAIL);
                    }
                })
                .builde()
                .post();
    }

    private void parseRepair(String formjson) {
        try {
            SQLiteDatabase db = helper.getWritableDatabase();

            // 处理整改计划主表
            for (MitRepairM mitRepairM : mitRepairMs) {
                mitRepairM.setPadisconsistent("1");// 上传成功
                mitRepairM.setUploadflag("1");// 确定上传
                mitRepairMDao.createOrUpdate(mitRepairM);
            }
            // 处理 整改计划终端表
            for (MitRepairterM mitRepairterM : mitRepairterMs) {
                mitRepairterM.setPadisconsistent("1");// 上传成功
                mitRepairterM.setUploadflag("1");// 确定上传
                mitRepairterMDao.createOrUpdate(mitRepairterM);
            }
            // 处理 整改计划审核表
            for (MitRepaircheckM mitRepaircheckM : mitRepaircheckMs) {
                mitRepaircheckM.setPadisconsistent("1");// 上传成功
                mitRepaircheckM.setUploadflag("1");// 确定上传
                mitRepaircheckMDao.createOrUpdate(mitRepaircheckM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
