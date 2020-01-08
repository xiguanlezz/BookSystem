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

@WebServlet("/usercheck")
public class UserCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String isLogin = (String) session.getAttribute("isLogin");
        USER user = (USER) session.getAttribute("user");
        PrintWriter out = response.getWriter();

        String oldPwd = request.getParameter("oldPwd");
        String email = request.getParameter("email");
        //String newPwd = request.getParameter("newPwd");
        if (user != null && "1".equals(isLogin)) {
            if (oldPwd != null) {
                if (user.getUSER_PASSWORD().equals(oldPwd)) {
                    out.print("oldPwdTrue");
                } else {
                    out.print("oldPwdFalse");
                }
                out.close();
            } else {
                if (email != null && user.getUSER_EMAIL().equals(email)) {
                    out.print("emailTrue");
                } else {
                    out.print("emailFalse");
                }
            }
            return;

        } else {
            out.print("<script>");
            out.print("alert('请先登录');");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            out.close();
        }


        String id = request.getParameter("id");
        int count = USERDao.getCountById(id);

        if (count > 0) {    //数据库中已存在则不能使用
            out.print("false");
        } else {        //数据库中不存在可以使用
            out.print("true");
        }
        out.close();
    }
}
