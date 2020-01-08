package com.cj.servlet.address;

import com.cj.entity.ADDRESS;
import com.cj.entity.USER;
import com.cj.service.ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addressadd")
public class AddressAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String uid = user.getUSER_ID();
        String uname = request.getParameter("name");
        String umobile = request.getParameter("mobile");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String address = request.getParameter("detailedAddress");
        String zipcode = request.getParameter("zipCode");
        ADDRESS a = new ADDRESS(0, uid, uname, umobile, province, city, address, zipcode);

        String eids = request.getParameter("eids");
        if (eids != null) {        //说明是在订单页面添加
            int count = ADDRESSDao.Insert(a);
            if (count > 0) {
                System.out.println("地址添加成功");
                session.setAttribute("ids",eids);
                response.sendRedirect("orderselect");
                //request.getRequestDispatcher("").forward(request, response);

            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('地址添加失败');");
                out.print("location.href='orderselect?eids=" + eids + "';");
                out.print("</script>");
                out.close();
            }
        } else {        //说明是地址管理页面添加
            int count = ADDRESSDao.Insert(a);
            if (count > 0) {
                System.out.println("地址添加成功");
                response.sendRedirect("addressselect");
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('地址添加失败');");
                out.print("location.href='address.jsp';");
                out.print("</script>");
                out.close();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
