package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:restaurant.db";

    public static Connection connect() {

        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Database Connected Successfully");
            return conn;

        } catch (Exception e) {

            System.out.println("Database Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
}