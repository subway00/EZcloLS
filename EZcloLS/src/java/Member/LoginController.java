package Member;

import Controller.DBController;
import model.DBConnectModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.ContentValues;
import model.MemberModel;
import tools.utils;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private final String SUCCESS_PATH = "/EZcloLS/FileManager/home.jsp";
    private final String ERROR_PATH = "/EZcloLS/index/login.jsp";
    private String page;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try(PrintWriter out = response.getWriter()) {
            DBController dbc = new DBController();
            ResultSet result = dbc.select("EZclo.dbo.Member",new String[]{"M_Number"}," M_Email='" + email + "' and M_PW='" + password + "' and M_Active='true';");

            if (result != null && result.next()) {
                HttpSession session = request.getSession();
                ContentValues values = new ContentValues();
                int index = result.getInt("M_Number");
                //hidden uid
                //String uid = utils.generatePassCode(index);
                //values.putString("M_Uid",uid);
                //dbc.update("EZclo.dbo.Member", values,"M_Number="+ index);
                session.setAttribute("email", email);
                page = SUCCESS_PATH;
                response.sendRedirect(page);
            } else {
                page = ERROR_PATH;
                utils.setWarning(out, "login fail", page);
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayLoginResult(String password, String passwordValue, PrintWriter out, HttpServletResponse response)
            throws IOException {
        out.println(password);
        out.println(passwordValue);

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
