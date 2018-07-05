package dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.DatabaseHelper;
import dd.tsingtaopad.db.dao.MstAgencyinfoMDao;
import dd.tsingtaopad.db.dao.MstProductMDao;
import dd.tsingtaopad.db.table.MstAgencyinfoM;
import dd.tsingtaopad.db.table.MstProductM;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

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

    

}
