/**
 * 
 */
package et.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MitValaddaccountM;
import et.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain.ZsTzLedger;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: DAO接口</br>
 */
public interface MitValaddaccountMDao extends Dao<MitValaddaccountM, String> {

    /**
     *
     * @param helper
     * @param terminalkey
     * @return
     */
    public List<ZsTzLedger> queryValTzByTemp(DatabaseHelper helper, String terminalkey);
}
