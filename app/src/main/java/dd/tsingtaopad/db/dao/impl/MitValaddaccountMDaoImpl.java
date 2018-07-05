package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitValaddaccountMDao;
import dd.tsingtaopad.db.table.MitValaddaccountM;
import dd.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzLedger;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MitValaddaccountMDaoImpl extends BaseDaoImpl<MitValaddaccountM, String> implements MitValaddaccountMDao {

    public MitValaddaccountMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MitValaddaccountM.class);
    }


    @Override
    public List<ZsTzLedger> queryValTzByTemp(DatabaseHelper helper, String terminalkey) {
        List<ZsTzLedger> lst = new ArrayList<ZsTzLedger>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("select mv.id valaddaccountid, g.id valaddaccountproid, mv.valsupplyid,  ");
        buffer.append("   mv.valagencyid, mv.valagencyname, mv.valterid termid, mv.valtername,  ");
        buffer.append("   mv.valproid, mv.valproname,  mv.valprostatus,  g.valprotime,   ");
        buffer.append("   g.valpronumfalg, g.valpronum,  g.valprotruenum,  g.valproremark    ");
        buffer.append("   from MIT_VALADDACCOUNT_M_TEMP mv   ");
        buffer.append("   left join MIT_VALADDACCOUNTPRO_M_TEMP g on g.valaddaccountid = mv.id   ");
        buffer.append("     where mv.valterid= ?  order by valproid   ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
        ZsTzLedger item;
        while (cursor.moveToNext()) {
            item = new ZsTzLedger();
            item.setValaddaccountid(cursor.getString(cursor.getColumnIndex("valaddaccountid")));// 主表主键
            item.setValaddaccountproid(cursor.getString(cursor.getColumnIndex("valaddaccountproid")));// 附表主键
            item.setValsupplyid(cursor.getString(cursor.getColumnIndex("valsupplyid")));// 终端追溯终端表ID

            item.setValagencyid(cursor.getString(cursor.getColumnIndex("valagencyid")));//经销商ID
            item.setValagencyname(cursor.getString(cursor.getColumnIndex("valagencyname")));// 经销商名称
            item.setValterid(cursor.getString(cursor.getColumnIndex("termid")));// 终端ID
            item.setValtername(cursor.getString(cursor.getColumnIndex("valtername")));// 终端名称
            item.setValproid(cursor.getString(cursor.getColumnIndex("valproid")));//产品ID
            item.setValproname(cursor.getString(cursor.getColumnIndex("valproname")));//  产品名称
            item.setValprostatus(cursor.getString(cursor.getColumnIndex("valprostatus")));//  稽查状态

            item.setValprotime(cursor.getString(cursor.getColumnIndex("valprotime")));// 台账日期
            item.setValpronumfalg(cursor.getString(cursor.getColumnIndex("valpronumfalg")));// 进货量正确与否
            item.setValpronum(cursor.getString(cursor.getColumnIndex("valpronum")));// 进货量原值
            item.setValprotruenum(cursor.getString(cursor.getColumnIndex("valprotruenum")));// 进货量正确值
            item.setValproremark(cursor.getString(cursor.getColumnIndex("valproremark")));// 备注
            lst.add(item);
        }
        return lst;
    }
}
