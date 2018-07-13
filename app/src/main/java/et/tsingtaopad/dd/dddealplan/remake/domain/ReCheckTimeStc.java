package et.tsingtaopad.dd.dddealplan.remake.domain;


import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：ReCheckTimeStc.java</br>
 * 日计划
 */
public class ReCheckTimeStc implements Serializable{


	private String id;//整改计划审核表 主键
	private String repairid;// 主表 主键
	private String status;//
	private String repairtime;//

	public ReCheckTimeStc() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepairid() {
		return repairid;
	}

	public void setRepairid(String repairid) {
		this.repairid = repairid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRepairtime() {
		return repairtime;
	}

	public void setRepairtime(String repairtime) {
		this.repairtime = repairtime;
	}
}
