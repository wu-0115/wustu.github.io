package com.itszb.web;

import com.itszb.pojo.User;
import com.itszb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String remember = request.getParameter("remember");
        //2. 调用service查询
        User user = service.login(username, password);
        //3. 判断
        if (user != null) {
            //登录成功，跳转到查询所有的BrandServlet

            // 判断用户是否勾选记住我
            if ("1".equals(remember)) {
                // 设置cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);

                //发送cookie
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //将登陆成功后的user对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");
        } else {
            // 登录失败,
            // 存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");
            // 跳转到login.jsp
            System.out.println("密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
