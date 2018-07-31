package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstTerminalinfoMDao;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.main.visit.shopvisit.term.domain.MstTermListMStc;
import et.tsingtaopad.main.visit.shopvisit.term.domain.TermSequence;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.TerminalName;
import et.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端表的DAO层</br>
 */
public class MstTerminalinfoMDaoImpl extends BaseDaoImpl<MstTerminalinfoM, String> implements MstTerminalinfoMDao {

    public MstTerminalinfoMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstTerminalinfoM.class);
    }

    /**
     * 获取某线路下的终端列表 (业代拜访,某条路线)
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermLst(SQLiteOpenHelper helper, String lineId) {
        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();
        lst.addAll(getTermList_sequence(helper, lineId, true));
        lst.addAll(getTermList_sequence(helper, lineId, false));
        return lst;
    }

    /**
     *  (协同拜访,某条路线) 选择终端界面
     * <p>
     * 用于：巡店拜访  -- 终端选择  (2018年3月21日16:50:54 新加 )
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<XtTermSelectMStc> queryLineTermLst(SQLiteOpenHelper helper, String lineId) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getLineTermList_sequence(helper, lineId, true));
        lst.addAll(getLineTermList_sequence(helper, lineId, false));
        return lst;
    }

    /**
     *  (协同拜访,某条路线) 选择终端界面
     * <p>
     * 用于：巡店拜访  -- 终端选择  (2018年3月21日16:50:54 新加 )
     *
     * 搜索终端主表的全部终端  (2018年7月23日20:38:09)
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryXtTestLineTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getXtTestLineTermList_sequence(helper, true));
        lst.addAll(getXtTestLineTermList_sequence(helper, false));
        return lst;
    }

    /**
     *  (终端追溯,某条路线) 选择终端界面
     * <p>
     * 用于：终端追溯  -- 终端选择  (2018年5月2日新加 )
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<XtTermSelectMStc> queryZsLineTermLst(SQLiteOpenHelper helper, String lineId) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getZsLineTermList_sequence(helper, lineId, true));
        lst.addAll(getZsLineTermList_sequence(helper, lineId, false));
        return lst;
    }

    /**
     *  终端追溯, 选择终端界面 有多少终端显示多少
     * <p>
     * 用于：终端追溯  -- 终端选择  (2018年5月2日新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryZsTestLineTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getZsTestLineTermList_sequence(helper, true));
        lst.addAll(getZsTestLineTermList_sequence(helper, false));
        return lst;
    }
    public List<XtTermSelectMStc> queryShaixuanTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getZsTestLineTermList_Shaixuansequence(helper));
        return lst;
    }

    /**
     * 获取购物车下的终端列表  (协同)
     * <p>
     * 用于：巡店拜访  -- 终端选择  (2018年3月23日16:50:54 新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryCartTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getCartTermList_sequence(helper,  true));
        lst.addAll(getCartTermList_sequence(helper, false));
        return lst;
    }

    /**
     * 获取购物车下的终端列表
     * <p>
     * 用于：终端追溯  -- 终端选择  (2018年5月2日 新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryZsCartTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getZsCartTermList_sequence(helper,  true));
        lst.addAll(getZsCartTermList_sequence(helper, false));
        return lst;
    }

    /**
     * 获取购物车下的终端列表
     * <p>
     * 用于：巡店+追溯  -- 终端选择  (2018年5月2日16:50:54 新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryAllCartTermLst(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        lst.addAll(getAllCartTermList_sequence(helper,  true));
        lst.addAll(getAllCartTermList_sequence(helper, false));
        return lst;
    }

    /***
     * 通过终端名称查询终端集合（模糊查询） (同时关联路线表,查看这个终端是那条路线的)
     * @param helper
     * @param termName
     * @return
     */
    @Override
    public List<MstTermListMStc> getTermListByName(SQLiteOpenHelper helper, String termName) {
        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select tm.terminalkey,tm.terminalcode,tm.terminalname,tm.mobile,tm.status,tm.contact,tm.address,tm.routekey,rm.routename ");
        buffer.append("from mst_terminalinfo_m tm ");
        buffer.append("inner join mst_route_m rm ");
        buffer.append("on tm.routekey=rm.routekey and coalesce(rm.deleteflag, '0') != '1' and coalesce(rm.routestatus, '0') != '1' ");
        buffer.append("where coalesce(tm.status,'0') != '2' ");
        buffer.append("and tm.terminalname like '%" + termName + "%' ");
        buffer.append("and coalesce(tm.deleteflag,'0') != '1' ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        MstTermListMStc item;
        while (cursor.moveToNext()) {
            item = new MstTermListMStc();
            item.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            item.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            item.setRoutekey(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("routekey"))));
            item.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }

    /***
     * 获取线路下的终端集合
     * @param helper
     * @param lineId      线路主键
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<MstTermListMStc> getTermList_sequence(SQLiteOpenHelper helper, String lineId, boolean isSequence) {
        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vm.isself, vm.iscmp, m.selftreaty, vm.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent, vm.uploadFlag, m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_visit_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2' and m.routekey=? ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{lineId});
        MstTermListMStc item;
        while (cursor.moveToNext()) {
            item = new MstTermListMStc();
            item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }

    /***
     * 获取线路下的终端集合
     * @param helper
     * @param lineId      线路主键
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getLineTermList_sequence(SQLiteOpenHelper helper, String lineId, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent, vm.uploadFlag, m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate, vm.enddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_visit_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2' and m.routekey=? ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{lineId});
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("enddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            item.setRoutekey(lineId);
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }
    /***
     * 获取线路下的终端集合
     * @param helper
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getXtTestLineTermList_sequence(SQLiteOpenHelper helper, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode,m.routekey, m.terminalname,m.status,m.sequence, ");

        buffer.append("m.city, m.province,m.county,m.address,m.contact,m.mobile,m.tlevel, ");
        buffer.append("m.cycle, m.hvolume,m.mvolume,m.pvolume,m.lvolume,m.sellchannel, ");
        buffer.append("m.mainchannel, m.areatype,m.sisconsistent,m.comid,m.remarks,m.orderbyno,m.creuser, ");
        buffer.append("m.cmpselftreaty, m.updateuser,m.deleteflag,m.ifminedate,m.ifmine,  ");

        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent, vm.uploadFlag, m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate, vm.enddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_visit_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2'  ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("enddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            // item.setRoutekey(lineId);


            item.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            item.setCity(cursor.getString(cursor.getColumnIndex("city")));
            item.setCounty(cursor.getString(cursor.getColumnIndex("county")));
            item.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            item.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            item.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            item.setTlevel(cursor.getString(cursor.getColumnIndex("tlevel")));
            item.setCycle(cursor.getString(cursor.getColumnIndex("cycle")));
            item.setHvolume(cursor.getString(cursor.getColumnIndex("hvolume")));
            item.setMvolume(cursor.getString(cursor.getColumnIndex("mvolume")));
            item.setPvolume(cursor.getString(cursor.getColumnIndex("pvolume")));
            item.setLvolume(cursor.getString(cursor.getColumnIndex("lvolume")));
            item.setSellchannel(cursor.getString(cursor.getColumnIndex("sellchannel")));
            item.setMainchannel(cursor.getString(cursor.getColumnIndex("mainchannel")));
            item.setAreatype(cursor.getString(cursor.getColumnIndex("areatype")));
            item.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
            item.setComid(cursor.getString(cursor.getColumnIndex("comid")));
            item.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
            item.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
            item.setCreuser(cursor.getString(cursor.getColumnIndex("creuser")));
            item.setCmpselftreaty(cursor.getString(cursor.getColumnIndex("cmpselftreaty")));
            item.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
            item.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
            item.setIfminedate(cursor.getString(cursor.getColumnIndex("ifminedate")));
            item.setIfmine(cursor.getString(cursor.getColumnIndex("ifmine")));


            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }
    /***
     * 获取追溯线路下的终端集合
     * @param helper
     * @param lineId      线路主键
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getZsLineTermList_sequence(SQLiteOpenHelper helper, String lineId, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent,  m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate,vm.videnddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_valter_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2' and m.routekey=? ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{lineId});
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("videnddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));// 0:未上传  1:已上传
                item.setUploadFlag("1");// 0:不上传  1:需上传
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }
    /***
     * 获取追溯终端集合, 终端表有多少出多少
     * @param helper
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getZsTestLineTermList_sequence(SQLiteOpenHelper helper, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.routekey, m.terminalname,m.status,m.sequence, ");

        buffer.append("m.city,m.province, m.county,m.address,m.contact,m.mobile,m.tlevel, ");
        buffer.append("m.cycle, m.hvolume,m.mvolume,m.pvolume,m.lvolume,m.sellchannel, ");
        buffer.append("m.mainchannel, m.areatype,m.sisconsistent,m.comid,m.remarks,m.orderbyno,m.creuser, ");
        buffer.append("m.cmpselftreaty, m.updateuser,m.deleteflag,m.ifminedate,m.ifmine,  ");

        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        // buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty,vm.iserror, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty,max(qwe.status) iserror, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent,  m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate,vm.videnddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_valter_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");


        buffer.append("left join MIT_REPAIRTER_M abc on m.terminalkey = abc.terminalkey   ");
        buffer.append("left join MIT_REPAIR_M qwe on qwe.id = abc.repairid  and qwe.status in ('0','1') ");




        buffer.append("where coalesce(m.status,'0') != '2'  ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }

        buffer.append("group by m.terminalkey, m.terminalcode, m.routekey, m.terminalname, m.status, m.sequence, m.city, m.province, m.county, m.address, m.contact, m.mobile, m.tlevel, m.cycle, m.hvolume, m.mvolume, m.pvolume, m.lvolume, m.sellchannel, m.mainchannel, m.areatype, m.sisconsistent, m.comid, m.remarks, m.orderbyno, m.creuser, m.cmpselftreaty, m.updateuser, m.deleteflag, m.ifminedate, m.ifmine, vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, vm.iserror, vm.padisconsistent, m.minorchannel, dm.dicname, vm.visitdate, vm.videnddate ");

        // buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");
        buffer.append("order by m.sequence asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setIserror(cursor.getString(cursor.getColumnIndex("iserror")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("videnddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));// 0:未上传  1:已上传
                item.setUploadFlag("1");// 0:不上传  1:需上传
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));

            item.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            item.setCity(cursor.getString(cursor.getColumnIndex("city")));
            item.setCounty(cursor.getString(cursor.getColumnIndex("county")));
            item.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            item.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            item.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            item.setTlevel(cursor.getString(cursor.getColumnIndex("tlevel")));
            item.setCycle(cursor.getString(cursor.getColumnIndex("cycle")));
            item.setHvolume(cursor.getString(cursor.getColumnIndex("hvolume")));
            item.setMvolume(cursor.getString(cursor.getColumnIndex("mvolume")));
            item.setPvolume(cursor.getString(cursor.getColumnIndex("pvolume")));
            item.setLvolume(cursor.getString(cursor.getColumnIndex("lvolume")));
            item.setSellchannel(cursor.getString(cursor.getColumnIndex("sellchannel")));
            item.setMainchannel(cursor.getString(cursor.getColumnIndex("mainchannel")));
            item.setAreatype(cursor.getString(cursor.getColumnIndex("areatype")));
            item.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
            item.setComid(cursor.getString(cursor.getColumnIndex("comid")));
            item.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
            item.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
            item.setCreuser(cursor.getString(cursor.getColumnIndex("creuser")));
            item.setCmpselftreaty(cursor.getString(cursor.getColumnIndex("cmpselftreaty")));
            item.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
            item.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
            item.setIfminedate(cursor.getString(cursor.getColumnIndex("ifminedate")));
            item.setIfmine(cursor.getString(cursor.getColumnIndex("ifmine")));
            item.setSelftreaty(cursor.getString(cursor.getColumnIndex("selftreaty")));

            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }
    /***
     * 获取追溯终端集合, 终端表有多少出多少
     * @param helper
     * @return
     */
    private List<XtTermSelectMStc> getZsTestLineTermList_Shaixuansequence(SQLiteOpenHelper helper) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.routekey, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty,vm.iserror, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent,  m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate,vm.videnddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m_down m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_valter_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2'  ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        /*if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }*/
        // buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");
        buffer.append("order by m.sequence asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setIserror(cursor.getString(cursor.getColumnIndex("iserror")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("videnddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));// 0:未上传  1:已上传
                item.setUploadFlag("1");// 0:不上传  1:需上传
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }

    /***
     * 获取线路下的终端集合 (购物车 协同)
     * @param helper
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getCartTermList_sequence(SQLiteOpenHelper helper, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode,m.routekey, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent, vm.uploadFlag, m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate,vm.enddate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m_cart m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_visit_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2' ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        buffer.append(" and m.ddtype = '1' ");// 1:协同  2:追溯
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            //item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("enddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }
    /***
     * 获取线路下的终端集合 (购物车 协同+追溯 所有终端)
     * @param helper
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getAllCartTermList_sequence(SQLiteOpenHelper helper, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        /*buffer.append("select m.terminalkey, m.terminalcode,m.routekey, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vm.isself, vm.iscmp, m.selftreaty, vm.cmptreaty, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent, vm.uploadFlag, m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m_cart m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_visit_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("where coalesce(m.status,'0') != '2' ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            //item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            item.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }*/

        buffer.append("select distinct terminalkey from (select * from MST_TERMINALINFO_M_ZSCART  ");
        buffer.append("Union ");
        buffer.append("select * from  MST_TERMINALINFO_M_CART) a ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            lst.add(item);
        }
        return lst;
    }
    /***
     * 获取线路下的终端集合 (购物车 追溯)
     * @param helper
     * @param isSequence  是否查询已排序的终端
     * @return
     */
    private List<XtTermSelectMStc> getZsCartTermList_sequence(SQLiteOpenHelper helper, boolean isSequence) {
        List<XtTermSelectMStc> lst = new ArrayList<XtTermSelectMStc>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, ");
        //buffer.append("vm.isself, vm.iscmp, vm.selftreaty, vm.cmptreaty, ");
        buffer.append("vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty,max(qwe.status) iserror, ");// 我品 竞品 我品协议店,竞品协议店
        buffer.append("vm.padisconsistent,  m.minorchannel, ");// 销售渠道编码
        buffer.append("dm.dicname terminalType, vm.visitdate,vm.videnddate  ");// 终端渠道类型 拜访时间
        buffer.append("from mst_terminalinfo_m_zscart m ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("left join v_mit_valter_m_newest vm on m.terminalkey = vm.terminalkey ");
        buffer.append("left join v_visit_m_newest vmn on m.terminalkey = vmn.terminalkey ");

        buffer.append("left join MIT_REPAIRTER_M abc on m.terminalkey = abc.terminalkey   ");
        buffer.append("left join MIT_REPAIR_M qwe on qwe.id = abc.repairid  and qwe.status in ('0','1') ");

        buffer.append("where coalesce(m.status,'0') != '2' ");
        buffer.append(" and coalesce(m.deleteflag,'0') != '1' ");
        buffer.append(" and m.ddtype = '2' ");// 1:协同  2:追溯
        if (isSequence) {
            buffer.append(" and m.sequence!='' and m.sequence not null ");// 终端排序不为空
        } else {
            buffer.append(" and (m.sequence='' or m.sequence is null) ");// 终端排序为空的
        }
        buffer.append(" group by m.terminalkey, m.terminalcode, m.terminalname, m.status, m.sequence, vmn.isself, vmn.iscmp, m.selftreaty, vmn.cmptreaty, vm.iserror, vm.padisconsistent, m.minorchannel, dm.dicname , vm.visitdate  ");
        buffer.append("order by m.sequence+0 asc, m.orderbyno, m.terminalname ");

        String visitDate = "";
        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        XtTermSelectMStc item;
        while (cursor.moveToNext()) {
            item = new XtTermSelectMStc();
            //item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            item.setIserror(cursor.getString(cursor.getColumnIndex("iserror")));

            item.setEndDate(cursor.getString(cursor.getColumnIndex("videnddate")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {// 若果 记录是当天生成的
                item.setVisitTime(visitDate);
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag("1");
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }
        }
        return lst;
    }

    /**
     * 获取某线路下的各终端当天的所有拜访进店及离店时间
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermVistTime(SQLiteOpenHelper helper, String lineId) {

        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();

        // 获取该线路下所有终端当天单击过上传按钮的数据
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, vm.visitdate, vm.enddate  ");
        buffer.append("from mst_terminalinfo_m m  ");
        buffer.append("inner join mst_visit_m vm on m.terminalkey = vm.terminalkey ");
        buffer.append("     and (vm.uploadflag='1' or vm.padisconsistent ='1') ");
        buffer.append("     and substr(vm.visitdate,1,8) = ? ");
        buffer.append("where m.routekey = ? and coalesce(m.deleteflag,'0') != '1'");
        buffer.append("order by m.terminalkey, vm.visitdate ");

        String[] args = new String[2];
        args[0] = GlobalValues.loginSession.getLoginDate().substring(0, 10).replace("-", "");
        args[1] = lineId;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), args);
        MstTermListMStc item;
        while (cursor.moveToNext()) {
            item = new MstTermListMStc();
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setVisitDate(cursor.getString(cursor.getColumnIndex("visitdate")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("enddate")));
            lst.add(item);
        }
        return lst;
    }

    @Override
    public List<MstTermListMStc> queryTermVistTimeByLoginDate(DatabaseHelper helper, String lineId, String loginDate) {


        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();

        // 获取该线路下所有终端当天单击过上传按钮的数据
        StringBuffer buffer = new StringBuffer();
        buffer.append("select m.terminalkey, vm.visitdate, vm.enddate  ");
        buffer.append("from mst_terminalinfo_m m  ");
        buffer.append("inner join mst_visit_m vm on m.terminalkey = vm.terminalkey ");
        buffer.append("     and (vm.uploadflag='1' or vm.padisconsistent ='1') ");
        buffer.append("     and substr(vm.visitdate,1,8) = ? ");
        buffer.append("where m.routekey = ? and coalesce(m.deleteflag,'0') != '1'");
        buffer.append("order by m.terminalkey, vm.visitdate ");
        // inner join  只取出匹配的数据
        // select m.terminalkey, vm.visitdate, vm.enddate  from mst_terminalinfo_m m  inner join mst_visit_m vm on m.terminalkey = vm.terminalkey      and (vm.uploadflag='1' or vm.padisconsistent ='1')      and substr(vm.visitdate,1,8) = ? where m.routekey = ? and coalesce(m.deleteflag,'0') != '1'order by m.terminalkey, vm.visitdate

        String[] args = new String[2];
        args[0] = loginDate;
        args[1] = lineId;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), args);
        MstTermListMStc item;
        while (cursor.moveToNext()) {
            item = new MstTermListMStc();
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setVisitDate(cursor.getString(cursor.getColumnIndex("visitdate")));
            item.setEndDate(cursor.getString(cursor.getColumnIndex("enddate")));
            lst.add(item);
        }
        return lst;
    }

    /**
     * 获取某线路下的当天已上传终端  关键在这条sql出几条
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermUpflag(SQLiteOpenHelper helper, String lineId) {

        List<MstTermListMStc> lst = new ArrayList<MstTermListMStc>();

        // 获取该线路下所有终端当天单击过上传按钮的数据
        StringBuffer buffer = new StringBuffer();
        //select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, 
        //vm.isself, vm.iscmp, m.selftreaty, vm.cmptreaty, vm.padisconsistent, vm.uploadFlag, 
        //m.minorchannel, dm.dicname terminalType, vm.visitdate
        buffer.append("select m.terminalkey, m.terminalcode, m.terminalname,m.status,m.sequence, vm.enddate,  ");
        buffer.append("vm.isself, vm.iscmp, m.selftreaty, vm.cmptreaty, vm.padisconsistent, vm.uploadFlag,");
        buffer.append("m.minorchannel, dm.dicname terminalType, vm.visitdate ");
        //buffer.append("select m.terminalkey, vm.visitdate, vm.enddate  ");  
        buffer.append("from mst_terminalinfo_m m  ");
        buffer.append("inner join mst_visit_m vm on m.terminalkey = vm.terminalkey ");
        buffer.append("     and (vm.uploadflag='1' or vm.padisconsistent ='1') ");// 当天确定上传的或已经上传的
        buffer.append("     and substr(vm.visitdate,1,8) = ? ");
        buffer.append("left join cmm_datadic_m dm on m.minorchannel = dm.diccode ");
        buffer.append("     and coalesce(dm.deleteflag,'0') != '1' ");
        buffer.append("where m.routekey = ? and coalesce(m.deleteflag,'0') != '1' ");
        buffer.append("group by m.terminalkey ");

        buffer.append("order by m.terminalkey, vm.visitdate ");

        String currDay = DateUtil.formatDate(new Date(), "yyyyMMdd");
        String[] args = new String[2];
        args[0] = currDay;
        args[1] = lineId;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), args);
        MstTermListMStc item;
        String visitDate = "";

        while (cursor.moveToNext()) {
            // ----------

            item = new MstTermListMStc();
            item.setRoutekey(lineId);
            item.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            item.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            item.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            item.setMineFlag(cursor.getString(cursor.getColumnIndex("isself")));
            item.setVieFlag(cursor.getString(cursor.getColumnIndex("iscmp")));
            item.setMineProtocolFlag(cursor.getString(cursor.getColumnIndex("selftreaty")));
            item.setVieProtocolFlag(cursor.getString(cursor.getColumnIndex("cmptreaty")));
            visitDate = cursor.getString(cursor.getColumnIndex("visitdate"));
            if (visitDate != null && currDay.equals(visitDate.substring(0, 8))) {
                item.setSyncFlag(cursor.getString(cursor.getColumnIndex("padisconsistent")));
                item.setUploadFlag(cursor.getString(cursor.getColumnIndex("uploadFlag")));
            } else {
                item.setSyncFlag(null);
                item.setUploadFlag(null);
            }
            item.setMinorchannel(FunUtil.isNullSetSpace(cursor.getString(cursor.getColumnIndex("minorchannel"))));
            item.setTerminalType(cursor.getString(cursor.getColumnIndex("terminalType")));
            String status = item.getStatus();
            if (!"2".equals(status)) {//有效终端
                lst.add(item);
            }

        }
        return lst;
    }

    /**
     * 依据终端主键获取终端 信息（包含数据字典对应的名称）
     *
     * @param helper
     * @param termId 终端主键
     * @return
     */
    public MstTerminalInfoMStc findById(SQLiteOpenHelper helper, String termId) {

        MstTerminalInfoMStc stc = new MstTerminalInfoMStc();

        StringBuffer buffer = new StringBuffer();
        buffer.append("select tm.*, prov.areaname provname, city.areaname cityname, country.areaname countryname, ");
        buffer.append("sell.dicname sellchannelname, mains.dicname mainchannelname, minors.dicname minorchannelname ");
        buffer.append("from mst_terminalinfo_m tm ");
        buffer.append("left join cmm_area_m prov on tm.province = prov.areacode ");
        buffer.append("left join cmm_area_m city on tm.city = city.areacode and city.parentcode = tm.province ");
        buffer.append("left join cmm_area_m country on tm.county = country.areacode and country.parentcode = tm.city ");
        buffer.append("left join cmm_datadic_m sell on tm.sellchannel = sell.diccode ");
        buffer.append("left join cmm_datadic_m mains on tm.mainchannel = mains.diccode and mains.parentcode = tm.sellchannel ");
        buffer.append("left join cmm_datadic_m minors on tm.minorchannel = minors.diccode and minors.parentcode = tm.mainchannel ");
        buffer.append("where tm.terminalkey = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{termId});
        while (cursor.moveToNext()) {
            stc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            stc.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            stc.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            stc.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            stc.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            stc.setProvName(cursor.getString(cursor.getColumnIndex("provname")));
            stc.setCity(cursor.getString(cursor.getColumnIndex("city")));
            stc.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
            stc.setCounty(cursor.getString(cursor.getColumnIndex("county")));
            stc.setCountryName(cursor.getString(cursor.getColumnIndex("countryname")));
            stc.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            stc.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            stc.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            stc.setTlevel(cursor.getString(cursor.getColumnIndex("tlevel")));
            stc.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            stc.setCycle(cursor.getString(cursor.getColumnIndex("cycle")));
            stc.setMvolume(cursor.getString(cursor.getColumnIndex("mvolume")));
            stc.setPvolume(cursor.getString(cursor.getColumnIndex("pvolume")));
            stc.setLvolume(cursor.getString(cursor.getColumnIndex("lvolume")));
            stc.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            stc.setSellchannel(cursor.getString(cursor.getColumnIndex("sellchannel")));
            stc.setSellChannelName(cursor.getString(cursor.getColumnIndex("sellchannelname")));
            stc.setMainchannel(cursor.getString(cursor.getColumnIndex("mainchannel")));
            stc.setMainChannelName(cursor.getString(cursor.getColumnIndex("mainchannelname")));
            stc.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
            stc.setMinorChannelName(cursor.getString(cursor.getColumnIndex("minorchannelname")));
            stc.setAreatype(cursor.getString(cursor.getColumnIndex("areatype")));
            stc.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
            stc.setScondate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(scondate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
            stc.setPadcondate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(padcondate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setComid(cursor.getString(cursor.getColumnIndex("comid")));
            stc.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
            stc.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
            stc.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
            stc.setVersion(new BigDecimal(cursor.getInt(cursor.getColumnIndex("version"))));
            stc.setCredate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(credate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setCreuser(cursor.getString(cursor.getColumnIndex("creuser")));
            stc.setUpdatetime(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(updatetime,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
        }

        return stc;
    }

    /**
     * 通过终端key,查询大区id,二级区域id,定格key,路线key
     * @param helper
     * @param termId
     * @return
     */
    public MstTerminalInfoMStc findKeyById(SQLiteOpenHelper helper, String termId) {

        MstTerminalInfoMStc stc = new MstTerminalInfoMStc();

        StringBuffer buffer = new StringBuffer();
        buffer.append("select sm.areaid,sm.areapid,g.gridkey,g.gridname,g.username,g.userid,  ");
        buffer.append("r.routekey,r.routename,t.terminalkey,t.terminalname   ");
        buffer.append("from mst_terminalinfo_m_temp t  ");
        buffer.append("left join mst_route_m r on t.routekey = r.routekey  ");
        buffer.append("left join mst_grid_m g on g.gridkey = r.gridkey  ");
        buffer.append("left join mst_marketarea_m sm on sm.areaid = g.areaid  ");
        buffer.append("where t.terminalkey = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{termId});
        while (cursor.moveToNext()) {
            stc.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
            stc.setAreapid(cursor.getString(cursor.getColumnIndex("areapid")));
            stc.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
            stc.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
            stc.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
            stc.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            stc.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            stc.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
            stc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            stc.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
        }
        return stc;
    }

    /**
     * 依据终端主键获取终端 信息（包含数据字典对应的名称）
     * <p>
     * 上传图片时,由于之前的方法(findById)出错,所以重写,注释掉会出错的代码
     *
     * @param helper
     * @param termId 终端主键
     * @return
     */
    public MstTerminalInfoMStc findByTermId(SQLiteOpenHelper helper, String termId) {

        MstTerminalInfoMStc stc = new MstTerminalInfoMStc();

        StringBuffer buffer = new StringBuffer();
        buffer.append("select tm.*, prov.areaname provname, city.areaname cityname, country.areaname countryname, ");
        buffer.append("sell.dicname sellchannelname, mains.dicname mainchannelname, minors.dicname minorchannelname ");
        buffer.append("from mst_terminalinfo_m tm ");
        buffer.append("left join cmm_area_m prov on tm.province = prov.areacode ");
        buffer.append("left join cmm_area_m city on tm.city = city.areacode and city.parentcode = tm.province ");
        buffer.append("left join cmm_area_m country on tm.county = country.areacode and country.parentcode = tm.city ");
        buffer.append("left join cmm_datadic_m sell on tm.sellchannel = sell.diccode ");
        buffer.append("left join cmm_datadic_m mains on tm.mainchannel = mains.diccode and mains.parentcode = tm.sellchannel ");
        buffer.append("left join cmm_datadic_m minors on tm.minorchannel = minors.diccode and minors.parentcode = tm.mainchannel ");
        buffer.append("where tm.terminalkey = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{termId});
        while (cursor.moveToNext()) {
            stc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            stc.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            stc.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
            stc.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            stc.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            stc.setProvName(cursor.getString(cursor.getColumnIndex("provname")));
            stc.setCity(cursor.getString(cursor.getColumnIndex("city")));
            stc.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
            stc.setCounty(cursor.getString(cursor.getColumnIndex("county")));
            stc.setCountryName(cursor.getString(cursor.getColumnIndex("countryname")));
            stc.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            stc.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            stc.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            stc.setTlevel(cursor.getString(cursor.getColumnIndex("tlevel")));
            stc.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
            stc.setCycle(cursor.getString(cursor.getColumnIndex("cycle")));
            stc.setMvolume(cursor.getString(cursor.getColumnIndex("mvolume")));
            stc.setPvolume(cursor.getString(cursor.getColumnIndex("pvolume")));
            stc.setLvolume(cursor.getString(cursor.getColumnIndex("lvolume")));
            stc.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            stc.setSellchannel(cursor.getString(cursor.getColumnIndex("sellchannel")));
            stc.setSellChannelName(cursor.getString(cursor.getColumnIndex("sellchannelname")));
            stc.setMainchannel(cursor.getString(cursor.getColumnIndex("mainchannel")));
            stc.setMainChannelName(cursor.getString(cursor.getColumnIndex("mainchannelname")));
            stc.setMinorchannel(cursor.getString(cursor.getColumnIndex("minorchannel")));
            stc.setMinorChannelName(cursor.getString(cursor.getColumnIndex("minorchannelname")));
            stc.setAreatype(cursor.getString(cursor.getColumnIndex("areatype")));
            stc.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));
            //stc.setScondate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(scondate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
            //stc.setPadcondate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(padcondate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setComid(cursor.getString(cursor.getColumnIndex("comid")));
            stc.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
            stc.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
            stc.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
            stc.setVersion(new BigDecimal(cursor.getInt(cursor.getColumnIndex("version"))));
            //stc.setCredate(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(credate,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setCreuser(cursor.getString(cursor.getColumnIndex("creuser")));
            //stc.setUpdatetime(DateUtil.parse(cursor.getString(cursor.getColumnIndex("datetime(updatetime,'localtime')")), "yyyy-MM-dd HH:mm:ss"));
            stc.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
        }

        return stc;
    }

    @Override
    public void updateTermSequence(SQLiteOpenHelper helper, List<TermSequence> list) {
        for (TermSequence term : list) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("update mst_terminalinfo_m set sequence=?");
            buffer.append("where terminalkey=?");
            SQLiteDatabase db = helper.getReadableDatabase();
            db.execSQL(buffer.toString(), new Object[]{term.getSequence(), term.getTerminalkey()});
        }
    }
    @Override
    public void updateXtTempSequence(SQLiteOpenHelper helper, List<TermSequence> list) {
        for (TermSequence term : list) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("update mst_terminalinfo_m_cart set sequence=?");
            buffer.append("where terminalkey=?");
            SQLiteDatabase db = helper.getReadableDatabase();
            db.execSQL(buffer.toString(), new Object[]{term.getSequence(), term.getTerminalkey()});
        }
    }
    @Override
    public void updateZsTempSequence(SQLiteOpenHelper helper, List<TermSequence> list) {
        for (TermSequence term : list) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("update mst_terminalinfo_m_zscart set sequence=?");
            buffer.append("where terminalkey=?");
            SQLiteDatabase db = helper.getReadableDatabase();
            db.execSQL(buffer.toString(), new Object[]{term.getSequence(), term.getTerminalkey()});
        }
    }

    @Override
    public TerminalName findByIdName(DatabaseHelper databaseHelper, String termId) {
        TerminalName terminalName = new TerminalName();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select a.terminalname,b.routename ");
        buffer.append("from mst_terminalinfo_m a ");
        buffer.append("join mst_route_m b on a.routekey = b.routekey ");
        buffer.append("where terminalkey=? ");

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{termId});

        while (cursor.moveToNext()) {
            terminalName.setRouteName(cursor.getString(cursor.getColumnIndex("routename")));
            terminalName.setTerminalName(cursor.getString(cursor.getColumnIndex("terminalname")));

        }

        return terminalName;
    }


}
