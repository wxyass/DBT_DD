package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;
import java.util.Map;

import dd.tsingtaopad.db.table.MstPromotionsM;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexPromotionStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 活动主表DAO</br>
 */
public interface MstPromotionsmDao extends Dao<MstPromotionsM, String> {

    /**
     * 查询终端参与的活动及活动达成状态情况
     * 
     * 用于：巡店拜访-查指标-促销活动
     * 
     * @param helper
     * @param visitId       拜访主键
     * @param channelId     终端销售渠道ID
     * @param termLevel     终端等级ID
     * @return
     */
    public List<CheckIndexPromotionStc> queryPromotionByterm(
            SQLiteOpenHelper helper, String visitId, String channelId, String termLevel);
    /**
     * 查询协同拜访终端参与的活动及活动达成状态情况
     *
     * 用于：巡店拜访-查指标-促销活动
     *
     * @param helper
     * @param visitId       拜访主键
     * @param channelId     终端销售渠道ID
     * @param termLevel     终端等级ID
     * @return
     */
    public List<CheckIndexPromotionStc> queryXtPromotionByterm(
            SQLiteOpenHelper helper, String visitId, String channelId, String termLevel);

    /**
     * 查询协同拜访终端参与的活动及活动达成状态情况
     *
     * 用于：终端追溯-查指标-促销活动
     *
     * @param helper
     * @param previsitId       拜访主键
     * @param channelId     终端销售渠道ID
     * @param termLevel     终端等级ID
     * @return
     */
    public List<CheckIndexPromotionStc> queryZsPromotionByterm(
            SQLiteOpenHelper helper, String previsitId, String channelId, String termLevel);

    /**
     * 促销活动查询
     * 
     * @param promId        活动ID
     * @param promSd        活动开始时间
     * @param searchD       查询日期
     * @param lineIds       线路ID
     * @param termLevels    终端等级
     * @return  YES:活动达成终端， NO:活动未终端
     */
    public Map<String, List<String>> promotionSearch(SQLiteOpenHelper helper,
                                                     String promId, String promSd, String searchD, String[] lineIds, String[] termLevels);
}
