package com.rtm.application.governance.mybatis.service;

import com.rtm.application.governance.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    /**
     * 插入
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 修改
     * @param sysUser
     * @return
     */
    int update(SysUser sysUser);

    /**
     * 修改全部内容
     * @param sysUser
     * @return
     */
    int updateAll(SysUser sysUser);

    /**
     * 查询所有
     * @param filters 查询过滤条件
     * @return
     */
    List<SysUser> query(@Param("filters") Map filters);


    /**
     * 根据管理员账号查询管理员基本信息
     * @param account
     * @return
     */
    SysUser queryByAccount(String account);


    /**
     * 删除管理员
     * @param userId
     */
    void remove(String userId);

    /**
     * 删除管理员
     * @param userId
     */
    void delete(String userId);

    /**
     * 根据账号查询管理员是否存在
     * @param account
     * @return
     */
    int queryExistUserAccount(String account);

    /**
     * 根据管理员id查询管理员
     * @param userId
     * @return
     */
    SysUser queryByUserId(String userId);

    /**
     * 根据角色查询用户名
     * @param roleId
     * @return
     */
    List<String> queryUserNamesByRoleId(String roleId);

    /**
     * 根据角色查询用户sid
     * @param roleId
     * @return
     */
    List<String> queryUserSidsByRoleId(String roleId);

    /**
     * 查询管理员的sid
     * @return
     */
    List<String> queryAdminsSid();

    /**
     * 根据账号查询管理员是否存在（包含删除）
     * @param account
     * @return
     */
    int queryExistUserAccountIncludeDel(String account);

    /**
     * 如果锁定时间为null 则则更新为null
     * @param sysUser
     */
    void updateDefaultUserStatus(SysUser sysUser);

    /**
     * 查询所有的管理员列表(可以查到admin)
     * @return
     */
    List<SysUser> querySysUserList();

    /**
     * 查询所有的超级管理员信息
     * @return
     */
    List<String> queryAllSysUserInfo();

    List<SysUser> querySysUserBySid(@Param("filters") Map filters);

    void batchUpdateValueByPropKey(SysUser sysUser);

    List<SysUser> getSysMenuInfo(Map filters);
}
