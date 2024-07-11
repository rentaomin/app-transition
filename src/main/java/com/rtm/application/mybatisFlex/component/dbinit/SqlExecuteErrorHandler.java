package com.rtm.application.mybatisFlex.component.dbinit;

import com.mybatisflex.core.dialect.DbType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;

/**
 *  该接口主要用于处理应用启动执行初始化 SQL 出错时的逻辑，默认为 跳过错误，并打印错误日志
 */
public interface SqlExecuteErrorHandler {

    /**
     *  初始化执行 SQL 部分 SQL 出错是否继续执行
     * @return 返回  true 则出错继续执行， 反之 false 不执行
     */
    boolean errorContinue();

    /**
     *   报告错误信息，默认日志打印
     * @param dbType 数据库类型，详细查看 {@linkplain DbType}
     * @param sql 执行出错的 sql 语句
     */
    default void reportError(DbType dbType, String sql,SQLException exception) {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("数据库：{} 执行初始化 SQL 出错！", dbType, sql);
        logger.error("错误为：{} ",exception);
    }

}
