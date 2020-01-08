package com.cj.servlet.order;

import com.cj.entity.ITEM;
import com.cj.entity.ORDERS;
import com.cj.entity.USER;
import com.cj.service.ITEMDao;
import com.cj.service.ORDERSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/showorder")
public class ShowOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession();
        USER user= (USER) session.getAttribute("user");
        List<Integer> orderids= (List<Integer>) session.getAttribute("oids");
        List<ORDERS> orders=ORDERSDao.QueryByUId(user.getUSER_ID());

        //重写ITEM的equals和hashcode函数
        Set<ITEM> items=new LinkedHashSet<ITEM>();
        for(Integer i:orderids) {
            List<ITEM> list=ITEMDao.QueryByOId(i);
            for(ITEM im:list) {
                items.add(im);
            }
        }
        request.setAttribute("items",items);
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("myorder.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
