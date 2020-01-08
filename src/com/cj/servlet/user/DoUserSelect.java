package com.cj.servlet.user;

import com.cj.entity.USER;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/douserselect.do")
public class DoUserSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int currentPage = 1;
        String cp = request.getParameter("currentPage");
        if (cp != null) {
            currentPage = Integer.parseInt(cp);
        }
        request.setAttribute("currentPage", currentPage);

        int pageSize = 3;
        String ps = request.getParameter("pageSize");
        if (ps != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        request.setAttribute("pageSize", pageSize);

        List<USER> users = new ArrayList<USER>();
        String keyWord = request.getParameter("keyWord");
        if (keyWord == null) {     //关键词为空, 只分页查询
            users = USERDao.QueryByPage(currentPage, pageSize);
            int totalCount = USERDao.getTotalCount();
            request.setAttribute("totalCount", totalCount);
            int totalPage = USERDao.getTotalPage(pageSize);
            request.setAttribute("totalPage", totalPage);

        } else {        //分页+关键词
            users = USERDao.QueryByPage_keyWord(currentPage, pageSize, keyWord);
            int totalCount = USERDao.getTotalCountBykeyWord(keyWord);
            request.setAttribute("totalCount", totalCount);
            int totalPage = USERDao.getTotalPageBykeyWord(keyWord, pageSize);
            request.setAttribute("totalPage", totalPage);
        }
        request.setAttribute("users", users);


        //List<USER> list = USERDao.QueryAll();     //不分页的查询
        //request.setAttribute("users", list);

        //点击查询之后, 输入关键词的标签内容会被清除, 这时将关键词保存起来
        if (keyWord != null) {
            request.setAttribute("keyWord", keyWord);
        }

        request.getRequestDispatcher("manage/admin_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
