package com.cj.servlet.product;

import com.cj.entity.CATEGORY;
import com.cj.entity.PRODUCT;
import com.cj.service.CATEGORYDao;
import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showproductcsort.do")
public class ShowProductCSort extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String temp = request.getParameter("id");
        //System.out.println("1111");
        int id = 0;
        if (temp != null) {
            id = Integer.parseInt(temp);
        }


        CATEGORY cate = null;
        List<CATEGORY> clist = CATEGORYDao.QueryTwoCate("child");
        PrintWriter out = response.getWriter();
        for (CATEGORY c : clist) {
            if (c.getCATE_PARENT_ID() == id) {
                cate = CATEGORYDao.QueryById(c.getCATE_ID());
                out.print("[" + cate.getCATE_ID() + "," + cate.getCATE_NAME() + "] ");
            }
        }
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
