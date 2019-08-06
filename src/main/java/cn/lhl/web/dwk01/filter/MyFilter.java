package cn.lhl.web.dwk01.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/8/5.
 */
//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String ctx = request.getContextPath(); // /ctx
        String uri = request.getRequestURI();
        List<String> whiteList = Arrays.asList(new String[] {ctx + "/login.jsp", ctx + "/login", ctx + "/keycloak/login"});
        if (whiteList.contains(uri)) {
            // 白名单直接放行
            filterChain.doFilter(req, resp);
            return;
        }

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // 已登录 放行
            filterChain.doFilter(req, resp);
        } else {
            // 未登录 重定向到登录页
            response.sendRedirect(ctx + "/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
