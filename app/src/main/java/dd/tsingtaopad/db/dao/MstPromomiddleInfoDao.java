package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MstPromomiddleInfo;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端参加活动信息表Dao层</br>
 */
public interface MstPromomiddleInfoDao extends Dao<MstPromomiddleInfo, String> {
	/**
	 * 获取活动中间表终端LIST
	 * 
	 * @param helper
	 * @param promKey
	 *            活动主键
	 * @param lineKey
	 *            线路主键字符串
	 * @param type
	 *            类型字符串
	 * @param date
	 *            时间
	 * @return
	 */

	public List<MstPromomiddleInfo> queryTermMiddleLst(SQLiteOpenHelper helper, String promKey, String lineKey, String type,
                                                       String date);
//	/**
//	 * 获取活动终端LIST
//	 * 
//	 * @param helper
//	 * @param promKey
//	 *            活动主键
//	 * @param lineKey
//	 *            线路主键字符串
//	 * @param type
//	 *            类型字符串
//	 * @param date
//	 *            时间
//	 * @return
//	 */
//	
//	public List<MstPromomiddleInfo> queryTermLst(SQLiteOpenHelper helper, String promKey, String lineKey,String type,
//			String date);

//	/**
//	 * 获取活动起止时间
//	 * @param promKey 活动主键
//	 * 
//	 */
//	public MstPromotionsM queryTime( SQLiteOpenHelper helper,String promKey);

}
