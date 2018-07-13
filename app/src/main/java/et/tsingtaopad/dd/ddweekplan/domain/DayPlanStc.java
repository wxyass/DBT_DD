package et.tsingtaopad.dd.ddweekplan.domain;


import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DayPlanStc.java</br>
 * 日计划
 */
public class DayPlanStc implements Serializable{

	private String planKey;//绑定的
	private String weekday;//周几 (周日)
	private String visitTime;// (2018-03-12)
	private String operation;//操作
	private String state = "0";//状态
	private String plancheck;//追溯项
	private String planareaid;//追溯区域
	private String plangridid;//追溯定格
	private String planroute;//追溯路线

	private String visitStartDate;//拜访开始日期yyMMdd
	private String visitEndDate;//拜访结束日期
	private String plandate;//计划日期

	// 追溯项name 集合
	private List<DayDetailStc> detailStcs;

	public DayPlanStc() {
	}

	public String getPlanKey() {
		return planKey;
	}

	public void setPlanKey(String planKey) {
		this.planKey = planKey;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPlancheck() {
		return plancheck;
	}

	public void setPlancheck(String plancheck) {
		this.plancheck = plancheck;
	}

	public String getPlanareaid() {
		return planareaid;
	}

	public void setPlanareaid(String planareaid) {
		this.planareaid = planareaid;
	}

	public String getPlangridid() {
		return plangridid;
	}

	public void setPlangridid(String plangridid) {
		this.plangridid = plangridid;
	}

	public String getPlanroute() {
		return planroute;
	}

	public void setPlanroute(String planroute) {
		this.planroute = planroute;
	}

	public String getVisitStartDate() {
		return visitStartDate;
	}

	public void setVisitStartDate(String visitStartDate) {
		this.visitStartDate = visitStartDate;
	}

	public String getVisitEndDate() {
		return visitEndDate;
	}

	public void setVisitEndDate(String visitEndDate) {
		this.visitEndDate = visitEndDate;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public List<DayDetailStc> getDetailStcs() {
		return detailStcs;
	}

	public void setDetailStcs(List<DayDetailStc> detailStcs) {
		this.detailStcs = detailStcs;
	}
}
