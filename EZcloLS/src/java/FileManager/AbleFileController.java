package FileManager;

import model.IndexProducerModel;
import model.DBConnectModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AbleFileController", urlPatterns = {"/AbleFileController"})
public class AbleFileController extends HttpServlet {

    HttpSession session;
    IndexProducerModel index;
    ArrayList<String> arr;
    DBConnectModel dbcm;
    String searchquery = "SELECT F_Name FROM FileFolder WHERE F_Able=1 AND M_Number=? ORDER BY F_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(searchquery);) {
                //
                System.out.println("AbleFile   M_Number");
                session = request.getSession();
                int mnumber = (Integer) session.getAttribute("M_Number");

                pstmt.setInt(1, mnumber);
                ResultSet result = pstmt.executeQuery();
                arr = new ArrayList<>();
                while (result.next()) {
                    String ablefile = result.getString("F_Name");
                    arr.add(ablefile);
                }
                index = new IndexProducerModel();
                request.setAttribute("ablefile", arr);
                request.setAttribute("IndexProducer", index);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
