package FileManager;

import model.DBConnectModel;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewFileCheck", urlPatterns = {"/NewFileCheck"})
public class NewFileCheck extends HttpServlet {

    ArrayList<String> arr;
    DBConnectModel dbcm;
    String ynrepeatQuery = "SELECT F_Name FROM FileFolder WHERE F_Able=1 AND F_Name=? ORDER BY F_Number";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dbcm = new DBConnectModel();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement ynrepeat = con.prepareStatement(ynrepeatQuery);) {
                String newfile = request.getParameter("newfile");
                if (newfile == "") {
                    out.write("ERROR");
                }
                String renamefile = request.getParameter("renamefile");
                //若傳入renamefile參數 擇設給newfile 做判斷
                if (renamefile != null) {
                    newfile = renamefile;
                }
                System.out.println("-------------------------------------");
                System.out.println("NewFileName     " + newfile);
                ynrepeat.setString(1, newfile);
                //查詢結果有相同資料名
                ResultSet result = ynrepeat.executeQuery();
                arr = new ArrayList<>();
                while (result.next()) {
                    String reString = result.getString("F_Name");
                    arr.add(reString);
                }
                if (arr.contains(newfile)) {
                    out.write("ERROR");
                } else {
                    out.write("SUCCESS");
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
