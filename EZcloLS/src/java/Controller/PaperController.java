/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PaperModel;

public class PaperController {
    
    private PaperModel pm;
    private String tb = "EZclo.dbo.Testing";
    private String NUMBER = "T_Number";
    private String NAME = "T_Name";
    private String CONTENT = "T_Content";
    private String LETTER = "T_Letter";
    private String DATETIME = "T_DateTime";

    public PaperController() {
        pm = new PaperModel();
    }

    public void getData(int index) {
        try {
            DBController dbc = new DBController();
            ResultSet rs = dbc.select(tb, new String[]{NAME,CONTENT,LETTER,DATETIME}, NUMBER+"="+index);
            if(rs!=null && rs.next()){
                pm.setName(rs.getString(NAME));
                pm.setContent(rs.getString(CONTENT));
                pm.setLetter(rs.getString(LETTER));
                pm.setTime(rs.getDate(DATETIME));
                pm.setT_number(index);
            }
            dbc.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(PaperController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
