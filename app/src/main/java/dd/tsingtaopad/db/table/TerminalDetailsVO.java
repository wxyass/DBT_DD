/**
 * 
 */
package dd.tsingtaopad.db.table;

/**
 * 项目名称：营销移动智能工作平台 文件名：TerminalDetailsFragment.java 作者：@沈潇 创建时间：2013/11/24 功能描述:
 * 终端详情 版本 V 1.0 修改履历 日期 原因 BUG号 修改人 修改版本
 */
public class TerminalDetailsVO {
	private String strTerminalId;
	private String strTerminalName;
	private String strProName;
	private String strProId;
	private String strAgencyId;
	private String strAgencyName;
	private String strNum;
	private String visit_date;
	private String strLineId;
	//"{\"strLineId\":\"线路ID\",\"strLineName\":\"线路名称\",\"strTerminalId\":\"终端ID\",\"strTerminalName\":\"终端名称\",\"strProId\":\"产品ID\",\"strProName\":\"产品名称\",\"strAgencyId\":\"经销商ID\",\"strAgencyName\":\"经销商名称\",\"strNum\":\"进货数量\"}," +
	
	public TerminalDetailsVO() {

	}
	public TerminalDetailsVO(String strTerminalId, String strTerminalName,
			String strProName, String strProId, String strAgencyId,
			String strAgencyName, String strNum, String visit_date,
			String strLineId) {
		super();
		this.strTerminalId = strTerminalId;
		this.strTerminalName = strTerminalName;
		this.strProName = strProName;
		this.strProId = strProId;
		this.strAgencyId = strAgencyId;
		this.strAgencyName = strAgencyName;
		this.strNum = strNum;
		this.visit_date = visit_date;
		this.strLineId = strLineId;
	}
	public String getStrTerminalId() {
		return strTerminalId;
	}
	public void setStrTerminalId(String strTerminalId) {
		this.strTerminalId = strTerminalId;
	}
	public String getStrTerminalName() {
		return strTerminalName;
	}
	public void setStrTerminalName(String strTerminalName) {
		this.strTerminalName = strTerminalName;
	}
	public String getStrProName() {
		return strProName;
	}
	public void setStrProName(String strProName) {
		this.strProName = strProName;
	}
	public String getStrProId() {
		return strProId;
	}
	public void setStrProId(String strProId) {
		this.strProId = strProId;
	}
	public String getStrAgencyId() {
		return strAgencyId;
	}
	public void setStrAgencyId(String strAgencyId) {
		this.strAgencyId = strAgencyId;
	}
	public String getStrAgencyName() {
		return strAgencyName;
	}
	public void setStrAgencyName(String strAgencyName) {
		this.strAgencyName = strAgencyName;
	}
	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getStrLineId() {
		return strLineId;
	}
	public void setStrLineId(String strLineId) {
		this.strLineId = strLineId;
	}
	

}
