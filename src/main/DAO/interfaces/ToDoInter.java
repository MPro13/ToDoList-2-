package DAO.interfaces;

import entity.ToDo;
import entity.User;

import java.sql.SQLException;

public interface ToDoInter extends DAOInter <ToDo> {

    ToDo findById(int id) throws SQLException;

}
