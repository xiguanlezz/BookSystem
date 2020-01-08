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

@WebServlet("/userchangepwd")
public class UserChangePwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String newPwd = request.getParameter("renewPwd");
        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        int count = USERDao.UpdatePwdById(user.getUSER_ID(), newPwd);
        PrintWriter out = response.getWriter();
        if (count > 0) {
            out.print("<script>");
            out.print("alert('修改成功, 请重新登陆');");
            session.removeAttribute("isLogin");
            session.removeAttribute("user");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            out.close();
        } else {
            out.print("<script>");
            out.print("alert('修改失败');");
            out.print("location.href='changebypwd.jsp';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
