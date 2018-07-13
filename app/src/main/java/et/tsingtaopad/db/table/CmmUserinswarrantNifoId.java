package et.tsingtaopad.db.table;

/**
 * CmmUserinswarrantNifoId entity. @author MyEclipse Persistence Tools
 */

public class CmmUserinswarrantNifoId implements java.io.Serializable {

	// Fields

	private String userid;
	private String instid;

	// Constructors

	/** default constructor */
	public CmmUserinswarrantNifoId() {
	}

	/** full constructor */
	public CmmUserinswarrantNifoId(String userid, String instid) {
		this.userid = userid;
		this.instid = instid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getInstid() {
		return this.instid;
	}

	public void setInstid(String instid) {
		this.instid = instid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CmmUserinswarrantNifoId))
			return false;
		CmmUserinswarrantNifoId castOther = (CmmUserinswarrantNifoId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getInstid() == castOther.getInstid()) || (this
						.getInstid() != null && castOther.getInstid() != null && this
						.getInstid().equals(castOther.getInstid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getInstid() == null ? 0 : this.getInstid().hashCode());
		return result;
	}

}