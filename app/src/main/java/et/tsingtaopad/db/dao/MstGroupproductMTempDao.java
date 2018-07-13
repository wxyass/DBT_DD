/**
 * 
 */
package et.tsingtaopad.db.dao;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MitValgroupproMTemp;
import et.tsingtaopad.db.table.MstGroupproductMTemp;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端进货详情DAO接口</br>
 */
public interface MstGroupproductMTempDao extends Dao<MstGroupproductMTemp, String> {
	/**
	 * @param helper
	 * @param terminalcode
	 * @return
	 */
	public List<MstGroupproductMTemp> queryMstGroupproductMByTerminalcode(DatabaseHelper helper, String terminalcode);

	/**
	 * @param helper
	 * @param valterid
	 * @return
	 */
	public List<MitValgroupproMTemp> queryZsMitValgroupproMTempByValterid(DatabaseHelper helper, String valterid);
}
