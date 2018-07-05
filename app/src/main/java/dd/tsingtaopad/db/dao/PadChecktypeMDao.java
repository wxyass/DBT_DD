package dd.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.PadChecktypeM;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: PAD端采集用指标主表DAO</br>
 */
public interface PadChecktypeMDao extends Dao<PadChecktypeM, String> {

    /**
     * 获取Pad端采集指标的结构数据， value:指标及指标值的层级表现形式
     * 
     * @param helper 
     * @param channelId  当前终端次渠道ID
     * @return
     */
    public List<KvStc> queryCheckType(DatabaseHelper helper, String channelId);
    
    /**
     * 获取并组建所有指标、指标值的树级关系
     * 
     * @param helper
     * @param productFlag   关于产品标志，1：只列出与产品相关的指标
     * @return
     */
    public List<KvStc> queryCheckTypeStatus(DatabaseHelper helper, String productFlag);
}
