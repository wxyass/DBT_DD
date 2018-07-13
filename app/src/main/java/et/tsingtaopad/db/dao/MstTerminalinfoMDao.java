package et.tsingtaopad.db.dao;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import et.tsingtaopad.db.DatabaseHelper;
import et.tsingtaopad.db.table.MstTerminalinfoM;
import et.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import et.tsingtaopad.main.visit.shopvisit.term.domain.MstTermListMStc;
import et.tsingtaopad.main.visit.shopvisit.term.domain.TermSequence;
import et.tsingtaopad.main.visit.shopvisit.termvisit.checkindex.domain.TerminalName;
import et.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;


/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 终端表的DAO层</br>
 */
public interface MstTerminalinfoMDao extends Dao<MstTerminalinfoM, String> {

    /**
     * 获取某线路下的终端列表 (业代拜访,某条路线)
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermLst(SQLiteOpenHelper helper, String lineId);
    /**
     * 获取某线路下的终端列表(协同拜访,某条路线)
     * <p>
     * 用于：巡店拜访  -- 终端选择(2018年3月21日16:50:54 新加 )
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<XtTermSelectMStc> queryLineTermLst(SQLiteOpenHelper helper, String lineId);
    /**
     *  (终端追溯,某条路线) 选择终端界面
     * <p>
     * 用于：终端追溯  -- 终端选择  (2018年5月2日新加 )
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<XtTermSelectMStc> queryZsLineTermLst(SQLiteOpenHelper helper, String lineId);
    /**
     *  (终端追溯,某条路线) 选择终端界面
     * <p>
     * 用于：终端追溯  -- 终端选择  (2018年5月2日新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryZsTestLineTermLst(SQLiteOpenHelper helper);


    /**
     * 获取购物车的终端列表
     * <p>
     * 用于：巡店拜访  -- 终端选择 (2018年3月23日16:50:54 新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryCartTermLst(SQLiteOpenHelper helper);
    /**
     * 获取购物车的终端列表
     * <p>
     * 用于：巡店+追溯  -- 终端选择 (2018年3月23日16:50:54 新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryAllCartTermLst(SQLiteOpenHelper helper);
    /**
     * 获取购物车的终端列表
     * <p>
     * 用于：终端追溯  -- 终端选择 (2018年5月2日新加 )
     *
     * @param helper
     * @return
     */
    public List<XtTermSelectMStc> queryZsCartTermLst(SQLiteOpenHelper helper);

    /***
     * 通过终端名称查询终端集合（模糊查询） (同时关联路线表,查看这个终端是那条路线的)
     * @param helper
     * @param termName
     * @return
     */
    public List<MstTermListMStc> getTermListByName(SQLiteOpenHelper helper, String termName);

    /**
     * 获取某线路下的各终端当天的所有拜访进店及离店时间
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermVistTime(SQLiteOpenHelper helper, String lineId);

    /**
     * 获取某线路下的当天已上传终端
     * <p>
     * 用于：巡店拜访  -- 终端选择
     *
     * @param helper
     * @param lineId 线路主键
     * @return
     */
    public List<MstTermListMStc> queryTermUpflag(SQLiteOpenHelper helper, String lineId);

    /**
     * 依据终端主键获取终端 信息（包含数据字典对应的名称）
     *
     * @param helper
     * @param termId 终端主键
     * @return
     */
    public MstTerminalInfoMStc findById(SQLiteOpenHelper helper, String termId);

    /**
     * 通过终端key,查询大区id,二级区域id,定格key,路线key
     * @param helper
     * @param termId
     * @return
     */
    public MstTerminalInfoMStc findKeyById(SQLiteOpenHelper helper, String termId);

    /**
     * 依据终端主键获取终端 信息（包含数据字典对应的名称）
     * <p>
     * 上传图片时,由于之前的方法(findById)出错,所以重写,注释掉会出错的代码
     *
     * @param helper
     * @param termId 终端主键
     * @return
     */
    public MstTerminalInfoMStc findByTermId(SQLiteOpenHelper helper, String termId);

    /***
     *  修改终端顺序
     * @param helper
     * @param list
     */
    public void updateTermSequence(SQLiteOpenHelper helper, List<TermSequence> list);
    /***
     *  修改协同购物车表顺序
     * @param helper
     * @param list
     */
    public void updateXtTempSequence(SQLiteOpenHelper helper, List<TermSequence> list);
    /***
     *  修改追溯购物车表顺序
     * @param helper
     * @param list
     */
    public void updateZsTempSequence(SQLiteOpenHelper helper, List<TermSequence> list);

    /**
     * 获取名称
     *
     * @param databaseHelper
     * @param termId
     * @return
     */
    public TerminalName findByIdName(DatabaseHelper databaseHelper, String termId);

    /**
     * @param helper
     * @param string
     * @param string2
     * @return
     */
    public List<MstTermListMStc> queryTermVistTimeByLoginDate(DatabaseHelper helper, String string, String string2);

}
