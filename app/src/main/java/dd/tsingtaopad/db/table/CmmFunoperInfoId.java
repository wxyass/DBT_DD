package dd.tsingtaopad.db.table;

/**
 * CmmFunoperInfoId entity. @author MyEclipse Persistence Tools
 */

public class CmmFunoperInfoId implements java.io.Serializable {

	// Fields

	private String functionid;
	private String operateid;

	// Constructors

	/** default constructor */
	public CmmFunoperInfoId() {
	}

	/** full constructor */
	public CmmFunoperInfoId(String functionid, String operateid) {
		this.functionid = functionid;
		this.operateid = operateid;
	}

	// Property accessors

	public String getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getOperateid() {
		return this.operateid;
	}

	public void setOperateid(String operateid) {
		this.operateid = operateid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CmmFunoperInfoId))
			return false;
		CmmFunoperInfoId castOther = (CmmFunoperInfoId) other;

		return ((this.getFunctionid() == castOther.getFunctionid()) || (this
				.getFunctionid() != null && castOther.getFunctionid() != null && this
				.getFunctionid().equals(castOther.getFunctionid())))
				&& ((this.getOperateid() == castOther.getOperateid()) || (this
						.getOperateid() != null
						&& castOther.getOperateid() != null && this
						.getOperateid().equals(castOther.getOperateid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		result = 37 * result
				+ (getOperateid() == null ? 0 : this.getOperateid().hashCode());
		return result;
	}

}