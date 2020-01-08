package com.cj.servlet.cart;

import com.cj.entity.CART;
import com.cj.entity.PRODUCT;
import com.cj.entity.USER;
import com.cj.service.CARTDao;
import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PRODUCT p = null;
        String pid = request.getParameter("id");
        int count = Integer.parseInt(request.getParameter("count"));
        String url = request.getParameter("url");
        HttpSession session = request.getSession();

        String isLogin = (String) session.getAttribute("isLogin");
        USER user = (USER) session.getAttribute("user");

        int flag = 0;

        if (user != null && "1".equals(isLogin)) {
            String uid = user.getUSER_ID();
            if (pid != null) {
                p = PRODUCTDao.QueryById(Integer.parseInt(pid));
            }

            CART cart = CARTDao.QueryByU_P_Id(uid, Integer.parseInt(pid));

            //解决同一种书本重复添加会新增对象的问题
            if (cart != null) {
                int now_num = cart.getCART_QUANTITY() + count;
                if (now_num > 5) {
                    now_num = 5;
                }
                flag = CARTDao.UpdateNum(cart.getCART_ID(), now_num);
                if (flag > 0) {
                    System.out.println("购物车内书本数量更新成功");
                }
            } else {
                cart = new CART(
                        0,      //CART_ID值自动增加
                        p.getPRODUCT_FILENAME(),
                        p.getPRODUCT_NAME(),
                        p.getPRODUCT_PRICE(),
                        count,
                        p.getPRODUCT_STOCK(),
                        p.getPRODUCT_ID(),
                        user.getUSER_ID(),
                        1
                );
                flag = CARTDao.Insert(cart);
                if (flag > 0) {
                    System.out.println("购物车内书本数量更新成功");
                }
            }
            if (flag > 0) {
                if ("buyNow".equals(url)) {
                    response.sendRedirect("showcart?id=" + pid);
                }
                if ("buySoon".equals(url)) {
                    response.sendRedirect("selectproductview?id=" + pid);
                }
                System.out.println("添加购物车成功");
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('加入购物车失败');");
                out.print("location.href='selectproductview?id" + pid + "'");
                out.print("</script>");
                out.close();
                return;
            }

        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('登陆后, 再购买');");
            out.print("location.href='login.jsp'");
            out.print("</script>");
            out.close();
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
