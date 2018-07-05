package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MstAgencyinfoM;
import dd.tsingtaopad.initconstvalues.domain.KvStc;
import dd.tsingtaopad.main.operation.agencystorage.domain.AgencystorageStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 分经销商信息主表Dao层</br>
 */
public interface MstAgencyinfoMDao extends Dao<MstAgencyinfoM, String> {
    
    /**
     * 获取经销商库存的查询显示数据
     * @param helper
     * @param agencyKey 经销商ID
     * @return
     */
    public List<AgencystorageStc> agencyStorageQuery(SQLiteOpenHelper helper, String datecureents, String datecureentx, String agencyKey, String datecureent, String dateselects, String dateselectx, String agencyKeys, String dateselect);
    
    
    /**
     * 我方经销商销售产品查询
     * @author 姜世杰 
     * @since    2013-12-17
     * @return
     */
    public List<KvStc>  agencySellProQuery(SQLiteOpenHelper helper);

    /**
     * 根据终端key 查询 我方经销商
     * @author wxyass
     * @since    2013-12-17
     * @return
     */
    public List<KvStc>  agencyTermQuery(SQLiteOpenHelper helper,String terminalkey);

    /**
     * 获取可拜访经销商及产品列表
     * 
     * @param helper
     * @return
     */
    public List<KvStc> queryVisitAgencyPro(SQLiteOpenHelper helper);
    
    /**
     * 获取当前定格的请经销商
     * 
     * @param helper
     * @param gridId
     * @return
     */
    public MstAgencyinfoM queryMainAgency(SQLiteOpenHelper helper, String gridId);
    
}
