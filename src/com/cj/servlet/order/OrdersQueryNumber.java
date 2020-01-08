package com.cj.servlet.order;

import com.cj.entity.*;
import com.cj.service.ORDERSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ordersquerynum")
public class OrdersQueryNumber extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String isLogin = (String) session.getAttribute("isLogin");

        if (user != null && "1".equals(isLogin)) {
            int waitPayNumber = 0;
            int waitCheckNumber = 0;
            int waitEvaluateNumber = 0;
            List<ORDERS> orders = ORDERSDao.QueryByUId(user.getUSER_ID());
            for (ORDERS o : orders) {
                if ("1".equals(o.getORDER_STATUS())) {
                    waitPayNumber++;
                } else if ("2".equals(o.getORDER_STATUS())) {
                    waitCheckNumber++;
                } else if ("3-1".equals(o.getORDER_STATUS())) {
                    waitEvaluateNumber++;
                }
            }
            request.setAttribute("waitPayNumber", waitPayNumber);
            request.setAttribute("waitCheckNumber", waitCheckNumber);
            request.setAttribute("waitEvaluateNumber", waitEvaluateNumber);
            request.getRequestDispatcher("mycenter.jsp").forward(request, response);

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
