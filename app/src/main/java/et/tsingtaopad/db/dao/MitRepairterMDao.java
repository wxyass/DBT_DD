/**
 * 
 */
package et.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MitRepairterM;
import et.tsingtaopad.dd.dddealplan.domain.DealStc;
import et.tsingtaopad.dd.dddealplan.make.domain.DealPlanMakeStc;
import et.tsingtaopad.dd.dddealplan.remake.domain.ReCheckTimeStc;

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

    public List<DealStc> queryTermDealPlan(DatabaseHelper helper,String terminalkey);// queryTermDealPlan


}
