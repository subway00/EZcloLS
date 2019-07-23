package FileManager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBConnectModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteTestController", urlPatterns = {"/DeleteTestController"})
public class DeleteTestController extends HttpServlet {

    ArrayList<String> arr;
    DBConnectModel dbcm;
    String deletequery = "UPDATE Test SET T_Able=0 WHERE T_Number=? AND T_Able=1";
    String searchquery = "SELECT T_Name FROM Test WHERE T_Able=1 AND F_Number=? ORDER BY T_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbcm = new DBConnectModel();
        HttpSession session = request.getSession();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt1 = con.prepareStatement(deletequery);
                    PreparedStatement pstmt2 = con.prepareStatement(searchquery)) {
                String testnumber = request.getParameter("testnumber");
                //delete test
                int tnumber = Integer.parseInt(testnumber);
                pstmt1.setInt(1, tnumber);
                pstmt1.executeUpdate();
                //display test
                Integer choosefile = (Integer) session.getAttribute("choosefile");
                pstmt2.setInt(1, choosefile);
                ResultSet result = pstmt2.executeQuery();
                arr = new ArrayList<>();
                while (result.next()) {
                    String tname = result.getString("T_Name");
                    arr.add(tname);
                }
                request.setAttribute("displayTest", arr);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
