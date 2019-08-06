package cn.lhl.web.dwk01.utils;

import cn.lhl.web.dwk01.model.MyKeycloakToken;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.TokenVerifier;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.common.VerificationException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/5.
 */
public class KeycloakUtil {
    public static MyKeycloakToken getToken(HttpServletRequest request) {
        Object o = request.getAttribute(KeycloakSecurityContext.class.getName());
        System.out.println("kc sec ctx " + o); // org.keycloak.adapters.RefreshableKeycloakSecurityContext
        MyKeycloakToken myKeycloakToken = null;
        if (o instanceof RefreshableKeycloakSecurityContext) {
            RefreshableKeycloakSecurityContext kc = (RefreshableKeycloakSecurityContext) o;
            String ts = kc.getTokenString(); // 获取当前登录用户的token
            // 解析token jwt string 获取用户相关信息
            try {
                myKeycloakToken = TokenVerifier.create(ts, MyKeycloakToken.class).getToken();
            } catch (VerificationException e) {
                e.printStackTrace();
            }
        }
        return myKeycloakToken;
    }
}
