package com.cj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/register")    //配置拦截注册的Servlet
public class RegisterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userName = request.getParameter("userName");
        PrintWriter out = response.getWriter();
        if ("".equals(userName)) {
            out.print("<script>");
            out.print("alert('用户名为空, 注册失败');");
            out.print("location.href='register.jsp';");
            out.print("</script>");
            out.close();
        }

        String code = request.getParameter("veryCode");
        String syscode = (String) request.getSession().getAttribute("code");
        if (!syscode.equals(code)) {
            out.print("<script>");
            out.print("alert('验证码输入有误, 注册失败');");
            out.print("location.href='register.jsp';");
            out.print("</script>");
            out.close();
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
