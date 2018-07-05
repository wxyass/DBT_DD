/**
 * 
 */
package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MitVisitM;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情DAO接口</br>
 */
public interface MitVisitMDao extends Dao<MitVisitM, String> {

    /**
     *  (终端追溯,根据终端key 获取未上传记录
     * 用于：终端追溯    (2018年5月3日新加 )
     *
     * @param helper
     * @param terminalkey 线路主键
     * @return
     */
    public List<MitVisitM> queryXtMitVisitM(SQLiteOpenHelper helper, String terminalkey);
}
