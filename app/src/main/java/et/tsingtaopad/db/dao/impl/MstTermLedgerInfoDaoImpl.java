package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstTermLedgerInfoDao;
import et.tsingtaopad.db.table.MstTermLedgerInfo;
import et.tsingtaopad.main.visit.termtz.domain.MstTzTermInfo;
import et.tsingtaopad.main.visit.termtz.domain.TzGridAreaInfo;
import et.tsingtaopad.main.visit.termtz.domain.TzTermProInfo;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstTermLedgerInfoDaoImpl extends BaseDaoImpl<MstTermLedgerInfo, String> implements MstTermLedgerInfoDao {

    public MstTermLedgerInfoDaoImpl(ConnectionSource
                        connectionSource) throws SQLException {
        super(connectionSource, MstTermLedgerInfo.class);
    }

    /**
	 * 根据经销商主键删除记录
	 * 
	 * @param helper
	 * @param agencykey 经销商主键
	 */
	@Override
	public void deleteRecord(SQLiteOpenHelper helper, String agencykey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_TERMLEDGER_INFO WHERE agencykey = ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { agencykey });
		
	}

	/**
	 * 根据经销商主键终端主键产品主键,更改进货量
	 * 
	 * @param helper
	 * @param agencykey   经销商主键
	 * @param terminalkey 终端主键
	 * @param productkey  产品主键
	 */
	@Override
	public void updataPurchase(SQLiteOpenHelper helper,String purchase, String agencykey,
			String terminalkey, String productkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_TERMLEDGER_INFO SET purchase = ? WHERE agencykey = ? AND terminalkey = ? AND productkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {purchase,agencykey,terminalkey,productkey});
		
	}

	/**
	 * 查询所有需要上传的记录
	 * 
	 * @param helper
	 * @param padisconsistent 是否需要上传(0需要上传 , 1不需要上传)
	 * @return
	 */
	@Override
	public ArrayList<MstTermLedgerInfo> getNeedUpAll(SQLiteOpenHelper helper,
			String padisconsistent,String terminalkey,String agencykey) {
		ArrayList<MstTermLedgerInfo> lst = new ArrayList<MstTermLedgerInfo>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_TERMLEDGER_INFO where padisconsistent = ? and terminalkey = ? and agencykey = ?");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {padisconsistent,terminalkey,agencykey});
		MstTermLedgerInfo mstTermLedgerInfo;
		while (cursor.moveToNext()) {
			mstTermLedgerInfo = new MstTermLedgerInfo();
			mstTermLedgerInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTermLedgerInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTermLedgerInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTermLedgerInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTermLedgerInfo.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			
			mstTermLedgerInfo.setAgencykey(cursor.getString(cursor.getColumnIndex("agencykey")));
			mstTermLedgerInfo.setAgencycode(cursor.getString(cursor.getColumnIndex("agencycode")));
			mstTermLedgerInfo.setAgencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
			mstTermLedgerInfo.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			mstTermLedgerInfo.setProcode(cursor.getString(cursor.getColumnIndex("procode")));
			mstTermLedgerInfo.setProname(cursor.getString(cursor.getColumnIndex("proname")));
			mstTermLedgerInfo.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			
			mstTermLedgerInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTermLedgerInfo.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
			mstTermLedgerInfo.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
			mstTermLedgerInfo.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
			mstTermLedgerInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTermLedgerInfo.setPurchase(cursor.getString(cursor.getColumnIndex("purchase")));
			mstTermLedgerInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			
			mstTermLedgerInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTermLedgerInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTermLedgerInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTermLedgerInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			
			mstTermLedgerInfo.setPurchasetime(cursor.getString(cursor.getColumnIndex("purchasetime")));
			
			lst.add(mstTermLedgerInfo); 
		}
		return lst;
	}

	/**
	 * 根据经销商主键终端主键产品主键  更新padisconsistent上传状态
	 * (根据经销商主键终端主键产品主键,更新上传状态为0(需要上传))
	 * 
	 * @param helper
	 * @param agencykey   经销商主键
	 * @param terminalkey 终端主键
	 * @param productkey  产品主键
	 */
	@Override
	public void updataPadisConsis(SQLiteOpenHelper helper, String padisconsistent, String agencykey,
			String terminalkey, String productkey) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_TERMLEDGER_INFO SET padisconsistent = ? WHERE agencykey = ? AND terminalkey = ? AND productkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {padisconsistent,agencykey,terminalkey,productkey});
		
	}

	/**
	 * 根据销商主键终端主键产品主键   更改上传成功状态yesup
	 * 
	 * 0上传成功  1上传失败
	 * 
	 * @param helper
	 * @param yesup       0上传成功  1上传失败
	 * @param agencykey   经销商主键
	 * @param terminalkey 终端主键
	 * @param productkey  产品主键
	 */
	@Override
	public void updataYesUp(SQLiteOpenHelper helper, String yesup, String agencykey,
			String terminalkey, String productkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_TERMLEDGER_INFO SET yesup = ? WHERE agencykey = ? AND terminalkey = ? AND productkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {yesup,agencykey,terminalkey,productkey});
		
	}

	/**
	 * 根据经销商主键,查询所有终端数据(注意排序)
	 * 
	 * @param helper
	 * @param agencykey 经销商主键
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermAll(SQLiteOpenHelper helper,
												 String agencykey) {
		
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_TERMLEDGER_INFO where agencykey = ? GROUP BY terminalkey ORDER BY firstzm");
		//buffer.append("select * from MST_TERMLEDGER_INFO where agencykey = ? GROUP BY terminalkey  ORDER BY  convert(terminalname USING gbk) COLLATE gbk_chinese_ci asc ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	/**
	 * 根据终端主键,查询此终端下产品
	 * 
	 * @param helper
	 * @param terminalkey 终端主键
	 * @param agencykey   经销商主键
	 * @return
	 */
	@Override
	public ArrayList<TzTermProInfo> queryTermProAll(SQLiteOpenHelper helper,
													String terminalkey, String agencykey) {
		ArrayList<TzTermProInfo> lst = new ArrayList<TzTermProInfo>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_TERMLEDGER_INFO where terminalkey = ? AND agencykey = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {terminalkey,agencykey});
		TzTermProInfo tzTermProInfo;
		while (cursor.moveToNext()) {
			tzTermProInfo = new TzTermProInfo();
			tzTermProInfo.setProname(cursor.getString(cursor.getColumnIndex("proname")));
			tzTermProInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			tzTermProInfo.setPurchase(Integer.parseInt(FunUtil.isBlankOrNullTo(cursor.getString(cursor.getColumnIndex("purchase")),"0")));
			//tzTermProInfo.setPurchase(Integer.parseInt(cursor.getString(cursor.getColumnIndex("purchase"))));
			tzTermProInfo.setPurchasetime(cursor.getString(cursor.getColumnIndex("purchasetime")));
			tzTermProInfo.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			tzTermProInfo.setAgencykey(cursor.getString(cursor.getColumnIndex("agencykey")));
			
			lst.add(tzTermProInfo); 
		}
		return lst;
	}

	/**
	 * 根据终端名称,模糊查询终端数据
	 * 
	 * @param helper
	 * @param terminalname
	 * @param agencykey
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermByName(SQLiteOpenHelper helper,
			String terminalname, String agencykey) {
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where agencykey = ? AND terminalname ");
		buffer.append(" LIKE '%" + terminalname + "%' GROUP BY terminalkey ORDER BY firstzm");
		
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	/**
	 * 根据经销商主键,查询所以区域名称定格名称,以定格主键分组
	 * 
	 * @param helper
	 * @param padisconsistent 是否需要上传(0需要上传 , 1不需要上传)
	 * @return
	 */
	@Override
	public ArrayList<TzGridAreaInfo> queryAreaGridAll(
			SQLiteOpenHelper helper, String agencykey) {
		ArrayList<TzGridAreaInfo> lst = new ArrayList<TzGridAreaInfo>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_TERMLEDGER_INFO where agencykey = ? GROUP BY gridkey ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {agencykey});
		TzGridAreaInfo mstTermLedgerInfo;
		while (cursor.moveToNext()) {
			mstTermLedgerInfo = new TzGridAreaInfo();
			mstTermLedgerInfo.setAreaid(cursor.getString(cursor.getColumnIndex("areaid")));
			mstTermLedgerInfo.setAreaname(cursor.getString(cursor.getColumnIndex("areaname")));
			mstTermLedgerInfo.setGridkey(cursor.getString(cursor.getColumnIndex("gridkey")));
			mstTermLedgerInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			lst.add(mstTermLedgerInfo); 
		}
		return lst;
	}

	/**
	 * 根据区域名称定格名称,查询终端
	 * 
	 * @param helper
	 * @param areaname
	 * @param gridname
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermByAreaGrid(DatabaseHelper helper,
			String areaname, String gridname,String agencykey) {
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where areaname = ? AND gridname = ? AND agencykey = ? GROUP BY terminalkey ORDER BY firstzm ");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {areaname,gridname,agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	/**
	 * 根据记录下载时间,删除一条记录
	 * 
	 * @param helper
	 * @param downTime
	 */
	@Override
	public void deleteRecordByDowntime(DatabaseHelper helper, String downTime) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM MST_TERMLEDGER_INFO WHERE downdate <> ?");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] { downTime });
	}

	/**
	 * 根据定格名称  以及终端名称 模糊查询终端列表
	 * 
	 * @param helper
	 * @param areaname 区域
	 * @param gridname 定格
	 * @param //termName 终端名称
	 * @param agencykey 经销商主键
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermByAreaGridAndname(
			DatabaseHelper helper, String areaname, String gridname,
			String terminalname, String agencykey) {
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where areaname = ? AND gridname = ? AND agencykey = ? AND terminalname ");
		buffer.append(" LIKE '%" + terminalname + "%' GROUP BY terminalkey ORDER BY firstzm");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {areaname,gridname,agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	/**
	 * 根据经销商主键终端主键产品主键  更新purchasetime进货时间
	 * 
	 * @param helper
	 * @param agencykey   经销商主键
	 * @param terminalkey 终端主键
	 * @param productkey  产品主键
	 */
	@Override
	public void updataPurchasetime(SQLiteOpenHelper helper, String purchasetime,
			String agencykey, String terminalkey, String productkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE MST_TERMLEDGER_INFO SET purchasetime = ? WHERE agencykey = ? AND terminalkey = ? AND productkey = ? ");
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(buffer.toString(), new String[] {purchasetime,agencykey,terminalkey,productkey});
		
		
	}

	/**
	 * 通过区域定格名称 获取该定格下的路线
	 * 
	 * @param areaname
	 * @param gridname
	 * @param agencykey
	 * @return
	 */
	@Override
	public ArrayList<String> queryAllRouteByAreaGrid(DatabaseHelper helper,
			String areaname, String gridname, String agencykey) {
		
		ArrayList<String> lst = new ArrayList<String>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where areaname = ? AND gridname = ? AND agencykey = ? GROUP BY routename ORDER BY routename ");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {areaname,gridname,agencykey});
		while (cursor.moveToNext()) {
			lst.add(cursor.getString(cursor.getColumnIndex("routename"))); 
		}
		return lst;
	}

	/**
	 * 通过区域定格名称及线路 获取终端
	 * 
	 * @param areaname
	 * @param gridname
	 * @param selectroute
	 * @param agencykey
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermByAreaGridAndRoutename(
			DatabaseHelper helper, String areaname, String gridname,
			String routename, String agencykey) {
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where areaname = ? AND gridname = ? AND routename = ? AND agencykey = ? GROUP BY terminalkey ORDER BY firstzm ");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {areaname,gridname,routename,agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	/**
	 * 通过区域定格名称及线路及终端名称  获取终端(模糊查询)
	 * 
	 * @param helper
	 * @param areaname
	 * @param gridname
	 * @param termName
	 * @param routename
	 * @param agencykey
	 * @return
	 */
	@Override
	public ArrayList<MstTzTermInfo> queryTermByAreaGridAndTermnameAndRoutename(
			DatabaseHelper helper, String areaname, String gridname,
			String termName, String routename, String agencykey) {
		
		ArrayList<MstTzTermInfo> lst = new ArrayList<MstTzTermInfo>();

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select * from MST_TERMLEDGER_INFO where areaname = ? AND gridname = ? AND routename = ? AND agencykey = ? AND terminalname ");
		buffer.append(" LIKE '%" + termName + "%' GROUP BY terminalkey ORDER BY firstzm");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(),new String[] {areaname,gridname,routename,agencykey});
		MstTzTermInfo mstTzTermInfo;
		while (cursor.moveToNext()) {
			mstTzTermInfo = new MstTzTermInfo();
			mstTzTermInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			mstTzTermInfo.setTerminalcode(cursor.getString(cursor.getColumnIndex("terminalcode")));
			mstTzTermInfo.setTerminalname(cursor.getString(cursor.getColumnIndex("terminalname")));
			mstTzTermInfo.setSequence(cursor.getString(cursor.getColumnIndex("sequence")));
			mstTzTermInfo.setGridname(cursor.getString(cursor.getColumnIndex("gridname")));
			mstTzTermInfo.setYesup(cursor.getString(cursor.getColumnIndex("yesup")));
			mstTzTermInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));
			mstTzTermInfo.setRoutename(cursor.getString(cursor.getColumnIndex("routename")));
			mstTzTermInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			mstTzTermInfo.setContact(cursor.getString(cursor.getColumnIndex("contact")));
			mstTzTermInfo.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
			lst.add(mstTzTermInfo); 
		}
		return lst;
	}

	@Override
	public ArrayList<TzTermProInfo> queryTermProByUpyes(DatabaseHelper helper,
			String terminalkey, String agencykey, String yesup) {

		ArrayList<TzTermProInfo> lst = new ArrayList<TzTermProInfo>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from MST_TERMLEDGER_INFO where terminalkey = ? AND agencykey = ? AND yesup = ? ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[] {terminalkey,agencykey,yesup});
		TzTermProInfo tzTermProInfo;
		while (cursor.moveToNext()) {
			tzTermProInfo = new TzTermProInfo();
			tzTermProInfo.setProname(cursor.getString(cursor.getColumnIndex("proname")));
			tzTermProInfo.setTerminalkey(cursor.getString(cursor.getColumnIndex("terminalkey")));
			tzTermProInfo.setPurchase(Integer.parseInt(FunUtil.isBlankOrNullTo(cursor.getString(cursor.getColumnIndex("purchase")),"0")));
			//tzTermProInfo.setPurchase(Integer.parseInt(cursor.getString(cursor.getColumnIndex("purchase"))));
			tzTermProInfo.setPurchasetime(cursor.getString(cursor.getColumnIndex("purchasetime")));
			tzTermProInfo.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			tzTermProInfo.setAgencykey(cursor.getString(cursor.getColumnIndex("agencykey")));
			
			lst.add(tzTermProInfo); 
		}
		return lst;
	
	}

}
