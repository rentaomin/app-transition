package com.rtm.application.mybatisFlex.demo;

import java.sql.*;

public class CasDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.gbasedbt.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:gbasedbt-sqli://192.168.149.99:9088/bms_general_aa:GBASEDBTSERVER=dtpMng;delimident=y;SQLMODE=GBase;DB_LOCALE=zh_CN.57372;", "gbasedbt", "Ws-123456,.");
//        Statement statement = conn.createStatement();
//        String sql = "insert into t_student values (2,'admin',1)";
//        boolean execute = false;
//        try {
//            execute = statement.execute(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(execute);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("database test;");
        stringBuilder.append("");
        stringBuilder.append("INSERT INTO test:fff (name) VALUES ('测试');");
//        try (Connection connection = DriverManager.getConnection("jdbc:gbasedbt-sqli://192.168.149.99:9098/bms_general_uba:DB_LOCALE=zh_CN.57372;DB_LOCALE=zh_CN.57372;", "gbasedbt", "Ws-123456,.");
        try (Connection connection = DriverManager.getConnection("jdbc:gbasedbt-sqli://192.168.149.99:9098/sys:GBASEDBTSERVER=dtpInstance;DB_LOCALE=zh_CN.57372;DB_LOCALE=zh_CN.57372;", "gbasedbt", "Ws-123456,.");
             Statement statement = connection.createStatement()) {
            String sql = stringBuilder.toString();
            boolean execute = statement.execute(sql);
            System.out.println(execute);
        }
    }
}
