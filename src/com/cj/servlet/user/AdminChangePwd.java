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
import java.util.List;

@WebServlet("/adminchangepwd.do")
public class AdminChangePwd extends HttpServlet {

    //Ajax
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String newPwd = request.getParameter("newPwd");

        List<String> lastPWd = (List<String>) session.getAttribute("lastPwd");
        //System.out.println(lastPWd);
        for (String pwd : lastPWd) {
            System.out.println(pwd);
            if (pwd.equals(newPwd)) {
                PrintWriter out = response.getWriter();
                out.print("false");
                out.close();
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String id = user.getUSER_ID();

        String newPwd = request.getParameter("newPwd");
        int count = USERDao.UpdatePwdById(id, newPwd);
        if (count > 0) {
            session.removeAttribute("user");
            session.removeAttribute("isLogin");
            session.removeAttribute("isAdminLogin");
            //session.setAttribute("lastPwd",newPwd);
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('密码修改成功, 请重新登陆');");
            out.print("location.href='manage/login.jsp';");
            out.print("</script>");
            out.close();
            //response.sendRedirect("manage/admin_index.jsp");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('密码修改失败');");
            out.print("location.href='adminchangepwd.do';");
            out.print("</script>");
            out.close();
        }

    }
}
