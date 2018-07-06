package dd.tsingtaopad.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import dd.tsingtaopad.db.dao.impl.MitValterMTempDaoImpl;


/**
 * MitValterMTemp entity. @author MyEclipse Persistence Tools
 */
//MitValterMTemp(追溯主表临时表)
@DatabaseTable(tableName = "MIT_VALTER_M_TEMP", daoClass = MitValterMTempDaoImpl.class)
public class MitValterMTemp implements java.io.Serializable {

	// Fields
	@DatabaseField(canBeNull = false, id = true)
	private String id;
	@DatabaseField
	private String padisconsistent;// 是否已上传  0:未上传 1:已上传
	@DatabaseField
	private String visitdate;
	@DatabaseField
	private String terminalkey;//
	@DatabaseField
	private String vidter               ;//char(1)                        null,
	@DatabaseField
	private String vidterflag           ;//char(1)                        null,
	@DatabaseField
	private String vidterremaek         ;//varchar2(300)                  null,
	@DatabaseField
	private String vidvisit             ;//char(1)                        null,
	@DatabaseField
	private String vidvisitflag        ;// char(1)                        null,
	@DatabaseField
	private String vidvisitremark       ;//varchar2(300)                  null,
	@DatabaseField
	private String vidifmine            ;//char(1)                        null,
	@DatabaseField
	private String vidifmineflag        ;//char(1)                        null,
	@DatabaseField
	private String vidifminermark       ;//varchar2(300)                  null,
	@DatabaseField
	private String vidifminedate        ;//varchar2(20)                   null,
	@DatabaseField
	private String vidifminedateflag    ;//char(1)                        null,
	@DatabaseField
	private String vidifminedatermark   ;//varchar2(300)                  null,
	@DatabaseField
	private String vidisself            ;//char(1)                        null,
	@DatabaseField
	private String vidisselfflag        ;//char(1)                        null,
	@DatabaseField
	private String vidiscmp            ;// char(1)                        null,
	@DatabaseField
	private String vidiscmpflag         ;//char(1)                        null,
	@DatabaseField
	private String vidisproremark       ;//varchar2(300)                  null,
	@DatabaseField
	private String vidselftreaty        ;//char(1)                        null,
	@DatabaseField
	private String vidselftreatyflag    ;//char(1)                        null,
	@DatabaseField
	private String vidcmptreaty         ;//char(1)                        null,
	@DatabaseField
	private String vidcmptreatyflag     ;//char(1)                        null,
	@DatabaseField
	private String vidterminalcode      ;//varchar2(50)                   null,
	@DatabaseField
	private String vidtercodeflag       ;//char(1)                        null,
	@DatabaseField
	private String vidtercodeval        ;//varchar2(50)                   null,
	@DatabaseField
	private String vidtercoderemark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidroutekey          ;//varchar2(50)                   null,
	@DatabaseField
	private String vidrtekeyflag        ;//char(1)                        null,
	@DatabaseField
	private String vidrtekeyval         ;//varchar2(50)                   null,
	@DatabaseField
	private String vidroutremark        ;//varchar2(300)                  null,
	@DatabaseField
	private String vidtername           ;//varchar2(100)                  null,
	@DatabaseField
	private String vidternameflag       ;//char(1)                        null,
	@DatabaseField
	private String vidternameval        ;//varchar2(100)                  null,
	@DatabaseField
	private String vidternameremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidcountry           ;//varchar2(20)                   null,
	@DatabaseField
	private String vidcountryflag       ;//char(1)                        null,
	@DatabaseField
	private String vidcountryval       ;// varchar2(20)                   null,
	@DatabaseField
	private String vidcountryremark    ;// varchar2(300)                  null,
	@DatabaseField
	private String vidaddress          ;// varchar2(150)                  null,
	@DatabaseField
	private String vidaddressflag       ;//char(1)                        null,
	@DatabaseField
	private String vidaddressval        ;//varchar2(20)                   null,
	@DatabaseField
	private String vidaddressremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidcontact           ;//varchar2(150)                  null,
	@DatabaseField
	private String vidcontactflag       ;//char(1)                        null,
	@DatabaseField
	private String vidcontactval        ;//varchar2(20)                   null,
	@DatabaseField
	private String vidcontactremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidmobile            ;//varchar2(150)                  null,
	@DatabaseField
	private String vidmobileflag        ;//char(1)                        null,
	@DatabaseField
	private String vidmobileval         ;//varchar2(50)                   null,
	@DatabaseField
	private String vidmobileremark      ;//varchar2(300)                  null,
	@DatabaseField
	private String vidsequence          ;//varchar2(150)                  null,
	@DatabaseField
	private String vidsequenceflag      ;//char(1)                        null,
	@DatabaseField
	private String vidsequenceval       ;//varchar2(10)                   null,
	@DatabaseField
	private String vidvidsequenceremark ;//varchar2(300)                  null,
	@DatabaseField
	private String vidcycle             ;//varchar2(150)                  null,
	@DatabaseField
	private String vidcycleflag         ;//char(1)                        null,
	@DatabaseField
	private String vidcycleval          ;//varchar2(10)                   null,
	@DatabaseField
	private String vidcycleremark2      ;//varchar2(300)                  null,
	@DatabaseField
	private String vidareatype          ;//varchar(36)                    null,
	@DatabaseField
	private String vidareatypeflag      ;//char(1)                        null,
	@DatabaseField
	private String vidareatypeval       ;//varchar(36)                    null,
	@DatabaseField
	private String vidareatyperemark    ;//varchar2(300)                  null,
	@DatabaseField
	private String vidhvolume           ;//varchar(36)                    null,
	@DatabaseField
	private String vidhvolumeflag      ;// char(1)                        null,
	@DatabaseField
	private String vidhvolumeval        ;//varchar(36)                    null,
	@DatabaseField
	private String vidhvolumeremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidzvolume           ;//varchar(36)                    null,
	@DatabaseField
	private String vidzvolumeflag       ;//char(1)                        null,
	@DatabaseField
	private String vidzvolumeval        ;//varchar(36)                    null,
	@DatabaseField
	private String vidxvolumeremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidpvolume           ;//varchar(36)                    null,
	@DatabaseField
	private String vidpvolumeflag       ;//char(1)                        null,
	@DatabaseField
	private String vidpvolumeval        ;//varchar(36)                    null,
	@DatabaseField
	private String vidpvolumeremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidlvolume           ;//varchar(36)                    null,
	@DatabaseField
	private String vidlvolumeflag       ;//char(1)                        null,
	@DatabaseField
	private String vidlvolumeval        ;//varchar(36)                    null,
	@DatabaseField
	private String vidlvolumeremark     ;//varchar2(300)                  null,
	@DatabaseField
	private String vidminchannel        ;//varchar(36)                    null,
	@DatabaseField
	private String vidminchannelflag    ;//char(1)                        null,
	@DatabaseField
	private String vidminchannelval     ;//varchar(36)                    null,
	@DatabaseField
	private String vidminchannelremark  ;//varchar2(300)                  null,
	@DatabaseField
	private String vidvisituser         ;//varchar2(100)                  null,
	@DatabaseField
	private String vidvisituserflag     ;//char(1)                        null,
	@DatabaseField
	private String vidvisituserval      ;//varchar2(100)                  null,
	@DatabaseField
	private String vidvisituserremark   ;//varchar2(300)                  null,
	@DatabaseField
	private String creuser              ;//varchar2(128)                  null,
	@DatabaseField
	private Date credate              ;//date                           null,
	@DatabaseField
	private String updateuser           ;//varchar2(128)                  null,
	@DatabaseField
	private Date updatedate           ;//date                           null

	@DatabaseField
	private String vidterlevel ;//varchar2(100) null,
	@DatabaseField
	private String vidtervidterlevelflag ;//char(1) null,
	@DatabaseField
	private String vidtervidterlevelval ;//varchar2(100) null,
	@DatabaseField
	private String vidtervidterlevelremark ;//varchar2(300),

	@DatabaseField
	private String vidvisitotherval;

	@DatabaseField
	private String vidvisitottrueval;

	@DatabaseField
	private String vidisselfremark;//销售产品范围我品备注内容
	@DatabaseField
	private String vidiscmpremark;//销售产品范围竞品备注内容
	@DatabaseField
	private String vidselftreatyremark;//终端合作状态我品备注内容
	@DatabaseField
	private String vidcmptreatyremark;//终端合作状态竞品备注内容

	@DatabaseField
	private String vidstartdate ;//拜访开始时间
	@DatabaseField
	private String videnddate;//拜访结束时间
	@DatabaseField
	private String uploaddate;//服务器上传时间

	@DatabaseField
	private String lon;//经度
	@DatabaseField
	private String lat;//维度

	// Constructors

	/** default constructor */
	public MitValterMTemp() {
	}

	public MitValterMTemp(String id, String terminalkey, String vidter, String vidterflag, String vidterremaek, String vidvisit, String vidvisitflag, String vidvisitremark, String vidifmine, String vidifmineflag, String vidifminermark, String vidifminedate, String vidifminedateflag, String vidifminedatermark, String vidisself, String vidisselfflag, String vidiscmp, String vidiscmpflag, String vidisproremark, String vidselftreaty, String vidselftreatyflag, String vidcmptreaty, String vidcmptreatyflag, String vidcmptreatyremark, String vidterminalcode, String vidtercodeflag, String vidtercodeval, String vidtercoderemark, String vidroutekey, String vidrtekeyflag, String vidrtekeyval, String vidroutremark, String vidtername, String vidternameflag, String vidternameval, String vidternameremark, String vidcountry, String vidcountryflag, String vidcountryval, String vidcountryremark, String vidaddress, String vidaddressflag, String vidaddressval, String vidaddressremark, String vidcontact, String vidcontactflag, String vidcontactval, String vidcontactremark, String vidmobile, String vidmobileflag, String vidmobileval, String vidmobileremark, String vidsequence, String vidsequenceflag, String vidsequenceval, String vidvidsequenceremark, String vidcycle, String vidcycleflag, String vidcycleval, String vidcycleremark2, String vidareatype, String vidareatypeflag, String vidareatypeval, String vidareatyperemark, String vidhvolume, String vidhvolumeflag, String vidhvolumeval, String vidhvolumeremark, String vidzvolume, String vidzvolumeflag, String vidzvolumeval, String vidxvolumeremark, String vidpvolume, String vidpvolumeflag, String vidpvolumeval, String vidpvolumeremark, String vidlvolume, String vidlvolumeflag, String vidlvolumeval, String vidlvolumeremark, String vidminchannel, String vidminchannelflag, String vidminchannelval, String vidminchannelremark, String vidvisituser, String vidvisituserflag, String vidvisituserval, String vidvisituserremark, String creuser, Date credate, String updateuser, Date updatedate) {
		this.id = id;
		this.terminalkey = terminalkey;
		this.vidter = vidter;
		this.vidterflag = vidterflag;
		this.vidterremaek = vidterremaek;
		this.vidvisit = vidvisit;
		this.vidvisitflag = vidvisitflag;
		this.vidvisitremark = vidvisitremark;
		this.vidifmine = vidifmine;
		this.vidifmineflag = vidifmineflag;
		this.vidifminermark = vidifminermark;
		this.vidifminedate = vidifminedate;
		this.vidifminedateflag = vidifminedateflag;
		this.vidifminedatermark = vidifminedatermark;
		this.vidisself = vidisself;
		this.vidisselfflag = vidisselfflag;
		this.vidiscmp = vidiscmp;
		this.vidiscmpflag = vidiscmpflag;
		this.vidisproremark = vidisproremark;
		this.vidselftreaty = vidselftreaty;
		this.vidselftreatyflag = vidselftreatyflag;
		this.vidcmptreaty = vidcmptreaty;
		this.vidcmptreatyflag = vidcmptreatyflag;
		this.vidcmptreatyremark = vidcmptreatyremark;
		this.vidterminalcode = vidterminalcode;
		this.vidtercodeflag = vidtercodeflag;
		this.vidtercodeval = vidtercodeval;
		this.vidtercoderemark = vidtercoderemark;
		this.vidroutekey = vidroutekey;
		this.vidrtekeyflag = vidrtekeyflag;
		this.vidrtekeyval = vidrtekeyval;
		this.vidroutremark = vidroutremark;
		this.vidtername = vidtername;
		this.vidternameflag = vidternameflag;
		this.vidternameval = vidternameval;
		this.vidternameremark = vidternameremark;
		this.vidcountry = vidcountry;
		this.vidcountryflag = vidcountryflag;
		this.vidcountryval = vidcountryval;
		this.vidcountryremark = vidcountryremark;
		this.vidaddress = vidaddress;
		this.vidaddressflag = vidaddressflag;
		this.vidaddressval = vidaddressval;
		this.vidaddressremark = vidaddressremark;
		this.vidcontact = vidcontact;
		this.vidcontactflag = vidcontactflag;
		this.vidcontactval = vidcontactval;
		this.vidcontactremark = vidcontactremark;
		this.vidmobile = vidmobile;
		this.vidmobileflag = vidmobileflag;
		this.vidmobileval = vidmobileval;
		this.vidmobileremark = vidmobileremark;
		this.vidsequence = vidsequence;
		this.vidsequenceflag = vidsequenceflag;
		this.vidsequenceval = vidsequenceval;
		this.vidvidsequenceremark = vidvidsequenceremark;
		this.vidcycle = vidcycle;
		this.vidcycleflag = vidcycleflag;
		this.vidcycleval = vidcycleval;
		this.vidcycleremark2 = vidcycleremark2;
		this.vidareatype = vidareatype;
		this.vidareatypeflag = vidareatypeflag;
		this.vidareatypeval = vidareatypeval;
		this.vidareatyperemark = vidareatyperemark;
		this.vidhvolume = vidhvolume;
		this.vidhvolumeflag = vidhvolumeflag;
		this.vidhvolumeval = vidhvolumeval;
		this.vidhvolumeremark = vidhvolumeremark;
		this.vidzvolume = vidzvolume;
		this.vidzvolumeflag = vidzvolumeflag;
		this.vidzvolumeval = vidzvolumeval;
		this.vidxvolumeremark = vidxvolumeremark;
		this.vidpvolume = vidpvolume;
		this.vidpvolumeflag = vidpvolumeflag;
		this.vidpvolumeval = vidpvolumeval;
		this.vidpvolumeremark = vidpvolumeremark;
		this.vidlvolume = vidlvolume;
		this.vidlvolumeflag = vidlvolumeflag;
		this.vidlvolumeval = vidlvolumeval;
		this.vidlvolumeremark = vidlvolumeremark;
		this.vidminchannel = vidminchannel;
		this.vidminchannelflag = vidminchannelflag;
		this.vidminchannelval = vidminchannelval;
		this.vidminchannelremark = vidminchannelremark;
		this.vidvisituser = vidvisituser;
		this.vidvisituserflag = vidvisituserflag;
		this.vidvisituserval = vidvisituserval;
		this.vidvisituserremark = vidvisituserremark;
		this.creuser = creuser;
		this.credate = credate;
		this.updateuser = updateuser;
		this.updatedate = updatedate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTerminalkey() {
		return terminalkey;
	}

	public void setTerminalkey(String terminalkey) {
		this.terminalkey = terminalkey;
	}

	public String getVidter() {
		return vidter;
	}

	public void setVidter(String vidter) {
		this.vidter = vidter;
	}

	public String getVidterflag() {
		return vidterflag;
	}

	public void setVidterflag(String vidterflag) {
		this.vidterflag = vidterflag;
	}

	public String getVidterremaek() {
		return vidterremaek;
	}

	public void setVidterremaek(String vidterremaek) {
		this.vidterremaek = vidterremaek;
	}

	public String getVidvisit() {
		return vidvisit;
	}

	public void setVidvisit(String vidvisit) {
		this.vidvisit = vidvisit;
	}

	public String getVidvisitflag() {
		return vidvisitflag;
	}

	public void setVidvisitflag(String vidvisitflag) {
		this.vidvisitflag = vidvisitflag;
	}

	public String getVidvisitremark() {
		return vidvisitremark;
	}

	public void setVidvisitremark(String vidvisitremark) {
		this.vidvisitremark = vidvisitremark;
	}

	public String getVidifmine() {
		return vidifmine;
	}

	public void setVidifmine(String vidifmine) {
		this.vidifmine = vidifmine;
	}

	public String getVidifmineflag() {
		return vidifmineflag;
	}

	public void setVidifmineflag(String vidifmineflag) {
		this.vidifmineflag = vidifmineflag;
	}

	public String getVidifminermark() {
		return vidifminermark;
	}

	public void setVidifminermark(String vidifminermark) {
		this.vidifminermark = vidifminermark;
	}

	public String getVidifminedate() {
		return vidifminedate;
	}

	public void setVidifminedate(String vidifminedate) {
		this.vidifminedate = vidifminedate;
	}

	public String getVidifminedateflag() {
		return vidifminedateflag;
	}

	public void setVidifminedateflag(String vidifminedateflag) {
		this.vidifminedateflag = vidifminedateflag;
	}

	public String getVidifminedatermark() {
		return vidifminedatermark;
	}

	public void setVidifminedatermark(String vidifminedatermark) {
		this.vidifminedatermark = vidifminedatermark;
	}

	public String getVidisself() {
		return vidisself;
	}

	public void setVidisself(String vidisself) {
		this.vidisself = vidisself;
	}

	public String getVidisselfflag() {
		return vidisselfflag;
	}

	public void setVidisselfflag(String vidisselfflag) {
		this.vidisselfflag = vidisselfflag;
	}

	public String getVidiscmp() {
		return vidiscmp;
	}

	public void setVidiscmp(String vidiscmp) {
		this.vidiscmp = vidiscmp;
	}

	public String getVidiscmpflag() {
		return vidiscmpflag;
	}

	public void setVidiscmpflag(String vidiscmpflag) {
		this.vidiscmpflag = vidiscmpflag;
	}

	public String getVidisproremark() {
		return vidisproremark;
	}

	public void setVidisproremark(String vidisproremark) {
		this.vidisproremark = vidisproremark;
	}

	public String getVidselftreaty() {
		return vidselftreaty;
	}

	public void setVidselftreaty(String vidselftreaty) {
		this.vidselftreaty = vidselftreaty;
	}

	public String getVidselftreatyflag() {
		return vidselftreatyflag;
	}

	public void setVidselftreatyflag(String vidselftreatyflag) {
		this.vidselftreatyflag = vidselftreatyflag;
	}

	public String getVidcmptreaty() {
		return vidcmptreaty;
	}

	public void setVidcmptreaty(String vidcmptreaty) {
		this.vidcmptreaty = vidcmptreaty;
	}

	public String getVidcmptreatyflag() {
		return vidcmptreatyflag;
	}

	public void setVidcmptreatyflag(String vidcmptreatyflag) {
		this.vidcmptreatyflag = vidcmptreatyflag;
	}

	public String getVidcmptreatyremark() {
		return vidcmptreatyremark;
	}

	public void setVidcmptreatyremark(String vidcmptreatyremark) {
		this.vidcmptreatyremark = vidcmptreatyremark;
	}

	public String getVidterminalcode() {
		return vidterminalcode;
	}

	public void setVidterminalcode(String vidterminalcode) {
		this.vidterminalcode = vidterminalcode;
	}

	public String getVidtercodeflag() {
		return vidtercodeflag;
	}

	public void setVidtercodeflag(String vidtercodeflag) {
		this.vidtercodeflag = vidtercodeflag;
	}

	public String getVidtercodeval() {
		return vidtercodeval;
	}

	public void setVidtercodeval(String vidtercodeval) {
		this.vidtercodeval = vidtercodeval;
	}

	public String getVidtercoderemark() {
		return vidtercoderemark;
	}

	public void setVidtercoderemark(String vidtercoderemark) {
		this.vidtercoderemark = vidtercoderemark;
	}

	public String getVidroutekey() {
		return vidroutekey;
	}

	public void setVidroutekey(String vidroutekey) {
		this.vidroutekey = vidroutekey;
	}

	public String getVidrtekeyflag() {
		return vidrtekeyflag;
	}

	public void setVidrtekeyflag(String vidrtekeyflag) {
		this.vidrtekeyflag = vidrtekeyflag;
	}

	public String getVidrtekeyval() {
		return vidrtekeyval;
	}

	public void setVidrtekeyval(String vidrtekeyval) {
		this.vidrtekeyval = vidrtekeyval;
	}

	public String getVidroutremark() {
		return vidroutremark;
	}

	public void setVidroutremark(String vidroutremark) {
		this.vidroutremark = vidroutremark;
	}

	public String getVidtername() {
		return vidtername;
	}

	public void setVidtername(String vidtername) {
		this.vidtername = vidtername;
	}

	public String getVidternameflag() {
		return vidternameflag;
	}

	public void setVidternameflag(String vidternameflag) {
		this.vidternameflag = vidternameflag;
	}

	public String getVidternameval() {
		return vidternameval;
	}

	public void setVidternameval(String vidternameval) {
		this.vidternameval = vidternameval;
	}

	public String getVidternameremark() {
		return vidternameremark;
	}

	public void setVidternameremark(String vidternameremark) {
		this.vidternameremark = vidternameremark;
	}

	public String getVidcountry() {
		return vidcountry;
	}

	public void setVidcountry(String vidcountry) {
		this.vidcountry = vidcountry;
	}

	public String getVidcountryflag() {
		return vidcountryflag;
	}

	public void setVidcountryflag(String vidcountryflag) {
		this.vidcountryflag = vidcountryflag;
	}

	public String getVidcountryval() {
		return vidcountryval;
	}

	public void setVidcountryval(String vidcountryval) {
		this.vidcountryval = vidcountryval;
	}

	public String getVidcountryremark() {
		return vidcountryremark;
	}

	public void setVidcountryremark(String vidcountryremark) {
		this.vidcountryremark = vidcountryremark;
	}

	public String getVidaddress() {
		return vidaddress;
	}

	public void setVidaddress(String vidaddress) {
		this.vidaddress = vidaddress;
	}

	public String getVidaddressflag() {
		return vidaddressflag;
	}

	public void setVidaddressflag(String vidaddressflag) {
		this.vidaddressflag = vidaddressflag;
	}

	public String getVidaddressval() {
		return vidaddressval;
	}

	public void setVidaddressval(String vidaddressval) {
		this.vidaddressval = vidaddressval;
	}

	public String getVidaddressremark() {
		return vidaddressremark;
	}

	public void setVidaddressremark(String vidaddressremark) {
		this.vidaddressremark = vidaddressremark;
	}

	public String getVidcontact() {
		return vidcontact;
	}

	public void setVidcontact(String vidcontact) {
		this.vidcontact = vidcontact;
	}

	public String getVidcontactflag() {
		return vidcontactflag;
	}

	public void setVidcontactflag(String vidcontactflag) {
		this.vidcontactflag = vidcontactflag;
	}

	public String getVidcontactval() {
		return vidcontactval;
	}

	public void setVidcontactval(String vidcontactval) {
		this.vidcontactval = vidcontactval;
	}

	public String getVidcontactremark() {
		return vidcontactremark;
	}

	public void setVidcontactremark(String vidcontactremark) {
		this.vidcontactremark = vidcontactremark;
	}

	public String getVidmobile() {
		return vidmobile;
	}

	public void setVidmobile(String vidmobile) {
		this.vidmobile = vidmobile;
	}

	public String getVidmobileflag() {
		return vidmobileflag;
	}

	public void setVidmobileflag(String vidmobileflag) {
		this.vidmobileflag = vidmobileflag;
	}

	public String getVidmobileval() {
		return vidmobileval;
	}

	public void setVidmobileval(String vidmobileval) {
		this.vidmobileval = vidmobileval;
	}

	public String getVidmobileremark() {
		return vidmobileremark;
	}

	public void setVidmobileremark(String vidmobileremark) {
		this.vidmobileremark = vidmobileremark;
	}

	public String getVidsequence() {
		return vidsequence;
	}

	public void setVidsequence(String vidsequence) {
		this.vidsequence = vidsequence;
	}

	public String getVidsequenceflag() {
		return vidsequenceflag;
	}

	public void setVidsequenceflag(String vidsequenceflag) {
		this.vidsequenceflag = vidsequenceflag;
	}

	public String getVidsequenceval() {
		return vidsequenceval;
	}

	public void setVidsequenceval(String vidsequenceval) {
		this.vidsequenceval = vidsequenceval;
	}

	public String getVidvidsequenceremark() {
		return vidvidsequenceremark;
	}

	public void setVidvidsequenceremark(String vidvidsequenceremark) {
		this.vidvidsequenceremark = vidvidsequenceremark;
	}

	public String getVidcycle() {
		return vidcycle;
	}

	public void setVidcycle(String vidcycle) {
		this.vidcycle = vidcycle;
	}

	public String getVidcycleflag() {
		return vidcycleflag;
	}

	public void setVidcycleflag(String vidcycleflag) {
		this.vidcycleflag = vidcycleflag;
	}

	public String getVidcycleval() {
		return vidcycleval;
	}

	public void setVidcycleval(String vidcycleval) {
		this.vidcycleval = vidcycleval;
	}

	public String getVidcycleremark2() {
		return vidcycleremark2;
	}

	public void setVidcycleremark2(String vidcycleremark2) {
		this.vidcycleremark2 = vidcycleremark2;
	}

	public String getVidareatype() {
		return vidareatype;
	}

	public void setVidareatype(String vidareatype) {
		this.vidareatype = vidareatype;
	}

	public String getVidareatypeflag() {
		return vidareatypeflag;
	}

	public void setVidareatypeflag(String vidareatypeflag) {
		this.vidareatypeflag = vidareatypeflag;
	}

	public String getVidareatypeval() {
		return vidareatypeval;
	}

	public void setVidareatypeval(String vidareatypeval) {
		this.vidareatypeval = vidareatypeval;
	}

	public String getVidareatyperemark() {
		return vidareatyperemark;
	}

	public void setVidareatyperemark(String vidareatyperemark) {
		this.vidareatyperemark = vidareatyperemark;
	}

	public String getVidhvolume() {
		return vidhvolume;
	}

	public void setVidhvolume(String vidhvolume) {
		this.vidhvolume = vidhvolume;
	}

	public String getVidhvolumeflag() {
		return vidhvolumeflag;
	}

	public void setVidhvolumeflag(String vidhvolumeflag) {
		this.vidhvolumeflag = vidhvolumeflag;
	}

	public String getVidhvolumeval() {
		return vidhvolumeval;
	}

	public void setVidhvolumeval(String vidhvolumeval) {
		this.vidhvolumeval = vidhvolumeval;
	}

	public String getVidhvolumeremark() {
		return vidhvolumeremark;
	}

	public void setVidhvolumeremark(String vidhvolumeremark) {
		this.vidhvolumeremark = vidhvolumeremark;
	}

	public String getVidzvolume() {
		return vidzvolume;
	}

	public void setVidzvolume(String vidzvolume) {
		this.vidzvolume = vidzvolume;
	}

	public String getVidzvolumeflag() {
		return vidzvolumeflag;
	}

	public void setVidzvolumeflag(String vidzvolumeflag) {
		this.vidzvolumeflag = vidzvolumeflag;
	}

	public String getVidzvolumeval() {
		return vidzvolumeval;
	}

	public void setVidzvolumeval(String vidzvolumeval) {
		this.vidzvolumeval = vidzvolumeval;
	}

	public String getVidxvolumeremark() {
		return vidxvolumeremark;
	}

	public void setVidxvolumeremark(String vidxvolumeremark) {
		this.vidxvolumeremark = vidxvolumeremark;
	}

	public String getVidpvolume() {
		return vidpvolume;
	}

	public void setVidpvolume(String vidpvolume) {
		this.vidpvolume = vidpvolume;
	}

	public String getVidpvolumeflag() {
		return vidpvolumeflag;
	}

	public void setVidpvolumeflag(String vidpvolumeflag) {
		this.vidpvolumeflag = vidpvolumeflag;
	}

	public String getVidpvolumeval() {
		return vidpvolumeval;
	}

	public void setVidpvolumeval(String vidpvolumeval) {
		this.vidpvolumeval = vidpvolumeval;
	}

	public String getVidpvolumeremark() {
		return vidpvolumeremark;
	}

	public void setVidpvolumeremark(String vidpvolumeremark) {
		this.vidpvolumeremark = vidpvolumeremark;
	}

	public String getVidlvolume() {
		return vidlvolume;
	}

	public void setVidlvolume(String vidlvolume) {
		this.vidlvolume = vidlvolume;
	}

	public String getVidlvolumeflag() {
		return vidlvolumeflag;
	}

	public void setVidlvolumeflag(String vidlvolumeflag) {
		this.vidlvolumeflag = vidlvolumeflag;
	}

	public String getVidlvolumeval() {
		return vidlvolumeval;
	}

	public void setVidlvolumeval(String vidlvolumeval) {
		this.vidlvolumeval = vidlvolumeval;
	}

	public String getVidlvolumeremark() {
		return vidlvolumeremark;
	}

	public void setVidlvolumeremark(String vidlvolumeremark) {
		this.vidlvolumeremark = vidlvolumeremark;
	}

	public String getVidminchannel() {
		return vidminchannel;
	}

	public void setVidminchannel(String vidminchannel) {
		this.vidminchannel = vidminchannel;
	}

	public String getVidminchannelflag() {
		return vidminchannelflag;
	}

	public void setVidminchannelflag(String vidminchannelflag) {
		this.vidminchannelflag = vidminchannelflag;
	}

	public String getVidminchannelval() {
		return vidminchannelval;
	}

	public void setVidminchannelval(String vidminchannelval) {
		this.vidminchannelval = vidminchannelval;
	}

	public String getVidminchannelremark() {
		return vidminchannelremark;
	}

	public void setVidminchannelremark(String vidminchannelremark) {
		this.vidminchannelremark = vidminchannelremark;
	}

	public String getVidvisituser() {
		return vidvisituser;
	}

	public void setVidvisituser(String vidvisituser) {
		this.vidvisituser = vidvisituser;
	}

	public String getVidvisituserflag() {
		return vidvisituserflag;
	}

	public void setVidvisituserflag(String vidvisituserflag) {
		this.vidvisituserflag = vidvisituserflag;
	}

	public String getVidvisituserval() {
		return vidvisituserval;
	}

	public void setVidvisituserval(String vidvisituserval) {
		this.vidvisituserval = vidvisituserval;
	}

	public String getVidvisituserremark() {
		return vidvisituserremark;
	}

	public void setVidvisituserremark(String vidvisituserremark) {
		this.vidvisituserremark = vidvisituserremark;
	}

	public String getCreuser() {
		return creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	public Date getCredate() {
		return credate;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getVidterlevel() {
		return vidterlevel;
	}

	public void setVidterlevel(String vidterlevel) {
		this.vidterlevel = vidterlevel;
	}

	public String getVidtervidterlevelflag() {
		return vidtervidterlevelflag;
	}

	public void setVidtervidterlevelflag(String vidtervidterlevelflag) {
		this.vidtervidterlevelflag = vidtervidterlevelflag;
	}

	public String getVidtervidterlevelval() {
		return vidtervidterlevelval;
	}

	public void setVidtervidterlevelval(String vidtervidterlevelval) {
		this.vidtervidterlevelval = vidtervidterlevelval;
	}

	public String getVidtervidterlevelremark() {
		return vidtervidterlevelremark;
	}

	public void setVidtervidterlevelremark(String vidtervidterlevelremark) {
		this.vidtervidterlevelremark = vidtervidterlevelremark;
	}

	public String getVidvisitotherval() {
		return vidvisitotherval;
	}

	public void setVidvisitotherval(String vidvisitotherval) {
		this.vidvisitotherval = vidvisitotherval;
	}

	public String getVidvisitottrueval() {
		return vidvisitottrueval;
	}

	public void setVidvisitottrueval(String vidvisitottrueval) {
		this.vidvisitottrueval = vidvisitottrueval;
	}

	public String getPadisconsistent() {
		return padisconsistent;
	}

	public void setPadisconsistent(String padisconsistent) {
		this.padisconsistent = padisconsistent;
	}

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getVidisselfremark() {
		return vidisselfremark;
	}

	public void setVidisselfremark(String vidisselfremark) {
		this.vidisselfremark = vidisselfremark;
	}

	public String getVidiscmpremark() {
		return vidiscmpremark;
	}

	public void setVidiscmpremark(String vidiscmpremark) {
		this.vidiscmpremark = vidiscmpremark;
	}

	public String getVidselftreatyremark() {
		return vidselftreatyremark;
	}

	public void setVidselftreatyremark(String vidselftreatyremark) {
		this.vidselftreatyremark = vidselftreatyremark;
	}

	public String getVidstartdate() {
		return vidstartdate;
	}

	public void setVidstartdate(String vidstartdate) {
		this.vidstartdate = vidstartdate;
	}

	public String getVidenddate() {
		return videnddate;
	}

	public void setVidenddate(String videnddate) {
		this.videnddate = videnddate;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}