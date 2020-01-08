package com.cj.dao;

import java.sql.*;

public class BaseDao {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        String dataBaseName = "keyshop";
        String url = "jdbc:mysql://localhost:3306/" + dataBaseName +
                "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true";
        String username = "root";
        String pwd = "123456";
        try {
            conn = DriverManager.getConnection(url, username, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 数据库增删改通用函数
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeIDU(String sql, Object[] params) {
        int count = 0;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
                //System.out.println(params[i]);
            }
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return count;
    }

    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
