package FileManager;

import model.TestModel;
import model.IndexProducerModel;
import model.DBConnectModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    ArrayList<TestModel> arr;
    DBConnectModel dbcm;
    String searchfile = "SELECT F_Number FROM FileFolder WHERE F_Able=1 AND F_Name=?";
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
        session = request.getSession();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(searchfile);
                    PreparedStatement pstmt2 = con.prepareStatement(searchtestINF);) {
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
                arr = new ArrayList<>();
                //get T_Name & T_Number & T_BuildTime & R_TestTime
                pstmt2.setInt(1, filenumber);
                ResultSet result2 = pstmt2.executeQuery();
                while (result2.next()) {
                    int tnumber = result2.getInt("T_Number");
                    String tname = result2.getString("T_Name");
                    Date tbuildtime = result2.getDate("T_BuildTime");
                    Date rtesttime = result2.getDate("R_TestTime");
                    arr.add(new TestModel(tnumber, tname, tbuildtime, rtesttime));
                }
                //get 

                index = new IndexProducerModel();
                request.setAttribute("ableTest", arr);
                request.setAttribute("IndexProducer", index);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void sessionGetSet(HttpServletRequest request) {
        int F_Number = 0;
        F_Number = (Integer) request.getSession().getAttribute("choosefile");
        if (F_Number == 0) {
            System.out.println("No session");
        } else {
            System.out.println("Y session");
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
