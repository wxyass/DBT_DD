package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MstCollectionitemM entity. @author MyEclipse Persistence Tools
 */

@DatabaseTable(tableName = "MST_COLLECTIONITEM_M")
public class MstCollectionitemM implements java.io.Serializable {

	// Fields

	@DatabaseField(canBeNull = false, id = true)
	private String colitemkey;
	@DatabaseField
	private String colitemcode;
	@DatabaseField
	private String colitemname;
	@DatabaseField
	private String sisconsistent;
	@DatabaseField
	private Date scondate;
	@DatabaseField
	private String isconsistent;
	@DatabaseField
	private Date condate;
	@DatabaseField
	private String remarks;
	@DatabaseField
	private String orderbyno;
	@DatabaseField
	private BigDecimal version;
	@DatabaseField
	private Date credate;
	@DatabaseField
	private String creuser;
	@DatabaseField
	private Date updatetime;
	@DatabaseField
	private String updateuser;
	@DatabaseField(defaultValue = "0")
	private String deleteflag;

	// Constructors

	/** default constructor */
	public MstCollectionitemM() {
	}

	/** minimal constructor */
	public MstCollectionitemM(String colitemkey) {
		this.colitemkey = colitemkey;
	}

	/** full constructor */
	public MstCollectionitemM(String colitemkey, String colitemcode, String colitemname, String sisconsistent, Date scondate, String isconsistent,
			Date condate, String remarks, String orderbyno, String deleteflag, BigDecimal version, Date credate, String creuser, Date updatetime,
			String updateuser) {
		this.colitemkey = colitemkey;
		this.colitemcode = colitemcode;
		this.colitemname = colitemname;
		this.sisconsistent = sisconsistent;
		this.scondate = scondate;
		this.isconsistent = isconsistent;
		this.condate = condate;
		this.remarks = remarks;
		this.orderbyno = orderbyno;
		this.deleteflag = deleteflag;
		this.version = version;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
	}

	// Property accessors

	public String getColitemkey() {
		return this.colitemkey;
	}

	public void setColitemkey(String colitemkey) {
		this.colitemkey = colitemkey;
	}

	public String getColitemcode() {
		return this.colitemcode;
	}

	public void setColitemcode(String colitemcode) {
		this.colitemcode = colitemcode;
	}

	public String getColitemname() {
		return this.colitemname;
	}

	public void setColitemname(String colitemname) {
		this.colitemname = colitemname;
	}

	public String getSisconsistent() {
		return this.sisconsistent;
	}

	public void setSisconsistent(String sisconsistent) {
		this.sisconsistent = sisconsistent;
	}

	public Date getScondate() {
		return this.scondate;
	}

	public void setScondate(Date scondate) {
		this.scondate = scondate;
	}

	public String getIsconsistent() {
		return this.isconsistent;
	}

	public void setIsconsistent(String isconsistent) {
		this.isconsistent = isconsistent;
	}

	public Date getCondate() {
		return this.condate;
	}

	public void setCondate(Date condate) {
		this.condate = condate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderbyno() {
		return this.orderbyno;
	}

	public void setOrderbyno(String orderbyno) {
		this.orderbyno = orderbyno;
	}

	public String getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public Date getCredate() {
		return this.credate;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

	public String getCreuser() {
		return this.creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

}