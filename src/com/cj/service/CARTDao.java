package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.CART;
import com.cj.entity.CATEGORY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CARTDao {

    /**
     * 插入一个购物车对象
     *
     * @param cart
     * @return
     */
    public static int Insert(CART cart) {
        String sql = "insert into cart values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                cart.getCART_P_FILENAME(), cart.getCART_P_NAME(),
                cart.getCART_P_PRICE(), cart.getCART_QUANTITY(),
                cart.getCART_P_STOCK(), cart.getCART_P_ID(),
                cart.getCART_U_ID(), cart.getCART_VALID()
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }

    /**
     * 通过id删除购物车
     *
     * @param id
     * @return
     */
    public static int DeleteById(int id) {
        String sql = "delete from cart where CART_ID=?";
        Object[] params = {
                id
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }


    /**
     * 更新特定id的购物车中物品数量
     *
     * @param id
     * @param num
     * @return
     */
    public static int UpdateNum(int id, int num) {
        String sql = "update cart set CART_QUANTITY=? where CART_ID=?";
        Object[] params = {
                num, id
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }

    /**
     * 更新购物车表中的库存
     *
     * @param id
     * @param stock
     * @return
     */
    public static int UpdateStock(int id, int stock) {
        String sql = "update cart set CART_P_STOCK=? where CART_ID=?";
        Object[] params = {
                stock, id
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }

    /**
     * 更新购物车中的valid标记位
     *
     * @param id
     * @param valid
     * @return
     */
    public static int UpdateValid(int id, int valid) {
        String sql = "update cart set CART_VALID=? where CART_ID=?";
        Object[] params = {
                valid, id
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }

    /**
     * 根据用户id查找他的所有购物车
     *
     * @param uid
     * @return
     */
    public static List<CART> QueryByUId(String uid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        List<CART> carts = new ArrayList<CART>();
        try {
            //标记位为1表示还未结算购物车, 并且按照商品添加的顺序降序排列, 新添加的在上面
            sql = "select * from cart where CART_U_ID=? and CART_VALID=1 order by CART_ID desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);

            rs = ps.executeQuery();
            while (rs.next()) {
                CART cart = new CART(
                        rs.getInt("CART_ID"),
                        rs.getString("CART_P_FILENAME"),
                        rs.getString("CART_P_NAME"),
                        rs.getInt("CART_P_PRICE"),
                        rs.getInt("CART_QUANTITY"),
                        rs.getInt("CART_P_STOCK"),
                        rs.getInt("CART_P_ID"),
                        rs.getString("CART_U_ID"),
                        rs.getInt("CART_VALID")
                );
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return carts;
    }

    /**
     * 根据购物车id查询
     *
     * @param id
     * @return
     */
    public static CART QueryById(int id) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        CART cart = null;
        try {
            sql = "select * from cart where CART_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                cart = new CART(
                        rs.getInt("CART_ID"),
                        rs.getString("CART_P_FILENAME"),
                        rs.getString("CART_P_NAME"),
                        rs.getInt("CART_P_PRICE"),
                        rs.getInt("CART_QUANTITY"),
                        rs.getInt("CART_P_STOCK"),
                        rs.getInt("CART_P_ID"),
                        rs.getString("CART_U_ID"),
                        rs.getInt("CART_VALID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return cart;
    }

    /**
     * 根据用户id和购买物品的id查找购物车
     *
     * @param uid
     * @param pid
     * @return
     */
    public static CART QueryByU_P_Id(String uid, int pid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        CART cart = null;
        try {
            sql = "select * from cart where CART_U_ID=? and CART_P_ID=? and CART_VALID=1 order by CART_ID desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, pid);

            rs = ps.executeQuery();
            while (rs.next()) {
                cart = new CART(
                        rs.getInt("CART_ID"),
                        rs.getString("CART_P_FILENAME"),
                        rs.getString("CART_P_NAME"),
                        rs.getInt("CART_P_PRICE"),
                        rs.getInt("CART_QUANTITY"),
                        rs.getInt("CART_P_STOCK"),
                        rs.getInt("CART_P_ID"),
                        rs.getString("CART_U_ID"),
                        rs.getInt("CART_VALID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return cart;
    }

}
