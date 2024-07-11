package com.rtm.application.mybatisFlex.component.dbinit.database;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ClickHouseSqlInitStatement implements SqlInitStatement {

    @Override
    public String getSql() {
        log.error("clickhouse 正在执行初始化sql !");
        return null;
    }

    @Override
    public boolean hasInit() {
        return false;
    }

    @Override
    public void completeInit(boolean result) {

    }

    @Override
    public DbType getDbType() {
        return DbType.CLICK_HOUSE;
    }
}
