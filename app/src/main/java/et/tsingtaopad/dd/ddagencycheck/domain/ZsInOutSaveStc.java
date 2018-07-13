package et.tsingtaopad.dd.ddagencycheck.domain;

import et.tsingtaopad.db.table.MstInvoicingInfo;


/**
 * 项目名称：营销移动智能工作平台 </br>
 * 功能描述: 督导经销商盘点结构体</br>
 * 版本 V 1.0</br>               
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
public class ZsInOutSaveStc extends MstInvoicingInfo
{

    private static final long serialVersionUID = -2655448232852193718L;

    private String proname;// 产品名称
    private String procode;// 产品编码
    private String realstore;// 实际库存
    private String des;// 备注


    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProcode() {
        return procode;
    }

    public void setProcode(String procode) {
        this.procode = procode;
    }

    public String getRealstore() {
        return realstore;
    }

    public void setRealstore(String realstore) {
        this.realstore = realstore;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
