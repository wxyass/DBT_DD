package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/25.
 * 功能描述: 同步记录表(记录每张表的更新情况的KV表)
 */
@DatabaseTable(tableName = "MST_SYNCKV_M")
public class MstSynckvM implements Serializable {

	private static final long serialVersionUID = 1L;

	// 表名
	@DatabaseField(canBeNull = false, id = true)
	private String tablename;  // 比如: CMM_BOARD_M

	// 表中最新数据的updatetime，格式：yyyy-MM-dd HH:mm:ss
	@DatabaseField
	private String updatetime;
	// 表最新一次单击更新按钮并成功更新的时间,格式：yyyy-MM-dd HH:mm:ss
	@DatabaseField
	private String synctime;
	// 要更新的数据时间埃范围(天)
	@DatabaseField
	private String syncDay;
	@DatabaseField
	private String remarks;

	@DatabaseField
	private String tableDesc;
	/**
	 * 记录同步失败次数
	 */
	private int  synFailtimes=0;
	
	public MstSynckvM() {
	}

	public MstSynckvM(String tablename, String updatetime, String synctime, String syncDay, String remarks) {
		super();
		this.tablename = tablename;
		this.updatetime = updatetime;
		this.synctime = synctime;
		this.syncDay = syncDay;
		this.remarks = remarks;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getSynctime() {
		return synctime;
	}

	public void setSynctime(String synctime) {
		this.synctime = synctime;
	}

	public String getSyncDay() {
		return syncDay;
	}

	public void setSyncDay(String syncDay) {
		this.syncDay = syncDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    public int getSynFailtimes() {
        return synFailtimes;
    }

    public void setSynFailtimes(int synFailtimes) {
        this.synFailtimes = synFailtimes;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }
	
    
	
}
