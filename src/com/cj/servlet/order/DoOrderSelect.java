package com.cj.servlet.order;

import com.cj.entity.ADDRESS;
import com.cj.entity.ORDERS;
import com.cj.entity.USER;
import com.cj.service.ADDRESSDao;
import com.cj.service.ORDERSDao;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/doorderselect.do")
public class DoOrderSelect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        String
        List<ORDERS> orderslist = ORDERSDao.QueryAll();
        Map<Integer, String> Index_Nmap = new LinkedHashMap<Integer, String>();     //ORDER_ID->USER
        Map<Integer, String> Index_Amap = new LinkedHashMap<Integer, String>();     //ORDER_ID->ADDRESS
        ORDERS temp = null;
        for (ORDERS o : orderslist) {
            temp = ORDERSDao.QueryById(o.getORDER_ID());
            int key = temp.getORDER_ID();
            USER user = USERDao.QueryById(temp.getORDER_U_ID());
            String nvalue = user.getUSER_NAME();
            Index_Nmap.put(key, nvalue);

            ADDRESS address = ADDRESSDao.QueryById(temp.getORDER_ADDRESS_ID());
            String avalue = address.getADDRESS_DETAILED_LOCATION();
            Index_Amap.put(key, avalue);
        }

        //ORDER_ID->USER_NAME

        //ORDER_ID->ADDRESS_DETAILED_LOCATION

        request.setAttribute("Index_Nmap", Index_Nmap);
        request.setAttribute("Index_Amap", Index_Amap);
        request.setAttribute("orderslist", orderslist);
        request.getRequestDispatcher("manage/admin_order.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
