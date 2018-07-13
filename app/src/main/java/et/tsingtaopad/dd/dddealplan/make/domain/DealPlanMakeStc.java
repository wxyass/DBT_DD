package et.tsingtaopad.dd.dddealplan.make.domain;


import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：DayPlanStc.java</br>
 * 日计划
 */
public class DealPlanMakeStc implements Serializable{

	private String areakey;//
	private String areaname;//

	private String gridkey;//
	private String gridname;//

	private String routekey;//
	private String routename;//

	private String terminalkey;//
	private String terminalname;//

	private String userkey;//
	private String username;//


	public DealPlanMakeStc() {
	}

	public String getAreakey() {
		return areakey;
	}

	public void setAreakey(String areakey) {
		this.areakey = areakey;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getGridkey() {
		return gridkey;
	}

	public void setGridkey(String gridkey) {
		this.gridkey = gridkey;
	}

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
	}

	public String getRoutekey() {
		return routekey;
	}

	public void setRoutekey(String routekey) {
		this.routekey = routekey;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTerminalkey() {
		return terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getTerminalname() {
		return terminalname;
	}

	public void setTerminalname(String terminalname) {
		this.terminalname = terminalname;
	}
}
