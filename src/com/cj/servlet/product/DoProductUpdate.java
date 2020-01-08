package com.cj.servlet.product;

import com.cj.entity.PRODUCT;
import com.cj.service.PRODUCTDao;
import com.jspsmart.upload.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doproductupdate.do")
public class DoProductUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        SmartUpload su = new SmartUpload();
        su.initialize(this.getServletConfig(), request, response);    //初始化
        try {
            su.upload();    //上传过程
            Files files = su.getFiles();
            int c1 = files.getCount();
            System.out.println(c1);

            File file = files.getFile(1);     //获取对象
            String filename = file.getFileName(); //获取文件名字;

            su.save("images/product");
            su.setAllowedFilesList("jpg");

            //上传文件的表单获取参数的方法
            Request req = su.getRequest();
            String bookName = req.getParameter("bookName");
            String f = req.getParameter("FId");
            int fid = 0;
            if (f != null) {
                fid = Integer.parseInt(f);
            }
            String c = req.getParameter("CId");
            int cid = 0;
            if (c != null) {
                cid = Integer.parseInt(c);
            }
            String bookDescription = req.getParameter("bookDescription");
            String price = req.getParameter("bookPrice");
            int bookPrice = 0;
            if (price != null) {
                bookPrice = Integer.parseInt(price);
            }
            String stock = req.getParameter("bookStock");
            int bookStock = 0;
            if (price != null) {
                bookStock = Integer.parseInt(stock);
            }

            //获取图书的id
            String pid = req.getParameter("productId");
            int bookId = 0;
            if (pid != null) {
                bookId = Integer.parseInt(pid);
            }

            PRODUCT p = PRODUCTDao.QueryById(bookId);

            PRODUCT product = null;
            if (!("".equals(filename))) {
                product = new PRODUCT(bookId, bookName, bookDescription, bookPrice, bookStock, fid, cid, filename);
            } else {
                product = new PRODUCT(bookId, bookName, bookDescription, bookPrice, bookStock, fid, cid, p.getPRODUCT_FILENAME());
            }
            PrintWriter out = response.getWriter();
            int count = PRODUCTDao.Update(bookId, product);
            if (count > 0) {
                out.print("<script>");
                out.print("alert('图书信息修改成功');");
                out.print("location.href='doproductselect.do'");
                out.print("</script>");
                out.close();
            } else {
                out.print("<script>");
                out.print("alert('图书信息修改失败');");
                out.print("location.href='toproductupdate.do?id=" + bookId + "'");
                out.print("</script>");
                out.close();
            }

        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
