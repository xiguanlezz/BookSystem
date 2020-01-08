package com.cj.servlet.order;

import com.cj.entity.*;
import com.cj.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/evaluationselect")
public class EvaluationSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String isLogin = (String) session.getAttribute("isLogin");

        if (user != null && "1".equals(isLogin)) {

            //查到用户的所有订单, 挑出ORDER_STATUS=3的订单
            List<ORDERS> orders = ORDERSDao.QueryByUId(user.getUSER_ID());
            Set<Integer> oids = new LinkedHashSet<Integer>();
            List<ORDERS> ordersList = new ArrayList<ORDERS>();
            for (ORDERS o : orders) {
                if ("3-1".equals(o.getORDER_STATUS()) || "3-2".equals(o.getORDER_STATUS())) {
                    oids.add(o.getORDER_ID());
                    ordersList.add(o);
                }
            }

//        Map<ITEM, PRODUCT> ipMap = new LinkedHashMap<ITEM, PRODUCT>();
            //为了不让key被覆盖掉, ORDER_ID作为value存入
            Map<ITEM, Integer> i_Indexmap = new LinkedHashMap<ITEM, Integer>();
            Map<PRODUCT, Integer> p_Indexmap = new LinkedHashMap<PRODUCT, Integer>();
            List<EVALUATION> evaluations = new ArrayList<EVALUATION>();
            List<ITEM> items = new ArrayList<ITEM>();
            PRODUCT p = null;
            for (Integer i : oids) {
                items = ITEMDao.QueryByOId(i);
                for (ITEM item : items) {
                    p = PRODUCTDao.QueryById(item.getITEM_P_ID());
                    i_Indexmap.put(item, i);
                    p_Indexmap.put(p, i);
                }

                EVALUATION e = EVALUATIONDao.QueryOId(i);
                evaluations.add(e);
            }
            request.setAttribute("i_Indexmap", i_Indexmap);
            request.setAttribute("p_Indexmap", p_Indexmap);
            request.setAttribute("orderslist", ordersList);

            //只放第一个评价
            request.setAttribute("evaluation", evaluations.get(0));

            //这里也只放第一个订单
            if (ordersList.size() != 0) {
                request.setAttribute("singleorder", ordersList.get(0));
            }

            Integer evaluateNum = (Integer) session.getAttribute("evaNum");
            if (evaluateNum == null) {
                evaluateNum = 0;
            }

            session.setAttribute("evaNum", evaluateNum);
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);

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
