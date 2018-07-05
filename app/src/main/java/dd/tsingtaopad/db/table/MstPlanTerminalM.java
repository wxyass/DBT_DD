package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * MstPlanTerminalM entity. @author 
 */
@DatabaseTable(tableName = "MST_PLANTERMINAL_M")
public class MstPlanTerminalM implements java.io.Serializable
{
    @DatabaseField(id = true, canBeNull = false)
    private String planterminalkey;//主键    
    @DatabaseField
    private String pcolitemkey;//(计划模板指标关联采集项信息表) 主键
    @DatabaseField
    private String terminalkey;// 终端主键
    @DatabaseField
    private String terminalname;// 终端名称
    @DatabaseField
    private String tlevel;// 终端等级
    @DatabaseField
    private String padisconsistent;// PAD端是否同步/PAD端是否上传  0 不上传 1 上传
    @DatabaseField
    private String creuser;// 创建人
    @DatabaseField
    private String updateuser;// 更新人
    @DatabaseField
    private Date cretime;// 创建时间
    @DatabaseField
    private Date updatetime;// 更新时间
	/**
	 * @return the planterminalkey
	 */
	public String getPlanterminalkey() {
		return planterminalkey;
	}
	/**
	 * @param planterminalkey the planterminalkey to set
	 */
	public void setPlanterminalkey(String planterminalkey) {
		this.planterminalkey = planterminalkey;
	}
	/**
	 * @return the pcolitemkey
	 */
	public String getPcolitemkey() {
		return pcolitemkey;
	}
	/**
	 * @param pcolitemkey the pcolitemkey to set
	 */
	public void setPcolitemkey(String pcolitemkey) {
		this.pcolitemkey = pcolitemkey;
	}
	/**
	 * @return the terminalkey
	 */
	public String getTerminalkey() {
		return terminalkey;
	}
	/**
	 * @param terminalkey the terminalkey to set
	 */
	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}
	/**
	 * @return the terminalname
	 */
	public String getTerminalname() {
		return terminalname;
	}
	/**
	 * @param terminalname the terminalname to set
	 */
	public void setTerminalname(String terminalname) {
		this.terminalname = terminalname;
	}
	/**
	 * @return the tlevel
	 */
	public String getTlevel() {
		return tlevel;
	}
	/**
	 * @param tlevel the tlevel to set
	 */
	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}
	/**
	 * @return the creuser
	 */
	public String getCreuser() {
		return creuser;
	}
	/**
	 * @param creuser the creuser to set
	 */
	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}
	/**
	 * @return the updateuser
	 */
	public String getUpdateuser() {
		return updateuser;
	}
	/**
	 * @param updateuser the updateuser to set
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	/**
	 * @return the cretime
	 */
	public Date getCretime() {
		return cretime;
	}
	/**
	 * @param cretime the cretime to set
	 */
	public void setCretime(Date cretime) {
		this.cretime = cretime;
	}
	/**
	 * @return the updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return the padisconsistent
	 */
	public String getPadisconsistent() {
		return padisconsistent;
	}
	/**
	 * @param padisconsistent the padisconsistent to set
	 */
	public void setPadisconsistent(String padisconsistent) {
		this.padisconsistent = padisconsistent;
	}

}