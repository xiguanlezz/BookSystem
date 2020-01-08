package com.cj.servlet.order;

import com.cj.entity.EVALUATION;
import com.cj.entity.USER;
import com.cj.service.EVALUATIONDao;
import com.cj.service.ORDERSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/evaluationadd")
public class EvaluationAdd extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        USER user = (USER) session.getAttribute("user");
        String score = request.getParameter("score");
        String words = request.getParameter("evawords");
        String oid = request.getParameter("oid");

        //更新订单为已评价状态
        EVALUATION e = new EVALUATION(0, user.getUSER_ID(), Integer.parseInt(oid), Integer.parseInt(score), words);
        int count = EVALUATIONDao.Insert(e);
        ORDERSDao.UpdateStatus(Integer.parseInt(oid), "3-2");

//        Integer evaluateNum = (Integer) session.getAttribute("evaNum");
//        if (evaluateNum == null) {
//            evaluateNum = 0;
//        } else {
//            evaluateNum++;
//        }
//        session.setAttribute("evaNum", evaluateNum);

        PrintWriter out = response.getWriter();
        if (count > 0) {
            out.print("evaAddSuccess");
            System.out.println("成功");
            out.close();
        } else {
            System.out.println("失败");
            out.print("evaAddFailure");
        }
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
