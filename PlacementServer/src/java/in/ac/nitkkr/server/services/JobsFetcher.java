package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.json.JSONConstants;
import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JobsFetcher {
    private final int username;
    private final Connection connection;
    public JobsFetcher(int username, Connection connection){
        this.username=username;
        this.connection=connection;
    }
    
    public JSONArray getJSON() throws SQLException, JSONException, NoRecordsException {
        String query = String.format("SELECT * FROM %s;",
                Tables.JOB.TABLE_NAME);
        
        ResultSet resultSet=connection.prepareStatement(query).executeQuery();
        JSONArray jobsArray = new JSONArray();
        
        int position=0;
        while(resultSet.next()){
            JSONObject jsonJob = new JSONObject();
            int jobId = resultSet.getInt(Tables.JOB.ID);
            
            jsonJob.put(JSONConstants.ID, jobId);
            jsonJob.put(JSONConstants.COMPANY_ID, resultSet.getInt(Tables.JOB.COMPANY_ID));
            jsonJob.put(JSONConstants.DESCRIPTION, resultSet.getString(Tables.JOB.DESCRIPTION));
            jsonJob.put(JSONConstants.ELIGIBILITY_CRITERIA, resultSet.getString(Tables.JOB.ELIGIBILITY_CRITERIA));
            jsonJob.put(JSONConstants.LOCATION, resultSet.getString(Tables.JOB.LOCATION));
            jsonJob.put(JSONConstants.PACKAGE, resultSet.getString(Tables.JOB.PACKAGE));
            jsonJob.put(JSONConstants.PACKAGE_STATUS, resultSet.getString(Tables.JOB.PACKAGE_STATUS));
            jsonJob.put(JSONConstants.POSTED_DATE, resultSet.getString(Tables.JOB.POSTED_DATE));
            jsonJob.put(JSONConstants.PROFILE, resultSet.getString(Tables.JOB.PROFILE));
       
            String statusQuery = String.format("SELECT * FROM %s where %s=%s AND %s=%s", 
                    Tables.ELIGIBLE.TABLE_NAME, 
                    Tables.ELIGIBLE.JOB_ID,
                    String.valueOf(jobId),
                    Tables.ELIGIBLE.STUDENT_ID,
                    String.valueOf(username));
            ResultSet statusResultSet = connection.prepareStatement(statusQuery).executeQuery();
            
            boolean eligible=false,applied=false;
            String status="Not Applied";
            
            if(!statusResultSet.next()){
                eligible=true;
                applied=statusResultSet.getBoolean(Tables.ELIGIBLE.APPLIED);
                String statusTemp=statusResultSet.getString(Tables.ELIGIBLE.STATUS);
                if(statusTemp!=null)
                   status=statusTemp;
                else
                   status="Applied";
            }
            
            jsonJob.put(JSONConstants.ELIGIBLE,eligible);
            jsonJob.put(JSONConstants.APPLIED,applied);
            jsonJob.put(JSONConstants.STATUS,status);
            jobsArray.put(position++,jsonJob);
        }
        return jobsArray;
    }
}
