/**
 * 
 */
package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MitValterM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: DAO接口</br>
 */
public interface MitValterMDao extends Dao<MitValterM, String> {

    /**
     *  (终端追溯,根据终端key 获取未上传记录
     * 用于：终端追溯    (2018年5月3日新加 )
     *
     * @param helper
     * @param terminalkey 线路主键
     * @return
     */
    public List<MitValterM> queryZsMitValterMData(SQLiteOpenHelper helper, String terminalkey);
}
