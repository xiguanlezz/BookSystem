package com.cj.servlet.address;

import com.cj.entity.ADDRESS;
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
import java.util.List;

@WebServlet("/addressselect")
public class AddressSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String isLogin = (String) session.getAttribute("isLogin");
        USER user = (USER) session.getAttribute("user");
        if (user != null && "1".equals(isLogin)) {
            List<ADDRESS> addresses = ADDRESSDao.QueryAll(user.getUSER_ID());

            //这里只放一个地址, 模板不行
            if (addresses.size() != 0) {
                request.setAttribute("address", addresses.get(0));
                request.setAttribute("addresses", addresses);
            }
            request.getRequestDispatcher("address.jsp").forward(request, response);
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
