package cn.lhl.web.dwk01.servlet;

import cn.lhl.web.dwk01.model.MyKeycloakToken;
import cn.lhl.web.dwk01.utils.KeycloakUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2019/8/5.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prefix = "/home";
        System.out.println(prefix + " begin");
        MyKeycloakToken token = KeycloakUtil.getToken(req);
        if (token != null) {
            String username = token.getPreferredUsername();
            System.out.println(prefix + " " + username);
        } else {
            System.out.println(prefix + " keycloak unlogined");
        }
        PrintWriter pw = resp.getWriter();
        pw.println("hwa");
        System.out.println(prefix + " end");
    }
}
