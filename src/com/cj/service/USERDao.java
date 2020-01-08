package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class USERDao {

    /**
     * 插入单个用户
     *
     * @param user
     * @return
     */
    public static int Insert(USER user) {
        String sql = "insert into user values(?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d'), ?, ?, ?, ?, ?)";
        Object[] params = {
                user.getUSER_ID(), user.getUSER_NAME(), user.getUSER_PASSWORD(),
                user.getUSER_SEX(), user.getUSER_BIRTHDAY(), user.getUSER_IDENTITY_CODE(),
                user.getUSER_EMAIL(), user.getUSER_MOBILE(), user.getUSER_ADDRESS(), user.getUSER_STATUS()
        };
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    public static int Delete(String id) {
        String sql = "delete from user where USER_ID=? and USER_STATUS!=2";   //USER_STATUS=2表示管理员
        Object[] params = {id};
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 根据id更改密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    public static int UpdatePwdById(String id, String newPwd) {
        String sql = "update user set USER_PASSWORD=? where USER_ID = ?";
        Object[] params = {
                newPwd, id
        };
        //return 0;
        return BaseDao.executeIDU(sql, params);
    }

    /**
     * 修改用户基本信息
     *
     * @param id
     * @param name
     * @param birthday
     * @param sex
     * @param email
     * @return
     */
    public static int UpdateBasic(String id, String name, String birthday, String sex, String email) {
        String sql = "update user set USER_NAME=?, USER_BIRTHDAY=?, USER_SEX=?, USER_EMAIL=? where USER_ID = ?";
        Object[] params = {
                name, birthday, sex, email, id
        };
        //return 0;
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 根据用户id修改信息
     *
     * @param id
     * @param user
     * @return
     */
    public static int Update(String id, USER user) {
        String sql = "update user set USER_NAME=?, USER_PASSWORD=?,USER_SEX=?,USER_BIRTHDAY=DATE_FORMAT(?, '%y-%m-%d'),USER_IDENTITY_CODE=?,USER_EMAIL=?,USER_MOBILE=?,USER_ADDRESS=?,USER_STATUS=? where USER_ID = ?";
        Object[] params = {
                user.getUSER_NAME(), user.getUSER_PASSWORD(), user.getUSER_SEX(),
                user.getUSER_BIRTHDAY(), user.getUSER_IDENTITY_CODE(), user.getUSER_EMAIL(),
                user.getUSER_MOBILE(), user.getUSER_ADDRESS(), user.getUSER_STATUS(), id
        };
        //return 0;
        return BaseDao.executeIDU(sql, params);
    }


    /**
     * 根据id查找单个用户
     *
     * @param id
     * @return
     */
    public static USER QueryById(String id) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        USER user = null;

        try {
            sql = "select m.*, DATE_FORMAT(m.USER_BIRTHDAY,'%Y-%m-%d')birthday from user m where USER_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("birthday"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return user;
    }

    /**
     * 根据id和password查询单个用户
     *
     * @param id
     * @param password
     * @return
     */
    public static USER QueryByIdPwd(String id, String password) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        USER user = null;

        try {
            sql = "select m.*, DATE_FORMAT(m.USER_BIRTHDAY,'%Y-%m-%d')birthday from user m where USER_ID=? and USER_PASSWORD=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {
                user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("birthday"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return user;
    }

    /**
     * 根据id查询, 返回匹配用户的数量
     *
     * @param id
     * @return
     */
    public static int getCountById(String id) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        int count = 0;

        try {
            sql = "select count(*) from user where USER_ID=?";   //从第几页查几页
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
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
     * 根据id和password查询, 返回匹配用户的数量
     *
     * @param id
     * @param password
     * @return
     */
    public static int getCountByIdPwd(String id, String password) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        int count = 0;

        try {
            sql = "select count(*) from user where USER_ID=? and USER_PASSWORD=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {
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
     * 查询所有用户
     *
     * @return
     */
    public static List<USER> QueryAll() {
        List<USER> list = new ArrayList<USER>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from user order by USER_BIRTHDAY desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                USER user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("USER_BIRTHDAY"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param currentPage 现在的页码
     * @param pageSize    每页最大的显示数
     * @return
     */
    public static List<USER> QueryByPage(int currentPage, int pageSize) {
        List<USER> list = new ArrayList<USER>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            sql = "select * from user order by USER_NAME desc limit ?, ?";  //分页查询的第一个参数是起始索引, 第二个参数是查询个数
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                USER user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("USER_BIRTHDAY"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 分页查询+模糊查询
     *
     * @param currentPage 现在的页码
     * @param pageSize    每页最大的显示数
     * @param keyWord     关键词
     * @return
     */
    public static List<USER> QueryByPage_keyWord(int currentPage, int pageSize, String keyWord) {
        List<USER> list = new ArrayList<USER>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {

            //会被sql注入, 有隐患
            //sql = "select * from user where USER_NAME like '%" + keyword + "%' order by USER_BIRTHDAY desc limit ?, ?";
            //用预处理语句预防sql注入
            sql = "select * from user where USER_NAME like ? order by USER_NAME desc limit ?, ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyWord + "%");
            ps.setInt(2, (currentPage - 1) * pageSize);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                USER user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("USER_BIRTHDAY"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 根据关键词  分页查询所有用户
     *
     * @param currentPage 当前页数
     * @param pageSize    每页的记录数
     * @return
     */
    public static List<USER> selectAll(int currentPage, int pageSize, String keyword) {
        List<USER> list = new ArrayList<USER>();
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";

        try {
            if (keyword != null) {
                //会被sql注入, 有隐患
                //sql = "select * from user where USER_NAME like '%" + keyword + "%' order by USER_BIRTHDAY desc limit ?, ?";

                //用预处理语句预防sql注入
                sql = "select * from user where USER_NAME like ? order by USER_NAME desc limit ?, ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, (currentPage - 1) * pageSize);
                ps.setInt(3, pageSize);

            } else {
                sql = "select * from user order by USER_NAME desc limit ?, ?";   //从第几页查几页
                ps = conn.prepareStatement(sql);
                ps.setInt(1, (currentPage - 1) * pageSize);
                ps.setInt(2, pageSize);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                USER user = new USER(
                        rs.getString("USER_ID"), rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWORD"), rs.getString("USER_SEX"),
                        rs.getString("USER_BIRTHDAY"), rs.getString("USER_IDENTITY_CODE"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"), rs.getInt("USER_STATUS")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }

    /**
     * 获取user表中所有的记录数
     *
     * @return
     */
    public static int getTotalCount() {
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int countNum = 0;
        String sql = "";
        try {
            sql = "select count(*) from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                countNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return countNum;
    }

    /**
     * 根据每页的最大记录数, 得到分页后的总页数
     *
     * @param pageSize
     * @return
     */
    public static int getTotalPage(int pageSize) {
        if (getTotalCount() % pageSize == 0)
            return getTotalCount() / pageSize;
        else
            return getTotalCount() / pageSize + 1;
    }

    /**
     * 根据关键词查询表中的所有记录值
     *
     * @param keyWord
     * @return
     */
    public static int getTotalCountBykeyWord(String keyWord) {
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int countNum = 0;
        String sql = "";
        try {
            sql = "select count(*) from user where USER_NAME like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyWord + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                countNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return countNum;
    }

    /**
     * 根据关键词计算分页后的总页数
     *
     * @param keyWord
     * @param pageSize
     * @return
     */
    public static int getTotalPageBykeyWord(String keyWord, int pageSize) {
        return getTotalCountBykeyWord(keyWord) % pageSize != 0 ? (getTotalCountBykeyWord(keyWord) / pageSize + 1) : (getTotalCountBykeyWord(keyWord) / pageSize);
    }

}
