package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * PadPlantempcheckM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_PLANTEMPCHECK_M")
public class PadPlantempcheckM implements java.io.Serializable {

	// Fields
    @DatabaseField(canBeNull = false, id = true)
	private String checkkey;
    @DatabaseField
	private String plantempkey;
    @DatabaseField
	private String tempname;
    @DatabaseField
	private String checkname;
    @DatabaseField
	private String mainclasskey;
    @DatabaseField
    private String checkuse;
	// Constructors

	/** default constructor */
	public PadPlantempcheckM() {
	}

	/** minimal constructor */
	public PadPlantempcheckM(String checkkey) {
		this.checkkey = checkkey;
	}

	/** full constructor */
	public PadPlantempcheckM(String checkkey, String plantempkey,
			String tempname, String checkname, String mainclasskey,String checkuse) {
		this.checkkey = checkkey;
		this.plantempkey = plantempkey;
		this.tempname = tempname;
		this.checkname = checkname;
		this.mainclasskey = mainclasskey;
		this.checkuse = checkuse;
	}

	// Property accessors

	public String getCheckkey() {
		return this.checkkey;
	}

	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}

	public String getPlantempkey() {
		return this.plantempkey;
	}

	public void setPlantempkey(String plantempkey) {
		this.plantempkey = plantempkey;
	}

	public String getTempname() {
		return this.tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
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

	public void setCheckuse(String checkuse) {
		this.checkuse = checkuse;
	}

	public String getCheckuse() {
		return checkuse;
	}
}