package DAO.interfaces;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserInter extends DAOInter <User> {
    List findAll();

    User findById(int id) throws SQLException;

    User findByName(String name)throws SQLException ;

    public User findByPhoneAndPass(String tel, String password)throws SQLException;

}
