package dd.tsingtaopad.dd.ddweekplan.domain;


import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DayDetailStc.java</br>
 * 日计划详情
 */
public class DayDetailValStc implements Serializable{

	private static final long serialVersionUID = 4156799315664212568L;

	// 周计划主键
	private String weekkey;
	// 日计划主键
	private String daykey;
	// 日计划详情表主键
	private String detailkey;
	// 日计划详情表附表主键
	private String detailvalkey;

	// 追溯项key
	private String valcheckkey;
	// 追溯项name
	private String valcheckname;

	// 追溯区域
	private String valareakey;
	// 追溯区域
	private String valareaname;

	// 追溯定格
	private String valgridkey;
	// 追溯定格
	private String valgridname;

	// 追溯路线key
	private String valroutekey;
	// 追溯路线name
	private String valroutename;

	public String getWeekkey() {
		return weekkey;
	}

	public void setWeekkey(String weekkey) {
		this.weekkey = weekkey;
	}

	public String getDaykey() {
		return daykey;
	}

	public void setDaykey(String daykey) {
		this.daykey = daykey;
	}

	public String getDetailkey() {
		return detailkey;
	}

	public void setDetailkey(String detailkey) {
		this.detailkey = detailkey;
	}

	public String getDetailvalkey() {
		return detailvalkey;
	}

	public void setDetailvalkey(String detailvalkey) {
		this.detailvalkey = detailvalkey;
	}

	public String getValcheckkey() {
		return valcheckkey;
	}

	public void setValcheckkey(String valcheckkey) {
		this.valcheckkey = valcheckkey;
	}

	public String getValcheckname() {
		return valcheckname;
	}

	public void setValcheckname(String valcheckname) {
		this.valcheckname = valcheckname;
	}

	public String getValareakey() {
		return valareakey;
	}

	public void setValareakey(String valareakey) {
		this.valareakey = valareakey;
	}

	public String getValareaname() {
		return valareaname;
	}

	public void setValareaname(String valareaname) {
		this.valareaname = valareaname;
	}

	public String getValgridkey() {
		return valgridkey;
	}

	public void setValgridkey(String valgridkey) {
		this.valgridkey = valgridkey;
	}

	public String getValgridname() {
		return valgridname;
	}

	public void setValgridname(String valgridname) {
		this.valgridname = valgridname;
	}

	public String getValroutekey() {
		return valroutekey;
	}

	public void setValroutekey(String valroutekey) {
		this.valroutekey = valroutekey;
	}

	public String getValroutename() {
		return valroutename;
	}

	public void setValroutename(String valroutename) {
		this.valroutename = valroutename;
	}

}
