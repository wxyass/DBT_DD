package et.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MstAgencyvisitM;
import et.tsingtaopad.dd.ddagencycheck.domain.ZsInOutSaveStc;
import et.tsingtaopad.main.visit.agencyvisit.domain.AgencySelectStc;
import et.tsingtaopad.main.visit.agencyvisit.domain.TransferStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 分经销商拜访主表DAO层</br>
 */
public interface MstAgencyvisitMDao extends Dao<MstAgencyvisitM, String> {

    /**
     * 获取当前定格下的拜访经销商
     * @param helper
     * @param gridId 定格Id
     * @return
     */
    public List<AgencySelectStc> agencySelectQuery(SQLiteOpenHelper helper, String gridId);
    /**
     * 查询二级区域下 所有经销商
     * @param helper
     * @param areaid 二级区域id
     * @return
     */
    public List<AgencySelectStc> agencyZsSelectQuery(SQLiteOpenHelper helper, String areaid);

    /**
     * 依据拜访请键获取经销商调货记录
     * 
     * @param helper
     * @param visitId   经销商拜访主键
     * @return
     */
    public List<TransferStc> queryTransByVisitId(SQLiteOpenHelper helper, String visitId);
    
    /***
     * 经销商某天拜访是否存在
     * @param agencyKey
     * @param visitDate
     * @return
     */
    public boolean isExistAgencyVisit(SQLiteOpenHelper helper, String agencyKey, String visitDate);

    /**
     * 根据上次visitkey , 查询所有
     * @param helper
     * @param agevisitkey
     * @return
     */
    List<ZsInOutSaveStc> queryZsInOutSaveStc(DatabaseHelper helper, String agevisitkey,String agencykey);
}
