package et.tsingtaopad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import et.tsingtaopad.db.table.CmmAreaM;
import et.tsingtaopad.db.table.CmmBoardM;
import et.tsingtaopad.db.table.CmmDatadicM;
import et.tsingtaopad.db.table.MstAgencygridInfo;
import et.tsingtaopad.db.table.MstAgencyinfoM;
import et.tsingtaopad.db.table.MstAgencysupplyInfo;
import et.tsingtaopad.db.table.MstAgencytransferInfo;
import et.tsingtaopad.db.table.MstAgencyvisitM;
import et.tsingtaopad.db.table.MstBrandsclassM;
import et.tsingtaopad.db.table.MstBrandseriesM;
import et.tsingtaopad.db.table.MstCenterM;
import et.tsingtaopad.db.table.MstCenterdetailsM;
import et.tsingtaopad.db.table.MstCheckaccomplishInfo;
import et.tsingtaopad.db.table.MstCheckcollectionInfo;
import et.tsingtaopad.db.table.MstCheckexerecordInfo;
import et.tsingtaopad.db.table.MstCheckexerecordInfoTemp;
import et.tsingtaopad.db.table.MstCheckmiddleInfo;
import et.tsingtaopad.db.table.MstCheckstatusInfo;
import et.tsingtaopad.db.table.MstChecktypeM;
import et.tsingtaopad.db.table.MstCmpagencyInfo;
import et.tsingtaopad.db.table.MstCmpareaInfo;
import et.tsingtaopad.db.table.MstCmpbrandsM;
import et.tsingtaopad.db.table.MstCmpcompanyM;
import et.tsingtaopad.db.table.MstCmproductinfoM;
import et.tsingtaopad.db.table.MstCmpsupplyInfo;
import et.tsingtaopad.db.table.MstCollectionexerecordInfo;
import et.tsingtaopad.db.table.MstCollectionitemM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstInvalidapplayInfo;
import et.tsingtaopad.db.table.MstInvoicingInfo;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstMonthtargetInfo;
import et.tsingtaopad.db.table.MstPlanWeekforuserM;
import et.tsingtaopad.db.table.MstPlancheckInfo;
import et.tsingtaopad.db.table.MstPlancollectionInfo;
import et.tsingtaopad.db.table.MstPlanforuserM;
import et.tsingtaopad.db.table.MstPlanrouteInfo;
import et.tsingtaopad.db.table.MstPlantempcheckInfo;
import et.tsingtaopad.db.table.MstPlantempcollectionInfo;
import et.tsingtaopad.db.table.MstPlantemplateM;
import et.tsingtaopad.db.table.MstPowerfulchannelInfo;
import et.tsingtaopad.db.table.MstPowerfulterminalInfo;
import et.tsingtaopad.db.table.MstPriceM;
import et.tsingtaopad.db.table.MstPricedetailsInfo;
import et.tsingtaopad.db.table.MstProductM;
import et.tsingtaopad.db.table.MstProductareaInfo;
import et.tsingtaopad.db.table.MstProductshowM;
import et.tsingtaopad.db.table.MstPromomiddleInfo;
import et.tsingtaopad.db.table.MstPromoproductInfo;
import et.tsingtaopad.db.table.MstPromotermInfo;
import et.tsingtaopad.db.table.MstPromotionsM;
import et.tsingtaopad.db.table.MstPromotionstypeM;
import et.tsingtaopad.db.table.MstQuestionsanswersInfo;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstShipmentledgerInfo;
import et.tsingtaopad.db.table.MstShowpicInfo;
import et.tsingtaopad.db.table.MstSynckvM;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.db.table.MstVisitM;
import et.tsingtaopad.db.table.MstVisitauthorizeInfo;
import et.tsingtaopad.db.table.MstVisitmemoInfo;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.db.table.MstWorksummaryInfo;
import et.tsingtaopad.db.table.PadCheckaccomplishInfo;
import et.tsingtaopad.db.table.PadCheckproInfo;
import et.tsingtaopad.db.table.PadCheckstatusInfo;
import et.tsingtaopad.db.table.PadChecktypeM;
import et.tsingtaopad.db.table.PadPlantempcheckM;
import et.tsingtaopad.db.table.PadPlantempcollectionInfo;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DeleteTools.java</br>
 * 作者：yajie   </br>
 * 创建时间：2015-9-12</br>
 * 功能描述: </br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
;

public class DeleteTools {
	private static final String DATABASE_NAME = "FsaDBT.db";
	private static Dao<MstVistproductInfo, String> mstVistproductInfoDao = null;
	private static Dao<MstTerminalinfoM, String> mstTerminalinfoMDao;
	private static Dao<MstRouteM, String> mstRouteMDao;
	private static Dao<MstSynckvM, String> mstSynckvMDao;
	private static Dao<CmmAreaM, String> cmmAreaMDao;
	private static Dao<CmmDatadicM, String> cmmDatadicMDao;
	private static Dao<MstAgencygridInfo, String> mstAgencygridInfoDao;
	private static Dao<MstAgencyinfoM, String> mstAgencyinfoMDao;
	private static Dao<MstAgencysupplyInfo, String> mstAgencysupplyInfoDao;
	private static Dao<MstAgencytransferInfo, String> mstAgencytransferInfoDao;
	private static Dao<MstAgencyvisitM, String> mstAgencyvisitMDao;
	private static Dao<MstAgencyvisitM, String> mstAgencyvisitMDao2;
	private static Dao<MstBrandsclassM, String> mstBrandsclassMDao;
	private static Dao<MstBrandseriesM, String> mstBrandseriesMDao;
	private static Dao<MstCenterdetailsM, String> mstCenterdetailsMDao;
	private static Dao<MstCenterM, String> mstCenterMDao;
	private static Dao<MstCheckaccomplishInfo, String> mstCheckaccomplishInfoDao;
	private static Dao<MstCheckcollectionInfo, String> mstCheckcollectionInfoDao;
	private static Dao<MstCheckexerecordInfo, String> mstCheckexerecordInfoDao;
	private static Dao<MstCheckexerecordInfoTemp, String> mstCheckexerecordInfoTempDao;
	private static Dao<MstCheckstatusInfo, String> mstCheckstatusInfoDao;
	private static Dao<MstChecktypeM, String> mstChecktypeMDao;
	private static Dao<MstCmpareaInfo, String> mstCmpareaInfoDao;
	private static Dao<MstCmpbrandsM, String> mstCmpbrandsMDao;
	private static Dao<MstCmpcompanyM, String> mstCmpcompanyMDao;
	private static Dao<MstCmproductinfoM, String> mstCmproductinfoMDao;
	private static Dao<MstCollectionitemM, String> mstCollectionitemMDao;
	private static Dao<MstGridM, String> mstGridMDao;
	private static Dao<MstInvalidapplayInfo, String> mstInvalidapplayInfoDao;
	private static Dao<MstInvoicingInfo, String> mstInvoicingInfoDao;
	private static Dao<MstMarketareaM, String> mstMarketareaMDao;
	private static Dao<MstPricedetailsInfo, String> mstPricedetailsInfoDao;
	private static Dao<MstPriceM, String> mstPriceMDao;
	private static Dao<MstProductareaInfo, String> mstProductareaInfoDao;
	private static Dao<MstProductM, String> mstProductMDao;
	private static Dao<MstPromoproductInfo, String> mstPromoproductInfoDao;
	private static Dao<MstPromotermInfo, String> mstPromotermInfoDao;
	private static Dao<MstPromotionsM, String> mstPromotionsMDao;
	private static Dao<MstPromotionstypeM, String> mstPromotionstypeMDao;
	private static Dao<MstShipmentledgerInfo, String> mstShipmentledgerInfoDao;
	private static Dao<MstVisitauthorizeInfo, String> mstVisitauthorizeInfoDao;
	private static Dao<MstVisitM, String> mstVisitMDao;
	private static Dao<MstVisitmemoInfo, String> mstVisitmemoInfoDao;
	private static Dao<MstWorksummaryInfo, String> mstWorksummaryInfoDao;
	private static Dao<MstQuestionsanswersInfo, String> mstQuestionsanswersInfoDao;
	private static Dao<MstCollectionexerecordInfo, String> mstCollectionexerecordInfoDao;
	private static Dao<MstPlanforuserM, String> mstPlanforuserMDao;
	private static Dao<PadCheckaccomplishInfo, String> padCheckaccomplishInfoDao;
	private static Dao<PadCheckstatusInfo, String> padCheckstatusInfoDao;
	private static Dao<PadChecktypeM, String> padChecktypeMDao;
	private static Dao<PadPlantempcheckM, String> padPlantempcheckMDao;
	private static Dao<PadPlantempcollectionInfo, String> padPlantempcollectionInfoDao;
	private static Dao<MstPlantemplateM, String> mstPlantemplateMDao;
	private static Dao<MstPlantempcheckInfo, String> mstPlantempcheckInfoDao;
	private static Dao<MstPlantempcollectionInfo, String> mstPlantempcollectionInfoDao;
	private static Dao<MstPlancheckInfo, String> mstPlancheckInfoDao;
	private static Dao<MstPlancollectionInfo, String> mstPlancollectionInfoDao;
	private static Dao<CmmBoardM, String> cmmBoardMDao;
	private static Dao<MstCheckmiddleInfo, String> mstCheckmiddleInfoDao;
	private static Dao<PadCheckproInfo, String> padCheckproInfoDao;
	private static Dao<MstPromomiddleInfo, String> mstPromomiddleInfoDao;
	private static Dao<MstPowerfulchannelInfo, String> mstPowerfulchannelInfoDao;
	private static Dao<MstPowerfulterminalInfo, String> mstPowerfulterminalInfoDao;
	private static Dao<MstCmpsupplyInfo, String> mstCmpsupplyInfoDao;
	private static Dao<MstPlanrouteInfo, String> mstPlanrouteInfoDao;
	private static Dao<MstMonthtargetInfo, String> mstMonthtargetInfoDao;
	private static Dao<MstCmpagencyInfo, String> mstCmpagencyInfoDao;
	private static Dao<MstProductshowM, String> mstProductshowMDao;
	private static Dao<MstShowpicInfo, String> mstShowpicInfoDao;
	private static Dao<MstPlanWeekforuserM, String> mstPlanWeekforuserMDao;

	// 清除本地数据库
	public static void deleteDatabase(Context context) {
		DatabaseHelper helper = DatabaseHelper.getHelper(context);
		try {
			/*
			cmmAreaMDao = helper.getCmmAreaMDao();
			cmmAreaMDao.executeRawNoArgs("delete from CMM_AREA_M");
			Log.i(DeleteTools.class.getName(), "delete cmm_area_m success");

			// cmmDatadicMDao
			cmmDatadicMDao = helper.getCmmDatadicMDao();
			cmmDatadicMDao.executeRawNoArgs("delete from CMM_DATADIC_M");
			Log.i(DeleteTools.class.getName(), "delete CMM_DATADIC_M Success");

			// mstAgencygridInfoDao
			mstAgencygridInfoDao = helper.getMstAgencygridInfoDao();
			mstAgencygridInfoDao
					.executeRawNoArgs("delete from MST_AGENCYGRID_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYGRID_INFO Success");
			
			// mstAgencyinfoMDao

			mstAgencyinfoMDao = helper.getMstAgencyinfoMDao();
			mstAgencyinfoMDao.executeRawNoArgs("delete from MST_AGENCYINFO_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYINFO_M Success");
			
			// mstAgencysupplyInfoDao
			mstAgencysupplyInfoDao = helper.getMstAgencysupplyInfoDao();
			mstAgencysupplyInfoDao
					.executeRawNoArgs("delete from MST_AGENCYSUPPLY_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYSUPPLY_INFO Success");
			// mstAgencytransferInfoDao

			mstAgencytransferInfoDao = helper.getMstAgencytransferInfoDao();
			mstAgencytransferInfoDao
					.executeRawNoArgs("delete from MST_AGENCYTRANSFER_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYTRANSFER_INFO Success");
			// mstAgencyvisitMDao

			mstAgencyvisitMDao = helper.getMstAgencyvisitMDao();
			mstAgencyvisitMDao
					.executeRawNoArgs("delete from MST_AGENCYVISIT_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYVISIT_M Success");
			
			mstBrandsclassMDao = helper.getMstBrandsclassMDao();
			mstBrandsclassMDao
					.executeRawNoArgs("delete from MST_BRANDSCLASS_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_BRANDSCLASS_M Success");
			
			// mstBrandseriesMDao

			mstBrandseriesMDao = helper.getMstBrandseriesMDao();
			mstBrandseriesMDao
					.executeRawNoArgs("delete from MST_BRANDSERIES_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_BRANDSERIES_M Success");
			
			// mstCenterdetailsMDao

			mstCenterdetailsMDao = helper.getMstCenterdetailsMDao();
			mstCenterdetailsMDao
					.executeRawNoArgs("delete from MST_CENTERDETAILS_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_CENTERDETAILS_M Success");
			
			// mstCenterMDao

			mstCenterMDao = helper.getMstCenterMDao();
			mstCenterMDao.executeRawNoArgs("delete from MST_CENTER_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_CENTER_M Success");
			
			// mstCheckaccomplishInfoDao

			mstCheckaccomplishInfoDao = helper.getMstCheckaccomplishInfoDao();
			mstCheckaccomplishInfoDao
					.executeRawNoArgs("delete from MST_CHECKACCOMPLISH_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKACCOMPLISH_INFO Success");
			
			// mstCheckcollectionInfoDao

			mstCheckcollectionInfoDao = helper.getMstCheckcollectionInfoDao();
			mstCheckcollectionInfoDao
					.executeRawNoArgs("delete from MST_CHECKCOLLECTION_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKCOLLECTION_INFO Success");
			
			// mstCheckexerecordInfoDao

			mstCheckexerecordInfoDao = helper.getMstCheckexerecordInfoDao();
			mstCheckexerecordInfoDao
					.executeRawNoArgs("delete from MST_CHECKEXERECORD_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKEXERECORD_INFO Success");
			
			// mstCheckexerecordInfoTempDao

			mstCheckexerecordInfoTempDao = helper
					.getMstCheckexerecordInfoTempDao();
			mstCheckexerecordInfoTempDao
					.executeRawNoArgs("delete from MST_CHECKEXERECORD_INFO_TEMP");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKEXERECORD_INFO_TEMP Success");
			
			// mstCheckstatusInfoDao

			mstCheckstatusInfoDao = helper.getMstCheckstatusInfoDao();
			mstCheckstatusInfoDao
					.executeRawNoArgs("delete from MST_CHECKSTATUS_INFO");

			// mstChecktypeMDao

			mstChecktypeMDao = helper.getMstChecktypeMDao();
			mstChecktypeMDao.executeRawNoArgs("delete from MST_CHECKTYPE_M");

			// mstCmpareaInfoDao

			mstCmpareaInfoDao = helper.getMstCmpareaInfoDao();
			mstCmpareaInfoDao.executeRawNoArgs("delete from MST_CMPAREA_INFO");

			// mstCmpbrandsMDao

			mstCmpbrandsMDao = helper.getMstCmpbrandsMDao();
			mstCmpbrandsMDao.executeRawNoArgs("delete from MST_CMPBRANDS_M");

			// mstCmpcompanyMDao

			mstCmpcompanyMDao = helper.getMstCmpcompanyMDao();
			mstCmpcompanyMDao.executeRawNoArgs("delete from MST_CMPCOMPANY_M");

			// mstCmproductinfoMDao

			mstCmproductinfoMDao = helper.getMstCmproductinfoMDao();
			mstCmproductinfoMDao
					.executeRawNoArgs("delete from MST_CMPRODUCTINFO_M");

			// mstCollectionitemMDao

			mstCollectionitemMDao = helper.getMstCollectionitemMDao();
			mstCollectionitemMDao
					.executeRawNoArgs("delete from MST_COLLECTIONITEM_M");

			// mstGridMDao

			mstGridMDao = helper.getMstGridMDao();
			mstGridMDao.executeRawNoArgs("delete from MST_GRID_M");

			// mstInvalidapplayInfoDao

			mstInvalidapplayInfoDao = helper.getMstInvalidapplayInfoDao();
			mstInvalidapplayInfoDao
					.executeRawNoArgs("delete from MST_INVALIDAPPLAY_INFO");

			// mstInvoicingInfoDao

			mstInvoicingInfoDao = helper.getMstInvoicingInfoDao();
			mstInvoicingInfoDao
					.executeRawNoArgs("delete from MST_INVOICING_INFO");

			// mstMarketareaMDao

			mstMarketareaMDao = helper.getMstMarketareaMDao();
			mstMarketareaMDao.executeRawNoArgs("delete from MST_MARKETAREA_M");

			// mstPricedetailsInfoDao

			mstPricedetailsInfoDao = helper.getMstPricedetailsInfoDao();
			mstPricedetailsInfoDao
					.executeRawNoArgs("delete from MST_PRICEDETAILS_INFO");

			// mstPriceMDao

			mstPriceMDao = helper.getMstPriceMDao();
			mstPriceMDao.executeRawNoArgs("delete from MST_PRICE_M");

			mstProductareaInfoDao = helper.getMstProductareaInfoDao();
			mstProductareaInfoDao
					.executeRawNoArgs("delete from MST_PRODUCTAREA_INFO");

			// mstProductMDao

			mstProductMDao = helper.getMstProductMDao();
			mstProductMDao.executeRawNoArgs("delete from MST_PRODUCT_M");

			// mstPromoproductInfoDao

			mstPromoproductInfoDao = helper.getMstPromoproductInfoDao();
			mstPromoproductInfoDao
					.executeRawNoArgs("delete from MST_PROMOPRODUCT_INFO");

			mstPromotermInfoDao = helper.getMstPromotermInfoDao();
			mstPromotermInfoDao
					.executeRawNoArgs("delete from MST_PROMOTERM_INFO");

			mstPromotionsMDao = helper.getMstPromotionsMDao();
			mstPromotionsMDao.executeRawNoArgs("delete from MST_PROMOTIONS_M");

			// mstPromotionstypeMDao

			mstPromotionstypeMDao = helper.getMstPromotionstypeMDao();
			mstPromotionstypeMDao
					.executeRawNoArgs("delete from MST_PROMOTIONSTYPE_M");

			// MST_ROUTE_M
			mstRouteMDao = helper.getMstRouteMDao();
			mstRouteMDao.executeRawNoArgs("delete from MST_ROUTE_M");

			// mstShipmentledgerInfoDao

			mstShipmentledgerInfoDao = helper.getMstShipmentledgerInfoDao();
			mstShipmentledgerInfoDao
					.executeRawNoArgs("delete from MST_SHIPMENTLEDGER_INFO");

			// mstVisitauthorizeInfoDao

			mstVisitauthorizeInfoDao = helper.getMstVisitauthorizeInfoDao();
			mstVisitauthorizeInfoDao
					.executeRawNoArgs("delete from MST_VISITAUTHORIZE_INFO");

			// mstVisitMDao

			mstVisitMDao = helper.getMstVisitMDao();
			mstVisitMDao.executeRawNoArgs("delete from MST_VISIT_M");

			// mstVisitmemoInfoDao

			mstVisitmemoInfoDao = helper.getMstVisitmemoInfoDao();
			mstVisitmemoInfoDao
					.executeRawNoArgs("delete from MST_VISITMEMO_INFO");

			// MST_VISTPRODUCT_INFO

			mstVistproductInfoDao = helper.getMstVistproductInfoDao();
			mstVistproductInfoDao
					.executeRawNoArgs("delete from MST_VISTPRODUCT_INFO");

			mstTerminalinfoMDao = helper.getMstTerminalinfoMDao();
			mstTerminalinfoMDao
					.executeRawNoArgs("delete from MST_TERMINALINFO_M");

			// mstWorksummaryInfoDao

			mstWorksummaryInfoDao = helper.getMstWorksummaryInfoDao();
			mstWorksummaryInfoDao
					.executeRawNoArgs("delete from MST_WORKSUMMARY_INFO");

			// mstQuestionsanswersInfoDao

			mstQuestionsanswersInfoDao = helper.getMstQuestionsanswersInfoDao();
			mstQuestionsanswersInfoDao
					.executeRawNoArgs("delete from MST_QUESTIONSANSWERS_INFO");

			// mstCollectionexerecordInfoDao

			mstCollectionexerecordInfoDao = helper
					.getMstCollectionexerecordInfoDao();
			mstCollectionexerecordInfoDao
					.executeRawNoArgs("delete from MST_COLLECTIONEXERECORD_INFO");

			// mstPlanforuserMDao

			mstPlanforuserMDao = helper.getMstPlanforuserMDao();
			mstPlanforuserMDao
					.executeRawNoArgs("delete from MST_PLANFORUSER_M");

			// padCheckaccomplishInfoDao

			padCheckaccomplishInfoDao = helper.getPadCheckaccomplishInfoDao();
			padCheckaccomplishInfoDao
					.executeRawNoArgs("delete from PAD_CHECKACCOMPLISH_INFO");

			// padCheckstatusInfoDao

			padCheckstatusInfoDao = helper.getPadCheckstatusInfoDao();
			padCheckstatusInfoDao
					.executeRawNoArgs("delete from PAD_CHECKSTATUS_INFO");

			// padChecktypeMDao

			padChecktypeMDao = helper.getPadChecktypeMDao();
			padChecktypeMDao.executeRawNoArgs("delete from PAD_CHECKTYPE_M");

			padPlantempcheckMDao = helper.getPadPlantempcheckMDao();
			padPlantempcheckMDao
					.executeRawNoArgs("delete from PAD_PLANTEMPCHECK_M");

			// padPlantempcollectionInfoDao

			padPlantempcollectionInfoDao = helper
					.getPadPlantempcollectionInfoDao();
			padPlantempcollectionInfoDao
					.executeRawNoArgs("delete from PAD_PLANTEMPCOLLECTION_INFO");

			// mstPlantemplateMDao

			mstPlantemplateMDao = helper.getMstPlantemplateMDao();
			mstPlantemplateMDao
					.executeRawNoArgs("delete from MST_PLANTEMPLATE_M");

			// mstPlantempcheckInfoDao

			mstPlantempcheckInfoDao = helper.getMstPlantempcheckInfoDao();
			mstPlantempcheckInfoDao
					.executeRawNoArgs("delete from MST_PLANTEMPCHECK_INFO");

			// mstPlantempcollectionInfoDao

			mstPlantempcollectionInfoDao = helper
					.getMstPlantempcollectionInfoDao();
			mstPlantempcollectionInfoDao
					.executeRawNoArgs("delete from MST_PLANTEMPCOLLECTION_INFO");

			// mstPlancheckInfoDao

			mstPlancheckInfoDao = helper.getMstPlancheckInfoDao();
			mstPlancheckInfoDao
					.executeRawNoArgs("delete from MST_PLANCHECK_INFO");

			// mstPlancollectionInfoDao

			mstPlancollectionInfoDao = helper.getMstPlancollectionInfoDao();
			mstPlancollectionInfoDao
					.executeRawNoArgs("delete from MST_PLANCOLLECTION_INFO");

			// cmmBoardMDao

			cmmBoardMDao = helper.getCmmBoardMDao();
			cmmBoardMDao.executeRawNoArgs("delete from CMM_BOARD_M");

			// mstCheckmiddleInfoDao

			mstCheckmiddleInfoDao = helper.getMstCheckmiddleInfoDao();
			mstCheckmiddleInfoDao
					.executeRawNoArgs("delete from MST_CHECKMIDDLE_INFO");

			// padCheckproInfoDao

			padCheckproInfoDao = helper.getPadCheckproInfoDao();
			padCheckproInfoDao
					.executeRawNoArgs("delete from PAD_CHECKPRO_INFO");

			// mstPromomiddleInfoDao

			mstPromomiddleInfoDao = helper.getMstPromomiddleInfoDao();
			mstPromomiddleInfoDao
					.executeRawNoArgs("delete from MST_PROMOMIDDLE_INFO");

			// mstPowerfulchannelInfoDao

			mstPowerfulchannelInfoDao = helper.getMstPowerfulchannelInfoDao();
			mstPowerfulchannelInfoDao
					.executeRawNoArgs("delete from MST_POWERFULCHANNEL_INFO");
			Log.i(DeleteTools.class.getName(),
					"delete MST_POWERFULCHANNEL_INFO Success");

			// mstPowerfulterminalInfoDao

			mstPowerfulterminalInfoDao = helper.getMstPowerfulterminalInfoDao();
			mstPowerfulterminalInfoDao
					.executeRawNoArgs("delete from MST_POWERFULTERMINAL_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_POWERFULTERMINAL_INFO Success");

			// mstCmpsupplyInfoDao

			mstCmpsupplyInfoDao = helper.getMstCmpsupplyInfoDao();
			mstCmpsupplyInfoDao
					.executeRawNoArgs("delete from MST_CMPSUPPLY_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_CMPSUPPLY_INFO Success");

			// mstPlanrouteInfoDao

			mstPlanrouteInfoDao = helper.getMstPlanrouteInfoDao();
			mstPlanrouteInfoDao
					.executeRawNoArgs("delete from MST_PLANROUTE_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_PLANROUTE_INFO Success");

			// mstMonthtargetInfoDao

			mstMonthtargetInfoDao = helper.getMstMonthtargetInfoDao();
			mstMonthtargetInfoDao
					.executeRawNoArgs("delete from MST_MONTHTARGET_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_MONTHTARGET_INFO Success");

			// mstCmpagencyInfo

			mstCmpagencyInfoDao = helper.getMstCmpagencyInfoDao();
			mstCmpagencyInfoDao
					.executeRawNoArgs("delete from MST_CMPAGENCY_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_CMPAGENCY_INFO Success");

			// mstProductshowMDao

			mstProductshowMDao = helper.getMstProductshowMDao();
			mstProductshowMDao
					.executeRawNoArgs("delete from MST_PRODUCTSHOW_M");

			// mstShowpicInfoDao

			mstShowpicInfoDao = helper.getMstShowpicInfoDao();
			mstShowpicInfoDao.executeRawNoArgs("delete from MST_SHOWPIC_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_SHOWPIC_INFO Success");

			// mstPlanWeekforuserMDao

			mstPlanWeekforuserMDao = helper.getMstPlanWeekforuserMDao();
			mstPlanWeekforuserMDao
					.executeRawNoArgs("delete from MST_PLANWEEKFORUSER_M");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_PLANWEEKFORUSER_M Success");

			mstSynckvMDao = helper.getMstSynckvMDao();
			mstSynckvMDao.executeRawNoArgs("delete from MST_SYNCKV_M");

			helper.initData(helper.getWritableDatabase());

			Log.i(DeleteTools.class.getName(), "Delete Alldatabase Success");
			// MST_SYNCKV_M
			
			// 删除数据库
			//File oldfile = new File("/data/data/et.tsingtaopad/databases/FsaDBT.db" );
			//FileUtil.deleteFile(oldfile);

			*/
			
			/*SQLiteDatabase mDb = helper.getReadableDatabase();
			// 关闭数据库
			helper.close();  
	        // 删除数据库
			helper.deleteDatabase(context);*/
			
			SQLiteDatabase db = helper.getReadableDatabase();
			// 清除表数据
			clearFeedTable(helper,"CMM_BOARD_M");//, "通知公告" }, 
            clearFeedTable(helper,"CMM_DATADIC_M");// "数据字典" }, 
            clearFeedTable(helper,"CMM_AREA_M");// "省市县区域" }, 
            clearFeedTable(helper,"MST_ROUTE_M");//, "线路" },
            clearFeedTable(helper,"MST_TERMINALINFO_M");//, "终端" }, 
            clearFeedTable(helper,"MST_AGENCYINFO_M");//, "分经销商" }, 
            clearFeedTable(helper,"MST_AGENCYGRID_INFO");//, "分经销商" }, 
            clearFeedTable(helper,"MST_AGENCYSUPPLY_INFO");//, "巡店拜访" }, 
            clearFeedTable(helper,"MST_AGENCYTRANSFER_INFO");//, "分经销商拜访" }, 
            clearFeedTable(helper,"MST_INVOICING_INFO");//, "分经销商拜访" }, 
            clearFeedTable(helper,"MST_PROMOTIONS_M");//, "活动基础信息" }, 
            clearFeedTable(helper,"MST_PROMOTIONSTYPE_M");//, "活动基础信息" }, 
            clearFeedTable(helper,"MST_PROMOPRODUCT_INFO");//, "活动基础信息" }, 
            clearFeedTable(helper,"MST_PROMOTERM_INFO");//, "巡店拜访" }, 
            clearFeedTable(helper,"MST_VISIT_M");//, "巡店拜访" }, 
            clearFeedTable(helper,"MST_VISTPRODUCT_INFO");//, "巡店拜访" }, 
            clearFeedTable(helper,"MST_CHECKEXERECORD_INFO");//, "巡店拜访" },
            clearFeedTable(helper,"MST_COLLECTIONEXERECORD_INFO");//,"巡店拜访"},
            clearFeedTable(helper,"MST_CMPBRANDS_M");//, "竞品" }, 
            clearFeedTable(helper,"MST_CMPCOMPANY_M");//, "竞品" }, 
            clearFeedTable(helper,"MST_CMPRODUCTINFO_M");//, "竞品" }, 
            clearFeedTable(helper,"MST_PRODUCT_M");//, "分经销商" }, 
            clearFeedTable(helper,"MST_VISITMEMO_INFO");//, "客情备忘录" }, 
            clearFeedTable(helper,"MST_VISITAUTHORIZE_INFO");//, "定格可拜访授权" }, 
            clearFeedTable(helper,"MST_AGENCYVISIT_M");//, "分经销商拜访" }, 
            clearFeedTable(helper,"MST_QUESTIONSANSWERS_INFO");//, "问题反馈" }, 
            clearFeedTable(helper,"MST_WORKSUMMARY_INFO");//, "日工作总结" }, 
            clearFeedTable(helper,"PAD_CHECKTYPE_M");//, "采集用指标" }, 
            clearFeedTable(helper,"MST_PLANFORUSER_M");//, "工作计划指标状态" }, 
            clearFeedTable(helper,"MST_PLANCOLLECTION_INFO");//, "工作计划指标状态" }, 
            clearFeedTable(helper,"PAD_PLANTEMPCHECK_M");//, "计划模板" }, 
            clearFeedTable(helper,"MST_CMPSUPPLY_INFO");//, "巡店拜访" }, 
            clearFeedTable(helper,"MST_PLANCHECK_INFO");//, "工作计划指标状态" }, 
            clearFeedTable(helper,"MST_PRICE_M");//, "价格明细" }, 
            clearFeedTable(helper,"MST_PRICEDETAILS_INFO");//, "价格明细" }, 
            clearFeedTable(helper,"MST_PRODUCTAREA_INFO");//, "分经销商信息" }, 
            clearFeedTable(helper,"MST_MONTHTARGET_INFO");//, "月目标管理" }, 
            clearFeedTable(helper,"MST_CMPAGENCY_INFO");//, "竞品供应商管理" }, 
            clearFeedTable(helper,"MST_PRODUCTSHOW_M");//, "产品展示信息" }, 
            clearFeedTable(helper,"MST_SHOWPIC_INFO");//, "产品展示" }, 
            clearFeedTable(helper,"MST_PLANWEEKFORUSER_M");//, "周工作计划" },
            clearFeedTable(helper,"MST_PICTYPE_M");//, "图片类型" },
            clearFeedTable(helper,"MST_AGENCYKF_M");//, "经销商开发" },
            clearFeedTable(helper,"MST_PLANTERMINAL_M");//, "计划终端表" }
            clearFeedTable(helper,"MST_SYNCKV_M");//
			
            // 删除并重建视图
            helper.initView(db);
            helper.initData(db);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(DeleteTools.class.getName(), "delete database failed");
		}

		// helper.close();
		// helper.deleteDataBase(context);

	}
	
	// 删除本地数据库
	public static void deleteDatabaseAll(Context context) {

		DatabaseHelper helper = DatabaseHelper.getHelper(context);
		try {
			/*
			cmmAreaMDao = helper.getCmmAreaMDao();
			cmmAreaMDao.executeRawNoArgs("delete from CMM_AREA_M");
			Log.i(DeleteTools.class.getName(), "delete cmm_area_m success");

			// cmmDatadicMDao
			cmmDatadicMDao = helper.getCmmDatadicMDao();
			cmmDatadicMDao.executeRawNoArgs("delete from CMM_DATADIC_M");
			Log.i(DeleteTools.class.getName(), "delete CMM_DATADIC_M Success");

			// mstAgencygridInfoDao
			mstAgencygridInfoDao = helper.getMstAgencygridInfoDao();
			mstAgencygridInfoDao
					.executeRawNoArgs("delete from MST_AGENCYGRID_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYGRID_INFO Success");
			
			// mstAgencyinfoMDao

			mstAgencyinfoMDao = helper.getMstAgencyinfoMDao();
			mstAgencyinfoMDao.executeRawNoArgs("delete from MST_AGENCYINFO_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYINFO_M Success");
			
			// mstAgencysupplyInfoDao
			mstAgencysupplyInfoDao = helper.getMstAgencysupplyInfoDao();
			mstAgencysupplyInfoDao
					.executeRawNoArgs("delete from MST_AGENCYSUPPLY_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYSUPPLY_INFO Success");
			// mstAgencytransferInfoDao

			mstAgencytransferInfoDao = helper.getMstAgencytransferInfoDao();
			mstAgencytransferInfoDao
					.executeRawNoArgs("delete from MST_AGENCYTRANSFER_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYTRANSFER_INFO Success");
			// mstAgencyvisitMDao

			mstAgencyvisitMDao = helper.getMstAgencyvisitMDao();
			mstAgencyvisitMDao
					.executeRawNoArgs("delete from MST_AGENCYVISIT_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_AGENCYVISIT_M Success");
			
			mstBrandsclassMDao = helper.getMstBrandsclassMDao();
			mstBrandsclassMDao
					.executeRawNoArgs("delete from MST_BRANDSCLASS_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_BRANDSCLASS_M Success");
			
			// mstBrandseriesMDao

			mstBrandseriesMDao = helper.getMstBrandseriesMDao();
			mstBrandseriesMDao
					.executeRawNoArgs("delete from MST_BRANDSERIES_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_BRANDSERIES_M Success");
			
			// mstCenterdetailsMDao

			mstCenterdetailsMDao = helper.getMstCenterdetailsMDao();
			mstCenterdetailsMDao
					.executeRawNoArgs("delete from MST_CENTERDETAILS_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_CENTERDETAILS_M Success");
			
			// mstCenterMDao

			mstCenterMDao = helper.getMstCenterMDao();
			mstCenterMDao.executeRawNoArgs("delete from MST_CENTER_M");
			Log.i(DeleteTools.class.getName(), "Delete MST_CENTER_M Success");
			
			// mstCheckaccomplishInfoDao

			mstCheckaccomplishInfoDao = helper.getMstCheckaccomplishInfoDao();
			mstCheckaccomplishInfoDao
					.executeRawNoArgs("delete from MST_CHECKACCOMPLISH_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKACCOMPLISH_INFO Success");
			
			// mstCheckcollectionInfoDao

			mstCheckcollectionInfoDao = helper.getMstCheckcollectionInfoDao();
			mstCheckcollectionInfoDao
					.executeRawNoArgs("delete from MST_CHECKCOLLECTION_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKCOLLECTION_INFO Success");
			
			// mstCheckexerecordInfoDao

			mstCheckexerecordInfoDao = helper.getMstCheckexerecordInfoDao();
			mstCheckexerecordInfoDao
					.executeRawNoArgs("delete from MST_CHECKEXERECORD_INFO");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKEXERECORD_INFO Success");
			
			// mstCheckexerecordInfoTempDao

			mstCheckexerecordInfoTempDao = helper
					.getMstCheckexerecordInfoTempDao();
			mstCheckexerecordInfoTempDao
					.executeRawNoArgs("delete from MST_CHECKEXERECORD_INFO_TEMP");
			Log.i(DeleteTools.class.getName(), "Delete MST_CHECKEXERECORD_INFO_TEMP Success");
			
			// mstCheckstatusInfoDao

			mstCheckstatusInfoDao = helper.getMstCheckstatusInfoDao();
			mstCheckstatusInfoDao
					.executeRawNoArgs("delete from MST_CHECKSTATUS_INFO");

			// mstChecktypeMDao

			mstChecktypeMDao = helper.getMstChecktypeMDao();
			mstChecktypeMDao.executeRawNoArgs("delete from MST_CHECKTYPE_M");

			// mstCmpareaInfoDao

			mstCmpareaInfoDao = helper.getMstCmpareaInfoDao();
			mstCmpareaInfoDao.executeRawNoArgs("delete from MST_CMPAREA_INFO");

			// mstCmpbrandsMDao

			mstCmpbrandsMDao = helper.getMstCmpbrandsMDao();
			mstCmpbrandsMDao.executeRawNoArgs("delete from MST_CMPBRANDS_M");

			// mstCmpcompanyMDao

			mstCmpcompanyMDao = helper.getMstCmpcompanyMDao();
			mstCmpcompanyMDao.executeRawNoArgs("delete from MST_CMPCOMPANY_M");

			// mstCmproductinfoMDao

			mstCmproductinfoMDao = helper.getMstCmproductinfoMDao();
			mstCmproductinfoMDao
					.executeRawNoArgs("delete from MST_CMPRODUCTINFO_M");

			// mstCollectionitemMDao

			mstCollectionitemMDao = helper.getMstCollectionitemMDao();
			mstCollectionitemMDao
					.executeRawNoArgs("delete from MST_COLLECTIONITEM_M");

			// mstGridMDao

			mstGridMDao = helper.getMstGridMDao();
			mstGridMDao.executeRawNoArgs("delete from MST_GRID_M");

			// mstInvalidapplayInfoDao

			mstInvalidapplayInfoDao = helper.getMstInvalidapplayInfoDao();
			mstInvalidapplayInfoDao
					.executeRawNoArgs("delete from MST_INVALIDAPPLAY_INFO");

			// mstInvoicingInfoDao

			mstInvoicingInfoDao = helper.getMstInvoicingInfoDao();
			mstInvoicingInfoDao
					.executeRawNoArgs("delete from MST_INVOICING_INFO");

			// mstMarketareaMDao

			mstMarketareaMDao = helper.getMstMarketareaMDao();
			mstMarketareaMDao.executeRawNoArgs("delete from MST_MARKETAREA_M");

			// mstPricedetailsInfoDao

			mstPricedetailsInfoDao = helper.getMstPricedetailsInfoDao();
			mstPricedetailsInfoDao
					.executeRawNoArgs("delete from MST_PRICEDETAILS_INFO");

			// mstPriceMDao

			mstPriceMDao = helper.getMstPriceMDao();
			mstPriceMDao.executeRawNoArgs("delete from MST_PRICE_M");

			mstProductareaInfoDao = helper.getMstProductareaInfoDao();
			mstProductareaInfoDao
					.executeRawNoArgs("delete from MST_PRODUCTAREA_INFO");

			// mstProductMDao

			mstProductMDao = helper.getMstProductMDao();
			mstProductMDao.executeRawNoArgs("delete from MST_PRODUCT_M");

			// mstPromoproductInfoDao

			mstPromoproductInfoDao = helper.getMstPromoproductInfoDao();
			mstPromoproductInfoDao
					.executeRawNoArgs("delete from MST_PROMOPRODUCT_INFO");

			mstPromotermInfoDao = helper.getMstPromotermInfoDao();
			mstPromotermInfoDao
					.executeRawNoArgs("delete from MST_PROMOTERM_INFO");

			mstPromotionsMDao = helper.getMstPromotionsMDao();
			mstPromotionsMDao.executeRawNoArgs("delete from MST_PROMOTIONS_M");

			// mstPromotionstypeMDao

			mstPromotionstypeMDao = helper.getMstPromotionstypeMDao();
			mstPromotionstypeMDao
					.executeRawNoArgs("delete from MST_PROMOTIONSTYPE_M");

			// MST_ROUTE_M
			mstRouteMDao = helper.getMstRouteMDao();
			mstRouteMDao.executeRawNoArgs("delete from MST_ROUTE_M");

			// mstShipmentledgerInfoDao

			mstShipmentledgerInfoDao = helper.getMstShipmentledgerInfoDao();
			mstShipmentledgerInfoDao
					.executeRawNoArgs("delete from MST_SHIPMENTLEDGER_INFO");

			// mstVisitauthorizeInfoDao

			mstVisitauthorizeInfoDao = helper.getMstVisitauthorizeInfoDao();
			mstVisitauthorizeInfoDao
					.executeRawNoArgs("delete from MST_VISITAUTHORIZE_INFO");

			// mstVisitMDao

			mstVisitMDao = helper.getMstVisitMDao();
			mstVisitMDao.executeRawNoArgs("delete from MST_VISIT_M");

			// mstVisitmemoInfoDao

			mstVisitmemoInfoDao = helper.getMstVisitmemoInfoDao();
			mstVisitmemoInfoDao
					.executeRawNoArgs("delete from MST_VISITMEMO_INFO");

			// MST_VISTPRODUCT_INFO

			mstVistproductInfoDao = helper.getMstVistproductInfoDao();
			mstVistproductInfoDao
					.executeRawNoArgs("delete from MST_VISTPRODUCT_INFO");

			mstTerminalinfoMDao = helper.getMstTerminalinfoMDao();
			mstTerminalinfoMDao
					.executeRawNoArgs("delete from MST_TERMINALINFO_M");

			// mstWorksummaryInfoDao

			mstWorksummaryInfoDao = helper.getMstWorksummaryInfoDao();
			mstWorksummaryInfoDao
					.executeRawNoArgs("delete from MST_WORKSUMMARY_INFO");

			// mstQuestionsanswersInfoDao

			mstQuestionsanswersInfoDao = helper.getMstQuestionsanswersInfoDao();
			mstQuestionsanswersInfoDao
					.executeRawNoArgs("delete from MST_QUESTIONSANSWERS_INFO");

			// mstCollectionexerecordInfoDao

			mstCollectionexerecordInfoDao = helper
					.getMstCollectionexerecordInfoDao();
			mstCollectionexerecordInfoDao
					.executeRawNoArgs("delete from MST_COLLECTIONEXERECORD_INFO");

			// mstPlanforuserMDao

			mstPlanforuserMDao = helper.getMstPlanforuserMDao();
			mstPlanforuserMDao
					.executeRawNoArgs("delete from MST_PLANFORUSER_M");

			// padCheckaccomplishInfoDao

			padCheckaccomplishInfoDao = helper.getPadCheckaccomplishInfoDao();
			padCheckaccomplishInfoDao
					.executeRawNoArgs("delete from PAD_CHECKACCOMPLISH_INFO");

			// padCheckstatusInfoDao

			padCheckstatusInfoDao = helper.getPadCheckstatusInfoDao();
			padCheckstatusInfoDao
					.executeRawNoArgs("delete from PAD_CHECKSTATUS_INFO");

			// padChecktypeMDao

			padChecktypeMDao = helper.getPadChecktypeMDao();
			padChecktypeMDao.executeRawNoArgs("delete from PAD_CHECKTYPE_M");

			padPlantempcheckMDao = helper.getPadPlantempcheckMDao();
			padPlantempcheckMDao
					.executeRawNoArgs("delete from PAD_PLANTEMPCHECK_M");

			// padPlantempcollectionInfoDao

			padPlantempcollectionInfoDao = helper
					.getPadPlantempcollectionInfoDao();
			padPlantempcollectionInfoDao
					.executeRawNoArgs("delete from PAD_PLANTEMPCOLLECTION_INFO");

			// mstPlantemplateMDao

			mstPlantemplateMDao = helper.getMstPlantemplateMDao();
			mstPlantemplateMDao
					.executeRawNoArgs("delete from MST_PLANTEMPLATE_M");

			// mstPlantempcheckInfoDao

			mstPlantempcheckInfoDao = helper.getMstPlantempcheckInfoDao();
			mstPlantempcheckInfoDao
					.executeRawNoArgs("delete from MST_PLANTEMPCHECK_INFO");

			// mstPlantempcollectionInfoDao

			mstPlantempcollectionInfoDao = helper
					.getMstPlantempcollectionInfoDao();
			mstPlantempcollectionInfoDao
					.executeRawNoArgs("delete from MST_PLANTEMPCOLLECTION_INFO");

			// mstPlancheckInfoDao

			mstPlancheckInfoDao = helper.getMstPlancheckInfoDao();
			mstPlancheckInfoDao
					.executeRawNoArgs("delete from MST_PLANCHECK_INFO");

			// mstPlancollectionInfoDao

			mstPlancollectionInfoDao = helper.getMstPlancollectionInfoDao();
			mstPlancollectionInfoDao
					.executeRawNoArgs("delete from MST_PLANCOLLECTION_INFO");

			// cmmBoardMDao

			cmmBoardMDao = helper.getCmmBoardMDao();
			cmmBoardMDao.executeRawNoArgs("delete from CMM_BOARD_M");

			// mstCheckmiddleInfoDao

			mstCheckmiddleInfoDao = helper.getMstCheckmiddleInfoDao();
			mstCheckmiddleInfoDao
					.executeRawNoArgs("delete from MST_CHECKMIDDLE_INFO");

			// padCheckproInfoDao

			padCheckproInfoDao = helper.getPadCheckproInfoDao();
			padCheckproInfoDao
					.executeRawNoArgs("delete from PAD_CHECKPRO_INFO");

			// mstPromomiddleInfoDao

			mstPromomiddleInfoDao = helper.getMstPromomiddleInfoDao();
			mstPromomiddleInfoDao
					.executeRawNoArgs("delete from MST_PROMOMIDDLE_INFO");

			// mstPowerfulchannelInfoDao

			mstPowerfulchannelInfoDao = helper.getMstPowerfulchannelInfoDao();
			mstPowerfulchannelInfoDao
					.executeRawNoArgs("delete from MST_POWERFULCHANNEL_INFO");
			Log.i(DeleteTools.class.getName(),
					"delete MST_POWERFULCHANNEL_INFO Success");

			// mstPowerfulterminalInfoDao

			mstPowerfulterminalInfoDao = helper.getMstPowerfulterminalInfoDao();
			mstPowerfulterminalInfoDao
					.executeRawNoArgs("delete from MST_POWERFULTERMINAL_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_POWERFULTERMINAL_INFO Success");

			// mstCmpsupplyInfoDao

			mstCmpsupplyInfoDao = helper.getMstCmpsupplyInfoDao();
			mstCmpsupplyInfoDao
					.executeRawNoArgs("delete from MST_CMPSUPPLY_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_CMPSUPPLY_INFO Success");

			// mstPlanrouteInfoDao

			mstPlanrouteInfoDao = helper.getMstPlanrouteInfoDao();
			mstPlanrouteInfoDao
					.executeRawNoArgs("delete from MST_PLANROUTE_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_PLANROUTE_INFO Success");

			// mstMonthtargetInfoDao

			mstMonthtargetInfoDao = helper.getMstMonthtargetInfoDao();
			mstMonthtargetInfoDao
					.executeRawNoArgs("delete from MST_MONTHTARGET_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_MONTHTARGET_INFO Success");

			// mstCmpagencyInfo

			mstCmpagencyInfoDao = helper.getMstCmpagencyInfoDao();
			mstCmpagencyInfoDao
					.executeRawNoArgs("delete from MST_CMPAGENCY_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_CMPAGENCY_INFO Success");

			// mstProductshowMDao

			mstProductshowMDao = helper.getMstProductshowMDao();
			mstProductshowMDao
					.executeRawNoArgs("delete from MST_PRODUCTSHOW_M");

			// mstShowpicInfoDao

			mstShowpicInfoDao = helper.getMstShowpicInfoDao();
			mstShowpicInfoDao.executeRawNoArgs("delete from MST_SHOWPIC_INFO");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_SHOWPIC_INFO Success");

			// mstPlanWeekforuserMDao

			mstPlanWeekforuserMDao = helper.getMstPlanWeekforuserMDao();
			mstPlanWeekforuserMDao
					.executeRawNoArgs("delete from MST_PLANWEEKFORUSER_M");
			Log.i(DeleteTools.class.getName(),
					"Delete MST_PLANWEEKFORUSER_M Success");

			mstSynckvMDao = helper.getMstSynckvMDao();
			mstSynckvMDao.executeRawNoArgs("delete from MST_SYNCKV_M");

			helper.initData(helper.getWritableDatabase());

			Log.i(DeleteTools.class.getName(), "Delete Alldatabase Success");
			// MST_SYNCKV_M
			
			// 删除数据库
			//File oldfile = new File("/data/data/et.tsingtaopad/databases/FsaDBT.db" );
			//FileUtil.deleteFile(oldfile);

			*/
			
			SQLiteDatabase mDb = helper.getReadableDatabase();
			// 关闭数据库
			helper.close();  
	        // 删除数据库
			helper.deleteDatabase(context);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(DeleteTools.class.getName(), "delete database failed");
		}

		// helper.close();
		// helper.deleteDataBase(context);

	}
	
	public static void clearFeedTable(DatabaseHelper dbHelper, String tabname) {

		String sql = "DELETE FROM " + tabname + ";";

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		db.execSQL(sql);

		//revertSeq(dbHelper, tabname);

		//dbHelper.close();

	}

}
