package com.cj.servlet.product;

import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doproductdelete.do")
public class DoProductDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        int product_id = 0;
        if (id != null) {
            product_id = Integer.parseInt(id);
        }
        int count= PRODUCTDao.DeleteById(product_id);
        if(count>0) {
            response.sendRedirect("doproductselect.do");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('图书删除失败');");
            out.print("location.href='doproductselect.do';");
            out.print("</script>");
            out.close();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }


}
