/**
 * 
 */
package et.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MitPlandayvalM;
import et.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import et.tsingtaopad.dd.ddweekplan.domain.DayDetailValStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: DAO接口</br>
 */
public interface MitPlandayvalMDao extends Dao<MitPlandayvalM, String> {

    /***
     *  根据2个字段 查询出日计划详情附表的记录
     * @param helper
     * @param plandaydetailid  详细日计划主键
     * @param plancomtype  追溯项或者路线类型
     * @return
     */
    public List<DayDetailValStc> queryPlanMitPlandayvalM(SQLiteOpenHelper helper, String plandaydetailid, String plancomtype);

    public void deleteByPlanweekid(SQLiteOpenHelper helper, String planweekid);

    public List<DayDetailStc> queryPlanMitPlandaydetailM(DatabaseHelper helper, String dayplanKey);

    public List<DayDetailValStc> queryPlanMitPlandayvalMRoutes(SQLiteOpenHelper helper, String plandaydetailid, String plancomtype);
}
