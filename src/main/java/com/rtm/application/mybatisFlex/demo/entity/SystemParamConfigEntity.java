package com.rtm.application.mybatisFlex.demo.entity;


import java.util.Date;


/**
 * 系统参数配置实体类
 *
 * @author zhangyi
 * @version 2018/6/11
 * @see SystemParamConfigEntity
 */
public class SystemParamConfigEntity {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 系统参数标识（1：系统参数配置，2：告警参数配置，3：备份参数配置）
     */
    private String sysParamKey;

    /**
     * 最小属性的唯一标识
     */
    private String propKey;

    /**
     * 参数类型（1：系统参数，2：邮箱参数,3:xxxx,4:xxxx,5:xxxx）
     */
    private String paramType;

    /**
     * 属性名称
     */
    private String propName;

    /**
     * 属性值
     */
    private String propValue;

    /**
     * 属性合法验证表达式
     */
    private String propValidExpr;

    /**
     * 属性输入提示信息
     */
    private String propTips;

    private Integer isPassword;

    private Integer isShow;

    private Integer isEdit;

    private Integer isRequiredField;

    /**
     * 参数描述，描述参数具体用途作用
     */
    private String paramDesc;

    /**
     * 组件类型（可扩展），0：输入框（默认）1：下拉单选框 2：下拉多选框，3.开关类（0/1和false/true）
     */
    private Integer componentType;

    /**
     *  可选项值，json，默认null
     */
    private String optionalValues;

    /**
     * 前端要求加的，说是查看和编辑界面合并的话要有个变量控制编辑和查看的转换，没有的话没法做处理，要求协助，纯前端使用，默认false zhangjun
     * @author: lixinyu 2023/11/28
     **/
    private boolean editShow = false;

    public boolean isEditShow() {
        return editShow;
    }

    public void setEditShow(boolean editShow) {
        this.editShow = editShow;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    public String getOptionalValues() {
        return optionalValues;
    }

    public void setOptionalValues(String optionalValues) {
        this.optionalValues = optionalValues;
    }

    public Integer getIsRequiredField() {
        return isRequiredField;
    }

    public void setIsRequiredField(Integer isRequiredField) {
        this.isRequiredField = isRequiredField;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getSysParamKey() {
        return sysParamKey;
    }

    public void setSysParamKey(String sysParamKey) {
        this.sysParamKey = sysParamKey;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public String getPropValidExpr() {
        return propValidExpr;
    }

    public void setPropValidExpr(String propValidExpr) {
        this.propValidExpr = propValidExpr;
    }

    public String getPropTips() {
        return propTips;
    }

    public void setPropTips(String propTips) {
        this.propTips = propTips;
    }

    public Integer getIsPassword() {
        return isPassword;
    }

    public void setIsPassword(Integer isPassword) {
        this.isPassword = isPassword;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    @Override
    public String toString() {
        return "SystemParamConfigEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", modifyTime=" + modifyTime +
                ", modifyUser='" + modifyUser + '\'' +
                ", sysParamKey='" + sysParamKey + '\'' +
                ", propKey='" + propKey + '\'' +
                ", paramType='" + paramType + '\'' +
                ", propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                ", propValidExpr='" + propValidExpr + '\'' +
                ", propTips='" + propTips + '\'' +
                ", isPassword=" + isPassword +
                ", isShow=" + isShow +
                ", isEdit=" + isEdit +
                ", isRequiredField=" + isRequiredField +
                ", paramDesc='" + paramDesc + '\'' +
                ", componentType=" + componentType +
                ", optionalValues='" + optionalValues + '\'' +
                '}';
    }
}
