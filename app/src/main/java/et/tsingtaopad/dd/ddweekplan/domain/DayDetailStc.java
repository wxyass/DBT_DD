package et.tsingtaopad.dd.ddweekplan.domain;


import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DayDetailStc.java</br>
 * 日计划详情
 */
public class DayDetailStc implements Serializable{

	private static final long serialVersionUID = 4156799315664212568L;

	// 周计划主键
	private String weekkey;
	// 日计划主键
	private String daykey;
	// 日计划单个详情主键
	private String detailkey;

	// 追溯项key 集合
	// private List<String> valcheckkeyLv;
	// 追溯项name 集合
	private List<String> valchecknameLv;

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
	private String valroutekeys;
	// 追溯路线name
	private String valroutenames;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	/*public List<String> getValcheckkeyLv() {
		return valcheckkeyLv;
	}

	public void setValcheckkeyLv(List<String> valcheckkeyLv) {
		this.valcheckkeyLv = valcheckkeyLv;
	}*/

	public List<String> getValchecknameLv() {
		return valchecknameLv;
	}

	public void setValchecknameLv(List<String> valchecknameLv) {
		this.valchecknameLv = valchecknameLv;
	}
	public String getValroutenames() {
		return valroutenames;
	}

	public void setValroutenames(String valroutenames) {
		this.valroutenames = valroutenames;
	}

	public String getValroutekeys() {
		return valroutekeys;
	}

	public void setValroutekeys(String valroutekeys) {
		this.valroutekeys = valroutekeys;
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
}
