/**
 * 
 */
package et.tsingtaopad.db.table;

/**
 * 项目名称：营销移动智能工作平台 <br>
 * 文件名：TomorrowWorkVO.java <br>
 * 作者：@沈潇 <br>
 * 创建时间：2013/11/24 <br>
 * 功能描述: 保存对象 <br>
 * 版本 V 1.0 <br>
 * 修改履历 <br>
 * 日期 原因 BUG号 修改人 修改版本 <br>
 * 30个字段
 */

public class TomorrowWorkVO {
	private String ROUTENAME;
	private String VISITDATE;
	private String VISITUSER;
	private String ISHDISTRIBUTION;
	private String SELFOCCUPANCY;
	private String ISCMPCOLLAPSE;
	private String STATUS;
	private String SELFTREATY;
	private String CMPTREATY;
	private String ISSELF;
	
	private String ISCMP;
	private String EXETREATY;
	private String TERMINALCODE;
	private String TERMINALNAME;
	private String TLEVEL;
	private String CONTACT;
	private String ADDRESS;
	private String MINORCHANNEL;
	private String PURCPRICE;
	private String RETAILPRICE;
	
	private String SALENUM;
	private String ADDCOUNT;
	private String TOTALCOUNT;
	private String COLITEMNAME;
	private String PROCODE;
	private String PRONAME;
	private String AGENCYNAME;
	private String ph;
	private String dj;
	private String cp;

	public TomorrowWorkVO(String rOUTENAME, String vISITDATE, String vISITUSER,
			String iSHDISTRIBUTION, String sELFOCCUPANCY, String iSCMPCOLLAPSE,
			String sTATUS, String sELFTREATY, String cMPTREATY, String iSSELF,
			String iSCMP, String eXETREATY, String tERMINALCODE,
			String tERMINALNAME, String tLEVEL, String cONTACT, String aDDRESS,
			String mINORCHANNEL, String pURCPRICE, String rETAILPRICE,
			String sALENUM, String aDDCOUNT, String tOTALCOUNT,
			String cOLITEMNAME, String pROCODE, String pRONAME,
			String aGENCYNAME, String ph, String dj, String cp) {
		super();
		ROUTENAME = rOUTENAME;
		VISITDATE = vISITDATE;
		VISITUSER = vISITUSER;
		ISHDISTRIBUTION = iSHDISTRIBUTION;
		SELFOCCUPANCY = sELFOCCUPANCY;
		ISCMPCOLLAPSE = iSCMPCOLLAPSE;
		STATUS = sTATUS;
		SELFTREATY = sELFTREATY;
		CMPTREATY = cMPTREATY;
		ISSELF = iSSELF;
		ISCMP = iSCMP;
		EXETREATY = eXETREATY;
		TERMINALCODE = tERMINALCODE;
		TERMINALNAME = tERMINALNAME;
		TLEVEL = tLEVEL;
		CONTACT = cONTACT;
		ADDRESS = aDDRESS;
		MINORCHANNEL = mINORCHANNEL;
		PURCPRICE = pURCPRICE;
		RETAILPRICE = rETAILPRICE;
		SALENUM = sALENUM;
		ADDCOUNT = aDDCOUNT;
		TOTALCOUNT = tOTALCOUNT;
		COLITEMNAME = cOLITEMNAME;
		PROCODE = pROCODE;
		PRONAME = pRONAME;
		AGENCYNAME = aGENCYNAME;
		this.ph = ph;
		this.dj = dj;
		this.cp = cp;
	}

	public String getROUTENAME() {
		return ROUTENAME;
	}

	public void setROUTENAME(String rOUTENAME) {
		ROUTENAME = rOUTENAME;
	}

	public String getVISITDATE() {
		return VISITDATE;
	}

	public void setVISITDATE(String vISITDATE) {
		VISITDATE = vISITDATE;
	}

	public String getVISITUSER() {
		return VISITUSER;
	}

	public void setVISITUSER(String vISITUSER) {
		VISITUSER = vISITUSER;
	}

	public String getISHDISTRIBUTION() {
		return ISHDISTRIBUTION;
	}

	public void setISHDISTRIBUTION(String iSHDISTRIBUTION) {
		ISHDISTRIBUTION = iSHDISTRIBUTION;
	}

	public String getSELFOCCUPANCY() {
		return SELFOCCUPANCY;
	}

	public void setSELFOCCUPANCY(String sELFOCCUPANCY) {
		SELFOCCUPANCY = sELFOCCUPANCY;
	}

	public String getISCMPCOLLAPSE() {
		return ISCMPCOLLAPSE;
	}

	public void setISCMPCOLLAPSE(String iSCMPCOLLAPSE) {
		ISCMPCOLLAPSE = iSCMPCOLLAPSE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getSELFTREATY() {
		return SELFTREATY;
	}

	public void setSELFTREATY(String sELFTREATY) {
		SELFTREATY = sELFTREATY;
	}

	public String getCMPTREATY() {
		return CMPTREATY;
	}

	public void setCMPTREATY(String cMPTREATY) {
		CMPTREATY = cMPTREATY;
	}

	public String getISSELF() {
		return ISSELF;
	}

	public void setISSELF(String iSSELF) {
		ISSELF = iSSELF;
	}

	public String getISCMP() {
		return ISCMP;
	}

	public void setISCMP(String iSCMP) {
		ISCMP = iSCMP;
	}

	public String getEXETREATY() {
		return EXETREATY;
	}

	public void setEXETREATY(String eXETREATY) {
		EXETREATY = eXETREATY;
	}

	public String getTERMINALCODE() {
		return TERMINALCODE;
	}

	public void setTERMINALCODE(String tERMINALCODE) {
		TERMINALCODE = tERMINALCODE;
	}

	public String getTERMINALNAME() {
		return TERMINALNAME;
	}

	public void setTERMINALNAME(String tERMINALNAME) {
		TERMINALNAME = tERMINALNAME;
	}

	public String getTLEVEL() {
		return TLEVEL;
	}

	public void setTLEVEL(String tLEVEL) {
		TLEVEL = tLEVEL;
	}

	public String getCONTACT() {
		return CONTACT;
	}

	public void setCONTACT(String cONTACT) {
		CONTACT = cONTACT;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getMINORCHANNEL() {
		return MINORCHANNEL;
	}

	public void setMINORCHANNEL(String mINORCHANNEL) {
		MINORCHANNEL = mINORCHANNEL;
	}

	public String getPURCPRICE() {
		return PURCPRICE;
	}

	public void setPURCPRICE(String pURCPRICE) {
		PURCPRICE = pURCPRICE;
	}

	public String getRETAILPRICE() {
		return RETAILPRICE;
	}

	public void setRETAILPRICE(String rETAILPRICE) {
		RETAILPRICE = rETAILPRICE;
	}

	public String getSALENUM() {
		return SALENUM;
	}

	public void setSALENUM(String sALENUM) {
		SALENUM = sALENUM;
	}

	public String getADDCOUNT() {
		return ADDCOUNT;
	}

	public void setADDCOUNT(String aDDCOUNT) {
		ADDCOUNT = aDDCOUNT;
	}

	public String getTOTALCOUNT() {
		return TOTALCOUNT;
	}

	public void setTOTALCOUNT(String tOTALCOUNT) {
		TOTALCOUNT = tOTALCOUNT;
	}

	public String getCOLITEMNAME() {
		return COLITEMNAME;
	}

	public void setCOLITEMNAME(String cOLITEMNAME) {
		COLITEMNAME = cOLITEMNAME;
	}

	public String getPROCODE() {
		return PROCODE;
	}

	public void setPROCODE(String pROCODE) {
		PROCODE = pROCODE;
	}

	public String getPRONAME() {
		return PRONAME;
	}

	public void setPRONAME(String pRONAME) {
		PRONAME = pRONAME;
	}

	public String getAGENCYNAME() {
		return AGENCYNAME;
	}

	public void setAGENCYNAME(String aGENCYNAME) {
		AGENCYNAME = aGENCYNAME;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

}
