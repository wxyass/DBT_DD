package dd.tsingtaopad.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.db.table.MstCheckexerecordInfo;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class DBManager {
    private DatabaseHelper helper;  
    private SQLiteDatabase db;  
    private static DBManager dBManager =null;
    public DBManager(Context context) {  
        helper =DatabaseHelper.getHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = helper.getWritableDatabase();  
    }  
     
    public static synchronized DBManager getDBManager(Context context) {

        if (dBManager == null) {
            dBManager = new DBManager(context);
        }
        return dBManager;
    }
 
    
    /** 
     * 向拜访指标执行记录表插入数据
     * @param //persons
     */  
    public void addMstCheckexerecordInfo( List<MstCheckexerecordInfo> mstCheckexerecordInfos)  {
            Log.i("updateData", "更新 " + "MstCheckexerecordInfo "+ " size :" + mstCheckexerecordInfos.size());
            db.beginTransaction();  //开始事务  
           for(MstCheckexerecordInfo mstCheckexerecordInfo:mstCheckexerecordInfos){
            mstCheckexerecordInfo.setPadisconsistent(ConstValues.FLAG_1);
            db.execSQL("INSERT INTO MST_CHECKEXERECORD_INFO(recordkey,visitkey,productkey,checkkey,checktype,acresult,iscom,cstatuskey,isauto,exestatus,startdate,enddate,terminalkey,sisconsistent,scondate,siebelid,resultstatus,padisconsistent,padcondate,comid,remarks,orderbyno,version,credate,creuser,updatetime,updateuser,deleteflag)  VALUES( ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[]{mstCheckexerecordInfo.getRecordkey(),mstCheckexerecordInfo.getVisitkey(),mstCheckexerecordInfo.getProductkey(),mstCheckexerecordInfo.getCheckkey(),
                                                                mstCheckexerecordInfo.getChecktype(),mstCheckexerecordInfo.getAcresult(),mstCheckexerecordInfo.getIscom(),mstCheckexerecordInfo.getCstatuskey(),mstCheckexerecordInfo.getIsauto(),mstCheckexerecordInfo.getExestatus(),mstCheckexerecordInfo.getStartdate(),
                                                                mstCheckexerecordInfo.getEnddate(),mstCheckexerecordInfo.getTerminalkey(),mstCheckexerecordInfo.getSisconsistent(),mstCheckexerecordInfo.getScondate(),mstCheckexerecordInfo.getSiebelid(),mstCheckexerecordInfo.getResultstatus(),mstCheckexerecordInfo.getPadisconsistent(),
                                                                mstCheckexerecordInfo.getPadcondate(),mstCheckexerecordInfo.getComid(),mstCheckexerecordInfo.getRemarks(),mstCheckexerecordInfo.getOrderbyno(),mstCheckexerecordInfo.getVersion(),mstCheckexerecordInfo.getCredate(),mstCheckexerecordInfo.getCreuser(),mstCheckexerecordInfo.getUpdatetime(),mstCheckexerecordInfo.getUpdateuser(),mstCheckexerecordInfo.getDeleteflag()});  
           }
            db.setTransactionSuccessful();  //设置事务成功完成  
            db.endTransaction();    //结束事务  
    } 
    
    
    
      
    
 
    /** 
     * close database 
     */  
    public void closeDB() {  
        db.close();  
    }  
  

}
