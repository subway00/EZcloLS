package FileManager;

import model.AccountStorageModel;
import org.json.JSONObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBConnectModel;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AccountInf", urlPatterns = {"/AccountInfController"})
public class AccountInfController extends HttpServlet {

    JSONObject jsonObj;
    AccountStorageModel accs;
    HttpSession session;
    DBConnectModel dbcm;
//    String selectmember = "SELECT M_Email, M_Gender, M_Born FROM Member WHERE M_Number=?";
    String searchAccInf = "SELECT M_Email, M_Gender, M_Born, \n"
            + "COUNT(R.R_Number)  AS 'FinishTest',\n"
            + "SUM(R.R_Wrong) + SUM(R.R_Right) AS 'TotalQuestions',\n"
            + "SUM(R.R_Right) AS 'RightCount',\n"
            + "ROUND(CONVERT(float, SUM(R.R_Right))/ SUM(R.R_Wrong + R.R_Right) *100, 2) AS 'RightRate'\n"
            + "FROM Member AS M LEFT JOIN FileFolder AS F\n"
            + "ON M.M_Number=F.M_Number\n"
            + "LEFT JOIN Test AS T\n"
            + "ON F.F_Number=T.F_Number\n"
            + "LEFT JOIN Result AS R\n"
            + "ON T.T_Number=R.T_Number\n"
            + "WHERE M.M_Number=? \n"
            + "GROUP BY M_Email, M_Gender, M_Born\n";
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        dbcm = new DBConnectModel();
        //JSON
        response.setContentType("application/json");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt = con.prepareStatement(searchAccInf);) {
                session = request.getSession();
                int mnumber = (Integer) session.getAttribute("M_Number");
                pstmt.setInt(1, mnumber);
                ResultSet result = pstmt.executeQuery();

                while (result.next()) {
                    Map map = new HashMap();
                    String mEmail = result.getString("M_Email");
                    String mGender = result.getString("M_Gender");
                    Date mBorn = result.getDate("M_Born");
                    int finishTest = result.getInt("FinishTest");
                    int totalQuestions = result.getInt("TotalQuestions");
                    int rightCount = result.getInt("RightCount");
                    float rightRate = result.getFloat("RightRate");

                    map.put("M_Email", mEmail);
                    map.put("M_Gender", mGender);
                    map.put("M_Born", mBorn);
                    map.put("FinishTest", finishTest);
                    map.put("TotalQuestions", totalQuestions);
                    map.put("RightCount", rightCount);
                    map.put("RightRate", rightRate);

                    jsonObj = new JSONObject(map);
                    out.print(jsonObj);
                }
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
