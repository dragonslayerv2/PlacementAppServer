package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.json.JSONConstants;
import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsFetcher {
    private final int username;
    private final Connection connection;
    public NotificationsFetcher(int username, Connection connection){
        this.username=username;
        this.connection=connection;
    }
    
    public JSONArray getJSON() throws SQLException, JSONException, NoRecordsException {
        String query = String.format("SELECT * FROM %s INNER JOIN %s ON %s = %s WHERE %s=%s;",
                Tables.Notification.TABLE_NAME,
                Tables.NOTIFICATION_STUDENT_RELATIONSHIP.TABLE_NAME,
                Tables.Notification.ID,
                Tables.NOTIFICATION_STUDENT_RELATIONSHIP.NOTIFICATION_ID,
                Tables.NOTIFICATION_STUDENT_RELATIONSHIP.STUDENT_ID,
                String.valueOf(username));
        
        ResultSet resultSet=connection.prepareStatement(query).executeQuery();
        JSONArray notificationArray = new JSONArray();
        int position=0;
        while(resultSet.next()){
            JSONObject jsonNotification = new JSONObject();
            jsonNotification.put(JSONConstants.ID,resultSet.getInt(Tables.Notification.ID));
            jsonNotification.put(JSONConstants.COMPANY_ID,resultSet.getInt(Tables.Notification.COMPANY_ID));
            jsonNotification.put(JSONConstants.JOB_ID,resultSet.getInt(Tables.Notification.JOB_ID));
            jsonNotification.put(JSONConstants.DESCRIPTION,resultSet.getString(Tables.Notification.DESCRIPTION));
            jsonNotification.put(JSONConstants.TITLE,resultSet.getString(Tables.Notification.TITLE));
            notificationArray.put(position++,jsonNotification);
        }
        return notificationArray;
    }
}
