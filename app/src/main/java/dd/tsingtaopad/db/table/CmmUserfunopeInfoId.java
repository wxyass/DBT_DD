package dd.tsingtaopad.db.table;

/**
 * CmmUserfunopeInfoId entity. @author MyEclipse Persistence Tools
 */

public class CmmUserfunopeInfoId implements java.io.Serializable {

	// Fields

	private String userid;
	private String functionid;
	private String operateid;

	// Constructors

	/** default constructor */
	public CmmUserfunopeInfoId() {
	}

	/** full constructor */
	public CmmUserfunopeInfoId(String userid, String functionid,
			String operateid) {
		this.userid = userid;
		this.functionid = functionid;
		this.operateid = operateid;
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
		if (!(other instanceof CmmUserfunopeInfoId))
			return false;
		CmmUserfunopeInfoId castOther = (CmmUserfunopeInfoId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
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
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		result = 37 * result
				+ (getOperateid() == null ? 0 : this.getOperateid().hashCode());
		return result;
	}

}