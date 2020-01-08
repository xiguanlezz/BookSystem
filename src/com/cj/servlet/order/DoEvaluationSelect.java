package com.cj.servlet.order;

import com.cj.entity.EVALUATION;
import com.cj.entity.USER;
import com.cj.service.EVALUATIONDao;
import com.cj.service.USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/doevaluationselect.do")
public class DoEvaluationSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        List<EVALUATION> evaluations = EVALUATIONDao.QueryAll();
        Map<EVALUATION, String> e_smap = new LinkedHashMap<EVALUATION, String>();
        for (EVALUATION e : evaluations) {
            USER user = USERDao.QueryById(e.getEVALUATION_U_ID());
            String name = user.getUSER_NAME();
            e_smap.put(e, name);
        }
        request.setAttribute("e_smap", e_smap);
        request.getRequestDispatcher("manage/admin_evaluation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
