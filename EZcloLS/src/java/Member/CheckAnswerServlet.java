package Member;

import Controller.DBController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContentValues;
import model.PaperModel;
import org.json.JSONObject;

public class CheckAnswerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PaperModel pm = new PaperModel();
        JSONObject jo = new JSONObject();
        DBController dbc = new DBController();

        response.setContentType("text/html;charset=UTF-8");
        //String[] jsonData = request.getParameterValues("json[]");
        String[] input = request.getParameterValues("input");
        String[] comp = request.getParameterValues("comp");
        String num = request.getParameter("number");

        boolean[] result = new boolean[input.length];

        int p_count = 0;
        int right = 0;
        int wrong = 0;
        int blk = 0;
        int t_right = 0;
        int t_blk = 0;
        float rate = 0f;

        for (int i = 0; i < input.length && i < comp.length; i++) {
            result[i] = input[i].equals(comp[i]);
            if (result[i]) {
                right++;
            } else {
                wrong++;
            }
        }
        blk = right + wrong;

        ContentValues cv = new ContentValues();
        cv.put("R_Right", right);
        cv.put("R_Wrong", wrong);
        cv.put("T_Number", Integer.parseInt(num));

        dbc.insert("EZclo.dbo.Result", cv);

        //records
        ResultSet rs = dbc.select("EZclo.dbo.Result", new String[]{"R_Right", "R_Wrong"}, "T_Number=" + num);

        try {
            while (rs != null && rs.next()) {
                p_count++;
                t_right += rs.getInt("R_Right");
                t_blk += rs.getInt("R_Right") + rs.getInt("R_Wrong");
            }
            rate = t_right * 100f / t_blk;

        } catch (SQLException ex) {
            Logger.getLogger(CheckAnswerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        jo.append("P_Count", p_count);
        jo.append("Score", right + "/" + blk);
        jo.append("T_Rate", String.format("%.2f", rate));
        jo.append("T_Right", t_right);
        jo.append("T_Block", t_blk);
        jo.append("Result", result);

        try (PrintWriter out = response.getWriter()) {
            String json = jo.toString();
            out.print(json);

            out.flush();
            out.close();
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
