/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ContentValues;
import model.DBConnectModel;

public class DBController {

    private DBConnectModel dbcm;
    private Connection conn;
    private PreparedStatement pstmt;

    public DBController() {
        dbcm = new DBConnectModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection(dbcm.getUrl(), dbcm.getUser(), dbcm.getPw());

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
            String query = "insert into " + Table + " (" + values.getKeyString() + ") values ("
                    + getBlock(values.getCount()) + ");";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            charge(pstmt, values);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String Table, ContentValues values, String limit) {
        try {
            String query = "update " + Table + " set " + getBlock(values) + " where " + limit + ";";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            charge(pstmt, values);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet select(String Table, String[] values, String limit) {
        try {
            String query = "select " + String.join(",", values) + " from " + Table + " where " + limit + ";";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void closeDB() {
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getBlock(int size) {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = result.concat("?");
            if (i < size - 1) {
                result = result.concat(",");
            }
        }
        return result;
    }

    private String getBlock(ContentValues values) {
        String result = "";
        ArrayList<String> keys = values.getKeys();

        int index = 1;
        for (String key : keys) {
            result = result.concat(key);
            result = result.concat("=?");
            if (index < keys.size()) {
                result = result.concat(",");
            }
            index++;
        }
        return result;
    }

    private void charge(PreparedStatement pstmt, ContentValues values) throws SQLException {
        int index = 1;
        for (Object obj : values.getValues()) {
            if (obj instanceof String) {
                pstmt.setString(index, (String) obj);
            } else if (obj instanceof Integer) {
                pstmt.setInt(index, (Integer) obj);
            } else if (obj instanceof byte[]) {
                pstmt.setBytes(index, (byte[]) obj);
            } else {
                pstmt.setObject(index, obj);
            }
            index++;
        }
    }

}
