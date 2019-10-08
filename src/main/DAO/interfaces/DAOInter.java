package DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAOInter <T> {

    int delete(int id) throws SQLException;

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    List<T> findAll();
}
