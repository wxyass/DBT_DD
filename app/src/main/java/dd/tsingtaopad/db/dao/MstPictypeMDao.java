package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.table.MitValpicMTemp;
import dd.tsingtaopad.db.table.MstPictypeM;
import dd.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 图片类型表Dao层</br>
 */
public interface MstPictypeMDao extends Dao<MstPictypeM, String> {
    
    /**
     * 查询图片类型表中所有记录,用于初始化要拍几张图片  (废弃)
     * 
     * @param helper
     * @param //gridId
     * @return
     */
    public List<CameraInfoStc> queryAllPictype(SQLiteOpenHelper helper);
    /**
     * 查询图片类型表中所有记录,用于追溯初始化要拍几张图片
     *
     * @param helper
     * @param //gridId
     * @return
     */
    public List<MitValpicMTemp> queryZsAllPictype(SQLiteOpenHelper helper);

    /**
     * 查询图片类型表中所有记录,用于初始化要拍几张图片
     *
     * @param helper
     * @param //gridId
     * @return
     */
    public List<CameraInfoStc> queryPictypeM(SQLiteOpenHelper helper);
    
}
