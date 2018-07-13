package et.tsingtaopad.login.domain;

import java.io.Serializable;

/**
 * Created by yangwenmin on 2017/12/12.
 * 功能描述: 登录用户信息Sesson对象</br>
 */
public class LoginSession implements Serializable {

    // 序列号
    private static final long serialVersionUID = -4293141439590006014L;

    // 用户姓名
    private String userName;
    
    // 用户Id
    private String userCode;
    
    // 设备ID
    private String padId;
    
    // 用于登录的用户名
    private String userGongHao;
    
    // 上次登录密码
    private String userPwd;
    
    // 所属区域ID
    private String disId;
    
    // 所有父区域ID列表 
    private String parentDisIDs;
    
    // 所属定格ID
    private String gridId;
    
    // 所属定格名称
    private String gridName;
    
    // 登录时间: yyyy-MM-dd HH:mm:ss
    private String loginDate;
    
    // 用户冻结状态: 0:未冻结，1:已冻结
    private String userStatus;
    
    // 设置冻结状态 : 0:未冻结，1:已冻结
    private String devStatus;
    
    // 设置用户是否删除过所有数据
    private String isDel;

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPadId() {
        return padId;
    }

    public void setPadId(String padId) {
        this.padId = padId;
    }

    public String getUserGongHao() {
        return userGongHao;
    }

    public void setUserGongHao(String userGongHao) {
        this.userGongHao = userGongHao;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    public String getParentDisIDs() {
        return parentDisIDs;
    }

    public void setParentDisIDs(String parentDisIDs) {
        this.parentDisIDs = parentDisIDs;
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }
    
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
    
}
