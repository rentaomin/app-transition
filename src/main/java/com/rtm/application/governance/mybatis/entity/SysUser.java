package com.rtm.application.governance.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * t_sys_admin
 * @Auther: caojq
 * @Date: 2020/07/21 15:16
 */
@Table(value = "t_sys_admin",camelToUnderline = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysUser implements Serializable {

    private String id; // 管理员Id
    private String account; // 用户名
    private String realName;  // 显示名
    private String password; // 密码
    private String salt; //盐值，暂未使用

    private String roleId; // 角色Id
    private int adminType; //管理员类型： 0开发，1外部
    private String email; //邮箱
    private String mobile; //手机
    private Integer themeColor; //主题颜色
    private String startIp; //起始ip，登录ip限制
    private String endIp; //结束ip，登录ip限制
    private int display; //是否显示 0否1是
    private int isFirstLogin; //是否首次登录，1否0是
    private int isLocked; //是否锁定
    private String lastLoginTime; //最后登录时间
    private String lastLoginIp; //最后登录ip
    private String nowLoginTime; //本次登录时间
    private String nowLoginIp; //本次登录ip
    private int isRemoved; // 是否启用
    private String createTime; // 创建时间
    private String creater;  //创建人
    private String updateTime; // 更新时间
    private String updater; //更新人
    private String version; //暂未使用
    private String lastPwdModTime;  //密码修改时间
    private String lastLockTime; //上次锁定时间
    private int pwdErrorNums; // 密码错误次数
    private String lastPwdWrongTime; //上次密码错误时间
    private List<String> groupIds; //管理员对应管辖组

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(Integer themeColor) {
        this.themeColor = themeColor;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(int isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getNowLoginTime() {
        return nowLoginTime;
    }

    public void setNowLoginTime(String nowLoginTime) {
        this.nowLoginTime = nowLoginTime;
    }

    public String getNowLoginIp() {
        return nowLoginIp;
    }

    public void setNowLoginIp(String nowLoginIp) {
        this.nowLoginIp = nowLoginIp;
    }

    public int getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(int isRemoved) {
        this.isRemoved = isRemoved;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLastPwdModTime() {
        return lastPwdModTime;
    }

    public void setLastPwdModTime(String lastPwdModTime) {
        this.lastPwdModTime = lastPwdModTime;
    }

    public String getLastLockTime() {
        return lastLockTime;
    }

    public void setLastLockTime(String lastLockTime) {
        this.lastLockTime = lastLockTime;
    }

    public int getPwdErrorNums() {
        return pwdErrorNums;
    }

    public void setPwdErrorNums(int pwdErrorNums) {
        this.pwdErrorNums = pwdErrorNums;
    }

    public String getLastPwdWrongTime() {
        return lastPwdWrongTime;
    }

    public void setLastPwdWrongTime(String lastPwdWrongTime) {
        this.lastPwdWrongTime = lastPwdWrongTime;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }


    @Override
    public String toString() {
        return "{" +
               "id='" + id + '\'' +
               ", account='" + account + '\'' +
               ", realName='" + realName + '\'' +
               ", password='" + password + '\'' +
               ", salt='" + salt + '\'' +
               ", roleId='" + roleId + '\'' +
               ", adminType='" + adminType + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
