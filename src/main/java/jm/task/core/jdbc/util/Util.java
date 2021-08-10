package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static String url = "jdbc:mysql://localhost:3306/db_test?sslUse=false";
    private static String userName = "root";
    private static String pass = "root";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, userName, pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
