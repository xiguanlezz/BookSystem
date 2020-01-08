package com.cj.servlet.address;

import com.cj.service.ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addressdelete")
public class AddressDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //地址管理中删除地址
        String aid1 = request.getParameter("aid");
        //System.out.println(aid1);

        //订单确认时候删除地址
        String aid2 = request.getParameter("id");
        //System.out.println(aid2);
        int id = 1;
        if (aid1 != null) {
            id = Integer.parseInt(aid1);
            int count = ADDRESSDao.DeleteById(id);
            PrintWriter out = response.getWriter();
            if (count > 0) {
                out.print("<script>");
                out.print("alert('地址删除成功');");
                out.print("location.href='addressselect'");
                out.print("</script>");
            } else {
                out.print("<script>");
                out.print("alert('地址删除失败');");
                out.print("location.href='addressselect'");
                out.print("</script>");
            }
            out.close();
            //System.out.println(111);
            return;
        }

        if (aid2 != null) {
            id = Integer.parseInt(aid2);
            int count = ADDRESSDao.DeleteById(id);
            String eids = request.getParameter("eids");
            PrintWriter out = response.getWriter();
            if (count > 0) {
                out.print("<script>");
                out.print("alert('地址删除成功');");
                out.print("location.href='orderselect?eids=" + eids + "'");
                out.print("</script>");
            } else {
                out.print("<script>");
                out.print("alert('地址删除失败');");
                out.print("location.href='orderselect?eids=" + eids + "'");
                out.print("</script>");
            }
            out.close();
            //System.out.println(222);
            return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
