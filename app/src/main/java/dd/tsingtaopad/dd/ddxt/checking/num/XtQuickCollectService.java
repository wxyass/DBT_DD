package dd.tsingtaopad.dd.ddxt.checking.num;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtQuicklyProItem;
import dd.tsingtaopad.dd.ddxt.shopvisit.XtShopVisitService;

/**
 * 文件名：XtShopVisitService.java</br>
 * 功能描述: </br>
 */
public class XtQuickCollectService extends XtShopVisitService {

    private final String TAG = "XtCheckIndexService";

    public XtQuickCollectService(Context context, Handler handler) {
        super(context, handler);
    }

    /**
     * 初始化快速采集所需要数据(将产品按采集项归类)
     *
     * @param proItemLst 分项采集部分的产品指标对应的采集项目数据
     * @return
     */
    public List<XtQuicklyProItem> initQuicklyProItem(List<XtProItem> proItemLst) {

        List<XtQuicklyProItem> quicklyLst = new ArrayList<XtQuicklyProItem>();
        if (!CheckUtil.IsEmpty(proItemLst)) {
            int index;
            XtQuicklyProItem quicklyItem;
            List<String> itemIdLst = new ArrayList<String>();
            for (XtProItem item : proItemLst) {
                if (!itemIdLst.contains(item.getItemId())) {
                    quicklyItem = new XtQuicklyProItem();
                    quicklyItem.setItemId(item.getItemId());
                    quicklyItem.setItemName(item.getItemName());
                    quicklyItem.setProItemLst(new ArrayList<XtProItem>());
                    quicklyLst.add(quicklyItem);
                    itemIdLst.add(item.getItemId());
                }
                index = itemIdLst.indexOf(item.getItemId());
                quicklyItem = quicklyLst.get(index);
                quicklyItem.getProItemLst().add(item);
            }
        }

        return quicklyLst;
    }

}
