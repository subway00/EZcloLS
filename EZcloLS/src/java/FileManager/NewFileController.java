package FileManager;

import model.DBConnectModel;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "NewFileController", urlPatterns = {"/NewFileController"})
public class NewFileController extends HttpServlet {

    HttpSession session;
    Map<Integer, String> map;
    DBConnectModel dbcm;

    String query = "INSERT INTO FileFolder (F_Name, F_Able, M_Number) VALUES (?, 1, ?)";
    String searchquery = "SELECT F_Name FROM FileFolder WHERE F_Able=1 AND M_Number=? ORDER BY F_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(query);
                    PreparedStatement pstmt2 = con.prepareStatement(searchquery);) {
                String newfile = request.getParameter("newfile");
                pstmt.setString(1, newfile);
                //get session
                session = request.getSession();
                int mnumber = (Integer) session.getAttribute("M_Number");
                //new file
                pstmt.setInt(2, mnumber);
                pstmt.execute();
                //select file
                pstmt2.setInt(1, mnumber);
                ResultSet result = pstmt2.executeQuery(searchquery);
                map = new HashMap<>();
                Map<Integer, String> map = getResult(result);
                request.setAttribute("map", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map getResult(ResultSet result) {
        try {
            int i = 0;
            while (result.next()) {

                String name = result.getString("F_Name");
                map.put(i++, name);
                //System.out.println("name------" + name);
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
