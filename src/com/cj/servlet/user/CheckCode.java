package com.cj.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkcode")
public class CheckCode extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String num = request.getParameter("num");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String validCode = request.getParameter("validCode");
        System.out.println(num + "---" + code);
        PrintWriter out = response.getWriter();

        if (validCode != null) {
            String vcode = (String) session.getAttribute("validcode");
            if (vcode.equals(validCode)) {
                out.print("true");
            } else {
                out.print("false");
            }
            return;
        }

        if (code.equalsIgnoreCase(num)) {
            out.print("true");
        } else {
            out.print("false");
        }

        out.close();
    }
}
