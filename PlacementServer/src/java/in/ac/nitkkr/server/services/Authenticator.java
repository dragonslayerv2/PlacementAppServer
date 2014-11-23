package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {
    private final Connection connection;

    public Authenticator(Connection connection) {
        this.connection = connection;
    }

    public boolean isAuthorized(int username, String password) throws SQLException {
        String query = String.format("SELECT %s FROM %s where %s=%s AND %s=\"%s\";", Tables.Students.ID, Tables.Students.TABLE_NAME, Tables.Students.ID, String.valueOf(username), Tables.Students.PASSWORD, password);  
        ResultSet result = connection.prepareStatement(query).executeQuery();
        return result.next();
    }
}
