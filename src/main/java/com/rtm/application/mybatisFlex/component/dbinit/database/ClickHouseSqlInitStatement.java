package com.rtm.application.mybatisFlex.component.dbinit.database;

import com.mybatisflex.core.dialect.DbType;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class ClickHouseSqlInitStatement implements SqlInitStatement {

    @Override
    public List<String> getSql() {
        return  Collections.emptyList();
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

    @Override
    public boolean enable() {
        return true;
    }
}
