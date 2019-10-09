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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService service= new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("in servlet");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        User user = null;
        HttpSession session = null;
//                UserService.login(phone, password);
//        HttpSession session = request.getSession();
//        session.setAttribute("id", user.getId());
//        response.sendRedirect(request.getContextPath() + "/home"); HttpSession session
        try {
            user = service.login(phone, password);
            if (user !=null){
                 session = request.getSession();
            }

            session.setAttribute("id", user.getId());
            session.setAttribute("user",user);
            request.getRequestDispatcher( "/home").forward(request,response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
