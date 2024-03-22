package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {


    private static final String userName = "root";
    private static final String password = "2605";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/mydbtest";



    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(connectionUrl, userName, password);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к Mysql");
        }

    }

}
