package dd.tsingtaopad.business.system;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.ref.SoftReference;

import dd.tsingtaopad.R;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.business.system.about.DdAboutFragment;
import dd.tsingtaopad.business.system.question.QueryBackFragment;
import dd.tsingtaopad.business.system.repwd.RePwdFragment;
import dd.tsingtaopad.business.system.version.DownApkFragment;
import dd.tsingtaopad.business.system.version.VersionService;
import dd.tsingtaopad.core.ui.loader.LatteLoader;
import dd.tsingtaopad.core.util.dbtutil.ConstValues;
import dd.tsingtaopad.core.util.dbtutil.NetStatusUtil;
import dd.tsingtaopad.core.util.dbtutil.PrefUtils;
import dd.tsingtaopad.dd.ddxt.updata.XtUploadService;
import dd.tsingtaopad.home.initadapter.GlobalValues;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class SystemFragment extends BaseFragmentSupport implements View.OnClickListener {

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;

    RelativeLayout repwd;
    RelativeLayout question;
    RelativeLayout uploadApk;
    RelativeLayout about;
    RelativeLayout exit;

    public static final int SHOWDOWNLOADDIALOG = 88; // 弹出进度条,设置最大值100 //显示正在下载的对话框
    public static final int UPDATEDOWNLOADDIALOG = 99;// 设置进度条  //刷新正在下载对话框的内容
    public static final int DOWNLOADFINISHED = 66;// 下载完成开始安装 //下载完成后进行的操作

    MyHandler handler;
    int count = 0;

    private XtUploadService uploadService;
    private VersionService versionService;

    /*String apkUrl = "http://oss.wxyass.com/tscs2.4.3.1.0.apk";
    String apkName = "tscs2.4.3.1.0.apk";
    String downPath = "dbt";// apk的存放位置*/

    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<SystemFragment> fragmentRef;

        public MyHandler(SystemFragment fragment) {
            fragmentRef = new SoftReference<SystemFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            SystemFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }

            // 处理UI 变化
            switch (msg.what) {
                /*case SHOWDOWNLOADDIALOG:
                    fragment.showDownloadDialog();
                    break;
                case UPDATEDOWNLOADDIALOG: // 督导输入数据后
                    fragment.showDownloading(msg);
                    break;
                case DOWNLOADFINISHED: // 督导输入数据后
                    fragment.stopDownloadDialog();
                    break;*/
                case ConstValues.WAIT5: // 升级进度弹窗
                    Bundle bundle = msg.getData();
                    String apkUrl = (String) bundle.getSerializable("apkUrl");
                    String apkName = (String) bundle.getSerializable("apkName");
                    fragment.startFrag(apkUrl,apkName);
                    break;
                case ConstValues.WAIT6: // 无需升级
                    fragment.showToa();
                    break;
            }
        }
    }

    private void showToa() {
        Toast.makeText(getActivity(), "已是最新版本,无需更新", Toast.LENGTH_SHORT).show();
    }

    private void startFrag(String apkUrl,String apkName) {

        Bundle bundle = new Bundle();
        bundle.putString("apkUrl", apkUrl);//
        bundle.putString("apkName", apkName);//
        DownApkFragment downApkFragment = new DownApkFragment();
        downApkFragment.setArguments(bundle);
        changeHomeFragment(downApkFragment, "downapkfragment");
    }

    private void closeProgress() {
        count ++;
        if(count>=7){
            LatteLoader.stopLoading();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dd_system, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        backBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setVisibility(View.INVISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        repwd = (RelativeLayout) view.findViewById(R.id.dd_system_rl_repwd);
        question = (RelativeLayout) view.findViewById(R.id.dd_system_rl_question);
        uploadApk = (RelativeLayout) view.findViewById(R.id.dd_system_rl_upload);
        about = (RelativeLayout) view.findViewById(R.id.dd_system_rl_about);
        exit = (RelativeLayout) view.findViewById(R.id.dd_system_rl_exit);

        repwd.setOnClickListener(this);
        question.setOnClickListener(this);
        uploadApk.setOnClickListener(this);
        about.setOnClickListener(this);
        exit.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("系统管理");
        handler = new MyHandler(this);
        uploadService = new XtUploadService(getActivity(), null);
        versionService = new VersionService(getActivity(), handler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dd_system_rl_repwd:// 修改密码
                changeHomeFragment(new RePwdFragment(), "ddweekplanfragment");
                break;
            case R.id.dd_system_rl_question:// 问题反馈
                changeHomeFragment(new QueryBackFragment(), "dddaysummaryfragment");
                break;
            case R.id.dd_system_rl_upload:// 检查更新
                if (hasPermission(GlobalValues.WRITE_READ_EXTERNAL_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    initUrlData();
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.WRITE_READ_EXTERNAL_CODE, GlobalValues.WRITE_READ_EXTERNAL_PERMISSION);
                }
                break;
            case R.id.dd_system_rl_about:// 关于系统
                changeHomeFragment(new DdAboutFragment(), "dddealplanfragment");
                break;
            case R.id.dd_system_rl_exit:// 关于系统
                exitSystem();
                break;
        }
    }

    private void exitSystem(){
        boolean allEmpty = uploadService.isAllEmpty();
        if(allEmpty){// 直接退出
            System.exit(0);
        }else {// 需要上传
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("退出");
            builder.setMessage("检测到还有未上传数据？");
            builder.setPositiveButton("上传", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // 检查网络
                    if (NetStatusUtil.isNetValid(getContext())) {
                        // 检查是否有未上传完的数据
                        count = 0;
                        LatteLoader.showLoading(getActivity(),true);// 处理数据中 true:点击隐藏进度条  (没有自动关闭滚动页)
                        uploadService.uploadTables(true);
                    } else {
                        // 提示修改网络
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("网络错误");
                        builder.setMessage("还有未上传数据，请连接好网络上传数据再退出");
                        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContext().startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        }).create().show();
                    }
                }
            });
            builder.setNegativeButton("取消", null).create().show();
        }
    }

    // 权限 下载apk
    @Override
    public void doWriteSDCard() {
        initUrlData();
    }

    private void initUrlData() {
        // 获取是否需要升级
        //getVersionData();
        String departmentid = PrefUtils.getString(getActivity(), "departmentid", "");
        String userid = PrefUtils.getString(getActivity(), "userid", "");
        versionService.getUrlData(departmentid,userid);
    }

    /*private void getVersionData() {

        String content = "{" +
                "areaid:'" + PrefUtils.getString(getActivity(), "departmentid", "") + "'," +
                "softversion:'" + DbtLog.getVersion() + "'," +
                "creuser:'" + PrefUtils.getString(getActivity(), "userid", "") + "'" +
                "}";

        String optcode = "opt_update_version";

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
                        String json = HttpParseJson.parseJsonResToString(response);

                        if ("".equals(json) || json == null) {
                            Toast.makeText(getActivity(), "后台成功接收,但返回的数据为null", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseStructBean resObj = new ResponseStructBean();
                            resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
                            // 保存信息
                            if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
                                // 保存信息
                                String formjson = resObj.getResBody().getContent();
                                parseTableJson(formjson);

                            } else {
                                Toast.makeText(getActivity(), resObj.getResHead().getContent(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .post();
    }


    // 解析数据
    private void parseTableJson(String formjson) {
        if(TextUtils.isEmpty(formjson)){
            Toast.makeText(getContext(), "已是最新版本,无需更新", Toast.LENGTH_SHORT).show();
        }else{
            ApkStc info = JsonUtil.parseJson(formjson, ApkStc.class);

            // apkUrl = PropertiesUtil.getProperties("platform_web") + info.getSoftpath();
            apkUrl = info.getSoftpath();
            apkName = info.getSoftversion()+".apk";


            // 下载apk
            Bundle bundle = new Bundle();
            bundle.putString("apkUrl", apkUrl);//
            bundle.putString("apkName", apkName);//
            DownApkFragment downApkFragment = new DownApkFragment();
            downApkFragment.setArguments(bundle);
            // downLoadApk();
            changeHomeFragment(downApkFragment, "downapkfragment");
        }

        // 下载apk
//        Bundle bundle = new Bundle();
//        bundle.putString("apkUrl", apkUrl);//
//        bundle.putString("apkName", apkName);//
//        DownApkFragment downApkFragment = new DownApkFragment();
//        downApkFragment.setArguments(bundle);
//        changeHomeFragment(downApkFragment, "downapkfragment");
    }*/



    // 下载apk
    /*private void downLoadApk() {

        Message msg = Message.obtain();
        msg.what = SHOWDOWNLOADDIALOG;
        handler.sendMessage(msg);

        RestClient.builder()
                .url(apkUrl)
                // .params("data", jsonZip)
                // .loader(getContext())// 滚动条
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //用handle通知主线程 下载完成 -> 开始安装
                        Message finishedMsg = Message.obtain();
                        finishedMsg.what = DOWNLOADFINISHED;
                        // finishedMsg.obj = downPath+apkName;// 文件路径
                        handler.sendMessage(finishedMsg);
                    }
                })
                .onDownLoadProgress(new OnDownLoadProgress() {
                    @Override
                    public void onProgressUpdate(long fileLength, int downLoadedLength) {
                        // 用handle通知主线程刷新进度, progress: 是1-100的正整数
                        Message updateMsg = Message.obtain();
                        updateMsg.what = UPDATEDOWNLOADDIALOG;
                        updateMsg.obj = fileLength;
                        updateMsg.arg1= downLoadedLength;
                        handler.sendMessage(updateMsg);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .name(apkName)
                .dir(downPath)
                .builde()
                .download();
    }*/

    /**
     * 显示正在下载的对话框
     */
    /*
    //private HttpHandler httpHandler;
    private AlertDialog downloadDialog;//正在下载的对话框
    private TextView tvCur;//当前下载的百分比
    private ProgressBar pb;//下载的进度条
    private TextView tvCanCel;//停止下载
    private TextView tvHidden;//隐藏对话框的按钮
    private int contentLength;//要下载文件的大小
    private boolean isDownloading = false;//是否正在下载
    private boolean isCancel = false;//是否取消升级
    private DecimalFormat df = new DecimalFormat("###.00");//设置结果保留两位小数

    private void showDownloadDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setCancelable(false);// 不可消失
        downloadDialog = adb.create();
        View view = View.inflate(getActivity(), R.layout.download_dialog_layout, null);
        downloadDialog.setView(view, 0, 0, 0, 0);
        tvCur = (TextView) view.findViewById(R.id.tv_cursize);
        tvCanCel = (TextView) view.findViewById(R.id.tv_cancel);
        tvHidden = (TextView) view.findViewById(R.id.tv_hidden);
        pb = (ProgressBar) view.findViewById(R.id.download_pb);

        downloadDialog.show();
    }

    private void showDownloading(android.os.Message msg) {


        long totalSize = (long) msg.obj;// 总进度
        int curSize = (int) msg.arg1;// 获取当前进度
        pb.setMax((int)totalSize);
        pb.setProgress(curSize);
    }

    private void stopDownloadDialog() {
        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
        isDownloading = false;
        if (downloadDialog.isShowing()) {
            downloadDialog.dismiss();
        }
    }*/
}
