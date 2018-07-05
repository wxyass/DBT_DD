/**
 * 
 */
package dd.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.table.MitRepairterM;
import dd.tsingtaopad.dd.dddealplan.domain.DealStc;
import dd.tsingtaopad.dd.dddealplan.make.domain.DealPlanMakeStc;
import dd.tsingtaopad.dd.dddealplan.remake.domain.ReCheckTimeStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: DAO接口</br>
 */
public interface MitRepairterMDao extends Dao<MitRepairterM, String> {

    /**
     *
     * @param helper
     * @param repairid
     * @return
     */
    public List<DealPlanMakeStc> querySelectTerminal(DatabaseHelper helper, String repairid);

    public List<DealStc> queryDealPan(DatabaseHelper helper);

    public List<ReCheckTimeStc> queryDealPlanStatus(DatabaseHelper helper, String repairid);

    public List<DealStc> queryDealPlanTermName(DatabaseHelper helper,String repairid);


}
