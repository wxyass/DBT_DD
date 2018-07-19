package et.tsingtaopad.business.visit;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.base.BaseFragmentSupport;
import et.tsingtaopad.business.visit.bean.AreaGridRoute;
import et.tsingtaopad.core.net.HttpUrl;
import et.tsingtaopad.core.net.RestClient;
import et.tsingtaopad.core.net.callback.IError;
import et.tsingtaopad.core.net.callback.IFailure;
import et.tsingtaopad.core.net.callback.ISuccess;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.core.util.dbtutil.PropertiesUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.db.table.CmmAreaM;
import et.tsingtaopad.db.table.CmmDatadicM;
import et.tsingtaopad.db.table.MitValcheckterM;
import et.tsingtaopad.db.table.MstAgencyKFM;
import et.tsingtaopad.db.table.MstAgencyinfoM;
import et.tsingtaopad.db.table.MstAgencyvisitM;
import et.tsingtaopad.db.table.MstCmpbrandsM;
import et.tsingtaopad.db.table.MstCmpcompanyM;
import et.tsingtaopad.db.table.MstCmproductinfoM;
import et.tsingtaopad.db.table.MstGridM;
import et.tsingtaopad.db.table.MstInvoicingInfo;
import et.tsingtaopad.db.table.MstMarketareaM;
import et.tsingtaopad.db.table.MstPictypeM;
import et.tsingtaopad.db.table.MstProductM;
import et.tsingtaopad.db.table.MstPromoproductInfo;
import et.tsingtaopad.db.table.MstPromotionsM;
import et.tsingtaopad.db.table.MstRouteM;
import et.tsingtaopad.db.table.MstVisitauthorizeInfo;
import et.tsingtaopad.db.table.PadCheckstatusInfo;
import et.tsingtaopad.home.app.MainService;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 同步基础数据
 * Created by yangwenmin on 2018/3/12.
 */

public class SyncBasicFragment extends BaseFragmentSupport {

    private final String TAG = "SyncBasicFragment";

    public static final int SYNC_SUCCSE = 1101;// 开始请求
    public static final int SYNC_START = 1102;// 弹出进度弹窗
    public static final int SYNC_CLOSE = 1103;// 请求结束 关闭进度条

    MainService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_downapk, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new MyHandler(this);
        initData();
    }

    // 初始化数据
    private void initData() {
        service = new MainService(getActivity(), null);
        startSyncInfo();
    }

    MyHandler handler;

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<SyncBasicFragment> fragmentRef;

        public MyHandler(SyncBasicFragment fragment) {
            fragmentRef = new SoftReference<SyncBasicFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            SyncBasicFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                case SYNC_SUCCSE:// 开始请求
                    Bundle bundle = msg.getData();
                    String msgdate = (String) bundle.getSerializable("msg");
                    fragment.getJsonProgress(msgdate);
                    break;
                case SYNC_START:// 弹出进度弹窗
                    fragment.showFirstDialog("正在同步数据");
                    break;
                case SYNC_CLOSE:// 请求结束
                    fragment.closeFirstDialog();
                    break;
            }
        }
    }


    private int count;

    private void startSyncInfo() {
        handler.sendEmptyMessage(VisitFragment.SYNC_START); // 弹出进度弹窗
        count = 1;
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    Looper.prepare();
                    getInfo();
                    Looper.loop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private List<String> tablenames;

    // 测试登录网络框架
    private void getInfo() {

        tablenames = new ArrayList<>();


        tablenames.add("MST_PROMOTIONS_M");
        tablenames.add("MST_PROMOPRODUCT_INFO");
        tablenames.add("CMM_DATADIC_M");
        tablenames.add("MST_PICTYPE_M");
        tablenames.add("MST_PRODUCT_M");
        tablenames.add("MST_AGENCYINFO_M");
        // tablenames.add("MIT_VALCHECKTER_M"); 督导模板
        tablenames.add("MST_CMPCOMPANY_M");
        tablenames.add("MST_CMPBRANDS_M");
        tablenames.add("MST_CMPRODUCTINFO_M");
        tablenames.add("MST_AGENCYKF_M");
        tablenames.add("MST_VISITAUTHORIZE_INFO");
        tablenames.add("MST_INVOICING_INFO");
        tablenames.add("MST_AGENCYVISIT_M");






        //tablenames.add("MST_MARKETAREA_GRID_ROUTE_M");

        // tablenames.add("MST_COLLECTIONTEMPLATE_CHECKSTATUS_INFO");

        tablenames.add("MST_MARKETAREA_M");
        tablenames.add("MST_GRID_M");
        tablenames.add("MST_ROUTE_M");
        tablenames.add("MST_CHECKSTATUS_INFO");
        tablenames.add("MST_COLLECTIONTEMPLATE_M");

        tablenames.add("CMM_AREA_M");

        //handler.sendEmptyMessage(FirstFragment.SYNC_SUCCSE);
        Bundle bundle = new Bundle();
        bundle.putString("msg", "开始同步数据");
        Message msg = new Message();
        msg.what = VisitFragment.SYNC_SUCCSE;//
        msg.setData(bundle);
        handler.sendMessage(msg);

    }

    // 请求表数据
    private void getJsonProgress(String msgdata) {

        if (count < tablenames.size() + 1) {
            String tablename = tablenames.get(count - 1);
            ceshiHttp("opt_get_dates2", tablename, getJson(tablename));
            DbtLog.write("发起同步请求: " + tablename + " " + DateUtil.getDateTimeStr(8));
        }

        showFirstDialog(msgdata);// 刷新进度条

        if (count >= tablenames.size() + 1) {
            closeFirstDialog();// 关闭进度条
            Toast.makeText(getActivity(), "同步成功", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 同步表数据
     *
     * @param optcode 请求码
     * @param table   请求表名(请求不同的)
     * @param content 请求json
     */
    void ceshiHttp(final String optcode, final String table, String content) {

        // 组建请求Json
        RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(getContext());
        requestHeadStc.setOptcode(PropertiesUtil.getProperties(optcode));
        RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, content);

        // 压缩请求数据
        String jsonZip = HttpParseJson.parseRequestJson(reqObj);

        RestClient.builder()
                .url(HttpUrl.IP_END)
                .params("data", jsonZip)
                //.loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        count++;
                        String json = HttpParseJson.parseJsonResToString(response);
                        ResponseStructBean resObj = new ResponseStructBean();
                        resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                        // 保存登录信息
                        if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                            DbtLog.write("返回同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
                            // 写入数据库
                            parseTableAll(table, resObj);

                        } else {
                            Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = VisitFragment.SYNC_CLOSE;//
                            handler.sendMessage(msg);
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        Message msg1 = new Message();
                        msg1.what = VisitFragment.SYNC_CLOSE;//
                        handler.sendMessage(msg1);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        Message msg2 = new Message();
                        msg2.what = VisitFragment.SYNC_CLOSE;//
                        handler.sendMessage(msg2);
                    }
                })
                .builde()
                .post();
    }

    private String getJson(String tablename) {
        String json = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "tablename:'" + tablename + "'" +
                "}";
        return json;
    }

    private AlertDialog dialog;

    /**
     * 展示滚动条
     */
    public void showFirstDialog(String msgdata) {

        if (dialog != null) {
            ProgressBar progress1 = dialog.findViewById(R.id.progressbar_visit_sync_1);
            TextView text1 = dialog.findViewById(R.id.dialog_visit_tv_sync);
            progress1.setProgress(count * (int) Math.floor(100 / tablenames.size()));//设置当前进度
            text1.setText(msgdata);
            dialog.setCancelable(false); // 是否可以通过返回键 关闭
            dialog.show();
        } else {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.sync_visit_progress, null);
            TextView dialog_tv_sync = (TextView) view.findViewById(R.id.dialog_visit_tv_sync);
            // ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar_sync_1);
            dialog = new AlertDialog.Builder(getActivity()).setCancelable(false).create();
            dialog_tv_sync.setText(msgdata);
            dialog.setView(view, 0, 0, 0, 0);
            dialog.setCancelable(false); // 是否可以通过返回键 关闭
            dialog.show();
        }
    }

    // 关闭滚动条
    public void closeFirstDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        supportFragmentManager.popBackStack();
    }

    private void parseTableAll(String table, ResponseStructBean resObj) {
        // 保存信息
        if ("MST_MARKETAREA_GRID_ROUTE_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载区域数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            parseTableJson(formjson);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("MST_COLLECTIONTEMPLATE_CHECKSTATUS_INFO".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载指标数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            parseIndexTableJson(formjson);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        }

        // --------------------------------------------------------------
        else if ("MST_MARKETAREA_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载区域数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String mst_marketarea_m = emp.getMST_MARKETAREA_M();
            service.createOrUpdateTable(mst_marketarea_m, "MST_MARKETAREA_M", MstMarketareaM.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        }
        else if ("MST_GRID_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载定格数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String mst_grid_m = emp.getMST_GRID_M();
            service.createOrUpdateTable(mst_grid_m, "MST_GRID_M", MstGridM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        }
        else if ("MST_ROUTE_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载路线数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String mst_route_m = emp.getMST_ROUTE_M();
            service.createOrUpdateTable(mst_route_m, "MST_ROUTE_M", MstRouteM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        }
        else if ("MST_CHECKSTATUS_INFO".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载指标字典数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String PAD_CHECKSTATUS_INFO = emp.getMST_CHECKSTATUS_INFO();
            service.createOrUpdateTable(PAD_CHECKSTATUS_INFO, "PAD_CHECKSTATUS_INFO", PadCheckstatusInfo.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        }
        else if ("MST_COLLECTIONTEMPLATE_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载指标数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_COLLECTIONTEMPLATE_M = emp.getMST_COLLECTIONTEMPLATE_M();
            service.parsePadCheckType(MST_COLLECTIONTEMPLATE_M);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        }

        else if ("CMM_AREA_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载区域数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String CMM_AREA_M = emp.getCMM_AREA_M();
            service.createOrUpdateTable(CMM_AREA_M, "CMM_AREA_M", CmmAreaM.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("CMM_DATADIC_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载字典数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);


            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String CMM_DATADIC_M = emp.getCMM_DATADIC_M();
            service.createOrUpdateTable(CMM_DATADIC_M, "CMM_DATADIC_M", CmmDatadicM.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("MST_PROMOTIONS_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载促销活动数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_PROMOTIONS_M = emp.getMST_PROMOTIONS_M();
            service.createOrUpdateTable(MST_PROMOTIONS_M, "MST_PROMOTIONS_M", MstPromotionsM.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("MST_PROMOPRODUCT_INFO".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载活动产品数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_PROMOPRODUCT_INFO = emp.getMST_PROMOPRODUCT_INFO();
            service.createOrUpdateTable(MST_PROMOPRODUCT_INFO, "MST_PROMOPRODUCT_INFO", MstPromoproductInfo.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("MST_PICTYPE_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载图片类型数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_PICTYPE_M = emp.getMST_PICTYPE_M();
            service.createOrUpdateTable(MST_PICTYPE_M, "MST_PICTYPE_M", MstPictypeM.class);

            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));

        } else if ("MST_PRODUCT_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载产品数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_PRODUCT_M = emp.getMST_PRODUCT_M();
            service.createOrUpdateTable(MST_PRODUCT_M, "MST_PRODUCT_M", MstProductM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_CMPCOMPANY_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载竞品公司数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_CMPCOMPANY_M = emp.getMST_CMPCOMPANY_M();
            service.createOrUpdateTable(MST_CMPCOMPANY_M, "MST_CMPCOMPANY_M", MstCmpcompanyM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_CMPBRANDS_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载竞品品牌数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);


            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_CMPBRANDS_M = emp.getMST_CMPBRANDS_M();
            service.createOrUpdateTable(MST_CMPBRANDS_M, "MST_CMPBRANDS_M", MstCmpbrandsM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_CMPRODUCTINFO_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载竞品产品数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_CMPRODUCTINFO_M = emp.getMST_CMPRODUCTINFO_M();
            service.createOrUpdateTable(MST_CMPRODUCTINFO_M, "MST_CMPRODUCTINFO_M", MstCmproductinfoM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MIT_VALCHECKTER_M".equals(table)) {
            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载督导指标数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MIT_VALCHECKTER_M = emp.getMIT_VALCHECKTER_M();
            service.createOrUpdateTable(MIT_VALCHECKTER_M, "MIT_VALCHECKTER_M", MitValcheckterM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_AGENCYKF_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载经销商开发数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_AGENCYKF_M = emp.getMST_AGENCYKF_M();
            service.createOrUpdateTable(MST_AGENCYKF_M, "MST_AGENCYKF_M", MstAgencyKFM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_AGENCYVISIT_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载经销商拜访数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_AGENCYVISIT_M = emp.getMST_AGENCYVISIT_M();
            service.createOrUpdateTable(MST_AGENCYVISIT_M, "MST_AGENCYVISIT_M", MstAgencyvisitM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_INVOICING_INFO".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载经销商库存盘点数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_INVOICING_INFO = emp.getMST_INVOICING_INFO();
            service.createOrUpdateTable(MST_INVOICING_INFO, "MST_INVOICING_INFO", MstInvoicingInfo.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_VISITAUTHORIZE_INFO".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载定格可拜访经销商数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_VISITAUTHORIZE_INFO = emp.getMST_VISITAUTHORIZE_INFO();
            service.createOrUpdateTable(MST_VISITAUTHORIZE_INFO, "MST_VISITAUTHORIZE_INFO", MstVisitauthorizeInfo.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        } else if ("MST_AGENCYINFO_M".equals(table)) {

            Bundle bundle = new Bundle();
            bundle.putString("msg", "正在下载经销商数据...");
            Message msg = new Message();
            msg.what = VisitFragment.SYNC_SUCCSE;//
            msg.setData(bundle);
            handler.sendMessage(msg);

            String formjson = resObj.getResBody().getContent();
            AreaGridRoute emp = JsonUtil.parseJson(formjson, AreaGridRoute.class);
            String MST_AGENCYINFO_M = emp.getMST_AGENCYINFO_M();
            service.createOrUpdateTable(MST_AGENCYINFO_M, "MST_AGENCYINFO_M", MstAgencyinfoM.class);
            DbtLog.write("解析同步请求: " + table + " " + DateUtil.getDateTimeStr(8));
        }
    }


    // 解析区域定格路线成功
    private void parseTableJson(String json) {
        // 解析区域定格路线信息
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);
        String mst_grid_m = emp.getMST_GRID_M();
        String mst_marketarea_m = emp.getMST_MARKETAREA_M();
        String mst_route_m = emp.getMST_ROUTE_M();

        // MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(mst_grid_m, "MST_GRID_M", MstGridM.class);
        service.createOrUpdateTable(mst_marketarea_m, "MST_MARKETAREA_M", MstMarketareaM.class);
        service.createOrUpdateTable(mst_route_m, "MST_ROUTE_M", MstRouteM.class);
    }

    // 解析指标数据成功
    private void parseIndexTableJson(String json) {
        // 解析指标数据
        AreaGridRoute emp = JsonUtil.parseJson(json, AreaGridRoute.class);

        String PAD_CHECKSTATUS_INFO = emp.getMST_CHECKSTATUS_INFO();
        String MST_COLLECTIONTEMPLATE_M = emp.getMST_COLLECTIONTEMPLATE_M();

        // MainService service = new MainService(getActivity(), null);
        service.createOrUpdateTable(PAD_CHECKSTATUS_INFO, "PAD_CHECKSTATUS_INFO", PadCheckstatusInfo.class);
        service.parsePadCheckType(MST_COLLECTIONTEMPLATE_M);
    }


}
