package com.cj.servlet.user;

import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/AdminUserAddCheck")
public class AdminUserAddCheck extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String REGEX_USERNAME = "^[a-zA-Z]\\w{5,11}";     //用户名必须是6-12位, 且必须是字母开头

        String REGEX_MOBILE = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
        String mobile = request.getParameter("mobile");

        String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";     //只能包含字母数字, 长度在6-16位之间
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        if (username != null) {
            int count = USERDao.getCountById(username);
            if (count > 0) {
                out.print("already exist");
            } else {
                if (Pattern.matches(REGEX_USERNAME, username)) {
                    out.print("username-true");
                } else {
                    out.print("username-false");
                }
            }
        }

        if (password != null) {
            if (Pattern.matches(REGEX_PASSWORD, password)) {
                out.print("password-true");
            } else {
                out.print("password-false");
            }
        }

        if (mobile != null) {
            if (Pattern.matches(REGEX_MOBILE, mobile)) {
                out.print("mobile-true");
            } else {
                out.print("mobile-false");
            }
        }

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
