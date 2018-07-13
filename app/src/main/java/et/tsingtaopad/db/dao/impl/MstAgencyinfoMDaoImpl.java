package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.db.dao.MstAgencyinfoMDao;
import et.tsingtaopad.db.table.MstAgencyinfoM;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.main.operation.agencystorage.domain.AgencystorageStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 分经销商信息主表Dao层实现</br>
 */
public class MstAgencyinfoMDaoImpl extends BaseDaoImpl<MstAgencyinfoM, String>
                                                 implements MstAgencyinfoMDao {

    public  MstAgencyinfoMDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, MstAgencyinfoM.class);
    }

    /**
     * 获取经销商库存的查询显示数据
     * @param helper
     * @param agencyKey 经销商ID
     * @return
     */
    @Override
    public List<AgencystorageStc> agencyStorageQuery(SQLiteOpenHelper helper, String datecureents, String datecureentx,
                                                     String agencyKey, String datecureent, String dateselects, String dateselectx, String agencyKeys, String dateselect) {
        List<AgencystorageStc> storStcLst = new ArrayList<AgencystorageStc>();
        SQLiteDatabase db = helper.getReadableDatabase();
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select u.proname, u.procode, sum(u.selectstorenum) selectstorenum , sum(u.preselectstorenum) preselectstorenum , sum(u.selectinnum) selectinnum, ");
        buffer.append("sum(u.selectdirecout+u.selectindirecout) selectsalenum, sum(u.storenum) storenum,sum(u.prestorenum) prestorenum, sum(u.innum) innum, ");
        buffer.append(" sum(u.direcout+u.indirecout) salenum, u.agencyname, u.agencycode, u.contact,u.mobile,u.agencykey ");
        buffer.append("from (select mpm.proname  proname,mpm.procode procode,vm.agevisitdate agevisitdate,0 as selectstorenum, ");
        buffer.append(" 0 as preselectstorenum,0 as selectinnum,0 as selectdirecout,0 as selectindirecout,mii.storenum  storenum,mii.prestorenum  prestorenum,mii.innum  innum, ");
        buffer.append(" mii.direcout direcout, indirecout indirecout,mii.recorddate  recorddate,mam.agencyname  agencyname, ");
        buffer.append("mam.agencycode  agencycode, mam.contact contact, mam.mobile   mobile, mam.agencykey   agencykey ");
        //buffer.append(" from v_agencyvisit_m_newest vm ");
        buffer.append("from (select m.* from mst_agencyvisit_m m  inner join (select max(agevisitdate) maxagevisitdate, ");
        buffer.append(" max(agencykey) maxagencykey from mst_agencyvisit_m  dd where  agevisitdate between ? and ? ");
        buffer.append(" group by agencykey ) v  on m.agencykey = v.maxagencykey ");
        buffer.append(" and m.agevisitdate = v.maxagevisitdate) vm ");
        
        buffer.append("inner join MST_INVOICING_INFO mii ");
        buffer.append("  on vm.agevisitkey = mii.agevisitkey  and coalesce(mii.deleteflag, '0') != '1' ");
        buffer.append("inner join MST_AGENCYINFO_M mam ");
        buffer.append("  on vm.agencykey = mam.agencykey ");
        buffer.append("inner join MST_PRODUCT_M mpm on mii.productkey = mpm.productkey ");
        buffer.append(" where vm.agencykey = ?  and mii.recorddate = ? ");
        buffer.append(" union ");
        buffer.append("select mpm.proname  proname,mpm.procode  procode,vm.agevisitdate agevisitdate, ");
        buffer.append("mii.storenum  storenum,mii.prestorenum  prestorenum,mii.innum  innum,mii.direcout direcout,indirecout  indirecout, ");
        buffer.append("0 as selectstorenum,0 as preselectstorenum,0  as selectinnum, 0  as selectdirecout, 0  as selectindirecout,  ");
        buffer.append("mii.recorddate  recorddate,mam.agencyname  agencyname,mam.agencycode  agencycode, mam.contact  contact,mam.mobile mobile,mam.agencykey agencykey ");
       // buffer.append("from v_agencyvisit_m_newest vm ");
        buffer.append("from (select m.* from mst_agencyvisit_m m  inner join (select max(agevisitdate) maxagevisitdate, ");
        buffer.append(" max(agencykey) maxagencykey from mst_agencyvisit_m  dd where  agevisitdate between ? and ? ");
        buffer.append(" group by agencykey ) v  on m.agencykey = v.maxagencykey ");
        buffer.append(" and m.agevisitdate = v.maxagevisitdate) vm ");
        
        buffer.append(" inner join MST_INVOICING_INFO mii ");
        buffer.append(" on vm.agevisitkey = mii.agevisitkey and coalesce(mii.deleteflag, '0') != '1' ");
        buffer.append("inner join MST_AGENCYINFO_M mam  on vm.agencykey = mam.agencykey ");
        buffer.append("inner join MST_PRODUCT_M mpm ");
        buffer.append(" on mii.productkey = mpm.productkey ");
        buffer.append(" where vm.agencykey = ?  and mii.recorddate = ? ) u ");
        buffer.append(" group by u.proname,u.procode, u.agencyname,u.agencycode,u.contact, u.mobile, u.agencykey ");
        
        /*buffer.append("select mpm.proname proname, mpm.procode procode, vm.agevisitdate agevisitdate,");
        buffer.append("mii.storenum storenum, mii.recorddate recorddate,");
        buffer.append("mii.innum innum,mii.direcout direcout,mii.indirecout indirecout,");
        buffer.append("mam.agencyname agencyname, mam.agencycode agencycode, ");
        buffer.append("mam.contact contact, mam.mobile mobile, mam.agencykey agencykey ");
        buffer.append("from v_agencyvisit_m_newest vm ");
        buffer.append("inner join MST_INVOICING_INFO mii ");
        buffer.append("  on vm.agevisitkey = mii.agevisitkey and coalesce(mii.deleteflag, '0') != '1' ");
        buffer.append("inner join MST_AGENCYINFO_M mam ");
        buffer.append("  on vm.agencykey = mam.agencykey ");
        buffer.append("inner join MST_PRODUCT_M mpm on mii.productkey = mpm.productkey ");
        buffer.append("where vm.agencykey = ? ");*/
        Cursor cursor = db.rawQuery(buffer.toString(), new String []{datecureents,datecureentx,agencyKey,datecureent,dateselects,dateselectx,agencyKey,dateselect});
        while(cursor.moveToNext()) {
            AgencystorageStc storStc = new AgencystorageStc();
            storStc.setProName(cursor.getString(cursor.getColumnIndex("proname")));
            storStc.setProCode(cursor.getString(cursor.getColumnIndex("procode")));
            storStc.setCreentstorenmu(cursor.getString(cursor.getColumnIndex("storenum")));//当前库存
            storStc.setCreentingoodsnum(cursor.getString(cursor.getColumnIndex("innum")));//当前进货量
            storStc.setCreentsalenum(cursor.getString(cursor.getColumnIndex("salenum")));//当前销售量
            storStc.setStorenum(cursor.getString(cursor.getColumnIndex("selectstorenum")));//上次库存
            storStc.setIngoodsnum(cursor.getString(cursor.getColumnIndex("selectinnum")));//上次进货量
            storStc.setSalenum(cursor.getString(cursor.getColumnIndex("selectsalenum")));//上次销售量
            storStc.setAgencyName(cursor.getString(cursor.getColumnIndex("agencyname")));
            storStc.setAgencyCode(cursor.getString(cursor.getColumnIndex("agencycode")));
            storStc.setAgencyUser(cursor.getString(cursor.getColumnIndex("contact")));
            storStc.setPhone(cursor.getString(cursor.getColumnIndex("mobile")));
            storStc.setPrestorenum(cursor.getString(cursor.getColumnIndex("preselectstorenum")));
            storStc.setPrecreentstorenum(cursor.getString(cursor.getColumnIndex("prestorenum")));
            storStcLst.add(storStc);
        }
        return storStcLst;
    }
    
    /**
     * 获取该定格下可供货经销商及其可销售产品(经销商可销售产品与我区常品的交集)
     * 
     * @author 姜世杰 
     * @since    2013-12-17
     * @return  一级为经销商信息、二级不经销商可销售的产品信息
     */
    @Override
    public List<KvStc> agencySellProQuery(SQLiteOpenHelper helper){
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select distinct ag.agencykey agencykey, ap.agencyname agencyname, ");
        buffer.append(" ap.productkey productkey, ap.proname proname ");
        buffer.append("from mst_agencygrid_info ag, v_agencyselproduct_info ap ");
        buffer.append("where ag.agencykey = ap.agencykey and coalesce(ag.deleteflag,'0') != '1' ");
        buffer.append(" and ((ap.enddate is null and ap.startdate <= ? ) or (? between ap.startdate and ap.enddate)) ");
        buffer.append("order by ag.agencykey, ap.proname ");
        
        SQLiteDatabase db = helper.getReadableDatabase();
        String currDate = DateUtil.formatDate(new Date(), "yyyyMMdd");
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{currDate, currDate});
        List<KvStc> kvLst = new ArrayList<KvStc>();
        KvStc kvItem = new KvStc();
        KvStc kvChild;
        String agencyId = "";
        while(cursor.moveToNext()) {
          
            if (!agencyId.equals(cursor.getString(cursor.getColumnIndex("agencykey")))) { // 经销商这一级别
                kvItem = new KvStc();
                kvItem.setKey(cursor.getString(cursor.getColumnIndex("agencykey")));
                kvItem.setValue(cursor.getString(cursor.getColumnIndex("agencyname")));
                agencyId = kvItem.getKey();
                kvLst.add(kvItem);
            }

            // 经销商对应的产品的这一级别
            kvChild = new KvStc();
            kvChild.setParentKey(agencyId);
            kvChild.setKey(cursor.getString(cursor.getColumnIndex("productkey")));
            kvChild.setValue(cursor.getString(cursor.getColumnIndex("proname")));
            kvItem.getChildLst().add(kvChild);
        }
        return kvLst;
    }

    /**
     * 根据终端key 查询给该终端供货的经销商
     *
     * @author wxyass
     * @since    2013-12-17
     * @return
     */
    @Override
    public List<KvStc> agencyTermQuery(SQLiteOpenHelper helper,String terminalkey){

        StringBuffer buffer = new StringBuffer();
        buffer.append("select mam.* from MST_AGENCYGRID_INFO  mai ");
        buffer.append("left join MST_ROUTE_M mtm on mtm.[gridkey] = mai.gridkey ");
        buffer.append("inner join MST_TERMINALINFO_M mrm  on mtm.[routekey]  = mrm.[routekey] ");
        buffer.append("inner join MST_AGENCYINFO_M mam on mai.agencykey = mam.agencykey ");
        buffer.append("where mrm.[terminalkey] = ? ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
        List<KvStc> kvLst = new ArrayList<KvStc>();
        KvStc kvItem = new KvStc();
        while(cursor.moveToNext()) {
            kvItem = new KvStc();
            kvItem.setKey(cursor.getString(cursor.getColumnIndex("agencykey")));
            kvItem.setValue(cursor.getString(cursor.getColumnIndex("agencyname")));
            kvLst.add(kvItem);
        }
        return kvLst;
    }
    
    /**
     * 获取可拜访经销商及产品列表
     * 
     * @param helper
     * @return
     */
    public List<KvStc> queryVisitAgencyPro(SQLiteOpenHelper helper) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("select distinct va.agencykey agencykey, ap.agencyname agencyname, ");
        buffer.append("  ap.productkey productkey, ap.proname proname ");
        buffer.append("from mst_visitauthorize_info va, v_agencyselproduct_info ap ");
        buffer.append("where coalesce(va.deleteflag,'0') != '1' and va.agencykey = ap.agencykey ");
        buffer.append("and ((ap.enddate is null and ap.startdate <= ?) or (? between ap.startdate and ap.enddate)) ");
        buffer.append("order by va.agencykey, ap.proname ");
        
        // 获取当前时间
        SQLiteDatabase db = helper.getReadableDatabase();
        String currDate = DateUtil.formatDate(new Date(), "yyyyMMdd");
        Cursor cursor = db.rawQuery(buffer.toString(), new String[] {currDate,currDate});
        List<KvStc> kvLst = new ArrayList<KvStc>();
        KvStc kvItem = new KvStc();
        KvStc kvChild;
        String agencyId = "";
        while(cursor.moveToNext()) {
          
            if (!agencyId.equals(cursor.getString(cursor.getColumnIndex("agencykey")))) {
                kvItem = new KvStc();
                kvItem.setKey(cursor.getString(cursor.getColumnIndex("agencykey")));
                kvItem.setValue(cursor.getString(cursor.getColumnIndex("agencyname")));
                agencyId = kvItem.getKey();
                kvLst.add(kvItem);
            }
            kvChild = new KvStc();
            kvChild.setParentKey(agencyId);
            kvChild.setKey(cursor.getString(cursor.getColumnIndex("productkey")));
            kvChild.setValue(cursor.getString(cursor.getColumnIndex("proname")));
            if (!CheckUtil.isBlankOrNull(kvChild.getKey())) {
                kvItem.getChildLst().add(kvChild);
            }
        }
        return kvLst;
    }
    

    /**
     * 获取当前定格的请经销商
     * 
     * @param helper
     * @param gridId
     * @return
     */
    public MstAgencyinfoM queryMainAgency(SQLiteOpenHelper helper, String gridId) {
        
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = helper.getReadableDatabase();
        
        // 定格经销商
        buffer.append("select * from mst_agencyinfo_m am where am.gridkey = ? ");
        buffer.append("and coalesce(am.province,'-1') != '-1' ");
        buffer.append("and coalesce(am.agencystatus,'0') != '1' ");
        Cursor cursor = db.rawQuery(buffer.toString(), new String[] {gridId});
        
        // 如果没有则，选择该定格的供货商中的第一个
        if (cursor.getCount() <= 0) {
            buffer = new StringBuffer();
            buffer.append("select am.* from mst_agencygrid_info ag, mst_agencyinfo_m am ");
            buffer.append("where ag.agencykey = am.agencykey and ag.gridkey = ? ");
            buffer.append("and coalesce(am.province,'-1') != '-1' ");
            buffer.append("and coalesce(am.agencystatus,'0') != '1' ");
            cursor = db.rawQuery(buffer.toString(), new String[] {gridId});
        }
        
        MstAgencyinfoM info = new MstAgencyinfoM();
        while(cursor.moveToNext()) {
            info.setAgencykey(cursor.getString(cursor.getColumnIndex("agencykey")));
            info.setAgencycode(cursor.getString(cursor.getColumnIndex("agencycode")));
            info.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
            info.setAgencystatus(cursor.getString(cursor.getColumnIndex("agencystatus")));
            info.setAgencyparent(cursor.getString(cursor.getColumnIndex("agencyparent")));
            info.setAgencytype(cursor.getString(cursor.getColumnIndex("agencytype")));
            info.setAgencylevel(cursor.getString(cursor.getColumnIndex("agencylevel")));
            info.setIsfranchise(cursor.getString(cursor.getColumnIndex("isfranchise")));
            info.setCustomertype(cursor.getString(cursor.getColumnIndex("customertype")));
            info.setPricekey(cursor.getString(cursor.getColumnIndex("pricekey")));
            info.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
            info.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            info.setCity(cursor.getString(cursor.getColumnIndex("city")));
            info.setCounty(cursor.getString(cursor.getColumnIndex("county")));
            info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            info.setBeerstartdate(cursor.getString(cursor.getColumnIndex("beerstartdate")));
            info.setMiansell(cursor.getString(cursor.getColumnIndex("miansell")));
            info.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            info.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            info.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
            break;
        }
        return info;
    }
}
