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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/orderquery")
public class OrderQuery extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String isLogin = (String) session.getAttribute("isLogin");

        if (user != null && "1".equals(isLogin)) {
            //直接从数据库中查询数据
            Map<ITEM, Integer> I_Indexmap = new LinkedHashMap<ITEM, Integer>();
            Map<PRODUCT, Integer> P_Indexmap = new LinkedHashMap<PRODUCT, Integer>();
            Map<Integer, ADDRESS> Index_Amap = new LinkedHashMap<Integer, ADDRESS>();

            Map<ORDERS, PAY> O_Pmap = new LinkedHashMap<ORDERS, PAY>();
            List<ORDERS> ordersList = ORDERSDao.QueryByUId(user.getUSER_ID());

            for (ORDERS o : ordersList) {
                PAY paykey = PAYDao.QueryById(o.getORDER_PAY_ID());
                O_Pmap.put(o, paykey);

                ADDRESS address = ADDRESSDao.QueryById(o.getORDER_ADDRESS_ID());
                Index_Amap.put(o.getORDER_ID(), address);

                List<ITEM> items = ITEMDao.QueryByOId(o.getORDER_ID());
                for (ITEM i : items) {
                    I_Indexmap.put(i, o.getORDER_ID());
                    PRODUCT prokey = PRODUCTDao.QueryById(i.getITEM_P_ID());
                    P_Indexmap.put(prokey, o.getORDER_ID());
                }
            }


            request.setAttribute("O_Pmap", O_Pmap);
            request.setAttribute("I_Indexmap", I_Indexmap);
            request.setAttribute("P_Indexmap", P_Indexmap);
            request.setAttribute("Index_Amap", Index_Amap);

            request.getRequestDispatcher("myorder.jsp").forward(request, response);

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
