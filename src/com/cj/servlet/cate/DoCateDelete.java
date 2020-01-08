package com.cj.servlet.cate;

import com.cj.entity.CATEGORY;
import com.cj.service.CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/docatedelete.do")
public class DoCateDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        //CATEGORYDao.deleteById(id);     //直接删除有问题, 当这个节点有孩子的时候 孩子会残留在数据库中

        boolean flag = false;   //标识结点下面是否有孩子结点
        List<CATEGORY> list = CATEGORYDao.QueryAll();
        for (CATEGORY cate : list) {
            if (cate.getCATE_PARENT_ID() == id) {
                CATEGORYDao.DeleteById(cate.getCATE_ID());  //删除该结点下面的孩子结点
                flag = true;
            }
        }
        if (!flag) {     //说明待删除的结点没有孩子
            CATEGORYDao.DeleteById(id);
        } else {
            CATEGORYDao.DeleteById(id);     //孩子删完后还要删老子
        }

        //不管删除与否, 都是跳到同一个页面
        response.sendRedirect("docateselect.do");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
