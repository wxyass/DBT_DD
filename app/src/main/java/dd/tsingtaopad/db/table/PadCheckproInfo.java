package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * PadCheckstatusInfo entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "PAD_CHECKPRO_INFO")
public class PadCheckproInfo implements java.io.Serializable {

	// Fields
    @DatabaseField(canBeNull = false, id = true)
	private String checkprokey;
    @DatabaseField
    private String checkkey;
    @DatabaseField
	private String minorchannel;
    @DatabaseField
	private String productkey;

	// Constructors

	/** default constructor */
	public PadCheckproInfo() {
	}

	/** minimal constructor */
	public PadCheckproInfo(String checkprokey) {
		this.checkprokey = checkprokey;
	}

	/** full constructor */
	public PadCheckproInfo(String checkprokey, String checkkey, String minorchannel, String productkey) {
		this.checkprokey = checkprokey;
		this.checkkey = checkkey;
		this.minorchannel = minorchannel;
		this.productkey = productkey;
	}

	// Property accessors
    public String getCheckprokey() {
        return checkprokey;
    }

    public void setCheckprokey(String checkprokey) {
        this.checkprokey = checkprokey;
    }

    public String getCheckkey() {
        return checkkey;
    }

    public void setCheckkey(String checkkey) {
        this.checkkey = checkkey;
    }

    public String getMinorchannel() {
        return minorchannel;
    }

    public void setMinorchannel(String minorchannel) {
        this.minorchannel = minorchannel;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

}