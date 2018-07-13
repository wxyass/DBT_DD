package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MstTermLedgerInfoDaoImpl;


/**
 * 文件名：MstTermLedgerInfo.java 终端进货台账_ 经销商+终端+产品表
 */
//@DatabaseTable(tableName = "MST_TERMLEDGER_INFO", daoClass = MstAgencyKFMDaoImpl.class)
@DatabaseTable(tableName = "MST_TERMLEDGER_INFO", daoClass = MstTermLedgerInfoDaoImpl.class)
public class MstTermLedgerInfo implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String termledgerkey;// 终端进货台账主键
	@DatabaseField
	private String terminalkey;// 终端主键
	@DatabaseField
	private String terminalcode;// 终端编码
	@DatabaseField
	private String terminalname;// 终端名称
	@DatabaseField
	private String gridname;// 定格名称
	@DatabaseField
	private String gridkey;// 定格主键
	@DatabaseField
	private String agencykey;// 经销商主键
	@DatabaseField
	private String agencycode;// 经销商编码
	@DatabaseField
	private String agencyname;// 经销商名称
	@DatabaseField
	private String productkey;// 产品主键
	@DatabaseField
	private String procode;// 产品编码
	@DatabaseField
	private String proname;// 产品名称
	@DatabaseField
	private String padisconsistent;// PAD端是否同步(0未上传,1已上传  在同步时会将字段默认值为1)
	@DatabaseField
	private String orderbyno;// 顺序
	@DatabaseField(defaultValue = "0")
	private String deleteflag;// 记录删除标记
	@DatabaseField
	private String remarks;//备注
	@DatabaseField
	private String yesup;// 此记录是否需要上传(0:不上传,1:确定上传)
	@DatabaseField
	private String purchase;// 进货量
	@DatabaseField
	private String sequence;// 终端排列顺序
	@DatabaseField
	private String routename; // 线路
	@DatabaseField
	private String address; // 地址
	@DatabaseField
	private String contact; // 关键人
	@DatabaseField
	private String mobile; // 联系电话
	@DatabaseField
	private String areaid; // 区域id
	@DatabaseField
	private String areaname; // 区域名称
	@DatabaseField
	private String downdate; // 记录下载时间(用于隔天清零)
	@DatabaseField
	private String firstzm; // 终端名称首字母
	@DatabaseField
	private String purchasetime; // 进货时间
	
	/**
	 * @return the termledgerkey
	 */
	public String getTermledgerkey() {
		return termledgerkey;
	}
	/**
	 * @param termledgerkey the termledgerkey to set
	 */
	public void setTermledgerkey(String termledgerkey) {
		this.termledgerkey = termledgerkey;
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
	 * @return the terminalcode
	 */
	public String getTerminalcode() {
		return terminalcode;
	}
	/**
	 * @param terminalcode the terminalcode to set
	 */
	public void setTerminalcode(String terminalcode) {
		this.terminalcode = terminalcode;
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
	 * @return the agencykey
	 */
	public String getAgencykey() {
		return agencykey;
	}
	/**
	 * @param agencykey the agencykey to set
	 */
	public void setAgencykey(String agencykey) {
		this.agencykey = agencykey;
	}
	/**
	 * @return the agencycode
	 */
	public String getAgencycode() {
		return agencycode;
	}
	/**
	 * @param agencycode the agencycode to set
	 */
	public void setAgencycode(String agencycode) {
		this.agencycode = agencycode;
	}
	/**
	 * @return the agencyname
	 */
	public String getAgencyname() {
		return agencyname;
	}
	/**
	 * @param agencyname the agencyname to set
	 */
	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}
	/**
	 * @return the productkey
	 */
	public String getProductkey() {
		return productkey;
	}
	/**
	 * @param productkey the productkey to set
	 */
	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}
	/**
	 * @return the procode
	 */
	public String getProcode() {
		return procode;
	}
	/**
	 * @param procode the procode to set
	 */
	public void setProcode(String procode) {
		this.procode = procode;
	}
	/**
	 * @return the proname
	 */
	public String getProname() {
		return proname;
	}
	/**
	 * @param proname the proname to set
	 */
	public void setProname(String proname) {
		this.proname = proname;
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
	/**
	 * @return the orderbyno
	 */
	public String getOrderbyno() {
		return orderbyno;
	}
	/**
	 * @param orderbyno the orderbyno to set
	 */
	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
	}
	/**
	 * @return the deleteflag
	 */
	public String getDeleteflag() {
		return deleteflag;
	}
	/**
	 * @param deleteflag the deleteflag to set
	 */
	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the yesup
	 */
	public String getYesup() {
		return yesup;
	}
	/**
	 * @param yesup the yesup to set
	 */
	public void setYesup(String yesup) {
		this.yesup = yesup;
	}
	/**
	 * @return the purchase
	 */
	public String getPurchase() {
		return purchase;
	}
	/**
	 * @param purchase the purchase to set
	 */
	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the routename
	 */
	public String getRoutename() {
		return routename;
	}
	/**
	 * @param routename the routename to set
	 */
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	 * @return the downdate
	 */
	public String getDowndate() {
		return downdate;
	}
	/**
	 * @param downdate the downdate to set
	 */
	public void setDowndate(String downdate) {
		this.downdate = downdate;
	}
	/**
	 * @return the firstzm
	 */
	public String getFirstzm() {
		return firstzm;
	}
	/**
	 * @param firstzm the firstzm to set
	 */
	public void setFirstzm(String firstzm) {
		this.firstzm = firstzm;
	}
	/**
	 * @return the purchasetime
	 */
	public String getPurchasetime() {
		return purchasetime;
	}
	/**
	 * @param purchasetime the purchasetime to set
	 */
	public void setPurchasetime(String purchasetime) {
		this.purchasetime = purchasetime;
	}
	
}