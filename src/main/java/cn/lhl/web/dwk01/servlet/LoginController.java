package cn.lhl.web.dwk01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/8/6.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List<String> userTable = Arrays.asList(new String[] {"zixun01", "zixun02"});
        if (userTable.contains(username)) {
            // EMS用户 登录
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
