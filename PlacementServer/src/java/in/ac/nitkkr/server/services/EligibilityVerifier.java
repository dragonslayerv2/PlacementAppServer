
package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EligibilityVerifier {
    
    public static boolean isEligible(int username, int jobId, Connection connection) throws SQLException{
        String statusQuery = String.format("SELECT * FROM %s where %s=%s AND %s=%s", 
                    Tables.ELIGIBLE.TABLE_NAME, 
                    Tables.ELIGIBLE.JOB_ID,
                    String.valueOf(jobId),
                    Tables.ELIGIBLE.STUDENT_ID,
                    String.valueOf(username));
        ResultSet statusResultSet = connection.prepareStatement(statusQuery).executeQuery();
        return statusResultSet.next();
    }
    
    public static boolean hasApplied(int username, int jobId, Connection connection) throws SQLException{
        String statusQuery = String.format("SELECT * FROM %s where %s=%s AND %s=%s", 
                    Tables.ELIGIBLE.TABLE_NAME, 
                    Tables.ELIGIBLE.JOB_ID,
                    String.valueOf(jobId),
                    Tables.ELIGIBLE.STUDENT_ID,
                    String.valueOf(username));
        ResultSet statusResultSet = connection.prepareStatement(statusQuery).executeQuery();
        if(!statusResultSet.next())
            return false;
        else 
            return statusResultSet.getBoolean(Tables.ELIGIBLE.APPLIED);
    }
    
    
}
