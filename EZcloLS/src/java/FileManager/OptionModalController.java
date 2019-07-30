package FileManager;

import model.IndexProducerModel;
import model.TestModel;
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

@WebServlet(name = "OptionModalController", urlPatterns = {"/OptionModalController"})
public class OptionModalController extends HttpServlet {

    HttpSession session;
    ArrayList<TestModel> arr2;
    IndexProducerModel index;
    ArrayList<String> arr;
    DBConnectModel dbcm;
    String searchfilenum = "SELECT F_Number FROM FileFolder WHERE F_Able=1 AND F_Name=?";
    String searchfile = "SELECT F_Name FROM FileFolder WHERE F_Able=1 AND M_Number=? ORDER BY F_Number";
    String searchtest = "SELECT T_Name, T_Number FROM Test WHERE T_Able=1 AND F_Number=? ORDER BY T_Number";
//    String searchmnumber ="SELECT M_Number FROM Member WHERE M_Email=?";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (
                    Connection con = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
                    PreparedStatement pstmt1 = con.prepareStatement(searchfilenum);
                    PreparedStatement pstmt2 = con.prepareStatement(searchfile);
                    PreparedStatement pstmt3 = con.prepareStatement(searchtest);) {
//                file modal
                int mnumber = (Integer) session.getAttribute("M_Number");
                pstmt2.setInt(1, mnumber);
                ResultSet result = pstmt2.executeQuery();
                arr = new ArrayList<>();
                while (result.next()) {
                    String ablefile = result.getString("F_Name");
                    arr.add(ablefile);
                }
                index = new IndexProducerModel();
                request.setAttribute("ablefile", arr);
                request.setAttribute("IndexProducer", index);
//                click file
//                String clickfile = request.getParameter("clickfile");
//                System.out.println("clickfile  " + clickfile);
//                pstmt1.setString(1, clickfile);
//                ResultSet result2 = pstmt1.executeQuery();
//                int i = 0;
                int fnumber = 0;
                if (request.getParameter("clickfile") != null) {
                    System.out.println("clickfile request call");
                    String clickfile = request.getParameter("clickfile");
                    pstmt1.setString(1, clickfile);
                    ResultSet result2 = pstmt1.executeQuery();
                    while (result2.next()) {
                        fnumber = result2.getInt("F_Number");
                    }
                } else {
                    if (session.getAttribute("choosefile") != null) {
                        fnumber = (Integer) session.getAttribute("choosefile");
                    }
                }
                pstmt3.setInt(1, fnumber);
                //get F_Number
//                i = result2.getInt("F_Number");
//                System.out.println("i為:" + i);
//                test
                //判斷取得session或拿點擊資料夾號碼(試卷增、刪、修，後仍位於以點選的資料夾位置)
//                if (i != 0) {
//                    pstmt3.setInt(1, i);
//                } else {
//                    if (session.getAttribute("choosefile") != null) {
//                        int choosefile = (Integer) session.getAttribute("choosefile");
//                        pstmt3.setInt(1, choosefile);
//                    }
//                }

//else if (i != 0) {
//                    pstmt2.setInt(1, i);
//                }
                ResultSet result3 = pstmt3.executeQuery();
                arr2 = new ArrayList<>();
                while (result3.next()) {
                    int testnumber = result3.getInt("T_Number");
                    String abletest = result3.getString("T_Name");
                    arr2.add(new TestModel(testnumber, abletest));
                }

                request.setAttribute("abletest", arr2);
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
