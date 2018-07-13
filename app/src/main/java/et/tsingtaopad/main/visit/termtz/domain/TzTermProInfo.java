package et.tsingtaopad.main.visit.termtz.domain;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述:终端进货台账_终端弹窗条目bean </br>
 */
public class TzTermProInfo {

	private String proname;// 产品名称
	private int purchase; // 进货量
	private String productkey;// 产品主键
	private String terminalkey;// 终端主键
	private String agencykey;// 经销商主键
	private String purchasetime; // 进货时间
	
	public TzTermProInfo() {
		super();
	}

	/**
	 * @param //itemName
	 */
	public TzTermProInfo(String proname) {
		super();
		this.proname = proname;
	}
	
	/**
	 * @param //itemName
	 * @param //changeNum
	 */
	public TzTermProInfo(String proname, int purchase) {
		super();
		this.proname = proname;
		this.purchase = purchase;
	}

	/**
	 * @param //itemName
	 * @param //changeNum
	 * @param productkey
	 * @param //procode
	 */
	public TzTermProInfo(String proname, int purchase, String productkey) {
		super();
		this.proname = proname;
		this.purchase = purchase;
		this.productkey = productkey;
	}
	
	/**
	 * @param //itemName
	 * @param //changeNum
	 * @param productkey
	 * @param terminalkey
	 * @param agencykey
	 */
	public TzTermProInfo(String proname, int purchase, String productkey,
			String terminalkey, String agencykey) {
		super();
		this.proname = proname;
		this.purchase = purchase;
		this.productkey = productkey;
		this.terminalkey = terminalkey;
		this.agencykey = agencykey;
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
	 * @return the purchase
	 */
	public int getPurchase() {
		return purchase;
	}

	/**
	 * @param purchase the purchase to set
	 */
	public void setPurchase(int purchase) {
		this.purchase = purchase;
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
