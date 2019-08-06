package cn.lhl.web.dwk01.servlet;

import cn.lhl.web.dwk01.model.MyKeycloakToken;
import cn.lhl.web.dwk01.utils.DBUtil;
import cn.lhl.web.dwk01.utils.KeycloakUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/8/6.
 */
@WebServlet("/keycloak/login")
public class KeycloakLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prefix = "/keycloak/login";
        MyKeycloakToken token = KeycloakUtil.getToken(request);
        if (token != null) {
            String username = token.getPreferredUsername();
            List<String> userTable = DBUtil.userTable;
            if (userTable.contains(username)) {
                // EMS用户 登录
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                //request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            System.out.println(prefix + " keycloak unlogined");
        }
    }
}
