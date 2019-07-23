package Member;

import Controller.DBController;
import model.DBConnectModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import model.AccModel;
import model.ContentValues;
import model.EmailModel;
import model.MemberModel;
import tools.utils;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    DBConnectModel dbc;
    private final static int error = -1;
    private final static int noExist = 0;
    private final static int noActive = 1;
    private final static int active = 2;

    private final static int register_work = 0;
    private final static int register_none = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (
                PrintWriter out = response.getWriter();) {

            String email = request.getParameter("email");
            String date = request.getParameter("date");
            System.out.println(date);
            //judge which gender
            String gender = request.getParameter("gender");
            String password = request.getParameter("password");
            MemberModel user = new MemberModel(email, date, password, gender, -1, false);

            int result = checkEmail(user);
            int returnState = register_none;
            boolean returnValue = false;
            if (result == 2) {
                returnState = register_none;
                returnValue = false;
            } else if (result == 1) {
                returnState = register_work;
                returnValue = updateInfo(user);
            } else if (result == 0) {
                returnState = register_work;
                returnValue = createNewAccount( user);
            } else {
                returnState = register_work;
                returnValue = false;
            }

            if (returnState == register_none) {
                utils.setWarning(out, "Email Exist!", "/EZcloLS/Register/Register.jsp");
            } else if (returnValue) {
                EmailModel em = new EmailModel();
                em.setTo(user.getEmail());
                em.setSubject("信箱驗證");
                em.setContext("<a href='http://172.16.13.22:8084/EZcloLS/ActivationServlet?M_Email=" + em.getTo() + "'>驗證連結</a>");
                utils.sendEmail(em);
                utils.setWarning(out, "Register success! Please check email for acount activation.", "/EZcloLS/index/login.jsp");
            } else {
                utils.setWarning(out, "Register Fail!", "/EZcloLS/Register/Register.jsp");
            }
        }

    }

    public int checkEmail(MemberModel user) {

        try {
            DBController dbc = new DBController();
            ResultSet rs = dbc.select("EZclo.dbo.Member", new String[]{"M_Number",  "M_Active"}, "M_Email='" + user.getEmail() + "'");
            boolean result = rs.next();
            
            if (result && rs.getBoolean("M_Active")) {
                user.setM_Number(rs.getInt("M_Number"));
                user.setActive(true);
                return active;
            } else if (result && !rs.getBoolean("M_Active")) {
                user.setM_Number(rs.getInt("M_Number"));
                return noActive;
            } else {
                return noExist;
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return error;
    }

    public boolean updateInfo(MemberModel user) {
        DBController dbc = new DBController();
        ContentValues values = new ContentValues();
        values.putString("M_PW", user.getPwd());
        values.putString("M_Gender", user.getGender());
        values.putString("M_Born", user.getBorn_date_s());
        dbc.update("EZclo.dbo.Member", values, " M_Number= " + user.getM_Number());
        return true;
    }

    public boolean createNewAccount(MemberModel user) {
        DBController dbc = new DBController();
        ContentValues values = new ContentValues();
        values.putString("M_Email", user.getEmail());
        values.putString("M_Born", user.getBorn_date_s());
        values.putString("M_PW", user.getPwd());
        values.putString("M_Gender", user.getGender());
        dbc.insert("EZclo.dbo.Member", values);
 
        return true;
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
