package com.cj.servlet.address;

import com.cj.entity.USER;
import com.cj.service.ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addressupdate")
public class AddressUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        //System.out.println(name);
        String province = request.getParameter("p");
        String city = request.getParameter("city");
        String address = request.getParameter("a");
        String zipcode = request.getParameter("z");
        int count = ADDRESSDao.UpdataByUId(user.getUSER_ID(), name, mobile, province, city, address, zipcode);
        PrintWriter out = response.getWriter();
        if (count > 0) {
            out.print("success");
        } else {
            out.print("failure");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
