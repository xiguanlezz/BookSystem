package com.cj.servlet.home;

import com.cj.entity.CART;
import com.cj.entity.CATEGORY;
import com.cj.entity.PRODUCT;
import com.cj.service.CARTDao;
import com.cj.service.CATEGORYDao;
import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectproductview")
public class SelectProductView extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<PRODUCT> ps = new ArrayList<PRODUCT>();

        //点开书本的信息
        int pid = Integer.parseInt(request.getParameter("id"));
        PRODUCT product = PRODUCTDao.QueryById(pid);
        request.setAttribute("product", product);

        //书本的类别信息
        int fid = product.getPRODUCT_FID();
        int cid = product.getPRODUCT_CID();
        CATEGORY fcate = CATEGORYDao.QueryById(fid);
        CATEGORY ccate = CATEGORYDao.QueryById(cid);
        request.setAttribute("fcate", fcate);
        request.setAttribute("ccate", ccate);

        String uid = request.getParameter("uid");
        if(uid!=null) {     //说明此时是购物车跳转到的商品页面, 需要显示已经加入购物车对应书的本数
            CART cart = CARTDao.QueryByU_P_Id(uid, pid);
            request.setAttribute("buyNumber", cart.getCART_QUANTITY());
        }

        //推荐书本
        List<PRODUCT> plist = PRODUCTDao.QueryByFId(fid);
        request.setAttribute("plist", plist);

        //设置一个队列用来保存最近浏览的记录
        int maxnum = 7;
        List<PRODUCT> lastlyAccess = new ArrayList<PRODUCT>();
        HttpSession session = request.getSession();

        List<Integer> q = (List<Integer>) session.getAttribute("ids");
        if (q == null) {
            q = new ArrayList<Integer>();
        }
        if (q.size() >= maxnum) {
            q.remove(0);
        }
        if (!q.contains(pid)) {
            q.add(pid);
        }
        session.setAttribute("ids", q);
        if (q != null) {
            lastlyAccess = PRODUCTDao.QueryByIds(q);
        }
        session.setAttribute("lastlyAccess", lastlyAccess);

        request.getRequestDispatcher("bookdetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
