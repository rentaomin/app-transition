package com.rtm.application.mybatisFlex.demo.service;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.configure.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GbaseSqlInitStatement implements SqlInitStatement {

    @Override
    public String getSql() {
        log.error("gbase 正在执行初始化sql !");
        return "create database fff";
    }

    @Override
    public boolean hasInit() {
        return false;
    }

    @Override
    public void completeCallback(boolean result) {

    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public DbType getDbType() {
        return DbType.GBASE_8S;
    }
}
