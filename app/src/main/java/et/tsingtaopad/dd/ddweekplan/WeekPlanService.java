package et.tsingtaopad.dd.ddweekplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.dao.MitPlandayvalMDao;
import et.tsingtaopad.db.table.MitPlandayM;
import et.tsingtaopad.db.table.MitPlandaydetailM;
import et.tsingtaopad.db.table.MitPlandayvalM;
import et.tsingtaopad.db.table.MitPlanweekM;
import et.tsingtaopad.dd.ddweekplan.domain.DayDetailStc;
import et.tsingtaopad.dd.ddweekplan.domain.DayPlanStc;

/**
 * 项目名称：营销移动智能工作平台 </br>
 * 原因 BUG号 修改人 修改版本</br>
 */
@SuppressLint("DefaultLocale")
public class WeekPlanService {

    private final String TAG = "WeekPlanService";

    private Context context;

    public WeekPlanService(Context context) {
        this.context = context;
    }


    public void saveTable(MitPlanweekM weekplan,
                          DayPlanStc workplanstc,
                          List<DayDetailStc> dayDetailStcs,
                          String weekDateStart,
                          String weekDateEnd) {
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitPlanweekM, String> mitPlanweekMDao = helper.getMitPlanweekMDao();
            Dao<MitPlandayM, String> mitPlandayMDao = helper.getMitPlandayMDao();
            Dao<MitPlandaydetailM, String> mitPlandaydetailMDao = helper.getMitPlandaydetailMDao();
            Dao<MitPlandayvalM, String> mitPlandayvalMDao = helper.getMitPlandayvalMDao();
            MitPlandayvalMDao mitPlandayvalMDao2 = helper.getDao(MitPlandayvalM.class);
            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            MitPlanweekM mitPlanweekM = null;
            // 周计划主表
            //if(weekplan == null){
            if(TextUtils.isEmpty(weekplan.getId())){
                mitPlanweekM = new MitPlanweekM();
                mitPlanweekM.setId(FunUtil.getUUID());
                mitPlanweekM.setStarttime(weekDateStart);
                mitPlanweekM.setEndtime(weekDateEnd);
                //mitPlanweekM.setStatus("3");// 状态 // 0未审核1审核通过2未通过 3已提交
                mitPlanweekM.setCredate(new Date());
                mitPlanweekM.setUpdatedate(new Date());
                mitPlanweekM.setCreuserareaid(PrefUtils.getString(context,"departmentid",""));
                mitPlanweekM.setCreuser(PrefUtils.getString(context,"userid",""));
                mitPlanweekM.setUpdateuser(PrefUtils.getString(context,"userid",""));
                mitPlanweekMDao.create(mitPlanweekM);
            }else{
                mitPlanweekM = weekplan;
            }

            // 日计划主表
            MitPlandayM mitPlandayM = new MitPlandayM();
            mitPlandayM.setId(workplanstc.getPlanKey());// 日计划主键
            mitPlandayM.setPlanweekid(mitPlanweekM.getId());// 周计划主键
            mitPlandayM.setPlandate(workplanstc.getPlandate());// 计划时间
            mitPlandayM.setStatus("1");// // 0:未制定 1等待提交 2待审核 3 审核通过  4 未通过
            mitPlandayMDao.createOrUpdate(mitPlandayM);



            // 日计划详情表
            MitPlandaydetailM mitPlandaydetailM;
            for (DayDetailStc indexItem : dayDetailStcs) {
                mitPlandaydetailM = new MitPlandaydetailM();
                if(indexItem.getDetailkey() == null || "".equals(indexItem.getDetailkey())){
                    mitPlandaydetailM.setId(FunUtil.getUUID());
                }else{
                    mitPlandaydetailM.setId(indexItem.getDetailkey());
                }
                mitPlandaydetailM.setPlanweekid(mitPlanweekM.getId());// // 周计划主键
                mitPlandaydetailM.setPlandayid(mitPlandayM.getId());// 日计划主键
                mitPlandaydetailM.setPlanareaid(indexItem.getValareakey());// 追溯区域
                mitPlandaydetailM.setPlangridid(indexItem.getValgridkey());// 追溯定格
                mitPlandaydetailMDao.createOrUpdate(mitPlandaydetailM);

                // 删除 日计划详情表附表  (在下面会重新添加)
                mitPlandayvalMDao2.deleteByPlanweekid(helper,mitPlandaydetailM.getId());

                // 日计划详情表附表
                MitPlandayvalM mitPlandayvalM;
                /*List<String> valchecknameLv = indexItem.getValchecknameLv();
                for (String s : valchecknameLv){
                    mitPlandayvalM = new MitPlandayvalM();
                    mitPlandayvalM.setId(FunUtil.getUUID());
                    mitPlandayvalM.setPlanweekid(mitPlanweekM.getId());// // 周计划主键
                    mitPlandayvalM.setPlandaydetailid(mitPlandaydetailM.getId());// 日计划详情主键
                    mitPlandayvalM.setPlancomtype("0");// 0是追溯项1是路线
                    mitPlandayvalM.setPlanareaid(indexItem.getValareakey());// 追溯区域
                    mitPlandayvalM.setPlangridid(indexItem.getValgridkey());// 追溯定格
                    mitPlandayvalM.setPlancomid(s);
                    mitPlandayvalMDao.createOrUpdate(mitPlandayvalM);
                }*/
                String valcheckkeys = indexItem.getValcheckkey();
                String[] valchecknameLv = valcheckkeys.split(",");
                for (String s : valchecknameLv){
                    mitPlandayvalM = new MitPlandayvalM();
                    mitPlandayvalM.setId(FunUtil.getUUID());
                    mitPlandayvalM.setPlanweekid(mitPlanweekM.getId());// // 周计划主键
                    mitPlandayvalM.setPlandaydetailid(mitPlandaydetailM.getId());// 日计划详情主键
                    mitPlandayvalM.setPlancomtype("0");// 0是追溯项1是路线
                    mitPlandayvalM.setPlanareaid(indexItem.getValareakey());// 追溯区域
                    mitPlandayvalM.setPlangridid(indexItem.getValgridkey());// 追溯定格
                    mitPlandayvalM.setPlancomid(s);
                    mitPlandayvalMDao.createOrUpdate(mitPlandayvalM);
                }

                String valroutekeys = indexItem.getValroutekeys();
                String[] valroutes = valroutekeys.split(",");
                for (String s : valroutes){
                    mitPlandayvalM = new MitPlandayvalM();
                    mitPlandayvalM.setId(FunUtil.getUUID());
                    mitPlandayvalM.setPlanweekid(mitPlanweekM.getId());// // 周计划主键
                    mitPlandayvalM.setPlandaydetailid(mitPlandaydetailM.getId());// 日计划详情主键
                    mitPlandayvalM.setPlancomtype("1");// 0是追溯项1是路线
                    mitPlandayvalM.setPlanareaid(indexItem.getValareakey());// 追溯区域
                    mitPlandayvalM.setPlangridid(indexItem.getValgridkey());// 追溯定格
                    mitPlandayvalM.setPlancomid(s);
                    mitPlandayvalMDao.createOrUpdate(mitPlandayvalM);
                }
            }
            connection.commit(null);
        } catch (Exception e) {
            Log.e(TAG, "保存追溯查指标数据发生异常", e);
            //ViewUtil.sendMsg(context, R.string.checkindex_save_fail);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚追溯查指标数据发生异常", e1);
            }
        }
    }

    /**
     * 删除经销商与终端的产品供应关系
     *
     * @param dayDetailStc 拜访产品-竞品我品记录表主键
     */
    public boolean deleteDayDetail(DayDetailStc dayDetailStc) {
        boolean isFlag=false;
        String key = dayDetailStc.getDetailkey();
        AndroidDatabaseConnection connection = null;
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            Dao<MitPlandaydetailM, String> mitPlandaydetailMDao = helper.getDao(MitPlandaydetailM.class);
            Dao<MitPlandayvalM, String> mitPlandayvalMDao =  helper.getDao(MitPlandayvalM.class);

            connection = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
            connection.setAutoCommit(false);

            // 删除拜访产品-竞品我品记录表，相关数据
            StringBuffer buffer = new StringBuffer();
            buffer.append("delete from MIT_PLANDAYDETAIL_M ");
            buffer.append("where id=? ");
            mitPlandaydetailMDao.executeRaw(buffer.toString(), new String[] {key});

            // 删除拜访产品-竞品我品记录表，相关数据
            buffer = new StringBuffer();
            buffer.append("delete from MIT_PLANDAYVAL_M ");
            buffer.append("where plandaydetailid=? ");
            mitPlandaydetailMDao.executeRaw(buffer.toString(), new String[] {key});




            connection.commit(null);
            isFlag=true;

        } catch (Exception e) {
            isFlag=false;
            DbtLog.logUtils(TAG,"解除供货关系失败");
            DbtLog.logUtils(TAG,e.getMessage());
            e.printStackTrace();
            Log.e(TAG, "保存进销存数据发生异常", e);
            try {
                connection.rollback(null);
            } catch (SQLException e1) {
                Log.e(TAG, "回滚进销存数据发生异常", e1);
            }
        }
        return isFlag;
    }
}
