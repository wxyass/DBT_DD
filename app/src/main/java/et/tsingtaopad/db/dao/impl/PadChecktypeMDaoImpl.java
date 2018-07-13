package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.PadChecktypeMDao;
import et.tsingtaopad.db.table.PadChecktypeM;
import et.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: PAD端采集用指标主表DAO</br>
 */
public class PadChecktypeMDaoImpl extends 
            BaseDaoImpl<PadChecktypeM, String> implements PadChecktypeMDao {
    
    
    public PadChecktypeMDaoImpl(ConnectionSource 
                        connectionSource) throws SQLException {
        super(connectionSource, PadChecktypeM.class);
    }
    
    /**
     * 获取Pad端采集指标的结构数据，
     * 
     * @param helper
     * @param channelId  当前终端次渠道ID
     * @return
     */
    @Override
    public List<KvStc> queryCheckType(
            DatabaseHelper helper, String channelId) {
        
        List<KvStc> indexLst = new ArrayList<KvStc>();
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select distinct m.minorchannel, m.checkkey, m.checkname, ");
        buffer.append("v.cstatuskey, v.cstatusname ");
        buffer.append("from pad_checktype_m m, pad_checkstatus_info v ");
        buffer.append("where m.checkkey = v.checkkey and isproduct = '1' ");
        buffer.append("     and v.ispadshow = '1' and m.minorchannel like '%");
        buffer.append(channelId).append("%' ");
        buffer.append("order by m.minorchannel, m.checkkey, v.orderbyno, v.cstatuskey ");

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        KvStc indexitem = null;
        KvStc valueItem;
        String prevCheckKey = "";
        String checkKey, checkName, cstatusKey,cstatusName;
        while (cursor.moveToNext()) {
            checkKey = cursor.getString(cursor.getColumnIndex("checkkey"));
            checkName = cursor.getString(cursor.getColumnIndex("checkname"));
            cstatusKey = cursor.getString(cursor.getColumnIndex("cstatuskey"));
            cstatusName = cursor.getString(cursor.getColumnIndex("cstatusname"));
            
            if (!prevCheckKey.equals(checkKey)) {
                prevCheckKey = checkKey;
                
                // 指标
                indexitem = new KvStc(checkKey, checkName, "");
                indexitem.setChildLst(new ArrayList<KvStc>());
                indexLst.add(indexitem);

                // 指标值
                valueItem = new KvStc(cstatusKey, cstatusName, checkKey);
                indexitem.getChildLst().add(valueItem);
                
            } else {

                valueItem = new KvStc(cstatusKey, cstatusName, checkKey);
                indexitem.getChildLst().add(valueItem);
            }
        }
        
        return indexLst;
    }
    
    
    /**
     * 获取并组建所有指标、指标值的树级关系
     * 
     * @param helper
     * @param productFlag   关于产品标志，1：只列出与产品相关的指标
     * @return
     */
    public List<KvStc> queryCheckTypeStatus(DatabaseHelper helper, String productFlag){
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("select distinct ct.checkkey, ct.checktype, ct.checkname, ");
        buffer.append("cs.cstatuskey, cs.cstatusname, cs.isdefault ");
        buffer.append("from pad_checktype_m ct, pad_checkstatus_info cs ");
        buffer.append("where ct.checkkey = cs.checkkey ");
        if (ConstValues.FLAG_1.equals(productFlag)) {
            buffer.append(" and ct.isproduct = '1' ");
        }
        buffer.append("order by ct.checkkey, cast(ct.orderbyno as int), cast(cs.orderbyno as int) ");
        
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(buffer.toString(), null);
        List<KvStc> kvLst = new ArrayList<KvStc>(); 
        String checkKey = "";
        String checkType = "";
        KvStc kvItem = new KvStc();
        KvStc kvChild;
        while (cursor.moveToNext()) {
            if (!checkKey.equals(cursor.getString(cursor.getColumnIndex("checkkey")))) {
                kvItem = new KvStc();
                kvItem.setKey(cursor.getString(cursor.getColumnIndex("checkkey")));
                kvItem.setValue(cursor.getString(cursor.getColumnIndex("checkname")));
                checkType = cursor.getString(cursor.getColumnIndex("checktype"));
                if (ConstValues.FLAG_0.equals(checkType) || ConstValues.FLAG_4.equals(checkKey)) {
                    kvItem.getChildLst().add(new KvStc("-1", "请选择", "-1"));
                }
                kvLst.add(kvItem);
                checkKey = kvItem.getKey();
            }
            kvChild = new KvStc();
            kvChild.setKey(cursor.getString(cursor.getColumnIndex("cstatuskey")));
            kvChild.setValue(cursor.getString(cursor.getColumnIndex("cstatusname")));
            kvChild.setIsDefault(cursor.getString(cursor.getColumnIndex("isdefault")));
            kvItem.getChildLst().add(kvChild);
        }
        kvLst.add(0, new KvStc("-1", "请选择", "-1"));
        return kvLst;
    }

}
