package et.tsingtaopad.login.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Date;

import et.tsingtaopad.R;
import et.tsingtaopad.core.net.HttpUrl;
import et.tsingtaopad.core.net.RestClient;
import et.tsingtaopad.core.net.callback.IError;
import et.tsingtaopad.core.net.callback.IFailure;
import et.tsingtaopad.core.net.callback.ISuccess;
import et.tsingtaopad.core.net.domain.RequestHeadStc;
import et.tsingtaopad.core.net.domain.RequestStructBean;
import et.tsingtaopad.core.net.domain.ResponseStructBean;
import et.tsingtaopad.core.util.dbtutil.CheckUtil;
import et.tsingtaopad.core.util.dbtutil.ConstValues;
import et.tsingtaopad.core.util.dbtutil.DateUtil;
import et.tsingtaopad.core.util.dbtutil.FunUtil;
import et.tsingtaopad.core.util.dbtutil.JsonUtil;
import et.tsingtaopad.core.util.dbtutil.PrefUtils;
import et.tsingtaopad.home.initadapter.GlobalValues;
import et.tsingtaopad.http.HttpParseJson;
import et.tsingtaopad.util.requestHeadUtil;

/**
 * 功能描述: 用户登录的业务逻辑</br>
 */
public class LoginService {

	private final String TAG = "LoginService";

	private Context context;
	private Handler handler;

	public LoginService(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	/**
     * 
     */
    public LoginService() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * 登录
	 * 
	 * @param uid   用户名
	 * @param pwd   密码
     * @param version 
	 * @return
	 */
	public void login(final String uid, final String pwd, String version,String preUserCode) {

		int msgId = -1;

		// 获取上次登录者信息
		final LoginSession loginSession = this.getLoginSession(context);

		if (CheckUtil.isBlankOrNull(uid)) {
			msgId = R.string.login_msg_invaluid;

		} else if (CheckUtil.isBlankOrNull(pwd)) {
			msgId = R.string.login_msg_invalpwd;

		} else if (!uid.equals(preUserCode) && preUserCode.length() > 0) {
			msgId = R.string.login_msg_invaluser;
		}

		// 弹出提示信息
		if (msgId != -1) {
			this.sendMsg(msgId);

		} else {

			// 组建参数json
			//final String padId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getImei();
			final String padId = android.os.Build.SERIAL;
			StringBuffer buffer = new StringBuffer();
			buffer.append("{usercode:'").append(uid);
			buffer.append("', password:'").append(pwd);
			buffer.append("', padid:'").append(padId);
			buffer.append("', version:'").append(version).append("'}");

			// 设置头部信息(用户信息)
			RequestHeadStc requestHeadStc = requestHeadUtil.parseRequestHead(context);
			requestHeadStc.setUsercode(uid);
			requestHeadStc.setPassword(pwd);
			requestHeadStc.setOptcode("get_login_3");

			// 获取请求实体对象
			RequestStructBean reqObj = HttpParseJson.parseRequestStructBean(requestHeadStc, buffer.toString());

			// 压缩请求数据
			String jsonZip = HttpParseJson.parseRequestJson(reqObj);

			RestClient.builder()
					.url(HttpUrl.IP_END)
					.params("data", jsonZip)
					//.loader(getContext())
					.success(new ISuccess() {
						@Override
						public void onSuccess(String response) {
							String json  = HttpParseJson.parseJsonResToString(response);
							ResponseStructBean resObj = new ResponseStructBean();
							resObj = JsonUtil.parseJson(json, ResponseStructBean.class);
							// 保存登录信息
							if (ConstValues.SUCCESS.equals(resObj.getResHead().getStatus())) {
								// 保存登录者信息
								BsVisitEmpolyeeStc emp = JsonUtil.parseJson(resObj.getResBody().getContent(), BsVisitEmpolyeeStc.class);
								if (emp == null) {
									sendMsg(R.string.login_msg_usererror);//服务器回应的内容为空时界面会收到   用户信息异常，不能正常登录

								} else {
									// 服务器时间与pad端时间差
									long timeDiff = Math.abs(System.currentTimeMillis()- DateUtil.parse(emp.getLoginDate(), "yyyy-MM-dd HH:mm:ss").getTime());
									// 校验用户的定格是否一致
									String preGridId = PrefUtils.getString(context,"gridId","");
									if (!CheckUtil.isBlankOrNull(preGridId)&& !preGridId.equals(emp.getGridId())) {
										sendMsg(R.string.login_msg_invalgrid);//现在登录的定格与上次的不一样时界面收到      用户所属定格变更，请先清除上次登录账户的缓存数据
									// 时间差
									} else if (timeDiff > 5 * 60000) {
										sendMsg(R.string.login_msg_invaldate);
									} else {
										// 保存登录信息到pref
										saveLoginSession(emp, pwd, padId);

										// 跳转到平台主界面 Isrepassword:剩余多少天修改密码 2393版本返回null
										sendMsg1(R.string.login_msg_online, true,emp.getIsrepassword());
									}
								}

							} else {
								String msg = FunUtil.isNullSetSpace(resObj.getResHead().getContent());
								if (msg.contains("用户已离职或冻结")) {
									PrefUtils.putString(context, "userStatus", ConstValues.FLAG_1);
								} else if (msg.contains("设备已冻结")) {
									PrefUtils.putString(context, "devStatus", ConstValues.FLAG_1);
								}
								sendMsg(msg);
							}
						}
					})
					.error(new IError() {
						@Override
						public void onError(int code, String msg) {
							// 尝试离线登录
							loginByNoNet(uid,pwd);
						}
					})
					.failure(new IFailure() {
						@Override
						public void onFailure() {
							// 尝试离线登录
							loginByNoNet(uid,pwd);
						}
					})
					.builde()
					.post();
		}
	}

	/**
	 * 向界面发送提示消息
	 * 
	 * @param msg   提示消息
	 */
	private void sendMsg(Object msg) {

		Bundle bundle = new Bundle();
		Message message = new Message();

		if (msg instanceof Integer) {
			bundle.putString("msg", context.getString(Integer.valueOf(msg.toString())));
		} else {
			bundle.putString("msg", String.valueOf(msg));
		}
		message.what = ConstValues.WAIT1;
		message.setData(bundle);
		handler.sendMessage(message);
	}

	private void sendMsg(Object msg, boolean isSuccess) {

		Bundle bundle = new Bundle();
		Message message = new Message();
		bundle.putBoolean("isSuccess", isSuccess);
		if (msg instanceof Integer) {
			bundle.putString("msg", context.getString(Integer.valueOf(msg.toString())));
		} else {
			bundle.putString("msg", String.valueOf(msg));
		}
		message.what = ConstValues.WAIT1;
		message.setData(bundle);
		handler.sendMessage(message);
	}

	// 离线登录,what2
	private void sendMsgNoNet(Object msg, boolean isSuccess) {

		Bundle bundle = new Bundle();
		Message message = new Message();
		bundle.putBoolean("isSuccess", isSuccess);
		if (msg instanceof Integer) {
			bundle.putString("msg", context.getString(Integer.valueOf(msg.toString())));
		} else {
			bundle.putString("msg", String.valueOf(msg));
		}
		message.what = ConstValues.WAIT2;
		message.setData(bundle);
		handler.sendMessage(message);
	}

	/**
	 * 登录成功 向界面发送提示消息
	 * 
	 * @param msg
	 * @param isSuccess    是否跳转
	 * @param isrepassword 是否修改密码 0:不需要 1:需要
	 */
	private void sendMsg1(Object msg, boolean isSuccess,String isrepassword) {
		
		Bundle bundle = new Bundle();
		Message message = new Message();
		bundle.putBoolean("isSuccess", isSuccess);
		bundle.putString("isrepassword", isrepassword);
		if (msg instanceof Integer) {
			bundle.putString("msg", context.getString(Integer.valueOf(msg.toString())));
		} else {
			bundle.putString("msg", String.valueOf(msg));
		}
		message.what = ConstValues.WAIT1;
		message.setData(bundle);
		handler.sendMessage(message);
		
	}

	// 离线登录
	private void loginByNoNet(final String uid, final String pwd){
		//状态为1时的情况   该用户已离职或冻结！
		if (ConstValues.FLAG_1.equals(PrefUtils.getString(context, "userStatus", ""))) {
			sendMsg(R.string.login_msg_userice);

		// 设置冻结状态 : 0:未冻结，1:已冻结
		} else if (ConstValues.FLAG_1.equals(PrefUtils.getString(context, "devStatus", ""))) {
			sendMsg(R.string.login_msg_device);

		// 如果缓存的用户名是空，是为第一次登录
		} else if (CheckUtil.isBlankOrNull(PrefUtils.getString(context, "userCode", ""))) {
			sendMsg(R.string.msg_err_netfail);

		// 否则尝试离线登录
		} else {

			// 判定与上次登录密码是否一至, 是则跳转平台主界面
			if (uid.equals(PrefUtils.getString(context, "userCode", ""))
					&& pwd.equals(PrefUtils.getString(context, "userPwd", ""))) {
				PrefUtils.putString(context, "loginDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss"));
				sendMsgNoNet(R.string.login_msg_offline,true);//离线登录成功
			} else {
				sendMsgNoNet(R.string.login_msg_pwdfail,false);//离线登录失败
			}
		}
	}

	/**
	 * 记录登录者信息
	 * 
	 * @param emp 登录成功后，返回的当前用户信息
	 */
	private void saveLoginSession(BsVisitEmpolyeeStc emp, String pwd, String padId) {
		if (emp != null) {
			//SharedPreferences sp = context.getSharedPreferences(GlobalValues.LOGINSESSIONKEY, 0);
			// 因为老是报错,怀疑loginsession保存出错,重新保存 修改  20170317
			PrefUtils.putString(context, "userCode", emp.getUsercode());
			PrefUtils.putString(context, "userGongHao", emp.getUsercode());
			PrefUtils.putString(context, "userName", emp.getUsername());
			PrefUtils.putString(context, "userPwd", pwd);
			PrefUtils.putString(context, "padId", padId);
			PrefUtils.putString(context, "loginDate", emp.getLoginDate());
			PrefUtils.putString(context, "isrepassword", emp.getIsrepassword());

			PrefUtils.putString(context, "disId", emp.getDepartmentid());
			PrefUtils.putString(context, "gridId", emp.getGridId());
			PrefUtils.putString(context, "gridName", emp.getGridName());
			PrefUtils.putString(context, "pDiscs", emp.getpDiscs());
			PrefUtils.putString(context, "isDel", emp.getIsDel());
			PrefUtils.putString(context, "userStatus", ConstValues.FLAG_0);
			PrefUtils.putString(context, "devStatus", ConstValues.FLAG_0);

			// 保存新加字段 大区和二级区域id
			PrefUtils.putString(context, "bigareaid", emp.getXtgl());
			PrefUtils.putString(context, "secareaid", emp.getXtgl());

			// 保存用户权限到缓存
			PrefUtils.putString(context, "bfgl", emp.getBfgl());
			PrefUtils.putString(context, "yxgl", emp.getYxgl());
			PrefUtils.putString(context, "xtgl", emp.getXtgl());
		}
	}

	/**
	 * 记录登录者信息
	 * 
	 * @param proName 登录成功后，返回的当前用户信息
	 */
	private void updateLoginSession(String proName, String proValue) {

		SharedPreferences sp = context.getSharedPreferences(GlobalValues.LOGINSESSIONKEY, 0);
		if (sp != null) {
			sp.edit().putString(proName, proValue).commit();
		}
	}

	/**
	 * 获取上次用户登录信息
	 * 
	 * @param context
	 * @return
	 */
	public LoginSession getLoginSession(Context context) {

		LoginSession session = new LoginSession();
		SharedPreferences sp = context.getSharedPreferences(GlobalValues.LOGINSESSIONKEY, 0);
		if (sp != null) {
			session.setUserCode(sp.getString("userCode", ""));
			session.setPadId(sp.getString("padId", ""));
			session.setUserName(sp.getString("userName", ""));
			session.setUserGongHao(sp.getString("userGongHao", ""));
			session.setUserPwd(sp.getString("userPwd", ""));
			session.setDisId(sp.getString("disId", ""));
			session.setGridId(sp.getString("gridId", ""));
			session.setGridName(sp.getString("gridName", ""));
			session.setLoginDate(sp.getString("loginDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss")));
			session.setParentDisIDs(sp.getString("pDiscs", ""));
			session.setUserStatus(sp.getString("userStatus", "0"));
			session.setDevStatus(sp.getString("devStatus", "0"));
			session.setIsDel(sp.getString("isDel", "0"));
		}
		return session;
	}
}
