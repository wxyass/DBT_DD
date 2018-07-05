package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * PadCheckstatusInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_CHECKSTATUS_INFO")
public class PadCheckstatusInfo implements java.io.Serializable {

	@DatabaseField(canBeNull = false, id = true)
	private String cstatuskey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String cstatuscode;
	@DatabaseField
	private String cstatusname;
	@DatabaseField
	private String orderbyno;
	@DatabaseField
	private String isdefault;
	@DatabaseField
	private String cstatustype;
	@DatabaseField
	private String ispadshow;

	// Constructors

	/** default constructor */
	public PadCheckstatusInfo() {
	}

	/** minimal constructor */
	public PadCheckstatusInfo(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	/** full constructor */
	public PadCheckstatusInfo(String cstatuskey, String checkkey, String cstatuscode, String cstatusname, String orderbyno, String isdefault, String cstatustype, String ispadshow) {
		this.cstatuskey = cstatuskey;
		this.checkkey = checkkey;
		this.cstatuscode = cstatuscode;
		this.orderbyno = orderbyno;
		this.cstatusname = cstatusname;
		this.isdefault = isdefault;
		this.cstatustype = cstatustype;
		this.ispadshow = ispadshow;
	}

	// Property accessors

	public String getCstatuskey() {
		return this.cstatuskey;
	}

	public void setCstatuskey(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getCstatuscode() {
		return this.cstatuscode;
	}

	public void setCstatuscode(String cstatuscode) {
		this.cstatuscode = cstatuscode;
	}

	public String getCstatusname() {
		return this.cstatusname;
	}

	public void setCstatusname(String cstatusname) {
		this.cstatusname = cstatusname;
	}

	public String getIsdefault() {
		return this.isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getCstatustype() {
		return this.cstatustype;
	}

	public void setCstatustype(String cstatustype) {
		this.cstatustype = cstatustype;
	}

	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
	}

	public String getOrderbyno() {
		return orderbyno;
	}

	public void setIspadshow(String ispadshow) {
		this.ispadshow = ispadshow;
	}

	public String getIspadshow() {
		return ispadshow;
	}

}