package in.ac.nitkkr.server.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import in.ac.nitkkr.server.services.NotificationsFetcher;
import in.ac.nitkkr.server.json.JSONConstants;
import in.ac.nitkkr.server.database.ConnectionManager;
import in.ac.nitkkr.server.services.Authenticator;
import in.ac.nitkkr.server.services.NoRecordsException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Shobhit Saxena
 */
public class GetNotificationsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, SQLException, NoRecordsException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject userJSON = new JSONObject(request.getParameter(JSONConstants.JSON_REQUEST_PARAMETER));
            int username = userJSON.getInt(JSONConstants.USERNAME);
            String password = userJSON.getString(JSONConstants.PASSWORD);
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            
            Authenticator authenticator = new Authenticator(connection);
            boolean isAuthorized = authenticator.isAuthorized(username, password);

            JSONObject responseObject = new JSONObject();
            responseObject.put(JSONConstants.IS_AUTHORIZED, isAuthorized);
            if (isAuthorized) {
                responseObject.put(JSONConstants.NOTIFICATIONS, (new NotificationsFetcher(username, connection)).getJSON());
            }
            
            out.print(responseObject);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoRecordsException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoRecordsException ex) {
            Logger.getLogger(GetNotificationsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
