/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAccModel {

    private String email;
    private Date born_date;
    private String pwd;
    private String gender;
    private int M_Number;

    public UserAccModel() {
    }

    public UserAccModel(String email, Date born_date, String pwd, String gender, int M_Number) {
        this.email = email;
        this.born_date = born_date;
        this.pwd = pwd;
        this.gender = gender;
        this.M_Number = M_Number;
    }

    public UserAccModel(String email, String born_date, String pwd, String gender,int M_Number) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.email = email;
        
        try {
            this.born_date = sdf.parse(born_date);
        } catch (ParseException ex) {
            this.born_date = null;
            Logger.getLogger(UserAccModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.pwd = pwd;
        this.gender = gender;
        this.M_Number = M_Number;
    }

    public int getM_Number() {
        return M_Number;
    }

    public void setM_Number(int M_Number) {
        this.M_Number = M_Number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getBorn_date() {
        return born_date;
    }
    
    public String getBorn_date_s() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(born_date);
    }

    public String getPwd() {
        return pwd;
    }

    public String getGender() {
        return gender;
    }

}
