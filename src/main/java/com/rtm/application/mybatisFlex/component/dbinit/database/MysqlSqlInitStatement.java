package com.rtm.application.mybatisFlex.component.dbinit.database;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MysqlSqlInitStatement implements SqlInitStatement {

    private boolean init = false;

    @Override
    public List<String> getSql() {
        log.error("mysql 正在执行初始化sql !");
        return null;
    }

    @Override
    public boolean hasInit() {
        return this.init;
    }

    @Override
    public void completeInit(boolean result) {
        this.init = result;
    }

    @Override
    public boolean enable() {
        return false;
    }

    @Override
    public DbType getDbType() {
        return DbType.MYSQL;
    }
}
