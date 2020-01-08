package com.cj.servlet.cate;

import com.cj.entity.CATEGORY;
import com.cj.service.CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/docateupdate.do")
public class DoCateUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));    //从隐藏表单中获取
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String className = request.getParameter("className");

        CATEGORY category = new CATEGORY(id, className, parentId);
        PrintWriter out = response.getWriter();
        int count = CATEGORYDao.Update(id, category);
        if (count > 0) {
            out.print("<script>");
            out.print("alert('分类修改成功');");
            out.print("location.href='docateselect.do'");
            out.print("</script>");
            out.close();
        } else {
            out.print("<script>");
            out.print("alert('分类修改失败');");
            out.print("location.href='docateselect.do'");
            out.print("</script>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
