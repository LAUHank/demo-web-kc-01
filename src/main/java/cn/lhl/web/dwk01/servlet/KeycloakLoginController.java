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

    /*
    ems 与 keycloak 整合 过渡期方案
    1. ems 新建一个controller 参考本类 此类的逻辑用于将keycloak用户转换为ems用户 并模拟原ems登录逻辑进行自动登录
    2. 将此controller加入到原来登录filter的白名单中 不过滤
    3. 将此controller配置到keycloak filter中

    这样就可以实现以下过渡期要求
    允许两套账号体系并存 依然可以使用原ems账号从原来登录页面登录
    如果从别的系统已经在keycloak单点登录了 跳转到ems时能自动登录

    但是使用时要注意
    1. 从别的系统跳转到ems时 入口地址必须是 新建的controller
    2. ems中的用户是keycloak用户的一个子集 对于keycloak有 ems没有的用户 要考虑提示用户 参考传智大学就业系统的实现方案
    3. 用户 lhl@itcast.cn 在ems使用zixun01登录
      后来在其他系统跳转回ems时 由于有一步自动登录的操作 所以zixun01可能会变成lhl@itcast.cn
    4. 退出时只能退出ems 不是单点退出
     */

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
