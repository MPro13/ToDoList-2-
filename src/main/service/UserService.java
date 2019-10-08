package service;


import DAO.implementation.UserImpl;
import entity.User;

import java.sql.SQLException;

public class  UserService   {
    private static UserImpl dao = new UserImpl();
    //private UserImpl dao = new UserImpl();

    public int create(User user) throws SQLException {
        return dao.insert(user);
    }

    public static User login(String tel, String password) throws SQLException {

       // String md5Pass = DigestUtils.md5Hex(uPassword);

        User user = dao.findByPhoneAndPass(tel, password);
        return user;
    }

    /*public static void registration(User user) throws SQLException {

        String email = user.getEmail();

        int isInDB = userDAO.selectByEmail(email);

        if (isInDB == 0) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            userDAO.create(user);
        } else {
            throw new EmailAlreadyExists();
        }

    }*/
//
//    public User readById(int id) throws SQLException {
//        return dao.findById(id);
//    }
//
//
//    public User readByName(String login) throws SQLException {
//        return dao.findByName(login);
//    }


//    public boolean userIsExist(String login, String password) {
//        return dao.existIsUser(login,password);
//    }
}