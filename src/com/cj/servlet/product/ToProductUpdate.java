package com.cj.servlet.product;

import com.cj.entity.CATEGORY;
import com.cj.entity.PRODUCT;
import com.cj.service.CATEGORYDao;
import com.cj.service.PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toproductupdate.do")
public class ToProductUpdate extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        request.setAttribute("id", id);
        int product_id = 0;
        if (id != null) {
            product_id = Integer.parseInt(id);
        }

        PRODUCT p = PRODUCTDao.QueryById(product_id);
        request.setAttribute("product", p);

        int fid = p.getPRODUCT_FID();
        int cid = p.getPRODUCT_CID();
        CATEGORY fcate = CATEGORYDao.QueryById(fid);
        CATEGORY ccate = CATEGORYDao.QueryById(cid);
        request.setAttribute("fcate", fcate);
        request.setAttribute("ccate", ccate);

        List<CATEGORY> flist = CATEGORYDao.QueryTwoCate("father");
        request.setAttribute("flist", flist);
        List<CATEGORY> clist = CATEGORYDao.QueryTwoCate("child");
        request.setAttribute("clist", clist);
        //System.out.println(clist.size());

        request.getRequestDispatcher("manage/admin_productmodify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
