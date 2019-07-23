/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ContentValues;
import model.DBConnectModel;

public class DBController {

    private DBConnectModel dbcm;
    private Statement stmt;
    private Connection conn;

    public DBController() {
        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());
            stmt = conn.createStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert(String Table, ContentValues values) {
        try {
            String query = "insert into " + Table + " (" + values.getKeys() + ") values (" + values.getValues() + ");";
            System.out.println(query);
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String Table, ContentValues values, String limit) {
        try {
            String query = "update " + Table + " set " + values.getTable() + " where " + limit + ";";
            System.out.println(query);
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet select(String Table, String[] values, String limit) {
        try {
            String query = "select " + String.join(",", values) + " from " + Table + " where " + limit + ";";
            System.out.println(query);
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void closeDB(){
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
