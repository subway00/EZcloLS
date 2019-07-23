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
import model.MemberModel;

public class MemberController {

    private MemberModel mm;
    private String tb = "EZclo.dbo.Member";
    private String NUMBER = "M_Number";
    private String BORN = "M_Born";
    private String EMAIL = "M_Email";
    private String PWD = "M_PW";
    private String GENDER = "M_Gender";
    private String ACTIVE = "M_Active";

    public MemberController() {
        mm = new MemberModel();
    }
    
    public int identify(String email,String pwd){
        try {
            DBController dbc = new DBController();
            ResultSet rs = dbc.select(tb, new String[]{NUMBER}, EMAIL+"="+email+" and "+PWD+"="+pwd);
            if(rs!=null && rs.next()){
                return rs.getInt(NUMBER);
            }
            dbc.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void getData(int index) {
        try {
            DBController dbc = new DBController();
            ResultSet rs = dbc.select(tb,new String[]{EMAIL,GENDER,BORN}, NUMBER+"="+index);
            mm.setEmail(rs.getString(EMAIL));
            mm.setGender(rs.getString(GENDER));
            mm.setBorn_date(rs.getDate(BORN));
            dbc.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isActive(int index){
        try {
            DBController dbc = new DBController();
            ResultSet rs = dbc.select(tb,new String[]{ACTIVE}, NUMBER+"="+index);
            if(rs!=null && rs.next()){
                return rs.getBoolean(ACTIVE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
