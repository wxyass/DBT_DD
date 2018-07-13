package et.tsingtaopad.db.table;

/**
 * CmmUserroleInfoId entity. @author MyEclipse Persistence Tools
 */

public class CmmUserroleInfoId implements java.io.Serializable {

	// Fields

	private String userid;
	private String roleid;

	// Constructors

	/** default constructor */
	public CmmUserroleInfoId() {
	}

	/** full constructor */
	public CmmUserroleInfoId(String userid, String roleid) {
		this.userid = userid;
		this.roleid = roleid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CmmUserroleInfoId))
			return false;
		CmmUserroleInfoId castOther = (CmmUserroleInfoId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getRoleid() == castOther.getRoleid()) || (this
						.getRoleid() != null && castOther.getRoleid() != null && this
						.getRoleid().equals(castOther.getRoleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		return result;
	}

}