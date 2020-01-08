package com.cj.servlet.cart;

import com.cj.entity.ADDRESS;
import com.cj.entity.CART;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;
import com.cj.service.ADDRESSDao;
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

@WebServlet("/orderselect")
public class OrderSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String eids = request.getParameter("eids");     //购物车id的拼接
       // System.out.println("eids="+eids);
        HttpSession session = request.getSession();
        String isLogin = (String) session.getAttribute("isLogin");
        USER user = (USER) session.getAttribute("user");

        int totalPrice = 0;

        if(eids==null) {
            eids= (String) session.getAttribute("ids");
            System.out.println("111");
        }

        if (user != null && "1".equals(isLogin)) {
            String ids[] = eids.split(",");
            List<CART> carts = new ArrayList<CART>();

            for (int i = 0; i < ids.length; i++) {
                CART es = CARTDao.QueryById(Integer.parseInt(ids[i]));
                carts.add(es);
                int price = es.getCART_QUANTITY() * es.getCART_P_PRICE();
                totalPrice += price;
            }
            request.setAttribute("shoplist", carts);
            request.setAttribute("totalPrice", totalPrice);

            List<ADDRESS> addresses = ADDRESSDao.QueryAll(user.getUSER_ID());
            List<ADDRESS> destination=new ArrayList<ADDRESS>();
            if (addresses.size() != 0) {

                for(int i=1;i<addresses.size();i++) {
                    destination.add(addresses.get(i));
                }

                request.setAttribute("addresses", destination);
                request.setAttribute("defaultAddress", addresses.get(0));
            }

            request.getRequestDispatcher("order.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('请先登录');");
            out.print("location.href='login.jsp';");
            out.print("</script>");
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
