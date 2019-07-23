package Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import model.DBConnectModel;

@WebServlet(name = "NewTestController", urlPatterns = {"/NewTestController"})
public class NewTestController extends HttpServlet {
    Map<String, String> map;
    DBConnectModel dbc;
    String query = "INSERT INTO Test (T_Name, T_Able) VALUES ( ?, 1)";
    String searchquery = "SELECT T_Number,T_Name FROM Test WHERE T_Able=1 ORDER BY T_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbc = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbc.getUrl(), dbc.getUser(), dbc.getPw());
                    PreparedStatement pstmt = con.prepareStatement(query);) {
                String newtest = request.getParameter("newtest");

                pstmt.setString(1, newtest);
                pstmt.execute();
                try (
                        Statement stmt = con.createStatement();) {
                    ResultSet result = stmt.executeQuery(searchquery);
                    map = new HashMap<>();
                    map = getResult(result);
                    request.setAttribute("map", map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map getResult(ResultSet result) {
        try {
            int i = 0;
            while (result.next()) {
                String name = result.getString("T_Name");
                String number = result.getString("T_Number");
               // String time = result.getString("T_Time");
                map.put(number , name);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
