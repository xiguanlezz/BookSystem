package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.ITEM;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITEMDao {

    /**
     * 插入订单中的某件物品对象
     *
     * @param item
     * @return
     */
    public static int Insert(ITEM item) {
        String sql = "insert into item values(null, ?, ?, ?)";
        Object[] params = {
                item.getITEM_O_ID(), item.getITEM_P_ID(), item.getITEM_P_QUANTITY()
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }

    /**
     * 根据pid查询item明细
     * @param pid
     * @return
     */
    public static ITEM QueryByPId(int pid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        ITEM item = null;

        try {
            sql = "select * from item where ITEM_P_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);

            rs = ps.executeQuery();
            while (rs.next()) {
                item = new ITEM(rs.getInt("ITEM_ID"),
                        rs.getInt("ITEM_O_ID"),
                        rs.getInt("ITEM_P_ID"),
                        rs.getInt("ITEM_P_QUANTITY")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return item;
    }


    /**
     * 根据订单id查询
     *
     * @param oid
     * @return
     */
    public static List<ITEM> QueryByOId(int oid) {
        ResultSet rs = null;
        List<ITEM> list = new ArrayList<ITEM>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        ITEM item = null;

        try {
            sql = "select * from item where ITEM_O_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);

            rs = ps.executeQuery();
            while (rs.next()) {
                ITEM i = new ITEM(rs.getInt("ITEM_ID"),
                        rs.getInt("ITEM_O_ID"),
                        rs.getInt("ITEM_P_ID"),
                        rs.getInt("ITEM_P_QUANTITY")
                );
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }
}
