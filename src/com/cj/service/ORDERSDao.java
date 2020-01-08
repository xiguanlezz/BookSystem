package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.CART;
import com.cj.entity.CATEGORY;
import com.cj.entity.ORDERS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ORDERSDao {

    /**
     * 插入一份订单, 并返回自增的id
     *
     * @param orders
     * @return
     */
    public static int Insert(ORDERS orders) {
        String sql = "insert into orders values(null, ?, DATE_FORMAT(?, '%Y-%m-%d %h:%i'), ?, ?, ?, ?, ?)";
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        int count = 1;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, orders.getORDER_U_ID());
            ps.setString(2, orders.getORDER_TIME());
            ps.setInt(3, orders.getORDER_TOTAL_PRICE());
            ps.setInt(4, orders.getORDER_PAY_ID());
            ps.setInt(5, orders.getORDER_ADDRESS_ID());
            ps.setString(6, orders.getORDER_REMARK());
            ps.setString(7, orders.getORDER_STATUS());

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
     * 更新订单的状态
     *
     * @param oid
     * @param status
     * @return
     */
    public static int UpdateStatus(int oid, String status) {
        String sql = "update orders set ORDER_STATUS=? where ORDER_ID=?";
        Object[] params = {
                status, oid
        };
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id查询订单
     *
     * @param oid
     * @return
     */
    public static ORDERS QueryById(int oid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        ORDERS orders = null;
        try {
            sql = "select * from orders where ORDER_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);

            rs = ps.executeQuery();
            while (rs.next()) {
                orders = new ORDERS(
                        rs.getInt("ORDER_ID"),
                        rs.getString("ORDER_U_ID"),
                        rs.getString("ORDER_TIME"),
                        rs.getInt("ORDER_TOTAL_PRICE"),
                        rs.getInt("ORDER_PAY_ID"),
                        rs.getInt("ORDER_ADDRESS_ID"),
                        rs.getString("ORDER_REMARK"),
                        rs.getString("ORDER_STATUS")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return orders;
    }


    /**
     * 根据用户id查询所有订单
     *
     * @param uid
     * @return
     */
    public static List<ORDERS> QueryByUId(String uid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        List<ORDERS> orders = new ArrayList<ORDERS>();

        try {
            sql = "select * from orders where ORDER_U_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);

            rs = ps.executeQuery();
            while (rs.next()) {
                ORDERS o = new ORDERS(
                        rs.getInt("ORDER_ID"),
                        rs.getString("ORDER_U_ID"),
                        rs.getString("ORDER_TIME"),
                        rs.getInt("ORDER_TOTAL_PRICE"),
                        rs.getInt("ORDER_PAY_ID"),
                        rs.getInt("ORDER_ADDRESS_ID"),
                        rs.getString("ORDER_REMARK"),
                        rs.getString("ORDER_STATUS")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return orders;
    }


    /**
     * 查询所有订单
     *
     * @return
     */
    public static List<ORDERS> QueryAll() {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        List<ORDERS> orders = new ArrayList<ORDERS>();

        try {
            sql = "select * from orders";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                ORDERS o = new ORDERS(
                        rs.getInt("ORDER_ID"),
                        rs.getString("ORDER_U_ID"),
                        rs.getString("ORDER_TIME"),
                        rs.getInt("ORDER_TOTAL_PRICE"),
                        rs.getInt("ORDER_PAY_ID"),
                        rs.getInt("ORDER_ADDRESS_ID"),
                        rs.getString("ORDER_REMARK"),
                        rs.getString("ORDER_STATUS")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return orders;
    }
}
