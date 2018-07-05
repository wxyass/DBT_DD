/**
 * 
 */
package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import cn.com.benyoyo.manage.bs.IntStc.DataresultTerPurchaseDetailsStc;
import dd.tsingtaopad.db.table.MstVisitM;
import dd.tsingtaopad.main.operation.indexstatus.domain.IndexStatusStc;
import dd.tsingtaopad.main.visit.tomorrowworkrecord.domain.DayWorkDetailStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情DAO接口</br>
 */
public interface MstVisitMDao  extends Dao<MstVisitM, String> {
	/**
	 * 查询终端详细信息
	 * listview多表查询
	 * @param helper
	 * @param key  参数where条件数组 bdt.ROUTEKEY=? and ( pdv.VISITDATE>=? and pdv.VISITDATE<=? )
	 * @return
	 */
	public List<DataresultTerPurchaseDetailsStc> searchTerminalDetails(SQLiteOpenHelper helper, String[] key);
	/**
	 * 查询每日工作明细
	 * listview多表查询
	 * @param helper
	 * @param //key 参数where条件数组 a.VISITDATE = ? and a.user_id = ?
	 * @return
	 */
	public List<DayWorkDetailStc> searchTomorrowWorkRecord(SQLiteOpenHelper helper, String startdate, String enddate, String useId);
	
	/**
	 * 获取当天计划拜访终端个数
	 * 
	 * @param helper
	 * @param planDate     计划日期：YYYYMMDD
	 * @return
	 */
    public int queryPlanTermNum(SQLiteOpenHelper helper, String planDate);
    
    /**
     * 获取当天结束拜访且有效的拜访终端个数
     * 
     * @param helper
     * @param visitDate     拜访日期：YYYYMMDD
     * @return
     */
    public int queryVisitTermNum(SQLiteOpenHelper helper, String visitDate);
    
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
    public List<MstVisitM> queryForNum(SQLiteOpenHelper helper, String routeKey, String visitDate, boolean ascFlag, int limitNum);
    
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
													String linePrevDate, String[] gridCurrDate, String[] gridPrevDate, String checkId, String valueId, String[] productIds);
}
