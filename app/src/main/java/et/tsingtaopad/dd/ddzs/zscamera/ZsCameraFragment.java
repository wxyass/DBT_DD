package et.tsingtaopad.dd.ddzs.zscamera;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.List;

import et.tsingtaopad.R;
import et.tsingtaopad.core.ui.camera.CameraImageBean;
import et.tsingtaopad.core.ui.camera.RequestCodes;
import et.tsingtaopad.core.util.callback.CallbackManager;
import et.tsingtaopad.core.util.callback.CallbackType;
import et.tsingtaopad.core.util.callback.IGlobalCallback;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FileUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.logutil.DbtLog;
import et.tsingtaopad.core.util.file.FileTool;
import et.tsingtaopad.db.table.MitValcmpotherMTemp;
import et.tsingtaopad.db.table.MitValpicMTemp;
import et.tsingtaopad.db.table.MitValterMTemp;
import et.tsingtaopad.db.table.MstTerminalinfoMTemp;
import et.tsingtaopad.db.table.MstTerminalinfoMZsCart;
import et.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import et.tsingtaopad.dd.ddxt.camera.XtCameraHandler;
import et.tsingtaopad.dd.ddxt.camera.XtCameraService;
import et.tsingtaopad.dd.ddzs.zschatvie.ZsChatVieService;
import et.tsingtaopad.dd.ddzs.zssayhi.ZsSayhiService;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;
import et.tsingtaopad.view.MyGridView;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class ZsCameraFragment extends XtBaseVisitFragment implements View.OnClickListener {

    public static final String TAG = "ZsCameraFragment";

    // 查看时的提示
    TextView descTv;
    ScrollView descGv;
    private MyGridView picGv;
    private Button sureBtn;
    private LinearLayout zdzs_camera_ll_visitreport_title ;//
    private LinearLayout zdzs_camera_ll_visitreport ;//
    private EditText zdzs_camera_et_visitreport ;//

    private XtCameraService xtCameraService;

    // 当前时间 如:20110411
    private String currentdata;

    // 图片类型列表
    //private List<CameraInfoStc> valueLst;
    private List<MitValpicMTemp> valueLst;

    // 最新终端信息
    private MstTerminalinfoMTemp termTemp;
    private MstTerminalinfoMZsCart termCart;

    // 读取数据库图片表已拍照记录
    private List<MitValpicMTemp> piclst;

    // 适配器
    private ZsCameraAdapter gridAdapter;

    public static final int SHOW_PROGRESS = 51;
    public static final int CLOSE_PROGRESS = 52;

    MyHandler handler;

    private AlertDialog dialog;

    MstTerminalInfoMStc mstTerminalInfoMStc;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_camera, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {

        descGv = (ScrollView) view.findViewById(R.id.xtbf_camera_sv_desc);
        picGv = (MyGridView) view.findViewById(R.id.xtbf_camera_gv_camera);
        descTv = (TextView) view.findViewById(R.id.xtbf_camera_tv_desc);
        zdzs_camera_ll_visitreport_title = (LinearLayout) view.findViewById(R.id.zdzs_camera_ll_visitreport_title);
        zdzs_camera_ll_visitreport = (LinearLayout) view.findViewById(R.id.zdzs_camera_ll_visitreport);
        zdzs_camera_et_visitreport = (EditText) view.findViewById(R.id.zdzs_camera_et_visitreport);
        sureBtn = (Button) view.findViewById(R.id.xtbf_camera_bt_next);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        xtCameraService = new XtCameraService(getActivity(), null);
        handler = new MyHandler(this);
        initData();
    }

    //ZsChatVieService zsChatVieService;
    private ZsSayhiService zsSayhiService;
    private MitValterMTemp mitValterMTemp;
    // MitValcmpotherMTemp mitValcmpotherMTemp = null;

    // 初始化数据
    private void initData() {
        // 如果不是查看,图片列表展示 提示消失
        if (!ConstValues.FLAG_1.equals(seeFlag)) {
            descGv.setVisibility(View.VISIBLE);
            descTv.setVisibility(View.GONE);
        } else { // 如果是查看,图片列表消失 提示展示
            descGv.setVisibility(View.GONE);
            descTv.setVisibility(View.VISIBLE);
            descTv.setText("图片上传后本地会自动删除,查看模式下不能显示,只能通过服务器后台查看");
        }

        // 初始化照片类型
        initpic();

        // 设置条目点击事件
        setGridItemListener();


        // zsChatVieService = new ZsChatVieService(getActivity(), null);

        zsSayhiService = new ZsSayhiService(getActivity(), null);
        mitValterMTemp = zsSayhiService.findMitValterMTempById(mitValterMTempKey);// 追溯临时表记录
        // 瓦解竞品
        //mitValcmpotherMTemp = zsChatVieService.findMitValcmpotherMTempById(mitValterMTempKey);

        // 拜访记录
        if("Y".equals(mitValcheckterM.getVisinote())){
            zdzs_camera_ll_visitreport_title.setVisibility(View.VISIBLE);
            zdzs_camera_ll_visitreport.setVisibility(View.VISIBLE);
            zdzs_camera_et_visitreport.setText(mitValterMTemp.getVisitremark());
        }
    }


    // 初始化照片 及已拍照片
    private void initpic() {

        DbtLog.logUtils(TAG, "initpic");

        // 获取当前时间 如:20110411
        currentdata = DateUtil.getDateTimeStr(0);

        // 初始化需要拍几张图
        // 查询图片类型表,获取需要拍几张图片 valueLst = new ArrayList<PictypeDataStc>();
        //valueLst = xtCameraService.queryPictypeMAll();
        valueLst = xtCameraService.queryZsPictypeMAll();
        // 获取最新的终端数据
        termTemp = xtCameraService.findTermTempById(termId);
        //termCart = xtCameraService.findTermById(termId);
        termCart = xtCameraService.findZsTermById(termId);

        mstTerminalInfoMStc = xtCameraService.findTermKeyById(termId);

        // 没有图片类型时,给与提示
        if (valueLst == null || valueLst.size() <= 0) {
            descGv.setVisibility(View.GONE);
            descTv.setVisibility(View.VISIBLE);
            descTv.setText("普通图片类型后台未配置或促销活动未达成,不予显示");
        } else if (ConstValues.FLAG_0.equals(seeFlag) && valueLst != null && valueLst.size() > 0) {
            descGv.setVisibility(View.VISIBLE);
            descTv.setVisibility(View.GONE);
        }

        // 获取当天已保存的图片 照片临时表 // 每次切换标签都会重新查询
        //piclst = xtCameraService.queryCurrentPicRecord(termId, visitId);
        piclst = xtCameraService.queryZsCurrentPicRecord(termId, mitValterMTempKey);

        for (MitValpicMTemp cameraDataStc : piclst) {// 已拍的
            for (MitValpicMTemp stctc : valueLst) {// 需拍的(图片类型key,图片类型name)
                if (cameraDataStc.getPictypekey().equals(stctc.getPictypekey())) {
                    stctc.setId(cameraDataStc.getId());
                    stctc.setPicname(cameraDataStc.getPicname());
                    stctc.setValterid(cameraDataStc.getValterid()); // 追溯主键
                    stctc.setPictypekey(cameraDataStc.getPictypekey());// 图片类型主键(UUID)
                    stctc.setPictypename(cameraDataStc.getPictypename());// 图片类型(中文名称)
                    //stctc.setImagefileString(imagefileString);// 将图片文件转成String保存在数据库
                    stctc.setAreaid(cameraDataStc.getAreaid());// 二级区域
                    stctc.setGridkey(cameraDataStc.getGridkey());//定格
                    stctc.setRoutekey(cameraDataStc.getRoutekey());// 路线
                    stctc.setTerminalkey(cameraDataStc.getTerminalkey());// 终端主键

                }
            }
        }

        // 设置适配器
        gridAdapter = new ZsCameraAdapter(getContext(), valueLst);
        picGv.setAdapter(gridAdapter);
    }

    int current = -1;
    private void setGridItemListener() {
        // 设置item的点击监听
        picGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                current = position;

                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {

                        // 弹出提示对话框
                        Message message = new Message();
                        message.what = SHOW_PROGRESS;
                        handler.sendMessage(message);// 提示:图片正在保存,请稍后

                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                // 保存到表
                                MitValpicMTemp valpicMTemp = valueLst.get(position);
                                CameraImageBean cameraImageBean = CameraImageBean.getInstance();
                                String picname = cameraImageBean.getPicname();
                                Uri uri = cameraImageBean.getmPath();
                                // 将图片转成字符串
                                String imagefileString = "";
                                try {
                                    // 裁剪 压缩
                                    Bitmap bitmap = getBitmapFormUri(getContext(), uri);
                                    // 添加水印
                                    //bitmap = cameraService.addWaterBitmap(bitmap, DateUtil.getDateTimeStr(3),"username",termname,cameraDataStc.getPictypename());
                                    // 删除原图
                                    FileUtil.deleteFile(new File(FileTool.CAMERA_PHOTO_DIR + picname));
                                    // 保存小图
                                    FunUtil.saveHeadImg(FileTool.CAMERA_PHOTO_DIR, bitmap, picname, 100, 25);
                                    // 图片转成字符串
                                    imagefileString = FileUtil.fileToString(FileTool.CAMERA_PHOTO_DIR + picname);
                                    // 将图片记录保存到数据库
                                    String id = xtCameraService.saveZsPicData(valpicMTemp, picname,
                                            imagefileString, termId,termCart, mitValterMTempKey, mstTerminalInfoMStc);

                                    // 更新UI界面 刷新适配器
                                    Message message1 = new Message();
                                    message1.what = CLOSE_PROGRESS;
                                    handler.sendMessage(message1);// 刷新UI

                                    valpicMTemp.setId(id);
                                    valpicMTemp.setPicname(picname);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    // 更新UI界面 刷新适配器
                                    Message message1 = new Message();
                                    message1.what = CLOSE_PROGRESS;
                                    handler.sendMessage(message1);// 刷新UI
                                }
                            }
                        }.start();
                    }
                });

                if (hasPermission(GlobalValues.HARDWEAR_CAMERA_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    startCamera(current);
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.HARDWEAR_CAMERA_CODE, GlobalValues.HARDWEAR_CAMERA_PERMISSION);
                }

            }
        });
    }

    @Override
    public void doOpenCamera() {
        startCamera(current);
    }

    private void startCamera(int position){
        // 拍照弹窗
        if (TextUtils.isEmpty(valueLst.get(position).getId()) || "".equals(valueLst.get(position).getId())) {// 是否有图片主键
            new XtCameraHandler(ZsCameraFragment.this).takePhoto();
        } else {
            new XtCameraHandler(ZsCameraFragment.this).beginCameraDialog(valueLst.get(position).getPicname());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHONE:// 拍照相机 回来的
                    // 获取照片文件路径
                    final Uri resultUri = CameraImageBean.getInstance().getmPath();
                    /*File tempFile = CameraImageBean.getInstance().getFile();
                    Uri resultUri = getResultUri(getContext(),tempFile);*/

                    // 处理图片:  裁剪 压缩 水印
                    /*Intent intent = startPhotoZoom(resultUri);
                    startActivityForResult(intent, RequestCodes.RESULT_REQUEST_CODE);*/

                    final IGlobalCallback<Uri> callbacktakephone = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if (callbacktakephone != null) {
                        callbacktakephone.executeCallback(resultUri);
                    }

                    /*// 去剪裁,将原图覆盖掉
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(480,640)
                            .start(getContext(),this);*/

                    break;

                default:
                    break;
            }
        } else {
            Toast.makeText(getActivity(), "拍照失败或初始化相机失败,请重新拍照", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<ZsCameraFragment> fragmentRef;

        public MyHandler(ZsCameraFragment fragment) {
            fragmentRef = new SoftReference<ZsCameraFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ZsCameraFragment fragment = fragmentRef.get();
            if (fragment == null) {
                return;
            }
            // 处理UI 变化
            switch (msg.what) {
                case SHOW_PROGRESS:
                    // 弹出进度框
                    fragment.showDialogSuc();
                    break;
                case CLOSE_PROGRESS:
                    fragment.closeDialogSuc();
                    break;
            }
        }
    }

    /**
     * 展示滚动条
     */
    public void showDialogSuc() {
        dialog = new AlertDialog.Builder(getActivity()).setCancelable(false).create();
        dialog.setView(getActivity().getLayoutInflater().inflate(R.layout.camera_progress, null), 0, 0, 0, 0);
        dialog.setCancelable(false); // 是否可以通过返回键 关闭
        dialog.show();
    }

    /**
     * 关闭滚动条
     */
    public void closeDialogSuc() {
        if (dialog != null) {
            dialog.dismiss();
        }
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        DbtLog.logUtils(TAG, "onPause()");
        // 如果是查看操作，则不做数据校验及数据处理
        if (ConstValues.FLAG_1.equals(seeFlag)) return;

        // 拜访记录
        // mitValcmpotherMTemp.setValvisitremark(zdzs_camera_et_visitreport.getText().toString());
        mitValterMTemp.setVisitremark(zdzs_camera_et_visitreport.getText().toString());

        // 保存追溯聊竞品页面数据到临时表
        //zsChatVieService.saveZsVisitremark(mitValcmpotherMTemp);
        // 将追溯数据 保存到追溯主表临时表中
        zsSayhiService.updateMitValterMTemp(mitValterMTemp);
    }


}
