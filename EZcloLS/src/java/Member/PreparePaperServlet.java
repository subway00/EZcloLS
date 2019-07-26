package Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.DBConnectModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaperModel;

/**
 *
 * @author User
 */
public class PreparePaperServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DBConnectModel dbc = new DBConnectModel();
        String T_Number = request.getParameter("T_Number");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(dbc.getUrl(), dbc.getUser(), dbc.getPw());
            Statement stmt = conn.createStatement();

            String query = "select T_Name,T_Content,T_Letter,F_Number from EZclo.dbo.Test "
                    + "where "
                    + "T_Number = " + T_Number;

            ResultSet rs = stmt.executeQuery(query);
            PaperModel paper = new PaperModel("", "", "", null, -1, -1);

            if (rs != null && rs.next()) {
                paper.setName(rs.getString("T_Name"));
                paper.setContent(rs.getString("T_Content"));
                paper.setLetter(rs.getString("T_Letter"));
                paper.setF_number(Integer.parseInt(rs.getString("F_Number")));
                paper.setT_number(Integer.parseInt(T_Number));
            }
            
            stmt.close();
            conn.close();
            
            request.setAttribute("Paper", paper);

            System.out.println(rs);
            RequestDispatcher rd = request.getRequestDispatcher("/PaperEditor/PaperEditor.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SavePaperServlet.class.getName()).log(Level.SEVERE, null, ex);
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
