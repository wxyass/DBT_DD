package dd.tsingtaopad.db.table;

/**
 * CmmRolefunopeInfoId entity. @author MyEclipse Persistence Tools
 */

public class CmmRolefunopeInfoId implements java.io.Serializable {

	// Fields

	private String roleid;
	private String functionid;
	private String operateid;

	// Constructors

	/** default constructor */
	public CmmRolefunopeInfoId() {
	}

	/** full constructor */
	public CmmRolefunopeInfoId(String roleid, String functionid,
			String operateid) {
		this.roleid = roleid;
		this.functionid = functionid;
		this.operateid = operateid;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

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
		if (!(other instanceof CmmRolefunopeInfoId))
			return false;
		CmmRolefunopeInfoId castOther = (CmmRolefunopeInfoId) other;

		return ((this.getRoleid() == castOther.getRoleid()) || (this
				.getRoleid() != null && castOther.getRoleid() != null && this
				.getRoleid().equals(castOther.getRoleid())))
				&& ((this.getFunctionid() == castOther.getFunctionid()) || (this
						.getFunctionid() != null
						&& castOther.getFunctionid() != null && this
						.getFunctionid().equals(castOther.getFunctionid())))
				&& ((this.getOperateid() == castOther.getOperateid()) || (this
						.getOperateid() != null
						&& castOther.getOperateid() != null && this
						.getOperateid().equals(castOther.getOperateid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		result = 37 * result
				+ (getOperateid() == null ? 0 : this.getOperateid().hashCode());
		return result;
	}

}