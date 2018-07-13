package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MstBsDataDaoImpl;


/**
 * MstAgencyinfoM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MST_BS_DATA", daoClass = MstBsDataDaoImpl.class)
//@DatabaseTable(tableName = "MST_AGENCYKF_M")
public class MstBsData implements java.io.Serializable {

	// Fields
	@DatabaseField(id = true, canBeNull = false)
	private String bsdatakey;// 主键
	@DatabaseField
	private String title;// 接口名称
	@DatabaseField
	private String sizes;// 流量大小
	@DatabaseField
	private String credate;// 创建时间
	@DatabaseField
	private String flag;// 上传:0  下载:1
	@DatabaseField
	private String content;// 工号
	@DatabaseField
	private String remark;// 备注
	
	/**
	 * @return the bsdatakey
	 */
	public String getBsdatakey() {
		return bsdatakey;
	}
	/**
	 * @param bsdatakey the bsdatakey to set
	 */
	public void setBsdatakey(String bsdatakey) {
		this.bsdatakey = bsdatakey;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the sizes
	 */
	public String getSizes() {
		return sizes;
	}
	/**
	 * @param sizes the sizes to set
	 */
	public void setSizes(String sizes) {
		this.sizes = sizes;
	}
	/**
	 * @return the credate
	 */
	public String getCredate() {
		return credate;
	}
	/**
	 * @param credate the credate to set
	 */
	public void setCredate(String credate) {
		this.credate = credate;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}