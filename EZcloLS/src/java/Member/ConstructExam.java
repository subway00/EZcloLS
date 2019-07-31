/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Controller.DBController;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaperModel;
import model.RecordModel;

/**
 *
 * @author User
 */
public class ConstructExam extends HttpServlet {

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

        //get parameters
        String T_Number = request.getParameter("T_Number");

        //set attributes
        request.setAttribute("Paper", getPaperContext(T_Number));

        //launch web
        RequestDispatcher rd = request.getRequestDispatcher("/PaperExam/ExamPanel.jsp");
        rd.forward(request, response);
    }

    private PaperModel getPaperContext(String T_Number) {
        DBController dbc = new DBController();
        ResultSet rs = dbc.select("EZclo.dbo.Test",
                new String[]{"T_Name", "T_Content", "T_Letter"},
                "T_Number = " + T_Number);

        PaperModel paper = new PaperModel();
        try {
            if (rs != null && rs.next()) {
                paper.setName(rs.getString("T_Name"));
                paper.setContent(rs.getBytes("T_Content"));
                paper.setLetter(rs.getBytes("T_Letter"));
                paper.setT_number(Integer.parseInt(T_Number));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrepareExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paper;
    }

    private RecordModel getExamRecord(String T_Number) {
        DBController dbc = new DBController();
        ResultSet rs = dbc.select("EZclo.dbo.Result",
                new String[]{"R_Number", "R_Right", "R_Wrong", "R_TestTime"},
                "T_Number = " + T_Number);

        RecordModel record = new RecordModel();
        try {
            if (rs != null && rs.next()) {
                record.setNumber(rs.getInt(T_Number));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrepareExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return record;
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
