/**
 * 
 */
package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.benyoyo.manage.bs.IntStc.DataresultTerPurchaseDetailsStc;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import dd.tsingtaopad.db.dao.MstVisitMDao;
import dd.tsingtaopad.db.table.MstVisitM;
import dd.tsingtaopad.main.operation.indexstatus.domain.IndexStatusStc;
import dd.tsingtaopad.main.visit.tomorrowworkrecord.domain.DayWorkDetailStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情实现DAO</br>
 */
public class MstVisitMDaoImpl extends BaseDaoImpl<MstVisitM, String> implements MstVisitMDao {
	/**
	 * @param connectionSource
	 * @param dataClass
	 * @throws SQLException
	 */
	public MstVisitMDaoImpl(ConnectionSource connectionSource,
			Class<MstVisitM> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

    /**
     * 获取终端进货明细数据
     * @param helper
     * @param key 
     */
	@Override
	public List<DataresultTerPurchaseDetailsStc> searchTerminalDetails(
			SQLiteOpenHelper helper, String[] key) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select bdt.terminalkey terminalkey,bdt.terminalname terminalname,bdp.proname proname,bdp.procode procode,bda.agencykey agencykey,bda.agencyname agencyname,pdb.purcnum purcnum,bdl.routename routename,bdl.routekey routekey "
				+ " from v_VISIT_M pdv join MST_VISTPRODUCT_INFO pdb on pdv.visitkey = pdb.visitkey"
				+ " join MST_TERMINALINFO_M bdt on pdv.terminalkey = bdt.terminalkey"
				+ " join MST_PRODUCT_M bdp on bdp.productkey = pdb.productkey"
				+ " join MST_AGENCYINFO_M bda on bda.agencykey = pdb.agencykey"
				+ " join MST_ROUTE_M bdl on bdt."
				+ "routekey = bdl.routekey"
				+ " where coalesce(bdt.status,'0') != 2 and  bdt.routekey=? and ( pdv.visitdate>=? and pdv.visitdate<=? )";
		// 1 bdt.line_id=?
		Cursor cursor = db.rawQuery(sql, key);
		List<DataresultTerPurchaseDetailsStc> listvo = new ArrayList<DataresultTerPurchaseDetailsStc>();
		while (cursor.moveToNext()) {
		    DataresultTerPurchaseDetailsStc vo = new DataresultTerPurchaseDetailsStc();
			vo.setStrTerminalId(cursor.getString(cursor.getColumnIndex("terminalkey")));
			vo.setStrTerminalName(cursor.getString(cursor.getColumnIndex("terminalname")));
			vo.setStrProName(cursor.getString(cursor.getColumnIndex("proname")));
			vo.setStrProId(cursor.getString(cursor.getColumnIndex("procode")));
			vo.setStrAgencyId(cursor.getString(cursor.getColumnIndex("agencykey")));
			vo.setStrAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));
			vo.setStrNum(cursor.getString(cursor.getColumnIndex("purcnum")));
			vo.setStrLineId(cursor.getString(cursor.getColumnIndex("routekey")));
			vo.setStrLineName(cursor.getString(cursor.getColumnIndex("routename")));
			listvo.add(vo);
		}
		return listvo;
	}

    /**
     * 获取日工作明细数据
     * @param helper
     * @param //key
     */
	@Override
	public List<DayWorkDetailStc> searchTomorrowWorkRecord(
			SQLiteOpenHelper helper, String startdate, String enddate,String useId) {
	    
	    String searchDay = startdate.substring(0, 8);
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select e.routename routename,b.terminalcode terminalcode,b.terminalname terminalname,td.dicname minorchannel,");
		buffer.append("tl.dicname tlevel,b.Contact Contact,b.Address Address,a.visitdate visitdate,a.isself isself,a.iscmp iscmp,a.selftreaty selftreaty,");
		buffer.append("a.cmptreaty cmptreaty,a.visituser visituser,a.status status,vnp.hz hz, vnp.ps ps,a.iscmpcollapse iscmpcollapse,vnp.zy zy,");
		buffer.append("a.ishdistribution ishdistribution,a.selfoccupancy selfoccupancy,a.exetreaty exetreaty,g.procode procode,g.proname proname,");
		buffer.append("c.purcprice purcprice,c.retailprice retailprice,h.agencyname agencyname,c.purcnum purcnum,");
		buffer.append("(c.purcnum + c.pronum - c.currnum) prosalenum,c.currnum currnum,c.salenum salenum,");
		buffer.append("vp.ph ph,vp.dj dj,vp.cp cp,im.colitemname colitemname, i.addcount addcount, i.totalcount totalcount ");
		buffer.append("from v_visit_m a ");
		buffer.append("inner join mst_terminalinfo_m b on a.terminalkey = b.terminalkey ");
		buffer.append("left join cmm_datadic_m td on b.minorchannel = td.diccode ");
		buffer.append("left join cmm_datadic_m tl on b.tlevel= tl.diccode  ");
		buffer.append("inner join mst_route_m e on a.Routekey = e.Routekey ");
		buffer.append("left join mst_vistproduct_info c on a.visitkey = c.visitkey and c.productkey is not null ");
		buffer.append("left join mst_product_m g on g.productkey = c.productkey ");
		buffer.append("left join mst_agencyinfo_m h on h.agencykey = c.agencykey ");
		
		//buffer.append("left join v_pd_check_result vp on c.visitkey = vp.visitkey and c.productkey = vp.productkey and vp.productkey is not null ");
		buffer.append("left join (select  p.terminalkey, p.productkey,");
		buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_puhuo")).append("' then i.cstatusname else null end) ph, ");
		buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_daoju")).append("' then i.cstatusname else null end) dj, ");
		buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_chanpin")).append("' then i.cstatusname else null end) cp "); 
		buffer.append("   from MST_CHECKEXERECORD_INFO p, PAD_CHECKSTATUS_INFO i ");
		buffer.append("   where p.checkkey = i.checkkey and p.acresult = i.cstatuskey");
        buffer.append("   and p.deleteflag!='"+ ConstValues.delFlag+"' ");
		buffer.append("   and '").append(searchDay).append("' >= p.startdate and '").append(searchDay).append("' < p.enddate and p.productkey is not null ");
		buffer.append("   group by p.terminalkey, p.productkey");
		buffer.append(") vp on a.terminalkey = vp.terminalkey and c.productkey = vp.productkey ");
		
        //buffer.append("left join v_pd_check_result vnp on c.visitkey = vnp.visitkey and vnp.productkey is null ");
		buffer.append("left join (select  p.visitkey, ");
		buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_hezuo")).append("' then i.cstatusname else null end) hz,"); 
        buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_peisong")).append("' then i.cstatusname else null end) ps,"); 
        buffer.append("       max(case when p.checkkey = '").append(PropertiesUtil.getProperties("check_zhanyou")).append("' then i.cstatusname else null end) zy "); 
		buffer.append("   from MST_CHECKEXERECORD_INFO p, PAD_CHECKSTATUS_INFO i ");
        buffer.append("   where p.checkkey = i.checkkey and p.acresult = i.cstatuskey");
        buffer.append("   and p.deleteflag!='"+ConstValues.delFlag+"' ");
        buffer.append("   and p.startdate = '").append(searchDay).append("'  and p.productkey is null ");
        buffer.append("   group by p.visitkey) vnp on c.visitkey = vnp.visitkey ");
        
		buffer.append("left join mst_collectionexerecord_info i on c.visitkey = i.visitkey and c.productkey = i.productkey ");
		buffer.append("left join v_pad_checkaccomplish_info im on i.colitemkey = im.colitemkey ");
		buffer.append("where a.visitdate between '");
		buffer.append(startdate);
		buffer.append("' and '");
		buffer.append(enddate );
		buffer.append("' and a.userid='");
		buffer.append(useId);
		buffer.append("' order by a.ROUTEKEY, a.TERMINALKEY ");
		String sql = buffer.toString();
		Cursor cursor = db.rawQuery(sql, null);
		List<DayWorkDetailStc> ddsList = new ArrayList<DayWorkDetailStc>();
		while (cursor.moveToNext()) {
		    DayWorkDetailStc dds = new DayWorkDetailStc();
		    dds.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
		    dds.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
		    dds.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
		    dds.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
		    dds.setTlevel(cursor.getString(cursor.getColumnIndex("tlevel")));
		    dds.setContact(cursor.getString(cursor.getColumnIndex("Contact")));
		    dds.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
		    dds.setVisitdate(cursor.getString(cursor.getColumnIndex("visitdate")));
		    dds.setIsself(cursor.getString(cursor.getColumnIndex("isself")));
		    dds.setIscmp(cursor.getString(cursor.getColumnIndex("iscmp")));
		    dds.setSelftreaty(cursor.getString(cursor.getColumnIndex("selftreaty")));
		    dds.setCmptreaty(cursor.getString(cursor.getColumnIndex("cmptreaty")));
		    dds.setVisituser(cursor.getString(cursor.getColumnIndex("visituser")));
		    dds.setStatus(cursor.getString(cursor.getColumnIndex("status")));
		    dds.setExetreaty(cursor.getString(cursor.getColumnIndex("hz")));
		    dds.setIshdistribution(cursor.getString(cursor.getColumnIndex("ps")));
		    dds.setIscmpcollapse(cursor.getString(cursor.getColumnIndex("iscmpcollapse")));
		    dds.setSelfoccupancy(cursor.getString(cursor.getColumnIndex("zy")));
		    dds.setProcode(cursor.getString(cursor.getColumnIndex("procode")));
		    dds.setProname(cursor.getString(cursor.getColumnIndex("proname")));
		    dds.setPurcprice(cursor.getString(cursor.getColumnIndex("purcprice")));
		    dds.setRetailprice(cursor.getString(cursor.getColumnIndex("retailprice")));
		    dds.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
		    dds.setPurcnum(cursor.getString(cursor.getColumnIndex("purcnum")));
		    dds.setSalenum(cursor.getString(cursor.getColumnIndex("prosalenum")));
		    dds.setCurrnum(cursor.getString(cursor.getColumnIndex("currnum")));
		    dds.setShopstate(cursor.getString(cursor.getColumnIndex("ph")));
		    dds.setProlively(cursor.getString(cursor.getColumnIndex("cp")));
		    dds.setToollively(cursor.getString(cursor.getColumnIndex("dj")));
		    dds.setColitemname(cursor.getString(cursor.getColumnIndex("colitemname")));
		    dds.setTotalcount(cursor.getString(cursor.getColumnIndex("totalcount")));
		    dds.setAddcount(cursor.getString(cursor.getColumnIndex("addcount")));
		    ddsList.add(dds);
		}
		return ddsList;
	};
	
	/**
	 * 获取当天计划拜访终端个数
	 * 
	 * @param helper
	 * @param planDate     计划日期：YYYYMMDD
	 * @return
	 */
	public int queryPlanTermNum(SQLiteOpenHelper helper, String planDate) {
	    
	    int termNum = 0;
	    
	    StringBuffer buffer = new StringBuffer();
	    buffer.append("select count(distinct tm.terminalkey) termnum ");
	    //mst_planforuser_m人员计划主表
	    buffer.append("from mst_planforuser_m pm, mst_route_m rm, mst_terminalinfo_m tm ");
	    buffer.append("where pm.linekey = rm.routekey and pm.linekey = tm.routekey ");
	    buffer.append("  and coalesce(pm.planstatus,'0') in ('0','3','4','5') ");
	    buffer.append("  and coalesce(tm.status,'0') != '2' ");
	    buffer.append("  and pm.plandate = '").append(planDate).append("' ");
	    
	    SQLiteDatabase db = helper.getReadableDatabase();
	    Cursor cursor = db.rawQuery(buffer.toString(), null);
	    cursor.moveToNext();
	    termNum = cursor.getInt(0);
	    return termNum;
	}

    /**
     * 获取当天结束拜访且有效的拜访终端个数
     * 
     * @param helper
     * @param visitDate     拜访日期：YYYYMMDD
     * @return
     */
    public int queryVisitTermNum(SQLiteOpenHelper helper, String visitDate) {
        
        int termNum = 0;
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select count(distinct vm.terminalkey) termnum ");
        buffer.append("from mst_visit_m vm ");
        buffer.append("where substr(vm.visitdate,1,8) = ? ");
        buffer.append(" and vm.status = '1' and vm.uploadflag='1' ");
        
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[] {visitDate});
        cursor.moveToNext();
        termNum = cursor.getInt(0);
        return termNum;
    }
    
    /**
     * 获取某线路的最近或最久拜访的N条数据
     * 
     * @param helper
     * @param routeKey      线路ID
     * @param visitDate     拜访时间：yyyyMMdd
     * @param ascFlag       升序标识：true:最久、 false:最新
     * @param limitNum      获取信息条数
     * @return
     */
    public List<MstVisitM> queryForNum(SQLiteOpenHelper helper, 
             String routeKey, String visitDate, boolean ascFlag, int limitNum) {
        
        List<String> args = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM mst_visit_m m ");
        buffer.append("inner JOIN (SELECT MAX (visitdate) visitdate,routekey maxroutekey ");
        buffer.append("  FROM mst_visit_m where status = '1' ");
        if (!CheckUtil.isBlankOrNull(visitDate)) {
            buffer.append(" and VISITDATE <= ? ");
            args.add(visitDate);
        }
        buffer.append("  GROUP BY routekey, SUBSTR(visitdate, 1, 8)) v ");
        buffer.append("ON M.routekey = v.maxroutekey AND M .visitdate = v.visitdate ");
        buffer.append("where 1 = 1 ");
        if (!CheckUtil.isBlankOrNull(routeKey)) {
            buffer.append("and m.routekey = ? ");
            args.add(routeKey);
        }
        if (ascFlag) {
            buffer.append("order by m.visitdate asc ");
        } else {
            buffer.append("order by m.visitdate desc ");
        }
        if (limitNum == 0) {
            buffer.append("limit 1 ");
        } else {
            buffer.append("limit ").append(limitNum);
        }
        
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), args.toArray(new String[]{}));
        MstVisitM info = null;
        List<MstVisitM> lst = new ArrayList<MstVisitM>();
        while (cursor.moveToNext()) {
            info = new MstVisitM();
            info.setVisitkey(cursor.getString(cursor.getColumnIndex("visitkey")));
            info.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            info.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            info.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
            info.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
            info.setVisitdate(cursor.getString(cursor.getColumnIndex("visitdate")));
            // 如若需要添加追加
            lst.add(info);
        }
        
        return lst;
    }
    
    /**
     * 指标状态查询
     * 
     * @param lineId        线路ID
     * @param lineCurrDate  线路当前拜访日期：yyyyMMdd
     * @param linePrevDate  线路上一次拜访日期：yyyyMMdd
     * @param gridCurrDate  定格下各线路当前拜访日期：yyyyMMdd
     * @param gridPrevDate  定格下各线路上一次拜访日期：yyyyMMdd
     * @param checkId       指标ID
     * @param valueId       指标值ID
     * @param productIds    产品ID集合
     */
    public List<IndexStatusStc> queryForIndexStatus(SQLiteOpenHelper helper, String lineId, String lineCurrDate,
													String linePrevDate, String[] gridCurrDate, String[] gridPrevDate, String checkId, String valueId, String[] productIds) {
        
        List<IndexStatusStc> lst = new ArrayList<IndexStatusStc>();
        
        List<String> args = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select pm.productkey,max(pm.proname) proname, ");
        buffer.append("count(distinct case when (substr(vmd.VISITDATE,1,8) || vmd.routekey) = ? then vmd.terminalkey else null end ) linePrevNum, ");
        args.add(linePrevDate);
        buffer.append("count(distinct case when (substr(vmd.VISITDATE,1,8) || vmd.routekey) = ? then vmd.terminalkey else null end ) lineCurrNum, ");
        args.add(lineCurrDate);
        List<String> gridDate = new ArrayList<String>();
        if (CheckUtil.IsEmpty(gridPrevDate)) {
            buffer.append("count(0) gridPrevNum, ");
        } else {
            buffer.append("count(distinct case when (substr(vmd.visitdate,1,8) || vmd.routekey) in ( ");
            for (String item : gridPrevDate) {
                args.add(item);
                buffer.append("?, ");
                gridDate.add(item);
            }
            buffer.deleteCharAt(buffer.length() - 2).append(") then vmd.terminalkey else null end ) gridPrevNum, ");
        }
        if (CheckUtil.IsEmpty(gridCurrDate)) {
            buffer.append("count(0) gridCurrNum ");
        } else {
            buffer.append("count(distinct case when (substr(vmd.visitdate,1,8) || vmd.routekey) in ( ");
            for (String item : gridCurrDate) {
                args.add(item);
                buffer.append("?, ");
                gridDate.add(item);
            }
            buffer.deleteCharAt(buffer.length() - 2).append(") then vmd.terminalkey else null end ) gridCurrNum ");
        }
        buffer.append("from mst_product_m pm ");
        buffer.append("left join ( ");
        buffer.append(" select vc.productkey, vc.terminalkey, vm.visitdate, vm.routekey ");
        buffer.append(" from v_visit_m vm, mst_checkexerecord_info vc ");
        buffer.append(" where vm.VISITKEY = vc.visitkey ");
        buffer.append("   and vc.deleteflag!='"+ConstValues.delFlag+"' ");
        
        if (!CheckUtil.IsEmpty(gridDate)) {
            buffer.append("and (substr(vm.visitdate,1,8) || vm.routekey) in ( ");
            for (String item : gridDate) {
                args.add(item);
                buffer.append("?, ");
            }
            buffer.deleteCharAt(buffer.length() - 2).append(") ");
        }
        buffer.append("and vc.checkkey = ? and vc.acresult = ? ");
        args.add(checkId);
        args.add(valueId);
        buffer.append(") vmd on  pm.productkey = vmd.productkey ");
        buffer.append("where 1 = 1 ");
        if (!CheckUtil.IsEmpty(productIds)) {
            buffer.append("and pm.productkey in ( ");
            for (String item : productIds) {
                args.add(item);
                buffer.append("?, ");
            }
            buffer.deleteCharAt(buffer.length() - 2).append(") ");
        }
        buffer.append("group by pm.productkey ");
        
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), args.toArray(new String[]{}));
        IndexStatusStc info = null;
        while (cursor.moveToNext()) {
            info = new IndexStatusStc();
            info.setProName(cursor.getString(cursor.getColumnIndex("proname")));
            info.setLinePrevNum(cursor.getInt(cursor.getColumnIndex("linePrevNum")));
            info.setLineCurrNum(cursor.getInt(cursor.getColumnIndex("lineCurrNum")));
            info.setGridPrevNum(cursor.getInt(cursor.getColumnIndex("gridPrevNum")));
            info.setGridCurrNum(cursor.getInt(cursor.getColumnIndex("gridCurrNum")));
            lst.add(info);
        }
        
        return lst;
    }
}
