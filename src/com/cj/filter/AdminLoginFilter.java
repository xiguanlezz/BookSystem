package com.cj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter({"*.do", "/manage/*"})
public class AdminLoginFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String flag = (String) session.getAttribute("isAdminLogin");
        String path1 = request.getContextPath();  //得到虚拟路径
        String path2 = request.getRequestURI();   //得到虚拟路径+Servlet路径
        String uri = path2.substring(path1.length());     //截取字符串
        //System.out.println(path1);
        //System.out.println(path2);


        //反向的权限控制....  有点绕
        if (flag == "1") {     //管理员登陆过了, 直接放行
            chain.doFilter(request, response);
        } else {
            if (uri.contains("admin_")) {    //admin_代表是我们在不登陆的情况下不希望被访问的页面
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('请先登陆');");
                out.print("location.href='login.jsp';");    //此时目录已经在manage下面
                out.print("</script>");
                out.close();
            } else if (uri.contains(".do")) {    //.do是管理员操作的Servlet
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('请先登陆');");
                out.print("location.href='manage/login.jsp';");
                out.print("</script>");
                out.close();
            } else {         //放行第一次进入管理员的登录页面(login.jsp)以及一些css js文件
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
