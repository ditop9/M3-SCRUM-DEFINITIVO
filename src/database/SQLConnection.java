package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    private static Connection con;
    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:Supermarket.sql");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }
}
