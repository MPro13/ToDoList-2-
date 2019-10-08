package DAO.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import DAO.ConnectionJDBC;
import DAO.interfaces.UserInter;
import entity.User;

public class UserImpl implements UserInter {

    private static final String DELETE = "DELETE FROM user WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM user ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM user WHERE name=?";
    private static final String INSERT = "INSERT INTO user(name, tel, password) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET name=?, tel=?, password=? WHERE id=?";
    private static final String FIND_BY_PHONE_AND_PASSWORD = "SELECT * FROM user WHERE `tel` = ? and `password` = ?";
    // executeUpdate(); - повертає числове значення рядків таблиці, що змінились
    // execute() - повертає ResultSet для команди SELECT
    @Override
    public int delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        return  0;
    }

    @Override
    public List<User> findAll() {
        Connection conn;
        PreparedStatement stmt;
        List<User> list = new ArrayList<User>();
        try  {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTel(rs.getString("tel"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTel(rs.getString("tel"));
                user.setPassword(rs.getString("password"));
                return user;
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
    public User findByName(String name) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(FIND_BY_NAME);
        try{

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTel(rs.getString("tel"));
                user.setPassword(rs.getString("password"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            stmt.close();
        }
        return null;
    }

    @Override
    public User findByPhoneAndPass(String tel, String password) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(FIND_BY_PHONE_AND_PASSWORD);
        try{

            stmt.setString(1, tel);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTel(rs.getString("tel"));
                user.setPassword(rs.getString("password"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            stmt.close();
        }
        return null;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        try {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getTel());
            stmt.setString(3, user.getPassword());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            stmt.close();
        }
        return 0;
    }

    @Override
    public int update(User user) throws SQLException {
        Connection conn = ConnectionJDBC.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE);
        try {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getTel());
            stmt.setString(4, user.getPassword());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
