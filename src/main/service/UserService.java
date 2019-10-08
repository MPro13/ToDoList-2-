package service;


import DAO.implementation.UserImpl;
import entity.User;

import java.sql.SQLException;

public class  UserService   {
    private UserImpl dao = new UserImpl();

    public int create(User user) throws SQLException {
        return dao.insert(user);
    }

    public User readById(int id) throws SQLException {
        return dao.findById(id);
    }


    public User readByName(String login) throws SQLException {
        return dao.findByName(login);
    }


//    public boolean userIsExist(String login, String password) {
//        return dao.existIsUser(login,password);
//    }
}