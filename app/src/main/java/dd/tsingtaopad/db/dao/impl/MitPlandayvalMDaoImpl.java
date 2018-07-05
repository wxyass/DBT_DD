package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitPlandayvalMDao;
import dd.tsingtaopad.db.table.MitPlandayvalM;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import dd.tsingtaopad.dd.ddweekplan.domain.DayDetailValStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitPlandayvalMDaoImpl extends BaseDaoImpl<MitPlandayvalM, String> implements MitPlandayvalMDao {

    public MitPlandayvalMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitPlandayvalM.class);
    }

    /***
     *  根据2个字段 查询出日计划详情附表的 追溯项记录
     * @param helper
     * @param plandaydetailid  详细日计划主键
     * @param plancomtype  追溯项或者路线类型
     * @return
     */
    public List<DayDetailValStc> queryPlanMitPlandayvalM(SQLiteOpenHelper helper, String plandaydetailid, String plancomtype) {
        List<DayDetailValStc> lst = new ArrayList<DayDetailValStc>();

        StringBuffer buffer = new StringBuffer();
        buffer.append("select mv.*,g.dicname,h.areaname,i.gridname  from MIT_PLANDAYVAL_M mv ");
        buffer.append("    left join CMM_DATADIC_M g on g.diccode = mv.plancomid   ");
        buffer.append("    left join MST_MARKETAREA_M h on h.areaid = mv.planareaid    ");
        buffer.append("    left join MST_GRID_M i on i.gridkey = mv.plangridid   ");
        buffer.append("where plandaydetailid = ? and plancomtype = ? ");

        /*buffer.append("select mv.*,g.routename,h.areaname,i.gridname from MIT_PLANDAYVAL_M mv   ");
        buffer.append("    left join MST_ROUTE_M g on g.routekey = mv.plancomid   ");
        buffer.append("    left join MST_MARKETAREA_M h on h.areaid = mv.planareaid    ");
        buffer.append("    left join MST_GRID_M i on i.gridkey = mv.plangridid   ");
        buffer.append("    where mv.plandaydetailid = ? and mv.plancomtype = ?  ");*/

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{plandaydetailid,plancomtype});
        DayDetailValStc dayDetailValStc;
        while (cursor.moveToNext()) {
            dayDetailValStc = new DayDetailValStc();
            dayDetailValStc.setDetailvalkey(cursor.getString(cursor.getColumnIndex("id")));// 日计划详情表附表主键
            dayDetailValStc.setWeekkey(cursor.getString(cursor.getColumnIndex("planweekid")));// 周计划主键
            // dayDetailValStc.setDaykey(cursor.getString(cursor.getColumnIndex("plancomid")));// 日计划主键
            dayDetailValStc.setDetailkey(cursor.getString(cursor.getColumnIndex("plandaydetailid")));// 日计划详情表主键

            dayDetailValStc.setValcheckkey(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯项key
            dayDetailValStc.setValcheckname(cursor.getString(cursor.getColumnIndex("dicname")));// 追溯项name
            dayDetailValStc.setValareakey(cursor.getString(cursor.getColumnIndex("planareaid")));// 追溯区域
            // dayDetailValStc.setValareaname(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯区域
            dayDetailValStc.setValgridkey(cursor.getString(cursor.getColumnIndex("plangridid")));// 追溯定格
            // dayDetailValStc.setValgridname(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯定格
            // dayDetailValStc.setValroutekey(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯路线key
            // dayDetailValStc.setValroutename(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯路线name
            lst.add(dayDetailValStc);
        }
        return lst;
    }

    /***
     *  根据2个字段 查询出日计划详情附表的 路线记录
     * @param helper
     * @param plandaydetailid  详细日计划主键
     * @param plancomtype  追溯项或者路线类型
     * @return
     */
    public List<DayDetailValStc> queryPlanMitPlandayvalMRoutes(SQLiteOpenHelper helper, String plandaydetailid, String plancomtype) {
        List<DayDetailValStc> lst = new ArrayList<DayDetailValStc>();

        StringBuffer buffer = new StringBuffer();
        buffer.append("select mv.*,g.routename,h.areaname,i.gridname from MIT_PLANDAYVAL_M mv   ");
        buffer.append("    left join MST_ROUTE_M g on g.routekey = mv.plancomid   ");
        buffer.append("    left join MST_MARKETAREA_M h on h.areaid = mv.planareaid    ");
        buffer.append("    left join MST_GRID_M i on i.gridkey = mv.plangridid   ");
        buffer.append("    where mv.plandaydetailid = ? and mv.plancomtype = ?  ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{plandaydetailid,plancomtype});
        DayDetailValStc dayDetailValStc;
        while (cursor.moveToNext()) {
            dayDetailValStc = new DayDetailValStc();
            dayDetailValStc.setDetailvalkey(cursor.getString(cursor.getColumnIndex("id")));// 日计划详情表附表主键
            dayDetailValStc.setWeekkey(cursor.getString(cursor.getColumnIndex("planweekid")));// 周计划主键
            // dayDetailValStc.setDaykey(cursor.getString(cursor.getColumnIndex("plancomid")));// 日计划主键
            dayDetailValStc.setDetailkey(cursor.getString(cursor.getColumnIndex("plandaydetailid")));// 日计划详情表主键

            // dayDetailValStc.setValcheckkey(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯项key
            // dayDetailValStc.setValcheckname(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯项name
            dayDetailValStc.setValareakey(cursor.getString(cursor.getColumnIndex("planareaid")));// 追溯区域key
            dayDetailValStc.setValareaname(cursor.getString(cursor.getColumnIndex("areaname")));// 追溯区域name
            dayDetailValStc.setValgridkey(cursor.getString(cursor.getColumnIndex("plangridid")));// 追溯定格key
            dayDetailValStc.setValgridname(cursor.getString(cursor.getColumnIndex("gridname")));// 追溯定格name
            dayDetailValStc.setValroutekey(cursor.getString(cursor.getColumnIndex("plancomid")));// 追溯路线key
            dayDetailValStc.setValroutename(cursor.getString(cursor.getColumnIndex("routename")));// 追溯路线name
            lst.add(dayDetailValStc);
        }
        return lst;
    }

    /**
     * 删除表记录
     * @param helper
     * @param planweekid  周计划主键
     */
    @Override
    public void deleteByPlanweekid(SQLiteOpenHelper helper, String planweekid) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("delete from MIT_PLANDAYVAL_M  where plandaydetailid = ?  ");
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(buffer.toString(), new String[] { planweekid });
    }

    @Override
    public List<DayDetailStc> queryPlanMitPlandaydetailM(DatabaseHelper helper, String dayplanKey) {
        List<DayDetailStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select mv.*,g.areaname,h.gridname from MIT_PLANDAYDETAIL_M mv  ");
        buffer.append("    left join MST_MARKETAREA_M g on g.areaid = mv.planareaid   ");
        buffer.append("    left join MST_GRID_M h on h.[gridkey] = mv.plangridid   ");
        buffer.append("    where plandayid = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{dayplanKey});
        DayDetailStc dayDetailStc;
        while (cursor.moveToNext()) {
            dayDetailStc = new DayDetailStc();
            dayDetailStc.setDetailkey(cursor.getString(cursor.getColumnIndex("id")));
            dayDetailStc.setWeekkey(cursor.getString(cursor.getColumnIndex("planweekid")));
            dayDetailStc.setDaykey(cursor.getString(cursor.getColumnIndex("plandayid")));

            dayDetailStc.setValareakey(cursor.getString(cursor.getColumnIndex("planareaid")));
            dayDetailStc.setValareaname(cursor.getString(cursor.getColumnIndex("areaname")));

            dayDetailStc.setValgridkey(cursor.getString(cursor.getColumnIndex("plangridid")));
            dayDetailStc.setValgridname(cursor.getString(cursor.getColumnIndex("gridname")));
            detailStcs.add(dayDetailStc);
        }
        return detailStcs;
    }

}
