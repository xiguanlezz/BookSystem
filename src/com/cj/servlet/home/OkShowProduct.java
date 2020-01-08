package com.cj.servlet.home;

import com.cj.entity.PRODUCT;
import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/okshowproduct")
public class OkShowProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //用HashMap保存 cid和fid的映射
        Map<Integer, Integer> mapid = new LinkedHashMap<Integer, Integer>();
        String pids = request.getParameter("pids");
        String p_ids[] = pids.split(",");
        Set<PRODUCT> ps = new LinkedHashSet<PRODUCT>();
        List<PRODUCT> boughtps = new ArrayList<PRODUCT>();
        for (String s : p_ids) {
            int pid = Integer.parseInt(s);
            PRODUCT p = PRODUCTDao.QueryById(pid);
            boughtps.add(p);
            int fid = p.getPRODUCT_FID();
            int cid = p.getPRODUCT_CID();
            mapid.put(cid, fid);
        }
        Set<Integer> key = mapid.keySet();
        for (Integer k : key) {
            List<PRODUCT> l = PRODUCTDao.QueryByF_C_Id(mapid.get(k), k);
            for (PRODUCT pp : l) {
                ps.add(pp);
            }
        }

        //重写PRODUCT的equals和hashcode函数
        //猜你喜欢 中只显示同类别的并且没有购买过的书
        for (PRODUCT p1 : boughtps) {
            if (ps.contains(p1)) {
                ps.remove(p1);
            }
        }
        for (PRODUCT p2 : ps) {
            System.out.println(p2);
        }
        request.setAttribute("ps", ps);
        request.getRequestDispatcher("ok.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
