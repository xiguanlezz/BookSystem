package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.PAY;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;

import java.sql.*;
import java.util.List;

public class PAYDao {

    /**
     * 插入付款对象, 并返回自增的id
     *
     * @param pay
     * @return
     */
    public static int Insert(PAY pay) {
        String sql = "insert into pay values(null, ?, ?, ?, ?, ?)";
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        int count = 1;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pay.getPAY_SEND_START_TIME());
            ps.setString(2, pay.getPAY_SEND_END_TIME());
            ps.setString(3, pay.getPAY_SEND_TAKE_TIME());
            ps.setString(4, pay.getPAY_WAY());
            ps.setString(5, pay.getPAY_SEND_WAY());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return count;
    }

    /**
     * 根据id查询pay对象
     *
     * @param pid
     * @return
     */
    public static PAY QueryById(int pid) {
        ResultSet rs = null;
        PAY pay = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        USER user = null;

        try {
            sql = "select * from pay where PAY_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);

            rs = ps.executeQuery();
            while (rs.next()) {
                pay = new PAY(
                        rs.getInt("PAY_ID"),
                        rs.getString("PAY_SEND_START_TIME"),
                        rs.getString("PAY_SEND_END_TIME"),
                        rs.getString("PAY_SEND_TAKE_TIME"),
                        rs.getString("PAY_WAY"),
                        rs.getString("PAY_SEND_WAY")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return pay;
    }
}
