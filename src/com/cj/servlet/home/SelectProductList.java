package com.cj.servlet.home;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int FId,CId;
        List<PRODUCT> ps=new ArrayList<PRODUCT>();

        String id = request.getParameter("id");
        String cid = request.getParameter("cid");
        String fid = request.getParameter("fid");
        if (id != null) {
            FId = Integer.parseInt(id);
            CATEGORY cate = CATEGORYDao.QueryById(FId);
            request.setAttribute("fcate", cate);

            ps= PRODUCTDao.QueryByFId(FId);
            request.setAttribute("ps",ps);
        }
        if (cid != null && fid != null) {
            CId = Integer.parseInt(cid);
            CATEGORY ccate = CATEGORYDao.QueryById(CId);
            request.setAttribute("ccate", ccate);
            FId = Integer.parseInt(fid);
            CATEGORY fcate = CATEGORYDao.QueryById(FId);
            request.setAttribute("fcate", fcate);

            ps=PRODUCTDao.QueryByF_C_Id(FId,CId);
            request.setAttribute("ps",ps);
        }

        request.getRequestDispatcher("showlist.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
