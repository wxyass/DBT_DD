package dd.tsingtaopad.dd.ddagencyres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MitvalagencykfMDao;
import dd.tsingtaopad.db.dao.MstAgencyKFMDao;
import dd.tsingtaopad.db.dao.MstAgencyvisitMDao;
import dd.tsingtaopad.db.table.MitValagencykfM;
import dd.tsingtaopad.db.table.MstAgencyKFM;
import dd.tsingtaopad.db.table.MstAgencyvisitM;
import dd.tsingtaopad.dd.ddxt.term.select.XtTermSelectService;
import dd.tsingtaopad.main.visit.agencyvisit.domain.AgencySelectStc;


/**
 * 项目名称：营销移动智能工作平台 </br>
 * 文件名：TermListService.java</br>
 * 功能描述: 巡店拜访_终端列表的业务逻辑</br>
 * 版本 V 1.0</br>
 * 修改履历</br>
 * 日期      原因  BUG号    修改人 修改版本</br>
 */
@SuppressLint("DefaultLocale")
public class DdAgencySelectService extends XtTermSelectService{

    private final String TAG = "TermListService";

    private Context context;

    public DdAgencySelectService(Context context) {
        super(context);
        this.context = context;
    }

    /***
     * 查询经销商开发表所有记录
     *
     * areaid 二级区域ID
     */
    public ArrayList<MstAgencyKFM> queryDdMstAgencyKFMAll(String areaid){
        ArrayList<MstAgencyKFM> valueLst =new ArrayList<MstAgencyKFM>();
        try {

            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstAgencyKFMDao dao = (MstAgencyKFMDao) helper.getMstAgencyKFMDao();

            valueLst =dao.queryZsMstAgencyKFMLst(helper,areaid);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "查询图片类型表中所有记录", e);
        }
        return valueLst;
    }

    /**
     * 查询二级区域下 所有经销商
     * areaid 二级区域id
     * @return
     */
    public List<AgencySelectStc> queryZsDdagencySelectLst(String areaid) {
        List<AgencySelectStc> agencySelectStcLst = new ArrayList<AgencySelectStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstAgencyvisitMDao dao = helper.getDao(MstAgencyvisitM.class);
            agencySelectStcLst = dao.agencyZsSelectQuery(helper,areaid);
        } catch (SQLException e) {
            Log.e(TAG, "选择经销商的数据查询时报错", e);
        }
        return agencySelectStcLst;
    }


    // 保存数据
    public MitValagencykfM saveMitValagencykfM(String des, MstAgencyKFM mstAgencyKFM, MitValagencykfM valagencykfM) {
        MitValagencykfM mitValagencykfM = new MitValagencykfM();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MitvalagencykfMDao dao = helper.getDao(MitValagencykfM.class);
            mitValagencykfM.setId(FunUtil.getUUID());
            mitValagencykfM.setGridkey(mstAgencyKFM.getGridkey());

            mitValagencykfM.setAgencyname(mstAgencyKFM.getAgencyname());
            mitValagencykfM.setAgencynameflag(valagencykfM.getAgencynameflag());
            mitValagencykfM.setAgencyrealname(valagencykfM.getAgencyrealname());
            mitValagencykfM.setAgencynameremark(valagencykfM.getAgencynameremark());

            mitValagencykfM.setContact(mstAgencyKFM.getContact());
            mitValagencykfM.setContactflag(valagencykfM.getContactflag());
            mitValagencykfM.setContactreal(valagencykfM.getContactreal());
            mitValagencykfM.setContactremark(valagencykfM.getContactremark());

            mitValagencykfM.setMobile(mstAgencyKFM.getMobile());
            mitValagencykfM.setMobileflag(valagencykfM.getMobileflag());
            mitValagencykfM.setMobilereal(valagencykfM.getMobilereal());
            mitValagencykfM.setMobileremark(valagencykfM.getMobileremark());

            mitValagencykfM.setAddress(mstAgencyKFM.getAddress());
            mitValagencykfM.setAddressflag(valagencykfM.getAddressflag());
            mitValagencykfM.setAddressreal(valagencykfM.getAddressreal());
            mitValagencykfM.setAddressremark(valagencykfM.getAddressremark());

            mitValagencykfM.setArea(mstAgencyKFM.getArea());
            mitValagencykfM.setAreaflag(valagencykfM.getAreaflag());
            mitValagencykfM.setAreareal(valagencykfM.getAreareal());
            mitValagencykfM.setArearemark(valagencykfM.getArearemark());

            mitValagencykfM.setMoney(mstAgencyKFM.getMoney());
            mitValagencykfM.setMoneyflag(valagencykfM.getMoneyflag());
            mitValagencykfM.setMoneyreal(valagencykfM.getMoneyreal());
            mitValagencykfM.setMoneyremark(valagencykfM.getMoneyremark());

            mitValagencykfM.setCarnum(mstAgencyKFM.getCarnum());
            mitValagencykfM.setCarnumflag(valagencykfM.getCarnumflag());
            mitValagencykfM.setCarnumreal(valagencykfM.getCarnumreal());
            mitValagencykfM.setCarnumremark(valagencykfM.getCarnumremark());

            mitValagencykfM.setProductname(mstAgencyKFM.getProductname());
            mitValagencykfM.setProductnameflag(valagencykfM.getProductnameflag());
            mitValagencykfM.setProductnamereal(valagencykfM.getProductnamereal());
            mitValagencykfM.setProductnameremark(valagencykfM.getProductnameremark());

            mitValagencykfM.setBusiness(mstAgencyKFM.getBusiness());
            mitValagencykfM.setBusinessflag(valagencykfM.getBusinessflag());
            mitValagencykfM.setBusinessreal(valagencykfM.getBusinessreal());
            mitValagencykfM.setBusinessremark(valagencykfM.getBusinessremark());

            mitValagencykfM.setStatus(mstAgencyKFM.getStatus());
            mitValagencykfM.setStatusflag(valagencykfM.getStatusflag());
            mitValagencykfM.setStatusremark(valagencykfM.getStatusremark());

            mitValagencykfM.setCoverterms(mstAgencyKFM.getCoverterms());
            mitValagencykfM.setCovertermflag(valagencykfM.getCovertermflag());
            mitValagencykfM.setCovertermreal(valagencykfM.getCovertermreal());
            mitValagencykfM.setCovertermremark(valagencykfM.getCovertermremark());

            mitValagencykfM.setSupplyterms(mstAgencyKFM.getSupplyterms());
            mitValagencykfM.setSupplytermsflag(valagencykfM.getSupplytermsflag());
            mitValagencykfM.setSupplytermsreal(valagencykfM.getSupplytermsreal());
            mitValagencykfM.setSupplytermsremark(valagencykfM.getSupplytermsremark());

            mitValagencykfM.setKfdate(mstAgencyKFM.getKfdate());
            mitValagencykfM.setKfdateflag(valagencykfM.getKfdateflag());
            mitValagencykfM.setKfdatereal(valagencykfM.getKfdatereal());
            mitValagencykfM.setKfdateremark(valagencykfM.getKfdateremark());

            mitValagencykfM.setPassdate(mstAgencyKFM.getPassdate());
            mitValagencykfM.setPassdateflag(valagencykfM.getPassdateflag());
            mitValagencykfM.setPassdatereal(valagencykfM.getPassdatereal());
            mitValagencykfM.setPassdateremark(valagencykfM.getPassdateremark());

            mitValagencykfM.setRemark(des);

            mitValagencykfM.setCreuserareaid(PrefUtils.getString(context,"departmentid",""));
            mitValagencykfM.setCredate(new Date());
            mitValagencykfM.setUpdatedate(new Date());
            mitValagencykfM.setCreuser(PrefUtils.getString(context,"userid",""));
            mitValagencykfM.setUpdateuser(PrefUtils.getString(context,"userid",""));

            mitValagencykfM.setUploadflag("1");// 0:该条记录不上传  1:该条记录需要上传
            mitValagencykfM.setPadisconsistent("0");// padisconsistent  0:还未上传  1:已上传
            dao.create(mitValagencykfM);
        } catch (SQLException e) {
            Log.e(TAG, "选择经销商的数据查询时报错", e);
        }
        return mitValagencykfM;
    }
}
