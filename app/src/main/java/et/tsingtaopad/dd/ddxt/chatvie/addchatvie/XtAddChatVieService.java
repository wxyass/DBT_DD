package et.tsingtaopad.dd.ddxt.chatvie.addchatvie;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstCmpCompanyMDao;
import et.tsingtaopad.db.table.MstCmpcompanyM;
import et.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtAddChatVieService extends XtShopVisitService {

    private final String TAG = "XtCameraService";

    public XtAddChatVieService(Context context, Handler handler) {
        super(context, handler);
    }

    /**
     * 初始化竞品公司及竞品的树级关系对象
     */
    public List<KvStc> getAgencyVie() {
        List<KvStc> agencySellProList=new ArrayList<KvStc>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            MstCmpCompanyMDao cmpCompanyMDao = helper.getDao(MstCmpcompanyM.class);
            agencySellProList = cmpCompanyMDao.agencySellProQuery(helper);
        } catch (Exception e) {
            Log.e(TAG, "初始化竞品公司及竞品的树级关系对象失败", e);
        }
        return agencySellProList;
    }



    

}
