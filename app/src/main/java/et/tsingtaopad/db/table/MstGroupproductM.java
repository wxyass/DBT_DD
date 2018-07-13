package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import et.tsingtaopad.db.dao.impl.MstGroupproductMDaoImpl;


/**
 * MstVisitM entity. @author MyEclipse Persistence Tools
 */
//MST_VISIT_M(拜访主表)
@DatabaseTable(tableName = "MST_GROUPPRODUCT_M", daoClass = MstGroupproductMDaoImpl.class)
public class MstGroupproductM implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String gproductid;
	@DatabaseField
	private String terminalcode;
	@DatabaseField
	private String terminalname;
	@DatabaseField
	private String ifrecstand;
	@DatabaseField
	private String startdate;// 默认: 当天时间 yyyy-MM-dd +"  00:00:00"
	@DatabaseField
	private String enddate;//  默认: "3000-12-01"+"  00:00:00"
	@DatabaseField
	private String createusereng;
	@DatabaseField
	private String createdate;
	@DatabaseField
	private String updateusereng;
	@DatabaseField
	private String updatetime;
	@DatabaseField
	private String visitkey;

	
	@DatabaseField(defaultValue = "0")
	private String uploadflag;//  0:该条记录不上传  1:该条记录需要上传 ;
	
	@DatabaseField(defaultValue = "0")
	private String padisconsistent; //  0:还未上传  1:已上传;
	
	// Constructors

	/** default constructor */
	public MstGroupproductM() {
	}

	/** minimal constructor */
	public MstGroupproductM(String gproductid) {
		this.gproductid = gproductid;
	}

	/**
	 * @param gproductid
	 * @param terminalcode
	 * @param terminalname
	 * @param ifrecstand
	 * @param startdate
	 * @param enddate
	 * @param createusereng
	 * @param createdate
	 * @param updateusereng
	 * @param updatetime
	 * @param uploadFlag
	 * @param padisconsistent
	 */
	public MstGroupproductM(String gproductid, String terminalcode, String terminalname, String ifrecstand, String startdate, String enddate, String createusereng, String createdate, String updateusereng, String updatetime, String uploadFlag, String padisconsistent) {
		super();
		this.gproductid = gproductid;
		this.terminalcode = terminalcode;
		this.terminalname = terminalname;
		this.ifrecstand = ifrecstand;
		this.startdate = startdate;
		this.enddate = enddate;
		this.createusereng = createusereng;
		this.createdate = createdate;
		this.updateusereng = updateusereng;
		this.updatetime = updatetime;
		this.uploadflag = uploadFlag;
		this.padisconsistent = padisconsistent;
	}

	/**
	 * @return the gproductid
	 */
	public String getGproductid() {
		return gproductid;
	}

	/**
	 * @param gproductid the gproductid to set
	 */
	public void setGproductid(String gproductid) {
		this.gproductid = gproductid;
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
	 * @return the ifrecstand
	 */
	public String getIfrecstand() {
		return ifrecstand;
	}

	/**
	 * @param ifrecstand the ifrecstand to set
	 */
	public void setIfrecstand(String ifrecstand) {
		this.ifrecstand = ifrecstand;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public String getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the createusereng
	 */
	public String getCreateusereng() {
		return createusereng;
	}

	/**
	 * @param createusereng the createusereng to set
	 */
	public void setCreateusereng(String createusereng) {
		this.createusereng = createusereng;
	}

	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the updateusereng
	 */
	public String getUpdateusereng() {
		return updateusereng;
	}

	/**
	 * @param updateusereng the updateusereng to set
	 */
	public void setUpdateusereng(String updateusereng) {
		this.updateusereng = updateusereng;
	}

	/**
	 * @return the updatetime
	 */
	public String getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * @return the uploadFlag
	 */
	public String getUploadFlag() {
		return uploadflag;
	}

	/**
	 * @param uploadFlag the uploadFlag to set
	 */
	public void setUploadFlag(String uploadFlag) {
		this.uploadflag = uploadFlag;
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

	public String getVisitkey() {
		return visitkey;
	}

	public void setVisitkey(String visitkey) {
		this.visitkey = visitkey;
	}

	public String getUploadflag() {
		return uploadflag;
	}

	public void setUploadflag(String uploadflag) {
		this.uploadflag = uploadflag;
	}
}