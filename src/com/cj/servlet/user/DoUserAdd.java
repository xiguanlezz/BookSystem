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

@WebServlet("/douseradd.do")
public class DoUserAdd extends HttpServlet {
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
        //String REGEX_USERNAME=""
        USER user = new USER(username, name, password, sex, birthday, null, email, mobile, address, 1);
        //System.out.println(user);

        int count = USERDao.Insert(user);

        if (count > 0) {
            response.sendRedirect("douserselect.do");

            //请求转发导致css文件实现问题 --> 已解决, 更改引入css的方式: 从相对路径改为绝对路径
            //request.getRequestDispatcher("douserselect").forward(request,response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('用户增加失败');");
            out.print("location.href='manage/admin_useradd.jsp';");

            //可以在request域中存些信息, 添加失败不需要重新录入

            out.print("</script>");
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
