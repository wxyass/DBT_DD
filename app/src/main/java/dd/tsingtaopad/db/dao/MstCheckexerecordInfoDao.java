package dd.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.MstCheckexerecordInfo;
import dd.tsingtaopad.main.visit.shopvisit.termindex.domain.TermIndexStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 拜访指标执行记录表Dao</br>
 */
public interface MstCheckexerecordInfoDao extends Dao<MstCheckexerecordInfo, String> {

    /**
     * 获取终端某次拜访下产品对应的各指标的采集项目状态
     * 
     * 用于：巡店拜访 -- 终端指标状态
     * @param helper
     * @param visitId       拜访主键
     * @param uploadFlag    结束拜访标识，1：结束拜访，0：未结束拜访
     * @param termId        终端主键
     * @param channelId     终端次渠道ID
     * @return
     */
    public List<TermIndexStc> queryTermIndex(DatabaseHelper helper, String visitId, String uploadFlag, String termId, String channelId);
    
    // 从checkexerecord表中查出 没有供货关系,却有铺货指标的记录
    public List<MstCheckexerecordInfo> queryFromcheckexerecord(DatabaseHelper helper);

	/**
	 * 从checkexerecord表中根据主键 删除记录 
	 * @param helper
	 * @param recordkey
	 */
	public void deletecheckexerecord(DatabaseHelper helper, String recordkey);
}
