package com.cj.servlet.order;

import com.cj.entity.*;
import com.cj.service.*;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/orderadd")
public class OrderAdd extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");

        //存数据到request域, 推荐猜你喜欢
        String pids = request.getParameter("pids");
        request.setAttribute("pids", pids);

        String pid[] = pids.split(",");
        String aid = request.getParameter("aid");
        String totalPrice1 = request.getParameter("totalPrice");
        //去掉￥
        String total = totalPrice1.substring(1);
        String send_way = request.getParameter("sendway");
        String pay_way = request.getParameter("payway");
        String pay_status = request.getParameter("pay_status");

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentTime = format.format(date);

        //判断是否付过钱
        PAY pay = null;
        ORDERS orders = null;
        int payid = 1;
        int ordid = 1;
        if ("true".equals(pay_status)) {
            String endTime = currentTime.substring(0, 8) + String.valueOf(Integer.parseInt(currentTime.substring(currentTime.length() - 2)) + 3);
            pay = new PAY(0, currentTime, endTime, "3天", pay_way, send_way);
            payid = PAYDao.Insert(pay);
            orders = new ORDERS(0, user.getUSER_ID(), currentTime, Integer.parseInt(total), payid, Integer.parseInt(aid), "尽快配送", "2");
        } else {
            pay = new PAY(0, "0", "0", "0", pay_way, send_way);
            payid = PAYDao.Insert(pay);
            orders = new ORDERS(0, user.getUSER_ID(), currentTime, Integer.parseInt(total), payid, Integer.parseInt(aid), "", "1");
        }


        ordid = ORDERSDao.Insert(orders);
        pay = PAYDao.QueryById(payid);
        orders = ORDERSDao.QueryById(ordid);

        ADDRESS address = ADDRESSDao.QueryById(orders.getORDER_ADDRESS_ID());

        //装入ORDERS和PAY的映射
        Map<ORDERS, PAY> O_Pmap = new LinkedHashMap<ORDERS, PAY>();
        O_Pmap.put(orders, pay);


        //替代方案不可以, key不能重复
//        Map<Integer, ITEM> Index_Imap = new LinkedHashMap<Integer, ITEM>();
//        Map<Integer, PRODUCT> Index_Pmap = new LinkedHashMap<Integer, PRODUCT>();
        Map<ITEM, Integer> I_Indexmap = new LinkedHashMap<ITEM, Integer>();
        Map<PRODUCT, Integer> P_Indexmap = new LinkedHashMap<PRODUCT, Integer>();

        //装入ORDER_ID和ADDRESS的映射
        Map<Integer, ADDRESS> Index_Amap = new LinkedHashMap<Integer, ADDRESS>();
        Index_Amap.put(ordid, address);

        for (int i = 0; i < pid.length; i++) {
            CART cart = CARTDao.QueryByU_P_Id(user.getUSER_ID(), Integer.parseInt(pid[i]));

            PRODUCT p = PRODUCTDao.QueryById(cart.getCART_P_ID());

            int nowStock = p.getPRODUCT_STOCK();
            int newStock = nowStock - cart.getCART_QUANTITY();
            if (newStock >= 0) {
                PRODUCTDao.UpdateStock(p.getPRODUCT_ID(), newStock);
                CARTDao.UpdateStock(cart.getCART_ID(), newStock);
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('" + p.getPRODUCT_NAME() + " 书本库存不足');");
                out.print("location.href='showcart';");
                out.print("</script>");
                out.close();
            }

            //只有支付了订单上的金额才把购物车清空
            if ("2".equals(orders.getORDER_STATUS())) {
                CARTDao.UpdateValid(cart.getCART_ID(), 2);  //购物车表中的CART_VALID=1表示未付款, 2表示已付款
            }

            int productNum = cart.getCART_QUANTITY();
            ITEM item = new ITEM(0, ordid, cart.getCART_P_ID(), productNum);
            ITEMDao.Insert(item);
            item = ITEMDao.QueryByPId(p.getPRODUCT_ID());

//            I_Indexmap.put(item, ordid);
//            P_Indexmap.put(p, ordid);

        }

//        session.setAttribute("O_Pmap", O_Pmap);
//
//        session.setAttribute("I_Indexmap", I_Indexmap);
//        session.setAttribute("P_Indexmap", P_Indexmap);
//
//        session.setAttribute("Index_Amap", Index_Amap);
        request.getRequestDispatcher("okshowproduct").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
