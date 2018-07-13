package et.tsingtaopad.main.operation.workplan.domain;

import java.io.Serializable;
import java.util.List;

import et.tsingtaopad.main.operation.weekworkplan.BlankTermLevelStc;


/**
 * 功能描述: ViewPager里listview item显示内容结构体</br>
 */
public class VpLvItemStc implements Serializable {

	private static final long serialVersionUID = 4538784749865013745L;

	private String pcolitemkey;// 删除空白终端需要用到 计划采集项主键
	private String name;// 道具生动化时 采集项名称
	private String key;//产品主键或者采集项主键colitemkey
	private String num = String.valueOf(0);
	private String term;//多个终端名称
	private List<BlankTermLevelStc> terminals;
	private List<BlankTermLevelStc> reviewterminals;//回显的数据 现在是放在adapter selectTerminalsMap 上从面向对象上讲应该放在此结构体上
	private String proname;// 道具生动化时 产品名称
	private String pronamekey;// 道具生动化时 产品名称key
	private String dingdannum;// 订单目标数量
	
	public VpLvItemStc() {
	}

	/**
	 * @param pcolitemkey
	 * @param name
	 * @param key
	 * @param num
	 * @param term
	 * @param terminals
	 * @param reviewterminals
	 */
	public VpLvItemStc(String pcolitemkey, String name, String key, String num, String term, List<BlankTermLevelStc> terminals, List<BlankTermLevelStc> reviewterminals) {
		this.pcolitemkey = pcolitemkey;
		this.name = name;
		this.key = key;
		this.num = num;
		this.term = term;
		this.terminals = terminals;
		this.reviewterminals = reviewterminals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<BlankTermLevelStc> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<BlankTermLevelStc> terminals) {
		this.terminals = terminals;
	}

	public String getPcolitemkey() {
		return pcolitemkey;
	}

	public void setPcolitemkey(String pcolitemkey) {
		this.pcolitemkey = pcolitemkey;
	}

	public List<BlankTermLevelStc> getReviewterminals() {
		return reviewterminals;
	}

	public void setReviewterminals(List<BlankTermLevelStc> reviewterminals) {
		this.reviewterminals = reviewterminals;
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
	 * @return the pronamekey
	 */
	public String getPronamekey() {
		return pronamekey;
	}

	/**
	 * @param pronamekey the pronamekey to set
	 */
	public void setPronamekey(String pronamekey) {
		this.pronamekey = pronamekey;
	}

	/**
	 * @return the dingdannum
	 */
	public String getDingdannum() {
		return dingdannum;
	}

	/**
	 * @param dingdannum the dingdannum to set
	 */
	public void setDingdannum(String dingdannum) {
		this.dingdannum = dingdannum;
	}

	

}
