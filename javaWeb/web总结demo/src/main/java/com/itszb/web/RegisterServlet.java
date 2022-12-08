package com.itszb.web;

import com.itszb.pojo.User;
import com.itszb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //程序生成的验证码 从session中获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {   //如果不等,则验证码错误
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);

            //不允许注册
            return;
        }


        boolean flag = service.register(user);
        if (flag) {
            //注册成功,跳转登录页面
            request.setAttribute("register_msg","注册成功,请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            //注册失败,跳转注册页面
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
