package dd.tsingtaopad.main.operation.promotion.domain;

import java.io.Serializable;

/**
 * 项目名称：营销移动智能工作平台 </br> 文件名：PromotionStc.java</br> 作者：@ray </br>
 * 创建时间：2013-12-7</br> 功能描述: 促销活动查询结构体</br> 版本 V 1.0</br> 修改履历</br> 日期 原因 BUG号
 * 修改人 修改版本</br>
 */
public class PromotionStc implements Serializable {

	private static final long serialVersionUID = 1412060996260153459L;
	private String termName;
	private String type;
	private String finishNum;
	private String unfinishNum;
	private String completeterms;
	private String notcomterms;

	public String getCompleteterms() {
		return completeterms;
	}

	public void setCompleteterms(String completeterms) {
		this.completeterms = completeterms;
	}

	public String getNotcomterms() {
		return notcomterms;
	}

	public void setNotcomterms(String notcomterms) {
		this.notcomterms = notcomterms;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(String finishNum) {
		this.finishNum = finishNum;
	}

	public String getUnfinishNum() {
		return unfinishNum;
	}

	public void setUnfinishNum(String unfinishNum) {
		this.unfinishNum = unfinishNum;
	}

	
}
