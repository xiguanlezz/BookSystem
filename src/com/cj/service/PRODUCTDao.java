package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.CATEGORY;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PRODUCTDao {

    /**
     * 插入一本书
     *
     * @param product
     * @return
     */
    public static int Insert(PRODUCT product) {
        String sql = "insert into product values(null, ?, ?, ?, ?, ?, ?, ?)";  //PRODUCT_ID会自动增长
        Object[] params = {
                product.getPRODUCT_NAME(),
                product.getPRODUCT_DESCRIPTION(),
                product.getPRODUCT_PRICE(),
                product.getPRODUCT_STOCK(),
                product.getPRODUCT_FID(),
                product.getPRODUCT_CID(),
                product.getPRODUCT_FILENAME()
        };
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 根据id删除一本书
     *
     * @param id
     * @return
     */
    public static int DeleteById(int id) {
        String sql = "delete from product where PRODUCT_ID=?";  //PRODUCT_ID会自动增长
        Object[] params = {
                id
        };
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 根据书的id修改书本信息
     *
     * @param pid
     * @param p
     * @return
     */
    public static int Update(int pid, PRODUCT p) {
        String sql = "update product set PRODUCT_NAME=?, PRODUCT_DESCRIPTION=?, PRODUCT_PRICE=?, " +
                "PRODUCT_STOCK=?, PRODUCT_FID=?, PRODUCT_CID=?, PRODUCT_FILENAME=? where PRODUCT_ID=?";
        Object[] params = {
                p.getPRODUCT_NAME(), p.getPRODUCT_DESCRIPTION(), p.getPRODUCT_PRICE(), p.getPRODUCT_STOCK(),
                p.getPRODUCT_FID(), p.getPRODUCT_CID(), p.getPRODUCT_FILENAME(), pid
        };
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 更新书本的库存
     * @param pid
     * @param newStock
     * @return
     */
    public static int UpdateStock(int pid,int newStock) {
        String sql = "update product set PRODUCT_STOCK=? where PRODUCT_ID=?";
        Object[] params = {
                newStock, pid
        };
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id查找这本图书
     *
     * @param id
     * @return
     */
    public static PRODUCT QueryById(int id) {
        ResultSet rs = null;
        PRODUCT product = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from product where PRODUCT_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                product = new PRODUCT(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PRODUCT_DESCRIPTION"),
                        rs.getInt("PRODUCT_PRICE"),
                        rs.getInt("PRODUCT_STOCK"),
                        rs.getInt("PRODUCT_FID"),
                        rs.getInt("PRODUCT_CID"),
                        rs.getString("PRODUCT_FILENAME")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return product;
    }

    /**
     * 根据父ID查找全部书
     *
     * @param fid
     * @return
     */
    public static List<PRODUCT> QueryByFId(int fid) {
        ResultSet rs = null;
        List<PRODUCT> list = new ArrayList<PRODUCT>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        USER user = null;

        try {
            sql = "select * from product where PRODUCT_FID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fid);

            rs = ps.executeQuery();
            while (rs.next()) {
                PRODUCT product = new PRODUCT(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PRODUCT_DESCRIPTION"),
                        rs.getInt("PRODUCT_PRICE"),
                        rs.getInt("PRODUCT_STOCK"),
                        rs.getInt("PRODUCT_FID"),
                        rs.getInt("PRODUCT_CID"),
                        rs.getString("PRODUCT_FILENAME")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 根据父ID和子ID查询所有书
     *
     * @param fid
     * @param cid
     * @return
     */
    public static List<PRODUCT> QueryByF_C_Id(int fid, int cid) {
        ResultSet rs = null;
        List<PRODUCT> list = new ArrayList<PRODUCT>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        USER user = null;

        try {
            sql = "select * from product where PRODUCT_FID=? and PRODUCT_CID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fid);
            ps.setInt(2, cid);

            rs = ps.executeQuery();
            while (rs.next()) {
                PRODUCT product = new PRODUCT(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PRODUCT_DESCRIPTION"),
                        rs.getInt("PRODUCT_PRICE"),
                        rs.getInt("PRODUCT_STOCK"),
                        rs.getInt("PRODUCT_FID"),
                        rs.getInt("PRODUCT_CID"),
                        rs.getString("PRODUCT_FILENAME")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }


    /**
     * 根据一个书本id数组查找所有书籍
     * @param list
     * @return
     */
    public static List<PRODUCT> QueryByIds(List<Integer> list) {
        ResultSet rs = null;
        List<PRODUCT> products = new ArrayList<PRODUCT>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        PRODUCT product = null;
        String sql = "";
        USER user = null;

        try {
            for (int i = 0; i < list.size(); i++) {
                sql = "select * from product where PRODUCT_ID=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, list.get(i));

                rs = ps.executeQuery();
                while (rs.next()) {
                    product = new PRODUCT(
                            rs.getInt("PRODUCT_ID"),
                            rs.getString("PRODUCT_NAME"),
                            rs.getString("PRODUCT_DESCRIPTION"),
                            rs.getInt("PRODUCT_PRICE"),
                            rs.getInt("PRODUCT_STOCK"),
                            rs.getInt("PRODUCT_FID"),
                            rs.getInt("PRODUCT_CID"),
                            rs.getString("PRODUCT_FILENAME")
                    );
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return products;
    }

    /**
     * 查询所有书籍
     *
     * @return
     */
    public static List<PRODUCT> QueryAll() {
        List<PRODUCT> list = new ArrayList<PRODUCT>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from product";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                PRODUCT p = new PRODUCT(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PRODUCT_DESCRIPTION"),
                        rs.getInt("PRODUCT_PRICE"),
                        rs.getInt("PRODUCT_STOCK"),
                        rs.getInt("PRODUCT_FID"),
                        rs.getInt("PRODUCT_CID"),
                        rs.getString("PRODUCT_FILENAME")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }
}
