package et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain;


import et.tsingtaopad.db.table.PadCheckaccomplishInfo;

/**
 * Created by yangwenmin on 2017/12/6.
 * 功能描述: 查指标 -- 快速采集 -- 数据源结构体</br>
 */
public class CheckIndexQuicklyStc extends PadCheckaccomplishInfo {

    private static final long serialVersionUID = -5728731700044085810L;
    
    //拜访指标执行采集项记录表主键
    private String colRecordId;
    
    // 产品名称
    private String proName;
    
    // 采集的变化量
    private Double changeNum;
    
    // 采集的最终量
    private Double finalNum;
    
    // 采集的新鲜度
    private String freshness;
    
    // 采集的变化量
    private String bianhualiang;
    
    // 采集的现有量
    private String xianyouliang;

    // 追溯采集项表主键
    private String id;
    // 追溯拉链表主键
    private String valchecktypeid;
    // 采集项原值结果量
    private String valitem;
    // 采集项正确值结果量
    private String valitemval;
    // 采集项备注
    private String valitemremark;


    public String getColRecordId() {
        return colRecordId;
    }

    public void setColRecordId(String colRecordId) {
        this.colRecordId = colRecordId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Double changeNum) {
        this.changeNum = changeNum;
    }

    public Double getFinalNum() {
        return finalNum;
    }

    public void setFinalNum(Double finalNum) {
        this.finalNum = finalNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	/**
	 * @return the freshness
	 */
	public String getFreshness() {
		return freshness;
	}

	/**
	 * @param freshness the freshness to set
	 */
	public void setFreshness(String freshness) {
		this.freshness = freshness;
	}

	/**
	 * @return the bianhualiang
	 */
	public String getBianhualiang() {
		return bianhualiang;
	}

	/**
	 * @param bianhualiang the bianhualiang to set
	 */
	public void setBianhualiang(String bianhualiang) {
		this.bianhualiang = bianhualiang;
	}

	/**
	 * @return the xianyouliang
	 */
	public String getXianyouliang() {
		return xianyouliang;
	}

	/**
	 * @param xianyouliang the xianyouliang to set
	 */
	public void setXianyouliang(String xianyouliang) {
		this.xianyouliang = xianyouliang;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValitem() {
        return valitem;
    }

    public void setValitem(String valitem) {
        this.valitem = valitem;
    }

    public String getValitemval() {
        return valitemval;
    }

    public void setValitemval(String valitemval) {
        this.valitemval = valitemval;
    }

    public String getValitemremark() {
        return valitemremark;
    }

    public void setValitemremark(String valitemremark) {
        this.valitemremark = valitemremark;
    }

    public String getValchecktypeid() {
        return valchecktypeid;
    }

    public void setValchecktypeid(String valchecktypeid) {
        this.valchecktypeid = valchecktypeid;
    }
}
