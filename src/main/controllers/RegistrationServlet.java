package controllers;


import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//import utils.MD5;


@WebServlet(name = "Registration", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private User user = new User();
    private UserService service = new UserService();
    private HttpSession session;
    private int userId;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        user.setName(request.getParameter("name"));
        user.setTel(request.getParameter("phone"));
    //  user.setPassword(MD5.runMD5(request.getParameter("password")));
        user.setPassword(request.getParameter("password"));

        try {
            userId = service.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session = request.getSession(true);
        session.setAttribute("id",userId);
        response.sendRedirect("/Project_war/Greetings");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
