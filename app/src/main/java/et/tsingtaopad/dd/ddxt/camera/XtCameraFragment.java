package et.tsingtaopad.dd.ddxt.camera;

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
import et.tsingtaopad.db.table.MstTerminalinfoMTemp;
import et.tsingtaopad.dd.ddxt.base.XtBaseVisitFragment;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.main.visit.shopvisit.termvisit.camera.domain.CameraInfoStc;
import et.tsingtaopad.main.visit.shopvisit.termvisit.sayhi.domain.MstTerminalInfoMStc;
import et.tsingtaopad.view.MyGridView;

/**
 * Created by yangwenmin on 2018/3/12.
 */

public class XtCameraFragment extends XtBaseVisitFragment implements View.OnClickListener{

    public static final String TAG = "XtCameraFragment";

    // 查看时的提示
    TextView descTv;
    ScrollView descGv;
    private MyGridView picGv;
    private Button sureBtn;

    private XtCameraService xtCameraService;

    // 当前时间 如:20110411
    private String currentdata;

    // 图片类型列表
    private List<CameraInfoStc> valueLst;

    // 最新终端信息
    private MstTerminalinfoMTemp termTemp;
    //private MstTerminalinfoMCart termCart;

    // 读取数据库图片表已拍照记录
    private List<CameraInfoStc> piclst;

    // 适配器
    private XtCameraAdapter gridAdapter;

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
    private void initView(View view){

        descGv = (ScrollView) view.findViewById(R.id.xtbf_camera_sv_desc);
        picGv = (MyGridView) view.findViewById(R.id.xtbf_camera_gv_camera);
        descTv = (TextView) view.findViewById(R.id.xtbf_camera_tv_desc);
        sureBtn = (Button) view.findViewById(R.id.xtbf_camera_bt_next);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        xtCameraService = new XtCameraService(getActivity(),null);
        handler = new MyHandler(this);
        initData();
    }

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
    }



    // 初始化照片 及已拍照片
    private void initpic() {

        DbtLog.logUtils(TAG, "initpic");

        // 获取当前时间 如:20110411
        currentdata = DateUtil.getDateTimeStr(0);

        // 初始化需要拍几张图
        // 查询图片类型表,获取需要拍几张图片 valueLst = new ArrayList<PictypeDataStc>();
        valueLst = xtCameraService.queryPictypeMAll();
        // 获取最新的终端数据
        termTemp = xtCameraService.findTermTempById(termId);


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
        piclst = xtCameraService.queryCurrentPicRecord(termId, visitId);

        for (CameraInfoStc cameraDataStc : piclst) {// 已拍的
            for (CameraInfoStc stctc : valueLst) {// 需拍的
                if (cameraDataStc.getPictypekey().equals(stctc.getPictypekey())) {
                    stctc.setCamerakey(cameraDataStc.getCamerakey());
                    stctc.setLocalpath(cameraDataStc.getLocalpath());// 终端名称
                    stctc.setPicname(cameraDataStc.getPicname());
                    stctc.setPicindex(cameraDataStc.getPicindex());
                    stctc.setVisitkey(cameraDataStc.getVisitkey());
                    stctc.setTerminalkey(cameraDataStc.getTerminalkey());
                }
            }
        }

        // 设置适配器
        gridAdapter = new XtCameraAdapter(getContext(), valueLst);
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
                                CameraInfoStc cameraDataStc = valueLst.get(position);
                                CameraImageBean cameraImageBean = CameraImageBean.getInstance();
                                String picname = cameraImageBean.getPicname();
                                Uri uri = cameraImageBean.getmPath();
                                // 将图片转成字符串
                                String imagefileString = "";
                                try {
                                    // 裁剪 压缩
                                    // Bitmap bitmap = getBitmapFormUri(getContext(), uri);
                                    Bitmap bitmap = FunUtil.getBitmapFormUri(getContext(), uri);
                                    // 添加水印
                                    //bitmap = cameraService.addWaterBitmap(bitmap, DateUtil.getDateTimeStr(3),"username",termname,cameraDataStc.getPictypename());
                                    // 删除原图
                                    FileUtil.deleteFile(new File(FileTool.CAMERA_PHOTO_DIR + picname));
                                    // 保存小图
                                    FunUtil.saveHeadImg(FileTool.CAMERA_PHOTO_DIR, bitmap, picname, 100, 25);
                                    // 图片转成字符串
                                    imagefileString = FileUtil.fileToString(FileTool.CAMERA_PHOTO_DIR + picname);
                                    // 将图片记录保存到数据库
                                    String cameraKey = xtCameraService.savePicData(cameraDataStc, picname,
                                            imagefileString,termId,termTemp,visitId,mstTerminalInfoMStc);

                                    // 更新UI界面 刷新适配器
                                    Message message1 = new Message();
                                    message1.what = CLOSE_PROGRESS;
                                    handler.sendMessage(message1);// 刷新UI

                                    cameraDataStc.setCamerakey(cameraKey);
                                    cameraDataStc.setPicname(picname);

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

                /*if (hasPermission(GlobalValues.HARDWEAR_CAMERA_PERMISSION)) {
                    // 拥有了此权限,那么直接执行业务逻辑
                    startCamera(current);
                } else {
                    // 还没有对一个权限(请求码,权限数组)这两个参数都事先定义好
                    requestPermission(GlobalValues.HARDWEAR_CAMERA_CODE, GlobalValues.HARDWEAR_CAMERA_PERMISSION);
                }*/

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
        if(TextUtils.isEmpty(valueLst.get(position).getCamerakey())||"".equals(valueLst.get(position).getCamerakey())){
            new XtCameraHandler(XtCameraFragment.this).takePhoto();
        }else{
            new XtCameraHandler(XtCameraFragment.this).beginCameraDialog(valueLst.get(position).getPicname());
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
        if(resultCode == getActivity().RESULT_OK){
            switch (requestCode){
                case RequestCodes.TAKE_PHONE:// 拍照相机 回来的
                    // 获取照片文件路径
                    final Uri resultUri = CameraImageBean.getInstance().getmPath();
                    /*File tempFile = CameraImageBean.getInstance().getFile();
                    Uri resultUri = getResultUri(getContext(),tempFile);*/

                    // 处理图片:  裁剪 压缩 水印
                    /*Intent intent = startPhotoZoom(resultUri);
                    startActivityForResult(intent, RequestCodes.RESULT_REQUEST_CODE);*/

                    final IGlobalCallback<Uri> callbacktakephone = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if(callbacktakephone!=null){
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
        }else {
            Toast.makeText(getActivity(), "拍照失败或初始化相机失败,请重新拍照", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 接收子线程消息的 Handler
     */
    public static class MyHandler extends Handler {

        // 软引用
        SoftReference<XtCameraFragment> fragmentRef;

        public MyHandler(XtCameraFragment fragment) {
            fragmentRef = new SoftReference<XtCameraFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            XtCameraFragment fragment = fragmentRef.get();
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

    public void closeDialogSuc() {
        if (dialog != null) {
            dialog.dismiss();
        }
        gridAdapter.notifyDataSetChanged();
    }


}
