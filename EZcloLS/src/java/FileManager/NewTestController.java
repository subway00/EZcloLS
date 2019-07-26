package FileManager;

import model.TestModel;
import model.DBConnectModel;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;

@WebServlet(name = "NewTestController", urlPatterns = {"/NewTestController"})
public class NewTestController extends HttpServlet {

    TestModel tm;
    ArrayList<TestModel> arr;
    DBConnectModel dbcm;
    RequestDispatcher rd;
    String query = "INSERT INTO Test (T_Name, T_Able, F_Number) VALUES ( ?, 1, ?)";
//    String searchquery = "SELECT T_Name, T_Number, T_BuildTime FROM Test WHERE T_Able=1 AND F_Number=? ORDER BY T_Number";
    String searchtestINF = "SELECT T_Name, T.T_Number, T_BuildTime, R_TestTime \n"
            + "FROM FileFolder AS F LEFT JOIN Test AS T\n"
            + "ON F.F_Number = T.F_Number\n"
            + "LEFT JOIN Result AS R\n"
            + "ON T.T_Number = R.T_Number\n"
            + "WHERE F.F_Number=? AND T_Able=1\n"
            + "ORDER BY T_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbcm = new DBConnectModel();
        HttpSession session = request.getSession();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(query);
                    PreparedStatement pstmt2 = con.prepareStatement(searchtestINF);) {
                
                String newtest = request.getParameter("newtest");
                Integer choosefile = (Integer) session.getAttribute("choosefile");
                //new test
                pstmt.setString(1, newtest);
                pstmt.setInt(2, choosefile);
                pstmt.executeUpdate();
                //get T_Name & T_Number & T_BuildTime
                pstmt2.setInt(1, choosefile);
                ResultSet result = pstmt2.executeQuery();
                arr = new ArrayList<>();
                while (result.next()) {
                    String tname = result.getString("T_Name");
                    int tnumber = result.getInt("T_Number");
                    Date tbuildtime = result.getDate("T_BuildTime");
                    Date rtesttime = result.getDate("R_TestTime");
                    arr.add(new TestModel(tnumber, tname, tbuildtime, rtesttime));
                }
                //RequestDispatcher
                request.setAttribute("ableTest", arr);
                rd = request.getRequestDispatcher("/FileManager/AbleTest.jsp");
                rd.forward(request, response);
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
