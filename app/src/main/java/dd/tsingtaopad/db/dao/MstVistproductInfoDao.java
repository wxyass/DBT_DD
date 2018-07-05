package dd.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.MstVistproductInfo;
import dd.tsingtaopad.dd.ddxt.chatvie.domain.XtChatVieStc;
import dd.tsingtaopad.dd.ddxt.invoicing.domain.XtInvoicingStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.chatvie.domain.ChatVieStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexCalculateStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.CheckIndexQuicklyStc;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.invoicing.domain.InvoicingStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public interface MstVistproductInfoDao extends Dao<MstVistproductInfo, String> {
    
    /**
     * 获取某次拜访的我品的进销存数据情况
     * 
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<InvoicingStc> queryMinePro(DatabaseHelper helper, String visitId, String termKey);

    /**
     * 从临时表中 获取某次拜访的我品的进销存数据情况
     *
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<InvoicingStc> queryMineProByTemp(DatabaseHelper helper, String visitId, String termKey);

    /**
     * 从临时表中 获取协同拜访的我品的进销存数据情况
     *
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<XtInvoicingStc> queryXtMineProByTemp(DatabaseHelper helper, String visitId, String termKey);
    /**
     * 从业代数据库中读取 获取 我品的进销存数据情况 wx_yss
     *
     * @param helper
     * @param previsitId
     *            拜访主键
     * @return
     */
    public List<XtInvoicingStc> queryInvocingProByTemp(DatabaseHelper helper, String previsitId, String termKey);

    /**
     * 获取某次拜访的竞品的进销存数据情况
     * 
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<ChatVieStc> queryViePro(DatabaseHelper helper, String visitId);

    /**
     * 获取某次拜访的竞品的进销存数据情况
     *
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<ChatVieStc> queryVieProByTemp(DatabaseHelper helper, String visitId);
    /**
     * 获取协同拜访的竞品的进销存数据情况
     *
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<XtChatVieStc> queryXtVieProByTemp(DatabaseHelper helper, String visitId);

    /**
     * 获取追溯 聊竞品 数据情况
     *
     * @param helper
     * @param visitId   拜访主键
     * @return
     */
    public List<XtChatVieStc> queryZsVieProByTemp(DatabaseHelper helper, String visitId, String termid);

    /**
     * 获取巡店拜访-查指标的分项采集部分的产品指标数据
     * 
     * @param visitId       本次拜访ID
     * @param termId        本次拜访终端ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param seeFlag       查看标识
     * @return
     */
    public List<CheckIndexCalculateStc> queryCalculateIndex(DatabaseHelper helper,
                                                            String visitId, String termId, String channelId, String seeFlag);
    
    /**
     * 获取巡店拜访-查指标的分项采集部分的产品指标对应的采集项目数据
     * 
     * @param visitId       本次拜访ID
     * @param visitId   上次拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @return
     */
    public List<CheckIndexQuicklyStc> queryCalculateItem(
            DatabaseHelper helper, String visitId, String channelId);

    /**
     * 获取终端追溯-查指标的分项采集部分的产品指标数据
     *
     * @param previsitId       本次拜访ID
     * @param termId        本次拜访终端ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param seeFlag       查看标识
     * @return
     */
    public List<CheckIndexCalculateStc> queryZsCalculateIndexTemp(DatabaseHelper helper,
                                                            String previsitId, String termId, String channelId, String seeFlag);
    /**
     * 获取巡店拜访-查指标的分项采集部分的产品指标数据
     *
     * @param visitId       本次拜访ID
     * @param termId        本次拜访终端ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param seeFlag       查看标识
     * @return
     */
    public List<CheckIndexCalculateStc> queryCalculateIndexTemp(DatabaseHelper helper,
                                                            String visitId, String termId, String channelId, String seeFlag);

    /**
     * 获取巡店拜访-查指标的分项采集部分的产品指标对应的采集项目数据
     *
     * @param visitId       本次拜访ID
     * @param visitId   上次拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @return
     */
    public List<CheckIndexQuicklyStc> queryCalculateItemTemp(
            DatabaseHelper helper, String visitId, String channelId);

    /**
     * 获取终端追溯-查指标的分项采集部分的产品指标对应的采集项数据
     *
     * @param previsitId   上次拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @return
     */
    public List<CheckIndexQuicklyStc> queryZsCalculateItemTemp(
            DatabaseHelper helper, String previsitId, String channelId);

    /**
     * 获取巡店拜访-查指标的分项采集部分的与产品无关的指标数据
     * 
     * @param visitId       拜访ID
     * @param channelId     本次拜访终端的次渠道ID
     * @param seeFlag       查看操作标识
     * @return
     */
    public List<CheckIndexCalculateStc> queryNoProIndex(
            DatabaseHelper helper, String visitId, String channelId, String seeFlag);

}
