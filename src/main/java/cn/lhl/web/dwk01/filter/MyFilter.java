package cn.lhl.web.dwk01.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2019/8/5.
 */
//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter begin");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter end");
    }

    @Override
    public void destroy() {

    }
}
