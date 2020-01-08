package com.cj.service;

import com.cj.dao.BaseDao;
import com.cj.entity.EVALUATION;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EVALUATIONDao {

    /**
     * 插入一条评价
     *
     * @param evaluation
     * @return
     */
    public static int Insert(EVALUATION evaluation) {
        String sql = "insert into evaluation values(null, ?, ?, ?, ?)";
        Object[] params = {
                evaluation.getEVALUATION_U_ID(),
                evaluation.getEVALUATION_O_ID(),
                evaluation.getEVALUATION_SCORE(),
                evaluation.getEVALUATION_WORDS()
        };
        int count = BaseDao.executeIDU(sql, params);
        return count;
    }


    /**
     * 根据订单id查询评论
     *
     * @param oid
     * @return
     */
    public static EVALUATION QueryOId(int oid) {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        EVALUATION e = null;

        try {
            sql = "select * from evaluation where EVALUATION_O_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);

            rs = ps.executeQuery();
            while (rs.next()) {
                e = new EVALUATION(
                        rs.getInt("EVALUATION_ID"),
                        rs.getString("EVALUATION_U_ID"),
                        rs.getInt("EVALUATION_O_ID"),
                        rs.getInt("EVALUATION_SCORE"),
                        rs.getString("EVALUATION_WORDS")
                );
            }
        } catch (SQLException es) {
            es.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return e;
    }


    /**
     * 查询全部评价, 并根据用户ID从小到大排序
     *
     * @return
     */
    public static List<EVALUATION> QueryAll() {
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        String sql = "";
        List<EVALUATION> list = new ArrayList<EVALUATION>();

        try {
            sql = "select * from evaluation order by EVALUATION_U_ID asc";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                EVALUATION e = new EVALUATION(
                        rs.getInt("EVALUATION_ID"),
                        rs.getString("EVALUATION_U_ID"),
                        rs.getInt("EVALUATION_O_ID"),
                        rs.getInt("EVALUATION_SCORE"),
                        rs.getString("EVALUATION_WORDS")
                );
                list.add(e);
            }
        } catch (SQLException es) {
            es.printStackTrace();
        } finally {
            BaseDao.closeAll(rs, ps, conn);
        }
        return list;
    }
}
