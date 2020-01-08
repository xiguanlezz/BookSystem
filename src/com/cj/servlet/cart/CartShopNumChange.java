package com.cj.servlet.cart;

import com.cj.service.CARTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cartshopnumchange")
public class CartShopNumChange extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String count = request.getParameter("count");
        String cpid = request.getParameter("cpid");
        CARTDao.UpdateNum(Integer.parseInt(cpid), Integer.parseInt(count));

//        PrintWriter out=response.getWriter();
//
//        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
