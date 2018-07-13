package et.tsingtaopad.core.view.refresh;

/**
 * 数据接口的回调
 * Created by wxyass.
 */
public interface UltraRefreshListener {

    //下拉刷新
    void onRefresh();

    //上拉加载
    void addMore();
}
