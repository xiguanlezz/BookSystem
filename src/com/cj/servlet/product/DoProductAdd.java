package com.cj.servlet.product;

import com.cj.entity.PRODUCT;
import com.cj.service.PRODUCTDao;
import com.jspsmart.upload.*;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doproductadd.do")
public class DoProductAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        SmartUpload su = new SmartUpload();
        su.initialize(this.getServletConfig(), request, response);    //初始化
        try {
            su.upload();    //上传过程
            Files files = su.getFiles();
            File file = files.getFile(0);     //获取对象
            String filename = file.getFileName(); //获取文件名字
            //System.out.println(filename);

            java.io.File nf1 = new java.io.File(this.getServletContext().getRealPath("images"));
            if (!nf1.exists()) {
                nf1.mkdir();
            }
            java.io.File nf2 = new java.io.File(nf1, "product");
            if (!nf2.exists()) {
                nf2.mkdir();
            }
            su.save("images/product");

            su.setAllowedFilesList("jpg");
            //file.saveAs("images/product/" + filename, SmartUpload.SAVE_VIRTUAL);

            //上传文件的表单获取参数的方法
            Request req = su.getRequest();

            String pname = req.getParameter("productName");
            String desc = req.getParameter("productDesc");
            int price = Integer.parseInt(req.getParameter("productPrice"));
            int stock = Integer.parseInt(req.getParameter("productStock"));
            int fid = Integer.parseInt(req.getParameter("F_C_Id").split("\\+")[0]);   //split分割特殊字符需要\\转译
            int cid = Integer.parseInt(req.getParameter("F_C_Id").split("\\+")[1]);
            System.out.println(filename);
            PRODUCT product = new PRODUCT(0, pname, desc, price, stock, fid, cid, filename);//PRODUCT_ID会自动增长
            //System.out.println(product);

            int count = PRODUCTDao.Insert(product);
            if (count > 0) {
                response.sendRedirect("doproductselect.do");
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('图书添加失败');");
                out.print("location.href='toproductadd.do';");
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
