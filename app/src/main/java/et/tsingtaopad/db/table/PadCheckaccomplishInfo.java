package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

// default package

/**
 * PadCheckaccomplishInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_CHECKACCOMPLISH_INFO")
public class PadCheckaccomplishInfo implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String accomplishkey;
	@DatabaseField
	private String checkkey;
	@DatabaseField
	private String cstatuskey;
	@DatabaseField
	private String colitemkey;
	@DatabaseField
	private String colitemname;
	@DatabaseField
	private String productkey;
	@DatabaseField
	private Double addcount;
	@DatabaseField
	private Double totalcount;
	@DatabaseField
	private String orderbyno;
	@DatabaseField
	private String minorchannel;
	@DatabaseField
	private String areaid;

	// Constructors

	/** default constructor */
	public PadCheckaccomplishInfo() {
	}

	/** minimal constructor */
	public PadCheckaccomplishInfo(String accomplishkey) {
		this.accomplishkey = accomplishkey;
	}

	/** full constructor */
	public PadCheckaccomplishInfo(String accomplishkey, String checkkey, String cstatuskey, String colitemkey, String colitemname, String productkey, Double addcount, Double totalcount, String orderbyno, String minorchannel) {
		this.accomplishkey = accomplishkey;
		this.checkkey = checkkey;
		this.cstatuskey = cstatuskey;
		this.colitemkey = colitemkey;
		this.colitemname = colitemname;
		this.productkey = productkey;
		this.addcount = addcount;
		this.totalcount = totalcount;
		this.orderbyno = orderbyno;
		this.minorchannel = minorchannel;
	}

	// Property accessors

	public String getAccomplishkey() {
		return this.accomplishkey;
	}

	public void setAccomplishkey(String accomplishkey) {
		this.accomplishkey = accomplishkey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getCstatuskey() {
		return this.cstatuskey;
	}

	public void setCstatuskey(String cstatuskey) {
		this.cstatuskey = cstatuskey;
	}

	public String getColitemkey() {
		return this.colitemkey;
	}

	public void setColitemkey(String colitemkey) {
		this.colitemkey = colitemkey;
	}

	public String getColitemname() {
		return this.colitemname;
	}

	public void setColitemname(String colitemname) {
		this.colitemname = colitemname;
	}

	public String getProductkey() {
		return this.productkey;
	}

	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}

	public Double getAddcount() {
		return this.addcount;
	}

	public void setAddcount(Double addcount) {
		this.addcount = addcount;
	}

	public Double getTotalcount() {
		return this.totalcount;
	}

	public void setTotalcount(Double totalcount) {
		this.totalcount = totalcount;
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

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
}