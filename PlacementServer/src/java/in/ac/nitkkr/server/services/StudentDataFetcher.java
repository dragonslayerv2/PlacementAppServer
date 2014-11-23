package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.json.JSONConstants;
import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentDataFetcher {
    private final int username;
    private final Connection connection;
    public StudentDataFetcher(int username, Connection connection){
        this.username=username;
        this.connection=connection;
    }
    
    public JSONObject getJSON() throws SQLException, JSONException, NoRecordsException {
        JSONObject jsonStudent = new JSONObject();
        String query = String.format("SELECT * FROM %s where %s=%s;",Tables.Students.TABLE_NAME,Tables.Students.ID,String.valueOf(username));
        
        ResultSet resultSet=connection.prepareStatement(query).executeQuery();
        if(!resultSet.next())
            throw new NoRecordsException("No student found with id="+username);
        jsonStudent.put(JSONConstants.USERNAME,resultSet.getInt(Tables.Students.ID));
        jsonStudent.put(JSONConstants.BACKLOGS,resultSet.getBoolean(Tables.Students.BACKLOGS));
        jsonStudent.put(JSONConstants.COURSE,resultSet.getString(Tables.Students.COURSE));
        jsonStudent.put(JSONConstants.MAJORS,resultSet.getString(Tables.Students.MAJORS));
        jsonStudent.put(JSONConstants.NAME,resultSet.getString(Tables.Students.NAME));
        jsonStudent.put(JSONConstants.RESUME_LINK,resultSet.getString(Tables.Students.RESUME_LINK));
        jsonStudent.put(JSONConstants.EMAIL,resultSet.getString(Tables.Students.EMAIL));
        jsonStudent.put(JSONConstants.CGPA,resultSet.getDouble(Tables.Students.CGPA));
        
        return jsonStudent;
    }
}
