package com.cj.servlet.order;

import com.cj.entity.*;
import com.cj.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/orderdetailselect")
public class OrderDetailSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String addressid = request.getParameter("addressid");
        int aid = Integer.parseInt(addressid);
        String payid = request.getParameter("payid");
        int pid = Integer.parseInt(payid);
        String orderid = request.getParameter("orderid");
        int oid = Integer.parseInt(orderid);

        ADDRESS address = ADDRESSDao.QueryById(aid);
        PAY pay = PAYDao.QueryById(pid);
        ORDERS order = ORDERSDao.QueryById(oid);
//        PRODUCT product= PRODUCTDao.QueryById()

        List<ITEM> items = ITEMDao.QueryByOId(oid);
        Map<ITEM, PRODUCT> I_Pmap = new LinkedHashMap<ITEM, PRODUCT>();
        for (ITEM e : items) {
            PRODUCT p = PRODUCTDao.QueryById(e.getITEM_P_ID());
            I_Pmap.put(e, p);
        }

        request.setAttribute("address", address);
        request.setAttribute("pay", pay);
        request.setAttribute("order", order);
//        request.setAttribute("items", items);
        request.setAttribute("I_Pmap", I_Pmap);
        request.getRequestDispatcher("myorderdetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
