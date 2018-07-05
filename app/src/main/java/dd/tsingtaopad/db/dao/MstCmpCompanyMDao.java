package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MstCmpcompanyM;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public interface MstCmpCompanyMDao  extends Dao<MstCmpcompanyM, String>{
    
    /**
     * 查询竞争对手可销售产品信息
     * @param helper
     * @return
     */
    public List<KvStc>  agencySellProQuery(SQLiteOpenHelper helper);
    
}
