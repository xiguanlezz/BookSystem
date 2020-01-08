package com.cj.servlet.cart;

import com.cj.service.CARTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cartshopdelete")
public class CartShopDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int cart_id = Integer.parseInt(request.getParameter("id"));
        int count = CARTDao.DeleteById(cart_id);
        if (count > 0) {
            response.sendRedirect("showcart");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('删除失败');");
            out.print("location.href='showcart';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String ids[] = request.getParameterValues("ck");
        int count = 0;
        if (ids != null) {
            for (String s : ids) {
                count = CARTDao.DeleteById(Integer.parseInt(s));
                //System.out.println(s);
            }
            if (count > 0) {
                response.sendRedirect("showcart");
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('物品删除失败');");
                out.print("location.href='showcart';");
                out.print("</script>");
                out.close();
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('尚未选择任何要删除的物品');");
            out.print("location.href='showcart';");
            out.print("</script>");
        }

        System.out.println(ids[0]);

    }

}
