package et.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;
import java.util.Map;

import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.main.visit.shopvisit.line.domain.MstRouteMStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 线路档案主表DAO层</br>
 */
public interface MstRouteMDao extends Dao<MstRouteM, String> {
    
    /**
     * 获取线路列表信息，包含计划拜访时间
     * 
     * 用于：巡店拜访  -- 线路选择
     */
    public List<MstRouteMStc> queryLine(SQLiteOpenHelper helper);
    
    /**
     * 获取线路上次拜访的日期
     * 
     * @param helper
     * @param lineIdLst
     * @return              Key:线路ID、 Value:该线路上次拜访日期
     */
    public Map<String, String> queryPrevVisitDate(SQLiteOpenHelper helper, List<String> lineIdLst);
    
    /**
     * 获取某线路下的终端个数
     * 
     * @param helper
     * @param lineId    线路ID，为null时返回所有线路下的终端数
     * @return
     */
    public int queryForTermNum(SQLiteOpenHelper helper, String lineId);


    /**
     * 获取次渠道集合
     * @param helper
     * @return
     */
    public List<KvStc> querySecondSell(SQLiteOpenHelper helper);
    /**
     * 获取次渠道集合
     * @param helper
     * @return
     */
    public List<KvStc> querySecondSingSell(SQLiteOpenHelper helper,String con);

}
