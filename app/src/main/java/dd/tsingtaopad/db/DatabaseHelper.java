package dd.tsingtaopad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.db.table.*;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "FsaDBT.db";
    private static final int DATABASE_VERSION = 50;

    private Dao<CmmAreaM, String> cmmAreaMDao = null;
    private Dao<CmmDatadicM, String> cmmDatadicMDao = null;
    private Dao<MstAgencygridInfo, String> mstAgencygridInfoDao = null;
    private Dao<MstAgencyinfoM, String> mstAgencyinfoMDao = null;
    private Dao<MstAgencysupplyInfo, String> mstAgencysupplyInfoDao = null;
    private Dao<MstAgencysupplyInfoTemp, String> mstAgencysupplyInfoTempDao = null;
    private Dao<MitAgencysupplyInfo, String> mitAgencysupplyInfoDao = null;
    private Dao<MitValcmpM, String> mitValcmpMDao = null;
    private Dao<MitValcheckterM, String> mitValcheckterMDao = null;
    private Dao<MitValcmpMTemp, String> mitValcmpMTempDao = null;
    private Dao<MitValgroupproM, String> mitValgroupproMDao = null;
    private Dao<MitValgroupproMTemp, String> mitValgroupproMTempDao = null;
    private Dao<MitValpicM, String> mitValpicMDao = null;
    private Dao<MitValpicMTemp, String> mitValpicMTempDao = null;
    private Dao<MitValcmpotherM, String> mitValcmpotherMDao = null;
    private Dao<MitValcmpotherMTemp, String> mitValcmpotherMTempDao = null;
    private Dao<MitValcheckitemM, String> mitValcheckitemMDao = null;
    private Dao<MitValcheckitemMTemp, String> mitValcheckitemMTempDao = null;
    private Dao<MitValchecktypeM, String> mitValchecktypeMDao = null;
    private Dao<MitValchecktypeMTemp, String> mitValchecktypeMTempDao = null;
    private Dao<MitValpromotionsM, String> mitValpromotionsMDao = null;
    private Dao<MitValpromotionsMTemp, String> mitValpromotionsMTempDao = null;
    private Dao<MstAgencytransferInfo, String> mstAgencytransferInfoDao = null;
    private Dao<MstAgencyvisitM, String> mstAgencyvisitMDao = null;
    private Dao<MstBrandsclassM, String> mstBrandsclassMDao = null;
    private Dao<MstBrandseriesM, String> mstBrandseriesMDao = null;
    private Dao<MstCenterdetailsM, String> mstCenterdetailsMDao = null;
    private Dao<MstCenterM, String> mstCenterMDao = null;
    private Dao<MstCheckaccomplishInfo, String> mstCheckaccomplishInfoDao = null;
    private Dao<MstCheckcollectionInfo, String> mstCheckcollectionInfoDao = null;
    private Dao<MstCheckexerecordInfo, String> mstCheckexerecordInfoDao = null;
    private Dao<MstCheckexerecordInfoTemp, String> mstCheckexerecordInfoTempDao = null;
    private Dao<MitCheckexerecordInfo, String> mitCheckexerecordInfoDao = null;
    private Dao<MstCheckstatusInfo, String> mstCheckstatusInfoDao = null;
    private Dao<MstChecktypeM, String> mstChecktypeMDao = null;
    private Dao<MstCmpareaInfo, String> mstCmpareaInfoDao = null;
    private Dao<MstCmpbrandsM, String> mstCmpbrandsMDao = null;
    private Dao<MstCmpcompanyM, String> mstCmpcompanyMDao = null;
    private Dao<MstCmproductinfoM, String> mstCmproductinfoMDao = null;
    private Dao<MstCollectionitemM, String> mstCollectionitemMDao = null;
    private Dao<MstGridM, String> mstGridMDao = null;
    private Dao<MstInvalidapplayInfo, String> mstInvalidapplayInfoDao = null;
    private Dao<MstInvoicingInfo, String> mstInvoicingInfoDao = null;
    private Dao<MstMarketareaM, String> mstMarketareaMDao = null;
    private Dao<MstPricedetailsInfo, String> mstPricedetailsInfoDao = null;
    private Dao<MstPriceM, String> mstPriceMDao = null;
    private Dao<MstProductareaInfo, String> mstProductareaInfoDao = null;
    private Dao<MstProductM, String> mstProductMDao = null;
    private Dao<MstPromoproductInfo, String> mstPromoproductInfoDao = null;
    private Dao<MstPromotermInfo, String> mstPromotermInfoDao = null;
    private Dao<MstPromotermInfoTemp, String> mstPromotermInfoTempDao = null;
    private Dao<MitPromotermInfo, String> mitPromotermInfoDao = null;
    private Dao<MstPromotionsM, String> mstPromotionsMDao = null;
    private Dao<MstPromotionstypeM, String> mstPromotionstypeMDao = null;
    private Dao<MstRouteM, String> mstRouteMDao = null;
    private Dao<MstShipmentledgerInfo, String> mstShipmentledgerInfoDao = null;
    private Dao<MstTerminalinfoM, String> mstTerminalinfoMDao = null;
    private Dao<MstTerminalinfoMTemp, String> mstTerminalinfoMTempDao = null;
    private Dao<MitTerminalinfoM, String> mitTerminalinfoMDao = null;
    private Dao<MstTerminalinfoMCart, String> mstTerminalinfoMCartDao = null;
    private Dao<MstTerminalinfoMZsCart, String> mstTerminalinfoMZsCartDao = null;
    private Dao<MstVisitauthorizeInfo, String> mstVisitauthorizeInfoDao = null;
    private Dao<MstVisitM, String> mstVisitMDao = null;
    private Dao<MitValterM, String> mitValterMDao = null;
    private Dao<MitValsupplyM, String> mitValsupplyMDao = null;
    private Dao<MitValsupplyMTemp, String> mitValsupplyMTempDao = null;
    private Dao<MitValterMTemp, String> mitValterMTempDao = null;
    private Dao<MitVisitM, String> mitVisitMDao = null;
    private Dao<MstVisitMTemp, String> mstVisitMTempDao = null;
    private Dao<MstVisitmemoInfo, String> mstVisitmemoInfoDao = null;
    private Dao<MstVistproductInfo, String> mstVistproductInfoDao = null;
    private Dao<MstVistproductInfoTemp, String> mstVistproductInfoTempDao = null;
    private Dao<MitVistproductInfo, String> mitVistproductInfoDao = null;
    private Dao<MstWorksummaryInfo, String> mstWorksummaryInfoDao = null;
    private Dao<MstSynckvM, String> mstSynckvMDao = null;
    private Dao<MstQuestionsanswersInfo, String> mstQuestionsanswersInfoDao = null;
    private Dao<MstCollectionexerecordInfo, String> mstCollectionexerecordInfoDao = null;
    private Dao<MstCollectionexerecordInfoTemp, String> mstCollectionexerecordInfoTempDao = null;
    private Dao<MitCollectionexerecordInfo, String> mitCollectionexerecordInfoDao = null;
    private Dao<MstPlanforuserM, String> mstPlanforuserMDao = null;
    private Dao<PadChecktypeM, String> padChecktypeMDao = null;
    private Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao = null;
    private Dao<PadCheckstatusInfo, String> padCheckstatusInfoDao = null;
    private Dao<PadPlantempcheckM, String> padPlantempcheckMDao = null;
    private Dao<PadPlantempcollectionInfo, String> padPlantempcollectionInfoDao = null;
    private Dao<MstCheckmiddleInfo, String> mstCheckmiddleInfoDao = null;

    private Dao<MstPlantemplateM, String> mstPlantemplateMDao = null;
    private Dao<MstPlantempcheckInfo, String> mstPlantempcheckInfoDao = null;
    private Dao<MstPlantempcollectionInfo, String> mstPlantempcollectionInfoDao = null;
    private Dao<MstPlancheckInfo, String> mstPlancheckInfoDao = null;
    private Dao<MstPlancollectionInfo, String> mstPlancollectionInfoDao = null;
    private Dao<CmmBoardM, String> cmmBoardMDao = null;
    private Dao<PadCheckproInfo, String> padCheckproInfoDao = null;
    private Dao<MstPromomiddleInfo, String> mstPromomiddleInfoDao = null;
    private Dao<MstPowerfulchannelInfo, String> mstPowerfulchannelInfoDao = null;
    private Dao<MstPowerfulterminalInfo, String> mstPowerfulterminalInfoDao = null;
    private Dao<MstCmpsupplyInfo, String> mstCmpsupplyInfoDao = null;
    private Dao<MstCmpsupplyInfoTemp, String> mstCmpsupplyInfoTempDao = null;
    private Dao<MitCmpsupplyInfo, String> mitCmpsupplyInfoDao = null;
    private Dao<MstPlanrouteInfo, String> mstPlanrouteInfoDao = null;
    private Dao<MstMonthtargetInfo, String> mstMonthtargetInfoDao = null;
    private Dao<MstCmpagencyInfo, String> mstCmpagencyInfo = null;
    private Dao<MstProductshowM, String> mstProductshowMDao = null;
    private Dao<MstShowpicInfo, String> mstShowpicInfoDao = null;
    private Dao<MstPlanWeekforuserM, String> mstPlanWeekforuserMDao = null;
    private Dao<MstCameraInfoM, String> mstCameraiInfoMDao = null;
    private Dao<MstCameraInfoMTemp, String> mstCameraiInfoMTempDao = null;
    private Dao<MitCameraInfoM, String> mitCameraInfoMDao = null;
    private Dao<MstPictypeM, String> mstpictypeMDao = null;
    private Dao<MstAgencyKFM, String> mstAgencyKFMDao = null;
    private Dao<MstTermLedgerInfo, String> mstTermLedgerInfoDao = null;
    private Dao<MstPlanTerminalM, String> mstPlanTerminalMDao = null;
    private Dao<MstBsData, String> mstBsDataDao = null;
    private Dao<MstGroupproductM, String> mstGroupproductMDao = null;
    private Dao<MstGroupproductMTemp, String> mstGroupproductMTempDao = null;
    private Dao<MitGroupproductM, String> mitGroupproductMDao = null;

    private Dao<MitAgencynumM, String> mitAgencynumMDao = null;
    private Dao<MitAgencyproM, String> mitAgencyproMDao = null;
    private Dao<MitTerminalM, String> mitTerminalMDao = null;
    private Dao<MitValagencykfM, String> mitValagencykfMDao = null;

    private Dao<MitValaddaccountMTemp, String> mitValaddaccountMTempDao = null;
    private Dao<MitValaddaccountM, String> mitValaddaccountMDao = null;
    private Dao<MitValaddaccountproMTemp, String> mitValaddaccountproMTempDao = null;
    private Dao<MitValaddaccountproM, String> mitValaddaccountproMDao = null;

    private Dao<MitPlanweekM, String> mitPlanweekMDao = null;
    private Dao<MitPlandayM, String> mitPlandayMDao = null;
    private Dao<MitPlandaydetailM, String> mitPlandaydetailMDao = null;
    private Dao<MitPlandayvalM, String> mitPlandayvalMDao = null;

    private Dao<MitRepairM, String> mitRepairMDao = null;
    private Dao<MitRepairterM, String> mitRepairterMDao = null;
    private Dao<MitRepaircheckM, String> mitRepaircheckMDao = null;


    private Dao<MstAgreeTmp, String> mstAgreeTmpDao = null;
    private Dao<MstAgreeDetailTmp, String> mstAgreeDetailTmpDao = null;
    private Dao<MitValagreeM, String> mitValagreeMDao = null;
    private Dao<MitValagreeMTemp, String> mitValagreeMTempDao = null;
    private Dao<MitValagreedetailM, String> mitValagreedetailMDao = null;
    private Dao<MitValagreedetailMTemp, String> mitValagreedetailMTempDao = null;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DatabaseHelper helper = null;

    public static synchronized DatabaseHelper getHelper(Context context) {

        if (helper == null) {
            helper = new DatabaseHelper(context);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        try {
            long first = System.currentTimeMillis();
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, CmmAreaM.class);
            TableUtils.createTable(connectionSource, CmmDatadicM.class);
            TableUtils.createTable(connectionSource, MstAgencygridInfo.class);
            TableUtils.createTable(connectionSource, MstAgencyinfoM.class);
            TableUtils.createTable(connectionSource, MstAgencysupplyInfo.class);
            TableUtils.createTable(connectionSource, MstAgencytransferInfo.class);
            TableUtils.createTable(connectionSource, MstAgencyvisitM.class);
            TableUtils.createTable(connectionSource, MstBrandsclassM.class);
            TableUtils.createTable(connectionSource, MstBrandseriesM.class);
            TableUtils.createTable(connectionSource, MstCenterdetailsM.class);
            TableUtils.createTable(connectionSource, MstCenterM.class);
            TableUtils.createTable(connectionSource, MstCheckaccomplishInfo.class);
            TableUtils.createTable(connectionSource, MstCheckcollectionInfo.class);
            //			TableUtils.createTable(connectionSource, MstCheckexerecordInfo.class);
            CreateMstCheckexerecordInfoTable(db);
            TableUtils.createTable(connectionSource, MstCheckexerecordInfoTemp.class);
            TableUtils.createTable(connectionSource, MstCheckstatusInfo.class);
            TableUtils.createTable(connectionSource, MstChecktypeM.class);
            TableUtils.createTable(connectionSource, MstCmpareaInfo.class);
            TableUtils.createTable(connectionSource, MstCmpbrandsM.class);
            TableUtils.createTable(connectionSource, MstCmpcompanyM.class);
            TableUtils.createTable(connectionSource, MstCmproductinfoM.class);
            TableUtils.createTable(connectionSource, MstCollectionitemM.class);
            TableUtils.createTable(connectionSource, MstGridM.class);
            TableUtils.createTable(connectionSource, MstInvalidapplayInfo.class);
            TableUtils.createTable(connectionSource, MstInvoicingInfo.class);
            TableUtils.createTable(connectionSource, MstMarketareaM.class);
            TableUtils.createTable(connectionSource, MstPricedetailsInfo.class);
            TableUtils.createTable(connectionSource, MstPriceM.class);
            TableUtils.createTable(connectionSource, MstProductareaInfo.class);
            TableUtils.createTable(connectionSource, MstProductM.class);
            TableUtils.createTable(connectionSource, MstPromoproductInfo.class);
            TableUtils.createTable(connectionSource, MstPromotermInfo.class);
            TableUtils.createTable(connectionSource, MstPromotionsM.class);
            TableUtils.createTable(connectionSource, MstPromotionstypeM.class);
            TableUtils.createTable(connectionSource, MstRouteM.class);
            TableUtils.createTable(connectionSource, MstShipmentledgerInfo.class);
            TableUtils.createTable(connectionSource, MstTerminalinfoM.class);
            TableUtils.createTable(connectionSource, MstVisitauthorizeInfo.class);
            TableUtils.createTable(connectionSource, MstVisitM.class);
            TableUtils.createTable(connectionSource, MstVisitmemoInfo.class);
            TableUtils.createTable(connectionSource, MstVistproductInfo.class);
            TableUtils.createTable(connectionSource, MstWorksummaryInfo.class);
            TableUtils.createTable(connectionSource, MstSynckvM.class);
            TableUtils.createTable(connectionSource, MstQuestionsanswersInfo.class);
            TableUtils.createTable(connectionSource, MstCollectionexerecordInfo.class);
            TableUtils.createTable(connectionSource, MstPlanforuserM.class);
            TableUtils.createTable(connectionSource, PadCheckaccomplishInfo.class);
            TableUtils.createTable(connectionSource, PadCheckstatusInfo.class);
            TableUtils.createTable(connectionSource, PadChecktypeM.class);
            TableUtils.createTable(connectionSource, PadPlantempcheckM.class);
            TableUtils.createTable(connectionSource, PadPlantempcollectionInfo.class);
            TableUtils.createTable(connectionSource, MstPlantemplateM.class);
            TableUtils.createTable(connectionSource, MstPlantempcheckInfo.class);
            TableUtils.createTable(connectionSource, MstPlantempcollectionInfo.class);
            TableUtils.createTable(connectionSource, MstPlancheckInfo.class);
            TableUtils.createTable(connectionSource, MstPlancollectionInfo.class);
            TableUtils.createTable(connectionSource, CmmBoardM.class);
            TableUtils.createTable(connectionSource, MstCheckmiddleInfo.class);
            TableUtils.createTable(connectionSource, PadCheckproInfo.class);
            TableUtils.createTable(connectionSource, MstPromomiddleInfo.class);
            TableUtils.createTable(connectionSource, MstPowerfulchannelInfo.class);
            TableUtils.createTable(connectionSource, MstPowerfulterminalInfo.class);
            TableUtils.createTable(connectionSource, MstCmpsupplyInfo.class);
            TableUtils.createTable(connectionSource, MstPlanrouteInfo.class);
            TableUtils.createTable(connectionSource, MstMonthtargetInfo.class);
            TableUtils.createTable(connectionSource, MstCmpagencyInfo.class);
            TableUtils.createTable(connectionSource, MstProductshowM.class);
            TableUtils.createTable(connectionSource, MstShowpicInfo.class);
            TableUtils.createTable(connectionSource, MstPlanWeekforuserM.class);
            TableUtils.createTable(connectionSource, MstCameraInfoM.class);// 图片表
            TableUtils.createTable(connectionSource, MstPictypeM.class);// 拍照图片类型
            TableUtils.createTable(connectionSource, MstAgencyKFM.class);// 经销商开发
            TableUtils.createTable(connectionSource, MstTermLedgerInfo.class);// 终端台账
            TableUtils.createTable(connectionSource, MstPlanTerminalM.class);// 计划终端表
            TableUtils.createTable(connectionSource, MstBsData.class);// 流量表
            TableUtils.createTable(connectionSource, MstGroupproductM.class);// 产品组合是否达标表


            TableUtils.createTable(connectionSource, MstTerminalinfoMTemp.class);// 终端表 临时表

            TableUtils.createTable(connectionSource, MstAgencysupplyInfoTemp.class);// 我品供货关系表 临时表
            TableUtils.createTable(connectionSource, MstVistproductInfoTemp.class);// 拜访产品表 临时表
            TableUtils.createTable(connectionSource, MstCmpsupplyInfoTemp.class);// 竞品供货关系表 临时表
            TableUtils.createTable(connectionSource, MstCollectionexerecordInfoTemp.class);// 采集项表 临时表
            TableUtils.createTable(connectionSource, MstPromotermInfoTemp.class);// 促销活动表 临时表
            TableUtils.createTable(connectionSource, MstCameraInfoMTemp.class);// 图片表 临时表
            TableUtils.createTable(connectionSource, MstVisitmemoInfoTemp.class);// 客情备忘表 临时表
            TableUtils.createTable(connectionSource, MstGroupproductMTemp.class);// 产品组合是否达标 临时表
            //TableUtils.createTable(connectionSource, MstGroupproductM.class);// 组合表 临时表

            TableUtils.createTable(connectionSource, MstVisitMTemp.class);// 拜访表 临时表
            TableUtils.createTable(connectionSource, MstTerminalinfoMCart.class);// 协同终端购物车
            TableUtils.createTable(connectionSource, MstTerminalinfoMZsCart.class);// 追溯终端购物车


            TableUtils.createTable(connectionSource, MitVisitM.class);// 协同拜访表
            TableUtils.createTable(connectionSource, MitAgencysupplyInfo.class);// 协同我品供货关系
            TableUtils.createTable(connectionSource, MitCameraInfoM.class);// 协同照片
            TableUtils.createTable(connectionSource, MitCmpsupplyInfo.class);// 协同竞品供货关系
            TableUtils.createTable(connectionSource, MitCollectionexerecordInfo.class);// 协同采集项表
            TableUtils.createTable(connectionSource, MitGroupproductM.class);// 协同产品组合
            TableUtils.createTable(connectionSource, MitPromotermInfo.class);// 协同活动终端表
            TableUtils.createTable(connectionSource, MitTerminalinfoM.class);// 协同终端表
            TableUtils.createTable(connectionSource, MitVisitmemoInfo.class);// 协同客情备忘
            TableUtils.createTable(connectionSource, MitVistproductInfo.class);// 协同竞品我品表
            TableUtils.createTable(connectionSource, MitCheckexerecordInfo.class);// 协同拉链表

            TableUtils.createTable(connectionSource, MitValterM.class);// 追溯主表
            TableUtils.createTable(connectionSource, MitValterMTemp.class);// 追溯主表临时表

            TableUtils.createTable(connectionSource, MitValcheckterM.class);// 追溯指标配置

            TableUtils.createTable(connectionSource, MitValsupplyM.class);// 追溯进销存表
            TableUtils.createTable(connectionSource, MitValsupplyMTemp.class);// 追溯进销存表临时表

            TableUtils.createTable(connectionSource, MitValcmpM.class);// 追溯聊竞品表
            TableUtils.createTable(connectionSource, MitValcmpMTemp.class);// 追溯聊竞品表 临时表

            TableUtils.createTable(connectionSource, MitValpromotionsM.class);// 追溯促销活动终端表
            TableUtils.createTable(connectionSource, MitValpromotionsMTemp.class);// 追溯促销活动终端表 临时表

            TableUtils.createTable(connectionSource, MitValchecktypeM.class);// 追溯拉链表表
            TableUtils.createTable(connectionSource, MitValchecktypeMTemp.class);// 追溯拉链表表 临时表

            TableUtils.createTable(connectionSource, MitValcheckitemM.class);// 追溯拉链表表
            TableUtils.createTable(connectionSource, MitValcheckitemMTemp.class);// 追溯拉链表表 临时表

            TableUtils.createTable(connectionSource, MitValcmpotherM.class);// 终端追溯竞品附表
            TableUtils.createTable(connectionSource, MitValcmpotherMTemp.class);// 终端追溯竞品附表 临时表

            TableUtils.createTable(connectionSource, MitValpicM.class);// 终端追溯图片
            TableUtils.createTable(connectionSource, MitValpicMTemp.class);// 终端追溯图片表 临时表

            TableUtils.createTable(connectionSource, MitValgroupproM.class);// 终端产品组合表
            TableUtils.createTable(connectionSource, MitValgroupproMTemp.class);// 终端产品组合表 临时表

            TableUtils.createTable(connectionSource, MitAgencynumM.class);// 经销商库存盘点主表
            TableUtils.createTable(connectionSource, MitAgencyproM.class);// 经销商判断产品表
            TableUtils.createTable(connectionSource, MitTerminalM.class);// 督导新增终端表
            TableUtils.createTable(connectionSource, MitValagencykfM.class);// 经销商开发核查表

            TableUtils.createTable(connectionSource, MitValaddaccountM.class);// 终端进货台账主表
            TableUtils.createTable(connectionSource, MitValaddaccountMTemp.class);// 终端进货台账主表 临时表
            TableUtils.createTable(connectionSource, MitValaddaccountproM.class);// 终端追溯台账产品详情表
            TableUtils.createTable(connectionSource, MitValaddaccountproMTemp.class);// 终端追溯台账产品详情表 临时表

            TableUtils.createTable(connectionSource, MitPlanweekM.class);// 周计划主表
            TableUtils.createTable(connectionSource, MitPlandayM.class);// 日计划主表
            TableUtils.createTable(connectionSource, MitPlandaydetailM.class);// 详细日计划
            TableUtils.createTable(connectionSource, MitPlandayvalM.class);// 日计划追溯项主表

            TableUtils.createTable(connectionSource, MitRepairM.class);// 整改计划主表
            TableUtils.createTable(connectionSource, MitRepairterM.class);// 整改计划终端表
            TableUtils.createTable(connectionSource, MitRepaircheckM.class);// 整改计划审核表

            TableUtils.createTable(connectionSource, MstAgreeTmp.class);// 协议主表
            TableUtils.createTable(connectionSource, MstAgreeDetailTmp.class);// 协议产品表

            TableUtils.createTable(connectionSource, MitValagreeM.class);// 终端追溯协议主表
            TableUtils.createTable(connectionSource, MitValagreeMTemp.class);// 终端追溯协议主表 临时表
            TableUtils.createTable(connectionSource, MitValagreedetailM.class);// 终端追溯协议对付信息表
            TableUtils.createTable(connectionSource, MitValagreedetailMTemp.class);// 终端追溯协议对付信息表 临时表


            this.initView(db);
            this.initData(db);

            Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + (System.currentTimeMillis() - first));
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化视图
     */
    public void initView(SQLiteDatabase db) {

        // 获取各终端     每天    最新的拜访记录
        StringBuffer buffer = new StringBuffer();
        buffer.append("drop view if exists v_visit_m");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_visit_m as ");
        buffer.append("select m.* from mst_visit_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mst_visit_m where visitdate is not null group by terminalkey, substr(visitdate,1,8)) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());

        // 获取各终端    所有    拜访数据中最新拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_visit_m_newest");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_visit_m_newest as ");
        buffer.append("select m.* from mst_visit_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mst_visit_m where visitdate is not null group by terminalkey) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());

        // 获取经销商拜访每天最新的拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_agencyvisit_m");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_agencyvisit_m as ");
        buffer.append("select m.* from mst_agencyvisit_m m  ");
        buffer.append("inner join (select max(agevisitdate) maxagevisitdate, ");
        buffer.append("    max(agencykey) maxagencykey ");
        buffer.append("    from mst_agencyvisit_m group by agencykey, substr(agevisitdate,1,8)) v ");
        buffer.append("  on m.agencykey = v.maxagencykey  ");
        buffer.append("    and m.agevisitdate = v.maxagevisitdate");
        db.execSQL(buffer.toString());

        // 获取经销商拜访最新的拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_agencyvisit_m_newest");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_agencyvisit_m_newest as ");
        buffer.append("select m.* from mst_agencyvisit_m m  ");
        buffer.append("inner join (select max(agevisitdate) maxagevisitdate, ");
        buffer.append("    max(agencykey) maxagencykey ");
        buffer.append("    from mst_agencyvisit_m group by agencykey) v ");
        buffer.append("  on m.agencykey = v.maxagencykey  ");
        buffer.append("    and m.agevisitdate = v.maxagevisitdate");
        db.execSQL(buffer.toString());

        // V_AGENCYSELPRODUCT_INFO(经销商可以销售产品)
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_agencyselproduct_info");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_agencyselproduct_info as ");
        buffer.append("select distinct am.agencykey, am.agencyname, pd.productkey, pdm.proname, pm.startdate, pm.enddate ");
        buffer.append("from mst_agencyinfo_m am, mst_price_m pm, mst_pricedetails_info pd, mst_productarea_info pa, mst_product_m pdm ");
        buffer.append("where am.pricekey = pm.pricekey and am.pricekey = pd.pricekey and pd.productkey = pa.productkey ");
        buffer.append("and pa.productkey = pdm.productkey and coalesce(pa.status, '0') != '1' and coalesce(am.agencystatus,'0') != '1' ");
        buffer.append("and coalesce(am.deleteflag,'0') != '1' and coalesce(pm.deleteflag,'0') != '1' and coalesce(pd.deleteflag,'0') != '1' ");
        buffer.append("and coalesce(pa.deleteflag,'0') != '1' and coalesce(pdm.deleteflag,'0') != '1' ");
        db.execSQL(buffer.toString());

        //v_pd_check_result (日工作明细查询指标  ---- 弃用20140428)
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_pd_check_result");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_pd_check_result as select p.visitkey, p.productkey,");
        buffer.append("max(case when p.checkkey = '666b74b3-b221-4920-b549-d9ec39a463fd' then i.cstatusname else null end) hz,"); //合作执行是否到位
        buffer.append("max(case when p.checkkey = 'df2e88c9-246f-40e2-b6e5-08cdebf8c281' then i.cstatusname else null end) ps,"); //是否高质量配送
        buffer.append("max(case when p.checkkey = '59802090-02ac-4146-9cc3-f09570c36a26' then i.cstatusname else null end) zy,"); //单店占有率
        buffer.append("max(case when p.checkkey = 'ad3030fb-e42e-47f8-a3ec-4229089aab5d' then i.cstatusname else null end) ph,"); //铺货
        buffer.append("max(case when p.checkkey = 'ad3030fb-e42e-47f8-a3ec-4229089aab6d' then i.cstatusname else null end) dj, "); //道具生动化
        buffer.append("max(case when p.checkkey = 'ad3030fb-e42e-47f8-a3ec-4229089aab7d' then i.cstatusname else null end) cp  "); //产品生动化
        buffer.append("from MST_CHECKEXERECORD_INFO p, PAD_CHECKSTATUS_INFO i ");
        buffer.append("where p.checkkey = i.checkkey and p.acresult = i.cstatuskey ");
        buffer.append("   and p.deleteflag!='" + ConstValues.delFlag + "' ");
        buffer.append("group by p.visitkey,p.productkey");
        db.execSQL(buffer.toString());

        // pad端采集项
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_pad_checkaccomplish_info");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_pad_checkaccomplish_info as ");
        buffer.append("select distinct p.colitemkey, p.colitemname from pad_checkaccomplish_info p ");
        db.execSQL(buffer.toString());

        // 获取各终端     每天    协同拜访的最新的拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_mit_visit_m");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_mit_visit_m as ");
        buffer.append("select m.* from mit_visit_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mit_visit_m where visitdate is not null group by terminalkey, substr(visitdate,1,8)) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());

        // 获取各终端    所有    协同拜访的拜访数据中最新拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_mit_visit_m_newest");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_mit_visit_m_newest as ");
        buffer.append("select m.* from mit_visit_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mit_visit_m where visitdate is not null group by terminalkey) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());

        // 获取各终端     每天    终端追溯的最新的拜访记录
        //StringBuffer buffer = new StringBuffer();
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_mit_valter_m");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_mit_valter_m as ");
        buffer.append("select m.* from mit_valter_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mit_valter_m where visitdate is not null group by terminalkey, substr(visitdate,1,8)) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());

        // 获取各终端    所有    终端追溯的拜访数据中最新拜访记录
        buffer = new StringBuffer();
        buffer.append("drop view if exists v_mit_valter_m_newest");
        db.execSQL(buffer.toString());
        buffer = new StringBuffer();
        buffer.append("create view IF NOT EXISTS v_mit_valter_m_newest as ");
        buffer.append("select m.* from mit_valter_m m  ");
        buffer.append("inner join (select max(visitdate) maxvisitdate, ");
        buffer.append("    max(terminalkey) maxterminalkey ");
        buffer.append("    from mit_valter_m where visitdate is not null group by terminalkey) v ");
        buffer.append("  on m.terminalkey = v.maxterminalkey  ");
        buffer.append("    and m.visitdate = v.maxvisitdate");
        db.execSQL(buffer.toString());
    }

    /**
     *
     */
    public void CreateMstCheckexerecordInfoTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE  IF NOT EXISTS MST_CHECKEXERECORD_INFO " + "(recordkey  VARCHAR primary key not null,visitkey VARCHAR,productkey VARCHAR,checkkey VARCHAR,checktype VARCHAR,acresult VARCHAR,iscom VARCHAR,cstatuskey VARCHAR,isauto VARCHAR,exestatus VARCHAR,startdate VARCHAR,enddate VARCHAR,terminalkey VARCHAR,sisconsistent VARCHAR,scondate timestamp,siebelid VARCHAR,resultstatus VARCHAR,padisconsistent VARCHAR DEFAULT '0',padcondate VARCHAR,comid VARCHAR,remarks VARCHAR,orderbyno VARCHAR,version VARCHAR,credate timestamp,creuser VARCHAR,updatetime timestamp,updateuser VARCHAR,deleteflag VARCHAR  DEFAULT '0'   )";
        db.execSQL(sql);
    }

    /**
     * 初始化基础数据
     *
     * @param db
     */
    public void initData(SQLiteDatabase db) {
        // 初始化KV表基础数据
        /* String synkvTablename[] = { "CMM_BOARD_M", "CMM_DATADIC_M", "CMM_AREA_M", "MST_ROUTE_M",
                 "MST_TERMINALINFO_M", "MST_AGENCYINFO_M", "MST_AGENCYGRID_INFO", 
                 "MST_AGENCYSUPPLY_INFO", "MST_AGENCYTRANSFER_INFO", 
                 "MST_INVOICING_INFO", "MST_PROMOTIONS_M", "MST_PROMOTIONSTYPE_M",
                 "MST_PROMOPRODUCT_INFO", "MST_PROMOTERM_INFO", "MST_VISIT_M", 
                 "MST_VISTPRODUCT_INFO", "MST_CHECKEXERECORD_INFO", "MST_COLLECTIONEXERECORD_INFO", 
                 "MST_CMPBRANDS_M", "MST_CMPCOMPANY_M", "MST_CMPRODUCTINFO_M", "MST_PRODUCT_M", 
                 "MST_VISITMEMO_INFO", "MST_VISITAUTHORIZE_INFO", "MST_AGENCYVISIT_M", "MST_QUESTIONSANSWERS_INFO", 
                 "MST_WORKSUMMARY_INFO", "PAD_CHECKTYPE_M", "MST_PLANFORUSER_M", "MST_PLANCOLLECTION_INFO", 
                 "PAD_PLANTEMPCHECK_M", "MST_CMPSUPPLY_INFO", "MST_PLANCHECK_INFO", 
                 "MST_PRICE_M", "MST_PRICEDETAILS_INFO", "MST_PRODUCTAREA_INFO", "MST_MONTHTARGET_INFO",
                 "MST_CMPAGENCY_INFO", "MST_PRODUCTSHOW_M","MST_SHOWPIC_INFO"};*/
        String synkvTabl[][] = {


                /*{"CMM_BOARD_M", "通知公告"},
                {"CMM_DATADIC_M", "数据字典"},
                {"CMM_AREA_M", "省市县区域"},*/
                {"MST_ROUTE_M", "线路"},
                {"MST_TERMINALINFO_M", "终端"}/*,
                {"MST_AGENCYINFO_M", "分经销商"},
                {"MST_AGENCYGRID_INFO", "分经销商"},
                {"MST_AGENCYSUPPLY_INFO", "巡店拜访"},
                {"MST_AGENCYTRANSFER_INFO", "分经销商拜访"},
                {"MST_INVOICING_INFO", "分经销商拜访"},
                {"MST_PROMOTIONS_M", "活动基础信息"},
                {"MST_PROMOTIONSTYPE_M", "活动基础信息"},
                {"MST_PROMOPRODUCT_INFO", "活动基础信息"},
                {"MST_PROMOTERM_INFO", "巡店拜访"},
                {"MST_VISIT_M", "巡店拜访"},
                {"MST_VISTPRODUCT_INFO", "巡店拜访"},
                {"MST_CHECKEXERECORD_INFO", "巡店拜访"},
                {"MST_COLLECTIONEXERECORD_INFO", "巡店拜访"},
                {"MST_CMPBRANDS_M", "竞品"},
                {"MST_CMPCOMPANY_M", "竞品"},
                {"MST_CMPRODUCTINFO_M", "竞品"},
                {"MST_PRODUCT_M", "分经销商"},
                {"MST_VISITMEMO_INFO", "客情备忘录"},
                {"MST_VISITAUTHORIZE_INFO", "定格可拜访授权"},
                {"MST_AGENCYVISIT_M", "分经销商拜访"},
                {"MST_QUESTIONSANSWERS_INFO", "问题反馈"},
                {"MST_WORKSUMMARY_INFO", "日工作总结"},
                {"PAD_CHECKTYPE_M", "采集用指标"},
                {"MST_PLANFORUSER_M", "工作计划指标状态"},
                {"MST_PLANCOLLECTION_INFO", "工作计划指标状态"},
                {"PAD_PLANTEMPCHECK_M", "计划模板"},
                {"MST_CMPSUPPLY_INFO", "巡店拜访"},
                {"MST_PLANCHECK_INFO", "工作计划指标状态"},
                {"MST_PRICE_M", "价格明细"},
                {"MST_PRICEDETAILS_INFO", "价格明细"},
                {"MST_PRODUCTAREA_INFO", "分经销商信息"},
                {"MST_MONTHTARGET_INFO", "月目标管理"},
                {"MST_CMPAGENCY_INFO", "竞品供应商管理"},
                {"MST_PRODUCTSHOW_M", "产品展示信息"},
                {"MST_SHOWPIC_INFO", "产品展示"},
                {"MST_PLANWEEKFORUSER_M", "周工作计划"},

                {"MST_PICTYPE_M", "图片类型"},

                {"MST_AGENCYKF_M", "经销商开发"},
                {"MST_PLANTERMINAL_M", "计划终端表"},
                {"MST_GROUPPRODUCT_M2", "产品组合是否达标"}*/


        };

        StringBuffer buffer;
        for (String[] item : synkvTabl) {
            buffer = new StringBuffer();
            buffer.append("insert into MST_SYNCKV_M (tablename, syncDay,tableDesc) ");
            buffer.append("values ('").append(item[0]).append("', '");
            buffer.append(PropertiesUtil.getProperties(item[0] + "_SYNCDAY", "0"));
            //buffer.append(PropertiesUtil.getProperties(item + "_SYNCDAY", "0"));
            buffer.append("','" + item[1] + "') ");
            db.execSQL(buffer.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        // C1： 添加表、视图
        if (oldVersion < 1) {
            db.execSQL("create table temp_hongen (usercode varchar2(20))");
            db.execSQL("create view v_hongen as select * from temp_hongen");
            db.execSQL("insert into temp_hongen(usercode) values('100001')");
        }

        // C2：添加字段
        if (oldVersion < 2) {
            db.execSQL("alter table temp_hongen add username varchar2(10)");
        }

        // C3：删除、修改字段
        if (oldVersion < 3) {
            db.execSQL("create table temp_hongen_temp as select * from temp_hongen");
            db.execSQL("drop table temp_hongen");
            db.execSQL("create table temp_hongen (usercode varchar2(20), username varchar2(20), deparment varchar2(10))");
            db.execSQL("insert into temp_hongen(usercode, username) select usercode,username from temp_hongen_temp");
            db.execSQL("drop table temp_hongen_temp");
        }

        // C4：修改视图
        if (oldVersion < 4) {
            db.execSQL("drop view if exists v_hongen");
            db.execSQL("create view v_hongen as select *, 'changeview' from temp_hongen");
        }

        // 28: 清除不需要下载数据的表
        if (oldVersion < 28) {
            db.execSQL("delete from MST_SYNCKV_M where tablename in ('MST_POWERFULCHANNEL_INFO','MST_POWERFULTERMINAL_INFO','MST_CHECKMIDDLE_INFO','MST_PLANROUTE_INFO')");
        }

        // 32: 拜访指标执行采集项记录表增加指标主键字段
        if (oldVersion < 32) {
            db.execSQL("alter table MST_COLLECTIONEXERECORD_INFO add checkkey varchar2");
        }

        // 33: 拜访指标执行采集项记录表增加指标主键字段
        if (oldVersion < 33) {
            //去除同步‘巡店拜访指标采集项’
            db.execSQL("delete from MST_SYNCKV_M  where tablename='MST_COLLECTIONEXERECORD_INFO'");
            //拜访终端是否店销和店销时间
            db.execSQL("alter table mst_visit_m add ifmine varchar2(10)");
            db.execSQL("alter table mst_visit_m add ifminedate varchar2(20)");
            //万能铺货率
            db.execSQL("alter table MST_POWERFULTERMINAL_INFO add prevdistribution integer");
            db.execSQL("alter table MST_POWERFULTERMINAL_INFO add preveffectdis integer");
            db.execSQL("alter table MST_POWERFULTERMINAL_INFO add preveffectsale integer");
            //
            db.execSQL("alter table MST_POWERFULCHANNEL_INFO add prevdistribution integer");
            db.execSQL("alter table MST_POWERFULCHANNEL_INFO add preveffectdis integer");
            db.execSQL("alter table MST_POWERFULCHANNEL_INFO add preveffectsale integer");
            //经销商拜访期初库存（上次库存）
            db.execSQL("alter table MST_INVOICING_INFO add prestorenum Double");
            //周工作计划
            db.execSQL("CREATE TABLE MST_PLANWEEKFORUSER_M (comid VARCHAR , credate VARCHAR , creuser VARCHAR , deleteflag VARCHAR DEFAULT '0' , enddate VARCHAR , gridkey VARCHAR , orderbyno VARCHAR , padcondate VARCHAR , padisconsistent VARCHAR DEFAULT '0' , plankey VARCHAR NOT NULL , planstatus VARCHAR , plantitle VARCHAR , remarks VARCHAR , scondate VARCHAR , sisconsistent VARCHAR , startdate VARCHAR , updatetime VARCHAR , updateuser VARCHAR , uploadFlag VARCHAR DEFAULT '0' , userid VARCHAR , version VARCHAR , PRIMARY KEY (plankey) );");
        }
        // 35: 创建 拜访拍照表
        if (oldVersion < 35) {
            try {
                /*//创建  本地拍照表,图片类型表
                TableUtils.createTable(connectionSource, MstCameraInfoM.class);
				TableUtils.createTable(connectionSource, MstPictypeM.class);
				// 创建经销商开发表
				TableUtils.createTable(connectionSource, MstAgencyKFM.class);
				// 创建终端台账表
				TableUtils.createTable(connectionSource, MstTermLedgerInfo.class);// 终端台账
				*/
                //创建  本地拍照表,图片类型表
                //TableUtils.createTable(connectionSource, MstCameraInfoM.class);
                db.execSQL("CREATE TABLE MST_CAMERAINFO_M (camerakey VARCHAR(36) NOT NULL,terminalkey VARCHAR(36),visitkey VARCHAR(36),pictypekey VARCHAR(36),picname VARCHAR(150),localpath VARCHAR(150),netpath VARCHAR(150),cameradata VARCHAR(20),isupload VARCHAR2(36),istakecamera VARCHAR(36),picindex VARCHAR(16),pictypename VARCHAR(150),sureup VARCHAR(16),imagefileString VARCHAR(4000));");
                //TableUtils.createTable(connectionSource, MstPictypeM.class);
                db.execSQL("CREATE TABLE MST_PICTYPE_M ( pictypekey VARCHAR(36) NOT NULL,pictypename VARCHAR(36),areaid VARCHAR(36),orderno VARCHAR(36),status VARCHAR(150),focus VARCHAR(150),createuser VARCHAR(36),updateuser VARCHAR(36),createdate VARCHAR2(36),updatedate VARCHAR(36));");
                // 创建经销商开发表
                //TableUtils.createTable(connectionSource, MstAgencyKFM.class);
                db.execSQL("CREATE TABLE MST_AGENCYKF_M ( agencykfkey VARCHAR(36) NOT NULL,gridkey VARCHAR(36),agencyname VARCHAR(36),contact VARCHAR(36),mobile VARCHAR(150),address VARCHAR(150),area VARCHAR(150),money VARCHAR(20),carnum VARCHAR2(5),productname VARCHAR(36),kfdate VARCHAR(20),status VARCHAR(5),createdate VARCHAR(20),createuser VARCHAR(20),updatedate VARCHAR(20),updateuser VARCHAR2(20),upload VARCHAR(5));");
                // 创建终端台账表
                //TableUtils.createTable(connectionSource, MstTermLedgerInfo.class);// 终端台账
                db.execSQL("CREATE TABLE MST_TERMLEDGER_INFO ( termledgerkey VARCHAR(36) NOT NULL,terminalkey VARCHAR(36),terminalcode VARCHAR(36),terminalname VARCHAR(36),gridname VARCHAR(15),gridkey VARCHAR(10),agencykey VARCHAR(36),agencycode VARCHAR(20),agencyname VARCHAR2(36),productkey VARCHAR(36),procode VARCHAR(16),proname VARCHAR(30),padisconsistent VARCHAR(2),orderbyno VARCHAR(1),deleteflag VARCHAR(1),remarks VARCHAR(36),yesup VARCHAR(1),purchase VARCHAR(5),sequence VARCHAR(5),routename VARCHAR(36),address VARCHAR(36),contact VARCHAR2(36),mobile VARCHAR(36),areaid VARCHAR(16),areaname VARCHAR(36),downdate VARCHAR(10),firstzm VARCHAR(10),purchasetime VARCHAR(10));");
                // 在同步kv表中插入图片类型一条记录
                StringBuffer buffer = new StringBuffer();
                buffer.append("insert into MST_SYNCKV_M (tablename, syncDay,tableDesc) ");
                buffer.append("values ('").append("MST_PICTYPE_M").append("', '");
                buffer.append(PropertiesUtil.getProperties("MST_PICTYPE_M" + "_SYNCDAY", "0"));
                buffer.append("','" + "图片类型" + "') ");
                db.execSQL(buffer.toString());
                // 在同步kv表中插入经销商开发一条记录
                StringBuffer buffer1 = new StringBuffer();
                buffer1.append("insert into MST_SYNCKV_M (tablename, syncDay,tableDesc) ");
                buffer1.append("values ('").append("MST_AGENCYKF_M").append("', '");
                buffer1.append(PropertiesUtil.getProperties("MST_PICTYPE_M" + "_SYNCDAY", "0"));
                buffer1.append("','" + "经销商开发" + "') ");
                db.execSQL(buffer1.toString());
                // copy上面
                // 在MST_VISTPRODUCT_INFO表中添加agencyname字段
                //alter table 表名 add 字段名 数据类型 default 默认值
                db.execSQL("alter table MST_VISTPRODUCT_INFO add agencyname varchar(100)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 36 创建计划终端表
        if (oldVersion < 36) {

            try {
                //创建  本地拍照表,图片类型表
                //TableUtils.createTable(connectionSource, MstPlanTerminalM.class);
                db.execSQL("CREATE TABLE MST_PLANTERMINAL_M ( planterminalkey VARCHAR(36) NOT NULL,pcolitemkey VARCHAR(36),terminalkey VARCHAR(36),terminalname VARCHAR(36),tlevel VARCHAR(150),padisconsistent VARCHAR(150),creuser VARCHAR(150),updateuser VARCHAR(20),cretime DATE,updatetime DATE);");

                // 在同步kv表中插入图片类型一条记录
                StringBuffer buffer = new StringBuffer();
                buffer.append("insert into MST_SYNCKV_M (tablename, syncDay,tableDesc) ");
                buffer.append("values ('").append("MST_PLANTERMINAL_M").append("', '");
                buffer.append(PropertiesUtil.getProperties("MST_PICTYPE_M" + "_SYNCDAY", "0"));
                buffer.append("','" + "计划终端表" + "') ");
                db.execSQL(buffer.toString());

                // 经销商开发 新加一些字段
                db.execSQL("alter table MST_AGENCYKF_M add persion varchar(100)");
                db.execSQL("alter table MST_AGENCYKF_M add business varchar(100)");
                db.execSQL("alter table MST_AGENCYKF_M add isone integer");
                db.execSQL("alter table MST_AGENCYKF_M add coverterms varchar(1000)");
                db.execSQL("alter table MST_AGENCYKF_M add supplyterms varchar(1000)");
                db.execSQL("alter table MST_AGENCYKF_M add passdate varchar(1000)");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 37 新增字段
        if (oldVersion < 37) {

            try {
                // MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表) 新加一些字段
                db.execSQL("alter table MST_VISTPRODUCT_INFO add fristdate varchar(100)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 38 新增字段
        if (oldVersion < 38) {

            try {
                db.execSQL("alter table MST_TERMINALINFO_M add selftreaty varchar(100)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (oldVersion < 39) {

            try {
                db.execSQL("alter table MST_TERMINALINFO_M add cmpselftreaty varchar(100)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (oldVersion < 40) {

            try {
                db.execSQL("alter table MST_VISIT_M add visitposition varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (oldVersion < 41) {

            try {
                db.execSQL("alter table MST_PLANCOLLECTION_INFO add ordernum varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (oldVersion < 42) {

            try {
                db.execSQL("alter table MST_PROMOTIONS_M add ispictype varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (oldVersion < 43) {

            try {
                db.execSQL("alter table MST_COLLECTIONEXERECORD_INFO add freshness varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 44) {

            try {
                // 新增累计卡字段
                db.execSQL("alter table MST_VISTPRODUCT_INFO add addcard varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 45) {

            try {

                //创建  本地流量统计表
                db.execSQL("CREATE TABLE MST_BS_DATA (bsdatakey VARCHAR(36) NOT NULL,title VARCHAR(36),sizes VARCHAR(36),credate VARCHAR(36),flag VARCHAR(150),content VARCHAR(150),remark VARCHAR(150));");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 46) {

            try {

                //
                db.execSQL("alter table MST_INVOICING_INFO add selfsales varchar2(36)");
                db.execSQL("alter table MST_INVOICING_INFO add unselfsales varchar2(36)");
                db.execSQL("alter table MST_INVOICING_INFO add othersales varchar2(36)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 47) {

            try {

                //
                db.execSQL("alter table MST_TERMINALINFO_M add ifmine varchar2(10)");
                db.execSQL("alter table MST_TERMINALINFO_M add ifminedate varchar2(20)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 48) {

            try {

                // 新加MST_GROUPPRODUCT_M表
                db.execSQL("CREATE TABLE MST_GROUPPRODUCT_M (gproductid VARCHAR2(36), terminalcode VARCHAR2(20), terminalname VARCHAR2(500), ifrecstand CHAR(1), startdate DATE, enddate DATE, createusereng VARCHAR2(30), createdate DATE , updateusereng VARCHAR2(30), updatetime DATE, uploadflag VARCHAR(30), padisconsistent VARCHAR(30))");


                // 在同步kv表中插入产品组合是否达标一条记录
                StringBuffer buffer = new StringBuffer();
                buffer.append("insert into MST_SYNCKV_M (tablename, syncDay,tableDesc) ");
                buffer.append("values ('").append("MST_GROUPPRODUCT_M2").append("', '");
                buffer.append(PropertiesUtil.getProperties("MST_PICTYPE_M" + "_SYNCDAY", "0"));
                buffer.append("','" + "产品组合是否达标" + "') ");
                db.execSQL(buffer.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 49) {

            try {

                //
                db.execSQL("alter table MST_COLLECTIONEXERECORD_INFO add bianhualiang varchar2(20)");
                db.execSQL("alter table MST_COLLECTIONEXERECORD_INFO add xianyouliang varchar2(20)");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 50) {

            try {
                // 图片表新加4个字段
                db.execSQL("alter table MST_CAMERAINFO_M add areaid varchar(100)");
                db.execSQL("alter table MST_CAMERAINFO_M add areapid varchar(100)");
                db.execSQL("alter table MST_CAMERAINFO_M add gridkey varchar(100)");
                db.execSQL("alter table MST_CAMERAINFO_M add routekey varchar(100)");

                // 产品组合是否达标  添加visitkey字段
                db.execSQL("alter table MST_GROUPPRODUCT_M add visitkey varchar(36)");
                // PAD_CHECKACCOMPLISH_INFO  添加visitkey字段
                db.execSQL("alter table PAD_CHECKACCOMPLISH_INFO add areaid varchar(36)");

                // 拜访临时表
                String visittemp = "create table MST_VISIT_M_TEMP (visitkey varchar,terminalkey varchar,routekey varchar,gridkey varchar,areaid varchar,visitdate varchar,tempkey varchar,enddate varchar,userid varchar,visituser varchar,isself varchar,iscmp varchar,selftreaty varchar,cmptreaty varchar,status varchar,ishdistribution varchar,exetreaty varchar,selfoccupancy varchar,iscmpcollapse varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar2,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar,longitude  varchar , latitude  varchar ,gpsstatus  varchar ,ifminedate  varchar , ifmine  varchar ,visitposition  varchar ,uploadFlag  varchar )";
                db.execSQL(visittemp);

                // 终端表临时表
                String termtemp = "create table MST_TERMINALINFO_M_TEMP (terminalkey varchar,routekey varchar,terminalcode varchar,terminalname varchar,province varchar,city varchar,county varchar,address varchar,contact varchar,mobile varchar,tlevel varchar,sequence varchar, cycle varchar,hvolume varchar,mvolume varchar,lvolume varchar,status varchar,sellchannel varchar,mainchannel varchar,minorchannel varchar,areatype varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,pvolume varchar ,selftreaty varchar ,cmpselftreaty varchar ,ifminedate varchar ,ifmine varchar )";
                db.execSQL(termtemp);

                // 协同终端表购物车
                String xttermcart = "create table MST_TERMINALINFO_M_CART (terminalkey varchar,routekey varchar,terminalcode varchar,ddtype varchar,visitflag varchar,terminalname varchar,province varchar,city varchar,county varchar,address varchar,contact varchar,mobile varchar,tlevel varchar,sequence varchar, cycle varchar,hvolume varchar,mvolume varchar,lvolume varchar,status varchar,sellchannel varchar,mainchannel varchar,minorchannel varchar,areatype varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,pvolume varchar ,selftreaty varchar ,cmpselftreaty varchar ,ifminedate varchar ,ifmine varchar )";
                db.execSQL(xttermcart);

                // 追溯终端表购物车
                String zstermcart = "create table MST_TERMINALINFO_M_ZSCART (terminalkey varchar,routekey varchar,terminalcode varchar,ddtype varchar,visitflag varchar,terminalname varchar,province varchar,city varchar,county varchar,address varchar,contact varchar,mobile varchar,tlevel varchar,sequence varchar, cycle varchar,hvolume varchar,mvolume varchar,lvolume varchar,status varchar,sellchannel varchar,mainchannel varchar,minorchannel varchar,areatype varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,pvolume varchar ,selftreaty varchar ,cmpselftreaty varchar ,ifminedate varchar ,ifmine varchar )";
                db.execSQL(zstermcart);

                // 我品供货关系表临时表
                String agencysupplytemp = "create table MST_AGENCYSUPPLY_INFO_TEMP (asupplykey varchar,status varchar,inprice varchar,reprice varchar,productkey varchar,lowerkey varchar,lowertype varchar,upperkey varchar,uppertype varchar,siebelkey varchar,sisconsistent varchar, scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(agencysupplytemp);

                // 拜访产品表临时表
                String visitprotemp = "create table MST_VISTPRODUCT_INFO_TEMP (recordkey varchar,visitkey varchar,productkey varchar,cmpproductkey varchar,cmpcomkey varchar, agencykey varchar,purcprice number(14,2) ,retailprice number(14,2) ,purcnum number(14,2) ,pronum number(14,2) ,currnum number(14,2) ,salenum number(14,2) ,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,agencyname varchar,fristdate varchar,addcard number(14,2))";
                db.execSQL(visitprotemp);

                // 竞品供货关系表临时表
                String cmpsupplytemp = "create table MST_CMPSUPPLY_INFO_TEMP ( cmpsupplykey varchar,cmpproductkey varchar,cmpcomkey varchar,terminalkey varchar,inprice varchar,reprice varchar,status varchar,cmpinvaliddate varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(cmpsupplytemp);

                // 指标采集项 临时表
                String collectiontemp = "create table MST_COLLECTIONEXERECORD_INFO_TEMP (colrecordkey varchar ,visitkey varchar , productkey varchar , checkkey varchar , colitemkey varchar ,addcount number(14,2) ,totalcount number(14,2) ,sisconsistent varchar ,scondate date , padisconsistent varchar ,padcondate date ,comid varchar , remarks varchar , freshness varchar ,orderbyno varchar ,deleteflag varchar ,version integer ,credate date ,creuser varchar ,updatetime date ,updateuser varchar ,bianhualiang varchar ,xianyouliang varchar )";
                db.execSQL(collectiontemp);

                // 促销活动表 临时表
                String promotermtemp = "create table MST_PROMOTERM_INFO_TEMP (recordkey varchar,ptypekey varchar,terminalkey varchar, startdate varchar,sisconsistent varchar,scondate date ,padisconsistent varchar, padcondate date ,comid varchar , remarks varchar, orderbyno varchar, deleteflag varchar,version integer ,credate date , creuser varchar,updatetime date ,updateuser varchar , visitkey varchar, isaccomplish varchar)";
                db.execSQL(promotermtemp);

                // 图片表 临时表
                String camerainfotemp = "CREATE TABLE MST_CAMERAINFO_M_TEMP (camerakey VARCHAR(36) NOT NULL,terminalkey VARCHAR(36),visitkey VARCHAR(36),pictypekey VARCHAR(36),picname VARCHAR(150),localpath VARCHAR(150),netpath VARCHAR(150),cameradata VARCHAR(20),isupload VARCHAR2(36),istakecamera VARCHAR(36),picindex VARCHAR(16),pictypename VARCHAR(150),sureup VARCHAR(16),imagefileString VARCHAR(4000),areaid VARCHAR(36),areapid VARCHAR(36),gridkey VARCHAR(36),routekey VARCHAR(36)) ";
                db.execSQL(camerainfotemp);

                // 客情表 临时表
                String visitmemoinfotemp = "create table MST_VISITMEMO_INFO_TEMP (memokey varchar,terminalkey varchar, content varchar,isover varchar,iswarn varchar,startdate varchar,enddate varchar,sisconsistent varchar, scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(visitmemoinfotemp);

                // 产品组合是否达标表 临时表
                String groupproductmtemp = "CREATE TABLE MST_GROUPPRODUCT_M_TEMP (gproductid VARCHAR(36) ,terminalcode VARCHAR(36),terminalname VARCHAR(36),ifrecstand VARCHAR(36),startdate VARCHAR(150),enddate VARCHAR(150),createusereng VARCHAR(150),createdate VARCHAR(20),updateusereng VARCHAR2(36),updatetime VARCHAR(36),uploadflag VARCHAR(16),padisconsistent VARCHAR(36),visitkey VARCHAR(36)) ";
                db.execSQL(groupproductmtemp);

                /*// 组合表 正式表 该表和指标表一样  //存储底端4个指标
                String checkgroup = "create table MST_CHECKGROUP_INFO (recordkey varchar,visitkey varchar, productkey varchar, checkkey varchar, checktype varchar, acresult varchar, iscom varchar,startdate varchar,enddate varchar,terminalkey varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date , comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date , creuser varchar, updatetime date ,updateuser varchar ,cstatuskey varchar,isauto varchar,exestatus varchar, siebelid varchar,resultstatus integer)";
                db.execSQL(checkgroup);

                // 组合表 临时表 该表和指标表一样 //存储底端4个指标
                String checkgrouptemp = "create table MST_CHECKGROUP_INFO_TEMP (recordkey varchar,visitkey varchar, productkey varchar, checkkey varchar, checktype varchar, acresult varchar, iscom varchar,startdate varchar,enddate varchar,terminalkey varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date , comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date , creuser varchar, updatetime date ,updateuser varchar ,cstatuskey varchar,isauto varchar,exestatus varchar, siebelid varchar,resultstatus integer)";
                db.execSQL(checkgrouptemp);*/


                // -----------------------------------

                // 协同拜访表
                String mitvisit = "create table MIT_VISIT_M (visitkey varchar,terminalkey varchar,routekey varchar,gridkey varchar,areaid varchar,visitdate varchar,tempkey varchar,enddate varchar,userid varchar,visituser varchar,isself varchar,iscmp varchar,selftreaty varchar,cmptreaty varchar,status varchar,ishdistribution varchar,exetreaty varchar,selfoccupancy varchar,iscmpcollapse varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar2,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar,longitude  varchar , latitude  varchar ,gpsstatus  varchar ,ifminedate  varchar , ifmine  varchar ,visitposition  varchar ,uploadFlag  varchar )";
                db.execSQL(mitvisit);

                // 协同终端表
                String mitterm = "create table MIT_TERMINALINFO_M (terminalkey varchar,routekey varchar,terminalcode varchar,terminalname varchar,province varchar,city varchar,county varchar,address varchar,contact varchar,mobile varchar,tlevel varchar,sequence varchar, cycle varchar,hvolume varchar,mvolume varchar,lvolume varchar,status varchar,sellchannel varchar,mainchannel varchar,minorchannel varchar,areatype varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,pvolume varchar ,selftreaty varchar ,cmpselftreaty varchar ,ifminedate varchar ,ifmine varchar )";
                db.execSQL(mitterm);

                // 协同我品供货关系表
                String mitagencysupply = "create table MIT_AGENCYSUPPLY_INFO (asupplykey varchar,status varchar,inprice varchar,reprice varchar,productkey varchar,lowerkey varchar,lowertype varchar,upperkey varchar,uppertype varchar,siebelkey varchar,sisconsistent varchar, scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(mitagencysupply);

                // 协同拜访产品表
                String mitvisitpro = "create table MIT_VISTPRODUCT_INFO (recordkey varchar,visitkey varchar,productkey varchar,cmpproductkey varchar,cmpcomkey varchar, agencykey varchar,purcprice number(14,2) ,retailprice number(14,2) ,purcnum number(14,2) ,pronum number(14,2) ,currnum number(14,2) ,salenum number(14,2) ,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar ,agencyname varchar,fristdate varchar,addcard number(14,2))";
                db.execSQL(mitvisitpro);

                // 协同竞品供货关系表
                String mitcmpsupply = "create table MIT_CMPSUPPLY_INFO ( cmpsupplykey varchar,cmpproductkey varchar,cmpcomkey varchar,terminalkey varchar,inprice varchar,reprice varchar,status varchar,cmpinvaliddate varchar,sisconsistent varchar,scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(mitcmpsupply);

                // 协同指标采集项
                String mitcollection = "create table MIT_COLLECTIONEXERECORD_INFO (colrecordkey varchar ,visitkey varchar , productkey varchar , checkkey varchar , colitemkey varchar ,addcount number(14,2) ,totalcount number(14,2) ,sisconsistent varchar ,scondate date , padisconsistent varchar ,padcondate date ,comid varchar , remarks varchar , freshness varchar ,orderbyno varchar ,deleteflag varchar ,version integer ,credate date ,creuser varchar ,updatetime date ,updateuser varchar ,bianhualiang varchar ,xianyouliang varchar )";
                db.execSQL(mitcollection);

                // 协同促销活动表
                String mitpromoterm = "create table MIT_PROMOTERM_INFO (recordkey varchar,ptypekey varchar,terminalkey varchar, startdate varchar,sisconsistent varchar,scondate date ,padisconsistent varchar, padcondate date ,comid varchar , remarks varchar, orderbyno varchar, deleteflag varchar,version integer ,credate date , creuser varchar,updatetime date ,updateuser varchar , visitkey varchar, isaccomplish varchar)";
                db.execSQL(mitpromoterm);

                // 协同图片表
                String mitcamerainfo = "CREATE TABLE MIT_CAMERAINFO_M (camerakey VARCHAR(36) NOT NULL,terminalkey VARCHAR(36),visitkey VARCHAR(36),pictypekey VARCHAR(36),picname VARCHAR(150),localpath VARCHAR(150),netpath VARCHAR(150),cameradata VARCHAR(20),isupload VARCHAR2(36),istakecamera VARCHAR(36),picindex VARCHAR(16),pictypename VARCHAR(150),sureup VARCHAR(16),imagefileString VARCHAR(4000),areaid VARCHAR(36),areapid VARCHAR(36),gridkey VARCHAR(36),routekey VARCHAR(36)) ";
                db.execSQL(mitcamerainfo);

                // 协同客情表
                String mitvisitmemoinfo = "create table MIT_VISITMEMO_INFO (memokey varchar,terminalkey varchar, content varchar,isover varchar,iswarn varchar,startdate varchar,enddate varchar,sisconsistent varchar, scondate date ,padisconsistent varchar,padcondate date ,comid varchar,remarks varchar,orderbyno varchar,deleteflag varchar,version integer ,credate date ,creuser varchar,updatetime date ,updateuser varchar )";
                db.execSQL(mitvisitmemoinfo);

                // 协同产品组合是否达标表
                String mitgroupproductm = "CREATE TABLE MIT_GROUPPRODUCT_M (gproductid VARCHAR(36) ,terminalcode VARCHAR(36),terminalname VARCHAR(36),ifrecstand VARCHAR(36),startdate VARCHAR(150),enddate VARCHAR(150),createusereng VARCHAR(150),createdate VARCHAR(20),updateusereng VARCHAR2(36),updatetime VARCHAR(36),uploadflag VARCHAR(16),padisconsistent VARCHAR(36),visitkey VARCHAR(36)) ";
                db.execSQL(mitgroupproductm);

                // 协同拉链表
                String sql = "CREATE TABLE  IF NOT EXISTS MIT_CHECKEXERECORD_INFO " + "(recordkey  VARCHAR ,visitkey VARCHAR,productkey VARCHAR,checkkey VARCHAR,checktype VARCHAR,acresult VARCHAR,iscom VARCHAR,cstatuskey VARCHAR,isauto VARCHAR,exestatus VARCHAR,startdate VARCHAR,enddate VARCHAR,terminalkey VARCHAR,sisconsistent VARCHAR,scondate timestamp,siebelid VARCHAR,resultstatus VARCHAR,padisconsistent VARCHAR DEFAULT '0',padcondate VARCHAR,comid VARCHAR,remarks VARCHAR,orderbyno VARCHAR,version VARCHAR,credate timestamp,creuser VARCHAR,updatetime timestamp,updateuser VARCHAR,deleteflag VARCHAR  DEFAULT '0'   )";
                db.execSQL(sql);

                // 获取各终端     每天    协同拜访的最新的拜访记录
                StringBuffer buffer = new StringBuffer();
                buffer.append("drop view if exists v_mit_visit_m");
                db.execSQL(buffer.toString());
                buffer = new StringBuffer();
                buffer.append("create view IF NOT EXISTS v_mit_visit_m as ");
                buffer.append("select m.* from mst_visit_m m  ");
                buffer.append("inner join (select max(visitdate) maxvisitdate, ");
                buffer.append("    max(terminalkey) maxterminalkey ");
                buffer.append("    from mst_visit_m where visitdate is not null group by terminalkey, substr(visitdate,1,8)) v ");
                buffer.append("  on m.terminalkey = v.maxterminalkey  ");
                buffer.append("    and m.visitdate = v.maxvisitdate");
                db.execSQL(buffer.toString());

                // 获取各终端    所有    协同拜访的拜访数据中最新拜访记录
                buffer = new StringBuffer();
                buffer.append("drop view if exists v_mit_visit_m_newest");
                db.execSQL(buffer.toString());
                buffer = new StringBuffer();
                buffer.append("create view IF NOT EXISTS v_mit_visit_m_newest as ");
                buffer.append("select m.* from mst_visit_m m  ");
                buffer.append("inner join (select max(visitdate) maxvisitdate, ");
                buffer.append("    max(terminalkey) maxterminalkey ");
                buffer.append("    from mst_visit_m where visitdate is not null group by terminalkey) v ");
                buffer.append("  on m.terminalkey = v.maxterminalkey  ");
                buffer.append("    and m.visitdate = v.maxvisitdate");
                db.execSQL(buffer.toString());

                // 0.0 追溯模板配置
                String mit_valcheckter_m = "create table MIT_VALCHECKTER_M (id varchar2(36) not null, areaid varchar2(36) null,padisconsistent  char(1)   null, vaildter char(1) null, vaildvisit char(1) null, ifmine char(1) null, salesarea  char(1) null, terworkstatus   char(1) null, terminalcode char(1) null, routecode  char(1) null, tername  char(1) null, terlevel char(1) null, province char(1) null, city   char(1) null, country  char(1) null, address  char(1) null, contact  char(1) null, mobile char(1) null, cylie  char(1) null, visitorder char(1) null, hvolume  char(1) null, zvolume  char(1) null, pvolume  char(1) null, lvolume  char(1) null, areatype char(1) null, sellchannel char(1) null, mainchannel char(1) null, minorchannel char(1) null, visituser  char(1) null, addsupply  char(1) null, losesupply char(1) null, iffleeing  char(1) null, proerror char(1) null, agencyerror char(1) null, dataerror  char(1) null, distrbution char(1) null, goodsvivi  char(1) null, provivi  char(1) null, icevivi  char(1) null, salespromotion  char(1) null, grouppro char(1) null, cooperation char(1) null, highps char(1) null, prooccupy  char(1) null, addcmp char(1) null, losecmp  char(1) null, cmperror char(1) null, cmpagencyerror  char(1) null, cmpdataerror char(1) null, ifcmp  char(1) null, visinote char(1) null, creuser  varchar2(128) null, credate  date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valcheckter_m);

                // 1.1 追溯主表
                String mit_valter_m = "create table MIT_VALTER_M (id  varchar2(36)  not null,vidstartdate varchar2(36)  null,videnddate varchar2(36)  null,uploaddate varchar2(36)  null, padisconsistent  char(1)   null,terminalkey varchar2(36)  null,vidisselfremark   varchar2(300)  null,vidiscmpremark  varchar2(300)  null, vidselftreatyremark  varchar2(300)  null,visitdate varchar2(36)  null,vidter  char(1)   null, vidterflag  char(1)   null, vidterremaek  varchar2(300)  null, vidvisit char(1)   null, vidvisitflag  char(1)   null, vidvisitremark   varchar2(300)  null, vidifmine   char(1)   null, vidifmineflag char(1)   null, vidvisitotherval varchar2(100)  null, vidvisitottrueval  varchar2(100)   null,vidterlevel varchar2(100) null, vidtervidterlevelflag char(1) null, vidtervidterlevelval varchar2(100) null, vidtervidterlevelremark varchar2(300),vidifminermark   varchar2(300)  null, vidifminedate varchar2(20)  null, vidifminedateflag char(1)   null, vidifminedatermark   varchar2(300)  null, vidisself   char(1)   null, vidisselfflag char(1)   null, vidiscmp char(1)   null, vidiscmpflag  char(1)   null, vidisproremark   varchar2(300)  null, vidselftreaty char(1)   null,vidvisitotherval varchar2(100)  null, vidvisitottrueval  varchar2(100)   null, vidselftreatyflag char(1)   null, vidcmptreaty  char(1)   null, vidcmptreatyflag char(1)   null, vidcmptreatyremark   varchar2(300)  null, vidterminalcode  varchar2(50)  null, vidtercodeflag   char(1)   null, vidtercodeval varchar2(50)  null, vidtercoderemark varchar2(300)  null, vidroutekey varchar2(50)  null, vidrtekeyflag char(1)   null, vidrtekeyval  varchar2(50)  null, vidroutremark varchar2(300)  null, vidtername  varchar2(100)  null, vidternameflag   char(1)   null, vidternameval varchar2(100)  null, vidternameremark varchar2(300)  null, vidcountry  varchar2(20)  null, vidcountryflag   char(1)   null, vidcountryval varchar2(20)  null, vidcountryremark varchar2(300)  null, vidaddress  varchar2(150)  null, vidaddressflag   char(1)   null, vidaddressval varchar2(20)  null, vidaddressremark varchar2(300)  null, vidcontact  varchar2(150)  null, vidcontactflag   char(1)   null, vidcontactval varchar2(20)  null, vidcontactremark varchar2(300)  null, vidmobile   varchar2(150)  null, vidmobileflag char(1)   null, vidmobileval  varchar2(50)  null, vidmobileremark  varchar2(300)  null, vidsequence varchar2(150)  null, vidsequenceflag  char(1)   null, vidsequenceval   varchar2(10)  null, vidvidsequenceremark varchar2(300)  null, vidcycle varchar2(150)  null, vidcycleflag  char(1)   null, vidcycleval varchar2(10)  null, vidcycleremark2  varchar2(300)  null, vidareatype varchar(36)   null, vidareatypeflag  char(1)   null, vidareatypeval   varchar(36)   null, vidareatyperemark varchar2(300)  null, vidhvolume  varchar(36)   null, vidhvolumeflag   char(1)   null, vidhvolumeval varchar(36)   null, vidhvolumeremark varchar2(300)  null, vidzvolume  varchar(36)   null, vidzvolumeflag   char(1)   null, vidzvolumeval varchar(36)   null, vidxvolumeremark varchar2(300)  null, vidpvolume  varchar(36)   null, vidpvolumeflag   char(1)   null, vidpvolumeval varchar(36)   null, vidpvolumeremark varchar2(300)  null, vidlvolume  varchar(36)   null, vidlvolumeflag   char(1)   null, vidlvolumeval varchar(36)   null, vidlvolumeremark varchar2(300)  null, vidminchannel varchar(36)   null,vidminchannelflag char(1)   null, vidminchannelval varchar(36)   null, vidminchannelremark  varchar2(300)  null,vidvisituser  varchar2(100)  null,vidvisituserflag char(1)   null, vidvisituserval  varchar2(100)  null,vidvisituserremark   varchar2(300)  null,creuser varchar2(128)  null,credate date null,updateuser  varchar2(128)  null,updatedate  date null)";
                db.execSQL(mit_valter_m);

                // 1.2 追溯主表临时表
                String mit_valter_m_temp = "create table MIT_VALTER_M_TEMP (id  varchar2(36)  not null,vidstartdate varchar2(36)  null,videnddate varchar2(36)  null,uploaddate varchar2(36)  null,padisconsistent  char(1)   null, terminalkey varchar2(36)  null,vidisselfremark   varchar2(300)  null,vidiscmpremark  varchar2(300)  null, vidselftreatyremark  varchar2(300)  null,visitdate varchar2(36)  null, vidter  char(1)   null, vidterflag  char(1)   null, vidterremaek  varchar2(300)  null, vidvisit char(1)   null, vidvisitflag  char(1)   null, vidvisitremark   varchar2(300)  null, vidifmine   char(1)   null, vidifmineflag char(1)   null,vidvisitotherval varchar2(100)  null, vidvisitottrueval  varchar2(100)   null,vidterlevel varchar2(100) null, vidtervidterlevelflag char(1) null, vidtervidterlevelval varchar2(100) null, vidtervidterlevelremark varchar2(300), vidifminermark   varchar2(300)  null, vidifminedate varchar2(20)  null, vidifminedateflag char(1)   null, vidifminedatermark   varchar2(300)  null, vidisself   char(1)   null, vidisselfflag char(1)   null, vidiscmp char(1)   null, vidiscmpflag  char(1)   null, vidisproremark   varchar2(300)  null, vidselftreaty char(1)   null, vidselftreatyflag char(1)   null, vidcmptreaty  char(1)   null, vidcmptreatyflag char(1)   null, vidcmptreatyremark   varchar2(300)  null, vidterminalcode  varchar2(50)  null,vidvisitotherval varchar2(100)  null, vidvisitottrueval  varchar2(100)   null, vidtercodeflag   char(1)   null, vidtercodeval varchar2(50)  null, vidtercoderemark varchar2(300)  null, vidroutekey varchar2(50)  null, vidrtekeyflag char(1)   null, vidrtekeyval  varchar2(50)  null, vidroutremark varchar2(300)  null, vidtername  varchar2(100)  null, vidternameflag   char(1)   null, vidternameval varchar2(100)  null, vidternameremark varchar2(300)  null, vidcountry  varchar2(20)  null, vidcountryflag   char(1)   null, vidcountryval varchar2(20)  null, vidcountryremark varchar2(300)  null, vidaddress  varchar2(150)  null, vidaddressflag   char(1)   null, vidaddressval varchar2(20)  null, vidaddressremark varchar2(300)  null, vidcontact  varchar2(150)  null, vidcontactflag   char(1)   null, vidcontactval varchar2(20)  null, vidcontactremark varchar2(300)  null, vidmobile   varchar2(150)  null, vidmobileflag char(1)   null, vidmobileval  varchar2(50)  null, vidmobileremark  varchar2(300)  null, vidsequence varchar2(150)  null, vidsequenceflag  char(1)   null, vidsequenceval   varchar2(10)  null, vidvidsequenceremark varchar2(300)  null, vidcycle varchar2(150)  null, vidcycleflag  char(1)   null, vidcycleval varchar2(10)  null, vidcycleremark2  varchar2(300)  null, vidareatype varchar(36)   null, vidareatypeflag  char(1)   null, vidareatypeval   varchar(36)   null, vidareatyperemark varchar2(300)  null, vidhvolume  varchar(36)   null, vidhvolumeflag   char(1)   null, vidhvolumeval varchar(36)   null, vidhvolumeremark varchar2(300)  null, vidzvolume  varchar(36)   null, vidzvolumeflag   char(1)   null, vidzvolumeval varchar(36)   null, vidxvolumeremark varchar2(300)  null, vidpvolume  varchar(36)   null, vidpvolumeflag   char(1)   null, vidpvolumeval varchar(36)   null, vidpvolumeremark varchar2(300)  null, vidlvolume  varchar(36)   null, vidlvolumeflag   char(1)   null, vidlvolumeval varchar(36)   null, vidlvolumeremark varchar2(300)  null, vidminchannel varchar(36)   null,vidminchannelflag char(1)   null, vidminchannelval varchar(36)   null, vidminchannelremark  varchar2(300)  null,vidvisituser  varchar2(100)  null,vidvisituserflag char(1)   null, vidvisituserval  varchar2(100)  null,vidvisituserremark   varchar2(300)  null,creuser varchar2(128)  null,credate date null,updateuser  varchar2(128)  null,updatedate  date null)";
                db.execSQL(mit_valter_m_temp);

                // 2.1 追溯拉链表
                String mit_valchecktype_m = "create table MIT_VALCHECKTYPE_M ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null, visitkey varchar2(36) null, valchecktype varchar2(36) null, valchecktypeid varchar2(36) null, productkey varchar2(36) null, acresult varchar2(36) null, terminalkey varchar2(36) null, valchecktypeflag char(1)  null, valgrouppro  char(1)  null, valgroupproflag  char(1)  null, valgroupproremark    varchar2(300)  null,ddacresult    varchar2(36)  null,ddremark    varchar2(300)  null, valhz    char(1)  null, valhzflag    char(1)  null, valhzremark  varchar2(300)  null, valgzlps char(1)  null, valgzlpsflag char(1)  null, valgzlpsremark varchar2(300)  null, valzyl   char(1)  null, valzylflag   char(1)  null, valzylremark varchar2(300)  null, creuser  varchar2(128)  null, credate  date   null, updateuser   varchar2(128)  null, updatedate   date   null )";
                db.execSQL(mit_valchecktype_m);
                // 2.2 追溯拉链表 临时表
                String mit_valchecktype_m_temp = "create table MIT_VALCHECKTYPE_M_TEMP ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null,visitkey varchar2(36) null, valchecktype varchar2(36) null, valchecktypeid varchar2(36) null, productkey varchar2(36) null, acresult varchar2(36) null, terminalkey varchar2(36) null, valchecktypeflag char(1)  null, valgrouppro  char(1)  null, valgroupproflag  char(1)  null, valgroupproremark    varchar2(300)  null,ddacresult    varchar2(36)  null,ddremark    varchar2(300)  null, valhz    char(1)  null, valhzflag    char(1)  null, valhzremark  varchar2(300)  null, valgzlps char(1)  null, valgzlpsflag char(1)  null, valgzlpsremark varchar2(300)  null, valzyl   char(1)  null, valzylflag   char(1)  null, valzylremark varchar2(300)  null, creuser  varchar2(128)  null, credate  date   null, updateuser   varchar2(128)  null, updatedate   date   null )";
                db.execSQL(mit_valchecktype_m_temp);


                // 3.1 追溯采集项表
                String mit_valcheckitem_m = "create table MIT_VALCHECKITEM_M ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null,valchecktypeid varchar2(36) null,visitkey varchar2(36) null, valitemid varchar2(36) null, colitemkey  varchar2(36) null, checkkey varchar2(36) null, addcount varchar2(36) null, totalcount varchar2(36) null, productkey varchar2(36) null, valitem  varchar2(10) null, valitemval varchar2(10) null, valitemremark varchar2(300) null, creuser  varchar2(128) null, credate  date  null, updateuser varchar2(128) null, updatedate date  null )";
                db.execSQL(mit_valcheckitem_m);
                // 3.2 追溯采集项表 临时表
                String mit_valcheckitem_m_temp = "create table MIT_VALCHECKITEM_M_TEMP ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null,valchecktypeid varchar2(36) null, visitkey varchar2(36) null, valitemid varchar2(36) null, colitemkey  varchar2(36) null, checkkey varchar2(36) null, addcount varchar2(36) null, totalcount varchar2(36) null, productkey varchar2(36) null, valitem  varchar2(10) null, valitemval varchar2(10) null, valitemremark varchar2(300) null, creuser  varchar2(128) null, credate  date  null, updateuser varchar2(128) null, updatedate date  null )";
                db.execSQL(mit_valcheckitem_m_temp);

                // 4.1 追溯促销活动终端表
                String mit_valpromotions_m = "create table MIT_VALPROMOTIONS_M ( id varchar2(36) not null, padisconsistent  char(1)   null,valterid varchar2(36) null, valpromotionsid  varchar2(36) null, valistrue    char(1)    null,visitkey varchar2(36) null,terminalkey varchar2(36) null, valistruefalg    char(1)    null, valistrueval char(1)    null, valistruenum varchar2(10) null, valistruenumflag char(10)   null, valistruenumval  char(10)   null, creuser  varchar2(128)  null, credate  date   null, updateuser   varchar2(128)  null, updatedate   date   null )";
                db.execSQL(mit_valpromotions_m);

                // 4.2 追溯促销活动终端表临时表
                String mit_valpromotions_m_temp = "create table MIT_VALPROMOTIONS_M_TEMP ( id varchar2(36) not null, padisconsistent  char(1)   null,valterid varchar2(36) null, valpromotionsid  varchar2(36) null, valistrue    char(1)    null,visitkey varchar2(36) null,terminalkey varchar2(36) null, valistruefalg    char(1)    null, valistrueval char(1)    null, valistruenum varchar2(10) null, valistruenumflag char(10)   null, valistruenumval  char(10)   null, creuser  varchar2(128)  null, credate  date   null, updateuser   varchar2(128)  null, updatedate   date   null )";
                db.execSQL(mit_valpromotions_m_temp);

                // 5.1 追溯产品组合表
                String mit_valgrouppro_m = "create table MIT_VALGROUPPRO_M ( id varchar2(36) not null, gproductid varchar2(36) null,terminalcode varchar2(36) null,valterid varchar2(36) null, padisconsistent char(1) null, valgrouppro char(1) null, valgroupproflag char(1) null, valgroupproremark varchar2(300) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valgrouppro_m);

                // 5.2 追溯产品组合表 临时表
                String mit_valgrouppro_m_temp = "create table MIT_VALGROUPPRO_M_TEMP ( id varchar2(36) not null, gproductid varchar2(36) null,terminalcode varchar2(36) null,valterid varchar2(36) null, padisconsistent char(1) null, valgrouppro char(1) null, valgroupproflag char(1) null, valgroupproremark varchar2(300) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valgrouppro_m_temp);


                // 6.1 追溯供货关系
                String mit_valsupply_m = "create table MIT_VALSUPPLY_M ( id varchar2(36) not null,valagencyqdflag char(1) null,valagencylsflag char(1) null,valtrueagencyname varchar2(100)  null, padisconsistent  char(1)   null,valterid varchar2(36) null, valaddagencysupply char(1) null, valsuplyid varchar2(36) null, valsqd varchar2(10) null, valsls varchar2(10) null, valsdd varchar2(10) null, valsrxl  char(10) null, valsljk  varchar2(10) null, valter varchar2(36) null, valpro varchar2(36) null, valagency varchar2(36) null,valproname varchar2(36) null, valagencyname varchar2(36) null, valagencysupplyflag  char(1) null, valproerror  char(1) null, valagencyerror char(1) null,valtrueagency varchar2(10) null, valdataerror char(1) null, valiffleeing char(1) null,valiffleeingremark varchar2(300) null, valagencysupplyqd varchar2(10) null, valagencysupplyls varchar2(10) null, valagencysupplydd varchar2(10) null, valagencysupplysrxl  char(10) null, valagencysupplyljk varchar2(10) null, valagencysupplyremark varchar2(300)  null, creuser  varchar2(128)  null, credate  date null, updateuser varchar2(128)  null, updatedate date null )";
                db.execSQL(mit_valsupply_m);
                // 6.2 追溯供货关系
                String mit_valsupply_m_temp = "create table MIT_VALSUPPLY_M_TEMP ( id varchar2(36) not null,valagencyqdflag char(1) null,valagencylsflag char(1) null,valtrueagencyname varchar2(100)  null, padisconsistent  char(1)   null, valterid varchar2(36) null, valaddagencysupply char(1) null, valsuplyid varchar2(36) null, valsqd varchar2(10) null, valsls varchar2(10) null, valsdd varchar2(10) null, valsrxl  char(10) null, valsljk  varchar2(10) null, valter varchar2(36) null, valpro varchar2(36) null, valagency varchar2(36) null,valproname varchar2(36) null, valagencyname varchar2(36) null, valagencysupplyflag  char(1) null, valproerror  char(1) null, valagencyerror char(1) null,valtrueagency varchar2(10) null, valdataerror char(1) null, valiffleeing char(1) null,valiffleeingremark varchar2(300) null, valagencysupplyqd varchar2(10) null, valagencysupplyls varchar2(10) null, valagencysupplydd varchar2(10) null, valagencysupplysrxl  char(10) null, valagencysupplyljk varchar2(10) null, valagencysupplyremark varchar2(300)  null, creuser  varchar2(128)  null, credate  date null, updateuser varchar2(128)  null, updatedate date null )";
                db.execSQL(mit_valsupply_m_temp);

                // 7.1 追溯竞品供货关系
                String mit_valcmp_m = "create table MIT_VALCMP_M ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null, valaddagencysupply   char(1)    null, valagencysupplyid    varchar2(36) null, valcmpjdj    varchar2(10) null, valcmplsj    varchar2(10) null, valcmpsales  varchar2(10) null, valcmpkc varchar2(10) null, valcmpremark     varchar2(300)  null, valcmpagency     varchar2(300)  null, valcmpagencyval     varchar2(300)  null, valcmpname     varchar2(300)  null,valcmpid varchar2(36) null, valiscmpter  varchar2(36) null, valagencysupplyflag  char(1)    null, valproerror  char(1)    null, valagencyerror   char(1)    null, valdataerror     char(1)    null, valcmpjdjval     varchar2(10) null, valcmplsjval     varchar2(10) null, valcmpsalesval   varchar2(10) null, valcmpkcval  varchar2(10) null, valcmpsupremark  varchar2(300)  null, valcmpremarkval  varchar2(300)  null, valistrueflag    char(1)    null, valistruecmpval  char(1)    null, valiscmpremark   varchar2(300)  null, valvisitremark   varchar2(300)  null,creuser  varchar2(128) null, credate  date  null, updateuser varchar2(128) null, updatedate date  null )";
                db.execSQL(mit_valcmp_m);
                // 7.2 追溯竞品供货关系
                String mit_valcmp_m_temp = "create table MIT_VALCMP_M_TEMP ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null, valaddagencysupply   char(1)    null, valagencysupplyid    varchar2(36) null, valcmpjdj    varchar2(10) null, valcmplsj    varchar2(10) null, valcmpsales  varchar2(10) null, valcmpkc varchar2(10) null, valcmpremark     varchar2(300)  null, valcmpagency     varchar2(300)  null,valcmpagencyval     varchar2(300)  null, valcmpname     varchar2(300)  null, valcmpid varchar2(36) null, valiscmpter  varchar2(36) null, valagencysupplyflag  char(1)    null, valproerror  char(1)    null, valagencyerror   char(1)    null, valdataerror     char(1)    null, valcmpjdjval     varchar2(10) null, valcmplsjval     varchar2(10) null, valcmpsalesval   varchar2(10) null, valcmpkcval  varchar2(10) null, valcmpsupremark  varchar2(300)  null, valcmpremarkval  varchar2(300)  null, valistrueflag    char(1)    null, valistruecmpval  char(1)    null, valiscmpremark   varchar2(300)  null, valvisitremark   varchar2(300)  null,creuser  varchar2(128) null, credate  date  null, updateuser varchar2(128) null, updatedate date  null )";
                db.execSQL(mit_valcmp_m_temp);

                // 8.1 终端追溯竞品附表
                String mit_valcmpother_m = "create table MIT_VALCMPOTHER_M ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null, valistrueflag char(1) null, valistruecmpval char(1) null, valiscmpremark varchar2(300) null, valvisitremark varchar2(300) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valcmpother_m);
                // 8.2 终端追溯竞品附表 临时表
                String mit_valcmpother_m_temp = "create table MIT_VALCMPOTHER_M_TEMP ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null, valistrueflag char(1) null, valistruecmpval char(1) null, valiscmpremark varchar2(300) null, valvisitremark varchar2(300) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null );";
                db.execSQL(mit_valcmpother_m_temp);

                // 9.1 终端追溯图片表
                String mit_valpic_m = "create table MIT_VALPIC_M ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null,areapid varchar2(36) null,terminalname varchar2(36) null,cameradata varchar2(36) null,imagefileString varchar2(4000) null, pictypekey varchar2(36) null,pictypename varchar2(300) null, picname varchar2(36) null, picpath varchar2(36) null, areaid varchar2(36) null, gridkey varchar2(36) null, routekey varchar2(36) null, terminalkey varchar2(36) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valpic_m);
                // 9.2 终端追溯图片表 临时表
                String mit_valpic_m_temp = "create table MIT_VALPIC_M_TEMP ( id varchar2(36) not null,padisconsistent  char(1)   null, valterid varchar2(36) null,areapid varchar2(36) null,terminalname varchar2(36) null,cameradata varchar2(36) null,imagefileString varchar2(4000) null, pictypekey varchar2(36) null, pictypename varchar2(300) null,picname varchar2(36) null, picpath varchar2(36) null, areaid varchar2(36) null, gridkey varchar2(36) null, routekey varchar2(36) null, terminalkey varchar2(36) null, creuser varchar2(128) null, credate date null, updateuser varchar2(128) null, updatedate date null )";
                db.execSQL(mit_valpic_m_temp);

                // 获取各终端     每天    终端追溯的最新的拜访记录
                //StringBuffer buffer = new StringBuffer();
                buffer = new StringBuffer();
                buffer.append("drop view if exists v_mit_valter_m");
                db.execSQL(buffer.toString());
                buffer = new StringBuffer();
                buffer.append("create view IF NOT EXISTS v_mit_valter_m as ");
                buffer.append("select m.* from mit_valter_m m  ");
                buffer.append("inner join (select max(visitdate) maxvisitdate, ");
                buffer.append("    max(terminalkey) maxterminalkey ");
                buffer.append("    from mit_valter_m where visitdate is not null group by terminalkey, substr(visitdate,1,8)) v ");
                buffer.append("  on m.terminalkey = v.maxterminalkey  ");
                buffer.append("    and m.visitdate = v.maxvisitdate");
                db.execSQL(buffer.toString());

                // 获取各终端    所有    终端追溯的拜访数据中最新拜访记录
                buffer = new StringBuffer();
                buffer.append("drop view if exists v_mit_valter_m_newest");
                db.execSQL(buffer.toString());
                buffer = new StringBuffer();
                buffer.append("create view IF NOT EXISTS v_mit_valter_m_newest as ");
                buffer.append("select m.* from mit_valter_m m  ");
                buffer.append("inner join (select max(visitdate) maxvisitdate, ");
                buffer.append("    max(terminalkey) maxterminalkey ");
                buffer.append("    from mit_valter_m where visitdate is not null group by terminalkey) v ");
                buffer.append("  on m.terminalkey = v.maxterminalkey  ");
                buffer.append("    and m.visitdate = v.maxvisitdate");
                db.execSQL(buffer.toString());

                // 经销商库存盘点主表
                String MIT_AGENCYNUM_M = "create table MIT_AGENCYNUM_M ( id varchar2(36) not null, agencyid varchar2(36) null,agencyareaid varchar2(36) null, creuser varchar2(128) null, creuserareaid varchar(36) null, credate date  null, updateuser  varchar2(128) null, updatedate  date  null )";
                db.execSQL(MIT_AGENCYNUM_M);
                // 经销商判断产品表
                String MIT_AGENCYPRO_M = "create table MIT_AGENCYPRO_M (  id varchar2(36) not null, agencynumid varchar2(36) null, proid varchar2(36) null, stocktotal varchar2(36) null, stockfact  varchar2(36) null, remark  varchar2(300) null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_AGENCYPRO_M);
                // 督导新增终端表
                String MIT_TERMINAL_M = "create table MIT_TERMINAL_M ( id varchar(36) not null, terminalkey varchar(36) null, terminalcode varchar(36) null, terminalname varchar(200) null, routekey varchar(36) null, areatype varchar(36) null, tlevel  varchar(36) null, province varchar2(36) null, city varchar2(36) null, county  varchar2(36) null, address varchar2(250) null, contact varchar2(50) null, mobile  varchar2(100) null, sequence varchar2(10) null, cycle  varchar2(36) null, hvolume varchar2(10) null, zvolume varchar2(10) null, pvolume varchar2(10) null, dvolume varchar2(10) null, sellchannel varchar2(36) null, mainchannel varchar2(36) null, minorchannel varchar2(36) null, creuser varchar2(128) null, creuserareaid  varchar(36) null, credate date  null, updateuser  varchar2(128) null, updatedate  date  null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_TERMINAL_M);
                // 经销商开发核查表
                String MIT_VALAGENCYKF_M = "create table MIT_VALAGENCYKF_M   ( id varchar2(36) not null, gridkey varchar2(36) null, agencyname varchar2(200) null, agencynameflag char(1)  null, agencyrealname varchar2(200) null, agencynameremark varchar2(300) null,persion varchar2(200) null, persionflag char(1)  null, persionreal varchar2(200) null, persionremark varchar2(300) null, contact varchar2(200) null, contactflag  char(1)  null, contactreal  varchar2(200) null, contactremark  varchar2(300) null, mobile  varchar2(50) null, mobileflag char(1)  null, mobilereal varchar2(50) null, mobileremark   varchar2(300) null, address varchar2(300) null, addressflag  char(1)  null, addressreal  varchar2(300) null, addressremark  varchar2(300) null, area  number null, areaflag   char(1)  null, areareal   number null, arearemark varchar2(300) null, money number null, moneyflag  char(1)  null, moneyreal  number null, moneyremark  varchar2(300) null, carnum  number null, carnumflag char(1)  null, carnumreal number null, carnumremark   varchar2(300) null, productname  varchar2(500) null, productnameflag  char(1)  null, productnamereal  varchar2(500) null, productnameremark  varchar2(300) null, business   varchar2(300) null, businessflag   char(1)  null, businessreal   varchar2(300) null, businessremark varchar2(300) null, status  char(1)  null, statusflag char(1)  null, statusremark   varchar2(300) null, coverterms varchar2(600) null, covertermflag  char(1)  null, covertermreal  varchar2(600) null, covertermremark  varchar2(300) null, supplyterms  varchar2(600) null, supplytermsflag  char(1)  null, supplytermsreal  varchar2(600) null, supplytermsremark  varchar2(300) null, kfdate  date null, kfdateflag char(1)  null, kfdatereal date null, kfdateremark   varchar2(300) null, passdate   date null, passdateflag   char(1)  null, passdatereal   date null, passdateremark varchar2(300) null, remark  varchar2(300) null, creuser varchar2(128) null, creuserareaid  varchar(36)  null, credate date null, updateuser varchar2(128) null, updatedate date null, uploadflag varchar2(1) null,  padisconsistent varchar2(1) null )";
                db.execSQL(MIT_VALAGENCYKF_M);


                // 终端进货台账主表
                String MIT_VALADDACCOUNT_M = "create table MIT_VALADDACCOUNT_M ( id varchar2(36) not null, valsupplyid varchar2(36) null, valagencyid varchar2(36) null, valagencyname varchar2(36) null, valterid    varchar2(36) null, valtername   varchar2(36) null, valproid    varchar2(36) null, valproname    varchar2(36) null, valprostatus   char(1) null, creuser varchar2(128)  null, credate date   null, updateuser  varchar2(128)  null, updatedate  date   null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_VALADDACCOUNT_M);
                // 终端进货台账主表
                String MIT_VALADDACCOUNT_M_TEMP = "create table MIT_VALADDACCOUNT_M_TEMP ( id varchar2(36) not null, valsupplyid varchar2(36) null, valagencyid varchar2(36) null, valagencyname varchar2(36) null, valterid    varchar2(36) null, valtername   varchar2(36) null, valproid    varchar2(36) null, valproname    varchar2(36) null, valprostatus   char(1) null, creuser varchar2(128)  null, credate date   null, updateuser  varchar2(128)  null, updatedate  date   null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_VALADDACCOUNT_M_TEMP);
                // 终端追溯台账产品详情表
                String MIT_VALADDACCOUNTPRO_M = "create table MIT_VALADDACCOUNTPRO_M ( id varchar2(36) not null,valterid varchar2(36) null, valaddaccountid varchar2(36) null, valprotime  varchar2(36) null, valpronumfalg  char(1) null, valpronum   varchar2(10) null, valprotruenum  varchar2(10) null, valproremark   varchar2(300)  null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_VALADDACCOUNTPRO_M);
                // 终端追溯台账产品详情表
                String MIT_VALADDACCOUNTPRO_M_TEMP = "create table MIT_VALADDACCOUNTPRO_M_TEMP ( id varchar2(36) not null,valterid varchar2(36) null, valaddaccountid varchar2(36) null, valprotime  varchar2(36) null, valpronumfalg  char(1) null, valpronum   varchar2(10) null, valprotruenum  varchar2(10) null, valproremark   varchar2(300)  null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_VALADDACCOUNTPRO_M_TEMP);


                // 周计划主表
                String MIT_PLANWEEK_M = "create table MIT_PLANWEEK_M ( id varchar(36) not null, status char(1) null, starttime  varchar(36) null, endtime varchar(36) null, creuser varchar2(128) null, creuserareaid varchar(36) null, credate date null, updateuser varchar2(128) null, updatedate date null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_PLANWEEK_M);
                // 日计划主表
                String MIT_PLANDAY_M = "create table MIT_PLANDAY_M   ( id varchar(36) not null, planweekid varchar(36) null, status char(1) null,plandate   date   null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_PLANDAY_M);
                // 详细日计划
                String MIT_PLANDAYDETAIL_M = "create table MIT_PLANDAYDETAIL_M   ( id varchar(36) not null, planweekid varchar(36) null, plandayid  varchar(36) null, planareaid varchar(36) null, plangridid varchar(36) null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_PLANDAYDETAIL_M);
                // 日计划追溯项主表
                String MIT_PLANDAYVAL_M = "create table MIT_PLANDAYVAL_M   ( id varchar(36) not null, planweekid varchar(36) null, plandaydetailid  varchar(36) null,planareaid varchar(36) null, plangridid varchar(36) null, plancomtype  char(1)    null, plancomid  varchar(36) null, uploadflag varchar2(1) null, padisconsistent varchar2(1) null )";
                db.execSQL(MIT_PLANDAYVAL_M);


                // 整改计划主表
                String MIT_REPAIR_M = "create table MIT_REPAIR_M ( id varchar2(36) not null, status char(1) null,gridkey  varchar2(36) null, userid varchar2(36) null, content  varchar2(500) null, repairremark varchar2(500) null, checkcontent varchar2(500) null, creuser  varchar2(128) null, creuserareaid varchar(36)  null, credate  varchar2(36) null, updateuser varchar2(128) null, updatedate varchar2(36) null, uploadflag  varchar2(1) null, padisconsistent  varchar2(1) null )";
                db.execSQL(MIT_REPAIR_M);
                // 整改计划终端表
                String MIT_REPAIRTER_M = "create table MIT_REPAIRTER_M ( id varchar2(36) not null, repairid varchar2(36) null, gridkey  varchar2(36) null, routekey varchar2(36) null, terminalkey  varchar2(36) null,terminalname varchar2(100) null, uploadflag  varchar2(1) null, padisconsistent  varchar2(1) null )";
                db.execSQL(MIT_REPAIRTER_M);
                // 整改计划审核表
                String MIT_REPAIRCHECK_M = "create table MIT_REPAIRCHECK_M ( id varchar2(36) not null, repairid varchar2(36) null, status char(1) null, repairtime date null, uploadflag  varchar2(1) null,credate varchar2(36) null, padisconsistent  varchar2(1) null )";
                db.execSQL(MIT_REPAIRCHECK_M);

                // 在定格表新加字段 业代名称 username  整顿计划显示要用到
                db.execSQL("alter table MST_GRID_M add username varchar(100)");
                db.execSQL("alter table MST_GRID_M add userid varchar(100)");


                // 协议产品详情
                String MST_AGREE_DETAIL_TMP = "create table MST_AGREE_DETAIL_TMP ( id varchar2(36) not null, amount varchar2(36) null, terminalkey varchar2(36) null,agreeid varchar2(36) null,cashdate varchar2(36) null, cashtype  varchar2(36) null, price  varchar2(36) null, proname varchar2(300) null, productkey  varchar2(36) null,qty  varchar2(36) null )";
                db.execSQL(MST_AGREE_DETAIL_TMP);
                // 协议表
                String MST_AGREE_TMP = "create table MST_AGREE_TMP ( id varchar2(36) not null, agencyname varchar2(128) null, agreecd varchar2(128) null, dealid  varchar2(36) null, enddate  varchar2(36) null, mobile varchar2(36) null, notes varchar2(2000) null,terminalkey varchar2(36) null, paymentagencyname  varchar2(128) null, paytype  varchar2(36) null, signer varchar2(36) null, startdate varchar2(36) null, suppierid  varchar2(36) null )";
                db.execSQL(MST_AGREE_TMP);


                // 终端追溯协议主表
                String MIT_VALAGREE_M = "create table MIT_VALAGREE_M ( id varchar2(36) not null, valterid varchar2(36) null, agreecode  varchar2(128) null, agencyname varchar2(128) null, agencykey  varchar2(36) null, moneyagency  varchar2(128) null, moneyagencykey  varchar2(36) null, startdate  date null, startdateflag char(1) null, startdateremark varchar2(500) null, enddate  date null, enddateflag  char(1) null, enddateremark varchar2(500) null, paytype  varchar2(36) null, contact  varchar2(50) null, mobile varchar2(50) null, notes varchar2(2000)  null, notesflag  char(1) null, notesremark  varchar2(500) null, creuser  varchar2(128) null, credate  date null, updateuser varchar2(128) null, updatedate date null, uploadflag char(1) null, padisconsistent char(1) null )";
                db.execSQL(MIT_VALAGREE_M);
                // 终端追溯协议主表 临时表
                String MIT_VALAGREE_M_TEMP = "create table MIT_VALAGREE_M_TEMP ( id varchar2(36) not null, valterid varchar2(36) null, agreecode  varchar2(128) null, agencyname varchar2(128) null, agencykey  varchar2(36) null, moneyagency  varchar2(128) null, moneyagencykey  varchar2(36) null, startdate  date null, startdateflag char(1) null, startdateremark varchar2(500) null, enddate  date null, enddateflag  char(1) null, enddateremark varchar2(500) null, paytype  varchar2(36) null, contact  varchar2(50) null, mobile varchar2(50) null, notes varchar2(2000)  null, notesflag  char(1) null, notesremark  varchar2(500) null, creuser  varchar2(128) null, credate  date null, updateuser varchar2(128) null, updatedate date null, uploadflag char(1) null, padisconsistent char(1) null )";
                db.execSQL(MIT_VALAGREE_M_TEMP);


                // 终端追溯协议对付信息表
                String MIT_VALAGREEDETAIL_M = "create table MIT_VALAGREEDETAIL_M ( id varchar2(36) not null, valterid varchar2(36) null, valagreeid varchar2(36) null, prokey varchar2(36) null, proname  varchar2(200) null,truepro  varchar2(200) null,truemoney  varchar2(22) null,truenum  varchar2(22) null, cashtype varchar2(36) null, commoney number(22,7) null, trunnum  number(22,7) null, price number(22,7) null, cashdate date null, agreedetailflag char(1) null, erroritem  char(1) null, remarks  varchar2(500) null, uploadflag char(1) null, padisconsistent char(1) null )";
                db.execSQL(MIT_VALAGREEDETAIL_M);
                // 终端追溯协议对付信息表 临时表
                String MIT_VALAGREEDETAIL_M_TEMP = "create table MIT_VALAGREEDETAIL_M_TEMP ( id varchar2(36) not null, valterid varchar2(36) null, valagreeid varchar2(36) null, prokey varchar2(36) null, proname  varchar2(200) null,truepro  varchar2(200) null,truemoney  varchar2(22) null,truenum  varchar2(22) null, cashtype varchar2(36) null, commoney number(22,7) null, trunnum  number(22,7) null, price number(22,7) null, cashdate date null, agreedetailflag char(1) null, erroritem  char(1) null, remarks  varchar2(500) null, uploadflag char(1) null, padisconsistent char(1) null )";
                db.execSQL(MIT_VALAGREEDETAIL_M_TEMP);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //		try {
        //			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
        //			TableUtils.dropTable(connectionSource, CmmAreaM.class, true);
        //			TableUtils.dropTable(connectionSource, CmmDatadicM.class, true);
        //			TableUtils.dropTable(connectionSource, MstAgencygridInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstAgencyinfoM.class, true);
        //			TableUtils.dropTable(connectionSource, MstAgencysupplyInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstAgencytransferInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstAgencyvisitM.class, true);
        //			TableUtils.dropTable(connectionSource, MstBrandsclassM.class, true);
        //			TableUtils.dropTable(connectionSource, MstBrandseriesM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCenterdetailsM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCenterM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCheckaccomplishInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCheckcollectionInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCheckexerecordInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCheckstatusInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstChecktypeM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmpareaInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmpbrandsM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmpcompanyM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmproductinfoM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCollectionitemM.class, true);
        //			TableUtils.dropTable(connectionSource, MstGridM.class, true);
        //			TableUtils.dropTable(connectionSource, MstInvalidapplayInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstInvoicingInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstMarketareaM.class, true);
        //			TableUtils.dropTable(connectionSource, MstPricedetailsInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPriceM.class, true);
        //			TableUtils.dropTable(connectionSource, MstProductareaInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstProductM.class, true);
        //			TableUtils.dropTable(connectionSource, MstPromoproductInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPromotermInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPromotionsM.class, true);
        //			TableUtils.dropTable(connectionSource, MstPromotionstypeM.class, true);
        //			TableUtils.dropTable(connectionSource, MstRouteM.class, true);
        //			TableUtils.dropTable(connectionSource, MstShipmentledgerInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstTerminalinfoM.class, true);
        //			TableUtils.dropTable(connectionSource, MstVisitauthorizeInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstVisitM.class, true);
        //			TableUtils.dropTable(connectionSource, MstVisitmemoInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstVistproductInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstWorksummaryInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstSynckvM.class, true);
        //			TableUtils.dropTable(connectionSource, MstQuestionsanswersInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCollectionexerecordInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlanforuserM.class, true);
        //			TableUtils.dropTable(connectionSource, PadCheckaccomplishInfo.class, true);
        //			TableUtils.dropTable(connectionSource, PadCheckstatusInfo.class, true);
        //			TableUtils.dropTable(connectionSource, PadChecktypeM.class, true);
        //			TableUtils.dropTable(connectionSource, PadPlantempcheckM.class, true);
        //			TableUtils.dropTable(connectionSource, PadPlantempcollectionInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlantemplateM.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlantempcheckInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlantempcollectionInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlancheckInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlancollectionInfo.class, true);
        //			TableUtils.dropTable(connectionSource, CmmBoardM.class, true);
        //			TableUtils.dropTable(connectionSource, MstCheckmiddleInfo.class, true);
        //			TableUtils.dropTable(connectionSource, PadCheckproInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPromomiddleInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPowerfulchannelInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPowerfulterminalInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmpsupplyInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstPlanrouteInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstMonthtargetInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstCmpagencyInfo.class, true);
        //			TableUtils.dropTable(connectionSource, MstProductshowM.class, true);
        //			TableUtils.dropTable(connectionSource, MstShowpicInfo.class, true);
        //			onCreate(db, connectionSource);
        //		} catch (SQLException e) {
        //			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
        //			throw new RuntimeException(e);
        //		}
    }

    @Override
    public void close() {
        super.close();
    }

    public boolean deleteDataBase(Context ctx) {
        return ctx.deleteDatabase(DATABASE_NAME);
    }

    public Dao<CmmAreaM, String> getCmmAreaMDao() throws SQLException {

        if (cmmAreaMDao == null) {
            cmmAreaMDao = getDao(CmmAreaM.class);
        }
        return cmmAreaMDao;
    }

    public Dao<CmmDatadicM, String> getCmmDatadicMDao() throws SQLException {

        if (cmmDatadicMDao == null) {
            cmmDatadicMDao = getDao(CmmDatadicM.class);
        }
        return cmmDatadicMDao;
    }

    public Dao<MstAgencygridInfo, String> getMstAgencygridInfoDao() throws SQLException {

        if (mstAgencygridInfoDao == null) {
            mstAgencygridInfoDao = getDao(MstAgencygridInfo.class);
        }
        return mstAgencygridInfoDao;
    }

    public Dao<MstAgencyinfoM, String> getMstAgencyinfoMDao() throws SQLException {

        if (mstAgencyinfoMDao == null) {
            mstAgencyinfoMDao = getDao(MstAgencyinfoM.class);
        }
        return mstAgencyinfoMDao;
    }

    public Dao<MstAgencysupplyInfo, String> getMstAgencysupplyInfoDao() throws SQLException {

        if (mstAgencysupplyInfoDao == null) {
            mstAgencysupplyInfoDao = getDao(MstAgencysupplyInfo.class);
        }
        return mstAgencysupplyInfoDao;
    }

    public Dao<MstAgencysupplyInfoTemp, String> getMstAgencysupplyInfoTempDao() throws SQLException {

        if (mstAgencysupplyInfoTempDao == null) {
            mstAgencysupplyInfoTempDao = getDao(MstAgencysupplyInfoTemp.class);
        }
        return mstAgencysupplyInfoTempDao;
    }

    public Dao<MitAgencysupplyInfo, String> getMitAgencysupplyInfoDao() throws SQLException {

        if (mitAgencysupplyInfoDao == null) {
            mitAgencysupplyInfoDao = getDao(MitAgencysupplyInfo.class);
        }
        return mitAgencysupplyInfoDao;
    }

    public Dao<MitValcmpM, String> getMitValcmpMDao() throws SQLException {

        if (mitValcmpMDao == null) {
            mitValcmpMDao = getDao(MitValcmpM.class);
        }
        return mitValcmpMDao;
    }

    public Dao<MitValcmpMTemp, String> getMitValcmpMTempDao() throws SQLException {

        if (mitValcmpMTempDao == null) {
            mitValcmpMTempDao = getDao(MitValcmpMTemp.class);
        }
        return mitValcmpMTempDao;
    }

    public Dao<MitValcheckterM, String> getMitValcheckterMDao() throws SQLException {

        if (mitValcheckterMDao == null) {
            mitValcheckterMDao = getDao(MitValcheckterM.class);
        }
        return mitValcheckterMDao;
    }


    public Dao<MitValgroupproM, String> getMitValgroupproMDao() throws SQLException {

        if (mitValgroupproMDao == null) {
            mitValgroupproMDao = getDao(MitValgroupproM.class);
        }
        return mitValgroupproMDao;
    }

    public Dao<MitValgroupproMTemp, String> getMitValgroupproMTempDao() throws SQLException {

        if (mitValgroupproMTempDao == null) {
            mitValgroupproMTempDao = getDao(MitValgroupproMTemp.class);
        }
        return mitValgroupproMTempDao;
    }


    public Dao<MitValpicM, String> getMitValpicMDao() throws SQLException {

        if (mitValpicMDao == null) {
            mitValpicMDao = getDao(MitValpicM.class);
        }
        return mitValpicMDao;
    }

    public Dao<MitValpicMTemp, String> getMitValpicMTempDao() throws SQLException {

        if (mitValpicMTempDao == null) {
            mitValpicMTempDao = getDao(MitValpicMTemp.class);
        }
        return mitValpicMTempDao;
    }


    public Dao<MitValcmpotherM, String> getMitValcmpotherMDao() throws SQLException {

        if (mitValcmpotherMDao == null) {
            mitValcmpotherMDao = getDao(MitValcmpotherM.class);
        }
        return mitValcmpotherMDao;
    }

    public Dao<MitValcmpotherMTemp, String> getMitValcmpotherMTempDao() throws SQLException {

        if (mitValcmpotherMTempDao == null) {
            mitValcmpotherMTempDao = getDao(MitValcmpotherMTemp.class);
        }
        return mitValcmpotherMTempDao;
    }

    public Dao<MitValcheckitemM, String> getMitValcheckitemMDao() throws SQLException {

        if (mitValcheckitemMDao == null) {
            mitValcheckitemMDao = getDao(MitValcheckitemM.class);
        }
        return mitValcheckitemMDao;
    }

    public Dao<MitValcheckitemMTemp, String> getMitValcheckitemMTempDao() throws SQLException {

        if (mitValcheckitemMTempDao == null) {
            mitValcheckitemMTempDao = getDao(MitValcheckitemMTemp.class);
        }
        return mitValcheckitemMTempDao;
    }

    public Dao<MitValchecktypeM, String> getMitValchecktypeMDao() throws SQLException {

        if (mitValchecktypeMDao == null) {
            mitValchecktypeMDao = getDao(MitValchecktypeM.class);
        }
        return mitValchecktypeMDao;
    }

    public Dao<MitValchecktypeMTemp, String> getMitValchecktypeMTempDao() throws SQLException {

        if (mitValchecktypeMTempDao == null) {
            mitValchecktypeMTempDao = getDao(MitValchecktypeMTemp.class);
        }
        return mitValchecktypeMTempDao;
    }

    public Dao<MitValpromotionsM, String> getMitValpromotionsMDao() throws SQLException {

        if (mitValpromotionsMDao == null) {
            mitValpromotionsMDao = getDao(MitValpromotionsM.class);
        }
        return mitValpromotionsMDao;
    }

    public Dao<MitValpromotionsMTemp, String> getMitValpromotionsMTempDao() throws SQLException {

        if (mitValpromotionsMTempDao == null) {
            mitValpromotionsMTempDao = getDao(MitValpromotionsMTemp.class);
        }
        return mitValpromotionsMTempDao;
    }


    public Dao<MstAgencytransferInfo, String> getMstAgencytransferInfoDao() throws SQLException {

        if (mstAgencytransferInfoDao == null) {
            mstAgencytransferInfoDao = getDao(MstAgencytransferInfo.class);
        }
        return mstAgencytransferInfoDao;
    }

    public Dao<MstAgencyvisitM, String> getMstAgencyvisitMDao() throws SQLException {

        if (mstAgencyvisitMDao == null) {
            mstAgencyvisitMDao = getDao(MstAgencyvisitM.class);
        }
        return mstAgencyvisitMDao;
    }

    public Dao<MstBrandsclassM, String> getMstBrandsclassMDao() throws SQLException {

        if (mstBrandsclassMDao == null) {
            mstBrandsclassMDao = getDao(MstBrandsclassM.class);
        }
        return mstBrandsclassMDao;
    }

    public Dao<MstBrandseriesM, String> getMstBrandseriesMDao() throws SQLException {

        if (mstBrandseriesMDao == null) {
            mstBrandseriesMDao = getDao(MstBrandseriesM.class);
        }
        return mstBrandseriesMDao;
    }

    public Dao<MstCenterdetailsM, String> getMstCenterdetailsMDao() throws SQLException {

        if (mstCenterdetailsMDao == null) {
            mstCenterdetailsMDao = getDao(MstCenterdetailsM.class);
        }
        return mstCenterdetailsMDao;
    }

    public Dao<MstCenterM, String> getMstCenterMDao() throws SQLException {

        if (mstCenterMDao == null) {
            mstCenterMDao = getDao(MstCenterM.class);
        }
        return mstCenterMDao;
    }

    public Dao<MstCheckaccomplishInfo, String> getMstCheckaccomplishInfoDao() throws SQLException {

        if (mstCheckaccomplishInfoDao == null) {
            mstCheckaccomplishInfoDao = getDao(MstCheckaccomplishInfo.class);
        }
        return mstCheckaccomplishInfoDao;
    }

    public Dao<MstCheckcollectionInfo, String> getMstCheckcollectionInfoDao() throws SQLException {

        if (mstCheckcollectionInfoDao == null) {
            mstCheckcollectionInfoDao = getDao(MstCheckcollectionInfo.class);
        }
        return mstCheckcollectionInfoDao;
    }

    public Dao<MstCheckexerecordInfo, String> getMstCheckexerecordInfoDao() throws SQLException {

        if (mstCheckexerecordInfoDao == null) {
            mstCheckexerecordInfoDao = getDao(MstCheckexerecordInfo.class);
        }
        return mstCheckexerecordInfoDao;
    }

    public Dao<MstCheckexerecordInfoTemp, String> getMstCheckexerecordInfoTempDao() throws SQLException {

        if (mstCheckexerecordInfoTempDao == null) {
            mstCheckexerecordInfoTempDao = getDao(MstCheckexerecordInfoTemp.class);
        }
        return mstCheckexerecordInfoTempDao;
    }

    public Dao<MitCheckexerecordInfo, String> getMitCheckexerecordInfoDao() throws SQLException {

        if (mitCheckexerecordInfoDao == null) {
            mitCheckexerecordInfoDao = getDao(MitCheckexerecordInfo.class);
        }
        return mitCheckexerecordInfoDao;
    }

    public Dao<MstCheckstatusInfo, String> getMstCheckstatusInfoDao() throws SQLException {

        if (mstCheckstatusInfoDao == null) {
            mstCheckstatusInfoDao = getDao(MstCheckstatusInfo.class);
        }
        return mstCheckstatusInfoDao;
    }

    public Dao<MstChecktypeM, String> getMstChecktypeMDao() throws SQLException {

        if (mstChecktypeMDao == null) {
            mstChecktypeMDao = getDao(MstChecktypeM.class);
        }
        return mstChecktypeMDao;
    }

    public Dao<MstCmpareaInfo, String> getMstCmpareaInfoDao() throws SQLException {

        if (mstCmpareaInfoDao == null) {
            mstCmpareaInfoDao = getDao(MstCmpareaInfo.class);
        }
        return mstCmpareaInfoDao;
    }

    public Dao<MstCmpbrandsM, String> getMstCmpbrandsMDao() throws SQLException {

        if (mstCmpbrandsMDao == null) {
            mstCmpbrandsMDao = getDao(MstCmpbrandsM.class);
        }
        return mstCmpbrandsMDao;
    }

    public Dao<MstCmpcompanyM, String> getMstCmpcompanyMDao() throws SQLException {

        if (mstCmpcompanyMDao == null) {
            mstCmpcompanyMDao = getDao(MstCmpcompanyM.class);
        }
        return mstCmpcompanyMDao;
    }

    public Dao<MstCmproductinfoM, String> getMstCmproductinfoMDao() throws SQLException {

        if (mstCmproductinfoMDao == null) {
            mstCmproductinfoMDao = getDao(MstCmproductinfoM.class);
        }
        return mstCmproductinfoMDao;
    }

    public Dao<MstCollectionitemM, String> getMstCollectionitemMDao() throws SQLException {

        if (mstCollectionitemMDao == null) {
            mstCollectionitemMDao = getDao(MstCollectionitemM.class);
        }
        return mstCollectionitemMDao;
    }

    public Dao<MstGridM, String> getMstGridMDao() throws SQLException {

        if (mstGridMDao == null) {
            mstGridMDao = getDao(MstGridM.class);
        }
        return mstGridMDao;
    }

    public Dao<MstInvalidapplayInfo, String> getMstInvalidapplayInfoDao() throws SQLException {

        if (mstInvalidapplayInfoDao == null) {
            mstInvalidapplayInfoDao = getDao(MstInvalidapplayInfo.class);
        }
        return mstInvalidapplayInfoDao;
    }

    public Dao<MstInvoicingInfo, String> getMstInvoicingInfoDao() throws SQLException {

        if (mstInvoicingInfoDao == null) {
            mstInvoicingInfoDao = getDao(MstInvoicingInfo.class);
        }
        return mstInvoicingInfoDao;
    }

    public Dao<MstMarketareaM, String> getMstMarketareaMDao() throws SQLException {

        if (mstMarketareaMDao == null) {
            mstMarketareaMDao = getDao(MstMarketareaM.class);
        }
        return mstMarketareaMDao;
    }

    public Dao<MstPricedetailsInfo, String> getMstPricedetailsInfoDao() throws SQLException {

        if (mstPricedetailsInfoDao == null) {
            mstPricedetailsInfoDao = getDao(MstPricedetailsInfo.class);
        }
        return mstPricedetailsInfoDao;
    }

    public Dao<MstPriceM, String> getMstPriceMDao() throws SQLException {

        if (mstPriceMDao == null) {
            mstPriceMDao = getDao(MstPriceM.class);
        }
        return mstPriceMDao;
    }

    public Dao<MstPictypeM, String> getMstpictypeMDao() throws SQLException {

        if (mstpictypeMDao == null) {
            mstpictypeMDao = getDao(MstPictypeM.class);
        }
        return mstpictypeMDao;
    }

    public Dao<MstProductareaInfo, String> getMstProductareaInfoDao() throws SQLException {

        if (mstProductareaInfoDao == null) {
            mstProductareaInfoDao = getDao(MstProductareaInfo.class);
        }
        return mstProductareaInfoDao;
    }

    public Dao<MstProductM, String> getMstProductMDao() throws SQLException {

        if (mstProductMDao == null) {
            mstProductMDao = getDao(MstProductM.class);
        }
        return mstProductMDao;
    }

    public Dao<MstPromoproductInfo, String> getMstPromoproductInfoDao() throws SQLException {

        if (mstPromoproductInfoDao == null) {
            mstPromoproductInfoDao = getDao(MstPromoproductInfo.class);
        }
        return mstPromoproductInfoDao;
    }

    public Dao<MstPromotermInfo, String> getMstPromotermInfoDao() throws SQLException {

        if (mstPromotermInfoDao == null) {
            mstPromotermInfoDao = getDao(MstPromotermInfo.class);
        }
        return mstPromotermInfoDao;
    }

    public Dao<MstPromotermInfoTemp, String> getMstPromotermInfoTempDao() throws SQLException {

        if (mstPromotermInfoTempDao == null) {
            mstPromotermInfoTempDao = getDao(MstPromotermInfoTemp.class);
        }
        return mstPromotermInfoTempDao;
    }

    public Dao<MitPromotermInfo, String> getMitPromotermInfoDao() throws SQLException {

        if (mitPromotermInfoDao == null) {
            mitPromotermInfoDao = getDao(MitPromotermInfo.class);
        }
        return mitPromotermInfoDao;
    }

    public Dao<MstPromotionsM, String> getMstPromotionsMDao() throws SQLException {

        if (mstPromotionsMDao == null) {
            mstPromotionsMDao = getDao(MstPromotionsM.class);
        }
        return mstPromotionsMDao;
    }

    public Dao<MstPromotionstypeM, String> getMstPromotionstypeMDao() throws SQLException {

        if (mstPromotionstypeMDao == null) {
            mstPromotionstypeMDao = getDao(MstPromotionstypeM.class);
        }
        return mstPromotionstypeMDao;
    }

    public Dao<MstRouteM, String> getMstRouteMDao() throws SQLException {

        if (mstRouteMDao == null) {
            mstRouteMDao = getDao(MstRouteM.class);
        }
        return mstRouteMDao;
    }

    public Dao<MstShipmentledgerInfo, String> getMstShipmentledgerInfoDao() throws SQLException {

        if (mstShipmentledgerInfoDao == null) {
            mstShipmentledgerInfoDao = getDao(MstShipmentledgerInfo.class);
        }
        return mstShipmentledgerInfoDao;
    }

    public Dao<MstTerminalinfoM, String> getMstTerminalinfoMDao() throws SQLException {

        if (mstTerminalinfoMDao == null) {
            mstTerminalinfoMDao = getDao(MstTerminalinfoM.class);
        }
        return mstTerminalinfoMDao;
    }

    public Dao<MstTerminalinfoMTemp, String> getMstTerminalinfoMTempDao() throws SQLException {

        if (mstTerminalinfoMTempDao == null) {
            mstTerminalinfoMTempDao = getDao(MstTerminalinfoMTemp.class);
        }
        return mstTerminalinfoMTempDao;
    }

    public Dao<MitTerminalinfoM, String> getMitTerminalinfoMDao() throws SQLException {

        if (mitTerminalinfoMDao == null) {
            mitTerminalinfoMDao = getDao(MitTerminalinfoM.class);
        }
        return mitTerminalinfoMDao;
    }

    public Dao<MstTerminalinfoMCart, String> getMstTerminalinfoMCartDao() throws SQLException {

        if (mstTerminalinfoMCartDao == null) {
            mstTerminalinfoMCartDao = getDao(MstTerminalinfoMCart.class);
        }
        return mstTerminalinfoMCartDao;
    }
    public Dao<MstTerminalinfoMZsCart, String> getMstTerminalinfoMZsCartDao() throws SQLException {

        if (mstTerminalinfoMZsCartDao == null) {
            mstTerminalinfoMZsCartDao = getDao(MstTerminalinfoMZsCart.class);
        }
        return mstTerminalinfoMZsCartDao;
    }

    public Dao<MstVisitauthorizeInfo, String> getMstVisitauthorizeInfoDao() throws SQLException {

        if (mstVisitauthorizeInfoDao == null) {
            mstVisitauthorizeInfoDao = getDao(MstVisitauthorizeInfo.class);
        }
        return mstVisitauthorizeInfoDao;
    }

    public Dao<MitValterMTemp, String> getMitValterMTempDao() throws SQLException {

        if (mitValterMTempDao == null) {
            mitValterMTempDao = getDao(MitValterMTemp.class);
        }
        return mitValterMTempDao;
    }

    public Dao<MitValterM, String> getMitValterMDao() throws SQLException {

        if (mitValterMDao == null) {
            mitValterMDao = getDao(MitValterM.class);
        }
        return mitValterMDao;
    }

    public Dao<MitValsupplyM, String> getMitValsupplyMDao() throws SQLException {

        if (mitValsupplyMDao == null) {
            mitValsupplyMDao = getDao(MitValsupplyM.class);
        }
        return mitValsupplyMDao;
    }

    public Dao<MitValsupplyMTemp, String> getMitValsupplyMTempDao() throws SQLException {

        if (mitValsupplyMTempDao == null) {
            mitValsupplyMTempDao = getDao(MitValsupplyMTemp.class);
        }
        return mitValsupplyMTempDao;
    }


    public Dao<MstVisitM, String> getMstVisitMDao() throws SQLException {

        if (mstVisitMDao == null) {
            mstVisitMDao = getDao(MstVisitM.class);
        }
        return mstVisitMDao;
    }

    public Dao<MitVisitM, String> getMitVisitMDao() throws SQLException {

        if (mitVisitMDao == null) {
            mitVisitMDao = getDao(MitVisitM.class);
        }
        return mitVisitMDao;
    }

    public Dao<MstVisitMTemp, String> getMstVisitMTempDao() throws SQLException {

        if (mstVisitMTempDao == null) {
            mstVisitMTempDao = getDao(MstVisitMTemp.class);
        }
        return mstVisitMTempDao;
    }

    public Dao<MstVisitmemoInfo, String> getMstVisitmemoInfoDao() throws SQLException {

        if (mstVisitmemoInfoDao == null) {
            mstVisitmemoInfoDao = getDao(MstVisitmemoInfo.class);
        }
        return mstVisitmemoInfoDao;
    }

    public Dao<MstVistproductInfo, String> getMstVistproductInfoDao() throws SQLException {

        if (mstVistproductInfoDao == null) {
            mstVistproductInfoDao = getDao(MstVistproductInfo.class);
        }
        return mstVistproductInfoDao;
    }

    public Dao<MstVistproductInfoTemp, String> getMstVistproductInfoTempDao() throws SQLException {

        if (mstVistproductInfoTempDao == null) {
            mstVistproductInfoTempDao = getDao(MstVistproductInfoTemp.class);
        }
        return mstVistproductInfoTempDao;
    }

    public Dao<MitVistproductInfo, String> getMitVistproductInfoDao() throws SQLException {

        if (mitVistproductInfoDao == null) {
            mitVistproductInfoDao = getDao(MitVistproductInfo.class);
        }
        return mitVistproductInfoDao;
    }

    public Dao<MstWorksummaryInfo, String> getMstWorksummaryInfoDao() throws SQLException {

        if (mstWorksummaryInfoDao == null) {
            mstWorksummaryInfoDao = getDao(MstWorksummaryInfo.class);
        }
        return mstWorksummaryInfoDao;
    }

    public Dao<MstSynckvM, String> getMstSynckvMDao() throws SQLException {

        if (mstSynckvMDao == null) {
            mstSynckvMDao = getDao(MstSynckvM.class);
        }
        return mstSynckvMDao;
    }

    public Dao<MstQuestionsanswersInfo, String> getMstQuestionsanswersInfoDao() throws SQLException {

        if (mstQuestionsanswersInfoDao == null) {
            mstQuestionsanswersInfoDao = getDao(MstQuestionsanswersInfo.class);
        }
        return mstQuestionsanswersInfoDao;
    }

    public Dao<MstCollectionexerecordInfo, String> getMstCollectionexerecordInfoDao() throws SQLException {

        if (mstCollectionexerecordInfoDao == null) {
            mstCollectionexerecordInfoDao = getDao(MstCollectionexerecordInfo.class);
        }
        return mstCollectionexerecordInfoDao;
    }

    public Dao<MstCollectionexerecordInfoTemp, String> getMstCollectionexerecordInfoTempDao() throws SQLException {

        if (mstCollectionexerecordInfoTempDao == null) {
            mstCollectionexerecordInfoTempDao = getDao(MstCollectionexerecordInfoTemp.class);
        }
        return mstCollectionexerecordInfoTempDao;
    }

    public Dao<MitCollectionexerecordInfo, String> getMitCollectionexerecordInfoDao() throws SQLException {

        if (mitCollectionexerecordInfoDao == null) {
            mitCollectionexerecordInfoDao = getDao(MitCollectionexerecordInfo.class);
        }
        return mitCollectionexerecordInfoDao;
    }

    public Dao<MstPlanforuserM, String> getMstPlanforuserMDao() throws SQLException {

        if (mstPlanforuserMDao == null) {
            mstPlanforuserMDao = getDao(MstPlanforuserM.class);
        }
        return mstPlanforuserMDao;
    }

    public Dao<PadCheckaccomplishInfo, String> getPadCheckaccomplishInfoDao() throws SQLException {

        if (padCheckaccomplishInfoDao == null) {
            padCheckaccomplishInfoDao = getDao(PadCheckaccomplishInfo.class);
        }
        return padCheckaccomplishInfoDao;
    }

    public Dao<PadCheckstatusInfo, String> getPadCheckstatusInfoDao() throws SQLException {

        if (padCheckstatusInfoDao == null) {
            padCheckstatusInfoDao = getDao(PadCheckstatusInfo.class);
        }
        return padCheckstatusInfoDao;
    }

    public Dao<PadChecktypeM, String> getPadChecktypeMDao() throws SQLException {

        if (padChecktypeMDao == null) {
            padChecktypeMDao = getDao(PadChecktypeM.class);
        }
        return padChecktypeMDao;
    }

    public Dao<PadPlantempcheckM, String> getPadPlantempcheckMDao() throws SQLException {

        if (padPlantempcheckMDao == null) {
            padPlantempcheckMDao = getDao(PadPlantempcheckM.class);
        }
        return padPlantempcheckMDao;
    }

    public Dao<PadPlantempcollectionInfo, String> getPadPlantempcollectionInfoDao() throws SQLException {

        if (padPlantempcollectionInfoDao == null) {
            padPlantempcollectionInfoDao = getDao(PadPlantempcollectionInfo.class);
        }
        return padPlantempcollectionInfoDao;
    }

    public Dao<MstPlantemplateM, String> getMstPlantemplateMDao() throws SQLException {

        if (mstPlantemplateMDao == null) {
            mstPlantemplateMDao = getDao(MstPlantemplateM.class);
        }
        return mstPlantemplateMDao;
    }

    public Dao<MstPlantempcheckInfo, String> getMstPlantempcheckInfoDao() throws SQLException {
        if (mstPlantempcheckInfoDao == null) {
            mstPlantempcheckInfoDao = getDao(MstPlantempcheckInfo.class);
        }
        return mstPlantempcheckInfoDao;
    }

    public Dao<MstPlantempcollectionInfo, String> getMstPlantempcollectionInfoDao() throws SQLException {
        if (mstPlantempcollectionInfoDao == null) {
            mstPlantempcollectionInfoDao = getDao(MstPlantempcollectionInfo.class);
        }
        return mstPlantempcollectionInfoDao;
    }

    public Dao<MstPlancheckInfo, String> getMstPlancheckInfoDao() throws SQLException {
        if (mstPlancheckInfoDao == null) {
            mstPlancheckInfoDao = getDao(MstPlancheckInfo.class);
        }
        return mstPlancheckInfoDao;
    }

    public Dao<MstPlancollectionInfo, String> getMstPlancollectionInfoDao() throws SQLException {
        if (mstPlancollectionInfoDao == null) {
            mstPlancollectionInfoDao = getDao(MstPlancollectionInfo.class);
        }
        return mstPlancollectionInfoDao;
    }

    public Dao<CmmBoardM, String> getCmmBoardMDao() throws SQLException {
        if (cmmBoardMDao == null) {
            cmmBoardMDao = getDao(CmmBoardM.class);
        }
        return cmmBoardMDao;
    }

    public Dao<MstCheckmiddleInfo, String> getMstCheckmiddleInfoDao() throws SQLException {
        if (mstCheckmiddleInfoDao == null) {
            mstCheckmiddleInfoDao = getDao(MstCheckmiddleInfo.class);
        }
        return mstCheckmiddleInfoDao;
    }

    public Dao<PadCheckproInfo, String> getPadCheckproInfoDao() throws SQLException {
        if (padCheckproInfoDao == null) {
            padCheckproInfoDao = getDao(PadCheckproInfo.class);
        }
        return padCheckproInfoDao;
    }

    public Dao<MstPromomiddleInfo, String> getMstPromomiddleInfoDao() throws SQLException {
        if (mstPromomiddleInfoDao == null) {
            mstPromomiddleInfoDao = getDao(MstPromomiddleInfo.class);
        }
        return mstPromomiddleInfoDao;
    }

    public Dao<MstPowerfulchannelInfo, String> getMstPowerfulchannelInfoDao() throws SQLException {
        if (mstPowerfulchannelInfoDao == null) {
            mstPowerfulchannelInfoDao = getDao(MstPowerfulchannelInfo.class);
        }
        return mstPowerfulchannelInfoDao;
    }

    public Dao<MstPowerfulterminalInfo, String> getMstPowerfulterminalInfoDao() throws SQLException {
        if (mstPowerfulterminalInfoDao == null) {
            mstPowerfulterminalInfoDao = getDao(MstPowerfulterminalInfo.class);
        }
        return mstPowerfulterminalInfoDao;
    }

    public Dao<MstCmpsupplyInfo, String> getMstCmpsupplyInfoDao() throws SQLException {
        if (mstCmpsupplyInfoDao == null) {
            mstCmpsupplyInfoDao = getDao(MstCmpsupplyInfo.class);
        }
        return mstCmpsupplyInfoDao;
    }

    public Dao<MstCmpsupplyInfoTemp, String> getMstCmpsupplyInfoTempDao() throws SQLException {
        if (mstCmpsupplyInfoTempDao == null) {
            mstCmpsupplyInfoTempDao = getDao(MstCmpsupplyInfoTemp.class);
        }
        return mstCmpsupplyInfoTempDao;
    }

    public Dao<MitCmpsupplyInfo, String> getMitCmpsupplyInfoDao() throws SQLException {
        if (mitCmpsupplyInfoDao == null) {
            mitCmpsupplyInfoDao = getDao(MitCmpsupplyInfo.class);
        }
        return mitCmpsupplyInfoDao;
    }

    public Dao<MstPlanrouteInfo, String> getMstPlanrouteInfoDao() throws SQLException {
        if (mstPlanrouteInfoDao == null) {
            mstPlanrouteInfoDao = getDao(MstPlanrouteInfo.class);
        }
        return mstPlanrouteInfoDao;
    }

    public Dao<MstMonthtargetInfo, String> getMstMonthtargetInfoDao() throws SQLException {
        if (mstMonthtargetInfoDao == null) {
            mstMonthtargetInfoDao = getDao(MstMonthtargetInfo.class);
        }
        return mstMonthtargetInfoDao;
    }

    public Dao<MstCmpagencyInfo, String> getMstCmpagencyInfoDao() throws SQLException {
        if (mstCmpagencyInfo == null) {
            mstCmpagencyInfo = getDao(MstCmpagencyInfo.class);
        }
        return mstCmpagencyInfo;
    }

    public Dao<MstProductshowM, String> getMstProductshowMDao() throws SQLException {
        if (mstProductshowMDao == null) {
            mstProductshowMDao = getDao(MstProductshowM.class);
        }
        return mstProductshowMDao;
    }

    public Dao<MstShowpicInfo, String> getMstShowpicInfoDao() throws SQLException {
        if (mstShowpicInfoDao == null) {
            mstShowpicInfoDao = getDao(MstShowpicInfo.class);
        }
        return mstShowpicInfoDao;
    }

    public Dao<MstPlanWeekforuserM, String> getMstPlanWeekforuserMDao() throws SQLException {
        if (mstPlanWeekforuserMDao == null) {
            mstPlanWeekforuserMDao = getDao(MstPlanWeekforuserM.class);
        }
        return mstPlanWeekforuserMDao;
    }

    public Dao<MstCameraInfoM, String> getMstCameraiInfoMDao() throws SQLException {
        if (mstCameraiInfoMDao == null) {
            mstCameraiInfoMDao = getDao(MstCameraInfoM.class);
        }
        return mstCameraiInfoMDao;
    }

    public Dao<MstCameraInfoMTemp, String> getMstCameraInfoMTempDao() throws SQLException {
        if (mstCameraiInfoMTempDao == null) {
            mstCameraiInfoMTempDao = getDao(MstCameraInfoMTemp.class);
        }
        return mstCameraiInfoMTempDao;
    }

    public Dao<MitCameraInfoM, String> getMitCameraInfoMDao() throws SQLException {
        if (mitCameraInfoMDao == null) {
            mitCameraInfoMDao = getDao(MitCameraInfoM.class);
        }
        return mitCameraInfoMDao;
    }


    public Dao<MstAgencyKFM, String> getMstAgencyKFMDao() throws SQLException {
        if (mstAgencyKFMDao == null) {
            mstAgencyKFMDao = getDao(MstAgencyKFM.class);
        }
        return mstAgencyKFMDao;
    }


    public Dao<MstTermLedgerInfo, String> getTermLedgerInfoDao() throws SQLException {
        if (mstTermLedgerInfoDao == null) {
            mstTermLedgerInfoDao = getDao(MstTermLedgerInfo.class);
        }
        return mstTermLedgerInfoDao;
    }

    public Dao<MstPlanTerminalM, String> getMstPlanTerminalM() throws SQLException {
        if (mstPlanTerminalMDao == null) {
            mstPlanTerminalMDao = getDao(MstPlanTerminalM.class);
        }
        return mstPlanTerminalMDao;
    }

    public Dao<MstBsData, String> getMstBsDataDao() throws SQLException {

        if (mstBsDataDao == null) {
            mstBsDataDao = getDao(MstBsData.class);
        }
        return mstBsDataDao;
    }

    public Dao<MstGroupproductM, String> getMstGroupproductMDao() throws SQLException {

        if (mstGroupproductMDao == null) {
            mstGroupproductMDao = getDao(MstGroupproductM.class);
        }
        return mstGroupproductMDao;
    }

    public Dao<MstGroupproductMTemp, String> getMstGroupproductMTempDao() throws SQLException {

        if (mstGroupproductMTempDao == null) {
            mstGroupproductMTempDao = getDao(MstGroupproductMTemp.class);
        }
        return mstGroupproductMTempDao;
    }

    public Dao<MitGroupproductM, String> getMitGroupproductMDao() throws SQLException {

        if (mitGroupproductMDao == null) {
            mitGroupproductMDao = getDao(MitGroupproductM.class);
        }
        return mitGroupproductMDao;
    }

    public Dao<MitAgencynumM, String> getMitAgencynumMDao() throws SQLException {

        if (mitAgencynumMDao == null) {
            mitAgencynumMDao = getDao(MitAgencynumM.class);
        }
        return mitAgencynumMDao;
    }

    public Dao<MitAgencyproM, String> getMitAgencyproMDao() throws SQLException {

        if (mitAgencyproMDao == null) {
            mitAgencyproMDao = getDao(MitAgencyproM.class);
        }
        return mitAgencyproMDao;
    }

    public Dao<MitTerminalM, String> getMitTerminalMDao() throws SQLException {

        if (mitTerminalMDao == null) {
            mitTerminalMDao = getDao(MitTerminalM.class);
        }
        return mitTerminalMDao;
    }

    public Dao<MitValagencykfM, String> getMitValagencykfMDao() throws SQLException {

        if (mitValagencykfMDao == null) {
            mitValagencykfMDao = getDao(MitValagencykfM.class);
        }
        return mitValagencykfMDao;
    }


    public Dao<MitValaddaccountMTemp, String> getMitValaddaccountMTempDao() throws SQLException {

        if (mitValaddaccountMTempDao == null) {
            mitValaddaccountMTempDao = getDao(MitValaddaccountMTemp.class);
        }
        return mitValaddaccountMTempDao;
    }

    public Dao<MitValaddaccountM, String> getMitValaddaccountMDao() throws SQLException {

        if (mitValaddaccountMDao == null) {
            mitValaddaccountMDao = getDao(MitValaddaccountM.class);
        }
        return mitValaddaccountMDao;
    }

    public Dao<MitValaddaccountproMTemp, String> getMitValaddaccountproMTempDao() throws SQLException {

        if (mitValaddaccountproMTempDao == null) {
            mitValaddaccountproMTempDao = getDao(MitValaddaccountproMTemp.class);
        }
        return mitValaddaccountproMTempDao;
    }

    public Dao<MitValaddaccountproM, String> getMitValaddaccountproMDao() throws SQLException {

        if (mitValaddaccountproMDao == null) {
            mitValaddaccountproMDao = getDao(MitValaddaccountproM.class);
        }
        return mitValaddaccountproMDao;
    }


    public Dao<MitPlanweekM, String> getMitPlanweekMDao() throws SQLException {

        if (mitPlanweekMDao == null) {
            mitPlanweekMDao = getDao(MitPlanweekM.class);
        }
        return mitPlanweekMDao;
    }

    public Dao<MitPlandayM, String> getMitPlandayMDao() throws SQLException {

        if (mitPlandayMDao == null) {
            mitPlandayMDao = getDao(MitPlandayM.class);
        }
        return mitPlandayMDao;
    }

    public Dao<MitPlandaydetailM, String> getMitPlandaydetailMDao() throws SQLException {

        if (mitPlandaydetailMDao == null) {
            mitPlandaydetailMDao = getDao(MitPlandaydetailM.class);
        }
        return mitPlandaydetailMDao;
    }

    public Dao<MitPlandayvalM, String> getMitPlandayvalMDao() throws SQLException {

        if (mitPlandayvalMDao == null) {
            mitPlandayvalMDao = getDao(MitPlandayvalM.class);
        }
        return mitPlandayvalMDao;
    }

    public Dao<MitRepairM, String> getMitRepairMDao() throws SQLException {

        if (mitRepairMDao == null) {
            mitRepairMDao = getDao(MitRepairM.class);
        }
        return mitRepairMDao;
    }

    public Dao<MitRepairterM, String> getMitRepairterMDao() throws SQLException {

        if (mitRepairterMDao == null) {
            mitRepairterMDao = getDao(MitRepairterM.class);
        }
        return mitRepairterMDao;
    }

    public Dao<MitRepaircheckM, String> getMitRepaircheckMDao() throws SQLException {

        if (mitRepaircheckMDao == null) {
            mitRepaircheckMDao = getDao(MitRepaircheckM.class);
        }
        return mitRepaircheckMDao;
    }

    public Dao<MstAgreeTmp, String> getMstAgreeTmpDao() throws SQLException {

        if (mstAgreeTmpDao == null) {
            mstAgreeTmpDao = getDao(MstAgreeTmp.class);
        }
        return mstAgreeTmpDao;
    }

    public Dao<MstAgreeDetailTmp, String> getMstAgreeDetailTmpDao() throws SQLException {

        if (mstAgreeDetailTmpDao == null) {
            mstAgreeDetailTmpDao = getDao(MstAgreeDetailTmp.class);
        }
        return mstAgreeDetailTmpDao;
    }

    public Dao<MitValagreeM, String> getMitValagreeMDao() throws SQLException {

        if (mitValagreeMDao == null) {
            mitValagreeMDao = getDao(MitValagreeM.class);
        }
        return mitValagreeMDao;
    }

    public Dao<MitValagreeMTemp, String> getMitValagreeMTempDao() throws SQLException {

        if (mitValagreeMTempDao == null) {
            mitValagreeMTempDao = getDao(MitValagreeMTemp.class);
        }
        return mitValagreeMTempDao;
    }

    public Dao<MitValagreedetailM, String> getMitValagreedetailMDao() throws SQLException {

        if (mitValagreedetailMDao == null) {
            mitValagreedetailMDao = getDao(MitValagreedetailM.class);
        }
        return mitValagreedetailMDao;
    }

    public Dao<MitValagreedetailMTemp, String> getMitValagreedetailMTempDao() throws SQLException {

        if (mitValagreedetailMTempDao == null) {
            mitValagreedetailMTempDao = getDao(MitValagreedetailMTemp.class);
        }
        return mitValagreedetailMTempDao;
    }

    /**
     * 删除数据库
     *
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

}
