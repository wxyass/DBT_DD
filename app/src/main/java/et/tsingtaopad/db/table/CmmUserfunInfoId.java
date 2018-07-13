package et.tsingtaopad.db.table;

/**
 * CmmUserfunInfoId entity. @author MyEclipse Persistence Tools
 */

public class CmmUserfunInfoId implements java.io.Serializable {

	// Fields

	private String userid;
	private String functionid;

	// Constructors

	/** default constructor */
	public CmmUserfunInfoId() {
	}

	/** full constructor */
	public CmmUserfunInfoId(String userid, String functionid) {
		this.userid = userid;
		this.functionid = functionid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CmmUserfunInfoId))
			return false;
		CmmUserfunInfoId castOther = (CmmUserfunInfoId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getFunctionid() == castOther.getFunctionid()) || (this
						.getFunctionid() != null
						&& castOther.getFunctionid() != null && this
						.getFunctionid().equals(castOther.getFunctionid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		return result;
	}

}