package com.rtm.application.mybatisFlex.demo.service.impl;

import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.rtm.application.mybatisFlex.component.dbinit.DataSourceProperties;
import com.rtm.application.mybatisFlex.demo.entity.Account;
import com.rtm.application.mybatisFlex.demo.mapper.AccountMapper;
import com.rtm.application.mybatisFlex.demo.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;


    @Resource
    private FlexDataSource flexDataSource;

    @Resource
    private DataSourceProperties dataSourceProperties;


    @Override
    public Account select(String id) {
        Account account = null;
        try {
            account = accountMapper.selectOneById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        return account;
    }

    @Override
    public Account selectGBase(String id) {
        Account account = null;
        try {
            account = accountMapper.selectOneById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        return account;
    }


    @Override
    public List<Account> selectAll() {
        DataSourceKey.use("gbase");
        return accountMapper.selectAll();
    }

    @Override
    public int insert(Account account) {
        return accountMapper.insert(account);
    }
}
