package com.rtm.application.governance.mybatis.controller;

import com.rtm.application.governance.mybatis.entity.SysUser;
import com.rtm.application.governance.mybatis.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/insert")
    public int insert(SysUser sysUser) {
        return sysUserService.insert(sysUser);
    }

    @PutMapping("/update")
    public int update(SysUser sysUser) {
        return sysUserService.update(sysUser);
    }

    @PutMapping("/updateAll")
    public int updateAll(SysUser sysUser) {
        return sysUserService.updateAll(sysUser);
    }

    @GetMapping("/query")
    public List<SysUser> query(Map filters) {
        return sysUserService.query(filters);
    }

    @GetMapping("/queryByAccount/{account}")
    public SysUser queryByAccount(@PathVariable String account) {
        return sysUserService.queryByAccount(account);
    }

    @DeleteMapping("/remove/{userId}")
    public void remove(@PathVariable String userId) {
        sysUserService.remove(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId) {
        sysUserService.delete(userId);
    }

    @GetMapping("/queryExistUserAccount/{account}")
    public int queryExistUserAccount(@PathVariable String account) {
        return sysUserService.queryExistUserAccount(account);
    }

    @GetMapping("/queryByUserId/{userId}")
    public SysUser queryByUserId(@PathVariable String userId) {
        return sysUserService.queryByUserId(userId);
    }

    @GetMapping("/queryUserNamesByRoleId/{roleId}")
    public List<String> queryUserNamesByRoleId(@PathVariable String roleId) {
        return sysUserService.queryUserNamesByRoleId(roleId);
    }

    @GetMapping("/queryUserSidsByRoleId/{roleId}")
    public List<String> queryUserSidsByRoleId(@PathVariable String roleId) {
        return sysUserService.queryUserSidsByRoleId(roleId);
    }

    @GetMapping("/queryAdminsSid")
    public List<String> queryAdminsSid() {
        return sysUserService.queryAdminsSid();
    }

    @GetMapping("/queryExistUserAccountIncludeDel/{account}")
    public int queryExistUserAccountIncludeDel(@PathVariable String account) {
        return sysUserService.queryExistUserAccountIncludeDel(account);
    }

    @PutMapping("/updateDefaultUserStatus")
    public void updateDefaultUserStatus(SysUser sysUser) {
        sysUserService.update(sysUser);
    }

    @GetMapping("/querySysUserList")
    public List<SysUser> querySysUserList() {
        return sysUserService.querySysUserList();
    }

    @GetMapping("/queryAllSysUserInfo")
    public List<String> queryAllSysUserInfo() {
        return sysUserService.queryAllSysUserInfo();
    }

    @GetMapping("/querySysUserBySid")
    public List<SysUser> querySysUserBySid(Map filters) {
        return sysUserService.querySysUserBySid(filters);
    }

    @PutMapping("/batchUpdateValueByPropKey")
    public void batchUpdateValueByPropKey(SysUser sysUser) {
        sysUserService.batchUpdateValueByPropKey(sysUser);
    }

    @GetMapping("/getSysMenuInfo")
    public List<SysUser> getSysMenuInfo(Map filters) {
        return sysUserService.getSysMenuInfo(filters);
    }
}
