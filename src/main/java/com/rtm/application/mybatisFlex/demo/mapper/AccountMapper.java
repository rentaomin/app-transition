package com.rtm.application.mybatisFlex.demo.mapper;


import com.mybatisflex.core.BaseMapper;
import com.rtm.application.mybatisFlex.demo.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.Map;

//public interface AccountMapper extends BaseMapper<Account> {
@Repository
public interface AccountMapper extends BaseMapper<Account> {

    int insertAccount(Account record);

    int insertTestBolb(Map<String,Blob> params);

    int insertTestByte(Map<String,Object> params);
}
