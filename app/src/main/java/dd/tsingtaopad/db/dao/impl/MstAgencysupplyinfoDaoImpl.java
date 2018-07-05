package dd.tsingtaopad.db.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.db.dao.MstAgencysupplyInfoDao;
import dd.tsingtaopad.db.table.MstAgencysupplyInfo;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: </br>
 */
public class MstAgencysupplyinfoDaoImpl extends
		BaseDaoImpl<MstAgencysupplyInfo, String> implements MstAgencysupplyInfoDao {

	/**
	 * @param connectionSource
	 * @param //dataClass
	 * @throws SQLException
	 */
	public MstAgencysupplyinfoDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, MstAgencysupplyInfo.class);
	}

	@Override
	public List<MstAgencysupplyInfo> agencysupply(SQLiteOpenHelper helper,String terminalkey) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from mst_agencysupply_info ap, mst_agencyinfo_m am ");
		buffer.append(" where ap.upperkey = am.agencykey and coalesce(ap.deleteflag,'0') != '1'  ");
		buffer.append(" and ap.uppertype != '2' and ap.lowerkey= ? and ap.lowertype = '2' ");
//		buffer.append(" and ap.lowerkey= ? and ap.lowertype = '2' ");
		buffer.append(" and coalesce(ap.status, '0') != '1' and coalesce(am.deleteflag,'0') != '1' ");
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(buffer.toString(), new String[]{terminalkey});
		List<MstAgencysupplyInfo> asList=new ArrayList<MstAgencysupplyInfo>();
		MstAgencysupplyInfo masInfo = null;
		while(cursor.moveToNext()){
			masInfo= new MstAgencysupplyInfo();
			masInfo.setUpperkey(cursor.getString(cursor.getColumnIndex("upperkey")));
			masInfo.setProductkey(cursor.getString(cursor.getColumnIndex("productkey")));
			masInfo.setLowerkey(cursor.getString(cursor.getColumnIndex("lowerkey")));
			masInfo.setStatus(cursor.getString(cursor.getColumnIndex("status")));

			masInfo.setAsupplykey(cursor.getString(cursor.getColumnIndex("asupplykey")));
			masInfo.setComid(cursor.getString(cursor.getColumnIndex("comid")));
			//masInfo.setCredate(cursor.getInt(cursor.getColumnIndex("credate")));
			masInfo.setCreuser(cursor.getString(cursor.getColumnIndex("creuser")));
			masInfo.setDeleteflag(cursor.getString(cursor.getColumnIndex("deleteflag")));
			masInfo.setInprice(cursor.getString(cursor.getColumnIndex("inprice")));

			masInfo.setLowertype(cursor.getString(cursor.getColumnIndex("lowertype")));
			masInfo.setOrderbyno(cursor.getString(cursor.getColumnIndex("orderbyno")));
			//masInfo.setPadcondate(cursor.getString(cursor.getColumnIndex("padcondate")));
			masInfo.setPadisconsistent(cursor.getString(cursor.getColumnIndex("padisconsistent")));

			masInfo.setRemarks(cursor.getString(cursor.getColumnIndex("remarks")));
			masInfo.setReprice(cursor.getString(cursor.getColumnIndex("reprice")));
			//masInfo.setScondate(cursor.getString(cursor.getColumnIndex("scondate")));
			masInfo.setSiebelkey(cursor.getString(cursor.getColumnIndex("siebelkey")));
			masInfo.setSisconsistent(cursor.getString(cursor.getColumnIndex("sisconsistent")));

			//masInfo.setUpdatetime(cursor.getLong(cursor.getColumnIndex("updatetime")));
			masInfo.setUpdateuser(cursor.getString(cursor.getColumnIndex("updateuser")));
			masInfo.setUppertype(cursor.getString(cursor.getColumnIndex("uppertype")));
			//masInfo.setVersion(cursor.getString(cursor.getColumnIndex("version")));
			//masInfo.setaddre(cursor.getString(cursor.getColumnIndex("address")));
			//masInfo.setagencycode(cursor.getString(cursor.getColumnIndex("agencycode")));
			//masInfo.setagencykey(cursor.getString(cursor.getColumnIndex("agencykey")));
			//masInfo.setagencylevel(cursor.getString(cursor.getColumnIndex("agencylevel")));
			//masInfo.setagencyname(cursor.getString(cursor.getColumnIndex("agencyname")));
//			masInfo.setagencyparent(cursor.getString(cursor.getColumnIndex("agencyparent")));
//			masInfo.setagencystatus(cursor.getString(cursor.getColumnIndex("agencystatus")));
//			masInfo.setagencytype(cursor.getString(cursor.getColumnIndex("agencytype")));
//			masInfo.setbeerstartdate(cursor.getString(cursor.getColumnIndex("beerstartdate")));
//			masInfo.setcity(cursor.getString(cursor.getColumnIndex("city")));

			asList.add(masInfo);
		}
		return asList;
	}

	
	
	
}
