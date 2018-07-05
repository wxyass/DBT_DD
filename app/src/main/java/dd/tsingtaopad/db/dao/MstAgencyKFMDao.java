package dd.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;

import dd.tsingtaopad.db.table.MstAgencyKFM;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 经销商开发Dao层</br>
 */
public interface MstAgencyKFMDao extends Dao<MstAgencyKFM, String> {

	/**
	 * 查询经销商开发表所有记录
	 * 
	 * @param helper
	 * @param status 0有效经销商  1无效经销商
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryMstAgencyKFMLst(SQLiteOpenHelper helper, String status);
	/**
	 * 查询二级区域下的 经销商开发
	 *
	 * @param helper
	 * @param areaid 二级区域ID
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryZsMstAgencyKFMLst(SQLiteOpenHelper helper, String areaid);

	/**
	 * 更新一条经销商开发记录
	 * (根据经销商开发主键,更新上传状态为1(已上传))
	 * @param helper
	 * @param //camerakey
	 */
	public void updataUploadbyAgencykfkey(
            SQLiteOpenHelper helper, String agencykfkey);
	/**
	 * 更新一条经销商开发有效记录
	 * (根据经销商开发主键,更新有效状态为1(无效))
	 * @param helper
	 * @param //camerakey
	 */
	public void updatastatusbyAgencykfkey(
            SQLiteOpenHelper helper, String agencykfkey);
	
	/**
	 * 根据是否上传状态 查询所有未上传表记录
	 * @param helper
	 * @param upload 0未上传    1,null已上传
	 */
	public ArrayList<MstAgencyKFM> queryMstAgencyKFMLstbynotupload(
            SQLiteOpenHelper helper, String upload);
	
	
	/**
	 * 删除一条经销商开发记录
	 * 
	 * @param helper
	 * @param //localpath
	 * @param //camerakey
	 */
	public void deleteAgencykfRecord(
            SQLiteOpenHelper helper, String agencykfkey);
	
	
	/**
	 * 根据经销商名称 模糊查询
	 * 
	 * @param helper
	 * @param AgencyName
	 * @return
	 */
	public ArrayList<MstAgencyKFM> queryAgencykfbyAgencyName(
            SQLiteOpenHelper helper, String AgencyName);

}
