package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * PadPlantempcollectionInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_PLANTEMPCOLLECTION_INFO")
public class PadPlantempcollectionInfo implements java.io.Serializable {

	// Fields
    @DatabaseField(canBeNull = false, id = true)
	private String ptcollkey;
    @DatabaseField
	private String checkkey;
    @DatabaseField
	private String colitemkey;
    @DatabaseField
	private String colitemname;

	// Constructors

	/** default constructor */
	public PadPlantempcollectionInfo() {
	}
	
	/** minimal constructor */
	public PadPlantempcollectionInfo(String ptcollkey) {
		this.ptcollkey = ptcollkey;
	}

	/** full constructor */
	public PadPlantempcollectionInfo(String ptcollkey, String checkkey,
			String colitemkey, String colitemname) {
		this.ptcollkey = ptcollkey;
		this.checkkey = checkkey;
		this.colitemkey = colitemkey;
		this.colitemname = colitemname;
	}

	// Property accessors

	public String getPtcollkey() {
		return this.ptcollkey;
	}

	public void setPtcollkey(String ptcollkey) {
		this.ptcollkey = ptcollkey;
	}

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
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

	@Override
	public String toString() {
		return "PadPlantempcollectionInfo [ptcollkey=" + ptcollkey + ", checkkey=" + checkkey + ", colitemkey=" + colitemkey + ", colitemname=" + colitemname + "]";
	}

}