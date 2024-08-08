package com.rtm.application.mybatisFlex.demo.service.impl;

import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.rtm.application.mybatisFlex.component.dbinit.DataSourceProperties;
import com.rtm.application.mybatisFlex.demo.entity.Account;
import com.rtm.application.mybatisFlex.demo.mapper.AccountMapper;
import com.rtm.application.mybatisFlex.demo.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(Account account) {
//        int insert = accountMapper.insertAccount(account);

//        int a = 1/ 0;

        insertByte(account);
        return 0;
    }

    public int insertByte(Account account) {

        Map<String, Object> params = new HashMap<>();
        String path = "E:\\gitProject\\ws-bms\\bms-general-starter\\bms-general-auth-starter\\pom.xml";
        try {
            byte[] bytes = readFileToByteArray(path);
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
            params.put("info", bytes);
            accountMapper.insertTestByte(params);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        int a = 1/ 0;
        return 0;
    }
    public int insertBlob(Account account) {

        Map<String, Blob> params = new HashMap<>();
        String path = "E:\\gitProject\\ws-bms\\bms-general-starter\\bms-general-auth-starter\\pom.xml";
        try {
            byte[] bytes = readFileToByteArray(path);
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
            params.put("info", blob);
            accountMapper.insertTestBolb(params);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        int a = 1/ 0;
        return 0;
    }


    public static byte[] readFileToByteArray(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}
