package com.cj.servlet.cate;

import com.cj.entity.CATEGORY;
import com.cj.entity.USER;
import com.cj.service.CATEGORYDao;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/docateadd.do")
public class DoCateAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String className = request.getParameter("className");

        CATEGORY category = new CATEGORY(0, className, parentId);   //CATE_ID会自动增长
        int count = CATEGORYDao.Insert(category);

        if (count > 0) {
            response.sendRedirect("docateselect.do");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('分类增加失败');");
            out.print("location.href='docateselect.do';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
