package dd.tsingtaopad.db.table;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dd.tsingtaopad.db.dao.impl.PadChecktypeMDaoImpl;


// default package

/**
 * PadChecktypeM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_CHECKTYPE_M", daoClass=PadChecktypeMDaoImpl.class)
public class PadChecktypeM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String checktypekey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String isproduct;
	@DatabaseField
	private String iscondition;
	@DatabaseField
	private String tempkey;
	@DatabaseField
	private String tempnae;
	@DatabaseField
	private String checktype;
	@DatabaseField
	private String checkname;
	@DatabaseField
	private String mainclasskey;
	@DatabaseField
	private String orderbyno;
	@DatabaseField
	private String minorchannel;

	// Constructors

	/** default constructor */
	public PadChecktypeM() {
	}

	/** minimal constructor */
	public PadChecktypeM(String checktypekey) {
		this.checktypekey = checktypekey;
	}

	/** full constructor */
	public PadChecktypeM(String checktypekey, String checkkey, String isproduct, String iscondition, String tempkey, String tempnae, String checktype, String checkname, String mainclasskey, String orderbyno, String minorchannel) {
		this.checktypekey = checktypekey;
		this.checkkey = checkkey;
		this.isproduct = isproduct;
		this.iscondition = iscondition;
		this.tempkey = tempkey;
		this.tempnae = tempnae;
		this.checktype = checktype;
		this.checkname = checkname;
		this.mainclasskey = mainclasskey;
		this.orderbyno = orderbyno;
		this.minorchannel = minorchannel;
	}

	// Property accessors

	public String getChecktypekey() {
		return this.checktypekey;
	}

	public void setChecktypekey(String checktypekey) {
		this.checktypekey = checktypekey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getIsproduct() {
		return this.isproduct;
	}

	public void setIsproduct(String isproduct) {
		this.isproduct = isproduct;
	}

	public String getIscondition() {
		return this.iscondition;
	}

	public void setIscondition(String iscondition) {
		this.iscondition = iscondition;
	}

	public String getTempkey() {
		return this.tempkey;
	}

	public void setTempkey(String tempkey) {
		this.tempkey = tempkey;
	}

	public String getTempnae() {
		return this.tempnae;
	}

	public void setTempnae(String tempnae) {
		this.tempnae = tempnae;
	}

	public String getChecktype() {
		return this.checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}

	public String getCheckname() {
		return this.checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}

	public String getMainclasskey() {
		return this.mainclasskey;
	}

	public void setMainclasskey(String mainclasskey) {
		this.mainclasskey = mainclasskey;
	}

	public String getOrderbyno() {
		return this.orderbyno;
	}

	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
	}

	public String getMinorchannel() {
		return this.minorchannel;
	}

	public void setMinorchannel(String minorchannel) {
		this.minorchannel = minorchannel;
	}

}