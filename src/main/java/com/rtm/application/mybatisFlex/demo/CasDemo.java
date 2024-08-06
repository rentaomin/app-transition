package com.rtm.application.mybatisFlex.demo;

import java.sql.*;

public class CasDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.gbasedbt.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:gbasedbt-sqli://192.168.149.99:9088/bms_general_aa:GBASEDBTSERVER=dtpMng;delimident=y;SQLMODE=GBase;DB_LOCALE=zh_CN.57372;", "gbasedbt", "Ws-123456,.");
        Statement statement = conn.createStatement();
        String sql = "select id, createTime as \"createTime\", createUser as \"createUser\", modifyTime as \"modifyTime\", modifyUser as \"modifyUser\", sysParamKey as \"sysParamKey\", paramType as \"paramType\", propKey as \"propKey\", propName as \"propName\", propValue as \"propValue\", propValidExpr as \"propValidExpr\", propValue as \"propValue\", propTips as \"propTips\", isPassword as \"isPassword\", isShow as \"isShow\", isRequiredField as \"isRequiredField\", isEdit as \"isEdit\", paramDesc as \"paramDesc\", componentType as \"componentType\", optionalValues as \"optionalValues\" from t_sys_param_config";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            String createtime = rs.getString("createTime");
            String createTime = rs.getString("createtime");

            // 输出数据
            System.out.print(", 站点名称 createTime: " + createTime);
            System.out.print(", 站点 URL createtime: " + createtime);
            System.out.print("\n");
        }
    }
}
