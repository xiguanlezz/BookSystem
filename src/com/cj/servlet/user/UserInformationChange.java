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

@WebServlet("/userchange")
public class UserInformationChange extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER u = (USER) session.getAttribute("user");
        String id = u.getUSER_ID();
        String name = request.getParameter("name");
        String birthday = request.getParameter("bir");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        int count = USERDao.UpdateBasic(id, name, birthday, sex, email);
        PrintWriter out = response.getWriter();
        if (count > 0) {
            out.print("success");
        } else {
            out.print("failure");
        }
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
