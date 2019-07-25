package FileManager;

import model.DBConnectModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RenameTestController", urlPatterns = {"/RenameTestController"})
public class RenameTestController extends HttpServlet {

    ArrayList<String> arr;
    String searchquery = "SELECT T_Name FROM Test WHERE T_Able=1 AND F_Number=? ORDER BY T_Number";
    String query = "UPDATE Test SET T_Name=? WHERE T_Number=? AND T_Able='1'";
    DBConnectModel dbcm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(query);
                    PreparedStatement pstmt2 = con.prepareCall(searchquery)) {

                String rename = request.getParameter("rename");
                String thistestnum = request.getParameter("testnumber");
                int tnumber = Integer.parseInt(thistestnum);
                pstmt.setString(1, rename);
                pstmt.setInt(2, tnumber);
                pstmt.executeUpdate();
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
