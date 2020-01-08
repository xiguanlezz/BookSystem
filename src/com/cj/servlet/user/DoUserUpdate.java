package com.cj.servlet.user;

import com.cj.entity.USER;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/douserupdate.do")
public class DoUserUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String cp = request.getParameter("currentPage");
        String status = request.getParameter("userStatus");
        String ps = request.getParameter("pageSize");

        int userStatus = 1; //默认是1, 1表示普通用户可以修改, 2是管理员不可更改
        if (status != null) {
            userStatus = Integer.parseInt(status);
        }

        int currentPage = 1;
        if (cp != null) {
            currentPage = Integer.parseInt(cp);
        }

        int pageSize = 5;
        if (ps != null) {
            pageSize = Integer.parseInt(ps);
        }

        USER user = new USER(username, name, password, sex, birthday, null, email, mobile, address, userStatus);
        int count = USERDao.Update(username, user);
        if (count > 0) {
            response.sendRedirect("douserselect.do?currentPage=" + currentPage + "&pageSize=" + pageSize);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('用户修改失败');");
            out.print("location.href='touserupdate.do?id=" + username + "&currentPage=" + cp + "'");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
