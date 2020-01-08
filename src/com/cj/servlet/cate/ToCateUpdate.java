package com.cj.servlet.cate;

import com.cj.entity.CATEGORY;
import com.cj.service.CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tocateupdate.do")
public class ToCateUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));

        CATEGORY cate = CATEGORYDao.QueryById(id);

        List<CATEGORY> list = CATEGORYDao.QueryAll();
        request.setAttribute("catelist", list);
        request.setAttribute("category", cate);
        request.getRequestDispatcher("manage/admin_catemodify.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
