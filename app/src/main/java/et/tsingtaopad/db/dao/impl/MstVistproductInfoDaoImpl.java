package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstVistproductInfoDao;
import et.tsingtaopad.db.table.MstVistproductInfo;
import et.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;
import et.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.chatvie.domain.ChatVieStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexCalculateStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexQuicklyStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.invoicing.domain.InvoicingStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstVistproductInfoDaoImpl extends
		BaseDaoImpl<MstVistproductInfo, String> implements
		MstVistproductInfoDao {



	public MstVistproductInfoDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, MstVistproductInfo.class);
	}

	/**
	 * 获取某次拜访的我品的进销存数据情况
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<InvoicingStc> queryMinePro(DatabaseHelper helper,
										   String visitId, String termKey) {

		Map<String, String> purcnumMap = getPurcnumSum(helper, termKey);
		List<InvoicingStc> lst = new ArrayList<InvoicingStc>();

		StringBuffer buffer = new StringBuffer();
		/*
		 * buffer.append("select vp.recordkey, vp.productkey, vp.purcprice, ");
		 * buffer.append("vp.retailprice, vp.purcnum, vp.pronum, vp.currnum, ");
		 * //PRONAME 产品名称
		 * buffer.append("vp.salenum, vp.agencykey, pm.proname, am.agencyname "
		 * ); //MST_VISTPRODUCT_INFO(拜访产品-竞品我品记录表)
		 * buffer.append("from mst_vistproduct_info vp ");
		 * //MST_PRODUCT_M(青啤产品信息主表) buffer.append(
		 * "inner join mst_product_m pm on vp.productkey = pm.productkey ");
		 * //MST_AGENCYINFO_M(分经销商信息主表) buffer.append(
		 * "inner join mst_agencyinfo_m am on vp.agencykey = am.agencykey ");
		 * buffer.append("where vp.visitkey = ? and vp.cmpproductkey is null ");
		 * buffer.append("and coalesce(vp.deleteflag,'0') != '1'");
		 */
		/*
		 * String sql = "select vp.recordkey," + "vp.productkey," +
		 * "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.pronum," +
		 * "vp.currnum," + "vp.salenum," + "pm.proname," +
		 * "mai.upperkey as agencykey," + "am.agencyname" +
		 * " from mst_vistproduct_info vp" + " left join mst_product_m pm" +
		 * " on vp.productkey = pm.productkey" +
		 * " left join mst_agencysupply_info mai" +
		 * " on  mai.productkey=vp.productkey" +
		 * " left join mst_agencyinfo_m am" + " on am.agencykey=mai.upperkey" +
		 * " where vp.visitkey = ? " + " and vp.cmpproductkey is null" +
		 * " and coalesce(vp.deleteflag, '0') != '1'" +
		 * " and mai.lowerkey= ? and mai.status='0'";
		 */
		// 鹰哥修改SQL
		/*
		 * String sql = "select vp.recordkey," + "mai.productkey," +
		 * "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.pronum," +
		 * "vp.currnum," + "vp.salenum," + "pm.proname," +
		 * "mai.upperkey as agencykey," + "am.agencyname" +
		 * " from mst_agencysupply_info mai" + " left join mst_product_m pm" +
		 * " on mai.productkey = pm.productkey" +
		 * " left join mst_vistproduct_info vp" +
		 * " on mai.productkey=vp.productkey" + " and vp.visitkey = ?" +
		 * " and vp.cmpproductkey is null and coalesce(vp.deleteflag, '0') != '1'"
		 * + " left join mst_agencyinfo_m am on am.agencykey=mai.upperkey" +
		 * " where mai.lowerkey= ? and mai.status='0'";
		 */

		String sql = "select vp.recordkey," + "mai.productkey,"  // INPRICE  // REPRICE
				//+ "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.fristdate,"
				// 修改进销存的渠道价零售价 从供货关系表读取  不再从竞品我品表读取
				+ "mai.inprice," + "mai.reprice," + "vp.purcnum," + "vp.fristdate,"
				+ "vp.pronum," + "vp.currnum," + "vp.salenum," + "pm.proname," + "vp.addcard,"
				+ "mai.upperkey as agencykey," + "am.agencyname"
				+ " from mst_agencysupply_info mai"
				+ " join mst_agencyinfo_m am"
				+ " on am.agencykey = mai.upperkey" + " join mst_product_m pm"
				+ " on mai.productkey = pm.productkey"
				+ " left join mst_vistproduct_info vp"
				+ " on mai.productkey = vp.productkey" + " and vp.visitkey = ?"
				+ " and vp.cmpproductkey is null"
				+ " and coalesce(vp.deleteflag, '0') != '1'"
				+ " where mai.lowerkey = ?" + " and mai.status = '0'"
				+ " and am.AGENCYSTATUS = '0'" + " and pm.status = '0'";

		buffer.append(sql);

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId, termKey });
		InvoicingStc item;
		while (cursor.moveToNext()) {
			item = new InvoicingStc();
			item.setRecordId(cursor.getString(cursor
					.getColumnIndex("recordkey")));
			String productkey = cursor.getString(cursor
					.getColumnIndex("productkey"));
			item.setProId(productkey);
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAgencyId(cursor.getString(cursor
					.getColumnIndex("agencykey")));
			item.setAgencyName(cursor.getString(cursor
					.getColumnIndex("agencyname")));
			item.setChannelPrice(cursor.getString((cursor
					.getColumnIndex("inprice"))));// 进店价
			item.setSellPrice(cursor.getString(cursor
					.getColumnIndex("reprice")));// 零售价

			item.setAddcard(cursor.getString(cursor
					.getColumnIndex("addcard")));// 累计卡

			// 上周期进货总量
			item.setPrevNum(cursor.getString(cursor.getColumnIndex("purcnum")));
			// 上周期进货总量总和
			item.setPrevNumSum(purcnumMap.get(productkey));
			// 上次库存
			item.setPrevStore(cursor.getString(cursor.getColumnIndex("pronum")));
			// 本次库存
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			// 日销量
			item.setDaySellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// 最早生产日期
			item.setFristdate(cursor.getString(cursor.getColumnIndex("fristdate")));
			lst.add(item);
		}

		return lst;
	}



	/***
	 * 设置进销存上周期进货总量(获取上月28日到现在的进货总和)
	 *
	 * @param helper
	 * @param termKey
	 * @return
	 */
	private Map<String, String> getPurcnumSum(DatabaseHelper helper,
											  String termKey) {
		Map<String, String> purcnumMap = new HashMap<String, String>();
		try {
			StringBuffer buffer = new StringBuffer();
			// PURCNUM--上周期进货总量
			buffer.append("select productkey,sum(purcnum) purcnumSum from MST_VISTPRODUCT_INFO pm ");
			buffer.append("where pm.visitkey in ");
			buffer.append("(select visitkey from mst_visit_m vm1 where vm1.visitdate in ");
			buffer.append("(select max(vm.visitdate) from mst_visit_m vm ");
			buffer.append("where vm.terminalkey=? ");
			// 获取当前日期
			int currDay = DateUtil.getCurrentDay();
			// 如果当前日期>=29号 代表本月新周期的数据 29000000-->日 时分秒
			if (currDay >= 29) {
				buffer.append("and vm.visitdate >= (substr(replace(date('now'),'-',''),0,7)||'29000000') ");
			} else {
				// 如果当前日期<29 代表上个月周期的数据
				buffer.append("and vm.visitdate >= (substr(replace(date('now','start of month','-1 month'),'-',''),0,7)||'29000000') ");
			}
			buffer.append("and vm.uploadflag='1' ");
			buffer.append("group by substr(vm.visitdate,0,9) ");
			buffer.append(") ");
			buffer.append("and vm1.terminalkey=? ");
			buffer.append("and vm1.uploadflag='1' ");
			buffer.append(") ");
			buffer.append("and deleteflag!='1' ");
			buffer.append("and productkey is not null ");
			buffer.append("group by productkey ");
			Cursor cursor = helper.getReadableDatabase().rawQuery(
					buffer.toString(), new String[] { termKey, termKey });
			while (cursor.moveToNext()) {
				String productkey = cursor.getString(cursor
						.getColumnIndex("productkey"));
				String purcnumSum = cursor.getString(cursor
						.getColumnIndex("purcnumSum"));
				purcnumMap.put(productkey, purcnumSum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purcnumMap;
	}

	/**
	 * 获取某次拜访的我品的进销存数据情况
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<ChatVieStc> queryViePro(DatabaseHelper helper, String visitId) {

		List<ChatVieStc> lst = new ArrayList<ChatVieStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select vp.recordkey,vp.cmpproductkey, vp.purcprice, ");
		buffer.append("vp.retailprice, vp.remarks, pm.cmpprodesc, vp.currnum, ");
		buffer.append("vp.salenum, vp.agencykey, vp.agencyname,pm.cmpproname, vp.cmpcomkey, pc.cmpagencyname ");
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("left join mst_cmproductinfo_m pm ");
		buffer.append("    on vp.cmpproductkey = pm.cmpproductkey ");
		buffer.append("left join mst_cmpagency_info pc ");
		buffer.append("    on pc.cmpagencykey = vp.agencykey ");
		buffer.append("where vp.visitkey = ? and vp.productkey is null ");
		buffer.append("and coalesce(vp.deleteflag,'0') != '1'");

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		ChatVieStc item;
		while (cursor.moveToNext()) {
			item = new ChatVieStc();
			item.setRecordId(cursor.getString(cursor
					.getColumnIndex("recordkey")));
			item.setProId(cursor.getString(cursor
					.getColumnIndex("cmpproductkey")));
			item.setProName(cursor.getString(cursor
					.getColumnIndex("cmpproname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setCommpayId(cursor.getString(cursor
					.getColumnIndex("cmpcomkey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));// 用户输入的竞品经销商
			item.setChannelPrice(cursor.getString((cursor
					.getColumnIndex("purcprice"))));
			item.setSellPrice(cursor.getString(cursor
					.getColumnIndex("retailprice")));
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			item.setMonthSellNum(cursor.getString(cursor
					.getColumnIndex("salenum")));
			// if
			// (CheckUtil.isBlankOrNull(cursor.getString(cursor.getColumnIndex("remarks"))))
			// {
			// item.setDescribe(cursor.getString(cursor.getColumnIndex("cmpprodesc")));
			// } else {
			item.setDescribe(cursor.getString(cursor.getColumnIndex("remarks")));
			// }
			lst.add(item);
		}

		return lst;
	}



	/**
	 * 获取巡店拜访-查指标的分项采集部分的产品指标数据
	 *
	 * @param helper
	 * @param visitId
	 *            本次拜访ID
	 * @param termId
	 *            本次拜访终端ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @param seeFlag
	 *            查看标识
	 * @return
	 */
	public List<CheckIndexCalculateStc> queryCalculateIndex(
			DatabaseHelper helper, String visitId, String termId,
			String channelId, String seeFlag) {

		List<CheckIndexCalculateStc> lst = new ArrayList<CheckIndexCalculateStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct vp.visitkey, vp.productkey, pm.proname, ");
		buffer.append("cm.checktype, cm.checkkey, cm.checkname, vr.acresult, cs.cstatusname, vr.recordkey ");
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("inner join mst_product_m pm ");
		buffer.append(" on vp.productkey = pm.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checkpro_info cp ");
		buffer.append(" on vp.productkey = cp.productkey ");
		buffer.append(" and cp.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on cp.checkkey = cm.checkkey and cm.isproduct = '1'");
		buffer.append(" and cm.minorchannel like '%").append(channelId)
				.append("%' ");

		// 如果seeFlag=1,
		// 则从指标记录结果表取数，否则从指标结果临时表取数（mst_checkexerecord_info_temp）
		if (ConstValues.FLAG_1.equals(seeFlag)) {
			buffer.append("left join mst_checkexerecord_info vr  ");
			buffer.append("  on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append("  and vr.terminalkey ='").append(termId)
					.append("' and vr.enddate='30001201' ");
			buffer.append("  and vr.deleteflag !='").append(
					ConstValues.delFlag + "'");
		} else {
			buffer.append("left join mst_checkexerecord_info_temp vr ");
			buffer.append(" on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append(" and vr.visitkey ='").append(visitId).append("' ");

		}
		buffer.append("left join pad_checkstatus_info cs on cs.cstatuskey = vr.acresult ");
		buffer.append("order by cm.checkkey, vp.productkey ");

		/*
		 * StringBuffer buffer = new StringBuffer(); buffer.append(
		 * "select distinct vp.visitkey, mai.productkey, pm.proname, cm.checktype, cm.checkkey, cm.checkname, "
		 * ); buffer.append("vr.acresult, cs.cstatusname, vr.recordkey ");
		 * buffer
		 * .append("from mst_agencysupply_info mai inner join mst_product_m pm "
		 * ); buffer.append(" on mai.productkey = pm.productkey ");
		 * buffer.append("inner join pad_checkpro_info cp ");
		 * buffer.append(" on mai.productkey = cp.productkey ");
		 * buffer.append(" and cp.minorchannel like '%"
		 * ).append(channelId).append("%' ");
		 * buffer.append("inner join pad_checktype_m cm ");
		 * buffer.append(" on cp.checkkey = cm.checkkey ");
		 * buffer.append(" and cm.isproduct = '1' ");
		 * buffer.append(" and cm.minorchannel like '%"
		 * ).append(channelId).append("%' ");
		 * buffer.append("left join mst_checkexerecord_info_temp vr ");
		 * buffer.append(" on vp.productkey = vr.productkey ");
		 * buffer.append(" and cm.checkkey = vr.checkkey ");
		 * buffer.append(" and vr.visitkey = ? ");
		 * buffer.append("left join mst_vistproduct_info vp ");
		 * buffer.append(" on vp.productkey = mai.productkey ");
		 * buffer.append(" and vp.visitkey= ? ");
		 * buffer.append("left join pad_checkstatus_info cs ");
		 * buffer.append(" on cs.cstatuskey = vr.acresult ");
		 * buffer.append("order by cm.checkkey, vp.productkey ");
		 */

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		CheckIndexCalculateStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexCalculateStc();
			item.setVisitId(cursor.getString(cursor.getColumnIndex("visitkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setIndexId(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setIndexType(cursor.getString(cursor
					.getColumnIndex("checktype")));
			item.setIndexName(cursor.getString(cursor
					.getColumnIndex("checkname")));
			item.setIndexValueId(cursor.getString(cursor
					.getColumnIndex("acresult")));
			item.setIndexValueName(cursor.getString(cursor
					.getColumnIndex("cstatusname")));
			item.setRecordId(cursor.getString(cursor
					.getColumnIndex("recordkey")));
			lst.add(item);
		}
		return lst;
	}

	/**
	 * 获取巡店拜访-查指标的分项采集部分的产品指标对应的采集项数据
	 *
	 * @param helper
	 * @param visitId
	 *            本次拜访ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @return
	 */
	public List<CheckIndexQuicklyStc> queryCalculateItem(DatabaseHelper helper,
														 String visitId, String channelId) {

		List<CheckIndexQuicklyStc> lst = new ArrayList<CheckIndexQuicklyStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct ci.checkkey,ci.cstatuskey,ci.colitemkey, ");
		buffer.append("ci.colitemname,ci.productkey,ci.addcount,ci.totalcount, ");
		buffer.append("ci.minorchannel,vi.addcount changenum, ");
		buffer.append("vi.totalcount finalnum, vi.bianhualiang, vi.xianyouliang,vi.colrecordkey,vi.freshness, pm.proname ");
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("inner join pad_checkaccomplish_info ci ");
		buffer.append(" on vp.productkey = ci.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on ci.checkkey  = cm.checkkey and cm.isproduct = '1' ");
		buffer.append("     and cm.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join mst_product_m pm  ");
		buffer.append(" on ci.productkey = pm.productkey ");
		buffer.append("left join mst_collectionexerecord_info vi ");
		buffer.append(" on ci.productkey = vi.productkey ");
		buffer.append("   and ci.colitemkey = vi.colitemkey and vi.visitKey=? ");
		buffer.append("where ci.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("order by ci.minorchannel,ci.productkey,ci.colitemkey ");

		String[] args = new String[] { visitId, visitId };
		Cursor cursor = helper.getReadableDatabase().rawQuery(buffer.toString(), args);
		CheckIndexQuicklyStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexQuicklyStc();
			item.setCheckkey(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setCstatuskey(cursor.getString(cursor.getColumnIndex("cstatuskey")));
			item.setColitemkey(cursor.getString(cursor.getColumnIndex("colitemkey")));
			item.setColitemname(cursor.getString(cursor.getColumnIndex("colitemname")));
			item.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAddcount(cursor.getDouble(cursor.getColumnIndex("addcount")));
			item.setTotalcount(cursor.getDouble(cursor.getColumnIndex("totalcount")));

			item.setFreshness(cursor.getString(cursor.getColumnIndex("freshness")));

			item.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
			String cnum = cursor.getString(cursor.getColumnIndex("changenum"));
			String fnum = cursor.getString(cursor.getColumnIndex("finalnum"));
			item.setChangeNum(FunUtil.isBlankOrNullToDouble(cnum));
			item.setFinalNum(FunUtil.isBlankOrNullToDouble(fnum));
			item.setBianhualiang(cursor.getString(cursor.getColumnIndex("bianhualiang")));
			item.setXianyouliang(cursor.getString(cursor.getColumnIndex("xianyouliang")));
			item.setColRecordId(cursor.getString(cursor.getColumnIndex("colrecordkey")));
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 获取巡店拜访-查指标的分项采集部分的产品指标数据
	 *
	 * @param helper
	 * @param visitId
	 *            本次拜访ID
	 * @param termId
	 *            本次拜访终端ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @param seeFlag
	 *            查看标识
	 * @return
	 */
	public List<CheckIndexCalculateStc> queryCalculateIndexTemp(
			DatabaseHelper helper, String visitId, String termId,
			String channelId, String seeFlag) {

		List<CheckIndexCalculateStc> lst = new ArrayList<CheckIndexCalculateStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct vp.visitkey, vp.productkey, pm.proname, ");
		buffer.append("cm.checktype, cm.checkkey, cm.checkname, vr.acresult, cs.cstatusname, vr.recordkey ");
		buffer.append("from mst_vistproduct_info_temp vp ");
		buffer.append("inner join mst_product_m pm ");
		buffer.append(" on vp.productkey = pm.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checkpro_info cp ");
		buffer.append(" on vp.productkey = cp.productkey ");
		buffer.append(" and cp.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on cp.checkkey = cm.checkkey and cm.isproduct = '1'");
		buffer.append(" and cm.minorchannel like '%").append(channelId)
				.append("%' ");

		// 如果seeFlag=1,
		// 则从指标记录结果表取数，否则从指标结果临时表取数（mst_checkexerecord_info_temp）
		if (ConstValues.FLAG_1.equals(seeFlag)) {
			buffer.append("left join mst_checkexerecord_info vr  ");
			buffer.append("  on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append("  and vr.terminalkey ='").append(termId)
					.append("' and vr.enddate='30001201' ");
			buffer.append("  and vr.deleteflag !='").append(
					ConstValues.delFlag + "'");
		} else {
			buffer.append("left join mst_checkexerecord_info_temp vr ");
			buffer.append(" on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append(" and vr.visitkey ='").append(visitId).append("' ");

		}
		buffer.append("left join pad_checkstatus_info cs on cs.cstatuskey = vr.acresult ");
		buffer.append("order by cm.checkkey, vp.productkey ");

		/*
		 * StringBuffer buffer = new StringBuffer(); buffer.append(
		 * "select distinct vp.visitkey, mai.productkey, pm.proname, cm.checktype, cm.checkkey, cm.checkname, "
		 * ); buffer.append("vr.acresult, cs.cstatusname, vr.recordkey ");
		 * buffer
		 * .append("from mst_agencysupply_info mai inner join mst_product_m pm "
		 * ); buffer.append(" on mai.productkey = pm.productkey ");
		 * buffer.append("inner join pad_checkpro_info cp ");
		 * buffer.append(" on mai.productkey = cp.productkey ");
		 * buffer.append(" and cp.minorchannel like '%"
		 * ).append(channelId).append("%' ");
		 * buffer.append("inner join pad_checktype_m cm ");
		 * buffer.append(" on cp.checkkey = cm.checkkey ");
		 * buffer.append(" and cm.isproduct = '1' ");
		 * buffer.append(" and cm.minorchannel like '%"
		 * ).append(channelId).append("%' ");
		 * buffer.append("left join mst_checkexerecord_info_temp vr ");
		 * buffer.append(" on vp.productkey = vr.productkey ");
		 * buffer.append(" and cm.checkkey = vr.checkkey ");
		 * buffer.append(" and vr.visitkey = ? ");
		 * buffer.append("left join mst_vistproduct_info vp ");
		 * buffer.append(" on vp.productkey = mai.productkey ");
		 * buffer.append(" and vp.visitkey= ? ");
		 * buffer.append("left join pad_checkstatus_info cs ");
		 * buffer.append(" on cs.cstatuskey = vr.acresult ");
		 * buffer.append("order by cm.checkkey, vp.productkey ");
		 */

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		CheckIndexCalculateStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexCalculateStc();
			item.setVisitId(cursor.getString(cursor.getColumnIndex("visitkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setIndexId(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setIndexType(cursor.getString(cursor.getColumnIndex("checktype")));
			item.setIndexName(cursor.getString(cursor.getColumnIndex("checkname")));
			item.setIndexValueId(cursor.getString(cursor.getColumnIndex("acresult")));
			item.setIndexValueName(cursor.getString(cursor.getColumnIndex("cstatusname")));
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			lst.add(item);
		}
		return lst;
	}

	/**
	 * 获取终端追溯-查指标的分项采集部分的产品指标数据
	 *
	 * @param helper
	 * @param previsitId
	 *            本次拜访ID
	 * @param termId
	 *            本次拜访终端ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @param seeFlag
	 *            查看标识
	 * @return
	 */
	public List<CheckIndexCalculateStc> queryZsCalculateIndexTemp(
			DatabaseHelper helper, String previsitId, String termId,
			String channelId, String seeFlag) {

		List<CheckIndexCalculateStc> lst = new ArrayList<CheckIndexCalculateStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct vp.visitkey, vp.productkey, pm.proname, ");
		buffer.append("cm.checktype, cm.checkkey, cm.checkname, vr.acresult,vr.ddacresult,vr.ddremark, vr.valchecktypeflag, cs.cstatusname, vr.valchecktypeid,vr.id ");
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("inner join mst_product_m pm ");
		buffer.append(" on vp.productkey = pm.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checkpro_info cp ");
		buffer.append(" on vp.productkey = cp.productkey ");
		buffer.append(" and cp.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on cp.checkkey = cm.checkkey and cm.isproduct = '1'");
		buffer.append(" and cm.minorchannel like '%").append(channelId)
				.append("%' ");

		// 如果seeFlag=1,
		// 则从指标记录结果表取数，否则从指标结果临时表取数（mst_checkexerecord_info_temp）
		/*if (ConstValues.FLAG_1.equals(seeFlag)) {
			buffer.append("left join mst_checkexerecord_info vr  ");
			buffer.append("  on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append("  and vr.terminalkey ='").append(termId)
					.append("' and vr.enddate='30001201' ");
			buffer.append("  and vr.deleteflag !='").append(
					ConstValues.delFlag + "'");
		} else {
			buffer.append("left join mst_checkexerecord_info_temp vr ");
			buffer.append(" on vp.productkey = vr.productkey and cm.checkkey = vr.checkkey ");
			buffer.append(" and vr.visitkey ='").append(visitId).append("' ");

		}*/

		buffer.append("left join mit_valchecktype_m_temp vr ");
		buffer.append(" on vp.productkey = vr.productkey and cm.checkkey = vr.valchecktype ");
		buffer.append(" and vr.visitkey ='").append(previsitId).append("' ");

		buffer.append("left join pad_checkstatus_info cs on cs.cstatuskey = vr.acresult ");
		buffer.append("order by cm.checkkey, vp.productkey ");


		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { previsitId });
		CheckIndexCalculateStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexCalculateStc();
			item.setVisitId(cursor.getString(cursor.getColumnIndex("visitkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setIndexId(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setIndexType(cursor.getString(cursor.getColumnIndex("checktype")));
			item.setIndexName(cursor.getString(cursor.getColumnIndex("checkname")));
			item.setIndexValueId(cursor.getString(cursor.getColumnIndex("acresult")));
			item.setIndexValueName(cursor.getString(cursor.getColumnIndex("cstatusname")));
			item.setRecordId(cursor.getString(cursor.getColumnIndex("valchecktypeid")));// 业代拉链表主键
			item.setId(cursor.getString(cursor.getColumnIndex("id")));// 督导拉链表主键
			item.setValchecktypeflag(cursor.getString(cursor.getColumnIndex("valchecktypeflag")));// 指标正确与否
			item.setDdacresult(cursor.getString(cursor.getColumnIndex("ddacresult")));// 督导自动计算的指标值
			item.setDdremark(cursor.getString(cursor.getColumnIndex("ddremark")));// 督导指标备注
			lst.add(item);
		}
		return lst;
	}

	/**
	 * 获取巡店拜访-查指标的分项采集部分的产品指标对应的采集项数据
	 *
	 * @param helper
	 * @param visitId
	 *            本次拜访ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @return
	 */
	public List<CheckIndexQuicklyStc> queryCalculateItemTemp(DatabaseHelper helper, String visitId, String channelId) {

		List<CheckIndexQuicklyStc> lst = new ArrayList<CheckIndexQuicklyStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct ci.checkkey,ci.cstatuskey,ci.colitemkey, ");
		buffer.append("ci.colitemname,ci.productkey,ci.addcount,ci.totalcount, ");
		buffer.append("ci.minorchannel,vi.addcount changenum, ");
		buffer.append("vi.totalcount finalnum, vi.bianhualiang, vi.xianyouliang,vi.colrecordkey,vi.freshness, pm.proname ");
		buffer.append("from mst_vistproduct_info_temp vp ");
		buffer.append("inner join pad_checkaccomplish_info ci ");
		buffer.append(" on vp.productkey = ci.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on ci.checkkey  = cm.checkkey and cm.isproduct = '1' ");
		buffer.append("     and cm.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join mst_product_m pm  ");
		buffer.append(" on ci.productkey = pm.productkey ");
		buffer.append("left join mst_collectionexerecord_info_temp vi ");
		buffer.append(" on ci.productkey = vi.productkey ");
		buffer.append("   and ci.colitemkey = vi.colitemkey and vi.visitKey=? ");
		buffer.append("where ci.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("order by ci.minorchannel,ci.productkey,ci.colitemkey ");

		String[] args = new String[] { visitId, visitId };
		Cursor cursor = helper.getReadableDatabase().rawQuery(buffer.toString(), args);
		CheckIndexQuicklyStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexQuicklyStc();
			item.setCheckkey(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setCstatuskey(cursor.getString(cursor.getColumnIndex("cstatuskey")));
			item.setColitemkey(cursor.getString(cursor.getColumnIndex("colitemkey")));
			item.setColitemname(cursor.getString(cursor.getColumnIndex("colitemname")));
			item.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAddcount(cursor.getDouble(cursor.getColumnIndex("addcount")));
			item.setTotalcount(cursor.getDouble(cursor.getColumnIndex("totalcount")));
			item.setFreshness(cursor.getString(cursor.getColumnIndex("freshness")));
			item.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
			String cnum = cursor.getString(cursor.getColumnIndex("changenum"));
			String fnum = cursor.getString(cursor.getColumnIndex("finalnum"));
			item.setChangeNum(FunUtil.isBlankOrNullToDouble(cnum));
			item.setFinalNum(FunUtil.isBlankOrNullToDouble(fnum));
			item.setBianhualiang(cursor.getString(cursor.getColumnIndex("bianhualiang")));
			item.setXianyouliang(cursor.getString(cursor.getColumnIndex("xianyouliang")));
			item.setColRecordId(cursor.getString(cursor.getColumnIndex("colrecordkey")));
			lst.add(item);
		}

		return lst;
	}
	/**
	 * 获取终端追溯-查指标的分项采集部分的产品指标对应的采集项数据
	 *
	 * @param helper
	 * @param previsitId
	 *            本次拜访ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @return
	 */
	public List<CheckIndexQuicklyStc> queryZsCalculateItemTemp(DatabaseHelper helper, String previsitId, String channelId) {

		List<CheckIndexQuicklyStc> lst = new ArrayList<CheckIndexQuicklyStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct ci.checkkey,ci.cstatuskey,ci.colitemkey, ");
		buffer.append("ci.colitemname,ci.productkey,ci.addcount,ci.totalcount, ");
		buffer.append("ci.minorchannel,vi.addcount changenum,vi.valchecktypeid, ");
		buffer.append("vi.totalcount finalnum,vi.valitemid,vi.id,vi.valitem,vi.valitemval,vi.valitemremark, pm.proname ");// id,原值,正确值
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("inner join pad_checkaccomplish_info ci ");
		buffer.append(" on vp.productkey = ci.productkey and vp.visitkey= ? ");
		buffer.append("inner join pad_checktype_m cm ");
		buffer.append(" on ci.checkkey  = cm.checkkey and cm.isproduct = '1' ");
		buffer.append("     and cm.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("inner join mst_product_m pm  ");
		buffer.append(" on ci.productkey = pm.productkey ");
		buffer.append("left join mit_valcheckitem_m_temp vi ");
		buffer.append(" on ci.productkey = vi.productkey ");
		buffer.append("   and ci.colitemkey = vi.colitemkey and vi.visitkey=? ");
		buffer.append("where ci.minorchannel like '%").append(channelId)
				.append("%' ");
		buffer.append("order by ci.minorchannel,ci.productkey,ci.colitemkey ");

		String[] args = new String[] { previsitId, previsitId };
		Cursor cursor = helper.getReadableDatabase().rawQuery(buffer.toString(), args);
		CheckIndexQuicklyStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexQuicklyStc();
			item.setCheckkey(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setCstatuskey(cursor.getString(cursor.getColumnIndex("cstatuskey")));
			item.setColitemkey(cursor.getString(cursor.getColumnIndex("colitemkey")));
			item.setColitemname(cursor.getString(cursor.getColumnIndex("colitemname")));
			item.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAddcount(cursor.getDouble(cursor.getColumnIndex("addcount")));
			item.setTotalcount(cursor.getDouble(cursor.getColumnIndex("totalcount")));
			item.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
			String cnum = cursor.getString(cursor.getColumnIndex("changenum"));
			String fnum = cursor.getString(cursor.getColumnIndex("finalnum"));
			item.setChangeNum(FunUtil.isBlankOrNullToDouble(cnum));
			item.setFinalNum(FunUtil.isBlankOrNullToDouble(fnum));
			item.setColRecordId(cursor.getString(cursor.getColumnIndex("valitemid")));// 业代采集项表主键
			item.setId(cursor.getString(cursor.getColumnIndex("id")));// 追溯采集项表主键
			item.setValchecktypeid(cursor.getString(cursor.getColumnIndex("valchecktypeid")));// 追溯拉链表主键
			item.setValitem(cursor.getString(cursor.getColumnIndex("valitem")));// 采集项原值结果量
			item.setValitemval(cursor.getString(cursor.getColumnIndex("valitemval")));// 采集项正确值结果量
			item.setValitemremark(cursor.getString(cursor.getColumnIndex("valitemremark")));// 采集项备注
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 获取巡店拜访-查指标的分项采集部分的与产品无关的指标数据
	 *
	 * @param visitId
	 *            拜访ID
	 * @param channelId
	 *            本次拜访终端的次渠道ID
	 * @param seeFlag
	 *            查看操作标识
	 * @return
	 */
	public List<CheckIndexCalculateStc> queryNoProIndex(DatabaseHelper helper,
														String visitId, String channelId, String seeFlag) {
		List<CheckIndexCalculateStc> lst = new ArrayList<CheckIndexCalculateStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select distinct vr.visitkey, cm.checktype, cm.checkkey, ");
		buffer.append("cm.checkname, vr.acresult, vr.recordkey ");
		buffer.append("from pad_checktype_m cm ");
		if (ConstValues.FLAG_1.equals(seeFlag)) {
			buffer.append("left join mst_checkexerecord_info vr ");
			buffer.append(" on cm.checkkey = vr.checkkey and vr.visitkey = ? ");
			buffer.append(" and vr.deleteflag!='" + ConstValues.delFlag + "' ");
		} else {
			buffer.append("left join mst_checkexerecord_info_temp vr ");
			buffer.append(" on cm.checkkey = vr.checkkey and vr.visitkey = ? ");
		}
		buffer.append("where coalesce(cm.isproduct,'0') = '0' ");
		buffer.append("and cm.minorchannel like '%").append(channelId)
				.append("%' ");

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		CheckIndexCalculateStc item;
		while (cursor.moveToNext()) {
			item = new CheckIndexCalculateStc();
			item.setVisitId(cursor.getString(cursor.getColumnIndex("visitkey")));
			item.setIndexId(cursor.getString(cursor.getColumnIndex("checkkey")));
			item.setIndexType(cursor.getString(cursor
					.getColumnIndex("checktype")));
			item.setIndexName(cursor.getString(cursor
					.getColumnIndex("checkname")));
			item.setIndexValueId(cursor.getString(cursor
					.getColumnIndex("acresult")));
			item.setRecordId(cursor.getString(cursor
					.getColumnIndex("recordkey")));
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 从临时表中 获取某次拜访的我品的进销存数据情况 wx_yss
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<InvoicingStc> queryMineProByTemp(DatabaseHelper helper,String visitId, String termKey) {

		Map<String, String> purcnumMap = getPurcnumSum(helper, termKey);
		List<InvoicingStc> lst = new ArrayList<InvoicingStc>();

		StringBuffer buffer = new StringBuffer();

		String sql = "select vp.recordkey," + "mai.productkey,"  // INPRICE  // REPRICE
				//+ "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.fristdate,"
				// 修改进销存的渠道价零售价 从供货关系表读取  不再从竞品我品表读取
				+ "mai.inprice," + "mai.reprice," + "vp.purcnum," + "vp.fristdate,"
				+ "vp.pronum," + "vp.currnum," + "vp.salenum," + "pm.proname," + "vp.addcard,"
				+ "mai.upperkey as agencykey," + "am.agencyname"
				+ " from mst_agencysupply_info_temp mai"
				+ " join mst_agencyinfo_m am"
				+ " on am.agencykey = mai.upperkey" + " join mst_product_m pm"
				+ " on mai.productkey = pm.productkey"
				+ " left join mst_vistproduct_info_temp vp"
				+ " on mai.productkey = vp.productkey" + " and vp.visitkey = ?"
				+ " and vp.cmpproductkey is null"
				+ " and coalesce(vp.deleteflag, '0') != '1'"
				// mai.status = '0'   0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
				+ " where mai.lowerkey = ?" + " and (mai.status = '0' or mai.status = '2'  or  mai.status = '3') "
				+ " and am.AGENCYSTATUS = '0'" + " and pm.status = '0'";

		buffer.append(sql);

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId, termKey });
		InvoicingStc item;
		while (cursor.moveToNext()) {
			item = new InvoicingStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			String productkey = cursor.getString(cursor.getColumnIndex("productkey"));
			item.setProId(productkey);
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("inprice"))));// 进店价
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("reprice")));// 零售价

			item.setAddcard(cursor.getString(cursor.getColumnIndex("addcard")));// 累计卡

			// 上周期进货总量
			item.setPrevNum(cursor.getString(cursor.getColumnIndex("purcnum")));
			// 上周期进货总量总和
			item.setPrevNumSum(purcnumMap.get(productkey));
			// 上次库存
			item.setPrevStore(cursor.getString(cursor.getColumnIndex("pronum")));
			// 本次库存
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			// 日销量
			item.setDaySellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// 最早生产日期
			item.setFristdate(cursor.getString(cursor.getColumnIndex("fristdate")));
			lst.add(item);
		}

		return lst;
	}
	/**
	 * 从临时表中 获取协同拜访的我品的进销存数据情况 wx_yss
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<XtInvoicingStc> queryXtMineProByTemp(DatabaseHelper helper, String visitId, String termKey) {

		Map<String, String> purcnumMap = getPurcnumSum(helper, termKey);
		List<XtInvoicingStc> lst = new ArrayList<XtInvoicingStc>();

		StringBuffer buffer = new StringBuffer();

		String sql = "select vp.recordkey," + "mai.productkey,"+ "mai.asupplykey,"  // INPRICE  // REPRICE
				//+ "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.fristdate,"
				// 修改进销存的渠道价零售价 从供货关系表读取  不再从竞品我品表读取
				+ "mai.inprice," + "mai.reprice," + "vp.purcnum," + "vp.fristdate,"
				+ "vp.pronum," + "vp.currnum," + "vp.salenum," + "pm.proname," + "vp.addcard,"
				+ "mai.upperkey as agencykey," + "am.agencyname"
				+ " from mst_agencysupply_info_temp mai"
				+ " join mst_agencyinfo_m am"
				+ " on am.agencykey = mai.upperkey" + " join mst_product_m pm"
				+ " on mai.productkey = pm.productkey"
				+ " left join mst_vistproduct_info_temp vp"
				+ " on mai.productkey = vp.productkey" + " and vp.visitkey = ?"
				+ " and vp.cmpproductkey is null"
				+ " and coalesce(vp.deleteflag, '0') != '1'"
				// mai.status = '0'   0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
				+ " where mai.lowerkey = ?" + " and (mai.status = '0' or mai.status = '2'  or  mai.status = '3') "
				+ " and am.AGENCYSTATUS = '0'" + " and pm.status = '0'";

		buffer.append(sql);

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId, termKey });
		XtInvoicingStc item;
		while (cursor.moveToNext()) {
			item = new XtInvoicingStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			item.setAsupplykey(cursor.getString(cursor.getColumnIndex("asupplykey")));
			String productkey = cursor.getString(cursor.getColumnIndex("productkey"));
			item.setProId(productkey);
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("inprice"))));// 进店价
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("reprice")));// 零售价

			item.setAddcard(cursor.getString(cursor.getColumnIndex("addcard")));// 累计卡

			// 上周期进货总量
			item.setPrevNum(cursor.getString(cursor.getColumnIndex("purcnum")));
			// 上周期进货总量总和
			item.setPrevNumSum(purcnumMap.get(productkey));
			// 上次库存
			item.setPrevStore(cursor.getString(cursor.getColumnIndex("pronum")));
			// 本次库存
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			// 日销量
			item.setDaySellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// 最早生产日期
			item.setFristdate(cursor.getString(cursor.getColumnIndex("fristdate")));
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 从业代数据库中读取 获取 我品的进销存数据情况 wx_yss
	 *
	 * @param helper
	 * @param previsitId
	 *            拜访主键
	 * @return
	 */
	public List<XtInvoicingStc> queryInvocingProByTemp(DatabaseHelper helper, String previsitId, String termKey) {

		Map<String, String> purcnumMap = getPurcnumSum(helper, termKey);
		List<XtInvoicingStc> lst = new ArrayList<XtInvoicingStc>();

		StringBuffer buffer = new StringBuffer();

		String sql = "select vp.recordkey," + "mai.productkey,"+ "mai.asupplykey,"  // INPRICE  // REPRICE
				//+ "vp.purcprice," + "vp.retailprice," + "vp.purcnum," + "vp.fristdate,"
				// 修改进销存的渠道价零售价 从供货关系表读取  不再从竞品我品表读取
				+ "mai.inprice," + "mai.reprice," + "vp.purcnum," + "vp.fristdate,"
				+ "vp.pronum," + "vp.currnum," + "vp.salenum," + "pm.proname," + "vp.addcard,"
				+ "mai.upperkey as agencykey," + "am.agencyname"
				+ " from mst_agencysupply_info mai"
				+ " join mst_agencyinfo_m am"
				+ " on am.agencykey = mai.upperkey" + " join mst_product_m pm"
				+ " on mai.productkey = pm.productkey"
				+ " left join mst_vistproduct_info vp"
				+ " on mai.productkey = vp.productkey" + " and vp.visitkey = ?"
				+ " and vp.cmpproductkey is null"
				+ " and coalesce(vp.deleteflag, '0') != '1'"
				// mai.status = '0'   0:供货关系有效  1:供货关系失效  2:新加供货关系  3:值修改的供货关系
				+ " where mai.lowerkey = ?" + " and (mai.status = '0' or mai.status = '2'  or  mai.status = '3') "
				+ " and am.AGENCYSTATUS = '0'" + " and pm.status = '0'";

		buffer.append(sql);

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { previsitId, termKey });
		XtInvoicingStc item;
		while (cursor.moveToNext()) {
			item = new XtInvoicingStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			item.setAsupplykey(cursor.getString(cursor.getColumnIndex("asupplykey")));
			String productkey = cursor.getString(cursor.getColumnIndex("productkey"));
			item.setProId(productkey);
			item.setProName(cursor.getString(cursor.getColumnIndex("proname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("inprice"))));// 进店价
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("reprice")));// 零售价

			item.setAddcard(cursor.getString(cursor.getColumnIndex("addcard")));// 累计卡

			// 上周期进货总量
			item.setPrevNum(cursor.getString(cursor.getColumnIndex("purcnum")));
			// 上周期进货总量总和
			item.setPrevNumSum(purcnumMap.get(productkey));
			// 上次库存
			item.setPrevStore(cursor.getString(cursor.getColumnIndex("pronum")));
			// 本次库存
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			// 日销量
			item.setDaySellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// 最早生产日期
			item.setFristdate(cursor.getString(cursor.getColumnIndex("fristdate")));
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 获取某次拜访的我品的进销存数据情况
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<ChatVieStc> queryVieProByTemp(DatabaseHelper helper, String visitId) {

		List<ChatVieStc> lst = new ArrayList<ChatVieStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select vp.recordkey,vp.cmpproductkey, vp.purcprice, ");
		buffer.append("vp.retailprice, vp.remarks, pm.cmpprodesc, vp.currnum, ");
		buffer.append("vp.salenum, vp.agencykey, vp.agencyname,pm.cmpproname, vp.cmpcomkey, pc.cmpagencyname ");
		buffer.append("from mst_vistproduct_info_temp vp ");
		buffer.append("left join mst_cmproductinfo_m pm ");
		buffer.append("    on vp.cmpproductkey = pm.cmpproductkey ");
		buffer.append("left join mst_cmpagency_info pc ");
		buffer.append("    on pc.cmpagencykey = vp.agencykey ");
		buffer.append("where vp.visitkey = ? and vp.productkey is null ");
		buffer.append("and coalesce(vp.deleteflag,'0') != '1'");

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		ChatVieStc item;
		while (cursor.moveToNext()) {
			item = new ChatVieStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("cmpproductkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("cmpproname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setCommpayId(cursor.getString(cursor.getColumnIndex("cmpcomkey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));// 用户输入的竞品经销商
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("purcprice"))));
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("retailprice")));
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			item.setMonthSellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// if
			// (CheckUtil.isBlankOrNull(cursor.getString(cursor.getColumnIndex("remarks"))))
			// {
			// item.setDescribe(cursor.getString(cursor.getColumnIndex("cmpprodesc")));
			// } else {
			item.setDescribe(cursor.getString(cursor.getColumnIndex("remarks")));
			// }
			lst.add(item);
		}

		return lst;
	}
	/**
	 * 获取协同拜访的我品的进销存数据情况
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<XtChatVieStc> queryXtVieProByTemp(DatabaseHelper helper, String visitId) {

		List<XtChatVieStc> lst = new ArrayList<XtChatVieStc>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select vp.recordkey,vp.cmpproductkey, vp.purcprice, ");
		buffer.append("vp.retailprice, vp.remarks, pm.cmpprodesc, vp.currnum, ");
		buffer.append("vp.salenum, vp.agencykey, vp.agencyname,pm.cmpproname, vp.cmpcomkey, pc.cmpagencyname ");
		buffer.append("from mst_vistproduct_info_temp vp ");
		buffer.append("left join mst_cmproductinfo_m pm ");
		buffer.append("    on vp.cmpproductkey = pm.cmpproductkey ");
		buffer.append("left join mst_cmpagency_info pc ");
		buffer.append("    on pc.cmpagencykey = vp.agencykey ");
		buffer.append("where vp.visitkey = ? and vp.productkey is null ");
		buffer.append("and coalesce(vp.deleteflag,'0') != '1'");

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] { visitId });
		XtChatVieStc item;
		while (cursor.moveToNext()) {
			item = new XtChatVieStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("cmpproductkey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("cmpproname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setCommpayId(cursor.getString(cursor.getColumnIndex("cmpcomkey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));// 用户输入的竞品经销商
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("purcprice"))));
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("retailprice")));
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			item.setMonthSellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// if
			// (CheckUtil.isBlankOrNull(cursor.getString(cursor.getColumnIndex("remarks"))))
			// {
			// item.setDescribe(cursor.getString(cursor.getColumnIndex("cmpprodesc")));
			// } else {
			item.setDescribe(cursor.getString(cursor.getColumnIndex("remarks")));
			// }
			lst.add(item);
		}

		return lst;
	}

	/**
	 * 获取追溯 聊竞品 数据情况
	 *
	 * @param helper
	 * @param visitId
	 *            拜访主键
	 * @return
	 */
	public List<XtChatVieStc> queryZsVieProByTemp(DatabaseHelper helper, String visitId, String termid) {

		List<XtChatVieStc> lst = new ArrayList<XtChatVieStc>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select vp.recordkey,vp.cmpproductkey, vp.purcprice, mci.cmpsupplykey,  ");
		buffer.append("vp.retailprice, vp.remarks, pm.cmpprodesc, vp.currnum, ");
		buffer.append("vp.salenum, vp.agencykey, vp.agencyname,pm.cmpproname, vp.cmpcomkey, pc.cmpagencyname ");
		buffer.append("from mst_vistproduct_info vp ");
		buffer.append("left join mst_cmproductinfo_m pm ");
		buffer.append("    on vp.cmpproductkey = pm.cmpproductkey ");
		buffer.append("left join mst_cmpagency_info pc ");
		buffer.append("    on pc.cmpagencykey = vp.agencykey ");

		buffer.append(" left join mst_cmpsupply_info mci");
		buffer.append(" 	on mci.cmpproductkey = vp.cmpproductkey  and mci.terminalkey = ? " );

		buffer.append("where vp.visitkey = ? and vp.productkey is null ");
		buffer.append("and coalesce(vp.deleteflag,'0') != '1'");

		Cursor cursor = helper.getReadableDatabase().rawQuery(
				buffer.toString(), new String[] {termid, visitId });
		XtChatVieStc item;
		while (cursor.moveToNext()) {
			item = new XtChatVieStc();
			item.setRecordId(cursor.getString(cursor.getColumnIndex("recordkey")));
			item.setProId(cursor.getString(cursor.getColumnIndex("cmpproductkey")));
			item.setCmpsupplykey(cursor.getString(cursor.getColumnIndex("cmpsupplykey")));
			item.setProName(cursor.getString(cursor.getColumnIndex("cmpproname")));
			item.setAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			item.setCommpayId(cursor.getString(cursor.getColumnIndex("cmpcomkey")));
			item.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));// 用户输入的竞品经销商
			item.setChannelPrice(cursor.getString((cursor.getColumnIndex("purcprice"))));
			item.setSellPrice(cursor.getString(cursor.getColumnIndex("retailprice")));
			item.setCurrStore(cursor.getString(cursor.getColumnIndex("currnum")));
			item.setMonthSellNum(cursor.getString(cursor.getColumnIndex("salenum")));
			// if
			// (CheckUtil.isBlankOrNull(cursor.getString(cursor.getColumnIndex("remarks"))))
			// {
			// item.setDescribe(cursor.getString(cursor.getColumnIndex("cmpprodesc")));
			// } else {
			item.setDescribe(cursor.getString(cursor.getColumnIndex("remarks")));
			// }
			lst.add(item);
		}

		return lst;
	}

}
