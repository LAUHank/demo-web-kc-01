package cn.lhl.web.dwk01.filter;

import cn.lhl.web.dwk01.model.MyKeycloakToken;
import cn.lhl.web.dwk01.utils.KeycloakUtil;
import org.keycloak.adapters.servlet.KeycloakOIDCFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2019/8/5.
 */
public class MyKcFilter extends KeycloakOIDCFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String prefix = "kc filter";
        System.out.println(prefix + " begin");

        MyKeycloakToken token = KeycloakUtil.getToken((HttpServletRequest) req);
        if (token != null) {
            String username = token.getPreferredUsername();
            System.out.println(prefix + " " + username);
        } else {
            System.out.println(prefix + " keycloak unlogined");
        }

        super.doFilter(req, res, chain);

        MyKeycloakToken token2 = KeycloakUtil.getToken((HttpServletRequest) req);
        if (token2 != null) {
            String username = token2.getPreferredUsername();
            System.out.println(prefix + " " + username);
        } else {
            System.out.println(prefix + " keycloak unlogined");
        }

        System.out.println(prefix + " end");
    }
}
