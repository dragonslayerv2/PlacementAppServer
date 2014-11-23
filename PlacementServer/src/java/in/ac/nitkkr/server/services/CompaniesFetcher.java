package in.ac.nitkkr.server.services;

import in.ac.nitkkr.server.json.JSONConstants;
import in.ac.nitkkr.server.database.Tables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompaniesFetcher {
    private final Connection connection;
    public CompaniesFetcher(Connection connection){
        this.connection=connection;
    }
    
    public JSONArray getJSON() throws SQLException, JSONException, NoRecordsException {
        String query = String.format("SELECT * FROM %s;", Tables.Company.TABLE_NAME);
        
        ResultSet resultSet=connection.prepareStatement(query).executeQuery();
        JSONArray companyArray = new JSONArray();
        int position=0;
        while(resultSet.next()){
            JSONObject jsonCompany = new JSONObject();
            jsonCompany.put(JSONConstants.ID,resultSet.getInt(Tables.Company.ID));
            jsonCompany.put(JSONConstants.NAME,resultSet.getString(Tables.Company.NAME));
            jsonCompany.put(JSONConstants.DESCRIPTION,resultSet.getString(Tables.Company.DESCRIPTION));
            jsonCompany.put(JSONConstants.LOGO_URL,resultSet.getString(Tables.Company.LOGO_URL));
            jsonCompany.put(JSONConstants.URL,resultSet.getString(Tables.Company.URL));
            companyArray.put(position++,jsonCompany);
        }
        return companyArray;
    }
}
