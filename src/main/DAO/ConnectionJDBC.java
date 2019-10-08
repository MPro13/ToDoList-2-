package DAO;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionJDBC {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/firstproject?serverTimezone=Europe/Kiev&useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASS = "-rCm9NG5_11q";

    private static Connection connection;


    private  ConnectionJDBC(){ }

    public static Connection getConnection(){
        if(connection == null) {
            try {
                Class.forName(DRIVER_NAME).getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(URL,LOGIN,PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

