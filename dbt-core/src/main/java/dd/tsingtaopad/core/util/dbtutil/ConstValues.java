package dd.tsingtaopad.core.util.dbtutil;

import android.os.Handler;

import java.io.Serializable;


/**
 * 系统常量配置 定义一些不变的常量  (除handler,打算将handler放到GlobalValues中)
 */
public class ConstValues implements Serializable{

	// 版本序列号
	private static final long serialVersionUID = 3714072522003250341L;
	
	/***
	 * 是否上线版本
	 * ture：发布线上版本，不记录日志
	 * false：测试版本，记录日志
	 */
	public static boolean isOnline=true;
	/***
	 * 是否正在上传新增终端失败的终端
	 */
	public static boolean isUploadTermAdd=false;
	
	// 常用标识
	public static final String FLAG_0 = "0";
	public static final String FLAG_1 = "1";
	public static final String FLAG_2 = "2";
	public static final String FLAG_3 = "3";
	public static final String FLAG_4 = "4";
	
    // 成功、失败消息字符串常量
    public static final String SUCCESS = "Y";
    public static final String ERROR = "N";
    
    // handler wait
    public static final int WAIT0 = 0;
    public static final int WAIT1 = 1;
    public static final int WAIT2 = 2;
    public static final int WAIT3 = 3;
    public static final int WAIT4 = 4;
    public static final int WAIT5 = 5;
    public static final int WAIT6 = 6;

	/***
	 * 指标标删除状态（解决重复指标）
	 */
	public static final String delFlag = "8";

	// 当前有效的Handler
	public static Handler msgHandler;
	public static Handler handler;






    


    
}
