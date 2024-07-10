package com.rtm.application.mybatisFlex.demo.service;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.configure.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MysqlSqlInitStatement implements SqlInitStatement {

    private boolean init = false;

    @Override
    public String getSql() {
        log.error("mysql 正在执行初始化sql !");
        return "create database fff";
    }

    @Override
    public boolean hasInit() {
        return this.init;
    }

    @Override
    public void completeCallback(boolean result) {
        this.init = result;
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public DbType getDbType() {
        return DbType.MYSQL;
    }
}
