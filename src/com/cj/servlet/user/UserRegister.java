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

@WebServlet("/register")
public class UserRegister extends HttpServlet {
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
        USER user = new USER(username, name, password, sex, birthday, null, email, mobile, address, 1);
        //System.out.println(user);

        int count = USERDao.Insert(user);
        request.setAttribute("userName",username);
        request.setAttribute("Pwd",password);
        if (count > 0) {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('用户注册失败');");
            out.print("location.href='register.jsp';");
            out.print("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
