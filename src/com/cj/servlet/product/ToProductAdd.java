package com.cj.servlet.product;

import com.cj.entity.CATEGORY;
import com.cj.service.CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toproductadd.do")
public class ToProductAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<CATEGORY> flist = CATEGORYDao.QueryTwoCate("father");
        request.setAttribute("flist", flist);
        List<CATEGORY> clist = CATEGORYDao.QueryTwoCate("child");
        request.setAttribute("clist", clist);
        request.getRequestDispatcher("manage/admin_productadd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
