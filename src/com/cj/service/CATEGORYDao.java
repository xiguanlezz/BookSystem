package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.CATEGORY;
import com.cj.entity.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CATEGORYDao {

    /**
     * 插入一个分类
     *
     * @param category
     * @return
     */
    public static int Insert(CATEGORY category) {
        String sql = "insert into category values(null, ?, ?)"; //CATE_ID会自动增长
        Object[] params = {
                category.getCATE_NAME(), category.getCATE_PARENT_ID()
        };
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id删除某个分类
     *
     * @param id
     * @return
     */
    public static int DeleteById(int id) {
        String sql = "delete from category where CATE_ID=?";
        Object[] params = {id};
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id修改分类
     *
     * @param cid
     * @param category
     * @return
     */
    public static int Update(int cid, CATEGORY category) {
        String sql = "update category set CATE_NAME=?, CATE_PARENT_ID=? where CATE_ID = ?";
        Object[] params = {
                category.getCATE_NAME(),
                category.getCATE_PARENT_ID(),
                cid
        };
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id查找分类
     *
     * @param id
     * @return
     */
    public static CATEGORY QueryById(int id) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        CATEGORY cate = null;

        try {
            sql = "select * from category where CATE_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                cate = new CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return cate;
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    public static List<CATEGORY> QueryAll() {
        List<CATEGORY> list = new ArrayList<CATEGORY>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from category";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                CATEGORY category = new CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")
                );
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 查询子分类和父级分类
     *
     * @param flag flag="father"  flag="child"
     * @return
     */
    public static List<CATEGORY> QueryTwoCate(String flag) {
        List<CATEGORY> list = new ArrayList<CATEGORY>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            if (flag != null && "father".equals(flag)) {
                sql = "select * from category where CATE_PARENT_ID=0";
            } else if (flag != null && "child".equals(flag)) {
                sql = "select * from category where CATE_PARENT_ID!=0";
            }

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                CATEGORY category = new CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")
                );
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }
}
