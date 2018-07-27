package et.tsingtaopad.dd.ddxt.invoicing.addinvoicing;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstAgencyinfoMDao;
import et.tsingtaopad.db.dao.MstProductMDao;
import et.tsingtaopad.db.table.MstAgencyinfoM;
import et.tsingtaopad.db.table.MstProductM;
import et.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtAddInvocingService extends XtShopVisitService {

    private final String TAG = "XtCameraService";

    public XtAddInvocingService(Context context, Handler handler) {
        super(context, handler);
    }

    /**
     * 查询该终端的供货经销商
     */
    public List<KvStc> getAgencyList(String terminalkey) {
        List<KvStc> agencyList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstAgencyinfoMDao dao = helper.getDao(MstAgencyinfoM.class);
            agencyList= dao.agencyTermQuery(helper,terminalkey);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return agencyList;
    }

    /**
     * 查询该终端的供货经销商
     */
    public List<KvStc> getXtAgencyList(String terminalkey) {
        List<KvStc> agencyList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstAgencyinfoMDao dao = helper.getDao(MstAgencyinfoM.class);
            agencyList= dao.agencyXtTermQuery(helper,terminalkey);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return agencyList;
    }

    /**
     * 查询 追溯时终端的供货经销商
     */
    public List<KvStc> getZsAgencyList(String terminalkey) {
        List<KvStc> agencyList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstAgencyinfoMDao dao = helper.getDao(MstAgencyinfoM.class);
            agencyList= dao.agencyZsTermQuery(helper,terminalkey);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return agencyList;
    }
    /**
     * 查询常用我品
     */
    public List<KvStc> getProList() {
        List<KvStc> proList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstProductMDao dao = helper.getDao(MstProductM.class);
            proList= dao.getProductData(helper);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return proList;
    }

    /**
     * 查询常用我品 添加渠道价 零售价
     */
    public List<ProSellStc> getAllproLst() {
        List<ProSellStc> proList=new ArrayList<ProSellStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstProductMDao dao = helper.getDao(MstProductM.class);
            proList= dao.getAllProductData(helper);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return proList;
    }


    /**
     * 查询所有竞品
     */
    public List<KvStc> getVieProList() {
        List<KvStc> proList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstProductMDao dao = helper.getDao(MstProductM.class);
            proList= dao.getVieProductData(helper);
        } catch (SQLException e) {
            Log.e(TAG, "获取拜访指标失败", e);
            e.printStackTrace();
        }
        return proList;
    }

    

}
