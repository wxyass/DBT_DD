package et.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import et.tsingtaopad.db.dao.impl.MitMorningmeetingMDaoImpl;
import et.tsingtaopad.db.dao.impl.MitValaddaccountMDaoImpl;


/**
 * 晨会表
 * MitValterM entity. @author MyEclipse Persistence Tools
 */
@DatabaseTable(tableName = "MIT_MORNINGMEETING_M", daoClass = MitMorningmeetingMDaoImpl.class)
public class MitMorningmeetingM implements java.io.Serializable {
    // Fields
    @DatabaseField(canBeNull = false, id = true)
    private String id;//

    @DatabaseField
    private String valsupplyid;// 终端追溯终端表ID

    @DatabaseField
    private String  areaid ;// 督导所在大区
    @DatabaseField
    private String compere ;// 主持人
    @DatabaseField
    private String participant ;//参会人
    @DatabaseField
    private String meetingrecord ;// 会议记录
    @DatabaseField
    private String startdate   ;// 追溯开始时间
    @DatabaseField
    private String enddate ;// 追溯结束时间
    @DatabaseField
    private String credate;// 创建日期
    @DatabaseField
    private String updateuser  ;// 更新人
    @DatabaseField
    private String updatedate  ;// 更新时间
    @DatabaseField
    private String creuser ;// 创建人
    @DatabaseField
    private String uploadflag  ;// 0:该条记录不上传  1:该条记录需要上传
    @DatabaseField
    private String padisconsistent ;// 0:还未上传  1:已上传
    @DatabaseField
    private String currentdate  ;// 当前时间(只有年月日)


    /**
     * default constructor
     */
    public MitMorningmeetingM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValsupplyid() {
        return valsupplyid;
    }

    public void setValsupplyid(String valsupplyid) {
        this.valsupplyid = valsupplyid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getMeetingrecord() {
        return meetingrecord;
    }

    public void setMeetingrecord(String meetingrecord) {
        this.meetingrecord = meetingrecord;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public String getUploadflag() {
        return uploadflag;
    }

    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    public String getPadisconsistent() {
        return padisconsistent;
    }

    public void setPadisconsistent(String padisconsistent) {
        this.padisconsistent = padisconsistent;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }
}