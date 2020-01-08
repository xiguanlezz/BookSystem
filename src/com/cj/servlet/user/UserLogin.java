package com.cj.servlet.user;

import com.cj.entity.USER;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        //System.out.println(username + "-" + password);

        int count = USERDao.getCountByIdPwd(username, password);
        if (count > 0) {
            HttpSession session = request.getSession();
            USER user = USERDao.QueryByIdPwd(username, password);
            session.setAttribute("user", user);
            session.setAttribute("isLogin", "1");    //用标记位标志是否登录
            response.sendRedirect("indexselect");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('用户名或者账号输入有误, 请重新输入');");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
