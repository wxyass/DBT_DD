package dd.tsingtaopad.main.visit.agencyvisit.domain;


import dd.tsingtaopad.db.table.MstAgencytransferInfo;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 调货台账结构体</br>
 */
public class TransferStc extends MstAgencytransferInfo {

    private static final long serialVersionUID = 8214919129390554696L;
    
    private String proName;
    private String tagAgencyName;
    
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getTagAgencyName() {
        return tagAgencyName;
    }
    public void setTagAgencyName(String tagAgencyName) {
        this.tagAgencyName = tagAgencyName;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
