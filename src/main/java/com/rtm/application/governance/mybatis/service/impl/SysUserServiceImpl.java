package com.rtm.application.governance.mybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rtm.application.governance.mybatis.entity.SysUser;
import com.rtm.application.governance.mybatis.mapper.SysMapper;
import com.rtm.application.governance.mybatis.mapper.SysUserMapper;
import com.rtm.application.governance.mybatis.service.SysUserService;
import com.rtm.application.mybatisFlex.demo.entity.PageQueryResult;
import com.rtm.application.mybatisFlex.demo.entity.ServiceStatusInfo;
import com.rtm.application.mybatisFlex.demo.mapper.ServiceStatusInfoMapper;
import com.rtm.application.mybatisFlex.demo.mapper.SystemParamConfigMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMapper sysMapper;

    @Resource
    private ServiceStatusInfoMapper serviceStatusInfoMapper;

    public SysUser setUp() {
        SysUser sysUser = new SysUser();
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setAccount("test");
        sysUser.setRealName("Test User");
        sysUser.setPassword("password");
        sysUser.setSalt("salt");
        sysUser.setRoleId("1");
        sysUser.setAdminType(1);
        sysUser.setEmail("test@example.com");
        sysUser.setMobile("1234567890");
        sysUser.setStartIp("192.168.1.1");
        sysUser.setEndIp("192.168.1.255");
        sysUser.setDisplay(2);
        sysUser.setIsFirstLogin(2);
        sysUser.setIsLocked(1);
        sysUser.setLastLoginTime("2017-06-09 16:38:36");
        sysUser.setLastLoginIp("192.168.1.100");
        sysUser.setIsRemoved(0);
        sysUser.setNowLoginTime("2017-06-09 16:38:36");
        sysUser.setNowLoginIp("192.168.1.200");
        sysUser.setCreateTime("2017-06-09 16:38:36");
        sysUser.setCreater("1");
        sysUser.setUpdateTime("2017-06-09 16:38:36");
        sysUser.setUpdater("1");
        sysUser.setVersion("1");
        sysUser.setLastPwdModTime("2017-06-09 16:38:36");
        sysUser.setLastLockTime("2017-06-09 16:38:36");
        sysUser.setPwdErrorNums(3);
        sysUser.setLastPwdWrongTime("2017-06-09 16:38:36");
        return sysUser;
    }

    @Override
    public int insert(SysUser sysUser) {
        SysUser user = setUp();

        return sysUserMapper.insert(user);
    }

    @Override
    public int update(SysUser sysUser) {
        SysUser user = updateSetUp();
        return sysUserMapper.update(user);
    }

    public SysUser updateSetUp() {
        SysUser sysUser = new SysUser();
        sysUser.setId("6106d0f4-db78-411f-bc54-518aa7274506");
        sysUser.setAccount("test3");
        sysUser.setRealName("Test User3");
        sysUser.setPassword("password3");
        sysUser.setSalt("salt3");
        sysUser.setRoleId("13");
        sysUser.setAdminType(13);
        sysUser.setEmail("test@example.com3");
        sysUser.setMobile("12345678903");
        sysUser.setStartIp("192.168.1.13");
        sysUser.setEndIp("192.168.1.2553");
        sysUser.setDisplay(23);
        sysUser.setIsFirstLogin(23);
        sysUser.setIsLocked(3);
        sysUser.setLastLoginTime("2017-06-09 16:38:33");
        sysUser.setLastLoginIp("192.168.1.103");
        sysUser.setIsRemoved(1);
        sysUser.setNowLoginTime("2017-06-09 16:38:33");
        sysUser.setNowLoginIp("192.168.1.203");
        sysUser.setCreateTime("2017-06-09 16:38:33");
        sysUser.setCreater("3");
        sysUser.setUpdateTime("2017-06-09 16:38:36");
        sysUser.setUpdater("3");
        sysUser.setVersion("3");
        sysUser.setLastPwdModTime("2017-06-09 16:38:33");
        sysUser.setLastLockTime("2017-06-09 16:38:33");
        sysUser.setPwdErrorNums(4);
        sysUser.setLastPwdWrongTime("2017-06-09 16:38:33");
        return sysUser;
    }

    @Override
    public int updateAll(SysUser sysUser) {
        return sysUserMapper.updateAll(sysUser);
    }

    @Override
    public List<SysUser> query(Map filters) {
        return sysUserMapper.query(filters);
    }


    @Resource
    private SystemParamConfigMapper systemParamConfigMapper;

    @Override
    public SysUser queryByAccount(String account) {

        PageQueryResult pageQueryResult = queryRoleList(2, 3);
        System.out.println(pageQueryResult);
//        int i = serviceStatusInfoMapper.deleteExpireInfoByDate("2024-08-01 13:35:30");
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq(SysUser::getRealName,"admin");
//        List<SysUser> sysUsers = sysMapper.selectListByQuery(queryWrapper);
//        System.out.println(sysUsers.size());
//        SysUser user = sysUserMapper.queryByAccount(account);
        return null;
    }

    public PageQueryResult queryRoleList(Integer pageNum, Integer pageSize) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        Map<String,Object> param = new HashMap<>();
        // 不翻页查询
        if (StringUtils.isEmpty(pageNum.toString()) || StringUtils.isEmpty(pageSize.toString())){
            List<ServiceStatusInfo> roleInfoList = serviceStatusInfoMapper.list(param);
            pageQueryResult.setTotalCount(roleInfoList.size());
            pageQueryResult.setList(roleInfoList);
        } else {
            Page<Object> page = PageHelper.startPage(pageNum, pageSize, true);
            System.out.println(page);
            param.put("timeLength",12);
            List<ServiceStatusInfo> roleInfoList = null;
            try {
                roleInfoList = serviceStatusInfoMapper.list(param);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            pageQueryResult.setTotalCount(page.getTotal());
            pageQueryResult.setList(roleInfoList);
        }
        return pageQueryResult;
    }

    @Override
    public void remove(String userId) {
        sysUserMapper.remove(userId);
    }

    @Override
    public void delete(String userId) {
        sysUserMapper.delete(userId);
    }

    @Override
    public int queryExistUserAccount(String account) {
        return sysUserMapper.queryExistUserAccount(account);
    }

    @Override
    public SysUser queryByUserId(String userId) {
        return sysUserMapper.queryByUserId(userId);
    }

    @Override
    public List<String> queryUserNamesByRoleId(String roleId) {
        return sysUserMapper.queryUserNamesByRoleId(roleId);
    }

    @Override
    public List<String> queryUserSidsByRoleId(String roleId) {
        return sysUserMapper.queryUserSidsByRoleId(roleId);
    }

    @Override
    public List<String> queryAdminsSid() {
        return sysUserMapper.queryAdminsSid();
    }

    @Override
    public int queryExistUserAccountIncludeDel(String account) {
        return sysUserMapper.queryExistUserAccountIncludeDel(account);
    }

    @Override
    public void updateDefaultUserStatus(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public List<SysUser> querySysUserList() {
        return sysUserMapper.querySysUserList();
    }

    @Override
    public List<String> queryAllSysUserInfo() {
        return sysUserMapper.queryAllSysUserInfo();
    }

    @Override
    public List<SysUser> querySysUserBySid(Map filters) {
        filters = new HashMap();
        filters.put("roleId",1);
        List<String> ids = Arrays.asList("B5A33586-15104954-9B752AE1-53333EC20","6106d0f4-db78-411f-bc54-518aa7274506");
        filters.put("list",ids);
        return sysUserMapper.querySysUserBySid(filters);
    }

    @Override
    public void batchUpdateValueByPropKey(SysUser sysUser) {
        List<SysUser> list = new ArrayList<>();
        SysUser user = new SysUser();
        user.setAccount("test3-22");
        user.setRealName("Test User3");
        list.add(user);

        SysUser user2 = new SysUser();
        user2.setAccount("admin22-22");
        user2.setRealName("admin22");
        list.add(user2);
        sysUserMapper.batchUpdateValueByPropKey(list);
    }

    @Override
    public List<SysUser> getSysMenuInfo(Map filters) {
        filters = new HashMap();
        filters.put("length",2);
        return sysUserMapper.getSysMenuInfo(filters);
    }
}
