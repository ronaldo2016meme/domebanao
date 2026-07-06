package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectService {
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLBanAo;encrypt=true;trustServerCertificate=true";

    private final String USER = "sa";

    private final String PASSWORD = "123";

    public ConnectService() {
    }

    public Connection myConnection() {
        Connection conn = null;

        try {

            // Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Create Connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connect successful!");

        } catch (ClassNotFoundException e) {

            System.out.println("Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Connect failed!");
            e.printStackTrace();
        }

        return conn;
    }
}
