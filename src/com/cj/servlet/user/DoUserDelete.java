package com.cj.servlet.user;

import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/douserdelete.do")
public class DoUserDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        String cp = request.getParameter("currentPage");
        String ps = request.getParameter("pageSize");

        int count = 0;
        count = USERDao.Delete(id);

        if (count > 0) {
            response.sendRedirect("douserselect.do?currentPage=" + cp + "&pageSize=" + ps);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('用户删除失败');");
            out.print("location.href='douserselect.do?currentPage=" + cp + "&pageSize=" + ps + "';");
            out.print("</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String cp = request.getParameter("currentPage");
        String ps = request.getParameter("pageSize");

        //批量删除用post提交表单
        String ids[] = request.getParameterValues("id[]");
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                USERDao.Delete(ids[i]);
            }
            response.sendRedirect("douserselect.do?currentPage=" + cp + "&pageSize=" + ps);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('尚未选择任何要删除的用户');");
            out.print("location.href='douserselect.do?currentPage=" + cp + "&pageSize=" + ps + "';");
            out.print("</script>");
        }

    }
}
