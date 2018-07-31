package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitRepairterMDao;
import et.tsingtaopad.db.table.MitRepairterM;
import et.tsingtaopad.dd.dddealplan.domain.DealStc;
import et.tsingtaopad.dd.dddealplan.make.domain.DealPlanMakeStc;
import et.tsingtaopad.dd.dddealplan.remake.domain.ReCheckTimeStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitRepairterMDaoImpl extends BaseDaoImpl<MitRepairterM, String> implements MitRepairterMDao {

    public MitRepairterMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitRepairterM.class);
    }

    @Override
    public List<DealPlanMakeStc> querySelectTerminal(DatabaseHelper helper, String repairid) {
        List<DealPlanMakeStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select mv.terminalkey, mv.terminalname,h.gridkey,h.gridname,r.routename,r.routekey,h.username,h.userid from MIT_REPAIRTER_M mv  ");
        buffer.append("    left join MST_TERMINALINFO_M g on g.terminalkey = mv.terminalkey   ");
        buffer.append("    left join MST_GRID_M h on h.gridkey = mv.gridkey   ");
        buffer.append("    left join MST_ROUTE_M r on r.routekey = mv.routekey   ");
        buffer.append("    where repairid = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{repairid});
        DealPlanMakeStc kvStc ;
        while (cursor.moveToNext()) {
            kvStc = new DealPlanMakeStc();
            kvStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            kvStc.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            kvStc.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
            kvStc.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
            kvStc.setRoutekey(cursor.getString(cursor.getColumnIndex("routekey")));
            kvStc.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
            kvStc.setUserkey(cursor.getString(cursor.getColumnIndex("userid")));
            kvStc.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            detailStcs.add(kvStc);
        }
        return detailStcs;
    }

    /**
     * 获取最后一条记录
     * @param helper
     * @return
     */
    @Override
    public List<DealStc> queryDealPan(DatabaseHelper helper) {
        List<DealStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();

        buffer.append("select mv.id repairid,g.id repaircheckid,mv.credate, mv.content,mv.status repairstatus, mv.repairremark, mv.checkcontent,  ");
        buffer.append("    f.gridkey, f.gridname, f.userid, f.username, g.status, g.repairtime   ");
        buffer.append("    from MIT_REPAIR_M mv   ");
        buffer.append("    left join MIT_REPAIRCHECK_M g     on g.repairid = mv.id ");
        buffer.append("    left join MST_GRID_M f     on f.gridkey = mv.gridkey ");
        buffer.append("    group by mv.id ");
        buffer.append("    order by g.credate desc ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        DealStc kvStc ;
        while (cursor.moveToNext()) {
            kvStc = new DealStc();
            kvStc.setRepairid(cursor.getString(cursor.getColumnIndex("repairid")));
            kvStc.setRepaircheckid(cursor.getString(cursor.getColumnIndex("repaircheckid")));
            kvStc.setContent(cursor.getString(cursor.getColumnIndex("content")));
            kvStc.setRepairstatus(cursor.getString(cursor.getColumnIndex("repairstatus")));
            kvStc.setRepairremark(cursor.getString(cursor.getColumnIndex("repairremark")));
            kvStc.setCheckcontent(cursor.getString(cursor.getColumnIndex("checkcontent")));
            kvStc.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
            kvStc.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
            kvStc.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
            kvStc.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            kvStc.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            kvStc.setRepairtime(cursor.getString(cursor.getColumnIndex("repairtime")));
            kvStc.setCredate(cursor.getString(cursor.getColumnIndex("credate")));
            detailStcs.add(kvStc);
        }
        return detailStcs;
    }
    /**
     * 根据终端 查找整改计划
     * @param helper
     * @return
     */
    @Override
    public List<DealStc> queryTermDealPlan(DatabaseHelper helper,String terminalkey) {
        List<DealStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();

        buffer.append("select mv.id repairid,g.id repaircheckid,mrm.id repairterid,mv.credate, mv.content,mv.status repairstatus,   ");
        buffer.append("    mv.repairremark, mv.checkcontent,  g.status, g.repairtime  ,mrm.terminalname     ");
        buffer.append("    from MIT_REPAIR_M mv     ");
        buffer.append("    left join MIT_REPAIRCHECK_M g     on g.repairid = mv.id  and g.status = '0'  ");
        buffer.append("    inner join MIT_REPAIRTER_M mrm     on mrm.repairid = mv.id and mrm.terminalkey = ?   ");
        buffer.append("    group by mv.id ");
        buffer.append("    order by g.credate desc ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
        DealStc kvStc ;
        while (cursor.moveToNext()) {
            kvStc = new DealStc();
            kvStc.setRepairid(cursor.getString(cursor.getColumnIndex("repairid")));
            kvStc.setRepaircheckid(cursor.getString(cursor.getColumnIndex("repaircheckid")));
            kvStc.setRepairterid(cursor.getString(cursor.getColumnIndex("repairterid")));
            kvStc.setCredate(cursor.getString(cursor.getColumnIndex("credate")));
            kvStc.setContent(cursor.getString(cursor.getColumnIndex("content")));
            kvStc.setRepairstatus(cursor.getString(cursor.getColumnIndex("repairstatus")));
            kvStc.setRepairremark(cursor.getString(cursor.getColumnIndex("repairremark")));
            kvStc.setCheckcontent(cursor.getString(cursor.getColumnIndex("checkcontent")));
            kvStc.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            kvStc.setRepairtime(cursor.getString(cursor.getColumnIndex("repairtime")));

            detailStcs.add(kvStc);
        }
        return detailStcs;
    }

    @Override
    public List<DealStc> queryDealPlanTermName(DatabaseHelper helper,String repairid) {
        List<DealStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();

        buffer.append("select mv.id, h.terminalkey,h.terminalname,r.routename   ");
        buffer.append("        from MIT_REPAIR_M mv   ");
        buffer.append("   left join MIT_REPAIRTER_M h on h.repairid = mv.id    ");
        buffer.append("    left join MST_ROUTE_M r on r.routekey = h.routekey   ");
        // buffer.append("   left join MST_TERMINALINFO_M i on i.terminalkey = h.terminalkey    ");
        buffer.append("    where h.repairid = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{repairid});
        DealStc kvStc ;
        while (cursor.moveToNext()) {
            kvStc = new DealStc();
            kvStc.setRepairid(cursor.getString(cursor.getColumnIndex("id")));
            kvStc.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
            kvStc.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
            kvStc.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
            detailStcs.add(kvStc);
        }
        return detailStcs;
    }


    @Override
    public List<ReCheckTimeStc> queryDealPlanStatus(DatabaseHelper helper, String repairid) {
        List<ReCheckTimeStc> detailStcs = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select * from MIT_REPAIRCHECK_M   ");
        buffer.append("    where repairid = ? order by credate");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{repairid});
        ReCheckTimeStc kvStc ;
        while (cursor.moveToNext()) {
            kvStc = new ReCheckTimeStc();
            kvStc.setId(cursor.getString(cursor.getColumnIndex("id")));
            kvStc.setRepairid(cursor.getString(cursor.getColumnIndex("repairid")));
            kvStc.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            kvStc.setRepairtime(cursor.getString(cursor.getColumnIndex("repairtime")));
            detailStcs.add(kvStc);
        }
        return detailStcs;
    }

}
