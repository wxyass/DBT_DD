package et.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MstProductMDao;
import et.tsingtaopad.db.table.MstProductM;
import et.tsingtaopad.dd.ddzs.zsterm.zsselect.domain.ProSellStc;
import et.tsingtaopad.initconstvalues.domain.KvStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 青啤产品信息主表Dao层</br>
 */
public class MstProductMDaoImpl extends BaseDaoImpl<MstProductM, String> implements MstProductMDao {

	public MstProductMDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, MstProductM.class);
	}

	/**
	 * 获取指标状态查询中的可选择的产品数据
	 * 
	 * @param helper
	 * @param //gridkey 定格主键
	 * @return
	 */
	@Override
	public List<KvStc> getIndexPro(DatabaseHelper helper) {
		List<KvStc> proLst = new ArrayList<KvStc>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select mpm.productkey,mpm.proname from MST_PRODUCTAREA_INFO mpi ");
		buffer.append("join mst_product_m mpm on mpi.productkey = mpm.productkey ");
		buffer.append(" and coalesce(mpi.status,'0') != '1' ");
		buffer.append(" and coalesce(mpm.status,'0') != '1' ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), null);
		while (cursor.moveToNext()) {
			KvStc pro = new KvStc();
			pro.setKey(cursor.getString(cursor.getColumnIndex("productkey")));
			pro.setValue(cursor.getString(cursor.getColumnIndex("proname")));
			proLst.add(pro);
		}
		return proLst;
	}

	/**
	 * 获取指标状态查询中的可选择的产品数据
	 *
	 * @param helper
	 * @param //gridkey 定格主键
	 * @return
	 */
	@Override
	public List<KvStc> getProductData(DatabaseHelper helper) {
		List<KvStc> proLst = new ArrayList<KvStc>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select mpm.[productkey],mpm.[procode],mpm.[proname] from MST_PRODUCT_M mpm ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), null);
		while (cursor.moveToNext()) {
			KvStc pro = new KvStc();
			pro.setKey(cursor.getString(cursor.getColumnIndex("productkey")));
			pro.setValue(cursor.getString(cursor.getColumnIndex("proname")));
			proLst.add(pro);
		}
		return proLst;
	}
	/**
	 * 查询常用我品 添加渠道价 零售价
	 *
	 * @param helper
	 * @return
	 */
	@Override
	public List<ProSellStc> getAllProductData(DatabaseHelper helper) {
		List<ProSellStc> proLst = new ArrayList<ProSellStc>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select mpm.[productkey],mpm.[procode],mpm.[proname] from MST_PRODUCT_M mpm ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), null);
		while (cursor.moveToNext()) {
			ProSellStc pro = new ProSellStc();
			pro.setKey(cursor.getString(cursor.getColumnIndex("productkey")));
			pro.setValue(cursor.getString(cursor.getColumnIndex("proname")));
			proLst.add(pro);
		}
		return proLst;
	}
	/**
	 * 获取竞品数据
	 *
	 * @param helper
	 * @param //gridkey 定格主键
	 * @return
	 */
	@Override
	public List<KvStc> getVieProductData(DatabaseHelper helper) {
		List<KvStc> proLst = new ArrayList<KvStc>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("select mpm.[cmpproductkey],mpm.[cmpproductcode],mpm.[cmpproname] " );
		buffer.append(" from MST_CMPRODUCTINFO_M mpm order by mpm.cmpbrandkey ");
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), null);
		while (cursor.moveToNext()) {
			KvStc pro = new KvStc();
			pro.setKey(cursor.getString(cursor.getColumnIndex("cmpproductkey")));
			pro.setValue(cursor.getString(cursor.getColumnIndex("cmpproname")));
			proLst.add(pro);
		}
		return proLst;
	}

}
