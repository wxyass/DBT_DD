package dd.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain;

/**
 * Created by yangwenmin on 2017/12/12.
 * 进销存 -- 终端信息
 */
public class TerminalName {
	
	private String terminalName;//终端名称
	private String routeName;//线路名称
	private String terminalKey;//终端key
	
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getTerminalKey() {
		return terminalKey;
	}
	public void setTerminalKey(String terminalKey) {
		this.terminalKey = terminalKey;
	}
	
}
