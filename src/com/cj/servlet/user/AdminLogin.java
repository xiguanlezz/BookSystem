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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin_login")
public class AdminLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        List<String> lastPwd = (List<String>) session.getAttribute("lastPwd");
        if (lastPwd == null) {
            lastPwd = new ArrayList<String>();
        }

        int count = USERDao.getCountByIdPwd(username, password);
        if (count > 0) {
            USER user = USERDao.QueryByIdPwd(username, password);

            session.setAttribute("user", user);
            session.setAttribute("isLogin", "1");   //设置用户登录标记
            if (user.getUSER_STATUS() == 2) {
                session.setAttribute("isAdminLogin", "1");  //管理员登陆标记

                if (lastPwd.size() >= 2) {  //设置最近保存的密码队列, 长度为2
                    lastPwd.remove(0);
                    //System.out.println("1111111");
                }

                lastPwd.add(password);

//                for (String s : lastPwd) {
//                    System.out.println(s);  //打印密码队列
//                }

                session.setAttribute("lastPwd", lastPwd);
                //System.out.println(lastPwd);

                response.sendRedirect("manage/admin_index.jsp");  //管理员登陆成功跳转到后台首页
            } else {
                response.sendRedirect("index.jsp");     //普通用户登陆成功跳转到网站首页
            }

        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('管理员登录失败');");
            out.print("location.href='manage/login.jsp';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
