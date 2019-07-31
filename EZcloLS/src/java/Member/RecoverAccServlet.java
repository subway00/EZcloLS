/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Controller.DBController;
import model.DBConnectModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccModel;
import model.ContentValues;
import model.EmailModel;
import model.MemberModel;
import tools.utils;

/**
 *
 * @author g3863
 */
@WebServlet(name = "RecoverAccServlet", urlPatterns = {"/RecoverAccServlet"})
public class RecoverAccServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        
        DBController dbc = new DBController();
        String email = request.getParameter("email");

        try (
                PrintWriter out = response.getWriter();
                
                ) {
            ResultSet rs = dbc.select("EZclo.dbo.Member", new String[]{"M_Number"}, "M_Email='" + email + "'");
            
            //if account exist
            if (rs.next()) {
                //get uid
                int uid = rs.getInt("M_Number");
                String new_pwd = pwdGenerator(10, 3);

                //update new pd
                ContentValues cv = new ContentValues();
                cv.put(" M_PW", new_pwd);
                dbc.update("EZclo.dbo.Member", cv, " M_Number= " + uid);

                //send email
                EmailModel em = new EmailModel();
                em.setSubject("密碼重置");
                em.setTo(email);
                em.setContext("new password: " + new_pwd);
                utils.sendEmail(em);
            }

            utils.setWarning(out, "Reset success!", "/EZcloLS/index/login.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(RecoverAccServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String pwdGenerator(int radix, int number) {
        String result = "";
        Random random = new Random(System.currentTimeMillis());
        boolean type[] = new boolean[radix];
        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 0; i < radix; i++) {
            type[i] = false;
            order.add(i);
        }

        //set bit type
        for (int i = 0; i < radix; i++) {
            int index = random.nextInt(order.size());
            if (number > 0) {
                type[order.get(index)] = false;
                number--;
            } else {
                type[order.get(index)] = true;
            }
            order.remove(index);
        }

        //set bit value
        for (int i = 0; i < radix; i++) {
            if (type[i]) {
                result = result.concat(Character.toString((char) (random.nextInt(26) + 65)));
            } else {
                result = result.concat(String.valueOf(random.nextInt(10)));
            }

        }
        return result;
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
