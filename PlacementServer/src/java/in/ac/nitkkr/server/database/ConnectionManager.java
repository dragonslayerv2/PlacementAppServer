package in.ac.nitkkr.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection;

    public ConnectionManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String loc = "jdbc:mysql://localhost:3306/placements";
            connection = DriverManager.getConnection(loc, "root", "admin");
        } catch (ClassNotFoundException | SQLException cnf) {
            System.out.println("Connection not established");
            System.out.println(cnf.toString());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
