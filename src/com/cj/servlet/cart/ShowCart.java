package com.cj.servlet.cart;

import com.cj.entity.CART;
import com.cj.entity.USER;
import com.cj.service.CARTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showcart")
public class ShowCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String isLogin = (String) session.getAttribute("isLogin");
//        String pid = request.getParameter("id");
        String uid = user.getUSER_ID();
        //System.out.println(uid);

        List<CART> carts = new ArrayList<CART>();
        if (user != null && "1".equals(isLogin)) {
            carts = CARTDao.QueryByUId(uid);
            request.setAttribute("carts", carts);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('请先登录');");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
