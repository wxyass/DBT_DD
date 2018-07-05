package dd.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.MstProductM;
import dd.tsingtaopad.initconstvalues.domain.KvStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 青啤产品信息主表Dao层</br>
 */
public interface MstProductMDao extends Dao<MstProductM, String> {

    /**
     * 获取指标状态查询中的可选产品列表
     * @param helper
     * @param //gridkey 定格主键
     * @return
     */
    public List<KvStc> getIndexPro(DatabaseHelper helper);

    /**
     * 获取产品列表
     * @param helper
     * @param //gridkey 定格主键
     * @return
     */
    public List<KvStc> getProductData(DatabaseHelper helper);
}
