package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.ADDRESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ADDRESSDao {

    /**
     * 插入新地址
     *
     * @param address
     * @return
     */
    public static int Insert(ADDRESS address) {
        String sql = "insert into address values(null, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                address.getADDRESS_U_ID(), address.getADDRESS_U_NAME(),
                address.getADDRESS_U_MOBILE(), address.getADDRESS_PROVINCE(),
                address.getADDRESS_CITY(), address.getADDRESS_DETAILED_LOCATION(),
                address.getADDRESS_ZIPCODE()
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }


    /**
     * 根据地址id删除某个地址
     *
     * @param aid
     * @return
     */
    public static int DeleteById(int aid) {
        String sql = "delete from address where ADDRESS_ID=?";
        Object[] params = {
                aid
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }


    /**
     * 根据用户id更新他的购物地址表
     *
     * @param name
     * @param mobile
     * @param province
     * @param city
     * @param address
     * @param zipcode
     * @return
     */
    public static int UpdataByUId(String uid, String name, String mobile, String province, String city, String address, String zipcode) {
        String sql = "update address set ADDRESS_U_NAME=?, ADDRESS_U_MOBILE=?, ADDRESS_PROVINCE=?, ADDRESS_CITY=?," +
                "ADDRESS_DETAILED_LOCATION=?, ADDRESS_ZIPCODE=? where ADDRESS_U_ID=?";
        Object[] params = {
                name, mobile, province, city, address, zipcode, uid
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }


    /**
     * 根据id查询地址
     * @param aid
     * @return
     */
    public static ADDRESS QueryById(int aid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        ADDRESS a = null;
        String sql = "";

        try {
            sql = "select * from address where ADDRESS_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, aid);

            rs = ps.executeQuery();
            while (rs.next()) {
                a = new ADDRESS(
                        rs.getInt("ADDRESS_ID"),
                        rs.getString("ADDRESS_U_ID"),
                        rs.getString("ADDRESS_U_NAME"),
                        rs.getString("ADDRESS_U_MOBILE"),
                        rs.getString("ADDRESS_PROVINCE"),
                        rs.getString("ADDRESS_CITY"),
                        rs.getString("ADDRESS_DETAILED_LOCATION"),
                        rs.getString("ADDRESS_ZIPCODE")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return a;
    }


    /**
     * 根据用户id查询他添加的所有地址信息
     *
     * @param uid
     * @return
     */
    public static List<ADDRESS> QueryAll(String uid) {
        List<ADDRESS> list = new ArrayList<ADDRESS>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from address";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                ADDRESS a = new ADDRESS(
                        rs.getInt("ADDRESS_ID"),
                        rs.getString("ADDRESS_U_ID"),
                        rs.getString("ADDRESS_U_NAME"),
                        rs.getString("ADDRESS_U_MOBILE"),
                        rs.getString("ADDRESS_PROVINCE"),
                        rs.getString("ADDRESS_CITY"),
                        rs.getString("ADDRESS_DETAILED_LOCATION"),
                        rs.getString("ADDRESS_ZIPCODE")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }
}
