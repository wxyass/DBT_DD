package et.tsingtaopad.initconstvalues;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import et.tsingtaopad.R;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstAgencyinfoMDao;
import et.tsingtaopad.db.dao.MstCmpCompanyMDao;
import et.tsingtaopad.db.dao.PadChecktypeMDao;
import et.tsingtaopad.db.table.CmmAreaM;
import et.tsingtaopad.db.table.CmmDatadicM;
import et.tsingtaopad.db.table.MstAgencyinfoM;
import et.tsingtaopad.db.table.MstCmpcompanyM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstSynckvM;
import et.tsingtaopad.db.table.PadChecktypeM;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.initconstvalues.domain.KvStc;
import et.tsingtaopad.main.visit.shopvisit.line.domain.MstRouteMStc;


/**
 * Created by yangwenmin on 2017/12/25
 * 初始化数据,加载到内存.
 *
 * 在下面2种情况下会执行这个线程
 * 1.打开主Activity时,执行
 * 2.同步完成后执行
 *
 */
public class InitConstValues extends Thread {
    private final String TAG = "InitConstValues";
    private Context context;
    public InitConstValues(Context context) {
        this.context = context;
    }
    
    @Override
    public void run() {
        
        // 1 初始化Kv表数据//
        this.initSyncKv();
        
        // 2 初始化拜访路线//
        this.initMstRoute();
        
        // 3 初始化区域信息//
        this.initAreaInfo();
                                                
        // 4 初始化数据字典//
        this.initDataDictionary();

        // 5 初始化指标、指标状态
        this.initCheckTypeStatus();  
        
        // 6 初始化经销商及经销商可销售产品的树级关系对象
        this.initAgencyMine();
        
        // 7 初始化竞品公司及产品关系
        this.initAgencyVie();
        
        // 8 初始化定格可拜访经销商及经销商可销售产品的树级关系对象
        this.initVisitAgencyPro();
        
        // 9 初始化权限图标 // 如果在这里初始化,会出未等初始化完成,用户就点击巡店管理了,结果系统崩溃
        //this.initAuthority();
        
        Log.i(TAG, "初始化静态变量完成");
    }
    
    /**
	 * 初始化权限图标
     * 如果在这里初始化,会出未等初始化完成,用户就点击巡店管理了,结果系统崩溃
	 */
	private void initAuthority() {
		// 初始化按钮图片对应
		HashMap<String, Integer> authorityMap = new HashMap<String, Integer>();
        // 初始化按钮图片对应
        authorityMap.put("1000000", R.drawable.bt_visit_shopvist);// 巡店拜访
        authorityMap.put("1000001", R.drawable.bt_visit_addterm);// 新增终端
        authorityMap.put("1000002", R.drawable.bt_visit_termdetail);// 终端进货明细
        authorityMap.put("1000003", R.drawable.bt_visit_agency);// 经销商拜访
        authorityMap.put("1000004", R.drawable.bt_visit_store);// 经销商库存
        authorityMap.put("1000005", R.drawable.bt_visit_agencykf);// 经销商开发
        authorityMap.put("1000006", R.drawable.bt_visit_sync);// 数据同步
        authorityMap.put("1000007", R.drawable.bt_visit_termtaizhang);// 终端进货台账录入
        authorityMap.put("1000008", R.drawable.bt_visit_showpro);// 产品展示
        authorityMap.put("1000009", R.drawable.bt_visit_addterm_other);// 其它

        authorityMap.put("1000010", R.drawable.bt_operation_workplan);// 日/周工作计划
        authorityMap.put("1000011", R.drawable.bt_operation_workdetail);// 日工作推进(标准)
        authorityMap.put("1000012", R.drawable.bt_operation_workweek);// 周工作总结
        authorityMap.put("1000013", R.drawable.bt_visit_work);// 日工作记录查询
        authorityMap.put("1000014", R.drawable.bt_operation_omnipotent);// 万能铺货率查询
        authorityMap.put("1000015", R.drawable.bt_operation_indexsearch);// 指标状态查询
        authorityMap.put("1000016", R.drawable.bt_operation_promotion);// 促销活动查询
        authorityMap.put("1000017", R.drawable.bt_operation_workdetailsd);// 日工作推进(山东) 不用了

        authorityMap.put("1000018", R.drawable.bt_business_notice);// 通知公告
        authorityMap.put("1000019", R.drawable.bt_business_question);// 问题反馈
        authorityMap.put("1000020", R.drawable.bt_syssetting_update);// 检查更新
        authorityMap.put("1000021", R.drawable.bt_syssetting_modify_pwd);// 修改密码
        authorityMap.put("1000022", R.drawable.bt_syssetting_info);// 关于系统

        authorityMap.put("1000023", R.drawable.bt_operation_dingdan);// 当日订单查询
        authorityMap.put("1000024", R.drawable.bt_operation_load);// 路线卡查询
        authorityMap.put("1000025", R.drawable.bt_operation_taizhang);// 终端进货台账查询
        GlobalValues.AuthorityMap = authorityMap;

	}

	/**
     * 获取并初始化拜访线路
     * @return
     */
    @SuppressWarnings("unchecked")
    public void initMstRoute(){
        
        List<MstRouteM> mstRouteList=new ArrayList<MstRouteM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstRouteM, String> mstRouteMDao = helper.getMstRouteMDao();
            QueryBuilder<MstRouteM, String> qBuilder = mstRouteMDao.queryBuilder();
            Where<MstRouteM, String> where = qBuilder.where();
            where.and(where.or(where.isNull("deleteflag"), where.eq("deleteflag", "0")),
                    where.or(where.isNull("routestatus"), where.eq("routestatus", "0"))
                    );
            qBuilder.orderBy("orderbyno", true).orderBy("routename", true);
            mstRouteList=qBuilder.query();
        } catch (Exception e) {
            Log.e(TAG, "获取线路信息失败", e);
        }

        // 添加请选择
        MstRouteM mstRouteM = new MstRouteMStc();
        mstRouteM.setRoutekey("-1");
        mstRouteM.setRoutename("请选择");
        mstRouteList.add(0, mstRouteM);
        GlobalValues.lineLst=mstRouteList;
    }
    
    /**
     * 获取省市县数据，并组建成相应的树级结构
     * @return
     */
    @SuppressWarnings("unchecked")
    public void initAreaInfo () {
        List<CmmAreaM> areaLst = new ArrayList<CmmAreaM>();
        MstAgencyinfoM agencyM = new MstAgencyinfoM();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            
            // 获取省市省
            Dao<CmmAreaM, String> cmmAreaMDao = helper.getCmmAreaMDao();
            QueryBuilder<CmmAreaM, String> qBuilder = cmmAreaMDao.queryBuilder();
            Where<CmmAreaM, String> where = qBuilder.where();
            where.or(where.isNull("deleteflag"), where.eq("deleteflag", "0"));
            qBuilder.orderBy("areacode", true).orderBy("orderbyno", true);
            areaLst = qBuilder.query();
            
            // 获取当前定格的主供货商
            MstAgencyinfoMDao agencyDao = helper.getDao(MstAgencyinfoM.class);
            //agencyM = agencyDao.queryMainAgency(helper, ConstValues.loginSession.getGridId());
            // 根据GridId先查MstAgencyinfoM表记录,没有再查mst_agencygrid_info表,谁先查出来,就取谁的第一条
            agencyM = agencyDao.queryMainAgency(helper, PrefUtils.getString(context, "gridId", ""));
        } catch (Exception e) {
             Log.e(TAG, "获取经销售商信息失败", e);
        }
        
        // 获取当前定格经销商
        GlobalValues.provLst=  queryChildForArea(areaLst, "-1", 1, agencyM.getProvince());
    }
    
    /**
     *  递归遍历区域树
     *  
     * @param areaLst       数据源
     * @param parentId      父ID
     * @param level         当前等级
     * @param provinceId    当前等级
     * @return
     */
    private List<KvStc> queryChildForArea(List<CmmAreaM> areaLst, String parentId, int level, String provinceId) {

        // areaLst,"-1",1,288897
        List<KvStc> kvLst = null;
        if (level <= 3) {
            kvLst = new ArrayList<KvStc>();
            KvStc kvItem = new KvStc();
            int nextLevel = level + 1;
            for (CmmAreaM areaItem : areaLst) {
                if (parentId.equals(FunUtil.isBlankOrNullTo(areaItem.getParentcode(), "-1"))) {
                    
                    // 省级限制
                    /*if (level == 1 && !areaItem.getAreacode().equals(provinceId)) {
                        continue;
                    }*/
                    
                    // 过滤 市的全省选项
                    if (level == 2 && areaItem.getAreaname().equals("全省")) {
                        continue;
                    }
                    
                    kvItem = new KvStc(areaItem.getAreacode(),areaItem.getAreaname(), areaItem.getParentcode());
                    kvItem.setChildLst(queryChildForArea(areaLst, areaItem.getAreacode(), nextLevel, ""));
                    kvLst.add(kvItem);
                }
            }
            
            // 添加请选择
            if (level <= 3) {
                kvItem = new KvStc("-1", "请选择", "-1");
            }
            if (level <= 2) {
                kvItem.getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            if (level <= 1) {
                // 在县的集合中添加"请选择"
                kvItem.getChildLst().get(0).getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            kvLst.add(0,kvItem);
        }
        return kvLst;
    }
    
    /**
     * 获取数据字典信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public void  initDataDictionary () {
        List<CmmDatadicM> dataDicLst = new ArrayList<CmmDatadicM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            QueryBuilder<CmmDatadicM, String> qBuilder = 
                                helper.getCmmDatadicMDao().queryBuilder();
            Where<CmmDatadicM, String> where = qBuilder.where();
            where.or(where.isNull("deleteflag"), where.eq("deleteflag", "0"));
            qBuilder.orderBy("parentcode", true).orderBy("orderbyno", true);
            dataDicLst = qBuilder.query();
        } catch (SQLException e) {
            Log.e(TAG, "初始化数据字典失败", e);
        }        

        // 区域类型(请选择,城区,乡镇,村级,大店部)
        this.initDataDicByAreaType(dataDicLst);
        // 区域类型(没有请选择)
        this.initDataDicByAreaType2(dataDicLst);
        // 终端等级
        this.initDataDicByTermLevel(dataDicLst);
        // 销售渠道
        this.initDataDicBySellChannel(dataDicLst);
        // 拜访对象
        this.initDataDicByVisitPosition(dataDicLst);
    }    
    
    /**
     * 初始化终端区域类型
     */
    private void initDataDicByAreaType(List<CmmDatadicM> dataDicLst) {
        
        // 获取数据字典表中区域字典对应的父ID
        String areaType= PropertiesUtil.getProperties("datadic_areaType");
        
        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (areaType.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode()
                        , item.getDicname(), item.getParentcode()));
            } else if (kvLst.size() > 0) {
                break;
            }
        }
        kvLst.add(0, new KvStc("-1", "请选择", "-1"));
        GlobalValues.dataDicMap.put("areaTypeLst", kvLst);
    }
    
    /**
     * 初始化终端区域类型(没有请选择)
     */
    private void initDataDicByAreaType2(List<CmmDatadicM> dataDicLst) {
        
        // 获取数据字典表中区域字典对应的父ID
        String areaType=PropertiesUtil.getProperties("datadic_areaType");
        
        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (areaType.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode()
                        , item.getDicname(), item.getParentcode()));
            } else if (kvLst.size() > 0) {
                break;
            }
        }
        GlobalValues.dataDicMap.put("areaTypeLst2", kvLst);
    }
    /**
     * 初始化拜访对象职位(老板老板娘)
     */
    private void initDataDicByVisitPosition(List<CmmDatadicM> dataDicLst) {
    	
    	// 获取数据字典表中区域字典对应的父ID
    	String areaType=PropertiesUtil.getProperties("datadic_visitposition");
    	
    	List<KvStc> kvLst = new ArrayList<KvStc>();
    	for (CmmDatadicM item : dataDicLst) {
    		if (areaType.equals(item.getParentcode())) {
    			kvLst.add(new KvStc(item.getDiccode()
    					, item.getDicname(), item.getParentcode()));
    		} else if (kvLst.size() > 0) {
    			break;
    		}
    	}
    	kvLst.add(0, new KvStc("-1", "请选择", "-1"));
        GlobalValues.dataDicMap.put("visitPositionLst", kvLst);
    }
    
    /**
     * 初始化终端等级
     */
    private void initDataDicByTermLevel(List<CmmDatadicM> dataDicLst) {
        
        // 获取数据字典表中区域字典对应的父ID
        String termLevel = PropertiesUtil.getProperties("datadic_termLevel");
        
        List<KvStc> kvLst = new ArrayList<KvStc>();
        for (CmmDatadicM item : dataDicLst) {
            if (termLevel.equals(item.getParentcode())) {
                kvLst.add(new KvStc(item.getDiccode(),
                        item.getDicname(), item.getParentcode()));
            } else if (kvLst.size() > 0) {
                break;
            }
        }
        kvLst.add(0, new KvStc("-1", "请选择", "-1"));
        GlobalValues.dataDicMap.put("levelLst", kvLst);
    }
    
    /**
     * 初始化销售渠道
     */
    private void initDataDicBySellChannel(List<CmmDatadicM> dataDicLst) {

        // 获取数据字典表中区域字典对应的父ID
        String sellChannel = PropertiesUtil.getProperties("datadic_sellChannel");

        GlobalValues.dataDicMap.put("sellChannelLst",
                    queryChildForDataDic(dataDicLst, sellChannel, 1));
    }
    
    /**
     *  递归遍历数据字典
     *  
     * @param dataDicLst    数据源
     * @param parentCode    父ID
     * @param level         当前等级  1 
     * @return
     */
    private List<KvStc> queryChildForDataDic(
                List<CmmDatadicM> dataDicLst, String parentCode, int level) {
        List<KvStc> kvLst = null;
        if (level <= 3) {
            kvLst = new ArrayList<KvStc>();
            KvStc kvItem = new KvStc();
            int nextLevel = level + 1;
            for (CmmDatadicM dataItem : dataDicLst) {
                if (parentCode.equals(dataItem.getParentcode())) {
                    kvItem = new KvStc(dataItem.getDiccode(),dataItem.getDicname(), dataItem.getParentcode());
                    kvItem.setChildLst(queryChildForDataDic( dataDicLst, dataItem.getDiccode(), nextLevel));
                    kvLst.add(kvItem);
                }
            }

            // 添加请选择
            if (level <= 3) {
                kvItem = new KvStc("-1", "请选择", "-1");
            }
            if (level <= 2) {
                kvItem.getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            if (level <= 1) {
                kvItem.getChildLst().get(0).getChildLst().add(new KvStc("-1", "请选择", "-1"));
            }
            kvLst.add(0,kvItem);
        }
        return kvLst;
    }
    
    /**
     * 初始化经销商及经销商可销售产品的树级关系对象
     */
    public void initAgencyMine(){
        List<KvStc> agencySellProList=new ArrayList<KvStc>();        
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);        
            MstAgencyinfoMDao agencyInfoMDao = helper.getDao(MstAgencyinfoM.class);
            agencySellProList = agencyInfoMDao.agencySellProQuery(helper);
        } catch (Exception e) {
             Log.e(TAG, "初始化经销商及经销商可销售产品的树级关系对象失败", e);
        }
        GlobalValues.agencyMineLst = agencySellProList;
    }
    
    /**
     * 初始化竞品公司及竞品的树级关系对象
     */
    public void initAgencyVie() {
        List<KvStc> agencySellProList=new ArrayList<KvStc>();        
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);        
            MstCmpCompanyMDao cmpCompanyMDao = helper.getDao(MstCmpcompanyM.class);
            agencySellProList = cmpCompanyMDao.agencySellProQuery(helper);
        } catch (Exception e) {
            Log.e(TAG, "初始化竞品公司及竞品的树级关系对象失败", e);
        }
        GlobalValues.agencyVieLst = agencySellProList;
    }
        
    /**
     * 初始化定格可拜访经销商及产品的树级关系对象
     */
    public void initVisitAgencyPro() {

        List<KvStc> visitAgencyProList=new ArrayList<KvStc>();        
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);        
            MstAgencyinfoMDao agencyInfoMDao = helper.getDao(MstAgencyinfoM.class);
            visitAgencyProList = agencyInfoMDao.queryVisitAgencyPro(helper);
        } catch (Exception e) {
             Log.e(TAG, "初始化定格可拜访经销商及产品的树级关系对象失败", e);
        }
        GlobalValues.agencyVisitLst = visitAgencyProList;
    }
    
    /**
     * 初始化指标、指标值树级关系 对象
     */
    public void initCheckTypeStatus() {        
        List<KvStc> checkTypeStatusList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            PadChecktypeMDao dao = helper.getDao(PadChecktypeM.class);
            checkTypeStatusList= dao.queryCheckTypeStatus(helper, ConstValues.FLAG_0);            
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        GlobalValues.indexLst = checkTypeStatusList;
    }
    
    /**
     * 初始化Kv表数据
     */
    public void initSyncKv() {
        List<MstSynckvM> syncKvLst = new ArrayList<MstSynckvM>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MstSynckvM, String> dao = helper.getMstSynckvMDao();
            syncKvLst = dao.queryForAll();          
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        
        Map<String, MstSynckvM> kvMap = new HashMap<String, MstSynckvM>();
        for (MstSynckvM item : syncKvLst) {
            kvMap.put(item.getTablename(), item);
        }
        GlobalValues.kvMap = kvMap;
    }
    
}    
    