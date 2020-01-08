package com.cj.servlet.user;

import com.cj.entity.USER;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/touserupdate.do")
public class ToUserUpdate extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        String cp = request.getParameter("currentPage");
        String ps = request.getParameter("pageSize");
        USER user = USERDao.QueryById(id);
        request.setAttribute("user", user);

        if (cp != null) {
            request.setAttribute("currentPage", cp);
        }
        if (ps != null) {
            request.setAttribute("pageSize", ps);
            System.out.println(ps);
        }
        //System.out.println(cp + "----" + ps);
        request.getRequestDispatcher("manage/admin_usermodify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
