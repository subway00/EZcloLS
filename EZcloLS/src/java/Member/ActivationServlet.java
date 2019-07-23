/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import model.DBConnectModel;
import tools.utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberModel;

/**
 *
 * @author g3863
 */
@WebServlet(name = "ActivationServlet", urlPatterns = {"/ActivationServlet"})
public class ActivationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnectModel dbc = new DBConnectModel();
        
        try (PrintWriter out = response.getWriter()) {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(dbc.getUrl(), dbc.getUser(), dbc.getPw());
            Statement stmt = con.createStatement();
            String email = request.getParameter("M_Email");
            MemberModel user = new MemberModel();
            user.setEmail(email);

            if (updateInfo(con, stmt, user)) {
                utils.setWarning(out, "Active success!", "/EZcloLS/index/login.jsp");
            } else {
                utils.setWarning(out, "Active fail!", "/EZcloLS/Register/Register.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    
    

    public boolean updateInfo(Connection conn, Statement stmt, MemberModel user) {
        String query = "Update EZclo.dbo.Member set "
                + " M_Active= 1"
                + " where "
                + " M_Email= '" + user.getEmail() + "' ";

        System.out.println(query);

        try {
            stmt.execute(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
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
