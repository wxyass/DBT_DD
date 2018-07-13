package et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/6.
 * 功能描述: 查指标 -- 分项采集部分 -- 查询结果对应的结构体</br>
 */
public class CheckIndexCalculateStc implements Serializable {

    private static final long serialVersionUID = 7309324791514462417L;
    
    // 拜访指标执行记录表主键( 拉链表主键 )
    private String recordId;
    
    // 拜访主键
    private String visitId;
    
    // 产品ID (与产品无关指标采集数据未用的此字段)
    private String proId;
    
    // 产品名称 (与产品无关指标采集数据未用的此字段)
    private String proName;
    
    // 指标ID (如:铺货状态,冰冻化,是否高质量配送 对应的ID)
    private String indexId;
    
    // 指标类型 (0:计算单选、 1:单选、 2:文本框、 3:数值、 4:下拉单选)
    private String indexType;
    
    // 指标名称 (如:铺货状态,冰冻化,是否高质量配送)
    private String indexName;
    
    // 指标值ID  (指标的指标值,如 指标:是否高质量配送,指标值:是 对应的ID)
    private String indexValueId;
    
    // 指标值名称 (指标的指标值,如 指标:是否高质量配送,指标值:是)
    private String indexValueName;

    // 终端追溯查指标表主键
    private String id;
    // 终端追溯 指标正确与否
    private String valchecktypeflag;
    // 终端追溯 督导指标值
    private String ddacresult;
    // 终端追溯 督导指标备注
    private String ddremark;


    /**
	 * 
	 */
	public CheckIndexCalculateStc() {
		super();
	}

	/**
	 * @param recordId
	 * @param visitId
	 * @param indexId
	 * @param indexType
	 * @param indexName
	 * @param indexValueId
	 */
	public CheckIndexCalculateStc(String recordId, String visitId,
			String indexId, String indexType, String indexName,
			String indexValueId) {
		super();
		this.recordId = recordId;
		this.visitId = visitId;
		this.indexId = indexId;
		this.indexType = indexType;
		this.indexName = indexName;
		this.indexValueId = indexValueId;
	}
	

	/**
	 * @param indexId
	 * @param indexType
	 * @param indexName
	 * @param indexValueId
	 */
	public CheckIndexCalculateStc(String indexId, String indexType,
			String indexName, String indexValueId) {
		super();
		this.indexId = indexId;
		this.indexType = indexType;
		this.indexName = indexName;
		this.indexValueId = indexValueId;
	}

	public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexValueId() {
        return indexValueId;
    }

    public void setIndexValueId(String indexValueId) {
        this.indexValueId = indexValueId;
    }

    public String getIndexValueName() {
        return indexValueName;
    }

    public void setIndexValueName(String indexValueName) {
        this.indexValueName = indexValueName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValchecktypeflag() {
        return valchecktypeflag;
    }

    public void setValchecktypeflag(String valchecktypeflag) {
        this.valchecktypeflag = valchecktypeflag;
    }

    public String getDdacresult() {
        return ddacresult;
    }

    public void setDdacresult(String ddacresult) {
        this.ddacresult = ddacresult;
    }

    public String getDdremark() {
        return ddremark;
    }

    public void setDdremark(String ddremark) {
        this.ddremark = ddremark;
    }
}
