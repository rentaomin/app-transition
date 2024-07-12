package com.rtm.application.mybatisFlex.component.dbinit;

import com.mybatisflex.core.dialect.DbType;
import java.util.List;

/**
 *  该接口主要为提供数据库执行初始化 sql, 若需要在应用启动时执行 sql ,则实现该接口
 */
public interface SqlInitStatement {

    /**
     *  获取需要初始化执行的 sql 语句，分批次执行，建议 DDL 和 DML 语句分开执行，避免部分数据库不兼容问题
     * @return 返回需要初始化执行是 sql
     */
    List<String> getSql();

    /**
     *  是否需要启动执行初始化 sql, 默认 false 不执行初始化
     * @return 返回 true 则进行初始化，反之 false 不初始化
     */
    default boolean enable() {
        return Boolean.FALSE;
    }

    /**
     *  是否允许重复执行初始化，默认 false
     * @return 返回 true 则允许， 反之 false 不允许
     */
    default boolean repeatExecute() {
        return Boolean.FALSE;
    }

    /**
     *  判断是否已经完成初始化 ,通过 {@linkplain #completeInit(boolean) 执行完成初始化标记}
     * @return 返回 true 则完成初始化，反之 false
     */
    boolean hasInit();

    /**
     *  初始化执行完成回调函数,该方法为回调函数，sql初始化执行完成后，调用该方法
     * @param result 初始化 sql 执行结果
     * @return 返回 true 则执行成功 反之 false 失败
     */
    void completeInit(boolean result);

    /**
     *  获取数据库类型,更多类型可查看 {@linkplain DbType}
     * @return 返回当前执行 sql 的数据库类型
     */
    default DbType getDbType() {
        return DbType.MYSQL;
    }

    /**
     *  多业务模块下 sql 语句执行顺序优先级，仅在同一个数据库类型下生效,值越大越优先
     * @return 返回当前顺序优先级，默认为 1
     */
    default int getOrder() {
        return 1;
    }

}
