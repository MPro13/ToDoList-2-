package controllers;

import DAO.implementation.ToDoImpl;
import entity.ToDo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private ToDoImpl toDoListDAO = new ToDoImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("task");
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute("id");
        ToDo task = new ToDo(userId, message);
        try {
            toDoListDAO.insert(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        ArrayList<ToDo> toDoLists = null;
        try {
            toDoLists = (ArrayList<ToDo>) toDoListDAO.findByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("todolist", toDoLists);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }

}