package com.cj.servlet.order;

import com.cj.entity.*;
import com.cj.service.CARTDao;
import com.cj.service.ITEMDao;
import com.cj.service.ORDERSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static javax.swing.text.html.CSS.getAttribute;

@WebServlet("/orderupdate")
public class OrderUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        //ORDER_ID +
        String orderid = request.getParameter("orderid");
        int oid = Integer.parseInt(orderid);
        ORDERS orders = ORDERSDao.QueryById(oid);
        String flag = request.getParameter("flag");

        //重新把session中对应的订单取出来, 更新标记后再存回去
        Map<ORDERS, PAY> opmap = (Map<ORDERS, PAY>) session.getAttribute("O_Pmap");
        Map<ORDERS, PAY> newopmap = new LinkedHashMap<ORDERS, PAY>();
        Set<ORDERS> keys = opmap.keySet();
        ORDERS temp = null;

        if ("pay".equals(flag)) {   //表示点击了订单页面的立即支付
            //更新购物车标记
            List<ITEM> items = ITEMDao.QueryByOId(oid);
            List<Integer> item_pids = new ArrayList<Integer>();
            for (ITEM i : items) {
                CART cart = CARTDao.QueryByU_P_Id(user.getUSER_ID(), i.getITEM_P_ID());
                CARTDao.UpdateValid(cart.getCART_ID(), 2);
            }
            //更新订单标记
            for (ORDERS o : keys) {
                temp = o;
                if (o.getORDER_ID() == oid) {
                    ORDERSDao.UpdateStatus(orders.getORDER_ID(), "2");
                    temp = ORDERSDao.QueryById(oid);
                }
                newopmap.put(temp, opmap.get(o));
            }
        } else {    //表示点击了订单页面的确认收货
            for (ORDERS o : keys) {
                temp = o;
                if (o.getORDER_ID() == oid) {
                    ORDERSDao.UpdateStatus(orders.getORDER_ID(), "3-1");
                    temp = ORDERSDao.QueryById(oid);
                }
                newopmap.put(temp, opmap.get(o));
            }
        }

        //重新存入订单的映射
        session.setAttribute("O_Pmap", newopmap);
        response.sendRedirect("myorder.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
