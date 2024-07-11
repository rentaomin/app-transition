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
        String sql = "CREATE DATABASE IF NOt EXISTS logdata222;\n" +
                "-- logdata.t_log_http definition\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS logdata222.t_log_http\n" +
                "(\n" +
                "    c_id               UInt64 COMMENT '主键',\n" +
                "    c_domain           String COMMENT '域名',\n" +
                "    c_methods          String COMMENT '请求方法',\n" +
                "    c_req_url          String COMMENT '请求url',\n" +
                "    c_sid              String COMMENT '请求的唯一标识',\n" +
                "    c_sid_res_index    UInt64 COMMENT '响应id索引',\n" +
                "    c_sid_req_index    UInt64 COMMENT '会话id索引',\n" +
                "    c_req_ip           String COMMENT '请求服务的IP',\n" +
                "    c_src_port         UInt64 COMMENT '监听端口',\n" +
                "    c_target_ip        String COMMENT '目标ip',\n" +
                "    c_target_port      UInt64 COMMENT '目标端口',\n" +
                "    c_http_sub         String COMMENT 'tcp,smtp,ssh,telnet',\n" +
                "    c_req_len          UInt64 COMMENT '单位字节',\n" +
                "    c_res_len          UInt64 COMMENT '单位字节',\n" +
                "    c_dev_id           String COMMENT '设备id',\n" +
                "    c_user_id          String COMMENT '用户id',\n" +
                "    c_req_app_id       String COMMENT '请求应用id',\n" +
                "    c_req_app_name     String COMMENT '请求应用名称',\n" +
                "    c_app_id           String COMMENT '目标应用id',\n" +
                "    c_app_name         String COMMENT '目标应用名称',\n" +
                "    c_req_area         String COMMENT '请求区域',\n" +
                "    c_target_area      String COMMENT '目标区域',\n" +
                "    c_result           UInt64 COMMENT '请求结果0：失败1：成功',\n" +
                "    c_spend_time       UInt64 COMMENT '耗时:单位毫秒',\n" +
                "    c_note             String COMMENT '节点',\n" +
                "    c_create_time      DateTime COMMENT '创建时间',\n" +
                "    c_is_report        UInt64 COMMENT '0:未上报1：已上报',\n" +
                "    c_target_res_data  String COMMENT '最多放4000个，超过截取',\n" +
                "    c_req_data         String COMMENT '请求数据',\n" +
                "    c_app_service_id   String COMMENT '应用服务id',\n" +
                "    c_app_service_name String COMMENT '应用服务名称',\n" +
                "    c_org_code         String COMMENT '预留页面不显示',\n" +
                "    c_org_name         String COMMENT '预留页面不显示'\n" +
                ") ENGINE = MergeTree()\n" +
                "      PRIMARY KEY c_id\n" +
                "      ORDER BY c_id\n" +
                "      PARTITION BY toYYYYMMDD(c_create_time) COMMENT 'http代理日志';\n" +
                "\n" +
                "-- logdata.t_log_http_web definition\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS logdata222.t_log_http_web\n" +
                "(\n" +
                "    c_id          UInt64 COMMENT '主键',\n" +
                "    c_domain      String COMMENT '域名',\n" +
                "    c_methods     String COMMENT 'post,get',\n" +
                "    c_port        UInt64 COMMENT '80,443',\n" +
                "    c_user_id     String COMMENT '用户Id，从t_dev_state中获取',\n" +
                "    c_dev_id      String COMMENT '设备Id，从t_dev_state中获取',\n" +
                "    c_req_len     UInt64 COMMENT '请求长度',\n" +
                "    c_res_len     UInt64 COMMENT '单位字节',\n" +
                "    c_type        UInt64 COMMENT '请求类型',\n" +
                "    c_req_url     String COMMENT '请求URl',\n" +
                "    c_req_ip      String COMMENT '请求服务的IP',\n" +
                "    c_result      UInt64 COMMENT '请求结果0：失败1：成功',\n" +
                "    c_spend_time  UInt64 COMMENT '耗时:单位毫秒',\n" +
                "    c_note        String COMMENT '节点',\n" +
                "    c_create_time DateTime COMMENT '创建时间',\n" +
                "    c_is_report   UInt64 COMMENT '0:未上报1：已上报'\n" +
                ") ENGINE = MergeTree()\n" +
                "      PRIMARY KEY c_id\n" +
                "      ORDER BY c_id\n" +
                "      PARTITION BY toYYYYMMDD(c_create_time) COMMENT '互联网代理日志';";
        log.error("clickhouse 正在执行初始化sql !");
        return Collections.singletonList(sql);
    }

    @Override
    public boolean enable() {
        return true;
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
