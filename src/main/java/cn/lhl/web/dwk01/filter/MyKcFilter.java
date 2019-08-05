package cn.lhl.web.dwk01.filter;

import org.keycloak.adapters.servlet.KeycloakOIDCFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/8/5.
 */
public class MyKcFilter extends KeycloakOIDCFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("kc filter begin");
        super.doFilter(req, res, chain);
        System.out.println("kc filter end");
    }
}
