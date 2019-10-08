package DAO.implementation;

import DAO.ConnectionJDBC;
import DAO.interfaces.ToDoInter;
import entity.ToDo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoImpl implements ToDoInter {

    private static final String DELETE_ONE_TASK = "DELETE FROM firstproject.list WHERE id=?";
    private static final String INSERT = "INSERT INTO firstproject.list(user_id, task) VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT task FROM firstproject.list WHERE id = ?";
    private static final String FIND_BY_USER_ID = "SELECT task FROM firstproject.list WHERE user_id = ?";
    private static final String UPDATE = "UPDATE list SET task=? WHERE task_id=?";

    @Override
    public ToDo findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ToDo task = new ToDo();
                task.setId(rs.getInt("id"));

                return task;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return null;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(DELETE_ONE_TASK);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return 0;
    }

    @Override
    public int insert(ToDo toDo) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        try {
            stmt.setInt(1, toDo.getUser_id());
            stmt.setString(2, toDo.getMessage());
            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                toDo.setId(rs.getInt(1));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return 0;
    }

    @Override
    public int update(ToDo toDo) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE);
        try {
            stmt.setString(1, toDo.getMessage());
            stmt.setInt(2, toDo.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public List<ToDo> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ToDo> findByUserId(Integer user_id) throws SQLException {
        List<ToDo> toDoLists = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(FIND_BY_USER_ID, user_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String message = resultSet.getString(3);
                toDoLists.add(new ToDo(id, userId, message));
                return toDoLists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}