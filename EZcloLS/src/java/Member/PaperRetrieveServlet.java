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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaperModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class PaperRetrieveServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DBConnectModel dbcs = new DBConnectModel();

        String F_Number = request.getParameter("F_Number");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String query = "select T_Name,T_BuildTime,F_Number,T_Number from EZclo.dbo.Test "
                    + "where "
                    + "F_Number=" + F_Number;

            Connection conn = DriverManager.getConnection(dbcs.getUrl(), dbcs.getUser(), dbcs.getPw());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<PaperModel> papers = new ArrayList<>();

            while (rs!=null && rs.next()) {
                PaperModel paper = new PaperModel(
                        rs.getString("T_Name"),
                        "",
                        "",
                        rs.getDate("T_BuildTime"),
                        rs.getInt("F_Number"),
                        rs.getInt("T_Number")
                );
                papers.add(paper);
            }
            stmt.close();
            conn.close();

            JSONObject jo = new JSONObject();
            jo.append("Papers", papers);
            String json = jo.toString();
            out.print(json);

            out.flush();
            out.close();

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
