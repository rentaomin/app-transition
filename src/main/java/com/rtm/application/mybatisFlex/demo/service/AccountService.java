package com.rtm.application.mybatisFlex.demo.service;

import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.rtm.application.mybatisFlex.component.dbinit.DataSourceProperties;
import com.rtm.application.mybatisFlex.demo.entity.AccountInfo;
import com.rtm.application.mybatisFlex.demo.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;


    @Resource
    private FlexDataSource flexDataSource;

    @Resource
    private DataSourceProperties dataSourceProperties;

    public AccountInfo select(String id) {
        AccountInfo account = null;
        try {
            account = accountMapper.selectOneById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        return account;
    }

    public AccountInfo selectGBase(String id) {
        AccountInfo account = null;
        try {
            account = accountMapper.selectOneById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        return account;
    }

    public List<AccountInfo> selectAll() {
        DataSourceKey.use("gbase");
        return accountMapper.selectAll();
    }
}
