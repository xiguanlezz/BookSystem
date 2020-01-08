package com.cj.servlet.order;

import com.cj.entity.ORDERS;
import com.cj.service.ORDERSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doorderclose.do")
public class DoOrderClose extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String orderid = request.getParameter("oid");
        int oid = Integer.parseInt(orderid);

        //ORDERS o = ORDERSDao.QueryById(oid);
        ORDERSDao.UpdateStatus(oid, "4");
        request.getRequestDispatcher("doorderselect.do").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
