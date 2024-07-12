package com.rtm.application.mybatisFlex.component.dbinit.database;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class GbaseSqlInitStatement implements SqlInitStatement {

    @Override
    public List<String> getSql() {
        log.error("gbase 正在执行初始化sql !");
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
    public boolean enable() {
        return false;
    }

    @Override
    public DbType getDbType() {
        return DbType.GBASE_8S;
    }
}
