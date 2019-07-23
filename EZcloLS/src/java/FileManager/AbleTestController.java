package FileManager;

import model.IndexProducerModel;
import model.DBConnectModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AbleTestController", urlPatterns = {"/AbleTestController"})
public class AbleTestController extends HttpServlet {

    HttpSession session;
    IndexProducerModel index;
    ArrayList<String> arr;
    DBConnectModel dbcm;
    String searchfile = "SELECT F_Number FROM FileFolder WHERE F_Able=1 AND F_Name=?";
    String searchquery = "SELECT T_Name FROM Test WHERE T_Able=1 AND F_Number=? ORDER BY T_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbcm = new DBConnectModel();
        session = request.getSession();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(searchfile);
                    PreparedStatement pstmt2 = con.prepareStatement(searchquery)) {
                String selectfile = request.getParameter("clickfile");
                pstmt.setString(1, selectfile);
                ResultSet result = pstmt.executeQuery();
                //filenumber initial
                int filenumber = 0;
                while (result.next()) {
                    filenumber = result.getInt("F_Number");
                }
                //set session
                session.setAttribute("choosefile", filenumber);
                pstmt2.setInt(1, filenumber);
                ResultSet result2 = pstmt2.executeQuery();
                arr = new ArrayList<>();
                while (result2.next()) {
                    String ablefile = result2.getString("T_Name");
                    arr.add(ablefile);
                }
                index = new IndexProducerModel();
                request.setAttribute("ableTest", arr);
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
