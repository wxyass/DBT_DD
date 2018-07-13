package et.tsingtaopad.main.visit.termtz.domain;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:终端进货台账_定格列表bean </br>
 */
public class TzGridAreaInfo {

	private String areaid;// 区域id
	private String areaname; // 区域名称
	private String gridkey; // 定格主键
	private String gridname; // 定格名称
	/**
	 * @return the areaid
	 */
	public String getAreaid() {
		return areaid;
	}
	/**
	 * @param areaid the areaid to set
	 */
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	/**
	 * @return the areaname
	 */
	public String getAreaname() {
		return areaname;
	}
	/**
	 * @param areaname the areaname to set
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	/**
	 * @return the gridkey
	 */
	public String getGridkey() {
		return gridkey;
	}
	/**
	 * @param gridkey the gridkey to set
	 */
	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}
	/**
	 * @return the gridname
	 */
	public String getGridname() {
		return gridname;
	}
	/**
	 * @param gridname the gridname to set
	 */
	public void setGridname(String gridname) {
		this.gridname = gridname;
	}
	
}
