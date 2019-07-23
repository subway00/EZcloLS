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
import javax.servlet.RequestDispatcher;
import model.DBConnectModel;

@WebServlet(name = "NewFileController", urlPatterns = {"/NewFileController"})
public class NewFileController extends HttpServlet {

    Map<Integer, String> map;
    DBConnectModel dbc;
    String ynrepeatQuery = "SELECT F_Name FROM FileFolder WHERE F_Able=1 AND F_Name=? ORDER BY F_Number";
    String query = "INSERT INTO FileFolder (F_Name, F_Able) VALUES (?, 1)";
    String searchquery = "SELECT F_Name FROM FileFolder WHERE F_Able=1 ORDER BY F_Number";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        dbc = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbc.getUrl(), dbc.getUser(), dbc.getPw());
                    //                    Connection con = dbc.getCon();
                    PreparedStatement pstmt = con.prepareStatement(query);
                    PreparedStatement ynrepeat = con.prepareStatement(ynrepeatQuery);
                    ) {
                String newfile = request.getParameter("newfile");
                
                ynrepeat.setString(1, newfile);
                //查詢結果有相同資料名
                if(! ynrepeat.execute()) {
                    RequestDispatcher rd = request.getRequestDispatcher("/EZcloLS/FileManager/Error.jsp");
                    rd.forward(request, response);
                }
                
                
                pstmt.setString(1, newfile);
                pstmt.execute();
                try (
                        Statement stmt = con.createStatement();) {
                    ResultSet result = stmt.executeQuery(searchquery);
                    map = new HashMap<>();
                    Map<Integer, String> map = getResult(result);
                    Collection <String> value;
//                    value = map.values();
//                    for (String tt : value ) {
//                        System.out.println("-----------");
//                        System.out.println(tt);
//                    }
//                    System.out.println(map);
//                    for (String a : resultArr) {
//                        System.out.println(a);
//                    }
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
                
                
                String name = result.getString("F_Name");
                map.put(i++ ,name);
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
